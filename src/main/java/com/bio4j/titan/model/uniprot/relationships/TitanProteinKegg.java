package com.bio4j.titan.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinKegg;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.nodes.TitanKegg;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 7/4/2014.
 */
public class TitanProteinKegg extends
		TitanRelationship<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanProteinKegg, TitanProteinKegg.TitanProteinKeggType,
				TitanKegg, TitanKegg.TitanKeggType
				>
		implements
		ProteinKegg<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanProteinKegg, TitanProteinKegg.TitanProteinKeggType,
				TitanKegg, TitanKegg.TitanKeggType
				> {


	TitanProteinKegg(TitanEdge edge, TitanUniprotGraph uniprotGraph) {

		super(edge);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanProteinKeggType type() {

		return uniprotGraph.proteinKeggT;
	}

	public static final class TitanProteinKeggType
			implements
			TitanRelationship.Type<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinKegg, TitanProteinKeggType,
					TitanKegg, TitanKegg.TitanKeggType
					>,
			UniprotGraph.ProteinKeggType<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinKegg, TitanProteinKeggType,
					TitanKegg, TitanKegg.TitanKeggType
					> {

		TitanUniprotGraph uniprotGraph;

		public TitanProteinKeggType(TitanUniprotGraph uniprotGraph) {

			this.uniprotGraph = uniprotGraph;
		}

		@Override
		public TitanLabel label() {
			return uniprotGraph.proteinKeggLabel;
		}

		@Override
		public TitanProteinKeggType value() {
			return uniprotGraph.proteinKeggT;
		}

		@Override
		public TitanProtein.TitanProteinType sourceType() {
			return uniprotGraph.proteinT;
		}

		@Override
		public TitanKegg.TitanKeggType targetType() {
			return uniprotGraph.keggT;
		}

		@Override
		public TitanProteinKegg fromTitanEdge(TitanEdge edge) {
			return new TitanProteinKegg(edge, uniprotGraph);
		}
	}
}


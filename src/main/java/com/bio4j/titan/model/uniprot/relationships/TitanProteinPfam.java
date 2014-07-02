package com.bio4j.titan.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph.ProteinPfamType;
import com.bio4j.model.uniprot.relationships.ProteinPfam;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.nodes.TitanPfam;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 7/2/2014.
 */
public class TitanProteinPfam extends
		TitanRelationship<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanProteinPfam, TitanProteinPfam.TitanProteinPfamType,
				TitanPfam, TitanPfam.TitanPfamType
				>
		implements
		ProteinPfam<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanProteinPfam, TitanProteinPfam.TitanProteinPfamType,
				TitanPfam, TitanPfam.TitanPfamType
				> {


	TitanProteinPfam(TitanEdge edge, TitanUniprotGraph uniprotGraph) {

		super(edge);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanProteinPfamType type() {

		return uniprotGraph.proteinPfamT;
	}

	public static final class TitanProteinPfamType
			implements
			TitanRelationship.Type<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinPfam, TitanProteinPfam.TitanProteinPfamType,
					TitanPfam, TitanPfam.TitanPfamType
					>,
			ProteinPfamType<
								TitanProtein, TitanProtein.TitanProteinType,
								TitanProteinPfam, TitanProteinPfamType,
								TitanPfam, TitanPfam.TitanPfamType
								> {

		TitanUniprotGraph uniprotGraph;

		public TitanProteinPfamType(TitanUniprotGraph uniprotGraph) {

			this.uniprotGraph = uniprotGraph;
		}

		@Override
		public TitanLabel label() {
			return uniprotGraph.proteinPfamLabel;
		}

		@Override
		public TitanProteinPfamType value() {
			return uniprotGraph.proteinPfamT;
		}

		@Override
		public TitanProtein.TitanProteinType sourceType() {
			return uniprotGraph.proteinT;
		}

		@Override
		public TitanPfam.TitanPfamType targetType() {
			return uniprotGraph.pfamT;
		}

		@Override
		public TitanProteinPfam fromTitanEdge(TitanEdge edge) {
			return new TitanProteinPfam(edge, uniprotGraph);
		}
	}
}


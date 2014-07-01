package com.bio4j.titan.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinInterpro;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.nodes.TitanInterpro;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 7/1/2014.
 */
public class TitanProteinInterpro extends
		TitanRelationship<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanProteinInterpro, TitanProteinInterpro.TitanProteinInterproType,
				TitanInterpro, TitanInterpro.TitanInterproType
				>
		implements
		ProteinInterpro<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanProteinInterpro, TitanProteinInterpro.TitanProteinInterproType,
				TitanInterpro, TitanInterpro.TitanInterproType
				> {


	TitanUniprotGraph uniprotGraph;

	TitanProteinInterpro(TitanEdge edge, TitanUniprotGraph uniprotGraph) {

		super(edge);
		this.uniprotGraph = uniprotGraph;
	}

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanProteinInterproType type() {

		return uniprotGraph.proteinInterproT;
	}

	public static final class TitanProteinInterproType
			implements
			TitanRelationship.Type<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinInterpro, TitanProteinInterpro.TitanProteinInterproType,
					TitanInterpro, TitanInterpro.TitanInterproType
					>,
			UniprotGraph.ProteinInterproType<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinInterpro, TitanProteinInterproType,
					TitanInterpro, TitanInterpro.TitanInterproType
					> {

		TitanUniprotGraph uniprotGraph;

		public TitanProteinInterproType(TitanUniprotGraph uniprotGraph) {

			this.uniprotGraph = uniprotGraph;
		}

		@Override
		public TitanLabel label() {
			return uniprotGraph.proteinInteproLabel;
		}

		@Override
		public TitanProteinInterproType value() {
			return uniprotGraph.proteinInterproT;
		}

		@Override
		public TitanProtein.TitanProteinType sourceType() {
			return uniprotGraph.proteinT;
		}

		@Override
		public TitanInterpro.TitanInterproType targetType() {
			return uniprotGraph.interproT;
		}

		@Override
		public TitanProteinInterpro fromTitanEdge(TitanEdge edge) {
			return new TitanProteinInterpro(edge, uniprotGraph);
		}
	}
}


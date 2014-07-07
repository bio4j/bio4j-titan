package com.bio4j.titan.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinUniGene;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.bio4j.titan.model.uniprot.nodes.TitanUniGene;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 7/7/2014.
 */
public class TitanProteinUniGene extends
		TitanRelationship<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanProteinUniGene, TitanProteinUniGene.TitanProteinUniGeneType,
				TitanUniGene, TitanUniGene.TitanUniGeneType
				>
		implements
		ProteinUniGene<
						TitanProtein, TitanProtein.TitanProteinType,
						TitanProteinUniGene, TitanProteinUniGene.TitanProteinUniGeneType,
						TitanUniGene, TitanUniGene.TitanUniGeneType
						> {


	TitanProteinUniGene(TitanEdge edge, TitanUniprotGraph uniprotGraph) {

		super(edge);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanProteinUniGeneType type() {

		return uniprotGraph.proteinUniGeneT;
	}

	public static final class TitanProteinUniGeneType
			implements
			TitanRelationship.Type<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinUniGene, TitanProteinUniGene.TitanProteinUniGeneType,
					TitanUniGene, TitanUniGene.TitanUniGeneType
					>,
			UniprotGraph.ProteinUniGeneType <
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinUniGene, TitanProteinUniGeneType,
					TitanUniGene, TitanUniGene.TitanUniGeneType
					> {

		TitanUniprotGraph uniprotGraph;

		public TitanProteinUniGeneType(TitanUniprotGraph uniprotGraph) {

			this.uniprotGraph = uniprotGraph;
		}

		@Override
		public TitanLabel label() {
			return uniprotGraph.proteinUniGeneLabel;
		}

		@Override
		public TitanProteinUniGeneType value() {
			return uniprotGraph.proteinUniGeneT;
		}

		@Override
		public TitanProtein.TitanProteinType sourceType() {
			return uniprotGraph.proteinT;
		}

		@Override
		public TitanUniGene.TitanUniGeneType targetType() {
			return uniprotGraph.uniGeneT;
		}

		@Override
		public TitanProteinUniGene fromTitanEdge(TitanEdge edge) {
			return new TitanProteinUniGene(edge, uniprotGraph);
		}
	}
}


package com.bio4j.titan.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph.ProteinOrganismType;
import com.bio4j.model.uniprot.relationships.ProteinOrganism;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.nodes.TitanOrganism;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 6/27/2014.
 */
public class TitanProteinOrganism extends
		TitanRelationship<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanProteinOrganism, TitanProteinOrganism.TitanProteinOrganismType,
				TitanOrganism, TitanOrganism.TitanOrganismType
				>
		implements
		ProteinOrganism<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanProteinOrganism, TitanProteinOrganism.TitanProteinOrganismType,
				TitanOrganism, TitanOrganism.TitanOrganismType
				> {


	TitanProteinOrganism(TitanEdge edge, TitanUniprotGraph uniprotGraph) {

		super(edge);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanProteinOrganismType type() {

		return uniprotGraph.proteinOrganismT;
	}

	public static final class TitanProteinOrganismType
			implements
			TitanRelationship.Type<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinOrganism, TitanProteinOrganism.TitanProteinOrganismType,
					TitanOrganism, TitanOrganism.TitanOrganismType
					>,
			ProteinOrganismType<
								TitanProtein, TitanProtein.TitanProteinType,
								TitanProteinOrganism, TitanProteinOrganismType,
								TitanOrganism, TitanOrganism.TitanOrganismType
								> {

		TitanUniprotGraph uniprotGraph;

		public TitanProteinOrganismType(TitanUniprotGraph uniprotGraph) {

			this.uniprotGraph = uniprotGraph;
		}

		@Override
		public TitanLabel label() {
			return uniprotGraph.proteinOrganismLabel;
		}

		@Override
		public TitanProteinOrganismType value() {
			return uniprotGraph.proteinOrganismT;
		}

		@Override
		public TitanProtein.TitanProteinType sourceType() {
			return uniprotGraph.proteinT;
		}

		@Override
		public TitanOrganism.TitanOrganismType targetType() {
			return uniprotGraph.organismT;
		}

		@Override
		public TitanProteinOrganism fromTitanEdge(TitanEdge edge) {
			return new TitanProteinOrganism(edge, uniprotGraph);
		}
	}
}


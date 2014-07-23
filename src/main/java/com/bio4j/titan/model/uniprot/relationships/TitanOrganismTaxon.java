package com.bio4j.titan.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.OrganismTaxon;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.nodes.TitanOrganism;
import com.bio4j.titan.model.uniprot.nodes.TitanTaxon;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 7/10/2014.
 */
public class TitanOrganismTaxon extends
		TitanRelationship<
				TitanOrganism, TitanOrganism.TitanOrganismType,
				TitanOrganismTaxon, TitanOrganismTaxon.TitanOrganismTaxonType,
				TitanTaxon, TitanTaxon.TitanTaxonType
				>
		implements
		OrganismTaxon<
				TitanOrganism, TitanOrganism.TitanOrganismType,
				TitanOrganismTaxon, TitanOrganismTaxon.TitanOrganismTaxonType,
				TitanTaxon, TitanTaxon.TitanTaxonType
				> {


	TitanUniprotGraph uniprotGraph;

	TitanOrganismTaxon(TitanEdge edge, TitanUniprotGraph uniprotGraph) {

		super(edge);
		this.uniprotGraph = uniprotGraph;
	}

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanOrganismTaxonType type() {

		return uniprotGraph.organismTaxonT;
	}

	public static final class TitanOrganismTaxonType
			implements
			TitanRelationship.Type<
					TitanOrganism, TitanOrganism.TitanOrganismType,
					TitanOrganismTaxon, TitanOrganismTaxonType,
					TitanTaxon, TitanTaxon.TitanTaxonType
					>,
			UniprotGraph.OrganismTaxonType<
											TitanOrganism, TitanOrganism.TitanOrganismType,
								TitanOrganismTaxon, TitanOrganismTaxonType,
								TitanTaxon, TitanTaxon.TitanTaxonType
											> {

		TitanUniprotGraph uniprotGraph;

		public TitanOrganismTaxonType(TitanUniprotGraph uniprotGraph) {

			this.uniprotGraph = uniprotGraph;
		}

		@Override
		public TitanLabel label() {
			return uniprotGraph.proteinDatasetLabel;
		}

		@Override
		public TitanOrganismTaxonType value() {
			return uniprotGraph.organismTaxonT;
		}

		@Override
		public TitanOrganism.TitanOrganismType sourceType() {
			return uniprotGraph.organismT;
		}

		@Override
		public TitanTaxon.TitanTaxonType targetType() {
			return uniprotGraph.taxonT;
		}

		@Override
		public TitanOrganismTaxon fromTitanEdge(TitanEdge edge) {
			return new TitanOrganismTaxon(edge, uniprotGraph);
		}
	}
}


package com.bio4j.titan.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.TaxonParent;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.nodes.TitanTaxon;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 7/9/2014.
 */
public class TitanTaxonParent extends
		TitanRelationship<
				TitanTaxon, TitanTaxon.TitanTaxonType,
				TitanTaxonParent, TitanTaxonParent.TitanTaxonParentType,
				TitanTaxon, TitanTaxon.TitanTaxonType
				>
		implements
		TaxonParent<
				TitanTaxon, TitanTaxon.TitanTaxonType,
				TitanTaxonParent, TitanTaxonParent.TitanTaxonParentType,
				TitanTaxon, TitanTaxon.TitanTaxonType
				> {


	TitanTaxonParent(TitanEdge edge, TitanUniprotGraph uniprotGraph) {

		super(edge);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanTaxonParentType type() {

		return uniprotGraph.taxonParentT;
	}

	public static final class TitanTaxonParentType
			implements
			TitanRelationship.Type<
					TitanTaxon, TitanTaxon.TitanTaxonType,
					TitanTaxonParent, TitanTaxonParentType,
					TitanTaxon, TitanTaxon.TitanTaxonType
					>,
			UniprotGraph.TaxonParentType<
					TitanTaxon, TitanTaxon.TitanTaxonType,
					TitanTaxonParent, TitanTaxonParentType,
					TitanTaxon, TitanTaxon.TitanTaxonType
					> {

		TitanUniprotGraph uniprotGraph;

		public TitanTaxonParentType(TitanUniprotGraph uniprotGraph) {

			this.uniprotGraph = uniprotGraph;
		}

		@Override
		public TitanLabel label() {
			return uniprotGraph.taxonParentLabel;
		}

		@Override
		public TitanTaxonParentType value() {
			return uniprotGraph.taxonParentT;
		}

		@Override
		public TitanTaxon.TitanTaxonType sourceType() {
			return uniprotGraph.taxonT;
		}

		@Override
		public TitanTaxon.TitanTaxonType targetType() {
			return uniprotGraph.taxonT;
		}

		@Override
		public TitanTaxonParent fromTitanEdge(TitanEdge edge) {
			return new TitanTaxonParent(edge, uniprotGraph);
		}
	}
}


package com.bio4j.titan.model.ncbiTaxonomy.relationships;

import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph;
import com.bio4j.model.ncbiTaxonomy.relationships.NCBITaxonParent;
import com.bio4j.titan.model.ncbiTaxonomy.TitanNCBITaxonomyGraph;
import com.bio4j.titan.model.ncbiTaxonomy.nodes.TitanNCBITaxon;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public class TitanNCBITaxonParent extends
		TitanRelationship<
				TitanNCBITaxon, TitanNCBITaxon.TitanNCBITaxonType,
				TitanNCBITaxonParent, TitanNCBITaxonParent.TitanNCBITaxonParentType,
				TitanNCBITaxon, TitanNCBITaxon.TitanNCBITaxonType
				>
		implements
		NCBITaxonParent<
						TitanNCBITaxon, TitanNCBITaxon.TitanNCBITaxonType,
						TitanNCBITaxonParent, TitanNCBITaxonParent.TitanNCBITaxonParentType,
						TitanNCBITaxon, TitanNCBITaxon.TitanNCBITaxonType
						> {


	TitanNCBITaxonParent(TitanEdge edge, TitanNCBITaxonomyGraph ncbiTaxonomyGraph) {

		super(edge);
		this.ncbiTaxonomyGraph = ncbiTaxonomyGraph;
	}

	TitanNCBITaxonomyGraph ncbiTaxonomyGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanNCBITaxonParentType type() {

		return ncbiTaxonomyGraph.nCBITaxonParentT;
	}

	public static final class TitanNCBITaxonParentType
			implements
			TitanRelationship.Type<
					TitanNCBITaxon, TitanNCBITaxon.TitanNCBITaxonType,
					TitanNCBITaxonParent, TitanNCBITaxonParentType,
					TitanNCBITaxon, TitanNCBITaxon.TitanNCBITaxonType
					>,
			NCBITaxonomyGraph.NCBITaxonParentType<
					TitanNCBITaxon, TitanNCBITaxon.TitanNCBITaxonType,
					TitanNCBITaxonParent, TitanNCBITaxonParentType,
					TitanNCBITaxon, TitanNCBITaxon.TitanNCBITaxonType
					> {

		TitanNCBITaxonomyGraph ncbiTaxonomyGraph;

		public TitanNCBITaxonParentType(TitanNCBITaxonomyGraph ncbiTaxonomyGraph) {

			this.ncbiTaxonomyGraph = ncbiTaxonomyGraph;
		}

		@Override
		public TitanLabel label() {
			return ncbiTaxonomyGraph.nCBITaxonParentLabel;
		}

		@Override
		public TitanNCBITaxonParentType value() {
			return ncbiTaxonomyGraph.nCBITaxonParentT;
		}

		@Override
		public TitanNCBITaxon.TitanNCBITaxonType sourceType() {
			return ncbiTaxonomyGraph.nCBITaxonT;
		}

		@Override
		public TitanNCBITaxon.TitanNCBITaxonType targetType() {
			return ncbiTaxonomyGraph.nCBITaxonT;
		}

		@Override
		public TitanNCBITaxonParent fromTitanEdge(TitanEdge edge) {
			return new TitanNCBITaxonParent(edge, ncbiTaxonomyGraph);
		}
	}
}


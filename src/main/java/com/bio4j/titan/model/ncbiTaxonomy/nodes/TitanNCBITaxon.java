package com.bio4j.titan.model.ncbiTaxonomy.nodes;

import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph;
import com.bio4j.model.ncbiTaxonomy.nodes.NCBITaxon;
import com.bio4j.model.ncbiTaxonomy.relationships.NCBITaxonParent;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.titan.model.ncbiTaxonomy.TitanNCBITaxonomyGraph;
import com.bio4j.titan.model.ncbiTaxonomy.relationships.TitanNCBITaxonParent;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;

import java.util.List;

/**
 * Created by ppareja on 7/14/2014.
 */
public class TitanNCBITaxon extends
		TitanNode<TitanNCBITaxon, TitanNCBITaxon.TitanNCBITaxonType>
		implements NCBITaxon<TitanNCBITaxon, TitanNCBITaxon.TitanNCBITaxonType> {

	public TitanNCBITaxon(TitanVertex vertex, TitanNCBITaxonomyGraph ncbiTaxonomyGraph) {

		super(vertex);
		this.ncbiTaxonomyGraph = ncbiTaxonomyGraph;
	}

	TitanNCBITaxonomyGraph ncbiTaxonomyGraph;

	@Override
	public TitanNCBITaxonType type() {

		return ncbiTaxonomyGraph.nCBITaxonT;
	}

	@Override
	public String id() {
		return get(ncbiTaxonomyGraph.nCBITaxonT.id);
	}

	@Override
	public String name() {
		return get(ncbiTaxonomyGraph.nCBITaxonT.name);
	}

	@Override
	public String scientificName() {
		return get(ncbiTaxonomyGraph.nCBITaxonT.scientificName);
	}

	@Override
	public String comment() {
		return get(ncbiTaxonomyGraph.nCBITaxonT.comment);
	}

	@Override
	public String taxonomicRank() {
		return get(ncbiTaxonomyGraph.nCBITaxonT.taxonomicRank);
	}

	@Override
	public List<TitanNCBITaxonParent> nCBITaxonParent_in() {
		return inFromMany(ncbiTaxonomyGraph.nCBITaxonParentT);
	}

	@Override
	public List<TitanNCBITaxon> nCBITaxonParent_inNodes() {
		return inFromManyNodes(ncbiTaxonomyGraph.nCBITaxonParentT);
	}

	@Override
	public TitanNCBITaxonParent nCBITaxonParent_out() {
		return outToOne(ncbiTaxonomyGraph.nCBITaxonParentT);
	}

	@Override
	public TitanNCBITaxon nCBITaxonParent_outNodes() {
		return outToOneNode(ncbiTaxonomyGraph.nCBITaxonParentT);
	}


	public static final class TitanNCBITaxonType
			implements
			TitanNode.Type<TitanNCBITaxon, TitanNCBITaxon.TitanNCBITaxonType>,
			NCBITaxonomyGraph.NCBITaxonType<TitanNCBITaxon, TitanNCBITaxonType> {

		public TitanNCBITaxonType(TitanNCBITaxonomyGraph ncbiTaxonomyGraph) {
			this.ncbiTaxonomyGraph = ncbiTaxonomyGraph;
		}

		TitanNCBITaxonomyGraph ncbiTaxonomyGraph;

		@Override
		public TitanKey titanKey() {

			return ncbiTaxonomyGraph.nCBITaxonTkey;
		}

		@Override
		public TitanNCBITaxonType value() {

			return ncbiTaxonomyGraph.nCBITaxonT;
		}

		@Override
		public TitanNCBITaxon fromTitanVertex(TitanVertex vertex) {

			return new TitanNCBITaxon(vertex, ncbiTaxonomyGraph);
		}

		// --------------------------------properties----------------------------------------
		public id id = new id();
		public final class id
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanNCBITaxon, TitanNCBITaxonType, id, String>,
				NCBITaxon.id<TitanNCBITaxon, TitanNCBITaxonType, id> {

			@Override
			public TitanNCBITaxonType elementType() {

				return TitanNCBITaxonType.this;
			}

			@Override
			public TitanKey titanKey() {

				return ncbiTaxonomyGraph.nCBITaxonIdKey;
			}
		}
		public comment comment = new comment();
		public final class comment
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanNCBITaxon, TitanNCBITaxonType, comment, String>,
				NCBITaxon.comment<TitanNCBITaxon, TitanNCBITaxonType, comment> {

			@Override
			public TitanNCBITaxonType elementType() {

				return TitanNCBITaxonType.this;
			}

			@Override
			public TitanKey titanKey() {

				return ncbiTaxonomyGraph.nCBITaxonCommentKey;
			}
		}
		public scientificName scientificName = new scientificName();
		public final class scientificName
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanNCBITaxon, TitanNCBITaxonType, scientificName, String>,
				NCBITaxon.scientificName<TitanNCBITaxon, TitanNCBITaxonType, scientificName> {

			@Override
			public TitanNCBITaxonType elementType() {

				return TitanNCBITaxonType.this;
			}

			@Override
			public TitanKey titanKey() {

				return ncbiTaxonomyGraph.nCBITaxonScientificNameKey;
			}
		}
		public taxonomicRank taxonomicRank = new taxonomicRank();
		public final class taxonomicRank
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanNCBITaxon, TitanNCBITaxonType, taxonomicRank, String>,
				NCBITaxon.taxonomicRank<TitanNCBITaxon, TitanNCBITaxonType, taxonomicRank> {

			@Override
			public TitanNCBITaxonType elementType() {

				return TitanNCBITaxonType.this;
			}

			@Override
			public TitanKey titanKey() {

				return ncbiTaxonomyGraph.nCBITaxonTaxonomicRankKey;
			}
		}
		public name name = new name();
		public final class name
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanNCBITaxon, TitanNCBITaxonType, name, String>,
				NCBITaxon.name<TitanNCBITaxon, TitanNCBITaxonType, name> {

			@Override
			public TitanNCBITaxonType elementType() {

				return TitanNCBITaxonType.this;
			}

			@Override
			public TitanKey titanKey() {

				return ncbiTaxonomyGraph.nCBITaxonNameKey;
			}
		}


	}
}

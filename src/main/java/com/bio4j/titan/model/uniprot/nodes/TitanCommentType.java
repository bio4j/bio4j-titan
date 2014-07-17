package com.bio4j.titan.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.CommentType;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinComment;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;

import java.util.List;

/**
 * Created by ppareja on 7/17/2014.
 */
public class TitanCommentType extends
		TitanNode<TitanCommentType, TitanCommentType.TitanCommentTypeType>
		implements CommentType<TitanCommentType, TitanCommentType.TitanCommentTypeType> {

	@Override
	public String name(){ return get(uniprotGraph.commentTypeT.name);}

	@Override
	public List<TitanProteinComment> proteinComment_in() {
		return inFromMany(uniprotGraph.proteinCommentT);
	}

	@Override
	public List<TitanProtein> proteinComment_inNodes() {
		return inFromManyNodes(uniprotGraph.proteinCommentT);
	}

	public TitanCommentType(TitanVertex vertex, TitanUniprotGraph uniprotGraph) {

		super(vertex);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	@Override
	public TitanCommentTypeType type() {

		return uniprotGraph.commentTypeT;
	}

	public static final class TitanCommentTypeType
			implements
			TitanNode.Type<TitanCommentType, TitanCommentTypeType>,
			UniprotGraph.CommentTypeType<TitanCommentType, TitanCommentTypeType> {

		public TitanCommentTypeType(TitanUniprotGraph uniprotGraph) {
			this.uniprotGraph = uniprotGraph;
		}

		TitanUniprotGraph uniprotGraph;

		@Override
		public TitanKey titanKey() {

			return uniprotGraph.commentTypeTKey;
		}

		@Override
		public TitanCommentTypeType value() {

			return uniprotGraph.commentTypeT;
		}

		@Override
		public TitanCommentType fromTitanVertex(TitanVertex vertex) {

			return new TitanCommentType(vertex, uniprotGraph);
		}

		// --------------------------------properties----------------------------------------

		public name name = new name();

		public final class name
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanCommentType, TitanCommentTypeType, name, String>,
				CommentType.name<TitanCommentType, TitanCommentTypeType, name> {

			@Override
			public TitanCommentTypeType elementType() {

				return TitanCommentTypeType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.commentTypeNameKey;
			}
		}
	}
}

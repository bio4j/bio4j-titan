package com.bio4j.titan.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinComment;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.nodes.TitanCommentType;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 7/17/2014.
 */
public class TitanProteinComment extends
		TitanRelationship<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanProteinComment, TitanProteinComment.TitanProteinCommentType,
				TitanCommentType, TitanCommentType.TitanCommentTypeType
				>
		implements
		ProteinComment<
						TitanProtein, TitanProtein.TitanProteinType,
						TitanProteinComment, TitanProteinComment.TitanProteinCommentType,
						TitanCommentType, TitanCommentType.TitanCommentTypeType
						> {


	TitanProteinComment(TitanEdge edge, TitanUniprotGraph uniprotGraph) {

		super(edge);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanProteinCommentType type() {

		return uniprotGraph.proteinCommentT;
	}

	public static final class TitanProteinCommentType
			implements
			TitanRelationship.Type<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinComment, TitanProteinCommentType,
					TitanCommentType, TitanCommentType.TitanCommentTypeType
					>,
			UniprotGraph.ProteinCommentType<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinComment, TitanProteinCommentType,
					TitanCommentType, TitanCommentType.TitanCommentTypeType
					> {

		TitanUniprotGraph uniprotGraph;

		public TitanProteinCommentType(TitanUniprotGraph uniprotGraph) {

			this.uniprotGraph = uniprotGraph;
		}

		@Override
		public TitanLabel label() {
			return uniprotGraph.proteinCommentLabel;
		}

		@Override
		public TitanProteinCommentType value() {
			return uniprotGraph.proteinCommentT;
		}

		@Override
		public TitanProtein.TitanProteinType sourceType() {
			return uniprotGraph.proteinT;
		}

		@Override
		public TitanCommentType.TitanCommentTypeType targetType() {
			return uniprotGraph.commentTypeT;
		}

		@Override
		public TitanProteinComment fromTitanEdge(TitanEdge edge) {
			return new TitanProteinComment(edge, uniprotGraph);
		}
	}
}


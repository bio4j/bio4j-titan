package com.bio4j.titan.model.go.relationships;

import com.bio4j.model.go.GoGraph.PositivelyRegulatesType;
import com.bio4j.model.go.relationships.PositivelyRegulates;
import com.bio4j.titan.model.go.TitanGoGraph;
import com.bio4j.titan.model.go.nodes.TitanGoTerm;
import com.bio4j.titan.model.go.nodes.TitanGoTerm.TitanGoTermType;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo
 *         Pareja-Tobes</a>
 */
public final class TitanPositivelyRegulates
		extends
		TitanRelationship<
				TitanGoTerm, TitanGoTerm.TitanGoTermType,
				TitanPositivelyRegulates, TitanPositivelyRegulates.TitanPositivelyRegulatesType,
				TitanGoTerm, TitanGoTerm.TitanGoTermType
				>
		implements
		PositivelyRegulates<
				TitanGoTerm, TitanGoTerm.TitanGoTermType,
				TitanPositivelyRegulates, TitanPositivelyRegulates.TitanPositivelyRegulatesType,
				TitanGoTerm, TitanGoTerm.TitanGoTermType
				> {

	TitanPositivelyRegulates(TitanEdge edge, TitanGoGraph goGraph) {

		super(edge);
		this.goGraph = goGraph;
	}

	TitanGoGraph goGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanPositivelyRegulatesType type() {
		return goGraph.positivelyRegulatesT;
	}

	public static final class TitanPositivelyRegulatesType
			implements
			TitanRelationship.Type<
					TitanGoTerm, TitanGoTerm.TitanGoTermType,
					TitanPositivelyRegulates, TitanPositivelyRegulates.TitanPositivelyRegulatesType,
					TitanGoTerm, TitanGoTerm.TitanGoTermType
					>,
			PositivelyRegulatesType<
					TitanGoTerm, TitanGoTerm.TitanGoTermType,
					TitanPositivelyRegulates, TitanPositivelyRegulates.TitanPositivelyRegulatesType,
					TitanGoTerm, TitanGoTerm.TitanGoTermType
					> {

		TitanGoGraph goGraph;

		public TitanPositivelyRegulatesType(TitanGoGraph goGraph) {

			this.goGraph = goGraph;
		}

		@Override
		public TitanLabel label() {
			return goGraph.regulatesLabel;
		}

		@Override
		public TitanPositivelyRegulatesType value() {
			return goGraph.positivelyRegulatesT;
		}

		@Override
		public TitanGoTermType sourceType() {
			return goGraph.goTermT;
		}

		@Override
		public TitanGoTermType targetType() {
			return goGraph.goTermT;
		}

		@Override
		public TitanPositivelyRegulates fromTitanEdge(TitanEdge edge) {
			return new TitanPositivelyRegulates(edge, goGraph);
		}
	}

}

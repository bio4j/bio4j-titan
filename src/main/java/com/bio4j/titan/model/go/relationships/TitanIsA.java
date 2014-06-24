package com.bio4j.titan.model.go.relationships;

import com.bio4j.model.go.GoGraph.IsAType;
import com.bio4j.model.go.relationships.IsA;
import com.bio4j.titan.model.go.TitanGoGraph;
import com.bio4j.titan.model.go.nodes.TitanGoTerm;
import com.bio4j.titan.model.go.nodes.TitanGoTerm.TitanGoTermType;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

// properties

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo
 *         Pareja-Tobes</a>
 */
public final class TitanIsA
		extends
		TitanRelationship<
				TitanGoTerm, TitanGoTerm.TitanGoTermType,
				TitanIsA, TitanIsA.TitanIsAType,
				TitanGoTerm, TitanGoTerm.TitanGoTermType
				>
		implements
		IsA<
				TitanGoTerm, TitanGoTerm.TitanGoTermType,
				TitanIsA, TitanIsA.TitanIsAType,
				TitanGoTerm, TitanGoTerm.TitanGoTermType
				> {


	TitanIsA(TitanEdge edge, TitanGoGraph goGraph) {

		super(edge);
		this.goGraph = goGraph;
	}

	TitanGoGraph goGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanIsAType type() {

		return goGraph.isAT;
	}

	public static final class TitanIsAType
			implements
			TitanRelationship.Type<
					TitanGoTerm, TitanGoTerm.TitanGoTermType,
					TitanIsA, TitanIsA.TitanIsAType,
					TitanGoTerm, TitanGoTerm.TitanGoTermType
					>,
			IsAType<
					TitanGoTerm, TitanGoTerm.TitanGoTermType,
					TitanIsA, TitanIsA.TitanIsAType,
					TitanGoTerm, TitanGoTerm.TitanGoTermType
					> {

		TitanGoGraph goGraph;

		public TitanIsAType(TitanGoGraph goGraph) {

			this.goGraph = goGraph;
		}

		@Override
		public TitanLabel label() {
			return goGraph.isALabel;
		}

		@Override
		public TitanIsAType value() {
			return goGraph.isAT;
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
		public TitanIsA fromTitanEdge(TitanEdge edge) {
			return new TitanIsA(edge, goGraph);
		}
	}
}


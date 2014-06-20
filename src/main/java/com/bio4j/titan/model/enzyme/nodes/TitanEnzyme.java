package com.bio4j.titan.model.enzyme.nodes;

import com.bio4j.titan.model.enzyme.TitanEnzymeDBGraph;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;

/**
 * Created by ppareja on 6/17/2014.
 */
public class TitanEnzyme extends
		TitanNode<TitanEnzyme, TitanEnzyme.TitanEnzymeType>
		implements Enzyme<TitanEnzyme, TitanEnzyme.TitanEnzymeType> {

	public TitanEnzyme(TitanVertex vertex, TitanEnzymeDBGraph enzymeDBGraph) {

		super(vertex);
		this.enzymeDBGraph = enzymeDBGraph;
	}

	TitanEnzymeDBGraph enzymeDBGraph;

	@Override
	public TitanEnzymeType type() {

		return enzymeDBGraph.enzymeT;
	}

	public static final class TitanEnzymeType
			implements
			TitanNode.Type<TitanEnzyme, TitanEnzyme.TitanEnzymeType>,
			EnzymeType<TitanEnzyme, TitanEnzyme.TitanEnzymeType> {

		public TitanEnzymeType(TitanEnzymeDBGraph enzymeDBGraph) {
			this.enzymeDBGraph = enzymeDBGraph;
		}

		TitanEnzymeDBGraph enzymeDBGraph;

		@Override
		public TitanKey titanKey() {

			return enzymeDBGraph.enzymeTkey;
		}

		@Override
		public TitanEnzymeType value() {

			return enzymeDBGraph.enzymeT;
		}

		@Override
		public TitanEnzyme fromTitanVertex(TitanVertex vertex) {

			return new TitanEnzyme(vertex, enzymeDBGraph);
		}

	}
}

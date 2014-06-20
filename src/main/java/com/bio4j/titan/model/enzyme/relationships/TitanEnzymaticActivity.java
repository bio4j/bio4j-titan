package com.bio4j.titan.model.enzyme.relationships;

import com.bio4j.model.enzymedb.relationships.EnzymaticActivity;
import com.bio4j.titan.model.enzyme.TitanEnzymeDBGraph;
import com.bio4j.titan.model.enzyme.nodes.TitanEnzyme;
import com.bio4j.titan.model.enzyme.nodes.TitanEnzyme.TitanEnzymeType;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 6/20/2014.
 */
public class TitanEnzymaticActivity extends
		TitanRelationship<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanEnzymaticActivity, TitanEnzymaticActivity.TitanEnzymaticActivityType,
				TitanEnzyme, TitanEnzyme.TitanEnzymeType
				>
		implements
		EnzymaticActivity<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanEnzymaticActivity, TitanEnzymaticActivity.TitanEnzymaticActivityType,
				TitanEnzyme, TitanEnzyme.TitanEnzymeType
				> {

	TitanEnzymaticActivity(TitanEdge edge, TitanEnzymeDBGraph enzymeDBGraph) {

		super(edge);
		this.enzymeDBGraph = enzymeDBGraph;
	}

	TitanEnzymeDBGraph enzymeDBGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanEnzymaticActivityType type() {

		return enzymeDBGraph.enzymaticActivityT;
	}

	public static final class TitanEnzymaticActivityType
			implements
			TitanRelationship.Type<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanEnzymaticActivity, TitanEnzymaticActivity.TitanEnzymaticActivityType,
					TitanEnzyme, TitanEnzyme.TitanEnzymeType
					>,
			EnzymaticActivityType<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanEnzymaticActivity, TitanEnzymaticActivity.TitanEnzymaticActivityType,
					TitanEnzyme, TitanEnzyme.TitanEnzymeType
					>{

		TitanEnzymeDBGraph enzymeDBGraph;

		public TitanEnzymaticActivityType(TitanEnzymeDBGraph enzymeDBGraph) {

			this.enzymeDBGraph = enzymeDBGraph;
		}

		@Override
		public TitanLabel label() {
			return enzymeDBGraph.enzymaticActivityLabel;
		}

		@Override
		public TitanEnzymaticActivityType value() {
			return enzymeDBGraph.enzymaticActivityT;
		}

		@Override
		public TitanProteinType sourceType() {
			return enzymeDBGraph.proteinT;
		}

		@Override
		public TitanEnzymeType targetType() {
			return enzymeDBGraph.enzymeT;
		}

		@Override
		public TitanEnzymaticActivity fromTitanEdge(TitanEdge edge) {
			return new TitanEnzymaticActivity(edge, enzymeDBGraph);
		}
	}

}



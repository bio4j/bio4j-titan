package com.bio4j.titan.model.uniprot_enzyme.relationships;

import com.bio4j.model.uniprot_enzymedb.UniprotEnzymeDBGraph;
import com.bio4j.model.uniprot_enzymedb.relationships.EnzymaticActivity;
import com.bio4j.titan.model.enzyme.nodes.TitanEnzyme;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.bio4j.titan.model.uniprot_enzyme.TitanUniprotEnzymeDBGraph;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 6/25/2014.
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

	TitanEnzymaticActivity(TitanEdge edge, TitanUniprotEnzymeDBGraph uniprotEnzymeDBGraph) {

		super(edge);
		this.uniprotEnzymeDBGraph = uniprotEnzymeDBGraph;
	}

	TitanUniprotEnzymeDBGraph uniprotEnzymeDBGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanEnzymaticActivityType type() {

		return uniprotEnzymeDBGraph.enzymaticActivityT;
	}

	public static final class TitanEnzymaticActivityType
			implements
			TitanRelationship.Type<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanEnzymaticActivity, TitanEnzymaticActivityType,
					TitanEnzyme, TitanEnzyme.TitanEnzymeType
					>,
			UniprotEnzymeDBGraph.EnzymaticActivityType<
								TitanProtein, TitanProtein.TitanProteinType,
								TitanEnzymaticActivity, TitanEnzymaticActivityType,
								TitanEnzyme, TitanEnzyme.TitanEnzymeType
								> {

		TitanUniprotEnzymeDBGraph uniprotEnzymeDBGraph;

		public TitanEnzymaticActivityType(TitanUniprotEnzymeDBGraph uniprotEnzymeDBGraph) {

			this.uniprotEnzymeDBGraph = uniprotEnzymeDBGraph;
		}

		@Override
		public TitanLabel label() {
			return uniprotEnzymeDBGraph.enzymaticActivityLabel;
		}

		@Override
		public TitanEnzymaticActivityType value() {
			return uniprotEnzymeDBGraph.enzymaticActivityT;
		}

		@Override
		public TitanProtein.TitanProteinType sourceType() {
			return uniprotEnzymeDBGraph.uniprotGraph.proteinT;
		}

		@Override
		public TitanEnzyme.TitanEnzymeType targetType() {
			return uniprotEnzymeDBGraph.enzymeDBGraph.enzymeT;
		}

		@Override
		public TitanEnzymaticActivity fromTitanEdge(TitanEdge edge) {
			return new TitanEnzymaticActivity(edge, uniprotEnzymeDBGraph);
		}
	}
}

package com.bio4j.titan.model.enzyme.nodes;

import com.bio4j.model.enzymedb.EnzymeDBGraph.EnzymeType;
import com.bio4j.model.enzymedb.nodes.Enzyme;
import com.bio4j.model.enzymedb.relationships.EnzymaticActivity;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.titan.model.enzyme.TitanEnzymeDBGraph;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;

import java.util.List;

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

	@Override
	public String id() {
		return get(enzymeDBGraph.enzymeT.id);
	}

	@Override
	public String cofactors() {
		return get(enzymeDBGraph.enzymeT.cofactors);
	}

	@Override
	public String officialName() {
		return get(enzymeDBGraph.enzymeT.officialName);
	}

	@Override
	public String catalyticActivity() {
		return get(enzymeDBGraph.enzymeT.catalyticActivity);
	}

	@Override
	public String comment() {
		return get(enzymeDBGraph.enzymeT.comment);
	}

	@Override
	public String prositeCrossReferences() {
		return get(enzymeDBGraph.enzymeT.prositeCrossReferences);
	}


	public static final class TitanEnzymeType
			implements
			TitanNode.Type<TitanEnzyme, TitanEnzyme.TitanEnzymeType>,
			EnzymeType<TitanEnzyme, TitanEnzymeType> {

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

		// --------------------------------properties----------------------------------------
		public id id = new id();
		public final class id
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanEnzyme, TitanEnzymeType, id, String>,
				Enzyme.id<TitanEnzyme, TitanEnzymeType, id> {

			@Override
			public TitanEnzymeType elementType() {

				return TitanEnzymeType.this;
			}

			@Override
			public TitanKey titanKey() {

				return enzymeDBGraph.enzymeIdKey;
			}
		}

		public cofactors cofactors = new cofactors();
		public final class cofactors
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanEnzyme, TitanEnzymeType, cofactors, String>,
				Enzyme.cofactors<TitanEnzyme, TitanEnzymeType, cofactors> {

			@Override
			public TitanEnzymeType elementType() {

				return TitanEnzymeType.this;
			}

			@Override
			public TitanKey titanKey() {

				return enzymeDBGraph.enzymeCofactorsKey;
			}
		}

		public officialName officialName = new officialName();
		public final class officialName
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanEnzyme, TitanEnzymeType, officialName, String>,
				Enzyme.officialName<TitanEnzyme, TitanEnzymeType, officialName> {

			@Override
			public TitanEnzymeType elementType() {

				return TitanEnzymeType.this;
			}

			@Override
			public TitanKey titanKey() {

				return enzymeDBGraph.enzymeOfficialNameKey;
			}
		}

		public catalyticActivity catalyticActivity = new catalyticActivity();
		public final class catalyticActivity
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanEnzyme, TitanEnzymeType, catalyticActivity, String>,
				Enzyme.catalyticActivity<TitanEnzyme, TitanEnzymeType, catalyticActivity> {

			@Override
			public TitanEnzymeType elementType() {

				return TitanEnzymeType.this;
			}

			@Override
			public TitanKey titanKey() {

				return enzymeDBGraph.enzymeCatalyticActivityKey;
			}
		}

		public comment comment = new comment();
		public final class comment
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanEnzyme, TitanEnzymeType, comment, String>,
				Enzyme.comment<TitanEnzyme, TitanEnzymeType, comment> {

			@Override
			public TitanEnzymeType elementType() {

				return TitanEnzymeType.this;
			}

			@Override
			public TitanKey titanKey() {

				return enzymeDBGraph.enzymeCommentKey;
			}
		}

		public prositeCrossReferences prositeCrossReferences = new prositeCrossReferences();
		public final class prositeCrossReferences
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanEnzyme, TitanEnzymeType, prositeCrossReferences, String>,
				Enzyme.prositeCrossReferences<TitanEnzyme, TitanEnzymeType, prositeCrossReferences> {

			@Override
			public TitanEnzymeType elementType() {

				return TitanEnzymeType.this;
			}

			@Override
			public TitanKey titanKey() {

				return enzymeDBGraph.enzymePrositeCrossReferencesKey;
			}
		}

		@Override
		public List<? extends EnzymaticActivity> enzymaticActivity_in() {
			return inFromMany(enzymeDBGraph.enzymaticActivityT);
		}

		@Override
		public List<? extends Protein> enzymaticActivity_inNodes() {
			return inFromManyNodes(enzymeDBGraph.enzymaticActivityT);
		}

	}
}

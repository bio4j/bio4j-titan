package com.bio4j.titan.model.go.nodes;

import com.bio4j.model.go.GoGraph.TermType;
import com.bio4j.model.go.nodes.Term;
import com.bio4j.titan.model.go.TitanGoGraph;
import com.bio4j.titan.model.go.relationships.*;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;

import java.util.List;

// properties

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo
 *         Pareja-Tobes</a>
 */


public final class TitanGoTerm
		extends
		TitanNode<TitanGoTerm, TitanGoTerm.TitanGoTermType>
		implements
		Term<TitanGoTerm, TitanGoTerm.TitanGoTermType> {

	public TitanGoTerm(TitanVertex vertex, TitanGoGraph goGraph) {

		super(vertex);
		this.goGraph = goGraph;
	}

	TitanGoGraph goGraph;

	@Override
	public TitanGoTermType type() {

		return goGraph.goTermT;
	}


	public static final class TitanGoTermType
			implements
			TitanNode.Type<TitanGoTerm, TitanGoTerm.TitanGoTermType>,
			TermType<TitanGoTerm, TitanGoTerm.TitanGoTermType> {

		public TitanGoTermType(TitanGoGraph goGraph) {
			this.goGraph = goGraph;
		}

		TitanGoGraph goGraph;

		@Override
		public TitanKey titanKey() {

			return goGraph.goTermTkey;
		}

		@Override
		public TitanGoTermType value() {

			return goGraph.goTermT;
		}

		@Override
		public TitanGoTerm fromTitanVertex(TitanVertex vertex) {

			return new TitanGoTerm(vertex, goGraph);
		}

		// --------------------------------properties----------------------------------------
		public id id = new id();
		public final class id
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanGoTerm, TitanGoTermType, id, String>,
				Term.id<TitanGoTerm, TitanGoTermType, id> {

			@Override
			public TitanGoTermType elementType() {

				return TitanGoTermType.this;
			}

			@Override
			public TitanKey titanKey() {

				return goGraph.goTermIdKey;
			}
		}

		public name name = new name();
		public final class name
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanGoTerm, TitanGoTermType, name, String>,
				Term.name<TitanGoTerm, TitanGoTermType, name> {

			@Override
			public TitanGoTermType elementType() {

				return TitanGoTermType.this;
			}

			@Override
			public TitanKey titanKey() {

				return goGraph.goTermNameKey;
			}
		}

		public definition definition = new definition();
		public final class definition
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanGoTerm, TitanGoTermType, definition, String>,
				Term.definition<TitanGoTerm, TitanGoTermType, definition> {

			@Override
			public TitanGoTermType elementType() {

				return TitanGoTermType.this;
			}

			@Override
			public TitanKey titanKey() {

				return goGraph.goTermDefinitionKey;
			}
		}

		public obsolete obsolete = new obsolete();
		public final class obsolete
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanGoTerm, TitanGoTermType, obsolete, String>,
				Term.obsolete<TitanGoTerm, TitanGoTermType, obsolete> {

			@Override
			public TitanGoTermType elementType() {

				return TitanGoTermType.this;
			}

			@Override
			public TitanKey titanKey() {

				return goGraph.goTermObsoleteKey;
			}
		}

		public comment comment = new comment();
		public final class comment
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanGoTerm, TitanGoTermType, comment, String>,
				Term.comment<TitanGoTerm, TitanGoTermType, comment> {

			@Override
			public TitanGoTermType elementType() {

				return TitanGoTermType.this;
			}

			@Override
			public TitanKey titanKey() {

				return goGraph.goTermCommentKey;
			}
		}
	}

	@Override
	public List<TitanIsA> isA_in() {
		return inFromMany(goGraph.isAT);
	}

	@Override
	public List<TitanGoTerm> isA_inNodes() {
		return inFromManyNodes(goGraph.isAT);
	}

	// // outgoing
	@Override
	public List<TitanIsA> isA_out() {
		return outToMany(goGraph.isAT);
	}

	@Override
	public List<TitanGoTerm> isA_outNodes() {
		return outToManyNodes(goGraph.isAT);
	}

	// regulates
	// incoming
	@Override
	public List<TitanRegulates> regulates_in() {
		return inFromMany(goGraph.regulatesT);
	}

	@Override
	public List<TitanGoTerm> regulates_inNodes() {
		return inFromManyNodes(goGraph.regulatesT);
	}

	// // outgoing
	@Override
	public List<TitanRegulates> regulates_out() {
		return outToMany(goGraph.regulatesT);
	}

	@Override
	public List<TitanGoTerm> regulates_outNodes() {
		return outToManyNodes(goGraph.regulatesT);
	}

	// positivelyRegulates
	// incoming
	@Override
	public List<TitanPositivelyRegulates> positivelyRegulates_in() {
		return inFromMany(goGraph.positivelyRegulatesT);
	}

	@Override
	public List<TitanGoTerm> positivelyRegulates_inNodes() {
		return inFromManyNodes(goGraph.positivelyRegulatesT);
	}

	// // outgoing
	@Override
	public List<TitanPositivelyRegulates> positivelyRegulates_out() {
		return outToMany(goGraph.positivelyRegulatesT);
	}

	@Override
	public List<TitanGoTerm> positivelyRegulates_outNodes() {
		return outToManyNodes(goGraph.positivelyRegulatesT);
	}

	// negativelyRegulates
	// incoming
	@Override
	public List<TitanNegativelyRegulates> negativelyRegulates_in() {
		return inFromMany(goGraph.negativelyRegulatesT);
	}

	@Override
	public List<TitanGoTerm> negativelyRegulates_inNodes() {
		return inFromManyNodes(goGraph.negativelyRegulatesT);
	}

	// // outgoing
	@Override
	public List<TitanNegativelyRegulates> negativelyRegulates_out() {
		return outToMany(goGraph.negativelyRegulatesT);
	}

	@Override
	public List<TitanGoTerm> negativelyRegulates_outNodes() {
		return outToManyNodes(goGraph.negativelyRegulatesT);
	}

	// // partOf
	// // incoming
	@Override
	public List<TitanPartOf> partOf_in() {
		return inFromMany(goGraph.partOfT);
	}

	@Override
	public List<TitanGoTerm> partOf_inNodes() {
		return inFromManyNodes(goGraph.partOfT);
	}

	// // outgoing
	@Override
	public List<TitanPartOf> partOf_out() {
		return outToMany(goGraph.partOfT);
	}

	@Override
	public List<TitanGoTerm> partOf_outNodes() {
		return outToManyNodes(goGraph.partOfT);
	}

	// // partOf
	// // incoming
	@Override
	public List<TitanHasPartOf> hasPartOf_in() {
		return inFromMany(goGraph.hasPartOfT);
	}

	@Override
	public List<TitanGoTerm> hasPartOf_inNodes() {
		return inFromManyNodes(goGraph.hasPartOfT);
	}

	// // outgoing
	@Override
	public List<TitanHasPartOf> hasPartOf_out() {
		return outToMany(goGraph.hasPartOfT);
	}

	@Override
	public List<TitanGoTerm> hasPartOf_outNodes() {
		return outToManyNodes(goGraph.hasPartOfT);
	}

	// SubOntology
	// outgoing
	@Override
	public TitanSubOntology subOntology_out() {
		return outToOne(goGraph.subOntologyT);
	}

	@Override
	public TitanSubOntologies subOntology_outNodes() {
		return outToOneNode(goGraph.subOntologyT);
	}


//    // /////////////////////// extras ////////////////////////////////////
//
//    // goAnnotation
//    // incoming
//    // TODO implement
//    @Override
//    public List<GoAnnotation> goAnnotation_in() {
//        return null;
//    }
//
//    @Override
//    public List<Protein> goAnnotation_inNodes() {
//        return null;
//    }


}


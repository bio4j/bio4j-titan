package com.bio4j.titan.model.go;

import com.bio4j.model.go.relationships.IsA;
import com.ohnosequences.typedGraphs.titan.*;
import com.thinkaurelius.titan.core.*;

/*
  Implementing the types with Titan
*/
public abstract class TitanGoGraph
        implements
        TitanTypedGraph,
        GoGraph {

    protected TitanGraph rawGraph;

    TitanGoGraph(TitanGraph rawGraph) {
        this.rawGraph = rawGraph;
    }

    @Override
    public TitanGraph rawGraph() {
        return rawGraph;
    }

    /*
    A gene ontology term implementation. It is a Titan node and implements the `Term` interface. It is defined as an inner class here just for convenience in this small example; it could be anywhere else. it'd just need a reference to this graph for retrieving its type.
    */
    public final class TitanTerm
            extends
            TitanNode<TitanTerm, TitanTermType>
            implements
            Term<TitanTerm, TitanTermType> {
        TitanTerm(TitanVertex vertex) {
            super(vertex);
        }

        /*
          Note here how we need a reference to the enclosing graph, which contains the term type value.
        */
        @Override
        public TitanTermType type() {
            return TitanGoGraph.this.termT;
        }
    }

    /*
      The type of a TitanTerm. This an inner class of the graph. The first key here represents the type of the node, while the rest are for properties of this term: `id` and `name` in this case.
    */
    TitanKey termTkey;
    TitanKey termIdKey;
    TitanKey termNameKey;
    TitanTermType termT = new TitanTermType();

    public final class TitanTermType
            implements
            TitanNode.Type<TitanTerm, TitanTermType>,
            TermType<TitanTerm, TitanTermType> {
        @Override
        public TitanKey titanKey() {
            return TitanGoGraph.this.termTkey;
        }

        @Override
        public TitanTermType value() {
            return TitanGoGraph.this.termT;
        }

        @Override
        public TitanTerm fromTitanVertex(TitanVertex vertex) {
            return new TitanTerm(vertex);
        }

        // properties
        public id id = new id();

        // no need to worry about the unchecked warning
        @Override
        public id Id() {
            return id;
        }

        public final class id
                implements
                com.ohnosequences.typedGraphs.titan.TitanProperty<TitanTerm, TitanTermType, id, String>,
                TermType.id<TitanTerm, TitanTermType, id> {
            @Override
            public TitanTermType elementType() {
                return TitanTermType.this;
            }

            @Override
            public TitanKey titanKey() {
                return TitanGoGraph.this.termIdKey;
            }
        }

        name name = new name();

        @Override
        public name Name() {
            return name;
        }

        public final class name
                implements
                com.ohnosequences.typedGraphs.titan.TitanProperty<TitanTerm, TitanTermType, name, String>,
                TermType.name<TitanTerm, TitanTermType, name> {
            @Override
            public TitanTermType elementType() {
                return TitanTermType.this;
            }

            @Override
            public TitanKey titanKey() {
                return TitanGoGraph.this.termNameKey;
            }
        }
    }

    //-----------------------------------------------------------------------------------------
    //--------------------------------RELATIONSHIPS--------------------------------------------


    //================partOf rel============================

    public final class TitanPartOf
            extends
            TitanRelationship<TitanTerm, TitanTermType, TitanPartOf, TitanPartOfType, TitanTerm, TitanTermType>
            implements
            PartOf<TitanTerm, TitanTermType, TitanPartOf, TitanPartOfType, TitanTerm, TitanTermType> {
        TitanPartOf(TitanEdge edge) {
            super(edge);
        }

        /*
          Note here how we need a reference to the enclosing graph, which contains the term type value.
        */
        @Override
        public TitanPartOfType type() {
            return TitanGoGraph.this.partOfT;
        }
    }

    TitanLabel partOfLabel;
    TitanPartOfType partOfT = new TitanPartOfType();

    public final class TitanPartOfType
            implements
            TitanRelationship.Type<TitanTerm, TitanTermType, TitanPartOf, TitanPartOfType, TitanTerm, TitanTermType>,
            PartOfType<TitanTerm, TitanTermType, TitanPartOf, TitanPartOfType, TitanTerm, TitanTermType> {
        @Override
        public TitanLabel label() {
            return TitanGoGraph.this.partOfLabel;
        }

        @Override
        public TitanPartOfType value() {
            return TitanGoGraph.this.partOfT;
        }

        @Override
        public TitanTermType sourceType() {
            return TitanGoGraph.this.termT;
        }

        @Override
        public TitanTermType targetType() {
            return TitanGoGraph.this.termT;
        }

        @Override
        public TitanPartOf from(TitanEdge edge) {
            return new TitanPartOf(edge);
        }
    }

    //================hasPartOf rel============================

    public final class TitanHasPartOf
            extends
            TitanRelationship<TitanTerm, TitanTermType, TitanHasPartOf, TitanHasPartOfType, TitanTerm, TitanTermType>
            implements
            HasPartOf<TitanTerm, TitanTermType, TitanHasPartOf, TitanHasPartOfType, TitanTerm, TitanTermType> {
        TitanHasPartOf(TitanEdge edge) {
            super(edge);
        }

        /*
          Note here how we need a reference to the enclosing graph, which contains the term type value.
        */
        @Override
        public TitanHasPartOfType type() {
            return TitanGoGraph.this.hasPartOfT;
        }
    }

    TitanLabel hasPartOfLabel;
    TitanHasPartOfType hasPartOfT = new TitanHasPartOfType();

    public final class TitanHasPartOfType
            implements
            TitanRelationship.Type<TitanTerm, TitanTermType, TitanHasPartOf, TitanHasPartOfType, TitanTerm, TitanTermType>,
            HasPartOfType<TitanTerm, TitanTermType, TitanHasPartOf, TitanHasPartOfType, TitanTerm, TitanTermType> {
        @Override
        public TitanLabel label() {
            return TitanGoGraph.this.hasPartOfLabel;
        }

        @Override
        public TitanHasPartOfType value() {
            return TitanGoGraph.this.hasPartOfT;
        }

        @Override
        public TitanTermType sourceType() {
            return TitanGoGraph.this.termT;
        }

        @Override
        public TitanTermType targetType() {
            return TitanGoGraph.this.termT;
        }

        @Override
        public TitanHasPartOf from(TitanEdge edge) {
            return new TitanHasPartOf(edge);
        }
    }

    //================isA rel============================

    public final class TitanIsA
            extends
            TitanRelationship<TitanTerm, TitanTermType, TitanIsA, TitanIsAType, TitanTerm, TitanTermType>
            implements
            IsA<TitanTerm, TitanTermType, TitanIsA, TitanIsAType, TitanTerm, TitanTermType> {
        TitanIsA(TitanEdge edge) {
            super(edge);
        }

        /*
          Note here how we need a reference to the enclosing graph, which contains the term type value.
        */
        @Override
        public TitanIsAType type() {
            return TitanGoGraph.this.isAT;
        }
    }

    TitanLabel isALabel;
    TitanIsAType isAT = new TitanIsAType();

    public final class TitanIsAType
            implements
            TitanRelationship.Type<TitanTerm, TitanTermType, TitanIsA, TitanIsAType, TitanTerm, TitanTermType>,
            IsAType<TitanTerm, TitanTermType, TitanIsA, TitanIsAType, TitanTerm, TitanTermType> {
        @Override
        public TitanLabel label() {
            return TitanGoGraph.this.isALabel;
        }

        @Override
        public TitanIsAType value() {
            return TitanGoGraph.this.isAT;
        }

        @Override
        public TitanTermType sourceType() {
            return TitanGoGraph.this.termT;
        }

        @Override
        public TitanTermType targetType() {
            return TitanGoGraph.this.termT;
        }

        @Override
        public TitanIsA from(TitanEdge edge) {
            return new TitanIsA(edge);
        }
    }

    //================positivelyRegulates rel============================

    public final class TitanPositivelyRegulates
            extends
            TitanRelationship<TitanTerm, TitanTermType, TitanPositivelyRegulates, TitanPositivelyRegulatesType, TitanTerm, TitanTermType>
            implements
            PositivelyRegulates<TitanTerm, TitanTermType, TitanPositivelyRegulates, TitanPositivelyRegulatesType, TitanTerm, TitanTermType> {

        TitanPositivelyRegulates(TitanEdge edge) {
            super(edge);
        }

        /*
          Note here how we need a reference to the enclosing graph, which contains the term type value.
        */
        @Override
        public TitanPositivelyRegulatesType type() {
            return TitanGoGraph.this.positivelyRegulatesT;
        }
    }

    TitanLabel positivelyRegulatesLabel;
    TitanPositivelyRegulatesType positivelyRegulatesT = new TitanPositivelyRegulatesType();

    public final class TitanPositivelyRegulatesType
            implements
            TitanRelationship.Type<TitanTerm, TitanTermType, TitanPositivelyRegulates, TitanPositivelyRegulatesType, TitanTerm, TitanTermType>,
            PositivelyRegulatesType<TitanTerm, TitanTermType, TitanPositivelyRegulates, TitanPositivelyRegulatesType, TitanTerm, TitanTermType> {
        @Override
        public TitanLabel label() {
            return TitanGoGraph.this.positivelyRegulatesLabel;
        }

        @Override
        public TitanPositivelyRegulatesType value() {
            return TitanGoGraph.this.positivelyRegulatesT;
        }

        @Override
        public TitanTermType sourceType() {
            return TitanGoGraph.this.termT;
        }

        @Override
        public TitanTermType targetType() {
            return TitanGoGraph.this.termT;
        }

        @Override
        public TitanPositivelyRegulates from(TitanEdge edge) {
            return new TitanPositivelyRegulates(edge);
        }
    }

    //================negativelyRegulates rel============================

    public final class TitanNegativelyRegulates
            extends
            TitanRelationship<TitanTerm, TitanTermType, TitanNegativelyRegulates, TitanNegativelyRegulatesType, TitanTerm, TitanTermType>
            implements
            NegativelyRegulates<TitanTerm, TitanTermType, TitanNegativelyRegulates, TitanNegativelyRegulatesType, TitanTerm, TitanTermType> {

        TitanNegativelyRegulates(TitanEdge edge) {
            super(edge);
        }

        /*
          Note here how we need a reference to the enclosing graph, which contains the term type value.
        */
        @Override
        public TitanNegativelyRegulatesType type() {
            return TitanGoGraph.this.negativelyRegulatesT;
        }
    }

    TitanLabel negativelyRegulatesLabel;
    TitanNegativelyRegulatesType negativelyRegulatesT = new TitanNegativelyRegulatesType();

    public final class TitanNegativelyRegulatesType
            implements
            TitanRelationship.Type<TitanTerm, TitanTermType, TitanNegativelyRegulates, TitanNegativelyRegulatesType, TitanTerm, TitanTermType>,
            NegativelyRegulatesType<TitanTerm, TitanTermType, TitanNegativelyRegulates, TitanNegativelyRegulatesType, TitanTerm, TitanTermType> {
        @Override
        public TitanLabel label() {
            return TitanGoGraph.this.negativelyRegulatesLabel;
        }

        @Override
        public TitanNegativelyRegulatesType value() {
            return TitanGoGraph.this.negativelyRegulatesT;
        }

        @Override
        public TitanTermType sourceType() {
            return TitanGoGraph.this.termT;
        }

        @Override
        public TitanTermType targetType() {
            return TitanGoGraph.this.termT;
        }

        @Override
        public TitanNegativelyRegulates from(TitanEdge edge) {
            return new TitanNegativelyRegulates(edge);
        }
    }


}
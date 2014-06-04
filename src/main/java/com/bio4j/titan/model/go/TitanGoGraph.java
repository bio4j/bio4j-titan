package com.bio4j.titan.model.go;

import com.bio4j.model.go.relationships.IsA;
import com.ohnosequences.typedGraphs.titan.*;
import com.thinkaurelius.titan.core.*;
import com.bio4j.model.go.nodes.*;
import com.bio4j.titan.model.go.nodes.*;
import com.bio4j.titan.model.go.nodes.TitanTerm.TitanTermType;
import com.bio4j.titan.model.go.nodes.TitanTerm;
import com.bio4j.titan.model.go.relationships.TitanPartOf;
import com.bio4j.titan.model.go.relationships.TitanPartOf.TitanPartOfType;
import com.bio4j.titan.model.go.relationships.TitanNegativelyRegulates.TitanNegativelyRegulatesType;

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
      The type of a TitanTerm. This an inner class of the graph. The first key here represents the type of the node, while the rest are for properties of this term: `id` and `name` in this case.
    */
    public TitanKey termTkey;
    public TitanKey termIdKey;
    public TitanKey termNameKey;
    public final TitanTermType termT = new TitanTermType(this);

    //-----------------------------------------------------------------------------------------
    //--------------------------------RELATIONSHIPS--------------------------------------------

    public TitanLabel partOfLabel;
    public TitanPartOfType partOfT = new TitanPartOfType(this);
    public TitanLabel negativelyRegulatesLabel;
    public TitanNegativelyRegulatesType negativelyRegulatesT = new TitanNegativelyRegulatesType(this);

    //================partOf rel============================

    //================hasPartOf rel============================

    public final class TitanHasPartOf
            extends
            TitanRelationship<TitanTerm, TitanTerm.TitanTermType, TitanHasPartOf, TitanHasPartOfType, TitanTerm, TitanTerm.TitanTermType>
            implements
            HasPartOf<TitanTerm, TitanTerm.TitanTermType, TitanHasPartOf, TitanHasPartOfType, TitanTerm, TitanTerm.TitanTermType> {
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
            TitanRelationship.Type<TitanTerm, TitanTerm.TitanTermType, TitanIsA, TitanIsAType, TitanTerm, TitanTerm.TitanTermType>,
            IsAType<TitanTerm, TitanTerm.TitanTermType, TitanIsA, TitanIsAType, TitanTerm, TitanTerm.TitanTermType> {
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

    //================regulates rel============================

    public final class TitanRegulates
            extends
            TitanRelationship<TitanTerm, TitanTermType, TitanRegulates, TitanRegulatesType, TitanTerm, TitanTermType>
            implements
            Regulates<TitanTerm, TitanTermType, TitanRegulates, TitanRegulatesType, TitanTerm, TitanTermType> {

        TitanRegulates(TitanEdge edge) {
            super(edge);
        }

        /*
          Note here how we need a reference to the enclosing graph, which contains the term type value.
        */
        @Override
        public TitanRegulatesType type() {
            return TitanGoGraph.this.regulatesT;
        }
    }

    TitanLabel regulatesLabel;
    TitanRegulatesType regulatesT = new TitanRegulatesType();

    public final class TitanRegulatesType
            implements
            TitanRelationship.Type<TitanTerm, TitanTermType, TitanRegulates, TitanRegulatesType, TitanTerm, TitanTermType>,
            RegulatesType<TitanTerm, TitanTermType, TitanRegulates, TitanRegulatesType, TitanTerm, TitanTermType> {
        @Override
        public TitanLabel label() {
            return TitanGoGraph.this.regulatesLabel;
        }

        @Override
        public TitanRegulatesType value() {
            return TitanGoGraph.this.regulatesT;
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
        public TitanRegulates from(TitanEdge edge) {
            return new TitanRegulates(edge);
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


}
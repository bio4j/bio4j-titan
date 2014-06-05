package com.bio4j.titan.model.go;

import com.ohnosequences.typedGraphs.titan.*;
import com.thinkaurelius.titan.core.*;
import com.bio4j.titan.model.go.nodes.TitanGoTerm.TitanGoTermType;
import com.bio4j.titan.model.go.nodes.TitanGoTerm;
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
      The type of a TitanGoTerm. This an inner class of the graph. The first key here represents the type of the node, while the rest are for properties of this term: `id` and `name` in this case.
    */
    public TitanKey goTermTkey;
    public TitanKey goTermIdKey;
    public TitanKey goTermNameKey;
    public final TitanGoTermType goTermT = new TitanGoTermType(this);

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
            TitanRelationship<TitanGoTerm, TitanGoTerm.TitanGoTermType, TitanHasPartOf, TitanHasPartOfType, TitanGoTerm, TitanGoTerm.TitanGoTermType>
            implements
            HasPartOf<TitanGoTerm, TitanGoTerm.TitanGoTermType, TitanHasPartOf, TitanHasPartOfType, TitanGoTerm, TitanGoTerm.TitanGoTermType> {
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
            TitanRelationship.Type<TitanGoTerm, TitanGoTermType, TitanHasPartOf, TitanHasPartOfType, TitanGoTerm, TitanGoTermType>,
            HasPartOfType<TitanGoTerm, TitanGoTermType, TitanHasPartOf, TitanHasPartOfType, TitanGoTerm, TitanGoTermType> {
        @Override
        public TitanLabel label() {
            return TitanGoGraph.this.hasPartOfLabel;
        }

        @Override
        public TitanHasPartOfType value() {
            return TitanGoGraph.this.hasPartOfT;
        }

        @Override
        public TitanGoTermType sourceType() {
            return TitanGoGraph.this.goTermT;
        }

        @Override
        public TitanGoTermType targetType() {
            return TitanGoGraph.this.goTermT;
        }

        @Override
        public TitanHasPartOf from(TitanEdge edge) {
            return new TitanHasPartOf(edge);
        }
    }

    //================isA rel============================

    public final class TitanIsA
            extends
            TitanRelationship<TitanGoTerm, TitanGoTermType, TitanIsA, TitanIsAType, TitanGoTerm, TitanGoTermType>
            implements
            IsA<TitanGoTerm, TitanGoTermType, TitanIsA, TitanIsAType, TitanGoTerm, TitanGoTermType> {
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
            TitanRelationship.Type<TitanGoTerm, TitanGoTerm.TitanGoTermType, TitanIsA, TitanIsAType, TitanGoTerm, TitanGoTerm.TitanGoTermType>,
            IsAType<TitanGoTerm, TitanGoTerm.TitanGoTermType, TitanIsA, TitanIsAType, TitanGoTerm, TitanGoTerm.TitanGoTermType> {
        @Override
        public TitanLabel label() {
            return TitanGoGraph.this.isALabel;
        }

        @Override
        public TitanIsAType value() {
            return TitanGoGraph.this.isAT;
        }

        @Override
        public TitanGoTermType sourceType() {
            return TitanGoGraph.this.goTermT;
        }

        @Override
        public TitanGoTermType targetType() {
            return TitanGoGraph.this.goTermT;
        }

        @Override
        public TitanIsA from(TitanEdge edge) {
            return new TitanIsA(edge);
        }
    }

    //================regulates rel============================

    public final class TitanRegulates
            extends
            TitanRelationship<TitanGoTerm, TitanGoTermType, TitanRegulates, TitanRegulatesType, TitanGoTerm, TitanGoTermType>
            implements
            Regulates<TitanGoTerm, TitanGoTermType, TitanRegulates, TitanRegulatesType, TitanGoTerm, TitanGoTermType> {

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
            TitanRelationship.Type<TitanGoTerm, TitanGoTermType, TitanRegulates, TitanRegulatesType, TitanGoTerm, TitanGoTermType>,
            RegulatesType<TitanGoTerm, TitanGoTermType, TitanRegulates, TitanRegulatesType, TitanGoTerm, TitanGoTermType> {
        @Override
        public TitanLabel label() {
            return TitanGoGraph.this.regulatesLabel;
        }

        @Override
        public TitanRegulatesType value() {
            return TitanGoGraph.this.regulatesT;
        }

        @Override
        public TitanGoTermType sourceType() {
            return TitanGoGraph.this.goTermT;
        }

        @Override
        public TitanGoTermType targetType() {
            return TitanGoGraph.this.goTermT;
        }

        @Override
        public TitanRegulates from(TitanEdge edge) {
            return new TitanRegulates(edge);
        }
    }

    //================positivelyRegulates rel============================

    public final class TitanPositivelyRegulates
            extends
            TitanRelationship<TitanGoTerm, TitanGoTermType, TitanPositivelyRegulates, TitanPositivelyRegulatesType, TitanGoTerm, TitanGoTermType>
            implements
            PositivelyRegulates<TitanGoTerm, TitanGoTermType, TitanPositivelyRegulates, TitanPositivelyRegulatesType, TitanGoTerm, TitanGoTermType> {

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
            TitanRelationship.Type<TitanGoTerm, TitanGoTermType, TitanPositivelyRegulates, TitanPositivelyRegulatesType, TitanGoTerm, TitanGoTermType>,
            PositivelyRegulatesType<TitanGoTerm, TitanGoTermType, TitanPositivelyRegulates, TitanPositivelyRegulatesType, TitanGoTerm, TitanGoTermType> {
        @Override
        public TitanLabel label() {
            return TitanGoGraph.this.positivelyRegulatesLabel;
        }

        @Override
        public TitanPositivelyRegulatesType value() {
            return TitanGoGraph.this.positivelyRegulatesT;
        }

        @Override
        public TitanGoTermType sourceType() {
            return TitanGoGraph.this.goTermT;
        }

        @Override
        public TitanGoTermType targetType() {
            return TitanGoGraph.this.goTermT;
        }

        @Override
        public TitanPositivelyRegulates from(TitanEdge edge) {
            return new TitanPositivelyRegulates(edge);
        }
    }

    //================negativelyRegulates rel============================


}
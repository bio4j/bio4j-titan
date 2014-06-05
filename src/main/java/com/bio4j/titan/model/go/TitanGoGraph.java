package com.bio4j.titan.model.go;

import com.ohnosequences.typedGraphs.titan.*;
import com.thinkaurelius.titan.core.*;
import com.bio4j.titan.model.go.nodes.TitanGoTerm.TitanGoTermType;
import com.bio4j.titan.model.go.nodes.TitanGoTerm;
import com.bio4j.titan.model.go.relationships.TitanPartOf.TitanPartOfType;
import com.bio4j.titan.model.go.relationships.TitanHasPartOf.TitanHasPartOfType;
import com.bio4j.titan.model.go.relationships.TitanNegativelyRegulates.TitanNegativelyRegulatesType;
import com.bio4j.titan.model.go.relationships.TitanPositivelyRegulates.TitanPositivelyRegulatesType;
import com.bio4j.titan.model.go.relationships.TitanRegulates.TitanRegulatesType;

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
    public TitanLabel hasPartOfLabel;
    public TitanHasPartOfType hasPartOfT = new TitanHasPartOfType(this);
    public TitanLabel regulatesLabel;
    public TitanRegulatesType regulatesT = new TitanRegulatesType(this);
    public TitanLabel positivelyRegulatesLabel;
    public TitanPositivelyRegulatesType positivelyRegulatesT = new TitanPositivelyRegulatesType(this);
    public TitanLabel negativelyRegulatesLabel;
    public TitanNegativelyRegulatesType negativelyRegulatesT = new TitanNegativelyRegulatesType(this);


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


}
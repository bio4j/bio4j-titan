package com.bio4j.titan.model.go.relationships;

import com.bio4j.titan.model.go.nodes.TitanGoTerm;
import com.ohnosequences.typedGraphs.titan.*;

// properties

import com.bio4j.titan.model.go.TitanGoGraph;
import com.bio4j.titan.model.go.nodes.TitanGoTerm.TitanGoTermType;

import com.bio4j.model.go.GoGraph.PartOfType;
import com.bio4j.model.go.relationships.*;

import com.thinkaurelius.titan.core.*;

public final class TitanPartOf
        extends
        TitanRelationship<
                TitanGoTerm, TitanGoTerm.TitanGoTermType,
                TitanPartOf, TitanPartOf.TitanPartOfType,
                TitanGoTerm, TitanGoTerm.TitanGoTermType
                >
        implements
        PartOf<
                TitanGoTerm, TitanGoTerm.TitanGoTermType,
                TitanPartOf, TitanPartOf.TitanPartOfType,
                TitanGoTerm, TitanGoTerm.TitanGoTermType
                > {


    TitanPartOf(TitanEdge edge, TitanGoGraph goGraph) {

        super(edge);
        this.goGraph = goGraph;
    }

    TitanGoGraph goGraph;

    /*
      Note here how we need a reference to the enclosing graph, which contains the term type value.
    */
    @Override
    public TitanPartOfType type() {

        return goGraph.partOfT;
    }

    public static final class TitanPartOfType
            implements
            TitanRelationship.Type<
                    TitanGoTerm, TitanGoTerm.TitanGoTermType,
                    TitanPartOf, TitanPartOf.TitanPartOfType,
                    TitanGoTerm, TitanGoTerm.TitanGoTermType
                    >,
            PartOfType<
                    TitanGoTerm, TitanGoTerm.TitanGoTermType,
                    TitanPartOf, TitanPartOf.TitanPartOfType,
                    TitanGoTerm, TitanGoTerm.TitanGoTermType
                    > {

        TitanGoGraph goGraph;

        public TitanPartOfType(TitanGoGraph goGraph) {

            this.goGraph = goGraph;
        }

        @Override
        public TitanLabel label() {
            return goGraph.partOfLabel;
        }

        @Override
        public TitanPartOfType value() {
            return goGraph.partOfT;
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
        public TitanPartOf from(TitanEdge edge) {
            return new TitanPartOf(edge, goGraph);
        }
    }


}
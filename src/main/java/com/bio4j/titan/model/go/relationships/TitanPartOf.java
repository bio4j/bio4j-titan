package com.bio4j.titan.model.go.relationships;

import com.ohnosequences.typedGraphs.titan.*;

// properties
import com.bio4j.model.properties.*;

import com.bio4j.titan.model.go.TitanGoGraph;
import com.bio4j.titan.model.go.nodes.TitanTerm;
import com.bio4j.titan.model.go.nodes.TitanTerm.TitanTermType;

import com.bio4j.model.go.GoGraph.PartOfType;
import com.bio4j.model.go.relationships.*;

import com.thinkaurelius.titan.core.*;

public final class TitanPartOf
        extends
        TitanRelationship<
                TitanTerm, TitanTerm.TitanTermType,
                TitanPartOf, TitanPartOf.TitanPartOfType,
                TitanTerm, TitanTerm.TitanTermType
                >
        implements
        PartOf<
                TitanTerm, TitanTerm.TitanTermType,
                TitanPartOf, TitanPartOf.TitanPartOfType,
                TitanTerm, TitanTerm.TitanTermType
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
                    TitanTerm, TitanTerm.TitanTermType,
                    TitanPartOf, TitanPartOf.TitanPartOfType,
                    TitanTerm, TitanTerm.TitanTermType
                    >,
            PartOfType<
                    TitanTerm, TitanTerm.TitanTermType,
                    TitanPartOf, TitanPartOf.TitanPartOfType,
                    TitanTerm, TitanTerm.TitanTermType
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
        public TitanTermType sourceType() {
            return goGraph.termT;
        }

        @Override
        public TitanTermType targetType() {
            return goGraph.termT;
        }

        @Override
        public TitanPartOf from(TitanEdge edge) {
            return new TitanPartOf(edge, goGraph);
        }
    }


}
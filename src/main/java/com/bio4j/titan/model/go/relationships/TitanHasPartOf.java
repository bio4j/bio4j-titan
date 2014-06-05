package com.bio4j.titan.model.go.relationships;


import com.bio4j.titan.model.go.nodes.TitanGoTerm;
import com.ohnosequences.typedGraphs.titan.*;

import com.bio4j.titan.model.go.TitanGoGraph;
import com.bio4j.titan.model.go.nodes.TitanGoTerm.TitanGoTermType;

import com.bio4j.model.go.GoGraph.HasPartOfType;
import com.bio4j.model.go.relationships.*;

import com.thinkaurelius.titan.core.*;


/**
*
* @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
* @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo
*         Pareja-Tobes</a>
*/
public final class TitanHasPartOf
        extends
        TitanRelationship<
                TitanGoTerm, TitanGoTerm.TitanGoTermType,
                TitanHasPartOf, TitanHasPartOf.TitanHasPartOfType,
                TitanGoTerm, TitanGoTerm.TitanGoTermType
                >
        implements
        HasPartOf<
                TitanGoTerm, TitanGoTerm.TitanGoTermType,
                TitanHasPartOf, TitanHasPartOf.TitanHasPartOfType,
                TitanGoTerm, TitanGoTerm.TitanGoTermType
                > {


    TitanHasPartOf(TitanEdge edge, TitanGoGraph goGraph) {

        super(edge);
        this.goGraph = goGraph;
    }

    TitanGoGraph goGraph;

    /*
      Note here how we need a reference to the enclosing graph, which contains the term type value.
    */
    @Override
    public TitanHasPartOfType type() {

        return goGraph.hasPartOfT;
    }

    public static final class TitanHasPartOfType
            implements
            TitanRelationship.Type<
                    TitanGoTerm, TitanGoTerm.TitanGoTermType,
                    TitanHasPartOf, TitanHasPartOf.TitanHasPartOfType,
                    TitanGoTerm, TitanGoTerm.TitanGoTermType
                    >,
            HasPartOfType<
                    TitanGoTerm, TitanGoTerm.TitanGoTermType,
                    TitanHasPartOf, TitanHasPartOf.TitanHasPartOfType,
                    TitanGoTerm, TitanGoTerm.TitanGoTermType
                    > {

        TitanGoGraph goGraph;

        public TitanHasPartOfType(TitanGoGraph goGraph) {

            this.goGraph = goGraph;
        }

        @Override
        public TitanLabel label() {
            return goGraph.partOfLabel;
        }

        @Override
        public TitanHasPartOfType value() {
            return goGraph.hasPartOfT;
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
        public TitanHasPartOf from(TitanEdge edge) {
            return new TitanHasPartOf(edge, goGraph);
        }
    }
}


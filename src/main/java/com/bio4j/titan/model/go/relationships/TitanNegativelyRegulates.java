package com.bio4j.titan.model.go.relationships;

import com.bio4j.model.go.GoGraph.NegativelyRegulatesType;
import com.bio4j.titan.model.go.nodes.TitanGoTerm;

import com.bio4j.model.go.relationships.NegativelyRegulates;
import com.bio4j.titan.model.go.nodes.TitanGoTerm.TitanGoTermType;

import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.*;

import com.bio4j.titan.model.go.TitanGoGraph;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo
 *         Pareja-Tobes</a>
 */
public final class TitanNegativelyRegulates
        extends
        TitanRelationship<
                TitanGoTerm, TitanGoTerm.TitanGoTermType,
                TitanNegativelyRegulates, TitanNegativelyRegulates.TitanNegativelyRegulatesType,
                TitanGoTerm, TitanGoTerm.TitanGoTermType
                >
        implements
        NegativelyRegulates<
                TitanGoTerm, TitanGoTerm.TitanGoTermType,
                TitanNegativelyRegulates, TitanNegativelyRegulates.TitanNegativelyRegulatesType,
                TitanGoTerm, TitanGoTerm.TitanGoTermType
                > {

    TitanNegativelyRegulates(TitanEdge edge, TitanGoGraph goGraph) {

        super(edge);
        this.goGraph = goGraph;
    }

    TitanGoGraph goGraph;

    /*
     Note here how we need a reference to the enclosing graph, which contains the term type value.
   */
    @Override
    public TitanNegativelyRegulatesType type() {

        return goGraph.negativelyRegulatesT;
    }

    public static final class TitanNegativelyRegulatesType
            implements
            TitanRelationship.Type<
                    TitanGoTerm, TitanGoTerm.TitanGoTermType,
                    TitanNegativelyRegulates, TitanNegativelyRegulates.TitanNegativelyRegulatesType,
                    TitanGoTerm, TitanGoTerm.TitanGoTermType
                    >,
            NegativelyRegulatesType<
                    TitanGoTerm, TitanGoTerm.TitanGoTermType,
                    TitanNegativelyRegulates, TitanNegativelyRegulates.TitanNegativelyRegulatesType,
                    TitanGoTerm, TitanGoTerm.TitanGoTermType
                    > {

        TitanGoGraph goGraph;

        public TitanNegativelyRegulatesType(TitanGoGraph goGraph) {

            this.goGraph = goGraph;
        }

        @Override
        public TitanLabel label() {
            return goGraph.negativelyRegulatesLabel;
        }

        @Override
        public TitanNegativelyRegulatesType value() {
            return goGraph.negativelyRegulatesT;
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
        public TitanNegativelyRegulates from(TitanEdge edge) {
            return new TitanNegativelyRegulates(edge, goGraph);
        }
    }

}

package com.bio4j.titan.model.go.relationships;

import com.bio4j.model.go.GoGraph.NegativelyRegulatesType;
import com.bio4j.titan.model.go.nodes.TitanTerm;

import com.bio4j.model.go.relationships.NegativelyRegulates;
import com.bio4j.titan.model.go.nodes.TitanTerm.TitanTermType;

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
                TitanTerm, TitanTerm.TitanTermType,
                TitanNegativelyRegulates, TitanNegativelyRegulates.TitanNegativelyRegulatesType,
                TitanTerm, TitanTerm.TitanTermType
                >
        implements
        NegativelyRegulates<
                TitanTerm, TitanTerm.TitanTermType,
                TitanNegativelyRegulates, TitanNegativelyRegulates.TitanNegativelyRegulatesType,
                TitanTerm, TitanTerm.TitanTermType
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
                    TitanTerm, TitanTerm.TitanTermType,
                    TitanNegativelyRegulates, TitanNegativelyRegulates.TitanNegativelyRegulatesType,
                    TitanTerm, TitanTerm.TitanTermType
                    >,
            NegativelyRegulatesType<
                    TitanTerm, TitanTerm.TitanTermType,
                    TitanNegativelyRegulates, TitanNegativelyRegulates.TitanNegativelyRegulatesType,
                    TitanTerm, TitanTerm.TitanTermType
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
        public TitanTermType sourceType() {
            return goGraph.termT;
        }

        @Override
        public TitanTermType targetType() {
            return goGraph.termT;
        }

        @Override
        public TitanNegativelyRegulates from(TitanEdge edge) {
            return new TitanNegativelyRegulates(edge, goGraph);
        }
    }

}

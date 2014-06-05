package com.bio4j.titan.model.go.relationships;

import com.bio4j.titan.model.go.nodes.TitanGoTerm;
import com.ohnosequences.typedGraphs.titan.*;

import com.thinkaurelius.titan.core.*;

import com.bio4j.titan.model.go.TitanGoGraph;
import com.bio4j.model.go.relationships.*;

import com.bio4j.model.go.GoGraph.RegulatesType;
import com.bio4j.titan.model.go.nodes.TitanGoTerm.TitanGoTermType;

/**
*
* @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
* @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo
*         Pareja-Tobes</a>
*/
public final class TitanRegulates
        extends
        TitanRelationship<
                TitanGoTerm, TitanGoTerm.TitanGoTermType,
                TitanRegulates, TitanRegulates.TitanRegulatesType,
                TitanGoTerm, TitanGoTerm.TitanGoTermType
                >
        implements
        Regulates<
                TitanGoTerm, TitanGoTerm.TitanGoTermType,
                TitanRegulates, TitanRegulates.TitanRegulatesType,
                TitanGoTerm, TitanGoTerm.TitanGoTermType
                >  {

    TitanRegulates(TitanEdge edge, TitanGoGraph goGraph) {

        super(edge);
        this.goGraph = goGraph;
    }

    TitanGoGraph goGraph;

    /*
      Note here how we need a reference to the enclosing graph, which contains the term type value.
    */
    @Override
    public TitanRegulatesType type() {
        return goGraph.regulatesT;
    }

    public static final class TitanRegulatesType
            implements
            TitanRelationship.Type<
                    TitanGoTerm, TitanGoTerm.TitanGoTermType,
                    TitanRegulates, TitanRegulates.TitanRegulatesType,
                    TitanGoTerm, TitanGoTerm.TitanGoTermType
                    >,
            RegulatesType<
                    TitanGoTerm, TitanGoTerm.TitanGoTermType,
                    TitanRegulates, TitanRegulates.TitanRegulatesType,
                    TitanGoTerm, TitanGoTerm.TitanGoTermType
                    > {

        TitanGoGraph goGraph;

        public TitanRegulatesType(TitanGoGraph goGraph) {

            this.goGraph = goGraph;
        }

        @Override
        public TitanLabel label() {
            return goGraph.regulatesLabel;
        }

        @Override
        public TitanRegulatesType value() {
            return goGraph.regulatesT;
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
        public TitanRegulates from(TitanEdge edge) {
            return new TitanRegulates(edge, goGraph);
        }
    }

}

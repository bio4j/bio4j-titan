package com.bio4j.titan.model.go.relationships;

import com.bio4j.titan.model.go.nodes.TitanGoTerm;
import com.bio4j.titan.model.go.nodes.TitanSubOntologies;
import com.ohnosequences.typedGraphs.titan.*;

import com.thinkaurelius.titan.core.*;

import com.bio4j.titan.model.go.TitanGoGraph;
import com.bio4j.model.go.relationships.*;
import com.bio4j.model.go.GoGraph.SubOntologyType;

import com.bio4j.titan.model.go.nodes.TitanSubOntologies;
import com.bio4j.titan.model.go.nodes.TitanGoTerm;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo
 *         Pareja-Tobes</a>
 */
public final class TitanSubOntology
        extends
        TitanRelationship<
                TitanGoTerm, TitanGoTerm.TitanGoTermType,
                TitanSubOntology, TitanSubOntology.TitanSubOntologyType,
                TitanSubOntologies, TitanSubOntologies.TitanSubOntologiesType
                >
        implements
        SubOntology<
                TitanGoTerm, TitanGoTerm.TitanGoTermType,
                TitanSubOntology, TitanSubOntology.TitanSubOntologyType,
                TitanSubOntologies, TitanSubOntologies.TitanSubOntologiesType
                >  {

    TitanSubOntology(TitanEdge edge, TitanGoGraph goGraph) {

        super(edge);
        this.goGraph = goGraph;
    }

    TitanGoGraph goGraph;

    /*
      Note here how we need a reference to the enclosing graph, which contains the term type value.
    */
    @Override
    public TitanSubOntologyType type() {
        return goGraph.subOntologyT;
    }

    public static final class TitanSubOntologyType
            implements
            TitanRelationship.Type<
                    TitanGoTerm, TitanGoTerm.TitanGoTermType,
                    TitanSubOntology, TitanSubOntology.TitanSubOntologyType,
                    TitanSubOntologies, TitanSubOntologies.TitanSubOntologiesType
                    >,
            SubOntologyType<
                    TitanGoTerm, TitanGoTerm.TitanGoTermType,
                    TitanSubOntology, TitanSubOntology.TitanSubOntologyType,
                    TitanSubOntologies, TitanSubOntologies.TitanSubOntologiesType
                    > {

        TitanGoGraph goGraph;

        public TitanSubOntologyType(TitanGoGraph goGraph) {

            this.goGraph = goGraph;
        }

        @Override
        public TitanLabel label() {
            return goGraph.subOntologyLabel;
        }

        @Override public TitanSubOntology.TitanSubOntologyType value() {
            return goGraph.subOntologyT;
        }

        @Override public TitanGoTerm.TitanGoTermType sourceType() {
            return goGraph.goTermT;
        }

        @Override public TitanSubOntologies.TitanSubOntologiesType targetType() {  return goGraph.subOntologiesT;   }

        @Override
        public TitanSubOntology from(TitanEdge edge) {
            return new TitanSubOntology(edge, goGraph);
        }
    }

}

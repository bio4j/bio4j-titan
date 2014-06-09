package com.bio4j.titan.model.go.nodes;

import java.util.List;


import com.bio4j.model.go.GoGraph;
import com.bio4j.model.go.nodes.*;
// properties
import com.bio4j.model.properties.*;
// relationships
import com.bio4j.model.go.relationships.*;
import com.bio4j.titan.model.go.relationships.*;
import com.bio4j.titan.model.go.TitanGoGraph;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.*;
import com.bio4j.model.go.GoGraph.SubOntologiesType;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo
 *         Pareja-Tobes</a>
 */
public final class TitanSubOntologies
        extends
        TitanNode<TitanSubOntologies, TitanSubOntologies.TitanSubOntologiesType>
        implements
        SubOntologies<TitanSubOntologies, TitanSubOntologies.TitanSubOntologiesType> {


    public TitanSubOntologies(TitanVertex vertex, TitanGoGraph goGraph) {
        super(vertex);
        this.goGraph = goGraph;
    }

    TitanGoGraph goGraph;

    @Override
    public TitanSubOntologiesType type() {
        return goGraph.subOntologiesT;
    }


    public static final class TitanSubOntologiesType
            implements
            TitanNode.Type<TitanGoTerm, TitanGoTerm.TitanGoTermType>,
            SubOntologiesType<TitanSubOntologies, TitanSubOntologiesType> {

        public TitanSubOntologiesType(TitanGoGraph goGraph) {
            this.goGraph = goGraph;
        }

        TitanGoGraph goGraph;

        @Override
        public TitanKey titanKey() {
            return goGraph.subOntologiesTKey;
        }

        @Override
        public TitanSubOntologiesType value() {
            return goGraph.subOntologiesT;
        }

        @Override
        public TitanGoTerm fromTitanVertex(TitanVertex vertex) {

            return new TitanGoTerm(vertex, goGraph);
        }

        name name = new name();

        @Override
        public name Name() {

            return name;
        }

        public final class name
                implements
                com.ohnosequences.typedGraphs.titan.TitanProperty<TitanSubOntologies, TitanSubOntologiesType, name, String>,
                SubOntologies.name<TitanSubOntologies, TitanSubOntologiesType, name> {

            @Override
            public TitanSubOntologies elementType() {

                return TitanSubOntologies.this;
            }

            @Override
            public TitanKey titanKey() {

                return goGraph.subOntologiesNameKey;
            }
        }
    }

    // MolecularFunction
    // incoming
    public List<? extends MolecularFunction> molecularFunction_in() {
        return inFromMany(graph().titanMolecularFunctionType());
    }

    public List<? extends Term> term_inNodes() {
        return inFromMany_Nodes(graph().titanMolecularFunctionType());
    }

    // BiologicalProcess
    // incoming
    public List<? extends BiologicalProcess> biologicalProcess_in() {
        return inFromMany(graph().titanBiologicalProcessType());
    }

    public List<? extends Term> biologicalProcess_inNodes() {
        return inFromMany_Nodes(graph().titanBiologicalProcessType());
    }

    // CellularComponent
    // incoming
    public List<? extends CellularComponent> cellularComponent_in() {
        return inFromMany(graph().titanCellularComponentType());
    }

    public List<? extends Term> cellularComponent_inNodes() {
        return inFromMany_Nodes(graph().titanCellularComponentType());
    }

}
package com.bio4j.titan.model.go.nodes;

import java.util.List;


import com.bio4j.model.go.nodes.*;
// properties
import com.bio4j.model.properties.*;
// relationships
import com.bio4j.model.go.relationships.*;
import com.bio4j.titan.model.go.relationships.*;
import com.bio4j.titan.model.go.TitanGoGraph;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.*;

/**
*
* @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
* @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo
*         Pareja-Tobes</a>
*/
public final class TitanSubOntologies
		extends
		TitanGoGraph.GoNode<SubOntologies, SubOntologies.Type, TitanSubOntologies, TitanGoGraph.TitanSubOntologiesType>
		implements SubOntologies {

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

	public TitanSubOntologies(TitanVertex vertex, TitanGoGraph graph) {
		super(vertex, graph);
	}

	@Override
	public TitanSubOntologiesType titanType() {
		return graph().titanSubOntologiesType();
	}

    public static final class TitanSubOntologiesType
            implements
            TitanNode.Type<TitanGoTerm, TitanGoTerm.TitanGoTermType>,
            TermType<TitanSubOntologies, TitanSubOntologies.TitanSubOntologiesType> {

        public TitanSubOntologiesType(TitanGoGraph goGraph) {
            this.goGraph = goGraph;
        }

        TitanGoGraph goGraph;

//        @Override
//        public TitanKey titanKey() {
//
//            return goGraph.goTermTkey;
//        }

        @Override
        public TitanSubOntologiesType value() {

            return goGraph.subOntologiesT;
        }

        @Override
        public TitanGoTerm fromTitanVertex(TitanVertex vertex) {

            return new TitanGoTerm(vertex, goGraph);
        }

        // properties
        public id id = new id();

        // no need to worry about the unchecked warning
        @Override
        public id Id() {

            return id;
        }

        public final class id
                implements
                com.ohnosequences.typedGraphs.titan.TitanProperty<TitanGoTerm, TitanGoTermType, id, String>,
                Term.id<TitanGoTerm, TitanGoTermType, id> {

            @Override
            public TitanGoTermType elementType() {

                return TitanGoTermType.this;
            }

            @Override
            public TitanKey titanKey() {

                return goGraph.goTermIdKey;
            }
        }

        name name = new name();

        @Override
        public name Name() {

            return name;
        }

        public final class name
                implements
                com.ohnosequences.typedGraphs.titan.TitanProperty<TitanGoTerm, TitanGoTermType, name, String>,
                Term.name<TitanGoTerm, TitanGoTermType, name> {

            @Override
            public TitanGoTermType elementType() {

                return TitanGoTermType.this;
            }

            @Override
            public TitanKey titanKey() {

                return goGraph.goTermNameKey;
            }
        }
    }

}
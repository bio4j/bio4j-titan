package com.bio4j.titan.model.go.nodes;

import java.util.List;

import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.ohnosequences.typedGraphs.titan.TitanNodeType;

import com.bio4j.model.go.nodes.*;
// properties
import com.bio4j.model.properties.*;
// relationships
import com.bio4j.model.go.relationships.*;
import com.bio4j.titan.model.go.relationships.*;
import com.bio4j.titan.model.go.TitanGoGraph;
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
	public TitanGoGraph.TitanSubOntologiesType titanType() {
		return graph().titanSubOntologiesType();
	}

}
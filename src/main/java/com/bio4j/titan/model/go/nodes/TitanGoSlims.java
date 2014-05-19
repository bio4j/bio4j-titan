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
public final class TitanGoSlims
		extends
		TitanGoGraph.GoNode<GoSlims, GoSlims.Type, TitanGoSlims, TitanGoGraph.TitanGoSlimsType>
		implements GoSlims {

	public TitanGoSlims(TitanVertex vertex, TitanGoGraph graph) {
		super(vertex, graph);
	}

	@Override
	public TitanGoGraph.TitanGoSlimsType titanType() {
		return graph().titanGoSlimsType();
	}

}
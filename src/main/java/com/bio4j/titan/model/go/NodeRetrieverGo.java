package com.bio4j.titan.model.go;

import com.ohnosequences.typedGraphs.Retriever;
import com.thinkaurelius.titan.core.TitanIndexQuery;
import com.tinkerpop.blueprints.Vertex;

import com.bio4j.titan.model.go.nodes.TitanTerm;

import java.util.Iterator;

public class NodeRetrieverGo extends Retriever {

    protected TitanGoGraphImpl graph;

	NodeRetrieverGo(TitanGoGraphImpl titanGoGraph) {
		super();
		graph = titanGoGraph;
	}

	public TitanTerm getTermById(String id){
        Iterator<TitanIndexQuery.Result<Vertex>> verticesIt = graph.rawGraph.indexQuery(graph.termT.id.fullName(), id).vertices().iterator();
        if(verticesIt.hasNext()){
            TitanTerm term = new TitanTerm(verticesIt.next().getElement(), graph);
        }else{
            return null;
        }
	}

}

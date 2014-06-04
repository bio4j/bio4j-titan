package com.bio4j.titan.model.go;

import com.ohnosequences.typedGraphs.Retriever;
import com.thinkaurelius.titan.core.TitanIndexQuery;
import com.tinkerpop.blueprints.Vertex;

import java.util.Iterator;

public class NodeRetrieverGo extends Retriever{

    protected TitanGoGraphImpl graph;

	NodeRetrieverGo(TitanGoGraphImpl titanGoGraph) {
		super();
		graph = titanGoGraph;
	}

	public TitanGoGraph.TitanTerm getTermById(String id){
        Iterator<TitanIndexQuery.Result<Vertex>> verticesIt = graph.rawGraph.indexQuery(graph.termT.id.fullName(), id).vertices().iterator();
        if(verticesIt.hasNext()){
            TitanGoGraph.TitanTerm term = new TitanGoGraph.TitanTerm(verticesIt.next().getElement());
        }else{
            return null;
        }
	}

}

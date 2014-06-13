package com.bio4j.titan.model.go;

import com.ohnosequences.typedGraphs.Retriever;
import com.thinkaurelius.titan.core.TitanIndexQuery;
import com.thinkaurelius.titan.core.TitanVertex;
import com.tinkerpop.blueprints.Vertex;

import com.bio4j.titan.model.go.nodes.TitanGoTerm;

import java.util.Iterator;

public class NodeRetrieverGo extends Retriever {

    protected TitanGoGraphImpl graph;

	NodeRetrieverGo(TitanGoGraphImpl titanGoGraph) {
		super();
		graph = titanGoGraph;
	}

	public TitanGoTerm getTermById(String id){
        Iterator<TitanIndexQuery.Result<Vertex>> verticesIt = graph.rawGraph.indexQuery(graph.goTermT.id.fullName(), id).vertices().iterator();
        if(verticesIt.hasNext()){
            //TitanGoTerm term = new TitanGoTerm(verticesIt.next().getElement(), graph);
        }else{
            return null;
        }
	}

}

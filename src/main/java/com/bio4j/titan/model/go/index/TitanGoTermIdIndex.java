package com.bio4j.titan.model.go.index;

import com.bio4j.titan.model.go.TitanGoGraph;
import com.bio4j.titan.model.go.nodes.TitanGoTerm;
import com.ohnosequences.typedGraphs.titan.TitanNodeIndex;

import java.util.List;

/**
 * Created by ppareja on 6/16/2014.
 */
public class TitanGoTermIdIndex extends TitanNodeIndex.DefaultUnique {

    public TitanGoTermIdIndex(TitanGoGraph graph) {
        super(graph, graph.goTermT.id);
    }


    public TitanGoTerm getGoTermById(String id){
        return new TitanGoTerm(this.getNode(id));
    }
}

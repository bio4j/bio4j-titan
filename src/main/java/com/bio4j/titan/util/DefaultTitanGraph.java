package com.bio4j.titan.util;

import com.ohnosequences.typedGraphs.titan.TitanUntypedGraph;
import com.thinkaurelius.titan.core.TitanGraph;

/**
 * Created by raquel on 26/08/14.
 */
public final class DefaultTitanGraph implements TitanUntypedGraph {

    private TitanGraph titanGraph;
    public DefaultTitanGraph(TitanGraph titanGraph) {

        this.titanGraph = titanGraph;
    }

    @Override public TitanGraph titanGraph() { return titanGraph; }
}

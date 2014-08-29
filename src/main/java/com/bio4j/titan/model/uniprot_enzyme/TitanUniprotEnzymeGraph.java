package com.bio4j.titan.model.uniprot_enzyme;

import com.bio4j.model.enzymedb.EnzymeDBGraph;
import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot_enzymedb.UniprotEnzymeDBGraph;
import com.bio4j.titan.model.enzyme.TitanEnzymeDBGraph;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.ohnosequences.typedGraphs.titan.TitanTypedVertexIndex;
import com.thinkaurelius.titan.core.*;


/**
 Implementing the types with Titan
 @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class TitanUniprotEnzymeGraph
        extends
        UniprotEnzymeDBGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> {

    private DefaultTitanGraph rawGraph;
    private UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> uniprotRawGraph;
    private EnzymeDBGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> enzymeDBRawGraph;


    public TitanUniprotEnzymeGraph(DefaultTitanGraph rawGraph) {
        super(rawGraph);
        this.rawGraph = rawGraph;
        this.uniprotRawGraph = new TitanUniprotGraph(rawGraph);
        this.enzymeDBRawGraph = new TitanEnzymeDBGraph(rawGraph);
        initTypes();
        initIndices();
    }

    @Override
    public DefaultTitanGraph raw() {
        return rawGraph;
    }

    private void initTypes() {


    }

    private void initIndices() {

    }


    @Override
    public UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> uniprotGraph() {
        return uniprotRawGraph;
    }

    @Override
    public EnzymeDBGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> enzymeDBGraph() {
        return enzymeDBRawGraph;
    }

    @Override
    public EnzymaticActivityType EnzymaticActivity() {
        return null;
    }
}
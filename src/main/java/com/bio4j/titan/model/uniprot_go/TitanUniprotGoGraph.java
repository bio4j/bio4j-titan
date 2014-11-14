package com.bio4j.titan.model.uniprot_go;

import com.bio4j.model.go.GoGraph;
import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot_go.UniprotGoGraph;
import com.bio4j.titan.model.go.TitanGoGraph;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.*;


/**
 Implementing the types with Titan
 @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class TitanUniprotGoGraph
        extends
        UniprotGoGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> {

    private DefaultTitanGraph rawGraph;
    private TitanUniprotGraph uniprotRawGraph;
    private TitanGoGraph goRawGraph;

    //---------------RELATIONSHIPS---------------------------

    private EdgeLabel goAnnotationLabel;
    private GoAnnotationType goAnnotationType;


    public TitanUniprotGoGraph(DefaultTitanGraph rawGraph, TitanUniprotGraph titanUniprotGraph, TitanGoGraph titanGoGraph) {
        super(rawGraph);
        this.rawGraph = rawGraph;
        this.uniprotRawGraph = titanUniprotGraph;
        this.goRawGraph = titanGoGraph;
        initTypes();
        initIndices();
    }

    @Override
    public DefaultTitanGraph raw() {
        return rawGraph;
    }

    private void initTypes() {

        //-----------------------------------------------------------------------------------------
        //--------------------------------RELATIONSHIPS--------------------------------------------

        goAnnotationLabel = raw().titanLabelForEdgeType(new GoAnnotationType((EdgeLabel) null));
        goAnnotationType = new GoAnnotationType(goAnnotationLabel);

    }

    private void initIndices() {

    }


    @Override
    public UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> uniprotGraph() {
        return uniprotRawGraph;
    }

    @Override
    public GoGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> goGraph() {
        return goRawGraph;
    }

    @Override
    public GoAnnotationType GoAnnotation() {
        return goAnnotationType;
    }

}
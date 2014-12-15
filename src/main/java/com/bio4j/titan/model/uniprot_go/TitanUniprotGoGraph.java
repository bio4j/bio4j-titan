package com.bio4j.titan.model.uniprot_go;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot_go.UniprotGoGraph;
import com.bio4j.titan.model.go.TitanGoGraph;
import com.bio4j.titan.model.uniprot.TitanUniProtGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.*;


/**
 Implementing the types with Titan
 @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class TitanUniProtGoGraph
        extends
        UniprotGoGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> {

    private TitanUniProtGraph uniprotGraph;
    private TitanGoGraph goGraph;

	private TitanManagement mgmt = null;

    //---------------RELATIONSHIPS---------------------------

    private EdgeLabel goAnnotationLabel;
    private GoAnnotationType goAnnotationType;


    public TitanUniProtGoGraph(DefaultTitanGraph rawGraph, TitanUniProtGraph titanUniProtGraph, TitanGoGraph titanGoGraph) {
        super(rawGraph);
        this.raw = rawGraph;

	    // First get a titanMgmt instance, that will be used throughout
	    this.mgmt = rawGraph.managementSystem();
        initTypes(mgmt);
        initIndices(mgmt);

	    // this should work now
	    mgmt.commit();

        this.goGraph        =       titanGoGraph.withUniprotGoGraph(this);
        this.uniprotGraph   =  titanUniProtGraph.withUniprotGoGraph(this);
    }

    @Override
    public DefaultTitanGraph raw() {
        return raw;
    }

    private void initTypes(TitanManagement mgmt) {

        //-----------------------------------------------------------------------------------------
        //--------------------------------RELATIONSHIPS--------------------------------------------

	    // goAnnotation
	    EdgeLabelMaker goAnnotationTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new GoAnnotationType(null));
	    goAnnotationType = new GoAnnotationType(goAnnotationTypeLabelMaker);
        goAnnotationLabel = raw().createOrGet(mgmt, goAnnotationType.raw());

    }

    private void initIndices(TitanManagement mgmt) {

    }


    @Override
    public TitanUniProtGraph uniprotGraph() {

        return uniprotGraph;
    }

    @Override
    public TitanGoGraph goGraph() {
        
        return goGraph;
    }

    @Override
    public GoAnnotationType GoAnnotation() {
        return goAnnotationType;
    }

}
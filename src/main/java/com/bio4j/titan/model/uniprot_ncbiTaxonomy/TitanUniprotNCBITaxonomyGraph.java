package com.bio4j.titan.model.uniprot_ncbiTaxonomy;

import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph;
import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot_ncbiTaxonomy.UniprotNCBITaxonomyGraph;
import com.bio4j.titan.model.ncbiTaxonomy.TitanNCBITaxonomyGraph;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.*;


/**
 Implementing the types with Titan
 @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class TitanUniprotNCBITaxonomyGraph
        extends
        UniprotNCBITaxonomyGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> {

    private UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> uniprotRawGraph;
    private NCBITaxonomyGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> ncbiTaxonomyRawGraph;

	private TitanManagement mgmt = null;


    //---------------RELATIONSHIPS---------------------------
    private EdgeLabel proteinNCBITaxonLabel;
    private ProteinNCBITaxonType proteinNCBITaxonType;

    public TitanUniprotNCBITaxonomyGraph(DefaultTitanGraph rawGraph, TitanUniprotGraph titanUniprotGraph, TitanNCBITaxonomyGraph titanNCBITaxonomyGraph) {
        super(rawGraph);
        this.raw = rawGraph;
        this.uniprotRawGraph = titanUniprotGraph;
        this.ncbiTaxonomyRawGraph = titanNCBITaxonomyGraph;

	    // First get a titanMgmt instance, that will be used throughout
	    this.mgmt = rawGraph.managementSystem();
        initTypes(mgmt);
        initIndices(mgmt);

	    // this should work now
	    mgmt.commit();
    }

    @Override
    public DefaultTitanGraph raw() {
        return raw;
    }

    private void initTypes(TitanManagement mgmt) {

        //-----------------------------------------------------------------------------------------
        //--------------------------------RELATIONSHIPS--------------------------------------------

	    // proteinNCBITaxon
	    EdgeLabelMaker proteinNCBITaxonTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new ProteinNCBITaxonType(null));
	    proteinNCBITaxonType = new ProteinNCBITaxonType(proteinNCBITaxonTypeLabelMaker);
        proteinNCBITaxonLabel = raw().createOrGet(mgmt, proteinNCBITaxonType.raw());

    }

    private void initIndices(TitanManagement mgmt) {

    }


    @Override
    public UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> uniprotGraph() {
        return uniprotRawGraph;
    }

    @Override
    public NCBITaxonomyGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> ncbiTaxonomyGraph() {
        return ncbiTaxonomyRawGraph;
    }

    @Override
    public ProteinNCBITaxonType ProteinNCBITaxon() {
        return proteinNCBITaxonType;
    }

}
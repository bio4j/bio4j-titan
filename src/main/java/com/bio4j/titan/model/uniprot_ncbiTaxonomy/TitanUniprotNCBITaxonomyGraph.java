package com.bio4j.titan.model.uniprot_ncbiTaxonomy;

import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph;
import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot_ncbiTaxonomy.UniProtNCBITaxonomyGraph;
import com.bio4j.titan.model.ncbiTaxonomy.TitanNCBITaxonomyGraph;
import com.bio4j.titan.model.uniprot.TitanUniProtGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.*;


/**
 Implementing the types with Titan
 @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class TitanUniProtNCBITaxonomyGraph
        extends
        UniProtNCBITaxonomyGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> {

    private TitanUniProtGraph uniProtGraph;
    private TitanNCBITaxonomyGraph ncbiTaxonomyGraph;

	private TitanManagement mgmt = null;


    //---------------RELATIONSHIPS---------------------------
    private EdgeLabel proteinNCBITaxonLabel;
    private ProteinNCBITaxonType proteinNCBITaxonType;

    public TitanUniProtNCBITaxonomyGraph(
            DefaultTitanGraph rawGraph,
            TitanUniprotGraph titanUniprotGraph,
            TitanNCBITaxonomyGraph titanNCBITaxonomyGraph
    ) {
        super(rawGraph);
        this.raw = rawGraph;

	    // First get a titanMgmt instance, that will be used throughout
	    this.mgmt = rawGraph.managementSystem();
        initTypes(mgmt);
        initIndices(mgmt);

	    // this should work now
	    mgmt.commit();

        /* update dependencies */
        this.uniProtGraph       =      titanUniprotGraph.withUniProtNCBITaxonomyGraph(this);
        this.ncbiTaxonomyGraph  = titanNCBITaxonomyGraph.withUniProtNCBITaxonomyGraph(this);
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
    public TitanUniProtGraph uniProtGraph() {
        return uniProtGraph;
    }

    @Override
    public TitanNCBITaxonomyGraph ncbiTaxonomyGraph() {
        return ncbiTaxonomyGraph;
    }

    @Override
    public ProteinNCBITaxonType ProteinNCBITaxon() {
        return proteinNCBITaxonType;
    }

}
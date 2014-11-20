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

    private DefaultTitanGraph rawGraph;
    private UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> uniprotRawGraph;
    private NCBITaxonomyGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> ncbiTaxonomyRawGraph;


    //---------------RELATIONSHIPS---------------------------
    private EdgeLabel proteinNCBITaxonLabel;
    private ProteinNCBITaxonType proteinNCBITaxonType;

    public TitanUniprotNCBITaxonomyGraph(DefaultTitanGraph rawGraph, TitanUniprotGraph titanUniprotGraph, TitanNCBITaxonomyGraph titanNCBITaxonomyGraph) {
        super(rawGraph);
        this.rawGraph = rawGraph;
        this.uniprotRawGraph = titanUniprotGraph;
        this.ncbiTaxonomyRawGraph = titanNCBITaxonomyGraph;
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

        // proteinNCBITaxonLabel = raw().titanLabelForEdgeType(new ProteinNCBITaxonType((EdgeLabel) null));
        // proteinNCBITaxonType = new ProteinNCBITaxonType(proteinNCBITaxonLabel);

    }

    private void initIndices() {

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
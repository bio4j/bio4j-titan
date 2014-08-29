package com.bio4j.titan.model.uniprot_ncbiTaxonomy;

import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph;
import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot_ncbiTaxonomy.UniprotNCBITaxonomyGraph;
import com.bio4j.titan.model.ncbiTaxonomy.TitanNCBITaxonomyGraph;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;


/**
 Implementing the types with Titan
 @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class TitanUniprotNCBITaxonomyGraph
        extends
        UniprotNCBITaxonomyGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> {

    private DefaultTitanGraph rawGraph;
    private UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> uniprotRawGraph;
    private NCBITaxonomyGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> ncbiTaxonomyRawGraph;


    //---------------RELATIONSHIPS---------------------------
    private TitanLabel proteinNCBITaxonLabel;
    private ProteinNCBITaxonType proteinNCBITaxonType;

    public TitanUniprotNCBITaxonomyGraph(DefaultTitanGraph rawGraph) {
        super(rawGraph);
        this.rawGraph = rawGraph;
        this.uniprotRawGraph = new TitanUniprotGraph(rawGraph);
        this.ncbiTaxonomyRawGraph = new TitanNCBITaxonomyGraph(rawGraph);
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

        proteinNCBITaxonLabel = raw().titanLabelForEdgeType(new ProteinNCBITaxonType((TitanLabel) null));
        proteinNCBITaxonType = new ProteinNCBITaxonType(proteinNCBITaxonLabel);

    }

    private void initIndices() {

    }


    @Override
    public UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> uniprotGraph() {
        return uniprotRawGraph;
    }

    @Override
    public NCBITaxonomyGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> ncbiTaxonomyGraph() {
        return ncbiTaxonomyRawGraph;
    }

    @Override
    public ProteinNCBITaxonType ProteinNCBITaxon() {
        return proteinNCBITaxonType;
    }

}
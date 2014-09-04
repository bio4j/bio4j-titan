package com.bio4j.titan.model.ncbiTaxonomy;

import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph;
import com.bio4j.model.ncbiTaxonomy.nodes.NCBITaxon;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.ohnosequences.typedGraphs.titan.TitanTypedVertexIndex;
import com.thinkaurelius.titan.core.*;


/**
 Implementing the types with Titan
 @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class TitanNCBITaxonomyGraph
        extends
        NCBITaxonomyGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> {

    private DefaultTitanGraph rawGraph;


    //-------------------VERTICES----------------------------

    public TitanKey nCBITaxonTypekey;
    public TitanKey nCBITaxonIdkey;
    public NCBITaxonType nCBITaxonType;


    //---------------INDICES---------------------------

    TitanTypedVertexIndex.DefaultUnique<
            NCBITaxon<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>,
            NCBITaxonType,
            NCBITaxonType.id, String,
            NCBITaxonomyGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>,
            DefaultTitanGraph
            > nCBITaxonIdIndex;

    public TitanNCBITaxonomyGraph(DefaultTitanGraph rawGraph) {
        super(rawGraph);
        this.rawGraph = rawGraph;
        initTypes();
        initIndices();
    }

    @Override
    public DefaultTitanGraph raw() {
        return rawGraph;
    }

    private void initTypes() {

        //-----------------------------------------------------------------------------------------
        //--------------------------------VERTICES--------------------------------------------
	    nCBITaxonType = new NCBITaxonType(nCBITaxonTypekey);
        nCBITaxonTypekey = raw().titanKeyMakerForVertexType(NCBITaxon().id).single().unique().make();
        nCBITaxonIdkey = nCBITaxonTypekey;


    }

    private void initIndices() {
        nCBITaxonIdIndex =  new TitanTypedVertexIndex.DefaultUnique<>(this, NCBITaxon().id);
    }


    @Override
    public NCBITaxonType NCBITaxon() {
        return nCBITaxonType;
    }

    @Override
    public NCBITaxonParentType NCBITaxonParent() {
        return null;
    }
}
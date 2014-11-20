package com.bio4j.titan.model.ncbiTaxonomy;

import com.bio4j.angulillos.TypedVertexIndex;
import com.bio4j.angulillos.titan.TitanTypedVertexIndex;
import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph;
import com.bio4j.model.ncbiTaxonomy.vertices.NCBITaxon;
import com.bio4j.model.ncbiTaxonomy_geninfo.NCBITaxonomyGenInfoGraph;
import com.bio4j.model.uniprot_ncbiTaxonomy.UniprotNCBITaxonomyGraph;
import com.bio4j.titan.model.uniprot_ncbiTaxonomy.TitanUniprotNCBITaxonomyGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.*;


/**
 Implementing the types with Titan
 @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class TitanNCBITaxonomyGraph
        extends
        NCBITaxonomyGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> {

    private DefaultTitanGraph rawGraph;
	private TitanUniprotNCBITaxonomyGraph uniprotNCBITaxonomyGraph = null;


    //-------------------VERTICES----------------------------

    public PropertyKey nCBITaxonTypekey;
    public PropertyKey nCBITaxonIdkey;
	public PropertyKey nCBITaxonScientificNamekey;
	public PropertyKey nCBITaxonTaxonomicRankkey;
    public NCBITaxonType nCBITaxonType;

	//---------------RELATIONSHIPS---------------------------

	private EdgeLabel nCBITaxonParentLabel;
	private NCBITaxonParentType ncbiTaxonParentType;


    //---------------INDICES---------------------------

    TitanTypedVertexIndex.DefaultUnique<
            NCBITaxon<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
            NCBITaxonType,
            NCBITaxonType.id, String,
            NCBITaxonomyGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
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

	@Override
	public TypedVertexIndex.Unique<NCBITaxon<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, NCBITaxonType, NCBITaxonType.id, String, NCBITaxonomyGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> nCBITaxonIdIndex() {
		return nCBITaxonIdIndex;
	}

	@Override
	public UniprotNCBITaxonomyGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> uniprotNCBITaxonomyGraph() {
		return uniprotNCBITaxonomyGraph;
	}

	@Override
	public NCBITaxonomyGenInfoGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> ncbiTaxonomyGenInfoGraph() {
		return ncbiTaxonomyGenInfoGraph();
	}

	private void initTypes() {

        //-----------------------------------------------------------------------------------------
        //--------------------------------VERTICES--------------------------------------------
//	    nCBITaxonType = new NCBITaxonType(nCBITaxonTypekey);
//        nCBITaxonTypekey = raw().titanKeyForVertexType(NCBITaxon().id);
//		nCBITaxonIdkey = nCBITaxonTypekey;
//		nCBITaxonTaxonomicRankkey = raw().titanKeyForVertexPropertySingle(NCBITaxon().taxonomicRank);
//		nCBITaxonScientificNamekey = raw().titanKeyForVertexPropertySingle(NCBITaxon().scientificName);


		//-----------------------------------------------------------------------------------------
		//--------------------------------RELATIONSHIPS--------------------------------------------
		// nCBITaxonParentLabel = raw().titanLabelForEdgeType(new NCBITaxonParentType((EdgeLabel) null));
		// ncbiTaxonParentType = new NCBITaxonParentType(nCBITaxonParentLabel);


    }

    private void initIndices() {
        // nCBITaxonIdIndex =  new TitanTypedVertexIndex.DefaultUnique<>(this, NCBITaxon().id);
    }


    @Override
    public NCBITaxonType NCBITaxon() {
        return nCBITaxonType;
    }

    @Override
    public NCBITaxonParentType NCBITaxonParent() {
        return ncbiTaxonParentType;
    }

	/*
		You can use this as `ncbiTaxonomyGraph.withUniprot(new TitanUniprotNCBITaxonomyGraph(raw, uniprotGraph, ncbiTaxonomyGraph))`
	*/
	public TitanNCBITaxonomyGraph withUniprot(TitanUniprotNCBITaxonomyGraph uniprotNCBITaxonomyGraph) {

		this.uniprotNCBITaxonomyGraph = uniprotNCBITaxonomyGraph;

		return this;
	}
}
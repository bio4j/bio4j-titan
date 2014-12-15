package com.bio4j.titan.model.ncbiTaxonomy;

import com.bio4j.angulillos.TypedVertexIndex;
import com.bio4j.angulillos.titan.TitanTypedVertexIndex;
import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph;
import com.bio4j.model.ncbiTaxonomy.vertices.NCBITaxon;
import com.bio4j.model.ncbiTaxonomy_geninfo.NCBITaxonomyGenInfoGraph;
import com.bio4j.model.uniprot_ncbiTaxonomy.UniprotNCBITaxonomyGraph;

import com.bio4j.titan.model.uniprot_ncbiTaxonomy.TitanUniProtNCBITaxonomyGraph;
import com.bio4j.titan.model.ncbiTaxonomy_geninfo.TitanNCBITaxonomyGenInfoGraph;

import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.EdgeLabelMaker;
import com.thinkaurelius.titan.core.schema.TitanManagement;
import com.thinkaurelius.titan.core.schema.VertexLabelMaker;


/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class TitanNCBITaxonomyGraph
		extends
		NCBITaxonomyGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> {

	private TitanUniProtNCBITaxonomyGraph uniprotNCBITaxonomyGraph = null;
	private NCBITaxonomyGenInfoGraph ncbiTaxonomyGenInfoGraph = null;

	private TitanManagement mgmt = null;

	//-------------------VERTICES----------------------------

	public VertexLabel nCBITaxonTypeLabel;
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
		this.raw = rawGraph;

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

	@Override
	public TypedVertexIndex.Unique<NCBITaxon<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, NCBITaxonType, NCBITaxonType.id, String, NCBITaxonomyGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> nCBITaxonIdIndex() {
		return nCBITaxonIdIndex;
	}

	@Override
	public TitanUniProtNCBITaxonomyGraph uniprotNCBITaxonomyGraph() {
		return uniprotNCBITaxonomyGraph;
	}

	@Override
	public TitanNCBITaxonomyGenInfoGraph ncbiTaxonomyGenInfoGraph() {
		return ncbiTaxonomyGenInfoGraph();
	}

	private void initTypes(TitanManagement mgmt) {

		//-----------------------------------------------------------------------------------------
		//--------------------------------VERTICES--------------------------------------------
		VertexLabelMaker nCBITaxonTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new NCBITaxonType(null));
		nCBITaxonType = new NCBITaxonType(nCBITaxonTypeLabelMaker);
		nCBITaxonIdkey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, NCBITaxon().id).cardinality(Cardinality.SINGLE));
		nCBITaxonTaxonomicRankkey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, NCBITaxon().taxonomicRank).cardinality(Cardinality.SINGLE));
		nCBITaxonScientificNamekey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, NCBITaxon().scientificName).cardinality(Cardinality.SINGLE));
		nCBITaxonTypeLabel = raw().createOrGet(mgmt, nCBITaxonType.raw());

		//-----------------------------------------------------------------------------------------
		//--------------------------------RELATIONSHIPS--------------------------------------------
		// nCBITaxonParent
		EdgeLabelMaker nCBITaxonParentTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new NCBITaxonParentType(null));
		ncbiTaxonParentType = new NCBITaxonParentType(nCBITaxonParentTypeLabelMaker);
		nCBITaxonParentLabel = raw().createOrGet(mgmt, ncbiTaxonParentType.raw());
	}

	private void initIndices(TitanManagement mgmt) {
		nCBITaxonIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(mgmt, this, NCBITaxon().id);
		nCBITaxonIdIndex.makeOrGet(nCBITaxonTypeLabel);
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
	public TitanNCBITaxonomyGraph withUniprotNCBITaxonomyGraph(TitanUniProtNCBITaxonomyGraph uniprotNCBITaxonomyGraph) {

		this.uniprotNCBITaxonomyGraph = uniprotNCBITaxonomyGraph;

		return this;
	}

	public TitanNCBITaxonomyGraph withNCBITaxonomyGenInfoGraph(TitanNCBITaxonomyGenInfoGraph ncbiTaxonomyGenInfoGraph) {

		this.ncbiTaxonomyGenInfoGraph = ncbiTaxonomyGenInfoGraph;

		return this;
	}
}
package com.bio4j.titan.model.geninfo;

import com.bio4j.angulillos.TypedVertexIndex;
import com.bio4j.angulillos.titan.TitanTypedVertexIndex;
import com.bio4j.model.geninfo.GenInfoGraph;
import com.bio4j.model.geninfo.vertices.GenInfo;
import com.bio4j.model.ncbiTaxonomy_geninfo.NCBITaxonomyGenInfoGraph;
import com.bio4j.titan.model.ncbiTaxonomy_geninfo.TitanNCBITaxonomyGenInfoGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.*;


/**
 @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class TitanGenInfoGraph
		extends
		GenInfoGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> {

	private TitanNCBITaxonomyGenInfoGraph ncbiTaxonomyGenInfoGraph = null;

	private TitanManagement mgmt;


	//-------------------VERTICES----------------------------

	public PropertyKey genInfoTypeKey;
	public PropertyKey genInfoIdkey;
	public GenInfoType genInfoType;

	//---------------INDICES---------------------------

	TitanTypedVertexIndex.DefaultUnique<
			GenInfo<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
			GenInfoType,
			GenInfoType.id, String,
			GenInfoGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
			DefaultTitanGraph
			> genInfoIdIndex;

	@Override
	public TypedVertexIndex.Unique<GenInfo<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, GenInfoType, GenInfoType.id, String, GenInfoGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> genInfoIdIndex() {
		return genInfoIdIndex;
	}

	public TitanGenInfoGraph(DefaultTitanGraph rawGraph) {
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
		return rawGraph;
	}

	@Override
	public NCBITaxonomyGenInfoGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> ncbiTaxonomyGenInfoGraph() {
		return null;
	}

	@Override
	public GenInfoType GenInfo() {
		return genInfoType;
	}

	private void initTypes() {

		//-----------------------------------------------------------------------------------------
		//--------------------------------VERTICES--------------------------------------------
		VertexLabelMaker enzymeTypeLabelMaker = raw().titanLabelMakerForVertexType( mgmt, new GenInfoType(null));
		genInfoType = new GenInfoType(genInfoTypeKey);
		genInfoTypeKey = raw().titanKeyForVertexType(GenInfo().id);
		genInfoIdkey = genInfoTypeKey;

	}

	private void initIndices() {
		// genInfoIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, GenInfo().id);
	}

	/*
		You can use this as `enzymeDBGraph.withUniprot(new TitanUniprotEnzymeDBGraph(raw, uniprotGraph, enzymeGraph))`
	*/
	public TitanGenInfoGraph withNCBITaxonomy(TitanNCBITaxonomyGenInfoGraph ncbiTaxonomyGenInfoGraph) {

		this.ncbiTaxonomyGenInfoGraph = ncbiTaxonomyGenInfoGraph;

		return this;
	}
}


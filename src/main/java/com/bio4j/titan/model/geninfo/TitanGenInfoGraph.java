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
		GenInfoGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> {

	private DefaultTitanGraph rawGraph;
	private TitanNCBITaxonomyGenInfoGraph ncbiTaxonomyGenInfoGraph = null;


	//-------------------VERTICES----------------------------

	public PropertyKey genInfoTypeKey;
	public PropertyKey genInfoIdkey;
	public GenInfoType genInfoType;

	//---------------INDICES---------------------------

	TitanTypedVertexIndex.DefaultUnique<
			GenInfo<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>,
			GenInfoType,
			GenInfoType.id, String,
			GenInfoGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>,
			DefaultTitanGraph
			> genInfoIdIndex;

	@Override
	public TypedVertexIndex.Unique<GenInfo<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, GenInfoType, GenInfoType.id, String, GenInfoGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> genInfoIdIndex() {
		return genInfoIdIndex;
	}

	public TitanGenInfoGraph(DefaultTitanGraph rawGraph) {
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
	public NCBITaxonomyGenInfoGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> ncbiTaxonomyGenInfoGraph() {
		return null;
	}

	@Override
	public GenInfoType GenInfo() {
		return genInfoType;
	}

	private void initTypes() {

		//-----------------------------------------------------------------------------------------
		//--------------------------------VERTICES--------------------------------------------
//		genInfoType = new GenInfoType(genInfoTypeKey);
//		genInfoTypeKey = raw().titanKeyForVertexType(GenInfo().id);
//		genInfoIdkey = genInfoTypeKey;

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


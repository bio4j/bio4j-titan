package com.bio4j.titan.model.ncbiTaxonomy_geninfo;

import com.bio4j.model.geninfo.GenInfoGraph;
import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph;
import com.bio4j.model.ncbiTaxonomy_geninfo.NCBITaxonomyGenInfoGraph;
import com.bio4j.titan.model.geninfo.TitanGenInfoGraph;
import com.bio4j.titan.model.ncbiTaxonomy.TitanNCBITaxonomyGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.*;


/**
 Implementing the types with Titan
 @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class TitanNCBITaxonomyGenInfoGraph
		extends
		NCBITaxonomyGenInfoGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> {

	private TitanNCBITaxonomyGraph ncbiTaxonomyGraph;
	private TitanGenInfoGraph genInfoGraph;

	private TitanManagement mgmt;

	//---------------RELATIONSHIPS---------------------------

	private EdgeLabel genInfoNCBITaxonLabel;
	private GenInfoNCBITaxonType genInfoNCBITaxonType;


	public TitanNCBITaxonomyGenInfoGraph(
		DefaultTitanGraph rawGraph, 
		TitanNCBITaxonomyGraph titanNCBITaxonomyGraph, 
		TitanGenInfoGraph titanGenInfoGraph
	) {

		/* assign the raw graph */
		super(rawGraph);
		this.raw = rawGraph;

		// First get a titanMgmt instance, that will be used throughout
		this.mgmt = rawGraph.managementSystem();
		initTypes(mgmt);
		initIndices(mgmt);

		// this should work now
		mgmt.commit();

		/* update dependencies */
		this.ncbiTaxonomyGraph 	= titanNCBITaxonomyGraph.withNCBITaxonomyGenInfoGraph(this);
		this.genInfoGraph 			= 		 titanGenInfoGraph.withNCBITaxonomyGenInfoGraph(this);
	}

	@Override
	public DefaultTitanGraph raw() {
		return raw;
	}

	private void initTypes(TitanManagement mgmt) {

		//-----------------------------------------------------------------------------------------
		//--------------------------------RELATIONSHIPS--------------------------------------------

		// genInfoNCBITaxon
		EdgeLabelMaker genInfoNCBITaxonTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new GenInfoNCBITaxonType(null));
		genInfoNCBITaxonType = new GenInfoNCBITaxonType(genInfoNCBITaxonTypeLabelMaker);
		genInfoNCBITaxonLabel = raw().createOrGet(mgmt, genInfoNCBITaxonType.raw());

	}

	private void initIndices(TitanManagement mgmt) {

	}


	@Override
	public TitanNCBITaxonomyGraph ncbiTaxonomyGraph() {

		return ncbiTaxonomyGraph;
	}

	@Override
	public TitanGenInfoGraph genInfoGraph() {
		
		return genInfoGraph;
	}

	@Override
	public GenInfoNCBITaxonType GenInfoNCBITaxon() {

		return genInfoNCBITaxonType;
	}
}
package com.bio4j.titan.model.uniprot_enzyme;

import com.bio4j.model.enzymedb.EnzymeDBGraph;
import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot_enzymedb.UniprotEnzymeDBGraph;
import com.bio4j.titan.model.enzyme.TitanEnzymeDBGraph;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.*;


/**
 Implementing the types with Titan
 @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class TitanUniprotEnzymeGraph
        extends
        UniprotEnzymeDBGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> {

    private UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> uniprotRawGraph;
    private EnzymeDBGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> enzymeDBRawGraph;

	private TitanManagement mgmt;


	//-----------------------------------------------------------------------------------------
	//--------------------------------RELATIONSHIPS--------------------------------------------

	// enzymaticActivity
	public EdgeLabel enzymaticActivityLabel;
	public EnzymaticActivityType enzymaticActivityType;


    public TitanUniprotEnzymeGraph(DefaultTitanGraph rawGraph, TitanUniprotGraph titanUniprotGraph, TitanEnzymeDBGraph titanEnzymeDBGraph) {
        super(rawGraph);
        this.raw = rawGraph;
        this.uniprotRawGraph = titanUniprotGraph;
        this.enzymeDBRawGraph = titanEnzymeDBGraph;

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

	    // enzymaticActivity
	    EdgeLabelMaker enzymaticActivityTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new EnzymaticActivityType(null));
	    enzymaticActivityType = new EnzymaticActivityType(enzymaticActivityTypeLabelMaker);
	    enzymaticActivityLabel = raw().createOrGet(mgmt, enzymaticActivityType.raw());

    }

    private void initIndices(TitanManagement mgmt) {

    }


    @Override
    public UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> uniprotGraph() {
        return uniprotRawGraph;
    }

    @Override
    public EnzymeDBGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> enzymeDBGraph() {
        return enzymeDBRawGraph;
    }

    @Override
    public EnzymaticActivityType EnzymaticActivity() {
        return enzymaticActivityType;
    }
}
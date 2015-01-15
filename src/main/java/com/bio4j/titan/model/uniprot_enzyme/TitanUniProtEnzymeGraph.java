package com.bio4j.titan.model.uniprot_enzyme;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot_enzymedb.UniProtEnzymeDBGraph;
import com.bio4j.titan.model.enzyme.TitanEnzymeDBGraph;
import com.bio4j.titan.model.uniprot.TitanUniProtGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.*;


/**
 Implementing the types with Titan
 @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class TitanUniProtEnzymeGraph
        extends
        UniProtEnzymeDBGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> {

    private TitanUniProtGraph uniProtGraph;
    private TitanEnzymeDBGraph enzymeDBGraph;

	private TitanManagement mgmt;


	//-----------------------------------------------------------------------------------------
	//--------------------------------RELATIONSHIPS--------------------------------------------

	// enzymaticActivity
	public EdgeLabel enzymaticActivityLabel;
	public EnzymaticActivityType enzymaticActivityType;


    public TitanUniProtEnzymeGraph(
            DefaultTitanGraph rawGraph,
            TitanUniProtGraph titanUniProtGraph,
            TitanEnzymeDBGraph titanEnzymeDBGraph
    ){
        super(rawGraph);
        this.raw = rawGraph;

	    // First get a titanMgmt instance, that will be used throughout
	    this.mgmt = rawGraph.managementSystem();
        initTypes(mgmt);
        initIndices(mgmt);

	    // this should work now
	    mgmt.commit();

        /* update dependencies */
        this.uniProtGraph    =  titanUniProtGraph.withUniProtEnzymeGraph(this);
        this.enzymeDBGraph   = titanEnzymeDBGraph.withUniProtEnzymeGraph(this);
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
    public TitanUniProtGraph uniProtGraph() {

        return uniProtGraph;
    }

    @Override
    public TitanEnzymeDBGraph enzymeDBGraph() {
        
        return enzymeDBGraph;
    }

    @Override
    public EnzymaticActivityType EnzymaticActivity() {
        return enzymaticActivityType;
    }
}
package com.bio4j.titan.model.uniprot_enzyme;

import com.bio4j.model.uniprot_enzymedb.UniprotEnzymeDBGraph;
import com.bio4j.titan.model.enzyme.TitanEnzymeDBGraph;
import com.bio4j.titan.model.enzyme.TitanEnzymeDBGraphImpl;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.TitanUniprotGraphImpl;
import com.bio4j.titan.model.uniprot_enzyme.relationships.TitanEnzymaticActivity;
import com.ohnosequences.typedGraphs.titan.TitanTypedGraph;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 6/25/2014.
 */
public abstract class TitanUniprotEnzymeDBGraph implements
		TitanTypedGraph, UniprotEnzymeDBGraph {

	protected TitanGraph rawGraph;
	public TitanUniprotGraph uniprotGraph;
	public TitanEnzymeDBGraph enzymeDBGraph;

	TitanUniprotEnzymeDBGraph(TitanGraph rawGraph) {

		this.rawGraph = rawGraph;
		this.uniprotGraph = new TitanUniprotGraphImpl(rawGraph);
		this.enzymeDBGraph = new TitanEnzymeDBGraphImpl(rawGraph);

	}

	@Override
	public TitanGraph rawGraph() {
		return rawGraph;
	}

	//-----------------------------------------------------------------------------------------
	//--------------------------------RELATIONSHIPS--------------------------------------------

	public TitanLabel enzymaticActivityLabel;
	public TitanEnzymaticActivity.TitanEnzymaticActivityType enzymaticActivityT = new TitanEnzymaticActivity.TitanEnzymaticActivityType(this);

}

package com.bio4j.titan.model.uniprot;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein.TitanProteinType;
import com.ohnosequences.typedGraphs.titan.TitanTypedGraph;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanKey;

/**
* Created by ppareja on 6/20/2014.
*/
public class TitanUniprotGraph implements
		TitanTypedGraph,
		UniprotGraph {

	protected TitanGraph rawGraph;

	TitanUniprotGraph(TitanGraph rawGraph) {

		this.rawGraph = rawGraph;
	}

	@Override
	public TitanGraph rawGraph() {
		return rawGraph;
	}

	public TitanKey proteinTkey;
	public TitanKey proteinAccessionKey;
	public TitanKey proteinNameKey;
	public TitanKey proteinFullNameKey;
	public TitanKey proteinShortNameKey;
	public TitanKey proteinSequenceKey;
	public TitanKey proteinMassKey;
	public TitanKey proteinModifiedDateKey;
	public TitanKey proteinLengthKey;

	public final TitanProteinType proteinT = new TitanProteinType(this);

	//------------------INDICES----------------
	//-----------------------------------------


	//-----------------------------------------------------------------------------------------
	//--------------------------------RELATIONSHIPS--------------------------------------------
}

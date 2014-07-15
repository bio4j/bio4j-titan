package com.bio4j.titan.model.enzyme;

import com.bio4j.model.enzymedb.EnzymeDBGraph;
import com.bio4j.titan.model.enzyme.nodes.TitanEnzyme;
import com.bio4j.titan.model.uniprot_enzyme.TitanUniprotEnzymeDBGraph;
import com.bio4j.titan.model.uniprot_enzyme.TitanUniprotEnzymeDBGraphImpl;
import com.ohnosequences.typedGraphs.titan.TitanNodeIndex;
import com.ohnosequences.typedGraphs.titan.TitanTypedGraph;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanKey;
import com.bio4j.titan.model.enzyme.nodes.TitanEnzyme.TitanEnzymeType;
import com.thinkaurelius.titan.core.TitanLabel;

/**
* Created by ppareja on 6/20/2014.
*/
public abstract class TitanEnzymeDBGraph 
implements
	TitanTypedGraph,
	EnzymeDBGraph 
{

	protected TitanGraph rawGraph;
	public TitanUniprotEnzymeDBGraph uniprotEnzymeDBGraph;

	TitanEnzymeDBGraph(TitanGraph rawGraph) {

		this.rawGraph = rawGraph;

	}

	@Override
	public TitanGraph rawGraph() {
		return rawGraph;
	}

	public TitanKey enzymeTkey;
	public TitanKey enzymeIdKey;
	public TitanKey enzymeCofactorsKey;
	public TitanKey enzymeOfficialNameKey;
	public TitanKey enzymeCatalyticActivityKey;
	public TitanKey enzymeCommentKey;
	public TitanKey enzymePrositeCrossReferencesKey;
	public final TitanEnzymeType enzymeT = new TitanEnzymeType(this);

	//------------------INDICES----------------
	//-----------------------------------------
	public TitanNodeIndex.Unique<TitanEnzyme,TitanEnzymeType, TitanEnzymeType.id,String> enzymeIdIndex;


}

package com.bio4j.titan.model.ncbiTaxonomy;

import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph;
import com.bio4j.titan.model.ncbiTaxonomy.nodes.TitanNCBITaxon;
import com.bio4j.titan.model.ncbiTaxonomy.nodes.TitanNCBITaxon.TitanNCBITaxonType;
import com.bio4j.titan.model.ncbiTaxonomy.relationships.TitanNCBITaxonParent.TitanNCBITaxonParentType;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.ohnosequences.typedGraphs.titan.TitanNodeIndex;
import com.ohnosequences.typedGraphs.titan.TitanTypedGraph;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 7/14/2014.
 */
public abstract class TitanNCBITaxonomyGraph
		implements
		TitanTypedGraph,
		NCBITaxonomyGraph
{

	protected TitanGraph rawGraph;

	TitanNCBITaxonomyGraph(TitanGraph rawGraph) {

		this.rawGraph = rawGraph;
	}


	@Override
	public TitanGraph rawGraph() {
		return rawGraph;
	}

	/*
	  The type of a NCBITaxon. This an inner class of the graph. The first key here represents the type of the node, while the rest are for properties of this term: `id` and `name` in this case.
	*/
	public TitanKey nCBITaxonTkey;
	public TitanKey nCBITaxonIdKey;
	public TitanKey nCBITaxonNameKey;
	public TitanKey nCBITaxonCommentKey;
	public TitanKey nCBITaxonScientificNameKey;
	public TitanKey nCBITaxonTaxonomicRankKey;
	public final TitanNCBITaxonType nCBITaxonT = new TitanNCBITaxonType(this);


	//------------------INDICES----------------
	//-----------------------------------------
	public TitanNodeIndex.Unique<TitanNCBITaxon,TitanNCBITaxonType, TitanNCBITaxonType.id,String> nCBITaxonIdIndex;

	//-----------------------------------------------------------------------------------------
	//--------------------------------RELATIONSHIPS--------------------------------------------

	public TitanLabel nCBITaxonParentLabel;
	public TitanNCBITaxonParentType nCBITaxonParentT = new TitanNCBITaxonParentType(this);


}
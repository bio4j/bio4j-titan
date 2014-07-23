package com.bio4j.titan.model.ncbiTaxonomy;

import com.ohnosequences.typedGraphs.titan.TitanNodeIndex;
import com.thinkaurelius.titan.core.TitanGraph;

/**
 * Created by ppareja on 7/14/2014.
 */
public class TitanNCBITaxonomyGraphImpl extends  TitanNCBITaxonomyGraph{

	public TitanNCBITaxonomyGraphImpl(TitanGraph graph) {
		super(graph);
		initTypes();
		initIndices();
	}


	private void initTypes() {

		// NCBITaxon keys
		nCBITaxonTkey = titanKeyForNodeType(nCBITaxonT.id);
		nCBITaxonIdKey = nCBITaxonTkey;
		nCBITaxonNameKey = titanKeyForNodeProperty(nCBITaxonT.name);
		nCBITaxonScientificNameKey = titanKeyForNodeProperty(nCBITaxonT.scientificName);
		nCBITaxonTaxonomicRankKey = titanKeyForNodeProperty(nCBITaxonT.taxonomicRank);
		nCBITaxonCommentKey = titanKeyForNodeProperty(nCBITaxonT.comment);

	}

	private void initIndices() {
		nCBITaxonIdIndex = new TitanNodeIndex.DefaultUnique(this, nCBITaxonT.id);
	}
}

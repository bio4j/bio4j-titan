package com.bio4j.titan.model.uniref;

import com.ohnosequences.typedGraphs.titan.TitanNodeIndex;
import com.thinkaurelius.titan.core.TitanGraph;

/**
 * Created by ppareja on 7/22/2014.
 */
public class TitanUniRefGraphImpl extends TitanUniRefGraph {

	public TitanUniRefGraphImpl(TitanGraph graph) {
		super(graph);
		initTypes();
		initIndices();
	}


	private void initTypes() {

		// Uniref50Cluster keys
		uniRef50ClusterTkey = titanKeyForNodeType(uniRef50ClusterT.id);
		uniRef50ClusterIdKey = uniRef50ClusterTkey;
		// Uniref90Cluster keys
		uniRef90ClusterTkey = titanKeyForNodeType(uniRef90ClusterT.id);
		uniRef90ClusterIdKey = uniRef90ClusterTkey;
		// Uniref100Cluster keys
		uniRef100ClusterTkey = titanKeyForNodeType(uniRef100ClusterT.id);
		uniRef100ClusterIdKey = uniRef100ClusterTkey;

	}

	private void initIndices() {
		uniRef50ClusterIdIndex = new TitanNodeIndex.DefaultUnique(this, uniRef50ClusterT.id);
		uniRef90ClusterIdIndex = new TitanNodeIndex.DefaultUnique(this, uniRef90ClusterT.id);
		uniRef100ClusterIdIndex = new TitanNodeIndex.DefaultUnique(this, uniRef100ClusterT.id);
	}
}

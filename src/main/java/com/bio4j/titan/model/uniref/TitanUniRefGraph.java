package com.bio4j.titan.model.uniref;

import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.titan.model.uniprot_uniref.TitanUniprotUniRefGraph;
import com.bio4j.titan.model.uniref.nodes.TitanUniRef50Cluster;
import com.ohnosequences.typedGraphs.titan.TitanNodeIndex;
import com.ohnosequences.typedGraphs.titan.TitanTypedGraph;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanKey;

/**
 * Created by ppareja on 7/22/2014.
 */
public class TitanUniRefGraph implements
		TitanTypedGraph,
		UniRefGraph
{

	protected TitanGraph rawGraph;
	public TitanUniprotUniRefGraph uniprotUniRefGraph = null;

	TitanUniRefGraph(TitanGraph rawGraph) {

		this.rawGraph = rawGraph;
	}


	@Override
	public TitanGraph rawGraph() {
		return rawGraph;
	}

	public TitanKey uniRef50ClusterTkey;
	public TitanKey uniRef50ClusterIdKey;
	public final TitanUniRef50Cluster.TitanUniRef50ClusterType uniRef50ClusterT = new TitanUniRef50Cluster.TitanUniRef50ClusterType(this);
	public TitanKey uniRef90ClusterTkey;
	public TitanKey uniRef90ClusterIdKey;
	public final TitanUniRef90ClusterType uniRef90ClusterT = new TitanUniRef90ClusterType(this);
	public TitanKey uniRef100ClusterTkey;
	public TitanKey uniRef100ClusterIdKey;
	public final TitanUniRef100ClusterType uniRef100ClusterT = new TitanUniRef100ClusterType(this);


	//------------------INDICES----------------
	//-----------------------------------------
	public TitanNodeIndex.Unique<TitanUniRef50Cluster,TitanUniRef50ClusterType, TitanUniRef50ClusterType.id,String> uniRef50ClusterIdIndex;
	public TitanNodeIndex.Unique<TitanUniRef90Cluster,TitanUniRef90ClusterType, TitanUniRef90ClusterType.id,String> uniRef90ClusterIdIndex;
	public TitanNodeIndex.Unique<TitanUniRef100Cluster,TitanUniRef100ClusterType, TitanUniRef100ClusterType.id,String> uniRef100ClusterIdIndex;


}

package com.bio4j.titan.model.uniref;

import com.bio4j.angulillos.TypedVertexIndex;
import com.bio4j.angulillos.titan.TitanTypedVertexIndex;
import com.bio4j.model.uniprot_uniref.UniprotUniRefGraph;
import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.model.uniref.vertices.UniRef100Cluster;
import com.bio4j.model.uniref.vertices.UniRef50Cluster;
import com.bio4j.model.uniref.vertices.UniRef90Cluster;
import com.bio4j.titan.model.uniprot_uniref.TitanUniprotUniRefGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.*;


/**
 Implementing the types with Titan
 @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class TitanUniRefGraph
        extends
        UniRefGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> {

    private DefaultTitanGraph rawGraph;
	private TitanUniprotUniRefGraph uniprotUniRefGraph;


    //-------------------VERTICES----------------------------

    public PropertyKey uniRef100ClusterTypekey;
    public PropertyKey uniRef100ClusterIdkey;
	public PropertyKey uniRef100ClusterNamekey;
	public PropertyKey uniRef100ClusterUpdatedDatekey;
    public UniRef100ClusterType uniRef100ClusterType;

    public PropertyKey uniRef90ClusterTypekey;
    public PropertyKey uniRef90ClusterIdkey;
	public PropertyKey uniRef90ClusterNamekey;
	public PropertyKey uniRef90ClusterUpdatedDatekey;
    public UniRef90ClusterType uniRef90ClusterType;

    public PropertyKey uniRef50ClusterTypekey;
    public PropertyKey uniRef50ClusterIdkey;
	public PropertyKey uniRef50ClusterNamekey;
	public PropertyKey uniRef50ClusterUpdatedDatekey;
    public UniRef50ClusterType uniRef50ClusterType;



    //---------------INDICES---------------------------

    TitanTypedVertexIndex.DefaultUnique<
            UniRef100Cluster<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
            UniRef100ClusterType,
            UniRef100ClusterType.id, String,
            UniRefGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
            DefaultTitanGraph
            > uniRef100ClusterIdIndex;
    TitanTypedVertexIndex.DefaultUnique<
            UniRef90Cluster<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
            UniRef90ClusterType,
            UniRef90ClusterType.id, String,
            UniRefGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
            DefaultTitanGraph
            > uniRef90ClusterIdIndex;
    TitanTypedVertexIndex.DefaultUnique<
            UniRef50Cluster<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
            UniRef50ClusterType,
            UniRef50ClusterType.id, String,
            UniRefGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
            DefaultTitanGraph
            > uniRef50ClusterIdIndex;


    public TitanUniRefGraph(DefaultTitanGraph rawGraph) {
        super(rawGraph);
        this.rawGraph = rawGraph;
        initTypes();
        initIndices();
    }

    @Override
    public DefaultTitanGraph raw() {
        return rawGraph;
    }

    private void initTypes() {

        //-----------------------------------------------------------------------------------------
        //--------------------------------VERTICES--------------------------------------------
//	    uniRef100ClusterType = new UniRef100ClusterType(uniRef100ClusterTypekey);
//        uniRef100ClusterTypekey = raw().titanKeyForVertexType(UniRef100Cluster().id);
//        uniRef100ClusterIdkey = uniRef100ClusterTypekey;
//	    uniRef100ClusterUpdatedDatekey = raw().titanKeyForVertexPropertySingle(UniRef100Cluster().updatedDate);
//	    uniRef100ClusterNamekey = raw().titanKeyForVertexPropertySingle(UniRef100Cluster().name);
//
//	    uniRef90ClusterType = new UniRef90ClusterType(uniRef90ClusterTypekey);
//        uniRef90ClusterTypekey = raw().titanKeyForVertexType(UniRef90Cluster().id);
//        uniRef90ClusterIdkey = uniRef90ClusterTypekey;
//	    uniRef90ClusterUpdatedDatekey = raw().titanKeyForVertexPropertySingle(UniRef90Cluster().updatedDate);
//	    uniRef90ClusterNamekey = raw().titanKeyForVertexPropertySingle(UniRef90Cluster().name);
//
//	    uniRef50ClusterType = new UniRef50ClusterType(uniRef50ClusterTypekey);
//        uniRef50ClusterTypekey = raw().titanKeyForVertexType(UniRef50Cluster().id);
//        uniRef50ClusterIdkey = uniRef50ClusterTypekey;
//	    uniRef50ClusterUpdatedDatekey = raw().titanKeyForVertexPropertySingle(UniRef50Cluster().updatedDate);
//	    uniRef50ClusterNamekey = raw().titanKeyForVertexPropertySingle(UniRef50Cluster().name);

    }

    private void initIndices() {
        // uniRef100ClusterIdIndex =  new TitanTypedVertexIndex.DefaultUnique<>(this, UniRef100Cluster().id);
        // uniRef90ClusterIdIndex =  new TitanTypedVertexIndex.DefaultUnique<>(this, UniRef90Cluster().id);
        // uniRef50ClusterIdIndex =  new TitanTypedVertexIndex.DefaultUnique<>(this, UniRef50Cluster().id);
    }


    @Override
    public UniprotUniRefGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> uniprotUniRefGraph() {
        return uniprotUniRefGraph;
    }

	@Override
	public TypedVertexIndex.Unique<UniRef50Cluster<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, UniRef50ClusterType, UniRef50ClusterType.id, String, UniRefGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> uniRef50ClusterIdIndex() {
		return uniRef50ClusterIdIndex;
	}

	@Override
	public TypedVertexIndex.Unique<UniRef90Cluster<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, UniRef90ClusterType, UniRef90ClusterType.id, String, UniRefGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> uniRef90ClusterIdIndex() {
		return uniRef90ClusterIdIndex;
	}

	@Override
	public TypedVertexIndex.Unique<UniRef100Cluster<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, UniRef100ClusterType, UniRef100ClusterType.id, String, UniRefGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> uniRef100ClusterIdIndex() {
		return uniRef100ClusterIdIndex;
	}

	@Override
    public UniRef50ClusterType UniRef50Cluster() {
        return uniRef50ClusterType;
    }

    @Override
    public UniRef90ClusterType UniRef90Cluster() {
        return uniRef90ClusterType;
    }

    @Override
    public UniRef100ClusterType UniRef100Cluster() {
        return uniRef100ClusterType;
    }

	/*
		You can use this as `uniRefGraph.withUniprot(new TitanUniprotUniRefGraph(raw, uniprotGraph, uniRefGraph))`
	*/
	public TitanUniRefGraph withUniprot(TitanUniprotUniRefGraph uniprotUniRefGraph) {

		this.uniprotUniRefGraph = uniprotUniRefGraph;

		return this;
	}
}
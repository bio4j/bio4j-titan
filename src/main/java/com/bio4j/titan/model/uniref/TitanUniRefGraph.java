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

	private TitanUniprotUniRefGraph uniprotUniRefGraph;

	private TitanManagement mgmt = null;


    //-------------------VERTICES----------------------------

	public VertexLabel uniRef100ClusterTypeLabel;
    public PropertyKey uniRef100ClusterTypekey;
    public PropertyKey uniRef100ClusterIdkey;
	public PropertyKey uniRef100ClusterNamekey;
	public PropertyKey uniRef100ClusterUpdatedDatekey;
    public UniRef100ClusterType uniRef100ClusterType;

	public VertexLabel uniRef90ClusterTypeLabel;
    public PropertyKey uniRef90ClusterTypekey;
    public PropertyKey uniRef90ClusterIdkey;
	public PropertyKey uniRef90ClusterNamekey;
	public PropertyKey uniRef90ClusterUpdatedDatekey;
    public UniRef90ClusterType uniRef90ClusterType;

	public VertexLabel uniRef50ClusterTypeLabel;
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
        this.raw = rawGraph;

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
        //--------------------------------VERTICES--------------------------------------------
	    VertexLabelMaker uniRef100ClusteTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new UniRef100ClusterType(null));
	    uniRef100ClusterType = new UniRef100ClusterType(uniRef100ClusteTypeLabelMaker);
        uniRef100ClusterIdkey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniRef100Cluster().id).cardinality(Cardinality.SINGLE));
	    uniRef100ClusterUpdatedDatekey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniRef100Cluster().updatedDate).cardinality(Cardinality.SINGLE));
	    uniRef100ClusterNamekey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniRef100Cluster().name).cardinality(Cardinality.SINGLE));
	    uniRef100ClusterTypeLabel = raw().createOrGet(mgmt, uniRef100ClusterType.raw());

	    VertexLabelMaker uniRef90ClusteTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new UniRef90ClusterType(null));
	    uniRef90ClusterType = new UniRef90ClusterType(uniRef90ClusteTypeLabelMaker);
        uniRef90ClusterIdkey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniRef90Cluster().id).cardinality(Cardinality.SINGLE));
	    uniRef90ClusterUpdatedDatekey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniRef90Cluster().updatedDate).cardinality(Cardinality.SINGLE));
	    uniRef90ClusterNamekey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniRef90Cluster().name).cardinality(Cardinality.SINGLE));
	    uniRef90ClusterTypeLabel = raw().createOrGet(mgmt, uniRef90ClusterType.raw());

	    VertexLabelMaker uniRef50ClusteTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new UniRef50ClusterType(null));
	    uniRef50ClusterType = new UniRef50ClusterType(uniRef50ClusteTypeLabelMaker);
        uniRef50ClusterIdkey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniRef50Cluster().id).cardinality(Cardinality.SINGLE));
	    uniRef50ClusterUpdatedDatekey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniRef50Cluster().updatedDate).cardinality(Cardinality.SINGLE));
	    uniRef50ClusterNamekey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniRef50Cluster().name).cardinality(Cardinality.SINGLE));
	    uniRef50ClusterTypeLabel = raw().createOrGet(mgmt, uniRef50ClusterType.raw());

    }

    private void initIndices(TitanManagement mgmt) {
        uniRef100ClusterIdIndex =  new TitanTypedVertexIndex.DefaultUnique<>(mgmt,this, UniRef100Cluster().id);
	    uniRef100ClusterIdIndex.make(uniRef100ClusterTypeLabel);
        uniRef90ClusterIdIndex =  new TitanTypedVertexIndex.DefaultUnique<>(mgmt,this, UniRef90Cluster().id);
	    uniRef90ClusterIdIndex.make(uniRef90ClusterTypeLabel);
        uniRef50ClusterIdIndex =  new TitanTypedVertexIndex.DefaultUnique<>(mgmt,this, UniRef50Cluster().id);
	    uniRef50ClusterIdIndex.make(uniRef50ClusterTypeLabel);
    }


    @Override
    public TitanUniprotUniRefGraph uniprotUniRefGraph() {
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
	public TitanUniRefGraph withUniprotUniRefGraph(TitanUniprotUniRefGraph uniprotUniRefGraph) {

		this.uniprotUniRefGraph = uniprotUniRefGraph;

		return this;
	}
}
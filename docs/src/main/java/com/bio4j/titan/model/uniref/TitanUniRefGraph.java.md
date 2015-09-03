
```java
package com.bio4j.titan.model.uniref;

import com.bio4j.angulillos.TypedVertexIndex;
import com.bio4j.angulillos.titan.TitanTypedVertexIndex;
import com.bio4j.model.uniprot_uniref.UniProtUniRefGraph;
import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.model.uniref.vertices.UniRef100Cluster;
import com.bio4j.model.uniref.vertices.UniRef50Cluster;
import com.bio4j.model.uniref.vertices.UniRef90Cluster;
import com.bio4j.titan.model.uniprot_uniref.TitanUniProtUniRefGraph;
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

	private TitanUniProtUniRefGraph uniProtUniRefGraph;

	private TitanManagement mgmt = null;


    //-------------------VERTICES----------------------------

	public VertexLabel uniRef100ClusterTypeLabel;
    public PropertyKey uniRef100ClusterIdkey;
	public PropertyKey uniRef100ClusterNamekey;
	public PropertyKey uniRef100ClusterUpdatedDatekey;
	public PropertyKey uniRef100ClusterMemberskey;
	public PropertyKey uniRef100ClusterRepresentantAccessionkey;
    public UniRef100ClusterType uniRef100ClusterType;

	public VertexLabel uniRef90ClusterTypeLabel;
    public PropertyKey uniRef90ClusterIdkey;
	public PropertyKey uniRef90ClusterNamekey;
	public PropertyKey uniRef90ClusterMemberskey;
	public PropertyKey uniRef90ClusterRepresentantAccessionkey;
	public PropertyKey uniRef90ClusterUpdatedDatekey;
    public UniRef90ClusterType uniRef90ClusterType;

	public VertexLabel uniRef50ClusterTypeLabel;
    public PropertyKey uniRef50ClusterIdkey;
	public PropertyKey uniRef50ClusterNamekey;
	public PropertyKey uniRef50ClusterUpdatedDatekey;
	public PropertyKey uniRef50ClusterMemberskey;
	public PropertyKey uniRef50ClusterRepresentantAccessionkey;
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

	    // First get a titanMgmt instance, that will be used throughout
	    this.mgmt = rawGraph.managementSystem();
        initTypes(mgmt);
        initIndices(mgmt);

	    // this should work now
	    mgmt.commit();
    }

    private void initTypes(TitanManagement mgmt) {

        //-----------------------------------------------------------------------------------------
        //--------------------------------VERTICES--------------------------------------------
	    
        VertexLabelMaker uniRef100ClusteTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new UniRef100ClusterType(null));
        uniRef100ClusterType = new UniRef100ClusterType(uniRef100ClusteTypeLabelMaker);
        uniRef100ClusterIdkey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniRef100Cluster().id).cardinality(Cardinality.SINGLE));
	    uniRef100ClusterUpdatedDatekey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniRef100Cluster().updatedDate).cardinality(Cardinality.SINGLE));
	    uniRef100ClusterNamekey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniRef100Cluster().name).cardinality(Cardinality.SINGLE));
	    uniRef100ClusterRepresentantAccessionkey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniRef100Cluster().representantAccession).cardinality(Cardinality.SINGLE));
	    uniRef100ClusterMemberskey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniRef100Cluster().members).cardinality(Cardinality.SINGLE));
	    uniRef100ClusterTypeLabel = raw().createOrGet(mgmt, uniRef100ClusterType.raw());

	    VertexLabelMaker uniRef90ClusteTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new UniRef90ClusterType(null));
	    uniRef90ClusterType = new UniRef90ClusterType(uniRef90ClusteTypeLabelMaker);
        uniRef90ClusterIdkey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniRef90Cluster().id).cardinality(Cardinality.SINGLE));
	    uniRef90ClusterUpdatedDatekey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniRef90Cluster().updatedDate).cardinality(Cardinality.SINGLE));
	    uniRef90ClusterNamekey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniRef90Cluster().name).cardinality(Cardinality.SINGLE));
	    uniRef90ClusterRepresentantAccessionkey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniRef90Cluster().representantAccession).cardinality(Cardinality.SINGLE));
	    uniRef90ClusterMemberskey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniRef90Cluster().members).cardinality(Cardinality.SINGLE));
	    uniRef90ClusterTypeLabel = raw().createOrGet(mgmt, uniRef90ClusterType.raw());

	    VertexLabelMaker uniRef50ClusteTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new UniRef50ClusterType(null));
	    uniRef50ClusterType = new UniRef50ClusterType(uniRef50ClusteTypeLabelMaker);
        uniRef50ClusterIdkey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniRef50Cluster().id).cardinality(Cardinality.SINGLE));
	    uniRef50ClusterUpdatedDatekey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniRef50Cluster().updatedDate).cardinality(Cardinality.SINGLE));
	    uniRef50ClusterNamekey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniRef50Cluster().name).cardinality(Cardinality.SINGLE));
	    uniRef50ClusterRepresentantAccessionkey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniRef50Cluster().representantAccession).cardinality(Cardinality.SINGLE));
	    uniRef50ClusterMemberskey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniRef50Cluster().members).cardinality(Cardinality.SINGLE));
	    uniRef50ClusterTypeLabel = raw().createOrGet(mgmt, uniRef50ClusterType.raw());

    }

    private void initIndices(TitanManagement mgmt) {

        uniRef100ClusterIdIndex =  new TitanTypedVertexIndex.DefaultUnique<>(mgmt, this, UniRef100Cluster().id);
	    uniRef100ClusterIdIndex.makeOrGet(uniRef100ClusterTypeLabel);

        uniRef90ClusterIdIndex =  new TitanTypedVertexIndex.DefaultUnique<>(mgmt,this, UniRef90Cluster().id);
	    uniRef90ClusterIdIndex.makeOrGet(uniRef90ClusterTypeLabel);

        uniRef50ClusterIdIndex =  new TitanTypedVertexIndex.DefaultUnique<>(mgmt,this, UniRef50Cluster().id);
	    uniRef50ClusterIdIndex.makeOrGet(uniRef50ClusterTypeLabel);
    }


    @Override
    public TitanUniProtUniRefGraph uniProtUniRefGraph() {
        return uniProtUniRefGraph;
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
    public final UniRef50ClusterType UniRef50Cluster() {
        return uniRef50ClusterType;
    }

    @Override
    public final UniRef90ClusterType UniRef90Cluster() {
        return uniRef90ClusterType;
    }

    @Override
    public final UniRef100ClusterType UniRef100Cluster() {
        return uniRef100ClusterType;
    }
```


		You can use this as `uniRefGraph.withUniprot(new TitanUniprotUniRefGraph(raw, uniprotGraph, uniRefGraph))`


```java
	public TitanUniRefGraph withUniProtUniRefGraph(TitanUniProtUniRefGraph uniProtUniRefGraph) {

		this.uniProtUniRefGraph = uniProtUniRefGraph;

		return this;
	}
}
```




[test/java/com/bio4j/titan/tests/ImportEnzymeDBTitanTest.java]: ../../../../../../../test/java/com/bio4j/titan/tests/ImportEnzymeDBTitanTest.java.md
[test/java/com/bio4j/titan/tests/ImportUniRefTitanTest.java]: ../../../../../../../test/java/com/bio4j/titan/tests/ImportUniRefTitanTest.java.md
[test/java/com/bio4j/titan/tests/uniprot_go.scala]: ../../../../../../../test/java/com/bio4j/titan/tests/uniprot_go.scala.md
[test/java/com/bio4j/titan/tests/uniref.scala]: ../../../../../../../test/java/com/bio4j/titan/tests/uniref.scala.md
[test/java/com/bio4j/titan/tests/ImportUniProtGoTitanTest.java]: ../../../../../../../test/java/com/bio4j/titan/tests/ImportUniProtGoTitanTest.java.md
[test/java/com/bio4j/titan/tests/ImportGOTitanTest.java]: ../../../../../../../test/java/com/bio4j/titan/tests/ImportGOTitanTest.java.md
[test/java/com/bio4j/titan/tests/go.scala]: ../../../../../../../test/java/com/bio4j/titan/tests/go.scala.md
[test/java/com/bio4j/titan/tests/IndicesTest.java]: ../../../../../../../test/java/com/bio4j/titan/tests/IndicesTest.java.md
[test/java/com/bio4j/titan/tests/IndexTestSuite.scala]: ../../../../../../../test/java/com/bio4j/titan/tests/IndexTestSuite.scala.md
[test/java/com/bio4j/titan/tests/enzymedb.scala]: ../../../../../../../test/java/com/bio4j/titan/tests/enzymedb.scala.md
[main/java/com/bio4j/titan/util/DefaultTitanGraph.java]: ../../util/DefaultTitanGraph.java.md
[main/java/com/bio4j/titan/programs/ImportTitanDB.java]: ../../programs/ImportTitanDB.java.md
[main/java/com/bio4j/titan/model/uniprot_uniref/programs/ImportUniProtUniRefUsingFolderTitan.java]: ../uniprot_uniref/programs/ImportUniProtUniRefUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_uniref/programs/ImportUniProtUniRefTitan.java]: ../uniprot_uniref/programs/ImportUniProtUniRefTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_uniref/TitanUniProtUniRefGraph.java]: ../uniprot_uniref/TitanUniProtUniRefGraph.java.md
[main/java/com/bio4j/titan/model/uniref/programs/SplitUniRefXMLFile.java]: programs/SplitUniRefXMLFile.java.md
[main/java/com/bio4j/titan/model/uniref/programs/ImportUniRefTitan.java]: programs/ImportUniRefTitan.java.md
[main/java/com/bio4j/titan/model/uniref/TitanUniRefGraph.java]: TitanUniRefGraph.java.md
[main/java/com/bio4j/titan/model/enzyme/programs/ImportEnzymeDBTitan.java]: ../enzyme/programs/ImportEnzymeDBTitan.java.md
[main/java/com/bio4j/titan/model/enzyme/TitanEnzymeDBGraph.java]: ../enzyme/TitanEnzymeDBGraph.java.md
[main/java/com/bio4j/titan/model/go/TitanGoGraph.java]: ../go/TitanGoGraph.java.md
[main/java/com/bio4j/titan/model/go/programs/ImportGOTitan.java]: ../go/programs/ImportGOTitan.java.md
[main/java/com/bio4j/titan/model/ncbiTaxonomy_geninfo/TitanNCBITaxonomyGenInfoGraph.java]: ../ncbiTaxonomy_geninfo/TitanNCBITaxonomyGenInfoGraph.java.md
[main/java/com/bio4j/titan/model/ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndexTitan.java]: ../ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndexTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomyUsingFolderTitan.java]: ../uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomyUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomyTitan.java]: ../uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomyTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_ncbiTaxonomy/TitanUniProtNCBITaxonomyGraph.java]: ../uniprot_ncbiTaxonomy/TitanUniProtNCBITaxonomyGraph.java.md
[main/java/com/bio4j/titan/model/ncbiTaxonomy/TitanNCBITaxonomyGraph.java]: ../ncbiTaxonomy/TitanNCBITaxonomyGraph.java.md
[main/java/com/bio4j/titan/model/ncbiTaxonomy/programs/ImportNCBITaxonomyTitan.java]: ../ncbiTaxonomy/programs/ImportNCBITaxonomyTitan.java.md
[main/java/com/bio4j/titan/model/geninfo/TitanGenInfoGraph.java]: ../geninfo/TitanGenInfoGraph.java.md
[main/java/com/bio4j/titan/model/uniprot_go/TitanUniProtGoGraph.java]: ../uniprot_go/TitanUniProtGoGraph.java.md
[main/java/com/bio4j/titan/model/uniprot_go/programs/ImportUniProtGoUsingFolderTitan.java]: ../uniprot_go/programs/ImportUniProtGoUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_go/programs/ImportUniProtGoTitan.java]: ../uniprot_go/programs/ImportUniProtGoTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_enzyme/TitanUniProtEnzymeGraph.java]: ../uniprot_enzyme/TitanUniProtEnzymeGraph.java.md
[main/java/com/bio4j/titan/model/uniprot_enzyme/programs/ImportUniProtEnzymeDBTitan.java]: ../uniprot_enzyme/programs/ImportUniProtEnzymeDBTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_enzyme/programs/ImportUniProtEnzymeDBUsingFolderTitan.java]: ../uniprot_enzyme/programs/ImportUniProtEnzymeDBUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtTitan.java]: ../uniprot/programs/ImportUniProtTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtEdgesTitan.java]: ../uniprot/programs/ImportUniProtEdgesTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportProteinInteractionsUsingFolderTitan.java]: ../uniprot/programs/ImportProteinInteractionsUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportIsoformSequencesTitan.java]: ../uniprot/programs/ImportIsoformSequencesTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/SplitUniProtXMLFile.java]: ../uniprot/programs/SplitUniProtXMLFile.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtVerticesTitan.java]: ../uniprot/programs/ImportUniProtVerticesTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtVerticesUsingFolderTitan.java]: ../uniprot/programs/ImportUniProtVerticesUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportProteinInteractionsTitan.java]: ../uniprot/programs/ImportProteinInteractionsTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtEdgesUsingFolderTitan.java]: ../uniprot/programs/ImportUniProtEdgesUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/TitanUniProtGraph.java]: ../uniprot/TitanUniProtGraph.java.md
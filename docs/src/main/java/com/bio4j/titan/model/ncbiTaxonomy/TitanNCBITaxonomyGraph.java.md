
```java
package com.bio4j.titan.model.ncbiTaxonomy;

import com.bio4j.angulillos.TypedVertexIndex;
import com.bio4j.angulillos.titan.TitanTypedVertexIndex;
import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph;
import com.bio4j.model.ncbiTaxonomy.vertices.NCBITaxon;
import com.bio4j.model.ncbiTaxonomy_geninfo.NCBITaxonomyGenInfoGraph;
import com.bio4j.model.uniprot_ncbiTaxonomy.UniProtNCBITaxonomyGraph;

import com.bio4j.titan.model.uniprot_ncbiTaxonomy.TitanUniProtNCBITaxonomyGraph;
import com.bio4j.titan.model.ncbiTaxonomy_geninfo.TitanNCBITaxonomyGenInfoGraph;

import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.EdgeLabelMaker;
import com.thinkaurelius.titan.core.schema.TitanManagement;
import com.thinkaurelius.titan.core.schema.VertexLabelMaker;


/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class TitanNCBITaxonomyGraph
		extends
		NCBITaxonomyGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> {

	private TitanUniProtNCBITaxonomyGraph uniprotNCBITaxonomyGraph = null;
	private NCBITaxonomyGenInfoGraph ncbiTaxonomyGenInfoGraph = null;

	private TitanManagement mgmt = null;

	//-------------------VERTICES----------------------------

	public VertexLabel nCBITaxonTypeLabel;
	public PropertyKey nCBITaxonTypekey;
	public PropertyKey nCBITaxonIdkey;
	public PropertyKey nCBITaxonNamekey;
	public PropertyKey nCBITaxonTaxonomicRankkey;
	public NCBITaxonType nCBITaxonType;

	//---------------RELATIONSHIPS---------------------------

	private EdgeLabel nCBITaxonParentLabel;
	private NCBITaxonParentType ncbiTaxonParentType;


	//---------------INDICES---------------------------

	TitanTypedVertexIndex.DefaultUnique<
			NCBITaxon<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
			NCBITaxonType,
			NCBITaxonType.id, String,
			NCBITaxonomyGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
			DefaultTitanGraph
			> nCBITaxonIdIndex;

	public TitanNCBITaxonomyGraph(DefaultTitanGraph rawGraph) {

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

	@Override
	public TypedVertexIndex.Unique<NCBITaxon<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, NCBITaxonType, NCBITaxonType.id, String, NCBITaxonomyGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> nCBITaxonIdIndex() {
		return nCBITaxonIdIndex;
	}

	@Override
	public TitanUniProtNCBITaxonomyGraph uniProtNCBITaxonomyGraph() {
		return uniprotNCBITaxonomyGraph;
	}

	@Override
	public TitanNCBITaxonomyGenInfoGraph ncbiTaxonomyGenInfoGraph() {
		return ncbiTaxonomyGenInfoGraph();
	}

	private void initTypes(TitanManagement mgmt) {

		//-----------------------------------------------------------------------------------------
		//--------------------------------VERTICES--------------------------------------------
		VertexLabelMaker nCBITaxonTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new NCBITaxonType(null));
		nCBITaxonType = new NCBITaxonType(nCBITaxonTypeLabelMaker);
		nCBITaxonIdkey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, NCBITaxon().id).cardinality(Cardinality.SINGLE));
		nCBITaxonTaxonomicRankkey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, NCBITaxon().taxonomicRank).cardinality(Cardinality.SINGLE));
		nCBITaxonNamekey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, NCBITaxon().name).cardinality(Cardinality.SINGLE));
		nCBITaxonTypeLabel = raw().createOrGet(mgmt, nCBITaxonType.raw());

		//-----------------------------------------------------------------------------------------
		//--------------------------------RELATIONSHIPS--------------------------------------------
		// nCBITaxonParent
		EdgeLabelMaker nCBITaxonParentTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new NCBITaxonParentType(null));
		ncbiTaxonParentType = new NCBITaxonParentType(nCBITaxonParentTypeLabelMaker);
		nCBITaxonParentLabel = raw().createOrGet(mgmt, ncbiTaxonParentType.raw());
	}

	private void initIndices(TitanManagement mgmt) {
		nCBITaxonIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(mgmt, this, NCBITaxon().id);
		nCBITaxonIdIndex.makeOrGet(nCBITaxonTypeLabel);
	}


	@Override
	public NCBITaxonType NCBITaxon() {
		return nCBITaxonType;
	}

	@Override
	public NCBITaxonParentType NCBITaxonParent() {
		return ncbiTaxonParentType;
	}
```


		You can use this as `ncbiTaxonomyGraph.withUniprot(new TitanUniprotNCBITaxonomyGraph(raw, uniprotGraph, ncbiTaxonomyGraph))`


```java
	public TitanNCBITaxonomyGraph withUniProtNCBITaxonomyGraph(TitanUniProtNCBITaxonomyGraph uniprotNCBITaxonomyGraph) {

		this.uniprotNCBITaxonomyGraph = uniprotNCBITaxonomyGraph;

		return this;
	}

	public TitanNCBITaxonomyGraph withNCBITaxonomyGenInfoGraph(TitanNCBITaxonomyGenInfoGraph ncbiTaxonomyGenInfoGraph) {

		this.ncbiTaxonomyGenInfoGraph = ncbiTaxonomyGenInfoGraph;

		return this;
	}
}

```




[main/java/com/bio4j/titan/model/enzyme/programs/ImportEnzymeDBTitan.java]: ../enzyme/programs/ImportEnzymeDBTitan.java.md
[main/java/com/bio4j/titan/model/enzyme/TitanEnzymeDBGraph.java]: ../enzyme/TitanEnzymeDBGraph.java.md
[main/java/com/bio4j/titan/model/geninfo/TitanGenInfoGraph.java]: ../geninfo/TitanGenInfoGraph.java.md
[main/java/com/bio4j/titan/model/go/programs/ImportGOTitan.java]: ../go/programs/ImportGOTitan.java.md
[main/java/com/bio4j/titan/model/go/TitanGoGraph.java]: ../go/TitanGoGraph.java.md
[main/java/com/bio4j/titan/model/ncbiTaxonomy/programs/ImportNCBITaxonomyTitan.java]: programs/ImportNCBITaxonomyTitan.java.md
[main/java/com/bio4j/titan/model/ncbiTaxonomy/TitanNCBITaxonomyGraph.java]: TitanNCBITaxonomyGraph.java.md
[main/java/com/bio4j/titan/model/ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndexTitan.java]: ../ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndexTitan.java.md
[main/java/com/bio4j/titan/model/ncbiTaxonomy_geninfo/TitanNCBITaxonomyGenInfoGraph.java]: ../ncbiTaxonomy_geninfo/TitanNCBITaxonomyGenInfoGraph.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportIsoformSequencesTitan.java]: ../uniprot/programs/ImportIsoformSequencesTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportProteinInteractionsTitan.java]: ../uniprot/programs/ImportProteinInteractionsTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportProteinInteractionsUsingFolderTitan.java]: ../uniprot/programs/ImportProteinInteractionsUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtEdgesTitan.java]: ../uniprot/programs/ImportUniProtEdgesTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtEdgesUsingFolderTitan.java]: ../uniprot/programs/ImportUniProtEdgesUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtTitan.java]: ../uniprot/programs/ImportUniProtTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtVerticesTitan.java]: ../uniprot/programs/ImportUniProtVerticesTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtVerticesUsingFolderTitan.java]: ../uniprot/programs/ImportUniProtVerticesUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/SplitUniProtXMLFile.java]: ../uniprot/programs/SplitUniProtXMLFile.java.md
[main/java/com/bio4j/titan/model/uniprot/TitanUniProtGraph.java]: ../uniprot/TitanUniProtGraph.java.md
[main/java/com/bio4j/titan/model/uniprot_enzyme/programs/ImportUniProtEnzymeDBTitan.java]: ../uniprot_enzyme/programs/ImportUniProtEnzymeDBTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_enzyme/programs/ImportUniProtEnzymeDBUsingFolderTitan.java]: ../uniprot_enzyme/programs/ImportUniProtEnzymeDBUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_enzyme/TitanUniProtEnzymeGraph.java]: ../uniprot_enzyme/TitanUniProtEnzymeGraph.java.md
[main/java/com/bio4j/titan/model/uniprot_go/programs/ImportUniProtGoTitan.java]: ../uniprot_go/programs/ImportUniProtGoTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_go/programs/ImportUniProtGoUsingFolderTitan.java]: ../uniprot_go/programs/ImportUniProtGoUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_go/TitanUniProtGoGraph.java]: ../uniprot_go/TitanUniProtGoGraph.java.md
[main/java/com/bio4j/titan/model/uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomyTitan.java]: ../uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomyTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomyUsingFolderTitan.java]: ../uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomyUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_ncbiTaxonomy/TitanUniProtNCBITaxonomyGraph.java]: ../uniprot_ncbiTaxonomy/TitanUniProtNCBITaxonomyGraph.java.md
[main/java/com/bio4j/titan/model/uniprot_uniref/programs/ImportUniProtUniRefTitan.java]: ../uniprot_uniref/programs/ImportUniProtUniRefTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_uniref/programs/ImportUniProtUniRefUsingFolderTitan.java]: ../uniprot_uniref/programs/ImportUniProtUniRefUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_uniref/TitanUniProtUniRefGraph.java]: ../uniprot_uniref/TitanUniProtUniRefGraph.java.md
[main/java/com/bio4j/titan/model/uniref/programs/ImportUniRefTitan.java]: ../uniref/programs/ImportUniRefTitan.java.md
[main/java/com/bio4j/titan/model/uniref/programs/SplitUniRefXMLFile.java]: ../uniref/programs/SplitUniRefXMLFile.java.md
[main/java/com/bio4j/titan/model/uniref/TitanUniRefGraph.java]: ../uniref/TitanUniRefGraph.java.md
[main/java/com/bio4j/titan/programs/ImportTitanDB.java]: ../../programs/ImportTitanDB.java.md
[main/java/com/bio4j/titan/util/DefaultTitanGraph.java]: ../../util/DefaultTitanGraph.java.md
[test/java/com/bio4j/titan/tests/enzymedb.scala]: ../../../../../../../test/java/com/bio4j/titan/tests/enzymedb.scala.md
[test/java/com/bio4j/titan/tests/go.scala]: ../../../../../../../test/java/com/bio4j/titan/tests/go.scala.md
[test/java/com/bio4j/titan/tests/ImportEnzymeDBTitanTest.java]: ../../../../../../../test/java/com/bio4j/titan/tests/ImportEnzymeDBTitanTest.java.md
[test/java/com/bio4j/titan/tests/ImportGOTitanTest.java]: ../../../../../../../test/java/com/bio4j/titan/tests/ImportGOTitanTest.java.md
[test/java/com/bio4j/titan/tests/ImportUniProtGoTitanTest.java]: ../../../../../../../test/java/com/bio4j/titan/tests/ImportUniProtGoTitanTest.java.md
[test/java/com/bio4j/titan/tests/ImportUniRefTitanTest.java]: ../../../../../../../test/java/com/bio4j/titan/tests/ImportUniRefTitanTest.java.md
[test/java/com/bio4j/titan/tests/IndexTestSuite.scala]: ../../../../../../../test/java/com/bio4j/titan/tests/IndexTestSuite.scala.md
[test/java/com/bio4j/titan/tests/IndicesTest.java]: ../../../../../../../test/java/com/bio4j/titan/tests/IndicesTest.java.md
[test/java/com/bio4j/titan/tests/uniprot_go.scala]: ../../../../../../../test/java/com/bio4j/titan/tests/uniprot_go.scala.md
[test/java/com/bio4j/titan/tests/uniref.scala]: ../../../../../../../test/java/com/bio4j/titan/tests/uniref.scala.md
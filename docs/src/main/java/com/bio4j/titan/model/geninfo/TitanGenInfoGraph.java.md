
```java
package com.bio4j.titan.model.geninfo;

import com.bio4j.angulillos.TypedVertexIndex;
import com.bio4j.angulillos.titan.TitanTypedVertexIndex;
import com.bio4j.model.geninfo.GenInfoGraph;
import com.bio4j.model.geninfo.vertices.GenInfo;
import com.bio4j.model.ncbiTaxonomy_geninfo.NCBITaxonomyGenInfoGraph;
import com.bio4j.titan.model.ncbiTaxonomy_geninfo.TitanNCBITaxonomyGenInfoGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.*;


/**
 @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class TitanGenInfoGraph
extends
		GenInfoGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> {

	private TitanNCBITaxonomyGenInfoGraph ncbiTaxonomyGenInfoGraph = null;

	private TitanManagement mgmt;


	//-------------------VERTICES----------------------------

	public VertexLabel genInfoTypeLabel;
	public PropertyKey genInfoIdkey;
	public GenInfoType genInfoType;

	//---------------INDICES---------------------------

	TitanTypedVertexIndex.DefaultUnique<
			GenInfo<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
			GenInfoType,
			GenInfoType.id, String,
			GenInfoGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
			DefaultTitanGraph
			> genInfoIdIndex;

	@Override
	public TypedVertexIndex.Unique<GenInfo<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, GenInfoType, GenInfoType.id, String, GenInfoGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> genInfoIdIndex() {
		return genInfoIdIndex;
	}

	public TitanGenInfoGraph(DefaultTitanGraph rawGraph) {
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
	public NCBITaxonomyGenInfoGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> ncbiTaxonomyGenInfoGraph() {
		return null;
	}

	@Override
	public GenInfoType GenInfo() {
		return genInfoType;
	}

	private void initTypes(TitanManagement mgmt) {

		//-----------------------------------------------------------------------------------------
		//--------------------------------VERTICES--------------------------------------------
		VertexLabelMaker enzymeTypeLabelMaker = raw().titanLabelMakerForVertexType( mgmt, new GenInfoType(null));
		genInfoType = new GenInfoType(enzymeTypeLabelMaker);
		genInfoIdkey = raw().createOrGet( mgmt,	raw().titanPropertyMakerForVertexProperty( mgmt, GenInfo().id ).cardinality(Cardinality.SINGLE));
		this.genInfoTypeLabel = raw().createOrGet(mgmt, genInfoType.raw());

	}

	private void initIndices(TitanManagement mgmt) {
		genInfoIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(mgmt, this, GenInfo().id);
		genInfoIdIndex.makeOrGet(genInfoTypeLabel);
	}
```


		You can use this as `enzymeDBGraph.withUniprot(new TitanUniprotEnzymeDBGraph(raw, uniprotGraph, enzymeGraph))`


```java
	public TitanGenInfoGraph withNCBITaxonomyGenInfoGraph(TitanNCBITaxonomyGenInfoGraph ncbiTaxonomyGenInfoGraph) {

		this.ncbiTaxonomyGenInfoGraph = ncbiTaxonomyGenInfoGraph;

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
[main/java/com/bio4j/titan/model/uniref/programs/SplitUniRefXMLFile.java]: ../uniref/programs/SplitUniRefXMLFile.java.md
[main/java/com/bio4j/titan/model/uniref/programs/ImportUniRefTitan.java]: ../uniref/programs/ImportUniRefTitan.java.md
[main/java/com/bio4j/titan/model/uniref/TitanUniRefGraph.java]: ../uniref/TitanUniRefGraph.java.md
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
[main/java/com/bio4j/titan/model/geninfo/TitanGenInfoGraph.java]: TitanGenInfoGraph.java.md
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
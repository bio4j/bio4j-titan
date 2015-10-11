
```scala
package com.bio4j.titan.tests.indices

import com.bio4j.titan.tests._

class IndexTestSuite extends org.scalatest.FunSuite {

  test("Testing indices") {

    val javaTestClass = new IndicesTest()

    javaTestClass.indicesTest("raw/indices")
  }
}

```




[main/java/com/bio4j/titan/model/enzyme/programs/ImportEnzymeDBTitan.java]: ../../../../../../main/java/com/bio4j/titan/model/enzyme/programs/ImportEnzymeDBTitan.java.md
[main/java/com/bio4j/titan/model/enzyme/TitanEnzymeDBGraph.java]: ../../../../../../main/java/com/bio4j/titan/model/enzyme/TitanEnzymeDBGraph.java.md
[main/java/com/bio4j/titan/model/geninfo/TitanGenInfoGraph.java]: ../../../../../../main/java/com/bio4j/titan/model/geninfo/TitanGenInfoGraph.java.md
[main/java/com/bio4j/titan/model/go/programs/ImportGOTitan.java]: ../../../../../../main/java/com/bio4j/titan/model/go/programs/ImportGOTitan.java.md
[main/java/com/bio4j/titan/model/go/TitanGoGraph.java]: ../../../../../../main/java/com/bio4j/titan/model/go/TitanGoGraph.java.md
[main/java/com/bio4j/titan/model/ncbiTaxonomy/programs/ImportNCBITaxonomyTitan.java]: ../../../../../../main/java/com/bio4j/titan/model/ncbiTaxonomy/programs/ImportNCBITaxonomyTitan.java.md
[main/java/com/bio4j/titan/model/ncbiTaxonomy/TitanNCBITaxonomyGraph.java]: ../../../../../../main/java/com/bio4j/titan/model/ncbiTaxonomy/TitanNCBITaxonomyGraph.java.md
[main/java/com/bio4j/titan/model/ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndexTitan.java]: ../../../../../../main/java/com/bio4j/titan/model/ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndexTitan.java.md
[main/java/com/bio4j/titan/model/ncbiTaxonomy_geninfo/TitanNCBITaxonomyGenInfoGraph.java]: ../../../../../../main/java/com/bio4j/titan/model/ncbiTaxonomy_geninfo/TitanNCBITaxonomyGenInfoGraph.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportIsoformSequencesTitan.java]: ../../../../../../main/java/com/bio4j/titan/model/uniprot/programs/ImportIsoformSequencesTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportProteinInteractionsTitan.java]: ../../../../../../main/java/com/bio4j/titan/model/uniprot/programs/ImportProteinInteractionsTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportProteinInteractionsUsingFolderTitan.java]: ../../../../../../main/java/com/bio4j/titan/model/uniprot/programs/ImportProteinInteractionsUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtEdgesTitan.java]: ../../../../../../main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtEdgesTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtEdgesUsingFolderTitan.java]: ../../../../../../main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtEdgesUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtTitan.java]: ../../../../../../main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtVerticesTitan.java]: ../../../../../../main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtVerticesTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtVerticesUsingFolderTitan.java]: ../../../../../../main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtVerticesUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/SplitUniProtXMLFile.java]: ../../../../../../main/java/com/bio4j/titan/model/uniprot/programs/SplitUniProtXMLFile.java.md
[main/java/com/bio4j/titan/model/uniprot/TitanUniProtGraph.java]: ../../../../../../main/java/com/bio4j/titan/model/uniprot/TitanUniProtGraph.java.md
[main/java/com/bio4j/titan/model/uniprot_enzyme/programs/ImportUniProtEnzymeDBTitan.java]: ../../../../../../main/java/com/bio4j/titan/model/uniprot_enzyme/programs/ImportUniProtEnzymeDBTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_enzyme/programs/ImportUniProtEnzymeDBUsingFolderTitan.java]: ../../../../../../main/java/com/bio4j/titan/model/uniprot_enzyme/programs/ImportUniProtEnzymeDBUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_enzyme/TitanUniProtEnzymeGraph.java]: ../../../../../../main/java/com/bio4j/titan/model/uniprot_enzyme/TitanUniProtEnzymeGraph.java.md
[main/java/com/bio4j/titan/model/uniprot_go/programs/ImportUniProtGoTitan.java]: ../../../../../../main/java/com/bio4j/titan/model/uniprot_go/programs/ImportUniProtGoTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_go/programs/ImportUniProtGoUsingFolderTitan.java]: ../../../../../../main/java/com/bio4j/titan/model/uniprot_go/programs/ImportUniProtGoUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_go/TitanUniProtGoGraph.java]: ../../../../../../main/java/com/bio4j/titan/model/uniprot_go/TitanUniProtGoGraph.java.md
[main/java/com/bio4j/titan/model/uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomyTitan.java]: ../../../../../../main/java/com/bio4j/titan/model/uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomyTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomyUsingFolderTitan.java]: ../../../../../../main/java/com/bio4j/titan/model/uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomyUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_ncbiTaxonomy/TitanUniProtNCBITaxonomyGraph.java]: ../../../../../../main/java/com/bio4j/titan/model/uniprot_ncbiTaxonomy/TitanUniProtNCBITaxonomyGraph.java.md
[main/java/com/bio4j/titan/model/uniprot_uniref/programs/ImportUniProtUniRefTitan.java]: ../../../../../../main/java/com/bio4j/titan/model/uniprot_uniref/programs/ImportUniProtUniRefTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_uniref/programs/ImportUniProtUniRefUsingFolderTitan.java]: ../../../../../../main/java/com/bio4j/titan/model/uniprot_uniref/programs/ImportUniProtUniRefUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_uniref/TitanUniProtUniRefGraph.java]: ../../../../../../main/java/com/bio4j/titan/model/uniprot_uniref/TitanUniProtUniRefGraph.java.md
[main/java/com/bio4j/titan/model/uniref/programs/ImportUniRefTitan.java]: ../../../../../../main/java/com/bio4j/titan/model/uniref/programs/ImportUniRefTitan.java.md
[main/java/com/bio4j/titan/model/uniref/programs/SplitUniRefXMLFile.java]: ../../../../../../main/java/com/bio4j/titan/model/uniref/programs/SplitUniRefXMLFile.java.md
[main/java/com/bio4j/titan/model/uniref/TitanUniRefGraph.java]: ../../../../../../main/java/com/bio4j/titan/model/uniref/TitanUniRefGraph.java.md
[main/java/com/bio4j/titan/programs/ImportTitanDB.java]: ../../../../../../main/java/com/bio4j/titan/programs/ImportTitanDB.java.md
[main/java/com/bio4j/titan/util/DefaultTitanGraph.java]: ../../../../../../main/java/com/bio4j/titan/util/DefaultTitanGraph.java.md
[test/java/com/bio4j/titan/tests/enzymedb.scala]: enzymedb.scala.md
[test/java/com/bio4j/titan/tests/go.scala]: go.scala.md
[test/java/com/bio4j/titan/tests/ImportEnzymeDBTitanTest.java]: ImportEnzymeDBTitanTest.java.md
[test/java/com/bio4j/titan/tests/ImportGOTitanTest.java]: ImportGOTitanTest.java.md
[test/java/com/bio4j/titan/tests/ImportUniProtGoTitanTest.java]: ImportUniProtGoTitanTest.java.md
[test/java/com/bio4j/titan/tests/ImportUniRefTitanTest.java]: ImportUniRefTitanTest.java.md
[test/java/com/bio4j/titan/tests/IndexTestSuite.scala]: IndexTestSuite.scala.md
[test/java/com/bio4j/titan/tests/IndicesTest.java]: IndicesTest.java.md
[test/java/com/bio4j/titan/tests/uniprot_go.scala]: uniprot_go.scala.md
[test/java/com/bio4j/titan/tests/uniref.scala]: uniref.scala.md
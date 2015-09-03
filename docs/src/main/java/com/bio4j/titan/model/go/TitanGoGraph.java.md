
```java
package com.bio4j.titan.model.go;

import com.bio4j.angulillos.TypedVertexIndex;
import com.bio4j.angulillos.titan.TitanTypedVertexIndex;
import com.bio4j.model.go.GoGraph;
import com.bio4j.model.go.vertices.GoTerm;
import com.bio4j.model.go.vertices.SubOntologies;
import com.bio4j.model.uniprot_go.UniProtGoGraph;
import com.bio4j.titan.model.uniprot_go.TitanUniProtGoGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.*;


/**
  Implementing the types with Titan
  @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
*/
public final class TitanGoGraph
        extends
        GoGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> {

	private TitanUniProtGoGraph uniProtGoGraph = null;

	private TitanManagement mgmt;

    //-------------------VERTICES----------------------------

	private VertexLabel goTermTypeLabel;
    private PropertyKey goTermTypekey;
    private PropertyKey goTermIdKey;
    private PropertyKey goTermNameKey;
    private PropertyKey goTermDefinitionKey;
    private PropertyKey goTermObsoleteKey;
    private PropertyKey goTermCommentKey;
    private PropertyKey goTermSynonymKey;
    public GoTermType goTermType;

	private VertexLabel subOntologiesTypeLabel;
    private PropertyKey subOntologiesTypekey;
    private PropertyKey subOntologiesNameKey;
    public SubOntologiesType subOntologiesType;

    //---------------RELATIONSHIPS---------------------------

    private EdgeLabel isALabel;
    private IsAType isAType;
    private EdgeLabel partOfLabel;
    private PartOfType partOfType;
    private EdgeLabel hasPartOfLabel;
    private HasPartOfType hasPartOfType;
    private EdgeLabel regulatesLabel;
    private RegulatesType regulatesType;
    private EdgeLabel positivelyRegulatesLabel;
    private PositivelyRegulatesType positivelyRegulatesType;
    private EdgeLabel negativelyRegulatesLabel;
    private NegativelyRegulatesType negativelyRegulatesType;
    private EdgeLabel subOntologyLabel;
    private SubOntologyType subOntologyType;

    //---------------INDICES---------------------------

    private TitanTypedVertexIndex.DefaultUnique<
        GoTerm<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
        GoTermType,
        GoTermType.id, String,
        GoGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
        DefaultTitanGraph
    > goTermIdIndex;

    private TitanTypedVertexIndex.DefaultUnique<
        SubOntologies<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
        SubOntologiesType,
        SubOntologiesType.name, String,
        GoGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
        DefaultTitanGraph
    > subOntologiesNameIndex;


    public TitanGoGraph(DefaultTitanGraph rawGraph) {
        
        super(rawGraph);

	    this.mgmt = raw().managementSystem();
        initTypes(mgmt);
        initIndices(mgmt);

	    this.mgmt.commit();
    }
```

this method should be idempotent. This is important

```java
    private void initTypes(TitanManagement mgmt) {

        //-----------------------------------------------------------------------------------------
        //--------------------------------VERTICES--------------------------------------------
	    VertexLabelMaker goTermTypeLabelMaker = raw().titanLabelMakerForVertexType( mgmt, 
            new GoTermType(null)
        );
        goTermType = new GoTermType(goTermTypeLabelMaker);
        goTermIdKey = raw().createOrGet( mgmt,raw().titanPropertyMakerForVertexProperty( mgmt, GoTerm().id ).cardinality(Cardinality.SINGLE));
        goTermNameKey = raw().createOrGet( mgmt,	raw().titanPropertyMakerForVertexProperty( mgmt, GoTerm().name ).cardinality(Cardinality.SINGLE));
        goTermDefinitionKey = raw().createOrGet( mgmt,	raw().titanPropertyMakerForVertexProperty( mgmt, GoTerm().definition ).cardinality(Cardinality.SINGLE));
        goTermObsoleteKey = raw().createOrGet( mgmt,	raw().titanPropertyMakerForVertexProperty( mgmt, GoTerm().obsolete ).cardinality(Cardinality.SINGLE));
        goTermCommentKey = raw().createOrGet( mgmt,	raw().titanPropertyMakerForVertexProperty( mgmt, GoTerm().comment ).cardinality(Cardinality.SINGLE));
        goTermSynonymKey = raw().createOrGet( mgmt,	raw().titanPropertyMakerForVertexProperty( mgmt, GoTerm().synonym ).cardinality(Cardinality.SINGLE));
	    goTermTypeLabel = raw().createOrGet(mgmt, goTermType.raw());


	    VertexLabelMaker subOntologiesTypeLabelMaker = raw().titanLabelMakerForVertexType( mgmt, new SubOntologiesType(null));
        subOntologiesType = new SubOntologiesType(subOntologiesTypeLabelMaker);
        subOntologiesNameKey = raw().createOrGet( mgmt,	raw().titanPropertyMakerForVertexProperty( mgmt, SubOntologies().name ).cardinality(Cardinality.SINGLE));
	    subOntologiesTypeLabel = raw().createOrGet(mgmt, subOntologiesType.raw());


        //-----------------------------------------------------------------------------------------
        //--------------------------------RELATIONSHIPS--------------------------------------------

	    // isA
	    EdgeLabelMaker isATypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new IsAType(null));
	    isAType = new IsAType(isATypeLabelMaker);
        isALabel = raw().createOrGet(mgmt, isAType.raw());

	    // partOf
	    EdgeLabelMaker partOfTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new PartOfType(null));
        partOfType = new PartOfType(partOfTypeLabelMaker);
	    partOfLabel = raw().createOrGet(mgmt, partOfType.raw());

	    // hasPartOf
	    EdgeLabelMaker hasPartOfTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new HasPartOfType(null));
        hasPartOfType = new HasPartOfType(hasPartOfTypeLabelMaker);
	    hasPartOfLabel = raw().createOrGet(mgmt, hasPartOfType.raw());

	    // regulates
	    EdgeLabelMaker regulatesTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new RegulatesType(null));
	    regulatesType = new RegulatesType(regulatesTypeLabelMaker);
        regulatesLabel = raw().createOrGet(mgmt, regulatesType.raw());

	    // positivelyRegulates
	    EdgeLabelMaker positivelyRegulatesTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new PositivelyRegulatesType(null));
	    positivelyRegulatesType = new PositivelyRegulatesType(positivelyRegulatesTypeLabelMaker);
        positivelyRegulatesLabel = raw().createOrGet(mgmt, positivelyRegulatesType.raw());

	    // negativelyRegulates
	    EdgeLabelMaker negativelyRegulatesTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new NegativelyRegulatesType(null));
        negativelyRegulatesType = new NegativelyRegulatesType(negativelyRegulatesTypeLabelMaker);
	    negativelyRegulatesLabel = raw().createOrGet(mgmt, negativelyRegulatesType.raw());

	    // subOntology
	    EdgeLabelMaker subOntologyTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new SubOntologyType(null));
        subOntologyType = new SubOntologyType(subOntologyTypeLabelMaker);
	    subOntologyLabel = raw().createOrGet(mgmt, subOntologyType.raw());

    }

    private void initIndices(TitanManagement mgmt) {

        goTermIdIndex =  new TitanTypedVertexIndex.DefaultUnique<>(mgmt, this, GoTerm().id);
	    goTermIdIndex.makeOrGet(goTermTypeLabel);

        subOntologiesNameIndex =  new TitanTypedVertexIndex.DefaultUnique<>(mgmt, this, SubOntologies().name);
	    subOntologiesNameIndex.makeOrGet(subOntologiesTypeLabel);
    }

    @Override
    public IsAType IsA() {
        return isAType;
    }

    @Override
    public GoSlimsType GoSlims() {
        return null;
    }

    @Override
    public GoTermType GoTerm() {
        return goTermType;
    }

    @Override
    public SubOntologiesType SubOntologies() {
        return subOntologiesType;
    }

    @Override
    public PartOfType PartOf() {
        return partOfType;
    }

    @Override
    public HasPartOfType HasPartOf() {
        return hasPartOfType;
    }

    @Override
    public NegativelyRegulatesType NegativelyRegulates() {
        return negativelyRegulatesType;
    }

    @Override
    public PositivelyRegulatesType PositivelyRegulates() {
        return positivelyRegulatesType;
    }

    @Override
    public RegulatesType Regulates() {
        return regulatesType;
    }


    @Override
    public SubOntologyType SubOntology() {
        return subOntologyType;
    }

    @Override
    public TitanUniProtGoGraph uniProtGoGraph() {
        return uniProtGoGraph;
    }

    @Override
    public TypedVertexIndex.Unique<GoTerm<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, GoTermType, GoTermType.id, String, GoGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> goTermIdIndex() {
        return goTermIdIndex;
    }

    @Override
    public TypedVertexIndex.Unique<SubOntologies<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, SubOntologiesType, SubOntologiesType.name, String, GoGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> subontologiesNameIndex() {
        return subOntologiesNameIndex;
    }
```


		You can use this as `goGraph.withUniprot(new TitanUniprotGoGraph(raw, uniprotGraph, goGraph))`


```java
	public TitanGoGraph withUniProtGoGraph(TitanUniProtGoGraph uniProtGoGraph) {

		this.uniProtGoGraph = uniProtGoGraph;

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
[main/java/com/bio4j/titan/model/go/TitanGoGraph.java]: TitanGoGraph.java.md
[main/java/com/bio4j/titan/model/go/programs/ImportGOTitan.java]: programs/ImportGOTitan.java.md
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
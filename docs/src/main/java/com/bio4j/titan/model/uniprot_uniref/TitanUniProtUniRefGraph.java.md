
```java
package com.bio4j.titan.model.uniprot_uniref;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot_uniref.UniProtUniRefGraph;
import com.bio4j.titan.model.uniprot.TitanUniProtGraph;
import com.bio4j.titan.model.uniref.TitanUniRefGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.*;


/**
 Implementing the types with Titan
 @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class TitanUniProtUniRefGraph
        extends
        UniProtUniRefGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> {

    private TitanUniProtGraph uniprotGraph;
    private TitanUniRefGraph uniRefGraph;

	private TitanManagement mgmt = null;

    //---------------RELATIONSHIPS---------------------------

    private EdgeLabel uniRef50MemberLabel;
    private UniRef50MemberType uniRef50MemberType;
	public PropertyKey uniRef50MemberProteinAccessionKey;

    private EdgeLabel uniRef90MemberLabel;
    private UniRef90MemberType uniRef90MemberType;
	public PropertyKey uniRef90MemberProteinAccessionKey;

    private EdgeLabel uniRef100MemberLabel;
    private UniRef100MemberType uniRef100MemberType;
	public PropertyKey uniRef100MemberProteinAccessionKey;

    private EdgeLabel uniRef50RepresentantLabel;
    private UniRef50RepresentantType uniRef50RepresentantType;
	public PropertyKey uniRef50RepresentantProteinAccessionKey;

    private EdgeLabel uniRef90RepresentantLabel;
    private UniRef90RepresentantType uniRef90RepresentantType;
	public PropertyKey uniRef90RepresentantProteinAccessionKey;

    private EdgeLabel uniRef100RepresentantLabel;
    private UniRef100RepresentantType uniRef100RepresentantType;
	public PropertyKey uniRef100RepresentantProteinAccessionKey;


    public TitanUniProtUniRefGraph(
            DefaultTitanGraph rawGraph,
            TitanUniProtGraph titanUniProtGraph,
            TitanUniRefGraph titanUniRefGraph
    ) {
        super(rawGraph);
        this.raw = rawGraph;
```

update dependencies

```java
        this.uniprotGraph   = titanUniProtGraph.withUniProtUniRefGraph(this);
        this.uniRefGraph    =  titanUniRefGraph.withUniProtUniRefGraph(this);

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
        //--------------------------------RELATIONSHIPS--------------------------------------------

	    // uniRef50Member
	    EdgeLabelMaker uniRef50MemberTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new UniRef50MemberType(null));
	    uniRef50MemberType = new UniRef50MemberType(uniRef50MemberTypeLabelMaker);
	    uniRef50MemberProteinAccessionKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, UniRef50Member().proteinAccession).cardinality(Cardinality.SINGLE));
        uniRef50MemberLabel = raw().createOrGet(mgmt, uniRef50MemberType.raw());

	    // uniRef90Member
	    EdgeLabelMaker uniRef90MemberTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new UniRef90MemberType(null));
	    uniRef90MemberType = new UniRef90MemberType(uniRef90MemberTypeLabelMaker);
	    uniRef90MemberProteinAccessionKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, UniRef90Member().proteinAccession).cardinality(Cardinality.SINGLE));
        uniRef90MemberLabel = raw().createOrGet(mgmt, uniRef90MemberType.raw());

	    // uniRef100Member
	    EdgeLabelMaker uniRef100MemberTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new UniRef100MemberType(null));
	    uniRef100MemberType = new UniRef100MemberType(uniRef100MemberTypeLabelMaker);
	    uniRef100MemberProteinAccessionKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, UniRef100Member().proteinAccession).cardinality(Cardinality.SINGLE));
        uniRef100MemberLabel = raw().createOrGet(mgmt, uniRef100MemberType.raw());


	    // uniRef50Representant
	    EdgeLabelMaker uniRef50RepresentantTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new UniRef100MemberType(null));
	    uniRef50RepresentantType = new UniRef50RepresentantType(uniRef50RepresentantTypeLabelMaker);
	    uniRef50RepresentantProteinAccessionKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, UniRef50Representant().proteinAccession).cardinality(Cardinality.SINGLE));
        uniRef50RepresentantLabel = raw().createOrGet(mgmt, uniRef50RepresentantType.raw());

	    // uniRef90Representant
	    EdgeLabelMaker uniRef90RepresentantTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new UniRef100MemberType(null));
	    uniRef90RepresentantType = new UniRef90RepresentantType(uniRef90RepresentantTypeLabelMaker);
	    uniRef90RepresentantProteinAccessionKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, UniRef90Representant().proteinAccession).cardinality(Cardinality.SINGLE));
        uniRef90RepresentantLabel = raw().createOrGet(mgmt, uniRef90RepresentantType.raw());

	    // uniRef100Representant
	    EdgeLabelMaker uniRef100RepresentantTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new UniRef100MemberType(null));
	    uniRef100RepresentantType = new UniRef100RepresentantType(uniRef100RepresentantTypeLabelMaker);
	    uniRef100RepresentantProteinAccessionKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, UniRef100Representant().proteinAccession).cardinality(Cardinality.SINGLE));
        uniRef100RepresentantLabel = raw().createOrGet(mgmt, uniRef100RepresentantType.raw());

    }

    private void initIndices(TitanManagement mgmt) {

    }


    @Override
    public TitanUniProtGraph uniProtGraph() {
        return uniprotGraph;
    }

    @Override
    public TitanUniRefGraph uniRefGraph() {
        return uniRefGraph;
    }

    @Override
    public UniRef50MemberType UniRef50Member() {
        return uniRef50MemberType;
    }

    @Override
    public UniRef90MemberType UniRef90Member() {
        return uniRef90MemberType;
    }

    @Override
    public UniRef100MemberType UniRef100Member() {
        return uniRef100MemberType;
    }

    @Override
    public UniRef100RepresentantType UniRef100Representant() {
        return uniRef100RepresentantType;
    }

    @Override
    public UniRef90RepresentantType UniRef90Representant() {
        return uniRef90RepresentantType;
    }

    @Override
    public UniRef50RepresentantType UniRef50Representant() {
        return uniRef50RepresentantType;
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
[main/java/com/bio4j/titan/model/uniprot_uniref/programs/ImportUniProtUniRefUsingFolderTitan.java]: programs/ImportUniProtUniRefUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_uniref/programs/ImportUniProtUniRefTitan.java]: programs/ImportUniProtUniRefTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_uniref/TitanUniProtUniRefGraph.java]: TitanUniProtUniRefGraph.java.md
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
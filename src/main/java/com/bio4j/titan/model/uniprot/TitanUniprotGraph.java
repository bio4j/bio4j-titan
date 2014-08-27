package com.bio4j.titan.model.uniprot;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.*;
import com.bio4j.model.uniprot_enzymedb.UniprotEnzymeDBGraph;
import com.bio4j.model.uniprot_go.UniprotGoGraph;
import com.bio4j.model.uniprot_ncbiTaxonomy.UniprotNCBITaxonomyGraph;
import com.bio4j.model.uniprot_uniref.UniprotUniRefGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.ohnosequences.typedGraphs.TypedVertexIndex;
import com.ohnosequences.typedGraphs.titan.TitanTypedVertexIndex;
import com.thinkaurelius.titan.core.*;


/*
  Implementing the types with Titan
*/
public final class TitanUniprotGraph
        extends
        UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> {

    private DefaultTitanGraph rawGraph;


    //-------------------VERTICES----------------------------

    public TitanKey proteinTypekey;
    public TitanKey proteinAcessionKey;
    public TitanKey proteinNameKey;
    public TitanKey proteinShortNameKey;
    public TitanKey proteinFullNameKey;
    public TitanKey proteinModifiedDateKey;
    public TitanKey proteinCreatedDateKey;
    public TitanKey proteinMassKey;
    public TitanKey proteinVersionKey;
    public TitanKey proteinLengthKey;
    public TitanKey proteinSequenceKey;
    public ProteinType proteinType;

    public TitanKey pfamTypekey;
    public TitanKey pfamNameKey;

    //---------------RELATIONSHIPS---------------------------

    private TitanLabel proteinOrganismLabel;
    private ProteinOrganismType proteinOrganismType;

    //---------------INDICES---------------------------

    TitanTypedVertexIndex.Unique<Protein<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>,ProteinType, ProteinType.accession,String> proteinAccessionIndex;

    TitanUniprotGraph(DefaultTitanGraph rawGraph) {
        super(rawGraph);
        this.rawGraph = rawGraph;
        initTypes();
        initIndices();
    }

    @Override
    public DefaultTitanGraph raw() {
        return rawGraph;
    }

    private void initTypes(){

        //-----------------------------------------------------------------------------------------
        //--------------------------------VERTICES--------------------------------------------
        proteinTypekey = raw().titanKeyForVertexType(Protein().accession);
        proteinAcessionKey = proteinTypekey;
        proteinNameKey = titanKeyForNodeProperty(Protein().name);
        proteinShortNameKey = titanKeyForNodeProperty(Protein().shortName);
        proteinFullNameKey = titanKeyForNodeProperty(Protein().fullName);
        proteinModifiedDateKey = titanKeyForNodeProperty(Protein().modifiedDate);
        proteinCreatedDateKey = titanKeyForNodeProperty(Protein().createdDate);
        proteinMassKey = titanKeyForNodeProperty(Protein().mass);
        proteinVersionKey = titanKeyForNodeProperty(Protein().version);
        proteinLengthKey = titanKeyForNodeProperty(Protein().length);
        proteinSequenceKey = titanKeyForNodeProperty(Protein().sequence);
        proteinType = new ProteinType(proteinTypekey);


        //-----------------------------------------------------------------------------------------
        //--------------------------------RELATIONSHIPS--------------------------------------------

        proteinOrganismLabel = raw().titanLabelForEdgeType(new ProteinOrganismType((TitanLabel) null));
        proteinOrganismType = new ProteinOrganismType(proteinOrganismLabel);


    }
    private void initIndices(){

    }

    @Override
    public ProteinOrganismType ProteinOrganism() {
        return proteinOrganismType;
    }

    @Override
    public ProteinPfamType ProteinPfam() {
        return null;
    }

    @Override
    public ProteinPIRType ProteinPIR() {
        return null;
    }

    @Override
    public ProteinReactomeTermType ProteinReactomeTerm() {
        return null;
    }

    @Override
    public ProteinSubcellularLocationType ProteinSubcellularLocation() {
        return null;
    }

    @Override
    public ProteinUniGeneType ProteinUniGene() {
        return null;
    }

    @Override
    public ProteinRefSeqType ProteinRefSeq() {
        return null;
    }

    @Override
    public ProteinReferenceType ProteinReference() {
        return null;
    }

    @Override
    public ReferenceArticleType ReferenceArticle() {
        return null;
    }

    @Override
    public ReferenceBookType ReferenceBook() {
        return null;
    }

    @Override
    public ReferenceOnlineArticleType ReferenceOnlineArticle() {
        return null;
    }

    @Override
    public ReferencePatentType ReferencePatent() {
        return null;
    }

    @Override
    public ReferenceThesisType ReferenceThesis() {
        return null;
    }

    @Override
    public ReferenceSubmissionType ReferenceSubmission() {
        return null;
    }

    @Override
    public ReferenceUnpublishedObservationType ReferenceUnpublishedObservation() {
        return null;
    }

    @Override
    public SubmissionDBType SubmissionDB() {
        return null;
    }

    @Override
    public SubcellularLocationParentType SubcellularLocationParent() {
        return null;
    }

    @Override
    public TaxonParentType TaxonParent() {
        return null;
    }

    @Override
    public ThesisInstituteType ThesisInstitute() {
        return null;
    }


    @Override
    public UniprotUniRefGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> uniprotUniRefGraph() {
        return null;
    }

    @Override
    public UniprotGoGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> uniprotGoGraph() {
        return null;
    }

    @Override
    public UniprotEnzymeDBGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> uniprotEnzymeDBGraph() {
        return null;
    }

    @Override
    public UniprotNCBITaxonomyGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> uniprotNCBITaxonomyGraph() {
        return null;
    }

    @Override
    public TypedVertexIndex.Unique<Ensembl<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, EnsemblType, EnsemblType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> ensemblIdIndex() {
        return null;
    }

    @Override
    public TypedVertexIndex.Unique<PIR<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, PIRType, PIRType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> pIRIdIndex() {
        return null;
    }

    @Override
    public TypedVertexIndex.Unique<UniGene<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, UniGeneType, UniGeneType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> uniGeneIdIndex() {
        return null;
    }

    @Override
    public TypedVertexIndex.Unique<Kegg<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, KeggType, KeggType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> keggIdIndex() {
        return null;
    }

    @Override
    public TypedVertexIndex.Unique<EMBL<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, EMBLType, EMBLType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> eMBLIdIndex() {
        return null;
    }

    @Override
    public TypedVertexIndex.Unique<RefSeq<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, RefSeqType, RefSeqType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> refSeqIdIndex() {
        return null;
    }

    @Override
    public TypedVertexIndex.Unique<ReactomeTerm<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, ReactomeTermType, ReactomeTermType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> reactomeTermIdIndex() {
        return null;
    }

    @Override
    public TypedVertexIndex.Unique<Dataset<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DatasetType, DatasetType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> datasetNameIndex() {
        return null;
    }

    @Override
    public TypedVertexIndex.Unique<Keyword<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, KeywordType, KeywordType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> keywordIdIndex() {
        return null;
    }

    @Override
    public TypedVertexIndex.Unique<Interpro<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, InterproType, InterproType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> interproIdIndex() {
        return null;
    }

    @Override
    public TypedVertexIndex.Unique<Pfam<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, PfamType, PfamType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> pfamIdIndex() {
        return null;
    }

    @Override
    public TypedVertexIndex.Unique<Organism<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, OrganismType, OrganismType.scientificName, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> organismScientificNameIndex() {
        return null;
    }

    @Override
    public TypedVertexIndex.Unique<Taxon<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, TaxonType, TaxonType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> taxonNameIndex() {
        return null;
    }

    @Override
    public TypedVertexIndex.Unique<FeatureType<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, FeatureTypeType, FeatureTypeType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> featureTypeNameIndex() {
        return null;
    }

    @Override
    public ArticleType Article() {
        return null;
    }

    @Override
    public BookType Book() {
        return null;
    }

    @Override
    public CityType City() {
        return null;
    }

    @Override
    public CommentTypeType CommentType() {
        return null;
    }

    @Override
    public DatasetType Dataset() {
        return null;
    }

    @Override
    public CountryType Country() {
        return null;
    }

    @Override
    public DBType DB() {
        return null;
    }

    @Override
    public EMBLType EMBL() {
        return null;
    }

    @Override
    public EnsemblType Ensembl() {
        return null;
    }

    @Override
    public FeatureTypeType FeatureType() {
        return null;
    }

    @Override
    public InstituteType Institute() {
        return null;
    }

    @Override
    public InterproType Interpro() {
        return null;
    }

    @Override
    public JournalType Journal() {
        return null;
    }

    @Override
    public KeggType Kegg() {
        return null;
    }

    @Override
    public KeywordType Keyword() {
        return null;
    }

    @Override
    public OnlineArticleType OnlineArticle() {
        return null;
    }

    @Override
    public OrganismType Organism() {
        return null;
    }

    @Override
    public OnlineJournalType OnlineJournal() {
        return null;
    }

    @Override
    public PatentType Patent() {
        return null;
    }

    @Override
    public PfamType Pfam() {
        return null;
    }

    @Override
    public PIRType PIR() {
        return null;
    }

    @Override
    public ProteinType Protein() {
        return proteinType;
    }

    @Override
    public PublisherType Publisher() {
        return null;
    }

    @Override
    public PubmedType Pubmed() {
        return null;
    }

    @Override
    public ReactomeTermType ReactomeTerm() {
        return null;
    }

    @Override
    public ReferenceType Reference() {
        return null;
    }

    @Override
    public RefSeqType RefSeq() {
        return null;
    }

    @Override
    public SubcellularLocationType SubcellularLocation() {
        return null;
    }

    @Override
    public SubmissionType Submission() {
        return null;
    }

    @Override
    public TaxonType Taxon() {
        return null;
    }

    @Override
    public ThesisType Thesis() {
        return null;
    }

    @Override
    public UniGeneType UniGene() {
        return null;
    }

    @Override
    public UnpublishedObservationType UnpublishedObservation() {
        return null;
    }

    @Override
    public ArticleJournalType ArticleJournal() {
        return null;
    }

    @Override
    public ArticlePubmedType ArticlePubmed() {
        return null;
    }

    @Override
    public ArticleJournalType ArticleJournalType() {
        return null;
    }

    @Override
    public BookCityType BookCity() {
        return null;
    }

    @Override
    public BookPublisherType BookPublisher() {
        return null;
    }

    @Override
    public InstituteCountryType InstituteCountry() {
        return null;
    }

    @Override
    public OnlineArticleOnlineJournalType OnlineArticleOnlineJournal() {
        return null;
    }

    @Override
    public OrganismTaxonType OrganismTaxon() {
        return null;
    }

    @Override
    public ProteinCommentType ProteinComment() {
        return null;
    }

    @Override
    public ProteinDatasetType ProteinDataset() {
        return null;
    }

    @Override
    public ProteinEMBLType ProteinEMBL() {
        return null;
    }

    @Override
    public ProteinEnsemblType ProteinEnsembl() {
        return null;
    }

    @Override
    public ProteinFeatureType ProteinFeature() {
        return null;
    }

    @Override
    public ProteinInterproType ProteinInterpro() {
        return null;
    }

    @Override
    public ProteinKeggType ProteinKegg() {
        return null;
    }

    @Override
    public ProteinKeywordType ProteinKeyword() {
        return null;
    }


}
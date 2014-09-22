package com.bio4j.titan.model.uniprot;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.*;
import com.bio4j.model.uniprot_enzymedb.UniprotEnzymeDBGraph;
import com.bio4j.model.uniprot_go.UniprotGoGraph;
import com.bio4j.model.uniprot_ncbiTaxonomy.UniprotNCBITaxonomyGraph;
import com.bio4j.model.uniprot_uniref.UniprotUniRefGraph;
import com.bio4j.titan.model.uniprot_enzyme.TitanUniprotEnzymeGraph;
import com.bio4j.titan.model.uniprot_go.TitanUniprotGoGraph;
import com.bio4j.titan.model.uniprot_ncbiTaxonomy.TitanUniprotNCBITaxonomyGraph;
import com.bio4j.titan.model.uniprot_uniref.TitanUniprotUniRefGraph;
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

	private TitanUniprotGoGraph uniprotGoGraph;
	private TitanUniprotUniRefGraph uniprotUniRefGraph;
	private TitanUniprotNCBITaxonomyGraph uniprotNCBITaxonomyGraph;
	private TitanUniprotEnzymeGraph uniprotEnzymeGraph;


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

    //---Article---
    public TitanKey articleTypeKey;
    public TitanKey articleTitleKey;
    public TitanKey articleDoIdKey;
    public ArticleType articleType;
    //---Db---
    public TitanKey dbTypeKey;
    public TitanKey dbNameKey;
    public DBType dbType;
    //---Book---
    public TitanKey bookTypeKey;
    public TitanKey bookNameKey;
    public BookType bookType;
    //---city---
    public TitanKey cityTypeKey;
    public TitanKey cityNameKey;
    public CityType cityType;
	//---consortium---
	public TitanKey consortiumTypeKey;
	public TitanKey consortiumNameKey;
	public ConsortiumType consortiumType;
    //---country---
    public TitanKey countryTypeKey;
    public TitanKey countryNameKey;
    public CountryType countryType;
    //---dataset---
    public TitanKey datasetTypeKey;
    public TitanKey datasetNameKey;
    public DatasetType datasetType;
	//---disease---
	public TitanKey diseaseTypeKey;
	public TitanKey diseaseNameKey;
	public TitanKey diseaseIdKey;
	public TitanKey diseaseAcronymKey;
	public TitanKey diseaseDescriptionKey;
	public DiseaseType diseaseType;
    //---organism---
    public TitanKey organismTypeKey;
    public TitanKey organismScientificNameKey;
    public TitanKey organismCommonNameKey;
    public TitanKey organismSynonymNameKey;
    public OrganismType organismType;
    //---keyword---
    public TitanKey keywordTypeKey;
    public TitanKey keywordNameKey;
    public TitanKey keywordIdKey;
    public KeywordType keywordType;
    //---interpro---
    public TitanKey interproTypeKey;
    public TitanKey interproNameKey;
    public TitanKey interproIdKey;
    public InterproType interproType;
	//---interpro---
	public TitanKey isoformTypeKey;
	public TitanKey isoformNameKey;
	public TitanKey isoformIdKey;
	public TitanKey isoformSequenceKey;
	public TitanKey isoformNoteKey;
	public IsoformType isoformType;
    //----institute-----
    public TitanKey instituteTypeKey;
    public TitanKey instituteNameKey;
    public InstituteType instituteType;
    //---journal---
    public TitanKey journalTypeKey;
    public TitanKey journalNameKey;
    public JournalType journalType;
	//---person---
	public TitanKey personTypeKey;
	public TitanKey personNameKey;
	public PersonType personType;
    //---publisher---
    public TitanKey publisherTypeKey;
    public TitanKey publisherNameKey;
    public PublisherType publisherType;
    //---pubmed---
    public TitanKey pubmedTypeKey;
    public TitanKey pubmedIdKey;
    public PubmedType pubmedType;
    //---reactome term---
    public TitanKey reactomeTermTypeKey;
    public TitanKey reactomeTermPathwayNameKey;
    public TitanKey reactomeTermIdKey;
    public ReactomeTermType reactomeTermType;
    //---pfam---
    public TitanKey pfamTypeKey;
    public TitanKey pfamNameKey;
    public TitanKey pfamIdKey;
    public PfamType pfamType;
    //---kegg---
    public TitanKey keggTypeKey;
    public TitanKey keggIdKey;
    public KeggType keggType;
    //---EMBL---
    public TitanKey eMBLTypeKey;
    public TitanKey eMBLIdKey;
    public TitanKey eMBLMoleculeTypeKey;
    public TitanKey eMBLProteinSequenceIdKey;
    public EMBLType eMBLType;
    //---Patent---
    public TitanKey patentTypeKey;
    public TitanKey patentTitleKey;
    public TitanKey patentNumberKey;
    public PatentType patentType;
    //---PIR---
    public TitanKey pIRTypeKey;
    public TitanKey pIRIdKey;
    public TitanKey pIREntryNameKey;
    public PIRType pIRType;
    //---UniGene---
    public TitanKey uniGeneTypeKey;
    public TitanKey uniGeneIdKey;
    public UniGeneType uniGeneType;
    //---Ensembl---
    public TitanKey ensemblTypeKey;
    public TitanKey ensemblIdKey;
    public TitanKey ensemblMoleculeIdKey;
    public TitanKey ensemblProteinSequenceIdKey;
    public TitanKey ensemblGeneIdKey;
    public EnsemblType ensemblType;
    //---Taxon---
    public TitanKey taxonTypeKey;
    public TitanKey taxonNameKey;
    public TaxonType taxonType;
    //---Thesis---
    public TitanKey thesisTypeKey;
    public TitanKey thesisTitleKey;
    public ThesisType thesisType;
    //----OnlineArticle-----
    public TitanKey onlineArticleTypeKey;
    public TitanKey onlineArticleTitleKey;
    public OnlineArticleType onlineArticleType;
    //----OnlineJournal-----
    public TitanKey onlineJournalTypeKey;
    public TitanKey onlineJournalNameKey;
    public OnlineJournalType onlineJournalType;
    //---RefSeq---
    public TitanKey refSeqTypeKey;
    public TitanKey refSeqIdKey;
    public TitanKey refSeqNucleotideSequenceIdKey;
    public RefSeqType refSeqType;
    //---Reference---
    public TitanKey referenceDateKey;
    public ReferenceType referenceType;
	//---SequenceCaution----
	public TitanKey sequenceCautionTypeKey;
	public TitanKey sequenceCautionNameKey;
	public SequenceCautionType sequenceCautionType;
    //---SubcellularLocation----
    public TitanKey subcellularLocationTypeKey;
    public TitanKey subcellularLocationNameKey;
    public SubcellularLocationType subcellularLocationType;
    //---Submission----
    public TitanKey submissionTypeKey;
    public TitanKey submissionTitleKey;
    public SubmissionType submissionType;
    //---FeatureType---
    public TitanKey featureTypeTypeKey;
    public TitanKey featureTypeNameKey;
    public FeatureTypeType featureTypeType;
    //---CommentType---
    public TitanKey commentTypeTypeKey;
    public TitanKey commentTypeNameKey;
    public CommentTypeType commentTypeType;
    //---UnpublishedObservation----
    public TitanKey unpublishedObservationScopeKey;
    public UnpublishedObservationType unpublishedObservationType;


    //------------------INDICES----------------
    //-----------------------------------------
    //---------------INDICES---------------------------

    public TitanTypedVertexIndex.Unique<Protein<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, ProteinType, ProteinType.accession, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> proteinAccessionIndex;
    public TitanTypedVertexIndex.Unique<Dataset<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DatasetType, DatasetType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> datasetNameIndex;
    public TitanTypedVertexIndex.Unique<Organism<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, OrganismType, OrganismType.scientificName, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> organismScientificNameIndex;
    public TitanTypedVertexIndex.Unique<Keyword<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, KeywordType, KeywordType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> keywordIdIndex;
    public TitanTypedVertexIndex.Unique<ReactomeTerm<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, ReactomeTermType, ReactomeTermType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> reactomeTermIdIndex;
    public TitanTypedVertexIndex.Unique<Interpro<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, InterproType, InterproType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> interproIdIndex;
    public TitanTypedVertexIndex.Unique<Pfam<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, PfamType, PfamType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> pfamIdIndex;
    public TitanTypedVertexIndex.Unique<Kegg<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, KeggType, KeggType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> keggIdIndex;
    public TitanTypedVertexIndex.Unique<EMBL<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, EMBLType, EMBLType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> eMBLIdIndex;
    public TitanTypedVertexIndex.Unique<PIR<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, PIRType, PIRType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> pIRIdIndex;
    public TitanTypedVertexIndex.Unique<UniGene<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, UniGeneType, UniGeneType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> uniGeneIdIndex;
    public TitanTypedVertexIndex.Unique<Ensembl<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, EnsemblType, EnsemblType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> ensemblIdIndex;
    public TitanTypedVertexIndex.Unique<Taxon<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, TaxonType, TaxonType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> taxonNameIndex;
    public TitanTypedVertexIndex.Unique<RefSeq<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, RefSeqType, RefSeqType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> refSeqIdIndex;
    public TitanTypedVertexIndex.Unique<CommentType<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, CommentTypeType, CommentTypeType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> commentTypeNameIndex;
    public TitanTypedVertexIndex.Unique<FeatureType<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, FeatureTypeType, FeatureTypeType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> featureTypeNameIndex;
	public TitanTypedVertexIndex.Unique<Journal<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, JournalType, JournalType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> journalNameIndex;
	public TitanTypedVertexIndex.Unique<Article<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, ArticleType, ArticleType.title, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> articleTitleIndex;
	public TitanTypedVertexIndex.Unique<OnlineJournal<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, OnlineJournalType, OnlineJournalType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> onlineJournalNameIndex;
	public TitanTypedVertexIndex.Unique<OnlineArticle<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, OnlineArticleType, OnlineArticleType.title, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> onlineArticleTitleIndex;
	public TitanTypedVertexIndex.Unique<Pubmed<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, PubmedType, PubmedType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> pubmedIdIndex;
	public TitanTypedVertexIndex.Unique<Person<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, PersonType, PersonType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> personNameIndex;
	public TitanTypedVertexIndex.Unique<Thesis<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, ThesisType, ThesisType.title, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> thesisTitleIndex;
	public TitanTypedVertexIndex.Unique<Consortium<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, ConsortiumType, ConsortiumType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> consortiumNameIndex;
	public TitanTypedVertexIndex.Unique<Institute<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, InstituteType, InstituteType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> instituteNameIndex;
	public TitanTypedVertexIndex.Unique<Submission<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, SubmissionType, SubmissionType.title, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> submissionTitleIndex;
	public TitanTypedVertexIndex.Unique<Patent<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, PatentType, PatentType.number, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> patentNumberIndex;
	public TitanTypedVertexIndex.Unique<City<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, CityType, CityType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> cityNameIndex;
	public TitanTypedVertexIndex.Unique<Country<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, CountryType, CountryType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> countryNameIndex;
	public TitanTypedVertexIndex.Unique<Publisher<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, PublisherType, PublisherType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> publisherNameIndex;
	public TitanTypedVertexIndex.Unique<Book<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, BookType, BookType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> bookNameIndex;
	public TitanTypedVertexIndex.Unique<DB<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DBType, DBType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> dbNameIndex;
	public TitanTypedVertexIndex.Unique<Disease<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DiseaseType, DiseaseType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> diseaseIdIndex;
	public TitanTypedVertexIndex.Unique<SubcellularLocation<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, SubcellularLocationType, SubcellularLocationType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> subcellularLocationNameIndex;
	public TitanTypedVertexIndex.Unique<Isoform<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, IsoformType, IsoformType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> isoformIdIndex;
	public TitanTypedVertexIndex.Unique<SequenceCaution<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, SequenceCautionType, SequenceCautionType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph> sequenceCautionNameIndex;




	//-----------------------------------------------------------------------------------------
    //--------------------------------RELATIONSHIPS--------------------------------------------

    // proteinDataset
    public TitanLabel proteinDatasetLabel;
    public ProteinDatasetType proteinDatasetType;
    // proteinOrganism
    public TitanLabel proteinOrganismLabel;
    public ProteinOrganismType proteinOrganismType;
    // proteinKeyword
    public TitanLabel proteinKeywordLabel;
    public ProteinKeywordType proteinKeywordType;
    // proteinReactomeTerm
    public TitanLabel proteinReactomeTermLabel;
    public ProteinReactomeTermType proteinReactomeTermType;
    // proteinInterpro
    public TitanLabel proteinInterproLabel;
    public ProteinInterproType proteinInterproType;
    // proteinPfam
    public TitanLabel proteinPfamLabel;
    public ProteinPfamType proteinPfamType;
    // proteinKegg
    public TitanLabel proteinKeggLabel;
    public ProteinKeggType proteinKeggType;
    // proteinEMBL
    public TitanLabel proteinEMBLLabel;
    public ProteinEMBLType proteinEMBLType;
    // proteinPIR
    public TitanLabel proteinPIRLabel;
    public ProteinPIRType proteinPIRType;
    // proteinUniGene
    public TitanLabel proteinUniGeneLabel;
    public ProteinUniGeneType proteinUniGeneType;
    // proteinEnsembl
    public TitanLabel proteinEnsemblLabel;
    public ProteinEnsemblType proteinEnsemblType;
    // proteinRefSeq
    public TitanLabel proteinRefSeqLabel;
    public ProteinRefSeqType proteinRefSeqType;
    // proteinFeature
    public TitanLabel proteinFeatureLabel;
    public TitanKey proteinFeatureIdKey;
    public TitanKey proteinFeatureDescriptionKey;
    public TitanKey proteinFeatureEvidenceKey;
    public TitanKey proteinFeatureStatusKey;
    public TitanKey proteinFeatureBeginKey;
    public TitanKey proteinFeatureEndKey;
    public TitanKey proteinFeatureOriginalKey;
    public TitanKey proteinFeatureVariationKey;
    public TitanKey proteinFeatureRefKey;
    public ProteinFeatureType proteinFeatureType;
    // proteinComment
    public TitanLabel proteinCommentLabel;
    public ProteinCommentType proteinCommentType;
	public TitanKey proteinCommentTextKey;
	public TitanKey proteinCommentStatusKey;
	public TitanKey proteinCommentEvidenceKey;
	public TitanKey proteinCommentBeginKey;
	public TitanKey proteinCommentEndKey;
	public TitanKey proteinCommentMethodKey;
	public TitanKey proteinCommentMassKey;
	public TitanKey proteinCommentAbsorptionMaxKey;
	public TitanKey proteinCommentAbsorptionTextKey;
	public TitanKey proteinCommentKineticsXMLKey;
	public TitanKey proteinCommentPhDependenceKey;
	public TitanKey proteinCommentPositionKey;
	public TitanKey proteinCommentRedoxPotentialKey;
	public TitanKey proteinCommentRedoxPotentialEvidenceKey;
	public TitanKey proteinCommentTemperatureDependenceKey;

	// proteinDisease
	public TitanLabel proteinDiseaseLabel;
	public ProteinDiseaseType proteinDiseaseType;
	public TitanKey proteinDiseaseTextKey;
	public TitanKey proteinDiseaseStatusKey;
	public TitanKey proteinDiseaseEvidenceKey;
    // proteinReference
    public TitanLabel proteinReferenceLabel;
    public ProteinReferenceType proteinReferenceType;
    // proteinSubcellularLocation
    public TitanLabel proteinSubcellularLocationLabel;
    public ProteinSubcellularLocationType proteinSubcellularLocationType;
	public TitanKey proteinSubcellularLocationStatusKey;
	public TitanKey proteinSubcellularLocationEvidenceKey;
	public TitanKey proteinSubcellularLocationTopologyKey;
	public TitanKey proteinSubcellularLocationTopologyStatusKey;
	// proteinSequenceCaution
	public TitanLabel proteinSequenceCautionLabel;
	public ProteinSequenceCautionType proteinSequenceCautionType;
	public TitanKey proteinSequenceCautionIdKey;
	public TitanKey proteinSequenceCautionEvidenceKey;
	public TitanKey proteinSequenceCautionStatusKey;
	public TitanKey proteinSequenceCautionTextKey;
	public TitanKey proteinSequenceCautionResourceKey;
	public TitanKey proteinSequenceCautionVersionKey;
	public TitanKey proteinSequenceCautionPositionKey;

    // articlePubmed
    public TitanLabel articlePubmedLabel;
    public ArticlePubmedType articlePubmedType;
    // articleJournal
    public TitanLabel articleJournalLabel;
    public ArticleJournalType articleJournalType;
	public TitanKey articleJournalVolumeKey;
	public TitanKey articleJournalFirstKey;
	public TitanKey articleJournalLastKey;
    // bookCity
    public TitanLabel bookCityLabel;
    public BookCityType bookCityType;
	// bookEditor
	public TitanLabel bookEditorLabel;
	public BookEditorType bookEditorType;
    // bookPublisher
    public TitanLabel bookPublisherLabel;
    public BookPublisherType bookPublisherType;
    // instituteCountry
    public TitanLabel instituteCountryLabel;
    public InstituteCountryType instituteCountryType;
    // taxonParent
    public TitanLabel taxonParentLabel;
    public TaxonParentType taxonParentType;
    // organismTaxon
    public TitanLabel organismTaxonLabel;
    public OrganismTaxonType organismTaxonType;
    // onlineArticleOnlineJournal
    public TitanLabel onlineArticleOnlineJournalLabel;
    public OnlineArticleOnlineJournalType onlineArticleOnlineJournalType;
	public TitanKey onlineArticleOnlineJournalLocatorKey;
	// referenceAuthorPerson
	public TitanLabel referenceAuthorPersonLabel;
	public ReferenceAuthorPersonType referenceAuthorPersonType;
	// referenceAuthorConsortium
	public TitanLabel referenceAuthorConsortiumLabel;
	public ReferenceAuthorConsortiumType referenceAuthorConsortiumType;
    // referenceArticle
    public TitanLabel referenceArticleLabel;
    public ReferenceArticleType referenceArticleType;
    // referenceBook
    public TitanLabel referenceBookLabel;
    public ReferenceBookType referenceBookType;
    // referenceThesis
    public TitanLabel referenceThesisLabel;
    public ReferenceThesisType referenceThesisType;
    // referenceSubmission
    public TitanLabel referenceSubmissionLabel;
    public ReferenceSubmissionType referenceSubmissionType;
    // referencePatent
    public TitanLabel referencePatentLabel;
    public ReferencePatentType referencePatentType;
    // referenceOnlineArticle
    public TitanLabel referenceOnlineArticleLabel;
    public ReferenceOnlineArticleType referenceOnlineArticleType;
    // referenceUnpublishedObservation
    public TitanLabel referenceUnpublishedObservationLabel;
    public ReferenceUnpublishedObservationType referenceUnpublishedObservationType;
    // thesisInstitute
    public TitanLabel thesisInstituteLabel;
    public ThesisInstituteType thesisInstituteType;
    // submissionDB
    public TitanLabel submissionDBLabel;
    public SubmissionDBType submissionDBType;
    // subcellularLocation
    public TitanLabel subcellularLocationParentLabel;
    public SubcellularLocationParentType subcellularLocationParentType;



    public TitanUniprotGraph(DefaultTitanGraph rawGraph) {
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


        // Protein keys
	    proteinType = new ProteinType(proteinTypekey);
        proteinTypekey = raw().titanKeyForVertexType(proteinType.accession);
        proteinAcessionKey = proteinTypekey;
        proteinNameKey = raw().titanKeyForVertexPropertySingle(proteinType.name);
        proteinShortNameKey = raw().titanKeyForVertexPropertySingle(proteinType.shortName);
        proteinFullNameKey = raw().titanKeyForVertexPropertySingle(proteinType.fullName);
        proteinModifiedDateKey = raw().titanKeyForVertexPropertySingle(proteinType.modifiedDate);
        proteinCreatedDateKey = raw().titanKeyForVertexPropertySingle(proteinType.createdDate);
        proteinMassKey = raw().titanKeyForVertexPropertySingle(proteinType.mass);
        proteinVersionKey = raw().titanKeyForVertexPropertySingle(proteinType.version);
        proteinLengthKey = raw().titanKeyForVertexPropertySingle(proteinType.length);
        proteinSequenceKey = raw().titanKeyForVertexPropertySingle(proteinType.sequence);

        // Article keys
	    articleType = new ArticleType(articleTypeKey);
        articleTypeKey = raw().titanKeyForVertexType(articleType.title);
        articleTitleKey = articleTypeKey;
        articleDoIdKey = raw().titanKeyForVertexPropertySingle(articleType.doId);

        // Book keys
	    bookType = new BookType(bookTypeKey);
        bookTypeKey = raw().titanKeyForVertexType(bookType.name);
        bookNameKey = bookTypeKey;

        // City keys
	    cityType = new CityType(cityTypeKey);
	    cityTypeKey = raw().titanKeyForVertexType(cityType.name);
	    cityNameKey = cityTypeKey;

	    // Consortium keys
	    consortiumType = new ConsortiumType(consortiumTypeKey);
	    consortiumTypeKey = raw().titanKeyForVertexType(consortiumType.name);
	    consortiumNameKey = consortiumTypeKey;

        // Country keys
	    countryType = new CountryType(countryTypeKey);
        countryTypeKey = raw().titanKeyForVertexType(countryType.name);
        countryNameKey = countryTypeKey;

        // DB keys
	    dbType = new DBType(dbTypeKey);
        dbTypeKey = raw().titanKeyForVertexType(dbType.name);
        dbNameKey = dbTypeKey;

        // Dataset keys
	    datasetType = new DatasetType(datasetTypeKey);
        datasetTypeKey = raw().titanKeyForVertexType(datasetType.name);
        datasetNameKey = datasetTypeKey;

	    // Disease keys
	    diseaseType = new DiseaseType(diseaseTypeKey);
	    diseaseTypeKey = raw().titanKeyForVertexType(diseaseType.id);
	    diseaseIdKey = diseaseTypeKey;
	    diseaseNameKey = raw().titanKeyForVertexPropertySingle(diseaseType.name);
	    diseaseAcronymKey = raw().titanKeyForVertexPropertySingle(diseaseType.acronym);
	    diseaseDescriptionKey = raw().titanKeyForVertexPropertySingle(diseaseType.description);

        // Institute keys
	    instituteType = new InstituteType(instituteTypeKey);
        instituteTypeKey = raw().titanKeyForVertexType(instituteType.name);
        instituteNameKey = instituteTypeKey;

        // Organism keys
	    organismType = new OrganismType(organismTypeKey);
        organismTypeKey = raw().titanKeyForVertexType(organismType.scientificName);
        organismScientificNameKey = organismTypeKey;
        organismCommonNameKey = raw().titanKeyForVertexPropertySingle(organismType.commonName);
        organismSynonymNameKey = raw().titanKeyForVertexPropertySingle(organismType.synonymName);

        // Keyword keys
	    keywordType = new KeywordType(keywordTypeKey);
        keywordTypeKey = raw().titanKeyForVertexType(keywordType.id);
        keywordIdKey = keywordTypeKey;
        keywordNameKey = raw().titanKeyForVertexPropertySingle(keywordType.name);

        // Interpro keys
	    interproType = new InterproType(interproTypeKey);
        interproTypeKey = raw().titanKeyForVertexType(interproType.id);
        interproIdKey = interproTypeKey;
        interproNameKey = raw().titanKeyForVertexPropertySingle(interproType.name);

	    // Isoform keys
	    isoformType = new IsoformType(isoformTypeKey);
	    isoformTypeKey = raw().titanKeyForVertexType(isoformType.id);
	    isoformIdKey = isoformTypeKey;
	    isoformNameKey = raw().titanKeyForVertexPropertySingle(isoformType.name);
	    isoformSequenceKey = raw().titanKeyForVertexPropertySingle(isoformType.sequence);
	    isoformNoteKey = raw().titanKeyForVertexPropertySingle(isoformType.note);

        // Journal keys
	    journalType = new JournalType(journalTypeKey);
        journalTypeKey = raw().titanKeyForVertexType(journalType.name);
        journalNameKey = journalTypeKey;

        // OnlineArticle keys
	    onlineArticleType = new OnlineArticleType(onlineArticleTypeKey);
        onlineArticleTypeKey = raw().titanKeyForVertexType(onlineArticleType.title);
        onlineArticleTitleKey = onlineArticleTypeKey;

        // OnlineJournal keys
	    onlineJournalType = new OnlineJournalType(onlineJournalTypeKey);
        onlineJournalTypeKey = raw().titanKeyForVertexType(onlineJournalType.name);
        onlineJournalNameKey = onlineJournalTypeKey;

	    // Reference keys
	    referenceType = new ReferenceType(null);
	    referenceDateKey = raw().titanKeyForVertexPropertySingle(referenceType.date);


        // ReactomeTerm keys
	    reactomeTermType = new ReactomeTermType(reactomeTermTypeKey);
        reactomeTermTypeKey = raw().titanKeyForVertexType(reactomeTermType.id);
        reactomeTermIdKey = reactomeTermTypeKey;
        reactomeTermPathwayNameKey = raw().titanKeyForVertexPropertySingle(reactomeTermType.pathwayName);

        // Publisher keys
	    publisherType = new PublisherType(publisherTypeKey);
        publisherTypeKey = raw().titanKeyForVertexType(publisherType.name);
        publisherNameKey = publisherTypeKey;

	    // Person keys
	    personType = new PersonType(personTypeKey);
	    personTypeKey = raw().titanKeyForVertexType(personType.name);
	    personNameKey = personTypeKey;

        // Pfam keys
	    pfamType = new PfamType(pfamTypeKey);
        pfamTypeKey = raw().titanKeyForVertexType(pfamType.id);
        pfamIdKey = pfamTypeKey;
        pfamNameKey = raw().titanKeyForVertexPropertySingle(pfamType.name);

        // Pubmed keys
	    pubmedType = new PubmedType(pubmedTypeKey);
        pubmedTypeKey = raw().titanKeyForVertexType(pubmedType.id);
        pubmedIdKey = pubmedTypeKey;

        // Submission keys
	    submissionType = new SubmissionType(submissionTypeKey);
        submissionTypeKey = raw().titanKeyForVertexType(submissionType.title);
        submissionTitleKey = submissionTypeKey;

	    // Thesis keys
	    thesisType = new ThesisType(thesisTypeKey);
	    thesisTypeKey = raw().titanKeyForVertexType(thesisType.title);
	    thesisTitleKey = thesisTypeKey;

        // EMBL keys
	    eMBLType = new EMBLType(eMBLTypeKey);
        eMBLTypeKey = raw().titanKeyForVertexType(eMBLType.id);
        eMBLIdKey = eMBLTypeKey;
        eMBLMoleculeTypeKey = raw().titanKeyForVertexPropertySingle(eMBLType.moleculeType);
        eMBLProteinSequenceIdKey = raw().titanKeyForVertexPropertySingle(eMBLType.proteinSequenceId);

        // PIR keys
	    pIRType = new PIRType(pIRTypeKey);
        pIRTypeKey = raw().titanKeyForVertexType(pIRType.id);
        pIRIdKey = pIRTypeKey;
        pIREntryNameKey = raw().titanKeyForVertexPropertySingle(pIRType.entryName);

        // Patent keys
	    patentType = new PatentType(patentTypeKey);
        patentTypeKey = raw().titanKeyForVertexType(patentType.number);
        patentNumberKey = patentTypeKey;
        patentTitleKey = raw().titanKeyForVertexPropertySingle(patentType.title);

        // Ensembl keys
	    ensemblType = new EnsemblType(ensemblTypeKey);
        ensemblTypeKey = raw().titanKeyForVertexType(ensemblType.id);
        ensemblIdKey = ensemblTypeKey;
        ensemblMoleculeIdKey = raw().titanKeyForVertexPropertySingle(ensemblType.moleculeId);
        ensemblProteinSequenceIdKey = raw().titanKeyForVertexPropertySingle(ensemblType.proteinSequenceId);
        ensemblGeneIdKey = raw().titanKeyForVertexPropertySingle(ensemblType.geneId);

        //---UniGene---
	    uniGeneType = new UniGeneType(uniGeneTypeKey);
        uniGeneTypeKey = raw().titanKeyForVertexType(uniGeneType.id);
        uniGeneIdKey = uniGeneTypeKey;

        //---SubcellularLocation---
	    subcellularLocationType = new SubcellularLocationType(subcellularLocationTypeKey);
        subcellularLocationTypeKey = raw().titanKeyForVertexType(subcellularLocationType.name);
        subcellularLocationNameKey = subcellularLocationTypeKey;

        //---Kegg---
	    keggType = new KeggType(keggTypeKey);
        keggTypeKey = raw().titanKeyForVertexType(keggType.id);
        keggIdKey = keggTypeKey;

        //---Taxon---
	    taxonType = new TaxonType(taxonTypeKey);
        taxonTypeKey = raw().titanKeyForVertexType(taxonType.name);
        taxonNameKey = taxonTypeKey;

        //---RefSeq---
	    refSeqType = new RefSeqType(refSeqTypeKey);
        refSeqTypeKey = raw().titanKeyForVertexType(refSeqType.id);
        refSeqIdKey = refSeqTypeKey;
        refSeqNucleotideSequenceIdKey = raw().titanKeyForVertexPropertySingle(refSeqType.nucleotideSequenceId);

	    //---SequenceCaution---
	    sequenceCautionType = new SequenceCautionType(sequenceCautionTypeKey);
	    sequenceCautionTypeKey = raw().titanKeyForVertexType(sequenceCautionType.name);
	    sequenceCautionNameKey = sequenceCautionTypeKey;

        //---Comment---
	    commentTypeType = new CommentTypeType(commentTypeTypeKey);
        commentTypeTypeKey = raw().titanKeyForVertexType(commentTypeType.name);
        commentTypeNameKey = commentTypeTypeKey;

        //---Feature---
	    featureTypeType = new FeatureTypeType(featureTypeTypeKey);
        featureTypeTypeKey = raw().titanKeyForVertexType(featureTypeType.name);
        featureTypeNameKey = featureTypeTypeKey;

        //---UnpublishedObservation
        unpublishedObservationType = new UnpublishedObservationType(null);
	    unpublishedObservationScopeKey = raw().titanKeyForVertexType(unpublishedObservationType.scope);

        //-----------------------------------------------------------------------------------------
        //--------------------------------EDGES--------------------------------------------

        // articlePubmed
        articlePubmedLabel = raw().titanLabelForEdgeType(this.new ArticlePubmedType(null));
        articlePubmedType = new ArticlePubmedType(articlePubmedLabel);

        // articleJournal
        articleJournalLabel = raw().titanLabelForEdgeType(this.new ArticleJournalType(null));
        articleJournalType = new ArticleJournalType(articleJournalLabel);
	    articleJournalVolumeKey = raw().titanKeyForEdgePropertySingle(articleJournalType.volume);
	    articleJournalFirstKey = raw().titanKeyForEdgePropertySingle(articleJournalType.first);
	    articleJournalLastKey = raw().titanKeyForEdgePropertySingle(articleJournalType.last);

        // bookCity
        bookCityLabel = raw().titanLabelForEdgeType(this.new BookCityType(null));
        bookCityType = new BookCityType(bookCityLabel);

	    // bookEditor
	    bookEditorLabel = raw().titanLabelForEdgeType(this.new BookEditorType(null));
	    bookEditorType = new BookEditorType(bookEditorLabel);

        // bookPublisher
        bookPublisherLabel = raw().titanLabelForEdgeType(this.new BookPublisherType(null));
        bookPublisherType = new BookPublisherType(bookPublisherLabel);

        // instituteCountry
        instituteCountryLabel = raw().titanLabelForEdgeType(this.new InstituteCountryType(null));
        instituteCountryType = new InstituteCountryType(instituteCountryLabel);

        // onlineArticleOnlineJournal
        onlineArticleOnlineJournalLabel = raw().titanLabelForEdgeType(this.new OnlineArticleOnlineJournalType(null));
        onlineArticleOnlineJournalType = new OnlineArticleOnlineJournalType(onlineArticleOnlineJournalLabel);
	    onlineArticleOnlineJournalLocatorKey = raw().titanKeyForEdgePropertySingle(onlineArticleOnlineJournalType.locator);

        // proteinDataset
        proteinDatasetLabel = raw().titanLabelForEdgeType(this.new ProteinDatasetType(null));
        proteinDatasetType = new ProteinDatasetType(proteinDatasetLabel);

	    // proteinDisease
	    proteinDiseaseLabel = raw().titanLabelForEdgeType(this.new ProteinDiseaseType(null));
	    proteinDiseaseType = new ProteinDiseaseType(proteinDiseaseLabel);
	    proteinDiseaseTextKey = raw().titanKeyForEdgePropertySingle(proteinDiseaseType.text);
	    proteinDiseaseStatusKey = raw().titanKeyForEdgePropertySingle(proteinDiseaseType.status);
	    proteinDiseaseEvidenceKey = raw().titanKeyForEdgePropertySingle(proteinDiseaseType.evidence);

        // proteinOrganism
        proteinOrganismLabel = raw().titanLabelForEdgeType(this.new ProteinOrganismType(null));
        proteinOrganismType = new ProteinOrganismType(proteinOrganismLabel);

        // proteinKeyword
        proteinKeywordLabel = raw().titanLabelForEdgeType(this.new ProteinKeywordType(null));
        proteinKeywordType = new ProteinKeywordType(proteinKeywordLabel);

        // proteinInterpro
        proteinInterproLabel = raw().titanLabelForEdgeType(this.new ProteinInterproType(null));
        proteinInterproType = new ProteinInterproType(proteinInterproLabel);

        // proteinReactomeTerm
        proteinReactomeTermLabel = raw().titanLabelForEdgeType(this.new ProteinReactomeTermType(null));
        proteinReactomeTermType = new ProteinReactomeTermType(proteinReactomeTermLabel);

        // proteinPfam
        proteinPfamLabel = raw().titanLabelForEdgeType(this.new ProteinPfamType(null));
        proteinPfamType = new ProteinPfamType(proteinPfamLabel);

        // proteinKegg
        proteinKeggLabel = raw().titanLabelForEdgeType(this.new ProteinKeggType(null));
        proteinKeggType = new ProteinKeggType(proteinKeggLabel);

        // proteinKegg
        proteinPIRLabel = raw().titanLabelForEdgeType(this.new ProteinPIRType(null));
        proteinPIRType = new ProteinPIRType(proteinPIRLabel);

        // proteinEMBL
        proteinEMBLLabel = raw().titanLabelForEdgeType(this.new ProteinEMBLType(null));
        proteinEMBLType = new ProteinEMBLType(proteinEMBLLabel);

        // proteinEnsembl
        proteinEnsemblLabel = raw().titanLabelForEdgeType(this.new ProteinEnsemblType(null));
        proteinEnsemblType = new ProteinEnsemblType(proteinEnsemblLabel);

        // proteinRefSeq
        proteinRefSeqLabel = raw().titanLabelForEdgeType(this.new ProteinRefSeqType(null));
        proteinRefSeqType = new ProteinRefSeqType(proteinRefSeqLabel);

	    // proteinSequenceCaution
	    proteinSequenceCautionLabel = raw().titanLabelForEdgeType(this.new ProteinSequenceCautionType(null));
	    proteinSequenceCautionType = new ProteinSequenceCautionType(proteinSequenceCautionLabel);
	    proteinSequenceCautionStatusKey = raw().titanKeyForEdgePropertySingle(proteinSequenceCautionType.status);
	    proteinSequenceCautionIdKey = raw().titanKeyForEdgePropertySingle(proteinSequenceCautionType.id);
	    proteinSequenceCautionEvidenceKey = raw().titanKeyForEdgePropertySingle(proteinSequenceCautionType.evidence);
	    proteinSequenceCautionVersionKey = raw().titanKeyForEdgePropertySingle(proteinSequenceCautionType.version);
	    proteinSequenceCautionPositionKey = raw().titanKeyForEdgePropertySingle(proteinSequenceCautionType.position);
	    proteinSequenceCautionResourceKey = raw().titanKeyForEdgePropertySingle(proteinSequenceCautionType.resource);
	    proteinSequenceCautionTextKey = raw().titanKeyForEdgePropertySingle(proteinSequenceCautionType.text);

	    // proteinSubcellularLocation
	    proteinSubcellularLocationLabel = raw().titanLabelForEdgeType(this.new ProteinSubcellularLocationType(null));
	    proteinSubcellularLocationType = new ProteinSubcellularLocationType(proteinSubcellularLocationLabel);
	    proteinSubcellularLocationStatusKey = raw().titanKeyForEdgePropertySingle(proteinSubcellularLocationType.status);
	    proteinSubcellularLocationEvidenceKey = raw().titanKeyForEdgePropertySingle(proteinSubcellularLocationType.evidence);
	    proteinSubcellularLocationTopologyKey = raw().titanKeyForEdgePropertySingle(proteinSubcellularLocationType.topology);
	    proteinSubcellularLocationTopologyStatusKey = raw().titanKeyForEdgePropertySingle(proteinSubcellularLocationType.topologyStatus);

        // proteinUnigene
        proteinUniGeneLabel = raw().titanLabelForEdgeType(this.new ProteinUniGeneType(null));
        proteinUniGeneType = new ProteinUniGeneType(proteinUniGeneLabel);

        // taxonParent
        taxonParentLabel = raw().titanLabelForEdgeType(this.new TaxonParentType(null));
        taxonParentType = new TaxonParentType(taxonParentLabel);

        // organismTaxon
        organismTaxonLabel = raw().titanLabelForEdgeType(this.new OrganismTaxonType(null));
        organismTaxonType = new OrganismTaxonType(organismTaxonLabel);

        // proteinComment
        proteinCommentLabel = raw().titanLabelForEdgeType(this.new ProteinCommentType(null));
        proteinCommentType = new ProteinCommentType(proteinCommentLabel);
	    proteinCommentEvidenceKey = raw().titanKeyForEdgePropertySingle(proteinCommentType.evidence);
	    proteinCommentStatusKey = raw().titanKeyForEdgePropertySingle(proteinCommentType.status);
	    proteinCommentTextKey = raw().titanKeyForEdgePropertySingle(proteinCommentType.text);
	    proteinCommentBeginKey = raw().titanKeyForEdgePropertySingle(proteinCommentType.begin);
	    proteinCommentEndKey = raw().titanKeyForEdgePropertySingle(proteinCommentType.end);
	    proteinCommentMassKey = raw().titanKeyForEdgePropertySingle(proteinCommentType.mass);
	    proteinCommentMethodKey = raw().titanKeyForEdgePropertySingle(proteinCommentType.method);
	    proteinCommentAbsorptionMaxKey = raw().titanKeyForEdgePropertySingle(proteinCommentType.absorptionMax);
	    proteinCommentAbsorptionTextKey = raw().titanKeyForEdgePropertySingle(proteinCommentType.absorptionText);
	    proteinCommentKineticsXMLKey = raw().titanKeyForEdgePropertySingle(proteinCommentType.kineticsXML);
	    proteinCommentPhDependenceKey = raw().titanKeyForEdgePropertySingle(proteinCommentType.phDependence);
	    proteinCommentPositionKey = raw().titanKeyForEdgePropertySingle(proteinCommentType.position);
	    proteinCommentRedoxPotentialKey = raw().titanKeyForEdgePropertySingle(proteinCommentType.redoxPotential);
	    proteinCommentRedoxPotentialEvidenceKey = raw().titanKeyForEdgePropertySingle(proteinCommentType.redoxPotentialEvidence);
	    proteinCommentTemperatureDependenceKey = raw().titanKeyForEdgePropertySingle(proteinCommentType.temperatureDependence);

        // proteinFeature
        proteinFeatureLabel = raw().titanLabelForEdgeType(this.new ProteinFeatureType(null));
	    proteinFeatureType = new ProteinFeatureType(proteinFeatureLabel);
        proteinFeatureIdKey = raw().titanKeyForEdgePropertySingle(proteinFeatureType.id);
        proteinFeatureDescriptionKey = raw().titanKeyForEdgePropertySingle(proteinFeatureType.description);
        proteinFeatureEvidenceKey = raw().titanKeyForEdgePropertySingle(proteinFeatureType.evidence);
        proteinFeatureStatusKey = raw().titanKeyForEdgePropertySingle(proteinFeatureType.status);
        proteinFeatureBeginKey = raw().titanKeyForEdgePropertySingle(proteinFeatureType.begin);
        proteinFeatureEndKey = raw().titanKeyForEdgePropertySingle(proteinFeatureType.end);
        proteinFeatureOriginalKey = raw().titanKeyForEdgePropertySingle(proteinFeatureType.original);
        proteinFeatureVariationKey = raw().titanKeyForEdgePropertySingle(proteinFeatureType.variation);
        proteinFeatureRefKey = raw().titanKeyForEdgePropertySingle(proteinFeatureType.ref);

        proteinOrganismLabel = raw().titanLabelForEdgeType(this.new ProteinOrganismType(null));
        proteinOrganismType = new ProteinOrganismType(proteinOrganismLabel);

        proteinReferenceLabel = raw().titanLabelForEdgeType(this.new ProteinReferenceType(null));
        proteinReferenceType = new ProteinReferenceType(proteinReferenceLabel);

        // referenceArticle
        referenceArticleLabel = raw().titanLabelForEdgeType(this.new ReferenceArticleType(null));
        referenceArticleType = new ReferenceArticleType(referenceArticleLabel);

	    // referenceAuthorPerson
	    referenceAuthorPersonLabel = raw().titanLabelForEdgeType(this.new ReferenceAuthorPersonType(null));
	    referenceAuthorPersonType = new ReferenceAuthorPersonType(referenceAuthorPersonLabel);

	    // referenceAuthorConsortium
	    referenceAuthorConsortiumLabel = raw().titanLabelForEdgeType(this.new ReferenceAuthorConsortiumType(null));
	    referenceAuthorConsortiumType = new ReferenceAuthorConsortiumType(referenceAuthorConsortiumLabel);

        // referenceThesis
        referenceThesisLabel = raw().titanLabelForEdgeType(this.new ReferenceThesisType(null));
        referenceThesisType = new ReferenceThesisType(referenceThesisLabel);

        // referenceSubmission
        referenceSubmissionLabel = raw().titanLabelForEdgeType(this.new ReferenceSubmissionType(null));
        referenceSubmissionType = new ReferenceSubmissionType(referenceSubmissionLabel);

        // referenceOnlineArticle
        referenceOnlineArticleLabel = raw().titanLabelForEdgeType(this.new ReferenceOnlineArticleType(null));
        referenceOnlineArticleType = new ReferenceOnlineArticleType(referenceOnlineArticleLabel);

        // referencePatent
        referencePatentLabel = raw().titanLabelForEdgeType(this.new ReferencePatentType(null));
        referencePatentType = new ReferencePatentType(referencePatentLabel);

        // referenceBook
        referenceBookLabel = raw().titanLabelForEdgeType(this.new ReferenceBookType(null));
        referenceBookType = new ReferenceBookType(referenceBookLabel);

        // referenceUnpublishedObservation
        referenceUnpublishedObservationLabel = raw().titanLabelForEdgeType(this.new ReferenceUnpublishedObservationType(null));
        referenceUnpublishedObservationType = new ReferenceUnpublishedObservationType(referenceUnpublishedObservationLabel);

        // thesisInstitute
        thesisInstituteLabel = raw().titanLabelForEdgeType(this.new ThesisInstituteType(null));
        thesisInstituteType = new ThesisInstituteType(thesisInstituteLabel);

        // subcellularLocationParent
        subcellularLocationParentLabel = raw().titanLabelForEdgeType(this.new SubcellularLocationParentType(null));
        subcellularLocationParentType = new SubcellularLocationParentType(subcellularLocationParentLabel);

	    // submissionDB
	    submissionDBLabel = raw().titanLabelForEdgeType(this.new SubmissionDBType(null));
	    submissionDBType = new SubmissionDBType(submissionDBLabel);



    }

    private void initIndices() {

        proteinAccessionIndex =  new TitanTypedVertexIndex.DefaultUnique<>(this, Protein().accession);
        datasetNameIndex =  new TitanTypedVertexIndex.DefaultUnique<>(this, Dataset().name);
        organismScientificNameIndex =  new TitanTypedVertexIndex.DefaultUnique<>(this, Organism().scientificName);
        keywordIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Keyword().id);
        interproIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Interpro().id);
        reactomeTermIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, ReactomeTerm().id);
        pfamIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Pfam().id);
        keggIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Kegg().id);
        eMBLIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, EMBL().id);
        pIRIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, PIR().id);
        uniGeneIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, UniGene().id);
        ensemblIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Ensembl().id);
        taxonNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Taxon().name);
        refSeqIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, RefSeq().id);
        commentTypeNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, CommentType().name);
        featureTypeNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, FeatureType().name);
	    consortiumNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Consortium().name);
	    personNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Person().name);
	    patentNumberIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Patent().number);
	    submissionTitleIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Submission().title);
	    instituteNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Institute().name);
	    thesisTitleIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Thesis().title);
	    onlineArticleTitleIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, OnlineArticle().title);
	    onlineJournalNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, OnlineJournal().name);
	    pubmedIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Pubmed().id);
	    cityNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, City().name);
	    countryNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Country().name);
	    publisherNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Publisher().name);
	    bookNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Book().name);
	    dbNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, DB().name);
	    articleTitleIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Article().title);
	    journalNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Journal().name);
	    diseaseIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Disease().id);
	    subcellularLocationNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, SubcellularLocation().name);
	    isoformIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Isoform().id);

    }

    @Override
    public ProteinOrganismType ProteinOrganism() {
        return proteinOrganismType;
    }

    @Override
    public ProteinPfamType ProteinPfam() {
        return proteinPfamType;
    }

    @Override
    public ProteinPIRType ProteinPIR() {
        return proteinPIRType;
    }

    @Override
    public ProteinReactomeTermType ProteinReactomeTerm() {
        return proteinReactomeTermType;
    }

    @Override
    public ProteinSubcellularLocationType ProteinSubcellularLocation() {
        return proteinSubcellularLocationType;
    }

    @Override
    public ProteinUniGeneType ProteinUniGene() {
        return proteinUniGeneType;
    }

    @Override
    public ProteinRefSeqType ProteinRefSeq() {
        return proteinRefSeqType;
    }

    @Override
    public ProteinReferenceType ProteinReference() {
        return proteinReferenceType;
    }

	@Override
	public ProteinSequenceCautionType ProteinSequenceCaution() {
		return proteinSequenceCautionType;
	}

	@Override
	public ReferenceAuthorPersonType ReferenceAuthorPerson() {
		return referenceAuthorPersonType;
	}

	@Override
	public ReferenceAuthorConsortiumType ReferenceAuthorConsortium() {
		return referenceAuthorConsortiumType;
	}

	@Override
    public ReferenceArticleType ReferenceArticle() {
        return referenceArticleType;
    }

    @Override
    public ReferenceBookType ReferenceBook() {
        return referenceBookType;
    }

    @Override
    public ReferenceOnlineArticleType ReferenceOnlineArticle() {
        return referenceOnlineArticleType;
    }

    @Override
    public ReferencePatentType ReferencePatent() {
        return referencePatentType;
    }

    @Override
    public ReferenceThesisType ReferenceThesis() {
        return referenceThesisType;
    }

    @Override
    public ReferenceSubmissionType ReferenceSubmission() {
        return referenceSubmissionType;
    }

    @Override
    public ReferenceUnpublishedObservationType ReferenceUnpublishedObservation() {
        return referenceUnpublishedObservationType;
    }

    @Override
    public SubmissionDBType SubmissionDB() {
        return submissionDBType;
    }

    @Override
    public SubcellularLocationParentType SubcellularLocationParent() {
        return subcellularLocationParentType;
    }

    @Override
    public TaxonParentType TaxonParent() {
        return taxonParentType;
    }

    @Override
    public ThesisInstituteType ThesisInstitute() {
        return thesisInstituteType;
    }


    @Override
    public UniprotUniRefGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> uniprotUniRefGraph() {
        return uniprotUniRefGraph;
    }

    @Override
    public UniprotGoGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> uniprotGoGraph() {
        return uniprotGoGraph;
    }

    @Override
    public UniprotEnzymeDBGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> uniprotEnzymeDBGraph() {
        return uniprotEnzymeGraph;
    }

    @Override
    public UniprotNCBITaxonomyGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> uniprotNCBITaxonomyGraph() {
        return uniprotNCBITaxonomyGraph;
    }

	@Override
	public TypedVertexIndex.Unique<Disease<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DiseaseType, DiseaseType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> diseaseIdIndex() {
		return diseaseIdIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Journal<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, JournalType, JournalType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> journalNameIndex() {
		return journalNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Article<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, ArticleType, ArticleType.title, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> articleTitleIndex() {
		return articleTitleIndex;
	}

	@Override
	public TypedVertexIndex.Unique<OnlineJournal<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, OnlineJournalType, OnlineJournalType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> onlineJournalNameIndex() {
		return onlineJournalNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<OnlineArticle<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, OnlineArticleType, OnlineArticleType.title, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> onlineArticleTitleIndex() {
		return onlineArticleTitleIndex;
	}

	@Override
	public TypedVertexIndex.Unique<City<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, CityType, CityType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> cityNameIndex() {
		return cityNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Publisher<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, PublisherType, PublisherType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> publisherNameIndex() {
		return publisherNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Book<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, BookType, BookType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> bookNameIndex() {
		return bookNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<DB<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DBType, DBType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> dbNameIndex() {
		return dbNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Country<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, CountryType, CountryType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> countryNameIndex() {
		return countryNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Patent<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, PatentType, PatentType.number, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> patentNumberIndex() {
		return patentNumberIndex;
	}

	@Override
	public TypedVertexIndex.Unique<SequenceCaution<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, SequenceCautionType, SequenceCautionType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> sequenceCautionNameIndex() {
		return sequenceCautionNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Submission<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, SubmissionType, SubmissionType.title, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> submissionTitleIndex() {
		return submissionTitleIndex;
	}

	@Override
	public TypedVertexIndex.Unique<SubcellularLocation<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, SubcellularLocationType, SubcellularLocationType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> subcellularLocationNameIndex() {
		return subcellularLocationNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Institute<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, InstituteType, InstituteType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> instituteNameIndex() {
		return instituteNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Isoform<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, IsoformType, IsoformType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> isoformIdIndex() {
		return isoformIdIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Consortium<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, ConsortiumType, ConsortiumType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> consortiumNameIndex() {
		return consortiumNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Thesis<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, ThesisType, ThesisType.title, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> thesisTitleIndex() {
		return thesisTitleIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Person<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, PersonType, PersonType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> personNameIndex() {
		return personNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Protein<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, ProteinType, ProteinType.accession, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> proteinAccessionIndex() {
		return proteinAccessionIndex;
	}

	@Override
    public TypedVertexIndex.Unique<Ensembl<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>,
            EnsemblType, EnsemblType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> ensemblIdIndex() {
        return ensemblIdIndex;
    }

    @Override
    public TypedVertexIndex.Unique<PIR<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>,
            PIRType, PIRType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> pIRIdIndex() {
        return pIRIdIndex;
    }

	@Override
	public TypedVertexIndex.Unique<Pubmed<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, PubmedType, PubmedType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> pubmedIdIndex() {
		return pubmedIdIndex;
	}

	@Override
    public TypedVertexIndex.Unique<UniGene<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>,
            UniGeneType, UniGeneType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> uniGeneIdIndex() {
        return uniGeneIdIndex;
    }

    @Override
    public TypedVertexIndex.Unique<Kegg<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>,
            KeggType, KeggType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> keggIdIndex() {
        return keggIdIndex;
    }

    @Override
    public TypedVertexIndex.Unique<EMBL<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>,
            EMBLType, EMBLType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> eMBLIdIndex() {
        return eMBLIdIndex;
    }

    @Override
    public TypedVertexIndex.Unique<RefSeq<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>,
            RefSeqType, RefSeqType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> refSeqIdIndex() {
        return refSeqIdIndex;
    }

    @Override
    public TypedVertexIndex.Unique<ReactomeTerm<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>,
            ReactomeTermType, ReactomeTermType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> reactomeTermIdIndex() {
        return reactomeTermIdIndex;
    }

    @Override
    public TypedVertexIndex.Unique<Dataset<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>,
            DatasetType, DatasetType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> datasetNameIndex() {
        return datasetNameIndex;
    }

    @Override
    public TypedVertexIndex.Unique<Keyword<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>,
            KeywordType, KeywordType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> keywordIdIndex() {
        return keywordIdIndex;
    }

    @Override
    public TypedVertexIndex.Unique<Interpro<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>,
            InterproType, InterproType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> interproIdIndex() {
        return interproIdIndex;
    }

    @Override
    public TypedVertexIndex.Unique<Pfam<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>,
            PfamType, PfamType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> pfamIdIndex() {
        return pfamIdIndex;
    }

    @Override
    public TypedVertexIndex.Unique<Organism<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>,
            OrganismType, OrganismType.scientificName, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> organismScientificNameIndex() {
        return organismScientificNameIndex;
    }

    @Override
    public TypedVertexIndex.Unique<Taxon<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>,
            TaxonType, TaxonType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> taxonNameIndex() {
        return taxonNameIndex;
    }

    @Override
    public TypedVertexIndex.Unique<FeatureType<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>,
            FeatureTypeType, FeatureTypeType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> featureTypeNameIndex() {
        return featureTypeNameIndex;
    }

	@Override
	public TypedVertexIndex.Unique<CommentType<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, CommentTypeType, CommentTypeType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> commentTypeNameIndex() {
		return commentTypeNameIndex;
	}

	@Override
    public ArticleType Article() {
        return articleType;
    }

    @Override
    public BookType Book() {
        return bookType;
    }

    @Override
    public CityType City() {
        return cityType;
    }

	@Override
	public DiseaseType Disease() {
		return diseaseType;
	}

	@Override
    public CommentTypeType CommentType() {
        return commentTypeType;
    }

	@Override
	public ConsortiumType Consortium() {
		return consortiumType;
	}

	@Override
    public DatasetType Dataset() {
        return datasetType;
    }

    @Override
    public CountryType Country() {
        return countryType;
    }

    @Override
    public DBType DB() {
        return dbType;
    }

    @Override
    public EMBLType EMBL() {
        return eMBLType;
    }

    @Override
    public EnsemblType Ensembl() {
        return ensemblType;
    }

    @Override
    public FeatureTypeType FeatureType() {
        return featureTypeType;
    }

    @Override
    public InstituteType Institute() {
        return instituteType;
    }

    @Override
    public InterproType Interpro() {
        return interproType;
    }

	@Override
	public IsoformType Isoform() {
		return isoformType;
	}

    @Override
    public JournalType Journal() {
        return journalType;
    }

    @Override
    public KeggType Kegg() {
        return keggType;
    }

    @Override
    public KeywordType Keyword() {
        return keywordType;
    }

    @Override
    public OnlineArticleType OnlineArticle() {
        return onlineArticleType;
    }

    @Override
    public OrganismType Organism() {
        return organismType;
    }

    @Override
    public OnlineJournalType OnlineJournal() {
        return onlineJournalType;
    }

    @Override
    public PatentType Patent() {
        return patentType;
    }

	@Override
	public PersonType Person() {
		return personType;
	}

	@Override
	public SequenceCautionType SequenceCaution() {
		return sequenceCautionType;
	}

	@Override
    public PfamType Pfam() {
        return pfamType;
    }

    @Override
    public PIRType PIR() {
        return pIRType;
    }

    @Override
    public ProteinType Protein() {
        return proteinType;
    }

    @Override
    public PublisherType Publisher() {
        return publisherType;
    }

    @Override
    public PubmedType Pubmed() {
        return pubmedType;
    }

    @Override
    public ReactomeTermType ReactomeTerm() {
        return reactomeTermType;
    }

    @Override
    public ReferenceType Reference() {
        return referenceType;
    }

    @Override
    public RefSeqType RefSeq() {
        return refSeqType;
    }

    @Override
    public SubcellularLocationType SubcellularLocation() {
        return subcellularLocationType;
    }

    @Override
    public SubmissionType Submission() {
        return submissionType;
    }

    @Override
    public TaxonType Taxon() {
        return taxonType;
    }

    @Override
    public ThesisType Thesis() {
        return thesisType;
    }

    @Override
    public UniGeneType UniGene() {
        return uniGeneType;
    }

    @Override
    public UnpublishedObservationType UnpublishedObservation() {
        return unpublishedObservationType;
    }

    @Override
    public ArticleJournalType ArticleJournal() {
        return articleJournalType;
    }

    @Override
    public ArticlePubmedType ArticlePubmed() {
        return articlePubmedType;
    }


    @Override
    public BookCityType BookCity() {
        return bookCityType;
    }

    @Override
    public BookPublisherType BookPublisher() {
        return bookPublisherType;
    }

	@Override
	public BookEditorType BookEditor() {
		return bookEditorType;
	}

	@Override
    public InstituteCountryType InstituteCountry() {
        return instituteCountryType;
    }

    @Override
    public OnlineArticleOnlineJournalType OnlineArticleOnlineJournal() {
        return onlineArticleOnlineJournalType;
    }

    @Override
    public OrganismTaxonType OrganismTaxon() {
        return organismTaxonType;
    }

    @Override
    public ProteinCommentType ProteinComment() {
        return proteinCommentType;
    }

    @Override
    public ProteinDatasetType ProteinDataset() {
        return proteinDatasetType;
    }

	@Override
	public ProteinDiseaseType ProteinDisease() {
		return proteinDiseaseType;
	}

	@Override
    public ProteinEMBLType ProteinEMBL() {
        return proteinEMBLType;
    }

    @Override
    public ProteinEnsemblType ProteinEnsembl() {
        return proteinEnsemblType;
    }

    @Override
    public ProteinFeatureType ProteinFeature() {
        return proteinFeatureType;
    }

    @Override
    public ProteinInterproType ProteinInterpro() {
        return proteinInterproType;
    }

    @Override
    public ProteinKeggType ProteinKegg() {
        return proteinKeggType;
    }

    @Override
    public ProteinKeywordType ProteinKeyword() {
        return proteinKeywordType;
    }

	/*
		You can use this as `uniprotGraph.withGo(new TitanUniprotGoGraph(raw, uniprotGraph, goGraph))`
	*/
	public TitanUniprotGraph withGo(TitanUniprotGoGraph uniprotGoGraph) {
		this.uniprotGoGraph = uniprotGoGraph;
		return this;
	}
	/*
		You can use this as `uniprotGraph.withEnzymeDB(new TitanUniprotEnzymeGraph(raw, uniprotGraph, enzymeGraph))`
	*/
	public TitanUniprotGraph withEnzymeDB(TitanUniprotEnzymeGraph uniprotEnzymeGraph) {
		this.uniprotEnzymeGraph = uniprotEnzymeGraph;
		return this;
	}
	/*
		You can use this as `uniprotGraph.withNCBITaxonomy(new TitanUniprotNCBITaxonomyGraph(raw, uniprotGraph, ncbiTaxonomyGraph))`
	*/
	public TitanUniprotGraph withNCBITaxonomy(TitanUniprotNCBITaxonomyGraph uniprotNCBITaxonomyGraph) {
		this.uniprotNCBITaxonomyGraph = uniprotNCBITaxonomyGraph;
		return this;
	}
	/*
		You can use this as `uniprotGraph.withUniRef(new TitanUniprotUniRefGraph(raw, uniprotGraph, uniRefGraph))`
	*/
	public TitanUniprotGraph withUniRef(TitanUniprotUniRefGraph uniprotUniRefGraph) {
		this.uniprotUniRefGraph = uniprotUniRefGraph;
		return this;
	}

}
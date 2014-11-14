package com.bio4j.titan.model.uniprot;

import com.bio4j.angulillos.TypedVertexIndex;
import com.bio4j.angulillos.titan.TitanTypedVertexIndex;
import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.vertices.*;
import com.bio4j.model.uniprot_enzymedb.UniprotEnzymeDBGraph;
import com.bio4j.model.uniprot_go.UniprotGoGraph;
import com.bio4j.model.uniprot_ncbiTaxonomy.UniprotNCBITaxonomyGraph;
import com.bio4j.model.uniprot_uniref.UniprotUniRefGraph;
import com.bio4j.titan.model.uniprot_enzyme.TitanUniprotEnzymeGraph;
import com.bio4j.titan.model.uniprot_go.TitanUniprotGoGraph;
import com.bio4j.titan.model.uniprot_ncbiTaxonomy.TitanUniprotNCBITaxonomyGraph;
import com.bio4j.titan.model.uniprot_uniref.TitanUniprotUniRefGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.*;


/*
  Implementing the types with Titan
*/
public final class TitanUniprotGraph
        extends
        UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> {

    private DefaultTitanGraph rawGraph;

	private TitanUniprotGoGraph uniprotGoGraph;
	private TitanUniprotUniRefGraph uniprotUniRefGraph;
	private TitanUniprotNCBITaxonomyGraph uniprotNCBITaxonomyGraph;
	private TitanUniprotEnzymeGraph uniprotEnzymeGraph;


    //-------------------VERTICES----------------------------

    public PropertyKey proteinTypekey;
    public PropertyKey proteinAcessionKey;
    public PropertyKey proteinNameKey;
    public PropertyKey proteinShortNameKey;
    public PropertyKey proteinFullNameKey;
    public PropertyKey proteinModifiedDateKey;
    public PropertyKey proteinCreatedDateKey;
    public PropertyKey proteinMassKey;
    public PropertyKey proteinVersionKey;
    public PropertyKey proteinLengthKey;
    public PropertyKey proteinSequenceKey;
    public ProteinType proteinType;

	//---AlternativeProduct---
	public PropertyKey alternativeProductTypeKey;
	public PropertyKey alternativeProductNameKey;
	public AlternativeProductType alternativeProductType;
    //---Article---
    public PropertyKey articleTypeKey;
    public PropertyKey articleTitleKey;
    public PropertyKey articleDoIdKey;
    public ArticleType articleType;
    //---Db---
    public PropertyKey dbTypeKey;
    public PropertyKey dbNameKey;
    public DBType dbType;
    //---Book---
    public PropertyKey bookTypeKey;
    public PropertyKey bookNameKey;
    public BookType bookType;
    //---city---
    public PropertyKey cityTypeKey;
    public PropertyKey cityNameKey;
    public CityType cityType;
	//---consortium---
	public PropertyKey consortiumTypeKey;
	public PropertyKey consortiumNameKey;
	public ConsortiumType consortiumType;
    //---country---
    public PropertyKey countryTypeKey;
    public PropertyKey countryNameKey;
    public CountryType countryType;
    //---dataset---
    public PropertyKey datasetTypeKey;
    public PropertyKey datasetNameKey;
    public DatasetType datasetType;
	//---gene location---
	public PropertyKey geneLocationTypeKey;
	public PropertyKey geneLocationNameKey;
	public GeneLocationType geneLocationType;
	//---disease---
	public PropertyKey diseaseTypeKey;
	public PropertyKey diseaseNameKey;
	public PropertyKey diseaseIdKey;
	public PropertyKey diseaseAcronymKey;
	public PropertyKey diseaseDescriptionKey;
	public DiseaseType diseaseType;
    //---organism---
    public PropertyKey organismTypeKey;
    public PropertyKey organismScientificNameKey;
    public PropertyKey organismCommonNameKey;
    public PropertyKey organismSynonymNameKey;
    public OrganismType organismType;
    //---keyword---
    public PropertyKey keywordTypeKey;
    public PropertyKey keywordNameKey;
    public PropertyKey keywordIdKey;
    public KeywordType keywordType;
    //---interpro---
    public PropertyKey interproTypeKey;
    public PropertyKey interproNameKey;
    public PropertyKey interproIdKey;
    public InterproType interproType;
	//---interpro---
	public PropertyKey isoformTypeKey;
	public PropertyKey isoformNameKey;
	public PropertyKey isoformIdKey;
	public PropertyKey isoformSequenceKey;
	public PropertyKey isoformNoteKey;
	public IsoformType isoformType;
    //----institute-----
    public PropertyKey instituteTypeKey;
    public PropertyKey instituteNameKey;
    public InstituteType instituteType;
    //---journal---
    public PropertyKey journalTypeKey;
    public PropertyKey journalNameKey;
    public JournalType journalType;
	//---person---
	public PropertyKey personTypeKey;
	public PropertyKey personNameKey;
	public PersonType personType;
    //---publisher---
    public PropertyKey publisherTypeKey;
    public PropertyKey publisherNameKey;
    public PublisherType publisherType;
    //---pubmed---
    public PropertyKey pubmedTypeKey;
    public PropertyKey pubmedIdKey;
    public PubmedType pubmedType;
    //---reactome term---
    public PropertyKey reactomeTermTypeKey;
    public PropertyKey reactomeTermPathwayNameKey;
    public PropertyKey reactomeTermIdKey;
    public ReactomeTermType reactomeTermType;
    //---pfam---
    public PropertyKey pfamTypeKey;
    public PropertyKey pfamNameKey;
    public PropertyKey pfamIdKey;
    public PfamType pfamType;
    //---kegg---
    public PropertyKey keggTypeKey;
    public PropertyKey keggIdKey;
    public KeggType keggType;
    //---EMBL---
    public PropertyKey eMBLTypeKey;
    public PropertyKey eMBLIdKey;
    public PropertyKey eMBLMoleculeTypeKey;
    public PropertyKey eMBLProteinSequenceIdKey;
    public EMBLType eMBLType;
    //---Patent---
    public PropertyKey patentTypeKey;
    public PropertyKey patentTitleKey;
    public PropertyKey patentNumberKey;
    public PatentType patentType;
    //---PIR---
    public PropertyKey pIRTypeKey;
    public PropertyKey pIRIdKey;
    public PropertyKey pIREntryNameKey;
    public PIRType pIRType;
    //---UniGene---
    public PropertyKey uniGeneTypeKey;
    public PropertyKey uniGeneIdKey;
    public UniGeneType uniGeneType;
    //---Ensembl---
    public PropertyKey ensemblTypeKey;
    public PropertyKey ensemblIdKey;
    public PropertyKey ensemblMoleculeIdKey;
    public PropertyKey ensemblProteinSequenceIdKey;
    public PropertyKey ensemblGeneIdKey;
    public EnsemblType ensemblType;
    //---Taxon---
    public PropertyKey taxonTypeKey;
    public PropertyKey taxonNameKey;
    public TaxonType taxonType;
    //---Thesis---
    public PropertyKey thesisTypeKey;
    public PropertyKey thesisTitleKey;
    public ThesisType thesisType;
    //----OnlineArticle-----
    public PropertyKey onlineArticleTypeKey;
    public PropertyKey onlineArticleTitleKey;
    public OnlineArticleType onlineArticleType;
    //----OnlineJournal-----
    public PropertyKey onlineJournalTypeKey;
    public PropertyKey onlineJournalNameKey;
    public OnlineJournalType onlineJournalType;
    //---RefSeq---
    public PropertyKey refSeqTypeKey;
    public PropertyKey refSeqIdKey;
    public PropertyKey refSeqNucleotideSequenceIdKey;
    public RefSeqType refSeqType;
    //---Reference---
    public PropertyKey referenceDateKey;
    public ReferenceType referenceType;
	//---SequenceCaution----
	public PropertyKey sequenceCautionTypeKey;
	public PropertyKey sequenceCautionNameKey;
	public SequenceCautionType sequenceCautionType;
    //---SubcellularLocation----
    public PropertyKey subcellularLocationTypeKey;
    public PropertyKey subcellularLocationNameKey;
    public SubcellularLocationType subcellularLocationType;
    //---Submission----
    public PropertyKey submissionTypeKey;
    public PropertyKey submissionTitleKey;
    public SubmissionType submissionType;
    //---FeatureType---
    public PropertyKey featureTypeTypeKey;
    public PropertyKey featureTypeNameKey;
    public FeatureTypeType featureTypeType;
    //---CommentType---
    public PropertyKey commentTypeTypeKey;
    public PropertyKey commentTypeNameKey;
    public CommentTypeType commentTypeType;
    //---UnpublishedObservation----
    public PropertyKey unpublishedObservationScopeKey;
    public UnpublishedObservationType unpublishedObservationType;


    //------------------INDICES----------------
    //-----------------------------------------
    //---------------INDICES---------------------------

    public TitanTypedVertexIndex.Unique<Protein<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, ProteinType, ProteinType.accession, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> proteinAccessionIndex;
    public TitanTypedVertexIndex.Unique<Dataset<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DatasetType, DatasetType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> datasetNameIndex;
    public TitanTypedVertexIndex.Unique<Organism<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, OrganismType, OrganismType.scientificName, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> organismScientificNameIndex;
    public TitanTypedVertexIndex.Unique<Keyword<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, KeywordType, KeywordType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> keywordIdIndex;
    public TitanTypedVertexIndex.Unique<ReactomeTerm<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, ReactomeTermType, ReactomeTermType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> reactomeTermIdIndex;
    public TitanTypedVertexIndex.Unique<Interpro<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, InterproType, InterproType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> interproIdIndex;
    public TitanTypedVertexIndex.Unique<Pfam<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, PfamType, PfamType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> pfamIdIndex;
    public TitanTypedVertexIndex.Unique<Kegg<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, KeggType, KeggType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> keggIdIndex;
    public TitanTypedVertexIndex.Unique<EMBL<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, EMBLType, EMBLType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> eMBLIdIndex;
    public TitanTypedVertexIndex.Unique<PIR<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, PIRType, PIRType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> pIRIdIndex;
    public TitanTypedVertexIndex.Unique<UniGene<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, UniGeneType, UniGeneType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> uniGeneIdIndex;
    public TitanTypedVertexIndex.Unique<Ensembl<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, EnsemblType, EnsemblType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> ensemblIdIndex;
    public TitanTypedVertexIndex.Unique<Taxon<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, TaxonType, TaxonType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> taxonNameIndex;
    public TitanTypedVertexIndex.Unique<RefSeq<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, RefSeqType, RefSeqType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> refSeqIdIndex;
    public TitanTypedVertexIndex.Unique<CommentType<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, CommentTypeType, CommentTypeType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> commentTypeNameIndex;
    public TitanTypedVertexIndex.Unique<FeatureType<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, FeatureTypeType, FeatureTypeType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> featureTypeNameIndex;
	public TitanTypedVertexIndex.Unique<Journal<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, JournalType, JournalType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> journalNameIndex;
	public TitanTypedVertexIndex.Unique<Article<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, ArticleType, ArticleType.title, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> articleTitleIndex;
	public TitanTypedVertexIndex.Unique<OnlineJournal<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, OnlineJournalType, OnlineJournalType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> onlineJournalNameIndex;
	public TitanTypedVertexIndex.Unique<OnlineArticle<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, OnlineArticleType, OnlineArticleType.title, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> onlineArticleTitleIndex;
	public TitanTypedVertexIndex.Unique<Pubmed<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, PubmedType, PubmedType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> pubmedIdIndex;
	public TitanTypedVertexIndex.Unique<Person<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, PersonType, PersonType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> personNameIndex;
	public TitanTypedVertexIndex.Unique<Thesis<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, ThesisType, ThesisType.title, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> thesisTitleIndex;
	public TitanTypedVertexIndex.Unique<Consortium<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, ConsortiumType, ConsortiumType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> consortiumNameIndex;
	public TitanTypedVertexIndex.Unique<Institute<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, InstituteType, InstituteType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> instituteNameIndex;
	public TitanTypedVertexIndex.Unique<Submission<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, SubmissionType, SubmissionType.title, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> submissionTitleIndex;
	public TitanTypedVertexIndex.Unique<Patent<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, PatentType, PatentType.number, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> patentNumberIndex;
	public TitanTypedVertexIndex.Unique<City<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, CityType, CityType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> cityNameIndex;
	public TitanTypedVertexIndex.Unique<Country<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, CountryType, CountryType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> countryNameIndex;
	public TitanTypedVertexIndex.Unique<Publisher<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, PublisherType, PublisherType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> publisherNameIndex;
	public TitanTypedVertexIndex.Unique<Book<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, BookType, BookType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> bookNameIndex;
	public TitanTypedVertexIndex.Unique<DB<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DBType, DBType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> dbNameIndex;
	public TitanTypedVertexIndex.Unique<Disease<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DiseaseType, DiseaseType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> diseaseIdIndex;
	public TitanTypedVertexIndex.Unique<SubcellularLocation<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, SubcellularLocationType, SubcellularLocationType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> subcellularLocationNameIndex;
	public TitanTypedVertexIndex.Unique<Isoform<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, IsoformType, IsoformType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> isoformIdIndex;
	public TitanTypedVertexIndex.Unique<SequenceCaution<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, SequenceCautionType, SequenceCautionType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> sequenceCautionNameIndex;
	public TitanTypedVertexIndex.Unique<GeneLocation<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, GeneLocationType, GeneLocationType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> geneLocationNameIndex;
	public TitanTypedVertexIndex.Unique<AlternativeProduct<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, AlternativeProductType, AlternativeProductType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph> alternativeProductNameIndex;




	//-----------------------------------------------------------------------------------------
    //--------------------------------RELATIONSHIPS--------------------------------------------

	// isoformEventGenerator
	public EdgeLabel isoformEventGeneratorLabel;
	public IsoformEventGeneratorType isoformEventGeneratorType;
    // proteinDataset
    public EdgeLabel proteinDatasetLabel;
    public ProteinDatasetType proteinDatasetType;
    // proteinOrganism
    public EdgeLabel proteinOrganismLabel;
    public ProteinOrganismType proteinOrganismType;
    // proteinKeyword
    public EdgeLabel proteinKeywordLabel;
    public ProteinKeywordType proteinKeywordType;
    // proteinReactomeTerm
    public EdgeLabel proteinReactomeTermLabel;
    public ProteinReactomeTermType proteinReactomeTermType;
    // proteinInterpro
    public EdgeLabel proteinInterproLabel;
    public ProteinInterproType proteinInterproType;
    // proteinPfam
    public EdgeLabel proteinPfamLabel;
    public ProteinPfamType proteinPfamType;
    // proteinKegg
    public EdgeLabel proteinKeggLabel;
    public ProteinKeggType proteinKeggType;
    // proteinEMBL
    public EdgeLabel proteinEMBLLabel;
    public ProteinEMBLType proteinEMBLType;
    // proteinPIR
    public EdgeLabel proteinPIRLabel;
    public ProteinPIRType proteinPIRType;
    // proteinUniGene
    public EdgeLabel proteinUniGeneLabel;
    public ProteinUniGeneType proteinUniGeneType;
    // proteinEnsembl
    public EdgeLabel proteinEnsemblLabel;
    public ProteinEnsemblType proteinEnsemblType;
    // proteinRefSeq
    public EdgeLabel proteinRefSeqLabel;
    public ProteinRefSeqType proteinRefSeqType;
	// proteinGeneLocation
	public EdgeLabel proteinGeneLocationLabel;
	public PropertyKey proteinGeneLocationNameKey;
	public ProteinGeneLocationType proteinGeneLocationType;
    // proteinFeature
    public EdgeLabel proteinFeatureLabel;
    public PropertyKey proteinFeatureIdKey;
    public PropertyKey proteinFeatureDescriptionKey;
    public PropertyKey proteinFeatureEvidenceKey;
    public PropertyKey proteinFeatureStatusKey;
    public PropertyKey proteinFeatureBeginKey;
    public PropertyKey proteinFeatureEndKey;
    public PropertyKey proteinFeatureOriginalKey;
    public PropertyKey proteinFeatureVariationKey;
    public PropertyKey proteinFeatureRefKey;
    public ProteinFeatureType proteinFeatureType;
    // proteinComment
    public EdgeLabel proteinCommentLabel;
    public ProteinCommentType proteinCommentType;
	public PropertyKey proteinCommentTextKey;
	public PropertyKey proteinCommentStatusKey;
	public PropertyKey proteinCommentEvidenceKey;
	public PropertyKey proteinCommentBeginKey;
	public PropertyKey proteinCommentEndKey;
	public PropertyKey proteinCommentMethodKey;
	public PropertyKey proteinCommentMassKey;
	public PropertyKey proteinCommentAbsorptionMaxKey;
	public PropertyKey proteinCommentAbsorptionTextKey;
	public PropertyKey proteinCommentKineticsXMLKey;
	public PropertyKey proteinCommentPhDependenceKey;
	public PropertyKey proteinCommentPositionKey;
	public PropertyKey proteinCommentRedoxPotentialKey;
	public PropertyKey proteinCommentRedoxPotentialEvidenceKey;
	public PropertyKey proteinCommentTemperatureDependenceKey;

	// proteinProteinInteraction
	public EdgeLabel proteinProteinInteractionLabel;
	public ProteinProteinInteractionType proteinProteinInteractionType;
	// proteinIsoform
	public EdgeLabel proteinIsoformLabel;
	public ProteinIsoformType proteinIsoformType;
	// proteinIsoformInteraction
	public EdgeLabel proteinIsoformInteractionLabel;
	public ProteinIsoformInteractionType proteinIsoformInteractionType;
	// isoformProteinInteraction
	public EdgeLabel isoformProteinInteractionLabel;
	public IsoformProteinInteractionType isoformProteinInteractionType;
	// proteinDisease
	public EdgeLabel proteinDiseaseLabel;
	public ProteinDiseaseType proteinDiseaseType;
	public PropertyKey proteinDiseaseTextKey;
	public PropertyKey proteinDiseaseStatusKey;
	public PropertyKey proteinDiseaseEvidenceKey;
    // proteinReference
    public EdgeLabel proteinReferenceLabel;
    public ProteinReferenceType proteinReferenceType;
    // proteinSubcellularLocation
    public EdgeLabel proteinSubcellularLocationLabel;
    public ProteinSubcellularLocationType proteinSubcellularLocationType;
	public PropertyKey proteinSubcellularLocationStatusKey;
	public PropertyKey proteinSubcellularLocationEvidenceKey;
	public PropertyKey proteinSubcellularLocationTopologyKey;
	public PropertyKey proteinSubcellularLocationTopologyStatusKey;
	// proteinSequenceCaution
	public EdgeLabel proteinSequenceCautionLabel;
	public ProteinSequenceCautionType proteinSequenceCautionType;
	public PropertyKey proteinSequenceCautionIdKey;
	public PropertyKey proteinSequenceCautionEvidenceKey;
	public PropertyKey proteinSequenceCautionStatusKey;
	public PropertyKey proteinSequenceCautionTextKey;
	public PropertyKey proteinSequenceCautionResourceKey;
	public PropertyKey proteinSequenceCautionVersionKey;
	public PropertyKey proteinSequenceCautionPositionKey;

    // articlePubmed
    public EdgeLabel articlePubmedLabel;
    public ArticlePubmedType articlePubmedType;
    // articleJournal
    public EdgeLabel articleJournalLabel;
    public ArticleJournalType articleJournalType;
	public PropertyKey articleJournalVolumeKey;
	public PropertyKey articleJournalFirstKey;
	public PropertyKey articleJournalLastKey;
    // bookCity
    public EdgeLabel bookCityLabel;
    public BookCityType bookCityType;
	// bookEditor
	public EdgeLabel bookEditorLabel;
	public BookEditorType bookEditorType;
    // bookPublisher
    public EdgeLabel bookPublisherLabel;
    public BookPublisherType bookPublisherType;
    // instituteCountry
    public EdgeLabel instituteCountryLabel;
    public InstituteCountryType instituteCountryType;
    // taxonParent
    public EdgeLabel taxonParentLabel;
    public TaxonParentType taxonParentType;
    // organismTaxon
    public EdgeLabel organismTaxonLabel;
    public OrganismTaxonType organismTaxonType;
    // onlineArticleOnlineJournal
    public EdgeLabel onlineArticleOnlineJournalLabel;
    public OnlineArticleOnlineJournalType onlineArticleOnlineJournalType;
	public PropertyKey onlineArticleOnlineJournalLocatorKey;
	// referenceAuthorPerson
	public EdgeLabel referenceAuthorPersonLabel;
	public ReferenceAuthorPersonType referenceAuthorPersonType;
	// referenceAuthorConsortium
	public EdgeLabel referenceAuthorConsortiumLabel;
	public ReferenceAuthorConsortiumType referenceAuthorConsortiumType;
    // referenceArticle
    public EdgeLabel referenceArticleLabel;
    public ReferenceArticleType referenceArticleType;
    // referenceBook
    public EdgeLabel referenceBookLabel;
    public ReferenceBookType referenceBookType;
    // referenceThesis
    public EdgeLabel referenceThesisLabel;
    public ReferenceThesisType referenceThesisType;
    // referenceSubmission
    public EdgeLabel referenceSubmissionLabel;
    public ReferenceSubmissionType referenceSubmissionType;
    // referencePatent
    public EdgeLabel referencePatentLabel;
    public ReferencePatentType referencePatentType;
    // referenceOnlineArticle
    public EdgeLabel referenceOnlineArticleLabel;
    public ReferenceOnlineArticleType referenceOnlineArticleType;
    // referenceUnpublishedObservation
    public EdgeLabel referenceUnpublishedObservationLabel;
    public ReferenceUnpublishedObservationType referenceUnpublishedObservationType;
    // thesisInstitute
    public EdgeLabel thesisInstituteLabel;
    public ThesisInstituteType thesisInstituteType;
    // submissionDB
    public EdgeLabel submissionDBLabel;
    public SubmissionDBType submissionDBType;
    // subcellularLocation
    public EdgeLabel subcellularLocationParentLabel;
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
	    proteinType = new ProteinType((PropertyKey) null);
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

	    // Alternative Product keys
	    alternativeProductType = new AlternativeProductType(cityTypeKey);
	    alternativeProductTypeKey = raw().titanKeyForVertexType(alternativeProductType.name);
	    alternativeProductNameKey = alternativeProductTypeKey;

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

	    // GeneLocation keys
	    geneLocationType = new GeneLocationType(geneLocationTypeKey);
	    geneLocationTypeKey = raw().titanKeyForVertexType(geneLocationType.name);
	    geneLocationNameKey = geneLocationTypeKey;

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
	    unpublishedObservationScopeKey = raw().titanKeyForVertexPropertySingle(unpublishedObservationType.scope);

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

	    // isoformEventGenerator
	    isoformEventGeneratorLabel = raw().titanLabelForEdgeType(this.new IsoformEventGeneratorType(null));
	    isoformEventGeneratorType = new IsoformEventGeneratorType(isoformEventGeneratorLabel);

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

	    // proteinGeneLocation
	    proteinGeneLocationLabel = raw().titanLabelForEdgeType(this.new ProteinGeneLocationType(null));
	    proteinGeneLocationType = new ProteinGeneLocationType(proteinGeneLocationLabel);

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

	    proteinProteinInteractionLabel = raw().titanLabelForEdgeType(this.new ProteinProteinInteractionType(null));
	    proteinProteinInteractionType = new ProteinProteinInteractionType(proteinProteinInteractionLabel);

	    proteinIsoformInteractionLabel = raw().titanLabelForEdgeType(this.new ProteinIsoformInteractionType(null));
	    proteinIsoformInteractionType = new ProteinIsoformInteractionType(proteinIsoformInteractionLabel);

	    proteinIsoformLabel = raw().titanLabelForEdgeType(this.new ProteinIsoformType(null));
	    proteinIsoformType = new ProteinIsoformType(proteinIsoformLabel);

	    isoformProteinInteractionLabel = raw().titanLabelForEdgeType(this.new IsoformProteinInteractionType(null));
	    isoformProteinInteractionType = new IsoformProteinInteractionType(isoformProteinInteractionLabel);

        proteinReferenceLabel = raw().titanLabelForEdgeType(this.new ProteinReferenceType(null));
	    proteinReferenceType = new ProteinReferenceType(proteinReferenceLabel);

	    proteinGeneLocationLabel = raw().titanLabelForEdgeType(this.new ProteinGeneLocationType(null));
	    proteinGeneLocationNameKey = raw().titanKeyForEdgePropertySingle(proteinGeneLocationType.name);
	    proteinGeneLocationType = new ProteinGeneLocationType(proteinGeneLocationLabel);

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
	    sequenceCautionNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, SequenceCaution().name);
	    geneLocationNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, GeneLocation().name);
	    alternativeProductNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, AlternativeProduct().name);

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
	public ProteinProteinInteractionType ProteinProteinInteraction() {
		return proteinProteinInteractionType;
	}

	@Override
	public ProteinIsoformInteractionType ProteinIsoformInteraction() {
		return proteinIsoformInteractionType;
	}

	@Override
	public ProteinIsoformType ProteinIsoform() {
		return proteinIsoformType;
	}

	@Override
	public IsoformProteinInteractionType IsoformProteinInteraction() {
		return isoformProteinInteractionType;
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
    public UniprotUniRefGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> uniprotUniRefGraph() {
        return uniprotUniRefGraph;
    }

    @Override
    public UniprotGoGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> uniprotGoGraph() {
        return uniprotGoGraph;
    }

    @Override
    public UniprotEnzymeDBGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> uniprotEnzymeDBGraph() {
        return uniprotEnzymeGraph;
    }

    @Override
    public UniprotNCBITaxonomyGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> uniprotNCBITaxonomyGraph() {
        return uniprotNCBITaxonomyGraph;
    }

	@Override
	public TypedVertexIndex.Unique<AlternativeProduct<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, AlternativeProductType, AlternativeProductType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> alternativeProductNameIndex() {
		return alternativeProductNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<GeneLocation<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, GeneLocationType, GeneLocationType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> geneLocationNameIndex() {
		return geneLocationNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Disease<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DiseaseType, DiseaseType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> diseaseIdIndex() {
		return diseaseIdIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Journal<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, JournalType, JournalType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> journalNameIndex() {
		return journalNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Article<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, ArticleType, ArticleType.title, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> articleTitleIndex() {
		return articleTitleIndex;
	}

	@Override
	public TypedVertexIndex.Unique<OnlineJournal<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, OnlineJournalType, OnlineJournalType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> onlineJournalNameIndex() {
		return onlineJournalNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<OnlineArticle<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, OnlineArticleType, OnlineArticleType.title, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> onlineArticleTitleIndex() {
		return onlineArticleTitleIndex;
	}

	@Override
	public TypedVertexIndex.Unique<City<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, CityType, CityType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> cityNameIndex() {
		return cityNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Publisher<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, PublisherType, PublisherType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> publisherNameIndex() {
		return publisherNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Book<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, BookType, BookType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> bookNameIndex() {
		return bookNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<DB<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DBType, DBType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> dbNameIndex() {
		return dbNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Country<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, CountryType, CountryType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> countryNameIndex() {
		return countryNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Patent<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, PatentType, PatentType.number, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> patentNumberIndex() {
		return patentNumberIndex;
	}

	@Override
	public TypedVertexIndex.Unique<SequenceCaution<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, SequenceCautionType, SequenceCautionType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> sequenceCautionNameIndex() {
		return sequenceCautionNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Submission<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, SubmissionType, SubmissionType.title, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> submissionTitleIndex() {
		return submissionTitleIndex;
	}

	@Override
	public TypedVertexIndex.Unique<SubcellularLocation<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, SubcellularLocationType, SubcellularLocationType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> subcellularLocationNameIndex() {
		return subcellularLocationNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Institute<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, InstituteType, InstituteType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> instituteNameIndex() {
		return instituteNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Isoform<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, IsoformType, IsoformType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> isoformIdIndex() {
		return isoformIdIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Consortium<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, ConsortiumType, ConsortiumType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> consortiumNameIndex() {
		return consortiumNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Thesis<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, ThesisType, ThesisType.title, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> thesisTitleIndex() {
		return thesisTitleIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Person<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, PersonType, PersonType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> personNameIndex() {
		return personNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Protein<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, ProteinType, ProteinType.accession, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> proteinAccessionIndex() {
		return proteinAccessionIndex;
	}

	@Override
    public TypedVertexIndex.Unique<Ensembl<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>,
            EnsemblType, EnsemblType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> ensemblIdIndex() {
        return ensemblIdIndex;
    }

    @Override
    public TypedVertexIndex.Unique<PIR<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>,
            PIRType, PIRType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> pIRIdIndex() {
        return pIRIdIndex;
    }

	@Override
	public TypedVertexIndex.Unique<Pubmed<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, PubmedType, PubmedType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> pubmedIdIndex() {
		return pubmedIdIndex;
	}

	@Override
    public TypedVertexIndex.Unique<UniGene<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>,
            UniGeneType, UniGeneType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> uniGeneIdIndex() {
        return uniGeneIdIndex;
    }

    @Override
    public TypedVertexIndex.Unique<Kegg<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>,
            KeggType, KeggType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> keggIdIndex() {
        return keggIdIndex;
    }

    @Override
    public TypedVertexIndex.Unique<EMBL<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>,
            EMBLType, EMBLType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> eMBLIdIndex() {
        return eMBLIdIndex;
    }

    @Override
    public TypedVertexIndex.Unique<RefSeq<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>,
            RefSeqType, RefSeqType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> refSeqIdIndex() {
        return refSeqIdIndex;
    }

    @Override
    public TypedVertexIndex.Unique<ReactomeTerm<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>,
            ReactomeTermType, ReactomeTermType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> reactomeTermIdIndex() {
        return reactomeTermIdIndex;
    }

    @Override
    public TypedVertexIndex.Unique<Dataset<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>,
            DatasetType, DatasetType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> datasetNameIndex() {
        return datasetNameIndex;
    }

    @Override
    public TypedVertexIndex.Unique<Keyword<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>,
            KeywordType, KeywordType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> keywordIdIndex() {
        return keywordIdIndex;
    }

    @Override
    public TypedVertexIndex.Unique<Interpro<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>,
            InterproType, InterproType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> interproIdIndex() {
        return interproIdIndex;
    }

    @Override
    public TypedVertexIndex.Unique<Pfam<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>,
            PfamType, PfamType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> pfamIdIndex() {
        return pfamIdIndex;
    }

    @Override
    public TypedVertexIndex.Unique<Organism<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>,
            OrganismType, OrganismType.scientificName, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> organismScientificNameIndex() {
        return organismScientificNameIndex;
    }

    @Override
    public TypedVertexIndex.Unique<Taxon<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>,
            TaxonType, TaxonType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> taxonNameIndex() {
        return taxonNameIndex;
    }

    @Override
    public TypedVertexIndex.Unique<FeatureType<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>,
            FeatureTypeType, FeatureTypeType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> featureTypeNameIndex() {
        return featureTypeNameIndex;
    }

	@Override
	public TypedVertexIndex.Unique<CommentType<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, CommentTypeType, CommentTypeType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> commentTypeNameIndex() {
		return commentTypeNameIndex;
	}

	@Override
	public AlternativeProductType AlternativeProduct() {
		return alternativeProductType;
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
	public GeneLocationType GeneLocation() {
		return geneLocationType;
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
	public IsoformEventGeneratorType IsoformEventGenerator() {
		return isoformEventGeneratorType;
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
	public ProteinGeneLocationType ProteinGeneLocation() {
		return proteinGeneLocationType;
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
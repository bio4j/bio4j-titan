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
import com.thinkaurelius.titan.core.schema.EdgeLabelMaker;
import com.thinkaurelius.titan.core.schema.TitanManagement;
import com.thinkaurelius.titan.core.schema.VertexLabelMaker;


/*
  Implementing the types with Titan
*/
public final class TitanUniprotGraph
		extends
		UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> {

	// protein
	public VertexLabel proteinTypeLabel;
	public PropertyKey proteinAcessionKey;
	public PropertyKey proteinNameKey;
	public PropertyKey proteinShortNameKey;
	public PropertyKey proteinFullNameKey;
	public PropertyKey proteinModifiedDateKey;

	//-------------------VERTICES----------------------------
	public PropertyKey proteinCreatedDateKey;
	public PropertyKey proteinMassKey;
	public PropertyKey proteinVersionKey;
	public PropertyKey proteinLengthKey;
	public PropertyKey proteinSequenceKey;
	public ProteinType proteinType;
	//---AlternativeProduct---
	public VertexLabel alternativeProductTypeLabel;
	public PropertyKey alternativeProductNameKey;
	public AlternativeProductType alternativeProductType;
	//---Article---
	public VertexLabel articleTypeLabel;
	public PropertyKey articleTitleKey;
	public PropertyKey articleDoIdKey;
	public ArticleType articleType;
	//---Db---
	public VertexLabel dbTypeLabel;
	public PropertyKey dbNameKey;
	public DBType dbType;
	//---Book---
	public VertexLabel bookTypeLabel;
	public PropertyKey bookNameKey;
	public BookType bookType;
	//---city---
	public VertexLabel cityTypeLabel;
	public PropertyKey cityNameKey;
	public CityType cityType;
	//---consortium---
	public VertexLabel consortiumTypeLabel;
	public PropertyKey consortiumNameKey;
	public ConsortiumType consortiumType;
	//---country---
	public VertexLabel countryTypeLabel;
	public PropertyKey countryNameKey;
	public CountryType countryType;
	//---dataset---
	public VertexLabel datasetTypeLabel;
	public PropertyKey datasetNameKey;
	public DatasetType datasetType;
	//---gene location---
	public VertexLabel geneLocationTypeLabel;
	public PropertyKey geneLocationNameKey;
	public GeneLocationType geneLocationType;
	//---disease---
	public VertexLabel diseaseTypeLabel;
	public PropertyKey diseaseNameKey;
	public PropertyKey diseaseIdKey;
	public PropertyKey diseaseAcronymKey;
	public PropertyKey diseaseDescriptionKey;
	public DiseaseType diseaseType;
	//---organism---
	public VertexLabel organismTypeLabel;
	public PropertyKey organismScientificNameKey;
	public PropertyKey organismCommonNameKey;
	public PropertyKey organismSynonymNameKey;
	public OrganismType organismType;
	//---keyword---
	public VertexLabel keywordTypeLabel;
	public PropertyKey keywordNameKey;
	public PropertyKey keywordIdKey;
	public KeywordType keywordType;
	//---interpro---
	public VertexLabel interproTypeLabel;
	public PropertyKey interproNameKey;
	public PropertyKey interproIdKey;
	public InterproType interproType;
	//---interpro---
	public VertexLabel isoformTypeLabel;
	public PropertyKey isoformNameKey;
	public PropertyKey isoformIdKey;
	public PropertyKey isoformSequenceKey;
	public PropertyKey isoformNoteKey;
	public IsoformType isoformType;
	//----institute-----
	public VertexLabel instituteTypeLabel;
	public PropertyKey instituteNameKey;
	public InstituteType instituteType;
	//---journal---
	public VertexLabel journalTypeLabel;
	public PropertyKey journalNameKey;
	public JournalType journalType;
	//---person---
	public VertexLabel personTypeLabel;
	public PropertyKey personNameKey;
	public PersonType personType;
	//---publisher---
	public VertexLabel publisherTypeLabel;
	public PropertyKey publisherNameKey;
	public PublisherType publisherType;
	//---pubmed---
	public VertexLabel pubmedTypeLabel;
	public PropertyKey pubmedIdKey;
	public PubmedType pubmedType;
	//---reactome term---
	public VertexLabel reactomeTermTypeLabel;
	public PropertyKey reactomeTermPathwayNameKey;
	public PropertyKey reactomeTermIdKey;
	public ReactomeTermType reactomeTermType;
	//---pfam---
	public VertexLabel pfamTypeLabel;
	public PropertyKey pfamNameKey;
	public PropertyKey pfamIdKey;
	public PfamType pfamType;
	//---kegg---
	public VertexLabel keggTypeLabel;
	public PropertyKey keggIdKey;
	public KeggType keggType;
	//---EMBL---
	public VertexLabel eMBLTypeLabel;
	public PropertyKey eMBLIdKey;
	public PropertyKey eMBLMoleculeTypeKey;
	public PropertyKey eMBLProteinSequenceIdKey;
	public EMBLType eMBLType;
	//---Patent---
	public VertexLabel patentTypeLabel;
	public PropertyKey patentTitleKey;
	public PropertyKey patentNumberKey;
	public PatentType patentType;
	//---PIR---
	public VertexLabel pIRTypeLabel;
	public PropertyKey pIRIdKey;
	public PropertyKey pIREntryNameKey;
	public PIRType pIRType;
	//---UniGene---
	public VertexLabel uniGeneTypeLabel;
	public PropertyKey uniGeneIdKey;
	public UniGeneType uniGeneType;
	//---Ensembl---
	public VertexLabel ensemblTypeLabel;
	public PropertyKey ensemblIdKey;
	public PropertyKey ensemblMoleculeIdKey;
	public PropertyKey ensemblProteinSequenceIdKey;
	public PropertyKey ensemblGeneIdKey;
	public EnsemblType ensemblType;
	//---Taxon---
	public VertexLabel taxonTypeLabel;
	public PropertyKey taxonNameKey;
	public TaxonType taxonType;
	//---Thesis---
	public VertexLabel thesisTypeLabel;
	public PropertyKey thesisTitleKey;
	public ThesisType thesisType;
	//----OnlineArticle-----
	public VertexLabel onlineArticleTypeLabel;
	public PropertyKey onlineArticleTitleKey;
	public OnlineArticleType onlineArticleType;
	//----OnlineJournal-----
	public VertexLabel onlineJournalTypeLabel;
	public PropertyKey onlineJournalNameKey;
	public OnlineJournalType onlineJournalType;
	//---RefSeq---
	public VertexLabel refSeqTypeLabel;
	public PropertyKey refSeqIdKey;
	public PropertyKey refSeqNucleotideSequenceIdKey;
	public RefSeqType refSeqType;
	//---Reference---
	public PropertyKey referenceDateKey;
	public ReferenceType referenceType;
	//---SequenceCaution----
	public VertexLabel sequenceCautionTypeLabel;
	public PropertyKey sequenceCautionNameKey;
	public SequenceCautionType sequenceCautionType;
	//---SubcellularLocation----
	public VertexLabel subcellularLocationTypeLabel;
	public PropertyKey subcellularLocationNameKey;
	public SubcellularLocationType subcellularLocationType;
	//---Submission----
	public VertexLabel submissionTypeLabel;
	public PropertyKey submissionTitleKey;
	public SubmissionType submissionType;
	//---FeatureType---
	public VertexLabel featureTypeTypeLabel;
	public PropertyKey featureTypeNameKey;
	public FeatureTypeType featureTypeType;
	//---CommentType---
	public VertexLabel commentTypeTypeLabel;
	public PropertyKey commentTypeNameKey;
	public CommentTypeType commentTypeType;
	//---UnpublishedObservation----
	public PropertyKey unpublishedObservationScopeKey;
	public UnpublishedObservationType unpublishedObservationType;


	//-----------------------------------------
	//---------------INDICES---------------------------
	TitanTypedVertexIndex.DefaultUnique<Protein<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, ProteinType,ProteinType.accession, String,UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,DefaultTitanGraph> proteinAccessionIndex;
	TitanTypedVertexIndex.DefaultUnique<Dataset<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DatasetType, DatasetType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> datasetNameIndex;
	TitanTypedVertexIndex.DefaultUnique<Organism<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, OrganismType, OrganismType.scientificName, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> organismScientificNameIndex;
	TitanTypedVertexIndex.DefaultUnique<Keyword<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, KeywordType, KeywordType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> keywordIdIndex;
	TitanTypedVertexIndex.DefaultUnique<ReactomeTerm<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, ReactomeTermType, ReactomeTermType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> reactomeTermIdIndex;
	TitanTypedVertexIndex.DefaultUnique<Interpro<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, InterproType, InterproType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> interproIdIndex;
	TitanTypedVertexIndex.DefaultUnique<Pfam<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, PfamType, PfamType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> pfamIdIndex;
	TitanTypedVertexIndex.DefaultUnique<Kegg<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, KeggType, KeggType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> keggIdIndex;
	TitanTypedVertexIndex.DefaultUnique<EMBL<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, EMBLType, EMBLType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> eMBLIdIndex;
	TitanTypedVertexIndex.DefaultUnique<PIR<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, PIRType, PIRType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> pIRIdIndex;
	TitanTypedVertexIndex.DefaultUnique<UniGene<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, UniGeneType, UniGeneType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> uniGeneIdIndex;
	TitanTypedVertexIndex.DefaultUnique<Ensembl<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, EnsemblType, EnsemblType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> ensemblIdIndex;
	TitanTypedVertexIndex.DefaultUnique<Taxon<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, TaxonType, TaxonType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> taxonNameIndex;
	TitanTypedVertexIndex.DefaultUnique<RefSeq<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, RefSeqType, RefSeqType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> refSeqIdIndex;
	TitanTypedVertexIndex.DefaultUnique<CommentType<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, CommentTypeType, CommentTypeType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> commentTypeNameIndex;
	TitanTypedVertexIndex.DefaultUnique<FeatureType<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, FeatureTypeType, FeatureTypeType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> featureTypeNameIndex;
	TitanTypedVertexIndex.DefaultUnique<Journal<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, JournalType, JournalType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> journalNameIndex;
	TitanTypedVertexIndex.DefaultUnique<Article<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, ArticleType, ArticleType.title, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> articleTitleIndex;
	TitanTypedVertexIndex.DefaultUnique<OnlineJournal<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, OnlineJournalType, OnlineJournalType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> onlineJournalNameIndex;
	TitanTypedVertexIndex.DefaultUnique<OnlineArticle<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, OnlineArticleType, OnlineArticleType.title, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> onlineArticleTitleIndex;
	TitanTypedVertexIndex.DefaultUnique<Pubmed<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, PubmedType, PubmedType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> pubmedIdIndex;
	TitanTypedVertexIndex.DefaultUnique<Person<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, PersonType, PersonType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> personNameIndex;
	TitanTypedVertexIndex.DefaultUnique<Thesis<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, ThesisType, ThesisType.title, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> thesisTitleIndex;
	TitanTypedVertexIndex.DefaultUnique<Consortium<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, ConsortiumType, ConsortiumType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> consortiumNameIndex;
	TitanTypedVertexIndex.DefaultUnique<Institute<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, InstituteType, InstituteType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> instituteNameIndex;
	TitanTypedVertexIndex.DefaultUnique<Submission<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, SubmissionType, SubmissionType.title, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> submissionTitleIndex;
	TitanTypedVertexIndex.DefaultUnique<Patent<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, PatentType, PatentType.number, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> patentNumberIndex;
	TitanTypedVertexIndex.DefaultUnique<City<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, CityType, CityType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> cityNameIndex;
	TitanTypedVertexIndex.DefaultUnique<Country<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, CountryType, CountryType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> countryNameIndex;
	TitanTypedVertexIndex.DefaultUnique<Publisher<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, PublisherType, PublisherType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> publisherNameIndex;
	TitanTypedVertexIndex.DefaultUnique<Book<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, BookType, BookType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> bookNameIndex;
	TitanTypedVertexIndex.DefaultUnique<DB<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DBType, DBType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> dbNameIndex;
	TitanTypedVertexIndex.DefaultUnique<Disease<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DiseaseType, DiseaseType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> diseaseIdIndex;
	TitanTypedVertexIndex.DefaultUnique<SubcellularLocation<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, SubcellularLocationType, SubcellularLocationType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> subcellularLocationNameIndex;
	TitanTypedVertexIndex.DefaultUnique<Isoform<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, IsoformType, IsoformType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> isoformIdIndex;
	TitanTypedVertexIndex.DefaultUnique<SequenceCaution<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, SequenceCautionType, SequenceCautionType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> sequenceCautionNameIndex;
	TitanTypedVertexIndex.DefaultUnique<GeneLocation<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, GeneLocationType, GeneLocationType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> geneLocationNameIndex;
	TitanTypedVertexIndex.DefaultUnique<AlternativeProduct<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, AlternativeProductType, AlternativeProductType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph> alternativeProductNameIndex;

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
	private TitanUniprotGoGraph uniprotGoGraph;
	private TitanUniprotUniRefGraph uniprotUniRefGraph;
	private TitanUniprotNCBITaxonomyGraph uniprotNCBITaxonomyGraph;
	private TitanUniprotEnzymeGraph uniprotEnzymeGraph;
	private TitanManagement mgmt;

	public TitanUniprotGraph(DefaultTitanGraph rawGraph) {

		super(rawGraph);

		// First get a titanMgmt instance, that will be used throughout
		this.mgmt = rawGraph.managementSystem();
		initTypes(mgmt);
		initIndices(mgmt);
	}

	public TitanManagement managementSystem() {
		return this.mgmt;
	}

	@Override
	public DefaultTitanGraph raw() {
		return raw;
	}

	private void initTypes(TitanManagement mgmt) {

		//-----------------------------------------------------------------------------------------
		//--------------------------------VERTICES--------------------------------------------


		// the tricky part is initializing the label part
		// first create all label and prop makers
		VertexLabelMaker proteinTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new ProteinType(null));
		//Protein type
		proteinType = new ProteinType(proteinTypeLabelMaker);
		proteinAcessionKey = raw().createOrGet(mgmt,
				raw().titanPropertyMakerForVertexProperty(mgmt, Protein().accession).cardinality(Cardinality.SINGLE)
		);
		proteinNameKey = raw().createOrGet(mgmt,
				raw().titanPropertyMakerForVertexProperty(mgmt, Protein().name).cardinality(Cardinality.SINGLE)
		);
		proteinShortNameKey = raw().createOrGet(mgmt,
				raw().titanPropertyMakerForVertexProperty(mgmt, Protein().shortName).cardinality(Cardinality.SINGLE)
		);
		proteinFullNameKey = raw().createOrGet(mgmt,
				raw().titanPropertyMakerForVertexProperty(mgmt, Protein().fullName).cardinality(Cardinality.SINGLE)
		);
		proteinModifiedDateKey = raw().createOrGet(mgmt,
				raw().titanPropertyMakerForVertexProperty(mgmt, Protein().modifiedDate).cardinality(Cardinality.SINGLE)
		);
		proteinCreatedDateKey = raw().createOrGet(mgmt,
				raw().titanPropertyMakerForVertexProperty(mgmt, Protein().createdDate).cardinality(Cardinality.SINGLE)
		);
		proteinMassKey = raw().createOrGet(mgmt,
				raw().titanPropertyMakerForVertexProperty(mgmt, Protein().mass).cardinality(Cardinality.SINGLE)
		);
		proteinVersionKey = raw().createOrGet(mgmt,
				raw().titanPropertyMakerForVertexProperty(mgmt, Protein().version).cardinality(Cardinality.SINGLE)
		);
		proteinLengthKey = raw().createOrGet(mgmt,
				raw().titanPropertyMakerForVertexProperty(mgmt, Protein().length).cardinality(Cardinality.SINGLE)
		);
		proteinSequenceKey = raw().createOrGet(mgmt,
				raw().titanPropertyMakerForVertexProperty(mgmt, Protein().sequence).cardinality(Cardinality.SINGLE)
		);

		// create everything
		this.proteinTypeLabel = raw().createOrGet(mgmt, proteinType.raw());

		//--------- Alternative Product type-------------
		VertexLabelMaker alternativeProductTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new AlternativeProductType(null));
		alternativeProductType = new AlternativeProductType(alternativeProductTypeLabelMaker);
		// alternative product props
		alternativeProductNameKey = raw().createOrGet(mgmt,
				raw().titanPropertyMakerForVertexProperty(mgmt, AlternativeProduct().name).cardinality(Cardinality.SINGLE)
		);
		this.alternativeProductTypeLabel = raw().createOrGet(mgmt, alternativeProductType.raw());

		//--------- Article keys-------------
		VertexLabelMaker articleTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new ArticleType(null));
		articleType = new ArticleType(articleTypeLabelMaker);
		articleTitleKey = raw().createOrGet(mgmt,
				raw().titanPropertyMakerForVertexProperty(mgmt, Article().title).cardinality(Cardinality.SINGLE)
		);
		articleDoIdKey = raw().createOrGet(mgmt,
				raw().titanPropertyMakerForVertexProperty(mgmt, Article().doId).cardinality(Cardinality.SINGLE)
		);
		articleTypeLabel = raw().createOrGet(mgmt, articleType.raw());

		//------------- Book keys---------------
		VertexLabelMaker bookTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new BookType(null));
		bookType = new BookType(bookTypeLabelMaker);
		bookNameKey = raw().createOrGet(mgmt,
				raw().titanPropertyMakerForVertexProperty(mgmt, Book().name).cardinality(Cardinality.SINGLE)
		);
		bookTypeLabel = raw().createOrGet(mgmt, bookType.raw());


		//------------- City keys--------------------
		VertexLabelMaker cityTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new CityType(null));
		cityType = new CityType(cityTypeLabelMaker);
		cityNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, City().name).cardinality(Cardinality.SINGLE));
		cityTypeLabel = raw().createOrGet(mgmt, cityType.raw());

		//------------ GeneLocation keys-----------------------
		VertexLabelMaker geneLocationTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new GeneLocationType(null));
		geneLocationType = new GeneLocationType(geneLocationTypeLabelMaker);
		geneLocationNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, GeneLocation().name).cardinality(Cardinality.SINGLE));
		geneLocationTypeLabel = raw().createOrGet(mgmt, geneLocationType.raw());

		//------------ Consortium keys---------------------------
		VertexLabelMaker consortiumTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new ConsortiumType(null));
		consortiumType = new ConsortiumType(consortiumTypeLabelMaker);
		consortiumNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Consortium().name).cardinality(Cardinality.SINGLE));
		consortiumTypeLabel = raw().createOrGet(mgmt, consortiumType.raw());

		//------------ Country keys ------------------------
		VertexLabelMaker countryTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new CountryType(null));
		countryType = new CountryType(countryTypeLabelMaker);
		countryNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Country().name).cardinality(Cardinality.SINGLE));
		countryTypeLabel = raw().createOrGet(mgmt, countryType.raw());

		//--------------- DB keys---------------------------
		VertexLabelMaker dbTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new DBType(null));
		dbType = new DBType(dbTypeLabelMaker);
		dbNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, DB().name).cardinality(Cardinality.SINGLE));
		dbTypeLabel = raw().createOrGet(mgmt, dbType.raw());

		//------------ Dataset keys----------------------
		VertexLabelMaker datasetTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new DatasetType(null));
		datasetType = new DatasetType(datasetTypeLabelMaker);
		datasetNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Dataset().name).cardinality(Cardinality.SINGLE));
		datasetTypeLabel = raw().createOrGet(mgmt, datasetType.raw());

		//----------------- Disease keys----------------------
		VertexLabelMaker diseaseTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new DiseaseType(null));
		diseaseType = new DiseaseType(diseaseTypeLabelMaker);
		diseaseIdKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Disease().id).cardinality(Cardinality.SINGLE));
		diseaseNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Disease().name).cardinality(Cardinality.SINGLE));
		diseaseAcronymKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Disease().acronym).cardinality(Cardinality.SINGLE));
		diseaseDescriptionKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Disease().description).cardinality(Cardinality.SINGLE));
		datasetTypeLabel = raw().createOrGet(mgmt, diseaseType.raw());

		//------------------ Institute keys--------------------
		VertexLabelMaker instituteTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new InstituteType(null));
		instituteType = new InstituteType(instituteTypeLabelMaker);
		instituteNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Institute().name).cardinality(Cardinality.SINGLE));
		instituteTypeLabel = raw().createOrGet(mgmt, instituteType.raw());

		//---------------- Organism keys-------------------------
		VertexLabelMaker organismTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new OrganismType(null));
		organismType = new OrganismType(organismTypeLabelMaker);
		organismScientificNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Organism().scientificName).cardinality(Cardinality.SINGLE));
		organismCommonNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Organism().commonName).cardinality(Cardinality.SINGLE));
		organismSynonymNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Organism().synonymName).cardinality(Cardinality.SINGLE));
		organismTypeLabel = raw().createOrGet(mgmt, organismType.raw());

		//---------------- Keyword keys----------------------
		VertexLabelMaker keyboardTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new KeywordType(null));
		keywordType = new KeywordType(keyboardTypeLabelMaker);
		keywordIdKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Keyword().id).cardinality(Cardinality.SINGLE));
		keywordNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Keyword().name).cardinality(Cardinality.SINGLE));
		keywordTypeLabel = raw().createOrGet(mgmt, keywordType.raw());

		//----------------- Interpro keys------------------------
		VertexLabelMaker interproTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new InterproType(null));
		interproType = new InterproType(interproTypeLabelMaker);
		interproIdKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Interpro().id).cardinality(Cardinality.SINGLE));
		interproNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Interpro().name).cardinality(Cardinality.SINGLE));
		interproTypeLabel = raw().createOrGet(mgmt, interproType.raw());

		//----------------- Isoform keys--------------------------
		VertexLabelMaker isoformTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new IsoformType(null));
		isoformType = new IsoformType(isoformTypeLabelMaker);
		isoformIdKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Isoform().id).cardinality(Cardinality.SINGLE));
		isoformNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Isoform().name).cardinality(Cardinality.SINGLE));
		isoformSequenceKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Isoform().sequence).cardinality(Cardinality.SINGLE));
		isoformNoteKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Isoform().note).cardinality(Cardinality.SINGLE));
		isoformTypeLabel = raw().createOrGet(mgmt, isoformType.raw());

		//---------------- Journal keys------------------------
		VertexLabelMaker journalTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new JournalType(null));
		journalType = new JournalType(journalTypeLabelMaker);
		journalNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Journal().name).cardinality(Cardinality.SINGLE));
		journalTypeLabel = raw().createOrGet(mgmt, journalType.raw());

		// ---------------- OnlineArticle keys-----------------------
		VertexLabelMaker onlineArticleTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new OnlineArticleType(null));
		onlineArticleType = new OnlineArticleType(onlineArticleTypeLabelMaker);
		onlineArticleTitleKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, OnlineArticle().title).cardinality(Cardinality.SINGLE));
		onlineArticleTypeLabel = raw().createOrGet(mgmt, onlineArticleType.raw());

		//------------------- OnlineJournal keys------------------------
		VertexLabelMaker onlineJournalTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new OnlineJournalType(null));
		onlineJournalType = new OnlineJournalType(onlineJournalTypeLabelMaker);
		onlineJournalNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, OnlineJournal().name).cardinality(Cardinality.SINGLE));
		onlineJournalTypeLabel = raw().createOrGet(mgmt, onlineJournalType.raw());

		//------------ Reference keys-----------------
		referenceType = new ReferenceType(null);
		referenceDateKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Reference().date).cardinality(Cardinality.SINGLE));

		//------------ ReactomeTerm keys----------------------
		VertexLabelMaker reactomeTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new ReactomeTermType(null));
		reactomeTermType = new ReactomeTermType(reactomeTypeLabelMaker);
		reactomeTermIdKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, ReactomeTerm().id).cardinality(Cardinality.SINGLE));
		reactomeTermPathwayNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, ReactomeTerm().pathwayName).cardinality(Cardinality.SINGLE));
		reactomeTermTypeLabel = raw().createOrGet(mgmt, reactomeTermType.raw());

		//------------------- Publisher keys ----------------------------
		VertexLabelMaker publisherTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new PublisherType(null));
		publisherType = new PublisherType(publisherTypeLabelMaker);
		publisherNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Publisher().name).cardinality(Cardinality.SINGLE));
		publisherTypeLabel = raw().createOrGet(mgmt, publisherType.raw());

		// ----------------------- Person keys --------------------------
		VertexLabelMaker personTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new PersonType(null));
		personType = new PersonType(personTypeLabelMaker);
		personNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Person().name).cardinality(Cardinality.SINGLE));
		personTypeLabel = raw().createOrGet(mgmt, personType.raw());

		//----------------- Pfam keys ------------------------------
		VertexLabelMaker pfamTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new PfamType(null));
		pfamType = new PfamType(pfamTypeLabelMaker);
		pfamIdKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Pfam().id).cardinality(Cardinality.SINGLE));
		pfamNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Pfam().name).cardinality(Cardinality.SINGLE));
		pfamTypeLabel = raw().createOrGet(mgmt, pfamType.raw());

		//--------------- Pubmed keys ---------------------
		VertexLabelMaker pubmedTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new PubmedType(null));
		pubmedType = new PubmedType(pubmedTypeLabelMaker);
		pubmedIdKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Pubmed().id).cardinality(Cardinality.SINGLE));
		pubmedTypeLabel = raw().createOrGet(mgmt, pubmedType.raw());

		//----------------- Submission keys ------------------------
		VertexLabelMaker submissionTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new SubmissionType(null));
		submissionType = new SubmissionType(submissionTypeLabelMaker);
		submissionTitleKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Submission().title).cardinality(Cardinality.SINGLE));
		submissionTypeLabel = raw().createOrGet(mgmt, submissionType.raw());

		//------------------- Thesis keys ---------------------
		VertexLabelMaker thesisTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new ThesisType(null));
		thesisType = new ThesisType(thesisTypeLabelMaker);
		thesisTitleKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Thesis().title).cardinality(Cardinality.SINGLE));
		thesisTypeLabel = raw().createOrGet(mgmt, thesisType.raw());

		//-------------------- EMBL keys --------------------------
		VertexLabelMaker eMBLTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new EMBLType(null));
		eMBLType = new EMBLType(eMBLTypeLabelMaker);
		eMBLIdKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, EMBL().id).cardinality(Cardinality.SINGLE));
		eMBLMoleculeTypeKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, EMBL().moleculeType).cardinality(Cardinality.SINGLE));
		eMBLProteinSequenceIdKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, EMBL().proteinSequenceId).cardinality(Cardinality.SINGLE));
		eMBLTypeLabel = raw().createOrGet(mgmt, eMBLType.raw());

		//--------------------- PIR keys ---------------------------
		VertexLabelMaker pIRTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new PIRType(null));
		pIRType = new PIRType(pIRTypeLabelMaker);
		pIRIdKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, PIR().id).cardinality(Cardinality.SINGLE));
		pIREntryNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, PIR().entryName).cardinality(Cardinality.SINGLE));
		pIRTypeLabel = raw().createOrGet(mgmt, pIRType.raw());

		// --------------------- Patent keys--------------------------
		VertexLabelMaker patentTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new PatentType(null));
		patentType = new PatentType(patentTypeLabelMaker);
		patentNumberKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Patent().number).cardinality(Cardinality.SINGLE));
		patentTitleKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Patent().title).cardinality(Cardinality.SINGLE));
		patentTypeLabel = raw().createOrGet(mgmt, patentType.raw());

		// -------------------  Ensembl keys-------------------------
		VertexLabelMaker ensemblTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new EnsemblType(null));
		ensemblType = new EnsemblType(ensemblTypeLabelMaker);
		ensemblIdKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Ensembl().id).cardinality(Cardinality.SINGLE));
		ensemblMoleculeIdKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Ensembl().moleculeId).cardinality(Cardinality.SINGLE));
		ensemblProteinSequenceIdKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Ensembl().proteinSequenceId).cardinality(Cardinality.SINGLE));
		ensemblGeneIdKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Ensembl().geneId).cardinality(Cardinality.SINGLE));
		ensemblTypeLabel = raw().createOrGet(mgmt, ensemblType.raw());

		//----------UniGene------------------
		VertexLabelMaker uniGeneTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new UniGeneType(null));
		uniGeneType = new UniGeneType(uniGeneTypeLabelMaker);
		uniGeneIdKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UniGene().id).cardinality(Cardinality.SINGLE));
		uniGeneTypeLabel = raw().createOrGet(mgmt, uniGeneType.raw());

		//-------- --SubcellularLocation----------------------
		VertexLabelMaker subcellularLocationTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new SubcellularLocationType(null));
		subcellularLocationType = new SubcellularLocationType(subcellularLocationTypeLabelMaker);
		subcellularLocationNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, SubcellularLocation().name).cardinality(Cardinality.SINGLE));
		subcellularLocationTypeLabel = raw().createOrGet(mgmt, subcellularLocationType.raw());

		//---Kegg---
		VertexLabelMaker keggTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new KeggType(null));
		keggType = new KeggType(keggTypeLabelMaker);
		keggIdKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Kegg().id).cardinality(Cardinality.SINGLE));
		keggTypeLabel = raw().createOrGet(mgmt, keggType.raw());

		//---Taxon---
		VertexLabelMaker taxonTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new TaxonType(null));
		taxonType = new TaxonType(taxonTypeLabelMaker);
		taxonNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, Taxon().name).cardinality(Cardinality.SINGLE));
		taxonTypeLabel = raw().createOrGet(mgmt, taxonType.raw());

		//---RefSeq---
		VertexLabelMaker refSeqTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new RefSeqType(null));
		refSeqType = new RefSeqType(refSeqTypeLabelMaker);
		refSeqIdKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, RefSeq().id).cardinality(Cardinality.SINGLE));
		refSeqNucleotideSequenceIdKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, RefSeq().nucleotideSequenceId).cardinality(Cardinality.SINGLE));
		refSeqTypeLabel = raw().createOrGet(mgmt, refSeqType.raw());

		//---SequenceCaution---
		VertexLabelMaker sequenceCautionTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new SequenceCautionType(null));
		sequenceCautionType = new SequenceCautionType(sequenceCautionTypeLabelMaker);
		sequenceCautionNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, SequenceCaution().name).cardinality(Cardinality.SINGLE));
		sequenceCautionTypeLabel = raw().createOrGet(mgmt, sequenceCautionType.raw());

		//---Comment---
		VertexLabelMaker commentTypeTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new CommentTypeType(null));
		commentTypeType = new CommentTypeType(commentTypeTypeLabelMaker);
		commentTypeNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, CommentType().name).cardinality(Cardinality.SINGLE));
		commentTypeTypeLabel = raw().createOrGet(mgmt, commentTypeType.raw());

		//---Feature---
		VertexLabelMaker featureTypeTypeLabelMaker = raw().titanLabelMakerForVertexType(mgmt, new FeatureTypeType(null));
		featureTypeType = new FeatureTypeType(featureTypeTypeLabelMaker);
		featureTypeNameKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, FeatureType().name).cardinality(Cardinality.SINGLE));
		featureTypeTypeLabel = raw().createOrGet(mgmt, featureTypeType.raw());

		//---UnpublishedObservation
		unpublishedObservationType = new UnpublishedObservationType(null);
		unpublishedObservationScopeKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForVertexProperty(mgmt, UnpublishedObservation().scope).cardinality(Cardinality.SINGLE));

		// TODO: keep going

		//-----------------------------------------------------------------------------------------
		//--------------------------------EDGES--------------------------------------------

		// articlePubmed
		EdgeLabelMaker articlePubmedTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new ArticlePubmedType(null));
		articlePubmedType = new ArticlePubmedType(articlePubmedTypeLabelMaker);
		articlePubmedLabel = raw().createOrGet(mgmt, articlePubmedType.raw());

		// articleJournal
		EdgeLabelMaker articleJournalTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new ArticleJournalType(null));
		articleJournalType = new ArticleJournalType(articleJournalTypeLabelMaker);
	    articleJournalVolumeKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ArticleJournal().volume).cardinality(Cardinality.SINGLE));
	    articleJournalFirstKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ArticleJournal().first).cardinality(Cardinality.SINGLE));
	    articleJournalLastKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ArticleJournal().last).cardinality(Cardinality.SINGLE));
		articleJournalLabel = raw().createOrGet(mgmt, articleJournalType.raw());

        // bookCity
		EdgeLabelMaker bookCityTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new BookCityType(null));
		bookCityType = new BookCityType(bookCityTypeLabelMaker);
        bookCityLabel = raw().createOrGet(mgmt, bookCityType.raw());

	    // bookEditor
		EdgeLabelMaker bookEditorTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new BookEditorType(null));
	    bookEditorType = new BookEditorType(bookEditorTypeLabelMaker);
		bookEditorLabel = raw().createOrGet(mgmt, bookEditorType.raw());

        // bookPublisher
		EdgeLabelMaker bookPublisherTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new BookPublisherType(null));
        bookPublisherType = new BookPublisherType(bookPublisherTypeLabelMaker);
		bookPublisherLabel = raw().createOrGet(mgmt, bookPublisherType.raw());

        // instituteCountry
		EdgeLabelMaker instituteCountryTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new InstituteCountryType(null));
        instituteCountryType = new InstituteCountryType(instituteCountryTypeLabelMaker);
		instituteCountryLabel = raw().createOrGet(mgmt, instituteCountryType.raw());

	    // isoformEventGenerator
		EdgeLabelMaker isoformEventGeneratorTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new IsoformEventGeneratorType(null));
		isoformEventGeneratorType = new IsoformEventGeneratorType(isoformEventGeneratorTypeLabelMaker);
	    isoformEventGeneratorLabel = raw().createOrGet(mgmt, isoformEventGeneratorType.raw());

        // onlineArticleOnlineJournal
		EdgeLabelMaker onlineArticleOnlineJournalTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new OnlineArticleOnlineJournalType(null));
		onlineArticleOnlineJournalType = new OnlineArticleOnlineJournalType(onlineArticleOnlineJournalTypeLabelMaker);
		onlineArticleOnlineJournalLocatorKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, OnlineArticleOnlineJournal().locator).cardinality(Cardinality.SINGLE));
        onlineArticleOnlineJournalLabel = raw().createOrGet(mgmt, onlineArticleOnlineJournalType.raw());

        // proteinDataset
		EdgeLabelMaker proteinDatasetTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new ProteinDatasetType(null));
		proteinDatasetType = new ProteinDatasetType(proteinDatasetTypeLabelMaker);
        proteinDatasetLabel = raw().createOrGet(mgmt, proteinDatasetType.raw());

	    // proteinDisease
		EdgeLabelMaker proteinDiseaseTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new ProteinDiseaseType(null));
		proteinDiseaseType = new ProteinDiseaseType(proteinDiseaseTypeLabelMaker);
	    proteinDiseaseTextKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinDisease().text).cardinality(Cardinality.SINGLE));
	    proteinDiseaseStatusKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinDisease().status).cardinality(Cardinality.SINGLE));
	    proteinDiseaseEvidenceKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinDisease().evidence).cardinality(Cardinality.SINGLE));
		proteinDiseaseLabel = raw().createOrGet(mgmt, proteinDiseaseType.raw());

        // proteinOrganism
		EdgeLabelMaker proteinOrganismTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new ProteinOrganismType(null));
		proteinOrganismType = new ProteinOrganismType(proteinOrganismTypeLabelMaker);
        proteinOrganismLabel = raw().createOrGet(mgmt, proteinOrganismType.raw());

        // proteinKeyword
		EdgeLabelMaker proteinKeywordTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new ProteinKeywordType(null));
		proteinKeywordType = new ProteinKeywordType(proteinKeywordTypeLabelMaker);
        proteinKeywordLabel = raw().createOrGet(mgmt, proteinKeywordType.raw());

	    // proteinGeneLocation
		EdgeLabelMaker proteinGeneLocationTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new ProteinGeneLocationType(null));
	    proteinGeneLocationType = new ProteinGeneLocationType(proteinGeneLocationTypeLabelMaker);
		proteinGeneLocationLabel = raw().createOrGet(mgmt, proteinGeneLocationType.raw());

        // proteinInterpro
		EdgeLabelMaker proteinInterproTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new ProteinInterproType(null));
		proteinInterproType = new ProteinInterproType(proteinInterproTypeLabelMaker);
        proteinInterproLabel = raw().createOrGet(mgmt, proteinInterproType.raw());

        // proteinReactomeTerm
		EdgeLabelMaker proteinReactomeTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new ProteinReactomeTermType(null));
		proteinReactomeTermType = new ProteinReactomeTermType(proteinReactomeTypeLabelMaker);
        proteinReactomeTermLabel = raw().createOrGet(mgmt, proteinReactomeTermType.raw());

        // proteinPfam
		EdgeLabelMaker proteinPfamTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new ProteinPfamType(null));
		proteinPfamType = new ProteinPfamType(proteinPfamTypeLabelMaker);
        proteinPfamLabel = raw().createOrGet(mgmt, proteinPfamType.raw());

        // proteinKegg
		EdgeLabelMaker proteinKeggTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new ProteinKeggType(null));
		proteinKeggType = new ProteinKeggType(proteinKeggTypeLabelMaker);
        proteinKeggLabel = raw().createOrGet(mgmt, proteinKeggType.raw());

        // proteinKegg
		EdgeLabelMaker proteinPIRTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new ProteinPIRType(null));
		proteinPIRType = new ProteinPIRType(proteinPIRTypeLabelMaker);
        proteinPIRLabel = raw().createOrGet(mgmt, proteinPIRType.raw());

        // proteinEMBL
		EdgeLabelMaker proteinEMBLTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new ProteinEMBLType(null));
		proteinEMBLType = new ProteinEMBLType(proteinEMBLTypeLabelMaker);
        proteinEMBLLabel = raw().createOrGet(mgmt, proteinEMBLType.raw());

        // proteinEnsembl
		EdgeLabelMaker proteinEnsemblTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new ProteinEnsemblType(null));
		proteinEnsemblType = new ProteinEnsemblType(proteinEnsemblTypeLabelMaker);
        proteinEnsemblLabel = raw().createOrGet(mgmt, proteinEnsemblType.raw());

        // proteinRefSeq
		EdgeLabelMaker proteinRefSeqTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new ProteinRefSeqType(null));
		proteinRefSeqType = new ProteinRefSeqType(proteinRefSeqTypeLabelMaker);
        proteinRefSeqLabel = raw().createOrGet(mgmt, proteinRefSeqType.raw());

	    // proteinSequenceCaution
		EdgeLabelMaker proteinSequenceCautionTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new ProteinSequenceCautionType(null));
	    proteinSequenceCautionType = new ProteinSequenceCautionType(proteinSequenceCautionTypeLabelMaker);
	    proteinSequenceCautionStatusKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinSequenceCaution().status).cardinality(Cardinality.SINGLE));
	    proteinSequenceCautionIdKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinSequenceCaution().id).cardinality(Cardinality.SINGLE));
	    proteinSequenceCautionEvidenceKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinSequenceCaution().evidence).cardinality(Cardinality.SINGLE));
	    proteinSequenceCautionVersionKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinSequenceCaution().version).cardinality(Cardinality.SINGLE));
	    proteinSequenceCautionPositionKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinSequenceCaution().position).cardinality(Cardinality.SINGLE));
	    proteinSequenceCautionResourceKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinSequenceCaution().resource).cardinality(Cardinality.SINGLE));
	    proteinSequenceCautionTextKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinSequenceCaution().text).cardinality(Cardinality.SINGLE));
		proteinSequenceCautionLabel = raw().createOrGet(mgmt, proteinSequenceCautionType.raw());

	    // proteinSubcellularLocation
		EdgeLabelMaker proteinSubcellularLocationTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new ProteinSubcellularLocationType(null));
	    proteinSubcellularLocationType = new ProteinSubcellularLocationType(proteinSubcellularLocationTypeLabelMaker);
	    proteinSubcellularLocationStatusKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinSubcellularLocation().status).cardinality(Cardinality.SINGLE));
	    proteinSubcellularLocationEvidenceKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinSubcellularLocation().evidence).cardinality(Cardinality.SINGLE));
	    proteinSubcellularLocationTopologyKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinSubcellularLocation().topology).cardinality(Cardinality.SINGLE));
	    proteinSubcellularLocationTopologyStatusKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinSubcellularLocation().topologyStatus).cardinality(Cardinality.SINGLE));
		proteinSubcellularLocationLabel = raw().createOrGet(mgmt, proteinSubcellularLocationType.raw());

        // proteinUnigene
		EdgeLabelMaker proteinUniGeneTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new ProteinUniGeneType(null));
        proteinUniGeneType = new ProteinUniGeneType(proteinUniGeneTypeLabelMaker);
		proteinUniGeneLabel = raw().createOrGet(mgmt, proteinUniGeneType.raw());

        // taxonParent
		EdgeLabelMaker taxonParentTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new TaxonParentType(null));
        taxonParentType = new TaxonParentType(taxonParentTypeLabelMaker);
		taxonParentLabel = raw().createOrGet(mgmt, taxonParentType.raw());

        // organismTaxon
		EdgeLabelMaker organismTaxonTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new OrganismTaxonType(null));
		organismTaxonType = new OrganismTaxonType(organismTaxonTypeLabelMaker);
        organismTaxonLabel = raw().createOrGet(mgmt, organismTaxonType.raw());

        // proteinComment
		EdgeLabelMaker proteinCommentTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new OrganismTaxonType(null));
        proteinCommentType = new ProteinCommentType(proteinCommentTypeLabelMaker);
	    proteinCommentEvidenceKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinComment().evidence).cardinality(Cardinality.SINGLE));
	    proteinCommentStatusKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinComment().status).cardinality(Cardinality.SINGLE));
	    proteinCommentTextKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinComment().text).cardinality(Cardinality.SINGLE));
	    proteinCommentBeginKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinComment().begin).cardinality(Cardinality.SINGLE));
	    proteinCommentEndKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinComment().end).cardinality(Cardinality.SINGLE));
	    proteinCommentMassKey = rraw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinComment().mass).cardinality(Cardinality.SINGLE));
	    proteinCommentMethodKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinComment().method).cardinality(Cardinality.SINGLE));
	    proteinCommentAbsorptionMaxKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinComment().absorptionMax).cardinality(Cardinality.SINGLE));
	    proteinCommentAbsorptionTextKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinComment().absorptionText).cardinality(Cardinality.SINGLE));
	    proteinCommentKineticsXMLKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinComment().kineticsXML).cardinality(Cardinality.SINGLE));
	    proteinCommentPhDependenceKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinComment().phDependence).cardinality(Cardinality.SINGLE));
	    proteinCommentPositionKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinComment().position).cardinality(Cardinality.SINGLE));
	    proteinCommentRedoxPotentialKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinComment().redoxPotential).cardinality(Cardinality.SINGLE));
	    proteinCommentRedoxPotentialEvidenceKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinComment().redoxPotentialEvidence).cardinality(Cardinality.SINGLE));
	    proteinCommentTemperatureDependenceKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinComment().temperatureDependence).cardinality(Cardinality.SINGLE));
		proteinCommentLabel = raw().createOrGet(mgmt, proteinCommentType.raw());

        // proteinFeature
		EdgeLabelMaker proteinFeatureTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new ProteinFeatureType(null));
	    proteinFeatureType = new ProteinFeatureType(proteinFeatureTypeLabelMaker);
        proteinFeatureIdKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinFeature().id).cardinality(Cardinality.SINGLE));
        proteinFeatureDescriptionKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinFeature().description).cardinality(Cardinality.SINGLE));
        proteinFeatureEvidenceKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinFeature().evidence).cardinality(Cardinality.SINGLE));
        proteinFeatureStatusKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinFeature().status).cardinality(Cardinality.SINGLE));
        proteinFeatureBeginKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinFeature().begin).cardinality(Cardinality.SINGLE));
        proteinFeatureEndKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinFeature().end).cardinality(Cardinality.SINGLE));
        proteinFeatureOriginalKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinFeature().original).cardinality(Cardinality.SINGLE));
        proteinFeatureVariationKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinFeature().variation).cardinality(Cardinality.SINGLE));
        proteinFeatureRefKey = raw().createOrGet(mgmt, raw().titanPropertyMakerForEdgeProperty(mgmt, ProteinFeature().ref).cardinality(Cardinality.SINGLE));
		proteinFeatureLabel = raw().createOrGet(mgmt, proteinFeatureType.raw());

		// proteinProteinInteraction
		EdgeLabelMaker proteinProteinInteractionTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new ProteinProteinInteractionType(null));
	    proteinProteinInteractionType = new ProteinProteinInteractionType(proteinProteinInteractionTypeLabelMaker);
		proteinProteinInteractionLabel = raw().createOrGet(mgmt, proteinProteinInteractionType.raw());

		// proteinIsoformInteraction
		EdgeLabelMaker proteinIsoformInteractionTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new ProteinIsoformInteractionType(null));
		proteinIsoformInteractionType = new ProteinIsoformInteractionType(proteinIsoformInteractionTypeLabelMaker);
	    proteinIsoformInteractionLabel = raw().createOrGet(mgmt, proteinIsoformInteractionType.raw());

		// proteinIsoform
		EdgeLabelMaker proteinIsoformTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new ProteinIsoformType(null));
		proteinIsoformType = new ProteinIsoformType(proteinIsoformTypeLabelMaker);
	    proteinIsoformLabel = raw().createOrGet(mgmt, proteinIsoformType.raw());

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

	private void initIndices(TitanManagement mgmt) {

		proteinAccessionIndex = new TitanTypedVertexIndex.DefaultUnique<>(mgmt, this, Protein().accession);
		proteinAccessionIndex.make(proteinTypeLabel);

		datasetNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Dataset().name);
		datasetNameIndex.make(datasetTypeLabel);

		organismScientificNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Organism().scientificName);
		organismScientificNameIndex.make(organismTypeLabel);

		keywordIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Keyword().id);
		keywordIdIndex.make(keywordTypeLabel);

		interproIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Interpro().id);
		interproIdIndex.make(interproTypeLabel);

		reactomeTermIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, ReactomeTerm().id);
		reactomeTermIdIndex.make(reactomeTermTypeLabel);

		pfamIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Pfam().id);
		pfamIdIndex.make(pfamTypeLabel);

		keggIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Kegg().id);
		keggIdIndex.make(keggTypeLabel);

		eMBLIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, EMBL().id);
		eMBLIdIndex.make(eMBLTypeLabel);

		pIRIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, PIR().id);
		pIRIdIndex.make(pIRTypeLabel);

		uniGeneIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, UniGene().id);
		uniGeneIdIndex.make(uniGeneTypeLabel);

		ensemblIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Ensembl().id);
		ensemblIdIndex.make(ensemblTypeLabel);

		taxonNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Taxon().name);
		taxonNameIndex.make(taxonTypeLabel);

		refSeqIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, RefSeq().id);
		refSeqIdIndex.make(refSeqTypeLabel);

		commentTypeNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, CommentType().name);
		commentTypeNameIndex.make(commentTypeTypeLabel);

		featureTypeNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, FeatureType().name);
		featureTypeNameIndex.make(featureTypeTypeLabel);

		consortiumNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Consortium().name);
		consortiumNameIndex.make(consortiumTypeLabel);

		personNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Person().name);
		personNameIndex.make(personTypeLabel);

		patentNumberIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Patent().number);
		patentNumberIndex.make(patentTypeLabel);

		submissionTitleIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Submission().title);
		submissionTitleIndex.make(submissionTypeLabel);

		instituteNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Institute().name);
		instituteNameIndex.make(instituteTypeLabel);

		thesisTitleIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Thesis().title);
		thesisTitleIndex.make(thesisTypeLabel);

		onlineArticleTitleIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, OnlineArticle().title);
		onlineArticleTitleIndex.make(onlineArticleTypeLabel);

		onlineJournalNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, OnlineJournal().name);
		onlineJournalNameIndex.make(onlineJournalTypeLabel);

		pubmedIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Pubmed().id);
		pubmedIdIndex.make(pubmedTypeLabel);

		cityNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, City().name);
		cityNameIndex.make(cityTypeLabel);

		countryNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Country().name);
		countryNameIndex.make(countryTypeLabel);

		publisherNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Publisher().name);
		publisherNameIndex.make(publisherTypeLabel);

		bookNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Book().name);
		bookNameIndex.make(bookTypeLabel);

		dbNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, DB().name);
		dbNameIndex.make(dbTypeLabel);

		articleTitleIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Article().title);
		articleTitleIndex.make(articleTypeLabel);

		journalNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Journal().name);
		journalNameIndex.make(journalTypeLabel);

		diseaseIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Disease().id);
		diseaseIdIndex.make(diseaseTypeLabel);

		subcellularLocationNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, SubcellularLocation().name);
		subcellularLocationNameIndex.make(subcellularLocationTypeLabel);

		isoformIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Isoform().id);
		isoformIdIndex.make(isoformTypeLabel);

		sequenceCautionNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, SequenceCaution().name);
		sequenceCautionNameIndex.make(sequenceCautionTypeLabel);

		geneLocationNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, GeneLocation().name);
		geneLocationNameIndex.make(geneLocationTypeLabel);

		alternativeProductNameIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, AlternativeProduct().name);
		alternativeProductNameIndex.make(alternativeProductTypeLabel);


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
	public UniprotUniRefGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> uniprotUniRefGraph() {
		return uniprotUniRefGraph;
	}

	@Override
	public UniprotGoGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> uniprotGoGraph() {
		return uniprotGoGraph;
	}

	@Override
	public UniprotEnzymeDBGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> uniprotEnzymeDBGraph() {
		return uniprotEnzymeGraph;
	}

	@Override
	public UniprotNCBITaxonomyGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> uniprotNCBITaxonomyGraph() {
		return uniprotNCBITaxonomyGraph;
	}

	@Override
	public TypedVertexIndex.Unique<AlternativeProduct<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, AlternativeProductType, AlternativeProductType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> alternativeProductNameIndex() {
		return alternativeProductNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<GeneLocation<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, GeneLocationType, GeneLocationType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> geneLocationNameIndex() {
		return geneLocationNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Disease<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DiseaseType, DiseaseType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> diseaseIdIndex() {
		return diseaseIdIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Journal<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, JournalType, JournalType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> journalNameIndex() {
		return journalNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Article<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, ArticleType, ArticleType.title, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> articleTitleIndex() {
		return articleTitleIndex;
	}

	@Override
	public TypedVertexIndex.Unique<OnlineJournal<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, OnlineJournalType, OnlineJournalType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> onlineJournalNameIndex() {
		return onlineJournalNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<OnlineArticle<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, OnlineArticleType, OnlineArticleType.title, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> onlineArticleTitleIndex() {
		return onlineArticleTitleIndex;
	}

	@Override
	public TypedVertexIndex.Unique<City<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, CityType, CityType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> cityNameIndex() {
		return cityNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Publisher<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, PublisherType, PublisherType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> publisherNameIndex() {
		return publisherNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Book<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, BookType, BookType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> bookNameIndex() {
		return bookNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<DB<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DBType, DBType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> dbNameIndex() {
		return dbNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Country<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, CountryType, CountryType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> countryNameIndex() {
		return countryNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Patent<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, PatentType, PatentType.number, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> patentNumberIndex() {
		return patentNumberIndex;
	}

	@Override
	public TypedVertexIndex.Unique<SequenceCaution<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, SequenceCautionType, SequenceCautionType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> sequenceCautionNameIndex() {
		return sequenceCautionNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Submission<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, SubmissionType, SubmissionType.title, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> submissionTitleIndex() {
		return submissionTitleIndex;
	}

	@Override
	public TypedVertexIndex.Unique<SubcellularLocation<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, SubcellularLocationType, SubcellularLocationType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> subcellularLocationNameIndex() {
		return subcellularLocationNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Institute<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, InstituteType, InstituteType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> instituteNameIndex() {
		return instituteNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Isoform<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, IsoformType, IsoformType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> isoformIdIndex() {
		return isoformIdIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Consortium<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, ConsortiumType, ConsortiumType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> consortiumNameIndex() {
		return consortiumNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Thesis<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, ThesisType, ThesisType.title, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> thesisTitleIndex() {
		return thesisTitleIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Person<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, PersonType, PersonType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> personNameIndex() {
		return personNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Protein<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, ProteinType, ProteinType.accession, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> proteinAccessionIndex() {
		return proteinAccessionIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Ensembl<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
			EnsemblType, EnsemblType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> ensemblIdIndex() {
		return ensemblIdIndex;
	}

	@Override
	public TypedVertexIndex.Unique<PIR<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
			PIRType, PIRType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> pIRIdIndex() {
		return pIRIdIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Pubmed<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, PubmedType, PubmedType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> pubmedIdIndex() {
		return pubmedIdIndex;
	}

	@Override
	public TypedVertexIndex.Unique<UniGene<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
			UniGeneType, UniGeneType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> uniGeneIdIndex() {
		return uniGeneIdIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Kegg<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
			KeggType, KeggType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> keggIdIndex() {
		return keggIdIndex;
	}

	@Override
	public TypedVertexIndex.Unique<EMBL<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
			EMBLType, EMBLType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> eMBLIdIndex() {
		return eMBLIdIndex;
	}

	@Override
	public TypedVertexIndex.Unique<RefSeq<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
			RefSeqType, RefSeqType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> refSeqIdIndex() {
		return refSeqIdIndex;
	}

	@Override
	public TypedVertexIndex.Unique<ReactomeTerm<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
			ReactomeTermType, ReactomeTermType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> reactomeTermIdIndex() {
		return reactomeTermIdIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Dataset<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
			DatasetType, DatasetType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> datasetNameIndex() {
		return datasetNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Keyword<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
			KeywordType, KeywordType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> keywordIdIndex() {
		return keywordIdIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Interpro<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
			InterproType, InterproType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> interproIdIndex() {
		return interproIdIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Pfam<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
			PfamType, PfamType.id, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> pfamIdIndex() {
		return pfamIdIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Organism<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
			OrganismType, OrganismType.scientificName, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> organismScientificNameIndex() {
		return organismScientificNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<Taxon<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
			TaxonType, TaxonType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> taxonNameIndex() {
		return taxonNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<FeatureType<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
			FeatureTypeType, FeatureTypeType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> featureTypeNameIndex() {
		return featureTypeNameIndex;
	}

	@Override
	public TypedVertexIndex.Unique<CommentType<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, CommentTypeType, CommentTypeType.name, String, UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> commentTypeNameIndex() {
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
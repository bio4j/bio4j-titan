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
    //---country---
    public TitanKey countryTypeKey;
    public TitanKey countryNameKey;
    public CountryType countryType;
    //---dataset---
    public TitanKey datasetTypeKey;
    public TitanKey datasetNameKey;
    public DatasetType datasetType;
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
    //----institute-----
    public TitanKey instituteTypeKey;
    public TitanKey instituteNameKey;
    public InstituteType instituteType;
    //---journal---
    public TitanKey journalTypeKey;
    public TitanKey journalNameKey;
    public JournalType journalType;
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
    public TitanKey thesisNameKey;
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
    public TitanKey referenceTypeKey;
    public TitanKey referenceNameKey;
    public ReferenceType referenceType;
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
    // proteinReference
    public TitanLabel proteinReferenceLabel;
    public ProteinReferenceType proteinReferenceType;
    // proteinSubcellularLocation
    public TitanLabel proteinSubcellularLocationLabel;
    public ProteinSubcellularLocationType proteinSubcellularLocationType;

    // articlePubmed
    public TitanLabel articlePubmedLabel;
    public ArticlePubmedType articlePubmedType;
    // taxonParent
    public TitanLabel taxonParentLabel;
    public TaxonParentType taxonParentType;
    // organismTaxon
    public TitanLabel organismTaxonLabel;
    public OrganismTaxonType organismTaxonType;
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
        proteinTypekey = raw().titanKeyForVertexType(Protein().accession);
        proteinAcessionKey = proteinTypekey;
        proteinNameKey = titanKeyForVertexProperty(Protein().name);
        proteinShortNameKey = titanKeyForVertexProperty(Protein().shortName);
        proteinFullNameKey = titanKeyForVertexProperty(Protein().fullName);
        proteinModifiedDateKey = titanKeyForVertexProperty(Protein().modifiedDate);
        proteinCreatedDateKey = titanKeyForVertexProperty(Protein().createdDate);
        proteinMassKey = titanKeyForVertexProperty(Protein().mass);
        proteinVersionKey = titanKeyForVertexProperty(Protein().version);
        proteinLengthKey = titanKeyForVertexProperty(Protein().length);
        proteinSequenceKey = titanKeyForVertexProperty(Protein().sequence);
        proteinType = new ProteinType(proteinTypekey);
        // Article keys
        articleTypeKey = titanKeyForVertexType(articleType.title);
        articleTitleKey = articleTypeKey;
        articleDoIdKey = titanKeyForVertexProperty(articleType.doId);
        articleType = new ArticleType(articleTypeKey);
        // Book keys
        bookTypeKey = titanKeyForVertexType(bookType.name);
        bookNameKey = bookTypeKey;
        bookType = new BookType(bookTypeKey);
        // City keys
        cityTypeKey = titanKeyForVertexType(cityType.name);
        cityNameKey = cityTypeKey;
        cityType = new CityType(cityTypeKey);
        // Country keys
        countryTypeKey = titanKeyForVertexType(countryType.name);
        countryNameKey = countryTypeKey;
        countryType = new CountryType(countryTypeKey);
        // DB keys
        dbTypeKey = titanKeyForVertexType(dbType.name);
        dbNameKey = dbTypeKey;
        dbType = new DBType(dbTypeKey);
        // Dataset keys
        datasetTypeKey = titanKeyForVertexType(datasetType.name);
        datasetNameKey = datasetTypeKey;
        datasetType = new DatasetType(datasetTypeKey);
        // Institute keys
        instituteTypeKey = titanKeyForVertexType(instituteType.name);
        instituteNameKey = instituteTypeKey;
        instituteType = new InstituteType(instituteTypeKey);
        // Organism keys
        organismTypeKey = titanKeyForVertexType(organismType.scientificName);
        organismScientificNameKey = organismTypeKey;
        organismCommonNameKey = titanKeyForVertexProperty(organismType.commonName);
        organismSynonymNameKey = titanKeyForVertexProperty(organismType.synonymName);
        organismType = new OrganismType(organismTypeKey);
        // Keyword keys
        keywordTypeKey = titanKeyForVertexType(keywordType.id);
        keywordIdKey = keywordTypeKey;
        keywordNameKey = titanKeyForVertexProperty(keywordType.name);
        keywordType = new KeywordType(keywordTypeKey);
        // Interpro keys
        interproTypeKey = titanKeyForVertexType(interproType.id);
        interproIdKey = interproTypeKey;
        interproNameKey = titanKeyForVertexProperty(interproType.name);
        interproType = new InterproType(interproTypeKey);
        // Journal keys
        journalTypeKey = titanKeyForVertexType(journalType.name);
        journalNameKey = journalTypeKey;
        journalType = new JournalType(journalTypeKey);
        // OnlineArticle keys
        onlineArticleTypeKey = titanKeyForVertexType(onlineArticleType.title);
        onlineArticleTitleKey = onlineArticleTypeKey;
        onlineArticleType = new OnlineArticleType(onlineArticleTypeKey);
        // OnlineJournal keys
        onlineJournalTypeKey = titanKeyForVertexType(onlineJournalType.name);
        onlineJournalNameKey = onlineJournalTypeKey;
        onlineJournalType = new OnlineJournalType(onlineJournalTypeKey);
        // ReactomeTerm keys
        reactomeTermTypeKey = titanKeyForVertexType(reactomeTermType.id);
        reactomeTermIdKey = reactomeTermTypeKey;
        reactomeTermPathwayNameKey = titanKeyForVertexProperty(reactomeTermType.pathwayName);
        reactomeTermType = new ReactomeTermType(reactomeTermTypeKey);
        // Publisher keys
        publisherTypeKey = titanKeyForVertexType(publisherType.name);
        publisherNameKey = publisherTypeKey;
        publisherType = new PublisherType(publisherTypeKey);
        // Pfam keys
        pfamTypeKey = titanKeyForVertexType(pfamType.id);
        pfamIdKey = pfamTypeKey;
        pfamNameKey = titanKeyForVertexProperty(pfamType.name);
        pfamType = new PfamType(pfamTypeKey);
        // Pfam keys
        pubmedTypeKey = titanKeyForVertexType(pubmedType.id);
        pubmedIdKey = pubmedTypeKey;
        pubmedType = new PubmedType(pubmedTypeKey);
        // Submission keys
        submissionTypeKey = titanKeyForVertexType(submissionType.title);
        submissionTitleKey = submissionTypeKey;
        submissionType = new SubmissionType(submissionTypeKey);
        // EMBL keys
        eMBLTypeKey = titanKeyForVertexType(eMBLType.id);
        eMBLIdKey = eMBLTypeKey;
        eMBLMoleculeTypeKey = titanKeyForVertexProperty(eMBLType.moleculeType);
        eMBLProteinSequenceIdKey = titanKeyForVertexProperty(eMBLType.proteinSequenceId);
        eMBLType = new EMBLType(eMBLTypeKey);
        // PIR keys
        pIRTypeKey = titanKeyForVertexType(pIRType.id);
        pIRIdKey = pIRTypeKey;
        pIREntryNameKey = titanKeyForVertexProperty(pIRType.entryName);
        pIRType = new PIRType(pIRTypeKey);
        // Patent keys
        patentTypeKey = titanKeyForVertexType(patentType.number);
        patentNumberKey = patentTypeKey;
        patentTitleKey = titanKeyForVertexProperty(patentType.title);
        patentType = new PatentType(patentTypeKey);
        // Ensembl keys
        ensemblTypeKey = titanKeyForVertexType(ensemblType.id);
        ensemblIdKey = ensemblTypeKey;
        ensemblMoleculeIdKey = titanKeyForVertexProperty(ensemblType.moleculeId);
        ensemblProteinSequenceIdKey = titanKeyForVertexProperty(ensemblType.proteinSequenceId);
        ensemblGeneIdKey = titanKeyForVertexProperty(ensemblType.geneId);
        ensemblType = new EnsemblType(ensemblTypeKey);
        //---UniGene---
        uniGeneTypeKey = titanKeyForVertexType(uniGeneType.id);
        uniGeneIdKey = uniGeneTypeKey;
        uniGeneType = new UniGeneType(uniGeneTypeKey);
        //---SubcellularLocation---
        subcellularLocationTypeKey = titanKeyForVertexType(subcellularLocationType.name);
        subcellularLocationNameKey = subcellularLocationTypeKey;
        subcellularLocationType = new SubcellularLocationType(subcellularLocationTypeKey);
        //---Kegg---
        keggTypeKey = titanKeyForVertexType(keggType.id);
        keggIdKey = keggTypeKey;
        keggType = new KeggType(keggTypeKey);
        //---Taxon---
        taxonTypeKey = titanKeyForVertexType(taxonType.name);
        taxonNameKey = taxonTypeKey;
        taxonType = new TaxonType(taxonTypeKey);
        //---RefSeq---
        refSeqTypeKey = titanKeyForVertexType(refSeqType.id);
        refSeqIdKey = refSeqTypeKey;
        refSeqNucleotideSequenceIdKey = titanKeyForVertexProperty(refSeqType.nucleotideSequenceId);
        refSeqType = new RefSeqType(refSeqTypeKey);
        //---Comment---
        commentTypeTypeKey = titanKeyForVertexType(commentTypeType.name);
        commentTypeNameKey = commentTypeTypeKey;
        commentTypeType = new CommentTypeType(commentTypeTypeKey);
        //---Feature---
        featureTypeTypeKey = titanKeyForVertexType(featureTypeType.name);
        featureTypeNameKey = featureTypeTypeKey;
        featureTypeType = new FeatureTypeType(featureTypeTypeKey);
        //---UnpublishedObservation
        //unpublishedObservationType = new UnpublishedObservationType(null);

        //-----------------------------------------------------------------------------------------
        //--------------------------------EDGES--------------------------------------------

        // articlePubmed
        articlePubmedLabel = raw().titanLabelForEdgeType(this.new ArticlePubmedType(null));
        articlePubmedType = new ArticlePubmedType(articlePubmedLabel);

        // proteinDataset
        proteinDatasetLabel = raw().titanLabelForEdgeType(this.new ProteinDatasetType(null));
        proteinDatasetType = new ProteinDatasetType(proteinDatasetLabel);

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

        // proteinFeature
        proteinFeatureLabel = raw().titanLabelForEdgeType(this.new ProteinFeatureType(null));
        proteinFeatureType = new ProteinFeatureType(proteinFeatureLabel);
        proteinFeatureIdKey = titanKeyForEdgeProperty(proteinFeatureType.id);
        proteinFeatureDescriptionKey = titanKeyForEdgeProperty(proteinFeatureType.description);
        proteinFeatureEvidenceKey = titanKeyForEdgeProperty(proteinFeatureType.evidence);
        proteinFeatureStatusKey = titanKeyForEdgeProperty(proteinFeatureType.status);
        proteinFeatureBeginKey = titanKeyForEdgeProperty(proteinFeatureType.begin);
        proteinFeatureEndKey = titanKeyForEdgeProperty(proteinFeatureType.end);
        proteinFeatureOriginalKey = titanKeyForEdgeProperty(proteinFeatureType.original);
        proteinFeatureVariationKey = titanKeyForEdgeProperty(proteinFeatureType.variation);
        proteinFeatureRefKey = titanKeyForEdgeProperty(proteinFeatureType.ref);

        proteinOrganismLabel = raw().titanLabelForEdgeType(this.new ProteinOrganismType(null));
        proteinOrganismType = new ProteinOrganismType(proteinOrganismLabel);

        proteinReferenceLabel = raw().titanLabelForEdgeType(this.new ProteinReferenceType(null));
        proteinReferenceType = new ProteinReferenceType(proteinReferenceLabel);

        // referenceArticle
        referenceArticleLabel = raw().titanLabelForEdgeType(this.new ReferenceArticleType(null));
        referenceArticleType = new ReferenceArticleType(referenceArticleLabel);

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



    }

    private void initIndices() {

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
    public ArticleType Article() {
        return articleType;
    }

    @Override
    public BookType Book() {
        return bookType;
    }

    @Override
    public CityType City() {
        return null;
    }

    @Override
    public CommentTypeType CommentType() {
        return commentTypeType;
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
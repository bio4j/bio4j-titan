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
    //---RefSeq---
    public TitanKey refSeqTypeKey;
    public TitanKey refSeqIdKey;
    public TitanKey refSeqNucleotideSequenceIdKey;
    public RefSeqType refSeqType;
    //---FeatureType---
    public TitanKey featureTypeTypeKey;
    public TitanKey featureTypeNameKey;
    public FeatureType featureType;
    //---CommentType---
    public TitanKey commentTypeTypeKey;
    public TitanKey commentTypeNameKey;
    public CommentType commentType;

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
    public TitanLabel proteinDatasetLabel;
    public ProteinDatasetType proteinDatasetType;
    public TitanLabel proteinOrganismLabel;
    public ProteinOrganismType proteinOrganismType;
    public TitanLabel proteinKeywordLabel;
    public ProteinKeywordType proteinKeywordType;
    public TitanLabel proteinReactomeTermLabel;
    public ProteinReactomeTermType proteinReactomeTermType;
    public TitanLabel proteinInterproLabel;
    public ProteinInterproType proteinInterproType;
    public TitanLabel proteinPfamLabel;
    public ProteinPfamType proteinPfamType;
    public TitanLabel proteinKeggLabel;
    public ProteinKeggType proteinKeggType;
    public TitanLabel proteinEMBLLabel;
    public ProteinEMBLType proteinEMBLType;
    public TitanLabel proteinPIRLabel;
    public ProteinPIRType proteinPIRType;
    public TitanLabel proteinUniGeneLabel;
    public ProteinUniGeneType proteinUniGeneType;
    public TitanLabel proteinEnsemblLabel;
    public ProteinEnsemblType proteinEnsemblType;
    public TitanLabel taxonParentLabel;
    public TaxonParentType taxonParentType;
    public TitanLabel organismTaxonLabel;
    public OrganismTaxonType organismTaxonType;
    public TitanLabel proteinRefSeqLabel;
    public ProteinRefSeqType proteinRefSeqType;
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
    public TitanLabel proteinCommentLabel;
    public ProteinCommentType proteinCommentType;


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

    private void initTypes() {

        //-----------------------------------------------------------------------------------------
        //--------------------------------VERTICES--------------------------------------------

        // Protein keys
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
        // Dataset keys
        datasetTypeKey = titanKeyForVertexType(datasetType.name);
        datasetNameKey = datasetTypeKey;
        // Organism keys
        organismTypeKey = titanKeyForVertexType(organismType.scientificName);
        organismScientificNameKey = organismTypeKey;
        organismCommonNameKey = titanKeyForNodeProperty(organismType.commonName);
        organismSynonymNameKey = titanKeyForNodeProperty(organismType.synonymName);
        // Keyword keys
        keywordTypeKey = titanKeyForVertexType(keywordType.id);
        keywordIdKey = keywordTypeKey;
        keywordNameKey = titanKeyForNodeProperty(keywordType.name);
        // Interpro keys
        interproTypeKey = titanKeyForVertexType(interproType.id);
        interproIdKey = interproTypeKey;
        interproNameKey = titanKeyForNodeProperty(interproType.name);
        // ReactomeTerm keys
        reactomeTermTypeKey = titanKeyForVertexType(reactomeTermType.id);
        reactomeTermIdKey = reactomeTermTypeKey;
        reactomeTermPathwayNameKey = titanKeyForNodeProperty(reactomeTermType.pathwayName);
        // Pfam keys
        pfamTypeKey = titanKeyForVertexType(pfamType.id);
        pfamIdKey = pfamTypeKey;
        pfamNameKey = titanKeyForNodeProperty(pfamType.name);
        // EMBL keys
        eMBLTypeKey = titanKeyForVertexType(eMBLType.id);
        eMBLIdKey = eMBLTypeKey;
        eMBLMoleculeTypeKey = titanKeyForNodeProperty(eMBLType.moleculeType);
        eMBLProteinSequenceIdKey = titanKeyForNodeProperty(eMBLType.proteinSequenceId);
        // PIR keys
        pIRTypeKey = titanKeyForVertexType(pIRType.id);
        pIRIdKey = pIRTypeKey;
        pIREntryNameKey = titanKeyForNodeProperty(pIRType.entryName);
        // Ensembl keys
        ensemblTypeKey = titanKeyForVertexType(ensemblType.id);
        ensemblIdKey = ensemblTypeKey;
        ensemblMoleculeIdKey = titanKeyForNodeProperty(ensemblType.moleculeId);
        ensemblProteinSequenceIdKey = titanKeyForNodeProperty(ensemblType.proteinSequenceId);
        ensemblGeneIdKey = titanKeyForNodeProperty(ensemblType.geneId);
        //---UniGene---
        uniGeneTypeKey = titanKeyForVertexType(uniGeneType.id);
        uniGeneIdKey = uniGeneTypeKey;
        //---Kegg---
        keggTypeKey = titanKeyForVertexType(keggType.id);
        keggIdKey = keggTypeKey;
        //---Taxon---
        taxonTypeKey = titanKeyForVertexType(taxonType.name);
        taxonNameKey = taxonTypeKey;
        //---RefSeq---
        refSeqTypeKey = titanKeyForVertexType(refSeqType.id);
        refSeqIdKey = refSeqTypeKey;
        refSeqNucleotideSequenceIdKey = titanKeyForNodeProperty(refSeqType.nucleotideSequenceId);
        //---Comment---
        commentTypeTypeKey = titanKeyForVertexType(commentTypeType.name);
        commentTypeNameKey = commentTypeTypeKey;
        //---Feature---
        featureTypeTypeKey = titanKeyForVertexType(featureTypeType.name);
        featureTypeNameKey = featureTypeTypeKey;
        // proteinDataset
        proteinDatasetLabel = titanLabelForRelationshipType(proteinDatasetType);
        // proteinOrganism
        proteinOrganismLabel = titanLabelForRelationshipType(proteinOrganismType);
        // proteinKeyword
        proteinKeywordLabel = titanLabelForRelationshipType(proteinKeywordType);
        // proteinInterpro
        proteinInterproLabel = titanLabelForRelationshipType(proteinInterproType);
        // proteinReactomeTerm
        proteinReactomeTermLabel = titanLabelForRelationshipType(proteinReactomeTermType);
        // proteinPfam
        proteinPfamLabel = titanLabelForRelationshipType(proteinPfamType);
        // proteinKegg
        proteinKeggLabel = titanLabelForRelationshipType(proteinKeggType);
        // proteinKegg
        proteinPIRLabel = titanLabelForRelationshipType(proteinPIRType);
        // proteinEMBL
        proteinEMBLLabel = titanLabelForRelationshipType(proteinEMBLType);
        // proteinEnsembl
        proteinEnsemblLabel = titanLabelForRelationshipType(proteinEnsemblType);
        // proteinRefSeq
        proteinRefSeqLabel = titanLabelForRelationshipType(proteinRefSeqType);
        // proteinUnigene
        proteinUniGeneLabel = titanLabelForRelationshipType(proteinUniGeneType);
        // taxonParent
        taxonParentLabel = titanLabelForRelationshipType(taxonParentType);
        // organismTaxon
        organismTaxonLabel = titanLabelForRelationshipType(organismTaxonType);
        // proteinComment
        proteinCommentLabel = titanLabelForRelationshipType(proteinCommentType);
        // proteinFeature
        proteinFeatureLabel = titanLabelForRelationshipType(proteinFeatureType);
        proteinFeatureIdKey = titanKeyForEdgeProperty(proteinFeatureType.id);
        proteinFeatureIdKey = titanKeyForEdgeProperty(proteinFeatureType.description);
        proteinFeatureIdKey = titanKeyForEdgeProperty(proteinFeatureType.evidence);
        proteinFeatureIdKey = titanKeyForEdgeProperty(proteinFeatureType.status);
        proteinFeatureIdKey = titanKeyForEdgeProperty(proteinFeatureType.begin);
        proteinFeatureIdKey = titanKeyForEdgeProperty(proteinFeatureType.end);
        proteinFeatureIdKey = titanKeyForEdgeProperty(proteinFeatureType.original);
        proteinFeatureIdKey = titanKeyForEdgeProperty(proteinFeatureType.variation);
        proteinFeatureIdKey = titanKeyForEdgeProperty(proteinFeatureType.ref);


        //-----------------------------------------------------------------------------------------
        //--------------------------------RELATIONSHIPS--------------------------------------------

        proteinOrganismLabel = raw().titanLabelForEdgeType(new ProteinOrganismType((TitanLabel) null));
        proteinOrganismType = new ProteinOrganismType(proteinOrganismLabel);


    }

    private void initIndices() {

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
package com.bio4j.titan.model.uniprot;

import com.bio4j.titan.model.uniprot_enzyme.TitanUniprotEnzymeDBGraphImpl;
import com.bio4j.titan.model.uniprot_go.TitanUniprotGoGraphImpl;
import com.ohnosequences.typedGraphs.titan.TitanNodeIndex;
import com.thinkaurelius.titan.core.TitanGraph;

/**
* Created by ppareja on 6/20/2014.
*/
public class TitanUniprotGraphImpl extends TitanUniprotGraph {

	public TitanUniprotGraphImpl(TitanGraph graph) {
		super(graph);
		initTypes();
		initIndices();
	}

	private void initTypes() {

		// Protein keys
		proteinTKey = titanKeyForNodeType(proteinT.accession);
		proteinAccessionKey = proteinTKey;
		proteinModifiedDateKey = titanKeyForNodeProperty(proteinT.modifiedDate);
		proteinMassKey = titanKeyForNodeProperty(proteinT.mass);
		proteinSequenceKey = titanKeyForNodeProperty(proteinT.sequence);
		proteinNameKey = titanKeyForNodeProperty(proteinT.name);
		proteinShortNameKey = titanKeyForNodeProperty(proteinT.shortName);
		proteinFullNameKey = titanKeyForNodeProperty(proteinT.fullName);
		proteinLengthKey = titanKeyForNodeProperty(proteinT.length);
		// Dataset keys
		datasetTKey = titanKeyForNodeType(datasetT.name);
		datasetNameKey = datasetTKey;
		// Organism keys
		organismTKey = titanKeyForNodeType(organismT.scientificName);
		organismScientificNameKey = organismTKey;
		organismCommonNameKey = titanKeyForNodeProperty(organismT.commonName);
		organismSynonymNameKey = titanKeyForNodeProperty(organismT.synonymName);
		// Keyword keys
		keywordTKey = titanKeyForNodeType(keywordT.id);
		keywordIdKey = keywordTKey;
		keywordNameKey = titanKeyForNodeProperty(keywordT.name);
		// Interpro keys
		interproTKey = titanKeyForNodeType(interproT.id);
		interproIdKey = interproTKey;
		interproNameKey = titanKeyForNodeProperty(interproT.name);
		// ReactomeTerm keys
		reactomeTermTKey = titanKeyForNodeType(reactomeTermT.id);
		reactomeTermIdKey = reactomeTermTKey;
		reactomeTermPathwayNameKey = titanKeyForNodeProperty(reactomeTermT.pathwayName);
		// Pfam keys
		pfamTKey = titanKeyForNodeType(pfamT.id);
		pfamIdKey = pfamTKey;
		pfamNameKey = titanKeyForNodeProperty(pfamT.name);
		// EMBL keys
		eMBLTKey = titanKeyForNodeType(eMBLT.id);
		eMBLIdKey = eMBLTKey;
		eMBLMoleculeTypeKey = titanKeyForNodeProperty(eMBLT.moleculeType);
		eMBLProteinSequenceIdKey = titanKeyForNodeProperty(eMBLT.proteinSequenceId);
		// PIR keys
		pIRTKey = titanKeyForNodeType(pIRT.id);
		pIRIdKey = pIRTKey;
		pIREntryNameKey = titanKeyForNodeProperty(pIRT.entryName);
		// Ensembl keys
		ensemblTKey = titanKeyForNodeType(ensemblT.id);
		ensemblIdKey = ensemblTKey;
		ensemblMoleculeIdKey = titanKeyForNodeProperty(ensemblT.moleculeId);
		ensemblProteinSequenceIdKey = titanKeyForNodeProperty(ensemblT.proteinSequenceId);
		ensemblGeneIdKey = titanKeyForNodeProperty(ensemblT.geneId);
		//---UniGene---
		uniGeneTKey = titanKeyForNodeType(uniGeneT.id);
		uniGeneIdKey = uniGeneTKey;
		//---Kegg---
		keggTKey = titanKeyForNodeType(keggT.id);
		keggIdKey = keggTKey;
		//---Taxon---
		taxonTKey = titanKeyForNodeType(taxonT.name);
		taxonNameKey = taxonTKey;
		//---RefSeq---
		refSeqTKey = titanKeyForNodeType(refSeqT.id);
		refSeqIdKey = refSeqTKey;
		refSeqNucleotideSequenceIdKey = titanKeyForNodeProperty(refSeqT.nucleotideSequenceId);
		//---Comment---
		commentTypeTKey = titanKeyForNodeType(commentTypeT.name);
		commentTypeNameKey = commentTypeTKey;
		//---Feature---
		featureTypeTKey = titanKeyForNodeType(featureTypeT.name);
		featureTypeNameKey = featureTypeTKey;


		// proteinDataset
		proteinDatasetLabel = titanLabelForRelationshipType(proteinDatasetT);
		// proteinOrganism
		proteinOrganismLabel = titanLabelForRelationshipType(proteinOrganismT);
		// proteinKeyword
		proteinKeywordLabel = titanLabelForRelationshipType(proteinKeywordT);
		// proteinInterpro
		proteinInterproLabel = titanLabelForRelationshipType(proteinInterproT);
		// proteinReactomeTerm
		proteinReactomeTermLabel = titanLabelForRelationshipType(proteinReactomeTermT);
		// proteinPfam
		proteinPfamLabel = titanLabelForRelationshipType(proteinPfamT);
		// proteinKegg
		proteinKeggLabel = titanLabelForRelationshipType(proteinKeggT);
		// proteinKegg
		proteinPIRLabel = titanLabelForRelationshipType(proteinPIRT);
		// proteinEMBL
		proteinEMBLLabel = titanLabelForRelationshipType(proteinEMBLT);
		// proteinEnsembl
		proteinEnsemblLabel = titanLabelForRelationshipType(proteinEnsemblT);
		// proteinRefSeq
		proteinRefSeqLabel = titanLabelForRelationshipType(proteinRefSeqT);
		// proteinUnigene
		proteinUniGeneLabel = titanLabelForRelationshipType(proteinUniGeneT);
		// taxonParent
		taxonParentLabel = titanLabelForRelationshipType(taxonParentT);
		// organismTaxon
		organismTaxonLabel = titanLabelForRelationshipType(organismTaxonT);
		// proteinComment
		proteinCommentLabel = titanLabelForRelationshipType(proteinCommentT);
		// proteinFeature
		proteinFeatureLabel = titanLabelForRelationshipType(proteinFeatureT);


	}

	private void initIndices() {
		proteinAccessionIndex = new TitanNodeIndex.DefaultUnique(this, proteinT.accession);
		datasetNameIndex = new TitanNodeIndex.DefaultUnique(this, datasetT.name);
		organismScientificNameIndex = new TitanNodeIndex.DefaultUnique(this, organismT.scientificName);
		interproIdIndex = new TitanNodeIndex.DefaultUnique(this, interproT.id);
		keywordIdIndex = new TitanNodeIndex.DefaultUnique(this, keywordT.id);
		reactomeTermIdIndex = new TitanNodeIndex.DefaultUnique(this, reactomeTermT.id);
		pfamIdIndex = new TitanNodeIndex.DefaultUnique(this, pfamT.id);
		eMBLIdIndex = new TitanNodeIndex.DefaultUnique(this, eMBLT.id);
		pIRIdIndex = new TitanNodeIndex.DefaultUnique(this, pIRT.id);
		uniGeneIdIndex = new TitanNodeIndex.DefaultUnique(this, uniGeneT.id);
		keggIdIndex = new TitanNodeIndex.DefaultUnique(this, keggT.id);
		ensemblIdIndex = new TitanNodeIndex.DefaultUnique(this, ensemblT.id);
		taxonNameIndex = new TitanNodeIndex.DefaultUnique(this, taxonT.name);
		refSeqIdIndex = new TitanNodeIndex.DefaultUnique(this, refSeqT.id);
		commentTypeNameIndex = new TitanNodeIndex.DefaultUnique(this, commentTypeT.name);
		featureTypeNameIndex = new TitanNodeIndex.DefaultUnique(this, featureTypeT.name);
	}
}

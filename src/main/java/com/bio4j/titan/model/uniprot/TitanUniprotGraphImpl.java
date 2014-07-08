package com.bio4j.titan.model.uniprot;

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
		proteinModifiedDateKey = titanKeyForNodeType(proteinT.modifiedDate);
		proteinMassKey = titanKeyForNodeType(proteinT.mass);
		proteinSequenceKey = titanKeyForNodeType(proteinT.sequence);
		proteinNameKey = titanKeyForNodeType(proteinT.name);
		proteinShortNameKey = titanKeyForNodeType(proteinT.shortName);
		proteinFullNameKey = titanKeyForNodeType(proteinT.fullName);
		proteinLengthKey = titanKeyForNodeType(proteinT.length);
		// Dataset keys
		datasetTKey = titanKeyForNodeType(datasetT.name);
		datasetNameKey = datasetTKey;
		// Organism keys
		organismTKey = titanKeyForNodeType(organismT.scientificName);
		organismScientificNameKey = organismTKey;
		organismCommonNameKey = titanKeyForNodeType(organismT.commonName);
		organismSynonymNameKey = titanKeyForNodeType(organismT.synonymName);
		// Keyword keys
		keywordTKey = titanKeyForNodeType(keywordT.id);
		keywordIdKey = keywordTKey;
		keywordNameKey = titanKeyForNodeType(keywordT.name);
		// Interpro keys
		interproTKey = titanKeyForNodeType(interproT.id);
		interproIdKey = interproTKey;
		interproNameKey = titanKeyForNodeType(interproT.name);
		// ReactomeTerm keys
		reactomeTermTKey = titanKeyForNodeType(reactomeTermT.id);
		reactomeTermIdKey = reactomeTermTKey;
		reactomeTermPathwayNameKey = titanKeyForNodeType(reactomeTermT.pathwayName);
		// Pfam keys
		pfamTKey = titanKeyForNodeType(pfamT.id);
		pfamIdKey = pfamTKey;
		pfamNameKey = titanKeyForNodeType(pfamT.name);
		// EMBL keys
		eMBLTKey = titanKeyForNodeType(eMBLT.id);
		eMBLIdKey = eMBLTKey;
		eMBLMoleculeTypeKey = titanKeyForNodeType(eMBLT.moleculeType);
		eMBLProteinSequenceIdKey = titanKeyForNodeType(eMBLT.proteinSequenceId);
		// PIR keys
		pIRTKey = titanKeyForNodeType(pIRT.id);
		pIRIdKey = pIRTKey;
		pIREntryNameKey = titanKeyForNodeType(pIRT.entryName);
		// Ensembl keys
		ensemblTKey = titanKeyForNodeType(ensemblT.id);
		ensemblIdKey = ensemblTKey;
		ensemblMoleculeIdKey = titanKeyForNodeType(ensemblT.moleculeId);
		ensemblProteinSequenceIdKey = titanKeyForNodeType(ensemblT.proteinSequenceId);
		ensemblGeneIdKey = titanKeyForNodeType(ensemblT.geneId);

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
		keggIdIndex = new TitanNodeIndex.DefaultUnique(this, keggT.id);
		ensemblIdIndex = new TitanNodeIndex.DefaultUnique(this, ensemblT.id);
	}
}

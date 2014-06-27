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
		organismTKey = titanKeyForNodeType(organismT.name);
		organismNameKey = organismTKey;

		// proteinDataset
		proteinDatasetLabel = titanLabelForRelationshipType(proteinDatasetT);

		// proteinOrganism
		proteinOrganismLabel = titanLabelForRelationshipType(proteinOrganismT);

	}

	private void initIndices() {
		proteinAccessionIndex = new TitanNodeIndex.DefaultUnique(this, proteinT.accession);
		datasetNameIndex = new TitanNodeIndex.DefaultUnique(this, datasetT.name);
		organismNameIndex = new TitanNodeIndex.DefaultUnique(this, organismT.name);
	}
}

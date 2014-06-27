package com.bio4j.titan.model.uniprot;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.titan.model.uniprot.nodes.TitanDataset;
import com.bio4j.titan.model.uniprot.nodes.TitanDataset.TitanDatasetType;
import com.bio4j.titan.model.uniprot.nodes.TitanOrganism;
import com.bio4j.titan.model.uniprot.nodes.TitanOrganism.TitanOrganismType;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein.TitanProteinType;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinDataset.TitanProteinDatasetType;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinOrganism.TitanProteinOrganismType;
import com.ohnosequences.typedGraphs.titan.TitanNodeIndex;
import com.ohnosequences.typedGraphs.titan.TitanTypedGraph;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanLabel;

/**
* Created by ppareja on 6/20/2014.
*/
public class TitanUniprotGraph implements
		TitanTypedGraph,
		UniprotGraph {

	protected TitanGraph rawGraph;

	TitanUniprotGraph(TitanGraph rawGraph) {

		this.rawGraph = rawGraph;
	}

	@Override
	public TitanGraph rawGraph() {
		return rawGraph;
	}
	//---protein---
	public TitanKey proteinTKey;
	public TitanKey proteinAccessionKey;
	public TitanKey proteinNameKey;
	public TitanKey proteinFullNameKey;
	public TitanKey proteinShortNameKey;
	public TitanKey proteinSequenceKey;
	public TitanKey proteinMassKey;
	public TitanKey proteinModifiedDateKey;
	public TitanKey proteinLengthKey;
	//---dataset---
	public TitanKey datasetTKey;
	public TitanKey datasetNameKey;
	//---organism---
	public TitanKey organismTKey;
	public TitanKey organismNameKey;

	public final TitanProteinType proteinT = new TitanProteinType(this);
	public final TitanDatasetType datasetT = new TitanDatasetType(this);
	public final TitanOrganismType organismT = new TitanOrganismType(this);

	//------------------INDICES----------------
	//-----------------------------------------
	public TitanNodeIndex.Unique<TitanProtein,TitanProteinType, TitanProteinType.accession,String> proteinAccessionIndex;
	public TitanNodeIndex.Unique<TitanDataset,TitanDatasetType, TitanDatasetType.name,String> datasetNameIndex;
	public TitanNodeIndex.Unique<TitanOrganism,TitanOrganismType, TitanOrganismType.name,String> organismNameIndex;

	//-----------------------------------------------------------------------------------------
	//--------------------------------RELATIONSHIPS--------------------------------------------
	public TitanLabel proteinDatasetLabel;
	public TitanProteinDatasetType proteinDatasetT = new TitanProteinDatasetType(this);
	public TitanLabel proteinOrganismLabel;
	public TitanProteinOrganismType proteinOrganismT = new TitanProteinOrganismType(this);
}

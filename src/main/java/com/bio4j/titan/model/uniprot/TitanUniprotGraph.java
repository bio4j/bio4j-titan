package com.bio4j.titan.model.uniprot;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.titan.model.uniprot.nodes.*;
import com.bio4j.titan.model.uniprot.nodes.TitanDataset.TitanDatasetType;
import com.bio4j.titan.model.uniprot.nodes.TitanKeyword.TitanKeywordType;
import com.bio4j.titan.model.uniprot.nodes.TitanOrganism.TitanOrganismType;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein.TitanProteinType;
import com.bio4j.titan.model.uniprot.nodes.TitanInterpro.TitanInterproType;
import com.bio4j.titan.model.uniprot.nodes.TitanEMBL.TitanEMBLType;
import com.bio4j.titan.model.uniprot.nodes.TitanPfam.TitanPfamType;
import com.bio4j.titan.model.uniprot.nodes.TitanKegg.TitanKeggType;
import com.bio4j.titan.model.uniprot.nodes.TitanPIR.TitanPIRType;
import com.bio4j.titan.model.uniprot.nodes.TitanUniGene.TitanUniGeneType;
import com.bio4j.titan.model.uniprot.nodes.TitanTaxon.TitanTaxonType;
import com.bio4j.titan.model.uniprot.nodes.TitanCommentType.TitanCommentTypeType;
import com.bio4j.titan.model.uniprot.nodes.TitanRefSeq.TitanRefSeqType;
import com.bio4j.titan.model.uniprot.nodes.TitanEnsembl.TitanEnsemblType;
import com.bio4j.titan.model.uniprot.nodes.TitanFeatureType.TitanFeatureTypeType;
import com.bio4j.titan.model.uniprot.nodes.TitanReactomeTerm.TitanReactomeTermType;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinComment.TitanProteinCommentType;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinDataset.TitanProteinDatasetType;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinFeature.TitanProteinFeatureType;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinInterpro.TitanProteinInterproType;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinKeyword.TitanProteinKeywordType;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinOrganism.TitanProteinOrganismType;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinPfam.TitanProteinPfamType;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinKegg.TitanProteinKeggType;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinEMBL.TitanProteinEMBLType;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinPIR.TitanProteinPIRType;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinRefSeq.TitanProteinRefSeqType;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinUniGene.TitanProteinUniGeneType;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinEnsembl.TitanProteinEnsemblType;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinReactomeTerm.TitanProteinReactomeTermType;
import com.bio4j.titan.model.uniprot.relationships.TitanTaxonParent.TitanTaxonParentType;
import com.bio4j.titan.model.uniprot.relationships.TitanOrganismTaxon.TitanOrganismTaxonType;
import com.bio4j.titan.model.uniprot_enzyme.TitanUniprotEnzymeDBGraph;
import com.bio4j.titan.model.uniprot_go.TitanUniprotGoGraph;
import com.ohnosequences.typedGraphs.titan.TitanNodeIndex;
import com.ohnosequences.typedGraphs.titan.TitanTypedGraph;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanLabel;

/**
* Created by ppareja on 6/20/2014.
*/
public abstract class TitanUniprotGraph implements
		TitanTypedGraph,
		UniprotGraph {

	protected TitanGraph rawGraph;
	public TitanUniprotEnzymeDBGraph uniprotEnzymeDBGraph = null;
	public TitanUniprotGoGraph uniprotGoGraph = null;

	TitanUniprotGraph(TitanGraph rawGraph) {

		this.rawGraph = rawGraph;
	}

	/*
		You can use this as `uniprotGraph.withEnzymeDB(new uniprotEnzymeDBGraph(raw, uniprotGraph, enzymeGraph))`
	*/
	public TitanUniprotGraph withEnzymeDB(TitanUniprotEnzymeDBGraph uniprotEnzymeDBGraph) {

		this.uniprotEnzymeDBGraph = uniprotEnzymeDBGraph;

		return this;
	}

	/*
		You can use this as `uniprotGraph.withGo(new uniprotGoGraph(raw, uniprotGraph, goGraph))`
	*/
	public TitanUniprotGraph withGo(TitanUniprotGoGraph uniprotGoGraph) {

		this.uniprotGoGraph = uniprotGoGraph;

		return this;
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
	public final TitanProteinType proteinT = new TitanProteinType(this);
	//---dataset---
	public TitanKey datasetTKey;
	public TitanKey datasetNameKey;
	public final TitanDatasetType datasetT = new TitanDatasetType(this);
	//---organism---
	public TitanKey organismTKey;
	public TitanKey organismScientificNameKey;
	public TitanKey organismCommonNameKey;
	public TitanKey organismSynonymNameKey;
	public final TitanOrganismType organismT = new TitanOrganismType(this);
	//---keyword---
	public TitanKey keywordTKey;
	public TitanKey keywordNameKey;
	public TitanKey keywordIdKey;
	public final TitanKeywordType keywordT = new TitanKeywordType(this);
	//---interpro---
	public TitanKey interproTKey;
	public TitanKey interproNameKey;
	public TitanKey interproIdKey;
	public final TitanInterproType interproT = new TitanInterproType(this);
	//---reactome term---
	public TitanKey reactomeTermTKey;
	public TitanKey reactomeTermPathwayNameKey;
	public TitanKey reactomeTermIdKey;
	public final TitanReactomeTermType reactomeTermT = new TitanReactomeTermType(this);
	//---pfam---
	public TitanKey pfamTKey;
	public TitanKey pfamNameKey;
	public TitanKey pfamIdKey;
	public final TitanPfamType pfamT = new TitanPfamType(this);
	//---kegg---
	public TitanKey keggTKey;
	public TitanKey keggIdKey;
	public final TitanKeggType keggT = new TitanKeggType(this);
	//---EMBL---
	public TitanKey eMBLTKey;
	public TitanKey eMBLIdKey;
	public TitanKey eMBLMoleculeTypeKey;
	public TitanKey eMBLProteinSequenceIdKey;
	public final TitanEMBLType eMBLT = new TitanEMBLType(this);
	//---PIR---
	public TitanKey pIRTKey;
	public TitanKey pIRIdKey;
	public TitanKey pIREntryNameKey;
	public final TitanPIRType pIRT = new TitanPIRType(this);
	//---UniGene---
	public TitanKey uniGeneTKey;
	public TitanKey uniGeneIdKey;
	public final TitanUniGeneType uniGeneT = new TitanUniGeneType(this);
	//---Ensembl---
	public TitanKey ensemblTKey;
	public TitanKey ensemblIdKey;
	public TitanKey ensemblMoleculeIdKey;
	public TitanKey ensemblProteinSequenceIdKey;
	public TitanKey ensemblGeneIdKey;
	public final TitanEnsemblType ensemblT = new TitanEnsemblType(this);
	//---Taxon---
	public TitanKey taxonTKey;
	public TitanKey taxonNameKey;
	public final TitanTaxonType taxonT = new TitanTaxonType(this);
	//---RefSeq---
	public TitanKey refSeqTKey;
	public TitanKey refSeqIdKey;
	public TitanKey refSeqNucleotideSequenceIdKey;
	public final TitanRefSeqType refSeqT = new TitanRefSeqType(this);
	//---FeatureType---
	public TitanKey featureTypeTKey;
	public TitanKey featureTypeNameKey;
	public final TitanFeatureTypeType featureTypeT = new TitanFeatureTypeType(this);
	//---CommentType---
	public TitanKey commentTypeTKey;
	public TitanKey commentTypeNameKey;
	public final TitanCommentTypeType commentTypeT = new TitanCommentTypeType(this);



	//------------------INDICES----------------
	//-----------------------------------------
	public TitanNodeIndex.Unique<TitanProtein,TitanProteinType, TitanProteinType.accession,String> proteinAccessionIndex;
	public TitanNodeIndex.Unique<TitanDataset,TitanDatasetType, TitanDatasetType.name,String> datasetNameIndex;
	public TitanNodeIndex.Unique<TitanOrganism,TitanOrganismType, TitanOrganismType.scientificName,String> organismScientificNameIndex;
	public TitanNodeIndex.Unique<TitanKeyword,TitanKeywordType, TitanKeywordType.id,String> keywordIdIndex;
	public TitanNodeIndex.Unique<TitanReactomeTerm,TitanReactomeTermType, TitanReactomeTermType.id,String> reactomeTermIdIndex;
	public TitanNodeIndex.Unique<TitanInterpro,TitanInterproType, TitanInterproType.id,String> interproIdIndex;
	public TitanNodeIndex.Unique<TitanPfam,TitanPfamType, TitanPfamType.id,String> pfamIdIndex;
	public TitanNodeIndex.Unique<TitanKegg,TitanKeggType, TitanKeggType.id,String> keggIdIndex;
	public TitanNodeIndex.Unique<TitanEMBL,TitanEMBLType, TitanEMBLType.id,String> eMBLIdIndex;
	public TitanNodeIndex.Unique<TitanPIR,TitanPIRType, TitanPIRType.id,String> pIRIdIndex;
	public TitanNodeIndex.Unique<TitanUniGene,TitanUniGeneType, TitanUniGeneType.id,String> uniGeneIdIndex;
	public TitanNodeIndex.Unique<TitanEnsembl,TitanEnsemblType, TitanEnsemblType.id,String> ensemblIdIndex;
	public TitanNodeIndex.Unique<TitanTaxon,TitanTaxonType, TitanTaxonType.name,String> taxonNameIndex;
	public TitanNodeIndex.Unique<TitanRefSeq,TitanRefSeqType, TitanRefSeqType.id,String> refSeqIdIndex;
	public TitanNodeIndex.Unique<TitanCommentType,TitanCommentTypeType, TitanCommentTypeType.name,String> commentTypeNameIndex;
	public TitanNodeIndex.Unique<TitanFeatureType,TitanFeatureTypeType, TitanFeatureTypeType.name,String> featureTypeNameIndex;

	//-----------------------------------------------------------------------------------------
	//--------------------------------RELATIONSHIPS--------------------------------------------
	public TitanLabel proteinDatasetLabel;
	public TitanProteinDatasetType proteinDatasetT = new TitanProteinDatasetType(this);
	public TitanLabel proteinOrganismLabel;
	public TitanProteinOrganismType proteinOrganismT = new TitanProteinOrganismType(this);
	public TitanLabel proteinKeywordLabel;
	public TitanProteinKeywordType proteinKeywordT = new TitanProteinKeywordType(this);
	public TitanLabel proteinReactomeTermLabel;
	public TitanProteinReactomeTermType proteinReactomeTermT = new TitanProteinReactomeTermType(this);
	public TitanLabel proteinInterproLabel;
	public TitanProteinInterproType proteinInterproT = new TitanProteinInterproType(this);
	public TitanLabel proteinPfamLabel;
	public TitanProteinPfamType proteinPfamT = new TitanProteinPfamType(this);
	public TitanLabel proteinKeggLabel;
	public TitanProteinKeggType proteinKeggT = new TitanProteinKeggType(this);
	public TitanLabel proteinEMBLLabel;
	public TitanProteinEMBLType proteinEMBLT = new TitanProteinEMBLType(this);
	public TitanLabel proteinPIRLabel;
	public TitanProteinPIRType proteinPIRT = new TitanProteinPIRType(this);
	public TitanLabel proteinUniGeneLabel;
	public TitanProteinUniGeneType proteinUniGeneT = new TitanProteinUniGeneType(this);
	public TitanLabel proteinEnsemblLabel;
	public TitanProteinEnsemblType proteinEnsemblT = new TitanProteinEnsemblType(this);
	public TitanLabel taxonParentLabel;
	public TitanTaxonParentType taxonParentT = new TitanTaxonParentType(this);
	public TitanLabel organismTaxonLabel;
	public TitanOrganismTaxonType organismTaxonT = new TitanOrganismTaxonType(this);
	public TitanLabel proteinRefSeqLabel;
	public TitanProteinRefSeqType proteinRefSeqT = new TitanProteinRefSeqType(this);
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
	public TitanProteinFeatureType proteinFeatureT = new TitanProteinFeatureType(this);
	public TitanLabel proteinCommentLabel;
	public TitanProteinCommentType proteinCommentT = new TitanProteinCommentType(this);
}

package com.bio4j.titan.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph.ProteinDatasetType;
import com.bio4j.model.uniprot.relationships.ProteinDataset;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.nodes.TitanDataset;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 6/27/2014.
 */
public class TitanProteinDataset extends
		TitanRelationship<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanProteinDataset, TitanProteinDataset.TitanProteinDatasetType,
				TitanDataset, TitanDataset.TitanDatasetType
				>
		implements
		ProteinDataset<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanProteinDataset, TitanProteinDataset.TitanProteinDatasetType,
				TitanDataset, TitanDataset.TitanDatasetType
				> {


	TitanProteinDataset(TitanEdge edge, TitanUniprotGraph uniprotGraph) {

		super(edge);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanProteinDatasetType type() {

		return uniprotGraph.proteinDatasetT;
	}

	public static final class TitanProteinDatasetType
			implements
			TitanRelationship.Type<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinDataset, TitanProteinDataset.TitanProteinDatasetType,
					TitanDataset, TitanDataset.TitanDatasetType
					>,
			ProteinDatasetType<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinDataset, TitanProteinDatasetType,
					TitanDataset, TitanDataset.TitanDatasetType
					> {

		TitanUniprotGraph uniprotGraph;

		public TitanProteinDatasetType(TitanUniprotGraph uniprotGraph) {

			this.uniprotGraph = uniprotGraph;
		}

		@Override
		public TitanLabel label() {
			return uniprotGraph.proteinDatasetLabel;
		}

		@Override
		public TitanProteinDatasetType value() {
			return uniprotGraph.proteinDatasetT;
		}

		@Override
		public TitanProtein.TitanProteinType sourceType() {
			return uniprotGraph.proteinT;
		}

		@Override
		public TitanDataset.TitanDatasetType targetType() {
			return uniprotGraph.datasetT;
		}

		@Override
		public TitanProteinDataset fromTitanEdge(TitanEdge edge) {
			return new TitanProteinDataset(edge, uniprotGraph);
		}
	}
}


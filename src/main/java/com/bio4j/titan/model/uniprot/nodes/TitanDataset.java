package com.bio4j.titan.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph.DatasetType;
import com.bio4j.model.uniprot.nodes.Dataset;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.relationships.ProteinDataset;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinDataset;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;

import java.util.List;

/**
 * Created by ppareja on 6/27/2014.
 */
public class TitanDataset extends
		TitanNode<TitanDataset, TitanDataset.TitanDatasetType>
		implements Dataset<TitanDataset, TitanDataset.TitanDatasetType> {

	@Override
	public String name(){ return get(uniprotGraph.datasetT.name);}

	@Override
	public List<TitanProteinDataset> proteinDataset_in() {
		return inFromMany(uniprotGraph.proteinDatasetT);
	}

	@Override
	public List<TitanProtein> proteinDataset_inNodes() {
		return inFromManyNodes(uniprotGraph.proteinDatasetT);
	}

	public TitanDataset(TitanVertex vertex, TitanUniprotGraph uniprotGraph) {

		super(vertex);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	@Override
	public TitanDatasetType type() {

		return uniprotGraph.datasetT;
	}

	public static final class TitanDatasetType
			implements
			TitanNode.Type<TitanDataset, TitanDataset.TitanDatasetType>,
			DatasetType<TitanDataset, TitanDatasetType> {

		public TitanDatasetType(TitanUniprotGraph uniprotGraph) {
			this.uniprotGraph = uniprotGraph;
		}

		TitanUniprotGraph uniprotGraph;

		@Override
		public TitanKey titanKey() {

			return uniprotGraph.datasetTKey;
		}

		@Override
		public TitanDatasetType value() {

			return uniprotGraph.datasetT;
		}

		@Override
		public TitanDataset fromTitanVertex(TitanVertex vertex) {

			return new TitanDataset(vertex, uniprotGraph);
		}

		// --------------------------------properties----------------------------------------
		public name name = new name();

		public final class name
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanDataset, TitanDatasetType, name, String>,
				Dataset.name<TitanDataset, TitanDatasetType, name> {

			@Override
			public TitanDatasetType elementType() {

				return TitanDatasetType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.datasetNameKey;
			}
		}
	}
}

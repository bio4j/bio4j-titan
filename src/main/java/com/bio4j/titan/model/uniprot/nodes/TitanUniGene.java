package com.bio4j.titan.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.UniGene;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinUniGene;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;

import java.util.List;

/**
 * Created by ppareja on 7/7/2014.
 */
public class TitanUniGene extends
		TitanNode<TitanUniGene, TitanUniGene.TitanUniGeneType>
		implements UniGene<TitanUniGene, TitanUniGene.TitanUniGeneType> {

	@Override
	public String id(){ return get(uniprotGraph.uniGeneT.id);}

	@Override
	public List<TitanProteinUniGene> proteinUniGene_in() {
		return inFromMany(uniprotGraph.proteinUniGeneT);
	}

	@Override
	public List<TitanProtein> proteinUniGene_inNodes() {
		return inFromManyNodes(uniprotGraph.proteinUniGeneT);
	}

	public TitanUniGene(TitanVertex vertex, TitanUniprotGraph uniprotGraph) {

		super(vertex);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	@Override
	public TitanUniGeneType type() {

		return uniprotGraph.uniGeneT;
	}

	public static final class TitanUniGeneType
			implements
			TitanNode.Type<TitanUniGene, TitanUniGene.TitanUniGeneType>,
			UniprotGraph.UniGeneType<TitanUniGene, TitanUniGeneType> {

		public TitanUniGeneType(TitanUniprotGraph uniprotGraph) {
			this.uniprotGraph = uniprotGraph;
		}

		TitanUniprotGraph uniprotGraph;

		@Override
		public TitanKey titanKey() {

			return uniprotGraph.uniGeneTKey;
		}

		@Override
		public TitanUniGeneType value() {

			return uniprotGraph.uniGeneT;
		}

		@Override
		public TitanUniGene fromTitanVertex(TitanVertex vertex) {

			return new TitanUniGene(vertex, uniprotGraph);
		}

		// --------------------------------properties----------------------------------------
		public id id = new id();
		public final class id
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanUniGene, TitanUniGeneType, id, String>,
				UniGene.id<TitanUniGene, TitanUniGeneType, id> {

			@Override
			public TitanUniGeneType elementType() {

				return TitanUniGeneType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.uniGeneIdKey;
			}
		}

	}
}

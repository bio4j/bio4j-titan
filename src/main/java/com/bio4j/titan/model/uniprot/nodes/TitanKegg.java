package com.bio4j.titan.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Kegg;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinKegg;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;

import java.util.List;

/**
 * Created by ppareja on 7/4/2014.
 */
public class TitanKegg extends
		TitanNode<TitanKegg, TitanKegg.TitanKeggType>
		implements Kegg<TitanKegg, TitanKegg.TitanKeggType> {

	@Override
	public String id(){ return get(uniprotGraph.keggT.id);}

	@Override
	public List<TitanProteinKegg> proteinKegg_in() {
		return inFromMany(uniprotGraph.proteinKeggT);
	}

	@Override
	public List<TitanProtein> proteinKegg_inNodes() {
		return inFromManyNodes(uniprotGraph.proteinKeggT);
	}

	public TitanKegg(TitanVertex vertex, TitanUniprotGraph uniprotGraph) {

		super(vertex);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	@Override
	public TitanKeggType type() {

		return uniprotGraph.keggT;
	}

	public static final class TitanKeggType
			implements
			TitanNode.Type<TitanKegg, TitanKegg.TitanKeggType>,
			UniprotGraph.KeggType<TitanKegg, TitanKeggType> {

		public TitanKeggType(TitanUniprotGraph uniprotGraph) {
			this.uniprotGraph = uniprotGraph;
		}

		TitanUniprotGraph uniprotGraph;

		@Override
		public TitanKey titanKey() {

			return uniprotGraph.keggTKey;
		}

		@Override
		public TitanKeggType value() {

			return uniprotGraph.keggT;
		}

		@Override
		public TitanKegg fromTitanVertex(TitanVertex vertex) {

			return new TitanKegg(vertex, uniprotGraph);
		}

		// --------------------------------properties----------------------------------------

		public id id = new id();

		public final class id
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanKegg, TitanKeggType, id, String>,
				Kegg.id<TitanKegg, TitanKeggType, id> {

			@Override
			public TitanKeggType elementType() {

				return TitanKeggType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.keggIdKey;
			}
		}
	}
}

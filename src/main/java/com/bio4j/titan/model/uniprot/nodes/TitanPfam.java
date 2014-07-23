package com.bio4j.titan.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Pfam;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinPfam;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;

import java.util.List;

/**
 * Created by ppareja on 7/1/2014.
 */
public class TitanPfam  extends
		TitanNode<TitanPfam, TitanPfam.TitanPfamType>
		implements Pfam<TitanPfam, TitanPfam.TitanPfamType> {

	@Override
	public String name(){ return get(uniprotGraph.pfamT.name);}
	@Override
	public String id(){ return get(uniprotGraph.pfamT.id);}

	@Override
	public List<TitanProteinPfam> proteinPfam_in() {
		return inFromMany(uniprotGraph.proteinPfamT);
	}

	@Override
	public List<TitanProtein> proteinPfam_inNodes() {
		return inFromManyNodes(uniprotGraph.proteinPfamT);
	}

	public TitanPfam(TitanVertex vertex, TitanUniprotGraph uniprotGraph) {

		super(vertex);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	@Override
	public TitanPfamType type() {

		return uniprotGraph.pfamT;
	}

	public static final class TitanPfamType
			implements
			TitanNode.Type<TitanPfam, TitanPfam.TitanPfamType>,
			UniprotGraph.PfamType<TitanPfam, TitanPfamType> {

		public TitanPfamType(TitanUniprotGraph uniprotGraph) {
			this.uniprotGraph = uniprotGraph;
		}

		TitanUniprotGraph uniprotGraph;

		@Override
		public TitanKey titanKey() {

			return uniprotGraph.pfamTKey;
		}

		@Override
		public TitanPfamType value() {

			return uniprotGraph.pfamT;
		}

		@Override
		public TitanPfam fromTitanVertex(TitanVertex vertex) {

			return new TitanPfam(vertex, uniprotGraph);
		}

		// --------------------------------properties----------------------------------------
		public name name = new name();

		public final class name
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanPfam, TitanPfamType, name, String>,
				Pfam.name<TitanPfam, TitanPfamType, name> {

			@Override
			public TitanPfamType elementType() {

				return TitanPfamType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.pfamNameKey;
			}
		}

		public id id = new id();

		public final class id
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanPfam, TitanPfamType, id, String>,
				Pfam.id<TitanPfam, TitanPfamType, id> {

			@Override
			public TitanPfamType elementType() {

				return TitanPfamType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.pfamIdKey;
			}
		}
	}
}

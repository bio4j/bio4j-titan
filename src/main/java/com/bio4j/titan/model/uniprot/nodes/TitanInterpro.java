package com.bio4j.titan.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Interpro;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinInterpro;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;

import java.util.List;

/**
 * Created by ppareja on 7/1/2014.
 */
public class TitanInterpro extends
		TitanNode<TitanInterpro, TitanInterpro.TitanInterproType>
		implements Interpro<TitanInterpro, TitanInterpro.TitanInterproType> {

	@Override
	public String name(){ return get(uniprotGraph.interproT.name);}
	@Override
	public String id(){ return get(uniprotGraph.interproT.id);}

	@Override
	public List<TitanProteinInterpro> proteinInterpro_in() {
		return inFromMany(uniprotGraph.proteinInterproT);
	}

	@Override
	public List<TitanProtein> proteinInterpro_inNodes() {
		return inFromManyNodes(uniprotGraph.proteinInterproT);
	}

	public TitanInterpro(TitanVertex vertex, TitanUniprotGraph uniprotGraph) {

		super(vertex);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	@Override
	public TitanInterproType type() {

		return uniprotGraph.interproT;
	}

	public static final class TitanInterproType
			implements
			TitanNode.Type<TitanInterpro, TitanInterpro.TitanInterproType>,
			UniprotGraph.InterproType<TitanInterpro, TitanInterproType> {

		public TitanInterproType(TitanUniprotGraph uniprotGraph) {
			this.uniprotGraph = uniprotGraph;
		}

		TitanUniprotGraph uniprotGraph;

		@Override
		public TitanKey titanKey() {

			return uniprotGraph.interproTKey;
		}

		@Override
		public TitanInterproType value() {

			return uniprotGraph.interproT;
		}

		@Override
		public TitanInterpro fromTitanVertex(TitanVertex vertex) {

			return new TitanInterpro(vertex, uniprotGraph);
		}

		// --------------------------------properties----------------------------------------
		public name name = new name();

		public final class name
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanInterpro, TitanInterproType, name, String>,
				Interpro.name<TitanInterpro, TitanInterproType, name> {

			@Override
			public TitanInterproType elementType() {

				return TitanInterproType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.interproNameKey;
			}
		}

		public id id = new id();

		public final class id
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanInterpro, TitanInterproType, id, String>,
				Interpro.id<TitanInterpro, TitanInterproType, id> {

			@Override
			public TitanInterproType elementType() {

				return TitanInterproType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.interproIdKey;
			}
		}
	}
}

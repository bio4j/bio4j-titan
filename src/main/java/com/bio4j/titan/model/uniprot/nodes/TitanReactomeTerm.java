package com.bio4j.titan.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.ReactomeTerm;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinReactomeTerm;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;

import java.util.List;

/**
 * Created by ppareja on 7/1/2014.
 */
public class TitanReactomeTerm extends
		TitanNode<TitanReactomeTerm, TitanReactomeTerm.TitanReactomeTermType>
		implements ReactomeTerm<TitanReactomeTerm, TitanReactomeTerm.TitanReactomeTermType> {

	@Override
	public String pathwayName(){ return get(uniprotGraph.reactomeTermT.pathwayName);}
	@Override
	public String id(){ return get(uniprotGraph.reactomeTermT.id);}

	@Override
	public List<TitanProteinReactomeTerm> proteinReactomeTerm_in() {
		return inFromMany(uniprotGraph.proteinReactomeTermT);
	}

	@Override
	public List<TitanProtein> proteinReactomeTerm_inNodes() {
		return inFromManyNodes(uniprotGraph.proteinReactomeTermT);
	}

	public TitanReactomeTerm(TitanVertex vertex, TitanUniprotGraph uniprotGraph) {

		super(vertex);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	@Override
	public TitanReactomeTermType type() {

		return uniprotGraph.reactomeTermT;
	}

	public static final class TitanReactomeTermType
			implements
			TitanNode.Type<TitanReactomeTerm, TitanReactomeTerm.TitanReactomeTermType>,
			UniprotGraph.ReactomeTermType<TitanReactomeTerm, TitanReactomeTermType> {

		public TitanReactomeTermType(TitanUniprotGraph uniprotGraph) {
			this.uniprotGraph = uniprotGraph;
		}

		TitanUniprotGraph uniprotGraph;

		@Override
		public TitanKey titanKey() {

			return uniprotGraph.reactomeTermTKey;
		}

		@Override
		public TitanReactomeTermType value() {

			return uniprotGraph.reactomeTermT;
		}

		@Override
		public TitanReactomeTerm fromTitanVertex(TitanVertex vertex) {

			return new TitanReactomeTerm(vertex, uniprotGraph);
		}

		// --------------------------------properties----------------------------------------
		public pathwayName pathwayName = new pathwayName();

		public final class pathwayName
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanReactomeTerm, TitanReactomeTermType, pathwayName, String>,
				ReactomeTerm.pathwayName<TitanReactomeTerm, TitanReactomeTermType, pathwayName> {

			@Override
			public TitanReactomeTermType elementType() {

				return TitanReactomeTermType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.reactomeTermPathwayNameKey;
			}
		}

		public id id = new id();

		public final class id
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanReactomeTerm, TitanReactomeTermType, id, String>,
				ReactomeTerm.id<TitanReactomeTerm, TitanReactomeTermType, id> {

			@Override
			public TitanReactomeTermType elementType() {

				return TitanReactomeTermType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.reactomeTermIdKey;
			}
		}
	}
}

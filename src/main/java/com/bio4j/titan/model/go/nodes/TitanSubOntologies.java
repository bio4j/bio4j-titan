package com.bio4j.titan.model.go.nodes;

import com.bio4j.model.go.GoGraph.SubOntologiesType;
import com.bio4j.model.go.nodes.SubOntologies;
import com.bio4j.titan.model.go.TitanGoGraph;
import com.bio4j.titan.model.go.relationships.TitanSubOntology;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;

import java.util.List;

// properties
// relationships

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo
 *         Pareja-Tobes</a>
 */
public final class TitanSubOntologies
		extends
		TitanNode<TitanSubOntologies, TitanSubOntologies.TitanSubOntologiesType>
		implements
		SubOntologies<TitanSubOntologies, TitanSubOntologies.TitanSubOntologiesType> {

	@Override
	public String name(){   return get(goGraph.subOntologiesT.name);}

	public TitanSubOntologies(TitanVertex vertex, TitanGoGraph goGraph) {
		super(vertex);
		this.goGraph = goGraph;
	}

	TitanGoGraph goGraph;

	@Override
	public TitanSubOntologiesType type() {
		return goGraph.subOntologiesT;
	}


	public static final class TitanSubOntologiesType
			implements
			TitanNode.Type<TitanSubOntologies, TitanSubOntologies.TitanSubOntologiesType>,
			SubOntologiesType<TitanSubOntologies, TitanSubOntologiesType> {

		public TitanSubOntologiesType(TitanGoGraph goGraph) {
			this.goGraph = goGraph;
		}

		TitanGoGraph goGraph;

		@Override
		public TitanKey titanKey() {
			return goGraph.subOntologiesTKey;
		}

		@Override
		public TitanSubOntologiesType value() {
			return goGraph.subOntologiesT;
		}

		@Override
		public TitanSubOntologies fromTitanVertex(TitanVertex vertex) {

			return new TitanSubOntologies(vertex, goGraph);
		}

		public name name = new name();

		public final class name
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanSubOntologies, TitanSubOntologiesType, name, String>,
				SubOntologies.name<TitanSubOntologies, TitanSubOntologiesType, name> {

			@Override
			public TitanSubOntologiesType elementType() {

				return TitanSubOntologiesType.this;
			}

			@Override
			public TitanKey titanKey() {

				return goGraph.subOntologiesNameKey;
			}
		}
	}

	// SubOntology
	// ingoing
	@Override
	public List<TitanSubOntology> subOntology_in() {
		return inFromMany(goGraph.subOntologyT);
	}

	@Override
	public List<TitanGoTerm> term_inNodes() {
		return inFromManyNodes(goGraph.subOntologyT);
	}


}
package com.bio4j.titan.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph.OrganismType;
import com.bio4j.model.uniprot.nodes.Organism;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.relationships.ProteinOrganism;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinOrganism;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;

import java.util.List;

/**
 * Created by ppareja on 6/27/2014.
 */
public class TitanOrganism extends
		TitanNode<TitanOrganism, TitanOrganism.TitanOrganismType>
		implements Organism<TitanOrganism, TitanOrganism.TitanOrganismType> {

	@Override
	public String name(){ return get(uniprotGraph.organismT.name);}

	@Override
	public List<TitanProteinOrganism> proteinOrganism_in() {
		return inFromMany(uniprotGraph.proteinOrganismT);
	}

	@Override
	public List<TitanProtein> proteinOrganism_inNodes() {
		return inFromManyNodes(uniprotGraph.proteinOrganismT);
	}

	public TitanOrganism(TitanVertex vertex, TitanUniprotGraph uniprotGraph) {

		super(vertex);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	@Override
	public TitanOrganismType type() {

		return uniprotGraph.organismT;
	}

	public static final class TitanOrganismType
			implements
			TitanNode.Type<TitanOrganism, TitanOrganism.TitanOrganismType>,
			OrganismType<TitanOrganism, TitanOrganismType> {

		public TitanOrganismType(TitanUniprotGraph uniprotGraph) {
			this.uniprotGraph = uniprotGraph;
		}

		TitanUniprotGraph uniprotGraph;

		@Override
		public TitanKey titanKey() {

			return uniprotGraph.organismTKey;
		}

		@Override
		public TitanOrganismType value() {

			return uniprotGraph.organismT;
		}

		@Override
		public TitanOrganism fromTitanVertex(TitanVertex vertex) {

			return new TitanOrganism(vertex, uniprotGraph);
		}

		// --------------------------------properties----------------------------------------
		public name name = new name();

		public final class name
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanOrganism, TitanOrganismType, name, String>,
				Organism.name<TitanOrganism, TitanOrganismType, name> {

			@Override
			public TitanOrganismType elementType() {

				return TitanOrganismType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.organismNameKey;
			}
		}
	}
}

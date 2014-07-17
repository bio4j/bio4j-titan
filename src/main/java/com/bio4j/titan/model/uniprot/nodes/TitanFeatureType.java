package com.bio4j.titan.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.FeatureType;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinFeature;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;

import java.util.List;

/**
 * Created by ppareja on 7/17/2014.
 */
public class TitanFeatureType extends
		TitanNode<TitanFeatureType, TitanFeatureType.TitanFeatureTypeType>
		implements FeatureType<TitanFeatureType, TitanFeatureType.TitanFeatureTypeType> {

	@Override
	public String name(){ return get(uniprotGraph.featureTypeT.name);}

	@Override
	public List<TitanProteinFeature> proteinFeature_in() {
		return inFromMany(uniprotGraph.proteinFeatureT);
	}

	@Override
	public List<TitanProtein> proteinFeature_inNodes() {
		return inFromManyNodes(uniprotGraph.proteinFeatureT);
	}

	public TitanFeatureType(TitanVertex vertex, TitanUniprotGraph uniprotGraph) {

		super(vertex);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	@Override
	public TitanFeatureTypeType type() {

		return uniprotGraph.featureTypeT;
	}

	public static final class TitanFeatureTypeType
			implements
			TitanNode.Type<TitanFeatureType, TitanFeatureTypeType>,
			UniprotGraph.FeatureTypeType<TitanFeatureType, TitanFeatureTypeType> {

		public TitanFeatureTypeType(TitanUniprotGraph uniprotGraph) {
			this.uniprotGraph = uniprotGraph;
		}

		TitanUniprotGraph uniprotGraph;

		@Override
		public TitanKey titanKey() {

			return uniprotGraph.featureTypeTKey;
		}

		@Override
		public TitanFeatureTypeType value() {

			return uniprotGraph.featureTypeT;
		}

		@Override
		public TitanFeatureType fromTitanVertex(TitanVertex vertex) {

			return new TitanFeatureType(vertex, uniprotGraph);
		}

		// --------------------------------properties----------------------------------------

		public name name = new name();

		public final class name
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanFeatureType, TitanFeatureTypeType, name, String>,
				FeatureType.name<TitanFeatureType, TitanFeatureTypeType, name> {

			@Override
			public TitanFeatureTypeType elementType() {

				return TitanFeatureTypeType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.featureTypeNameKey;
			}
		}
	}
}

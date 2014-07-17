package com.bio4j.titan.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinFeature;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.nodes.TitanFeatureType;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 7/17/2014.
 */
public class TitanProteinFeature extends
		TitanRelationship<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanProteinFeature, TitanProteinFeature.TitanProteinFeatureType,
				TitanFeatureType, TitanFeatureType.TitanFeatureTypeType
				>
		implements
		ProteinFeature<
						TitanProtein, TitanProtein.TitanProteinType,
						TitanProteinFeature, TitanProteinFeature.TitanProteinFeatureType,
						TitanFeatureType, TitanFeatureType.TitanFeatureTypeType
						> {


	TitanProteinFeature(TitanEdge edge, TitanUniprotGraph uniprotGraph) {

		super(edge);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanProteinFeatureType type() {

		return uniprotGraph.proteinFeatureT;
	}

	public static final class TitanProteinFeatureType
			implements
			TitanRelationship.Type<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinFeature, TitanProteinFeatureType,
					TitanFeatureType, TitanFeatureType.TitanFeatureTypeType
					>,
			UniprotGraph.ProteinFeatureType<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinFeature, TitanProteinFeatureType,
					TitanFeatureType, TitanFeatureType.TitanFeatureTypeType
					> {

		TitanUniprotGraph uniprotGraph;

		public TitanProteinFeatureType(TitanUniprotGraph uniprotGraph) {

			this.uniprotGraph = uniprotGraph;
		}

		@Override
		public TitanLabel label() {
			return uniprotGraph.proteinFeatureLabel;
		}

		@Override
		public TitanProteinFeatureType value() {
			return uniprotGraph.proteinFeatureT;
		}

		@Override
		public TitanProtein.TitanProteinType sourceType() {
			return uniprotGraph.proteinT;
		}

		@Override
		public TitanFeatureType.TitanFeatureTypeType targetType() {
			return uniprotGraph.featureTypeT;
		}

		@Override
		public TitanProteinFeature fromTitanEdge(TitanEdge edge) {
			return new TitanProteinFeature(edge, uniprotGraph);
		}
	}
}


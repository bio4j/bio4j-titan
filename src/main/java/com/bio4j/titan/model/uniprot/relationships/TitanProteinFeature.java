package com.bio4j.titan.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinFeature;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.nodes.TitanFeatureType;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanKey;
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

	@Override
	public String id() {	return get(uniprotGraph.proteinFeatureT.id);	}

	@Override
	public String description() {
		return get(uniprotGraph.proteinFeatureT.description);
	}

	@Override
	public String evidence() {
		return get(uniprotGraph.proteinFeatureT.evidence);
	}

	@Override
	public String status() {
		return get(uniprotGraph.proteinFeatureT.status);
	}

	@Override
	public Integer begin() {
		return get(uniprotGraph.proteinFeatureT.begin);
	}

	@Override
	public Integer end() {
		return get(uniprotGraph.proteinFeatureT.end);
	}

	@Override
	public String original() {
		return get(uniprotGraph.proteinFeatureT.original);
	}

	@Override
	public String variation() {
		return get(uniprotGraph.proteinFeatureT.variation);
	}

	@Override
	public String ref() {
		return get(uniprotGraph.proteinFeatureT.ref);
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

		public id id = new id();
		public final class id
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanProteinFeature, TitanProteinFeatureType, id, String>,
				ProteinFeature.id<TitanProtein, TitanProtein.TitanProteinType,
						TitanProteinFeature, TitanProteinFeatureType,
						TitanFeatureType, TitanFeatureType.TitanFeatureTypeType, id> {

			@Override
			public TitanProteinFeatureType elementType() {

				return TitanProteinFeatureType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.proteinFeatureIdKey;
			}
		}

		public description description = new description();
		public final class description
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanProteinFeature, TitanProteinFeatureType, description, String>,
				ProteinFeature.description<TitanProtein, TitanProtein.TitanProteinType,
						TitanProteinFeature, TitanProteinFeatureType,
						TitanFeatureType, TitanFeatureType.TitanFeatureTypeType, description> {

			@Override
			public TitanProteinFeatureType elementType() {

				return TitanProteinFeatureType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.proteinFeatureDescriptionKey;
			}
		}

		public evidence evidence = new evidence();
		public final class evidence
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanProteinFeature, TitanProteinFeatureType, evidence, String>,
				ProteinFeature.evidence<TitanProtein, TitanProtein.TitanProteinType,
						TitanProteinFeature, TitanProteinFeatureType,
						TitanFeatureType, TitanFeatureType.TitanFeatureTypeType, evidence> {

			@Override
			public TitanProteinFeatureType elementType() {

				return TitanProteinFeatureType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.proteinFeatureEvidenceKey;
			}
		}

		public status status = new status();
		public final class status
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanProteinFeature, TitanProteinFeatureType, status, String>,
				ProteinFeature.status<TitanProtein, TitanProtein.TitanProteinType,
						TitanProteinFeature, TitanProteinFeatureType,
						TitanFeatureType, TitanFeatureType.TitanFeatureTypeType, status> {

			@Override
			public TitanProteinFeatureType elementType() {

				return TitanProteinFeatureType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.proteinFeatureStatusKey;
			}
		}

		public begin begin = new begin();
		public final class begin
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanProteinFeature, TitanProteinFeatureType, begin, Integer>,
				ProteinFeature.begin<TitanProtein, TitanProtein.TitanProteinType,
						TitanProteinFeature, TitanProteinFeatureType,
						TitanFeatureType, TitanFeatureType.TitanFeatureTypeType, begin> {

			@Override
			public TitanProteinFeatureType elementType() {

				return TitanProteinFeatureType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.proteinFeatureBeginKey;
			}
		}

		public end end = new end();
		public final class end
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanProteinFeature, TitanProteinFeatureType, end, Integer>,
				ProteinFeature.end<TitanProtein, TitanProtein.TitanProteinType,
						TitanProteinFeature, TitanProteinFeatureType,
						TitanFeatureType, TitanFeatureType.TitanFeatureTypeType, end> {

			@Override
			public TitanProteinFeatureType elementType() {

				return TitanProteinFeatureType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.proteinFeatureEndKey;
			}
		}

		public original original = new original();
		public final class original
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanProteinFeature, TitanProteinFeatureType, original, String>,
				ProteinFeature.original<TitanProtein, TitanProtein.TitanProteinType,
						TitanProteinFeature, TitanProteinFeatureType,
						TitanFeatureType, TitanFeatureType.TitanFeatureTypeType, original> {

			@Override
			public TitanProteinFeatureType elementType() {

				return TitanProteinFeatureType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.proteinFeatureOriginalKey;
			}
		}

		public variation variation = new variation();
		public final class variation
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanProteinFeature, TitanProteinFeatureType, variation, String>,
				ProteinFeature.variation<TitanProtein, TitanProtein.TitanProteinType,
						TitanProteinFeature, TitanProteinFeatureType,
						TitanFeatureType, TitanFeatureType.TitanFeatureTypeType, variation> {

			@Override
			public TitanProteinFeatureType elementType() {

				return TitanProteinFeatureType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.proteinFeatureVariationKey;
			}
		}

		public ref ref = new ref();
		public final class ref
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanProteinFeature, TitanProteinFeatureType, ref, String>,
				ProteinFeature.ref<TitanProtein, TitanProtein.TitanProteinType,
						TitanProteinFeature, TitanProteinFeatureType,
						TitanFeatureType, TitanFeatureType.TitanFeatureTypeType, ref> {

			@Override
			public TitanProteinFeatureType elementType() {

				return TitanProteinFeatureType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.proteinFeatureRefKey;
			}
		}

	}


}


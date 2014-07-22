package com.bio4j.titan.model.uniprot_uniref.relationships;

import com.bio4j.model.uniprot_uniref.UniprotUniRefGraph;
import com.bio4j.model.uniprot_uniref.relationships.UniRef50Representant;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.bio4j.titan.model.uniprot_uniref.TitanUniprotUniRefGraph;
import com.bio4j.titan.model.uniref.nodes.TitanUniRef50Cluster;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 7/22/2014.
 */
public class TitanUniRef50Representant extends
		TitanRelationship<
				TitanUniRef50Cluster, TitanUniRef50Cluster.TitanUniRef50ClusterType,
				TitanUniRef50Representant, TitanUniRef50Representant.TitanUniRef50RepresentantType,
				TitanProtein, TitanProtein.TitanProteinType
				>
		implements
		UniRef50Representant<
				TitanUniRef50Cluster, TitanUniRef50Cluster.TitanUniRef50ClusterType,
				TitanUniRef50Representant, TitanUniRef50Representant.TitanUniRef50RepresentantType,
				TitanProtein, TitanProtein.TitanProteinType
				> {

	TitanUniRef50Representant(TitanEdge edge, TitanUniprotUniRefGraph uniprotUniRefGraph) {

		super(edge);
		this.uniprotUniRefGraph = uniprotUniRefGraph;
	}

	TitanUniprotUniRefGraph uniprotUniRefGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanUniRef50RepresentantType type() {

		return uniprotUniRefGraph.uniRef50RepresentantT;
	}

	public static final class TitanUniRef50RepresentantType
			implements
			TitanRelationship.Type<
					TitanUniRef50Cluster, TitanUniRef50Cluster.TitanUniRef50ClusterType,
					TitanUniRef50Representant, TitanUniRef50Representant.TitanUniRef50RepresentantType,
					TitanProtein, TitanProtein.TitanProteinType
					>,
			UniprotUniRefGraph.UniRef50RepresentantType<
					TitanUniRef50Cluster, TitanUniRef50Cluster.TitanUniRef50ClusterType,
					TitanUniRef50Representant, TitanUniRef50RepresentantType,
					TitanProtein, TitanProtein.TitanProteinType
					> {

		TitanUniprotUniRefGraph uniprotUniRefGraph;

		public TitanUniRef50RepresentantType(TitanUniprotUniRefGraph uniprotUniRefGraph) {

			this.uniprotUniRefGraph = uniprotUniRefGraph;
		}

		@Override
		public TitanLabel label() {
			return uniprotUniRefGraph.uniRef50RepresentantLabel;
		}

		@Override
		public TitanUniRef50RepresentantType value() {
			return uniprotUniRefGraph.uniRef50RepresentantT;
		}

		@Override
		public TitanUniRef50Cluster.TitanUniRef50ClusterType sourceType() {
			return uniprotUniRefGraph.uniRefGraph.uniRef50ClusterT;
		}

		@Override
		public TitanProtein.TitanProteinType targetType() {
			return uniprotUniRefGraph.uniprotGraph.proteinT;
		}

		@Override
		public TitanUniRef50Representant fromTitanEdge(TitanEdge edge) {
			return new TitanUniRef50Representant(edge, uniprotUniRefGraph);
		}
	}
}
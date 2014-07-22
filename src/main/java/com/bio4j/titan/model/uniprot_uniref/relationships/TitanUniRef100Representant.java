package com.bio4j.titan.model.uniprot_uniref.relationships;

import com.bio4j.model.uniprot_uniref.UniprotUniRefGraph;
import com.bio4j.model.uniprot_uniref.relationships.UniRef100Representant;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.bio4j.titan.model.uniprot_uniref.TitanUniprotUniRefGraph;
import com.bio4j.titan.model.uniref.nodes.TitanUniRef100Cluster;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 7/22/2014.
 */
public class TitanUniRef100Representant extends
		TitanRelationship<
				TitanUniRef100Cluster, TitanUniRef100Cluster.TitanUniRef100ClusterType,
				TitanUniRef100Representant, TitanUniRef100Representant.TitanUniRef100RepresentantType,
				TitanProtein, TitanProtein.TitanProteinType
				>
		implements
		UniRef100Representant<
				TitanUniRef100Cluster, TitanUniRef100Cluster.TitanUniRef100ClusterType,
				TitanUniRef100Representant, TitanUniRef100Representant.TitanUniRef100RepresentantType,
				TitanProtein, TitanProtein.TitanProteinType
				> {

	TitanUniRef100Representant(TitanEdge edge, TitanUniprotUniRefGraph uniprotUniRefGraph) {

		super(edge);
		this.uniprotUniRefGraph = uniprotUniRefGraph;
	}

	TitanUniprotUniRefGraph uniprotUniRefGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanUniRef100RepresentantType type() {

		return uniprotUniRefGraph.uniRef100RepresentantT;
	}

	public static final class TitanUniRef100RepresentantType
			implements
			TitanRelationship.Type<
					TitanUniRef100Cluster, TitanUniRef100Cluster.TitanUniRef100ClusterType,
					TitanUniRef100Representant, TitanUniRef100Representant.TitanUniRef100RepresentantType,
					TitanProtein, TitanProtein.TitanProteinType
					>,
			UniprotUniRefGraph.UniRef100RepresentantType<
					TitanUniRef100Cluster, TitanUniRef100Cluster.TitanUniRef100ClusterType,
					TitanUniRef100Representant, TitanUniRef100RepresentantType,
					TitanProtein, TitanProtein.TitanProteinType
					> {

		TitanUniprotUniRefGraph uniprotUniRefGraph;

		public TitanUniRef100RepresentantType(TitanUniprotUniRefGraph uniprotUniRefGraph) {

			this.uniprotUniRefGraph = uniprotUniRefGraph;
		}

		@Override
		public TitanLabel label() {
			return uniprotUniRefGraph.uniRef100RepresentantLabel;
		}

		@Override
		public TitanUniRef100RepresentantType value() {
			return uniprotUniRefGraph.uniRef100RepresentantT;
		}

		@Override
		public TitanUniRef100Cluster.TitanUniRef100ClusterType sourceType() {
			return uniprotUniRefGraph.uniRefGraph.uniRef100ClusterT;
		}

		@Override
		public TitanProtein.TitanProteinType targetType() {
			return uniprotUniRefGraph.uniprotGraph.proteinT;
		}

		@Override
		public TitanUniRef100Representant fromTitanEdge(TitanEdge edge) {
			return new TitanUniRef100Representant(edge, uniprotUniRefGraph);
		}
	}
}

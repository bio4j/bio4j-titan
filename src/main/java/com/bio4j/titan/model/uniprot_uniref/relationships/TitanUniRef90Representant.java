package com.bio4j.titan.model.uniprot_uniref.relationships;

import com.bio4j.model.uniprot_uniref.UniprotUniRefGraph;
import com.bio4j.model.uniprot_uniref.relationships.UniRef90Representant;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.bio4j.titan.model.uniprot_uniref.TitanUniprotUniRefGraph;
import com.bio4j.titan.model.uniref.nodes.TitanUniRef90Cluster;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 7/22/2014.
 */
public class TitanUniRef90Representant extends
		TitanRelationship<
				TitanUniRef90Cluster, TitanUniRef90Cluster.TitanUniRef90ClusterType,
				TitanUniRef90Representant, TitanUniRef90Representant.TitanUniRef90RepresentantType,
				TitanProtein, TitanProtein.TitanProteinType
				>
		implements
		UniRef90Representant<
				TitanUniRef90Cluster, TitanUniRef90Cluster.TitanUniRef90ClusterType,
				TitanUniRef90Representant, TitanUniRef90Representant.TitanUniRef90RepresentantType,
				TitanProtein, TitanProtein.TitanProteinType
				> {

	TitanUniRef90Representant(TitanEdge edge, TitanUniprotUniRefGraph uniprotUniRefGraph) {

		super(edge);
		this.uniprotUniRefGraph = uniprotUniRefGraph;
	}

	TitanUniprotUniRefGraph uniprotUniRefGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanUniRef90RepresentantType type() {

		return uniprotUniRefGraph.uniRef90RepresentantT;
	}

	public static final class TitanUniRef90RepresentantType
			implements
			TitanRelationship.Type<
					TitanUniRef90Cluster, TitanUniRef90Cluster.TitanUniRef90ClusterType,
					TitanUniRef90Representant, TitanUniRef90Representant.TitanUniRef90RepresentantType,
					TitanProtein, TitanProtein.TitanProteinType
					>,
			UniprotUniRefGraph.UniRef90RepresentantType<
					TitanUniRef90Cluster, TitanUniRef90Cluster.TitanUniRef90ClusterType,
					TitanUniRef90Representant, TitanUniRef90RepresentantType,
					TitanProtein, TitanProtein.TitanProteinType
					> {

		TitanUniprotUniRefGraph uniprotUniRefGraph;

		public TitanUniRef90RepresentantType(TitanUniprotUniRefGraph uniprotUniRefGraph) {

			this.uniprotUniRefGraph = uniprotUniRefGraph;
		}

		@Override
		public TitanLabel label() {
			return uniprotUniRefGraph.uniRef90RepresentantLabel;
		}

		@Override
		public TitanUniRef90RepresentantType value() {
			return uniprotUniRefGraph.uniRef90RepresentantT;
		}

		@Override
		public TitanUniRef90Cluster.TitanUniRef90ClusterType sourceType() {
			return uniprotUniRefGraph.uniRefGraph.uniRef90ClusterT;
		}

		@Override
		public TitanProtein.TitanProteinType targetType() {
			return uniprotUniRefGraph.uniprotGraph.proteinT;
		}

		@Override
		public TitanUniRef90Representant fromTitanEdge(TitanEdge edge) {
			return new TitanUniRef90Representant(edge, uniprotUniRefGraph);
		}
	}
}

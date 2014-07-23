package com.bio4j.titan.model.uniref.nodes;

import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.model.uniref.nodes.UniRef50Cluster;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.bio4j.titan.model.uniprot_uniref.relationships.TitanUniRef50Member;
import com.bio4j.titan.model.uniprot_uniref.relationships.TitanUniRef50Representant;
import com.bio4j.titan.model.uniref.TitanUniRefGraph;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;

import java.util.List;

/**
 * Created by ppareja on 7/22/2014
 */
public class TitanUniRef50Cluster extends
		TitanNode<TitanUniRef50Cluster, TitanUniRef50Cluster.TitanUniRef50ClusterType>
		implements UniRef50Cluster<TitanUniRef50Cluster, TitanUniRef50Cluster.TitanUniRef50ClusterType> {

	public TitanUniRef50Cluster(TitanVertex vertex, TitanUniRefGraph uniRefGraph) {

		super(vertex);
		this.uniRefGraph = uniRefGraph;
	}

	TitanUniRefGraph uniRefGraph;

	@Override
	public TitanUniRef50ClusterType type() {

		return uniRefGraph.uniRef50ClusterT;
	}

	@Override
	public String id() {
		return get(uniRefGraph.uniRef50ClusterT.id);
	}

	@Override
	public List<TitanUniRef50Member> uniRef50Member_out() {
		return outToMany(uniRefGraph.uniprotUniRefGraph.uniRef50MemberT);
	}
	@Override
	public List<TitanProtein> uniRef50Member_outNodes() {
		return outToManyNodes(uniRefGraph.uniprotUniRefGraph.uniRef50MemberT);
	}
	@Override
	public TitanUniRef50Representant uniRef50Representant_out() {
		return outToOne(uniRefGraph.uniprotUniRefGraph.uniRef50RepresentantT);
	}
	@Override
	public TitanProtein uniRef50Representant_outNode() {
		return outToOneNode(uniRefGraph.uniprotUniRefGraph.uniRef50RepresentantT);
	}

	public static final class TitanUniRef50ClusterType
			implements
			TitanNode.Type<TitanUniRef50Cluster, TitanUniRef50Cluster.TitanUniRef50ClusterType>,
			UniRefGraph.UniRef50ClusterType<TitanUniRef50Cluster, TitanUniRef50ClusterType> {

		public TitanUniRef50ClusterType(TitanUniRefGraph uniRefGraph) {
			this.uniRefGraph = uniRefGraph;
		}

		TitanUniRefGraph uniRefGraph;

		@Override
		public TitanKey titanKey() {

			return uniRefGraph.uniRef50ClusterTkey;
		}

		@Override
		public TitanUniRef50ClusterType value() {

			return uniRefGraph.uniRef50ClusterT;
		}

		@Override
		public TitanUniRef50Cluster fromTitanVertex(TitanVertex vertex) {

			return new TitanUniRef50Cluster(vertex, uniRefGraph);
		}

		// --------------------------------properties----------------------------------------
		public id id = new id();
		public final class id
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanUniRef50Cluster, TitanUniRef50ClusterType, id, String>,
				UniRef50Cluster.id<TitanUniRef50Cluster, TitanUniRef50ClusterType, id> {

			@Override
			public TitanUniRef50ClusterType elementType() {

				return TitanUniRef50ClusterType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniRefGraph.uniRef50ClusterIdKey;
			}
		}


	}
}

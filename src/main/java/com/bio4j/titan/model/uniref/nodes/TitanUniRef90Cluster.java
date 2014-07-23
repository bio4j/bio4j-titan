package com.bio4j.titan.model.uniref.nodes;

import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.model.uniref.nodes.UniRef90Cluster;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.bio4j.titan.model.uniprot_uniref.relationships.TitanUniRef90Member;
import com.bio4j.titan.model.uniprot_uniref.relationships.TitanUniRef90Representant;
import com.bio4j.titan.model.uniref.TitanUniRefGraph;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;

import java.util.List;

/**
 * Created by ppareja on 7/22/2014
 */
public class TitanUniRef90Cluster extends
		TitanNode<TitanUniRef90Cluster, TitanUniRef90Cluster.TitanUniRef90ClusterType>
		implements UniRef90Cluster<TitanUniRef90Cluster, TitanUniRef90Cluster.TitanUniRef90ClusterType> {

	public TitanUniRef90Cluster(TitanVertex vertex, TitanUniRefGraph uniRefGraph) {

		super(vertex);
		this.uniRefGraph = uniRefGraph;
	}

	TitanUniRefGraph uniRefGraph;

	@Override
	public TitanUniRef90ClusterType type() {

		return uniRefGraph.uniRef90ClusterT;
	}

	@Override
	public String id() {
		return get(uniRefGraph.uniRef90ClusterT.id);
	}

	@Override
	public List<TitanUniRef90Member> uniRef90Member_out() {
		return outToMany(uniRefGraph.uniprotUniRefGraph.uniRef90MemberT);
	}
	@Override
	public List<TitanProtein> uniRef90Member_outNodes() {
		return outToManyNodes(uniRefGraph.uniprotUniRefGraph.uniRef90MemberT);
	}
	@Override
	public TitanUniRef90Representant uniRef90Representant_out() {
		return outToOne(uniRefGraph.uniprotUniRefGraph.uniRef90RepresentantT);
	}
	@Override
	public TitanProtein uniRef90Representant_outNode() {
		return outToOneNode(uniRefGraph.uniprotUniRefGraph.uniRef90RepresentantT);
	}

	public static final class TitanUniRef90ClusterType
			implements
			TitanNode.Type<TitanUniRef90Cluster, TitanUniRef90Cluster.TitanUniRef90ClusterType>,
			UniRefGraph.UniRef90ClusterType<TitanUniRef90Cluster, TitanUniRef90ClusterType> {

		public TitanUniRef90ClusterType(TitanUniRefGraph uniRefGraph) {
			this.uniRefGraph = uniRefGraph;
		}

		TitanUniRefGraph uniRefGraph;

		@Override
		public TitanKey titanKey() {

			return uniRefGraph.uniRef90ClusterTkey;
		}

		@Override
		public TitanUniRef90ClusterType value() {

			return uniRefGraph.uniRef90ClusterT;
		}

		@Override
		public TitanUniRef90Cluster fromTitanVertex(TitanVertex vertex) {

			return new TitanUniRef90Cluster(vertex, uniRefGraph);
		}

		// --------------------------------properties----------------------------------------
		public id id = new id();
		public final class id
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanUniRef90Cluster, TitanUniRef90ClusterType, id, String>,
				UniRef90Cluster.id<TitanUniRef90Cluster, TitanUniRef90ClusterType, id> {

			@Override
			public TitanUniRef90ClusterType elementType() {

				return TitanUniRef90ClusterType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniRefGraph.uniRef90ClusterIdKey;
			}
		}


	}
}

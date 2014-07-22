package com.bio4j.titan.model.uniref.nodes;

import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.model.uniref.nodes.UniRef100Cluster;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.bio4j.titan.model.uniprot_uniref.relationships.TitanUniRef100Member;
import com.bio4j.titan.model.uniprot_uniref.relationships.TitanUniRef100Representant;
import com.bio4j.titan.model.uniref.TitanUniRefGraph;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;

import java.util.List;

/**
 * Created by ppareja on 7/22/2014
 */
public class TitanUniRef100Cluster extends
		TitanNode<TitanUniRef100Cluster, TitanUniRef100Cluster.TitanUniRef100ClusterType>
		implements UniRef100Cluster<TitanUniRef100Cluster, TitanUniRef100Cluster.TitanUniRef100ClusterType> {

	public TitanUniRef100Cluster(TitanVertex vertex, TitanUniRefGraph uniRefGraph) {

		super(vertex);
		this.uniRefGraph = uniRefGraph;
	}

	TitanUniRefGraph uniRefGraph;

	@Override
	public TitanUniRef100ClusterType type() {

		return uniRefGraph.uniRef100ClusterT;
	}

	@Override
	public String id() {
		return get(uniRefGraph.uniRef100ClusterT.id);
	}

	@Override
	public List<TitanUniRef100Member> uniRef100Member_out() {
		return outToMany(uniRefGraph.uniprotUniRefGraph.uniRef100MemberT);
	}
	@Override
	public List<TitanProtein> uniRef100Member_outNodes() {
		return outToManyNodes(uniRefGraph.uniprotUniRefGraph.uniRef100MemberT);
	}
	@Override
	public TitanUniRef100Representant uniRef100Representant_out() {
		return outToOne(uniRefGraph.uniprotUniRefGraph.uniRef100RepresentantT);
	}
	@Override
	public TitanProtein uniRef100Representant_outNode() {
		return outToOneNode(uniRefGraph.uniprotUniRefGraph.uniRef100RepresentantT);
	}

	public static final class TitanUniRef100ClusterType
			implements
			TitanNode.Type<TitanUniRef100Cluster, TitanUniRef100Cluster.TitanUniRef100ClusterType>,
			UniRefGraph.UniRef100ClusterType<TitanUniRef100Cluster, TitanUniRef100ClusterType> {

		public TitanUniRef100ClusterType(TitanUniRefGraph uniRefGraph) {
			this.uniRefGraph = uniRefGraph;
		}

		TitanUniRefGraph uniRefGraph;

		@Override
		public TitanKey titanKey() {

			return uniRefGraph.uniRef100ClusterTkey;
		}

		@Override
		public TitanUniRef100ClusterType value() {

			return uniRefGraph.uniRef100ClusterT;
		}

		@Override
		public TitanUniRef100Cluster fromTitanVertex(TitanVertex vertex) {

			return new TitanUniRef100Cluster(vertex, uniRefGraph);
		}

		// --------------------------------properties----------------------------------------
		public id id = new id();
		public final class id
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanUniRef100Cluster, TitanUniRef100ClusterType, id, String>,
				UniRef100Cluster.id<TitanUniRef100Cluster, TitanUniRef100ClusterType, id> {

			@Override
			public TitanUniRef100ClusterType elementType() {

				return TitanUniRef100ClusterType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniRefGraph.uniRef100ClusterIdKey;
			}
		}


	}
}

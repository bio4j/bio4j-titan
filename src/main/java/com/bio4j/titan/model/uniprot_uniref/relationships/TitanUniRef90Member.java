package com.bio4j.titan.model.uniprot_uniref.relationships;

import com.bio4j.model.uniprot_uniref.UniprotUniRefGraph;
import com.bio4j.model.uniprot_uniref.relationships.UniRef90Member;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.bio4j.titan.model.uniprot_uniref.TitanUniprotUniRefGraph;
import com.bio4j.titan.model.uniref.nodes.TitanUniRef90Cluster;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 7/22/2014.
 */
public class TitanUniRef90Member extends
		TitanRelationship<
				TitanUniRef90Cluster, TitanUniRef90Cluster.TitanUniRef90ClusterType,
				TitanUniRef90Member, TitanUniRef90Member.TitanUniRef90MemberType,
				TitanProtein, TitanProtein.TitanProteinType
				>
		implements
		UniRef90Member<
				TitanUniRef90Cluster, TitanUniRef90Cluster.TitanUniRef90ClusterType,
				TitanUniRef90Member, TitanUniRef90Member.TitanUniRef90MemberType,
				TitanProtein, TitanProtein.TitanProteinType
				> {

	TitanUniRef90Member(TitanEdge edge, TitanUniprotUniRefGraph uniprotUniRefGraph) {

		super(edge);
		this.uniprotUniRefGraph = uniprotUniRefGraph;
	}

	TitanUniprotUniRefGraph uniprotUniRefGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanUniRef90MemberType type() {

		return uniprotUniRefGraph.uniRef90MemberT;
	}

	public static final class TitanUniRef90MemberType
			implements
			TitanRelationship.Type<
					TitanUniRef90Cluster, TitanUniRef90Cluster.TitanUniRef90ClusterType,
					TitanUniRef90Member, TitanUniRef90Member.TitanUniRef90MemberType,
					TitanProtein, TitanProtein.TitanProteinType
					>,
			UniprotUniRefGraph.UniRef90MemberType<
					TitanUniRef90Cluster, TitanUniRef90Cluster.TitanUniRef90ClusterType,
					TitanUniRef90Member, TitanUniRef90MemberType,
					TitanProtein, TitanProtein.TitanProteinType
					> {

		TitanUniprotUniRefGraph uniprotUniRefGraph;

		public TitanUniRef90MemberType(TitanUniprotUniRefGraph uniprotUniRefGraph) {

			this.uniprotUniRefGraph = uniprotUniRefGraph;
		}

		@Override
		public TitanLabel label() {
			return uniprotUniRefGraph.uniRef90MemberLabel;
		}

		@Override
		public TitanUniRef90MemberType value() {
			return uniprotUniRefGraph.uniRef90MemberT;
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
		public TitanUniRef90Member fromTitanEdge(TitanEdge edge) {
			return new TitanUniRef90Member(edge, uniprotUniRefGraph);
		}
	}
}

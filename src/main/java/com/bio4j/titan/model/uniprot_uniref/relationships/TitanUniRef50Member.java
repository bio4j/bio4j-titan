package com.bio4j.titan.model.uniprot_uniref.relationships;

import com.bio4j.model.uniprot_uniref.UniprotUniRefGraph;
import com.bio4j.model.uniprot_uniref.relationships.UniRef50Member;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.bio4j.titan.model.uniprot_uniref.TitanUniprotUniRefGraph;
import com.bio4j.titan.model.uniref.nodes.TitanUniRef50Cluster;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 7/22/2014.
 */
public class TitanUniRef50Member extends
		TitanRelationship<
				TitanUniRef50Cluster, TitanUniRef50Cluster.TitanUniRef50ClusterType,
				TitanUniRef50Member, TitanUniRef50Member.TitanUniRef50MemberType,
				TitanProtein, TitanProtein.TitanProteinType
				>
		implements
		UniRef50Member<
				TitanUniRef50Cluster, TitanUniRef50Cluster.TitanUniRef50ClusterType,
				TitanUniRef50Member, TitanUniRef50Member.TitanUniRef50MemberType,
				TitanProtein, TitanProtein.TitanProteinType
						> {

	TitanUniRef50Member(TitanEdge edge, TitanUniprotUniRefGraph uniprotUniRefGraph) {

		super(edge);
		this.uniprotUniRefGraph = uniprotUniRefGraph;
	}

	TitanUniprotUniRefGraph uniprotUniRefGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanUniRef50MemberType type() {

		return uniprotUniRefGraph.uniRef50MemberT;
	}

	public static final class TitanUniRef50MemberType
			implements
			TitanRelationship.Type<
					TitanUniRef50Cluster, TitanUniRef50Cluster.TitanUniRef50ClusterType,
					TitanUniRef50Member, TitanUniRef50Member.TitanUniRef50MemberType,
					TitanProtein, TitanProtein.TitanProteinType
					>,
			UniprotUniRefGraph.UniRef50MemberType<
								TitanUniRef50Cluster, TitanUniRef50Cluster.TitanUniRef50ClusterType,
								TitanUniRef50Member, TitanUniRef50MemberType,
								TitanProtein, TitanProtein.TitanProteinType
								> {

		TitanUniprotUniRefGraph uniprotUniRefGraph;

		public TitanUniRef50MemberType(TitanUniprotUniRefGraph uniprotUniRefGraph) {

			this.uniprotUniRefGraph = uniprotUniRefGraph;
		}

		@Override
		public TitanLabel label() {
			return uniprotUniRefGraph.uniRef50MemberLabel;
		}

		@Override
		public TitanUniRef50MemberType value() {
			return uniprotUniRefGraph.uniRef50MemberT;
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
		public TitanUniRef50Member fromTitanEdge(TitanEdge edge) {
			return new TitanUniRef50Member(edge, uniprotUniRefGraph);
		}
	}
}

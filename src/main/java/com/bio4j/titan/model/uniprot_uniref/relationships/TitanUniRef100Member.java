package com.bio4j.titan.model.uniprot_uniref.relationships;

import com.bio4j.model.uniprot_uniref.UniprotUniRefGraph;
import com.bio4j.model.uniprot_uniref.relationships.UniRef100Member;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.bio4j.titan.model.uniprot_uniref.TitanUniprotUniRefGraph;
import com.bio4j.titan.model.uniref.nodes.TitanUniRef100Cluster;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 7/22/2014.
 */
public class TitanUniRef100Member extends
		TitanRelationship<
				TitanUniRef100Cluster, TitanUniRef100Cluster.TitanUniRef100ClusterType,
				TitanUniRef100Member, TitanUniRef100Member.TitanUniRef100MemberType,
				TitanProtein, TitanProtein.TitanProteinType
				>
		implements
		UniRef100Member<
				TitanUniRef100Cluster, TitanUniRef100Cluster.TitanUniRef100ClusterType,
				TitanUniRef100Member, TitanUniRef100Member.TitanUniRef100MemberType,
				TitanProtein, TitanProtein.TitanProteinType
				> {

	TitanUniRef100Member(TitanEdge edge, TitanUniprotUniRefGraph uniprotUniRefGraph) {

		super(edge);
		this.uniprotUniRefGraph = uniprotUniRefGraph;
	}

	TitanUniprotUniRefGraph uniprotUniRefGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanUniRef100MemberType type() {

		return uniprotUniRefGraph.uniRef100MemberT;
	}

	public static final class TitanUniRef100MemberType
			implements
			TitanRelationship.Type<
					TitanUniRef100Cluster, TitanUniRef100Cluster.TitanUniRef100ClusterType,
					TitanUniRef100Member, TitanUniRef100Member.TitanUniRef100MemberType,
					TitanProtein, TitanProtein.TitanProteinType
					>,
			UniprotUniRefGraph.UniRef100MemberType<
					TitanUniRef100Cluster, TitanUniRef100Cluster.TitanUniRef100ClusterType,
					TitanUniRef100Member, TitanUniRef100MemberType,
					TitanProtein, TitanProtein.TitanProteinType
					> {

		TitanUniprotUniRefGraph uniprotUniRefGraph;

		public TitanUniRef100MemberType(TitanUniprotUniRefGraph uniprotUniRefGraph) {

			this.uniprotUniRefGraph = uniprotUniRefGraph;
		}

		@Override
		public TitanLabel label() {
			return uniprotUniRefGraph.uniRef100MemberLabel;
		}

		@Override
		public TitanUniRef100MemberType value() {
			return uniprotUniRefGraph.uniRef100MemberT;
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
		public TitanUniRef100Member fromTitanEdge(TitanEdge edge) {
			return new TitanUniRef100Member(edge, uniprotUniRefGraph);
		}
	}
}

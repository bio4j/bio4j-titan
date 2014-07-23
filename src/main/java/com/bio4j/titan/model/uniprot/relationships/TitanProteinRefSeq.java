package com.bio4j.titan.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinRefSeq;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.bio4j.titan.model.uniprot.nodes.TitanRefSeq;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 7/14/2014.
 */
public class TitanProteinRefSeq extends
		TitanRelationship<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanProteinRefSeq, TitanProteinRefSeq.TitanProteinRefSeqType,
				TitanRefSeq, TitanRefSeq.TitanRefSeqType
				>
		implements
		ProteinRefSeq<
						TitanProtein, TitanProtein.TitanProteinType,
						TitanProteinRefSeq, TitanProteinRefSeq.TitanProteinRefSeqType,
						TitanRefSeq, TitanRefSeq.TitanRefSeqType
						> {


	TitanProteinRefSeq(TitanEdge edge, TitanUniprotGraph uniprotGraph) {

		super(edge);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanProteinRefSeqType type() {

		return uniprotGraph.proteinRefSeqT;
	}

	public static final class TitanProteinRefSeqType
			implements
			TitanRelationship.Type<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinRefSeq, TitanProteinRefSeqType,
					TitanRefSeq, TitanRefSeq.TitanRefSeqType
					>,
			UniprotGraph.ProteinRefSeqType <
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinRefSeq, TitanProteinRefSeqType,
					TitanRefSeq, TitanRefSeq.TitanRefSeqType
					> {

		TitanUniprotGraph uniprotGraph;

		public TitanProteinRefSeqType(TitanUniprotGraph uniprotGraph) {

			this.uniprotGraph = uniprotGraph;
		}

		@Override
		public TitanLabel label() {
			return uniprotGraph.proteinRefSeqLabel;
		}

		@Override
		public TitanProteinRefSeqType value() {
			return uniprotGraph.proteinRefSeqT;
		}

		@Override
		public TitanProtein.TitanProteinType sourceType() {
			return uniprotGraph.proteinT;
		}

		@Override
		public TitanRefSeq.TitanRefSeqType targetType() {
			return uniprotGraph.refSeqT;
		}

		@Override
		public TitanProteinRefSeq fromTitanEdge(TitanEdge edge) {
			return new TitanProteinRefSeq(edge, uniprotGraph);
		}
	}
}


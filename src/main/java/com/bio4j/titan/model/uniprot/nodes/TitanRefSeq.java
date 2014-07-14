package com.bio4j.titan.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.RefSeq;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinRefSeq;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;

import java.sql.Ref;
import java.util.List;

/**
 * Created by ppareja on 7/14/2014.
 */
public class TitanRefSeq extends
		TitanNode<TitanRefSeq, TitanRefSeq.TitanRefSeqType>
		implements RefSeq<TitanRefSeq, TitanRefSeq.TitanRefSeqType> {

	@Override
	public String nucleotideSequenceId(){ return get(uniprotGraph.refSeqT.nucleotideSequenceId);}
	@Override
	public String id(){ return get(uniprotGraph.refSeqT.id);}

	@Override
	public List<TitanProteinRefSeq> proteinRefSeq_in() {	return inFromMany(uniprotGraph.proteinRefSeqT);	}

	@Override
	public List<TitanProtein> proteinRefSeq_inNodes() {
		return inFromManyNodes(uniprotGraph.proteinRefSeqT);
	}

	public TitanRefSeq(TitanVertex vertex, TitanUniprotGraph uniprotGraph) {

		super(vertex);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	@Override
	public TitanRefSeqType type() {

		return uniprotGraph.refSeqT;
	}

	public static final class TitanRefSeqType
			implements
			TitanNode.Type<TitanRefSeq, TitanRefSeq.TitanRefSeqType>,
			UniprotGraph.RefSeqType<TitanRefSeq, TitanRefSeqType> {

		public TitanRefSeqType(TitanUniprotGraph uniprotGraph) {
			this.uniprotGraph = uniprotGraph;
		}

		TitanUniprotGraph uniprotGraph;

		@Override
		public TitanKey titanKey() {

			return uniprotGraph.refSeqTKey;
		}

		@Override
		public TitanRefSeqType value() {

			return uniprotGraph.refSeqT;
		}

		@Override
		public TitanRefSeq fromTitanVertex(TitanVertex vertex) {

			return new TitanRefSeq(vertex, uniprotGraph);
		}

		// --------------------------------properties----------------------------------------
		public id id = new id();
		public final class id
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanRefSeq, TitanRefSeqType, id, String>,
				RefSeq.id<TitanRefSeq, TitanRefSeqType, id> {

			@Override
			public TitanRefSeqType elementType() {

				return TitanRefSeqType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.refSeqIdKey;
			}
		}
		public nucleotideSequenceId nucleotideSequenceId = new nucleotideSequenceId();
		public final class nucleotideSequenceId
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanRefSeq, TitanRefSeqType, nucleotideSequenceId, String>,
				RefSeq.nucleotideSequenceId<TitanRefSeq, TitanRefSeqType, nucleotideSequenceId> {

			@Override
			public TitanRefSeqType elementType() {

				return TitanRefSeqType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.refSeqNucleotideSequenceIdKey;
			}
		}
	}
}

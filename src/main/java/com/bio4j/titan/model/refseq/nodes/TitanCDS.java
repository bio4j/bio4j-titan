package com.bio4j.titan.model.refseq.nodes;

import com.bio4j.model.refseq.nodes.CDS;
import com.bio4j.titan.model.refseq.TitanRefSeqGraph;

public class TitanCDS extends
		TitanRefSeqGraph.CDSNode<TitanCDS, TitanRefSeqGraph.TitanCDSType>
		implements CDS {

	public TitanGene(TitanVertex vertex, TitanRefSeqGraph graph) {
		super(vertex, graph);
	}

	@Override
	public TitanRefSeqGraph.TitanCDSType titanType() {
		return graph().titanCDSType();
	}

}

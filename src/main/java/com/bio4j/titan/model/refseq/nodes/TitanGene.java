package com.bio4j.titan.model.refseq.nodes;

import com.bio4j.model.refseq.nodes.Gene;

public class TitanGene
		extends
		TitanRefSeqGraph.GeneNode<TitanGene, TitanRefSeqGraph.TitanGeneType>
		implements Gene {

	public TitanGene(TitanVertex vertex, TitanRefSeqGraph graph) {
		super(vertex, graph);
	}

	@Override
	public TitanRefSeqGraph.TitanGeneType titanType() {
		return graph().titanGeneType();
	}

}

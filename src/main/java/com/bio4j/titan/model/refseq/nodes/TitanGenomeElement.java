package com.bio4j.titan.model.refseq.nodes;

import com.bio4j.model.refseq.nodes.GenomeElement;

public class TitanGenomeElement
		extends
		TitanRefSeqGraph.GenomeElementNode<TitanGenomeElement, TitanRefSeqGraph.TitanGenomeElementType>
		implements GenomeElement {
	
	public TitanGenomeElement(TitanVertex vertex, TitanRefSeqGraph graph) {
		super(vertex, graph);
	}

	@Override
	public TitanRefSeqGraph.TitanGenomeElementType titanType() {
		return graph().titanGenomeElementType();
	}

}

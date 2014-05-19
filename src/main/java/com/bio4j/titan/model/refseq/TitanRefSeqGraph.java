package com.bio4j.titan.model.refseq;

import com.ohnosequences.typedGraphs.titan.*;
import com.thinkaurelius.titan.core.*;
import com.bio4j.model.refseq.nodes.*;


public class TitanRefSeqGraph extends TitanTypedGraph {

	public TitanRefSeqGraph(TitanGraph graph) {
		super(graph);
	}

	/*
	 * For each node type, you need
	 * 
	 * - a TitanKey for indexing the type - a type instance of that node - a
	 * class for creating those type instances
	 */

	// NODES

	// GenomeElement
	public TitanGenomeElementType titanGenomeElementType() {
		return titanGenomeElementType;
	}

	private TitanKey titanGenomeElementKey;
	private TitanGenomeElementType titanGenomeElementType;

	public final class TitanGenomeElementType implements
			TitanNodeType<GenomeElement, GenomeElement.Type, TitanGenomeElement, TitanGenomeElementType>, GenomeElement.Type {
		@Override
		public GenomeElement.Type type() {
			return GenomeElement.TYPE;
		}

		@Override
		public TitanKey titanKey() {
			return TitanRefSeqGraph.this.titanGenomeElementKey;
		}

		@Override
		public TitanGenomeElement from(TitanVertex vertex) {
			return new TitanGenomeElement(vertex, TitanRefSeqGraph.this);
		}
	}

}

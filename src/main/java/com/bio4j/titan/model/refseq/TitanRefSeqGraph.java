package com.bio4j.titan.model.refseq;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;
import com.ohnosequences.typedGraphs.titan.*;
import com.thinkaurelius.titan.core.*;
import com.bio4j.model.refseq.nodes.*;
import com.bio4j.titan.model.refseq.nodes.TitanGene;
import com.bio4j.titan.model.refseq.nodes.TitanGenomeElement;

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

	public final class TitanGenomeElementType
			implements
			TitanNodeType<GenomeElement, GenomeElement.Type, TitanGenomeElement, TitanGenomeElementType>,
			GenomeElement.Type {
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

	// Gene
	public TitanGeneType titanGeneType() {
		return titanGeneType;
	}

	private TitanKey titanGeneKey;
	private TitanGeneType titanGeneType;

	public final class TitanGeneType implements
			TitanNodeType<Gene, Gene.Type, TitanGene, TitanGeneType>, Gene.Type {
		@Override
		public Gene.Type type() {
			return Gene.TYPE;
		}

		@Override
		public TitanKey titanKey() {
			return TitanRefSeqGraph.this.titanGeneKey;
		}

		@Override
		public TitanGene from(TitanVertex vertex) {
			return new TitanGene(vertex, TitanRefSeqGraph.this);
		}
	}

	// CDS
	public TitanCDSType titanCDSType() {
		return titanCDSType;
	}

	private TitanKey titanCDSKey;
	private TitanCDSType titanCDSType;

	public final class TitanCDSType implements
			TitanNodeType<CDS, CDS.Type, TitanCDS, TitanCDSType>, CDS.Type {
		@Override
		public CDS.Type type() {
			return CDS.TYPE;
		}

		@Override
		public TitanKey titanKey() {
			return TitanRefSeqGraph.this.titanCDSKey;
		}

		@Override
		public TitanCDS from(TitanVertex vertex) {
			return new TitanCDS(vertex, TitanRefSeqGraph.this);
		}
	}

	public static abstract class GenomeElementNode<N extends Node<N, NT>, NT extends NodeType<N, NT>, TitanN extends TitanNode<N, NT, TitanN, TitanNT>, TitanNT extends TitanNodeType<N, NT, TitanN, TitanNT>>
			extends TitanNode<N, NT, TitanN, TitanNT> {

		public GenomeElementNode(TitanVertex vertex, TitanRefSeqGraph graph) {
			super(vertex);
			this.refSeqGraph = graph;
		}

		private TitanRefSeqGraph refSeqGraph;

		@Override
		public TitanRefSeqGraph graph() {
			return refSeqGraph;
		}
	}

	public static abstract class GeneNode<N extends Node<N, NT>, NT extends NodeType<N, NT>, TitanN extends TitanNode<N, NT, TitanN, TitanNT>, TitanNT extends TitanNodeType<N, NT, TitanN, TitanNT>>
			extends TitanNode<N, NT, TitanN, TitanNT> {

		public GeneNode(TitanVertex vertex, TitanRefSeqGraph graph) {
			super(vertex);
			this.refSeqGraph = graph;
		}

		private TitanRefSeqGraph refSeqGraph;

		@Override
		public TitanRefSeqGraph graph() {
			return refSeqGraph;
		}
	}

	public static abstract class CDS<N extends Node<N, NT>, NT extends NodeType<N, NT>, TitanN extends TitanNode<N, NT, TitanN, TitanNT>, TitanNT extends TitanNodeType<N, NT, TitanN, TitanNT>>
			extends TitanNode<N, NT, TitanN, TitanNT> {

		public CDS(TitanVertex vertex, TitanRefSeqGraph graph) {
			super(vertex);
			this.refSeqGraph = graph;
		}

		private TitanRefSeqGraph refSeqGraph;

		@Override
		public TitanRefSeqGraph graph() {
			return refSeqGraph;
		}
	}

}

package com.bio4j.titan.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Taxon;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.relationships.TitanTaxonParent;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;

/**
 * Created by ppareja on 7/9/2014.
 */
public class TitanTaxon extends
		TitanNode<TitanTaxon, TitanTaxon.TitanTaxonType>
		implements Taxon<TitanTaxon, TitanTaxon.TitanTaxonType> {

	@Override
	public String name(){ return get(uniprotGraph.taxonT.name);}

	@Override
	public TitanTaxonParent taxonParent_in() {
		return inFromOne(uniprotGraph.taxonParentT);
	}
	@Override
	public TitanTaxon taxonParent_inNode() {
		return inFromOneNode(uniprotGraph.taxonParentT);
	}
	@Override
	public TitanTaxonParent taxonParent_out() {
		return outToOne(uniprotGraph.taxonParentT);
	}
	@Override
	public TitanTaxon taxonParent_outNode() {
		return outToOneNode(uniprotGraph.taxonParentT);
	}

	public TitanTaxon(TitanVertex vertex, TitanUniprotGraph uniprotGraph) {

		super(vertex);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	@Override
	public TitanTaxonType type() {

		return uniprotGraph.taxonT;
	}

	public static final class TitanTaxonType
			implements
			TitanNode.Type<TitanTaxon, TitanTaxon.TitanTaxonType>,
			UniprotGraph.TaxonType<TitanTaxon, TitanTaxonType> {

		public TitanTaxonType(TitanUniprotGraph uniprotGraph) {
			this.uniprotGraph = uniprotGraph;
		}

		TitanUniprotGraph uniprotGraph;

		@Override
		public TitanKey titanKey() {

			return uniprotGraph.taxonTKey;
		}

		@Override
		public TitanTaxonType value() {

			return uniprotGraph.taxonT;
		}

		@Override
		public TitanTaxon fromTitanVertex(TitanVertex vertex) {

			return new TitanTaxon(vertex, uniprotGraph);
		}

		// --------------------------------properties----------------------------------------
		public name name = new name();
		public final class name
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanTaxon, TitanTaxonType, name, String>,
				Taxon.name<TitanTaxon, TitanTaxonType, name> {

			@Override
			public TitanTaxonType elementType() {

				return TitanTaxonType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.taxonNameKey;
			}
		}
	}
}

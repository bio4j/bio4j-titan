package com.bio4j.titan.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.PIR;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinPIR;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;

import java.util.List;

/**
 * Created by ppareja on 7/7/2014.
 */
public class TitanPIR extends
		TitanNode<TitanPIR, TitanPIR.TitanPIRType>
		implements PIR<TitanPIR, TitanPIR.TitanPIRType> {

	@Override
	public String id(){ return get(uniprotGraph.pIRT.id);}
	@Override
	public String entryName(){  return get(uniprotGraph.pIRT.entryName);}

	@Override
	public List<TitanProteinPIR> proteinPIR_in() {
		return inFromMany(uniprotGraph.proteinPIRT);
	}

	@Override
	public List<TitanProtein> proteinPIR_inNodes() {
		return inFromManyNodes(uniprotGraph.proteinPIRT);
	}

	public TitanPIR(TitanVertex vertex, TitanUniprotGraph uniprotGraph) {

		super(vertex);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	@Override
	public TitanPIRType type() {

		return uniprotGraph.pIRT;
	}

	public static final class TitanPIRType
			implements
			TitanNode.Type<TitanPIR, TitanPIR.TitanPIRType>,
			UniprotGraph.PIRType<TitanPIR, TitanPIRType> {

		public TitanPIRType(TitanUniprotGraph uniprotGraph) {
			this.uniprotGraph = uniprotGraph;
		}

		TitanUniprotGraph uniprotGraph;

		@Override
		public TitanKey titanKey() {

			return uniprotGraph.pIRTKey;
		}

		@Override
		public TitanPIRType value() {

			return uniprotGraph.pIRT;
		}

		@Override
		public TitanPIR fromTitanVertex(TitanVertex vertex) {

			return new TitanPIR(vertex, uniprotGraph);
		}

		// --------------------------------properties----------------------------------------
		public id id = new id();
		public final class id
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanPIR, TitanPIRType, id, String>,
				PIR.id<TitanPIR, TitanPIRType, id> {

			@Override
			public TitanPIRType elementType() {

				return TitanPIRType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.pIRIdKey;
			}
		}
		public entryName entryName = new entryName();
		public final class entryName
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanPIR, TitanPIRType, entryName, String>,
				PIR.entryName<TitanPIR, TitanPIRType, entryName> {

			@Override
			public TitanPIRType elementType() {

				return TitanPIRType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.pIREntryNameKey;
			}
		}
	}
}

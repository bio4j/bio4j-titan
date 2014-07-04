package com.bio4j.titan.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.EMBL;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinEMBL;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;

import java.util.List;

/**
 * Created by ppareja on 7/4/2014.
 */
public class TitanEMBL extends
		TitanNode<TitanEMBL, TitanEMBL.TitanEMBLType>
		implements EMBL<TitanEMBL, TitanEMBL.TitanEMBLType> {

	@Override
	public String id(){ return get(uniprotGraph.eMBLT.id);}
	@Override
	public String proteinSequenceId(){ return get(uniprotGraph.eMBLT.proteinSequenceId);}
	@Override
	public String moleculeType(){ return get(uniprotGraph.eMBLT.moleculeType);}

	@Override
	public List<TitanProteinEMBL> proteinEMBL_in() {
		return inFromMany(uniprotGraph.proteinEMBLT);
	}

	@Override
	public List<TitanProtein> proteinEMBL_inNodes() {
		return inFromManyNodes(uniprotGraph.proteinEMBLT);
	}

	public TitanEMBL(TitanVertex vertex, TitanUniprotGraph uniprotGraph) {

		super(vertex);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	@Override
	public TitanEMBLType type() {

		return uniprotGraph.eMBLT;
	}

	public static final class TitanEMBLType
			implements
			TitanNode.Type<TitanEMBL, TitanEMBL.TitanEMBLType>,
			UniprotGraph.EMBLType<TitanEMBL, TitanEMBLType> {

		public TitanEMBLType(TitanUniprotGraph uniprotGraph) {
			this.uniprotGraph = uniprotGraph;
		}

		TitanUniprotGraph uniprotGraph;

		@Override
		public TitanKey titanKey() {

			return uniprotGraph.eMBLTKey;
		}

		@Override
		public TitanEMBLType value() {

			return uniprotGraph.eMBLT;
		}

		@Override
		public TitanEMBL fromTitanVertex(TitanVertex vertex) {

			return new TitanEMBL(vertex, uniprotGraph);
		}

		// --------------------------------properties----------------------------------------

		public id id = new id();
		public final class id
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanEMBL, TitanEMBLType, id, String>,
				EMBL.id<TitanEMBL, TitanEMBLType, id> {

			@Override
			public TitanEMBLType elementType() {

				return TitanEMBLType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.eMBLIdKey;
			}
		}
		public proteinSequenceId proteinSequenceId = new proteinSequenceId();
		public final class proteinSequenceId
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanEMBL, TitanEMBLType, proteinSequenceId, String>,
				EMBL.proteinSequenceId<TitanEMBL, TitanEMBLType, proteinSequenceId> {

			@Override
			public TitanEMBLType elementType() {

				return TitanEMBLType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.eMBLProteinSequenceIdKey;
			}
		}
		public moleculeType moleculeType = new moleculeType();
		public final class moleculeType
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanEMBL, TitanEMBLType, moleculeType, String>,
				EMBL.moleculeType<TitanEMBL, TitanEMBLType, moleculeType> {

			@Override
			public TitanEMBLType elementType() {

				return TitanEMBLType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.eMBLMoleculeTypeKey;
			}
		}
	}
}

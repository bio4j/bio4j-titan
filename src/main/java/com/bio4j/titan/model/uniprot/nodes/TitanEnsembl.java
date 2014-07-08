package com.bio4j.titan.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Ensembl;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinEnsembl;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;

import java.util.List;

/**
 * Created by ppareja on 7/8/2014.
 */
public class TitanEnsembl extends
		TitanNode<TitanEnsembl, TitanEnsembl.TitanEnsemblType>
		implements Ensembl<TitanEnsembl, TitanEnsembl.TitanEnsemblType> {

	@Override
	public String id(){ return get(uniprotGraph.ensemblT.id);}
	@Override
	public String proteinSequenceId(){ return get(uniprotGraph.ensemblT.proteinSequenceId);}
	@Override
	public String moleculeId(){ return get(uniprotGraph.ensemblT.moleculeId);}
	@Override
	public String geneId(){ return get(uniprotGraph.ensemblT.geneId);}

	@Override
	public List<TitanProteinEnsembl> proteinEnsembl_in() {
		return inFromMany(uniprotGraph.proteinEnsemblT);
	}

	@Override
	public List<TitanProtein> proteinEnsembl_inNodes() {
		return inFromManyNodes(uniprotGraph.proteinEnsemblT);
	}

	public TitanEnsembl(TitanVertex vertex, TitanUniprotGraph uniprotGraph) {

		super(vertex);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	@Override
	public TitanEnsemblType type() {

		return uniprotGraph.ensemblT;
	}

	public static final class TitanEnsemblType
			implements
			TitanNode.Type<TitanEnsembl, TitanEnsembl.TitanEnsemblType>,
			UniprotGraph.EnsemblType<TitanEnsembl, TitanEnsemblType> {

		public TitanEnsemblType(TitanUniprotGraph uniprotGraph) {
			this.uniprotGraph = uniprotGraph;
		}

		TitanUniprotGraph uniprotGraph;

		@Override
		public TitanKey titanKey() {

			return uniprotGraph.ensemblTKey;
		}

		@Override
		public TitanEnsemblType value() {

			return uniprotGraph.ensemblT;
		}

		@Override
		public TitanEnsembl fromTitanVertex(TitanVertex vertex) {

			return new TitanEnsembl(vertex, uniprotGraph);
		}

		// --------------------------------properties----------------------------------------

		public id id = new id();
		public final class id
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanEnsembl, TitanEnsemblType, id, String>,
				Ensembl.id<TitanEnsembl, TitanEnsemblType, id> {

			@Override
			public TitanEnsemblType elementType() {

				return TitanEnsemblType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.ensemblIdKey;
			}
		}
		public geneId geneId = new geneId();
		public final class geneId
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanEnsembl, TitanEnsemblType, geneId, String>,
				Ensembl.geneId<TitanEnsembl, TitanEnsemblType, geneId> {

			@Override
			public TitanEnsemblType elementType() {

				return TitanEnsemblType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.ensemblGeneIdKey;
			}
		}
		public proteinSequenceId proteinSequenceId = new proteinSequenceId();
		public final class proteinSequenceId
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanEnsembl, TitanEnsemblType, proteinSequenceId, String>,
				Ensembl.proteinSequenceId<TitanEnsembl, TitanEnsemblType, proteinSequenceId> {

			@Override
			public TitanEnsemblType elementType() {

				return TitanEnsemblType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.ensemblProteinSequenceIdKey;
			}
		}

		public moleculeId moleculeId = new moleculeId();
		public final class moleculeId
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanEnsembl, TitanEnsemblType, moleculeId, String>,
				Ensembl.moleculeId<TitanEnsembl, TitanEnsemblType, moleculeId> {

			@Override
			public TitanEnsemblType elementType() {

				return TitanEnsemblType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.ensemblMoleculeIdKey;
			}
		}

	}
}


package com.bio4j.titan.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinEnsembl;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.nodes.TitanEnsembl;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 7/8/2014.
 */
public class TitanProteinEnsembl extends
		TitanRelationship<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanProteinEnsembl, TitanProteinEnsembl.TitanProteinEnsemblType,
				TitanEnsembl, TitanEnsembl.TitanEnsemblType
				>
		implements
		ProteinEnsembl<
						TitanProtein, TitanProtein.TitanProteinType,
						TitanProteinEnsembl, TitanProteinEnsembl.TitanProteinEnsemblType,
						TitanEnsembl, TitanEnsembl.TitanEnsemblType
						> {


	TitanProteinEnsembl(TitanEdge edge, TitanUniprotGraph uniprotGraph) {

		super(edge);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanProteinEnsemblType type() {

		return uniprotGraph.proteinEnsemblT;
	}

	public static final class TitanProteinEnsemblType
			implements
			TitanRelationship.Type<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinEnsembl, TitanProteinEnsemblType,
					TitanEnsembl, TitanEnsembl.TitanEnsemblType
					>,
			UniprotGraph.ProteinEnsemblType<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinEnsembl, TitanProteinEnsemblType,
					TitanEnsembl, TitanEnsembl.TitanEnsemblType
					> {

		TitanUniprotGraph uniprotGraph;

		public TitanProteinEnsemblType(TitanUniprotGraph uniprotGraph) {

			this.uniprotGraph = uniprotGraph;
		}

		@Override
		public TitanLabel label() {
			return uniprotGraph.proteinEnsemblLabel;
		}

		@Override
		public TitanProteinEnsemblType value() {
			return uniprotGraph.proteinEnsemblT;
		}

		@Override
		public TitanProtein.TitanProteinType sourceType() {
			return uniprotGraph.proteinT;
		}

		@Override
		public TitanEnsembl.TitanEnsemblType targetType() {
			return uniprotGraph.ensemblT;
		}

		@Override
		public TitanProteinEnsembl fromTitanEdge(TitanEdge edge) {
			return new TitanProteinEnsembl(edge, uniprotGraph);
		}
	}
}


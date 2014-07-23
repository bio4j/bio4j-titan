package com.bio4j.titan.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinEMBL;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.nodes.TitanEMBL;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 7/4/2014.
 */
public class TitanProteinEMBL extends
		TitanRelationship<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanProteinEMBL, TitanProteinEMBL.TitanProteinEMBLType,
				TitanEMBL, TitanEMBL.TitanEMBLType
				>
		implements
		ProteinEMBL<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanProteinEMBL, TitanProteinEMBL.TitanProteinEMBLType,
				TitanEMBL, TitanEMBL.TitanEMBLType
				> {


	TitanProteinEMBL(TitanEdge edge, TitanUniprotGraph uniprotGraph) {

		super(edge);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanProteinEMBLType type() {

		return uniprotGraph.proteinEMBLT;
	}

	public static final class TitanProteinEMBLType
			implements
			TitanRelationship.Type<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinEMBL, TitanProteinEMBLType,
					TitanEMBL, TitanEMBL.TitanEMBLType
					>,
			UniprotGraph.ProteinEMBLType<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinEMBL, TitanProteinEMBLType,
					TitanEMBL, TitanEMBL.TitanEMBLType
					> {

		TitanUniprotGraph uniprotGraph;

		public TitanProteinEMBLType(TitanUniprotGraph uniprotGraph) {

			this.uniprotGraph = uniprotGraph;
		}

		@Override
		public TitanLabel label() {
			return uniprotGraph.proteinEMBLLabel;
		}

		@Override
		public TitanProteinEMBLType value() {
			return uniprotGraph.proteinEMBLT;
		}

		@Override
		public TitanProtein.TitanProteinType sourceType() {
			return uniprotGraph.proteinT;
		}

		@Override
		public TitanEMBL.TitanEMBLType targetType() {
			return uniprotGraph.eMBLT;
		}

		@Override
		public TitanProteinEMBL fromTitanEdge(TitanEdge edge) {
			return new TitanProteinEMBL(edge, uniprotGraph);
		}
	}
}


package com.bio4j.titan.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinPIR;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.nodes.TitanPIR;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 7/7/2014.
 */
public class TitanProteinPIR extends
		TitanRelationship<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanProteinPIR, TitanProteinPIR.TitanProteinPIRType,
				TitanPIR, TitanPIR.TitanPIRType
				>
		implements
		ProteinPIR<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanProteinPIR, TitanProteinPIR.TitanProteinPIRType,
				TitanPIR, TitanPIR.TitanPIRType
				> {


	TitanProteinPIR(TitanEdge edge, TitanUniprotGraph uniprotGraph) {

		super(edge);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanProteinPIRType type() {

		return uniprotGraph.proteinPIRT;
	}

	public static final class TitanProteinPIRType
			implements
			TitanRelationship.Type<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinPIR, TitanProteinPIR.TitanProteinPIRType,
					TitanPIR, TitanPIR.TitanPIRType
					>,
			UniprotGraph.ProteinPIRType <
								TitanProtein, TitanProtein.TitanProteinType,
								TitanProteinPIR, TitanProteinPIRType,
								TitanPIR, TitanPIR.TitanPIRType
								> {

		TitanUniprotGraph uniprotGraph;

		public TitanProteinPIRType(TitanUniprotGraph uniprotGraph) {

			this.uniprotGraph = uniprotGraph;
		}

		@Override
		public TitanLabel label() {
			return uniprotGraph.proteinPIRLabel;
		}

		@Override
		public TitanProteinPIRType value() {
			return uniprotGraph.proteinPIRT;
		}

		@Override
		public TitanProtein.TitanProteinType sourceType() {
			return uniprotGraph.proteinT;
		}

		@Override
		public TitanPIR.TitanPIRType targetType() {
			return uniprotGraph.pIRT;
		}

		@Override
		public TitanProteinPIR fromTitanEdge(TitanEdge edge) {
			return new TitanProteinPIR(edge, uniprotGraph);
		}
	}
}


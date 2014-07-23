package com.bio4j.titan.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinReactomeTerm;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.bio4j.titan.model.uniprot.nodes.TitanReactomeTerm;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 7/1/2014.
 */
public class TitanProteinReactomeTerm extends
		TitanRelationship<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanProteinReactomeTerm, TitanProteinReactomeTerm.TitanProteinReactomeTermType,
				TitanReactomeTerm, TitanReactomeTerm.TitanReactomeTermType
				>
		implements
		ProteinReactomeTerm<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanProteinReactomeTerm, TitanProteinReactomeTerm.TitanProteinReactomeTermType,
				TitanReactomeTerm, TitanReactomeTerm.TitanReactomeTermType
				> {


	TitanProteinReactomeTerm(TitanEdge edge, TitanUniprotGraph uniprotGraph) {

		super(edge);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanProteinReactomeTermType type() {

		return uniprotGraph.proteinReactomeTermT;
	}

	public static final class TitanProteinReactomeTermType
			implements
			TitanRelationship.Type<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinReactomeTerm, TitanProteinReactomeTerm.TitanProteinReactomeTermType,
					TitanReactomeTerm, TitanReactomeTerm.TitanReactomeTermType
					>,
			UniprotGraph.ProteinReactomeTermType<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinReactomeTerm, TitanProteinReactomeTerm.TitanProteinReactomeTermType,
					TitanReactomeTerm, TitanReactomeTerm.TitanReactomeTermType
					> {

		TitanUniprotGraph uniprotGraph;

		public TitanProteinReactomeTermType(TitanUniprotGraph uniprotGraph) {

			this.uniprotGraph = uniprotGraph;
		}

		@Override
		public TitanLabel label() {
			return uniprotGraph.proteinReactomeTermLabel;
		}

		@Override
		public TitanProteinReactomeTermType value() {
			return uniprotGraph.proteinReactomeTermT;
		}

		@Override
		public TitanProtein.TitanProteinType sourceType() {
			return uniprotGraph.proteinT;
		}

		@Override
		public TitanReactomeTerm.TitanReactomeTermType targetType() {
			return uniprotGraph.reactomeTermT;
		}

		@Override
		public TitanProteinReactomeTerm fromTitanEdge(TitanEdge edge) {
			return new TitanProteinReactomeTerm(edge, uniprotGraph);
		}
	}
}


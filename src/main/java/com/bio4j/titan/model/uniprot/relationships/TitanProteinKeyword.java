package com.bio4j.titan.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph.ProteinKeywordType;
import com.bio4j.model.uniprot.relationships.ProteinKeyword;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.nodes.TitanKeyword;
import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
import com.ohnosequences.typedGraphs.titan.TitanRelationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 6/30/2014.
 */
public class TitanProteinKeyword extends
		TitanRelationship<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanProteinKeyword, TitanProteinKeyword.TitanProteinKeywordType,
				TitanKeyword, TitanKeyword.TitanKeywordType
				>
		implements
		ProteinKeyword<
				TitanProtein, TitanProtein.TitanProteinType,
				TitanProteinKeyword, TitanProteinKeyword.TitanProteinKeywordType,
				TitanKeyword, TitanKeyword.TitanKeywordType
				> {


	TitanProteinKeyword(TitanEdge edge, TitanUniprotGraph uniprotGraph) {

		super(edge);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	/*
	  Note here how we need a reference to the enclosing graph, which contains the term type value.
	*/
	@Override
	public TitanProteinKeywordType type() {

		return uniprotGraph.proteinKeywordT;
	}

	public static final class TitanProteinKeywordType
			implements
			TitanRelationship.Type<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinKeyword, TitanProteinKeyword.TitanProteinKeywordType,
					TitanKeyword, TitanKeyword.TitanKeywordType
					>,
			ProteinKeywordType<
					TitanProtein, TitanProtein.TitanProteinType,
					TitanProteinKeyword, TitanProteinKeywordType,
					TitanKeyword, TitanKeyword.TitanKeywordType
					> {

		TitanUniprotGraph uniprotGraph;

		public TitanProteinKeywordType(TitanUniprotGraph uniprotGraph) {

			this.uniprotGraph = uniprotGraph;
		}

		@Override
		public TitanLabel label() {
			return uniprotGraph.proteinKeywordLabel;
		}

		@Override
		public TitanProteinKeywordType value() {
			return uniprotGraph.proteinKeywordT;
		}

		@Override
		public TitanProtein.TitanProteinType sourceType() {
			return uniprotGraph.proteinT;
		}

		@Override
		public TitanKeyword.TitanKeywordType targetType() {
			return uniprotGraph.keywordT;
		}

		@Override
		public TitanProteinKeyword fromTitanEdge(TitanEdge edge) {
			return new TitanProteinKeyword(edge, uniprotGraph);
		}
	}
}


package com.bio4j.titan.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Keyword;
import com.bio4j.model.uniprot.nodes.Organism;
import com.bio4j.model.uniprot.relationships.ProteinKeyword;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinKeyword;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;

import java.util.List;

/**
 * Created by ppareja on 6/30/2014.
 */
public class TitanKeyword extends
		TitanNode<TitanKeyword, TitanKeyword.TitanKeywordType>
		implements Keyword<TitanKeyword, TitanKeyword.TitanKeywordType> {

	@Override
	public String name(){ return get(uniprotGraph.keywordT.name);}
	@Override
	public String id(){ return get(uniprotGraph.keywordT.id);}

	@Override
	public List<TitanProteinKeyword> proteinKeyword_in() {
		return inFromMany(uniprotGraph.proteinKeywordT);
	}

	@Override
	public List<TitanProtein> proteinKeyword_inNodes() {
		return inFromManyNodes(uniprotGraph.proteinKeywordT);
	}

	public TitanKeyword(TitanVertex vertex, TitanUniprotGraph uniprotGraph) {

		super(vertex);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	@Override
	public TitanKeywordType type() {

		return uniprotGraph.keywordT;
	}

	public static final class TitanKeywordType
			implements
			TitanNode.Type<TitanKeyword, TitanKeyword.TitanKeywordType>,
			UniprotGraph.KeywordType<TitanKeyword, TitanKeywordType> {

		public TitanKeywordType(TitanUniprotGraph uniprotGraph) {
			this.uniprotGraph = uniprotGraph;
		}

		TitanUniprotGraph uniprotGraph;

		@Override
		public TitanKey titanKey() {

			return uniprotGraph.keywordTKey;
		}

		@Override
		public TitanKeywordType value() {

			return uniprotGraph.keywordT;
		}

		@Override
		public TitanKeyword fromTitanVertex(TitanVertex vertex) {

			return new TitanKeyword(vertex, uniprotGraph);
		}

		// --------------------------------properties----------------------------------------
		public name name = new name();

		public final class name
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanKeyword, TitanKeywordType, name, String>,
				Keyword.name<TitanKeyword, TitanKeywordType, name> {

			@Override
			public TitanKeywordType elementType() {

				return TitanKeywordType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.keywordNameKey;
			}
		}

		public id id = new id();

		public final class id
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanKeyword, TitanKeywordType, id, String>,
				Keyword.name<TitanKeyword, TitanKeywordType, id> {

			@Override
			public TitanKeywordType elementType() {

				return TitanKeywordType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.keywordIdKey;
			}
		}
	}
}

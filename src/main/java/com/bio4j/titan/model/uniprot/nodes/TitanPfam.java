package com.bio4j.titan.model.uniprot.nodes;

import com.bio4j.model.uniprot.nodes.Pfam;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.ohnosequences.typedGraphs.titan.TitanNode;

/**
 * Created by ppareja on 7/1/2014.
 */
public class TitanPfam  extends
		TitanNode<TitanPfam, TitanPfam.TitanPfamType>
		implements Pfam<TitanPfam, TitanPfam.TitanPfamType> {

	@Override
	public String name(){ return get(uniprotGraph.pfamT.name);}
	@Override
	public String id(){ return get(uniprotGraph.pfamT.id);}

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

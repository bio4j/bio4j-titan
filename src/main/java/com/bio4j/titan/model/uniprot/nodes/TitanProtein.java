package com.bio4j.titan.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph.ProteinType;
import com.bio4j.model.uniprot.nodes.*;
import com.bio4j.model.uniprot.relationships.*;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.relationships.*;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;

import java.util.List;

/**
 * Created by ppareja on 6/26/2014.
 */
public class TitanProtein extends
		TitanNode<TitanProtein, TitanProtein.TitanProteinType>
		implements Protein<TitanProtein, TitanProtein.TitanProteinType> {

	@Override
	public String accession(){ return get(uniprotGraph.proteinT.accession);}
	@Override
	public String name(){ return get(uniprotGraph.proteinT.name);}
	@Override
	public String shortName(){ return get(uniprotGraph.proteinT.shortName);}
	@Override
	public String fullName(){ return get(uniprotGraph.proteinT.fullName);}
	@Override
	public String modifiedDate(){ return get(uniprotGraph.proteinT.modifiedDate);}
	@Override
	public String sequence(){ return get(uniprotGraph.proteinT.sequence);}
	@Override
	public String mass(){ return get(uniprotGraph.proteinT.mass);}
	@Override
	public int length(){ return get(uniprotGraph.proteinT.length);}

	@Override
	public List<TitanProteinInterpro> proteinIntepro_out() {
		return outToMany(uniprotGraph.proteinInterproT);
	}

	@Override
	public List<TitanInterpro> proteinInterpro_outNodes() {
		return outToManyNodes(uniprotGraph.proteinInterproT);
	}

	@Override
	public List<TitanProteinReactomeTerm> proteinReactomeTerm_out() {
		return outToMany(uniprotGraph.proteinReactomeTermT);
	}

	@Override
	public List<TitanReactomeTerm> proteinReactomeTerm_outNodes() {
		return outToManyNodes(uniprotGraph.proteinReactomeTermT);
	}

	@Override
	public List<TitanProteinKeyword> proteinKeyword_out() {
		return outToMany(uniprotGraph.proteinKeywordT);
	}

	@Override
	public List<TitanKeyword> proteinKeyword_outNodes() {
		return outToManyNodes(uniprotGraph.proteinKeywordT);
	}

	@Override
	public TitanProteinOrganism proteinOrganism_out() {
		return outToOne(uniprotGraph.proteinOrganismT);
	}

	@Override
	public TitanOrganism proteinOrganism_outNodes() {
		return outToOneNode(uniprotGraph.proteinOrganismT);
	}

	@Override
	public TitanProteinDataset proteinDataset_out() {
		return outToOne(uniprotGraph.proteinDatasetT);
	}

	@Override
	public TitanDataset proteinDataset_outNodes() {
		return outToOneNode(uniprotGraph.proteinDatasetT);
	}

	public TitanProtein(TitanVertex vertex, TitanUniprotGraph uniprotGraph) {

		super(vertex);
		this.uniprotGraph = uniprotGraph;
	}

	TitanUniprotGraph uniprotGraph;

	@Override
	public TitanProteinType type() {

		return uniprotGraph.proteinT;
	}

	public static final class TitanProteinType
			implements
			TitanNode.Type<TitanProtein, TitanProtein.TitanProteinType>,
			ProteinType<TitanProtein, TitanProteinType> {

		public TitanProteinType(TitanUniprotGraph uniprotGraph) {
			this.uniprotGraph = uniprotGraph;
		}

		TitanUniprotGraph uniprotGraph;

		@Override
		public TitanKey titanKey() {

			return uniprotGraph.proteinTKey;
		}

		@Override
		public TitanProteinType value() {

			return uniprotGraph.proteinT;
		}

		@Override
		public TitanProtein fromTitanVertex(TitanVertex vertex) {

			return new TitanProtein(vertex, uniprotGraph);
		}

		// --------------------------------properties----------------------------------------
		public accession accession = new accession();
		public final class accession
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanProtein, TitanProteinType, accession, String>,
				Protein.accession<TitanProtein, TitanProteinType, accession> {

			@Override
			public TitanProteinType elementType() {

				return TitanProteinType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.proteinAccessionKey;
			}
		}

		public name name = new name();
		public final class name
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanProtein, TitanProteinType, name, String>,
				Protein.name<TitanProtein, TitanProteinType, name> {

			@Override
			public TitanProteinType elementType() {

				return TitanProteinType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.proteinNameKey;
			}
		}

		public shortName shortName = new shortName();
		public final class shortName
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanProtein, TitanProteinType, shortName, String>,
				Protein.shortName<TitanProtein, TitanProteinType, shortName> {

			@Override
			public TitanProteinType elementType() {

				return TitanProteinType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.proteinShortNameKey;
			}
		}

		public fullName fullName = new fullName();
		public final class fullName
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanProtein, TitanProteinType, fullName, String>,
				Protein.fullName<TitanProtein, TitanProteinType, fullName> {

			@Override
			public TitanProteinType elementType() {

				return TitanProteinType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.proteinFullNameKey;
			}
		}

		public sequence sequence = new sequence();
		public final class sequence
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanProtein, TitanProteinType, sequence, String>,
				Protein.sequence<TitanProtein, TitanProteinType, sequence> {

			@Override
			public TitanProteinType elementType() {

				return TitanProteinType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.proteinSequenceKey;
			}
		}

		public modifiedDate modifiedDate = new modifiedDate();
		public final class modifiedDate
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanProtein, TitanProteinType, modifiedDate, String>,
				Protein.modifiedDate<TitanProtein, TitanProteinType, modifiedDate> {

			@Override
			public TitanProteinType elementType() {

				return TitanProteinType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.proteinModifiedDateKey;
			}
		}

		public mass mass = new mass();
		public final class mass
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanProtein, TitanProteinType, mass, String>,
				Protein.mass<TitanProtein, TitanProteinType, mass> {

			@Override
			public TitanProteinType elementType() {

				return TitanProteinType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.proteinMassKey;
			}
		}

		public length length = new length();
		public final class length
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanProtein, TitanProteinType, length, Integer>,
				Protein.length<TitanProtein, TitanProteinType, length> {

			@Override
			public TitanProteinType elementType() {

				return TitanProteinType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.proteinLengthKey;
			}
		}

	}

}

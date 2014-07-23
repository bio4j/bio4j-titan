package com.bio4j.titan.model.uniprot.nodes;

import com.bio4j.model.enzymedb.nodes.Enzyme;
import com.bio4j.model.uniprot.UniprotGraph.ProteinType;
import com.bio4j.model.uniprot.nodes.*;
import com.bio4j.model.uniprot.relationships.*;
import com.bio4j.model.uniprot_enzymedb.relationships.EnzymaticActivity;
import com.bio4j.model.uniprot_uniref.relationships.*;
import com.bio4j.model.uniref.nodes.UniRef100Cluster;
import com.bio4j.model.uniref.nodes.UniRef50Cluster;
import com.bio4j.model.uniref.nodes.UniRef90Cluster;
import com.bio4j.titan.model.enzyme.nodes.TitanEnzyme;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.relationships.*;
import com.bio4j.titan.model.uniprot_enzyme.relationships.TitanEnzymaticActivity;
import com.bio4j.titan.model.uniprot_uniref.relationships.*;
import com.bio4j.titan.model.uniref.nodes.TitanUniRef100Cluster;
import com.bio4j.titan.model.uniref.nodes.TitanUniRef50Cluster;
import com.bio4j.titan.model.uniref.nodes.TitanUniRef90Cluster;
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
	public String createdDate(){ return get(uniprotGraph.proteinT.createdDate);}
	@Override
	public String sequence(){ return get(uniprotGraph.proteinT.sequence);}
	@Override
	public String mass(){ return get(uniprotGraph.proteinT.mass);}
	@Override
	public int length(){ return get(uniprotGraph.proteinT.length);}
	@Override
	public int version(){ return get(uniprotGraph.proteinT.version);}

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
	public List<TitanEnzymaticActivity> enzymaticActivity_out() {   return outToMany(uniprotGraph.uniprotEnzymeDBGraph.enzymaticActivityT);	}

	@Override
	public List<TitanEnzyme> enzymaticActivity_outNodes() { 	return outToManyNodes(uniprotGraph.uniprotEnzymeDBGraph.enzymaticActivityT);
	}

	@Override
	public TitanUniRef50Representant uniref50Representant_in() {
		return inFromOne(uniprotGraph.uniprotUniRefGraph.uniRef50RepresentantT);
	}

	@Override
	public TitanUniRef50Cluster uniref50Representant_inNode() {
		return inFromOneNode(uniprotGraph.uniprotUniRefGraph.uniRef50RepresentantT);
	}

	@Override
	public TitanUniRef90Representant uniref90Representant_in() {
		return inFromOne(uniprotGraph.uniprotUniRefGraph.uniRef90RepresentantT);
	}

	@Override
	public TitanUniRef90Cluster uniref90Representant_inNode() {
		return inFromOneNode(uniprotGraph.uniprotUniRefGraph.uniRef90RepresentantT);
	}

	@Override
	public TitanUniRef100Member uniref100Member_in() {
		return inFromOne(uniprotGraph.uniprotUniRefGraph.uniRef100MemberT);
	}

	@Override
	public TitanUniRef100Cluster uniref100Member_inNode() {
		return inFromOneNode(uniprotGraph.uniprotUniRefGraph.uniRef100MemberT);
	}

	@Override
	public TitanUniRef50Member uniref50Member_in() {
		return inFromOne(uniprotGraph.uniprotUniRefGraph.uniRef50MemberT);
	}

	@Override
	public TitanUniRef50Cluster uniref50Member_inNode() {
		return inFromOneNode(uniprotGraph.uniprotUniRefGraph.uniRef50MemberT);
	}

	@Override
	public TitanUniRef90Member uniref90Member_in() {
		return inFromOne(uniprotGraph.uniprotUniRefGraph.uniRef90MemberT);
	}

	@Override
	public TitanUniRef90Cluster uniref90Member_inNode() {
		return inFromOneNode(uniprotGraph.uniprotUniRefGraph.uniRef90MemberT);
	}

	@Override
	public TitanUniRef100Representant uniref100Representant_in() {
		return inFromOne(uniprotGraph.uniprotUniRefGraph.uniRef100RepresentantT);
	}

	@Override
	public TitanUniRef100Cluster uniref100Representant_inNode() {
		return inFromOneNode(uniprotGraph.uniprotUniRefGraph.uniRef100RepresentantT);
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

		public createdDate createdDate = new createdDate();
		public final class createdDate
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanProtein, TitanProteinType, createdDate, String>,
				Protein.createdDate<TitanProtein, TitanProteinType, createdDate> {

			@Override
			public TitanProteinType elementType() {

				return TitanProteinType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.proteinCreatedDateKey;
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

		public version version = new version();
		public final class version
				implements
				com.ohnosequences.typedGraphs.titan.TitanProperty<TitanProtein, TitanProteinType, version, Integer>,
				Protein.version<TitanProtein, TitanProteinType, version> {

			@Override
			public TitanProteinType elementType() {

				return TitanProteinType.this;
			}

			@Override
			public TitanKey titanKey() {

				return uniprotGraph.proteinVersionKey;
			}
		}

	}

}

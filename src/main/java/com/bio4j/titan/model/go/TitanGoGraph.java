package com.bio4j.titan.model.go;

import com.bio4j.model.go.GoGraph;
import com.bio4j.titan.model.go.nodes.TitanGoTerm;
import com.bio4j.titan.model.go.nodes.TitanGoTerm.TitanGoTermType;
import com.bio4j.titan.model.go.nodes.TitanSubOntologies;
import com.bio4j.titan.model.go.nodes.TitanSubOntologies.TitanSubOntologiesType;
import com.bio4j.titan.model.go.relationships.TitanHasPartOf.TitanHasPartOfType;
import com.bio4j.titan.model.go.relationships.TitanIsA.TitanIsAType;
import com.bio4j.titan.model.go.relationships.TitanNegativelyRegulates.TitanNegativelyRegulatesType;
import com.bio4j.titan.model.go.relationships.TitanPartOf.TitanPartOfType;
import com.bio4j.titan.model.go.relationships.TitanPositivelyRegulates.TitanPositivelyRegulatesType;
import com.bio4j.titan.model.go.relationships.TitanRegulates.TitanRegulatesType;
import com.bio4j.titan.model.go.relationships.TitanSubOntology.TitanSubOntologyType;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.ohnosequences.typedGraphs.titan.TitanNodeIndex;
import com.ohnosequences.typedGraphs.titan.TitanTypedGraph;
import com.thinkaurelius.titan.core.*;


/*
  Implementing the types with Titan
*/
public class TitanGoGraph
		extends
		GoGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> {

	protected TitanGraph rawGraph;

	TitanGoGraph(TitanGraph rawGraph) {

		this.rawGraph = rawGraph;
	}

	@Override
	public TitanGraph rawGraph() {
		return rawGraph;
	}

	/*
	  The type of a TitanGoTerm. This an inner class of the graph. The first key here represents the type of the node, while the rest are for properties of this term: `id` and `name` in this case.
	*/
	public TitanKey goTermTkey;
	public TitanKey goTermIdKey;
	public TitanKey goTermNameKey;
	public TitanKey goTermDefinitionKey;
	public TitanKey goTermObsoleteKey;
	public TitanKey goTermCommentKey;
	public TitanKey goTermSynonymKey;
	public final TitanGoTermType goTermT = new TitanGoTermType(this);

	public TitanKey subOntologiesTKey;
	public TitanKey subOntologiesNameKey;
	public final TitanSubOntologiesType subOntologiesT = new TitanSubOntologiesType(this);

	//------------------INDICES----------------
	//-----------------------------------------
	public TitanNodeIndex.Unique<TitanGoTerm,TitanGoTermType, TitanGoTermType.id,String> goTermIdIndex;
	public TitanNodeIndex.Unique<TitanSubOntologies,TitanSubOntologiesType, TitanSubOntologiesType.name,String> subOntologiesNameIndex;


	//-----------------------------------------------------------------------------------------
	//--------------------------------RELATIONSHIPS--------------------------------------------

	public TitanLabel isA;
	public TitanIsAType isAT = new TitanIsAType(this);
	public TitanLabel partOfLabel;
	public TitanPartOfType partOfT = new TitanPartOfType(this);
	public TitanLabel hasPartOfLabel;
	public TitanHasPartOfType hasPartOfT = new TitanHasPartOfType(this);
	public TitanLabel regulatesLabel;
	public TitanRegulatesType regulatesT = new TitanRegulatesType(this);
	public TitanLabel positivelyRegulatesLabel;
	public TitanPositivelyRegulatesType positivelyRegulatesT = new TitanPositivelyRegulatesType(this);
	public TitanLabel negativelyRegulatesLabel;
	public TitanNegativelyRegulatesType negativelyRegulatesT = new TitanNegativelyRegulatesType(this);
	public TitanLabel subOntologyLabel;
	public TitanSubOntologyType subOntologyT = new TitanSubOntologyType(this);


}
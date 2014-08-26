package com.bio4j.titan.model.go;

import com.bio4j.model.go.GoGraph;
import com.bio4j.model.go.nodes.GoTerm;
import com.bio4j.model.go.nodes.SubOntologies;
import com.bio4j.model.uniprot_go.UniprotGoGraph;
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
import com.ohnosequences.typedGraphs.TypedVertexIndex;
import com.ohnosequences.typedGraphs.titan.TitanNodeIndex;
import com.ohnosequences.typedGraphs.titan.TitanTypedGraph;
import com.thinkaurelius.titan.core.*;


/*
  Implementing the types with Titan
*/
public final class TitanGoGraph
		extends
		GoGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> {

	private DefaultTitanGraph rawGraph;

    private TitanLabel isALabel;
    private IsAType isAType;
    private TitanLabel partOfLabel;
    private PartOfType partOfType;
    private TitanLabel hasPartOfLabel;
    private HasPartOfType hasPartOfType;

	TitanGoGraph(DefaultTitanGraph rawGraph) {
        super(rawGraph);
		this.rawGraph = rawGraph;
        initTypes();
        initIndices();
	}

	@Override
	public DefaultTitanGraph raw() {
		return rawGraph;
	}

    private void initTypes(){

        //-----------------------------------------------------------------------------------------
        //--------------------------------RELATIONSHIPS--------------------------------------------

        isALabel = raw().titanLabelForEdgeType(this.new IsAType(null));
        isAType = new IsAType(isALabel);
        partOfLabel = raw().titanLabelForEdgeType(this.new PartOfType(null));
        partOfType = new PartOfType(partOfLabel);
        hasPartOfLabel = raw().titanLabelForEdgeType(this.new HasPartOfType(null));
        hasPartOfType = new HasPartOfType(hasPartOfLabel);


    }
    private void initIndices(){

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




	public TitanLabel regulatesLabel;
	public TitanRegulatesType regulatesT = new TitanRegulatesType(this);
	public TitanLabel positivelyRegulatesLabel;
	public TitanPositivelyRegulatesType positivelyRegulatesT = new TitanPositivelyRegulatesType(this);
	public TitanLabel negativelyRegulatesLabel;
	public TitanNegativelyRegulatesType negativelyRegulatesT = new TitanNegativelyRegulatesType(this);
	public TitanLabel subOntologyLabel;
	public TitanSubOntologyType subOntologyT = new TitanSubOntologyType(this);

    @Override public IsAType IsA() {
        return isAType;
    }

    @Override
    public UniprotGoGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> uniprotGoGraph() {
        return null;
    }

    @Override
    public TypedVertexIndex.Unique<GoTerm<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, GoTermType, GoTermType.id, String, GoGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> goTermIdIndex() {
        return null;
    }

    @Override
    public TypedVertexIndex.Unique<SubOntologies<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, SubOntologiesType, SubOntologiesType.name, String, GoGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> subontologiesNameIndex() {
        return null;
    }

    @Override
    public GoSlimsType GoSlims() {
        return null;
    }

    @Override
    public GoTermType GoTerm() {
        return null;
    }

    @Override
    public SubOntologiesType SubOntologies() {
        return null;
    }

    @Override
    public PartOfType PartOf() {
        return null;
    }

    @Override
    public HasPartOfType HasPartOf() {
        return null;
    }

    @Override
    public NegativelyRegulatesType NegativelyRegulates() {
        return null;
    }

    @Override
    public PositivelyRegulatesType PositivelyRegulates() {
        return null;
    }

    @Override
    public RegulatesType Regulates() {
        return null;
    }


    @Override
    public SubOntologyType SubOntology() {
        return null;
    }
}
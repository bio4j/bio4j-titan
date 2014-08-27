package com.bio4j.titan.model.go;

import com.bio4j.model.go.GoGraph;
import com.bio4j.model.go.nodes.GoTerm;
import com.bio4j.model.go.nodes.SubOntologies;
import com.bio4j.model.uniprot_go.UniprotGoGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.ohnosequences.typedGraphs.TypedVertexIndex;
import com.thinkaurelius.titan.core.*;


/*
  Implementing the types with Titan
*/
public final class TitanGoGraph
		extends
		GoGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> {

	private DefaultTitanGraph rawGraph;

    public TitanKey goTermTypekey;
    public TitanKey goTermIdKey;
    public TitanKey goTermNameKey;
    public TitanKey goTermDefinitionKey;
    public TitanKey goTermObsoleteKey;
    public TitanKey goTermCommentKey;
    public TitanKey goTermSynonymKey;
    public GoTermType goTermType;

    private TitanLabel isALabel;
    private IsAType isAType;
    private TitanLabel partOfLabel;
    private PartOfType partOfType;
    private TitanLabel hasPartOfLabel;
    private HasPartOfType hasPartOfType;
    private TitanLabel regulatesLabel;
    private RegulatesType regulatesType;
    private TitanLabel positivelyRegulatesLabel;
    private PositivelyRegulatesType positivelyRegulatesType;
    private TitanLabel negativelyRegulatesLabel;
    private NegativelyRegulatesType negativelyRegulatesType;
    private TitanLabel subOntologyLabel;
    private SubOntologyType subOntologyType;

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
        //--------------------------------VERTICES--------------------------------------------
        goTermTypekey = titanKeyForVertexType();
        goTermIdKey = goTermTypekey;
        goTermNameKey = titanKeyForNodeProperty(goTermT.name);
        goTermDefinitionKey = titanKeyForNodeProperty(goTermT.definition);
        goTermObsoleteKey = titanKeyForNodeProperty(goTermT.obsolete);
        goTermCommentKey = titanKeyForNodeProperty(goTermT.comment);
        goTermType = new GoTermType()

        //-----------------------------------------------------------------------------------------
        //--------------------------------RELATIONSHIPS--------------------------------------------

        isALabel = raw().titanLabelForEdgeType(this.new IsAType(null));
        isAType = new IsAType(isALabel);
        partOfLabel = raw().titanLabelForEdgeType(this.new PartOfType(null));
        partOfType = new PartOfType(partOfLabel);
        hasPartOfLabel = raw().titanLabelForEdgeType(this.new HasPartOfType(null));
        hasPartOfType = new HasPartOfType(hasPartOfLabel);
        regulatesLabel = raw().titanLabelForEdgeType(this.new RegulatesType(null));
        regulatesType = new RegulatesType(regulatesLabel);
        positivelyRegulatesLabel = raw().titanLabelForEdgeType(this.new PositivelyRegulatesType(null));
        positivelyRegulatesType = new PositivelyRegulatesType(positivelyRegulatesLabel);
        negativelyRegulatesLabel = raw().titanLabelForEdgeType(this.new NegativelyRegulatesType(null));
        negativelyRegulatesType = new NegativelyRegulatesType(negativelyRegulatesLabel);
        subOntologyLabel = raw().titanLabelForEdgeType(this.new SubOntologyType(null));
        subOntologyType = new SubOntologyType(subOntologyLabel);


    }
    private void initIndices(){

    }

	/*
	  The type of a TitanGoTerm. This an inner class of the graph. The first key here represents the type of the node, while the rest are for properties of this term: `id` and `name` in this case.
	*/


//	public TitanKey subOntologiesTKey;
//	public TitanKey subOntologiesNameKey;
//	public final TitanSubOntologiesType subOntologiesT = new TitanSubOntologiesType(this);

	//------------------INDICES----------------
	//-----------------------------------------
//	public TitanNodeIndex.Unique<TitanGoTerm,TitanGoTermType, TitanGoTermType.id,String> goTermIdIndex;
//	public TitanNodeIndex.Unique<TitanSubOntologies,TitanSubOntologiesType, TitanSubOntologiesType.name,String> subOntologiesNameIndex;
//
//
//	public TitanLabel subOntologyLabel;
//	public TitanSubOntologyType subOntologyT = new TitanSubOntologyType(this);

    @Override
    public IsAType IsA() {
        return isAType;
    }
    @Override
    public GoSlimsType GoSlims() {
        return null;
    }

    @Override
    public GoTermType GoTerm() {
        return goTermType;
    }

    @Override
    public SubOntologiesType SubOntologies() {
        return null;
    }

    @Override
    public PartOfType PartOf() {
        return partOfType;
    }

    @Override
    public HasPartOfType HasPartOf() {
        return hasPartOfType;
    }

    @Override
    public NegativelyRegulatesType NegativelyRegulates() {
        return negativelyRegulatesType;
    }

    @Override
    public PositivelyRegulatesType PositivelyRegulates() {
        return positivelyRegulatesType;
    }

    @Override
    public RegulatesType Regulates() {
        return regulatesType;
    }


    @Override
    public SubOntologyType SubOntology() {
        return subOntologyType;
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


}
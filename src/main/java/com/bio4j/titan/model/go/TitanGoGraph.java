package com.bio4j.titan.model.go;

import com.bio4j.model.go.GoGraph;
import com.bio4j.model.go.nodes.GoTerm;
import com.bio4j.model.go.nodes.SubOntologies;
import com.bio4j.model.uniprot_go.UniprotGoGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.ohnosequences.typedGraphs.TypedVertexIndex;
import com.ohnosequences.typedGraphs.titan.TitanTypedVertexIndex;
import com.thinkaurelius.titan.core.*;


/**
  Implementing the types with Titan
  @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
*/
public final class TitanGoGraph
        extends
        GoGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> {

    private DefaultTitanGraph rawGraph;


    //-------------------VERTICES----------------------------

    public TitanKey goTermTypekey;
    public TitanKey goTermIdKey;
    public TitanKey goTermNameKey;
    public TitanKey goTermDefinitionKey;
    public TitanKey goTermObsoleteKey;
    public TitanKey goTermCommentKey;
    public TitanKey goTermSynonymKey;
    public GoTermType goTermType;

    public TitanKey subOntologiesTypekey;
    public TitanKey subOntologiesNameKey;
    public SubOntologiesType subOntologiesType;

    //---------------RELATIONSHIPS---------------------------

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

    //---------------INDICES---------------------------

    TitanTypedVertexIndex.Unique<
            GoTerm<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>,
            GoTermType,
            GoTermType.id, String,
            GoGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>,
            DefaultTitanGraph
            > goTermIdIndex;
    TitanTypedVertexIndex.DefaultUnique<SubOntologies<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>,
            SubOntologiesType,
            SubOntologiesType.name, String,
            GoGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>,
            DefaultTitanGraph> subOntologiesNameIndex;


    public TitanGoGraph(DefaultTitanGraph rawGraph) {
        super(rawGraph);
        this.rawGraph = rawGraph;
        initTypes();
        initIndices();
    }

    @Override
    public DefaultTitanGraph raw() {
        return rawGraph;
    }

    private void initTypes() {

        //-----------------------------------------------------------------------------------------
        //--------------------------------VERTICES--------------------------------------------
        goTermType = new GoTermType(goTermTypekey);
        goTermTypekey = raw().titanKeyMakerForVertexType(GoTerm().id).single().unique().make();
        goTermIdKey = goTermTypekey;
        goTermNameKey = raw().titanKeyMakerForVertexProperty(GoTerm().name).single().make();
        goTermDefinitionKey = raw().titanKeyMakerForVertexProperty(GoTerm().definition).single().make();
        goTermObsoleteKey = raw().titanKeyMakerForVertexProperty(GoTerm().obsolete).single().make();
        goTermCommentKey = raw().titanKeyMakerForVertexProperty(GoTerm().comment).single().make();
        goTermSynonymKey = raw().titanKeyMakerForVertexProperty(GoTerm().synonym).single().make();


        subOntologiesType = new SubOntologiesType(subOntologiesTypekey);
        subOntologiesTypekey = raw().titanKeyMakerForVertexType(SubOntologies().name).single().unique().make();
        subOntologiesNameKey = subOntologiesTypekey;



        //-----------------------------------------------------------------------------------------
        //--------------------------------RELATIONSHIPS--------------------------------------------

        isAType = new IsAType(isALabel);
        isALabel = raw().titanLabelForEdgeType(new IsAType((TitanLabel) null));
        partOfType = new PartOfType(partOfLabel);
        partOfLabel = raw().titanLabelForEdgeType(this.new PartOfType(null));
        hasPartOfType = new HasPartOfType(hasPartOfLabel);
        hasPartOfLabel = raw().titanLabelForEdgeType(this.new HasPartOfType(null));
        regulatesType = new RegulatesType(regulatesLabel);
        regulatesLabel = raw().titanLabelForEdgeType(this.new RegulatesType(null));
        positivelyRegulatesType = new PositivelyRegulatesType(positivelyRegulatesLabel);
        positivelyRegulatesLabel = raw().titanLabelForEdgeType(this.new PositivelyRegulatesType(null));
        negativelyRegulatesType = new NegativelyRegulatesType(negativelyRegulatesLabel);
        negativelyRegulatesLabel = raw().titanLabelForEdgeType(this.new NegativelyRegulatesType(null));
        subOntologyType = new SubOntologyType(subOntologyLabel);
        subOntologyLabel = raw().titanLabelForEdgeType(this.new SubOntologyType(null));


    }

    private void initIndices() {
        goTermIdIndex =  new TitanTypedVertexIndex.DefaultUnique<>(this, GoTerm().id);
        subOntologiesNameIndex =  new TitanTypedVertexIndex.DefaultUnique<>(this, SubOntologies().name);
    }

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
        return subOntologiesType;
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
        return goTermIdIndex;
    }

    @Override
    public TypedVertexIndex.Unique<SubOntologies<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, SubOntologiesType, SubOntologiesType.name, String, GoGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> subontologiesNameIndex() {
        return subOntologiesNameIndex;
    }


}
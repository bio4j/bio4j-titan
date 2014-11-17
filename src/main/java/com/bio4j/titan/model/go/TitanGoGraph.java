package com.bio4j.titan.model.go;

import com.bio4j.angulillos.TypedVertexIndex;
import com.bio4j.angulillos.titan.TitanTypedVertexIndex;
import com.bio4j.model.go.GoGraph;
import com.bio4j.model.go.vertices.GoTerm;
import com.bio4j.model.go.vertices.SubOntologies;
import com.bio4j.model.uniprot_go.UniprotGoGraph;
import com.bio4j.titan.model.uniprot_go.TitanUniprotGoGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.*;


/**
  Implementing the types with Titan
  @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
*/
public final class TitanGoGraph
        extends
        GoGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> {

    private DefaultTitanGraph rawGraph;
	private TitanUniprotGoGraph uniprotGoGraph = null;

    //-------------------VERTICES----------------------------

	public VertexLabel goTermLabel;
    public PropertyKey goTermTypekey;
    public PropertyKey goTermIdKey;
    public PropertyKey goTermNameKey;
    public PropertyKey goTermDefinitionKey;
    public PropertyKey goTermObsoleteKey;
    public PropertyKey goTermCommentKey;
    public PropertyKey goTermSynonymKey;
    public GoTermType goTermType;

	public VertexLabel subOntologiesLabel;
    public PropertyKey subOntologiesTypekey;
    public PropertyKey subOntologiesNameKey;
    public SubOntologiesType subOntologiesType;

    //---------------RELATIONSHIPS---------------------------

    private EdgeLabel isALabel;
    private IsAType isAType;
    private EdgeLabel partOfLabel;
    private PartOfType partOfType;
    private EdgeLabel hasPartOfLabel;
    private HasPartOfType hasPartOfType;
    private EdgeLabel regulatesLabel;
    private RegulatesType regulatesType;
    private EdgeLabel positivelyRegulatesLabel;
    private PositivelyRegulatesType positivelyRegulatesType;
    private EdgeLabel negativelyRegulatesLabel;
    private NegativelyRegulatesType negativelyRegulatesType;
    private EdgeLabel subOntologyLabel;
    private SubOntologyType subOntologyType;

    //---------------INDICES---------------------------

    TitanTypedVertexIndex.Unique<
            GoTerm<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>,
            GoTermType,
            GoTermType.id, String,
            GoGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>,
            DefaultTitanGraph
            > goTermIdIndex;
    TitanTypedVertexIndex.DefaultUnique<SubOntologies<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>,
            SubOntologiesType,
            SubOntologiesType.name, String,
            GoGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>,
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
	    goTermLabel = raw().createOrGet( raw().titanLabelMakerForVertexType(goTermType) );
        goTermTypekey = raw().titanKeyForVertexType(GoTerm().id);
        goTermIdKey = goTermTypekey;
        goTermNameKey = raw().titanKeyForVertexPropertySingle(GoTerm().name);
        goTermDefinitionKey = raw().titanKeyForVertexPropertySingle(GoTerm().definition);
        goTermObsoleteKey = raw().titanKeyForVertexPropertySingle(GoTerm().obsolete);
        goTermCommentKey = raw().titanKeyForVertexPropertySingle(GoTerm().comment);
        goTermSynonymKey = raw().titanKeyForVertexPropertySingle(GoTerm().synonym);


        subOntologiesType = new SubOntologiesType(subOntologiesTypekey);
        subOntologiesTypekey = raw().titanKeyForVertexType(SubOntologies().name);
        subOntologiesNameKey = subOntologiesTypekey;


        //-----------------------------------------------------------------------------------------
        //--------------------------------RELATIONSHIPS--------------------------------------------


        isALabel = raw().titanLabelForEdgeType(new IsAType((EdgeLabel) null));
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
    public UniprotGoGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> uniprotGoGraph() {
        return uniprotGoGraph;
    }

    @Override
    public TypedVertexIndex.Unique<GoTerm<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, GoTermType, GoTermType.id, String, GoGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> goTermIdIndex() {
        return goTermIdIndex;
    }

    @Override
    public TypedVertexIndex.Unique<SubOntologies<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, SubOntologiesType, SubOntologiesType.name, String, GoGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> subontologiesNameIndex() {
        return subOntologiesNameIndex;
    }

	/*
		You can use this as `goGraph.withUniprot(new TitanUniprotGoGraph(raw, uniprotGraph, goGraph))`
	*/
	public TitanGoGraph withUniprot(TitanUniprotGoGraph uniprotGoGraph) {

		this.uniprotGoGraph = uniprotGoGraph;

		return this;
	}


}
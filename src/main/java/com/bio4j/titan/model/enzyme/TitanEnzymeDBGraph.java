package com.bio4j.titan.model.enzyme;

import com.bio4j.model.enzymedb.EnzymeDBGraph;
import com.bio4j.model.enzymedb.nodes.Enzyme;
import com.bio4j.model.uniprot_enzymedb.UniprotEnzymeDBGraph;
import com.bio4j.titan.model.uniprot_enzyme.TitanUniprotEnzymeGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.ohnosequences.typedGraphs.TypedVertexIndex;
import com.ohnosequences.typedGraphs.titan.TitanTypedVertexIndex;
import com.thinkaurelius.titan.core.*;


/**
 Implementing the types with Titan
 @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class TitanEnzymeDBGraph
        extends
        EnzymeDBGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> {

    private DefaultTitanGraph rawGraph;
	private TitanUniprotEnzymeGraph uniprotEnzymeGraph = null;


    //-------------------VERTICES----------------------------

    public TitanKey enzymeTypekey;
    public TitanKey enzymeIdkey;
    public TitanKey enzymeCofactorskey;
    public TitanKey enzymeOfficialNamekey;
    public TitanKey enzymeAlternateNameskey;
    public TitanKey enzymeCommentkey;
    public TitanKey enzymeCatalyticActivitykey;
    public TitanKey enzymePrositeCrossReferenceskey;
    public EnzymeType enzymeType;

    //---------------INDICES---------------------------

    TitanTypedVertexIndex.DefaultUnique<
            Enzyme<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>,
            EnzymeType,
            EnzymeType.id, String,
            EnzymeDBGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>,
            DefaultTitanGraph
            > enzymeIdIndex;

	@Override
	public TypedVertexIndex.Unique<Enzyme<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, EnzymeType, EnzymeType.id, String, EnzymeDBGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>, DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> enzymeIdIndex() {
		return enzymeIdIndex;
	}

	public TitanEnzymeDBGraph(DefaultTitanGraph rawGraph) {
        super(rawGraph);
        this.rawGraph = rawGraph;
        initTypes();
        initIndices();
    }

    @Override
    public DefaultTitanGraph raw() {
        return rawGraph;
    }

    @Override
    public UniprotEnzymeDBGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> uniprotEnzymeDBGraph() {
        return null;
    }

    @Override
    public EnzymeType Enzyme() {
        return enzymeType;
    }

    private void initTypes() {

        //-----------------------------------------------------------------------------------------
        //--------------------------------VERTICES--------------------------------------------
	    enzymeType = new EnzymeType(enzymeTypekey);
        enzymeTypekey = raw().titanKeyMakerForVertexType(Enzyme().id).single().unique().make();
        enzymeIdkey = enzymeTypekey;
        enzymeCofactorskey = raw().titanKeyMakerForVertexProperty(Enzyme().cofactors).single().make();
        enzymeOfficialNamekey = raw().titanKeyMakerForVertexProperty(Enzyme().officialName).single().make();
        enzymeAlternateNameskey = raw().titanKeyMakerForVertexProperty(Enzyme().alternateNames).single().make();
        enzymeCommentkey = raw().titanKeyMakerForVertexProperty(Enzyme().comment).single().make();
        enzymeCatalyticActivitykey = raw().titanKeyMakerForVertexProperty(Enzyme().catalyticActivity).single().make();
        enzymePrositeCrossReferenceskey = raw().titanKeyMakerForVertexProperty(Enzyme().prositeCrossReferences).single().make();

    }

    private void initIndices() {
        enzymeIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Enzyme().id);
    }

	/*
		You can use this as `enzymeDBGraph.withUniprot(new TitanUniprotEnzymeDBGraph(raw, uniprotGraph, enzymeGraph))`
	*/
	public TitanEnzymeDBGraph withUniprot(TitanUniprotEnzymeGraph uniprotEnzymeGraph) {

		this.uniprotEnzymeGraph = uniprotEnzymeGraph;

		return this;
	}
}


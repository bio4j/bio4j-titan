package com.bio4j.titan.model.enzyme;

import com.bio4j.model.enzymedb.EnzymeDBGraph;
import com.bio4j.model.enzymedb.nodes.Enzyme;
import com.bio4j.model.uniprot_enzymedb.UniprotEnzymeDBGraph;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
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
	private TitanUniprotEnzymeGraph uniprotEnzymeGraph;


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
		uniprotEnzymeGraph = new TitanUniprotEnzymeGraph(rawGraph, new TitanUniprotGraph(rawGraph), this);
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
        enzymeTypekey = raw().titanKeyMakerForVertexType(Enzyme().id).single().unique().make();
        enzymeIdkey = enzymeTypekey;
        enzymeCofactorskey = raw().titanKeyMakerForVertexType(Enzyme().cofactors).single().make();
        enzymeOfficialNamekey = raw().titanKeyMakerForVertexType(Enzyme().officialName).single().make();
        enzymeAlternateNameskey = raw().titanKeyMakerForVertexType(Enzyme().alternateNames).single().make();
        enzymeCommentkey = raw().titanKeyMakerForVertexType(Enzyme().comment).single().make();
        enzymeCatalyticActivitykey = raw().titanKeyMakerForVertexType(Enzyme().catalyticActivity).single().make();
        enzymePrositeCrossReferenceskey = raw().titanKeyMakerForVertexType(Enzyme().prositeCrossReferences).single().make();
        enzymeType = new EnzymeType(enzymeTypekey);


    }

    private void initIndices() {
        enzymeIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(this, Enzyme().id);
    }
}


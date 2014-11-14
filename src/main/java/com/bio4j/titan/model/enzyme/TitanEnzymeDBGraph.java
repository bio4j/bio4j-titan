package com.bio4j.titan.model.enzyme;

import com.bio4j.angulillos.TypedVertexIndex;
import com.bio4j.angulillos.titan.TitanTypedVertexIndex;
import com.bio4j.model.enzymedb.EnzymeDBGraph;
import com.bio4j.model.enzymedb.vertices.Enzyme;
import com.bio4j.model.uniprot_enzymedb.UniprotEnzymeDBGraph;
import com.bio4j.titan.model.uniprot_enzyme.TitanUniprotEnzymeGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.*;


/**
 Implementing the types with Titan
 @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class TitanEnzymeDBGraph
    extends
    EnzymeDBGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> {

    // why this?
    private DefaultTitanGraph rawGraph;
	private TitanUniprotEnzymeGraph uniprotEnzymeGraph = null;


    //-------------------VERTICES----------------------------

    public VertexLabel enzymeTypeLabel;
    public PropertyKey enzymeIdkey;
    public PropertyKey enzymeCofactorskey;
    public PropertyKey enzymeOfficialNamekey;
    public PropertyKey enzymeAlternateNameskey;
    public PropertyKey enzymeCommentkey;
    public PropertyKey enzymeCatalyticActivitykey;
    public PropertyKey enzymePrositeCrossReferenceskey;
    public EnzymeType enzymeType;

    //---------------INDICES---------------------------

    TitanTypedVertexIndex.DefaultUnique<
            Enzyme<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>,
            EnzymeType,
            EnzymeType.id, String,
            EnzymeDBGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>,
            DefaultTitanGraph
            > enzymeIdIndex;

	@Override
	public TypedVertexIndex.Unique<Enzyme<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, EnzymeType, EnzymeType.id, String, EnzymeDBGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel>, DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> enzymeIdIndex() {
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
    public UniprotEnzymeDBGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> uniprotEnzymeDBGraph() {
        return uniprotEnzymeGraph;
    }

    @Override
    public EnzymeType Enzyme() {
        return enzymeType;
    }

    private void initTypes() {

        //-----------------------------------------------------------------------------------------
        //--------------------------------VERTICES--------------------------------------------
        // first create the type with a ref to the yet uninitialized label
	    enzymeType = new EnzymeType(enzymeTypeLabel);
        // init the label
        enzymeTypeLabel = raw().createOrGet( 
            raw().titanLabelMakerForVertexType(enzymeType) 
        );
        // init properties
        enzymeIdkey = raw().createOrGet( 
            raw().titanPropertyMakerForVertexProperty( Enzyme().id ).cardinality(Cardinality.SINGLE) 
        );
        enzymeCofactorskey = raw().createOrGet( 
            raw().titanPropertyMakerForVertexProperty( Enzyme().cofactors ).cardinality(Cardinality.SINGLE) 
        );
        enzymeOfficialNamekey = raw().createOrGet( 
            raw().titanPropertyMakerForVertexProperty( Enzyme().officialName ).cardinality(Cardinality.SINGLE) 
        );
        enzymeAlternateNameskey = raw().createOrGet( 
            raw().titanPropertyMakerForVertexProperty( Enzyme().alternateNames ).cardinality(Cardinality.SINGLE) 
        );
        enzymeCommentkey = raw().createOrGet( 
            raw().titanPropertyMakerForVertexProperty( Enzyme().comment ).cardinality(Cardinality.SINGLE) 
        );
        enzymeCatalyticActivitykey = raw().createOrGet( 
            raw().titanPropertyMakerForVertexProperty( Enzyme().catalyticActivity ).cardinality(Cardinality.SINGLE) 
        );
        enzymePrositeCrossReferenceskey = raw().createOrGet( 
            raw().titanPropertyMakerForVertexProperty( Enzyme().prositeCrossReferences ).cardinality(Cardinality.SINGLE) 
        );

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


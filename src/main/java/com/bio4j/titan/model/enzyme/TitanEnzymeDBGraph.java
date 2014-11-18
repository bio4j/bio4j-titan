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

    private TitanManagement mgmt;

    public TitanManagement managementSystem() { return this.mgmt; } 


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

        // First get a titanMgmt instance, that will be used throughout
        this.mgmt = rawGraph.managementSystem();
        initTypes(mgmt);
        initIndices(mgmt);

        // mgmt.commit();
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

    private void initTypes(TitanManagement mgmt) {

        //-----------------------------------------------------------------------------------------
        //--------------------------------VERTICES--------------------------------------------
        // the tricky part is initializing the label part
        // first init the label
        enzymeTypeLabel = raw().createOrGet( mgmt,
            raw().titanLabelMakerForVertexType( mgmt, new EnzymeType(null)) // null is OK here :-/
        );
        // then create the type with a ref to the label
        enzymeType = new EnzymeType(enzymeTypeLabel);
        // init properties
        enzymeIdkey = raw().createOrGet( mgmt,
            raw().titanPropertyMakerForVertexProperty( mgmt, Enzyme().id ).cardinality(Cardinality.SINGLE) 
        );
        enzymeCofactorskey = raw().createOrGet( mgmt,
            raw().titanPropertyMakerForVertexProperty( mgmt, Enzyme().cofactors ).cardinality(Cardinality.SINGLE) 
        );
        enzymeOfficialNamekey = raw().createOrGet( mgmt, 
            raw().titanPropertyMakerForVertexProperty( mgmt, Enzyme().officialName ).cardinality(Cardinality.SINGLE) 
        );
        enzymeAlternateNameskey = raw().createOrGet( mgmt, 
            raw().titanPropertyMakerForVertexProperty( mgmt, Enzyme().alternateNames ).cardinality(Cardinality.SINGLE) 
        );
        enzymeCommentkey = raw().createOrGet( mgmt, 
            raw().titanPropertyMakerForVertexProperty( mgmt, Enzyme().comment ).cardinality(Cardinality.SINGLE) 
        );
        enzymeCatalyticActivitykey = raw().createOrGet( mgmt, 
            raw().titanPropertyMakerForVertexProperty( mgmt, Enzyme().catalyticActivity ).cardinality(Cardinality.SINGLE) 
        );
        enzymePrositeCrossReferenceskey = raw().createOrGet( mgmt, 
            raw().titanPropertyMakerForVertexProperty( mgmt, Enzyme().prositeCrossReferences ).cardinality(Cardinality.SINGLE) 
        );

    }

    private void initIndices(TitanManagement mgmt) {
        
        enzymeIdIndex = new TitanTypedVertexIndex.DefaultUnique<>(mgmt, this, Enzyme().id);
    }

	/*
		You can use this as `enzymeDBGraph.withUniprot(new TitanUniprotEnzymeDBGraph(raw, uniprotGraph, enzymeGraph))`
	*/
	public TitanEnzymeDBGraph withUniprot(TitanUniprotEnzymeGraph uniprotEnzymeGraph) {

		this.uniprotEnzymeGraph = uniprotEnzymeGraph;

		return this;
	}
}


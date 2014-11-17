package com.bio4j.titan.model.uniprot_uniref;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot_uniref.UniprotUniRefGraph;
import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniref.TitanUniRefGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;


/**
 Implementing the types with Titan
 @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class TitanUniprotUniRefGraph
        extends
        UniprotUniRefGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> {

    private DefaultTitanGraph rawGraph;
    private UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> uniprotRawGraph;
    private UniRefGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> uniRefRawGraph;

    //---------------RELATIONSHIPS---------------------------

    private TitanLabel uniRef50MemberLabel;
    private UniRef50MemberType uniRef50MemberType;
	public TitanKey uniRef50MemberProteinAccessionKey;

    private TitanLabel uniRef90MemberLabel;
    private UniRef90MemberType uniRef90MemberType;
	public TitanKey uniRef90MemberProteinAccessionKey;

    private TitanLabel uniRef100MemberLabel;
    private UniRef100MemberType uniRef100MemberType;
	public TitanKey uniRef100MemberProteinAccessionKey;

    private TitanLabel uniRef50RepresentantLabel;
    private UniRef50RepresentantType uniRef50RepresentantType;
	public TitanKey uniRef50RepresentantProteinAccessionKey;

    private TitanLabel uniRef90RepresentantLabel;
    private UniRef90RepresentantType uniRef90RepresentantType;
	public TitanKey uniRef90RepresentantProteinAccessionKey;

    private TitanLabel uniRef100RepresentantLabel;
    private UniRef100RepresentantType uniRef100RepresentantType;
	public TitanKey uniRef100RepresentantProteinAccessionKey;


    public TitanUniprotUniRefGraph(DefaultTitanGraph rawGraph, TitanUniprotGraph titanUniprotGraph, TitanUniRefGraph titanUniRefGraph) {
        super(rawGraph);
        this.rawGraph = rawGraph;
        this.uniprotRawGraph = titanUniprotGraph;
        this.uniRefRawGraph = titanUniRefGraph;
        initTypes();
        initIndices();
    }

    @Override
    public DefaultTitanGraph raw() {
        return rawGraph;
    }

    private void initTypes() {

        //-----------------------------------------------------------------------------------------
        //--------------------------------RELATIONSHIPS--------------------------------------------

        uniRef50MemberLabel = raw().titanLabelForEdgeType(new UniRef50MemberType((TitanLabel) null));
        uniRef50MemberType = new UniRef50MemberType(uniRef50MemberLabel);
	    uniRef50MemberProteinAccessionKey = raw().titanKeyForEdgePropertySingle(uniRef50MemberType.proteinAccession);

        uniRef90MemberLabel = raw().titanLabelForEdgeType(new UniRef90MemberType((TitanLabel) null));
        uniRef90MemberType = new UniRef90MemberType(uniRef90MemberLabel);
	    uniRef90MemberProteinAccessionKey = raw().titanKeyForEdgePropertySingle(uniRef90MemberType.proteinAccession);

        uniRef100MemberLabel = raw().titanLabelForEdgeType(new UniRef100MemberType((TitanLabel) null));
        uniRef100MemberType = new UniRef100MemberType(uniRef100MemberLabel);
	    uniRef100MemberProteinAccessionKey = raw().titanKeyForEdgePropertySingle(uniRef100MemberType.proteinAccession);

        uniRef50RepresentantLabel = raw().titanLabelForEdgeType(new UniRef50RepresentantType((TitanLabel) null));
        uniRef50RepresentantType = new UniRef50RepresentantType(uniRef50RepresentantLabel);
	    uniRef50RepresentantProteinAccessionKey = raw().titanKeyForEdgePropertySingle(uniRef50RepresentantType.proteinAccession);

        uniRef90RepresentantLabel = raw().titanLabelForEdgeType(new UniRef90RepresentantType((TitanLabel) null));
        uniRef90RepresentantType = new UniRef90RepresentantType(uniRef90RepresentantLabel);
	    uniRef90RepresentantProteinAccessionKey = raw().titanKeyForEdgePropertySingle(uniRef90RepresentantType.proteinAccession);

	    uniRef100RepresentantLabel = raw().titanLabelForEdgeType(new UniRef100RepresentantType((TitanLabel) null));
        uniRef100RepresentantType = new UniRef100RepresentantType(uniRef100RepresentantLabel);
	    uniRef100RepresentantProteinAccessionKey = raw().titanKeyForEdgePropertySingle(uniRef100RepresentantType.proteinAccession);


    }

    private void initIndices() {

    }


    @Override
    public UniprotGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> uniprotGraph() {
        return uniprotRawGraph;
    }

    @Override
    public UniRefGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> uniRefGraph() {
        return uniRefRawGraph;
    }

    @Override
    public UniRef50MemberType UniRef50Member() {
        return uniRef50MemberType;
    }

    @Override
    public UniRef90MemberType UniRef90Member() {
        return uniRef90MemberType;
    }

    @Override
    public UniRef100MemberType UniRef100Member() {
        return uniRef100MemberType;
    }

    @Override
    public UniRef100RepresentantType UniRef100Representant() {
        return uniRef100RepresentantType;
    }

    @Override
    public UniRef90RepresentantType UniRef90Representant() {
        return uniRef90RepresentantType;
    }

    @Override
    public UniRef50RepresentantType UniRef50Representant() {
        return uniRef50RepresentantType;
    }


}
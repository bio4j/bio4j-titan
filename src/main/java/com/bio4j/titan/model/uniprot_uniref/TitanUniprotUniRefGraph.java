package com.bio4j.titan.model.uniprot_uniref;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot_uniref.UniprotUniRefGraph;
import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniref.TitanUniRefGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.*;


/**
 Implementing the types with Titan
 @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class TitanUniprotUniRefGraph
        extends
        UniprotUniRefGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> {

    private DefaultTitanGraph rawGraph;
    private UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> uniprotRawGraph;
    private UniRefGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> uniRefRawGraph;

    //---------------RELATIONSHIPS---------------------------

    private EdgeLabel uniRef50MemberLabel;
    private UniRef50MemberType uniRef50MemberType;
    private EdgeLabel uniRef90MemberLabel;
    private UniRef90MemberType uniRef90MemberType;
    private EdgeLabel uniRef100MemberLabel;
    private UniRef100MemberType uniRef100MemberType;
    private EdgeLabel uniRef50RepresentantLabel;
    private UniRef50RepresentantType uniRef50RepresentantType;
    private EdgeLabel uniRef90RepresentantLabel;
    private UniRef90RepresentantType uniRef90RepresentantType;
    private EdgeLabel uniRef100RepresentantLabel;
    private UniRef100RepresentantType uniRef100RepresentantType;


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

        // uniRef50MemberLabel = raw().titanLabelForEdgeType(new UniRef50MemberType((EdgeLabel) null));
        // uniRef50MemberType = new UniRef50MemberType(uniRef50MemberLabel);
        // uniRef90MemberLabel = raw().titanLabelForEdgeType(new UniRef90MemberType((EdgeLabel) null));
        // uniRef90MemberType = new UniRef90MemberType(uniRef90MemberLabel);
        // uniRef100MemberLabel = raw().titanLabelForEdgeType(new UniRef100MemberType((EdgeLabel) null));
        // uniRef100MemberType = new UniRef100MemberType(uniRef100MemberLabel);


        // uniRef50RepresentantLabel = raw().titanLabelForEdgeType(new UniRef50RepresentantType((EdgeLabel) null));
        // uniRef50RepresentantType = new UniRef50RepresentantType(uniRef50RepresentantLabel);
        // uniRef90RepresentantLabel = raw().titanLabelForEdgeType(new UniRef90RepresentantType((EdgeLabel) null));
        // uniRef90RepresentantType = new UniRef90RepresentantType(uniRef90RepresentantLabel);
        // uniRef100RepresentantLabel = raw().titanLabelForEdgeType(new UniRef100RepresentantType((EdgeLabel) null));
        // uniRef100RepresentantType = new UniRef100RepresentantType(uniRef100RepresentantLabel);

    }

    private void initIndices() {

    }


    @Override
    public UniprotGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> uniprotGraph() {
        return uniprotRawGraph;
    }

    @Override
    public UniRefGraph<DefaultTitanGraph, TitanVertex, VertexLabel, TitanEdge, EdgeLabel> uniRefGraph() {
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
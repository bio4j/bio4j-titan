package com.bio4j.titan.model.uniprot_uniref;

import com.bio4j.model.uniprot_uniref.UniprotUniRefGraph;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot_uniref.relationships.*;
import com.bio4j.titan.model.uniref.TitanUniRefGraph;
import com.ohnosequences.typedGraphs.titan.TitanTypedGraph;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 7/22/2014.
 */
public class TitanUniprotUniRefGraph implements
		TitanTypedGraph, UniprotUniRefGraph {

	protected TitanGraph rawGraph;
	public TitanUniprotGraph uniprotGraph;
	public TitanUniRefGraph uniRefGraph;

	TitanUniprotUniRefGraph(TitanGraph rawGraph, TitanUniprotGraph uniprotGraph, TitanUniRefGraph uniRefGraph) {

		this.rawGraph = rawGraph;
		this.uniprotGraph = uniprotGraph;
		this.uniRefGraph = uniRefGraph;
	}

	@Override
	public TitanGraph rawGraph() {

		return rawGraph;
	}

	//-----------------------------------------------------------------------------------------
	//--------------------------------RELATIONSHIPS--------------------------------------------

	public TitanLabel uniRef50MemberLabel;
	public TitanUniRef50Member.TitanUniRef50MemberType uniRef50MemberT = new TitanUniRef50Member.TitanUniRef50MemberType(this);
	public TitanLabel uniRef90MemberLabel;
	public TitanUniRef90Member.TitanUniRef90MemberType uniRef90MemberT = new TitanUniRef90Member.TitanUniRef90MemberType(this);
	public TitanLabel uniRef100MemberLabel;
	public TitanUniRef100Member.TitanUniRef100MemberType uniRef100MemberT = new TitanUniRef100Member.TitanUniRef100MemberType(this);
	public TitanLabel uniRef50RepresentantLabel;
	public TitanUniRef50Representant.TitanUniRef50RepresentantType uniRef50RepresentantT = new TitanUniRef50Representant.TitanUniRef50RepresentantType(this);
	public TitanLabel uniRef90RepresentantLabel;
	public TitanUniRef90Representant.TitanUniRef90RepresentantType uniRef90RepresentantT = new TitanUniRef90Representant.TitanUniRef90RepresentantType(this);
	public TitanLabel uniRef100RepresentantLabel;
	public TitanUniRef100Representant.TitanUniRef100RepresentantType uniRef100RepresentantT = new TitanUniRef100Representant.TitanUniRef100RepresentantType(this);

}

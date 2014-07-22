package com.bio4j.titan.model.uniprot_uniref;

import com.bio4j.model.uniprot_uniref.UniprotUniRefGraph;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot_uniref.relationships.TitanUniRef100Member;
import com.bio4j.titan.model.uniprot_uniref.relationships.TitanUniRef50Member;
import com.bio4j.titan.model.uniprot_uniref.relationships.TitanUniRef90Member;
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
	public TitanUniRef90Member.TitanUniRef90MemberType uniRef90MemberT = new TitanUniRef90Member().TitanUniRef90MemberType(this);
	public TitanLabel uniRef100MemberLabel;
	public TitanUniRef100Member.TitanUniRef100MemberType uniRef100MemberT = new TitanUniRef100Member.TitanUniRef100MemberType(this);

}

package com.bio4j.titan.model.uniprot_uniref;

import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniref.TitanUniRefGraph;
import com.thinkaurelius.titan.core.TitanGraph;

/**
 * Created by ppareja on 7/22/2014.
 */
public class TitanUniprotUniRefGraphImpl extends TitanUniprotUniRefGraph {

	public TitanUniprotUniRefGraphImpl(TitanGraph rawGraph, TitanUniprotGraph uniprotGraph, TitanUniRefGraph uniRefGraph) {
		super(rawGraph, uniprotGraph, uniRefGraph);
		initTypes();
	}

	private void initTypes() {
		// uniRef50Member
		uniRef50MemberLabel = titanLabelForRelationshipType(uniRef50MemberT);
		// uniRef90Member
		uniRef90MemberLabel = titanLabelForRelationshipType(uniRef90MemberT);
		// uniRef100Member
		uniRef100MemberLabel = titanLabelForRelationshipType(uniRef100MemberT);
		// uniRef50Representant
		uniRef50RepresentantLabel = titanLabelForRelationshipType(uniRef50RepresentantT);
		// uniRef90Representant
		uniRef90RepresentantLabel = titanLabelForRelationshipType(uniRef90RepresentantT);
		// uniRef100Representant
		uniRef100RepresentantLabel = titanLabelForRelationshipType(uniRef100RepresentantT);
	}

}

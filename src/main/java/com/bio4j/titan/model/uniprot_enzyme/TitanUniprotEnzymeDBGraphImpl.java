package com.bio4j.titan.model.uniprot_enzyme;

import com.thinkaurelius.titan.core.TitanGraph;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.enzyme.TitanEnzymeDBGraph;


/**
 * Created by ppareja on 6/25/2014.
 */
public class TitanUniprotEnzymeDBGraphImpl extends TitanUniprotEnzymeDBGraph {

	public TitanUniprotEnzymeDBGraphImpl(TitanGraph rawGraph, TitanUniprotGraph uniprotGraph, TitanEnzymeDBGraph enzymeDBGraph) {
		super(rawGraph, uniprotGraph, enzymeDBGraph);
		initTypes();
	}

	private void initTypes() {
		// enzymaticActivity
		enzymaticActivityLabel = titanLabelForRelationshipType(enzymaticActivityT);
	}

}

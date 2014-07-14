package com.bio4j.titan.model.uniprot_enzyme;

import com.thinkaurelius.titan.core.TitanGraph;

/**
 * Created by ppareja on 6/25/2014.
 */
public class TitanUniprotEnzymeDBGraphImpl extends TitanUniprotEnzymeDBGraph{

	public TitanUniprotEnzymeDBGraphImpl(TitanGraph graph) {
		super(graph);
		initTypes();
	}

	private void initTypes() {
		// enzymaticActivity
		enzymaticActivityLabel = titanLabelForRelationshipType(enzymaticActivityT);
	}

}

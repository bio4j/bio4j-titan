package com.bio4j.titan.model.uniprot;

import com.thinkaurelius.titan.core.TitanGraph;

/**
* Created by ppareja on 6/20/2014.
*/
public class TitanUniprotGraphImpl extends TitanUniprotGraph {

	public TitanUniprotGraphImpl(TitanGraph graph) {
		super(graph);
		initTypes();
		initIndices();
	}

	private void initTypes() {

	}

	private void initIndices() {

	}
}

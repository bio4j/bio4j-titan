package com.bio4j.titan.model.enzyme;

import com.thinkaurelius.titan.core.TitanGraph;

/**
 * Created by ppareja on 6/20/2014.
 */
public class TitanEnzymeDBGraphImpl extends TitanEnzymeDBGraph {

	public TitanEnzymeDBGraphImpl(TitanGraph graph) {
		super(graph);
		initTypes();
		initIndices();
	}

	private void initTypes() {

	}

	private void initIndices() {

	}
}

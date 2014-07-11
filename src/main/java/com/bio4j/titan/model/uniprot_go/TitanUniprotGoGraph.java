package com.bio4j.titan.model.uniprot_go;

import com.bio4j.model.uniprot_go.UniprotGoGraph;
import com.bio4j.titan.model.go.TitanGoGraph;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.TitanUniprotGraphImpl;
import com.ohnosequences.typedGraphs.titan.TitanTypedGraph;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanLabel;

/**
 * Created by ppareja on 6/25/2014.
 */
public class TitanUniprotGoGraph implements
		TitanTypedGraph, UniprotGoGraph {

	protected TitanGraph rawGraph;
	protected TitanUniprotGraph titanUniprotGraph;
	protected TitanGoGraph titanGoGraph;

	TitanUniprotGoGraph(TitanGraph rawGraph) {

		this.rawGraph = rawGraph;

	}

	@Override
	public TitanGraph rawGraph() {
		return rawGraph;
	}

	//-----------------------------------------------------------------------------------------
	//--------------------------------RELATIONSHIPS--------------------------------------------

	public TitanLabel goAnnotationLabel;
	public TitanGoAnnotationType goAnnotationT = new TitanGoAnnotationType(this);

}

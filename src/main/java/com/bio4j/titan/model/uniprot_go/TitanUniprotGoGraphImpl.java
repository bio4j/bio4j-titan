package com.bio4j.titan.model.uniprot_go;


import com.bio4j.titan.model.go.TitanGoGraph;
import com.bio4j.titan.model.go.TitanGoGraphImpl;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.TitanUniprotGraphImpl;
import com.thinkaurelius.titan.core.TitanGraph;

/**
 * Created by ppareja on 6/25/2014.
 */
public class TitanUniprotGoGraphImpl extends TitanUniprotGoGraph{

	public TitanUniprotGoGraphImpl(TitanGraph graph, TitanUniprotGraph uniprotGraph, TitanGoGraph goGraph) {
		super(graph, uniprotGraph, goGraph);
		initTypes();
	}

	private void initTypes() {
		// goAnnotation
		goAnnotationLabel = titanLabelForRelationshipType(goAnnotationT);
	}

}

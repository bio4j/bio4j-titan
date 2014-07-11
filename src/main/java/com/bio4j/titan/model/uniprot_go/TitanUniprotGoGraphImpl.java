package com.bio4j.titan.model.uniprot_go;


import com.thinkaurelius.titan.core.TitanGraph;

/**
 * Created by ppareja on 6/25/2014.
 */
public class TitanUniprotGoGraphImpl extends TitanUniprotGoGraph{

	public TitanUniprotGoGraphImpl(TitanGraph graph) {
		super(graph);
		initTypes();
	}

	private void initTypes() {
		// goAnnotation
		goAnnotationLabel = titanLabelForRelationshipType(goAnnotationT);
	}

}

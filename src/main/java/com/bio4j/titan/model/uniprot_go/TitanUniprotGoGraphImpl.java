package com.bio4j.titan.model.uniprot_go;


import com.bio4j.titan.model.go.TitanGoGraphImpl;
import com.bio4j.titan.model.uniprot.TitanUniprotGraphImpl;
import com.thinkaurelius.titan.core.TitanGraph;

/**
 * Created by ppareja on 6/25/2014.
 */
public class TitanUniprotGoGraphImpl extends TitanUniprotGoGraph{

	public TitanUniprotGoGraphImpl(TitanGraph graph) {
		super(graph);
		this.uniprotGraph = new TitanUniprotGraphImpl(rawGraph);
		this.goGraph = new TitanGoGraphImpl(rawGraph);
		initTypes();
	}

	private void initTypes() {
		// goAnnotation
		goAnnotationLabel = titanLabelForRelationshipType(goAnnotationT);
	}

}

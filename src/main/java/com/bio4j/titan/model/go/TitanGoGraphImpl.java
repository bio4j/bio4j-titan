package com.bio4j.titan.model.go;

import com.ohnosequences.typedGraphs.titan.TitanNodeIndex;
import com.thinkaurelius.titan.core.TitanGraph;
import com.ohnosequences.typedGraphs.titan.TitanNodeIndex.DefaultUnique

public final class TitanGoGraphImpl extends TitanGoGraph {

	public TitanGoGraphImpl(TitanGraph graph) {
		super(graph);
		initTypes();
		initIndices();
	}

	private void initTypes() {


		// Term keys
		goTermTkey = titanKeyForNodeType(goTermT.id);
		goTermIdKey = goTermTkey;
		goTermNameKey = titanKeyForNodeProperty(goTermT.name).make();
		// if you want to index the name just add the corresponding titan stuff before make, like
		// termNameKey = titanKeyForNodeProperty(termT.name).indexed(com.tinkerpop.blueprints.Edge.class).make();

		// partOf stuff
		partOfLabel = titanLabelForRelationshipType(partOfT).make();
		// hasPartOF stuff
		hasPartOfLabel = titanLabelForRelationshipType(hasPartOfT).make();
		// isA stuff
		isALabel = titanLabelForRelationshipType(isAT).make();
		// regulates stuff
		regulatesLabel = titanLabelForRelationshipType(regulatesT).make();
		// positivelyRegulates stuff
		positivelyRegulatesLabel = titanLabelForRelationshipType(positivelyRegulatesT).make();
		// negativelyRegulates stuff
		negativelyRegulatesLabel = titanLabelForRelationshipType(negativelyRegulatesT).make();

	}

	private void initIndices(){
		termIdIndex = new TitanNodeIndex.DefaultUnique(this.rawGraph, goTermT.id);
	}
}
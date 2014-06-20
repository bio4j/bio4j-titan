package com.bio4j.titan.model.go;

import com.ohnosequences.typedGraphs.titan.TitanNodeIndex;
import com.thinkaurelius.titan.core.TitanGraph;
import com.ohnosequences.typedGraphs.titan.TitanNodeIndex.DefaultUnique;

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
		goTermNameKey = titanKeyForNodeProperty(goTermT.name);
		goTermDefinitionKey = titanKeyForNodeProperty(goTermT.definition);
		goTermObsoleteKey = titanKeyForNodeProperty(goTermT.obsolete);
		goTermCommentKey = titanKeyForNodeProperty(goTermT.comment);
		// if you want to index the name just add the corresponding titan stuff before make, like
		// termNameKey = titanKeyForNodeProperty(termT.name).indexed(com.tinkerpop.blueprints.Edge.class).make();

		// partOf stuff
		partOfLabel = titanLabelForRelationshipType(partOfT);
		// hasPartOF stuff
		hasPartOfLabel = titanLabelForRelationshipType(hasPartOfT);
		// isA stuff
		isALabel = titanLabelForRelationshipType(isAT);
		// regulates stuff
		regulatesLabel = titanLabelForRelationshipType(regulatesT);
		// positivelyRegulates stuff
		positivelyRegulatesLabel = titanLabelForRelationshipType(positivelyRegulatesT);
		// negativelyRegulates stuff
		negativelyRegulatesLabel = titanLabelForRelationshipType(negativelyRegulatesT);

	}

	private void initIndices(){
		goTermIdIndex = new TitanNodeIndex.DefaultUnique(this, goTermT.id);
		subontologiesNameIndex = new TitanNodeIndex.DefaultUnique(this, subOntologiesT.name);
	}
}
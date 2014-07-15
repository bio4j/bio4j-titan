package com.bio4j.titan.model.enzyme;

import com.bio4j.titan.model.uniprot_enzyme.TitanUniprotEnzymeDBGraph;
import com.bio4j.titan.model.uniprot_enzyme.TitanUniprotEnzymeDBGraphImpl;
import com.ohnosequences.typedGraphs.titan.TitanNodeIndex;
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

		// Term keys
		enzymeTkey = titanKeyForNodeType(enzymeT.id);
		enzymeIdKey = enzymeTkey;
		enzymeCofactorsKey = titanKeyForNodeProperty(enzymeT.cofactors);
		enzymeOfficialNameKey = titanKeyForNodeProperty(enzymeT.officialName);
		enzymeCatalyticActivityKey = titanKeyForNodeProperty(enzymeT.catalyticActivity);
		enzymeCommentKey = titanKeyForNodeProperty(enzymeT.comment);
		enzymePrositeCrossReferencesKey = titanKeyForNodeProperty(enzymeT.prositeCrossReferences);

	}

	private void initIndices() {
		enzymeIdIndex = new TitanNodeIndex.DefaultUnique(this, enzymeT.id);
	}
}

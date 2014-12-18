package com.bio4j.titan.model.uniprot.programs;

import com.bio4j.titan.model.uniprot.TitanUniProtGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.ohnosequences.util.Executable;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.schema.EdgeLabelMaker;
import com.thinkaurelius.titan.core.schema.VertexLabelMaker;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import com.bio4j.model.uniprot.programs.ImportUniProtVertices;

import java.util.ArrayList;

/**
 * Created by ppareja on 12/11/2014.
 */
public class ImportUniProtVerticesTitan extends ImportUniProtVertices<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> implements Executable {

	@Override
	protected TitanUniProtGraph config(String dbFolder, String propertiesFile) {

		//-------creating graph handlers---------------------
		TitanGraph graph = TitanFactory.open(propertiesFile);
		return new TitanUniProtGraph(new DefaultTitanGraph(graph));
	}

	@Override
	public void execute(ArrayList<String> array) {
		String[] args = new String[array.size()];
		for (int i = 0; i < array.size(); i++) {
			args[i] = array.get(i);
		}
		importUniProtVertices(args);
	}
}

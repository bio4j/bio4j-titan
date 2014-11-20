package com.bio4j.titan.model.uniprot_go.tests;

import com.bio4j.model.uniprot_go.UniprotGoGraph;
import com.bio4j.titan.model.go.TitanGoGraph;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot_go.TitanUniprotGoGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.ohnosequences.util.Executable;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.*;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import com.bio4j.model.uniprot_go.tests.ImportUniprotGoTest;

import java.util.ArrayList;

/**
 * Created by ppareja on 11/5/2014.
 */
public class ImportUniprotGoTestTitan extends ImportUniprotGoTest<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> implements Executable {

	@Override
	protected UniprotGoGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> config(String dbFolder) {
		//----------DB configuration------------------
		Configuration conf = new BaseConfiguration();
		conf.setProperty("storage.directory", dbFolder);
		conf.setProperty("storage.backend", "local");
		conf.setProperty("autotype", "none");
		//-------creating graph handlers---------------------
		TitanGraph graph = TitanFactory.open(conf);
		DefaultTitanGraph defGraph = new DefaultTitanGraph(graph);
		return new TitanUniprotGoGraph(defGraph, new TitanUniprotGraph(defGraph), new TitanGoGraph(defGraph));
	}

	@Override
	public void execute(ArrayList<String> array) {
		String[] args = new String[array.size()];
		for (int i = 0; i < array.size(); i++) {
			args[i] = array.get(i);
		}
		importUniprotGoTest(args);
	}

}

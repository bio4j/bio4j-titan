package com.bio4j.titan.model.uniprot_uniref.programs;

import com.bio4j.model.uniprot_uniref.UniProtUniRefGraph;
import com.bio4j.model.uniprot_uniref.programs.ImportUniProtUniRef;
import com.bio4j.titan.model.uniprot.TitanUniProtGraph;
import com.bio4j.titan.model.uniprot_uniref.TitanUniProtUniRefGraph;
import com.bio4j.titan.model.uniref.TitanUniRefGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.ohnosequences.util.Executable;


import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.*;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import java.util.ArrayList;

/**
 * Imports uniref(100,90,50) clusters info into Bio4j
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public class ImportUniProtUniRefTitan extends ImportUniProtUniRef<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> implements Executable {

	@Override
	protected TitanUniProtUniRefGraph config(String dbFolder) {
		//----------DB configuration------------------
		Configuration conf = new BaseConfiguration();
		conf.setProperty("storage.directory", dbFolder);
		conf.setProperty("storage.backend", "berkeleyje");
		conf.setProperty("autotype", "none");
		conf.setProperty("storage.batch-loading","true"); //We can add this configuration parameter since we are not creating any nodes here
		//-------creating graph handlers---------------------
		TitanGraph graph = TitanFactory.open(conf);
		DefaultTitanGraph defGraph = new DefaultTitanGraph(graph);
		return new TitanUniProtUniRefGraph(defGraph, new TitanUniProtGraph(defGraph), new TitanUniRefGraph(defGraph));
	}

	@Override
	public void execute(ArrayList<String> array) {
		String[] args = new String[array.size()];
		for (int i = 0; i < array.size(); i++) {
			args[i] = array.get(i);
		}
		importUniProtUniRef(args);
	}

}
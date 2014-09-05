package com.bio4j.titan.model.uniprot_uniref.programs;

import com.bio4j.model.uniprot_uniref.UniprotUniRefGraph;
import com.bio4j.titan.model.uniprot_uniref.TitanUniprotUniRefGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.ohnosequences.util.Executable;


import com.thinkaurelius.titan.core.*;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import java.util.ArrayList;

/**
 * Imports uniref(100,90,50) clusters info into Bio4j
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public class ImportUniprotUniRefTitan extends ImportUniprotUniRef<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> implements Executable {

	@Override
	protected UniprotUniRefGraph<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> config(String dbFolder) {
		//----------DB configuration------------------
		Configuration conf = new BaseConfiguration();
		conf.setProperty("storage.directory", dbFolder);
		conf.setProperty("storage.backend", "local");
		conf.setProperty("autotype", "none");
		//-------creating graph handlers---------------------
		TitanGraph graph = TitanFactory.open(conf);
		return new TitanUniprotUniRefGraph(new DefaultTitanGraph(graph));
	}

	@Override
	public void execute(ArrayList<String> array) {
		String[] args = new String[array.size()];
		for (int i = 0; i < array.size(); i++) {
			args[i] = array.get(i);
		}
		importUniprotUniRef(args);
	}

}
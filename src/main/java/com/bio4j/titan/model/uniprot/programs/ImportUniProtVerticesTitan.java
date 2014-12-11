package com.bio4j.titan.model.uniprot.programs;

import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
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
	protected TitanUniprotGraph config(String dbFolder) {
		//----------DB configuration------------------
		Configuration conf = new BaseConfiguration();
		conf.setProperty("storage.directory", dbFolder);
		conf.setProperty("storage.backend", "berkeleyje");
		conf.setProperty("storage.batch-loading","true");
		conf.setProperty("query.force-index", "true");
		conf.setProperty("storage.transactions", "false");
		conf.setProperty("query.fast-property", "false");
		conf.setProperty("autotype", "none");
		conf.setProperty("storage.berkeleydb.cache-percentage", "80");
		//-------creating graph handlers---------------------
		TitanGraph graph = TitanFactory.open(conf);
		return new TitanUniprotGraph(new DefaultTitanGraph(graph));
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

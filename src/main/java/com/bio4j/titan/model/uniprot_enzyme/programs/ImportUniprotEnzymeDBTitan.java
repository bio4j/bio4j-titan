/*
  * Copyright (C) 2010-2013  "Bio4j"
  *
  * This file is part of Bio4j
  *
  * Bio4j is free software: you can redistribute it and/or modify
  * it under the terms of the GNU Affero General Public License as
  * published by the Free Software Foundation, either version 3 of the
  * License, or (at your option) any later version.
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU Affero General Public License for more details.
  * You should have received a copy of the GNU Affero General Public License
  * along with this program.  If not, see <http:www.gnu.org/licenses/>
  */
package com.bio4j.titan.model.uniprot_enzyme.programs;

import com.bio4j.model.uniprot_enzymedb.UniprotEnzymeDBGraph;
import com.bio4j.model.uniprot_enzymedb.programs.ImportUniprotEnzymeDB;
import com.bio4j.titan.model.enzyme.TitanEnzymeDBGraph;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot_enzyme.TitanUniprotEnzymeGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.ohnosequences.util.Executable;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.*;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;


import java.util.ArrayList;

/**
 * Imports Enzyme database links to Uniprot into Bio4j
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public class ImportUniprotEnzymeDBTitan extends ImportUniprotEnzymeDB<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> implements Executable {

	@Override
	protected UniprotEnzymeDBGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> config(String dbFolder) {
		//----------DB configuration------------------
		Configuration conf = new BaseConfiguration();
		conf.setProperty("storage.directory", dbFolder);
		conf.setProperty("storage.backend", "local");
		conf.setProperty("autotype", "none");
		conf.setProperty("storage.batch-loading","true"); //We can add this configuration parameter since we are not creating any nodes here

		//-------creating graph handlers---------------------
		TitanGraph graph = TitanFactory.open(conf);
		DefaultTitanGraph defGraph = new DefaultTitanGraph(graph);
		return new TitanUniprotEnzymeGraph(defGraph, new TitanUniprotGraph(defGraph), new TitanEnzymeDBGraph(defGraph));
	}

	@Override
	public void execute(ArrayList<String> array) {
		String[] args = new String[array.size()];
		for (int i = 0; i < array.size(); i++) {
			args[i] = array.get(i);
		}
		importUniprotEnzymeDB(args);
	}

}

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
package com.bio4j.titan.model.uniprot_ncbiTaxonomy.programs;

import com.bio4j.model.uniprot_go.UniprotGoGraph;
import com.bio4j.model.uniprot_go.programs.ImportUniprotGo;
import com.bio4j.model.uniprot_ncbiTaxonomy.UniprotNCBITaxonomyGraph;
import com.bio4j.model.uniprot_ncbiTaxonomy.programs.ImportUniprotNCBITaxonomy;
import com.bio4j.titan.model.ncbiTaxonomy.TitanNCBITaxonomyGraph;
import com.bio4j.titan.model.uniprot.TitanUniProtGraph;
import com.bio4j.titan.model.uniprot_ncbiTaxonomy.TitanUniProtNCBITaxonomyGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.ohnosequences.util.Executable;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.*;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;


import java.util.ArrayList;

/**
 * Imports the Gene Ontology into Bio4j
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public class ImportUniProtNCBITaxonomyTitan extends ImportUniProtNCBITaxonomy<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> implements Executable {

	@Override
	protected TitanUniProtNCBITaxonomyGraph config(String dbFolder) {
		//----------DB configuration------------------
		Configuration conf = new BaseConfiguration();
		conf.setProperty("storage.directory", dbFolder);
		conf.setProperty("storage.backend", "berkeleyje");
		conf.setProperty("autotype", "none");
		conf.setProperty("storage.batch-loading","true"); //We can add this configuration parameter since we are not creating any nodes here

		//-------creating graph handlers---------------------
		TitanGraph graph = TitanFactory.open(conf);
		DefaultTitanGraph defGraph = new DefaultTitanGraph(graph);
		return new TitanUniProtNCBITaxonomyGraph(defGraph, new TitanUniProtGraph(defGraph), new TitanNCBITaxonomyGraph(defGraph));
	}

	@Override
	public void execute(ArrayList<String> array) {
		String[] args = new String[array.size()];
		for (int i = 0; i < array.size(); i++) {
			args[i] = array.get(i);
		}
		importUniProtNCBITaxonomy(args);
	}

}

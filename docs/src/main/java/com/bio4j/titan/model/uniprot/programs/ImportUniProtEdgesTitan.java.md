
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


```java
package com.bio4j.titan.model.uniprot.programs;

import com.bio4j.model.uniprot.programs.ImportUniProtEdges;
import com.bio4j.titan.model.uniprot.TitanUniProtGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.ohnosequences.util.Executable;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.*;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;


import java.util.ArrayList;

/**
 * Imports Uniprot into Bio4j
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public class ImportUniProtEdgesTitan extends ImportUniProtEdges<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> implements Executable {

	@Override
	protected TitanUniProtGraph config(String dbFolder, String propertiFile) {
		//-------creating graph handlers---------------------
		TitanGraph graph = TitanFactory.open(propertiFile);
		return new TitanUniProtGraph(new DefaultTitanGraph(graph));
	}

	@Override
	public void execute(ArrayList<String> array) {
		String[] args = new String[array.size()];
		for (int i = 0; i < array.size(); i++) {
			args[i] = array.get(i);
		}
		importUniProtEdges(args);
	}

}

```




[test/java/com/bio4j/titan/tests/ImportEnzymeDBTitanTest.java]: ../../../../../../../../test/java/com/bio4j/titan/tests/ImportEnzymeDBTitanTest.java.md
[test/java/com/bio4j/titan/tests/ImportUniRefTitanTest.java]: ../../../../../../../../test/java/com/bio4j/titan/tests/ImportUniRefTitanTest.java.md
[test/java/com/bio4j/titan/tests/uniprot_go.scala]: ../../../../../../../../test/java/com/bio4j/titan/tests/uniprot_go.scala.md
[test/java/com/bio4j/titan/tests/uniref.scala]: ../../../../../../../../test/java/com/bio4j/titan/tests/uniref.scala.md
[test/java/com/bio4j/titan/tests/ImportUniProtGoTitanTest.java]: ../../../../../../../../test/java/com/bio4j/titan/tests/ImportUniProtGoTitanTest.java.md
[test/java/com/bio4j/titan/tests/ImportGOTitanTest.java]: ../../../../../../../../test/java/com/bio4j/titan/tests/ImportGOTitanTest.java.md
[test/java/com/bio4j/titan/tests/go.scala]: ../../../../../../../../test/java/com/bio4j/titan/tests/go.scala.md
[test/java/com/bio4j/titan/tests/IndicesTest.java]: ../../../../../../../../test/java/com/bio4j/titan/tests/IndicesTest.java.md
[test/java/com/bio4j/titan/tests/IndexTestSuite.scala]: ../../../../../../../../test/java/com/bio4j/titan/tests/IndexTestSuite.scala.md
[test/java/com/bio4j/titan/tests/enzymedb.scala]: ../../../../../../../../test/java/com/bio4j/titan/tests/enzymedb.scala.md
[main/java/com/bio4j/titan/util/DefaultTitanGraph.java]: ../../../util/DefaultTitanGraph.java.md
[main/java/com/bio4j/titan/programs/ImportTitanDB.java]: ../../../programs/ImportTitanDB.java.md
[main/java/com/bio4j/titan/model/uniprot_uniref/programs/ImportUniProtUniRefUsingFolderTitan.java]: ../../uniprot_uniref/programs/ImportUniProtUniRefUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_uniref/programs/ImportUniProtUniRefTitan.java]: ../../uniprot_uniref/programs/ImportUniProtUniRefTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_uniref/TitanUniProtUniRefGraph.java]: ../../uniprot_uniref/TitanUniProtUniRefGraph.java.md
[main/java/com/bio4j/titan/model/uniref/programs/SplitUniRefXMLFile.java]: ../../uniref/programs/SplitUniRefXMLFile.java.md
[main/java/com/bio4j/titan/model/uniref/programs/ImportUniRefTitan.java]: ../../uniref/programs/ImportUniRefTitan.java.md
[main/java/com/bio4j/titan/model/uniref/TitanUniRefGraph.java]: ../../uniref/TitanUniRefGraph.java.md
[main/java/com/bio4j/titan/model/enzyme/programs/ImportEnzymeDBTitan.java]: ../../enzyme/programs/ImportEnzymeDBTitan.java.md
[main/java/com/bio4j/titan/model/enzyme/TitanEnzymeDBGraph.java]: ../../enzyme/TitanEnzymeDBGraph.java.md
[main/java/com/bio4j/titan/model/go/TitanGoGraph.java]: ../../go/TitanGoGraph.java.md
[main/java/com/bio4j/titan/model/go/programs/ImportGOTitan.java]: ../../go/programs/ImportGOTitan.java.md
[main/java/com/bio4j/titan/model/ncbiTaxonomy_geninfo/TitanNCBITaxonomyGenInfoGraph.java]: ../../ncbiTaxonomy_geninfo/TitanNCBITaxonomyGenInfoGraph.java.md
[main/java/com/bio4j/titan/model/ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndexTitan.java]: ../../ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndexTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomyUsingFolderTitan.java]: ../../uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomyUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomyTitan.java]: ../../uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomyTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_ncbiTaxonomy/TitanUniProtNCBITaxonomyGraph.java]: ../../uniprot_ncbiTaxonomy/TitanUniProtNCBITaxonomyGraph.java.md
[main/java/com/bio4j/titan/model/ncbiTaxonomy/TitanNCBITaxonomyGraph.java]: ../../ncbiTaxonomy/TitanNCBITaxonomyGraph.java.md
[main/java/com/bio4j/titan/model/ncbiTaxonomy/programs/ImportNCBITaxonomyTitan.java]: ../../ncbiTaxonomy/programs/ImportNCBITaxonomyTitan.java.md
[main/java/com/bio4j/titan/model/geninfo/TitanGenInfoGraph.java]: ../../geninfo/TitanGenInfoGraph.java.md
[main/java/com/bio4j/titan/model/uniprot_go/TitanUniProtGoGraph.java]: ../../uniprot_go/TitanUniProtGoGraph.java.md
[main/java/com/bio4j/titan/model/uniprot_go/programs/ImportUniProtGoUsingFolderTitan.java]: ../../uniprot_go/programs/ImportUniProtGoUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_go/programs/ImportUniProtGoTitan.java]: ../../uniprot_go/programs/ImportUniProtGoTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_enzyme/TitanUniProtEnzymeGraph.java]: ../../uniprot_enzyme/TitanUniProtEnzymeGraph.java.md
[main/java/com/bio4j/titan/model/uniprot_enzyme/programs/ImportUniProtEnzymeDBTitan.java]: ../../uniprot_enzyme/programs/ImportUniProtEnzymeDBTitan.java.md
[main/java/com/bio4j/titan/model/uniprot_enzyme/programs/ImportUniProtEnzymeDBUsingFolderTitan.java]: ../../uniprot_enzyme/programs/ImportUniProtEnzymeDBUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtTitan.java]: ImportUniProtTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtEdgesTitan.java]: ImportUniProtEdgesTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportProteinInteractionsUsingFolderTitan.java]: ImportProteinInteractionsUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportIsoformSequencesTitan.java]: ImportIsoformSequencesTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/SplitUniProtXMLFile.java]: SplitUniProtXMLFile.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtVerticesTitan.java]: ImportUniProtVerticesTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtVerticesUsingFolderTitan.java]: ImportUniProtVerticesUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportProteinInteractionsTitan.java]: ImportProteinInteractionsTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/programs/ImportUniProtEdgesUsingFolderTitan.java]: ImportUniProtEdgesUsingFolderTitan.java.md
[main/java/com/bio4j/titan/model/uniprot/TitanUniProtGraph.java]: ../TitanUniProtGraph.java.md
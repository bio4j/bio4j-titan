
```java
package com.bio4j.titan.model.uniprot.programs;

import com.ohnosequences.util.Executable;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

/**
 * Created by ppareja on 12/15/2014.
 */
public class ImportUniProtEdgesUsingFolderTitan extends ImportUniProtEdgesTitan implements Executable {

	@Override
	public void execute(ArrayList<String> array) {

		if(array.size() != 4){
			System.out.println("This program expects the following parameters:\n" +
					"1. Folder name including the XML to be used \n" +
					"2. Bio4j database folder \n" +
					"3. Config XML file \n" +
					"4. DB properties file (.properties)");
		}else{

			String folderSt = array.get(0);
			String dbFolderSt = array.get(1);
			String configFilest = array.get(2);
			String propertiesFile = array.get(3);

			File folderFile = new File(folderSt);

			if(!folderFile.isDirectory()){

				System.out.println("Error: the value provided is not a folder... :(");

			}else{

				FileFilter xmlFilter = new FileFilter() {
					@Override
					public boolean accept(File pathname) {
						return pathname.isFile() && pathname.getName().endsWith(".xml");
					}
				};
				File[] files = folderFile.listFiles(xmlFilter);

				for (File file : files){
					ArrayList<String> arguments = new ArrayList<>();
					arguments.add(file.getAbsolutePath());
					arguments.add(dbFolderSt);
					arguments.add(configFilest);
					arguments.add(propertiesFile);
					super.execute(arguments);
				}

			}
		}
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
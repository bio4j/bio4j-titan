package com.bio4j.titan.tests;

import com.bio4j.titan.model.enzyme.programs.ImportEnzymeDBTitan;
import com.bio4j.titan.model.uniprot_go.programs.ImportUniProtGoTitan;

import java.io.File;
import java.util.ArrayList;

public class ImportUniProtGoTitanTest {

	public void importUniProtGoTitanTest(String dbFolder){

		ImportUniProtGoTitan importUniProtGoTitan = new ImportUniProtGoTitan();

		String propertiesFile = getPropertiesFile().getAbsolutePath();
		String uniProtFile = getUniProtFile().getAbsolutePath();
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(uniProtFile);
		arguments.add(dbFolder);
		arguments.add(propertiesFile);
		importUniProtGoTitan.execute(arguments);
	}

	private File getPropertiesFile() {

		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("file/importUniProtGoTitanProperties.properties").getFile());

		return file;

	}

	private File getUniProtFile() {

		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("file/uniprot_sprot.xml").getFile());

		return file;

	}

}

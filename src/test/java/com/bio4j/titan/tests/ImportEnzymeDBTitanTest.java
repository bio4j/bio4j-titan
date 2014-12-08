package com.bio4j.titan.tests;

import com.bio4j.titan.model.enzyme.programs.ImportEnzymeDBTitan;

import java.io.File;
import java.util.ArrayList;

public class ImportEnzymeDBTitanTest {

	public void importEnzymeDBTitanTest(String dbFolder){

		ImportEnzymeDBTitan importEnzymeDBTitan = new ImportEnzymeDBTitan();

		String enzymeDataFile = getEnzymeDBDataFile().getAbsolutePath();
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(enzymeDataFile);
		arguments.add(dbFolder);
		importEnzymeDBTitan.execute(arguments);
	}

	private File getEnzymeDBDataFile() {

		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("file/enzyme.dat").getFile());

		return file;

	}
}

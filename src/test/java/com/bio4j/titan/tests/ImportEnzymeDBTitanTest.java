package com.bio4j.titan.tests;

import com.bio4j.titan.model.enzyme.programs.ImportEnzymeDBTitan;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by ppareja on 11/18/2014.
 */
public class ImportEnzymeDBTitanTest {

	public static void main(String[] args){

		ImportEnzymeDBTitanTest importEnzymeDBTitanTest = new ImportEnzymeDBTitanTest();

		ImportEnzymeDBTitan importEnzymeDBTitan = new ImportEnzymeDBTitan();
		String enzymeDataFile = importEnzymeDBTitanTest.getEnzymeDBDataFile().getAbsolutePath();
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(enzymeDataFile);
		arguments.add("bio4j_test");
		importEnzymeDBTitan.execute(arguments);

	}

	private File getEnzymeDBDataFile() {

		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("file/enzyme.dat").getFile());

		return file;

	}
}

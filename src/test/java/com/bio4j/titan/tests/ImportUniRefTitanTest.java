package com.bio4j.titan.tests;

import com.bio4j.titan.model.uniref.programs.ImportUniRefTitan;

import java.io.File;
import java.util.ArrayList;

public class ImportUniRefTitanTest {

	public void importUniRefTitanTest(String dbFolder){

		ImportUniRefTitan importUniRefTitan = new ImportUniRefTitan();

		String propertiesFile = getPropertiesFile().getAbsolutePath();
		String uniRef100File = getUniRef100File().getAbsolutePath();
		String uniRef90File = getUniRef90File().getAbsolutePath();
		String uniRef50File = getUniRef50File().getAbsolutePath();
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(uniRef100File);
		arguments.add(uniRef90File);
		arguments.add(uniRef50File);
		arguments.add(dbFolder);
		arguments.add(propertiesFile);
		importUniRefTitan.execute(arguments);
	}

	private File getPropertiesFile() {

		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("file/importUniRefTitanProperties.properties").getFile());

		return file;

	}

	private File getUniRef100File() {
		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("file/uniref100.xml").getFile());
		return file;
	}
	private File getUniRef90File() {
		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("file/uniref90.xml").getFile());
		return file;
	}
	private File getUniRef50File() {
		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("file/uniref50.xml").getFile());
		return file;
	}

}

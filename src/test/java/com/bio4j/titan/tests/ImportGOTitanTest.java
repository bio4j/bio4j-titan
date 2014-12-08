package com.bio4j.titan.tests;

import com.bio4j.titan.model.go.programs.ImportGOTitan;

import java.io.File;
import java.util.ArrayList;

public class ImportGOTitanTest {

  public void importGOTitanTest(String dbFolder){

    ImportGOTitan importGOTitan = new ImportGOTitan();

    String goDataFile = getGODataFile().getAbsolutePath();
    ArrayList<String> arguments = new ArrayList<>();
    arguments.add(goDataFile);
    arguments.add(dbFolder);
    importGOTitan.execute(arguments);
  }

  private File getGODataFile() {

    //Get file from resources folder
    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource("file/go.xml").getFile());

    return file;

  }
}
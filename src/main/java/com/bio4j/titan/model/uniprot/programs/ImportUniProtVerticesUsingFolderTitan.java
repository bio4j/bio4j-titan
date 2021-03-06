package com.bio4j.titan.model.uniprot.programs;

import com.ohnosequences.util.Executable;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

/**
 * Created by ppareja on 12/15/2014.
 */
public class ImportUniProtVerticesUsingFolderTitan extends ImportUniProtVerticesTitan implements Executable {

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

package com.bio4j.titan.model.uniref.programs;

import com.ohnosequences.util.Executable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public class SplitUniRefXMLFile implements Executable{

	public static final String ENTRY_TAG_NAME = "entry";

	@Override
	public void execute(ArrayList<String> array) {
		String[] args = new String[array.size()];
		for (int i = 0; i < array.size(); i++) {
			args[i] = array.get(i);
		}
		main(args);
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("This program expects two parameters: \n"
					+ "1. UniRef XML input file\n"
					+ "2. Number of entries per resulting part file\n");
		} else {

			int numberOfEntries = Integer.parseInt(args[1]);
			int currentFile = 1;
			int currentEntry = 1;

			File inFile = new File(args[0]);

			String prefixOutFile = args[0].split("\\.")[0];

			try {

				BufferedWriter outBuff = new BufferedWriter(new FileWriter(new File(prefixOutFile + currentFile + ".xml")));
				outBuff.write("<entries>\n");

				BufferedReader reader = new BufferedReader(new FileReader(inFile));
				String line = null;
				int limitForPrintingOut = 10000;

				while ((line = reader.readLine()) != null) {
					if (line.trim().startsWith("<" + ENTRY_TAG_NAME)) {

						if(currentEntry % numberOfEntries == 0){
							outBuff.write("</entries>\n");
							outBuff.close();
							System.out.println("Closing file...");
							currentFile++;
							outBuff = new BufferedWriter(new FileWriter(new File(prefixOutFile + currentFile + ".xml")));
							outBuff.write("<entries>\n");
						}

						outBuff.write((line + "\n"));

						while (!line.trim().startsWith("</" + ENTRY_TAG_NAME + ">")) {
							line = reader.readLine();
							outBuff.write((line + "\n"));
						}

						outBuff.flush();

						currentEntry++;

						if(currentEntry % limitForPrintingOut == 0){
							System.out.println(currentEntry + " already...");
						}
					}
				}

				reader.close();

				outBuff.close();

				System.out.println("Finished!! there were " + currentFile + " files generated :)");


			} catch (Exception e) {
				e.printStackTrace();
			}


		}
	}
}

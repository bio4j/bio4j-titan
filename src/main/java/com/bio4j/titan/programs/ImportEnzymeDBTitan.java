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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.bio4j.titan.programs;

import com.bio4j.titan.model.enzyme.TitanEnzymeDBGraphImpl;
import com.bio4j.titan.model.enzyme.nodes.TitanEnzyme;
import com.ohnosequences.util.Executable;
import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

/**
 * Imports Expasy Enzyme DB into Bio4j
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public class ImportEnzymeDBTitan implements Executable {

	public static final String IDENTIFICATION_LINE_CODE = "ID";
	public static final String OFFICIAL_NAME_LINE_CODE = "DE";
	public static final String ALTERNATE_NAME_LINE_CODE = "AN";
	public static final String CATALYTIC_ACTIVITY_LINE_CODE = "CA";
	public static final String COMMENTS_LINE_CODE = "CC";
	public static final String COFACTORS_LINE_CODE = "CF";
	public static final String PROSITE_CROSS_REFERENCES_LINE_CODE = "PR";
	public static final String SWISS_PROT_CROSS_REFERENCES_LINE_CODE = "DR";
	public static final String TERMINATION_LINE_CODE = "//";
	private static final Logger logger = Logger.getLogger("ImportEnzymeDB");
	private static FileHandler fh;

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
			System.out.println("This program expects the following parameters: \n"
					+ "1. Enzyme DB data file (.dat) \n"
					+ "2. Bio4j DB folder \n");

		} else {

			long initTime = System.nanoTime();

			File inFile = new File(args[0]);

			BufferedWriter statsBuff = null;

			//----------DB configuration------------------
			Configuration conf = new BaseConfiguration();
			conf.setProperty("storage.directory", args[2]);
			conf.setProperty("storage.backend", "local");
			conf.setProperty("autotype", "none");

			//-------creating graph handlers---------------------
			TitanGraph g = TitanFactory.open(conf);
			TitanEnzymeDBGraphImpl graph = new TitanEnzymeDBGraphImpl(g);

			int enzymeCounter = 0;

			try {

				// This block configures the logger with handler and formatter
				fh = new FileHandler("ImportEnzymeDB.log", true);
				SimpleFormatter formatter = new SimpleFormatter();
				fh.setFormatter(formatter);
				logger.addHandler(fh);
				logger.setLevel(Level.ALL);

				//---creating writer for stats file-----
				statsBuff = new BufferedWriter(new FileWriter(new File("ImportEnzymeDBStats.txt")));

				BufferedReader reader = new BufferedReader(new FileReader(inFile));
				String line;

				boolean enzymeFound = false;
				String officialName = "";
				String enzymeId = "";
				String commentsSt = "";
				String catalyticActivity = "";
				//List<String> alternateNames = new LinkedList<>();
				List<String> cofactors = new LinkedList<>();
				List<String> prositeCrossRefs = new LinkedList<>();
				boolean deletedEntry = false;
				boolean transferredEntry = false;

				System.out.println("Reading file....");

				while ((line = reader.readLine()) != null) {
					if (line.startsWith(IDENTIFICATION_LINE_CODE)) {
						enzymeFound = true;
						enzymeId = line.substring(5).trim();


					} else if (enzymeFound) {

						if (line.startsWith(OFFICIAL_NAME_LINE_CODE)) {

							officialName += line.substring(5).trim();

							if (officialName.contains("Deleted entry.")) {
								deletedEntry = true;
							} else if (officialName.contains("Transferred entry:")) {
								transferredEntry = true;
							}

						}
//						else if (line.startsWith(ALTERNATE_NAME_LINE_CODE)) {
//
//							alternateNames.add(line.substring(5).trim());
//
//						}
						else if (line.startsWith(COFACTORS_LINE_CODE)) {

							String[] cofs = line.substring(5).trim().split(";");
							for (String cofactorSt : cofs) {
								cofactors.add(cofactorSt.trim());
							}

						} else if (line.startsWith(PROSITE_CROSS_REFERENCES_LINE_CODE)) {

							String[] proRefs = line.substring(5).trim().split(";");
							for (String prositeSt : proRefs) {
								if (!prositeSt.equals("PROSITE")) {
									prositeCrossRefs.add(prositeSt.trim());
								}
							}

						} else if (line.startsWith(COMMENTS_LINE_CODE)) {

							commentsSt += line.substring(5).trim() + " ";

						} else if (line.startsWith(CATALYTIC_ACTIVITY_LINE_CODE)) {

							catalyticActivity += line.substring(5).trim() + " ";

						} else if (line.startsWith(TERMINATION_LINE_CODE)) {
							if (enzymeFound) {

								if (deletedEntry) {
									logger.log(Level.INFO, ("Entry with id " + enzymeId + " was deleted. It won't be stored..."));
									deletedEntry = false;
								} else if (transferredEntry) {
									logger.log(Level.INFO, ("Entry with id " + enzymeId + " was transferred. It won't be stored..."));
									transferredEntry = false;
								}

								TitanEnzyme enzyme = graph.enzymeT.from(graph.rawGraph().addVertex(null));

								enzyme.set(graph.enzymeT.id, enzymeId);
								enzyme.set(graph.enzymeT.officialName, officialName);
								enzyme.set(graph.enzymeT.cofactors, cofactors.toArray(new String[0]));
								enzyme.set(graph.enzymeT.prositeCrossReferences, prositeCrossRefs.toArray(new String[0]));
								enzyme.set(graph.enzymeT.catalyticActivity, catalyticActivity);
								enzyme.set(graph.enzymeT.comment, commentsSt);

								enzymeCounter++;
								if (enzymeCounter % 100 == 0) {
									System.out.println(enzymeCounter + " enzymes inserted...");
								}

							}
							enzymeFound = false;
							officialName = "";
							enzymeId = "";
							commentsSt = "";
							catalyticActivity = "";
//							alternateNames.clear();
							cofactors.clear();
							prositeCrossRefs.clear();
						}

					}

				}

				reader.close();


			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
				StackTraceElement[] trace = e.getStackTrace();
				for (StackTraceElement stackTraceElement : trace) {
					logger.log(Level.SEVERE, stackTraceElement.toString());
				}
			} finally {

				try {
					//closing logger file handler
					fh.close();
					logger.log(Level.INFO, "Closing up graph service....");
					// shutdown, makes sure all changes are written to disk
					g.shutdown();

					//-----------------writing stats file---------------------
					long elapsedTime = System.nanoTime() - initTime;
					long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
					long hours = elapsedSeconds/3600;
					long minutes = (elapsedSeconds % 3600)/60;
					long seconds = (elapsedSeconds % 3600)%60;

					statsBuff.write("Statistics for program ImportEnzymeDB:\nInput file: " + inFile.getName()
							+ "\nThere were " + enzymeCounter + " enzymes inserted.\n" +
							"The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

					//---closing stats writer---
					statsBuff.close();


				} catch (Exception e) {
					logger.log(Level.SEVERE, e.getMessage());
					StackTraceElement[] trace = e.getStackTrace();
					for (StackTraceElement stackTraceElement : trace) {
						logger.log(Level.SEVERE, stackTraceElement.toString());
					}
				}

			}

		}
	}
}
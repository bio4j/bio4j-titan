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
  * along with this program.  If not, see <http:www.gnu.org/licenses/>
  */
package com.bio4j.titan.programs;

import com.bio4j.titan.model.go.TitanGoGraphImpl;
import com.bio4j.titan.model.go.nodes.TitanGoTerm;
import com.era7.bioinfo.bioinfoutil.Executable;
import com.era7.era7xmlapi.model.XMLElement;
import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Vertex;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.jdom2.Element;

import java.io.*;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
* Imports the Gene Ontology into Bio4j
*
* @author Pablo Pareja Tobes <ppareja@era7.com>
*/
public class ImportGeneOntologyTitan implements Executable {

     public static final String TERM_TAG_NAME = "term";
     public static final String ID_TAG_NAME = "id";
     public static final String NAME_TAG_NAME = "name";
     public static final String DEF_TAG_NAME = "def";
     public static final String DEFSTR_TAG_NAME = "defstr";
     public static final String IS_ROOT_TAG_NAME = "is_root";
     public static final String IS_OBSOLETE_TAG_NAME = "is_obsolete";
     public static final String COMMENT_TAG_NAME = "comment";
     public static final String NAMESPACE_TAG_NAME = "namespace";
     public static final String RELATIONSHIP_TAG_NAME = "relationship";

     public static final String REGULATES_OBOXML_RELATIONSHIP_NAME = "regulates";
     public static final String POSITIVELY_REGULATES_OBOXML_RELATIONSHIP_NAME = "positively_regulates";
     public static final String NEGATIVELY_REGULATES_OBOXML_RELATIONSHIP_NAME = "negatively_regulates";
     public static final String PART_OF_OBOXML_RELATIONSHIP_NAME = "part_of";
     public static final String HAS_PART_OF_OBOXML_RELATIONSHIP_NAME = "has_part";
	 public static final String IS_A_OBOXML_RELATIONSHIP_NAME = "is_a";

     private static final Logger logger = Logger.getLogger("ImportGeneOntologyBP");
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
                     + "1. Gene ontology xml filename \n"
                     + "2. Bio4j DB folder \n");

         } else {

             long initTime = System.nanoTime();

             File inFile = new File(args[0]);

             //----------DB configuration------------------
             Configuration conf = new BaseConfiguration();
             conf.setProperty("storage.directory", args[1]);
             conf.setProperty("storage.backend", "local");
	         conf.setProperty("autotype", "none");

             //-------creating graph handlers---------------------
	         TitanGraph g = TitanFactory.open(conf);
	         TitanGoGraphImpl graph = new TitanGoGraphImpl(g);


             BufferedWriter statsBuff = null;

             int termCounter = 0;
             int limitForPrintingOut = 10000;

             try {

                 //This block configures the logger with handler and formatter
                 fh = new FileHandler("ImportGeneOntologyTitan.log", true);
                 SimpleFormatter formatter = new SimpleFormatter();
                 fh.setFormatter(formatter);
                 logger.addHandler(fh);
                 logger.setLevel(Level.ALL);

                 //---creating writer for stats file-----
                 statsBuff = new BufferedWriter(new FileWriter(new File("ImportGeneOntologyBPStats.txt")));

                 Map<String, ArrayList<String>> termParentsMap = new HashMap<>();
                 Map<String, ArrayList<String>> regulatesMap = new HashMap<>();
                 Map<String, ArrayList<String>> negativelyRegulatesMap = new HashMap<>();
                 Map<String, ArrayList<String>> positivelyRegulatesMap = new HashMap<>();
                 Map<String, ArrayList<String>> partOfMap = new HashMap<>();
                 Map<String, ArrayList<String>> hasPartMap = new HashMap<>();


                 BufferedReader reader = new BufferedReader(new FileReader(inFile));
                 String line;
                 StringBuilder termStBuilder = new StringBuilder();

                 logger.log(Level.INFO, "inserting nodes....");

                 //-----first I create all the elements whitout their relationships-------------

                 while ((line = reader.readLine()) != null) {
                     if (line.trim().startsWith("<" + TERM_TAG_NAME)) {

                         while (!line.trim().startsWith("</" + TERM_TAG_NAME + ">")) {
                             termStBuilder.append(line);
                             line = reader.readLine();
                         }
                         //organism final line
                         termStBuilder.append(line);
                         XMLElement termXMLElement = new XMLElement(termStBuilder.toString());
                         termStBuilder.delete(0, termStBuilder.length());

                         String goId = termXMLElement.asJDomElement().getChildText(ID_TAG_NAME);
                         String goName = termXMLElement.asJDomElement().getChildText(NAME_TAG_NAME);
                         if (goName == null) {
                             goName = "";
                         }
                         String goNamespace = termXMLElement.asJDomElement().getChildText(NAMESPACE_TAG_NAME);
                         if (goNamespace == null) {
                             goNamespace = "";
                         }
                         String goDefinition = "";
                         Element defElem = termXMLElement.asJDomElement().getChild(DEF_TAG_NAME);
                         if (defElem != null) {
                             Element defstrElem = defElem.getChild(DEFSTR_TAG_NAME);
                             if (defstrElem != null) {
                                 goDefinition = defstrElem.getText();
                             }
                         }
                         String goComment = termXMLElement.asJDomElement().getChildText(COMMENT_TAG_NAME);
                         if (goComment == null) {
                             goComment = "";
                         }
                         String goIsObsolete = termXMLElement.asJDomElement().getChildText(IS_OBSOLETE_TAG_NAME);
                         if (goIsObsolete == null) {
                             goIsObsolete = "";
                         } else {
                             if (goIsObsolete.equals("1")) {
                                 goIsObsolete = "true";
                             } else {
                                 goIsObsolete = "false";
                             }
                         }


                         List<Element> altIdElems = termXMLElement.asJDomElement().getChildren("alt_id");
                         String[] alternativeIds = new String[altIdElems.size()];
                         for (int i = 0; i < altIdElems.size(); i++) {
                             alternativeIds[i] = altIdElems.get(i).getText();
                         }


                         //----term parents----
                         List<Element> termParentTerms = termXMLElement.asJDomElement().getChildren(IS_A_OBOXML_RELATIONSHIP_NAME);
                         ArrayList<String> array = new ArrayList<>();
                         for (Element elem : termParentTerms) {
                             array.add(elem.getText().trim());
                         }
                         termParentsMap.put(goId, array);
                         //---------------------

                         //-------relationship tags-----------
                         List<Element> relationshipTags = termXMLElement.asJDomElement().getChildren(RELATIONSHIP_TAG_NAME);

                         for (Element relationshipTag : relationshipTags) {

                             String relType = relationshipTag.getChildText("type");
                             String toSt = relationshipTag.getChildText("to");

                             if (relType.equals(REGULATES_OBOXML_RELATIONSHIP_NAME)) {

                                 ArrayList<String> tempArray = regulatesMap.get(goId);
                                 if (tempArray == null) {
                                     tempArray = new ArrayList<>();
                                     regulatesMap.put(goId, tempArray);
                                 }
                                 tempArray.add(toSt);

                             } else if (relType.equals(POSITIVELY_REGULATES_OBOXML_RELATIONSHIP_NAME)) {

                                 ArrayList<String> tempArray = positivelyRegulatesMap.get(goId);
                                 if (tempArray == null) {
                                     tempArray = new ArrayList<>();
                                     positivelyRegulatesMap.put(goId, tempArray);
                                 }
                                 tempArray.add(toSt);

                             } else if (relType.equals(NEGATIVELY_REGULATES_OBOXML_RELATIONSHIP_NAME)) {

                                 ArrayList<String> tempArray = negativelyRegulatesMap.get(goId);
                                 if (tempArray == null) {
                                     tempArray = new ArrayList<>();
                                     negativelyRegulatesMap.put(goId, tempArray);
                                 }
                                 tempArray.add(toSt);

                             } else if (relType.equals(PART_OF_OBOXML_RELATIONSHIP_NAME)) {

                                 ArrayList<String> tempArray = partOfMap.get(goId);
                                 if (tempArray == null) {
                                     tempArray = new ArrayList<>();
                                     partOfMap.put(goId, tempArray);
                                 }
                                 tempArray.add(toSt);

                             } else if (relType.equals(HAS_PART_OF_OBOXML_RELATIONSHIP_NAME)) {

                                 ArrayList<String> tempArray = hasPartMap.get(goId);
                                 if (tempArray == null) {
                                     tempArray = new ArrayList<>();
                                     hasPartMap.put(goId, tempArray);
                                 }
                                 tempArray.add(toSt);

                             }
                         }
                         //-------------------------------------

                         Vertex goTermVertex = graph.rawGraph().addVertex(null);

                         goTermVertex.setProperty(graph.goTermT.id.fullName(), goId);
                         goTermVertex.setProperty(graph.goTermT.name.fullName(), goName);
                         goTermVertex.setProperty(graph.goTermT.definition.fullName(), goDefinition);
                         goTermVertex.setProperty(graph.goTermT.obsolete.fullName(), goIsObsolete);
                         goTermVertex.setProperty(graph.goTermT.comment.fullName(), goComment);
                         //----------------------

	                     // TODO see if this has to be implemented in the new version
                         //----alternative IDs----
//                         TitanVertex goTitanVertex = (TitanVertex) goTermVertex;
//                         for (String string : alternativeIds) {
//                             goTitanVertex.addProperty(GoTerm.ALTERNATIVE_IDS_PROPERTY, string);
//                         }

                     }
                     termCounter++;
                     if ((termCounter % limitForPrintingOut) == 0) {
                         logger.log(Level.INFO, (termCounter + " terms inserted!!"));
                     }
                 }
                 reader.close();

	             //----committing transaction---
	             graph.rawGraph().commit();

                 //-----------------------------------------------------------------------

                 logger.log(Level.INFO, "Inserting relationships....");

                 logger.log(Level.INFO, "'is_a' relationships....");

                 //-------------------'is_a' relationships-----------------
                 Set<String> keys = termParentsMap.keySet();
                 for (String key : keys) {

	                 TitanGoTerm tempGoTerm = graph.goTermIdIndex.getNode(key);
	                 //System.out.println("id: " + tempGoTerm.id() + " name: " + tempGoTerm.name());
                     ArrayList<String> tempArray = termParentsMap.get(key);

                     for (String string : tempArray) {
	                     //System.out.println("string: " + string);
	                     TitanGoTerm tempGoTerm2 = graph.goTermIdIndex.getNode(string);
	                     //System.out.println("id2: " + tempGoTerm2.id() + " name2: " + tempGoTerm2.name());
	                     tempGoTerm.addOut(graph.isAT, tempGoTerm2);
                     }
                 }

                 logger.log(Level.INFO, "'regulates' relationships....");
                 //-------------------'regulates' relationships----------------------
                 keys = regulatesMap.keySet();
                 for (String key : keys) {
	                 TitanGoTerm tempGoTerm = graph.goTermIdIndex.getNode(key);
                     ArrayList<String> tempArray = regulatesMap.get(key);
                     for (String string : tempArray) {
	                     TitanGoTerm tempGoTerm2 = graph.goTermIdIndex.getNode(string);
	                     tempGoTerm.addOut(graph.regulatesT, tempGoTerm2);
                     }
                 }

                 logger.log(Level.INFO, "'negatively_regulates' relationships....");
                 //-------------------'negatively regulates' relationships----------------------
                 keys = negativelyRegulatesMap.keySet();
                 for (String key : keys) {
	                 TitanGoTerm tempGoTerm = graph.goTermIdIndex.getNode(key);
                     ArrayList<String> tempArray = negativelyRegulatesMap.get(key);
                     for (String string : tempArray) {
	                     TitanGoTerm tempGoTerm2 = graph.goTermIdIndex.getNode(string);
	                     tempGoTerm.addOut(graph.negativelyRegulatesT, tempGoTerm2);
                     }
                 }

                 logger.log(Level.INFO, "'positively_regulates' relationships....");
                 //-------------------'positively regulates' relationships----------------------
                 keys = positivelyRegulatesMap.keySet();
                 for (String key : keys) {
	                 TitanGoTerm tempGoTerm =  graph.goTermIdIndex.getNode(key);
                     ArrayList<String> tempArray = positivelyRegulatesMap.get(key);
                     for (String string : tempArray) {
	                     TitanGoTerm tempGoTerm2 =  graph.goTermIdIndex.getNode(string);
	                     tempGoTerm.addOut(graph.positivelyRegulatesT, tempGoTerm2);
                     }
                 }

                 logger.log(Level.INFO, "'part_of' relationships....");
                 //-------------------'parf of' relationships----------------------
                 keys = partOfMap.keySet();
                 for (String key : keys) {
	                 TitanGoTerm tempGoTerm = graph.goTermIdIndex.getNode(key);
                     ArrayList<String> tempArray = partOfMap.get(key);
                     for (String string : tempArray) {
	                     TitanGoTerm tempGoTerm2 = graph.goTermIdIndex.getNode(string);
	                     tempGoTerm.addOut(graph.partOfT, tempGoTerm2);
                     }
                 }

                 logger.log(Level.INFO, "'has_part_of' relationships....");
                 //-------------------'has part of' relationships----------------------
                 keys = hasPartMap.keySet();
                 for (String key : keys) {
                	 TitanGoTerm tempGoTerm = graph.goTermIdIndex.getNode(key);
                     ArrayList<String> tempArray = hasPartMap.get(key);
                     for (String string : tempArray) {
	                     TitanGoTerm tempGoTerm2 = graph.goTermIdIndex.getNode(string);
	                     tempGoTerm.addOut(graph.hasPartOfT, tempGoTerm2);
                     }
                 }

                 logger.log(Level.INFO, "Done! :)");


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
                     logger.log(Level.INFO, "Closing up manager....");
                     //shutdown, makes sure all changes are written to disk
                     graph.rawGraph().shutdown();

                     //-----------------writing stats file---------------------
                     long elapsedTime = System.nanoTime() - initTime;
                     long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
                     long hours = elapsedSeconds / 3600;
                     long minutes = (elapsedSeconds % 3600) / 60;
                     long seconds = (elapsedSeconds % 3600) % 60;

                     statsBuff.write("Statistics for program ImportGeneOntology:\nInput file: " + inFile.getName()
                             + "\nThere were " + termCounter + " terms inserted.\n"
                             + "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

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

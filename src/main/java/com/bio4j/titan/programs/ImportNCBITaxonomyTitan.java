package com.bio4j.titan.programs;

import java.io.*;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.bio4j.titan.model.ncbiTaxonomy.TitanNCBITaxonomyGraphImpl;
import com.bio4j.titan.model.ncbiTaxonomy.nodes.TitanNCBITaxon;
import com.bio4j.titan.model.uniprot.TitanUniprotGraphImpl;
import com.ohnosequences.util.Executable;
import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;


/**
* @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
*/
public class ImportNCBITaxonomyTitan implements Executable {

     private static final Logger logger = Logger.getLogger("ImportNCBITaxonomyTitan");
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

         if (args.length != 4) {
             System.out.println("This program expects the following parameters: \n"
                     + "1. Nodes DMP filename \n"
                     + "2. Names DMP filename \n"
                     + "3. Merged DMP filename \n"
                     + "4. Bio4j DB folder \n");
         } else {

             long initTime = System.nanoTime();

             logger.log(Level.INFO, "creating manager...");

	         //----------DB configuration------------------
	         Configuration conf = new BaseConfiguration();
	         conf.setProperty("storage.directory", args[1]);
	         conf.setProperty("storage.backend", "local");
	         conf.setProperty("autotype", "none");

	         //-------creating graph handlers---------------------
	         TitanGraph g = TitanFactory.open(conf);
	         TitanNCBITaxonomyGraphImpl ncbiTaxonomyGraph = new TitanNCBITaxonomyGraphImpl(g);

             int taxonCounter = 0;
             int limitForTransaction = 1000;

             BufferedWriter statsBuff = null;

             File nodesDumpFile = new File(args[0]);
             File namesDumpFile = new File(args[1]);
             File mergedDumpFile = new File(args[2]);


             try {

                 // This block configure the logger with handler and formatter
                 fh = new FileHandler("ImportNCBITaxonomyTitan.log", true);
                 SimpleFormatter formatter = new SimpleFormatter();
                 fh.setFormatter(formatter);
                 logger.addHandler(fh);
                 logger.setLevel(Level.ALL);

                 //---creating writer for stats file-----
                 statsBuff = new BufferedWriter(new FileWriter(new File("ImportNCBITaxonomyTitanStats.txt")));

                 BufferedReader reader = new BufferedReader(new FileReader(nodesDumpFile));
                 String line;


                 HashMap<String, String> nodeParentMap = new HashMap<>();

                 logger.log(Level.INFO, "reading nodes file...");

                 while ((line = reader.readLine()) != null) {
                     if (line.trim().length() > 0) {
                         String[] columns = line.split("\\|");

	                     String id = columns[0].trim();
	                     TitanNCBITaxon taxon = ncbiTaxonomyGraph.nCBITaxonT.from(ncbiTaxonomyGraph.rawGraph().addVertex(null));
	                     taxon.set(ncbiTaxonomyGraph.nCBITaxonT.id, id);
	                     taxon.set(ncbiTaxonomyGraph.nCBITaxonT.taxonomicRank, columns[2].trim());
	                     //taxon.set(ncbiTaxonomyGraph.nCBITaxonT., columns[2].trim());
                         //node.setEmblCode(columns[3].trim()); TODO add emblCode??

	                     //saving the parent of the node for later
	                     nodeParentMap.put(id, columns[1].trim());

                         taxonCounter++;

                         if((taxonCounter % limitForTransaction) == 0){
	                         ncbiTaxonomyGraph.rawGraph().commit();
                         }
                     }
                 }
                 reader.close();
	             ncbiTaxonomyGraph.rawGraph().commit();
                 logger.log(Level.INFO, "done!");

                 logger.log(Level.INFO, "reading names file...");
                 //------------reading names file-----------------
                 reader = new BufferedReader(new FileReader(namesDumpFile));
                 int linesCounter = 0;
                 while ((line = reader.readLine()) != null) {

                     String[] columns = line.split("\\|");

                     if (columns[columns.length - 1].trim().equals("scientific name")) {

                         String taxId = columns[0].trim();
                         String scientificNameSt = columns[1].trim();

	                     Optional<TitanNCBITaxon> optionalTaxon = ncbiTaxonomyGraph.nCBITaxonIdIndex.getNode(taxId);
	                     if(optionalTaxon.isPresent()){
		                     TitanNCBITaxon taxon = optionalTaxon.get();
		                     taxon.set(ncbiTaxonomyGraph.nCBITaxonT.scientificName, scientificNameSt);
	                     }

                         linesCounter++;
                         if((linesCounter % limitForTransaction) == 0){
	                         ncbiTaxonomyGraph.rawGraph().commit();
                         }

                     }

                 }
                 reader.close();
	             ncbiTaxonomyGraph.rawGraph().commit();
                 logger.log(Level.INFO, "done!");

                 logger.log(Level.INFO, "storing relationships...");

                 linesCounter = 0;
                 Set<String> nodesSet = nodeParentMap.keySet();
                 for (String nodeTaxId : nodesSet) {

                     String parentTaxId = nodeParentMap.get(nodeTaxId);

	                 TitanNCBITaxon taxon = ncbiTaxonomyGraph.nCBITaxonIdIndex.getNode(nodeTaxId).get();

                     if (!nodeTaxId.equals(parentTaxId)) {
	                     TitanNCBITaxon parentTaxon = ncbiTaxonomyGraph.nCBITaxonIdIndex.getNode(parentTaxId).get();
	                     parentTaxon.addOut(ncbiTaxonomyGraph.nCBITaxonParentT, taxon);
                     }

                     linesCounter++;
                     if((linesCounter % limitForTransaction) == 0){
                         ncbiTaxonomyGraph.rawGraph().commit();
                     }

                 }
	             ncbiTaxonomyGraph.rawGraph().commit();

                 logger.log(Level.INFO, "Done!");

//                 logger.log(Level.INFO, "reading merged file...");
//                 //------------reading merged file-----------------
//                 reader = new BufferedReader(new FileReader(mergedDumpFile));
//                 linesCounter = 0;
//                 while ((line = reader.readLine()) != null) {
//
//                     String[] columns = line.split("\\|");
//
//                     String oldId = columns[0].trim();
//                     String goodId = columns[1].trim();
//
//                     NCBITaxonNode goodNode = nodeRetriever.getNCBITaxonByTaxId(goodId);
//                     if (goodNode != null) {
//                         goodNode.addOldTaxId(oldId);
//
//                         linesCounter++;
//                         if((linesCounter % limitForTransaction) == 0){
//                             graph.commit();
//                         }
//
//                     } else {
//                         logger.log(Level.WARNING, "Taxon ID " + goodId +
//                                    " is not found. Old ID " + oldId + " is not mapped to it.");
//                     }
//
//                 }
//                 reader.close();
//                 graph.commit();
//
//                 logger.log(Level.INFO, "done!");

             } catch (Exception ex) {
                 Logger.getLogger(ImportNCBITaxonomyTitan.class.getName()).log(Level.SEVERE, null, ex);

             } finally {

             	//committing last transaction
             	ncbiTaxonomyGraph.rawGraph().commit();
                 //closing logger file handler
                 fh.close();
                 logger.log(Level.INFO, "Closing up inserter and index service....");
                 // shutdown, makes sure all changes are written to disk
	             ncbiTaxonomyGraph.rawGraph().shutdown();

                 try {

                     //-----------------writing stats file---------------------
                     long elapsedTime = System.nanoTime() - initTime;
                     long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
                     long hours = elapsedSeconds / 3600;
                     long minutes = (elapsedSeconds % 3600) / 60;
                     long seconds = (elapsedSeconds % 3600) % 60;

                     statsBuff.write("Statistics for program ImportNCBITaxonomy:\nInput file: " + nodesDumpFile.getName()
                             + "\nThere were " + taxonCounter + " taxonomic units inserted.\n"
                             + "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

                     //---closing stats writer---
                     statsBuff.close();

                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }

         }
     }

//     private static void associateTaxonomy(Bio4jManager manager,
//             NodeRetrieverTitan nodeRetriever,
//             TitanGraph graph) {
//
//
//         Iterator<Vertex> organismIterator = manager.getGraph().getVertices(OrganismNode.NODE_TYPE_PROPERTY, OrganismNode.NODE_TYPE).iterator();
//
//         while (organismIterator.hasNext()) {
//             OrganismNode organismNode = new OrganismNode(organismIterator.next());
//             Vertex ncbiNode = nodeRetriever.getNCBITaxonByTaxId(organismNode.getNcbiTaxonomyId()).getNode();
//             graph.addEdge(null, organismNode.getNode(), ncbiNode, NCBITaxonRel.NAME);
//         }
//     }
}
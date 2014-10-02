package com.bio4j.titan.samples;

import com.bio4j.model.ncbiTaxonomy.nodes.NCBITaxon;
import com.bio4j.model.uniprot.nodes.GeneLocation;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.relationships.ProteinGeneLocation;
import com.bio4j.model.uniref.nodes.UniRef100Cluster;
import com.bio4j.titan.model.ncbiTaxonomy.TitanNCBITaxonomyGraph;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot_ncbiTaxonomy.TitanUniprotNCBITaxonomyGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import java.io.*;
import java.util.*;

/**
 * Created by ppareja on 9/24/2014.
 *
 * This program gets the Interpro motifs which are unique for an specific child of the NCBI taxon provided
 */
public class TaxaRelationshipsThroughUniRef {

	public static final String HEADER = "NCBITaxon1 ID\tNCBITaxon1 name\tNCBITaxon2 ID\tNCBITaxon2 name\tNumber of protein pairs from both taxa shared in UniRef clusters";
	public static final String EXTENDED_HEADER = "NCBITaxon1 ID\tNCBITaxon1 name\tProtein1 ID\tGeneLocation 1\tGeneLocation1 name\tNCBITaxon2 ID\tNCBITaxon2 name\tProtein2 ID\tGeneLocation2\tGeneLocation2 name";

	public static void main(String[] args){
		if(args.length != 4){
			System.out.println("This program expects the following arguments: \n" +
					"1. Bio4j folder \n" +
					"2. TXT file including NCBI taxon IDs\n" +
					"3. Output file name\n" +
					"4. Output mode (simple/extended)");
		}else{


			String dbFolder = args[0];
			String ncbiTaxonIdsFileSt = args[1];
			String outFileSt = args[2];
			String outputMode = args[3];

			try{

				List<String> ncbiTaxonIds = new LinkedList<>();

				File ncbiTaxonFile = new File(ncbiTaxonIdsFileSt);
				File outFile = new File(outFileSt);

				System.out.println("Reading NCBI taxon IDs....");
				BufferedReader reader = new BufferedReader(new FileReader(ncbiTaxonFile));
				String line;
				while((line = reader.readLine()) != null){
					ncbiTaxonIds.add(line.trim());
				}
				reader.close();

				BufferedWriter outBuff = new BufferedWriter(new FileWriter(outFile));
				if(outputMode.equals("simple")){
					outBuff.write(HEADER + "\n");
				}else{
					outBuff.write(EXTENDED_HEADER + "\n");
				}


				//----------DB configuration------------------
				Configuration conf = new BaseConfiguration();
				conf.setProperty("storage.directory", dbFolder);
				conf.setProperty("storage.backend", "local");
				conf.setProperty("autotype", "none");
				//-------creating graph handlers---------------------
				TitanGraph graph = TitanFactory.open(conf);
				DefaultTitanGraph defGraph = new DefaultTitanGraph(graph);

				System.out.println("Creating the graph manager....");
				TitanUniprotNCBITaxonomyGraph uniprotNCBITaxonomyGraph = new TitanUniprotNCBITaxonomyGraph(defGraph, new TitanUniprotGraph(defGraph), new TitanNCBITaxonomyGraph(defGraph));


				for(String ncbiTaxonId : ncbiTaxonIds){

					Optional<NCBITaxon<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>> optionalTaxon = uniprotNCBITaxonomyGraph.ncbiTaxonomyGraph().nCBITaxonIdIndex().getVertex(ncbiTaxonId);

					if(optionalTaxon.isPresent()){

						NCBITaxon<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> currentTaxon = optionalTaxon.get();
						List<Protein<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>> proteins = currentTaxon.proteinNCBITaxon_inV();

						for(Protein<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> protein1 : proteins){

							UniRef100Cluster<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> uniRef100Cluster1 =  protein1.uniref100Member_outNode();
							List<Protein<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>> proteins2 = uniRef100Cluster1.uniRef100Member_inNode();

							for(Protein<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> protein2 : proteins){

								NCBITaxon<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> currentTaxon2 = protein2.proteinNCBITaxon_outV();

								if(!currentTaxon.id().equals(currentTaxon2.id())){

									if(outputMode.equals("extended")){

										String geneLocation1Name, geneLocation2Name, geneLocationType1, geneLocationType2;
										List<ProteinGeneLocation<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>> geneLocations1 = protein1.proteinGeneLocation_out();
										if(geneLocations1.size() > 0){
											ProteinGeneLocation<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> proteinGeneLocation1 = geneLocations1.get(0);
											geneLocation1Name = proteinGeneLocation1.name();
											geneLocationType1 = proteinGeneLocation1.target().name();

										}else{
											geneLocation1Name = "";
											geneLocationType1 = "";
										}
										List<ProteinGeneLocation<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>> geneLocations2 = protein1.proteinGeneLocation_out();
										if(geneLocations2.size() > 0){
											ProteinGeneLocation<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> proteinGeneLocation2 = geneLocations2.get(0);
											geneLocation2Name = proteinGeneLocation2.name();
											geneLocationType2 = proteinGeneLocation2.target().name();

										}else{
											geneLocation2Name = "";
											geneLocationType2 = "";
										}


										outBuff.write(currentTaxon.id() + "\t" + currentTaxon.name() + "\t" +
														protein1.accession() + "\t" + geneLocationType1 + "\t" + geneLocation1Name + "\t" +
														currentTaxon2.id() + "\t" + currentTaxon2.name() + "\t" +
														protein2.accession() + "\t" + geneLocationType2 + "\t" + geneLocation2Name + "\n");

									}else{

									}
								}
							}



						}

					}else{
						throw new Exception("The ID provided: " + ncbiTaxonId + "  was not found... :( \nExiting the program....");
					}
				}


				System.out.println("Closing output file...");
				outBuff.close();

				System.out.println("Closing the manager....");
				uniprotNCBITaxonomyGraph.raw().shutdown();

			}catch(Exception e){
				e.printStackTrace();
			}



		}
	}
}

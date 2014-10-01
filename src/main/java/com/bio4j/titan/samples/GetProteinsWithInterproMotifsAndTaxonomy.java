package com.bio4j.titan.samples;

import com.bio4j.model.ncbiTaxonomy.nodes.NCBITaxon;
import com.bio4j.model.uniprot.nodes.Interpro;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot_ncbiTaxonomy.UniprotNCBITaxonomyGraph;
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
public class GetProteinsWithInterproMotifsAndTaxonomy {

	public static final String HEADER = "Protein accession\tProtein name";

	public static void main(String[] args){
		if(args.length != 4){
			System.out.println("This program expects the following arguments: \n" +
					"1. Bio4j folder \n" +
					"2. TXT file including Interpro motifs \n" +
					"3. TXT file including NCBI taxon IDs\n" +
					"4. Output file name");
		}else{


			String dbFolder = args[0];
			String interproIdsFileSt = args[1];
			String ncbiTaxonIdsFileSt = args[2];
			String outFileSt = args[3];

			try{

				List<String> interproIds = new LinkedList<>();
				List<String> ncbiTaxonIds = new LinkedList<>();
				List<String> proteinsFulfillingInterpro = new LinkedList<>();
				List<String> finalListOfProteins = new LinkedList<>();

				File interproFile = new File(interproIdsFileSt);
				File ncbiTaxonFile = new File(ncbiTaxonIdsFileSt);
				File outFile = new File(outFileSt);


				System.out.println("Reading interpro IDs....");
				BufferedReader reader = new BufferedReader(new FileReader(interproFile));
				String line;
				while((line = reader.readLine()) != null){
					interproIds.add(line.trim());
				}
				reader.close();

				System.out.println("Reading NCBI taxon IDs....");
				reader = new BufferedReader(new FileReader(ncbiTaxonFile));
				while((line = reader.readLine()) != null){
					ncbiTaxonIds.add(line.trim());
				}
				reader.close();

				BufferedWriter outBuff = new BufferedWriter(new FileWriter(outFile));
				outBuff.write(HEADER + "\n");

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

				boolean firstInterpro = true;

				for (String interproId : interproIds){
					Optional<Interpro<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>> interproOptional = uniprotNCBITaxonomyGraph.uniprotGraph().interproIdIndex().getVertex(interproId);
					if(interproOptional.isPresent()){
						Interpro<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> interpro = interproOptional.get();
						List<Protein<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>> proteins = interpro.proteinInterpro_inNodes();
						if(firstInterpro){
							firstInterpro = false;
							for (Protein<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> protein : proteins){
								proteinsFulfillingInterpro.add(protein.accession());
							}
						}

						List<String> tempProteins = new LinkedList<>();
						for (Protein<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> protein : proteins){
							tempProteins.add(protein.accession());
						}
						for (String tempProteinId : tempProteins){
							if(!proteinsFulfillingInterpro.contains(tempProteinId)){
								tempProteins.remove(tempProteinId);
								break;
							}
						}

					}else{
						throw new Exception("The interpro ID provided: " + interproId + " does not exist... Finishing the program... :(");
					}
				}

				//Here we already have the set of proteins that fulfill the interpro links provided
				//Now it's time to check their taxonomy

				System.out.println("Checking up proteins taxonomy...");
				for (String proteinId : proteinsFulfillingInterpro){
					Protein<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> protein = uniprotNCBITaxonomyGraph.uniprotGraph().proteinAccessionIndex().getVertex(proteinId).get();
					NCBITaxon<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> taxon = protein.proteinNCBITaxon_outV();
					if(ncbiTaxonIds.contains(taxon.id())){
						finalListOfProteins.add(proteinId);
					}else{
						while(taxon.ncbiTaxonParent_outV() != null){
							taxon = taxon.ncbiTaxonParent_outV();
							if(ncbiTaxonIds.contains(taxon.id())){
								finalListOfProteins.add(proteinId);
								break;
							}
						}
					}
				}

				System.out.println("Writing output file....");

				for(String proteinId : finalListOfProteins){
					outBuff.write(proteinId + "\n");
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

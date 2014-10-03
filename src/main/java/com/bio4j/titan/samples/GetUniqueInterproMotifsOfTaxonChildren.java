package com.bio4j.titan.samples;

import com.bio4j.model.ncbiTaxonomy.vertices.NCBITaxon;
import com.bio4j.model.uniprot.vertices.Interpro;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.titan.model.ncbiTaxonomy.TitanNCBITaxonomyGraph;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot_ncbiTaxonomy.TitanUniprotNCBITaxonomyGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

/**
 * Created by ppareja on 9/24/2014.
 *
 * This program gets the Interpro motifs which are unique for an specific child of the NCBI taxon provided
 */
public class GetUniqueInterproMotifsOfTaxonChildren {

	public static final String HEADER = "NCBITaxon ID\tNCBITaxon name\tUnique Interpro motif ID\tUnique Interpro motif name";

	public static void main(String[] args){
		if(args.length != 3){
			System.out.println("This program expects the following arguments: \n" +
								"1. Bio4j folder \n" +
								"2. NCBI taxon ID \n" +
								"3. Output file name");
		}else{


			String dbFolder = args[0];
			String ncbiTaxonId = args[1];
			String outFileSt = args[2];

			try{

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

				System.out.println("Retrieving NCBI taxon provided...");
				Optional<NCBITaxon<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>> ncbiTaxonOptional = uniprotNCBITaxonomyGraph.ncbiTaxonomyGraph().nCBITaxonIdIndex().getVertex(ncbiTaxonId);

				BufferedWriter outBuff = new BufferedWriter(new FileWriter(new File(outFileSt)));
				outBuff.write(HEADER + "\n");

				if(!ncbiTaxonOptional.isPresent()){

					System.out.println("There was no NCBI taxon found for the ID provided... :(");

				}else{

					NCBITaxon<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> ncbiTaxon = ncbiTaxonOptional.get();
					List<NCBITaxon<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>> taxonChildren = ncbiTaxon.ncbiTaxonParent_inV();
					Set<String> interproMotifs = new HashSet<>();
					Set<String> repeatedInterproMotifs = new HashSet<>();

					System.out.println("Retrieving shared motifs...");

					for (NCBITaxon<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> tempTaxon : taxonChildren){

						List<Protein<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>> proteinList = tempTaxon.proteinNCBITaxon_inV();
						for (Protein<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> tempProtein : proteinList){
							List<Interpro<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>> interproList = tempProtein.proteinInterpro_outV();
							for (Interpro<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> tempInterpro : interproList){
								String tempId = tempInterpro.id();
								if(interproMotifs.contains(tempId)){
									repeatedInterproMotifs.add(tempId);
								}
								interproMotifs.add(tempId);
							}
						}
					}

					System.out.println("Looking for motifs which are unique...");

					for (NCBITaxon<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> tempTaxon : taxonChildren){

						List<Protein<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>> proteinList = tempTaxon.proteinNCBITaxon_inV();

						for (Protein<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> tempProtein : proteinList){

							List<Interpro<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>> interproList = tempProtein.proteinInterpro_outV();
							for (Interpro<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> tempInterpro : interproList){
								String tempId = tempInterpro.id();
								if(!repeatedInterproMotifs.contains(tempId)){
									outBuff.write(tempTaxon.id() + "\t" + tempTaxon.scientificName() + "\t" + tempInterpro.id() + "\t" + tempInterpro.name() + "\n");
								}
							}
						}
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

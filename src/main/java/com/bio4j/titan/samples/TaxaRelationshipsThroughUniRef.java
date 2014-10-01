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
public class TaxaRelationshipsThroughUniRef {

	public static final String HEADER = "NCBITaxon1 ID\tNCBITaxon1 name\tNCBITaxon2 ID\tNCBITaxon2 name\tNumber of protein pairs from both taxa shared in UniRef clusters";
	public static final String EXTENDED_HEADER = "NCBITaxon1 ID\tNCBITaxon1 name\tProtein1 ID\tGeneLocation 1\tGeneLocation1 name\tNCBITaxon2 ID\tNCBITaxon2 name\tProtein2 ID\tGeneLocation2\tGeneLocation2 name"

	public static void main(String[] args){
		if(args.length != 3){
			System.out.println("This program expects the following arguments: \n" +
					"1. Bio4j folder \n" +
					"2. TXT file including NCBI taxon IDs\n" +
					"3. Output file name");
		}else{


			String dbFolder = args[0];
			String ncbiTaxonIdsFileSt = args[1];
			String outFileSt = args[2];

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

package com.bio4j.titan.samples;

import com.bio4j.model.enzymedb.nodes.Enzyme;
import com.bio4j.model.go.nodes.GoTerm;
import com.bio4j.model.ncbiTaxonomy.nodes.NCBITaxon;
import com.bio4j.model.uniprot.nodes.Interpro;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot_ncbiTaxonomy.UniprotNCBITaxonomyGraph;
import com.bio4j.model.uniref.nodes.UniRef100Cluster;
import com.bio4j.model.uniref.nodes.UniRef50Cluster;
import com.bio4j.model.uniref.nodes.UniRef90Cluster;
import com.bio4j.titan.model.go.TitanGoGraph;
import com.bio4j.titan.model.ncbiTaxonomy.TitanNCBITaxonomyGraph;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot_ncbiTaxonomy.TitanUniprotNCBITaxonomyGraph;
import com.bio4j.titan.model.uniprot_uniref.TitanUniprotUniRefGraph;
import com.bio4j.titan.model.uniref.TitanUniRefGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by ppareja on 9/24/2014.
 *
 * This program gets the Interpro motifs which are unique for an specific child of the NCBI taxon provided
 */
public class GOTermsSharedInUniRefCluster {

	public static final String HEADER = "GO Term ID\tGO Term name\tGO Term frequency";

	public static void main(String[] args){
		if(args.length != 4){
			System.out.println("This program expects the following arguments: \n" +
					"1. Bio4j folder \n" +
					"2. UniRef cluster ID \n" +
					"3. UniRef cluster (50/90/100) \n" +
					"4. Output file name");
		}else{


			String dbFolder = args[0];
			String uniRefClusterId = args[1];
			String uniRefCluster = args[2];
			String outFileSt = args[3];

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
				TitanUniprotUniRefGraph uniprotUniRefGraph = new TitanUniprotUniRefGraph(defGraph, new TitanUniprotGraph(defGraph), new TitanUniRefGraph(defGraph));

				BufferedWriter outBuff = new BufferedWriter(new FileWriter(new File(outFileSt)));
				outBuff.write(HEADER + "\n");

				if(uniRefCluster.equals("50")){

					Optional<UniRef50Cluster<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>> clusterOptional = uniprotUniRefGraph.uniRefGraph().uniRef50ClusterIdIndex().getVertex(uniRefClusterId);
					if(clusterOptional.isPresent()){

						UniRef50Cluster<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> cluster = clusterOptional.get();
						List<Protein<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>> proteins = cluster.uniRef50Member_inNode();
						Map<String, Integer> goMap = getGOTermFrequenciesForProteins(proteins);
						writeToOutputFile(goMap, new TitanGoGraph(defGraph), outBuff);

					}else{
						System.out.println("The ID provided could not be found... :(");
					}


				}else if(uniRefCluster.equals("90")){

					Optional<UniRef90Cluster<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>> clusterOptional = uniprotUniRefGraph.uniRefGraph().uniRef90ClusterIdIndex().getVertex(uniRefClusterId);
					if(clusterOptional.isPresent()){

						UniRef90Cluster<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> cluster = clusterOptional.get();
						List<Protein<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>> proteins = cluster.uniRef90Member_inNode();
						Map<String, Integer> goMap = getGOTermFrequenciesForProteins(proteins);
						writeToOutputFile(goMap, new TitanGoGraph(defGraph), outBuff);

					}else{
						System.out.println("The ID provided could not be found... :(");
					}

				}else if (uniRefCluster.equals("100")){

					Optional<UniRef100Cluster<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>> clusterOptional = uniprotUniRefGraph.uniRefGraph().uniRef100ClusterIdIndex().getVertex(uniRefClusterId);
					if(clusterOptional.isPresent()){

						UniRef100Cluster<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> cluster = clusterOptional.get();
						List<Protein<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>> proteins = cluster.uniRef100Member_inNode();
						Map<String, Integer> goMap = getGOTermFrequenciesForProteins(proteins);
						writeToOutputFile(goMap, new TitanGoGraph(defGraph), outBuff);

					}else{
						System.out.println("The ID provided could not be found... :(");
					}

				}else{
					System.out.println("Wrong value for parameter 'UniRef cluster' it should be one of the following values: 50, 90, 100");
				}

				System.out.println("Closing output file...");
				outBuff.close();

				System.out.println("Closing the manager....");
				uniprotUniRefGraph.raw().shutdown();

			}catch(Exception e){
				e.printStackTrace();
			}

		}
	}

	protected static void writeToOutputFile(Map<String, Integer> goMap, TitanGoGraph graph, BufferedWriter outBuff) throws IOException {
		Set<String> goIds = goMap.keySet();
		for(String goID : goIds){
			GoTerm<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> term = graph.goTermIdIndex().getVertex(goID).get();
			outBuff.write(goID + "\t" + term.name() + "\t" + goMap.get(goID) + "\n");
		}
	}

	protected static Map<String, Integer> getGOTermFrequenciesForProteins(
			List<Protein<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>> proteins){

		HashMap<String, Integer> goMap = new HashMap<>();

		for(Protein<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> protein : proteins){
			List<GoTerm<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>> terms = protein.goAnnotation_outNodes();
			for(GoTerm<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> term : terms){
				Integer value = goMap.get(term.id());
				if(value == null){
					goMap.put(term.id(), 0);
				}else{
					goMap.put(term.id(), (value + 1));
				}
			}

		}

		return goMap;
	}
}

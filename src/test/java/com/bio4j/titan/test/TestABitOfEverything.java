package com.bio4j.titan.test;

import com.bio4j.titan.model.go.TitanGoGraphImpl;
import com.bio4j.titan.model.go.nodes.TitanGoTerm;
import com.bio4j.titan.model.go.nodes.TitanSubOntologies;
import com.bio4j.titan.model.go.relationships.TitanIsA;
import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import java.util.Iterator;
import java.util.List;


/**
 * Created by ppareja on 6/16/2014.
 */
public final class TestABitOfEverything {

	public static void main(String[] args) {


		//----------DB configuration------------------
		Configuration conf = new BaseConfiguration();
		conf.setProperty("storage.directory", "bio4j");
		conf.setProperty("storage.backend", "local");
		conf.setProperty("autotype", "none");

		//-------creating graph handlers---------------------
		TitanGraph g = TitanFactory.open(conf);
		TitanGoGraphImpl graph = new TitanGoGraphImpl(g);

		TitanGoTerm tempGoTerm = graph.goTermIdIndex.getNode("GO:0000003").get();
		System.out.println("is A relationships: ");
		List<TitanIsA> list = tempGoTerm.isA_in();
		for(TitanIsA rel : list){
			System.out.println(rel.getLabel());
		}

		List<TitanGoTerm> listNodes = tempGoTerm.isA_inNodes();
		System.out.println("in IsA nodes");
		for(TitanGoTerm term : listNodes){
			System.out.println(term.id());
		}

		List<TitanGoTerm> listNodesOut = tempGoTerm.isA_outNodes();
		System.out.println("out IsA nodes");
		for(TitanGoTerm term : listNodesOut){
			System.out.println(term.id());
		}

		TitanSubOntologies titanSubOntologies = tempGoTerm.subOntology_outNodes();
		System.out.println(titanSubOntologies.name());

		System.out.println("term keys: ");

		Iterator<String> iterator = tempGoTerm.getPropertyKeys().iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}

		g.shutdown();


	}
}
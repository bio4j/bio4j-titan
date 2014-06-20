package com.bio4j.titan.test;

import com.bio4j.titan.model.go.TitanGoGraphImpl;
import com.bio4j.titan.model.go.nodes.TitanGoTerm;
import com.bio4j.titan.model.go.relationships.TitanIsA;
import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Vertex;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import java.util.Iterator;


/**
 * Created by ppareja on 6/16/2014. 
 */
public final class TestGetTypeName {

	public static void main(String[] args) {


		//----------DB configuration------------------
		Configuration conf = new BaseConfiguration();
		conf.setProperty("storage.directory", "bio4j");
		conf.setProperty("storage.backend", "local");
		conf.setProperty("autotype", "none");

		//-------creating graph handlers---------------------
		TitanGraph g = TitanFactory.open(conf);
		TitanGoGraphImpl graph = new TitanGoGraphImpl(g);

		TitanGoTerm tempGoTerm = graph.goTermIdIndex.getNode("GO:0019952");

		System.out.println("term keys: ");

		Iterator<String> iterator = tempGoTerm.getPropertyKeys().iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}

		g.shutdown();


	}
}

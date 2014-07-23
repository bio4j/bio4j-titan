package com.bio4j.titan.test;

import com.bio4j.titan.model.go.TitanGoGraphImpl;
import com.bio4j.titan.model.go.nodes.TitanGoTerm;
import com.bio4j.titan.model.go.relationships.TitanIsA;
import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Vertex;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;


/**
 * Created by ppareja on 6/16/2014. 
 */
public final class TestAddRel {

	public static void main(String[] args) {


		//----------DB configuration------------------
		Configuration conf = new BaseConfiguration();
		conf.setProperty("storage.directory", "hola");
		conf.setProperty("storage.backend", "local");
		conf.setProperty("autotype", "none");

		//-------creating graph handlers---------------------
		TitanGraph g = TitanFactory.open(conf);
		TitanGoGraphImpl graph = new TitanGoGraphImpl(g);

		g.commit();

		Vertex goTermVertex = graph.rawGraph().addVertex(null);
		goTermVertex.setProperty(graph.goTermT.id.fullName(), "1");

		Vertex goTermVertex2 = graph.rawGraph().addVertex(null);
		goTermVertex2.setProperty(graph.goTermT.id.fullName(), "2");

		g.commit();


		TitanGoTerm tempGoTerm = graph.goTermIdIndex.getNode("1").get();
		TitanGoTerm tempGoTerm2 = graph.goTermIdIndex.getNode("2").get();

		System.out.println("term 1: " + tempGoTerm.id());
		System.out.println("term 2: " + tempGoTerm2.id());

		TitanIsA tisa = tempGoTerm.addOut(graph.isAT, tempGoTerm2);
		System.out.println(tisa);
	}
}

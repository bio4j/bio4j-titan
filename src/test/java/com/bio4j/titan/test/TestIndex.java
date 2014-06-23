package com.bio4j.titan.test;

import com.bio4j.titan.model.go.TitanGoGraphImpl;
import com.thinkaurelius.titan.core.KeyMaker;
import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanKey;
import com.tinkerpop.blueprints.Compare;
import com.tinkerpop.blueprints.Vertex;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import java.util.Iterator;

/**
 * Created by ppareja on 6/23/2014.
 */
public class TestIndex {

	public static void main(String args[]){

		//----------DB configuration------------------
		Configuration conf = new BaseConfiguration();
		conf.setProperty("storage.directory", "lalala");
		conf.setProperty("storage.backend", "local");
		conf.setProperty("autotype", "none");

		//-------creating graph handlers---------------------
		TitanGraph g = TitanFactory.open(conf);
		TitanGoGraphImpl graph = new TitanGoGraphImpl(g);

		KeyMaker keyMaker = g.makeKey("name").dataType(String.class);
		TitanKey titanKey = keyMaker.indexed(com.tinkerpop.blueprints.Vertex.class).unique().make();

		Vertex vertex = g.addVertex(null);
		vertex.setProperty("name", "biological_process");

		g.commit();

		Iterator<Vertex> it1 = g.query().has("name", Compare.EQUAL,"biological_process").vertices().iterator();

		if(it1.hasNext()){
			Vertex v = it1.next();
			System.out.println("it1: " + v.getProperty("name").toString());
		}else{
			System.out.println("it1: " + "ooo.... :(");
		}

		Iterator<Vertex> it2 = g.getVertices("name","biological_process").iterator();

		if(it2.hasNext()){
			Vertex v = it2.next();
			System.out.println("it2: " + v.getProperty("name").toString());
		}else{
			System.out.println("it2: " + "ooo.... :(");
		}

		g.shutdown();

	}
}

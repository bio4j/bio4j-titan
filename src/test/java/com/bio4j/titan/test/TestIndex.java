package com.bio4j.titan.test;

import com.bio4j.titan.model.go.TitanGoGraphImpl;
import com.thinkaurelius.titan.core.KeyMaker;
import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanKey;
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
		KeyMaker keyMaker = g.makeKey("name").dataType(String.class);
		TitanKey titanKey = keyMaker.indexed(com.tinkerpop.blueprints.Vertex.class).unique().make();

		Vertex vertex = g.addVertex(null);
		vertex.setProperty("name", "biological_process");

		g.commit();

		Iterator<Vertex> it = g.getVertices("name","biological_process").iterator();

		if(it.hasNext()){
			Vertex v = it.next();
			System.out.println(v.getProperty("name").toString());
		}else{
			System.out.println("ooo.... :(");
		}

		g.shutdown();

	}
}

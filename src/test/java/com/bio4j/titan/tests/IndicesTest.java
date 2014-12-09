package com.bio4j.titan.tests;

import com.bio4j.model.uniprot.vertices.EMBL;
import com.bio4j.model.uniprot.vertices.FeatureType;
import com.bio4j.model.uniprot.vertices.RefSeq;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.*;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import java.util.Optional;

public class IndicesTest {

	public void indicesTest(String dbFolder){

		//----------DB configuration------------------
		Configuration conf = new BaseConfiguration();
		conf.setProperty("storage.directory", dbFolder);
		conf.setProperty("storage.backend", "berkeleyje");
		conf.setProperty("query.force-index", "true");
		conf.setProperty("autotype", "none");
		//-------creating graph handlers---------------------
		TitanUniprotGraph graph = new TitanUniprotGraph(new DefaultTitanGraph(TitanFactory.open(conf)));

		Optional<FeatureType<DefaultTitanGraph,TitanVertex,VertexLabelMaker,TitanEdge,EdgeLabelMaker>> optionalFeature = graph.featureTypeNameIndex().getVertex("hola");
		Optional<FeatureType<DefaultTitanGraph,TitanVertex,VertexLabelMaker,TitanEdge,EdgeLabelMaker>> optionalFeature1 = graph.featureTypeNameIndex().getVertex("hola adios");
		Optional<FeatureType<DefaultTitanGraph,TitanVertex,VertexLabelMaker,TitanEdge,EdgeLabelMaker>> optionalFeature2 = graph.featureTypeNameIndex().getVertex("compositionally biased region");

		graph.raw().commit();

		graph.raw().shutdown();
		// .shutdown();


	}
}

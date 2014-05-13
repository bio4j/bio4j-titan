// /*
//  * Copyright (C) 2010-2013  "Bio4j"
//  *
//  * This file is part of Bio4j
//  *
//  * Bio4j is free software: you can redistribute it and/or modify
//  * it under the terms of the GNU Affero General Public License as
//  * published by the Free Software Foundation, either version 3 of the
//  * License, or (at your option) any later version.
//  * This program is distributed in the hope that it will be useful,
//  * but WITHOUT ANY WARRANTY; without even the implied warranty of
//  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  * GNU Affero General Public License for more details.
//  * You should have received a copy of the GNU Affero General Public License
//  * along with this program.  If not, see <http://www.gnu.org/licenses/>
//  */
// package com.bio4j.titan.model;

// import com.bio4j.model.go.nodes.GoTerm;
// import com.bio4j.model.go.relationships.HasPartOf;
// import com.bio4j.model.go.relationships.IsA;
// import com.bio4j.model.go.relationships.NegativelyRegulates;
// import com.bio4j.model.go.relationships.PartOf;
// import com.bio4j.model.go.relationships.PositivelyRegulates;
// import com.bio4j.model.go.relationships.Regulates;
// import com.bio4j.model.uniprot.nodes.Protein;
// import com.bio4j.model.uniprot_go.relationships.GoAnnotation;
// import com.thinkaurelius.titan.core.TitanEdge;
// import com.thinkaurelius.titan.core.TitanElement;
// import com.thinkaurelius.titan.core.TitanKey;
// import com.thinkaurelius.titan.core.TitanLabel;
// import com.thinkaurelius.titan.core.TitanProperty;
// import com.thinkaurelius.titan.core.TitanRelation;
// import com.thinkaurelius.titan.core.TitanType;
// import com.thinkaurelius.titan.core.TitanVertex;
// import com.thinkaurelius.titan.core.TitanVertexQuery;
// import com.tinkerpop.blueprints.Direction;
// import com.tinkerpop.blueprints.Edge;
// import com.tinkerpop.blueprints.Vertex;

// import java.util.Iterator;
// import java.util.LinkedList;
// import java.util.List;
// import java.util.Set;

// /**
//  *
//  * @author Pablo Pareja Tobes <ppareja@era7.com>
//  */
// public class GoTermNode extends NodeTitan implements GoTerm{
    
//     public GoTermNode(Vertex v){
//         super(v);
//     }

// 	@Override
// 	public Type getType() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public String id() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public String name() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public String definition() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public String comment() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public Boolean obsolete() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public String[] alternativeIds() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public TitanEdge addEdge(TitanLabel arg0, TitanVertex arg1) {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public TitanEdge addEdge(String arg0, TitanVertex arg1) {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public TitanProperty addProperty(TitanKey arg0, Object arg1) {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public TitanProperty addProperty(String arg0, Object arg1) {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public long getEdgeCount() {
// 		// TODO Auto-generated method stub
// 		return 0;
// 	}

// 	@Override
// 	public Iterable<TitanEdge> getEdges() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public Iterable<Edge> getEdges(Direction arg0, String... arg1) {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public Iterable<TitanProperty> getProperties() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public Iterable<TitanProperty> getProperties(TitanKey arg0) {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public Iterable<TitanProperty> getProperties(String arg0) {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public long getPropertyCount() {
// 		// TODO Auto-generated method stub
// 		return 0;
// 	}

// 	@Override
// 	public Iterable<TitanRelation> getRelations() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public Iterable<TitanEdge> getTitanEdges(Direction arg0, TitanLabel... arg1) {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public boolean isConnected() {
// 		// TODO Auto-generated method stub
// 		return false;
// 	}

// 	@Override
// 	public boolean isModified() {
// 		// TODO Auto-generated method stub
// 		return false;
// 	}

// 	@Override
// 	public TitanVertexQuery query() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public long getID() {
// 		// TODO Auto-generated method stub
// 		return 0;
// 	}

// 	@Override
// 	public Object getId() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public <O> O getProperty(TitanKey arg0) {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public <O> O getProperty(String arg0) {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public boolean hasId() {
// 		// TODO Auto-generated method stub
// 		return false;
// 	}

// 	@Override
// 	public boolean isLoaded() {
// 		// TODO Auto-generated method stub
// 		return false;
// 	}

// 	@Override
// 	public boolean isNew() {
// 		// TODO Auto-generated method stub
// 		return false;
// 	}

// 	@Override
// 	public boolean isRemoved() {
// 		// TODO Auto-generated method stub
// 		return false;
// 	}

// 	@Override
// 	public void remove() {
// 		// TODO Auto-generated method stub
		
// 	}

// 	@Override
// 	public <O> O removeProperty(String arg0) {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public <O> O removeProperty(TitanType arg0) {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public void setProperty(String arg0, Object arg1) {
// 		// TODO Auto-generated method stub
		
// 	}

// 	@Override
// 	public void setProperty(TitanKey arg0, Object arg1) {
// 		// TODO Auto-generated method stub
		
// 	}

// 	@Override
// 	public Set<String> getPropertyKeys() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public int compareTo(TitanElement arg0) {
// 		// TODO Auto-generated method stub
// 		return 0;
// 	}

// 	@Override
// 	public Edge addEdge(String arg0, Vertex arg1) {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public Iterable<Vertex> getVertices(Direction arg0, String... arg1) {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<GoAnnotation> goAnnotation_in() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<Protein> goAnnotation_inNodes() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<HasPartOf> hasPartOf_in() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<GoTerm> hasPartOf_inNodes() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<HasPartOf> hasPartOf_out() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<GoTerm> hasPartOf_outNodes() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<IsA> isA_in() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<GoTerm> isA_inNodes() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<IsA> isA_out() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<GoTerm> isA_outNodes() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<NegativelyRegulates> negativelyRegulates_in() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<GoTerm> negativelyRegulates_inNodes() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<NegativelyRegulates> negativelyRegulates_out() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<GoTerm> negativelyRegulates_outNodes() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<PartOf> partOf_in() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<GoTerm> partOf_inNodes() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<PartOf> partOf_out() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<GoTerm> partOf_outNodes() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<PositivelyRegulates> positivelyRegulates_in() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<GoTerm> positivelyRegulates_inNodes() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<PositivelyRegulates> positivelyRegulates_out() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<GoTerm> positivelyRegulates_outNodes() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<Regulates> regulates_in() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<GoTerm> regulates_inNodes() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<Regulates> regulates_out() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public List<GoTerm> regulates_outNodes() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}
    
// //    @Override
// //    public String[] getAlternativeIds(){    
// //        String[] result;
// //        TitanVertex tempVertex = (TitanVertex) vertex;
// //        Iterator<TitanProperty> iterator = tempVertex.getProperties(ALTERNATIVE_IDS_PROPERTY).iterator();
// //        List<String> tempList = new LinkedList<>();
// //        while(iterator.hasNext()){
// //            tempList.add((String)iterator.next().getValue());
// //        }
// //        result = tempList.toArray(new String[tempList.size()]);
// //        return result; 
// //    }
// //    
// //    @Override
// //    public void setAlternativeIds(String[] list){ 
// //        TitanVertex tempVertex = (TitanVertex) vertex;
// //        //first we have to delete any previous properties
// //        tempVertex.removeProperty(ALTERNATIVE_IDS_PROPERTY);
// //        for (String string : list) {
// //            tempVertex.addProperty(ALTERNATIVE_IDS_PROPERTY, string);
// //        } 
// //    }
    
// }

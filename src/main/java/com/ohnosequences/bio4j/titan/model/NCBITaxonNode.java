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
// package com.ohnosequences.bio4j.titan.model;

// import java.util.Iterator;
// import java.util.LinkedList;

// import com.thinkaurelius.titan.core.TitanProperty;
// import com.thinkaurelius.titan.core.TitanVertex;
// import com.tinkerpop.blueprints.Vertex;

// public class NCBITaxonNode extends com.bio4j.model.nodes.ncbi.NCBITaxonNode{

//     public NCBITaxonNode(Vertex v) {
//         super(v);
//     }
    
//     protected TitanVertex titanVertex = (TitanVertex) vertex;

//     public String[] getGiIds(){ return titanVertex.getProperty(GI_IDS_PROPERTY); }
//     public String[] getOldTaxIds(){ return titanVertex.getProperty(OLD_TAX_IDS_PROPERTY); } 
    
//     public void addGiId(String value){ titanVertex.addProperty(GI_IDS_PROPERTY, value); }
//     public void addOldTaxId(String value){ titanVertex.addProperty(OLD_TAX_IDS_PROPERTY, value); }

// }

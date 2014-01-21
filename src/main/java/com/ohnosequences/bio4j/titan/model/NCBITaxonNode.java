/*
 * Copyright (C) 2010-2013  "Bio4j"
 *
 * This file is part of Bio4j
 *
 * Bio4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.ohnosequences.bio4j.titan.model;

import java.util.Iterator;
import java.util.LinkedList;

import com.thinkaurelius.titan.core.TitanProperty;
import com.thinkaurelius.titan.core.TitanVertex;
import com.tinkerpop.blueprints.Vertex;

public class NCBITaxonNode extends com.ohnosequences.bio4j.blueprints.model.nodes.ncbi.NCBITaxonNode{

	public NCBITaxonNode(Vertex v) {
		super(v);
	}
	
	public String[] getGIIds(){        return getMultiValuedProperty(GI_IDS_PROPERTY); }
	public String[] getOldTaxIds(){        return getMultiValuedProperty(OLD_TAX_IDS_PROPERTY); }	
	

    public void setGIIds(String[] list){    setMultiValuedProperty(GI_IDS_PROPERTY, list);}
    public void setOldTaxIds(String[] list){    setMultiValuedProperty(OLD_TAX_IDS_PROPERTY, list);}
    
	
	private void setMultiValuedProperty(String propertyName, String[] value){
        TitanVertex tempVertex = (TitanVertex) vertex;
        tempVertex.removeProperty(propertyName);
        for (String string : value) {
            tempVertex.addProperty(propertyName, string);
        } 
    }
    
    private String[] getMultiValuedProperty(String propertyName){
        TitanVertex tempVertex = (TitanVertex) vertex;
        Iterator<TitanProperty> iterator = tempVertex.getProperties(propertyName).iterator();
        LinkedList<String> list = new LinkedList<>();
        while(iterator.hasNext()){
            list.add(String.valueOf(iterator.next().getValue()));
        }
        return list.toArray(new String[list.size()]);
    }

}

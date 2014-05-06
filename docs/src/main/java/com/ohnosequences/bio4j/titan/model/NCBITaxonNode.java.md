
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


```java
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
    
    protected TitanVertex titanVertex = (TitanVertex) vertex;

    public String[] getGiIds(){ return titanVertex.getProperty(GI_IDS_PROPERTY); }
    public String[] getOldTaxIds(){ return titanVertex.getProperty(OLD_TAX_IDS_PROPERTY); } 
    
    public void addGiId(String value){ titanVertex.addProperty(GI_IDS_PROPERTY, value); }
    public void addOldTaxId(String value){ titanVertex.addProperty(OLD_TAX_IDS_PROPERTY, value); }

}

```


------

### Index

+ src
  + main
    + java
      + com
        + ohnosequences
          + bio4j
            + titan
              + model
                + [GoTermNode.java][main/java/com/ohnosequences/bio4j/titan/model/GoTermNode.java]
                + [NCBITaxonNode.java][main/java/com/ohnosequences/bio4j/titan/model/NCBITaxonNode.java]
                + [ProteinNode.java][main/java/com/ohnosequences/bio4j/titan/model/ProteinNode.java]
                + util
                  + [Bio4jManager.java][main/java/com/ohnosequences/bio4j/titan/model/util/Bio4jManager.java]
                  + [NodeRetrieverTitan.java][main/java/com/ohnosequences/bio4j/titan/model/util/NodeRetrieverTitan.java]
                  + [UniprotStuff.java][main/java/com/ohnosequences/bio4j/titan/model/util/UniprotStuff.java]
              + programs
                + [ImportEnzymeDBTitan.java][main/java/com/ohnosequences/bio4j/titan/programs/ImportEnzymeDBTitan.java]
                + [ImportGeneOntologyTitan.java][main/java/com/ohnosequences/bio4j/titan/programs/ImportGeneOntologyTitan.java]
                + [ImportIsoformSequencesTitan.java][main/java/com/ohnosequences/bio4j/titan/programs/ImportIsoformSequencesTitan.java]
                + [ImportNCBITaxonomyTitan.java][main/java/com/ohnosequences/bio4j/titan/programs/ImportNCBITaxonomyTitan.java]
                + [ImportProteinInteractionsTitan.java][main/java/com/ohnosequences/bio4j/titan/programs/ImportProteinInteractionsTitan.java]
                + [ImportRefSeqTitan.java][main/java/com/ohnosequences/bio4j/titan/programs/ImportRefSeqTitan.java]
                + [ImportTitanDB.java][main/java/com/ohnosequences/bio4j/titan/programs/ImportTitanDB.java]
                + [ImportUniprotTitan.java][main/java/com/ohnosequences/bio4j/titan/programs/ImportUniprotTitan.java]
                + [ImportUnirefTitan.java][main/java/com/ohnosequences/bio4j/titan/programs/ImportUnirefTitan.java]
                + [IndexNCBITaxonomyByGiIdTitan.java][main/java/com/ohnosequences/bio4j/titan/programs/IndexNCBITaxonomyByGiIdTitan.java]
                + [InitBio4jTitan.java][main/java/com/ohnosequences/bio4j/titan/programs/InitBio4jTitan.java]

[main/java/com/ohnosequences/bio4j/titan/model/GoTermNode.java]: GoTermNode.java.md
[main/java/com/ohnosequences/bio4j/titan/model/NCBITaxonNode.java]: NCBITaxonNode.java.md
[main/java/com/ohnosequences/bio4j/titan/model/ProteinNode.java]: ProteinNode.java.md
[main/java/com/ohnosequences/bio4j/titan/model/util/Bio4jManager.java]: util/Bio4jManager.java.md
[main/java/com/ohnosequences/bio4j/titan/model/util/NodeRetrieverTitan.java]: util/NodeRetrieverTitan.java.md
[main/java/com/ohnosequences/bio4j/titan/model/util/UniprotStuff.java]: util/UniprotStuff.java.md
[main/java/com/ohnosequences/bio4j/titan/programs/ImportEnzymeDBTitan.java]: ../programs/ImportEnzymeDBTitan.java.md
[main/java/com/ohnosequences/bio4j/titan/programs/ImportGeneOntologyTitan.java]: ../programs/ImportGeneOntologyTitan.java.md
[main/java/com/ohnosequences/bio4j/titan/programs/ImportIsoformSequencesTitan.java]: ../programs/ImportIsoformSequencesTitan.java.md
[main/java/com/ohnosequences/bio4j/titan/programs/ImportNCBITaxonomyTitan.java]: ../programs/ImportNCBITaxonomyTitan.java.md
[main/java/com/ohnosequences/bio4j/titan/programs/ImportProteinInteractionsTitan.java]: ../programs/ImportProteinInteractionsTitan.java.md
[main/java/com/ohnosequences/bio4j/titan/programs/ImportRefSeqTitan.java]: ../programs/ImportRefSeqTitan.java.md
[main/java/com/ohnosequences/bio4j/titan/programs/ImportTitanDB.java]: ../programs/ImportTitanDB.java.md
[main/java/com/ohnosequences/bio4j/titan/programs/ImportUniprotTitan.java]: ../programs/ImportUniprotTitan.java.md
[main/java/com/ohnosequences/bio4j/titan/programs/ImportUnirefTitan.java]: ../programs/ImportUnirefTitan.java.md
[main/java/com/ohnosequences/bio4j/titan/programs/IndexNCBITaxonomyByGiIdTitan.java]: ../programs/IndexNCBITaxonomyByGiIdTitan.java.md
[main/java/com/ohnosequences/bio4j/titan/programs/InitBio4jTitan.java]: ../programs/InitBio4jTitan.java.md
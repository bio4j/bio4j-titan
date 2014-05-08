package com.bio4j.titan.model.enzymedb.nodes;

import java.util.List;

import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.ohnosequences.typedGraphs.titan.TitanNodeType;

import com.thinkaurelius.titan.core.TitanVertex;

// properties
import com.bio4j.model.properties.Id;
import com.bio4j.model.properties.Cofactors;
import com.bio4j.model.properties.OfficialName;
import com.bio4j.model.properties.AlternateNames;
import com.bio4j.model.properties.CatalyticActivity;
import com.bio4j.model.properties.Comment;
import com.bio4j.model.properties.PrositeCrossReferences;

import com.bio4j.model.enzymedb.nodes.*;

// relationships
import com.bio4j.model.enzymedb.relationships.EnzymaticActivity;

// other nodes
import com.bio4j.model.uniprot.nodes.Protein;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public class TitanEnzyme extends TitanNode<TitanEnzyme, Enzyme.Type> implements com.bio4j.model.enzymedb.nodes.Enzyme

  // properties
  // Id<Enzyme, Enzyme.Type>,
  // Cofactors<Enzyme, Enzyme.Type>,
  // OfficialName<Enzyme, Enzyme.Type>,
  // AlternateNames<Enzyme, Enzyme.Type>,
  // CatalyticActivity<Enzyme, Enzyme.Type>,
  // Comment<Enzyme, Enzyme.Type>, // WARNING: changed this from comments to comment
  // PrositeCrossReferences<Enzyme, Enzyme.Type>

{
  
  TitanEnzyme(TitanVertex vertex) { super(vertex); }
  
  // properties
  public String id() { return get(Id.TYPE); }
  public String comment() { return get(Comment.TYPE); }
  // etc etc

  // enzymaticActivity
  // incoming
  public List<TitanEnzymaticActivity> enzymaticActivity_in() { 

    return inFromMany(TitanEnzymaticActivity.TYPE);

  }
  // TODO not implemented abstractly yet
  public List<Protein> enzymaticActivity_inNodes() {}
  
  // WARNING: moved to rel method
  // public List<Protein> associatedProteins();  

  // public static Type TYPE = Type.enzyme;
  public static enum Type implements TitanNodeType<TitanEnzyme, Enzyme.Type> {

    titanEnzyme; 
    public Enzyme.Type type() { return Enzyme.TYPE; }
    public TitanEnzyme from(TitanVertex vertex) { return new TitanEnzyme(vertex); }
  }  
}

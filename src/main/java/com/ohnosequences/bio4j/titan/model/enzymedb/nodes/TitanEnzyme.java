package com.bio4j.titan.model.enzymedb.nodes;

import java.util.List;
import java.util.LinkedList;

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
public class TitanEnzyme extends TitanNode<
  
  Enzyme, Enzyme.Type,
  TitanEnzyme, TitanEnzyme.Type
> implements com.bio4j.model.enzymedb.nodes.Enzyme

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

  public Type titanType() { return Type.titanEnzyme; }
  
  // properties
  @Override
  public String id() { return get(Id.TYPE(this.type())); }
  public String comment() { return get(Comment.TYPE((this.type()))); }
  // etc etc

  // enzymaticActivity
  // incoming
  public List<EnzymaticActivity> enzymaticActivity_in() { 

    // return inFromMany(TitanEnzymaticActivity.TYPE);

    return new LinkedList<EnzymaticActivity>();

  }
  // TODO not implemented
  public List<Protein> enzymaticActivity_inNodes() { return new LinkedList<Protein>(); }
  
  // public static Type TYPE = Type.enzyme;
  public static enum Type implements TitanNodeType<

    Enzyme, Enzyme.Type,
    TitanEnzyme, TitanEnzyme.Type
  > 
  {

    titanEnzyme;
    public Enzyme.Type type() { return Enzyme.TYPE; }
    public TitanEnzyme from(TitanVertex vertex) { return new TitanEnzyme(vertex); }
  }  
}

package com.bio4j.titan.model.enzymedb.nodes;

import java.util.List;
import java.util.LinkedList;

import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.ohnosequences.typedGraphs.titan.TitanNodeType;

import com.thinkaurelius.titan.core.*;

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
// public final class TitanEnzyme extends TitanNode <
  
//   Enzyme, Enzyme.Type,
//   TitanEnzyme, TitanEnzyme.Type
// > implements com.bio4j.model.enzymedb.nodes.Enzyme
// {
  
//   TitanEnzyme(TitanVertex vertex, TitanGraph graph) { super(vertex, graph); }
  
//   // properties
//   @Override public String   id()                      { return get(Id.TYPE(type())); }
//   @Override public String   officialName()            { return get(OfficialName.TYPE(type())); }
//   @Override public String[] alternateNames()          { return get(AlternateNames.TYPE(type())); }
//   @Override public String   comment()                 { return get(Comment.TYPE((type()))); }
//   @Override public String[] cofactors()               { return get(Cofactors.TYPE(type())); }
//   @Override public String   catalyticActivity()       { return get(CatalyticActivity.TYPE(type())); }
//   @Override public String[] prositeCrossReferences()  { return get(PrositeCrossReferences.TYPE(type())); }

//   // enzymaticActivity
//   // incoming
//   // TODO implement
//   public List<EnzymaticActivity> enzymaticActivity_in() { 

//     // return inFromMany(TitanEnzymaticActivity.TYPE);
//     return null;
//   }
//   // TODO implement
//   public List<Protein> enzymaticActivity_inNodes() { 

//     return null; 
//   }

//   ///////////////////////////////////////////////////////////////////////////////////////

//   public Type titanType() { return Type.titanEnzyme; }
//   // public static Type TYPE = Type.enzyme;
//   public static enum Type implements TitanNodeType <
//     Enzyme, Enzyme.Type,
//     TitanEnzyme, TitanEnzyme.Type
//   > 
//   {

//     titanEnzyme;

//     public Enzyme.Type type() { return Enzyme.TYPE; }

//     public TitanKey titanKey() { return null; }

//     public TitanEnzyme from(TitanVertex vertex) { return new TitanEnzyme(vertex); }
//   }  
// }

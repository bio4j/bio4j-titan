package com.bio4j.titan.model.go.nodes;

import java.util.List;

import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.ohnosequences.typedGraphs.titan.TitanNodeType;

import com.bio4j.model.go.nodes.*;
// properties
import com.bio4j.model.properties.*;
// relationships
import com.bio4j.model.go.relationships.*;
import com.bio4j.titan.model.go.relationships.*;
import com.bio4j.titan.model.go.TitanGoGraph;
import com.thinkaurelius.titan.core.*;

// goAnnotation
// import com.bio4j.model.uniprot_go.relationships.GoAnnotation;
// import com.bio4j.model.uniprot.nodes.Protein;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public final class TitanTerm extends TitanGoGraph.GoNode <
  Term,Term.Type, TitanTerm, TitanGoGraph.TitanTermType
> 
  implements Term
{

  public TitanTerm(TitanVertex vertex, TitanGoGraph graph) { super(vertex,graph); }

  @Override public TitanGoGraph.TitanTermType titanType() { return graph().titanTermType(); }

  @Override public String   id()          { return get(Id.TYPE(type())); }
  @Override public String   name()        { return get(Name.TYPE(type())); }
  @Override public String   comment()     { return get(Comment.TYPE((type()))); }

  // isA
  // incoming
  @Override public List<TitanIsA> isA_in() { return inFromMany( graph().titanIsAType() ); }
  // public List<Term> isA_inNodes();
  // // outgoing
  @Override public List<TitanIsA> isA_out() { return outToMany( graph().titanIsAType() ); }
  // public List<Term> isA_outNodes();

  // // regulates
  // // incoming
  // public List<Regulates> regulates_in();
  // public List<Term> regulates_inNodes();
  // // outgoing
  // public List<Regulates> regulates_out(); 
  // public List<Term> regulates_outNodes();

  // // negativelyRegulates
  // // incoming
  // public List<NegativelyRegulates> negativelyRegulates_in();
  // public List<Term> negativelyRegulates_inNodes();
  // // outgoing
  // public List<NegativelyRegulates> negativelyRegulates_out(); 
  // public List<Term> negativelyRegulates_outNodes();
    

  // // positivelyRegulates
  // // incoming
  // public List<PositivelyRegulates> positivelyRegulates_in();
  // public List<Term> positivelyRegulates_inNodes();
  // // outgoing
  // public List<PositivelyRegulates> positivelyRegulates_out(); 
  // public List<Term> positivelyRegulates_outNodes();  
  
  // // partOf
  // // incoming
  // public List<PartOf> partOf_in();
  // public List<Term> partOf_inNodes();
  // // outgoing
  // public List<PartOf> partOf_out();
  // public List<Term> partOf_outNodes();

  // // hasPartOf
  // // incoming
  // public List<HasPartOf> hasPartOf_in();
  // public List<Term> hasPartOf_inNodes();
  // // outgoing
  // public List<HasPartOf> hasPartOf_out();
  // public List<Term> hasPartOf_outNodes();

  ///////////////////////// extras ////////////////////////////////////

  // goAnnotation
  // incoming
  // public List<GoAnnotation> goAnnotation_in();
  // public List<Protein> goAnnotation_inNodes();



  // relationships
  // public List<Protein> associatedProteins();
  // public List<Term> isAGoNodes();
  // public List<Term> negativelyRegulatesNodes();
  // public List<Term> positivelyRegulatesNodes();
  // public List<Term> partOfNodes();
}

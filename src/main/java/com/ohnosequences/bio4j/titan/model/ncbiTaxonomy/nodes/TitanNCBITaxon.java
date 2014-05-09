package com.bio4j.titan.model.ncbiTaxonomy.nodes;

import java.util.List;
import java.util.LinkedList;

import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.ohnosequences.typedGraphs.titan.TitanNodeType;
import com.ohnosequences.typedGraphs.NodeType;


import com.thinkaurelius.titan.core.TitanVertex;

// properties
// import com.bio4j.model.properties.Name;
// import com.bio4j.model.properties.Comment;
// import com.bio4j.model.properties.TaxId;
// import com.bio4j.model.properties.ScientificName;
// import com.bio4j.model.properties.TaxonomicRank;
// import com.bio4j.model.properties.EmblCode;

import com.bio4j.model.ncbiTaxonomy.nodes.*;

// relationships
import com.bio4j.model.ncbiTaxonomy.relationships.Parent;
import com.bio4j.titan.model.ncbiTaxonomy.relationships.TitanParent;

// other nodes
// import com.bio4j.model.uniprot.nodes.Taxon;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public final class TitanNCBITaxon extends TitanNode<TitanNCBITaxon, NCBITaxon.Type> implements NCBITaxon
  
  // // properties
  // TaxId<NCBITaxon, NCBITaxon.Type>, // TODO what is this? probably should be changed to id
  // Name<NCBITaxon, NCBITaxon.Type>,
  // Comment<NCBITaxon, NCBITaxon.Type>, // WARNING changed comments to comment
  // ScientificName<NCBITaxon, NCBITaxon.Type>,
  // TaxonomicRank<NCBITaxon, NCBITaxon.Type>, // WARNING this was rank before
  // EmblCode<NCBITaxon, NCBITaxon.Type> // TODO maybe this should be promoted to a rel

{
  
  TitanNCBITaxon(TitanVertex vertex) { super(vertex); }

  // parent
  // incoming
  @Override
  public List<? extends TitanParent> parent_in()  { return inFromMany(TitanParent.TYPE); }

  // TODO implement
  @Override
  public List<TitanNCBITaxon> parent_inNodes() { return new LinkedList<TitanNCBITaxon>(); }
  // outgoing
  @Override
  public TitanParent parent_out() { return outToOne(TitanParent.TYPE); }
  @Override
  public TitanNCBITaxon parent_outNodes() { return new LinkedList<TitanNCBITaxon>(); }

  // is this about some sort of connection with UniProt taxonomy?
  // public Taxon taxon(); // TODO what is this??

  public static Type TYPE = Type.ncbiTaxon;
  
  public static enum Type implements TitanNodeType<TitanNCBITaxon, NCBITaxon.Type> {

    ncbiTaxon;

    public NCBITaxon.Type type() { return NCBITaxon.TYPE; }
    public Type value() { return ncbiTaxon; }

    public TitanNCBITaxon from(TitanVertex vertex) { return new TitanNCBITaxon(vertex); }
  }
}

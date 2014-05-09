package com.bio4j.titan.model.ncbiTaxonomy.relationships;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;

import com.ohnosequences.typedGraphs.titan.*;

import com.thinkaurelius.titan.core.TitanEdge;

import com.bio4j.model.ncbiTaxonomy.nodes.NCBITaxon;
import com.bio4j.model.ncbiTaxonomy.nodes.*;
import com.bio4j.model.ncbiTaxonomy.relationships.*;
import com.bio4j.titan.model.ncbiTaxonomy.nodes.TitanNCBITaxon;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public final class TitanParent extends TitanRelationship <
  TitanNCBITaxon, NCBITaxon.Type,
  TitanParent, Parent.Type,
  NCBITaxon, NCBITaxon.Type
> 
{

  TitanParent(TitanEdge edge) { super(edge); }

  public static Type TYPE = Type.parent;
  public static enum Type implements TitanRelationshipType <
    TitanNCBITaxon, NCBITaxon.Type,
    TitanParent, Parent.Type,
    TitanNCBITaxon, NCBITaxon.Type
  > {

    parent;

    @Override
    public Parent.Type type() { return Parent.TYPE; }

    @Override
    public TitanNCBITaxon.Type titanSourceType() { return TitanNCBITaxon.TYPE; }
    @Override
    public TitanNCBITaxon.Type titanTargetType() { return TitanNCBITaxon.TYPE; }

    @Override
    public TitanParent from(TitanEdge edge) { return new TitanParent(edge); }
  }  
}

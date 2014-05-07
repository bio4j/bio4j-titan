package com.bio4j.model.uniprot.nodes.references;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;
import com.bio4j.model.uniprot.nodes.UnpublishedObservation;


/**
 *  This Node has just one instance per graph. Relationships of type `UnpublishedObservation` to this node blahblahblah
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface UnpublishedObservations extends Node<UnpublishedObservations, UnpublishedObservations.Type> {
  
  public List<? extends UnpublishedObservation> unpublishedObservation_in();
  public List<? extends Reference> unpublishedObservation_inNodes();

  public static Type TYPE = Type.unpublishedObservation;

  public static enum Type implements NodeType<UnpublishedObservations, UnpublishedObservations.Type> {
    
	  unpublishedObservation;
    
    public Type value() { return unpublishedObservation; }
  }
}

package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.relationships.UnpublishedObservationAuthor;

import java.util.List;

import com.bio4j.model.NodeType;
import com.bio4j.model.properties.Date;

/**
 * 
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface UnpublishedObservation extends Node<UnpublishedObservation, UnpublishedObservation.Type>,
	
	// properties
	Date<UnpublishedObservation, UnpublishedObservation.Type> 

{

	public static Type TYPE = Type.unpublishedObservation;

	public static enum Type implements NodeType<UnpublishedObservation, UnpublishedObservation.Type> {

		unpublishedObservation;

		public Type value() {
			
			return unpublishedObservation;
		}
	}

	// unpublishedObservationAuthor
	// outgoing
	public List<UnpublishedObservationAuthor> unpublishedObservationAuthor_out();
	public List<Person> unpublishedObservationAuthor_outNodes();

	// unpublishedObservationProteinCitation
	// outgoing
	public List<UnpublishedObservationAuthor> unpublishedObservationProteinCitation_out();
	public List<Protein> unpublishedObservationProteinCitation_outNodes();
}

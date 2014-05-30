//package com.bio4j.titan.model.go.nodes;
//
//import java.util.List;
//
//import com.ohnosequences.typedGraphs.Element;
//import com.ohnosequences.typedGraphs.titan.TitanNode;
//import com.ohnosequences.typedGraphs.titan.TitanNodeType;
//
//import com.bio4j.model.go.nodes.*;
//import com.bio4j.model.uniprot.nodes.Protein;
//import com.bio4j.model.uniprot_go.relationships.*;
//// properties
//import com.bio4j.model.properties.*;
//// relationships
//import com.bio4j.model.go.relationships.*;
//import com.bio4j.titan.model.go.relationships.*;
//import com.bio4j.titan.model.go.TitanGoGraph;
//import com.thinkaurelius.titan.core.*;
//
//// goAnnotation
//// import com.bio4j.model.uniprot_go.relationships.GoAnnotation;
//// import com.bio4j.model.uniprot.nodes.Protein;
//
///**
// *
// * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
// * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo
// *         Pareja-Tobes</a>
// */
//public final class TitanTerm extends
//		TitanGoGraph.GoNode<TitanTerm, TitanGoGraph.TitanTermType> implements
//		Term {
//
//	public TitanTerm(TitanVertex vertex, TitanGoGraph graph) {
//		super(vertex, graph);
//	}
//
//	@Override
//	public TitanGoGraph.TitanTermType titanType() {
//		return graph().titanTermType();
//	}
//
//	// isA
//	// incoming
//	@Override
//	public List<TitanIsA> isA_in() {
//		return inFromMany(graph().titanIsAType());
//	}
//
//	@Override
//	public List<TitanTerm> isA_inNodes() {
//		return inFromMany_Nodes(graph().titanIsAType());
//	}
//
//	// // outgoing
//	@Override
//	public List<TitanIsA> isA_out() {
//		return outToMany(graph().titanIsAType());
//	}
//
//	@Override
//	public List<TitanTerm> isA_outNodes() {
//		return outToMany_Nodes(graph().titanIsAType());
//	}
//
//	// regulates
//	// incoming
//	@Override
//	public List<TitanRegulates> regulates_in() {
//		return inFromMany(graph().titanRegulatesType());
//	}
//
//	@Override
//	public List<TitanTerm> regulates_inNodes() {
//		return inFromMany_Nodes(graph().titanRegulatesType());
//	}
//
//	// // outgoing
//	@Override
//	public List<TitanRegulates> regulates_out() {
//		return outToMany(graph().titanRegulatesType());
//	}
//
//	@Override
//	public List<TitanTerm> regulates_outNodes() {
//		return outToMany_Nodes(graph().titanRegulatesType());
//	}
//
//	// positivelyRegulates
//	// incoming
//	@Override
//	public List<TitanPositivelyRegulates> positivelyRegulates_in() {
//		return inFromMany(graph().titanPositivelyRegulatesType());
//	}
//
//	@Override
//	public List<TitanTerm> positivelyRegulates_inNodes() {
//		return inFromMany_Nodes(graph().titanPositivelyRegulatesType());
//	}
//
//	// // outgoing
//	@Override
//	public List<TitanPositivelyRegulates> positivelyRegulates_out() {
//		return outToMany(graph().titanPositivelyRegulatesType());
//	}
//
//	@Override
//	public List<TitanTerm> positivelyRegulates_outNodes() {
//		return outToMany_Nodes(graph().titanPositivelyRegulatesType());
//	}
//
//	// negativelyRegulates
//	// incoming
//	@Override
//	public List<TitanNegativelyRegulates> negativelyRegulates_in() {
//		return inFromMany(graph().titanNegativelyRegulatesType());
//	}
//
//	@Override
//	public List<TitanTerm> negativelyRegulates_inNodes() {
//		return inFromMany_Nodes(graph().titanNegativelyRegulatesType());
//	}
//
//	// // outgoing
//	@Override
//	public List<TitanNegativelyRegulates> negativelyRegulates_out() {
//		return outToMany(graph().titanNegativelyRegulatesType());
//	}
//
//	@Override
//	public List<TitanTerm> negativelyRegulates_outNodes() {
//		return outToMany_Nodes(graph().titanNegativelyRegulatesType());
//	}
//
//	// // partOf
//	// // incoming
//	@Override
//	public List<TitanPartOf> partOf_in() {
//		return inFromMany(graph().titanPartOfType());
//	}
//
//	@Override
//	public List<TitanTerm> partOf_inNodes() {
//		return inFromMany_Nodes(graph().titanPartOfType());
//	}
//
//	// // outgoing
//	@Override
//	public List<TitanPartOf> partOf_out() {
//		return outToMany(graph().titanPartOfType());
//	}
//
//	@Override
//	public List<TitanTerm> partOf_outNodes() {
//		return outToMany_Nodes(graph().titanPartOfType());
//	}
//
//	// // partOf
//	// // incoming
//	@Override
//	public List<TitanHasPartOf> hasPartOf_in() {
//		return inFromMany(graph().titanHasPartOfType());
//	}
//
//	@Override
//	public List<TitanTerm> hasPartOf_inNodes() {
//		return inFromMany_Nodes(graph().titanHasPartOfType());
//	}
//
//	// // outgoing
//	@Override
//	public List<TitanHasPartOf> hasPartOf_out() {
//		return outToMany(graph().titanHasPartOfType());
//	}
//
//	@Override
//	public List<TitanTerm> hasPartOf_outNodes() {
//		return outToMany_Nodes(graph().titanHasPartOfType());
//	}
//
//	// MolecularFunction
//	// outgoing
//	public TitanMolecularFunction molecularFunction_out() {
//		return outToOne(graph().titanMolecularFunctionType());
//	}
//
//	public TitanSubOntologies molecularFunction_outNodes() {
//		return outToOne_Node(graph().titanMolecularFunctionType());
//	}
//
//	// BiologicalProcess
//	// outgoing
//	public BiologicalProcess biologicalProcess_out() {
//		return outToOne(graph().titanBiologicalProcessType());
//	}
//
//	public SubOntologies biologicalProcess_outNodes() {
//		return outToOne_Node(graph().titanBiologicalProcessType());
//	}
//
//	// CellularComponent
//	// outgoing
//	public CellularComponent cellularComponent_out() {
//		return outToOne(graph().titanCellularComponentType());
//	}
//
//	public SubOntologies cellularComponent_outNodes() {
//		return outToOne_Node(graph().titanCellularComponentType());
//	}
//
//	// /////////////////////// extras ////////////////////////////////////
//
//	// goAnnotation
//	// incoming
//	// TODO implement
//	@Override
//	public List<GoAnnotation> goAnnotation_in() {
//		return null;
//	}
//
//	@Override
//	public List<Protein> goAnnotation_inNodes() {
//		return null;
//	}
//
//    @Override
//    public Element.Type type() {
//        return null;
//    }
//}

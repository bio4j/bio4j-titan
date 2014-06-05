//package com.bio4j.titan.model.go.relationships;
//
//import com.ohnosequences.typedGraphs.Relationship;
//import com.ohnosequences.typedGraphs.RelationshipType;
//
//import com.bio4j.model.go.nodes.Term;
//import com.bio4j.titan.model.go.nodes.TitanGoTerm;
//
//import com.bio4j.model.go.relationships.IsA;
//
//import com.thinkaurelius.titan.core.*;
//
//import com.bio4j.titan.model.go.TitanGoGraph;
//
///**
// *
// * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
// * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo
// *         Pareja-Tobes</a>
// */
//public final class TitanIsA
//		extends
//		TitanGoGraph.GoRel<Term, Term.Type, TitanGoTerm, TitanGoGraph.TitanTermType, IsA, IsA.Type, TitanIsA, TitanGoGraph.TitanIsAType, Term, Term.Type, TitanGoTerm, TitanGoGraph.TitanTermType>
//		implements IsA {
//
//	public TitanIsA(TitanEdge edge, TitanGoGraph graph) {
//		super(edge, graph);
//	}
//
//	@Override
//	public TitanGoGraph.TitanIsAType titanType() {
//		return graph().titanIsAType();
//	}
//
//}

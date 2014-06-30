//package com.bio4j.titan.model.uniprot_go.relationships;
//
//import com.bio4j.model.uniprot_go.UniprotGoGraph;
//import com.bio4j.model.uniprot_go.relationships.GoAnnotation;
//import com.bio4j.titan.model.go.nodes.TitanGoTerm;
//import com.bio4j.titan.model.uniprot.nodes.TitanProtein;
//import com.bio4j.titan.model.uniprot_go.TitanUniprotGoGraph;
//import com.ohnosequences.typedGraphs.titan.TitanRelationship;
//import com.thinkaurelius.titan.core.TitanEdge;
//import com.thinkaurelius.titan.core.TitanLabel;
//
///**
// * Created by ppareja on 6/25/2014.
// */
//public class TitanGoAnnotation extends
//		TitanRelationship<
//		TitanProtein, TitanProtein.TitanProteinType,
//		TitanGoAnnotation, TitanGoAnnotation.TitanGoAnnotationType,
//		TitanGoTerm, TitanGoTerm.TitanGoTermType
//		>
//		implements
//		GoAnnotation<
//						TitanProtein, TitanProtein.TitanProteinType,
//						TitanGoAnnotation, TitanGoAnnotation.TitanGoAnnotationType,
//						TitanGoTerm, TitanGoTerm.TitanGoTermType
//						> {
//
//	TitanGoAnnotation(TitanEdge edge, TitanUniprotGoGraph goGraph) {
//
//		super(edge);
//		this.uniprotGoGraph = goGraph;
//	}
//
//	TitanUniprotGoGraph uniprotGoGraph;
//
//	/*
//	  Note here how we need a reference to the enclosing graph, which contains the term type value.
//	*/
//	@Override
//	public TitanGoAnnotationType type() {
//
//		return uniprotGoGraph.goAnnotationT;
//	}
//
//	public static final class TitanGoAnnotationType
//			implements
//			TitanRelationship.Type<
//					TitanProtein, TitanProtein.TitanProteinType,
//					TitanGoAnnotation, TitanGoAnnotation.TitanGoAnnotationType,
//					TitanGoTerm, TitanGoTerm.TitanGoTermType
//					>,
//			UniprotGoGraph.GoAnnotationType<
//								TitanProtein, TitanProtein.TitanProteinType,
//								TitanGoAnnotation, TitanGoAnnotationType,
//								TitanGoTerm, TitanGoTerm.TitanGoTermType
//								> {
//
//		TitanUniprotGoGraph uniprotGoGraph;
//
//		public TitanGoAnnotationType(TitanUniprotGoGraph goGraph) {
//
//			this.uniprotGoGraph = uniprotGoGraph;
//		}
//
//		@Override
//		public TitanLabel label() {
//			return uniprotGoGraph.goAnnotationLabel;
//		}
//
//		@Override
//		public TitanGoAnnotationType value() {
//			return uniprotGoGraph.goAnnotationT;
//		}
//
//		@Override
//		public TitanProtein.TitanProteinType sourceType() {
//			return uniprotGoGraph.proteinT;
//		}
//
//		@Override
//		public TitanGoTerm.TitanGoTermType targetType() {
//			return uniprotGoGraph.goTermT;
//		}
//
//		@Override
//		public TitanGoAnnotation fromTitanEdge(TitanEdge edge) {
//			return new TitanGoAnnotation(edge, uniprotGoGraph);
//		}
//	}
//}

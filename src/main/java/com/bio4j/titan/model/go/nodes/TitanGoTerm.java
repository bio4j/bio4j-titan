package com.bio4j.titan.model.go.nodes;

import com.ohnosequences.typedGraphs.titan.*;

// properties

import com.bio4j.titan.model.go.TitanGoGraph;

import com.bio4j.model.go.nodes.Term;
import com.bio4j.model.go.GoGraph.TermType;

import com.thinkaurelius.titan.core.*;

import java.util.List;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo
 *         Pareja-Tobes</a>
 */


public final class TitanGoTerm
        extends
        TitanNode<TitanGoTerm, TitanGoTerm.TitanGoTermType>
        implements
        Term<TitanGoTerm, TitanGoTerm.TitanGoTermType> {

    public TitanGoTerm(TitanVertex vertex, TitanGoGraph goGraph) {

        super(vertex);
        this.goGraph = goGraph;
    }

    TitanGoGraph goGraph;

    @Override
    public TitanGoTermType type() {

        return goGraph.goTermT;
    }


    public static final class TitanGoTermType
            implements
            TitanNode.Type<TitanGoTerm, TitanGoTerm.TitanGoTermType>,
            TermType<TitanGoTerm, TitanGoTerm.TitanGoTermType> {

        public TitanGoTermType(TitanGoGraph goGraph) {
            this.goGraph = goGraph;
        }

        TitanGoGraph goGraph;

        @Override
        public TitanKey titanKey() {

            return goGraph.goTermTkey;
        }

        @Override
        public TitanGoTermType value() {

            return goGraph.goTermT;
        }

        @Override
        public TitanGoTerm fromTitanVertex(TitanVertex vertex) {

            return new TitanGoTerm(vertex, goGraph);
        }

        // properties
        public id id = new id();

        // no need to worry about the unchecked warning
        @Override
        public id Id() {

            return id;
        }

        public final class id
                implements
                com.ohnosequences.typedGraphs.titan.TitanProperty<TitanGoTerm, TitanGoTermType, id, String>,
                Term.id<TitanGoTerm, TitanGoTermType, id> {

            @Override
            public TitanGoTermType elementType() {

                return TitanGoTermType.this;
            }

            @Override
            public TitanKey titanKey() {

                return goGraph.goTermIdKey;
            }
        }

        name name = new name();

        @Override
        public name Name() {

            return name;
        }

        public final class name
                implements
                com.ohnosequences.typedGraphs.titan.TitanProperty<TitanGoTerm, TitanGoTermType, name, String>,
                Term.name<TitanGoTerm, TitanGoTermType, name> {

            @Override
            public TitanGoTermType elementType() {

                return TitanGoTermType.this;
            }

            @Override
            public TitanKey titanKey() {

                return goGraph.goTermNameKey;
            }
        }
    }


    @Override
    public List<TitanGoTerm> isA_inNodes() {
        return inFromMany_Nodes(goGraph.titanIsAType());
    }

    // // outgoing
    @Override
    public List<TitanIsA> isA_out() {
        return outToMany(graph().titanIsAType());
    }

    @Override
    public List<TitanGoTerm> isA_outNodes() {
        return outToMany_Nodes(graph().titanIsAType());
    }

    // regulates
    // incoming
    @Override
    public List<TitanRegulates> regulates_in() {
        return inFromMany(graph().titanRegulatesType());
    }

    @Override
    public List<TitanGoTerm> regulates_inNodes() {
        return inFromMany_Nodes(graph().titanRegulatesType());
    }

    // // outgoing
    @Override
    public List<TitanRegulates> regulates_out() {
        return outToMany(graph().titanRegulatesType());
    }

    @Override
    public List<TitanGoTerm> regulates_outNodes() {
        return outToMany_Nodes(graph().titanRegulatesType());
    }

    // positivelyRegulates
    // incoming
    @Override
    public List<TitanPositivelyRegulates> positivelyRegulates_in() {
        return inFromMany(graph().titanPositivelyRegulatesType());
    }

    @Override
    public List<TitanGoTerm> positivelyRegulates_inNodes() {
        return inFromMany_Nodes(graph().titanPositivelyRegulatesType());
    }

    // // outgoing
    @Override
    public List<TitanPositivelyRegulates> positivelyRegulates_out() {
        return outToMany(graph().titanPositivelyRegulatesType());
    }

    @Override
    public List<TitanGoTerm> positivelyRegulates_outNodes() {
        return outToMany_Nodes(graph().titanPositivelyRegulatesType());
    }

    // negativelyRegulates
    // incoming
    @Override
    public List<TitanNegativelyRegulates> negativelyRegulates_in() {
        return inFromMany(graph().titanNegativelyRegulatesType());
    }

    @Override
    public List<TitanGoTerm> negativelyRegulates_inNodes() {
        return inFromMany_Nodes(graph().titanNegativelyRegulatesType());
    }

    // // outgoing
    @Override
    public List<TitanNegativelyRegulates> negativelyRegulates_out() {
        return outToMany(graph().titanNegativelyRegulatesType());
    }

    @Override
    public List<TitanGoTerm> negativelyRegulates_outNodes() {
        return outToMany_Nodes(graph().titanNegativelyRegulatesType());
    }

    // // partOf
    // // incoming
    @Override
    public List<TitanPartOf> partOf_in() {
        return inFromMany(graph().titanPartOfType());
    }

    @Override
    public List<TitanGoTerm> partOf_inNodes() {
        return inFromMany_Nodes(graph().titanPartOfType());
    }

    // // outgoing
    @Override
    public List<TitanPartOf> partOf_out() {
        return outToMany(graph().titanPartOfType());
    }

    @Override
    public List<TitanGoTerm> partOf_outNodes() {
        return outToMany_Nodes(graph().titanPartOfType());
    }

    // // partOf
    // // incoming
    @Override
    public List<TitanHasPartOf> hasPartOf_in() {
        return inFromMany(graph().titanHasPartOfType());
    }

    @Override
    public List<TitanGoTerm> hasPartOf_inNodes() {
        return inFromMany_Nodes(graph().titanHasPartOfType());
    }

    // // outgoing
    @Override
    public List<TitanHasPartOf> hasPartOf_out() {
        return outToMany(graph().titanHasPartOfType());
    }

    @Override
    public List<TitanGoTerm> hasPartOf_outNodes() {
        return outToMany_Nodes(graph().titanHasPartOfType());
    }

    // MolecularFunction
    // outgoing
    public TitanMolecularFunction molecularFunction_out() {
        return outToOne(graph().titanMolecularFunctionType());
    }

    public TitanSubOntologies molecularFunction_outNodes() {
        return outToOne_Node(graph().titanMolecularFunctionType());
    }

    // BiologicalProcess
    // outgoing
    public BiologicalProcess biologicalProcess_out() {
        return outToOne(graph().titanBiologicalProcessType());
    }

    public SubOntologies biologicalProcess_outNodes() {
        return outToOne_Node(graph().titanBiologicalProcessType());
    }

    // CellularComponent
    // outgoing
    public CellularComponent cellularComponent_out() {
        return outToOne(graph().titanCellularComponentType());
    }

    public TitanSubOntologies cellularComponent_outNodes() {
        return outToOne_Node(graph().titanCellularComponentType());
    }

//    // /////////////////////// extras ////////////////////////////////////
//
//    // goAnnotation
//    // incoming
//    // TODO implement
//    @Override
//    public List<GoAnnotation> goAnnotation_in() {
//        return null;
//    }
//
//    @Override
//    public List<Protein> goAnnotation_inNodes() {
//        return null;
//    }


}


// package com.bio4j.titan.model;

// /*
//   A typed relationship with typed source and target. 

//   - `S` the source Node
//   - `ST` the source Node type
//   - `R` the relationship
//   - `RT` the relationship type
//   - `T` the target Node
//   - `TT` the target Node type

//   @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
// */
// public interface RelationshipTitan <
//   S extends NodeTitan<S,ST>,
//   ST extends Enum<ST> & NodeType<S,ST>,
//   R extends RelationshipTitan<S,ST,R,RT,T,TT>, 
//   RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
//   T extends NodeTitan<T,TT>,
//   TT extends Enum<TT> & NodeType<T,TT>
// > extends Element<R,RT> {

//   /*
//     Its type
//   */
//   public RT getType();

//   /*
//     source node
//   */
//   public S source();
//   /*
//     target node
//   */
//   public T target();
// }

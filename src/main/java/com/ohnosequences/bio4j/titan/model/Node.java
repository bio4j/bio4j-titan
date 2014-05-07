package com.ohnosequences.bio4j.titan.model;

import com.thinkaurelius.titan.core.TitanVertex;

/*
 A typed node.

 @author <a href="mailto:ppareja@ohnosequences.com">Pablo Pareja-Tobes</a>
 */
public abstract class Node implements TitanVertex, com.bio4j.model.Node<Node<N,T>, T> {

}

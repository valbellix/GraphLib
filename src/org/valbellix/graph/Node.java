package org.valbellix.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * This is the representation of a node with its information and out edges.
 *
 */
public class Node {
	private Object info;
	private Set<Edge> out;
	
	public Node(Object info) {
		this.info = info;
		this.out = new HashSet<Edge>();
	}
	
	public Object getInfo() {
		return info;
	}
	
	public Set<Edge> outEdges() {
		return out;
	}
	
	public boolean addEdge(Edge edge) {
		return out.add(edge);
	}
	
	public int hashCode() {
		return info.hashCode();
	}
	
	public boolean equals(Object o) {
		if (o != null) {
			if (this.getClass().equals(o.getClass())) {
				Node n = (Node)o;
				
				return n.info.equals(this.info);
			}
		}
		
		return false;
	}
	
	public String toString() {
		return info.toString();
	}
}

package org.valbellix.graph;

public class Edge {
	private Node from;
	private Node to;
	private int weight;
	
	public Edge(Node from, Node to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	
	public Node from() {
		return from;
	}
	
	public Node to() {
		return to;
	}
	
	public int weight() {
		return weight;
	}
	
	public boolean equals(Object o) {
		if (o != null) {
			if (this.getClass().equals(o.getClass())) {
				Edge e = (Edge)o;
				
				return this.from.equals(e.from) 
						&& this.to.equals(e.to);
			}
		}
		
		return false;
	}
	
	public int hashCode() {
		return from.hashCode() + 31*to.hashCode();
	}
}

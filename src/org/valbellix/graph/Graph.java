package org.valbellix.graph;

import java.util.Set;

/**
 * This class abstracts the basic concepts of a graph
 */
public abstract class Graph {
	protected Node root;
	
	public Graph() {
		root = null;
	}
	
	public abstract Set<Node> getNodes();
	public abstract Set<Edge> getEdges();
	
	public abstract Node root();
	
	public abstract void setRoot(Node root);
	
	public abstract Edge addNode(Node from, Object destInfo, int weight);
	
	public float averageOutDegree() {
		if (getNodes().size() == 0)
			return 0;
		
		int totOutEdges = 0;
		for (Node n: getNodes()) {
			totOutEdges += n.outEdges().size();
		}
		
		return totOutEdges / getNodes().size();
	}
	
	public abstract void visitNode(Node n);
	public abstract void visitEdge(Edge e);
	public void visit(VisitType type) {
		
	}
}

package org.valbellix.graph;

import java.util.Set;

public interface Graph {
	public Set<Node> getNodes();
	public Set<Edge> getEdges();
	
	public Node root();
	
	public void visit();
	
	public void setRoot(Node root);
	public Edge addNode(Node from, Object destInfo, int weight);
}

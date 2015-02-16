package org.valbellix.graph;

import java.util.HashSet;
import java.util.Set;

public class AdjListGraph implements Graph {
	private Set<Node> nodes;
	private Set<Edge> edges;
	private Node root;
	
	public AdjListGraph() {
		root = null;
		nodes = new HashSet<Node>();
		edges = new HashSet<Edge>();
	}

	@Override
	public Set<Node> getNodes() {
		return nodes;
	}

	@Override
	public Set<Edge> getEdges() {
		return edges;
	}

	@Override
	public Node root() {
		return this.root;
	}

	@Override
	public void visit() {
		// TODO Auto-generated method stub

	}
	
	public void setRoot(Node root) {
		if (this.root == null) {
			this.root = root;
			
			nodes.add(root);
		}
	}
	
	public Edge addNode(Node from, Object destInfo, int weight) {
		Node dest = new Node(destInfo);
		Edge destEdge = new Edge(from, dest, weight);
		
		if (dest.addEdge(destEdge)) {
			nodes.add(dest);
			edges.add(destEdge);
			
			return destEdge;
		}
		else
			return null;
	}
}

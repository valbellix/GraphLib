package org.valbellix.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * This implements the operation of a graph described in terms of adjacency list
 */
public class AdjListGraph extends Graph {
	private Set<Node> nodes;
	private Set<Edge> edges;
	
	public AdjListGraph() {
		super();
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
	public Node getRoot() {
		return this.root;
	}
	
	@Override
	public void visitNode(Node n) {
		System.out.println(n.toString());
	}
	
	@Override
	public void visitEdge(Edge e) {
		// TODO: implement
	}
	
	@Override
	public void setRoot(Node root) {
		if (this.root == null) {
			this.root = root;
			
			nodes.add(root);
		}
	}
	
	public Edge addNode(Node from, Object destInfo, int weight) {
		Node dest = new Node(destInfo);
		Edge destEdge = new Edge(from, dest, weight);
		
		if (from.addEdge(destEdge)) {
			nodes.add(dest);
			edges.add(destEdge);
			
			return destEdge;
		}
		else
			return null;
	}
}

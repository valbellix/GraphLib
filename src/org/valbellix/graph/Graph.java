package org.valbellix.graph;

import java.util.ArrayDeque;
import java.util.Deque;
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
	
	public abstract Node getRoot();
	
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
		if (type == VisitType.BFS)
			visitBFS();
		if (type == VisitType.DFS)
			visitDFS();
	}
	
	public void unmarkNodes() {
		for (Node n: getNodes()) {
			n.unmark();
		}
	}
	
	private void visitBFS() {
		unmarkNodes();
		
		Deque<Node> queue = new ArrayDeque<Node>();
		
		queue.add(root);
		while (!queue.isEmpty()) {
			Node current = queue.pop();
			if (current.isMarked())
				continue;
			
			visitNode(current);
			
			current.mark();
			for (Edge edge: current.outEdges()) {
				if (!edge.to().isMarked())
					queue.add(edge.to());
			}
		}
	}
	
	private void visitDFS() {
		unmarkNodes();
		
		Deque<Node> queue = new ArrayDeque<Node>();
		
		queue.push(root);
		while (!queue.isEmpty()) {
			Node current = queue.pop();
			if (current.isMarked())
				continue;
			
			visitNode(current);
			
			current.mark();
			for (Edge edge: current.outEdges()) {
				if (!edge.to().isMarked())
					queue.push(edge.to());
			}
		}
	}
}

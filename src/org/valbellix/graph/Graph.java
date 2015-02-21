package org.valbellix.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;

/**
 * This class abstracts the basic concepts of a graph. This representation describes a Graph as a set
 * of <b>nodes</b> and <b>edges</b>. How these could be organized internally is demanded to the specific
 * implementation chosen for a graph (i.e. matrix vs adjacency list).
 *  
 * It allows to override the main aspects that leads to different implementations of a graph. 
 * Look to <b>AdjListGraph</b> to see how these abstract methods can be implemented 
 * obtaining a different representation of a Graph.
 */
public abstract class Graph {
	protected Node root;
	
	/**
	 * This is the constructor of the Graph class
	 */
	public Graph() {
		root = null;
	}
	
	/** 
	 * @return the nodes
	 */
	public abstract Set<Node> getNodes();
	
	/**
	 * @return the edges
	 */
	public abstract Set<Edge> getEdges();
	
	/**
	 * @return the root node of the graph
	 */
	public abstract Node getRoot();
	
	/**
	 * It sets the root node of the graph
	 * @param root
	 */
	public abstract void setRoot(Node root);
	
	/**
	 * It adds a node to the graph, returning the edge
	 * @param from the existing node we want to connect
	 * @param destInfo the information we want to the destination node
	 * @param weight the weight of the edge to create
	 * @return the resulting edge
	 */
	public Edge addNode(Node from, Object destInfo, int weight) {
		Node n = new Node(destInfo);
		
		return addNode(from, n, weight);
	}
	
	/**
	 * It adds a node to the graph, returning the edge
	 * @param from the existing node we want to connect as origin
	 * @param dest the existing node we want to connect as destination
	 * @param weight the weight of the edge to create
	 * @return the resulting edge
	 */
	public abstract Edge addNode(Node from, Node dest, int weight);
	
	/**
	 * @return the outbound degree of the graph as average of the outbound edges of the nodes
	 */
	public float averageOutDegree() {
		if (getNodes().size() == 0)
			return 0;
		
		int totOutEdges = 0;
		for (Node n: getNodes()) {
			totOutEdges += n.outEdges().size();
		}
		
		return totOutEdges / getNodes().size();
	}
	
	/**
	 * This will be called inside the visit of a graph. The implementation should contain the 
	 * code that realize the visit of the node.
	 * @param n the node to visit
	 */
	public abstract void visitNode(Node n);
	
	/**
	 * This will be called inside the visit of a graph. The implementation should contain the 
	 * code that realize the visit of the edge.
	 * @param e the edge to visit
	 */
	public abstract void visitEdge(Edge e);
	
	/**
	 * This method visit the graph according to the visit type specified. It calls visitNode and 
	 * visitEdge.
	 * @param type it can be BFS or DFS
	 */
	public void visit(VisitType type) {
		unmarkNodes();
		
		visitFromNode(this, root, type);
	}
	
	/**
	 * This can be used to unmark all the nodes of the graph during a visit
	 */
	public void unmarkNodes() {
		for (Node n: getNodes()) {
			n.unmark();
		}
	}
	
	// this is the implementation of the visit algorithm
	private static void visitFromNode(Graph g, Node n, VisitType type) {
		Deque<Node> queue = new ArrayDeque<Node>();
		
		if (type == VisitType.BFS)
			queue.addLast(n);
		else
			queue.push(n);
		
		while (queue.isEmpty()) {
			Node current = queue.pop();
			if (current.isMarked())
				continue;
			
			g.visitNode(current);
			
			current.mark();
			for (Edge edge: current.outEdges()) {
				if (!edge.to().isMarked()) {
					g.visitEdge(edge);
					
					if (type == VisitType.BFS)
						queue.addLast(edge.to());
					else
						queue.push(edge.to());
				}
			}
		}
	}
}

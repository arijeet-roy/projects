package com.assignments;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class GraphTraversal {

	//An array of vertices. Each will have an adjacency list linked to it in a linked list format.
	private Vertex[] vertices;
	
	public GraphTraversal(int num_Vertices) {
		// TODO Auto-generated constructor stub
		vertices = new Vertex[num_Vertices];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				Scanner scanner = new Scanner(System.in);
				//no of vertices
				System.out.println("Enter the number of vertices : ");
				int num_vertices = scanner.nextInt();
				//no of edges
				System.out.println("Enter the number of edges : ");
				int num_edges = scanner.nextInt();
				
				GraphTraversal graph = new GraphTraversal(num_vertices);
				for (int i = 0; i < num_vertices; i++) {
					graph.vertices[i] = graph.new Vertex(i+1);
				}
				
				//add edges
				System.out.println("Enter the "+num_edges+" edges : ");
				for (int i = 0; i < num_edges; i++) {
					int vertex1 = scanner.nextInt();
					int vertex2 = scanner.nextInt();
					graph.addEdge(vertex1, vertex2);
				}
				
				//to cover all the vertices in dfs we need to check for every vertex as source. 
				//For example in case of disconnected graph.
				System.out.println("----- DFS traversal of the graph -----");
				for (int i = 0; i < num_vertices; i++) {
					if(!graph.vertices[i].isVisited) {
						graph.dfsSearch(graph.vertices[i]);
					}
				}
				
				//after dfs traversal change the isVisited flag of the vertices to false 
				//so that the same graph can be used for bfs traversal.
				for (int i = 0; i < num_vertices; i++) {
					graph.vertices[i].isVisited = false;
				}
				
				System.out.println("----- BFS traversal of the graph -----");
				//take source as vertex 0
				graph.bfsSearch(graph.vertices[0]);
				scanner.close();
	}

	//using queue for iterative bfs
	private void bfsSearch(Vertex vertex) {
		// TODO Auto-generated method stub
		Queue<Vertex> bfs_queue = new LinkedList<>();
		bfs_queue.add(vertex);
		vertex.isVisited = true;
		
		while(bfs_queue.size() > 0) {
			Vertex addElement = bfs_queue.poll();
			System.out.println(addElement.data);
			
			if(addElement.neighbors.size() > 0) {
				for (int i = 0; i < addElement.neighbors.size(); i++) {
					Vertex neighbor = addElement.neighbors.get(i);
					if(!neighbor.isVisited) {
						bfs_queue.add(neighbor);
						neighbor.isVisited = true;
					}
				}			
			}
		}
		
	}

	//a recursive dfs search
	private void dfsSearch(Vertex vertex) {
		// TODO Auto-generated method stub
		vertex.isVisited = true;
		System.out.println(vertex.data);
		
		if(vertex.neighbors.size() > 0) {
			for (int i = 0; i < vertex.neighbors.size(); i++) {
				Vertex neighbor = vertex.neighbors.get(i);
				if(!neighbor.isVisited) {
					dfsSearch(neighbor);
				}
			}			
		}
		
	}

	//method to add edge between two vertices
	private void addEdge(int vertex1, int vertex2) {
		// TODO Auto-generated method stub
		this.vertices[vertex1 - 1].addEdge(this.vertices[vertex2 - 1]);
	}

	class Vertex {
		int data;
		LinkedList<Vertex> neighbors;
		boolean isVisited;
		
		Vertex(int data) {
			this.data = data;
			neighbors = new LinkedList();
			isVisited = false;
		}
		
		protected void addEdge(Vertex v) {
			// TODO Auto-generated method stub
			this.neighbors.add(v);
			v.neighbors.add(this);
		}
	}
}

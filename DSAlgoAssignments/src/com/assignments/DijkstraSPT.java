package com.assignments;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


public class DijkstraSPT {

	//An array of vertices. Each will have an adjacency list linked to it in a linked list format.
	private Vertex[] vertices;
	
	public DijkstraSPT(int num_Vertices) {
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
				
				DijkstraSPT graph = new DijkstraSPT(num_vertices);
				for (int i = 0; i < num_vertices; i++) {
					graph.vertices[i] = graph.new Vertex(i+1);
				}
				
				//add edges
				System.out.println("Enter the "+num_edges+" edges along with their weight(Format : vertex1 vertex2 weight): ");
				for (int i = 0; i < num_edges; i++) {
					int vertex1 = scanner.nextInt();
					int vertex2 = scanner.nextInt();
					int weight = scanner.nextInt();
					graph.addEdge(vertex1, vertex2, weight);
				}
				System.out.println();
				
				//Print the adjacency list with weights
				System.out.println("---Adjacency list :---");
				for (int i = 0; i < num_vertices; i++) {
					System.out.print(graph.vertices[i].data + " : ");
					for (int j = 0; j < graph.vertices[i].neighbors.size(); j++) {
						Edge neighborEdge = graph.vertices[i].neighbors.get(j);
						System.out.print(neighborEdge.destination.data + "("+ neighborEdge.weight + ") ");
					}
					System.out.println();
				}
				System.out.println();
				
				//Set vertices distance to max value and unvisited
				for (int i = 0; i < num_vertices; i++) {
					graph.vertices[i].distance = Integer.MAX_VALUE;
				}
				//Select the source vertex
				int sourceIdx;
				do {
					System.out.println("Enter the index of the source vertex(index input starts from 1)");
			        while (!scanner.hasNextInt()) scanner.next();
			        sourceIdx = scanner.nextInt();
			    } while (sourceIdx > num_vertices || sourceIdx < 1);
				System.out.println();
				
				sourceIdx--;
				graph.vertices[sourceIdx].distance = 0;
				
				graph.shortestPath(sourceIdx);
				
				//print vertices with their shortest path parent vertices.
				PriorityQueue<Vertex> printQueue = new PriorityQueue<>(graph.vertices.length, graph.new Vertex());
				printQueue.add(graph.vertices[sourceIdx]);
				System.out.println("Printing the edges of the shortest path starting from the source : ");
				while(!printQueue.isEmpty()) {
					Vertex parent = printQueue.poll();
					for (Vertex vertex : graph.vertices) {
						if(vertex.parent == parent) {
							System.out.println(vertex.parent.data + " -> " + vertex.data);
							printQueue.add(vertex);
						}
					}
					
				}
				
				
				scanner.close();
	}

	private void shortestPath(int sourceIdx) {
		// TODO Auto-generated method stub
		PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>(vertices.length, new Vertex());
		priorityQueue.add(vertices[sourceIdx]);
		//until queue is empty
		while(!priorityQueue.isEmpty()) {
			Vertex vertex = priorityQueue.poll();
			LinkedList<Edge> neighborEdges = vertex.neighbors;
			for (Edge neighborEdge : neighborEdges) {
				if(!neighborEdge.isVisited) {
					//mark as visited
					neighborEdge.isVisited = true;
					if(vertex.distance + neighborEdge.weight < neighborEdge.destination.distance) {
						//update distance and parent to the vertex
						neighborEdge.destination.distance = vertex.distance + neighborEdge.weight;
						neighborEdge.destination.parent = vertex;
						if(priorityQueue.contains(neighborEdge.destination)) {
							priorityQueue.remove(neighborEdge.destination);
						}
						priorityQueue.add(neighborEdge.destination);
						
					}
					
				}
			}
		}
		
	}

	//method to add edge between two vertices
	private void addEdge(int vertex1, int vertex2, int weight) {
		// TODO Auto-generated method stub
		this.vertices[vertex1 - 1].addEdge(this.vertices[vertex2 - 1], weight);
	}

	class Edge {
		Vertex destination;
		int weight;
		boolean isVisited;
		
		Edge(Vertex destination, int weight, boolean isVisited) {
			this.destination = destination;
			this.weight = weight;
			this.isVisited = isVisited;
		}
		
	}
	
	class Vertex implements Comparator<Vertex>{
		int data;
		int distance;
		Vertex parent;
		LinkedList<Edge> neighbors;
		
		Vertex() {
		}
		
		Vertex(int data) {
			this.data = data;
			neighbors = new LinkedList();
			parent = null;
		}
		
		protected void addEdge(Vertex v, int weight) {
			// TODO Auto-generated method stub
			//add edge along with the weight
			this.neighbors.add(new Edge(v, weight, false));
			v.neighbors.add(new Edge(this, weight, false));
		}

		@Override
		public int compare(Vertex o1, Vertex o2) {
			// TODO Auto-generated method stub
			return o1.distance - o2.distance;
		}
	}
}

package com.hackerrank.eventree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Solution {

	private Vertex vertices[];
	
	public Solution(int size) {
		// TODO Auto-generated constructor stub
		vertices = new Vertex[size];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		
		Solution graph = new Solution(n);
		for (int i = 0; i < n; i++) {
			graph.vertices[i] = new Vertex(i+1);
		}
		
		//add edges
		for (int i = 0; i < m; i++) {
			int vertex1 = scanner.nextInt();
			int vertex2 = scanner.nextInt();
			graph.vertices[vertex1 - 1].addEdge(graph.vertices[vertex2 - 1]);
		}
		
		int rootChildren = graph.countChildNodes(graph.vertices[0]);
		
		int edgeCount = 0;
		for (Vertex vertex : graph.vertices) {
			if((vertex.numChildren+1) % 2 == 0) edgeCount++;
		}
		if(rootChildren % 2 == 0 || edgeCount == 0) edgeCount --;
		System.out.println(edgeCount);
		scanner.close();

	}

	private int countChildNodes(Vertex vertex) {
		// TODO Auto-generated method stub
		vertex.isVisited = true;
		if(vertex.neighbors.size() == 1 && vertex.neighbors.get(0).isVisited) {
			vertex.numChildren = 0;
			return 1;
		}
		int count = 0;
		for (Iterator<Vertex> iterator = vertex.neighbors.iterator(); iterator.hasNext();) {
			Vertex v1 = (Vertex) iterator.next();
			if(v1.isVisited) continue;
			count = count + countChildNodes(v1);
			
		}
		vertex.numChildren = count;
		count++;
		return count;
	}
	

}

class Vertex {
	int data;
	List<Vertex> neighbors;
	int numChildren;
	boolean isVisited;
	
	Vertex(int data) {
		this.data = data;
		neighbors = new ArrayList<>();
		numChildren = 0;
		isVisited = false;
	}
	
	protected void addEdge(Vertex v) {
		// TODO Auto-generated method stub
		this.neighbors.add(v);
		v.neighbors.add(this);
	}
}

package com.algos.practice;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class EvenTree1 {
		
	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. 
         * Your class should be named Solution. */
    	Scanner in = new Scanner(System.in);
    	int n = in.nextInt();
    	int m = in.nextInt();
    	Graph graph = new Graph(n);
    	for (int i = 0; i < n; i++) {
    		graph.vertices[i] = new Vertex(i + 1);
    	}
    	for (int j = 0; j < m; j++) {
    		int idu = in.nextInt();
    		int idv = in.nextInt();
    		graph.vertices[idu-1].addEdge(graph.vertices[idv-1]);
    	}
    	in.close();
    	Vertex root = graph.vertices[0];
    	graph.countChildren(root);
    	System.out.println("Root number of children : "+graph.countChildren(root));
    	System.out.println("Root explored : "+graph.explored.size());
    	int result = 0;
    	for (Vertex v : graph.vertices) {
    		if (v.numberOfChildren % 2 == 0) {
    			result++;
    		}
    	}
    	System.out.println(result - 1);
    }
}

class Graph {
	Vertex[] vertices;
	HashSet<Vertex> explored;
	public Graph(int n) {
		vertices = new Vertex[n];
		explored = new HashSet<Vertex>();
	}
	public int countChildren(Vertex v) {
		if (explored.contains(v)) {
			return v.numberOfChildren;
		} else if (v.neighbours.size() == 1) {
			v.numberOfChildren = 1;
			explored.add(v);
			return 1;
		} else {
			explored.add(v);
			int count = 1;
			for (Vertex u : v.neighbours) {
				// System.out.println(u.id);
				count = count + countChildren(u);
			}
			v.numberOfChildren = count;
			return count;
		}
	}
}

class Vertex {
	int id;
	ArrayList<Vertex> neighbours;
	int numberOfChildren;
	public Vertex(int id){
		this.id = id;
		neighbours = new ArrayList<Vertex>();
		numberOfChildren = 0;
	}
	public void addEdge(Vertex v){
		neighbours.add(v);
		v.neighbours.add(this);
	}
}
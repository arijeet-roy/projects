package com.algos.practice;

//A Java program for Floyd Warshall All Pairs Shortest
//Path algorithm.
import java.util.*;
import java.lang.*;
import java.io.*;


class ShortestPath
{
 final static int INF = 99999, V = 4;

 void floydWarshall(int graph[][])
 {
     int dist[][] = new int[V][V];
     int i, j, k;

     for (i = 0; i < V; i++)
         for (j = 0; j < V; j++)
             dist[i][j] = graph[i][j];

     for (k = 0; k < V; k++)
     {
         // Pick all vertices as source one by one
         for (i = 0; i < V; i++)
         {
             // Pick all vertices as destination for the
             // above picked source
             for (j = 0; j < V; j++)
             {
                 // If vertex k is on the shortest path from
                 // i to j, then update the value of dist[i][j]
            	 if(dist[i][k] == 0 || dist[k][j] == 0) continue;
                 if (dist[i][k] + dist[k][j] < dist[i][j])
                     dist[i][j] = dist[i][k] + dist[k][j];
             }
         }
     }

     // Print the shortest distance matrix
     printSolution(dist);
 }

 void printSolution(int dist[][])
 {
	 int totalSum = 0;
     for (int i = 0; i < dist.length - 1; i++) {
		for (int j = i+1; j < dist[0].length; j++) {
			System.out.print(dist[i][j] + " ");
			totalSum += dist[i][j];
		}
		System.out.println();
	}
     System.out.println(totalSum);
 }

 // Driver program to test above function
 public static void main (String[] args)
 {
	 Scanner scanner = new Scanner(System.in);
	 int m, n;
	 m=5;n=6;
	 int graph[][] = new int[m][m];
	 for (int i = 0; i < graph.length; i++) {
		for (int j = 0; j < graph[0].length; j++) {
			graph[i][j] = 0;
		}
	}
	 
	 for (int i = 0; i < n; i++) {
		int v1 = scanner.nextInt();
		int v2 = scanner.nextInt();
		int weight = scanner.nextInt();
		graph[v1 - 1][v2 - 1] = (int) Math.pow(2, weight);
	}
	 
	 
     
     ShortestPath a = new ShortestPath();

     // Print the solution
     a.floydWarshall(graph);
     scanner.close();
 }
}

package com.hackerrank.shortestpaths;

import java.util.*;
import java.lang.*;
import java.io.*;


class Solution
{

void floydWarshall(int graph[][])
{
   int dist[][] = new int[graph.length][graph.length];
   int i, j, k;

   for (i = 0; i < graph.length; i++)
       for (j = 0; j < graph.length; j++)
           dist[i][j] = graph[i][j];

       for (i = 0; i < graph.length; i++)
       {
           for (j = 0; j < graph.length; j++)
           {
          	 for (k = 0; k < graph.length; k++)
          	 {
              
          	 if(dist[i][k] == 0 || dist[k][j] == 0) continue;
          	 if(dist[i][j] != 0) {
          		 if (dist[i][k] + dist[k][j] < dist[i][j])
          			 dist[i][j] = dist[i][k] + dist[k][j];            		 
          	 }
          	 else {
          		 if(i == j) continue;
          		 dist[i][j] = dist[i][k] + dist[k][j];  
          	 }
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
			totalSum += dist[i][j];
		}
	 }
   Stack<Integer> integers = new Stack<>();
   while(totalSum > 1) {
	 integers.push(totalSum%2);
  	 totalSum /= 2;
   }
   integers.push(totalSum);
   while (!integers.isEmpty()) {
  	 System.out.print(integers.pop()+"");
   }
   System.out.println();
}

// Driver program to test above function
public static void main (String[] args)
{
	 Scanner scanner = new Scanner(System.in);
	 int m, n;
	 m = scanner.nextInt();
	 n = scanner.nextInt();
	 int graph[][] = new int[m][m];
	 
	 for (int i = 0; i < n; i++) {
		int v1 = scanner.nextInt();
		int v2 = scanner.nextInt();
		int weight = scanner.nextInt();
		graph[v1 - 1][v2 - 1] = (int) Math.pow(2, weight);
		graph[v2 - 1][v1 - 1] = (int) Math.pow(2, weight);
	}
	
	 
   Solution a = new Solution();

   // Print the solution
   a.floydWarshall(graph);
   scanner.close();
}
}


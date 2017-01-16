package com.hackerrank.luckynumber;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

	public Solution() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		long nums[] = new long[n];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = scanner.nextLong();
		}
		ArrayList<Integer> coins = new ArrayList<>();
		coins.add(4);
		coins.add(7);
		
		for (int i = 0; i < nums.length; i++) {
			if(make_change((int)nums[i], coins) == 0) {
				System.out.println("No");
			}
			else
				System.out.println("Yes");
		}
		scanner.close();
		
	}

	private static int getCount(long num, ArrayList<Integer> coins, int start, int end) {
		// TODO Auto-generated method stub
		if(num < 0) return 0;
		if(num == 0) return 1;
		if(start > end) return 0;
		int lCount = getCount(num - coins.get(end), coins, start, end);
		int rCount = getCount(num, coins, start, end - 1);
		return lCount + rCount;
	}
	
	private static long make_change(int n, ArrayList<Integer> coins) {
	    int m = coins.size();
	    long cache[][] = new long[n+1][m+1];
	    
	    for (int i = 0; i <= n; i++) {
	        for (int j = 0; j <= m; j++) {
	            cache[i][j] = 0;
	        }
	    }
	    for (int j = 0; j <= m; j++) {
	        cache[0][j] = 1;
	    }
	    for (int i = 0; i <= n; i++) {
	        cache[i][0] = 0;
	    }
	    
	    for(int i = 1; i <= n; i++) {
	        for(int j = 1; j <= m; j++) {
	            cache[i][j] = ((i >= coins.get(j - 1)) ? cache[i - coins.get(j - 1)][j] : 0)
	            + cache[i][j - 1];
	        }
	    }
	    return cache[n][m];
	}

}

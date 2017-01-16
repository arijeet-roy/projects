package com.hackerrank.liswithsequence;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

	public Solution() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int numItems = scanner.nextInt();
		int inputArr[] = new int[numItems];
		for (int i = 0; i < inputArr.length; i++) {
			inputArr[i] = scanner.nextInt();
		}
		
		Solution solution = new Solution();
		System.out.println(solution.findLIS(inputArr));
	}

	private int findLIS(int[] inputArr) {
		// TODO Auto-generated method stub
		ArrayList<Integer> lis = new ArrayList<>();
		int []prev = new int[inputArr.length];
		for (int i = 0; i < prev.length; i++) {
			prev[i] = -1;
		}
		lis.add(0);
		int bestEnd = 0;
		for (int i = 1; i < inputArr.length; i++) {
			if(inputArr[i] > inputArr[lis.get(lis.size() - 1)]) {
				prev[i] = i - 1;
				bestEnd = i;
				lis.add(i);
			}
			else {
				int pos = findPos(lis, inputArr, inputArr[i], 0, lis.size() - 1);
				prev[i] = prev[lis.get(pos)];
				if(pos < lis.size() - 1) 
					prev[lis.get(pos + 1)] = i;
				lis.remove(pos);
				lis.add(pos, i);
			}
		}
		int current = bestEnd;
		while(current != -1) {
			System.out.println(inputArr[current]);
			current = prev[current];
		}
		return lis.size();
		
	}

	private int findPos(ArrayList<Integer> lis, int[] inputArr, int num, int start, int end) {
		// TODO Auto-generated method stub
		if (start == end) return start;
		else if(start < end) {
			int mid = (start + end) / 2;
			if(num > inputArr[lis.get(mid)]) {
				return findPos(lis, inputArr, num, mid + 1, end);
			}
			else if(num < inputArr[lis.get(mid)]) {
				return 1 + findPos(lis, inputArr, num, start, mid - 1);
			}
			else {
				return mid;
			}
			
		}
		else {
			return -1;
		}
	}

}

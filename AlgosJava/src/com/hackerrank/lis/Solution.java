package com.hackerrank.lis;

import java.util.ArrayList;
import java.util.Arrays;
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
		scanner.close();
	}

	private int findLIS(int[] inputArr) {
		// TODO Auto-generated method stub
		if(inputArr == null || inputArr.length == 0) return 0;
		ArrayList<Integer> lis = new ArrayList<Integer>(); 
		for (int i = 0; i < inputArr.length; i++) {
			if(lis.size() == 0 || inputArr[i] > inputArr[lis.get(lis.size() - 1)]) {
				lis.add(i);
			}
			else {
	            int pos = findPos(lis, inputArr, inputArr[i], 0, lis.size() - 1);
				lis.set(pos, i);
			}
		}
		
		return lis.size();
		
	}
	
	private int findPos(ArrayList<Integer> lis, int[] inputArr, int num, int start, int end) {
		
		int j=start; 
        int k=end;

        while(j<k){
            int mid = (j+k)/2;
            if(inputArr[lis.get(mid)] < num){
                j=mid+1;
            }else{
                k=mid;
            }
        }
        return k;
	}

}

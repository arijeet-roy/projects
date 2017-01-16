package com.hackerrank.candypiles;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public Solution() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int arr[] = new int[n];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = scanner.nextInt();
		}
		Arrays.sort(arr);
		if(arr.length == 1) {
			System.out.println(arr[0] * 2 + " 1");
		}
		else {
			int minHappyness = 0;
			if(arr[0] * 2 > arr[1])	{
				minHappyness = arr[1];
			}
			else {
				minHappyness = arr[0] * 2;
			}
			
			int num = 1;
			for (int i = 1; i < arr.length; i++) {
				if(arr[i] == arr[0]) num++;
			}
			
			System.out.println(minHappyness + " " + num);
		}
		scanner.close();
	}

}

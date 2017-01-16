package com.hackerrank.cipher;

import java.util.Scanner;

public class Solution {

	public Solution() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		scanner.nextLine();
		String input = scanner.nextLine();
		
		char[] inputArr = input.toCharArray();
		char[] result = new char[n];
		result[0] = inputArr[0];
		char temp = result[0];
		
		for(int i=1; i<n; i++) {
			if(i < k) {
				result[i] = Character.forDigit(inputArr[i] ^ temp, 2); 
				temp = Character.forDigit(temp ^ result[i], 2);
			}
			else {
				temp = Character.forDigit(temp ^ result[i - k], 2);
				result[i] = Character.forDigit(temp ^ inputArr[i], 2) ;
				temp = Character.forDigit(temp ^ result[i], 2);
			}
		}
		
		String res = new String(result);
		System.out.println(res);
		scanner.close();
	}

}

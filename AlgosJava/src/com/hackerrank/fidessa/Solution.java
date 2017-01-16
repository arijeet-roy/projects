package com.hackerrank.fidessa;

import java.util.Scanner;

public class Solution {

	public Solution() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		int sides[][] = new int[num][3];
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < 3; j++) {
				sides[i][j] = scanner.nextInt();
			}
		}
		
		for (int i = 0; i < num; i++) {
			if(sides[i][0] == sides[i][1] && sides[i][1] == sides[i][2]) {
				System.out.println("Equilateral");
			}
			else if(sides[i][0] == sides[i][1] || sides[i][1] == sides[i][2]
					|| sides[i][0] == sides[i][2]) {
				System.out.println("Isosceles");
			}
			else {
				System.out.println("None of these");
			}
		}
		scanner.close();
	}

}

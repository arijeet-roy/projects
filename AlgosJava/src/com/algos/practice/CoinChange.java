package com.algos.practice;

import java.util.ArrayList;

public class CoinChange {

	public CoinChange() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> coins = new ArrayList<>();
		coins.add(2);
		coins.add(3);
		coins.add(5);
		coins.add(6);
		int num = 4;
		
		System.out.println(getCount(num, coins, 0, coins.size() - 1));
	}

	private static int getCount(int num, ArrayList<Integer> coins, int start, int end) {
		// TODO Auto-generated method stub
		if(num < 0) return 0;
		if(num == 0) return 1;
		if(start > end) return 0;
		int lCount = getCount(num - coins.get(end), coins, start, end);
		int rCount = getCount(num, coins, start, end - 1);
		return lCount + rCount;
	}

}

package com.algos.practice;

public class Swap {

	public Swap() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {1, 2};
		System.out.println(arr[0]+" "+arr[1]);
		swap(0, 1, arr);
		System.out.println(arr[0]+" "+arr[1]);
	}

	private static void swap(int i, int j, int arr[]) {
		// TODO Auto-generated method stub
				int temp;
				temp = arr[i];
				arr[i]=arr[j];
				arr[j]=temp;
	}

}

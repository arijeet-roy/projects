package com.assignments;

import java.util.Scanner;

public class TertiarySearch {

	public TertiarySearch() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter size of array(greater than 0) :");
		int size = scanner.nextInt();
		int[] input_arr = new int[size];
		System.out.println("Enter the elements of the array :");
		for(int i=0; i<size; i++) {
			int num = scanner.nextInt();
			input_arr[i] = num;
		}
		System.out.println("Enter the element to search for :");
		int num_to_search = scanner.nextInt();
		TertiarySearch search = new TertiarySearch();
		int index = search.tertiarySearch(input_arr, 0, 
				input_arr.length - 1, num_to_search);
		System.out.println(index == -1 ? "Element not found" :
					"Element found at index : "+(index+1));
		
	}
	
	private int tertiarySearch(int[] arr, int start, int end, int num) {
		
		if(start > end) return -1;
		
		int len = end - start + 1;
		
		int div = len/3;
		if(arr[start + div] == num) return start + div;
		if(arr[start + (2*div)] == num) return start + (2*div);
		if(arr[start + div] > num) return tertiarySearch(arr, start, start + div-1, num);
		if(arr[start + div] < num && arr[start + (2*div)] > num) return tertiarySearch(arr, start+div+1, start + (2*div)-1, num);
		if(arr[start + (2*div)] < num) return tertiarySearch(arr, start + (2*div)+1, end, num);
		
		return -1;
	}

}

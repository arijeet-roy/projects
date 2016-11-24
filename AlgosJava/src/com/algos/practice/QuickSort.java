package com.algos.practice;

public class QuickSort {

	public QuickSort() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {7,5,3,1,2,4,6};
		QuickSort sortObj = new QuickSort();
//		int[] newArr = sortObj.quickSort(0, arr.length - 1, arr);
		int[] newArr = sortObj.quickSortMid(0, arr.length - 1, arr);
		for(int i=0; i<newArr.length; i++) {
			System.out.println(newArr[i]);
		}

	}
	
	private int[] quickSort(int start, int end, int[] arr) {
		if(start < end) {
			int partition = partition(start, end, arr, end);
			quickSort(start, partition - 1, arr);
			quickSort(partition + 1, end, arr);
		}
		return arr;
	}

	
	private int[] quickSortMid(int start, int end, int[] arr) {
		if(start < end) {
			int partition = partitionMid(start, end, arr);
			quickSort(start, partition - 1, arr);
			quickSort(partition + 1, end, arr);
		}
		return arr;
	}
	
	private int partitionMid(int start, int end, int[] arr) {
		// TODO Auto-generated method stub
		int i = start;
		int j = end;
		int pivot = (start + end)/2;
		
		if(i < j) {
			while(arr[i] < arr[pivot] && i < pivot) {
				i++;
			}
			while(arr[j] > arr[pivot] && j > pivot) {
				j--;
			}
			if(i<j) {
				swap(i, j, arr);
				partitionMid(start, end, arr);
			}
		}
		return pivot;
	}
	
	private int partition(int start, int end, int[] arr, int pivot) {
		// TODO Auto-generated method stub
		int i = start;
		int j = end;
		if(start < end) {
			if(pivot == start) {
				while(arr[pivot] < arr[j]) {
					j--;
				}
				if(pivot <= j) {
					swap(pivot, j, arr);
					pivot = j;
				}
			}
			else if(pivot == end){
				while(arr[pivot] > arr[i]) {
					i++;
				}
				if(pivot >= i) {
					swap(pivot, i, arr);
					pivot = i;
				}
			}
			partition(i, j, arr, pivot);
		}
		return pivot;
	}

	private void swap(int i, int j, int arr[]) {
		// TODO Auto-generated method stub
				int temp;
				temp = arr[i];
				arr[i]=arr[j];
				arr[j]=temp;
	}

}

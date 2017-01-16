#include <stdio.h>
#include <iostream>

using namespace std;

int binarySearch(int arr[], int start, int end, int num);

int main(void) {
	int arr[] = {1,4,7,9,11};
	int size = sizeof(arr)/sizeof(arr[0]);

	int pos = binarySearch(arr, 0, size - 1, 6);
	cout<<"Position found is "<<pos;
}

int binarySearch(int arr[], int start, int end, int num) {

	int mid = start + (end - start)/2;

	if(mid > 0) {
	  if(arr[mid] == num) return mid;

	  if(arr[mid] < num) return binarySearch(arr, mid + 1, end, num);

	  if(arr[mid] > num) return binarySearch(arr, start, mid - 1, num);
	}

	return -1;
}

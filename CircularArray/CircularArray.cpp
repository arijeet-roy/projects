//============================================================================
// Name        : CircularArray.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C, Ansi-style
//============================================================================

#include <stdio.h>
#include <stdlib.h>
#include <iostream>

using namespace std;

void rotateArray(int n, int k, int q, int arr[], int check[]);

int main(void) {
	int n, k, q;
//	cout << "Enter three numbers";
	cin >> n >> k >> q;
//	cout << endl;
	int arr[n];
	int check[q];

//	cout << "Enter " << n << " numbers for the array input";
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}

//	cout << endl;
//	cout << "Enter " << q << " numbers for the checking input";
	for (int i = 0; i < q; i++) {
		cin >> check[i];
//		cout << endl;
	}

	rotateArray(n, k, q, arr, check);
	return 0;
}

void rotateArray(int n, int k, int q, int arr[], int check[]) {
	k = k % n;

	if (k > 0) {
		for (int i = 0; i < k; i++) {
			int temp = arr[n-1];
			for(int j=0; j<n-1; j++) {
				arr[n-1-j] = arr[n-2-j];
			}
			arr[0] = temp;
		}
	}

	for(int i=0; i<q; i++) {
		check[i] = check[i]%n;
	}

	//print the array
	for(int x=0; x<q; x++) {
		cout<<arr[check[x]]<<endl;
	}
}

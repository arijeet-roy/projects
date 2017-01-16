//============================================================================
// Name        : PrimeCheck.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C, Ansi-style
//============================================================================

#include <stdio.h>
#include <stdlib.h>
#include <iostream>

using namespace std;

bool isPrimeNum(int n);

int main(void) {
	int num;
	cout<<"Enter a positive integer";
	cin>>num;

	bool isPrime = isPrimeNum(num);
	cout<<"The number is :" <<isPrime;
}

bool isPrimeNum(int num) {
	if(num == 1) return false;
	if(num == 2) return true;
	if(num % 2 == 0) return false;
	for(int i=3; (i*i) <= num; i+=2) {
		if(num % i == 0) return false;
	}
	return true;
}

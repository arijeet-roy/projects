//============================================================================
// Name        : Arrays.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C, Ansi-style
//============================================================================

#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <vector>

using namespace std;

int main(void) {
	int RR = 5, CC = 5;
	vector<vector<int> > matrix(RR);
	for(int i=0; i<5; i++) {
		matrix[i].resize(CC);
	}

	for(int i=0; i<matrix.size(); i++) {
		for(int j=0; j<matrix[i].size();j++) {
			cout<<matrix[i][j];
		}
		cout<<endl;
	}
}

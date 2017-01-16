//============================================================================
// Name        : Overloading.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C, Ansi-style
//============================================================================

#include <stdio.h>
#include <stdlib.h>
#include <iostream>

using namespace std;

class Box {
public :
	double getVolume(void) {
		return length*breadth*height;
	}

	void setLength(double len) {
		length = len;
	}

	void setBreadth(double bth) {
		breadth = bth;
	}

	void setHeight(double hgt) {
		height = hgt;
	}

	//Operator overloading to add two boxes
	Box operator+(const Box& b) {
		Box box;
		box.length = this->length + b.length;
		box.breadth = this->breadth + b.breadth;
		box.height = this->height + b.height;
		return box;
	}

private :
	double length;
	double breadth;
	double height;

};

int main(void) {
	Box box1, box2, box3;

	//initialize box1
	box1.setLength(2.0);
	box1.setBreadth(3.0);
	box1.setHeight(4.0);

	//initialize box2
	box2.setLength(5.0);
	box2.setBreadth(6.0);
	box2.setHeight(7.0);

	cout<<"Volume of box 1 : "<<box1.getVolume()<<endl;
	cout<<"Volume of box 2 : "<<box2.getVolume()<<endl;

	//add the two boxes
	box3 = box1 + box2;

	cout<<"Volume of box 3 : "<<box3.getVolume()<<endl;
}

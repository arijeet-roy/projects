package com.assignments;

import java.util.Scanner;

public class LinkedList {

	public LinkedList() {
	}

	private Node insertAtStart(int number, Node head) {
		Node newNode = new Node(number, null);

		// first element
		if (head == null) {
			head = newNode;
		} else {
			newNode.setNext(head);
			head = newNode;
		}
		return head;
	}

	private void traverseList(Node head) {
		while(head != null) {
			System.out.print(head.getData()+" ");
			head = head.getNext();
		}
		System.out.println();
	}

	private int count_d(Node head, int num) {
		if(head != null) {
			if(head.getData() == num) return 1 + count_d(head.getNext(), num);
			return count_d(head.getNext(), num);
		}
		return 0;
	}
	
	private int add_list(Node head) {
		if(head != null) {
			return head.getData() + add_list(head.getNext());
		}
		return 0;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node head = null;
		System.out.println("Enter your student id : ");
		Scanner scanner = new Scanner(System.in);
		int studentId = scanner.nextInt();

		LinkedList newLinkedList = new LinkedList();
		while (studentId > 10) {
			// keep inserting one by one
			head = newLinkedList.insertAtStart(5, head);
			head = newLinkedList.insertAtStart(studentId % 10, head);
			studentId /= 10;
		}
		// insert the last digit
		head = newLinkedList.insertAtStart(5, head);
		head = newLinkedList.insertAtStart(studentId, head);

		//list traversal
		newLinkedList.traverseList(head);
		
		System.out.println("Enter the digit you want to count the number of occurences for :");
		int digitToSearch = scanner.nextInt();
		//count occurrences
		System.out.println("Count of the number "+digitToSearch+" is : "+newLinkedList.count_d(head, digitToSearch));
		
		//Sum of all nodes
		System.out.println("Sum of all the nodes : "+newLinkedList.add_list(head));
	}

	private class Node {
		private int data;
		private Node next;

		Node(int data, Node next) {
			this.data = data;
			this.next = next;
		}

		public int getData() {
			return data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

	}

}

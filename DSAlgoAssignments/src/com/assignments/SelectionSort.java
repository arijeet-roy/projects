package com.assignments;

import java.util.Scanner;

public class SelectionSort {

	public SelectionSort() {
	}

	private Node insertAtEnd(int number, Node head) {
		Node newNode = new Node(number, null);
		Node temp = head;

		// first element
		if (head == null) {
			head = newNode;
			temp = head;
		} else {
			//keep inserting at the end while advancing the temp pointer
			while (temp.getNext() != null) {
				temp = temp.getNext();
			}
			temp.setNext(newNode);
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
	
	//returns the pointer to the head node.
	private Node swap(Node head, Node node1, Node node2) {
		Node temp = null; Node temp_head = head;
		if(node1 == node2) return head;
		//the case where the nodes to be swapped are next to each other.
		if(node1.getNext() == node2) {
			temp = node1;
			node1.setNext(node2.getNext());
			node2.setNext(temp);
		}
		//the case where the nodes to be swapped are at at least a node distance apart.
		else {
			Node prev_node2 = null;
			while(temp_head.getNext() != node2) {
				temp_head = temp_head.getNext();
			}
			prev_node2 = temp_head;
			prev_node2.setNext(node1);
			temp = node1.getNext();
			node1.setNext(node2.getNext());
			node2.setNext(temp);
		}	
		
		//adjust the head pointer in case the first node is not the head node itself.
		if(head != node1) {
			Node temp_head2 = head;
			while(temp_head2.getNext() != node1) {
				temp_head2 = temp_head2.getNext();
			}
			temp_head2.setNext(node2);
			return head;
		}
		return node2;
	}
	
	//perform the sort and return the head node.
	private Node selectionSort(Node head) {
		if(head == null || head.getNext() == null) return head;
		if(head.getNext().getNext() == null) {
			if(head.getData() > head.getNext().getData()) 
				return swap(head, head, head.getNext());
			return head;
		}
		Node temp1 = head; 
		Node temp3 = null;
		while(temp1.getNext() != null) {
			Node min = temp1;
			Node temp2 = temp1.getNext();
			while(temp2 != null) {
				if(min.getData() > temp2.getData()) {
					min = temp2;
				}
				temp2 = temp2.getNext();
			}
			temp3 = temp1;
			temp1 = temp1.getNext();
			if(min != temp3) {
				head = swap(head, temp3, min);
			}
			
		}
		
		return head;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node head = null;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the size of linked list(greater than 0) :");
		int size = scanner.nextInt();
		System.out.println("Enter the numbers randomly into the "+size+"-sized linked list");
		
		SelectionSort newLinkedList = new SelectionSort();
		for(int i=0; i<size; i++) {
			int num = scanner.nextInt();
			head = newLinkedList.insertAtEnd(num, head);
		}
		
		System.out.println("The entered list is : ");
		//list traversal
		newLinkedList.traverseList(head);
		head = newLinkedList.selectionSort(head);
	
		System.out.println("The sorted list is : ");
		newLinkedList.traverseList(head);
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

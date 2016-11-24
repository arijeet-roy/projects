package com.algos.practice;

import java.util.Scanner;

public class ReverseLinkedList {

	public ReverseLinkedList() {
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
	
	private Node reverseList(Node head) {
		Node prev = null; Node curr = head;
		while(curr != null){
			Node temp = curr.getNext();
			curr.setNext(prev);
			prev = curr;
			curr = temp;
		}
		return prev;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node head = null;
		ReverseLinkedList linkedList = new ReverseLinkedList();
		head = linkedList.insertAtStart(5, head);
		head = linkedList.insertAtStart(4, head);
		head = linkedList.insertAtStart(3, head);
		head = linkedList.insertAtStart(2, head);
		head = linkedList.insertAtStart(1, head);
		//reverse
		head = linkedList.reverseList(head);
		linkedList.traverseList(head);
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

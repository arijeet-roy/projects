package com.assignments;

import java.util.Scanner;

public class AVLTree {

	private Node insert(int key, Node node, Node parentOfNode) {
		
		if (node == null) {
			Node newNode = new Node(key);
			newNode.parent = parentOfNode;
			return newNode;
		}

		if (key < node.key) {
			node.left = insert(key, node.left, node);
		} else {
			node.right = insert(key, node.right, node);
		}

		node.height = getHeight(node);

		int diff = getBalance(node);

		//Left subtree
		if(diff < -1) {
			//left left case
			if(key < node.left.key) {
				return rightRotate(node);
			}
			//left right case
			else {
				return rightRotate(leftRotate(node.left));
			}
		}
		//Right subtree
		else if(diff > 1) {
			//right right case
			if(key > node.right.key) {
				return leftRotate(node);
			}
			//right left case
			else {
				return leftRotate(rightRotate(node.right));
			}
		}

		return node;
	}

	Node rightRotate(Node node) {
		Node newRoot = node.left;
		Node temp = newRoot.right;
		
		// Perform rotation
		newRoot.right = node;
		node.left = temp;

		// Update heights
		node.height = getHeight(node);
		newRoot.height = getHeight(newRoot);
		
		Node tempParent = node.parent;
		node.parent = newRoot;
		newRoot.parent = tempParent;
		
		//update the node.left parent IF EXISTS
		if(node.left != null) {
			node.left.parent = node;
		}

		// Return new root
		return newRoot;
	}

	private Node leftRotate(Node node) {
		Node newRoot = node.right;
		Node temp = newRoot.left;

		// Perform rotation
		newRoot.left = node;
		node.right = temp;

		// Update heights
		node.height = getHeight(node);
		newRoot.height = getHeight(newRoot);
		
		Node tempParent = node.parent;
		node.parent = newRoot;
		newRoot.parent = tempParent;
		
		//update the node.right parent IF EXISTS
		if(node.right != null) {
			node.right.parent = node;
		}
		
		// Return new root
		return newRoot;
	}

	private int getBalance(Node N) {
		if (N == null) {
			return 0;
		}
		return getHeight(N.right) - getHeight(N.left);
	}
	
	private int getHeight(Node node) {
		// TODO Auto-generated method stub
		if(node == null) return 0;
		
		return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
	}

	private void inOrder(Node node) {
		if (node != null) {
			inOrder(node.left);
			System.out.print(node.key + " ");
			inOrder(node.right);
		}
	}

	private void preOrder(Node node) {
		if (node != null) {
			System.out.print(node.key + " ");
			inOrder(node.left);
			inOrder(node.right);
		}
	}
	
	private void postOrder(Node node) {
		if (node != null) {
			inOrder(node.left);
			inOrder(node.right);
			System.out.print(node.key + " ");
		}
	}
	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		Node root = null;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the size :");
		int size = scanner.nextInt();
		System.out.println("Enter the "+size+" numbers : ");
		for(int i=0; i<size; i++) {
			int num = scanner.nextInt();
			root = tree.insert(num, root, null);
			System.out.println("Inorder traversal : ");
			tree.inOrder(root);
			System.out.println();
			System.out.println("Preorder traversal : ");
			tree.preOrder(root);
			System.out.println();
			System.out.println("Postorder traversal : ");
			tree.postOrder(root);
			System.out.println();
		}
	}
	
	class Node {

		int key, height;
		Node left, right, parent;

		Node(int d) {
			key = d;
			height = 0;
			parent = null;
		}
	}
}
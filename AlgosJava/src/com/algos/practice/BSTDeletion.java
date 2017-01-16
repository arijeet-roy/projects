package com.algos.practice;

public class BSTDeletion {

	public BSTDeletion() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BSTDeletion bstDeletion = new BSTDeletion();
		Node root = null;
		root = bstDeletion.insert(10, root);
		root = bstDeletion.insert(5, root);
		root = bstDeletion.insert(15, root);
		root = bstDeletion.insert(3, root);
		root = bstDeletion.insert(2, root);
		root = bstDeletion.insert(8, root);
		root = bstDeletion.insert(6, root);
		root = bstDeletion.insert(7, root);
		root = bstDeletion.insert(12, root);
		root = bstDeletion.insert(20, root);
		System.out.println(bstDeletion.countLeafNodes(root));
		bstDeletion.preOrder(root);
		System.out.println();
		root = bstDeletion.deleteNodeNormal(root, 10);
		bstDeletion.preOrder(root);

	}

	private int countLeafNodes(Node root) {
		// TODO Auto-generated method stub
		if(root == null) return 0;
		if(root.left == null && root.right == null) {
			System.out.println("leaf node : "+root.key);
			return 1;
		}
		return countLeafNodes(root.left) + countLeafNodes(root.right);
	}

	private Node deleteNodeRecurse(Node root, int data) {
		// TODO Auto-generated method stub
		if(root == null) return null;
		if(data < root.key) {
			root.left = deleteNodeRecurse(root.left, data);
		}
		else if(data > root.key) {
			root.right = deleteNodeRecurse(root.right, data);
		}
		else {
			if(root.left == null) {
				return root.right;
			}
			else if(root.right == null) {
				return root.left;
			}
			else if(root.left != null && root.right != null) {
				int predecessor = findPredecessor(root.left);
				root.key = predecessor;
				root.left = deleteNodeRecurse(root.left, root.key);
			}
		}
		return root;
	}
	
	private Node deleteNodeNormal(Node root, int data) {
		// TODO Auto-generated method stub
		if(root == null) return null;
		if(data < root.key) {
			root.left = deleteNodeRecurse(root.left, data);
		}
		else if(data > root.key) {
			root.right = deleteNodeRecurse(root.right, data);
		}
		else {
			if(root.left == null) {
				return root.right;
			}
			else if(root.right == null) {
				return root.left;
			}
			else if(root.left != null && root.right != null) {
				int predecessor = findPredecessorNormal(root);
				root.key = predecessor;
			}
		}
		return root;
	}
	
	private int findPredecessorNormal(Node left) {
		// TODO Auto-generated method stub
		Node parent = left;
		Node curr = left.left;
		if(curr.right == null) {
			parent.left = curr.left;
			return curr.key;
		}
		while(curr.right != null) {
			parent = curr;
			curr = curr.right;
		}
		parent.right = curr.left;
		return curr.key;
	}

	private int findPredecessor(Node left) {
		// TODO Auto-generated method stub
		Node curr = left;
		while(curr.right != null) {
			curr = curr.right;
		}
		return curr.key;
	}

	private Node insert(int key, Node root) {
		
		if (root == null) {
			Node newNode = new Node(key);
			return newNode;
		}

		if (key < root.key) {
			root.left = insert(key, root.left);
		} else {
			root.right = insert(key, root.right);
		}
		
		return root;
	}
	
	private void preOrder(Node node) {
		if (node != null) {
			System.out.print(node.key + " ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	
	class Node {

		int key;
		Node left, right;

		Node(int d) {
			key = d;
		}
	}
}

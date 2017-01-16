package com.hackerrank.triecontacts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	private TrieNode root;
	private static Solution solution;
	
	public Solution() {
		// TODO Auto-generated constructor stub
		root = new TrieNode('\0', false);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		solution = new Solution();
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		String words[] = new String[num];
		scanner.nextLine();
		for (int i = 0; i < num; i++) {
			words[i] = scanner.nextLine();
		}
		
		for (int i = 0; i < words.length; i++) {
			String operation[] = words[i].split(" ");
			if(operation[0].equals("add")) {
				solution.insertWord(operation[1]);
			}
			else if(operation[0].equals("find")) {
				System.out.println(solution.searchPrefix(operation[1]));
			}
		}
		scanner.close();
	}

	private int searchPrefix(String prefix) {
		// TODO Auto-generated method stub
		TrieNode tempRoot = root;
		for (int i = 0; i < prefix.length(); i++) {
			tempRoot = searchChar(prefix.charAt(i), tempRoot);
			if(tempRoot == null) return 0;
		}
		
		Queue<TrieNode> queue = new LinkedList<>();
		queue.add(tempRoot);
		int wordCount = 0;
		while(!queue.isEmpty()) {
			TrieNode node = queue.poll();
			if(node.isWord == true) wordCount++;
			for (Iterator<Entry<Character, TrieNode>> iterator = node.children.entrySet().iterator(); iterator.hasNext();) {
				Map.Entry<Character, TrieNode> trieNode = (Map.Entry<Character, TrieNode>) iterator.next();
				queue.add(trieNode.getValue());
			}
		}
		return wordCount;
	}

	private TrieNode searchChar(char c, TrieNode tempRoot) {
		// TODO Auto-generated method stub
		if(tempRoot.children.containsKey(c)) {
			tempRoot = tempRoot.children.get(c);
			return tempRoot;
		}
		return null;
	}

	private void insertWord(String word) {
		// TODO Auto-generated method stub
		TrieNode tempRoot = root;
		for (int i = 0; i < word.length(); i++) {
			tempRoot = solution.insertChar(word.charAt(i), tempRoot);
		}
		tempRoot.isWord = true;
	}

	private TrieNode insertChar(char c, TrieNode root) {
		// TODO Auto-generated method stub
		if(!root.children.containsKey(c)) {
			TrieNode node = solution.new TrieNode(c, false);
			root.children.put(c, node);
			root = node;
		}
		else {
			root = root.children.get(c);
		}
		return root;
	}

	class TrieNode {
		char c;
		Map<Character, TrieNode> children = new HashMap<>();
		boolean isWord;
		
		TrieNode(char c, boolean isWord) {
			this.c = c;
			this.isWord = isWord;
		}
	}
}

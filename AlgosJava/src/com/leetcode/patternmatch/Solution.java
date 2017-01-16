package com.leetcode.patternmatch;

public class Solution {

	public Solution() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		String str = "baaabab";
		String pattern = "*****ba*****ab";
		
		Solution solution = new Solution();
		System.out.println(solution.isPatternMatch(str, pattern));
	}

	private boolean isPatternMatch(String str, String pattern) {
		// TODO Auto-generated method stub
		int starCount = 0;
		while(pattern.contains("**")) {
			pattern = pattern.replace("**", "*");
		}
		
		for (int i = 0; i < pattern.length(); i++) {
			if(pattern.charAt(i) == '*'){
				starCount++;
			}
		}
		for (int i = 0, j = 0; i < str.length() || j < pattern.length();) {
			char strChar = str.charAt(i);
			char pattChar = pattern.charAt(j);
			if(pattChar == '*' && starCount > 1) {
				i++;
				j++;
				starCount--;
				continue;
			}
			else if(pattChar == '*' && starCount == 1) {
				j++;
				if(j == pattern.length()) continue;
				int tempStart = str.length() - (pattern.length() - j);
				for (int k = tempStart; k < str.length(); k++) {
					if(str.charAt(k) != pattern.charAt(j)) return false;
				}
			}
			else if(pattChar == '?' || pattChar == strChar) {
				i++;
				j++;
			}
			else if(pattChar != strChar) {
				if(pattern.charAt(j-1) == '*') {
					i++;
					continue;
				}
				return false;
			}
			else if(j == pattern.length() - 1) return true;
			else if(i == str.length() - 1) return false;
			
			
		}
		
		return true;
	}
}

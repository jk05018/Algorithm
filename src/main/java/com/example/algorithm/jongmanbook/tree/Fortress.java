package com.example.algorithm.jongmanbook.tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import java.util.*;

public class Fortress {
	static Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
	static int[] x, y, radius;

	static int longest;

	int height(TreeNode root){
		List<Integer> heights = new ArrayList<>();

		for(int i=0; i < root.children.size() ; ++i){
			heights.add(height(root.children.get(i)));
		}

		int hSize = heights.size();

		// base case. if leaf node -> return 0;
		if(hSize == 0){
			return 0;
		}

		// sorting for pick up biggest heights
		Collections.sort(heights);

		if(heights.size() >= 2){
			longest = Math.max(longest, heights.get(hSize - 1) + heights.get(hSize-2));
		}

		// return heights
		return heights.get(hSize - 1) + 1;

	}

	int sqrdist(int a, int b){
		return (int)(Math.pow(x[a] - x[b],2) + Math.pow(y[a] - y[b],2));
	}

	// check if wall a include wall b
	boolean encloses(int a, int b){
		return radius[a] > radius[b] && sqrdist(a,b) < Math.pow(radius[a] - radius[b],2);
	}

	boolean isChild(int parent, int child){
		if(!encloses(parent, child)){
			return false;
		}

		// for(int i=0; i<n ; ++i){
		// 	if( i != parent && i != child && encloses(parent, i) && encloses(i, child)){
		// 		return false;
		// 	}
		// }

		return true;
	}


	private static void solve(){
	}

	public static void main(String[] args) {
		int tt = sc.nextInt();

		for(int t=0; t<tt ; ++t){
			solve();
		}
	}
}

class TreeNode{
	List<TreeNode> children;
}

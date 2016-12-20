package com.ex.tree;

import java.util.ArrayList;
import java.util.List;

public class Test {
	
	
	
	public static void main(String[] args) {
		/*TreeNode<Integer> root=new TreeNode<Integer>(1);
		TreeNode<Integer> a=new TreeNode<Integer>(2);
		TreeNode<Integer> b=new TreeNode<Integer>(3);
		TreeNode<Integer> c=new TreeNode<Integer>(4);
		TreeNode<Integer> d=new TreeNode<Integer>(5);
		root.leftChild=a;
		root.rightChild=b;
		a.leftChild=c;
		a.rightChild=d;
		System.out.println("pre:");
		TreeTools.preOrderTravel(root);
		System.out.println("\nback:");
		TreeTools.backOrderTravel(root);
		System.out.println("\nmid:");
		TreeTools.midOrderTravel(root);*/
		List pre=new ArrayList(5);
		pre.add(4);
		pre.add(5);
		pre.add(2);
		pre.add(3);
		pre.add(1);
		List mid=new ArrayList(5);
		mid.add(4);
		mid.add(2);
		mid.add(5);
		mid.add(1);
		mid.add(3);
		TreeNode t=TreeTools.getTreeFromBackAndMid(pre, mid);
		System.out.println("\nback:");
		TreeTools.backOrderTravel(t);
		System.out.println("\nmid:");
		TreeTools.midOrderTravel(t);
		System.out.println("\npre:");
		TreeTools.noRecPreOrderTravel(t);
		
	}
}

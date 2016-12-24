package com.ex.search;

import com.ex.tree.TreeNode;
import com.ex.tree.TreeTools;

public class test {
	public static void main(String[] args) {
		/*int[] a={0,1,16,24,35,47,59,62,73,88,99};
		System.out.println(interpolationSearch(a,62));*/
		TreeNode<Integer> root=new TreeNode<Integer>(62);
		TreeNode<Integer> a=new TreeNode<Integer>(58);
		TreeNode<Integer> b=new TreeNode<Integer>(47);
		TreeNode<Integer> c=new TreeNode<Integer>(35);
		TreeNode<Integer> d=new TreeNode<Integer>(37);
		TreeNode<Integer> e=new TreeNode<Integer>(51);
		TreeNode<Integer> f=new TreeNode<Integer>(88);
		TreeNode<Integer> g=new TreeNode<Integer>(73);
		TreeNode<Integer> h=new TreeNode<Integer>(99);
		TreeNode<Integer> i=new TreeNode<Integer>(93);
		root.leftChild=a;
		root.rightChild=f;
		a.leftChild=b;
		b.leftChild=c;
		b.rightChild=e;
		c.rightChild=d;
		f.leftChild=g;
		f.rightChild=h;
		h.leftChild=i;
		TreeNode<Integer> p=new TreeNode<Integer>();
		//inseertBST(root, 95);
		//System.out.println(SearchTools.noRecSearchBST(root, 35).value);
		
		//System.out.println(SearchTools.insertBST(root,93));
		//TreeTools.midOrderTravel(root);
		int[] num={62,88,58,47,35,73,51,99,37,93};
		TreeNode<Integer> r=new TreeNode();
		for(int j=0;j<num.length;j++){
			SearchTools.insertBST(r, num[j]);
		}
		TreeTools.midOrderTravel(r);
		System.out.println();
		SearchTools.insertBST(r, 100);
		TreeTools.midOrderTravel(r);
		System.out.println();
		SearchTools.deleteBST(r, 109);
		TreeTools.midOrderTravel(r);
	}
}

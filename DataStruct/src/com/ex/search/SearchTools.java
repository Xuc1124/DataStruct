package com.ex.search;

import com.ex.tree.TreeNode;

public class SearchTools {
	/*
	 * 二分查找
	 */
	public static int binarySearch(int[] a,int key){
		int low=0;
		int high=a.length-1;
		int mid=0;
		while(low<high){
			mid=(low+high)/2;
			if(key<a[mid]){
				high=mid;
			}else if(key>a[mid]){
				low=mid+1;
			}else{
				return mid;
			}
		}
		return -1;
	}
	
	/*
	 * 插值查找，对二分查找的改进
	 */
	public static int interpolationSearch(int[] a,int key){
		int low=0;
		int high=a.length-1;
		int mid=0;
		while(low<high){
			mid=low+(high-low)*(key-a[low])/(a[high]-a[low]);
			if(key<a[mid]){
				high=mid;
			}else if(key>a[mid]){
				low=mid+1;
			}else{
				return mid;
			}
		}
		return -1;
	}
	
	/*
	 * 二叉排序树中的查找，递归
	 */
	public static TreeNode<Integer> searchBST(TreeNode<Integer> root,int key){
		if(root==null){
			return new TreeNode<Integer>();
		}else if(key==root.value){
			return root;
		}else if(key<root.value){
			return searchBST(root.leftChild, key);
		}else{
			return searchBST(root.rightChild, key);
		}
	}
	
	/*
	 * 二叉排序树中的查找，非递归
	 */
	public static TreeNode<Integer> noRecSearchBST(TreeNode<Integer> root,int key){
		TreeNode<Integer> r=root;
		while(r!=null){
			if(r.value==key){
				return r;
			}else if(r.value<key){
				r=r.rightChild;
			}else{
				r=r.leftChild;
			}
		}
		return new TreeNode<Integer>();
	}
	
	
	/*
	 * 二叉排序树的插入,非递归
	 */
	public static boolean insertBST(TreeNode<Integer> root,int key){
		if(root==null) return false;
		if(root.value==null){
			root.value=key;
			return true;
		}
		TreeNode<Integer> r=root;
		TreeNode<Integer> temp=new TreeNode<Integer>(key);
		TreeNode<Integer> parent=null;
		while(r!=null){
			if(r.value==key){
				System.out.println(key+" is in the BST...");
				return false;
			}else if(r.value<key){
				parent=r;
				r=r.rightChild;
			}else{
				parent=r;
				r=r.leftChild;
			}
		}
		if(parent.value<key){
			parent.rightChild=temp;
		}else{
			parent.leftChild=temp;
		}
		return true;
	}
	
	/*
	 * 二叉排序树的删除，非递归
	 */
	public static boolean deleteBST(TreeNode<Integer> root,int key){
		if(root==null) return false;
		TreeNode<Integer> r=root;
		TreeNode<Integer> parent=null;
		while(r.value!=key){		//找到要删除的节点
			parent=r;
			if(r.value>key){
				r=r.leftChild;
			}else{
				r=r.rightChild;
			}
			if(r==null){
				System.out.println("can't find "+key);
				return false;
			}
		}
		TreeNode<Integer> q;
		TreeNode<Integer> s;
		if(r.rightChild==null){					//只有左子树的情况
			if(parent.leftChild==r){
				parent.leftChild=r.leftChild;
			}else{
				parent.rightChild=r.leftChild;
			}
		}else if(r.leftChild==null){			//只有右子树的情况
			if(parent.leftChild==r){
				parent.leftChild=r.rightChild;
			}else{
				parent.rightChild=r.rightChild;
			}
		}else{									//左右子树都存在
			q=r;
			s=r.leftChild;
			while(s.rightChild!=null){
				q=s;
				s=s.rightChild;
			}
			r.value=s.value;
			if(q!=r){
				q.rightChild=s.leftChild;
			}else{
				q.leftChild=s.leftChild;
			}
		}
		return true;
	}
	
}

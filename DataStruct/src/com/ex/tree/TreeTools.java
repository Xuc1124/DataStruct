package com.ex.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TreeTools {
	/*
	 * 返回节点数
	 */
	public static <T> int getTreeNum(TreeNode<T> root){
		if(root==null){
			return 0;
		}
		return getTreeNum(root.leftChild)+getTreeNum(root.rightChild)+1;
	}
	
	/*
	 * 返回树的深度
	 */
	public static <T> int getTreeDepth(TreeNode<T> root){
		if(root==null){
			return 0;
		}
		int leftDepth=getTreeDepth(root.leftChild)+1;
		int rightDepth=getTreeDepth(root.rightChild)+1;
		return Math.max(leftDepth, rightDepth);
	}
	
	/*
	 * 访问节点
	 */
	public static <T> void visitNode(TreeNode<T> node){
		System.out.print(node.value.toString()+" ");
	}
	
	/*
	 * 前序遍历
	 */
	public static <T> void preOrderTravel(TreeNode<T> root){
		if(root==null){
			return;
		}
		visitNode(root);
		preOrderTravel(root.leftChild);
		preOrderTravel(root.rightChild);
	}
	
	/*
	 * 后续遍历
	 */
	public static <T> void backOrderTravel(TreeNode<T> root){
		if(root==null){
			return;
		}
		backOrderTravel(root.leftChild);
		backOrderTravel(root.rightChild);
		visitNode(root);
	}
	
	/*
	 * 中序遍历
	 */
	public static <T> void midOrderTravel(TreeNode<T> root){
		if(root==null){
			return;
		}
		midOrderTravel(root.leftChild);
		visitNode(root);
		midOrderTravel(root.rightChild);
	}
	
	/*
	 * 层序遍历
	 */
	public static <T> void levelTravel(TreeNode<T> root){
		Queue<TreeNode<T>> q=new LinkedList<TreeNode<T>>();
		q.offer(root);
		while(!q.isEmpty()){
			TreeNode<T> temp=q.poll();
			visitNode(temp);
			if(temp.leftChild!=null){
				q.offer(temp.leftChild);
			}
			if(temp.rightChild!=null){
				q.offer(temp.rightChild);
			}
		}
	}
	
	/*
	 * 返回k层节点数
	 */
	public static <T> int getKthLevelNum(TreeNode<T> root,int k){
		if(root==null || k<1){
			return 0;
		}
		if(k==1){
			return 1;
		}
		int leftNum=getKthLevelNum(root.leftChild, k-1);
		int rightNum=getKthLevelNum(root.rightChild, k-1);
		return leftNum+rightNum;
	}
	
	/*
	 * 返回叶子节点数
	 */
	public static <T> int getLeafNum(TreeNode<T> root){
		if(root==null){
			return 0;
		}
		if(root.leftChild==null&&root.rightChild==null){
			return 1;
		}
		int leftNum=getLeafNum(root.leftChild);
		int rightNum=getLeafNum(root.rightChild);
		return leftNum+rightNum;
	}
	
	/*
	 * 交换根节点的左右子树
	 */
	public static <T> TreeNode<T> exchange(TreeNode<T> root){
		if(root==null){
			return null;
		}
		TreeNode<T> left=exchange(root.leftChild);
		TreeNode<T> right=exchange(root.rightChild);
		root.leftChild=right;
		root.rightChild=left;
		return root;
	}
	
	/*
	 * 根据前序和中序创建树
	 */
	public static <T> TreeNode<T> getTreeFromPreAndMid(List<T> pre,List<T> mid){
		if(pre==null||mid==null||pre.size()==0||mid.size()==0){
			return null;
		}
		if(pre.size()==1){
			return new TreeNode<T>(pre.get(0));
		}
		TreeNode root=new TreeNode<T>(pre.get(0));
		int index=0;
		while(!(mid.get(index).equals(root.value))&&index<mid.size()-1){
			index++;
		}
		//System.out.println("index:"+index);
		List<T> preLeft=new ArrayList<T>(index);
		List<T> midLeft=new ArrayList<T>(index);
		for(int i=1;i<index+1;i++){
			preLeft.add(pre.get(i));
		}
		for(int i=0;i<index;i++){
			midLeft.add(mid.get(i));
		}
		root.leftChild=getTreeFromPreAndMid(preLeft, midLeft);
		List<T> preRight=new ArrayList<T>(pre.size()-index-1);
		List<T> midRight=new ArrayList<T>(mid.size()-index-1);
		for(int i=index+1;i<pre.size();i++){
			preRight.add(pre.get(i));
		}
		for(int i=index+1;i<mid.size();i++){
			midRight.add(mid.get(i));
		}
		root.rightChild=getTreeFromPreAndMid(preRight, midRight);
		return root;
	}
	
	/*
	 * 根据后序和中序创建树
	 */
	public static <T> TreeNode<T> getTreeFromBackAndMid(List<T> back,List<T> mid){
		if(back==null||mid==null||back.size()==0||mid.size()==0){
			return null;
		}
		if(back.size()==1){
			return new TreeNode<T>(back.get(back.size()-1));
		}
		TreeNode<T> root=new TreeNode<T>(back.get(back.size()-1));
		int index=0;
		while(!mid.get(index).equals(root.value)&&index<back.size()-1){
			index++;
		}
		List<T> backLeft=new ArrayList<T>(index);
		List<T> midLeft=new ArrayList<T>(index);
		for(int i=0;i<index;i++){
			backLeft.add(back.get(i));
		}
		for(int i=0;i<index;i++){
			midLeft.add(mid.get(i));
		}
		root.leftChild=getTreeFromBackAndMid(backLeft, midLeft);
		List<T> backRight=new ArrayList<T>(back.size()-index-1);
		List<T> midRight=new ArrayList<T>(mid.size()-index-1);
		for(int i=index;i<back.size()-1;i++){
			backRight.add(back.get(i));
		}
		for(int i=index+1;i<mid.size();i++){
			midRight.add(mid.get(i));
		}
		root.rightChild=getTreeFromBackAndMid(backRight, midRight);
		return root;
	}
	
	/*
	 * 非递归前序遍历
	 */
	public static <T> void noRecPreOrderTravel(TreeNode<T> root){
		Stack<TreeNode> stack=new Stack<TreeNode>();
		TreeNode node=root;
		if(node!=null){
			stack.push(node);
			while(!stack.isEmpty()){
				node=stack.pop();
				visitNode(node);
				if(node.rightChild!=null){
					stack.push(node.rightChild);
				}
				if(node.leftChild!=null){
					stack.push(node.leftChild);
				}
			}
		}
	}
	
	/*
	 * 非递归后续遍历
	 */
	public static <T> void noRecBackOrderTravel(TreeNode<T> root){
		Stack<TreeNode> stack=new Stack<TreeNode>();
		Stack<TreeNode> temp=new Stack<TreeNode>();
		TreeNode node=root;
		if(node!=null){
			stack.push(node);
			while(!stack.isEmpty()){
				node=stack.pop();
				//visitNode(node);
				temp.push(node);
				if(node.leftChild!=null){
					stack.push(node.leftChild);
				}
				if(node.rightChild!=null){
					stack.push(node.rightChild);
				}
			}
		}
		while(!temp.isEmpty()){
			visitNode(temp.pop());
		}
	}
	
	/*
	 * 非递归中序遍历
	 */
	public static <T> void noRecMidOrderTravel(TreeNode<T> root){
		Stack<TreeNode> stack=new Stack<TreeNode>();
		TreeNode node=root;
		while(node!=null||!stack.isEmpty()){
			while(node!=null){
				stack.push(node);
				node=node.leftChild;
			}
			if(!stack.isEmpty()){
				node=stack.pop();
				visitNode(node);
				node=node.rightChild;
			}
		}
	}
}

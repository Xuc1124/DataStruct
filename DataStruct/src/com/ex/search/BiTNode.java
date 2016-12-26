package com.ex.search;

/*
 * 实现平衡二叉树的二叉树结构
 */
public class BiTNode {
	int data=65535;		//默认的一个不可能的值
	int bf;				//节点的平衡因子
	BiTNode leftChild=null;
	BiTNode rightChild=null;
	
	public BiTNode(int data){
		this.data=data;
	}
	public BiTNode(){}
	
	
	
	/*
	 *前序遍历 
	 */
	public static void preOrder(BiTNode root){
		if(root==null) return;
		System.out.print(root.data+" ");
		preOrder(root.leftChild);
		preOrder(root.rightChild);
	}
	
	
	public static void main(String[] args) {
		BiTNode root=new BiTNode(0);
		BiTNode a=new BiTNode(1);
		BiTNode b=new BiTNode(2);
		BiTNode c=new BiTNode(3);
		BiTNode d=new BiTNode(4);
		BiTNode e=new BiTNode(5);
		root.leftChild=a;
		root.rightChild=b;
		a.leftChild=c;
		a.rightChild=d;
		c.leftChild=e;
		preOrder(root);
		System.out.println();
		//BiTNode r=rotateRight(root);
		//System.out.println(root.data);
		//preOrder(r);
	}
}

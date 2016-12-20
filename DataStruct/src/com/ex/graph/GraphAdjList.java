package com.ex.graph;

import java.util.ArrayList;
import java.util.Scanner;
/*
 * 无向图的邻接表表示
 */
public class GraphAdjList {
	
	public static class EdgeNode{
		int adjvex;//邻接点域，存储该顶点对应的下标
		int weight;//存储权值，对于非网图可以不需要
		EdgeNode next=null;
		public EdgeNode(int adjvex, int weight){
			this.adjvex=adjvex;
			this.weight=weight;
		}
		public EdgeNode(int adjvex){
			this.adjvex=adjvex;
		}
		public EdgeNode(){}
	}
	
	public static class VertexNode{
		String data=null;				//顶点域，存储顶点信息
		EdgeNode firstEdge=null;//边表头指针
		public VertexNode(String data){
			this.data=data;
		}
		public VertexNode(){}
	}
	
	VertexNode[] adjList;
	int vertexNum,edgeNum;
	public GraphAdjList(int vertexNum,int edgeNum){
		this.adjList=new VertexNode[vertexNum];
		for(int i=0;i<vertexNum;i++){
			this.adjList[i]=new VertexNode();
		}
		this.vertexNum=vertexNum;
		this.edgeNum=edgeNum;
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		EdgeNode temp=new EdgeNode();
		System.out.println("输入顶点数和边数：");
		int vertexNum=sc.nextInt();
		int edgeNum=sc.nextInt();
		GraphAdjList graph=new GraphAdjList(vertexNum,edgeNum);
		
		for(int k=0;k<vertexNum;k++){
			System.out.println("输入顶点"+(k+1)+"信息：");
			graph.adjList[k].data=sc.next();
			graph.adjList[k].firstEdge=null;
		}
		System.out.println("顶点设置完成！\n输入边信息：\n\n");
		for(int k=0;k<edgeNum;k++){
			System.out.println("输入边(vi,vj)上的顶点序号：");
			int i=sc.nextInt();
			int j=sc.nextInt();
			temp.adjvex=j;
			temp.next=graph.adjList[i].firstEdge;
			graph.adjList[i].firstEdge=temp;
			
			temp.adjvex=i;
			temp.next=graph.adjList[j].firstEdge;
			graph.adjList[j].firstEdge=temp;
		}
		System.out.println("边表建立完成");
	}
}

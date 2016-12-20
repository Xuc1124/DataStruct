package com.ex.graph;

import java.util.Scanner;

import com.ex.graph.GraphAdjList.EdgeNode;

/*
 * 图遍历工具类
 */
public class GraphTools {
	
	private static boolean[] visited;
	
	/*
	 * 生成邻接矩阵图
	 */
	public static MGraph creatMGraph(){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入顶点数和边数：\n");
		int vertexNum=sc.nextInt();
		int edgeNum=sc.nextInt();
		MGraph g = new MGraph(vertexNum, edgeNum);
		
		for(int i=0;i<vertexNum;i++){
			visited[i]=false;
		}
		
		for (int k = 0; k < vertexNum; k++) {
			System.out.println("请输入顶点"+(k+1)+"信息:");
			g.vexs[k] = sc.next();
		}
		for(int k=0;k<edgeNum;k++){
			System.out.println("输入第"+k+"条边上的顶点序号：");
			int i=sc.nextInt();
			int j=sc.nextInt();
			g.arc[i][j]=1;
			g.arc[j][i]=1;
		}
		System.out.println("创建完成！");
		return g;
	}
	
	/*
	 * 生成邻接表图
	 */
	public static GraphAdjList creatGraphAdjList(){
		Scanner sc=new Scanner(System.in);
		EdgeNode temp=new EdgeNode();
		System.out.println("输入顶点数和边数：");
		int vertexNum=sc.nextInt();
		int edgeNum=sc.nextInt();
		GraphAdjList graph=new GraphAdjList(vertexNum,edgeNum);
		
		visited=new boolean[vertexNum];
		for (int i = 0; i < vertexNum; i++) {
			visited[i] = false;
		}
		
		for(int k=0;k<vertexNum;k++){
			System.out.println("输入顶点"+(k+1)+"信息：");
			graph.adjList[k].data=sc.next();
			graph.adjList[k].firstEdge=null;
		}
		System.out.println("顶点设置完成！\n输入边信息：\n\n");
		for(int k=0;k<edgeNum;k++){
			System.out.println("输入第"+k+"条边上的顶点序号：");
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
		return graph;
	}
	
	
	

	

	/*
	 * 邻接矩阵 DFS递归
	 */
	public static void DFSRec1(MGraph g, int i) {
		visited[i] = true;
		System.out.print(g.vexs[i] + " ");//此处做遍历到该节点时做的操作
		for (int j = 0; j < g.vertexNum; j++) {
			if (g.arc[i][j] == 1 && !visited[j]) {
				DFSRec1(g, j);
			}
		}
	}

	public static void DFSTraverse(MGraph g) {
		for (int i = 0; i < g.vertexNum; i++) {
			if (!visited[i]) {
				DFSRec1(g, i);
			}
		}
	}

	/*
	 * 邻接表，DFS递归
	 */
	public static void DFSRec2(GraphAdjList g,int i){
		GraphAdjList.EdgeNode p=new GraphAdjList.EdgeNode();
		visited[i]=true;
		System.out.print(g.adjList[i].data+" ");
		p=g.adjList[i].firstEdge;
		while(p!=null){
			if(!visited[p.adjvex]){
				DFSRec2(g,p.adjvex);
			}
			p=p.next;
		}
	}
	
	public static void DFSTraverse2(GraphAdjList g){
		
		for(int i=0;i<g.vertexNum;i++){
			if(!visited[i]){
				DFSRec2(g,i);
			}
		}
	}
	
	public static void main(String[] args) {
		MGraph g=creatMGraph();
		DFSTraverse(g);
		//GraphAdjList graph=creatGraphAdjList();
		
		//GraphTools.DFSTraverse2(graph);
	}

}

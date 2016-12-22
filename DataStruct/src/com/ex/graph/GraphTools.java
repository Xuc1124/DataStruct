package com.ex.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import com.ex.graph.GraphAdjList.EdgeNode;

/*
 * 图的相关算法
 */
public class GraphTools {

	private static boolean[] visited;//遍历时用到，判断是否访问过

	/*
	 * 生成邻接矩阵图
	 */
	public static MGraph creatMGraph() {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入顶点数和边数：\n");
		int vertexNum = sc.nextInt();
		int edgeNum = sc.nextInt();
		MGraph g = new MGraph(vertexNum, edgeNum);

		visited = new boolean[vertexNum];
		for (int i = 0; i < vertexNum; i++) {
			visited[i] = false;
		}

		for (int k = 0; k < vertexNum; k++) {
			System.out.println("请输入顶点" + (k + 1) + "信息:");
			g.vexs[k] = sc.next();
		}
		for (int k = 0; k < edgeNum; k++) {
			System.out.println("输入第" + k + "条边上的顶点序号：");
			int i = sc.nextInt();
			int j = sc.nextInt();
			g.arc[i][j] = 1;
			g.arc[j][i] = 1;
		}
		System.out.println("创建完成！");
		return g;
	}

	/*
	 * 生成邻接表图，EdgeNode会有重复，但重复的必须是不同的实例
	 */
	public static GraphAdjList creatGraphAdjList() {
		Scanner sc = new Scanner(System.in);
		System.out.println("输入顶点数和边数：");
		EdgeNode temp;
		int vertexNum = sc.nextInt();
		int edgeNum = sc.nextInt();
		GraphAdjList graph = new GraphAdjList(vertexNum, edgeNum);

		visited = new boolean[vertexNum];
		for (int i = 0; i < vertexNum; i++) {
			visited[i] = false;
		}

		for (int k = 0; k < vertexNum; k++) {
			System.out.println("输入顶点" + (k + 1) + "信息：");
			graph.adjList[k].data = sc.next();
			graph.adjList[k].firstEdge = null;
		}
		System.out.println("顶点设置完成！\n输入边信息：\n\n");
		for (int k = 0; k < edgeNum; k++) {
			System.out.println("输入第" + k + "条边上的顶点序号：");
			int i = sc.nextInt();
			int j = sc.nextInt();
			temp = new EdgeNode();
			temp.adjvex = j;
			temp.next = graph.adjList[i].firstEdge;
			graph.adjList[i].firstEdge = temp;

			temp = new EdgeNode();
			temp.adjvex = i;
			temp.next = graph.adjList[j].firstEdge;
			graph.adjList[j].firstEdge = temp;
		}
		System.out.println("边表建立完成");
		return graph;
	}

	/*
	 * 邻接矩阵 DFS递归
	 */
	public static void DFSRec1(MGraph g, int i) {
		visited[i] = true;
		System.out.print(g.vexs[i] + " ");// 此处做遍历到该节点时做的操作
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
	public static void DFSRec2(GraphAdjList g, int i) {
		GraphAdjList.EdgeNode p = new GraphAdjList.EdgeNode();
		visited[i] = true;
		System.out.print(g.adjList[i].data + " ");
		p = g.adjList[i].firstEdge;
		while (p != null) {
			if (!visited[p.adjvex]) {
				DFSRec2(g, p.adjvex);
			}
			p = p.next;
		}
	}

	public static void DFSTraverse2(GraphAdjList g) {

		for (int i = 0; i < g.vertexNum; i++) {
			if (!visited[i]) {
				DFSRec2(g, i);
			}
		}
	}

	/*
	 * 邻接矩阵BFS
	 */
	public static void BFSTraverse1(MGraph g) {
		Queue q = new LinkedList();
		for (int i = 0; i < g.vertexNum; i++) {
			if (!visited[i]) {
				visited[i] = true;
				System.out.println((g.vexs[i]) + " ");
				q.offer(i);
				while (!q.isEmpty()) {
					i = (int) q.poll();
					for (int j = 0; j < g.vertexNum; j++) {
						if (g.arc[i][j] == 1 && !visited[j]) {
							visited[j] = true;
							System.out.println(g.vexs[j] + " ");
							q.offer(j);
						}
					}
				}
			}
		}
	}

	/*
	 * 邻接表BFS
	 */
	public static void BFSTraverse2(GraphAdjList graph) {
		GraphAdjList.EdgeNode p;
		Queue q = new LinkedList();
		for (int i = 0; i < graph.vertexNum; i++) {
			if (!visited[i]) {
				visited[i] = true;
				System.out.print(graph.adjList[i].data + " ");
				q.offer(i);
				while (!q.isEmpty()) {
					i = (int) q.poll();
					p = graph.adjList[i].firstEdge;
					while (p != null) {
						if (!visited[p.adjvex]) {
							visited[p.adjvex] = true;
							System.out.print(graph.adjList[p.adjvex].data + " ");
							q.offer(p.adjvex);
						}
						p = p.next;
					}
				}
			}
		}
	}

	/*
	 * 最小生成树，Prim算法,假设从0节点开始
	 */
	public static void miniSpanTreePrim(MGraph g) {

		int[] lowcost = new int[g.vertexNum];
		int[] adjvex = new int[g.vertexNum];
		lowcost[0] = 0; // 下标为0的顶点已经加入生成树中
		adjvex[0] = 0; // 初始化第一个顶点为0；
		for (int i = 1; i < g.vertexNum; i++) {
			lowcost[i] = g.arc[0][i]; // 将v0顶点与之有边的权值存入数组
			adjvex[i] = 0; // 初始化都为v0的下标
		}
		int min, j, k;
		for (int i = 1; i < g.vertexNum; i++) {
			min = 65535;
			j = 1;
			k = 0;
			while (j < g.vertexNum) {
				if (lowcost[j] != 0 && lowcost[j] < min) {
					min = lowcost[j];
					k = j;
				}
				j++;
			}
			System.out.println(adjvex[k] + "\t" + k);
			lowcost[k] = 0;
			for (j = 1; j < g.vertexNum; j++) {
				if (lowcost[j] != 0 && g.arc[k][j] < lowcost[j]) {
					lowcost[j] = g.arc[k][j];
					adjvex[j] = k;
				}
			}
		}
		for (int n = 0; n < g.vertexNum; n++) {
			System.out.print(adjvex[n] + " ");
		}
	}

	/*
	 * 邻接矩阵转边集数组
	 */
	public static Edge[] mgraphToEdge(MGraph g) {
		Edge[] edges = new Edge[g.edgeNum];
		int k = 0;
		for (int i = 0; i < g.vertexNum; i++) { // 把邻接矩阵值存到边集数组中
			for (int j = i + 1; j < g.vertexNum; j++) {
				if (g.arc[i][j] != 0 && g.arc[i][j] < 65535) {
					edges[k++] = new Edge(i, j, g.arc[i][j]);
				}
			}
		}
		Edge temp;
		for (int i = 0; i < edges.length; i++) { // 对边集数组排序
			for (int j = i; j < edges.length; j++) {
				if (edges[j].weight < edges[i].weight) {
					temp = edges[i];
					edges[i] = edges[j];
					edges[j] = temp;
				}
			}
		}
		return edges;
	}

	/*
	 * 最小生成树，Kruskal算法
	 */
	public static void miniSpanTreeKruskal(MGraph g) {
		Edge[] edges = mgraphToEdge(g);
		int[] parent = new int[g.vertexNum];
		for (int i = 0; i < g.edgeNum; i++) {
			int n = find(parent, edges[i].begin);
			int m = find(parent, edges[i].end);
			if (n != m) { // 若相等，则说明有环路
				parent[n] = m;
				System.out.println("(" + edges[i].begin + "," + edges[i].end + ") " + edges[i].weight);
			}
		}
	}

	public static int find(int[] parent, int f) {
		while (parent[f] > 0) {
			f = parent[f];
		}
		return f;
	}

	/*
	 * 最短路径，Dijkstra算法，邻接矩阵
	 */
	public static void shortestPathDijkstra(MGraph g, int v0, int[] pathArc, int[] pathTable) {
		int[] fin = new int[g.vertexNum];		//判断是否要求v0与其他节点的最短距离，1表示求过或不要
		for (int v = 0; v < g.vertexNum; v++) {
			fin[v] = 0;
			pathTable[v] = g.arc[v0][v]; // 与v0节点有边的节点加上权值
			pathArc[v] = 0; // 初始化路径数组
		}
		pathTable[v0] = 0; // v0到v0路径为0
		fin[v0] = 1; // v0到v0不需要求路径
		int k = 0;

		for (int v = 1; v < g.vertexNum; v++) { // 主循环，每次求得v0到某个节点的最短距离
			int min = 65535;
			for (int w = 0; w < g.vertexNum; w++) {		//找出距离v0节点最近的节点，k为最近节点的下标
				if (fin[w] == 0 && pathTable[w] < min) {
					k = w;
					min = pathTable[w];
				}
			}
			fin[k] = 1;									//vk节点不需要在求最段路径了，已经是最短
			for (int w = 0; w < g.vertexNum; w++) {
				if (fin[w] == 0 && (min + g.arc[k][w] < pathTable[w])) {
					pathTable[w] = min + g.arc[k][w];
					pathArc[w] = k;						//v0到vw节点的前驱是vk节点，而到vk节点的前驱就是pathArc[k]=?值
				}
			}
		}
	}

	/*
	 * 获得从vk节点到vw节点的路径，Dijkstra算法
	 */
	public static void getPathByDijkstra(MGraph g,int k,int w){
		int[] pathArc = new int[g.vertexNum];
		int[] pathTable = new int[g.vertexNum];
		shortestPathDijkstra(g, k, pathArc, pathTable);
		Stack stack=new Stack();
		int x=w;
		while(pathArc[x]!=0){
			stack.push(pathArc[x]);
			x=pathArc[x];
		}
		System.out.print(k+"->");
		while(!stack.isEmpty()){
			System.out.print(stack.pop()+"->");
		}
		System.out.println(w+"  总权值为："+pathTable[w]);
	}
	
	/*
	 * 最短路径，Floyd算法，邻接矩阵
	 */
	public static void shortestPathFloyd(MGraph g, int[][] pathArc, int[][] pathTable){
		//初始化二维数组
		for(int i=0;i<g.vertexNum;i++){
			for(int j=0;j<g.vertexNum;j++){
				pathTable[i][j]=g.arc[i][j];
				pathArc[i][j]=j;
			}
		}
		for(int k=0;k<g.vertexNum;k++){
			for(int v=0;v<g.vertexNum;v++){
				for(int w=0;w<g.vertexNum;w++){
					if(pathTable[v][w]>pathTable[v][k]+pathTable[k][w]){
						pathTable[v][w]=pathTable[v][k]+pathTable[k][w];
						pathArc[v][w]=pathArc[v][k];
					}
				}
			}
		}
	}
	/*
	 * 获得从vk节点到vw节点的路径,Floyd算法
	 */
	public static void getPathByFloyd(MGraph g,int k,int w){
		int[][] pathArc=new int[g.vertexNum][g.vertexNum];
		int[][] pathTable=new int[g.vertexNum][g.vertexNum];
		shortestPathFloyd(g,pathArc,pathTable);
		int x=pathArc[k][w];
		System.out.print(k+"->");
		while(x!=w){
			System.out.print(x+"->");
			x=pathArc[x][w];
		}
		System.out.println(w+"  总权值为："+pathTable[k][w]);
	}
	
	/*
	 * 拓扑排序(判断图中是否有回路)，邻接表
	 */
	public static boolean toplogiclSort(GraphAdjList gl){
		Stack stack=new Stack();
		String res="";
		int count=0;				
		for(int i=0;i<gl.vertexNum;i++){
			if(gl.adjList[i].in==0){
				stack.push(i);
			}
		}
		while(!stack.isEmpty()){
			int gettop=(int)stack.pop();
			//System.out.print(gl.adjList[gettop].data+"->");//只有存在拓扑排序的时候才输出，所以储存在字符串里
			res=res+gl.adjList[gettop].data+"->";
			count++;
			GraphAdjList.EdgeNode e=gl.adjList[gettop].firstEdge;
			while(e!=null){
				int k=e.adjvex;
				gl.adjList[k].in=gl.adjList[k].in-1;
				if(gl.adjList[k].in==0){
					stack.push(k);
				}
				e=e.next;
			}
		}
		if(count<gl.vertexNum){
			return false;
		}else{
			System.out.println(res);
			return true;
		}
	}
	
	
	

}

package com.ex.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import com.ex.graph.GraphAdjList.EdgeNode;

/*
 * 图遍历工具类
 */
public class GraphTools {

	private static boolean[] visited;

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
	 * 获得从vk节点到vw节点的路径
	 */
	public static void getPathFromVkToVw(MGraph g,int k,int w){
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
		System.out.println(w);
	}
	
	public static void main(String[] args) {
		// MGraph g=creatMGraph();
		MGraph g = new MGraph(9, 16);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				g.arc[i][j] = 65535;
			}
		}
		for (int i = 0; i < 9; i++) {
			g.arc[i][i] = 0;
		}

		g.arc[0][1] = 1;
		g.arc[1][0] = 1;
		g.arc[0][2] = 5;
		g.arc[2][0] = 5;
		g.arc[1][2] = 3;
		g.arc[2][1] = 3;
		g.arc[1][3] = 7;
		g.arc[3][1] = 7;
		g.arc[1][4] = 5;
		g.arc[4][1] = 5;
		g.arc[2][4] = 1;
		g.arc[2][4] = 1;
		g.arc[2][5] = 7;
		g.arc[5][2] = 7;
		g.arc[3][4] = 2;
		g.arc[4][3] = 2;
		g.arc[3][6] = 3;
		g.arc[6][3] = 3;
		g.arc[4][5] = 3;
		g.arc[5][4] = 3;
		g.arc[4][6] = 6;
		g.arc[6][4] = 6;
		g.arc[4][7] = 9;
		g.arc[7][4] = 9;
		g.arc[5][7] = 5;
		g.arc[7][5] = 5;
		g.arc[6][7] = 2;
		g.arc[7][6] = 2;
		g.arc[7][8] = 4;
		g.arc[8][7] = 4;
		getPathFromVkToVw(g,2,8);
		/*int[] pathArc = new int[g.vertexNum];
		int[] pathTable = new int[g.vertexNum];
		shortestPathDijkstra(g, 0, pathArc, pathTable);
		for (int i = 0; i < pathArc.length; i++) {
			System.out.print(pathArc[i] + " ");
		}
		Stack stack=new Stack();
		int k=8;
		while(pathArc[k]!=0){
			stack.push(pathArc[k]);
			k=pathArc[k];
		}
		while(!stack.isEmpty()){
			System.out.print(stack.pop()+"->");
		}
		System.out.println();
		for (int i = 0; i < pathTable.length; i++) {
			System.out.print(pathTable[i] + " ");
		}*/
		/*
		 * g.arc[0][1] = 10; g.arc[1][0] = 10; g.arc[0][5] = 11; g.arc[5][0] =
		 * 11; g.arc[1][2] = 18; g.arc[2][1] = 18; g.arc[1][6] = 16; g.arc[6][1]
		 * = 16; g.arc[1][8] = 12; g.arc[8][1] = 12; g.arc[2][3] = 22;
		 * g.arc[3][2] = 22; g.arc[2][8] = 8; g.arc[8][2] = 8; g.arc[3][4] = 20;
		 * g.arc[4][3] = 20; g.arc[3][6] = 26; g.arc[6][3] = 26; g.arc[3][7] =
		 * 16; g.arc[7][3] = 16; g.arc[3][8] = 21; g.arc[8][3] = 21; g.arc[4][5]
		 * = 26; g.arc[5][4] = 26; g.arc[4][7] = 7; g.arc[7][4] = 7; g.arc[5][6]
		 * = 17; g.arc[6][5] = 17; g.arc[6][7] = 19; g.arc[7][6] = 19;
		 */
		// miniSpanTreeKruskal(g);
		// Edge[] edges=mgraphToEdge(g);
		// miniSpanTreePrim(g);
		// BFSTraverse1(g);

	}

}

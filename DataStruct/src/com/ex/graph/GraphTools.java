package com.ex.graph;

import java.util.LinkedList;
import java.util.Queue;
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
	public static void prime(MGraph g) {

		int[] lowcost = new int[g.vertexNum];
		int[] adjvex = new int[g.vertexNum];
		lowcost[0] = 0; // 下标为0的顶点已经加入生成树中
		adjvex[0] = 0; // 初始化第一个顶点为0；
		for (int i = 1; i < g.vertexNum; i++) {
			lowcost[i] = g.arc[0][i];			//将v0顶点与之有边的权值存入数组
			adjvex[i] = 0;						//初始化都为v0的下标
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

	public static void main(String[] args) {
		// MGraph g=creatMGraph();
		/*MGraph g = new MGraph(9, 15);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				g.arc[i][j] = 65535;
			}
		}
		for (int i = 0; i < 9; i++) {
			g.arc[i][i] = 0;
		}
		g.arc[0][1] = 10;
		g.arc[1][0] = 10;
		g.arc[0][5] = 11;
		g.arc[5][0] = 11;
		g.arc[1][2] = 18;
		g.arc[2][1] = 18;
		g.arc[1][6] = 16;
		g.arc[6][1] = 16;
		g.arc[1][8] = 12;
		g.arc[8][1] = 12;
		g.arc[2][3] = 22;
		g.arc[3][2] = 22;
		g.arc[2][8] = 8;
		g.arc[8][2] = 8;
		g.arc[3][4] = 20;
		g.arc[4][3] = 20;
		g.arc[3][6] = 26;
		g.arc[6][3] = 26;
		g.arc[3][7] = 16;
		g.arc[7][3] = 16;
		g.arc[3][8] = 21;
		g.arc[8][3] = 21;
		g.arc[4][5] = 26;
		g.arc[5][4] = 26;
		g.arc[4][7] = 7;
		g.arc[7][4] = 7;
		g.arc[5][6] = 17;
		g.arc[6][5] = 17;
		g.arc[6][7] = 19;
		g.arc[7][6] = 19;
		prime(g);*/
		// BFSTraverse1(g);

	}

}

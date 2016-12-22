package com.ex.graph;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * 无向图的邻接表表示
 */
public class GraphAdjList {

	public static class EdgeNode {
		int adjvex;// 邻接点域，存储该顶点对应的下标
		int weight;// 存储权值，对于非网图可以不需要
		EdgeNode next = null;

		public EdgeNode(int adjvex, int weight) {
			this.adjvex = adjvex;
			this.weight = weight;
		}

		public EdgeNode(int adjvex) {
			this.adjvex = adjvex;
		}

		public EdgeNode() {
		}
	}

	public static class VertexNode {
		String data = null; // 顶点域，存储顶点信息
		EdgeNode firstEdge = null;// 边表头指针
		int in;						//拓扑排序时用到表示该节点的入度
		public VertexNode(String data) {
			this.data = data;
		}

		public VertexNode() {
		}
	}

	VertexNode[] adjList;
	int vertexNum, edgeNum;

	public GraphAdjList(int vertexNum, int edgeNum) {
		this.adjList = new VertexNode[vertexNum];
		for (int i = 0; i < vertexNum; i++) {
			this.adjList[i] = new VertexNode();
		}
		this.vertexNum = vertexNum;
		this.edgeNum = edgeNum;
	}

}

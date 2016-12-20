package com.ex.graph;

/*
 * 无向图的邻接多重表结构
 */
public class GraphAdjList2 {

	public static class EdgeNode {
		int ivex, jvex; // 顶点表中的下标，两个表示一条边
		EdgeNode ilink = null;// 指向依附于顶点ivex的下一条边,****注意：ilink指向的节点的jvex等于自身的ivex；
		EdgeNode jlink = null;// 指向依附于顶点jvex的下一条边,****注意：jlink指向的节点的ivex等于自身的jvex；

		public EdgeNode() {
		}

		public EdgeNode(int ivex, int jvex) {
			this.ivex = ivex;
			this.jvex = jvex;
		}
	}

	public class VertexNode {
		String data;
		EdgeNode firstEdge = null;

		public VertexNode() {
		}

		public VertexNode(String data) {
			this.data = data;
		}
	}

	VertexNode[] adjList = null;
	int vertexNum, edgeNum;

	public GraphAdjList2(int vertexNum, int edgeNum) {
		this.vertexNum = vertexNum;
		this.edgeNum = edgeNum;
		this.adjList = new VertexNode[vertexNum];
		for (int i = 0; i < vertexNum; i++) {
			this.adjList[i] = new VertexNode();
		}
	}

	public static void main(String[] args) {
		String[] vertexNodes = { "v0", "v1", "v2", "v3" };
		GraphAdjList2 graph = new GraphAdjList2(4, 5);
		for (int k = 0; k < 4; k++) {
			graph.adjList[k].data = vertexNodes[k];
		}
		EdgeNode v01 = new EdgeNode(0, 3);
		EdgeNode v12 = new EdgeNode(1, 2);
		EdgeNode v23 = new EdgeNode(2, 3);
		EdgeNode v30 = new EdgeNode(3, 0);
		EdgeNode v02 = new EdgeNode(0, 2);
		graph.adjList[0].firstEdge = v01;
		graph.adjList[1].firstEdge = v12;
		graph.adjList[2].firstEdge = v23;
		graph.adjList[3].firstEdge = v30;
		v01.ilink = v30;
		v12.ilink = v01;
		v12.jlink = v02;
		v23.ilink = v12;
		v30.ilink = v23;
		v30.jlink = v02;
	}

}

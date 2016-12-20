package com.ex.graph;

import java.util.Scanner;

/*
 * 有向图的十字链表表示
 */
public class OrthogonalList {

	public static class EdgeNode {
		int tailVex, headVex;
		int weight; // 若是网，则有权重
		EdgeNode headLink = null; // 拥有相同的“头”,箭头指向相同
		EdgeNode tailLink = null; // 拥有相同“尾”，从相同的节点指出

		public EdgeNode() {
		}

		public EdgeNode(int tailVex, int headVex) {
			this.tailVex = tailVex;
			this.headVex = headVex;
		}
	}

	public class VertexNode {
		String data;
		EdgeNode firstIn = null;
		EdgeNode firstOut = null;

		public VertexNode() {
		};

		public VertexNode(String data) {
			this.data = data;
		}
	}

	VertexNode[] adjList;
	int vertextNum, edgeNum;

	public OrthogonalList() {
	}

	public OrthogonalList(int vertexNum, int edgeNum) {
		this.adjList = new VertexNode[vertexNum];
		for (int i = 0; i < vertexNum; i++) {
			this.adjList[i] = new VertexNode();
		}
		this.vertextNum = vertexNum;
		this.edgeNum = edgeNum;
	}

	public static void main(String[] args) {
		EdgeNode temp = new EdgeNode();
		int vertexNum = 4;
		int edgeNum = 5;
		OrthogonalList graph = new OrthogonalList(vertexNum, edgeNum);
		String[] vertexNodes = { "v0", "v1", "v2", "v3" };
		for (int k = 0; k < vertexNum; k++) {
			graph.adjList[k].data = vertexNodes[k];
		}
		EdgeNode v03 = new EdgeNode(0, 3);
		EdgeNode v10 = new EdgeNode(1, 0);
		EdgeNode v12 = new EdgeNode(1, 2);
		EdgeNode v21 = new EdgeNode(2, 1);
		EdgeNode v20 = new EdgeNode(2, 0);
		graph.adjList[0].firstIn = v10;
		graph.adjList[0].firstOut = v03;
		graph.adjList[1].firstIn = v21;
		graph.adjList[1].firstOut = v10;
		graph.adjList[2].firstIn = v12;
		graph.adjList[2].firstOut = v20;
		graph.adjList[3].firstIn = v03;
		v10.headLink = v20;
		v10.tailLink = v12;
		v20.tailLink = v21;
	}

}

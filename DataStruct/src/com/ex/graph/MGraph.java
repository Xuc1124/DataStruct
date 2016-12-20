package com.ex.graph;

public class MGraph {
	String[] vexs;
	int[][] arc;
	int vertexNum, edgeNum;

	public MGraph() {
	}

	public MGraph(int vertexNum, int edgeNum) {
		this.vertexNum = vertexNum;
		this.edgeNum = edgeNum;
		this.vexs = new String[vertexNum];
		this.arc = new int[vertexNum][vertexNum];
		for (int i = 0; i < vertexNum; i++) {
			for (int j = 0; j < vertexNum; j++) {
				this.arc[i][j] = 0;
			}
		}
	}
}

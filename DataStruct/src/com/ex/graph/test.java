package com.ex.graph;

public class test {
	public static void main(String[] args) {
		// MGraph g=creatMGraph();
		/*MGraph g = new MGraph(9, 16);
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
		g.arc[8][7] = 4;*/
		/*int[][] pathArc=new int[g.vertexNum][g.vertexNum];
		int[][] pathTable=new int[g.vertexNum][g.vertexNum];
		shortestPathFloyd(g,pathArc,pathTable);
		for(int i=0;i<g.vertexNum;i++){
			System.out.print(pathTable[2][i]+" ");
		}*/
		/*getPathByFloyd(g,5,8);
		getPathByDijkstra(g,5,8);*/
		//getPathFromVkToVw(g,2,8);
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
		GraphAdjList gl=GraphTools.creatGraphAdjList();
		gl.adjList[0].in=0;
		gl.adjList[1].in=0;
		gl.adjList[2].in=2;
		gl.adjList[3].in=0;
		gl.adjList[4].in=2;
		gl.adjList[5].in=3;
		gl.adjList[6].in=1;
		gl.adjList[7].in=2;
		gl.adjList[8].in=2;
		gl.adjList[9].in=2;
		gl.adjList[10].in=1;
		gl.adjList[11].in=2;
		gl.adjList[12].in=1;
		gl.adjList[13].in=2;
		boolean flag=GraphTools.toplogiclSort(gl);
		System.out.println(flag);
	}
}

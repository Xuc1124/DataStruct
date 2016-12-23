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
		//GraphAdjList gl=GraphTools.creatGraphAdjList();
		GraphAdjList gl=new GraphAdjList(10,13);
		String[] strs={"v0","v1","v2","v3","v4","v5","v6","v7","v8","v9"};
		for(int i=0;i<gl.vertexNum;i++){
			gl.adjList[i].data=strs[i];
		}
		GraphAdjList.EdgeNode v00=new GraphAdjList.EdgeNode(2,4);
		v00.next=new GraphAdjList.EdgeNode(1,3);
		gl.adjList[0].firstEdge=v00;
		
		GraphAdjList.EdgeNode v10=new GraphAdjList.EdgeNode(4,6);
		v10.next=new GraphAdjList.EdgeNode(3,5);
		gl.adjList[1].firstEdge=v10;
		
		GraphAdjList.EdgeNode v20=new GraphAdjList.EdgeNode(5,7);
		v20.next=new GraphAdjList.EdgeNode(3,8);
		gl.adjList[2].firstEdge=v20;
		
		GraphAdjList.EdgeNode v30=new GraphAdjList.EdgeNode(4,3);
		gl.adjList[3].firstEdge=v30;
		
		GraphAdjList.EdgeNode v40=new GraphAdjList.EdgeNode(7,4);
		v40.next=new GraphAdjList.EdgeNode(6,9);
		gl.adjList[4].firstEdge=v40;
		
		GraphAdjList.EdgeNode v50=new GraphAdjList.EdgeNode(7,6);
		gl.adjList[5].firstEdge=v50;
		
		GraphAdjList.EdgeNode v60=new GraphAdjList.EdgeNode(9,2);
		gl.adjList[6].firstEdge=v60;
		
		GraphAdjList.EdgeNode v70=new GraphAdjList.EdgeNode(8,5);
		gl.adjList[7].firstEdge=v70;
		
		GraphAdjList.EdgeNode v80=new GraphAdjList.EdgeNode(9,3);
		gl.adjList[8].firstEdge=v80;
		
		gl.adjList[0].in=0;
		gl.adjList[1].in=1;
		gl.adjList[2].in=1;
		gl.adjList[3].in=2;
		gl.adjList[4].in=2;
		gl.adjList[5].in=1;
		gl.adjList[6].in=1;
		gl.adjList[7].in=2;
		gl.adjList[8].in=1;
		gl.adjList[9].in=2;
		//gl.adjList[10].in=1;
		/*gl.adjList[11].in=2;
		gl.adjList[12].in=1;
		gl.adjList[13].in=2;*/
		//boolean flag=GraphTools.toplogiclSort(gl);
		GraphTools.criticalPath(gl);
		//System.out.println(flag);
	}
}

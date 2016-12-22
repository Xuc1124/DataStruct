package com.ex.graph;

/*
 * 边集数组
 */
public class Edge {
	int begin;
	int end;
	int weight;
	public Edge(int begin,int end,int weight){
		this.begin=begin;
		this.end=end;
		this.weight=weight;
	}
	public Edge(){}
}

package com.ex.sort;

/*
 * 排序用的顺序表结构
 */
public class SqList {
	// private final static int MAXSIZE=9;
	int[] r;
	int length;
	int t; // 哨兵

	public SqList(int n) {
		this.r = new int[n];
		this.length = n;
	}

	public SqList() {
	};

	public void swap(int i, int j) {
		int temp = this.r[i];
		this.r[i] = this.r[j];
		this.r[j] = temp;
	}

	public void print() {
		for (int i = 0; i < this.r.length; i++) {
			System.out.print(this.r[i] + " ");
		}
		System.out.println();
	}
}

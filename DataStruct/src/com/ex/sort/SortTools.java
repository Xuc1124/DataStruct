package com.ex.sort;

/*
 * 排序工具类
 */
public class SortTools {

	/*
	 * 冒泡排序(初级版)
	 */
	public static void bubbleSort0(SqList l) {
		for (int i = 0; i < l.length; i++) {
			for (int j = i + 1; j < l.length; j++) {
				if (l.r[i] > l.r[j]) {
					l.swap(i, j);
				}
			}
		}
	}

	/*
	 * 冒泡排序
	 */
	public static void bubbleSort(SqList l) {
		for (int i = 0; i < l.length; i++) {
			/*
			 * for(int j=l.length-2;j>=i;j--){ //大话数据结构上的 if(l.r[j]>l.r[j+1]){
			 * l.swap(j, j+1); } }
			 */
			for (int j = i + 1; j < l.length; j++) { // 自己写的
				if (l.r[i] > l.r[j]) {
					l.swap(i, j);
				}
			}
		}
	}

	/*
	 * 冒泡排序优化
	 */
	public static void bubbleSort2(SqList l) {
		boolean flag = true;
		for (int i = 0; i < l.length && flag == true; i++) {
			flag = false;
			for (int j = l.length - 2; j >= i; j--) { // 大话数据结构上的
				if (l.r[j] > l.r[j + 1]) {
					l.swap(j, j + 1);
					flag = true;
				}
			}
			/*
			 * for(int j=i+1;j<l.r.length;j++){ if(l.r[i]>l.r[j]){ l.swap(i, j);
			 * flag=true; } }
			 */
		}
	}

	/*
	 * 选择排序
	 */
	public static void selectSort(SqList l) {
		for (int i = 0; i < l.length; i++) {
			int min = i; // 定义当前下标尾最小下标
			for (int j = i + 1; j < l.length; j++) {
				if (l.r[min] > l.r[j]) {
					min = j; // 如果有小于当前小标的数，则min改为那个下标
				}
			}
			if (i != min) { // 若不相等，说明有比下标为i的值小的数，交换
				l.swap(i, min);
			}
		}
	}

	/*
	 * 插入排序（相当于希尔排序h=1）
	 */
	public static void insertSort(SqList l) {
		for (int i = 1; i < l.length; i++) {
			for (int j = i; j > 0 && l.r[j] < l.r[j - 1]; j--) {
				l.swap(j, j - 1);
			}
		}
	}

	/*
	 * 希尔排序
	 */
	public static void shellSort(SqList l) {
		int len = l.length;
		int h = 1;
		while (h < (len / 3)) {
			h = h * 3 + 1;
		}
		while (h >= 1) {
			for (int i = h; i < len; i++) {
				for (int j = i; j >= h && (l.r[j] < l.r[j - h]); j -= h) {
					l.swap(j, j - h);
				}
			}
			h = h / 3;
		}
	}

	/*
	 * 堆排序
	 */
	public static void heapSort(SqList l) {
		int len = l.r.length - 1;
		for (int k = len / 2 - 1; k >= 0; k--) {
			sink(l, k, len);
		}
		while (len > 1) {
			l.swap(0, len--);
			sink(l, 0, len);
		}
	}

	/*
	 * 保证节点的值比它的两个子节点值大，用于从小到大排序
	 */
	public static void sink(SqList l, int k, int len) {
		while (2 * k + 2 <= len) {
			int j = 2 * k + 1;
			if (j < len && l.r[j] < l.r[j + 1])
				j++;
			if (l.r[k] >= l.r[j])
				break;
			l.swap(k, j);
			k = j;
		}
	}

	/*
	 * 归并排序（递归），需要额外数组
	 */
	private static int[] res;

	public static void mergeSort(SqList l) {
		res = new int[l.length];
		mergeSort(l, 0, l.r.length - 1);
	}

	public static void mergeSort(SqList l, int low, int high) {
		if (high <= low)
			return;
		int mid = low + (high - low) / 2;
		mergeSort(l, low, mid);
		mergeSort(l, mid + 1, high);
		merge(l, low, mid, high);
	}

	public static void merge(SqList l, int low, int mid, int high) {
		int i = low, j = mid + 1;
		for (int k = low; k <= high; k++) {
			res[k] = l.r[k];
		}
		for (int k = low; k <= high; k++) {
			if (i > mid) {
				l.r[k] = res[j++];
			} else if (j > high) {
				l.r[k] = res[i++];
			} else if (res[j] < res[i]) {
				l.r[k] = res[j++];
			} else {
				l.r[k] = res[i++];
			}
		}
	}

	/*
	 * 快速排序，递归调用
	 */
	public static void quickSort(SqList l) {
		quickSort(l, 0, l.r.length - 1);
	}

	public static void quickSort(SqList l, int left, int right) {
		if (left > right)
			return;
		int temp = l.r[left]; // 作为基准，要使基准左边小于基准，右边大于基准
		int i = left; // i,j为两个指针，一个从左往右，一个从右往左
		int j = right;
		while (i != j) { // 循环直到i,j相遇，说明i走过的地方都小于基准，j走过的地方都大于基准
			while (l.r[j] >= temp && i < j) { // 先走右边的，很重要
				j--;
			}
			while (l.r[i] <= temp && i < j) {
				i++;
			}
			if (i < j) { // i,j未相遇，则交换
				l.swap(i, j);
			}
		}
		l.r[left] = l.r[i];
		l.r[i] = temp;
		quickSort(l, left, i - 1);
		quickSort(l, i + 1, right);
	}
}

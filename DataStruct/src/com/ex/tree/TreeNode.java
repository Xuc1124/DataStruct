package com.ex.tree;

public class TreeNode<T> {
	public T value = null;
	public TreeNode<T> leftChild = null;
	public TreeNode<T> rightChild = null;

	public TreeNode(T value) {
		this.value = value;
	}

	public TreeNode() {
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof TreeNode)) {
			return false;
		}
		return this.value.equals(((TreeNode<?>) obj).value);
	}

	public int hashCode() {
		return this.value.hashCode();
	}

	public String toString() {
		return this.value == null ? "" : this.value.toString();
	}


}

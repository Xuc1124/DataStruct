package com.ex.linkedlist;

import java.util.HashMap;
import java.util.Stack;

public class NodeTools {
	
	/*
	 * 返回单链表节点个数
	 */
	public static <T> int getLength(Node<T> head){
		int len=0;
		Node h=head;
		while(h!=null){
			h=h.next;
			len++;
		}
		return len;
	}
	
	/*
	 * 单链表反转
	 */
	public static <T> Node<T> reverseList(Node<T> head){
		if(head==null||head.next==null) return head;
		Node<T> pre=null,nxt=null;
		while(head!=null){
			nxt=head.next;
			head.next=pre;
			pre=head;
			head=nxt;
		}
		return pre;
	}
	
	/*
	 * 单链表反转，递归
	 */
	public static <T> Node<T> reverseListRec(Node<T> head){
		if(head==null||head.next==null) return head;
		Node<T> reHead=reverseListRec(head.next);
		head.next.next=head;
		head.next=null;
		return reHead;
	}
	
	/*
	 * 查找倒数第k个节点
	 */
	public static <T> Node<T> getKthNode(Node<T> head,int k){
		if(head==null||k<1) return null;
		Node<T> p1=head;
		Node<T> p2=head;
		for(int i=1;i<k;i++){
			if(p1.next==null) return null;
			p1=p1.next;
		}
		while(p1.next!=null){
			p1=p1.next;
			p2=p2.next;
		}
		return p2;
	}
	
	/*
	 * 从尾到头打印链表
	 */
	public static <T> void revPrint(Node<T> head){
		Stack<Node> stack=new Stack<Node>();
		Node h=head;
		while(h!=null){
			stack.push(h);
			h=h.next;
		}
		while(!stack.isEmpty()){
			System.out.print(stack.pop().val+" ");
		}
		System.out.println();
	}
	
	/*
	 * 从尾到头打印单链表，递归
	 */
	public static <T> void revPrintRec(Node<T> head){
		if(head==null) return;
		else{
			revPrintRec(head.next);
			System.out.print(head.val+" ");
		}
	}
	
	/*
	 * 判断单链表中是否有环
	 */
	public static <T> boolean hasCycle(Node<T> head){
		if(head==null) return false;
		Node<T> p1=head;
		Node<T> p2=head;
		while(p1!=null&&p2!=null&&p2.next!=null){
			p1=p1.next;
				p2=p2.next.next;
			if(p1==p2){
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 存在环，求进入环中的第一个节点，hashmap
	 */
	public static <T> Node<T> getFirstNodeInCycle1(Node<T> head){
		Node<T> target=null;
		Node<T> h=head;
		HashMap<Node<T>,Boolean> map=new HashMap<Node<T>,Boolean>();
		while(h!=null){
			if(map.containsKey(h)){
				target=h;
				break;
			}else{
				map.put(h, true);
			}
			h=h.next;
		}
		return target;
	}
	
	/*
	 * 存在环，求进入环中的第一个节点，常规
	 */
	public static <T> Node<T> getFirstNodeInCycle2(Node<T> head){
		Node<T> fast=head;
		Node<T> slow=head;
		while(fast!=null&&slow!=null){
			slow=slow.next;
			fast=fast.next.next;
			if(slow==fast) break;
		}
		if(fast==null||fast==null) return null;
		slow=head;
		while(slow!=fast){
			slow=slow.next;
			fast=fast.next;
		}
		return slow;
	}
	
	/*
	 * 合并两个有序的单链表
	 */
	public static <Integer> Node<Integer> mergeSortedList(Node<Integer> head1,Node<Integer> head2){
		if(head1==null) return head2;
		if(head2==null) return head2;
		Node<Integer> target=null;
		if((int)head1.val > (int)head2.val){
			target=head2;
			head2=head2.next;
		}else{
			target=head1;
			head1=head1.next;
		}
		target.next=null;
		Node<Integer> mergeHead=target;
		while(head1!=null&&head2!=null){
			if((int)head1.val>(int)head2.val){
				target.next=head2;
				head2=head2.next;
			}else{
				target.next=head1;
				head1=head1.next;
			}
			target=target.next;
			target.next=null;
		}
		if(head1==null){
			target.next=head2;
		}else{
			target.next=head1;
		}
		return mergeHead;
	}
	
	/*
	 * 合并两个有序的单链表，递归 
	 */
	public static <Integer> Node<Integer> mergeSortedListRec(Node<Integer> head1,Node<Integer> head2){
		if(head1==null) return head2;
		if(head2==null) return head1;
		if((int)head1.val>(int)head2.val){
			head2.next=mergeSortedListRec(head2.next, head1);
			return head2;
		}else{
			head1.next=mergeSortedListRec(head1.next, head2);
			return head1;
		}
	}
	
	/*
	 * 对单链表进行排序，插入排序
	 */
	public static <Integer> Node<Integer> sortListByInsert(Node<Integer> head){
		if(head==null||head.next==null){
			return head;
		}
		Node<Integer> pnex=head.next;
		Node<Integer> pnex_nex=null;
		head.next=null;
		while(pnex!=null){
			pnex_nex=pnex.next;
			Node<Integer> temp=head;
			Node<Integer> temp_pre=null;
			while(temp!=null){
				if((int)temp.val>(int)pnex.val)
					break;
				temp_pre=temp;
				temp=temp.next;
			}
			if(temp_pre==null){
				head=pnex;
				pnex.next=temp;
			}else{
				temp_pre.next=pnex;
				pnex.next=temp;
			}
			pnex=pnex_nex;
		}
		return head;
	}
	
}

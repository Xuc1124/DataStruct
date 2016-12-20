package com.ex.string;

public class StringTools {
	/*
	 * 朴素匹配算法
	 */
	public static int index(String s,String t){
		int i=0,j=0;
		while(i<s.length()&&j<t.length()){
			if(s.charAt(i)==t.charAt(j)){
				i++;
				j++;
			}else{
				i=i-j+1;
				j=0;
			}
			if(j==t.length()){
				return i-t.length();
			}
		}
		return 0;
	}
	/*
	 * 获取next数组
	 */
	public static int[] getNext(String t){
		int[] next=new int[t.length()];
		next[0]=0;
		int i=0,j=-1;
		while(i<t.length()-1){
			if(j==-1||(t.charAt(i)==t.charAt(j))){
				i++;
				j++;
				next[i]=j+1;
			}else{
				j=next[j]-1;
			}
		}
		/*
		 * 求nextval数组
		 */
		int[] nextval=new int[t.length()];
		nextval[0]=0;
		for(int k=1;k<t.length();k++){
			if(t.charAt(next[k]-1)==t.charAt(k)){
				nextval[k]=nextval[next[k]-1];
			}else{
				nextval[k]=next[k];
			}
		}
		return nextval;
	}
	
	public static int indexKMP(String s,String t){
		int i=0,j=0;
		int[] next=new int[t.length()];
		next=getNext(t);
		while(i<s.length()&&j<t.length()){
			if(j==0||s.charAt(i)==t.charAt(j)){
				i++;
				j++;
			}else{
				j=next[j]-1;
			}
			if(j==t.length()){
				return i-t.length();
			}
		}
		return 0;
	}
}

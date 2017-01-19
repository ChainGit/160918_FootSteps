package com.bxd.day13;

public class StringBufferTest {

	public static void main(String[] args){
		
		StringBuffer sb = new StringBuffer();
		sop(sb.capacity());
		sb.append(1);
		sb.append("abc");
		sb.append(true);
		sb.append('s');
		sb.append(new char[]{'a','b',});
		sb.append(new StringBuffer("ERDCF"));
		sb.append(1.34);
		sop(sb);
		sop(sb.capacity());
		sop(sb.charAt(3));
		sb.delete(3, 4);
		sop(sb);
		sop(sb.capacity());
		sb.deleteCharAt(7);
		sop(sb);
		sop(sb.capacity());
		sop(sb.length());
		char[] cc=new char[sb.capacity()];
		sb.getChars(0, sb.length(), cc, 0);
		sop(cc);
		sop(sb.indexOf("a"));
		sb.insert(4, "ABC");
		sop(sb);
		sop(sb.lastIndexOf("ABC"));
		sb.replace(4,7,"XdC");
		sop(sb);
		sb.reverse();
		sop(sb);
		sb.delete(3, 10);
		sop(sb.capacity());
		sop(sb);
		sb.trimToSize();
		sop(sb);
		sop(sb.capacity());
	}
	
	public static void sop(Object o){
		System.out.println(o.toString());
	}
}

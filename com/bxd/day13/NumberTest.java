package com.bxd.day13;

import static com.bxd.day00.SimplePrint.sop;

public class NumberTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer i = new Integer(100);
		Integer j = new Integer("100");
		sop(i==j);
		sop(i.equals(j));
		
		sop(Integer.bitCount(1));
		sop(Integer.bitCount(-1));
		sop(i.compareTo(j));
		
		int i1=i.intValue();
		sop(i1);
	}

}

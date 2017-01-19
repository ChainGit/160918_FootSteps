package com.bxd.day00;

public class SimplePrint {

	public synchronized static void sop(Object obj) {
		System.out.println(obj.toString());
	}

	public synchronized static <T> void sop2(T t) {
		System.out.println(t);
	}
	
	@Override
	public String toString(){
		return getClass().getName()+"@"+Integer.toHexString(hashCode());
	}
	
	@Override
	public boolean equals(Object obj){
		return this==obj;
	}
}

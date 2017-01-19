package com.bxd.day15;


import static com.bxd.day00.SimplePrint.sop;

import java.util.ArrayList;
import java.util.Iterator;

public class GenericTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		print(new Integer(123));
		print(new String("dcc"));
		
		MyGenericClass<String> m = new MyGenericClass<>();
		m.show(new Integer(123));
		m.show(new String("ghv"));
		m.show2(new Boolean(true));
		m.show3(new ArrayList<Integer>());
		
	}
	
	public static <T> void print(T t){
		sop(t);
	}

}

interface MyInter<T extends Number>{
	void show(T t);
}

class MyGenericClass<Q> implements MyInter<Integer>{

	@Override
	public void show(Integer t) {
		// TODO Auto-generated method stub
		sop(t);
	}
	
	public <E> void show2(E e){
		sop(e);
	}
	
	@SuppressWarnings("unused")
	public void show3(ArrayList<?> t){
		Iterator<? extends Number> it;
		sop(t);
	}
	
	public void show(Q q){
		sop(q);
	}
	
}
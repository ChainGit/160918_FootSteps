package com.bxd.day07;

public class InterfaceClass extends AbstractClass implements Inter1,Inter2,Inter3{

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println("Susc");
	}
	
	public static void main(String[] a){
		
		//The field InterfaceClass.HSC is ambiguous
		//模棱两可的
		System.out.println(InterfaceClass.HSC);
	}
	
	public InterfaceClass() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void work() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void study() {
		// TODO Auto-generated method stub
		
	}
	
	//InterfaceClass.ppp();
	
	
	

}

interface Inter3{
	public static void print(){
		System.out.println("dsdscc");
	}
}

interface Inter1{
	public static void ppp(){
		System.out.println("PPPPHC");
	}
	
	public static final int HSC=123;
	
	public abstract void print();
}

interface Inter2{
	//public static final int HSC=124;

	//public abstract int print();
	public abstract void print();
}

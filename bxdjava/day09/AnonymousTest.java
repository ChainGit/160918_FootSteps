package com.bxd.day09;

public class AnonymousTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Outer o = new Outer();
		o.print();
		int n=234;
		o.print3(n);
		
		/*Object o1=new Object()
		{
			public void show(int a){
				System.out.println("Object :: show :: num is "+a);
			}
		};
		((Object) o1).show(n);*/
		
		new Object()
		{
			public void show(int a){
				System.out.println("Object :: show :: num is "+a);
			}
		}.show(n);
		
		Outer.fun().method();
		new Outer.InnerClass().method();
	}

}

interface InterInAnonymousTest{
	int num=41;
	public void showInInter();
}

interface InterInAnonymousTest2{
	public void printNum(int num);
}

class Outer{
	
	public Outer(){
		
	}
	
	public Outer(InterInAnonymousTest i){
		i.showInInter();
	}
	
	private int num=10;
	private static int STATIC_NUM=20;
	
	class Inner{
		public void show(){
			System.out.println("Inner :: show :: num is "+num);
		}
		
		//The method showStatic cannot be declared static; static methods can only be declared in a static or top level type
		//public static void showStatic(){ }
	}
	
	static class InnerStatic{
		public void show(){
			System.out.println("InnerStatic :: show :: num is "+new Outer().num);
			System.out.println("InnerStatic :: show :: num is "+STATIC_NUM);
		}
		
		public static void showStatic(){
			new InnerStatic().show();
		}
	}
	
	class Inter2{
		int num=13;
		public void show(){
			int num=14;
			System.out.println("Inner2 :: show :: num is "+num);
			System.out.println("Inner2 :: show :: num is "+this.num);
			System.out.println("Inner2 :: show :: num is "+Inter2.this.num);
			System.out.println("Inner2 :: show :: num is "+Outer.this.num);
		}
	}
	
	class Inter3 implements InterInAnonymousTest{

		@Override
		public void showInInter() {
			// TODO Auto-generated method stub
			System.out.println("class Inter3 implements InterInAnonymousTest :: showInInter :: num is "+num);
		}
		
	}
	
	public void print(){
		new Inner().show();
		InnerStatic.showStatic();
		new Inter2().show();
		new Inter3().showInInter();
		
		int numx=21;
		class InnerInPrint{
			int num=22;
			void show(){
				System.out.println("InnerInPrint :: show :: num is "+num);
				System.out.println("InnerInPrint :: show :: num is "+this.num);
				System.out.println("InnerInPrint :: show :: num is "+InnerInPrint.this.num);
				System.out.println("InnerInPrint :: show :: num is "+new Outer().new Inter2().num);
				System.out.println("InnerInPrint :: show :: num is "+Outer.this.num);
				System.out.println("InnerInPrint :: show :: num is "+Outer.this.num);
			}
		}
		
		new InnerInPrint().show();
		
		new InterInAnonymousTest() {
			int num=31;
			@Override
			public void showInInter() {
				// TODO Auto-generated method stub
				int num=32;
				System.out.println("InterInAnonymousTest :: showInInter :: num is "+num);
				System.out.println("InterInAnonymousTest :: showInInter :: num is "+this.num);
			}
		}.showInInter();
		
		new InterInAnonymousTest2() {
			@Override
			public void printNum(int num) {
				// TODO Auto-generated method stub
				System.out.println("new InterInAnonymousTest2() :: printNum :: num is "+num);
			}
		}.printNum(numx);
		
		new Outer(new InterInAnonymousTest(){

			@Override
			public void showInInter() {
				// TODO Auto-generated method stub
				System.out.println("new Outer(new InterInAnonymousTest()) :: "+num);
				System.out.println("new Outer(new InterInAnonymousTest()) :: "+numx);
			}
			
		});
		
		print2(numx);
		
	}
	
	public void print2(int numx){
		new InterInAnonymousTest2() {
			@Override
			public void printNum(int num) {
				// TODO Auto-generated method stub
				System.out.println("print2 :: InterInAnonymousTest2() :: printNum :: num is "+numx);
			}
		}.printNum(10); 
	}
	
	public void print3(int a){
		int b=11;
		//a++;//Local variable a defined in an enclosing scope must be final or effectively final
		class Innerx{
			void print(){
				System.out.println("print3 :: print :: num is "+a+" "+b);
			}
		}
		Innerx i = new Innerx();
		i.print();
	}
	
	interface Inter{
		public void method();
	}
	
	static Inter fun(){
		return new Inter(){

			@Override
			public void method() {
				// TODO Auto-generated method stub
				System.out.println("HAHAHA");
			}
			
		};
	}
	
	static class InnerClass implements Inter{

		@Override
		public void method() {
			// TODO Auto-generated method stub
			System.out.println("HOHOHO");
		}
		
	}
	
}

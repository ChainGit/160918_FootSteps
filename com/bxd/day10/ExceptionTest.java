package com.bxd.day10;

public class ExceptionTest {
	public static void main(String[] args) throws MyThrowable,Exception{
		/*
		try{
			DemoA a = new DemoA();
			//a.run();
			int n=a.show(16,0);
			System.out.println("n = "+n);
		}catch(MyException e){
			throw new Exception("发生错误1");
		}catch(Exception e){
			throw new MyRuntimeException("发生错误2");
		}finally{
			System.out.println("Over");
		}
		
		
		DemoA a = new DemoA();
		a.run();
		*/
		
		DemoA a = new DemoA();
		a.run2();
		
	}

}

interface InterA{

}

class DemoA{
	int show(int a,int b) throws MyException {
		if(b==0)
			throw new MyException("除数不能为零");
		return a/b;
	}
	
	void run(){
		throw new MyRuntimeException("发生错误3");
	}
	
	void run2()throws MyThrowable{
		throw new MyThrowable("发生错误4");
	}
	
	
	class Inner{
	
	}
}

class MyException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 317330083698987372L;

	MyException(String msg){
		super(msg);
	}
}

class MyRuntimeException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1594371933718568845L;

	MyRuntimeException(String msg){
		super(msg);
	}
}

class MyThrowable extends Throwable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -483693093015837054L;

	MyThrowable(String msg){
		super(msg);
	}

}
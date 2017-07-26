package com.bxd.day06;

public class DesignModeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// The constructor SingleTonEager() is not visible
		//SingleTonEager s = new SingleTonEager();
		
		SingleTonEager se1=SingleTonEager.getInstance();
		SingleTonEager se2=SingleTonEager.getInstance();
		se1.setNum(5);
		System.out.println("The method getNum return is "+se2.getNum());
		
		SingleTonLazy sl1=SingleTonLazy.getInstance();
		SingleTonLazy sl2=SingleTonLazy.getInstance();
		sl1.setNum(9);
		System.out.println("The method getNum return is "+sl2.getNum());
		SingleTonLazy.getInstance();
		sl2.setNum(4);
		SingleTonLazy sl3=SingleTonLazy.getInstance();
		System.out.println("The method getNum return is "+sl3.getNum());

	}

}

/**
 * 单例模式： 饿汉式
 * 
 * @author Lenovo
 *
 */
class SingleTonEager {
	
	private int num;
	
	private static SingleTonEager st = new SingleTonEager();
	
	private SingleTonEager() {
		System.out.println("SingleTonEager has Created");
		System.out.println("The num original is "+num);
	}
	
	public void setNum(int num){
		this.num=num;
	}
	
	public int getNum(){
		return this.num;
	}

	public static SingleTonEager getInstance() {
		return st;
	}

}

/**
 * 单例模式： 懒汉式，加入synchronized关键字
 * 
 * @author Lenovo
 *
 */
class SingleTonLazy {
	
	private int num;
	
	private static SingleTonLazy st = null;
	
	private SingleTonLazy() {
		System.out.println("SingleTonLazy has Created");
		num=0;
		System.out.println("The num original is "+num);
	}
	
	public void setNum(int num){
		this.num=num;
	}
	
	public int getNum(){
		return this.num;
	}

	public static SingleTonLazy getInstance() {
		if (st == null)
			synchronized(SingleTonLazy.class){
				if(st==null){
					st = new SingleTonLazy();
				}
			}
		return st;
	}
}

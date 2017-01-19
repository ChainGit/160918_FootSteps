package com.bxd.day08;

abstract class Animal{
	public abstract void getName(); 
	public static void Move(){
		System.out.println("Animal move");
	}
}

class Cat extends Animal{
	@Override
	public void getName() {
		// TODO Auto-generated method stub
		System.out.println("This is Cat");
	}
	
	public void MiaoMiao(){
		System.out.println("Cat MiaoMiao");
	}
	
	public static void Move(){
		System.out.println("Cat move");
	}
}

class Dog extends Animal{
	@Override
	public void getName() {
		// TODO Auto-generated method stub
		System.out.println("This is Dog");
	}
	
	public void WangWang(){
		System.out.println("Dog WangWang");
	}
	
	public static void Move(){
		System.out.println("Dog move");
	}
}
package com.bxd.day08;

public class PolymorphicTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnimalTest();
		System.out.println();
		
		new Computer();
		
	}
	
	public static void AnimalTest(){
		Cat a=new Cat();
		Dog d=new Dog();
		a.getName();
		d.getName();
		
		Animal ans[]=new Animal[2];
		ans[0]=new Cat();
		ans[1]=new Dog();
		Animal.Move();
//		ans[0].Move();
//		ans[1].Move();	
		//ans[0].MiaoMiao();
		//ans[1].WangWang();
		((Cat) ans[0]).MiaoMiao();
		((Dog) ans[1]).WangWang();
//		((Cat) ans[0]).Move();
//		((Dog) ans[1]).Move();
	}
}




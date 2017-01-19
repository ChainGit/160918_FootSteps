package com.bxd.day19;

//装饰和继承的区别和联系
public class DecorationTest {

	public static void main(String[] args) {
		DecorationMeat dm = new DecorationMeat(new Meat());
		dm.decorationPrint();
	}
}

abstract class Food {
	protected String s = null;

	public abstract void print();
}

class Meat extends Food {

	public Meat() {
		s = "food";
	}

	public Meat(String s) {
		this.s = s;
	}

	@Override
	public void print() {
		System.out.println(s);
	}
}

class DecorationMeat {
	private Meat m;

	public DecorationMeat(Meat m) {
		this.m = m;
	}

	public void decorationPrint() {
		System.out.print("nice ");
		m.print();
	}
}
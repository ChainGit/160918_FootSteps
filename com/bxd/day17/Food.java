package com.bxd.day17;

public abstract class Food{

	protected String foodName;
	protected Size size;

	public abstract String getFoodDescription();
}

class Meat extends Food {

	public Meat(Size s) {
		this.size = s;
	}

	public String getFoodDescription() {
		return size.getSize() + this.foodName;
	}
}

class FishMeat extends Meat {

	public FishMeat(Size s) {
		super(s);
		this.foodName = "鱼";
	}

}

class DuckMeat extends Meat {

	public DuckMeat(Size s) {
		super(s);
		this.foodName = "鸭";
	}

}
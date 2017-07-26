package com.bxd.day17;

public class DecorationTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Make m1 = new Make(new SteamWay(), new FishMeat(new BigSize()));
		Make m2 = new Make(new ToastWay(), new FishMeat(new BigSize()));
		Make m3 = new Make(new SteamWay(), new DuckMeat(new BigSize()));
		Make m4 = new Make(new SteamWay(), new DuckMeat(new SmallSize()));
		System.out.println(m1);
		System.out.println(m2);
		System.out.println(m3);
		System.out.println(m4);
	}

}

class Make {
	protected Way way;
	protected Food food;

	public Make(Way w, Food f) {
		way = w;
		food = f;
	}

	@Override
	public String toString() {
		return way.getWay() + food.getFoodDescription();
	}
}

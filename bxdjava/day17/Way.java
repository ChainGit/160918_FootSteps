package com.bxd.day17;

public abstract class Way {
	public abstract String getWay();
}

class SteamWay extends Way {

	@Override
	public String getWay() {
		// TODO Auto-generated method stub
		return "蒸";
	}

}

class ToastWay extends Way {

	@Override
	public String getWay() {
		// TODO Auto-generated method stub
		return "烤";
	}

}

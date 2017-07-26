package com.bxd.day17;

public abstract class Size {
	protected String size;

	public String getSize() {
		return size;
	}
}

class SmallSize extends Size {
	// protected String size = "小"; //无效

	public SmallSize() {
		size = "小";
	}
}

class BigSize extends Size {
	// protected String size = "大";

	public BigSize() {
		size = "大";
	}
}

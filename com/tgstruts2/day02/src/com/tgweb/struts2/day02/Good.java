package com.tgweb.struts2.day02;

public class Good {

	private long goodId;
	private String goodName;
	private double goodPrice;
	private String goodDesc;

	public long getGoodId() {
		return goodId;
	}

	public void setGoodId(long goodId) {
		this.goodId = goodId;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public double getGoodPrice() {
		return goodPrice;
	}

	public void setGoodPrice(double goodPrice) {
		this.goodPrice = goodPrice;
	}

	public String getGoodDesc() {
		return goodDesc;
	}

	public void setGoodDesc(String goodDesc) {
		this.goodDesc = goodDesc;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Good [goodId=").append(goodId).append(", goodName=").append(goodName).append(", goodPrice=")
				.append(goodPrice).append(", goodDesc=").append(goodDesc).append("]");
		return builder.toString();
	}

	public String save() {
		System.out.println(this);
		return "details";
	}

}

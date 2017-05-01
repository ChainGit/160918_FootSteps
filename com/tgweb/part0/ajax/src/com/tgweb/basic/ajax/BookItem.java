package com.tgweb.basic.ajax;

public class BookItem {

	private int count = 1;
	private float price;

	public BookItem(float price) {
		this.price = price;
	}

	public int getCount() {
		return this.count;
	}

	public void subCount() {
		this.count--;
	}

	public void addCount() {
		this.count++;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}

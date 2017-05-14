package com.chapter4;

import java.io.UnsupportedEncodingException;

public class Car {

	private String name, number, madeTime;

	public String getName() {
		try {
			return new String(name.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		try {
			return new String(number.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMadeTime() {
		try {
			return new String(madeTime.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return madeTime;
	}

	public void setMadeTime(String madeTime) {
		this.madeTime = madeTime;
	}

}

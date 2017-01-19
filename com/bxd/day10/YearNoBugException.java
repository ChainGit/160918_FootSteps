package com.bxd.day10;

public class YearNoBugException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2971597877295119974L;

	public YearNoBugException(String s) {
		super(s);
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		throw new YearNoBugException("Caused by 2017,Happy New Year!");
	}

}

package com.tgweb.spring4.day03;

public class Money {

	private Double money;

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Money(Double money) {
		super();
		this.money = money;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Money [Money=").append(money).append("]");
		return builder.toString();
	}

	public void sub(Double mon) {
		this.money -= mon;
	}

	public void add(Double mon) {
		this.money += mon;
	}

	public void sub(Money mon) {
		this.money -= mon.getMoney();
	}

	public void add(Money mon) {
		this.money += mon.getMoney();
	}

	public static Money sub(Money mon1, Money mon2) {
		return new Money(mon1.getMoney() - mon2.getMoney());
	}

	public static Money add(Money mon1, Money mon2) {
		return new Money(mon1.getMoney() - mon2.getMoney());
	}

	public static Double sub(Double mon1, Double mon2) {
		return mon1 - mon2;
	}

	public static Double add(Double mon1, Double mon2) {
		return mon1 + mon2;
	}

}

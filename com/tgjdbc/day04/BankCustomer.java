package com.tgjdbc.day04;

/**
 * ���еĿͻ�
 * 
 * @author Chain
 *
 */
public class BankCustomer {
	private String name;
	private int money;

	@Override
	public String toString() {
		return name + "[" + money + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}

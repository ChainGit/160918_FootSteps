package com.tgweb.ssh.entity;

/*
 * Component方式与User组合在一起，在数据表中是一张表，user
 * 
 */
public class Account {

	private Double balance;

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Account(Double balance) {
		super();
		this.balance = balance;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account [balance=").append(balance).append("]");
		return builder.toString();
	}

}

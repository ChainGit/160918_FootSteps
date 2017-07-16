package com.tgweb.ssh.entity;

public class User {

	private Long id;
	private String name;
	private Account account;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=").append(id).append(", name=").append(name).append(", account=").append(account)
				.append("]");
		return builder.toString();
	}

	public User(String name, Account account) {
		super();
		this.name = name;
		this.account = account;
	}

	public User() {
		super();
	}

	public User(String name) {
		this.name = name;
	}

}

package com.tgweb.hibernate.day02;

public class Person {

	private Long id;
	private java.util.Date birth0 = new java.util.Date();
	private java.util.Date birth1 = birth0;
	private java.util.Date birth2 = birth0;
	private java.util.Date birth3 = birth0;
	private java.sql.Date birth4 = new java.sql.Date(birth0.getTime());
	private java.sql.Time birth5 = new java.sql.Time(birth0.getTime());
	private java.sql.Timestamp birth6 = new java.sql.Timestamp(birth0.getTime());

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [id=").append(id).append(", birth0=").append(birth0).append(", birth1=").append(birth1)
				.append(", birth2=").append(birth2).append(", birth3=").append(birth3).append(", birth4=")
				.append(birth4).append(", birth5=").append(birth5).append(", birth6=").append(birth6).append("]");
		return builder.toString();
	}

}

package com.tgweb.spring4.day03;

public class LongId {

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LongId(Long id) {
		super();
		this.id = id;
	}

	public LongId(Integer id) {
		super();
		this.id = (long) id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LongId [id=").append(id).append("]");
		return builder.toString();
	}

}

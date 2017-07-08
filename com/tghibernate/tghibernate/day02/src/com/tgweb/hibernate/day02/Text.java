package com.tgweb.hibernate.day02;

import java.sql.Blob;
import java.sql.Clob;

public class Text {

	private Long id;

	private Blob image1;

	private byte[] image2;

	public Blob getImage1() {
		return image1;
	}

	public void setImage1(Blob image1) {
		this.image1 = image1;
	}

	private Clob article1;

	private String article2;

}

package com.tgweb.struts2.day14;

import org.junit.Test;

public class IDCardTest {

	@Test
	public void test() {
		IDCard card = new IDCard();
		String id1 = "123";
		System.out.println(card.verify(id1));
		String id2 = "12345678";
		System.out.println(card.verify(id2));
		String id3 = "12345a78";
		System.out.println(card.verify(id3));
	}

}

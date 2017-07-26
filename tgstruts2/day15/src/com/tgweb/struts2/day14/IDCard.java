package com.tgweb.struts2.day14;

public class IDCard {

	public boolean verify(String idCard) {
		char[] arr = idCard.toCharArray();
		if (arr.length != 8)
			return false;

		for (char i : arr)
			if (i < '0' || i > '9')
				return false;

		return true;
	}
}

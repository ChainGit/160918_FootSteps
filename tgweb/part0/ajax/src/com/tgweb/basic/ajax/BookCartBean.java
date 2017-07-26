package com.tgweb.basic.ajax;

import java.util.HashMap;
import java.util.Map;

public class BookCartBean {

	private Map<String, BookItem> books = new HashMap<String, BookItem>();

	public BookCartBean() {

	}

	public BookItem getBookItem(String key) {
		return books.get(key);
	}

	public void removeBookItem(String key) {
		if (getBookItem(key) != null && getBookItem(key).getCount() > 1)
			getBookItem(key).subCount();
		else
			books.remove(key);
	}

	public void setBookItem(String key, BookItem item) {
		if (getBookItem(key) == null)
			books.put(key, item);
		else {
			getBookItem(key).addCount();
		}
	}

	public Map<String, BookItem> getBooks() {
		return books;
	}

}

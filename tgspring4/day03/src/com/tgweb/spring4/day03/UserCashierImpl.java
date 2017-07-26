package com.tgweb.spring4.day03;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserCashierImpl implements UserCashier {

	private BookShopService bookShopService;

	public void setBookShopService(BookShopService bookShopService) {
		this.bookShopService = bookShopService;
	}

	@Override
	public void cash(Long userId, List<Long> bookIds) {
		for (Long bookId : bookIds) {
			bookShopService.purchase(userId, bookId, 1);
		}
	}

	@Override
	public void cash(Long userId, Map<Long, Integer> books) {
		Set<Long> booksId = books.keySet();
		for (Long bookId : booksId) {
			bookShopService.purchase(userId, bookId, books.get(bookId));
		}
	}

}

package com.tgweb.ssh.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tgweb.ssh.service.BookShopService;
import com.tgweb.ssh.service.UserCashierService;

@Service("userCashierService")
public class UserCashierServiceImpl implements UserCashierService {

	@Autowired
	private BookShopService bookShopService;

	@Transactional
	@Override
	public void cash(Long userId, List<Long> bookIds) {
		for (Long bookId : bookIds) {
			bookShopService.purchase(userId, bookId, 1);
		}
	}

	@Transactional
	@Override
	public void cash(Long userId, Map<Long, Integer> books) {
		Set<Long> booksId = books.keySet();
		for (Long bookId : booksId) {
			bookShopService.purchase(userId, bookId, books.get(bookId));
		}
	}

}

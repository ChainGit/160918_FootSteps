package com.tgweb.spring4.day02;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userCashier")
public class UserCashierImpl implements UserCashier {

	@Autowired
	private BookShopService bookShopService;

	/*
	 * 
	 * 用户结账操作，调用BookShopService的purchase方法，一次购买多本书 (non-Javadoc)
	 * 
	 * @see com.tgweb.spring4.day02.UserCashier#cash()
	 */
	@Transactional
	@Override
	public void cash(Long userId, List<Long> bookIds) {
		for (Long bookId : bookIds) {
			bookShopService.purchase(userId, bookId);
		}
	}

}

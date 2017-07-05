package com.tgweb.spring4.day02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {

	@Autowired
	private BookShopDao bookShopDao;

	/*
	 * 用户购买书，一次购买一本
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.tgweb.spring4.day02.BookShopService#purchase(java.lang.Long,
	 * java.lang.Long)
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, isolation = Isolation.READ_COMMITTED, timeout = 5 /*, noRollbackFor = com.tgweb.spring4.day02.UserAccountException.class*/)
	@Override
	public void purchase(Long userId, Long bookId) {
		// 获取书的单价
		double price = bookShopDao.getBookPriceById(bookId);
		// 更新仓库
		bookShopDao.updateBookStockById(bookId, 1);
		// 更新用户
		bookShopDao.updateCustomerBalanceById(userId, price);
	}

}

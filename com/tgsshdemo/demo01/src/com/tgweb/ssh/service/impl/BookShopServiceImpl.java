package com.tgweb.ssh.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tgweb.ssh.dao.BookShopDao;
import com.tgweb.ssh.service.BookShopService;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {

	@Autowired
	private BookShopDao bookShopDao;

	/*
	 * 
	 * 用户购买书，一次购买一种书
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.tgweb.ssh.service.BookShopService#purchase(java.lang.Long,
	 * java.lang.Long)
	 */
	@Transactional
	@Override
	public void purchase(Long userId, Long bookId, Integer bookAmount) {
		double price = bookShopDao.getBookPriceById(bookId);
		double deltaBalance = new BigDecimal(price).setScale(2, RoundingMode.HALF_EVEN)
				.multiply(new BigDecimal(bookAmount).setScale(2, RoundingMode.HALF_EVEN))
				.setScale(2, RoundingMode.HALF_EVEN).doubleValue();
		bookShopDao.updateBookStockById(bookId, bookAmount);
		bookShopDao.updateUserBalanceById(userId, deltaBalance);
	}

}

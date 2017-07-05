package com.tgweb.spring4.day03;

public class BookShopServiceImpl implements BookShopService {

	private BookShopDao bookShopDao;

	public void setBookShopDao(BookShopDao bookShopDao) {
		this.bookShopDao = bookShopDao;
	}

	@Override
	public void purchase(Long userId, Long bookId, Integer booksAmount) {
		// 获取书的单价
		double price = bookShopDao.getBookPriceById(bookId);
		// 更新仓库
		bookShopDao.updateBookStockById(bookId, booksAmount);
		// 更新用户
		bookShopDao.updateCustomerBalanceById(userId, price);
	}

}

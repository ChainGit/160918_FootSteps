package com.tgweb.spring4.day03;

public interface BookShopDao {

	public double getBookPriceById(Long id);

	public void updateBookStockById(Long id, Integer stock);

	public void updateCustomerBalanceById(Long id, Double deltaBalance);
}

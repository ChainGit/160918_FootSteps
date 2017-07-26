package com.tgweb.ssh.dao;

import com.tgweb.ssh.entity.Book;
import com.tgweb.ssh.entity.User;

public interface BookShopDao {

	public Book getBookById(Long id);

	public User getUserById(Long id);

	public void updateUser(User user);

	public void updateBook(Book book);

	public double getBookPriceById(Long id);

	public void updateBookStockById(Long id, Integer deltaStock);

	public void updateUserBalanceById(Long id, Double deltaBalance);

}

package com.tgweb.ssh.dao.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.tgweb.ssh.dao.BookShopDao;
import com.tgweb.ssh.entity.Account;
import com.tgweb.ssh.entity.Book;
import com.tgweb.ssh.entity.User;
import com.tgweb.ssh.execption.BookShopException;
import com.tgweb.ssh.execption.UserAccountException;
import com.tgweb.ssh.utils.HibernateUtils;

@Repository("bookShopDao")
public class BookShopDaoImpl implements BookShopDao {

	// 不推荐使用HibernateTemplate和HibernateDaoSupport，在Hibernate4内已经被废除，会导致Hibernate和Spring耦合性增强
	// 在Spring里使用JdbcTemplate
	private HibernateUtils hibernateUtils;

	{
		hibernateUtils = HibernateUtils.getInstance();
	}

	@Override
	public double getBookPriceById(Long id) {
		return getBookById(id).getPrice();
	}

	@Override
	public void updateBookStockById(Long id, Integer deltaStock) {
		Book book = getBookById(id);
		int stock = book.getStock();
		if (stock < deltaStock)
			throw new BookShopException("书的库存不足");
		book.setStock(stock - deltaStock);
		updateBook(book);
	}

	@Override
	public void updateUserBalanceById(Long id, Double deltaBalance) {
		User user = getUserById(id);
		double balance = user.getAccount().getBalance();
		if (balance < deltaBalance)
			throw new UserAccountException("账户余额不足");
		user.getAccount().setBalance(balance - deltaBalance);
		updateUser(user);
	}

	@Override
	public Book getBookById(Long id) {
		String hql = "SELECT b.id, b.isbn, b.name, b.price, b.stock FROM Book b WHERE b.id = :id";
		Object[] objs = (Object[]) hibernateUtils.getSession().createQuery(hql).setLong("id", id).uniqueResult();
		Book book = new Book();
		book.setId((Long) objs[0]);
		book.setIsbn((String) objs[1]);
		book.setName((String) objs[2]);
		book.setPrice(((BigDecimal) objs[3]).doubleValue());
		book.setStock((Integer) objs[4]);
		return book;
	}

	@Override
	public User getUserById(Long id) {
		String hql = "SELECT u.id, u.name, u.account.balance FROM User u WHERE u.id = :id";
		Object[] objs = (Object[]) hibernateUtils.getSession().createQuery(hql).setLong("id", id).uniqueResult();
		User user = new User((String) objs[1], new Account(((BigDecimal) objs[2]).doubleValue()));
		user.setId((Long) objs[0]);
		return user;
	}

	@Override
	public void updateUser(User user) {
		String hql = "UPDATE User u SET u.name = :name,u.account.balance = :balance WHERE u.id = :id";
		hibernateUtils.getSession().createQuery(hql).setString("name", user.getName())
				.setDouble("balance", user.getAccount().getBalance()).setLong("id", user.getId()).executeUpdate();
	}

	@Override
	public void updateBook(Book book) {
		String hql = "UPDATE Book b SET b.isbn = :isbn,b.name = :name,b.price = :price,b.stock = :stock WHERE b.id = :id";
		hibernateUtils.getSession().createQuery(hql).setString("isbn", book.getIsbn()).setString("name", book.getName())
				.setDouble("price", book.getPrice()).setInteger("stock", book.getStock()).setLong("id", book.getId())
				.executeUpdate();
	}

}

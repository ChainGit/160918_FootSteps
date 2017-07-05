package com.tgweb.spring4.day03;

import org.springframework.jdbc.core.JdbcTemplate;

public class BookShopDaoImpl implements BookShopDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public double getBookPriceById(Long id) {
		String sql = "SELECT book_price FROM book_info WHERE id = ?";
		Double price = jdbcTemplate.queryForObject(sql, Double.class, id);
		return price;
	}

	@Override
	public void updateBookStockById(Long id, Integer deltaStock) {
		String sql0 = "SELECT book_store_id from book_info where id = ?";
		long storeId = jdbcTemplate.queryForObject(sql0, Long.class, id);

		String sql1 = "SELECT book_stock FROM book_store WHERE id = ?";
		int stock = jdbcTemplate.queryForObject(sql1, Integer.class, storeId);
		if (stock < deltaStock)
			throw new BookShopException("书的库存不足");

		String sql2 = "UPDATE book_store SET book_stock = book_stock - ? WHERE id = ?";
		jdbcTemplate.update(sql2, deltaStock, storeId);
	}

	@Override
	public void updateCustomerBalanceById(Long id, Double deltaBalance) {
		String sql0 = "SELECT balance FROM customer_info WHERE id = ?";
		double balance = jdbcTemplate.queryForObject(sql0, Double.class, id);
		if (balance < deltaBalance)
			throw new UserAccountException("账户余额不足");

		String sql = "UPDATE customer_info SET balance = balance - ? WHERE id = ?";
		jdbcTemplate.update(sql, deltaBalance, id);
	}

}

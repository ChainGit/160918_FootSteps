package com.tgweb.basic.ajax.day07;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class BaseDao {

	private Connection conn;
	private QueryRunner runner = new QueryRunner();
	private DBManager man = new DBManager();

	public BaseDao() {
		try {
			conn = man.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public <T> List<T> getForList(String sql, Class<T> clz, Object... args) throws SQLException {
		return runner.query(conn, sql, new BeanListHandler<T>(clz), args);
	}

	public <E> E getForSingle(String sql, Class<E> clz, Object... args) throws SQLException {
		return runner.query(conn, sql, new BeanHandler<E>(clz), args);
	}

}

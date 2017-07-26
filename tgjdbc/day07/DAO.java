package com.tgjdbc.day07;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.dbutils.ResultSetHandler;

/**
 * DAO接口
 * 
 * @author Chain
 *
 */
public interface DAO {

	/**
	 * 返回数据库连接池中的一个可用连接
	 * 
	 * @return 数据库连接,若连接失败返回null
	 */
	public Connection getConnection();

	/**
	 * 执行executUpdate语句
	 * 
	 * @param conn
	 *            数据库连接
	 * @param sql
	 *            要执行的sql语句
	 * @param args
	 *            要填入sql语句的参数
	 */
	public void update(Connection conn, String sql, Object... args);

	/**
	 * 执行executeQuery
	 * 
	 * @param conn
	 *            数据库连接
	 * @param rsh
	 *            传入ResultSetHandler接口实现类或者继承类
	 * @param sql
	 *            要执行的sql语句
	 * @param args
	 *            要填入sql语句的参数
	 */
	public <T> T query(Connection conn, ResultSetHandler<T> rsh, String sql, Object... args);

	/**
	 * 检查SQL语句是否合法
	 * 
	 * @param sql
	 *            要检查的sql语句
	 */
	void check(String sql) throws Exception;

	/**
	 * 由内往外依次关闭资源
	 * 
	 * @param res
	 *            要关闭的ResultSet
	 * @param stat
	 *            要关闭的Statement
	 * @param conn
	 *            要关闭的Connection
	 */
	public void close(ResultSet res, Statement stat, Connection conn);
}

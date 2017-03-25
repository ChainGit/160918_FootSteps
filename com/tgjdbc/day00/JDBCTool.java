package com.tgjdbc.day00;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBC工具类(只是一个粗糙的工具)<br>
 * 是DatabaseTool的完善版本 <br>
 * 使用:<br>
 * 1.BeanUtils <br>
 * 2.DbUtils <br>
 * 3.c3p0 <br>
 * 4...<br>
 * 
 * @author Chain
 * @version 20170323
 *
 */
public class JDBCTool {

	// 数据库连接池
	private static DataSource ds = null;
	// 操作句柄
	private static QueryRunner qr = null;

	// 连接池只创建一次
	static {
		ds = new ComboPooledDataSource("tg-jdbc-c3p0-config");
		qr = new QueryRunner();
	}

	/**
	 * 返回数据库连接池中的一个可用连接
	 * 
	 * @return 数据库连接,若连接失败返回null
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

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
	public static void update(Connection conn, String sql, Object... args) {
		try {
			check(sql);
			qr.update(conn, sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("executeUpdate sql: '" + sql + "' sucess.");
	}

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
	public static <T> T query(Connection conn, ResultSetHandler<T> rsh, String sql, Object... args) {
		T t = null;
		try {
			check(sql);
			t = qr.query(conn, sql, rsh, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("executeQuery sql: '" + sql + "' sucess.");
		return t;
	}

	/**
	 * 检查SQL语句是否合法
	 * 
	 * @param sql
	 *            要检查的sql语句
	 */
	private static void check(String sql) throws Exception {
		if (sql == null || sql.length() < 1)
			throw new Exception("illegal sql string");
	}

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
	public static void close(ResultSet res, Statement stat, Connection conn) {
		try {
			if (res != null)
				res.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stat != null)
					try {
						stat.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
			} finally {
				if (conn != null)
					try {
						conn.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}

		System.out.println("close resource " + (res == null ? "" : "res,") + (stat == null ? "" : "stat,")
				+ (conn == null ? "" : "conn") + " done.");
	}

}

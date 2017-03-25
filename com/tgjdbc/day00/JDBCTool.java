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
 * JDBC������(ֻ��һ���ֲڵĹ���)<br>
 * ��DatabaseTool�����ư汾 <br>
 * ʹ��:<br>
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

	// ���ݿ����ӳ�
	private static DataSource ds = null;
	// �������
	private static QueryRunner qr = null;

	// ���ӳ�ֻ����һ��
	static {
		ds = new ComboPooledDataSource("tg-jdbc-c3p0-config");
		qr = new QueryRunner();
	}

	/**
	 * �������ݿ����ӳ��е�һ����������
	 * 
	 * @return ���ݿ�����,������ʧ�ܷ���null
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
	 * ִ��executUpdate���
	 * 
	 * @param conn
	 *            ���ݿ�����
	 * @param sql
	 *            Ҫִ�е�sql���
	 * @param args
	 *            Ҫ����sql���Ĳ���
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
	 * ִ��executeQuery
	 * 
	 * @param conn
	 *            ���ݿ�����
	 * @param rsh
	 *            ����ResultSetHandler�ӿ�ʵ������߼̳���
	 * @param sql
	 *            Ҫִ�е�sql���
	 * @param args
	 *            Ҫ����sql���Ĳ���
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
	 * ���SQL����Ƿ�Ϸ�
	 * 
	 * @param sql
	 *            Ҫ����sql���
	 */
	private static void check(String sql) throws Exception {
		if (sql == null || sql.length() < 1)
			throw new Exception("illegal sql string");
	}

	/**
	 * �����������ιر���Դ
	 * 
	 * @param res
	 *            Ҫ�رյ�ResultSet
	 * @param stat
	 *            Ҫ�رյ�Statement
	 * @param conn
	 *            Ҫ�رյ�Connection
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

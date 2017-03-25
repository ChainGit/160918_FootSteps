package com.tgjdbc.day00;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.tgjdbc.day04.BankCustomer;

public class JDBCToolTest {

	// ScalarHandler(ResultSetHandler接口实现类,T是返回的值)
	@Test
	public void test5() {
		Connection conn = null;

		try {
			conn = JDBCTool.getConnection();

			String sql = "select name,money from small_bank";
			// String sql = "select id from batch_speed";

			String b = JDBCTool.query(conn, new ScalarHandler<String>(), sql);

			// 返回第一条记录
			System.out.println(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTool.close(null, null, conn);
		}
	}

	// MapListHandler(ResultSetHandler接口实现类,T为List<Map<String, Object>>)
	@Test
	public void test4() {
		Connection conn = null;

		try {
			conn = JDBCTool.getConnection();

			String sql = "select name,money from small_bank";

			List<Map<String, Object>> lstMap = JDBCTool.query(conn, new MapListHandler(), sql);

			// 返回第一条记录
			System.out.println(lstMap);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTool.close(null, null, conn);
		}
	}

	// MapHandler(ResultSetHandler接口实现类,T为Map<String, Object>)
	@Test
	public void test3() {
		Connection conn = null;

		try {
			conn = JDBCTool.getConnection();

			String sql = "select name,money from small_bank";

			Map<String, Object> map = JDBCTool.query(conn, new MapHandler(), sql);

			// 返回第一条记录
			System.out.println(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTool.close(null, null, conn);
		}
	}

	// BeanListHandler(ResultSetHandler接口实现类,T为List<T>)
	@Test
	public void test2() {
		Connection conn = null;

		try {
			conn = JDBCTool.getConnection();

			String sql = "select name,money from small_bank";

			List<BankCustomer> lst = JDBCTool.query(conn, new BeanListHandler<BankCustomer>(BankCustomer.class), sql);

			for (BankCustomer b : lst)
				System.out.println(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTool.close(null, null, conn);
		}
	}

	// ResultSetHandler
	@Test
	public void test1() {
		ResultSetHandler<ArrayList<BankCustomer>> rsh = new ResultSetHandler<ArrayList<BankCustomer>>() {

			@Override
			public ArrayList<BankCustomer> handle(ResultSet res) {
				ArrayList<BankCustomer> lst = new ArrayList<>();
				try {
					while (res.next()) {
						BankCustomer b = new BankCustomer();
						b.setName(res.getString(1));
						b.setMoney(res.getInt(2));
						lst.add(b);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return lst;
			}
		};

		Connection conn = null;

		try {
			conn = JDBCTool.getConnection();

			queryTest(rsh, conn);

			updateTest(conn);

			queryTest(rsh, conn);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTool.close(null, null, conn);
		}
	}

	private void updateTest(Connection conn) {
		String sql = "update small_bank set money = money + ? where id = ?";
		JDBCTool.update(conn, sql, -100, 1);
	}

	private void queryTest(ResultSetHandler<ArrayList<BankCustomer>> rsh, Connection conn) {
		ArrayList<BankCustomer> lst;
		String sql = "select name,money from small_bank";
		lst = JDBCTool.query(conn, rsh, sql);
		for (BankCustomer b : lst)
			System.out.println(b);
	}
}

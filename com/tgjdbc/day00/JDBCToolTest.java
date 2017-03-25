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

	// ScalarHandler(ResultSetHandler�ӿ�ʵ����,T�Ƿ��ص�ֵ)
	@Test
	public void test5() {
		Connection conn = null;

		try {
			conn = JDBCTool.getConnection();

			String sql = "select name,money from small_bank";
			// String sql = "select id from batch_speed";

			String b = JDBCTool.query(conn, new ScalarHandler<String>(), sql);

			// ���ص�һ����¼
			System.out.println(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTool.close(null, null, conn);
		}
	}

	// MapListHandler(ResultSetHandler�ӿ�ʵ����,TΪList<Map<String, Object>>)
	@Test
	public void test4() {
		Connection conn = null;

		try {
			conn = JDBCTool.getConnection();

			String sql = "select name,money from small_bank";

			List<Map<String, Object>> lstMap = JDBCTool.query(conn, new MapListHandler(), sql);

			// ���ص�һ����¼
			System.out.println(lstMap);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTool.close(null, null, conn);
		}
	}

	// MapHandler(ResultSetHandler�ӿ�ʵ����,TΪMap<String, Object>)
	@Test
	public void test3() {
		Connection conn = null;

		try {
			conn = JDBCTool.getConnection();

			String sql = "select name,money from small_bank";

			Map<String, Object> map = JDBCTool.query(conn, new MapHandler(), sql);

			// ���ص�һ����¼
			System.out.println(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTool.close(null, null, conn);
		}
	}

	// BeanListHandler(ResultSetHandler�ӿ�ʵ����,TΪList<T>)
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

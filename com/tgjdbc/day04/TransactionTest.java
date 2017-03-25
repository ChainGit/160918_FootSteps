package com.tgjdbc.day04;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tgjdbc.day00.DatabaseTool;

public class TransactionTest {

	public static void main(String[] args) {
		test();
	}

	// ���������ACID��ԭ���ԣ�һ���ԣ������ԣ��־���
	private static void test() {
		Connection conn = null;

		try {
			conn = DatabaseTool.getConnection();

			conn.setAutoCommit(false);

			String sql = "update small_bank set money = money + ? where name = ?";
			DatabaseTool.update(conn, sql, -50, "С��");
			DatabaseTool.update(conn, sql, 50, "Сǿ");

			// ����һ�����ύ�Ͳ����ٳ���
			conn.commit();

			ArrayList<BankCustomer> lst = DatabaseTool.getObjectList(BankCustomer.class, conn,
					"select name,money from small_bank");

			for (BankCustomer b : lst)
				System.out.println(b);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseTool.close(null, null, conn);
			}
		}
	}
}

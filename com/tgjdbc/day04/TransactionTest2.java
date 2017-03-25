package com.tgjdbc.day04;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tgjdbc.day00.DatabaseTool;

/**
 * 事务的隔离级别
 * 
 * @author Chain
 *
 */
public class TransactionTest2 {

	public static void main(String[] args) {
		new TransactionTest2().test();
	}

	// 脏读、不可重复读、幻读
	// TRANSACTION_READ_UNCOMMITTED/TRANSACTION_READ_COMMITTED
	// TRANSACTION_REPEATABLE_READ/TRANSACTION_SERIALIZABLE
	// TRANSACTION_NONE
	private void test() {
		// 修改前查询
		new Thread(new MyTransThread(false)).start();

		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 执行修改
		Thread boss = new Thread(new MyTransThread(true));
		boss.setDaemon(true);
		boss.start();

		// 第一次查询
		new Thread(new MyTransThread(false)).start();

		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 第二次查询
		new Thread(new MyTransThread(false)).start();

		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 第三次查询
		new Thread(new MyTransThread(false)).start();

		try {
			boss.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class MyTransThread implements Runnable {

	private boolean type;

	public MyTransThread(boolean type) {
		this.type = type;
	}

	@Override
	public void run() {
		Connection conn = null;
		try {
			conn = DatabaseTool.getConnection();
			if (type) {
				conn.setAutoCommit(false);

				DatabaseTool.update(conn, "update small_bank set money = money + 100 where id = 1");
				Thread.sleep(5000);
				conn.rollback();
				DatabaseTool.update(conn, "update small_bank set money = money - 100 where id = 2");
				Thread.sleep(5000);

				conn.commit();
				Thread.sleep(5000);
			} else {
				Thread.sleep(1000);

				// 设置隔离级别(默认是TRANSACTION_REPEATABLE_READ)
				conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
				// conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
				// conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
				// conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

				ArrayList<BankCustomer> lst = DatabaseTool.getObjectList(BankCustomer.class, conn,
						"select name,money from small_bank");
				for (BankCustomer b : lst)
					System.out.println(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (type)
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		} finally {
			DatabaseTool.close(null, null, conn);
		}

	}
}

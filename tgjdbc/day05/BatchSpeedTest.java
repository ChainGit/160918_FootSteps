package com.tgjdbc.day05;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.tgjdbc.day00.DatabaseTool;

/**
 * Batch测试 <br>
 * 运行环境：虚拟机且MySQL，测试时间仅仅是参考
 * 
 * @author Chain
 *
 */
public class BatchSpeedTest {

	public static void main(String[] args) {
		// test1();
		// test2();
		test3();
	}

	private static void test3() {
		Connection conn = null;
		PreparedStatement stat = null;

		try {
			conn = DatabaseTool.getConnection();
			conn.setAutoCommit(false);
			String sql = "insert into batch_speed (name,age,birth) values (?,?,?)";
			stat = conn.prepareStatement(sql);

			long start = System.currentTimeMillis();
			int TOTAL = 100000;
			int BATCH = 200;
			for (int i = 0; i < TOTAL; i++) {
				int x = i % 100;
				stat.setString(1, "name_" + x);
				stat.setInt(2, x);
				stat.setDate(3, new Date(new java.util.Date().getTime()));

				// 先积攒SQL语句
				stat.addBatch();

				if ((i + 1) % BATCH == 0) {
					stat.executeBatch();
					stat.clearBatch();
				}
			}

			if (TOTAL % BATCH != 0) {
				stat.executeBatch();
				stat.clearBatch();
			}

			conn.commit();
			long end = System.currentTimeMillis();
			// time=27248,autocommit=false
			// time=173759,autocommit=true
			// time=30949,usebatch
			System.out.println("time=" + (end - start));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseTool.close(null, stat, conn);
		}
	}

	// PerpareStatement速度比Statement要快一点
	// PerpareStatement可以有效防止SQL注入
	private static void test1() {
		Connection conn = null;
		PreparedStatement stat = null;
		// Satement stat = null;

		try {
			conn = DatabaseTool.getConnection();
			// conn.setAutoCommit(false);
			String sql = "insert into batch_speed (name,age,birth) values (?,?,?)";
			stat = conn.prepareStatement(sql);

			long start = System.currentTimeMillis();
			for (int i = 0; i < 100000; i++) {
				int x = i % 100;
				stat.setString(1, "name_" + x);
				stat.setInt(2, x);
				stat.setDate(3, new Date(new java.util.Date().getTime()));

				stat.executeUpdate();
			}
			// conn.commit();
			long end = System.currentTimeMillis();
			// time=27248,autocommit=false
			// time=173759,autocommit=true
			System.out.println("time=" + (end - start));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseTool.close(null, stat, conn);
		}
	}

	// PerpareStatement速度比Statement要快一点
	// PerpareStatement可以有效防止SQL注入
	private static void test2() {
		Connection conn = null;
		Statement stat = null;

		try {
			conn = DatabaseTool.getConnection();
			// conn.setAutoCommit(false);
			stat = conn.createStatement();

			long start = System.currentTimeMillis();
			for (int i = 0; i < 100000; i++) {
				String sql = "insert into batch_speed (name,age,birth) values ('name_" + (i % 100) + "'," + i % 100
						+ ",'" + new Date(new java.util.Date().getTime()) + "')";
				stat.executeUpdate(sql);
			}
			// conn.commit();
			long end = System.currentTimeMillis();
			// time=29649,autocommit=false
			// time=154124,autocommit=true
			System.out.println("time=" + (end - start));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseTool.close(null, stat, conn);
		}
	}
}

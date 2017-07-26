package com.tgjdbc.day00;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 数据库操作的常用方法(只是一个粗糙的工具)
 * 
 * @author Chain
 *
 */
public class DatabaseTool {

	/**
	 * 读取mysql.properties文件并建立数据库的连接
	 * 
	 * @return 数据库连接,若连接失败返回null
	 */
	public static Connection getConnection() {
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("mysql.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		String driverClass = prop.getProperty("driver");
		String jdbcUrl = prop.getProperty("url");
		String user = prop.getProperty("user");
		String pass = prop.getProperty("password");

		// 加载驱动(加载类时会执行static代码块)
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("create a new connection.");

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
		PreparedStatement stat = null;

		try {
			checkSQL(sql);
			stat = conn.prepareStatement(sql);

			for (int i = 0; i < args.length; i++)
				stat.setObject(i + 1, args[i]);

			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, stat, null);
		}

		System.out.println("executeUpdate sql: '" + sql + "' sucess.");
	}

	/**
	 * 执行executeQuery
	 * 
	 * @param conn
	 *            数据库连接
	 * @param sql
	 *            要执行的sql语句
	 * @param args
	 *            要填入sql语句的参数
	 */
	public static ResultSet query(Connection conn, String sql, Object... args) {
		PreparedStatement stat = null;
		ResultSet res = null;

		try {
			checkSQL(sql);
			stat = conn.prepareStatement(sql);

			for (int i = 0; i < args.length; i++)
				stat.setObject(i + 1, args[i]);

			res = stat.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(res, stat, null);
		}

		System.out.println("executeQuery sql: '" + sql + "' sucess.");
		return res;
	}

	/**
	 * 传入sql语句和对应的sql参数,进过查询操作后获得传入的Class的对象数组(DAO,Data Access Object)
	 * 
	 * @param cl
	 *            要获得对象的Class类
	 * @param sql
	 *            查询的sql语句
	 * @param args
	 *            查询的sql语句的参数
	 * @return 根据查询的结果和Class文件生成的对象列表
	 */
	public static <T> ArrayList<T> getObjectList(Class<T> cl, Connection conn, String sql, String... args) {
		ArrayList<T> lstT = new ArrayList<>();

		ResultSet res = null;
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++)
				stat.setObject(i + 1, args[i]);

			res = stat.executeQuery();

			ResultSetMetaData rsmd = res.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if (columnCount < 1)
				return null;

			Map<String, Object> vals = new HashMap<>();
			ArrayList<String> lstcol = new ArrayList<>();
			for (int i = 0; i < columnCount; i++) {
				String columnLabel = rsmd.getColumnLabel(i + 1);
				lstcol.add(columnLabel);
				vals.put(columnLabel, null);
			}

			while (res.next()) {
				for (int i = 0; i < columnCount; i++) {
					Object columnValue = res.getObject(i + 1);
					vals.put(lstcol.get(i), columnValue);
				}

				Object obj = cl.newInstance();
				for (Map.Entry<String, Object> entry : vals.entrySet())
					// setFieldValue(obj, entry.getKey(), entry.getValue());
					BeanUtils.setProperty(obj, entry.getKey(), entry.getValue());

				lstT.add((T) obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseTool.close(res, stat, null);
		}

		return lstT;
	}

	// 反射修改对象的Field
	@SuppressWarnings("unused")
	private void setFieldValue(Object obj, String key, Object value) {
		Class cl = obj.getClass();

		try {
			Field fi = cl.getDeclaredField(key);
			fi.setAccessible(true);
			String type = fi.getType().toString();
			if (type.endsWith("String"))
				fi.set(obj, value);
			else if (type.endsWith("int") || type.endsWith("Integer"))
				fi.set(obj, (int) value);
		} catch (SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	// 仅仅用作测试
	public static void update(String sql) {
		Connection conn = null;
		Statement stat = null;

		try {
			checkSQL(sql);

			// 获得数据库的连接
			conn = DatabaseTool.getConnection();
			// 创建一个Statement对象
			stat = conn.createStatement();
			// 执行Update操作,可以对应insert/update/delete
			stat.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, stat, conn);
		}

		System.out.println("executeUpdate sql: '" + sql + "' sucess.");
	}

	// 仅仅用作测试
	public static void query(String sql) {
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;

		try {
			checkSQL(sql);

			conn = DatabaseTool.getConnection();
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			while (res.next()) {
				int id = res.getInt(1);
				String name = res.getString(2);
				int age = res.getInt(3);

				System.out.printf("%d,%s,%d", id, name, age);
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(res, stat, conn);
		}

		System.out.println("executeQuery sql: '" + sql + "' sucess.");
	}

	/**
	 * 检查SQL语句是否合法
	 * 
	 * @param sql
	 *            要检查的sql语句
	 */
	private static void checkSQL(String sql) throws Exception {
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

		System.out.println("close resource done.");
	}
}

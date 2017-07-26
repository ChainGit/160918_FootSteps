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
 * ���ݿ�����ĳ��÷���(ֻ��һ���ֲڵĹ���)
 * 
 * @author Chain
 *
 */
public class DatabaseTool {

	/**
	 * ��ȡmysql.properties�ļ����������ݿ������
	 * 
	 * @return ���ݿ�����,������ʧ�ܷ���null
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

		// ��������(������ʱ��ִ��static�����)
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
	 * ִ��executeQuery
	 * 
	 * @param conn
	 *            ���ݿ�����
	 * @param sql
	 *            Ҫִ�е�sql���
	 * @param args
	 *            Ҫ����sql���Ĳ���
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
	 * ����sql���Ͷ�Ӧ��sql����,������ѯ�������ô����Class�Ķ�������(DAO,Data Access Object)
	 * 
	 * @param cl
	 *            Ҫ��ö����Class��
	 * @param sql
	 *            ��ѯ��sql���
	 * @param args
	 *            ��ѯ��sql���Ĳ���
	 * @return ���ݲ�ѯ�Ľ����Class�ļ����ɵĶ����б�
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

	// �����޸Ķ����Field
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

	// ������������
	public static void update(String sql) {
		Connection conn = null;
		Statement stat = null;

		try {
			checkSQL(sql);

			// ������ݿ������
			conn = DatabaseTool.getConnection();
			// ����һ��Statement����
			stat = conn.createStatement();
			// ִ��Update����,���Զ�Ӧinsert/update/delete
			stat.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, stat, conn);
		}

		System.out.println("executeUpdate sql: '" + sql + "' sucess.");
	}

	// ������������
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
	 * ���SQL����Ƿ�Ϸ�
	 * 
	 * @param sql
	 *            Ҫ����sql���
	 */
	private static void checkSQL(String sql) throws Exception {
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

		System.out.println("close resource done.");
	}
}

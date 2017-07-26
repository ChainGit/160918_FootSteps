package com.tgjdbc.day03;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.tgjdbc.day00.DatabaseTool;

/**
 * ���÷����JDBCԪ���ݻ�ö���
 * 
 * @author Chain
 *
 */
public class DAO {

	/**
	 * ����sql���Ͷ�Ӧ��sql����,������ѯ�������ô����Class�Ķ�������
	 * 
	 * @param cl
	 *            Ҫ��ö����Class��
	 * @param sql
	 *            ��ѯ��sql���
	 * @param args
	 *            ��ѯ��sql���Ĳ���
	 * @return ���ݲ�ѯ�Ľ����Class�ļ����ɵĶ���
	 */
	public <T> ArrayList<T> getObject(Class<T> cl, String sql, String... args) {
		ArrayList<T> lstT = new ArrayList<>();

		Connection conn = null;
		ResultSet res = null;
		PreparedStatement stat = null;
		try {
			conn = DatabaseTool.getConnection();
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
			DatabaseTool.close(res, stat, conn);
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
}

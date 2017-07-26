package com.tgjdbc.day07;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.dbutils.ResultSetHandler;

/**
 * DAO�ӿ�
 * 
 * @author Chain
 *
 */
public interface DAO {

	/**
	 * �������ݿ����ӳ��е�һ����������
	 * 
	 * @return ���ݿ�����,������ʧ�ܷ���null
	 */
	public Connection getConnection();

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
	public void update(Connection conn, String sql, Object... args);

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
	public <T> T query(Connection conn, ResultSetHandler<T> rsh, String sql, Object... args);

	/**
	 * ���SQL����Ƿ�Ϸ�
	 * 
	 * @param sql
	 *            Ҫ����sql���
	 */
	void check(String sql) throws Exception;

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
	public void close(ResultSet res, Statement stat, Connection conn);
}

package com.chapter7;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OneServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8083579796417300462L;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String dataBase = request.getParameter("dataBase");
		String tableName = request.getParameter("tableName");
		String user = request.getParameter("user");
		String password = request.getParameter("password");

		HttpSession session = request.getSession();
		Connection conn = null;
		OneBean recordBean = null;
		recordBean = (OneBean) session.getAttribute("recordBean");
		if (recordBean == null) {
			recordBean = new OneBean();
			session.setAttribute("recordBean", recordBean);
		}
		String uri = "jdbc:mysql://192.168.56.2/" + dataBase;
		try {
			conn = DriverManager.getConnection(uri, user, password);
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from " + tableName);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			String[] columnName = new String[columnCount];
			for (int i = 0; i < columnName.length; i++) {
				columnName[i] = metaData.getColumnName(i + 1);
			}
			recordBean.setColumnName(columnName);
			rs.last();
			int rowNumber = rs.getRow();
			String[][] tableRecord = recordBean.getTableRecord();
			tableRecord = new String[rowNumber][columnCount];
			rs.beforeFirst();
			int i = 0;
			while (rs.next()) {
				for (int j = 0; j < columnCount; j++)
					tableRecord[i][j] = rs.getString(j + 1);
				i++;
			}
			recordBean.setTableRecord(tableRecord);
			conn.close();
			response.sendRedirect("/170409_GpWebLab/Lab7_1/inputDatabase.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

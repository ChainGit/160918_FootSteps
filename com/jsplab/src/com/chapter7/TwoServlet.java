package com.chapter7;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TwoServlet extends HttpServlet {

	private static final long serialVersionUID = -7758265652900742247L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		TwoBean resultBean = null;

		try {
			resultBean = (TwoBean) request.getAttribute("resultBean");
			if (resultBean == null) {

				resultBean = new TwoBean();

				request.setAttribute("resultBean", resultBean);
			}

		} catch (Exception exp) {

			resultBean = new TwoBean();

			request.setAttribute("resultBean", resultBean);
		}

		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (Exception e) {
		}

		request.setCharacterEncoding("utf-8");

		String dataBase = request.getParameter("dataBase");

		String tableName = request.getParameter("tableName");
		String number = request.getParameter("number");

		String name = request.getParameter("name");

		String ageStr = request.getParameter("age");

		if (number == null || number.length() == 0) {

		}

		if (ageStr == null || ageStr.length() == 0)

			ageStr = "-1";

		int age = Integer.parseInt(ageStr);

		java.sql.Connection con;

		PreparedStatement sql;

		ResultSet rs;

		try {

			String uri = "jdbc:mysql://192.168.56.2/" + dataBase + "?" +

					"user=chain&password=0702";

			con = DriverManager.getConnection(uri);

			sql = con.prepareStatement("insert into " + tableName + " VALUES (?,?,?)");

			sql.setString(1, number);

			sql.setString(2, name);

			sql.setInt(3, age);

			System.out.println(dataBase + " " + tableName + " " + number + " " + name + " " + age);

			sql.executeUpdate();

			sql = con.prepareStatement("select * from " + tableName);

			rs = sql.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();

			String[] columnName = new String[columnCount];

			for (int i = 0; i < columnName.length; i++) {

				columnName[i] = metaData.getColumnName(i + 1);

			}

			resultBean.setColumnName(columnName);

			rs.last();

			int rowNumber = rs.getRow();

			String[][] tableRecord = resultBean.getTableRecord();

			tableRecord = new String[rowNumber][columnCount];

			rs.beforeFirst();

			int i = 0;

			while (rs.next()) {

				for (int k = 0; k < columnCount; k++)

					tableRecord[i][k] = rs.getString(k + 1);

				i++;

			}

			resultBean.setTableRecord(tableRecord);

			con.close();

			javax.servlet.RequestDispatcher dispatcher =

					request.getRequestDispatcher("Lab7_2/showRecord.jsp");

			dispatcher.forward(request, response);

		} catch (SQLException e) {

			System.out.println(e);

		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}

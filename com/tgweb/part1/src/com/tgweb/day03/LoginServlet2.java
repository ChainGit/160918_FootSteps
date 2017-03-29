package com.tgweb.day03;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tgweb.day02.MyHttpServlet;

public class LoginServlet2 extends MyHttpServlet {

	private Connection connect = null;

	@Override
	public void init() {
		try {
			getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("post");

		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");

		String usercli = req.getParameter("user");
		String passcli = req.getParameter("pass");

		boolean login = false;

		if (doCheck(usercli, passcli))
			login = doLogin(usercli, passcli);

		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		if (login)
			pw.write("<center><font size=\"12px\">µÇÂ½³É¹¦</font><center>");
		else
			pw.write("<center><font size=\"12px\">µÇÂ½Ê§°Ü</font><center>");
		pw.close();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("get");
		doPost(req, res);
	}

	private boolean doLogin(String usercli, String passcli) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		boolean login = false;
		try {
			conn = getConnection();
			if (conn == null)
				return login;
			String sql = "select count(*) num from login_user where name=? and pass=?";
			stat = conn.prepareStatement(sql);
			if (stat == null)
				return login;
			stat.setString(1, usercli);
			stat.setString(2, passcli);
			res = stat.executeQuery();
			if (res == null)
				return login;
			int num = -1;
			while (res.next())
				num = res.getInt("num");

			if (num > 0)
				login = true;

			System.out.println(usercli + "[" + passcli + "]" + ":" + num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeRes(null, stat, res);
		}

		return login;
	}

	private void closeRes(Connection conn, PreparedStatement stat, ResultSet res) {
		try {
			if (res != null)
				res.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (stat != null)
					stat.close();
			} catch (Exception e3) {
				e3.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private Connection getConnection() {
		if (connect != null) {
			System.out.println("use exist connection");
			return connect;
		}

		try {
			Properties prop = new Properties();
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("mysql.properties");
			prop.load(is);

			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			try {
				Class.forName(driver);
			} catch (Exception e) {
				e.printStackTrace();
			}

			connect = DriverManager.getConnection(url, prop);

			System.out.println("create new connection");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connect;
	}

	private boolean doCheck(String usercli, String passcli) {
		if (usercli == null || usercli.length() < 1 || usercli.matches("\'"))
			return false;
		if (passcli == null || passcli.length() < 1 || passcli.matches("\'"))
			return false;
		return true;
	}

}

package com.tgweb.basic.ajax.day07;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class GetInfo
 */
public class GetInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = request.getParameter("method");
		String id = request.getParameter("id");
		if (method == null)
			return;

		switch (method) {
		case "city":
			method = "getCityList";
			break;
		case "depart":
			method = "getDepartmentList";
			break;
		case "employ":
			method = "getEmployeeList";
			break;
		case "single":
			method = "getEmployeeSingle";
			break;
		}

		try {
			Method md = this.getClass().getDeclaredMethod(method, String.class, HttpServletRequest.class,
					HttpServletResponse.class);
			md.invoke(this, id, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private BaseDao dao = new BaseDao();

	public void getCityList(String id, HttpServletRequest request, HttpServletResponse response) {
		String sql = "SELECT city_id id,city_name name FROM city";
		process(response, sql, City.class);
	}

	public void getDepartmentList(String id, HttpServletRequest request, HttpServletResponse response) {
		if (id == null)
			return;
		String sql = "SELECT depart_id id,depart_name name,city_id eid FROM department WHERE city_id = " + id;
		process(response, sql, Department.class);
	}

	public void getEmployeeList(String id, HttpServletRequest request, HttpServletResponse response) {
		if (id == null)
			return;
		String sql = "SELECT employ_id id,employ_name name,depart_id eid FROM employee WHERE depart_id = " + id;
		process(response, sql, Employee.class);
	}

	public void getEmployeeSingle(String id, HttpServletRequest request, HttpServletResponse response) {
		if (id == null)
			return;
		String sql = "SELECT employ_id id,employ_name name,depart_id eid FROM employee WHERE employ_id = " + id;
		ObjectMapper mapper = new ObjectMapper();
		try {
			Object obj = dao.getForSingle(sql, Employee.class);
			String jsonRes = mapper.writeValueAsString(obj);
			response.getWriter().write(jsonRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private <T> void process(HttpServletResponse response, String sql, Class<T> bean) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<T> lst = dao.getForList(sql, bean);
			String jsonRes = mapper.writeValueAsString(lst);
			response.getWriter().write(jsonRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

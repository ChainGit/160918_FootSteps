package com.tgweb.struts2.day07;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ModelDriven;

public class EmployeeAction implements RequestAware, ModelDriven<Employee> {

	private Dao dao = new Dao();

	private Map<String, Object> requestMap;

	private Employee employee;

	public String list() {
		requestMap.put("emps", dao.getEmployees());
		return "list-success";
	}

	public String delete() {
		dao.delete(employee.getEmpId());
		return "redirect-success";
	}

	public String add() {
		dao.add(employee);
		return "redirect-success";
	}

	public String edit() {
		Employee emp = dao.get(employee.getEmpId());
		employee.setEmpName(emp.getEmpName());
		employee.setEmpEmail(emp.getEmpEmail());
		return "edit-success";
	}

	public String update() {
		dao.update(employee);
		return "redirect-success";
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.requestMap = arg0;
	}

	@Override
	public Employee getModel() {
		employee = new Employee();
		return employee;
	}

}

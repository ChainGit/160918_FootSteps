package com.tgweb.struts2.day07;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ModelDriven;

public class EmployeeAction implements RequestAware, ModelDriven<Employee> {

	private Dao dao = new Dao();

	private Map<String, Object> requestMap;

	private Employee employee;

	private Long empId;

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String list() {
		requestMap.put("emps", dao.getEmployees());
		return "list-success";
	}

	public String delete() {
		dao.delete(empId);
		return "redirect-success";
	}

	public String add() {
		dao.add(employee);
		return "redirect-success";
	}

	public String edit() {
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
		if (empId == null)
			employee = new Employee();
		else
			employee = dao.get(empId);
		return employee;
	}

}

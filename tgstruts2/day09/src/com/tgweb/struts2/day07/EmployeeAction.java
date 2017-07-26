package com.tgweb.struts2.day07;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class EmployeeAction implements RequestAware, ModelDriven<Employee>, Preparable {

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

	public void prepareAdd() {
		employee = new Employee();
	}

	public String edit() {
		return "edit-success";
	}

	public void prepareEdit() {
		employee = dao.get(empId);
	}

	public String update() {
		dao.update(employee);
		return "redirect-success";
	}

	public void prepareUpdate() {
		employee = new Employee();
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.requestMap = arg0;
	}

	@Override
	public Employee getModel() {
		return employee;
	}

	@Override
	public void prepare() throws Exception {
		System.out.println("prepare");
	}

}

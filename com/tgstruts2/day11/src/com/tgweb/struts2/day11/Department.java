package com.tgweb.struts2.day11;

import java.util.List;

public class Department {

	private Long deptId;
	private String deptName;
	private List<Employee> emps;
	private Manager mgr;

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<Employee> getEmps() {
		return emps;
	}

	public void setEmps(List<Employee> emps) {
		this.emps = emps;
	}

	public Manager getMgr() {
		return mgr;
	}

	public void setMgr(Manager mgr) {
		this.mgr = mgr;
	}

	public Department(Long deptId, String deptName, List<Employee> emps, Manager mgr) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.emps = emps;
		this.mgr = mgr;
	}

	public Department() {
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Department [deptId=").append(deptId).append(", deptName=").append(deptName).append(", emps=")
				.append(emps).append(", mgr=").append(mgr).append("]");
		return builder.toString();
	}

}

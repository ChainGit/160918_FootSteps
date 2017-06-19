package com.tgweb.struts2.day11;

import java.util.Date;

public class Employee {

	private Long empId;
	private String empName;
	private String empEmail;
	private Date empBirth;

	public Date getEmpBirth() {
		return empBirth;
	}

	public void setEmpBirth(Date empBirth) {
		this.empBirth = empBirth;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public Employee(Long empId, String empName, String empEmail, Date empBirth) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empEmail = empEmail;
		this.empBirth = empBirth;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [empId=").append(empId).append(", empName=").append(empName).append(", empEmail=")
				.append(empEmail).append(", empBirth=").append(empBirth).append("]");
		return builder.toString();
	}

	public Employee() {
	}

}

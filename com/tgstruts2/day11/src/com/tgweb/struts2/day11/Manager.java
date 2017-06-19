package com.tgweb.struts2.day11;

import java.util.Date;

public class Manager {

	private Long mgrId;
	private String mgrName;
	private String mgrEmail;
	private Date mgrBirth;

	public Date getMgrBirth() {
		return mgrBirth;
	}

	public void setMgrBirth(Date mgrBirth) {
		this.mgrBirth = mgrBirth;
	}

	public Long getMgrId() {
		return mgrId;
	}

	public void setMgrId(Long mgrId) {
		this.mgrId = mgrId;
	}

	public String getMgrName() {
		return mgrName;
	}

	public void setMgrName(String mgrName) {
		this.mgrName = mgrName;
	}

	public String getMgrEmail() {
		return mgrEmail;
	}

	public void setMgrEmail(String mgrEmail) {
		this.mgrEmail = mgrEmail;
	}

	public Manager(Long mgrId, String mgrName, String mgrEmail, Date mgrBirth) {
		super();
		this.mgrId = mgrId;
		this.mgrName = mgrName;
		this.mgrEmail = mgrEmail;
		this.mgrBirth = mgrBirth;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Manager [mgrId=").append(mgrId).append(", mgrName=").append(mgrName).append(", mgrEmail=")
				.append(mgrEmail).append(", mgrBirth=").append(mgrBirth).append("]");
		return builder.toString();
	}

	public Manager() {
	}

}

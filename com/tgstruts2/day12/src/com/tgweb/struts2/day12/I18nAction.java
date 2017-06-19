package com.tgweb.struts2.day12;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

public class I18nAction extends ActionSupport {

	private static final long serialVersionUID = 6113755644077237650L;

	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String execute() {
		System.out.println(this.getText("username"));
		date = new Date();
		
		System.out.println(date);
		return "success";
	}
}

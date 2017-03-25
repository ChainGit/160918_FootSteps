package com.tgjdbc.day02;

import java.util.LinkedList;

import com.tgjdbc.day00.DatabaseTool;

public class OperatePerson {

	public static void main(String[] args) {
		// addPerson();
		getPerson();
	}

	private static void getPerson() {
		getPersonListFromDB();
	}

	private static void getPersonListFromDB() {
		String sql = "select * from stu_info";
		DatabaseTool.query(sql);
	}

	@SuppressWarnings("unused")
	private static void addPerson() {
		LinkedList<Person> lst = new LinkedList<>();
		// ��ΪDatabaseTool����һֱ���е��߳�,���Բ��������������ݿ����
		lst.add(new Person("С��", 21));
		addPersonListToDB(lst);
	}

	private static void addPersonListToDB(LinkedList<Person> lst) {
		for (Person p : lst) {
			String sql = "insert into stu_info (name,age) values ('" + p.getName() + "'," + p.getAge() + ")";
			DatabaseTool.update(sql);
		}
	}
}

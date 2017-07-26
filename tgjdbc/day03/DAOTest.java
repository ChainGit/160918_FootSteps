package com.tgjdbc.day03;

import java.util.ArrayList;

public class DAOTest {

	public static void main(String[] args) {
		DAO dao = new DAO();

		// test1(dao);
		// test2(dao);
		test3(dao);
	}

	private static void test3(DAO dao) {
		String sql = "select tea_name name,tea_age age,tea_salary salary from tea_info where id = 2";

		ArrayList<Teacher> lstb = dao.getObject(Teacher.class, sql);

		for (Teacher t : lstb)
			System.out.println(t.getName() + "," + t.getAge() + "," + t.getSalary());

	}

	private static void test2(DAO dao) {
		String sql = "select tea_name name,tea_age age,tea_salary salary from tea_info";

		ArrayList<Teacher> lstb = dao.getObject(Teacher.class, sql);

		for (Teacher t : lstb)
			System.out.println(t.getName() + "," + t.getAge() + "," + t.getSalary());

	}

	private static void test1(DAO dao) {
		String sql = "select stu_name name,stu_age age,stu_score score from stu_info";

		ArrayList<Student> lsta = dao.getObject(Student.class, sql);

		for (Student s : lsta)
			System.out.println(s.getName() + "," + s.getAge() + "," + s.getScore());
	}
}

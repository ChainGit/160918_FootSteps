package com.bxd.day21;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class StudentDemo {

	private static final String PATH = "E:\\Special\\Tests";

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		TreeSet<Student> tstu = new TreeSet<>();
		Student st = null;
		System.out.println("please input(ex: zhansan,20,88,78,86):");
		while ((st = StudentTool.input(in)) != null)
			tstu.add(st);
		in.close();
		StudentTool.show(tstu);

		FileWriter fw = null;
		try {
			fw = new FileWriter(PATH + "\\student.txt");
			StudentTool.writeTo(fw, tstu);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fw != null)
					fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}

class StudentTool {

	public static void writeTo(Writer wr, Set<? extends Student> set) throws IOException {
		if (wr == null || set == null || set.size() < 1)
			throw new RuntimeException("NULL Err");

		for (Student s : set) {
			wr.write(s.toString());
			wr.write("\r\n");// In Windows
		}
		wr.flush();
	}

	public static void show(Set<? extends Student> set) {
		for (Student s : set)
			System.out.println(s);
	}

	public static Student input(Scanner in) {
		String s = in.nextLine();
		String[] da = null;
		if (s != null)
			if (s.equals("over"))
				return null;
			else
				da = s.split(",");

		StringBuilder sbname = new StringBuilder(da[0]);
		String name = null;
		// 限制名字的长度为10
		if (sbname.length() < 1)
			return null;
		if (sbname.length() > 10)
			name = sbname.substring(0, 10);
		else
			name = sbname.toString();

		int age = str2Int(da[1]);

		int chinese = str2Int(da[2]);
		int math = str2Int(da[3]);
		int english = str2Int(da[4]);
		if (chinese == -1 || math == -1 || english == -1 || age == -1)
			return null;

		return new Student(name, age, new Lesson(chinese, math, english));
	}

	private static int str2Int(String s) {
		int t = Integer.parseInt(s);
		// 限制数据在0~100之间
		if (t < 0 || t > 100)
			return -1;
		return t;
	}
}

class Lesson implements Comparable<Lesson> {

	private int math;
	private int english;
	private int chinese;
	private int sum;

	public Lesson(int c, int m, int e) {
		setChinese(c);
		setMath(m);
		setEnglish(e);
		setSum();
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	public int getChinese() {
		return chinese;
	}

	public void setChinese(int chinese) {
		this.chinese = chinese;
	}

	public void setSum() {
		sum = getMath() + getEnglish() + getChinese();
	}

	public int getSum() {
		return sum;
	}

	@Override
	public String toString() {
		return "[" + getChinese() + "," + getMath() + "," + getEnglish() + ";" + getSum() + "]";
	}

	@Override
	public int hashCode() {
		return (getChinese() >> 5) + (getMath() >> 4) + (getEnglish() >> 3);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Lesson))
			return false;

		return compareTo((Lesson) o) == 0 ? true : false;
	}

	@Override
	public int compareTo(Lesson lt) {
		if (lt.getChinese() == getChinese() && lt.getEnglish() == getEnglish() && lt.getMath() == getMath())
			return 0;

		int ds = getSum() - lt.getSum();
		if (ds == 0)
			return 1;
		else
			return ds;
	}

}

class Student extends Person2 implements Comparable<Student> {

	private Lesson le;

	Student(String s, int a, Lesson l) {
		super(s, a);
		setLesson(l);
	}

	public Lesson getLesson() {
		return le;
	}

	public void setLesson(Lesson le) {
		this.le = le;
	}

	@Override
	public String toString() {
		return getName() + "-" + getAge() + "-" + getLesson().toString();
	}

	@Override
	public int hashCode() {
		return getName().hashCode() + (getAge() >> 8) + getLesson().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Student))
			return false;

		return compareTo((Student) o) == 0 ? true : false;
	}

	@Override
	public int compareTo(Student s) {
		// TODO Auto-generated method stub
		String n = s.getName();
		int ag = s.getAge();
		if (!n.equals(getName()))
			return getName().compareTo(n);
		else if (ag != getAge())
			return getAge() - ag;
		else
			return getLesson().compareTo(s.getLesson());
	}

}

class Person2 {
	private String name;
	private int age;

	Person2(String s, int a) {
		setName(s);
		setAge(a);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}

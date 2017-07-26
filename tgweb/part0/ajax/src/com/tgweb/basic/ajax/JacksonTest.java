package com.tgweb.basic.ajax;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {

	@Test
	public void test() {
		Person per = new Person("Ð¡Ã÷", 21);
		Student stu = new Student("Ð¡ºì", 23, new int[] { 87, 78, 88 });
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonPer = mapper.writeValueAsString(per);
			String jsonStu = mapper.writeValueAsString(stu);
			System.out.println(jsonPer);
			System.out.println(jsonStu);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}

class Student {
	private Person per;
	private int chinese;
	private int math;
	private int english;

	public Student(String name, int age, int[] scores) {
		this.per = new Person(name, age);
		this.chinese = scores[0];
		this.math = scores[1];
		this.english = scores[2];
	}

	public Person getPerson() {
		return per;
	}

	public void setPerson(Person per) {
		this.per = per;
	}

	public int getChinese() {
		return chinese;
	}

	public void setChinese(int chinese) {
		this.chinese = chinese;
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

}

class Person {
	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
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

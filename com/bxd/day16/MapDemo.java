package com.bxd.day16;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class MapDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<Student, String> map = new HashMap<>();
		map.put(new Student("Zhansan", 19), "Beijing");
		map.put(new Student("Lisi", 23), "Tianjin");
		map.put(new Student("Wangwu", 20), "Shanghai");
		map.put(new Student("Songtao", 20), "Qingdao");
		map.put(new Student("Daipai", 22), "Nanjing");
		map.put(new Student("Wanghao", 21), "Nanjing");
		map.put(new Student("Wanghao", 21), "Xian");
		show1(map);
		System.out.println();
		show2(map);
		System.out.println();
		show3(map);
		System.out.println();
		show4(map);
		System.out.println();

		map = new TreeMap<>();
		map.put(new Student("Zhansan", 19), "Beijing");
		map.put(new Student("Lisi", 23), "Tianjin");
		map.put(new Student("Wangwu", 20), "Shanghai");
		map.put(new Student("Songtao", 20), "Qingdao");
		map.put(new Student("Daipai", 22), "Nanjing");
		map.put(new Student("Wanghao", 21), "Nanjing");
		map.put(new Student("Wanghao", 21), "Xian");
		show1(map);
		System.out.println();
		show2(map);
		System.out.println();
		show3(map);
		System.out.println();
		show4(map);
		System.out.println();

		//实现比较：
		//方法0
		map = new TreeMap<>();//默认比较器
		//方法1
		map = new TreeMap<>(new Student().new ComparatorByName());
		//方法2
		map = new TreeMap<>(new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				System.out.println(o1.getName() + ".compareTo(" + o2.getName() + ")");

				int delta = o1.getName().compareTo(o2.getName());
				if (delta == 0)
					return o1.getAge() - o2.getAge();

				return delta;
			}
		});
		//方法3
		map = new TreeMap<>((o1, o2) -> {
			System.out.println(o1.getName() + ".compareTo(" + o2.getName() + ")");

			int delta = o1.getName().compareTo(o2.getName());
			if (delta == 0)
				return o1.getAge() - o2.getAge();

			return delta;
		});
		map.put(new Student("CZhansan", 19), "Beijing");
		map.put(new Student("YLisi", 23), "Tianjin");
		map.put(new Student("SWangwu", 20), "Shanghai");
		map.put(new Student("BSongtao", 20), "Qingdao");
		map.put(new Student("ADaipai", 22), "Nanjing");
		map.put(new Student("ZWanghao", 21), "Nanjing");
		map.put(new Student("SWanghao", 21), "Xian");
		show1(map);
		System.out.println();
		show2(map);
		System.out.println();
		show3(map);
		System.out.println();
		show4(map);
		System.out.println();
	}

	public static void show1(Map<Student, String> map) {
		Iterator<Student> it = map.keySet().iterator();
		while (it.hasNext()) {
			Student s = it.next();
			System.out.println(s + "-" + map.get(s));
		}
	}

	public static void show2(Map<Student, String> map) {
		Iterator<Map.Entry<Student, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Student, String> e = it.next();
			System.out.println(e.getKey() + "-" + e.getValue());
		}
	}

	public static void show3(Map<Student, String> map) {
		for (Student s : map.keySet())
			System.out.println(s + "-" + map.get(s));
	}

	public static void show4(Map<Student, String> map) {
		for (Map.Entry<Student, String> e : map.entrySet())
			System.out.println(e + ": " + e.getKey() + "-" + e.getValue());
	}

}

class Student extends Person implements Comparable<Student> {

	public Student(String name, int age) {
		super(name, age);
		// TODO Auto-generated constructor stub
	}

	public Student() {
		// TODO Auto-generated constructor stub
		super();
	}

	public class ComparatorByName implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			// TODO Auto-generated method stub
			System.out.println(o1.getName() + ".compareTo(" + o2.getName() + ")");

			int delta = o1.getName().compareTo(o2.getName());
			if (delta == 0)
				return o1.getAge() - o2.getAge();

			return delta;
		}

	}

	// 优先比较年龄
	@Override
	public int compareTo(Student stu) {
		// TODO Auto-generated method stub

		System.out.println(this.getName() + ".compareTo(" + stu.getName() + ")");

		int delta = this.getAge() - stu.getAge();
		if (delta == 0)
			return this.getName().compareTo(stu.getName());

		return delta;
	}

	@Override
	public String toString() {

		System.out.println(this.getName() + ".toString()");

		return this.getName() + "[" + this.getAge() + "]";
		// return new String(super.getName()+"["+super.getAge()+"]");
	}

	@Override
	public int hashCode() {

		System.out.println(this.getName() + ".hashCode()");

		return super.getName().hashCode() + super.getAge();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Student))
			throw new ClassCastException("not Student");

		Student stu = (Student) obj;

		System.out.println(this.getName() + ".equals(" + stu.getName() + ")");

		return this.getName().equals(stu.getName()) && this.getAge() == stu.getAge();
	}

}

class Person {
	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Person() {
		// TODO Auto-generated constructor stub
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
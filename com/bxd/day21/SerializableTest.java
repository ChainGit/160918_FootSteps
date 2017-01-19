package com.bxd.day21;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableTest {

	private static final String PATH = "E:\\Special\\Tests";

	public static void main(String[] args) throws Exception {
		test1();
		test2();
	}

	public static void test1() throws Exception {
		// TODO Auto-generated method stub
		ObjectOutputStream objos = new ObjectOutputStream(new FileOutputStream(PATH + "\\person.obj"));

		objos.writeObject(new Person("zhansan", 20, "JAPAN"));
		objos.writeObject(new Person("lisi", 21, "USA"));
		objos.writeObject(new Person("wangwu", 19, "KEA"));

		System.out.println("save end!");

		objos.close();
	}

	public static void test2() throws Exception {
		ObjectInputStream objis = new ObjectInputStream(new FileInputStream(PATH + "\\person.obj"));

		Object obj = null;
		try {
			while (true) {
				obj = objis.readObject();
				Person p1 = (Person) obj;
				System.out.println(p1.getName() + "-" + p1.getAge() + "-" + p1.getCountry());
			}
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			System.out.println("read end!");
			objis.close();
		}

	}
}

// 序列化序列堆内存的数据
class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2494486109058875721L;
	// private static final long serialVersionUID = -5893167203038927646L;

	private String name;
	private int age;
	// 加入transient可以避免堆内存的数据被序列化
	private transient String country = "CHINA";

	// 不能序列化静态成员
	protected static String info = "stuinfo";

	public Person(String n, int a, String c) {
		this.setName(n);
		this.setAge(a);
		this.setCountry(c);
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}

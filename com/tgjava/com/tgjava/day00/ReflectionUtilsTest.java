package com.tgjava.day00;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class ReflectionUtilsTest {

	// @Test
	public void test1() {
		MyArrayList1<String> lst1 = new MyArrayList1<>();
		MyArrayList1<Person> lst2 = new MyArrayList1<>();
		MyArrayList2 lst3 = new MyArrayList2();
		MyHashMap map = new MyHashMap();

		TestUtils.println(ReflectionUtils.getAllGenericSuperType(getClass()));
		TestUtils.println(ReflectionUtils.getAllGenericSuperType(lst1.getClass()));
		TestUtils.println(ReflectionUtils.getAllGenericSuperType(lst2.getClass()));
		TestUtils.println(ReflectionUtils.getAllGenericSuperType(lst3.getClass()));
		TestUtils.println(ReflectionUtils.getAllGenericSuperType(map.getClass()));
		TestUtils.println(ReflectionUtils.getFirstGenericSuperType(map.getClass()));
		TestUtils.println(ReflectionUtils.getLastGenericSuperType(map.getClass()));
		TestUtils.println(ReflectionUtils.getGenericSuperType(map.getClass(), 0));
		TestUtils.println(ReflectionUtils.getGenericSuperType(map.getClass(), 1));
		TestUtils.println(ReflectionUtils.getGenericSuperType(map.getClass(), 100));
		TestUtils.println(ReflectionUtils.getGenericSuperType(map.getClass(), -100));
	}

	@Test
	public void test2() throws NoSuchMethodException, NoSuchFieldException {
		Person per = new Person();
		Student stu = new Student();
		CollegeStudent cstu = new CollegeStudent();

		ReflectionUtils.invokeDeclaredMethod(per, "showPerson", null, null);
		ReflectionUtils.invokeDeclaredMethod(per, "showData", null, null);

		ReflectionUtils.invokeDeclaredMethod(cstu, "showData", null, null);
		ReflectionUtils.invokeDeclaredMethod(cstu, "showStudent", null, null);
		ReflectionUtils.invokeDeclaredMethod(cstu, "showPerson", null, null);

		TestUtils.println(ReflectionUtils.getDeclaredFieldValue(cstu, "name"));
		TestUtils.println(ReflectionUtils.getDeclaredFieldValue(cstu, "age"));
		TestUtils.println(ReflectionUtils.getDeclaredFieldValue(cstu, "id"));
	}

}

class MyArrayList1<T> extends ArrayList<T> {
	private static final long serialVersionUID = 8459731134734667590L;
}

class MyArrayList2 extends ArrayList<Person> {
	private static final long serialVersionUID = 4152061240932657360L;
}

class MyHashMap extends HashMap<String, Person> {
	private static final long serialVersionUID = 3249789991292166167L;
}
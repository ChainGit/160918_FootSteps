package com.bxd.day27;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ��day27��ʼ<br>
 * ����ժ�Ա���35����Ƶ<br>
 * ֮ǰ���Ǳ���25����Ƶ<br>
 * 
 * ���䣺����ִ��Class�ļ�������Tomcat��web.xml�ļ�
 * 
 * @author Chain
 *
 */
public class ReflactTest {

	public static void main(String[] args) {
		test1();
		test2();
		test3();
	}

	// ����ʹ�ù�����������������
	@SuppressWarnings("unchecked")
	private static void test3() {
		String name = "com.bxd.day27.Base";
		Class<Base> c = null;
		try {
			c = (Class<Base>) Class.forName(name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Constructor<Base> cons = null;
		try {
			cons = c.getConstructor(String.class, int.class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		Field fname = null;
		try {
			fname = c.getDeclaredField("name");// ���Ի��private,��Ҫ����setAccessable
		} catch (NoSuchFieldException | SecurityException e2) {
			e2.printStackTrace();
		}

		Field fage = null;
		try {
			fage = c.getDeclaredField("age");
		} catch (NoSuchFieldException | SecurityException e1) {
			e1.printStackTrace();
		}

		Field fid = null;
		try {
			fid = c.getField("id");// ���ܻ��private
		} catch (NoSuchFieldException | SecurityException e1) {
			e1.printStackTrace();
		}

		// ��÷�private����
		Method[] meths = c.getMethods();
		for (Method m : meths)
			System.out.println(m);

		Method m1 = null;
		try {
			m1 = c.getMethod("getName", null);
		} catch (NoSuchMethodException | SecurityException e1) {
			e1.printStackTrace();
		}

		Method m2 = null;
		try {
			m2 = c.getDeclaredMethod("getPerson", null);
		} catch (NoSuchMethodException | SecurityException e1) {
			e1.printStackTrace();
		}

		Method m3 = null;
		try {
			m3 = c.getMethod("setName", String.class);
		} catch (NoSuchMethodException | SecurityException e1) {
			e1.printStackTrace();
		}

		// ��������
		Object obj = null;
		if (cons != null)
			try {
				obj = cons.newInstance("Zhansan", 20);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}

		// ��������,��ʹ˽��privateҲ�����õ�����
		fname.setAccessible(true);
		fage.setAccessible(true);

		m2.setAccessible(true);

		if (obj != null)
			try {
				System.out.println(fname.get(obj));
				System.out.println(fage.get(obj));
				System.out.println(fid.get(obj));
				System.out.println(m1.invoke(obj, null));
				System.out.println(m2.invoke(obj, null));
				System.out.println(m3.invoke(obj, "Lisi"));
				System.out.println(m2.invoke(obj, null));
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}

	}

	// ���佨������
	@SuppressWarnings("unchecked")
	private static void test2() {
		String name = "com.bxd.day27.Base";
		Class<Base> c = null;
		try {
			c = (Class<Base>) Class.forName(name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Object obj = null;
		try {
			obj = c.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		if (obj != null)
			System.out.println(obj.toString());

	}

	// ��ȡClass�ļ����ַ���
	@SuppressWarnings("unchecked")
	public static void test1() {
		Base base = new Base();
		Class<Base> c1 = (Class<Base>) base.getClass();
		System.out.println(c1.getName());

		Class<Base> c2 = Base.class;
		System.out.println(c2.getName());

		String name = "com.bxd.day27.Base";
		Class<Base> c3 = null;
		try {
			c3 = (Class<Base>) Class.forName(name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (c3 != null)
			System.out.println(c3.getName());

	}
}

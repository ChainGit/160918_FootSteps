package com.bxd.day17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Java的简化书写的升级，有好有坏，结合老方法一起使用。
public class ArraysTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
		test2();
		test3();
	}

	public static void test3() {
		// TODO Auto-generated method stub
		varp1(new int[] { 1, 2, 3, 4 });
		varp2(1, 2, 3, 4);
	}

	public static void varp2(int... a) {
		System.out.println(a);
		varp1(a);
	}

	public static void varp1(int[] a) {
		for (int i : a)
			System.out.print(i);
		System.out.println(a);
	}

	public static void test2() {
		// TODO Auto-generated method stub
		List<Integer> lst = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			lst.add(i * ((int) (Math.random() * (0 + 10) + 0)));
		}
		System.out.println(lst);

		// Integer[] res=(Integer[])lst.toArray();
		Integer[] res = lst.toArray(new Integer[lst.size()]);
		for (int i : res)// res需要具有Iterable
			System.out.print(i + " ");
		System.out.println();
		res[1] = 10;// 可以修改，不改变原来集合的值
		for (int i : res)
			System.out.print(i + " ");
		System.out.println();

		for (int i : lst)
			System.out.print(i + " ");
		System.out.println();
	}

	public static void test1() {
		int[] a01 = { 1, 2, 3 };
		Integer[] a02 = { 1, 2, 3 };
		System.out.println(Arrays.toString(a01));
		System.out.println(Arrays.toString(a02));

		int[] a1 = new int[] { 1, 2, 3 };
		Integer[] a2 = new Integer[] { 1, 2, 3 };

		List<int[]> lst1 = Arrays.asList(a1);
		System.out.println(lst1);

		List<Integer> lst2 = Arrays.asList(a2);
		System.out.println(lst2);

		// asList返回的是Arrays内部类ArrayList，不是java.util.ArrayList
		// lst1.add(4);
		// lst1.add(new int[] { 4 });
		// System.out.println(lst1);
		// lst.toString()

		// lst2.add(4);
		// System.out.println(lst2);
		lst1.set(0, new int[] { 7, 8, 9 });// 可以置换
		System.out.println(lst1);
		System.out.println(a1);//置换不改变原来的数组

	}

}

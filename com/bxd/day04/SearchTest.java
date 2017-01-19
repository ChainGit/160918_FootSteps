package com.bxd.day04;

import com.bxd.day03.RandomInteger;

public class SearchTest {
	
	private static final int KEY_NUM=14;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = new int[10];
		a = RandomInteger.randomSelectArray(10, 19, 10);
		printArray(a);
		printArray(a, Search.orderSearch(a, KEY_NUM));

		Sort.selectSort(a);
		printArray(a);
		printArray(a, Search.halfSearch(a, KEY_NUM));

	}

	public static void printArray(int[] a) {
		for (int i : a) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public static void printArray(int[] a, int k) {
		if (k != -1)
			System.out.println("Ans: a[" + k + "] = " + a[k]);
		else
			System.out.println("Sorry,key is " + k);
	}

}
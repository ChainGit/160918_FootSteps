package com.bxd.day04;

import com.bxd.day03.RandomInteger;

public class SortTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = new int[10];

		a = RandomInteger.randomSelectArray(0, 9, 10);
		printArray(a);
		Sort.insertSort(a);
		printArray(a);
		System.out.println();

		a = RandomInteger.randomSelectArray(0, 9, 10);
		printArray(a);
		Sort.bubbleSort(a);
		printArray(a);
		System.out.println();

		a = RandomInteger.randomSelectArray(0, 9, 10);
		printArray(a);
		Sort.shellSort(a, 0);
		printArray(a);
		System.out.println();

		a = RandomInteger.randomSelectArray(0, 9, 10);
		printArray(a);
		Sort.shellSort(a, 1);
		printArray(a);
		System.out.println();

		a = RandomInteger.randomSelectArray(0, 9, 10);
		printArray(a);
		Sort.shellSort(a, 2);
		printArray(a);
		System.out.println();

		a = RandomInteger.randomSelectArray(0, 9, 10);
		printArray(a);
		Sort.quickSort(a, 0, a.length - 1);
		printArray(a);
		System.out.println();

	}

	public static void printArray(int[] a) {
		for (int i : a) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

}

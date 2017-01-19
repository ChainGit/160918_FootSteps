package com.bxd.day04;

/**
 * 常见的查找算法
 * 
 * @author Lenovo
 * @version 20161120
 *
 */
public class Search {

	/**
	 * 线性查找
	 * 
	 * @param a
	 *            待查找数组
	 * @param k
	 *            查找的数据
	 * @return 若找到则返回元素所在数组的下标，若没有找到则返回-1
	 */
	public static int orderSearch(int[] a, int k) {
		int index = -1;
		for (index = 0; index < a.length; index++) {
			if (a[index] == k)
				return index;
		}
		return -1;
	}

	/**
	 * 折半查找(二分查找，必须有序数组或者已知固定数组)
	 * 
	 * @param a
	 *            待查找数组
	 * @param k
	 *            查找的数据
	 * @return 若找到则返回元素所在数组的下标，若没有找到则返回-1
	 */
	public static int halfSearch(int[] a, int k) {
		int min, max, mid;
		min = 0;
		max = a.length - 1;

		do {
			mid = (max + min) >> 1;

			if (k < a[mid])
				max = mid - 1;
			else if (k > a[mid])
				min = mid + 1;
			else if (k == a[mid])
				return mid;

		} while (min <= max);
		return -1;
	}

}

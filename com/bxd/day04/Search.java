package com.bxd.day04;

/**
 * �����Ĳ����㷨
 * 
 * @author Lenovo
 * @version 20161120
 *
 */
public class Search {

	/**
	 * ���Բ���
	 * 
	 * @param a
	 *            ����������
	 * @param k
	 *            ���ҵ�����
	 * @return ���ҵ��򷵻�Ԫ������������±꣬��û���ҵ��򷵻�-1
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
	 * �۰����(���ֲ��ң������������������֪�̶�����)
	 * 
	 * @param a
	 *            ����������
	 * @param k
	 *            ���ҵ�����
	 * @return ���ҵ��򷵻�Ԫ������������±꣬��û���ҵ��򷵻�-1
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

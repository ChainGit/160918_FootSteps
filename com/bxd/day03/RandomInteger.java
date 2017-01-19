package com.bxd.day03;

import java.util.HashSet;
import java.util.Random;

public class RandomInteger {

	/**
	 * ���ָ����Χ��N�����ظ����� ���������ķ���(˫��ѭ��)
	 * 
	 * @param min
	 *            ָ����Χ��Сֵ
	 * @param max
	 *            ָ����Χ���ֵ
	 * @param n
	 *            ���������
	 */
	public static int[] randomDoubleCirculation(int min, int max, int n) {
		if (n > (max - min + 1) || max < min) {
			return null;
		}
		int[] result = new int[n];
		int count = 0;
		boolean flag = true;
		while (count < n - 1) {
			int num = (int) (Math.random() * (max - min)) + min;
			flag = true;
			for (int j = 0; j < count; j++) {
				if (num == result[j]) {
					flag = false;
					break;
				}
			}
			if (flag == true) {
				result[count] = num;
				count++;
			}
		}
		return result;
	}

	/**
	 * ���ָ����Χ��N�����ظ����� ����HashSet��������ֻ�ܴ�Ų�ͬ��ֵ
	 * 
	 * @param min
	 *            ָ����Χ��Сֵ
	 * @param max
	 *            ָ����Χ���ֵ
	 * @param n
	 *            ���������
	 * @param HashSet<Integer>
	 *            set ����������
	 */
	public static void randomHashSet(int min, int max, int n, HashSet<Integer> set) {
		if (n > (max - min + 1) || max < min) {
			return;
		}
		for (int i = 0; i < n; i++) {
			// ����Math.random()����
			int num = (int) (Math.random() * (max - min)) + min;
			set.add(num);// ����ͬ��������HashSet��
		}
		int setSize = set.size();
		// ����������С��ָ�����ɵĸ���������õݹ�������ʣ�����������������ѭ����ֱ���ﵽָ����С
		if (setSize < n) {
			randomHashSet(min, max, n - setSize, set);// �ݹ�
		}
	}

	/**
	 * ���ָ����Χ��N�����ظ����� �ڳ�ʼ�������ظ���ѡ�������������һ�����������У�
	 * ����ѡ���鱻������������ô�ѡ����(len-1)�±��Ӧ�����滻 Ȼ���len-2�����������һ����������������
	 * 
	 * @param max
	 *            ָ����Χ���ֵ
	 * @param min
	 *            ָ����Χ��Сֵ
	 * @param n
	 *            ���������
	 * @return int[] ����������
	 */
	public static int[] randomSelectArray(int min, int max, int n) {
		int len = max - min + 1;

		if (max < min || n > len) {
			return null;
		}

		// ��ʼ��������Χ�Ĵ�ѡ����
		int[] source = new int[len];
		for (int i = min; i < min + len; i++) {
			source[i - min] = i;
		}

		int[] result = new int[n];
		Random rd = new Random();
		int index = 0;
		for (int i = 0; i < result.length; i++) {
			// ��ѡ����0��(len-2)���һ���±�
			index = Math.abs(rd.nextInt() % len--);
			// �������������������
			result[i] = source[index];
			// ����ѡ�����б�������������ô�ѡ����(len-1)�±��Ӧ�����滻
			source[index] = source[len];
		}
		return result;
	}
}

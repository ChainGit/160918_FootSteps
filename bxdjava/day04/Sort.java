package com.bxd.day04;

/**
 * �����������㷨
 * 
 * @author Lenovo
 * @version 20161120
 *
 */
public class Sort {

	/**
	 * ��������Ԫ��(���)
	 * 
	 * @param a
	 *            Ԫ����������
	 * @param x
	 *            Ԫ��A
	 * @param y
	 *            Ԫ��B
	 */
	private static void swap(int[] a, int x, int y) {
		a[x] ^= a[y];
		a[y] ^= a[x];
		a[x] ^= a[y];
	}

	/**
	 * ѡ�����򣺴ӵ�һ��Ԫ�ؿ�ʼ���Ӻ����Ԫ��ѡ��һ����С��Ԫ�أ�Ȼ�󽫸����Ԫ�ص��뱾Ԫ�ؽ���
	 * 
	 * @param a
	 *            ����������
	 */
	public static void selectSort(int[] a) {
		int i, j, max;
		for (i = 0; i < a.length - 1; i++) {
			max = i;
			for (j = i; j < a.length; j++) {
				if (a[max] > a[j])
					max = j;
			}
			if (max != i) {
				swap(a, i, max);
			}
		}
	}

	/**
	 * ��������:�ӵڶ���Ԫ�ؿ�ʼ����Ԫ�غ�֮ǰ�Ľ��бȽϣ����뵽֮ǰ�ĺ���λ�ã�����Ԫ�غ���
	 * 
	 * @param a
	 *            ����������
	 */
	public static void insertSort(int[] a) {
		// tΪҪ���뵽ǰ��Ԫ�����е��Ǹ�����ֵ
		int i, j, t;
		for (i = 1; i < a.length; i++) {
			t = a[i];
			for (j = i - 1; j > -1 && a[j] > t; j--) {
				a[j + 1] = a[j];
			}
			a[j + 1] = t;
		}
	}

	/**
	 * ð������(��flag):�ӵ�һ����ʼ����Ƚ�֮������ݣ�Ȼ����ͬð��һ�㽫������������
	 * 
	 * @param a
	 *            ����������
	 */
	public static void bubbleSort(int[] a) {
		int i, j;
		// flag���ڱ�������Ƿ��Ѿ�����,falseΪ����
		boolean flag = true;
		for (i = 0; i < a.length - 1 && flag == true; i++) {
			flag = false;
			for (j = 0; j < a.length - 1 - i; j++)
				if (a[j] > a[j + 1]) {
					flag = true;
					swap(a, j, j + 1);
				}
		}
	}

	/**
	 * ϣ��Shell����:ÿһ�εļ����������һ�ε�1/2��������Ӧ�������������򷨣�Ԫ�ص��ƶ�������
	 * ������Ծʽ��
	 * 
	 * @param a
	 *            ����������
	 * 
	 * @param s
	 *            ����ѡ�0 ������ʹ��ð������ 1 ������ʹ�ò������� 2
	 *            ������ʹ��ѡ������
	 */
	public static void shellSort(int[] a, int s) {
		if (s > 2 || s < 0)
			return;

		int gap = -1;
		for (gap = a.length >> 1; gap > 0; gap >>= 1) {
			for (int i = 0; i < gap; i++) {

				switch (s) {
				case 0:
					// ������ʹ��ð������
					boolean flag = true;
					for (int j = i; j < a.length - gap && flag == true; j += gap) {
						flag = false;
						for (int k = i; k < a.length - j - gap; k += gap) {
							if (a[k] > a[k + gap]) {
								flag = true;
								swap(a, k, k + gap);
							}
						}
					}
					break;
				case 1:
					// ������ʹ�ò�������
					for (int j = i + gap; j < a.length; j += gap) {
						int t = a[j];
						int k = j - gap;
						for (; k > i - gap && a[k] > t; k -= gap) {
							a[k + gap] = a[k];
						}
						a[k + gap] = t;
					}
					break;
				case 2:
					// ������ʹ��ѡ������
					for (int j = i; j < a.length - gap; j += gap) {
						int index = j;
						for (int k = j; k < a.length; k += gap) {
							if (a[index] > a[k])
								index = k;
						}
						if (index != j) {
							swap(a, j, index);
						}
					}
					break;
				default:
					break;
				}

			}
		}
	}

	/**
	 * ��������:���õ��Ƿ���˼�룬��һ��Ԫ����Ϊ��׼(pivot)��Ȼ���������з���(partition)���
	 * �,ʹ��׼���Ԫ�ص�ֵ�������ڻ�׼ֵ, ��׼�ұߵ�Ԫ��ֵ
	 * ����С�ڻ�׼ֵ�������Ϊ��׼��Ԫ�ص�������������ȷλ�ã��ݹ�������򣬽�����n-1��Ԫ��Ҳ����
	 * ����������ȷλ�á� �������������������� ��һ���ǽ�����ֵ����󽫻�׼����"����"
	 * �ڶ����ǽ�����׼������׼����"����"
	 * 
	 * @param a
	 *            ����������
	 * @param le
	 *            ���±�
	 * @param ri
	 *            ���±�
	 */
	public static void quickSort(int[] a, int le, int ri) {
		if (le >= ri)
			return;

		// ��������з���
		int i = le;
		int j = ri;
		// p�洢��׼ֵ
		int p = a[le];
		while (i < j) {
			while (a[j] > p && i < j)
				j--;

			if (i < j) {
				a[i] = a[j];
				i++;
			}

			while (a[i] < p && i < j)
				i++;

			if (i < j) {
				a[j] = a[i];
				j--;
			}

		}
		a[i] = p;

		quickSort(a, le, i - 1); // �Ե��ֱ���еݹ�����
		quickSort(a, i + 1, ri); // �Ը��ֱ���еݹ�����

	}

}

package com.bxd.day16;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//���A��ɾ��D���ж�J����ȡG
//HashTable HashMap(�ײ���HashTable,����ռ���ֵ) TreeMap(���Զ�������)
public class MapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
		test2();
		test3();
	}

	// entrySet���ǽ�key-value���ӳ���ϵ��Ϊ������뵽set��
	public static void test3() {
		// TODO Auto-generated method stub
		Map<Integer, String> map = new HashMap<>();
		map.put(0, "s00");
		map.put(0, "s000");
		map.put(1, "s01");
		Set<Map.Entry<Integer, String>> set = map.entrySet();
		Iterator<Map.Entry<Integer, String>> it = set.iterator();
		while (it.hasNext())
			System.out.println(it.next().getValue());
	}

	// keySet����˼����һ�����Key��set����
	public static void test2() {
		// TODO Auto-generated method stub
		Map<Integer, String> map = new HashMap<>();
		map.put(0, "s00");
		map.put(0, "s000");
		map.put(1, "s01");
		Set<Integer> set = map.keySet();
		Iterator<Integer> it = set.iterator();
		while (it.hasNext())
			System.out.println(map.get(it.next()));
		System.out.println(it.toString());
		for (Integer i : set)
			System.out.println(map.get(i));
	}

	public static void test1() {
		// Map<Integer,String> map=new Hashtable<>();
		Map<Integer, String> map = new HashMap<>();
		map.put(0, "s00");
		map.put(0, "s000");
		map.put(1, "s01");
		System.out.println(map.get(0));
		System.out.println(map.get(2));
		System.out.println(map.containsKey(1));
		System.out.println(map.containsKey(2));
		System.out.println(map.containsValue("s000"));
		Collection<String> col = map.values();
		System.out.println(col.toString());

	}

}

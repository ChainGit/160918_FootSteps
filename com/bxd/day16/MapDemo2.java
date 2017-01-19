package com.bxd.day16;

import java.util.Map;
import java.util.TreeMap;

public class MapDemo2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = new String("aacd@dsw%s*aw-sQSa.sQ+aq@ASX!sax");

		print(count(s));

	}

	public static void print(Map<Character, Integer> map) {
		// TODO Auto-generated method stub
		System.out.println(map);
	}

	public static Map<Character, Integer> count(String s) {
		// TODO Auto-generated method stub
		char[] chs = s.replaceAll("[^a-zA-z]", "").toLowerCase().toCharArray();
		Map<Character, Integer> map = new TreeMap<>();
		for (int i = 0; i < chs.length; i++) {
			Integer val = map.get(chs[i]);
			map.put(chs[i], val == null ? 1 : val + 1);
		}
		return map;
	}

}

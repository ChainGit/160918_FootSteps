package com.bxd.day16;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MapDemo3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<Integer, String> class1 = new TreeMap<>();
		Map<Integer, String> class2 = new TreeMap<>();

		for (int i = 9; i >= 0; i--)
			class1.put(i, "a" + i);

		for (int i = 0; i < 10; i++)
			class2.put(i, "b" + i);

		Map<String, Map<Integer, String>> school = new SchoolMap<>();
		school.put("class1", class1);
		school.put("class2", class2);

		System.out.println(school.toString());
	}

}

class SchoolMap<K, V> extends TreeMap<K, V> {

	private static final long serialVersionUID = -7103713322347486468L;

	@Override
	public String toString() {
		String ls = System.getProperty("line.separator");
		String s = "";
		Set<Map.Entry<K, V>> e = this.entrySet();
		Iterator<Map.Entry<K, V>> it = e.iterator();
		while (it.hasNext()) {
			Map.Entry<K, V> m = it.next();
			s += m.getKey() + " : " + m.getValue() + ls;
		}
		return s;
	}
}

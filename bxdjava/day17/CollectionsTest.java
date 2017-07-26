package com.bxd.day17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CollectionsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Person> lst = new ArrayList<>();
		lst.add(new Person("HHH", 23));
		lst.add(new Person("AAA", 21));
		lst.add(new Person("CCC", 18));
		lst.add(new Person("CCB", 18));
		lst.add(new Person("AAA", 22));
		lst.add(new Person("AAA", 22));

		test1(lst);
		test2(lst);
		test3(new ArrayList<>(lst));
		test4(lst);
		test5(new ArrayList<>(lst));
	}

	public static void test1(List<Person> lst) {

		Collections.sort(lst, new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2) {
				return -1;
			}
		});

		// Collections.sort(lst, new PersonComparator());

		// Collections.sort(lst, (Person p1, Person p2) -> {return -1;});

		System.out.println(lst);

		Collections.sort(lst);
		System.out.println(lst);

		System.out.println(Collections.max(lst));
		System.out.println(Collections.min(lst));
	}

	public static void test2(List<Person> lst) {
		System.out.println(Collections.binarySearch(lst, new Person("AAA", 22)));
		System.out.println(Collections.binarySearch(lst, new Person("AAA", 23)));

		System.out.println(myBinarySearch(lst, new Person("AAA", 22)));
		System.out.println(myBinarySearch(lst, new Person("AAA", 23)));

		System.out.println(Collections.binarySearch(lst, new Person("AAA", 23), (Person p1, Person p2) -> {
			return -1;
		}));
	}

	public static void test3(List<Person> lst) {
		Collections.replaceAll(lst, new Person("AAA", 22), new Person("ABC", 33));
		System.out.println(lst);
		Collections.reverse(lst);
		System.out.println(lst);
		Collections.fill(lst, new Person("XXX", 99));
		System.out.println(lst);
	}

	public static void test4(List<Person> lst) {

		System.out.println(lst);

		Set<Person> set = new TreeSet<>(Collections.reverseOrder());
		set.addAll(lst);

		Iterator<Person> it = set.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();

		Set<Person> set2 = new TreeSet<>(Collections.reverseOrder(new PersonComparator()));
		set2.addAll(lst);
		for (Person p : set2)
			System.out.print(p + " ");
		System.out.println();

	}

	public static void test5(List<Person> lst) {
		System.out.println(lst);

		Collections.swap(lst, 2, 3);
		System.out.println(lst);

		Collections.shuffle(lst);//随手放
		System.out.println(lst);

		List<Person> syncLst = Collections.synchronizedList(lst);
		System.out.println(syncLst);
	}

	public static int myBinarySearch(List<Person> lst, Person pkey) {
		int max = lst.size() - 1, min = 0, mid = 0;

		while (min <= max) {
			mid = (max + min) >>> 1;

			int r = lst.get(mid).compareTo(pkey);

			if (r > 0)
				max = mid - 1;
			else if (r < 0)
				min = mid + 1;
			else
				return mid;

		}
		return -mid - 1;
	}

}

class PersonComparator implements Comparator<Person> {
	@Override
	public int compare(Person p1, Person p2) {
		return -1;
	}
}

class Person implements Comparable<Person> {
	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return name + "-" + age;
	}

	@Override
	public int compareTo(Person p) {
		int t = name.compareTo(p.name);
		return t == 0 ? age - p.age : t;
	}

}

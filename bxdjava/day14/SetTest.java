package com.bxd.day14;

import static com.bxd.day00.SimplePrint.sop;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

//Set 无序 不可重复
//Set 与Collection类似
//HashSet TreeSet
//HashSet 以HashCode为首要比较目标，其次是对象本身的equals方法
//HashSet底层是HashMap
//TreeSet排序特性，看Comparator的compare方法或对象本身的Comparable接口的覆写的compareTo方法，equals尽量不覆写
//                不看hashCode
public class SetTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		sop("HashSet Test");

		Set<Integer> set = new HashSet<Integer>();
		sop(set.isEmpty() + "" + set.size());
		for (int i = 0; i < 10; i++) {
			sop(set.add(i % 5));
		}

		Iterator<Integer> it = set.iterator();
		while (it.hasNext()) {
			sop(it.next());
		}

		sop("TreeSet Test");

		TreeSet<Integer> set2 = new TreeSet<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				if (o1 > o2) {
					sop("o1>o2");
					return o1 - o2;
				} else if (o1 < o2) {
					sop("o1<o2");
					return o1 - o2;
				} else {
					sop("o1==o2");
					return 0;
				}
			}

		});

		for (int i = 0; i < 10; i++) {
			set2.add((int) (Math.random() * (0 + 10) + 0));
		}

		it = set2.iterator();
		while (it.hasNext()) {
			sop(it.next());
		}
		

		for (Integer i : set2) {
			sop(i);
		}

		sop("HashSet Person Demo");

		Set<Person> setp = new HashSet<>();
		setp.add(new Person("AAA", 20));
		setp.add(new Person("BBB", 22));
		setp.add(new Person("AAA", 21));
		setp.add(new Person("CCC", 24));
		setp.add(new Person("AAA", 21));
		setp.add(new Person("AAB", 20));

		sop(setp);

		Iterator<Person> itp = setp.iterator();
		while (itp.hasNext())
			sop(itp.next());

		sop("TreeSet Person Demo");

		Set<Person> setp2 = new TreeSet<>(setp);
		Iterator<Person> itp2 = setp2.iterator();
		while (itp2.hasNext())
			sop(itp2.next());

	}

}

class PersonComparator implements Comparator<Person> {

	@Override
	public int compare(Person p1, Person p2) {
		boolean flag = false;

		if (flag)
			return p1.getName().compareTo(p2.getName());
		else
			return p1.getAge() - p2.getAge();
	}
}

class Person implements Comparable<Person> {
	private String name;
	private int age;

	public Person(String name, int age) {
		this.setName(name);
		this.setAge(age);
	}

	@Override
	public int compareTo(Person p) {
		return getAge() == p.getAge() ? getName().compareTo(p.getName()) : getAge() - p.getAge();
	}

	@Override
	public int hashCode() {
		return getName().hashCode() + getAge() << 1;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Person))
			return false;

		Person p = (Person) o;
		return getName() == p.getName() && getAge() == p.getAge();
	}

	@Override
	public String toString() {
		return this.getName() + "-" + this.getAge();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}

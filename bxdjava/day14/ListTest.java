package com.bxd.day14;

import static com.bxd.day00.SimplePrint.sop;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

//增删改插 
//List 有序 可重复
//List 判断元素是否相同以及是否重复，使用equals即可，因为有序所以与hashCode无关
//ArrayList LinkedList Vector
public class ListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> alist = new ArrayList<>();
		alist.add(new Integer(1));
		alist.add(2);
		sop(alist);

		ListIterator<Integer> lit = alist.listIterator();
		while (lit.hasNext()) {
			lit.set(lit.next() + 10);
		}

		Iterator<Integer> it = alist.iterator();
		while (it.hasNext()) {
			sop(it.next());
		}

		ArrayList<Integer> blist = new ArrayList<>();
		blist.addAll(alist);
		blist.add(3);
		sop(blist);

		alist.clear();
		sop(alist);

		sop(blist.contains(2));

		Integer[] i = new Integer[blist.size()];
		blist.toArray(i);
		sop(i);

		Object o[] = blist.toArray();
		sop(o);

		sop(alist.retainAll(blist));

		blist.removeAll(alist);
		alist.clear();

		sop(alist);
		sop(blist);

		sop("LinkedList Test");

		List<Integer> clist = new LinkedList<Integer>();
		clist.add(1);
		clist.add(2);
		((LinkedList<Integer>) clist).addFirst(3);
		clist.add(4);

		((LinkedList<Integer>) clist).removeFirst();
		sop(((LinkedList<Integer>) clist).getFirst());

		for (int k : clist)
			sop(k);

		sop("Vector Test");

		Vector<Integer> vet = new Vector<>();
		vet.addElement(1);
		vet.addElement(2);
		vet.addElement(3);
		sop(vet);

		Enumeration<Integer> en = vet.elements();
		while (en.hasMoreElements())
			sop(en.nextElement());

		sop("StackLinkedList Test");

		StackLinkedList<Integer> slst = new StackLinkedList<>();
		for (int i1 = 0; i1 < 10; i1++)
			slst.myPush(i1);
		sop(slst.size());
		for (Iterator<Integer> it1 = slst.iterator(); it1.hasNext();)
			System.out.print(it1.next());
		System.out.println();
		while (slst.size() > 0)
			System.out.print(slst.myPop());
		System.out.println();

		sop("QueueLinkedList Test");

		QueueLinkedList<Integer> qlst = new QueueLinkedList<>();
		for (int i1 = 0; i1 < 10; i1++)
			qlst.myIn(i1);
		sop(qlst.size());
		for (Iterator<Integer> it1 = qlst.iterator(); it1.hasNext();)
			System.out.print(it1.next());
		System.out.println();
		while (qlst.size() > 0)
			System.out.print(qlst.myOut());
		System.out.println();

		sop("SingleStackLinkedList Test");
		
		SingleStackLinkedList<Integer> sslst = new SingleStackLinkedList<>();
		for (int i1 = 0; i1 < 10; i1++)
			sslst.myPush(i1 % 5);
		sop(sslst.size());
		for (Iterator<Integer> it1 = sslst.iterator(); it1.hasNext();)
			System.out.print(it1.next());
		System.out.println();
		while (sslst.size() > 0)
			System.out.print(sslst.myPop());
		System.out.println();

	}

}

class SingleStackLinkedList<T> extends StackLinkedList<T> {
	public void myPush(T t) {
		if (!lst.contains(t))
			lst.addFirst(t);
		else
			System.out.println(t+" is existed");
		// lst.offerFirst(t);
	}
}

class StackLinkedList<T> {

	LinkedList<T> lst;

	public int size() {
		return lst.size();
	}

	public Iterator<T> iterator() {
		return lst.iterator();
	}

	public StackLinkedList() {
		lst = new LinkedList<>();
	}

	public void myPush(T t) {
		lst.addFirst(t);
		// lst.offerFirst(t);
	}

	public T myPop() {
		return lst.pollFirst();
		// return lst.removeFirst();
		// return lst.peekFirst();
	}
}

class QueueLinkedList<T> {

	LinkedList<T> lst;

	public int size() {
		return lst.size();
	}

	public Iterator<T> iterator() {
		return lst.iterator();
	}

	public QueueLinkedList() {
		lst = new LinkedList<>();
	}

	public void myIn(T t) {
		lst.addLast(t);
	}

	public T myOut() {
		return lst.pollFirst();
	}

}

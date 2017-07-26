package com.bxd.day27;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Ö÷°å
 * 
 * @author Chain
 *
 */
public class MotherBoard {

	private Properties p;

	public MotherBoard(Properties p) {
		this.p = p;
	}

	@SuppressWarnings("unchecked")
	public void work() throws Exception {
		int size = p.size();
		System.out.println("init PCIs");
		System.out.println("find PCIs " + size);
		for (int i = 0; i < size; i++) {
			String s = p.getProperty("PCI" + i);
			Class<? extends PCI> c = (Class<? extends PCI>) Class.forName(s);
			Method m = c.getMethod("work", null);
			Object o = c.newInstance();
			m.invoke(o, null);
		}

		if (size == 0)
			System.out.println("No PCI");
		else
			System.out.println("PCI OK");
	}
}

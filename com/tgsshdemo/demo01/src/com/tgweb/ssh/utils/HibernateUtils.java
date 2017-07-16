package com.tgweb.ssh.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateUtils {

	private SessionFactory sessionFactory;

	private static HibernateUtils instance;

	public static HibernateUtils getInstance() {
		if (instance == null)
			instance = new HibernateUtils();
		return instance;
	}

	private HibernateUtils() {

	}

	public SessionFactory getSessionFactory() {
		sessionFactory = (SessionFactory) SpringUtils.getInstance().getApplicationContext().getBean("sessionFactory");
		return sessionFactory;
	}

	public Session getSession() {
		// 获得与当前线程绑定的Session
		return getSessionFactory().getCurrentSession();
	}
}

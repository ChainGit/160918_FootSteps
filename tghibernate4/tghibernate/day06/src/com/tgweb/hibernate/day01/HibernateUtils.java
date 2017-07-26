package com.tgweb.hibernate.day01;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtils {

	private SessionFactory sessionFactory;

	private static HibernateUtils instance;

	public static HibernateUtils getInstance() {
		if (instance == null)
			instance = new HibernateUtils();
		return instance;
	}

	public SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			Configuration configuration = new Configuration().configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
					.buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
		return sessionFactory;
	}

	public Session getSession() {
		// getCurrentSession与线程绑定，需要配置文件
		return getSessionFactory().getCurrentSession();
	}

}

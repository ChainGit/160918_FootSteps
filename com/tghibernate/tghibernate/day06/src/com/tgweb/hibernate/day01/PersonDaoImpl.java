package com.tgweb.hibernate.day01;

import org.hibernate.Session;

public class PersonDaoImpl implements PersonDao {

	@Override
	public void save(Person person) {
		Session session = HibernateUtils.getInstance().getSession();
		System.out.println(session.hashCode());
		session.save(person);
	}

}

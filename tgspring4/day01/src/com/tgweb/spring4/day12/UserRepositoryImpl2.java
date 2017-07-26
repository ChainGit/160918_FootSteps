package com.tgweb.spring4.day12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "userRepository2")
public class UserRepositoryImpl2 implements UserRepository {

	private UserObject UserObject;

	@Autowired(required = false)
	private UserObject2 UserObject2;

	@Autowired(required = false)
	public void setUserObject2(UserObject2 userObject2) {
		UserObject2 = userObject2;
	}

	@Autowired
	public void setUserObject(UserObject userObject) {
		UserObject = userObject;
	}

	@Override
	public void insert() {
		System.out.println("UserRepository2 insert..");
		UserObject.show();
		try {
			UserObject2.show();
		} catch (Exception e) {
			System.out.println(UserObject2);
		}
	}

}

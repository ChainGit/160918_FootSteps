package com.tgweb.spring4.day11;

import org.springframework.stereotype.Repository;

@Repository(value = "userRepository")
public class UserRepositoryImpl implements UserRepository {

	@Override
	public void insert() {
		System.out.println("UserRepository insert..");
	}

}

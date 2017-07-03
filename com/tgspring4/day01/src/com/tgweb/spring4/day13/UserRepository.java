package com.tgweb.spring4.day13;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends BaseRepository<User> {

	@Override
	public void insert() {
		System.out.println("UserRepository insert..");
	}

}

package com.tgweb.spring4.day13;

import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User> {

	@Override
	public void save() {
		System.out.println("UserService save..");
		baseRepository.insert();
	}

}

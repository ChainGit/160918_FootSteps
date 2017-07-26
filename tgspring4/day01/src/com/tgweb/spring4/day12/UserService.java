package com.tgweb.spring4.day12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	@Qualifier(value = "userRepository")
	private UserRepository userRepository;

	@Autowired
	// @Qualifier(value = "userRepository2")
	public void setUserRepository(@Qualifier(value = "userRepository2") UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void save() {
		System.out.println("UserService save..");
		userRepository.insert();
	}
}

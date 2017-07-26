package com.tgweb.spring4.day12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	public void execute() {
		System.out.println("UserController execute..");
		userService.save();
	}

}

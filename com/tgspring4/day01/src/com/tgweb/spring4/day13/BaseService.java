package com.tgweb.spring4.day13;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<T> {

	@Autowired
	protected BaseRepository<T> baseRepository;

	public void save() {
		System.out.println("BaseService save..");
		baseRepository.insert();
	}

}

package com.tgweb.ssh.service;

import java.util.List;
import java.util.Map;

public interface UserCashierService {

	public void cash(Long userId, List<Long> bookIds);

	public void cash(Long userId, Map<Long, Integer> books);
}

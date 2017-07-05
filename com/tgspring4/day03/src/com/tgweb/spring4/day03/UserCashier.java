package com.tgweb.spring4.day03;

import java.util.List;
import java.util.Map;

public interface UserCashier {

	public void cash(Long userId, List<Long> bookIds);

	public void cash(Long userId, Map<Long, Integer> books);
}

package com.tgweb.spring4.day02;

import java.util.List;

public interface UserCashier {

	public void cash(Long userId, List<Long> bookIds);
}

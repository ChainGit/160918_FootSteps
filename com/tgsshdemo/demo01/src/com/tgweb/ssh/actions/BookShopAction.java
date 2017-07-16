package com.tgweb.ssh.actions;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import com.tgweb.ssh.service.UserCashierService;
import com.tgweb.ssh.utils.HibernateUtils;

public class BookShopAction {

	private UserCashierService userCashierService;

	public UserCashierService getUserCashierService() {
		return userCashierService;
	}

	public void setUserCashierService(UserCashierService userCashierService) {
		this.userCashierService = userCashierService;
	}

	public String bookShopUi() {
		System.out.println("hello");
		return "success";
	}

	@Transactional
	public String txbookShopBuy() {
		List<Long> bookIds = new ArrayList<>();
		bookIds.add(new Long(1));
		bookIds.add(new Long(2));
		Transaction transaction = HibernateUtils.getInstance().getSession().beginTransaction();
		try {
			userCashierService.cash(1L, bookIds);
			transaction.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "success";
	}

}

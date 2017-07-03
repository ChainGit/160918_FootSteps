package com.tgweb.spring4.day06;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class SpringTest {

	@Test
	public void test() throws SQLException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-properties.xml");
		DataSource ds = (ComboPooledDataSource) ctx.getBean("dataSource");
		System.out.println(ds.getConnection());
	}

}

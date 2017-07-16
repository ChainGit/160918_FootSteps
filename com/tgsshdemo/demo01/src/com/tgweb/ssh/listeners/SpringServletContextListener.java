package com.tgweb.ssh.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;

import com.tgweb.ssh.utils.SpringUtils;

public class SpringServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext sc = sce.getServletContext();
		String configFile = sc.getInitParameter("contextConfigLocation");

		ApplicationContext ctx = SpringUtils.getInstance().getApplicationContext(configFile);

		sc.setAttribute("spring_ctx_root", ctx);
	}

}

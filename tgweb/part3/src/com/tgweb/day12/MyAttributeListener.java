package com.tgweb.day12;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class
 * MyServletContextAttributeListener
 *
 */
public class MyAttributeListener
		implements ServletContextAttributeListener, ServletRequestAttributeListener, HttpSessionAttributeListener {

	/**
	 * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
	 */
	public void attributeAdded(ServletContextAttributeEvent scab) {
		// TODO Auto-generated method stub
		System.out.println(this + ":ServletContextAttributeListener-attributeAdded");
	}

	/**
	 * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
	 */
	public void attributeRemoved(ServletContextAttributeEvent scab) {
		// TODO Auto-generated method stub
		System.out.println(this + ":ServletContextAttributeListener-attributeRemoved");
	}

	/**
	 * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
	 */
	public void attributeReplaced(ServletContextAttributeEvent scab) {
		// TODO Auto-generated method stub
		System.out.println(this + ":ServletContextAttributeListener-attributeReplaced");
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		System.out.println(this + ":HttpSessionAttributeListener-attributeAdded");
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		System.out.println(this + ":HttpSessionAttributeListener-attributeRemoved");
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		System.out.println(this + ":HttpSessionAttributeListener-attributeReplaced");
	}

	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		System.out.println(this + ":ServletRequestAttributeListener-attributeAdded");
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		System.out.println(this + ":ServletRequestAttributeListener-attributeRemoved");
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		System.out.println(this + ":ServletRequestAttributeListener-attributeReplaced");
	}

}

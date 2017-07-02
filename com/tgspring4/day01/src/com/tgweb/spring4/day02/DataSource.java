package com.tgweb.spring4.day02;

import java.util.Properties;

public class DataSource {

	private Properties properties;

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DataSource [properties=").append(properties).append("]");
		return builder.toString();
	}

	public DataSource() {
	}

	public DataSource(Properties properties) {
		super();
		this.properties = properties;
	}

}

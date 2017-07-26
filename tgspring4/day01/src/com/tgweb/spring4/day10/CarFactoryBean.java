package com.tgweb.spring4.day10;

import org.springframework.beans.factory.FactoryBean;

import com.tgweb.spring4.day08.Car;

public class CarFactoryBean implements FactoryBean<Car> {

	private String carName;

	public void setName(String carName) {
		this.carName = carName;
	}

	@Override
	public Car getObject() throws Exception {
		return new Car(carName);
	}

	@Override
	public Class<?> getObjectType() {
		return Class.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}

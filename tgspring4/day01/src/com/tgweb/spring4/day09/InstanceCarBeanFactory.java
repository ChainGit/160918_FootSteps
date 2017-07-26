package com.tgweb.spring4.day09;

import java.util.LinkedHashMap;
import java.util.Map;

import com.tgweb.spring4.day08.Car;

public class InstanceCarBeanFactory extends InstanceBeanFactory {

	private Map<String, Car> cars;

	public void initCars() {
		cars = new LinkedHashMap<>();
		cars.put("CarA", new Car("CarA"));
		cars.put("CarB", new Car("CarB"));
		cars.put("CarC", new Car("CarC"));
	}

	public InstanceCarBeanFactory() {
		initCars();
	}

	public Car getCar(String carName) {
		return cars.get(carName);
	}
}

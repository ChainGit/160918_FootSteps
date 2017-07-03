package com.tgweb.spring4.day09;

import java.util.LinkedHashMap;
import java.util.Map;

import com.tgweb.spring4.day08.Car;

public class StaticCarBeanFactory extends StaticBeanFactory {

	private static Map<String, Car> cars;

	static {
		cars = new LinkedHashMap<>();
		cars.put("Car1", new Car("Car1"));
		cars.put("Car2", new Car("Car2"));
		cars.put("Car3", new Car("Car3"));
	}

	private StaticCarBeanFactory() {
	}

	public static Car getCar(String carName) {
		return cars.get(carName);
	}

}

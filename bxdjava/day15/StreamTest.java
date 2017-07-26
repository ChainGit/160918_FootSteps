package com.bxd.day15;

import java.util.ArrayList;
import java.util.List;

public class StreamTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> nums = new ArrayList<>();
		nums.add(null);
		nums.add(1);
		nums.add(null);
		nums.add(3);
		nums.add(null);
		System.out.println(nums.stream().filter(num -> num != null).count());
		
		
	}

}

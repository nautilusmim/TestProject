package org.test;

import java.util.Arrays;
import java.util.Date;

public class ArraysTest {

	public static void main(String[] args) {
		long[] arr = {1, 3, 5, 7};
		String arrStr = Arrays.toString(arr);
		System.out.println(arrStr);
		
		System.out.println(new Date());
		Long a = 10000L;
		Long b = 10000L;
		if(a.longValue() == b.longValue()) {
			System.out.println(a == b);
		}
	}

}

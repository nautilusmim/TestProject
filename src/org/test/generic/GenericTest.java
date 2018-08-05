package org.test.generic;

import java.util.*;

public class GenericTest {
	
	public static void printListData(List<?> data) {
		System.out.println("data: " + data.get(0));
	}
	
	public static void printUpperData(List<? extends Number> data) {
		System.out.println("data: " + data.get(0));
	}
	
	public static void printLowerData(List<? super Number> data) {
		System.out.println("data: " + data.get(0));
	}

	public static void main(String[] args) {
		List<Integer> li = new ArrayList<Integer>();
		li.add(20);
		
		List<String> ls = new ArrayList<String>();
		ls.add("test");
		
		List<Number> ln = new ArrayList<Number>();
		ln.add(200);
		
		printListData(li);
		printListData(ls);
		printListData(ln);
		
		printUpperData(li);
//		printUpperData(ls);
		printUpperData(ln);
		
		List<Object> lo = new ArrayList<Object>();
		lo.add(new Object());
		
//		printLowerData(li);
//		printLowerData(ls);
		printLowerData(ln);
		printLowerData(lo);
	}

}

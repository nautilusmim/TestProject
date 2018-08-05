package org.test.generic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class GenericTest2 {

	public static void main(String[] args) {
		// 测试使用反射添加不合法泛型实参
		List<Integer> li = new ArrayList<Integer>();
		
		li.add(20);
//		li.add('a');
		
		Class<? extends List> clazz = li.getClass();
		Method m;
		try {
			m = clazz.getMethod("add", Object.class);
			try {
				m.invoke(li, "123");
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(li.get(0) + li.get(1)); // can't cast String to Integer
	}

}

package org.test;

import java.util.Arrays;

public class RegexTest {

	public static void main(String[] args) {
		String key = " a2. a. w30.55.b";
		String[] subKey = key.split("\\.");
		for(String tmp : subKey) {
			System.out.println(tmp);
		}
		Long[] ids = new Long[] { 100L, 101L, 102L, 103L };
		String idStr = Arrays.toString(ids).replaceAll("\\[", "").replaceAll("\\]", "");
		System.out.println(idStr);
	}

}

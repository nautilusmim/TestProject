package org.test.generic;

public class MaximumTest {
	
	public static <T extends Comparable<T>> T max(T x, T y, T z) {
		T t = x;
		
		if(t.compareTo(y) < 0) {
			t = y;
		}
		if(t.compareTo(z) < 0) {
			t = z;
		}
		
		return t;
	}

	public static void main(String[] args) {
		System.out.printf("%d %d %d, max number is %d.\n", 1, 2, 3, max(1, 2, 3));
		System.out.printf("%.1f %.1f %.1f, max number is %.1f.\n", 1.2, 2.1, 3.0, max(1.2, 2.1, 3.0));
		System.out.printf("%s %s %s, max string is %s.", "abc", "Abc", "12", max("abc", "Abc", "12"));
	}

}

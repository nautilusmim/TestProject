package org.test.generic;

public class GenericMethodTest {

	public static <T> void printArray(T[] array) {
		if(0 == array.length) {
			// exit this proc
		} else {
			for(int i = 0; i < array.length; i ++) {
				System.out.printf("%s ", array[i]);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		Integer[] intArr = {1, 2, 3};
		Double[] doubleArr = {1.1, 2.2, 3.3};
		Character[] charArr = {'H', 'E', 'L', 'L', 'O'};
		
		printArray(intArr);
		printArray(doubleArr);
		printArray(charArr);
	}
	
}

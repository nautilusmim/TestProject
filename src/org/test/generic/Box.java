package org.test.generic;

public class Box<T> {
	
	private T t;
	
	public void add(T t) {
		this.t = t;
	}
	
	public T get() {
		return this.t;
	}

	public static void main(String[] args) {
		Box<Integer> bi = new Box<Integer>();
		bi.add(20);
		
		Box<String> bs = new Box<String>();
		bs.add("test generic class");
		
		System.out.println(bi.get());
		System.out.println(bs.get());
	}

}

package org.test.multithread;

public class TestThread {

	public static void main(String[] args) {
		/*1
		RunnableDemo demo1 = new RunnableDemo("Thread-1");
		demo1.start();
		
		RunnableDemo demo2 = new RunnableDemo("Thread-2");
		demo2.start();
		*/
		
		/*2*/
		ThreadDemo demo1 = new ThreadDemo("Thread-1");
		demo1.start();
		ThreadDemo demo2 = new ThreadDemo("Thread-2");
		demo2.start();
		
	}

}

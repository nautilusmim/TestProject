package org.test.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 多线程第三种基本实现方式 - 带有返回值
 */
public class CallableDemo implements Callable<Integer> {
	
	public static void main(String[] args) throws Exception {
		CallableDemo cd = new CallableDemo();
		FutureTask<Integer> ft = new FutureTask<Integer>(cd);
		
		for(int i = 0; i < 100; i ++) {
			System.out.println(Thread.currentThread().getName() + "(m) " + i);
			if(20 == i) {
				new Thread(ft, "haveReturnThread").start();
			}
		}
		
		System.out.println(ft.get());
	}
	

	@Override
	public Integer call() throws Exception {
		Integer i = 0;
		
		for(; i < 100; i ++) {
			System.out.println(Thread.currentThread().getName() + "(t) " + i);
		}
		
		return i;
	}
	
}

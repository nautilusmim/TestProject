package org.test.multithread;

// 测试Thread类的一些常用方法
public class ThreadClassDemo {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + " is starting...");
		
		// 显示任务一
		DisplayMessage hello = new DisplayMessage("Hello");
		Thread thread1 = new Thread(hello);
		thread1.setDaemon(true);
		thread1.setName("hello");
		System.out.println("Start hello thread.");
		thread1.start();
		
		// 显示任务二
		DisplayMessage bye = new DisplayMessage("Goodbye");
		Thread thread2 = new Thread(bye);
		thread2.setDaemon(true);
		thread2.setPriority(Thread.MIN_PRIORITY);
		System.out.println("Start goodbye thread.");
		thread2.start();
		
		// 猜数字任务一
		GuessANumber thread3 = new GuessANumber(57);
		System.out.println("Start thread3 thread.");
		thread3.start();
		try {
			thread3.join();
		} catch (InterruptedException e) {
			System.out.println("Thread interrupted.");
		}
		
		// 猜数字任务二
		GuessANumber thread4 = new GuessANumber(75);
		System.out.println("Start thread4 thread.");
		thread4.start();
		
		System.out.println(Thread.currentThread().getName() + " is over...");
	}

}

package org.test.multithread;

/**
 * 多线程第二种基础实现方式(class Thread)
 */
public class ThreadDemo extends Thread {
	
	private Thread t;
	private String threadName;
	
	public ThreadDemo(String threadName) {
		this.threadName = threadName;
		System.out.println("Creating " + this.threadName);
	}
	
	public void start() {
		System.out.println("Starting " + threadName);
		if(null == t) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
	
	@Override
	public void run() {
		System.out.println("Running " + this.threadName);
		
		try {
			int i = 4;
			do {
				System.out.println("Thread:" + threadName + ", " + i);
				i --;
				Thread.sleep(50);
			} while (i > 0);
		} catch (InterruptedException e) {
			System.out.println("Thread:" + threadName + " interrupted");
		}
		
		System.out.println("Thread:" + threadName + " exit");
	}

}

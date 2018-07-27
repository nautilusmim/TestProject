package org.test.multithread;

/**
 * 多线程第一种基础实现方式(interface Runnable)
 */
public class RunnableDemo implements Runnable {
	
	private Thread t;
	private String threadName;
	
	public RunnableDemo(String threadName) {
		this.threadName = threadName;
		System.out.println("Creating " + this.threadName);
	}

	public void run() {
		System.out.println("Running " + threadName);
		try {
			for(int i = 4; i > 0; i --) {
				System.out.println("Thread:" + threadName + ", " + i);
				Thread.sleep(50);
			}
		} catch (InterruptedException e) {
			System.out.println("Thread:" + threadName + " interrupted");
		}
		System.out.println("Thread:" + threadName + " exit");
	}
	
	public void start() {
		System.out.println("Starting " + threadName);
		if(null == t) {
			t = new Thread(this, threadName);
			t.start();
		}
	}

}

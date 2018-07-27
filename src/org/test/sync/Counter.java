package org.test.sync;

public class Counter implements Runnable {
	
	private int count;
	
	public Counter() {
		count = 0;
	}
	
	public void countAdd() {
		synchronized(this) {
			try {
				for(int i = 0; i < 5; i ++) {
					System.out.println(Thread.currentThread().getName() + ":" + (count ++));
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void printCount() {
		try {
			for(int i = 0; i < 5; i ++) {
				System.out.println(Thread.currentThread().getName() + " count:" + count);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		if("A".equals(threadName)) {
			countAdd();
		} else if("B".equals(threadName)) {
			printCount();
		}
	}

}

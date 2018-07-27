package org.test.sync;

public class MyRunnable implements Runnable {
	
	private int total;

	@Override
	public void run() {
		synchronized(this) {
			System.out.println("synchronized in: before notify");
			for(int i = 0; i < 10; i ++) {
				total += i;
			}
			notify();
			System.out.println("synchronized in: after notify");
		}
		
		System.out.println("synchronized out");
	}
	
	public int getTotal() {
		return total;
	}

}

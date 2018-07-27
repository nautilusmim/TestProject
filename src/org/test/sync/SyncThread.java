package org.test.sync;

public class SyncThread implements Runnable {
	
	private static int count;

	public int getCount() {
		return count;
	}
	
	public SyncThread() {
		count = 0;
	}
	
	/* == static class method
	public synchronized static void syncMethod() {
		try {
			for(int i = 0; i < 5; i ++) {
				System.out.println(Thread.currentThread().getName() + ":" + (count ++));
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	*/
	
	/* == class */
	public static void syncMethod() {
		synchronized(SyncThread.class) {
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

	@Override
	public void run() {
		syncMethod();
	}
	
	/* == method
	public synchronized void run() {
		try {
			for(int i = 0; i < 5; i ++) {
				System.out.println(Thread.currentThread().getName() + ":" + (count ++));
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	*/
	
	/* == code scriptlet
	public void run() {
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
	*/

}

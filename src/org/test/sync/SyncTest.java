package org.test.sync;

public class SyncTest {

	public static void main(String[] args) {
		/*
		SyncThread sync = new SyncThread();
		
		Thread t1 = new Thread(sync, "syncThread1");
		Thread t2 = new Thread(sync, "syncThread2");
		t1.start();
		t2.start();
		*/
		
		/**/
		Thread t1 = new Thread(new SyncThread(), "syncThread1");
		Thread t2 = new Thread(new SyncThread(), "syncThread2");
		t1.start();
		t2.start();
		
		/*
		Counter counter = new Counter();
		Thread t1 = new Thread(counter, "A");
		Thread t2 = new Thread(counter, "B");
		t1.start();
		t2.start();
		*/
		
		/*
		Account account = new Account("Leopards", 10000.0f);
		AccountOperator operator = new AccountOperator(account);
		final int THREAD_NUM = 5;
		Thread[] threads = new Thread[THREAD_NUM];
		for(int i = 0; i < THREAD_NUM; i ++) {
			threads[i] = new Thread(operator, "Thread" + i);
			threads[i].start();
		}
		*/
	}

}

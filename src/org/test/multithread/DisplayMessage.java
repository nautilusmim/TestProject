package org.test.multithread;

// 任务一：显示消息
public class DisplayMessage implements Runnable {
	
	private String message;
	
	public DisplayMessage(String message) {
		this.message = message;
	}

	@Override
	public void run() {
		while(true) {
			System.out.println(message);
		}
	}
	
}

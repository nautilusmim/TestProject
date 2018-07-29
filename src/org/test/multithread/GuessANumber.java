package org.test.multithread;

// 任务二：猜100以内的两位数
public class GuessANumber extends Thread {
	
	private Integer number;
	
	public GuessANumber(Integer number) {
		this.number = number;
	}

	@Override
	public void run() {
		int guess;
		int counter = 0;
		
		do {
			guess = (int)(Math.random() * 100 + 1);
			System.out.println("** guess number " + guess + " on " + counter + " time.**");
			counter ++;
		} while(guess != number);
		System.out.println("** you got the right number on " + counter + " times.**");
	}

}

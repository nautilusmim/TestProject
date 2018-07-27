package org.test.sync;

public class Account {
	
	private String name;
	
	private float amount;
	
	public Account(String name, float amount) {
		this.name = name;
		this.amount = amount;
	}
	
	public float deposit(float issue) {
		this.amount += issue;
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return this.amount;
	}
	
	public float withdraw(float issue) {
		this.amount -= issue;
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return this.amount;
	}
	
	public float getBalance() {
		return this.amount;
	}

}

package org.test.sync;

public class AccountOperator implements Runnable {
	
	private Account account;
	
	public AccountOperator(Account account) {
		this.account = account;
	}

	@Override
	public void run() {
		synchronized(account) {
			account.deposit(500);
			account.withdraw(500);
			System.out.println(Thread.currentThread().getName() + ":" + account.getBalance());
		}
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}

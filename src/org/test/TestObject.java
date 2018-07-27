package org.test;

import org.test.sync.MyRunnable;

public class TestObject implements Cloneable {
	
	private int guid;

	public int getGuid() {
		return guid;
	}

	public void setGuid(int guid) {
		this.guid = guid;
	}

	@Override
	public boolean equals(Object obj) {
		if(null == obj || !(obj instanceof TestObject)) {
			return false;
		}
		if(((TestObject)obj).getGuid() == this.getGuid()) {
			return true;
		}
		return false;
	}
	
	public int hashCode() {
		int result = 17;
		
		result = result * 31 + this.getGuid();
		
		return result;
	}
	
	public static void main(String[] args) {
		TestObject obj1 = new TestObject();
		obj1.setGuid(1);
		TestObject obj2 = new TestObject();
		obj2.setGuid(1);
		// equals
		System.out.println(obj1.equals(obj2));
		// hashCode
		System.out.println(obj1.hashCode() == obj2.hashCode());
		
		// wait|notify
		MyRunnable r = new MyRunnable();
		Thread t = new Thread(r);
		t.start();
		
		synchronized(r) {
			try {
				System.out.println("before r invoking wait");
				r.wait();
				System.out.println("after r invoking wait");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(r.getTotal());
		}
	}

}

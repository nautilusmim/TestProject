package org.test.inherit;

public class Parent {
	
	protected int varInt;
	
	protected String varStr;
	
	public Parent() {
	}
	
	public Parent(int varInt, String varStr) {
		this.varInt = varInt;
		this.varStr = varStr;
	}
	
	protected String getProps() {
		return "ID: " + varInt + ", DESC: " + varStr; 
	}
	
	protected static String getProp(String name) {
		if("id".equals(name)) {
			return "varInt";
		} else {
			return "varStr";
		}
	}

}

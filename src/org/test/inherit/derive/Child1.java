package org.test.inherit.derive;

import org.test.inherit.Parent;

public class Child1 extends Parent {
	
	// different package, parent-child
	public static void main(String[] args) {
		Child1 child = new Child1();
		child.varInt = 1;
		child.varStr = "test";
		
		System.out.println(child.getProps());
		
		Parent parent = new Parent();
//		System.out.println(parent.getProps()); // Error: The method getProps() from the type Parent is not visible
		System.out.println(getProp("id"));
	}

}

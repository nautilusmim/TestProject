package org.test;

public class DataTypeCast {

	public static void main(String[] args) {
		System.out.println(NumberUtil.formatNumberByCutNOComma(1234.44, 2));
		Double d = new Double(NumberUtil.formatNumberByCutNOComma(1234.44, 2));
		System.out.println(d);
	}

}

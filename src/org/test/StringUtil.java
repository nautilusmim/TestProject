/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * 版权声明 v1.0
 *
 * 本程�?(包括源代码和二进制码)的知识产�?(包括但不限于著作权�?�专利申请权、商标权、专有技�?)的所有权、使用权�?
 * 转让权以及收益等�?切权利均属于ISoftstone�?有�??
 * =================================================================================org.test==*/
package org.test;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.apache.commons.lang.StringUtils;

/**
 * Class description goes here.
 * 
 * @author <a href="mailto:xttao@isoftstone.com">xttao</a>
 * @version $Id: StringUtil.java,v0.1 2007-1-22 上午09:48:52 xttao Exp$
 */
public abstract class StringUtil {

	/**
	* @Title: isNotBlank 
	* @Description: TODO(字符串非空判�?  不为空：true  为空:false) 
	* @param @param str
	* @param @return     
	* @return   
	* @throws
	 */
//	public static boolean isNotBlank(String str) {
//		return StringUtils.isNotEmpty(str);
//	}

	/**
	     * 得到�?个字符串的真正长度，如果字符串包含汉字，则一个汉字当作两个字�?
	     * 
	     * @param Value
	     *                字符�?
	     * @return 字符串真正长�?
	     */
	public static int getTrueSizeFromString(String Value) {
		if (Value == null) {
			return 0;
		}
		int intLen = Value.getBytes().length;
		return intLen;

	}

	/**
	 * 正则表达式匹�?
	 * @param regex 表达�?
	 * @param str 要匹配得字符
	 * @return
	 */
	public static boolean regExMacher(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}

	public static String substringBetween(String str, String beginStr, String endStr) {
		String s = null;
		try {
			int start = str.indexOf(beginStr);
			int end = str.indexOf(endStr);
			if (start != -1) {
				if (end == -1) {
					end = str.length();
					s = str.substring(start + beginStr.length(), end);
				}
				else {
					s = str.substring(start + beginStr.length(), end);
				}
			}
		} catch (Exception err) {

		}
		return s;
	}

	/**
	 * 拼接字符串方�?
	 * 		add by weichen2
	 * @param longDates 长整型数�?
	 * @param sign 拼接�?
	 * @return
	 */
	public static String linkString(Long[] longDates, String sign) {
		StringBuffer tempSB = new StringBuffer();
		if (longDates.length == 0)
		{
			return null;
		}
		for (int i = 0; i < longDates.length; i++)
		{
			if (longDates[i] != null)
				tempSB.append(sign + longDates[i]);
		}
		String strReturn = "";
		if (tempSB.length() > sign.length())
		{
			strReturn = tempSB.substring(sign.length());
		}
		return strReturn;
	}

	/**
	 * 拼接字符串方�?
	 * 		add by sunpf
	 * @param longDates String型数�?
	 * @param sign 拼接�?
	 * @return
	 */
	public static String linkString(String[] longDates, String sign) {
		StringBuffer tempSB = new StringBuffer();
		if (longDates.length == 0)
		{
			return null;
		}
		for (int i = 0; i < longDates.length; i++)
		{
			if (longDates[i] != null)
				tempSB.append(sign + longDates[i]);
		}
		String strReturn = "";
		if (tempSB.length() > sign.length())
		{
			strReturn = tempSB.substring(sign.length());
		}
		return strReturn;
	}

	/**
	* 拼接字符串方�?+改进
	* 		add by ypxu
	* @param longDates 长整型数�?
	* @param sign 拼接�?
	* @return
	*/
	public static String linkString(Long[] longDates, String sign, int beginIndex, int endIndex) {
		StringBuffer tempSB = new StringBuffer();
		if (endIndex > longDates.length)
			endIndex = longDates.length;
		if (beginIndex < 0)
			beginIndex = 0;
		if (endIndex <= beginIndex)
			return "";
		for (int i = beginIndex; i < endIndex; i++)
		{
			tempSB.append(longDates[i] + sign);
		}
		return tempSB.substring(0, tempSB.length() - sign.length());
	}

	/**
	* 拼接字符串方�?+改进
	* 		add by ypxu
	* @param longDates 长整型数�?
	* @param sign 拼接�?
	* @return
	*/
	public static String linkString(Long[] longDates, String sign, int beginIndex) {
		StringBuffer tempSB = new StringBuffer();
		if (beginIndex < 0)
			beginIndex = 0;
		for (int i = beginIndex; i < longDates.length; i++)
		{
			tempSB.append(longDates[i] + sign);
		}
		return tempSB.substring(0, tempSB.length() - sign.length());
	}

	/**
	 * 根据字符集类型，得到�?个字符串的真正长�?
	 * @author dongwenbin
	 * @date 2013-12-19
	 * @param Value  字符�?
	 * @param charsetName  字符集类�?
	 * @return 字符串真正长�?
	 * @throws UnsupportedEncodingException 
	 * 
	 * @see getTrueSizeFromString(String Value)
	 * 
	 */
	public static int getTrueSizeFromString(String Value, String charsetName)
			throws UnsupportedEncodingException {
		if (Value == null) {
			return 0;
		}
		int intLen = Value.getBytes(charsetName).length;
		return intLen;

	}

	public static int getTrueSizeFromStringUTF8(String Value) throws UnsupportedEncodingException {
		return getTrueSizeFromString(Value, "UTF-8");
	}

	public static int getTrueSizeFromStringUTF8(String Value, String ecoding) throws UnsupportedEncodingException {
		return getTrueSizeFromString(Value, ecoding);
	}

	/*
	public static void main(String[] args){
	String s = StringUtil.substringBetween("com.ibm.db2.jcc.c.SqlException: DB2 SQL error: SQLCODE: -438, SQLSTATE: 70001, SQLERRMC: \0类型为[123]的凭证要求贷方必有现金类科目! \1 ", "C:", "\1");
	System.out.println(s);
	}
	*/
	/**
	 * 根据文件路径中的文件后缀名返回文件名�?
	 * 主要用于解决txt/jpg后缀的文件直接打�?的问�?
	 * @param URL
	 * @return filename 文件名称
	 */
	public static String fileNameVerify(String URL, String fileName) {
		String fName = "";
		URL = URL.toLowerCase();
		if (URL.indexOf("txt") != -1 || URL.indexOf("jpg") != -1) {
			fName = fileName + "(右击目标另存�?)";
		} else {
			fName = fileName;
		}
		return fName;
	}

	/**
	 * 注释�?	获取�?大合同号
	 * 时间�?	2016-11-15下午4:09:08
	 * 创建人：	Janus
	 */
	public static String getMaxSercontract(List<String> sercontractList) {
		Integer max = 0;
		String maxSerconton = "";
		for (String contract : sercontractList) {
			Integer a = Integer.parseInt(contract);
			if (max < a) {
				max = a;
				maxSerconton = contract;
			}
		}
		return maxSerconton;
	}

}
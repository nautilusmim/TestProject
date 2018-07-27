/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * 版权声明 v1.0
 *
 * 本程�?(包括源代码和二进制码)的知识产�?(包括但不限于著作权�?�专利申请权、商标权、专有技�?)的所有权、使用权�?
 * 转让权以及收益等�?切权利均属于ISoftstone�?有�??
 * =================================================================================org.test==*/
package org.test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class description goes here.
 * 
 * @author <a href="mailto:xttao@isoftstone.com">xttao</a>
 * @version $Id: NumberUtil.java,v0.1 2007-1-21 下午11:39:10 xttao Exp$
 */
public class NumberUtil {
	/**
	 * 格式化数字，例如�?12345转化�?12,345 //按照四舍五入
	 * 
	 * @param dValue
	 *            被格式化的数�?
	 * @param iScale
	 *            小数点后保留位数,不足�?0
	 * @return
	 */
	public static String formatNumber(double dValue, int iScale) {
		if (Double.isNaN(dValue)) {
			dValue = 0.0d;
		}
		DecimalFormat df = null;
		StringBuffer sPattern = new StringBuffer(",##0");
		if (iScale > 0) {
			sPattern.append(".");
			for (int i = 0; i < iScale; i++) {
				sPattern.append("0");
			}
		}
		df = new DecimalFormat(sPattern.toString());
		//df.setRoundingMode(RoundingMode.HALF_UP);
		String str = df.format(dValue);
		return str;

	}

	/**
	 * 格式化数字，按照截位
	 * 
	 * @param dValue
	 *            被格式化的数�?
	 * @param iScale
	 *            小数点后保留位数,不足�?0
	 * @return
	 */
	public static String formatNumberByCut(double dValue, int iScale) {
		if (Double.isNaN(dValue)) {
			dValue = 0.0d;
		}
		DecimalFormat df = null;
		StringBuffer sPattern = new StringBuffer(",##0");
		String str = null;
		if (iScale >= 0) {
			sPattern.append(".");
			for (int i = 0; i <= iScale + 1; i++) {
				sPattern.append("0");
			}
		}
		df = new DecimalFormat(sPattern.toString());
		str = df.format(dValue);
		if (iScale == 0) {
			str = str.substring(0, str.length() - 3);
		} else {
			str = str.substring(0, str.length() - 2);
		}
		return str;
	}

	/**
	 * 格式化数字，按照截位(格式化后的数据没有�?�号)
	 * 
	 * @param dValue
	 *            被格式化的数�?
	 * @param iScale
	 *            小数点后保留位数,不足�?0
	 * @return
	 */
	public static String formatNumberByCutNOComma(double dValue, int iScale) {
		if (Double.isNaN(dValue)) {
			dValue = 0.0d;
		}
		DecimalFormat df = null;
		StringBuffer sPattern = new StringBuffer("##0");
		String str = null;
		if (iScale >= 0) {
			sPattern.append(".");
			for (int i = 0; i < iScale + 1; i++) {
				sPattern.append("0");
			}
		}
		df = new DecimalFormat(sPattern.toString());
		str = df.format(dValue);
		if (iScale == 0) {
			str = str.substring(0, str.length() - 2);
		} else {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	/**
	 * 格式化数字，例如�?12345.32转化�?12,345.32
	 * 
	 * @param dValue
	 *            被格式化的数�?
	 * @return
	 */
	public static String formatNumber(double dValue) {
		if (Double.isNaN(dValue)) {
			dValue = 0.0d;
		}
		DecimalFormat df = null;
		StringBuffer sPattern = new StringBuffer(",##0");
		String sValue = String.valueOf(dValue);
		int point = sValue.indexOf(".");
		int iScale = sValue.substring(point, sValue.length() - 1).length();
		if (iScale > 0) {
			sPattern.append(".");
			for (int i = 0; i < iScale; i++) {
				sPattern.append("0");
			}
		}
		df = new DecimalFormat(sPattern.toString());
		String str = df.format(dValue);
		return str;
	}
	/**
	 * 特殊处理解决精度问题，同时进行金额格式化，将后面�?0去掉
	 * @author yqs
	 * 
	 */
	public static String formatNumberOutZero(Double amout, Integer iscale){
		String str =formatNumber(amout,iscale);
		str=subZeroAndDot(str);
		return str;
	}
	/** 
	 * 使用java正则表达式去掉多余的.�?0 
	 * @param s 
	 * @return  
	 */  
	public static String subZeroAndDot(String s){  
		if(s.indexOf(".") > 0){  
			s = s.replaceAll("0+?$", "");//去掉多余�?0  
			s = s.replaceAll("[.]$", "");//如最后一位是.则去�?  
		}  
		return s;  
	}  

	/**
	 * 格式化数字，例如�?12345.32转化�?12,345.32
	 * 
	 * @param dValue
	 *            被格式化的数�?
	 * @return
	 */
	public static String formatNumberNew(double dValue) {
		if (Double.isNaN(dValue)) {
			dValue = 0.0d;
		}
		DecimalFormat df = null;
		StringBuffer sPattern = new StringBuffer(",##0");
		String sValue = cancelScientificAth(String.valueOf(dValue));
		int point = sValue.indexOf(".");
		int iScale = sValue.substring(point, sValue.length() - 1).length();
		if (iScale > 0) {
			sPattern.append(".");
			for (int i = 0; i < iScale; i++) {
				sPattern.append("0");
			}
		}
		df = new DecimalFormat(sPattern.toString());
		String str = df.format(dValue);
		return str;
	}

	/**
	 * double数据保留小数点位�?(四舍五入)，返回字符串
	 * @param value
	 * @param iScale
	 * @return
	 */
	public static String formatPointBits(double value, int iScale) {
		if (Double.isNaN(value)) {
			value = 0.0d;
		}
		DecimalFormat df = null;
		StringBuffer sPattern = new StringBuffer("##0");
		if (iScale > 0) {
			sPattern.append(".");
			for (int i = 0; i < iScale; i++) {
				sPattern.append("0");
			}
		}
		df = new DecimalFormat(sPattern.toString());
		String result = df.format(value);
		return result;
	}

	public static String formatDotNumber(double dValue) {
		if (Double.isNaN(dValue)) {
			dValue = 0.0d;
		}
		DecimalFormat df = null;
		StringBuffer sPattern = new StringBuffer(",##0.00");
		df = new DecimalFormat(sPattern.toString());
		String str = df.format(dValue);
		while (str.endsWith("0") && str.indexOf(".") != str.length() - 3) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	public static String formatNumber(long lValue) {
		return formatNumber((double) lValue, 0);
	}

	/**
	 * 解析格式化的字符串，转化为数值，例如�?12,345.00转化�?12345
	 * 
	 * @param text
	 *            被格式化的数�?
	 * @return
	 */
	public static double parseNumber(String text) {
		int index = text.indexOf(",");
		String sbNumber = "";
		while (index != -1) {
			sbNumber += text.substring(0, index);
			text = text.substring(index + 1, text.length());
			index = text.indexOf(",");
		}
		sbNumber += text;
		return Double.parseDouble(sbNumber);
	}

	/**
	 * 解析格式化的字符串，转化为数值，例如�?12,345.00转化�?12345,增加为空的判�?
	 * 
	 * @param text
	 *            被格式化的数�?
	 * @return
	 */
	public static double parseNumber4Null(String text) {
		if (text == null || text.equals("")) {
			return Double.parseDouble("0");
		}
		int index = text.indexOf(",");
		String sbNumber = "";
		while (index != -1) {
			sbNumber += text.substring(0, index);
			text = text.substring(index + 1, text.length());
			index = text.indexOf(",");
		}
		sbNumber += text;
		return Double.parseDouble(sbNumber);
	}

	/**
	 * 判断字符串是否为数字
	 * @return true：为数字 false：非数字
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static long parseLong(String text) {
		if (text != null) {
			int index = text.indexOf(",");
			String sbNumber = "";
			while (index != -1) {
				sbNumber += text.substring(0, index);
				text = text.substring(index + 1, text.length());
				index = text.indexOf(",");
			}
			sbNumber += text;
			return Long.parseLong(sbNumber);
		} else {
			return 0L;
		}

	}

	public static String toString(double number) {
		NumberFormat format = NumberFormat.getInstance();

		format.setGroupingUsed(false);

		return format.format(number);
	}

	public static String toString(Number number) {
		NumberFormat format = NumberFormat.getInstance();

		format.setGroupingUsed(false);

		return format.format(number);
	}

	public static double percent(double number) {
		NumberFormat format = NumberFormat.getInstance();

		format.setMaximumFractionDigits(4);
		format.setGroupingUsed(false);

		return Double.parseDouble(format.format(number));
	}

	/**
	     * 返回传入数字的百分数
	     * 
	     * @param number
	     * @return
	     */
	public static String percentString(double number) {
		NumberFormat format = NumberFormat.getPercentInstance();

		format.setMaximumFractionDigits(4);
		format.setGroupingUsed(false);

		return format.format(number);
	}

	public static String percentString(Number number) {
		NumberFormat format = NumberFormat.getPercentInstance();

		format.setMaximumFractionDigits(4);
		format.setGroupingUsed(false);

		return format.format(number);
	}

	// 四舍五入取两位小�?

	public static double to2dec(double number) {
		NumberFormat format = NumberFormat.getInstance();

		format.setMaximumFractionDigits(2);
		format.setGroupingUsed(false);

		return Double.parseDouble(format.format(number + 1.0e-5));// 防止0.005被舍�?

	}

	// 四舍五入取n位小�?

	public static Double to2dec(double number, int digit) {
		NumberFormat format = NumberFormat.getInstance();

		format.setMaximumFractionDigits(digit);
		format.setGroupingUsed(false);
		int num = digit + 2;
		return Double.parseDouble(format.format(number + java.lang.Math.pow(0.1, num)));// 防止0.005被舍�?

	}

	/**
	     * 四舍五入保留两位小数
	     * 
	     * @param number
	     * @return
	     */
	public static Double to2dec(Number number) {
		NumberFormat format = NumberFormat.getInstance();

		format.setMaximumFractionDigits(2);
		format.setGroupingUsed(false);

		return Double.valueOf(format.format(number.doubleValue() + 1.0e-5));// 防止0.005被舍�?

	}

	/**
	 * 结果:0:number1 == number2 , 结果: -1:number1< number2 ,  结果:1: number1> number2
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static int compareDouble(double number1, double number2) {

		BigDecimal bA = new BigDecimal(number1);
		BigDecimal bB = new BigDecimal(number2);
		return bA.compareTo(bB);
	}

	public static String to2decString(double number) {
		NumberFormat format = NumberFormat.getInstance();

		format.setMaximumFractionDigits(2);
		format.setGroupingUsed(false);

		return format.format(number);
	}

	public static String to2decString(Number number) {
		if (number == null)
			return "";
		NumberFormat format = NumberFormat.getInstance();

		format.setMaximumFractionDigits(2);
		format.setGroupingUsed(false);

		return format.format(number);
	}

	public static String usdString(double number) {
		NumberFormat format = NumberFormat.getInstance();

		format.setCurrency(Currency.getInstance(Locale.US));

		return format.format(number);
	}

	/**
	     * 将传入数字格式化为美�?
	     * 
	     * @param number
	     * @return
	     */
	public static String usdString(Number number) {
		NumberFormat format = NumberFormat.getInstance();

		format.setCurrency(Currency.getInstance(Locale.US));

		return format.format(number);
	}

	/**
	 * 格式化金额显�?
	 * @param obj要格式化数据,num为小数点位数
	 * @return
	 */
	public static String getFormatStyle(Object obj, int num) {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMinimumFractionDigits(num);
		return nf.format(obj);
	}

	/**
	 * 转化百分比为小数,ex:传入�?50%,输出�?0.5
	 * @param obj 要格式化的百分比
	 * @return 
	 * */
	public static Double getNumberfrompercent(String str) {
		String percentstr = str.substring(0, str.length() - 1);
		Double num = Double.valueOf(percentstr);
		return num / 100;
	}

	/**
	 * 格式化金�?
	 * @param obj 要格式化的金�?
	 * @param iScale 保留的小数位�?
	 * @return Double 类型的金额，保留小数位数后面的金额直接截�?
	 *
	 * */
	public static Double getDirectNumberfromIsScale(String str, int iScale) {
		int index = str.indexOf(".");
		String sstr = str.substring(0, index);
		String estr = str.substring(index + 1, str.length());
		if (estr.length() > iScale)
			estr = estr.substring(0, iScale);
		return Double.valueOf(sstr + "." + estr);

	}

	/**
	 * 加法
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static Double add(Double v1, Double v2) {
		v1 = v1 == null ? 0.0D : v1;
		v2 = v2 == null ? 0.0D : v2;
		BigDecimal b1 = new BigDecimal(String.valueOf(v1));
		BigDecimal b2 = new BigDecimal(String.valueOf(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 减法
	* @param v1
	* @param v2
	* @return
	*/
	public static Double sub(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(String.valueOf(v1));
		BigDecimal b2 = new BigDecimal(String.valueOf(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 乘法
	* @param v1
	* @param v2
	* @return
	*/
	public static Double mul(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(String.valueOf(v1));
		BigDecimal b2 = new BigDecimal(String.valueOf(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 根据type的截位方式，point的截位位数，d1和d2相乘
	 * @param d1
	 * @param d2
	 * @param type
	 * @param point
	 * @return
	 */
	public static Double mul(Double d1, Double d2, Integer type, Integer point) {
		Double d = 0d;
		if (type == 0) {//四舍五入
			d = parseNumber(formatNumber(mul(d1, d2), point));
		} else if (type == 1) {//直接截位
			d = parseNumber(formatNumberByCut(mul(d1, d2), point));
		}
		return d;
	}

	/**
	 * 除法
	* @param v1
	* @param v2
	* @return
	*/
	public static Double div(Double v1, Double v2, int scale) {
		BigDecimal b1 = new BigDecimal(String.valueOf(v1));
		BigDecimal b2 = new BigDecimal(String.valueOf(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 除法
	 * @param v1 被除�?
	 * @param v2 除数
	 * @param scale 保留位数
	 * @param roundType 截位方式，与BigDecimal�?定义的一�?
	 * @return
	 */
	public static Double div(Double v1, Double v2, int scale, int roundType) {
		BigDecimal b1 = new BigDecimal(String.valueOf(v1));
		BigDecimal b2 = new BigDecimal(String.valueOf(v2));
		return b1.divide(b2, scale, roundType).doubleValue();
	}

	public static Double divByUp(Double v1, Double v2, int scale) {
		BigDecimal b1 = new BigDecimal(String.valueOf(v1));
		BigDecimal b2 = new BigDecimal(String.valueOf(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_UP).doubleValue();
	}

	/**
	 * 截位除法
	 * @param v1 被除�?
	 * @param v2 除数
	 * @param scale 保留位数
	 * @return
	 */
	public static Double divByDown(Double v1, Double v2, int scale) {
		BigDecimal b1 = new BigDecimal(String.valueOf(v1));
		BigDecimal b2 = new BigDecimal(String.valueOf(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_DOWN).doubleValue();
	}

	/**
	 * 四舍五入
	* @param v
	* @param limited
	* @return
	*/
	public static Double round(Double v, int scale) {
		BigDecimal b = new BigDecimal(String.valueOf(v));
		return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static Double roundByDown(Double v, int scale) {
		BigDecimal b = new BigDecimal(String.valueOf(v));
		return b.setScale(scale, BigDecimal.ROUND_DOWN).doubleValue();
	}

	public static Double roundByUp(Double v, int scale) {
		BigDecimal b = new BigDecimal(String.valueOf(v));
		return b.setScale(scale, BigDecimal.ROUND_UP).doubleValue();
	}

	/**
	 * 初始化double数量
	 * @param d
	 * @param value
	 * @return
	 */
	public static Double initDouble(Double d, Double value) {

		return d == null ? 0d : new BigDecimal(String.valueOf(value)).doubleValue();
	}

	/**
	 * 截取2位小�?
	 * @param num
	 * @return
	 */
	public static Double getFactnumber(Double num) {
		String amount = num.toString();
		int pos = amount.indexOf(".");
		String strFront = amount.substring(0, pos);
		String strEnd = amount.substring(pos + 1, amount.length());
		if (strEnd != null && strEnd.length() > 2)
			strEnd = strEnd.substring(0, 2);
		return Double.valueOf(strFront + "." + strEnd);
	}

	/**
	 * 截取n位小�?
	 * @param num
	 * @return
	 */
	public static Double getFactnumber(Double num, int n) {
		String amount = num.toString();
		int pos = amount.indexOf(".");
		String strFront = amount.substring(0, pos);
		String strEnd = amount.substring(pos + 1, amount.length());
		if (strEnd != null && strEnd.length() > n)
			strEnd = strEnd.substring(0, n);
		return Double.valueOf(strFront + "." + strEnd);
	}

	/**
	 * 把一个BigDecimal转换成Long
	 * @param num
	 * @return
	 */
	public static Long getLongFromBigdecimal(BigDecimal num) {
		if (num == null) {
			return null;
		} else {
			return new Long(num.longValue());
		}
	}

	/**
	 * 把一个BigDecimal转换成Integer
	 * @param num
	 * @return
	 */
	public static Integer getIntegerFromBigdecimal(BigDecimal num) {
		if (num == null) {
			return null;
		} else {
			return new Integer(num.intValue());
		}
	}

	/**
	 * 取消科学计算�?
	 * @param num
	 * @return
	 */
	public static String cancelScientificAth(String amount) {
		Double double1 = Double.valueOf(amount);
		DecimalFormat df = new DecimalFormat("###0.00");
		//		保留两位小数且不用科学计数法，并使用千分�? 
		amount = df.format(double1);
		return amount;
	}

	/**
	 * 取消科学计算法，不对小数点做控制
	 * @param num
	 * @return
	 */
	//合并代码时该方法重复
	/*public static String cancelScientificAthWithout(String amount) {
		Double double1 = Double.valueOf(amount);
		DecimalFormat df = new DecimalFormat("###0.#");
		amount = df.format(double1);
		return amount;
	}*/

	/**
	 * 取消科学计算�?
	 * @param num
	 * @return
	 */
	public static String cancelScientificAth4(String amount) {
		Double double1 = Double.valueOf(amount);
		DecimalFormat df = new DecimalFormat("###0.0000");
		//		保留两位小数且不用科学计数法，并使用千分�? 
		amount = df.format(double1);
		return amount;
	}

	/**
	 * 取消科学计算法，保留原有数�?�，不做处理
	 * @param num
	 * @return
	 */
	public static String cancelScientificAthWithout(String amount) {
		if(null == amount || "".equals(amount) || "null".equals(amount)){
			amount = "0";
		}
		Double double1 = Double.valueOf(amount);
		DecimalFormat df = new DecimalFormat("###0.#######");
		//		保留两位小数且不用科学计数法，并使用千分�? 
		amount = df.format(double1);
		return amount;
	}

	private final static String[] _NUMUPPER = { "�?", "�?", "�?", "�?", "�?", "�?", "�?", "�?", "�?", "�?" };

	private final static String[] _SMALLUNIT = { "", "�?", "�?", "�?" };
	private final static String[] _BIGUNIT = { "", "", "�?", "�?" };
	private final static String[] _AMONTUNIT = { "�?", "�?", "�?" };

	//小写转大�?
	public static String amountConvert2Cn(String amount) {

		final String ZERO = "0000";

		StringBuilder buff = new StringBuilder();

		String[] splits = amount.replaceAll(",", "").split("\\.");
		final String yuan = splits[0];
		//final String jiaofen = splits[1];

		int btyes = yuan.length();
		//分割段数�?4位一段，第一段位�?<=4�?
		int partCounts = btyes % 4 == 0 ? btyes / 4 : (btyes / 4 + 1);
		//第一部分长度
		int firstPartLen = btyes % 4 == 0 ? 4 : btyes % 4;
		//
		String smallNumConvert = smallNumConvert(yuan.substring(0, firstPartLen));
		buff.append(formatAllZore(smallNumConvert, _BIGUNIT[partCounts]));

		for (int i = 1; i < partCounts; i++) {
			String temp = yuan.substring(firstPartLen + i * 4 - 4, firstPartLen + i * 4);
			if (!ZERO.equals(temp)) {
				buff.append(formatAllZore(smallNumConvert(temp), _BIGUNIT[partCounts - i]));
			}

		}

		buff.append(_AMONTUNIT[0]);

		return buff.toString().replaceAll("�?+", "�?");
	}

	private static String formatAllZore(String numConvert, String unit) {
		return "�?".equals(numConvert) ? numConvert : (numConvert + unit);
	}

	/**
	 * 千位计数以内转换处理
	 * @param num 1234
	 * @return
	 */
	private static String smallNumConvert(String num) {
		StringBuilder buff = new StringBuilder();

		if (Integer.parseInt(num) == 10)
			return "�?";
		if (Integer.parseInt(num) == 0)
			return "�?";

		char[] arrays = num.toCharArray();

		for (int i = 0; i < arrays.length; i++) {
			int number = Integer.parseInt(String.valueOf(arrays[i]));
			buff.append(_NUMUPPER[number]);
			if (number != 0) {
				buff.append(_SMALLUNIT[arrays.length - i - 1]);
			}
		}

		String tmp = buff.toString().replaceAll("�?+", "�?");
		if (tmp.endsWith("�?"))
			tmp = tmp.substring(0, tmp.length() - 1);
		return tmp;
	}

	/**
	 * 对于传入的数值有可能超长时，使用BigDecimal型�??
	 * @Title: formatBigDecimalByZero 
	 * @Description: TODO(给传入的数字前面�?0) 
	 * @param num
	 * @param sce
	 * @return
	 */
	public static String formatBigDecimalByZero(BigDecimal num, int sce) {
		if (sce < 0) {
			sce = 2;
		}
		if (num != null) {
			StringBuffer sbf = new StringBuffer();
			if (num.toString().length() < sce) {
				for (int i = 0; i < sce - num.toString().length(); i++) {
					sbf.append("0");
				}
			}
			return sbf.append(num.toString()).toString();
		} else {
			return "";
		}

	}

	/**
	 * 
	* @Title: formatNumberByZero 
	* @Description: TODO(给传入的数字前面�?0) 
	* @param @param num
	* @param @param sce
	* @param @return     
	* @return String    
	* @throws
	 */
	public static String formatNumberByZero(Integer num, int sce) {
		if (sce < 0) {
			sce = 2;
		}
		if (num != null) {
			StringBuffer sbf = new StringBuffer();
			if (num.toString().length() < sce) {
				for (int i = 0; i < sce - num.toString().length(); i++) {
					sbf.append("0");
				}
			}
			return sbf.append(num.toString()).toString();
		} else {
			return "";
		}

	}

	/**
	 * 
	* @Title: to2dec_4 
	* @Description:解决四舍五入时出现的bug
	* @param @param number
	* @param @param num
	* @param @return     
	* @return double    
	* @throws
	 */
	public static double to2dec_4(double number, int num) {
		NumberFormat format = NumberFormat.getInstance();
		format.setMaximumFractionDigits(num);
		format.setGroupingUsed(false);
		return Double.parseDouble(format.format(number));
	}

	public static void main(String[] args) {
//		System.out.println(formatNumberByCutNOComma(0.91724836721292738, 2));
//		System.out.println(formatPointBits(0.91724836721292738, 2));
//		System.out.println(isNumeric("9.9"));
//		System.out.println(checkNumberLength("9.9", 32, 10));
		System.out.println(formatNumberNoApenndZero(100000.9102000,6));
		
	}

	public static boolean checkNumberLength(String value) {
		return checkNumberLength(value, 16, 2);
	}

	public static boolean checkNumberLength(String value, long integer, long iScale) {
		String reg = "";
		if (iScale > 0)//整数小数
		{
			reg = "^(0|([1-9]\\d{0," + integer + "}))(\\.\\d{1," + iScale + "})?$";
		} else if (iScale < 0)//小数
		{
			reg = "^(0\\.\\d{1," + -iScale + "})?$";
		} else//整数
		{
			reg = "^(0|([1-9]\\d{0," + integer + "}))?$";
		}
		if (value != null) {
			return value.matches(reg);
		}
		return false;
	}

	/**
	 * add by HYQ 2016-08-26
	 * 生成6位随机数
	 * 随机数最大上线为999999
	 * 若超出最大上线则会出现重复�??
	 * @return
	 */
	public static String getRandomNum6() {
		int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = array[index];
			array[index] = array[i - 1];
			array[i - 1] = tmp;
		}
		int num = 0;
		for (int i = 0; i < 6; i++) {
			num = num * 10 + array[i];
		}
		String result = String.valueOf(num);
		if (result.length() < 6) {
			result = "0" + result;
		}
		return result;
	}

	/**
	 * add by HYQ 2016.08.26
	 * 生成当前时间年月日时分秒+六位随机数的字符�?
	 * 如：2016�?8�?26�? 17:07:08
	 * 生成�?20168261778+6位随机数
	 * @return
	 */
	public static String getDateAndRandomNum6() {
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		return year + "" + (month + 1) + "" + date + "" + hour + "" + minute + "" + second + getRandomNum6();
	}
	
	/**
	 * 格式化数字，例如�?12345转化�?12,345 //按照四舍五入
	 * 
	 * @param dValue
	 *            被格式化的数�?
	 * @param iScale
	 *            去掉小数后面�?0
	 * @return
	 */
	public static String formatNumberNoApenndZero(double dValue, int iScale) {
		if (Double.isNaN(dValue)) {
			dValue = 0.0d;
		}
		DecimalFormat df = null;
		StringBuffer sPattern = new StringBuffer(",##0");
		if (iScale > 0) {
			sPattern.append(".");
			for (int i = 0; i < iScale; i++) {
				sPattern.append("#");
			}
		}
		df = new DecimalFormat(sPattern.toString());
		String str = df.format(dValue);
		return str;
	}
	
	
}

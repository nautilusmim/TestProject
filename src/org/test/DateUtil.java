/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * 版权声明 v1.0
 *
 * 本程�?(包括源代码和二进制码)的知识产�?(包括但不限于著作权�?�专利申请权、商标权、专有技�?)的所有权、使用权�?
 * 转让权以及收益等�?切权利均属于ISoftstone�?有�??
 * =================================================================================org.test==*/
package org.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Class description goes here.
 * 
 * @author <a href="mailto:xttao@isoftstone.com">xttao</a>
 * @version $Id: DateUtil.java,v0.1 2007-1-22 上午09:45:30 xttao Exp$
 */
public class DateUtil {
	public static long oneDay = 24 * 60 * 60 * 1000;

	public static final int FMT_DATE_YYYYMMDD = 1;
	public static final int FMT_DATE_YYYYMMDD_HHMMSS = 2;
	public static final int FMT_DATE_HHMM = 3;
	public static final int FMT_DATE_HHMMSS = 4;
	public static final int PARSE_DATE_YYYYMMDD = 5;

	public static String format(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
		return formater.format(date);
	}

	public static String Dateformat(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		return formater.format(date);
	}

	/**
	 * 判断是d1是否大于等于d2
	 * @author wxf
	 * @Date 2016-6-27上午11:00:04
	 */
	public static boolean isAgoOrEqual(String d1, String d2) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date dt1 = df.parse(d1);
			Date dt2 = df.parse(d2);
			if (dt1.getTime() >= dt2.getTime()) {
				System.out.println("d1 �? d2�?");
				return true;
			} else {
				return false;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}

	/**
	 * d1 是否小于或等于d2
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean isAgoOrEquals(Date d1, Date d2) {
		d1 = textToDate(format(d1), "yyyy/MM/dd");
		d2 = textToDate(format(d2), "yyyy/MM/dd");
		return d1.getTime() <= d2.getTime();

	}

	/**
	 * d1 是否小于d2
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean isAgoNoEqual(Date d1, Date d2) {
		d1 = textToDate(format(d1), "yyyy/MM/dd");
		d2 = textToDate(format(d2), "yyyy/MM/dd");
		return d1.getTime() < d2.getTime();

	}

	public static String format(Date date, String patern) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat formater = new SimpleDateFormat(patern);
		return formater.format(date);
	}

	/**
	 * java.util.Date类型转换成xx年xx月xx�?
	 * 
	 * @author ouzhuan
	 */
	public static String getStringDate(Date date) {
		String resultString = "";
		String tempString = format(date, "yyyy-MM-dd");
		String[] sub = tempString.split("-");

		if (sub.length >= 0) {
			resultString = sub[0] + "�?" + sub[1] + "�?" + sub[2] + "�?";
		}
		return resultString;

	}

	/**
	 * 字符串转成Date�?
	 * 
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date textToDate(String dateStr, String pattern) {
		try {
			Date date = new SimpleDateFormat(pattern).parse(dateStr);
			return date;
		} catch (ParseException e) {
			// TODO 自动生成 catch �?

			e.printStackTrace();
		}
		return null;
	}

	public static Date parseDate(String dateStr, int nFmtDate) throws ParseException {
		SimpleDateFormat fmtDate = new SimpleDateFormat();
		switch (nFmtDate) {
		default:
		case FMT_DATE_YYYYMMDD:
			fmtDate.applyPattern("yyyy-MM-dd");
			break;
		case FMT_DATE_YYYYMMDD_HHMMSS:
			fmtDate.applyPattern("yyyy-MM-dd HH:mm:ss");
			break;
		case FMT_DATE_HHMM:
			fmtDate.applyPattern("HH:mm");
			break;
		case FMT_DATE_HHMMSS:
			fmtDate.applyPattern("HH:mm:ss");
			break;
		case PARSE_DATE_YYYYMMDD:
			fmtDate.applyPattern("yyyyMMdd");
			break;
		}
		return fmtDate.parse(dateStr);
	}

	/**
	 * 判断date是否在flag之前
	 */
	public static boolean isBefore(Date flag, Date date) {
		return date.getTime() < flag.getTime();
	}

	/**
	 * 判断date是否在flag之前或相�?
	 */
	public static boolean isBeforeOrEquals(Date flag, Date date) {
		return date.getTime() <= flag.getTime();
	}

	/**
	 * 判断date是否在flag之后
	 */
	public static boolean isAfter(Date flag, Date date) {
		return date.getTime() > flag.getTime();
	}

	/**
	 * 判断date是否在flag之后或相�?
	 */
	public static boolean isAfterOrEquals(Date flag, Date date) {
		return date.getTime() >= flag.getTime();
	}

	public static boolean isStartWrap(Date flagStart, Date flagEnd, Date dateStart) {
		return dateStart.before(flagEnd) && dateStart.after(flagStart)
				|| dateStart.equals(flagStart) || dateStart.equals(flagEnd);
	}

	public static boolean isEndWrap(Date flagStart, Date flagEnd, Date dateEnd) {
		return dateEnd.before(flagEnd) && dateEnd.after(flagStart)
				|| dateEnd.equals(flagStart) || dateEnd.equals(flagEnd);
	}

	/**
	 * example: DateUtil.getYear(new Date(182,4,7)) <p/> result: 1982
	 */
	public static int getYear(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		return year;
	}

	/**
	 * example: DateUtil.getMonth(new Date(182,4,7)) <p/> result: 5
	 */
	public static int getMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		return month;
	}

	/**
	 * @
	 */
	/**
	 * example: DateUtil.getDate(new Date(182,4,7)) <p/> result: 7
	 */
	public static int getDate(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int date2 = calendar.get(Calendar.DATE);
		return date2;
	}

	public static int getHour(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		return hour;
	}

	public static int getMinute(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int minute = calendar.get(Calendar.MINUTE);
		return minute;
	}

	/**
	 * example: DateUtil.addYears(new Date(182,4,7), 22) <p/> result: 2004�?5�?7�?
	 * 
	 */
	public static Date addYears(Date date, int yearNum) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, yearNum);
		return calendar.getTime();
	}

	/**
	 * example: DateUtil.addMonths(new Date(182,4,7), 13) <p/> result: 1983�?6�?7�?
	 * 
	 */
	public static Date addMonths(Date date, int monthNum) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, monthNum);
		return calendar.getTime();
	}

	public static Date addHours(Date date, int hourNum) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hourNum);
		return calendar.getTime();
	}

	/**
	 * 日期比较函数，相等返�?0,大于大于零的�?,小于返回小于零的�?
	 */
	public static int dateCompare(Date dateA, Date dateB) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String datea = format.format(dateA);
		String dateb = format.format(dateB);
		return datea.compareTo(dateb);
	}

	/**
	 * 
	 * 为date加上dateNum天数之后的日�?
	 * 
	 */
	public static Date addDates(Date date, int dateNum) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, dateNum);
		return calendar.getTime();
	}

	/**
	 * example: DateUtil.createDate(1982,5,7) <p/> result: 1982�?5�?7�?
	 * 
	 */
	public static Date createDate(int year, int month, int date) {
		Calendar calendar = new GregorianCalendar(year, month - 1, date);
		return calendar.getTime();
	}

	/**
	 * 将指定格式的日期/时间字符串转换成Date格式
	 * 
	 * @param strDate
	 *            String类型，日期字�?
	 * @param strFormat
	 *            String类型，格�?
	 * @return Date类型
	 * @throws java.lang.Exception
	 */
	public static java.util.Date toUtilDate(String strDate, String strFormat) {
		try {
			if (strDate == null || strDate.equals("")) {
				return null;
			} else {
				SimpleDateFormat _formatdate = new SimpleDateFormat(strFormat, Locale
						.getDefault());
				java.util.Date _date = new java.util.Date((_formatdate.parse(strDate))
						.getTime());
				return _date;
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
			// return null;
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 将指定格式的日期/时间字符串转换成Date格式
	 * 
	 * @param strDate
	 *            String类型，日期字�?
	 * @param strFormat
	 *            String类型，格�?
	 * @return Date类型
	 * @throws java.lang.Exception
	 */
	public static java.util.Date toUtilDateTow(String strDate, String strFormat) throws Exception {
		if (strDate == null || strDate.equals("")) {
			return null;
		} else {
			SimpleDateFormat _formatdate = new SimpleDateFormat(strFormat, Locale
					.getDefault());
			java.util.Date _date = new java.util.Date((_formatdate.parse(strDate))
					.getTime());
			return _date;
		}
	}

	/**
	 * 将指定格式的日期/时间字符串转换成Date格式
	 * 
	 * @param strDate
	 *            String类型，日期字�?
	 * @param strFormat
	 *            String类型，格�?
	 * @return Date类型
	 * @throws java.lang.Exception
	 */
	public static java.sql.Date toSQLDate(String strDate, String strFormat) {
		try {
			if (strDate == null || strDate.equals("")) {
				return null;
			} else {
				SimpleDateFormat _formatdate = new SimpleDateFormat(strFormat, Locale
						.getDefault());
				java.sql.Date _date = new java.sql.Date((_formatdate.parse(strDate))
						.getTime());
				return _date;
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
			// return null;
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 取得系统当前时间
	 */
	public static String getCurrentSysTime(int iType) {

		Date dtNow = new Date(System.currentTimeMillis());

		String dateString = "";

		try {
			SimpleDateFormat formatter = null;
			switch (iType) {
			case 1:
				formatter = new SimpleDateFormat("yyyy.MM.dd");
				break;
			case 2:
				formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
				break;
			case 3:
				formatter = new SimpleDateFormat("yyyy.MM.dd hh:mm a");
				break;
			case 4:
				formatter = new SimpleDateFormat("yyyy-MM-dd");
				break;
			case 5:
				formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				break;
			case 6:
				formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm a");
				break;
			case 7:
				formatter = new SimpleDateFormat("yyyyMMdd");
				break;
			default:
				formatter = new SimpleDateFormat("yyyy.MM.dd");
				break;
			}
			dateString = formatter.format(dtNow);
		} catch (Exception e) {
			dateString = "";
		}
		return dateString;
	}

	/**
	 * 获得两个日期相隔天数
	 * 
	 * @param dtBeginDate
	 * @param dtEndDate
	 * @return
	 */
	public static long intervalDays(Date dtBeginDate, Date dtEndDate) {
		GregorianCalendar gc1, gc2;
		gc1 = new GregorianCalendar();
		gc1.setTime(dtBeginDate);
		gc2 = new GregorianCalendar();
		gc2.setTime(dtEndDate);
		gc1.clear(Calendar.MILLISECOND);
		gc1.clear(Calendar.SECOND);
		gc1.clear(Calendar.MINUTE);
		gc1.clear(Calendar.HOUR_OF_DAY);
		gc2.clear(Calendar.MILLISECOND);
		gc2.clear(Calendar.SECOND);
		gc2.clear(Calendar.MINUTE);
		gc2.clear(Calendar.HOUR_OF_DAY);
		long lInterval = 0;
		lInterval = gc2.getTime().getTime() - gc1.getTime().getTime();
		lInterval = lInterval / (24 * 60 * 60 * 1000);
		return lInterval;
	}

	/**
	 * 取当月第�?天，输出格式：YYYY-MM-DD
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Date getFirstDayOfMonth(Date date) {
		StringBuffer newDate = new StringBuffer();
		newDate.append(getYear(date)).append("-");
		newDate.append(getMonth(date)).append("-");
		newDate.append("1");
		return toSQLDate(newDate.toString(), "yyyy-MM-dd");
	}

	/**
	 * 取当月最后一天，输出格式：YYYY-MM-DD
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Date getLastDayOfMonth(Date date) {
		Integer year = getYear(date);
		Integer[] monthArray = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		// 闰年判断
		if ((year % 4 == 0 && year % 100 != 0) || (year % 100 == 0 && year % 400 == 0)) {
			monthArray[1] = 29;
		}
		Integer month = getMonth(date);
		StringBuffer newDate = new StringBuffer();
		newDate.append(year).append("-");
		newDate.append(month).append("-");
		newDate.append(monthArray[month - 1]);
		return toSQLDate(newDate.toString(), "yyyy-MM-dd");
	}

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		Date businessDate;
		try {
			businessDate = DateUtil.parseDate("2100-02-25", DateUtil.FMT_DATE_YYYYMMDD);
			System.out.println(getLastDayOfMonth(businessDate));
			SimpleDateFormat _formatdate = new SimpleDateFormat("yyyy-MM-dd", Locale
					.getDefault());
			java.sql.Date _date = new java.sql.Date((_formatdate.parse("2017-02-29"))
					.getTime());
			System.out.println(_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 取当年第�?天，输出格式：YYYY-MM-DD
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Date getFirstDayOfYear(Date date) {
		StringBuffer newDate = new StringBuffer();
		newDate.append(getYear(date)).append("-");
		newDate.append("1-1");
		return toSQLDate(newDate.toString(), "yyyy-MM-dd");
	}

	/**
	 * 取当年第�?天，输出格式：YYYY-MM-DD
	 * add by qsyanga
	 * @param date
	 * @return
	 * @throws ParseException 
	 */
	public static Date getFirstDayOfYearUtilDate(Date date) throws ParseException {
		StringBuffer newDate = new StringBuffer();
		newDate.append(getYear(date)).append("-");
		newDate.append("1-1");
		return DateUtil.parseDate(newDate.toString(), DateUtil.FMT_DATE_YYYYMMDD);
	}

	public static String getFirstDayOfYearUtilDate(int year) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, 1);

		return DateUtil.Dateformat(DateUtil.getFirstDayOfYearUtilDate(cal.getTime()));
	}

	/**
	 * 将JAVA DATE型转成sql Date 输出格式：YYYY-MM-DD
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Date utilDate_2_sqlDate(Date date) {
		StringBuffer newDate = new StringBuffer();
		newDate.append(getYear(date)).append("-");
		newDate.append(getMonth(date)).append("-");
		newDate.append(getDate(date));
		return toSQLDate(newDate.toString(), "yyyy-MM-dd");
	}

	/**
	 * 只精确到日期，舍去时分秒
	 * 
	 * @param date
	 * @return
	 */
	public static Date correctToDate(Date date) {
		GregorianCalendar gc1 = new GregorianCalendar();
		gc1.setTime(date);
		gc1.clear(Calendar.MILLISECOND);
		gc1.clear(Calendar.SECOND);
		gc1.clear(Calendar.MINUTE);
		gc1.clear(Calendar.HOUR_OF_DAY);
		return gc1.getTime();
	}

	/**
	 * 只精确到日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date correctToDate2(Date date) {
		GregorianCalendar gc1 = new GregorianCalendar();
		gc1.setTime(date);
		return gc1.getTime();
	}

	/**
	 * 获得这一天的�?始时间（去掉分秒�?
	 * 例如2011-11-11:16:16:16.300 处理后变�?2011-11-11:00:00:00.000
	 * @param date
	 * @return
	 */
	public static Date getStartOfDate(Date date) {
		GregorianCalendar gc1 = new GregorianCalendar();
		gc1.setTime(date);
		gc1.set(Calendar.MILLISECOND, 0);
		gc1.set(Calendar.SECOND, 0);
		gc1.set(Calendar.MINUTE, 0);
		gc1.set(Calendar.HOUR_OF_DAY, 0);
		return gc1.getTime();
	}

	public static Date getEndOfDate(Date date) {
		GregorianCalendar gc1 = new GregorianCalendar();
		gc1.setTime(date);
		gc1.set(Calendar.MILLISECOND, 999);
		gc1.set(Calendar.SECOND, 59);
		gc1.set(Calendar.MINUTE, 59);
		gc1.set(Calendar.HOUR_OF_DAY, 23);
		return gc1.getTime();
	}

	public static Date getLastMonthDate(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month - 1);
		return calendar.getTime();
	}

	// 将日期加�?�?
	public static Date addOneDay(Date date) {

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(GregorianCalendar.DATE, 1);
		Date newdate = calendar.getTime();
		return newdate;
	}

	//将字符串"20020809" 拼接�?"2002-08-09"
	@SuppressWarnings("unused")
	public static String toMakeDateStr(String dateStr) {
		StringBuffer sbf = new StringBuffer();
		if (!"".equals(dateStr)) {
			sbf.append(dateStr.substring(0, 4)).append("-").append(dateStr.substring(4, 6)).append("-")
					.append(dateStr.substring(6));
		}
		return sbf.toString();
	}

	public static Date getTimeStamp(Date date) {
		StringBuffer newDate = new StringBuffer();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		newDate.append(calendar.get(Calendar.YEAR)).append("-");
		newDate.append(calendar.get(Calendar.MONTH)).append("-");
		newDate.append(calendar.get(Calendar.DATE)).append(" ");
		newDate.append(calendar.get(Calendar.HOUR_OF_DAY)).append(":");
		newDate.append(calendar.get(Calendar.MINUTE)).append(":");
		newDate.append(calendar.get(Calendar.SECOND));
		return toUtilDate(newDate.toString(), "yyyy-MM-dd HH:mm:ss");
	}

	public static Date getTimeStamp2(Date date) {
		StringBuffer newDate = new StringBuffer();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		newDate.append(calendar.get(Calendar.YEAR)).append("-");
		newDate.append(calendar.get(Calendar.MONTH) + 1).append("-");
		newDate.append(calendar.get(Calendar.DATE)).append(" ");
		newDate.append(calendar.get(Calendar.HOUR_OF_DAY)).append(":");
		newDate.append(calendar.get(Calendar.MINUTE)).append(":");
		newDate.append(calendar.get(Calendar.SECOND));
		return toUtilDate(newDate.toString(), "yyyy-MM-dd HH:mm:ss");
	}

	public static Date getNormalDate(Date date)
	{
		StringBuffer newDate = new StringBuffer();
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(date.getTime());
		newDate.append(calendar.get(Calendar.YEAR)).append("-");
		newDate.append(calendar.get(Calendar.MONTH)).append("-");
		newDate.append(calendar.get(Calendar.DATE));
		return toUtilDate(newDate.toString(), "yyyy-MM-dd");
	}

	//取指定日期的前N天工作日
	public static String lastWorkDay(Date date, int daynum) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar start = Calendar.getInstance();
		String newDate = sdf.format(date.getTime());
		//下面代码设置�?始日期，注：不要设置为周�?
		//假设设置�?(2011)�?(8)�?(18)，注：如果是8月，设置时�?�要�?1，如下：
		//start.set(2011, 8-1, 18);
		start.set(Integer.valueOf(newDate.substring(0, 4)), Integer.valueOf(newDate.substring(5, 7)) - 1,
				Integer.valueOf(newDate.substring(8, newDate.length())));
		//下面设置工作�? 整数 
		int n = daynum;
		//�?始时间增�? n/7*5+n*7%5�?有天(包括非工作日)，等同增加n天工作日
		start.add(Calendar.DAY_OF_YEAR, -(int) n * 7 / 5 + n * 7 % 5);
		//输出完成工作�?
		String lastWorkDate = sdf.format(start.getTime());

		return lastWorkDate;
	}

	public static boolean isDate(String dateStr, String pattern) {
		try {
			Date date = new SimpleDateFormat(pattern).parse(dateStr);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**add by MarongSheng
	 * 获取上一个月的第�?�?
	 * @return
	 */
	public static Date getLastMonthFirstDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();

	}

	/**add by MarongSheng
	 * 获取上一个月的最后一�?
	 * @return
	 */
	public static Date getLastMonthEndDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();

	}

	public static boolean validateSimpleDate(String dateStr, String type) {
		if (null == dateStr) {
			return false;
		}
		if (dateStr.trim().length() > type.length() || dateStr.trim().length() < type.length() - 2) {
			return false;
		}
		SimpleDateFormat f = new SimpleDateFormat(type);
		f.setLenient(false);
		try {
			f.parse(dateStr);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public static int daysBetween(Date smdate, Date bdate) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**add by MarongSheng
	 * 获取明天
	 * @return
	 */
	public static Date getTomorrowDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, +1);
		return calendar.getTime();

	}
	
	public static Calendar getCalendarDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	public static Date getCurrentDate(){
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}
}
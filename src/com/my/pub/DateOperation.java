package com.my.pub;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

public class DateOperation {
	protected Date date;

	public DateOperation() {
	}
	
	public DateOperation(Date date) {
		this.date = date;
	}

	public Date getDateStart() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public Date getDateEnd() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	public Date getDatetime() {
		return date;
	}

	public void add(int CalendarField, int value) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(CalendarField, cal.get(CalendarField) + value);
		date = cal.getTime();
	}

	/**
	 * 日期格式过滤
	 * 
	 * @author chwj
	 * @param list
	 * @param SimpleDateFormat
	 * @return List
	 */
	public static List<String> dateFormatFilter(List<String> list, SimpleDateFormat df) {
		List<String> result = new ArrayList<String>();
		for (String str : list) {
			try {
				df.parse(str);
				result.add(str);
			} catch (java.text.ParseException e) {
				// System.out.println("The input is error");
			}
		}
		return result;
	}

	public long getTimeCompare(String format, String startTime)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date now = new Date();
		Date startT = df.parse(startTime);
		return (now.getTime() - startT.getTime()) / 1000;//返回单位为秒
	}

	public static long getTimeCompare(SimpleDateFormat df, String startTime)
			throws ParseException {
		Date now = new Date();
		Date startT = df.parse(startTime);
		return (now.getTime() - startT.getTime()) / 1000;//返回单位为秒
	}

	public static long getTimeCompare(String s1, String s2, SimpleDateFormat df)
			throws ParseException {
		Date d1 = df.parse(s1);
		Date d2 = df.parse(s2);
		return (d1.getTime() - d2.getTime()) / 1000;// 返回单位为秒
	}

	public static boolean minuteCompare(String s1, String s2, SimpleDateFormat df, int minutes) {
		long second;
		try {
			second = Math.abs(getTimeCompare(s1, s2, df));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return (Math.abs(minutes) * 60) <= second;
	}

	public static boolean hourCompare(String s1, String s2, SimpleDateFormat df, int hours) {
		long second;
		try {
			second = Math.abs(getTimeCompare(s1, s2, df));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return (Math.abs(hours) * 60 * 60) <= second;
	}

	public static String dateToString(Date date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
	
	public static String dateToString(Date date, String format){
		SimpleDateFormat df;
		if(format!=null && !format.equals(""))
			df = new SimpleDateFormat(format);
		else{
			df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		}
		return df.format(date);
	}
	
	public static Date StringToDate(String s, String format) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.parse(s);
	}
	
	public static Date StringToDate(String s, SimpleDateFormat df) throws ParseException{
		return df.parse(s);
	}
	
	//XMLGregorianCalendar 与 Date 转换的方法
	@SuppressWarnings("static-access")
	public static javax.xml.datatype.XMLGregorianCalendar getXMLGregorianCalendar(
			java.util.Date date) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		javax.xml.datatype.DatatypeFactory dtf = javax.xml.datatype.DatatypeFactory
				.newInstance();
		return dtf.newXMLGregorianCalendar(calendar.get(calendar.YEAR),
				calendar.get(calendar.MONTH) + 1, calendar
						.get(calendar.DAY_OF_MONTH), calendar
						.get(calendar.HOUR), calendar.get(calendar.MINUTE),
				calendar.get(calendar.SECOND), calendar
						.get(calendar.MILLISECOND), calendar
						.get(calendar.ZONE_OFFSET)
						/ (1000 * 60));
	}
	
	public static Date getDateFromXMLGregorianCalendar(XMLGregorianCalendar cal) {
		return cal.toGregorianCalendar().getTime();
	}
	
	public static void main(String args[]) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date now;
		try {
			now = df.parse("2011-03-26 13:31:40");
			java.util.Date date = df.parse("2011-03-26 13:21:24");
			System.out.println(now.getTime());
			System.out.println(date.getTime());
			System.out.println(((now.getTime() - date.getTime()) > 600000));
			System.out.println(getTimeCompare(df,"2012-02-16 16:44:24"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

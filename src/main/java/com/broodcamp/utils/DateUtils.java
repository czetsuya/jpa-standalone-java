package com.broodcamp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Edward P. Legaspi <czetsuya@gmail.com>
 * 
 * Date utility functions.
 */
public class DateUtils {

	public static final String format1 = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String format2 = "yyyy-MM-dd.HH:mm:ss";
	public static final String TIMEZONE = "UTC";

	private DateUtils() {

	}

	public static Date parse(String strDate) throws ParseException {

		return parse(format1, strDate);
	}

	public static Date parse(String format, String strDate) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat(format);
		formatter.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
		return formatter.parse(strDate);
	}

	public static Date addHours(Date date, int hours) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, hours);

		return cal.getTime();
	}
}

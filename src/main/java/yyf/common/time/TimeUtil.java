package yyf.common.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.joda.time.DateTime;



public class TimeUtil {
	public static final String dtsFormat = "<br>支持如下格式：<br>2017-03-09T10:10:10Z<br>2017-03-09 10:10:10" +
			"<br>2017年03月09日 10:10:10<br>2017/03/09 10:10:10<br>2017-03-09<br>2017年03月09日<br>2017/03/09";
	public static int stripTimestamp(int time, int interval) {
		int currTime = time + 8 * 60 * 60;
		int remainders = currTime % interval;
		return time - remainders;
	}
	
	public static long stripTimestamp(long time, long interval) {
		long currTime = time + 8 * 60 * 60 * 1000;
		long remainders = currTime % interval;
		return time - remainders;
	}
	
	public static DateTime toJdaDateTime(String value)   {
		try {
			String format = "yyyy-MM-dd HH:mm:ss";
			if (value.matches("\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}Z")) {
				format = "yyyy-MM-dd'T'HH:mm:ssZ";
			} else if (value.matches("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
				format = "yyyy-MM-dd HH:mm:ss";
			} else if (value.matches("\\d{4}年\\d{1,2}月\\d{1,2}日 \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
				format = "yyyy年MM月dd日 HH:mm:ss";
			} else if (value.matches("\\d{4}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
				format = "yyyy/MM/dd HH:mm:ss";
			} else if (value.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
				format = "yyyy-MM-dd";
			} else if (value.matches("\\d{4}年\\d{1,2}月\\d{1,2}日")) {
				format = "yyyy年MM月dd日";
			} else if (value.matches("\\d{4}/\\d{1,2}/\\d{1,2}")) {
				format = "yyyy/MM/dd";
			} else {
				//do nothing.
			}
			
			return org.joda.time.format.DateTimeFormat.forPattern(format).parseDateTime(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static DateTime toJdaDateTime(String name, String value)   {
		try {
			String format = "yyyy-MM-dd HH:mm:ss";
			if (value.matches("\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}Z")) {
				format = "yyyy-MM-dd'T'HH:mm:ssZ";
			} else if (value.matches("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
				format = "yyyy-MM-dd HH:mm:ss";
			} else if (value.matches("\\d{4}年\\d{1,2}月\\d{1,2}日 \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
				format = "yyyy年MM月dd日 HH:mm:ss";
			} else if (value.matches("\\d{4}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
				format = "yyyy/MM/dd HH:mm:ss";
			} else if (value.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
				format = "yyyy-MM-dd";
			} else if (value.matches("\\d{4}年\\d{1,2}月\\d{1,2}日")) {
				format = "yyyy年MM月dd日";
			} else if (value.matches("\\d{4}/\\d{1,2}/\\d{1,2}")) {
				format = "yyyy/MM/dd";
			} else {
				//do nothing.
			}
//			org.joda.time.format.DateTimeFormat.forPattern(format).parseDateTime(value);
			return org.joda.time.format.DateTimeFormat.forPattern(format).parseDateTime(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static java.util.Date toUtilDateTime(String value)  {
		try {
			String format = "yyyy-MM-dd HH:mm:ss";
			if (value.matches("\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}Z")) {
				format = "yyyy-MM-dd'T'HH:mm:ssZ";
			} else if (value.matches("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
				format = "yyyy-MM-dd HH:mm:ss";
			} else if (value.matches("\\d{4}年\\d{1,2}月\\d{1,2}日 \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
				format = "yyyy年MM月dd日 HH:mm:ss";
			} else if (value.matches("\\d{4}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
				format = "yyyy/MM/dd HH:mm:ss";
			} else if (value.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
				format = "yyyy-MM-dd";
			} else if (value.matches("\\d{4}年\\d{1,2}月\\d{1,2}日")) {
				format = "yyyy年MM月dd日";
			} else if (value.matches("\\d{4}/\\d{1,2}/\\d{1,2}")) {
				format = "yyyy/MM/dd";
			} else {
				//do nothing.
			}
			
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            java.util.Date date = sdf.parse(value);
            return date;
        } catch (ParseException e) {
        }
		return null;
	}
	
}

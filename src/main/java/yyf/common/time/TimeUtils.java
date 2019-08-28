package yyf.common.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class TimeUtils {
	
	public static String getDateStr(Long timeStamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(new Date(timeStamp));
		return dateStr;
	}
	
	public static String getProDayStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(new Date(System.currentTimeMillis() - 86400000l));
		return dateStr;
	}
	
	/**
	 * 根据时间戳获取特定时间
	 * 1970-01-01 8:00:00 时间戳为0
	 * 一天为24小时 24*60*60秒
	 * 当前时间戳减去时间精度，表示我的时间是一个以小时为精度的时间戳格式
	 * 得到当前时间和距离八点的秒数
	 * 当前秒数减去距离八点的描述得到八点的一个时间戳加上需要得到的时间和八点的时间差的秒数
	 * @param timeStamp
	 * @return
	 */
	public static Long getspecifyTime(Long timeStamp) {
		Long day = 24 * 60 * 60l;
		Long currTime = timeStamp - 2 * 60 * 60;
		Long remainders = currTime % day;
		Long value = currTime - remainders + 2 * 60 * 60;
		return value;
	}
	
	/**
	 * 以八点为界限当天的八点之前为前一天八点的时间，当天八点之后的时间为当天八点的时间
	 * @param timeStamp
	 * @return
	 */
	public static Long getspecifyTime2(Long timeStamp) {
		Long timeFlag = 24*60*60l;
		Long currTime = timeStamp-timeFlag;
		Long remainders = currTime % timeFlag;
		Long value = currTime - remainders+timeFlag;
		return value;
	}
	/**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * @param strTime1 时间参数 1 格式：2016-06-22 18:21:20
     * @param strTime2 时间参数 2 格式：2016-06-22 17:21:20
     * @param timeFormat 日期格式 yyyy-MM-dd HH:mm:ss    毫秒(yyyy-MM-dd HH:mm:ss.SSS)
     * @return String 返回值为：xx天xx小时xx分xx秒xx毫秒
     */
    public static String getDifferenceTime(String strTime1, String strTime2, String timeFormat) {
        DateFormat df = new SimpleDateFormat(timeFormat);
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        long ms = 0;
        try {
        	one = df.parse(strTime1);
        	two = df.parse(strTime2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
            ms = (diff - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - sec * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        return day + "天" + hour + "小时" + min + "分" + sec + "秒";
        return day + "天" + hour + "小时" + min + "分" + sec + "秒" + ms + "毫秒";
    }
	
	public static Map<String, Integer> timeRange(String startTime, String endTime, String interval) {
		Map<String, Integer> timeStatisticMap = new LinkedHashMap<>();
		String format = "yyyy-MM-dd";
		int filed = Calendar.DAY_OF_YEAR;
		if ("month".equals(interval)) {
			format = "yyyy-MM";
			filed = Calendar.MONTH;
		} else if ("year".equals(interval)) {
			format = "yyyy";
			filed = Calendar.YEAR;
		} else {
			// donothing
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			Date startDate = sdf.parse(startTime);
			Date endDate = sdf.parse(endTime);
			Calendar tempStart = Calendar.getInstance();
			tempStart.setTime(startDate);
			while (startDate.getTime() <= endDate.getTime()) {
				String dateStr = sdf.format(startDate);
				timeStatisticMap.put(dateStr, 0);
				System.out.println(dateStr);
				tempStart.add(filed, 1);
				startDate = tempStart.getTime();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeStatisticMap;
	}
}

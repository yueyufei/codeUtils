package yyf.common.time;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	


}

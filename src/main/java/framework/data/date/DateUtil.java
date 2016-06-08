package framework.data.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-04-18
 */
public class DateUtil {

	/**
	 * 获取当前日期
	 */
	public static String getDateString() {
		return getDateString(new Date());
	}

	/**
	 * 获取当前时间
	 */
	public static String getDateTimeString() {
		return getDateTimeString(new Date());
	}

	/**
	 * 获取指定日期
	 */
	public static String getDateString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	/**
	 * 获取指定时间
	 */
	public static String getDateTimeString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/**
	 * 获取指定日期
	 */
	public static Date getDate() {
		return new Date();
	}

	/**
	 * 获取指定日期
	 */
	public static Date getDate(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取指定时间
	 */
	public static Date getDateTime(String dateTime) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime);
		} catch (ParseException e) {
			return null;
		}
	}
}

package com.powere2e.sco.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 日期格式化工具类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年3月19日
 */
public class DateUtils {

	/**
	 * 将当前时间转为默认格式(yyyy-MM-dd)的字符串
	 */
	public static String formateDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATA_INTEFACE_DATEFORMATE);
		return sdf.format(new Date());
	}

	/**
	 * 格式化时间
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDateToStr(Date date, String format) {
		if (date == null || format == null) {
			return null;
		}
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 将当前时间转为默认格式(yyyyMMddHHmmss)的字符串
	 */
	public static String formateDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATA_TIME_DATEFORMATE);
		return sdf.format(new Date());
	}

	/**
	 * 获取系统当前时间
	 */
	public static Date getCurrentDate() {
		return new Date();
	}

	/**
	 * 获取当前时间昨天的区间
	 * 
	 * @return 昨天00:00:00 -- 今天00:00:00
	 */
	public static Date[] getYesterdayArea() {
		Calendar cl = getCalendar();
		Calendar yestarday = Calendar.getInstance(cl.getTimeZone());
		yestarday.set(Calendar.DAY_OF_MONTH, yestarday.get(Calendar.DAY_OF_MONTH) - 1);
		yestarday.set(Calendar.HOUR_OF_DAY, 0);
		yestarday.set(Calendar.MINUTE, 0);
		yestarday.set(Calendar.SECOND, 0);
		yestarday.set(Calendar.MILLISECOND,0);
		
		return new Date[] { yestarday.getTime(), cl.getTime() };
	}

	/**
	 * 获取当前时间的上一个月份
	 * 
	 * @return 当前时间上一个年月
	 */
	public static Date getLastMonthByNow() {
		Calendar cl = Calendar.getInstance();
		cl.set(Calendar.DAY_OF_MONTH, 1);
		cl.add(Calendar.DAY_OF_MONTH, -1);
		return cl.getTime();
	}

	/**
	 * 获取指定时间的某个时间(只操作月份)
	 * 
	 * @return 当前时间上一个年月
	 */
	public static Date getLastMonthByDate(Date date, int lastIndex) {
		Calendar cl = getCalendar(date);
		cl.set(Calendar.DAY_OF_MONTH, 1);
		cl.set(Calendar.MONTH, cl.get(Calendar.MONTH) + lastIndex);
		return cl.getTime();
	}

	/**
	 * 获取某个时间段的年月份
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 对应时间区间的年月份
	 */
	public static List<String> getRegionDates(Date startDate, Date endDate) {
		List<String> result = new ArrayList<String>();
		setDateTime(startDate, endDate);
		if (endDate.before(startDate)) {
			return result;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATA_INTEFACE_DATEFORMATE);
		Calendar sDate = Calendar.getInstance();
		sDate.setTime(startDate);
		Calendar eDate = Calendar.getInstance();
		eDate.setTime(endDate);

		Calendar temp = Calendar.getInstance();
		temp.setTime(startDate);
		temp.set(Calendar.DAY_OF_MONTH, 1);
		while ((eDate.getTimeInMillis() - temp.getTimeInMillis()) >= 0) {
			result.add(sdf.format(temp.getTime()));
			temp.add(Calendar.MONTH, 1);
		}
		return result;
	}

	/**
	 * 获取一个时分秒为0的Calendar
	 * 
	 * @return 时分秒为0的Calendar
	 */
	public static Calendar getCalendar() {
		Calendar cl = Calendar.getInstance();
		cl.set(Calendar.HOUR_OF_DAY, 0);
		cl.set(Calendar.MINUTE, 0);
		cl.set(Calendar.SECOND, 0);
		cl.set(Calendar.MILLISECOND, 0);
		
		return cl;
	}

	/**
	 * 获取一个时分秒为0的Calendar
	 * 
	 * @param date
	 *            指定日期
	 * @return 时分秒为0的Calendar
	 */
	public static Calendar getCalendar(Date date) {
		if (date == null)
			return null;
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.set(Calendar.HOUR_OF_DAY, 0);
		cl.set(Calendar.MINUTE, 0);
		cl.set(Calendar.SECOND, 0);
		cl.set(Calendar.MILLISECOND, 0);
		
		return cl;
	}

	/**
	 * 将日期的时分秒设置为0
	 * 
	 * @param dateArr
	 *            日期数组
	 */
	@SuppressWarnings("deprecation")
	public static void setDateTime(Date... dateArr) {
		if (dateArr != null && dateArr.length > 1)
			for (Date date : dateArr) {
				date.setHours(0);
				date.setMinutes(0);
				date.setSeconds(0);
			}
	}

	/**
	 * 格式化字符串日期
	 * 
	 * @param str
	 *            日期字符串
	 * @param format
	 *            字符串日期格式
	 * @return 格式化后的日期
	 */
	public static Date formatStrToDate(String str, String format) {
		Date date = null;
		if (StringUtils.isBlank(format) || StringUtils.isBlank(str)) {
			return date;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取开始日期到结束日期之间的年月(格式为yyyy-MM) <br>
	 * <li>各年月用"'"连接,之间用","隔开</li> <li>包含开始和结束日期</li>
	 * 
	 * @param startDate
	 *            开始日期(格式为yyyy-MM)
	 * @param endDate
	 *            结束日期(格式为yyyy-MM)
	 * @return 区间年月
	 */
	public static String getDateRegionMonth(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return null;
		}

		Calendar tmpDate = Calendar.getInstance();
		tmpDate.setTime(startDate);

		Calendar end = Calendar.getInstance();
		end.setTime(endDate);

		StringBuilder areaDate = new StringBuilder();
		while (tmpDate.compareTo(end) <= 0) {
			areaDate.append("'").append(formatDateToStr(tmpDate.getTime(), Constant.DATA_INTEFACE_DATEFORMATE_MONTH)).append("',");
			tmpDate.set(Calendar.MONTH, tmpDate.get(Calendar.MONTH) + 1);
		}

		return areaDate.length() > 0 ? areaDate.substring(0, areaDate.length() - 1) : null;
	}

	/**
	 * 获取开始日期到结束日期之间的所有年月(格式为yyyy-MM) <br>
	 * <li>各年月用"'"连接,之间用","隔开</li> <li>包含开始和结束日期</li>
	 * 
	 * @param startYear
	 *            开始日期
	 * @param endYear
	 *            结束日期
	 * @return 区间年月
	 */
	public static String getDateRegionMonth(Integer startYear, Integer endYear) {
		Calendar cl = Calendar.getInstance();
		cl.set(Calendar.YEAR, startYear);
		cl.set(Calendar.MONTH, 0);
		cl.set(Calendar.DAY_OF_MONTH, 1);

		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, endYear);
		c.set(Calendar.MONTH, 11);
		c.set(Calendar.DAY_OF_MONTH, 1);
		if (endYear < startYear)
			return "";
		return getDateRegionMonth(cl.getTime(), c.getTime());
	}

	/**
	 * 获取开始日期到结束日期之间的所有年
	 * 
	 * @param startYear
	 *            开始年
	 * @param endYear
	 *            结束年
	 * @return 之间的年(包括开始、结束年)
	 */
	public static String getDateRegion(Integer startYear, Integer endYear) {
		if (startYear == null || endYear == null)
			return null;
		StringBuilder sb = new StringBuilder();
		while (endYear - startYear >= 0) {
			if (sb.length() != 0)
				sb = sb.append(",");
			sb = sb.append("'").append(startYear).append("'");
			startYear++;
		}
		return sb.toString();
	}

	/**
	 * 将日期字符串的天向(前/后)推<br/>
	 * 			<pre><B>只操作天</B></pre>
	 * @param date
	 *            日期字符串
	 * @param day
	 *            向后推迟天数<br>
	 *            <ul>
	 *            	<li>当day > 0 : 则向后推迟</li>
	 *            	<li>当day < 0 : 则向前提前</li>
	 *            </ul>
	 * @return (提前/推迟)后的日期字符串[格式:yyyy-MM-dd]
	 */
	public static String getTime(String date, Integer day) {
		Date myDate = DateUtils.formatStrToDate(date, "yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(myDate);
		c.add(Calendar.DAY_OF_YEAR, day);
		return DateUtils.formatDateToStr(c.getTime(), "yyyy-MM-dd").toString();
	}

	/**
	 * 判断当前日期是否为当月1号
	 * 
	 * @return
	 */
	public static boolean isFirstDay() {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DATE);
		if (day == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 计算两个日期之间相差的天数<br>
	 * <li>起始日期1号开始到结束日期月份的最后一天的天数</li>
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 相差天数
	 */
	public static int daysBetweenDate(Date startDate, Date endDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(endDate);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONDAY) + 1);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 拼接字符串并转换为日期(yyyy-MM)
	 * 
	 * @param year
	 *            年份
	 * @param month
	 *            月份
	 * @return 年-月格式
	 */
	public static Date concatDate(String year, Integer month) {
		return formatStrToDate(year + "-" + StrUtils.formatStrLength(month, 2), Constant.DATA_INTEFACE_DATEFORMATE_MONTH);
	}

	/**
	 * 根据当前时间获取最近的几个月
	 * 
	 * @param lastMonth
	 *            需要最近几个月
	 * @return 最近的月数的开始日期与结束日期(start <= 日期 < end )
	 */
	@SuppressWarnings("deprecation")
	public static Date[] getRecentYearAndMonthByCurrent(int lastMonth) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date date = cal.getTime();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		cal.setTime(date);

		int month = cal.get(Calendar.MONTH);
		cal.set(Calendar.MONTH, month - lastMonth + 1);
		Date d1 = cal.getTime();

		cal.set(Calendar.MONTH, month + 1);
		Date d2 = cal.getTime();

		return new Date[] { d1, d2 };
	}

	/**
	 * 根据当前时间获取最近的一年
	 * 
	 * @return 最近的一年的开始日期与结束日期(start <= 日期 < end )
	 */
	@SuppressWarnings("deprecation")
	public static Date[] getLastYearByCurrent() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date date = cal.getTime();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		cal.setTime(date);

		int month = cal.get(Calendar.MONTH);
		cal.set(Calendar.MONTH, month + 1);
		Date d1 = cal.getTime();

		cal.set(Calendar.MONTH, month);
		Date d2 = cal.getTime();
		d2.setYear(d2.getYear() - 1);

		return new Date[] { d2, d1 };
	}

	/**
	 * 判断第一个日期是否早于第二个日期(当是同一天时为不早于)
	 * 
	 * @param date1
	 *            日期1
	 * @param date2
	 *            日期2
	 * @return 是否早于
	 */
	public static boolean ifBefore(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return false;
		if (org.apache.commons.lang3.time.DateUtils.isSameDay(date1, date2)) {
			return false;
		} else {
			return date1.before(date2);
		}
	}

	/**
	 * 判断第一个日期是否早于第二个日期(根据情况判断)
	 * 
	 * @param date1
	 *            日期1
	 * @param date2
	 *            日期2
	 * @param igEqal
	 *            是否忽略相等时，日期1小于日期2
	 * @return 是否早于
	 */
	public static boolean ifBefore(Date date1, Date date2, boolean igEqal) {
		if (date1 == null || date2 == null)
			return false;
		if (org.apache.commons.lang3.time.DateUtils.isSameDay(date1, date2)) {
			if (igEqal) {
				return false;
			} else {
				return true;
			}
		} else {
			return date1.before(date2);
		}
	}
	
}

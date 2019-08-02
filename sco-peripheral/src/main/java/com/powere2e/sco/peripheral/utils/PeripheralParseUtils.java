package com.powere2e.sco.peripheral.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 接口入库公用工具类
 * 
 * @author Joyce.li
 * @since 2015年8月13日 下午2:05:15
 * @version 1.0
 */
public class PeripheralParseUtils {
	public static final String yyyy_MM_dd = "yyyy-MM-dd";

	/**
	 * 将字符串转换为Date类型
	 * 
	 * @param date
	 * @return
	 */
	public static Date strToDate(String date) throws Exception {
		if (StringUtils.isNotBlank(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat(yyyy_MM_dd);
			return sdf.parse(date.trim());
		}
		return null;
	}

	/**
	 * 如果字符串不为null，就去除两端空格
	 * 
	 * @param str
	 * @return
	 */
	public static String trimString(String str) {
		if (StringUtils.isNotBlank(str)) {
			return str.trim();
		}
		return str;
	}

	/**
	 * 将字符串转换为BigDecimal类型
	 * 
	 * @param str
	 * @return
	 */
	public static BigDecimal strToBigDecimal(String str) {
		if (StringUtils.isNotBlank(str)) {
			return new BigDecimal(str.trim());
		}
		return new BigDecimal(0);
	}

	/**
	 * 将字符串转换为Integer类型
	 * 
	 * @param str
	 * @return
	 */
	public static Integer strToInteger(String str) {
		if (StringUtils.isNotBlank(str)) {
			return new Integer(Double.valueOf(str.trim()).intValue());
		}
		return new Integer(0);
	}
}

package com.powere2e.sco.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.commons.lang3.StringUtils;

/**
 * 数字格式化工具类
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月23日
 * @version 1.0
 */
public class DecimalFormatUtils {
	
	private static NumberFormat formatter = NumberFormat.getNumberInstance();

	/**
	 * 格式化Bigdecimal并设置相应精度
	 * 
	 * @param bigDecimal
	 *            待设置精度值
	 * @param precision
	 *            精度
	 * @param roundingMode
	 *            取舍规则
	 * @return 格式化后的数值
	 */
	public static BigDecimal formatBigDecimal(BigDecimal bigDecimal, int precision, int roundingMode) {
		if (bigDecimal == null)
			return null;
		return bigDecimal.setScale(precision, roundingMode);
	}

	/**
	 * 默认格式化为保留精度为5，四舍五入的数值
	 * 
	 * @param bigDecimal
	 *            待设置精度值
	 * @return 格式化后的数值
	 */
	public static BigDecimal formatBigDecimal(BigDecimal bigDecimal) {
		return formatBigDecimal(bigDecimal, Constant.DEFAULT_PRECISION, Constant.DEFAULT_ROUNDINGMODE);
	}

	/**
	 * 计算毛利率
	 * 
	 * @param purchasePrice
	 * 			采购价
	 * @param sellPrice
	 * 			销售价
	 * @return
	 * 			(销售价-采购价)/销售价
	 * @throws Exception
	 */
	public static BigDecimal divideBigDecimal(BigDecimal purchasePrice, BigDecimal sellPrice) throws Exception{
		return divideBigDecimal(purchasePrice, sellPrice, 2);
	}

	/**
	 * 计算毛利率
	 * 
	 * @param purchasePrice
	 * 			采购价
	 * @param sellPrice
	 * 			销售价
	 * @param scale 保留位数
	 * @return
	 * 			(销售价-采购价)/销售价
	 * @throws Exception
	 */
	public static BigDecimal divideBigDecimal(BigDecimal purchasePrice, BigDecimal sellPrice, int scale) throws Exception{
		if (sellPrice == null || purchasePrice == null) return null;
		return (sellPrice.subtract(purchasePrice)).divide(sellPrice, scale, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 导出excel时,判断数量是否为null。
	 * 
	 * @param sellQuantity
	 *            数量
	 * @return
	 */
	public static String bigDecimalToString(BigDecimal sellQuantity) {
		return (sellQuantity == null) ? "" : DecimalFormatUtils.formatBigDecimal(formatBigDecimal(sellQuantity, 2, Constant.DEFAULT_ROUNDINGMODE), "#,##0.00");
	}
	
	/**
	 * 数字格式化(加入千分位)
	 * 
	 * @param sellQuantity
	 *            值
	 * @param precision
	 *            精度
	 * @return
	 */
	public static String bigDecimalToString(BigDecimal sellQuantity,int precision) {
		StringBuffer sb = new StringBuffer("#,##0");
		if (precision != 0) {
			sb.append(".");
		}
		for (int i = 1; i <= precision; i++) {
			sb.append("0");
		}
		return (sellQuantity == null) ? "" : DecimalFormatUtils.formatBigDecimal(formatBigDecimal(sellQuantity, precision, Constant.DEFAULT_ROUNDINGMODE), sb.toString());
	}

	/**
	 * 将BigDecimal转成百分比
	 * 
	 * @param bd
	 *            数字
	 * @return 百分比字符串
	 */
	public static String formatBigPercent(BigDecimal bd) {
		if ("".equals(bd)||bd == null || bd.compareTo(BigDecimal.ZERO) == 0) {
			return "0.00%";
		} else {
			return bd.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP) + "%";
		}
	}
	
	/**
	 * 去掉BigDecimal后多余的零(传入时多少位去除最后没的零后返回最多5位)
	 * 
	 * @param bd
	 *            需要去掉数字后面零的数字
	 */
	public static String removeRemain(BigDecimal bd) {
		if (bd == null) return null;
		double d = bd.doubleValue();
		int i = bd.intValue();
		if (d == i) {
			return i + "";
		}
		return new DecimalFormat("#.#####").format(d);
	}
	
	/**
	 * 去掉BigDecimal后多余的零(自定义格式)
	 * 
	 * @param bd
	 *            需要去掉数字后面零的数字
	 */
	public static String removeRemain(BigDecimal bd, String pattern) {
		if (bd == null) {
			return null;
		} else if (StringUtils.isNotBlank(pattern)) {
			int i = StringUtils.substringAfterLast(pattern, ".").length();//看看保留位数
			bd = bd.setScale(i,RoundingMode.HALF_UP);//先四舍五入
		}
		double d = bd.doubleValue();
		int i = bd.intValue();
		DecimalFormat f = new DecimalFormat(pattern);
		if (d == i) {
			return f.format(i) + "";
		}
		return f.format(d);
	}
	
	/**
	 * 将BigDecimal格式化为对应格式
	 * 
	 * @param bd
	 *            需格式数据
	 * @param pattern
	 *            格式形式
	 * @return 格式化后字符串
	 * 
	 */
	public static String formatBigDecimal(BigDecimal bd, String pattern) {
		if (bd == null || pattern == null) return null;
		if (StringUtils.isNotBlank(pattern)) {
			int i = StringUtils.substringAfterLast(pattern, ".").length();//看看保留位数
			bd = bd.setScale(i,RoundingMode.HALF_UP);//先四舍五入
		}
		return new DecimalFormat(pattern)
			.format(new BigDecimal(removeRemain(bd)));
	}
	
	/**
	 * 将BigDecimal转成百分比
	 * 
	 * @param sellQuantityChange
	 *            数字
	 * @return 百分比字符串
	 */
	public static String bigDecimalToPercent(BigDecimal sellQuantityChange) {
		if (sellQuantityChange == null) {
			return "";
		} else if(Double.valueOf(sellQuantityChange.toString()) == 0){
			return "";
		}else{
			return DecimalFormatUtils.formatBigDecimal(formatBigDecimal(sellQuantityChange, 2, Constant.DEFAULT_ROUNDINGMODE), "#,##0.00") + "%";
		}
	}
	
	/**
	 * 保留指定位数的小数,不足补零
	 * @param value 值
	 * @param precision 精度
	 * @return 结果 如果异常返回空的字符串
	 */
	public static String roundNumber(Object value,int precision){
		if (value == null) {
			return "";
		} else {
			try {
				formatter.setMinimumFractionDigits(precision);
				formatter.setMaximumFractionDigits(precision);
				return formatter.format(Double.parseDouble(value.toString()));
			} catch (Exception e) {
				return "";
			}
		}
	}
	
	/**
	 * 获取Bigdecimal整数部分
	 * 
	 * @param bg
	 *            Bigdecimal
	 * @return 整数部分
	 */
	public static Integer getInteger(BigDecimal bg) {
		if (bg == null) return null;
		return bg.intValue();
	}
	
	/**
	 * 求Integer的和[如果有null,最后结果为null]
	 * 
	 * @param integers
	 *            一个或多个Integer
	 */
	public static Integer sumInteger(Integer...integers) {
		Integer sum = 0;
		for (Integer it : integers) {
			if (it == null) {
				return null;
			} else {
				sum += it;
			}
		}
		return sum;
	}
	
}

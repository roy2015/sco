package com.powere2e.sco.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigPath;

/**
 * 字符串工具类
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月23日
 * @version 1.0
 */
public class StrUtils {

	/**
	 * 将字符串按照指定位数换行
	 * 
	 * @param str
	 *            需处理字符串
	 * @param warpLength
	 *            间隔位数
	 * @param warpString
	 *            换行符
	 * @return 追加换行符后的内容
	 */
	public static String warpString(String str, int warpLength,
			String warpString) {
		// 字符串为空或处理位置不合理时直接返回
		if (StringUtils.isBlank(str) || warpLength <= 0)
			return str;
		int strLength = str.length(); // 字符串总长度
		if (strLength < warpLength)
			return str;

		StringBuilder sb = new StringBuilder();

		double currentCount = 0;// 记录字符个数，中文算两个，英语和数字算一个
		for (char c : str.toCharArray()) {
			if (Pattern.matches("[\u4e00-\u9fa5]", String.valueOf(c))) {
				// 如果是中文
				currentCount += 1.3;
			} else {// 不是中文
				currentCount++;
			}
			sb.append(c);
			if ((currentCount / 1.3) >= (warpLength + 0.0)) {// 如果达到换行个数要求
				sb.append(warpString);
				currentCount = 0;// 计数器清零
			}
		}

		/*
		 * int currentPoint = 0;//记录当前需处理位置 for (int i = 0; i * point <=
		 * strLength; i++) { currentPoint = i * point; if(currentPoint !=0 &&
		 * currentPoint <= strLength) { sb.append(str.substring((i-1)*point,
		 * currentPoint).concat(appendStr));//拼接之前数据并处理当前点 } }
		 * sb.append(str.substring(currentPoint));//追加未添加的字符
		 */
		return sb.toString();
	}

	/**
	 * 将字符串(除空和null)用符号"'"拼接起来
	 * 
	 * @param str
	 *            需拼接字符串
	 */
	public static String concatStr(String... str) {
		if (str == null)
			return null;
		String rs = "";
		for (String s : str) {
			if (!StringUtils.isBlank(s)) {
				rs += "'".concat(s).concat("',");
			}
		}
		return rs.length() == 0 ? null : rs.substring(0, rs.length() - 1);
	}
	
	/**
	 * 指定分隔符连接字符串
	 * 
	 * @param split
	 *            分隔符
	 * @param strs
	 *            字符串
	 * @return 连接后的字符串
	 */
	public static String concatStr(StringBuffer split, String... strs) {
		StringBuilder sb = new StringBuilder();
		for (String str : strs) {
			sb.append(str).append(split);
		}
		return sb.toString();
	}
	
	/**
	 * 将数组用指定字符串连接连接
	 * 
	 * @param arr
	 *            数组
	 * @param split
	 *            分隔符
	 * @return 字符串
	 */
	public static String arrayToStr(String[] arr, String split) {
		if (arr == null)
			return null;
		if (split == null)
			split = ",";
		return Arrays.toString(arr).replace("[", "").replace(", ", split)
				.replace("]", "");
	}

	/**
	 * 去掉字符串中重复元素
	 * 
	 * @param str
	 *            字符串
	 * @param split
	 *            分隔符
	 * @return 去重后的字符串
	 */
	public static String filterRepeatStr(String str, String split) {
		if (str == null || split == null)
			return str;
		Set<String> set = new HashSet<String>(Arrays.asList(str.split(split)));
		return set.toString().replaceAll("\\[", "").replaceAll("\\]", "")
				.replace(" ", "");
	}

	public static String changeEncodeType(String str) throws Exception {
		if (StringUtils.isNotBlank(str)) {
			return new String(str.getBytes(Constant.DEFAULT_ENCODED_2),
					Constant.DEFAULT_ENCODED_3);
		}
		return "";
	}

	/**
	 * 去除上传的excel中数据的空和换号
	 */
	public static String replaceBlankAndNewline(String str) {
		return str == null ? "" : str.trim().replaceAll("\\n", "")
				.replaceAll("	", "").replaceAll(" ", "");
	}

	/**
	 * 获取字符串中最后一次出现分隔符之前的字符串
	 * 
	 * @param str
	 *            字符串
	 * @param split
	 *            分隔符
	 * @return 最后出现分隔符之前的字符串
	 */
	public static String getStrBefLastSplit(String str, String split) {
		if (StringUtils.isBlank(split) || StringUtils.isBlank(str))
			return null;
		int i = str.lastIndexOf(split);
		if (i < 0) {
			return str;
		}
		return str.substring(0, i);
	}

	/**
	 * 获取字符串中最后一次出现分隔符之后的字符串
	 * 
	 * @param str
	 *            字符串
	 * @param split
	 *            分隔符
	 * @return 最后出现分隔符之后的字符串
	 */
	public static String getStrAftLastSplit(String str, String split) {
		if (StringUtils.isBlank(split) || StringUtils.isBlank(str))
			return null;
		int i = str.lastIndexOf(split);
		if (i < 0 || i == str.length() - 1)
			return null;
		return str.substring(i + 1);
	}

	/**
	 * 获取上传文件名称的原始名称(去除后面的时间戳)
	 * 
	 * @param fileName
	 *            含时间戳文件名称
	 * @return 去掉时间戳后的文件名称
	 */
	public static String getFileResourceName(String fileName) {
		if (StringUtils.isBlank(fileName))
			return null;
		String prefix = StringUtils.substringBeforeLast(fileName, "_");
		String suffix = StringUtils.substringAfterLast(fileName, ".");
		return prefix + "." + suffix;
	}

	/**
	 * 字符串前导加0
	 * 
	 * @param number
	 *            数字
	 * @param length
	 *            前导位数
	 * @return 补零后的字符串
	 */
	public static String formatStrLength(Integer number, int length) {
		return String.format("%0" + length + "d", number); // 这里可以补空格
	}

	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字长度为3,英文字符长度为1
	 * 
	 * @param String
	 *            s 需要得到长度的字符串
	 * @return int 得到的字符串长度
	 */
	public static int getLength(String s) {
		int valueLength = 0;
		if (s == null) {
			return valueLength;
		}
		String chinese = "[\u4e00-\u9fa5]";
		// 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
		for (int i = 0; i < s.length(); i++) {
			// 获取一个字符
			String temp = s.substring(i, i + 1);
			// 判断是否为中文字符
			if (temp.matches(chinese)) {
				// 中文字符长度为1
				valueLength += 3;
			} else {
				// 其他字符长度为0.5
				valueLength += 1;
			}
		}
		// 进位取整
		return valueLength;
	}

	/**
	 * 将异常信息转换为字符串输出
	 * 
	 * @param thw
	 *            异常对象
	 * @return 字符串信息
	 */
	public static String getStackMsgToStr(Throwable thw) {
		StringWriter sw = new StringWriter();
		thw.printStackTrace(new PrintWriter(sw, true));
		return sw.getBuffer().toString();
	}
	
	/**
	 * 拼接上传文件的路径
	 * 
	 * @param strs 需拼接字符串
	 */
	public static String concatUploadPathString(String... strs) {
		StringBuilder sb = new StringBuilder(ConfigPath.getUploadFilePath());
		for (String str : strs) {
			sb.append(str);
		}
		return sb.toString();
	}

	/**
	 * 格式化字符串百分比(带千分符,默认两位)
	 * 
	 * @param val
	 *            徐格式化的字符串
	 * @param scal
	 *            需保留位数
	 * @return 带千分符的两位小数百分比
	 */
	public static String formatterStrPercent(String val, int scal) {
		if (StringUtils.isBlank(val)) return "";
		int index = val.indexOf("%");
		if (index > -1 && val.length() < 2) return val;
		if (index > -1){
			val = val.substring(0, val.length() - 1);
		} 
		return DecimalFormatUtils.bigDecimalToString(new BigDecimal(val), scal) + "%"; 
	}

	/**
	 * 格式化字符串百分比(带千分符,默认两位)
	 * 
	 * @param val
	 *            徐格式化的字符串
	 * @return 带千分符的两位小数百分比
	 */
	public static String formatterStrPercent(String val) {
		return formatterStrPercent(val, 2);
	}
	
}

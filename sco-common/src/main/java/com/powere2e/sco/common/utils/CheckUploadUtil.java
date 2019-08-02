package com.powere2e.sco.common.utils;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import com.powere2e.frame.commons.common.Arith;

/**
 *  导入excel校验工具类
 * 	@author Joyce.li
 *  @since 2015年3月25日 上午10:04:20
 *  @version 1.0
 */
public class CheckUploadUtil {
	
	/**
	 * 判断是否是数字。
	 * @param obj
	 */
	public static boolean checkNumber(Object obj){
		try {
			new BigDecimal(obj.toString());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 判断是否是数字(不含小数)。
	 * @param obj
	 */
	public static boolean checkNumber2(Object obj){
		Pattern pattern = Pattern.compile("^[1-9][0-9]*$");
		return pattern.matcher(obj.toString()).matches();
	}
	
	/**
	 * 判断是否是数字或字母。
	 * @param obj
	 */
	public static boolean checkNumberOrLetters(Object obj){
		Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
		return pattern.matcher(obj.toString()).matches();
	}
	
	/**
	 * 判断是否是数字或字母或汉字。
	 * @param obj
	 */
	public static boolean checkNumberOrLettersOrChinese(Object obj){
		Pattern pattern = Pattern.compile("^[A-Za-z0-9\u4E00-\u9FA5]+$");
		return pattern.matcher(obj.toString()).matches();
	}
	
	/**
	 * 判断日期
	 * @param obj
	 */
	public static boolean checkDate(Object obj){
		Pattern pattern = Pattern.compile("^((?:19|20)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$");
		return pattern.matcher(obj.toString()).matches();
	}
	
	//首字母转大写
    public static String toUpperCaseFirstOne(String s)
    {
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    
    /**
     * 判断是否是null或者空
     * @param obj
     * 
     */
    public static boolean isNullOrEmpty(Object cellValue){
    	return (cellValue==null ||cellValue.toString().equals(""));
    }
    
    /**
     * 判断是否超长
     * @param cellValue
     * @param length
     * @return
     */
    public static boolean isTooLong(Object cellValue,int length){
    	return (cellValue.toString().length()>length);
    }
    
    /**
     * 去除两端的空格
     * @param obj
     * 
     */
    public static String trimObjToStr(Object cellValue){
    	return cellValue.toString().trim();
    }
    
	/**
	 * 返回只能是数字的校验信息
	 * 
	 * @param index
	 * @param columnName
	 * @return
	 */
	public static String getNumberNotice(int index, String columnName) {
		return "第" + (index + 1) + "行" + columnName + "只能为数字!<br/>";
	}

	/**
	 * 返回只能是日期格式的校验信息
	 * 
	 * @param index
	 * @param columnName
	 * @return
	 */
	public static String getDateNotice(int index, String columnName) {
		return "第" + (index + 1) + "行" + columnName + "只能为日期格式！(例如:2015-01-12)<br/>";
	}

	/**
	 * 返回只能是数字和字母的校验信息
	 * 
	 * @param index
	 * @param columnName
	 * @return
	 */
	public static String getNunberOrLettersNotice(int index, String columnName) {
		return "第" + (index + 1) + "行" + columnName + "只能为数字或字母!<br/>";
	}

	/**
	 * 返回只能是数字、字母和中文的校验信息
	 * 
	 * @param index
	 * @param columnName
	 * @return
	 */
	public static String getNumberOrLettersOrChineseNotice(int index, String columnName) {
		return "第" + (index + 1) + "行" + columnName + "只能为数字、字母或中文!<br/>";
	}

	/**
	 * 返回非空字段的校验信息
	 * 
	 * @param index
	 * @param columnName
	 * @return
	 */
	public static String getBlankNotice(int index, String columnName) {
		return "第" + (index + 1) + "行" + columnName + "不能为空!<br/>";
	}
	
	/**
	 * 返回映射找不到校验信息
	 * 
	 * @param index
	 * @param columnName
	 * @return
	 */
	public static String getMappingNotice(int index, String columnName) {
		return "第" + (index + 1) + "行" + columnName + "映射找不到!<br/>";
	}
	
	/**
	 * 字段长度过长校验
	 * 
	 * @return
	 */
	public static String getTooLongNotice(int index,String columnName) {
		return "第"+(index+1)+"行"+columnName+"长度超长!<br/>";
	}
	
	/**
	 * 将超过2位小数的数，进行四舍五入的截取，保留2位小数
	 * @param cellValue
	 * @return
	 */
	public static BigDecimal formateDoubleTo2Scale(Object cellValue){
		return new BigDecimal(Arith.round(Double.parseDouble(cellValue.toString()),2));
	}
	
}

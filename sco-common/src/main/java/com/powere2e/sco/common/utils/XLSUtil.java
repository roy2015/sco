/**
 * @File: XLSUtil.java
 *
 */
package com.powere2e.sco.common.utils;


import java.text.DecimalFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;

/**
 * 读取解析excel单元格工具类
 *  @author Joyce.li
 *  @since 2015年3月25日 下午6:05:49
 *  @version 1.0
 */
public class XLSUtil {

    public static Object getCellValue(HSSFCell cell) {
		Object value = null;
		
		
		/*
		 * ziliao:
		 * 上面用的是一般的方法，如果确定输入类型，比如你以double输入，可以采用相对的子类，

			NumberFormat df = new DecimalFormat("#0");
     		String str3 = df.format(num);

			你是要把科学计数法的数转成一般的数吗？可以用下面这个方法。
			double d = Double.parseDouble("-1.23E-12");
			
			
			
			
			当使用POI处理excel的时候，遇到了比较长的数字，虽然excel里面设置该单元格是文本类型的，但是POI的cell的类型就会变成数字类型。 

			而且无论数字是否小数，使用cell.getNumbericCellValue() 去获取值的时候，会得到一个double，而且当长度大一点的时候会变成科学计数法形式。 

			那么获取这个单元格的原始的数据，就其实是一个double怎么转换成整数的问题了。 

			使用DecimalFormat对这个double进行了格式话，随后使用format方法获得的String就是你想要的值了。 
			
			1.DecimalFormat df = new DecimalFormat("0");     
			2.String whatYourWant = df.format(cell.getNumericCellValue());  

		 */
//		DecimalFormat df = new DecimalFormat("0"); 
//		String str = "";
		if (cell != null) {
			//value = cell.getStringCellValue();
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_BLANK:
				//System.out.println("blank");
				value = "";
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				//System.out.println("boolean");
				value = new Boolean(cell.getBooleanCellValue());
				break;
			case HSSFCell.CELL_TYPE_ERROR:
			    //System.out.println("Cell Type Error");
				//value = new Byte(cell.getErrorCellValue());
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
				//System.out.println("formula");
				value = cell.getCellFormula();
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				//System.out.println("numeric");
			    /*if (HSSFDateUtil.isCellDateFormatted(cell)) {
				value = cell.getDateCellValue();
			    } else {
				value = new Double(cell.getNumericCellValue());
			    }*/
				
				//**********************************
				try{
					value = cell.getStringCellValue();
				}catch(Exception e){
					value = cell.getNumericCellValue();
				}
				//***************************************
				
				/*try{
					value = cell.getStringCellValue();
				}catch(Exception e){
					value = cell.getNumericCellValue();
					if((value+"").indexOf("E")>-1||(value+"").indexOf("e")>-1||(value+"").endsWith(".0")){
						str = df.format(cell.getNumericCellValue()); 
						value = str;
					}	
				}*/
				
				break;
				
	//		case HSSFCell.CELL_TYPE_STRING:
	//			//System.out.println("string");
	//			value = cell.getRichStringCellValue().getString();
	//			break;
			default:
				value = cell.getStringCellValue();
				
				
			}
	    }
	
		return value;
    }

    /*
     * add by alex at 2012-11-14
     */
	public static Object getCellValue2007(XSSFCell cell) {

		Object value = null;
		DecimalFormat df = new DecimalFormat("0.00"); 
		DecimalFormat df1 = new DecimalFormat("0"); 
		String str = "";
		if (cell != null) {
			//value = cell.getStringCellValue();
			//switch (cell.getCellType()) {
			switch (cell.getCellType()) {
				//case HSSFCell.CELL_TYPE_BLANK:
			   case XSSFCell.CELL_TYPE_BLANK:
					//System.out.println("blank");  
					value = "";
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					//System.out.println("boolean");
					value = new Boolean(cell.getBooleanCellValue());
					break;
				case XSSFCell.CELL_TYPE_ERROR:
				    //System.out.println("Cell Type Error");
					//value = new Byte(cell.getErrorCellValue());
					break;
				case XSSFCell.CELL_TYPE_FORMULA:
					//System.out.println("formula");
					value = cell.getCellFormula();
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					//System.out.println("numeric");
//					if (HSSFDateUtil.isCellDateFormatted(cell)) {
//					value = cell.getDateCellValue();
//				    } else {
//					value = new Double(cell.getNumericCellValue());
//				    }
					// strCell = String.valueOf(cell.getNumericCellValue()).substring(0, String.valueOf(cell.getNumericCellValue()).indexOf("."));
					///alex
					try{
						value = cell.getStringCellValue();
					}catch(Exception e){
						value = cell.getNumericCellValue();
						str = df.format(cell.getNumericCellValue());
						int a=str.lastIndexOf(".");
				    	String fileType = str.substring(a+1, str.length()).toLowerCase();
				    	if(fileType.equals("00")){
				    		str = df1.format(cell.getNumericCellValue());
				    	}
						value = str;		
					}
					break;
				//case HSSFCell.CELL_TYPE_STRING:
				//value = cell.getRichStringCellValue().getString();
				//break;
				default:
					try {
						value = cell.getDateCellValue();
					} catch (Exception e) {
						value = cell.getStringCellValue();
					} 
			}
	    }
		return value;
	}
	
	
	 /*
     * add by alex at 2012-11-14
     */
	public static Object getCellValue2007forMaped(Cell cell) {
		
		Object value = null;
		DecimalFormat df = new DecimalFormat("0.0000"); 
		DecimalFormat df1 = new DecimalFormat("0"); 
		String str = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			   case XSSFCell.CELL_TYPE_BLANK: 
					value = "";
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					value = new Boolean(cell.getBooleanCellValue());
					break;
				case XSSFCell.CELL_TYPE_ERROR:
					break;
				case XSSFCell.CELL_TYPE_FORMULA:
					value = cell.getCellFormula();
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					try{
						value = cell.getStringCellValue();
					}catch(Exception e){
						value = cell.getNumericCellValue();
						str = df.format(cell.getNumericCellValue());
						int a=str.lastIndexOf(".");
				    	String fileType = str.substring(a+1, str.length()).toLowerCase();
				    	if(fileType.equals("0000")){
				    		str = df1.format(cell.getNumericCellValue());
				    	}
						value = str;		
					}
					break;
				case XSSFCell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				default:
					try {
						value = cell.getDateCellValue();
					} catch (Exception e) {
						value = cell.getStringCellValue();
					} 
			}
	    }
		return value;
	}
}

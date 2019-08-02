package com.powere2e.sco.common.utils;

import java.awt.Color;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.powere2e.utils.excel.BigExcelWriter;

/**
 * Excel的相关方法
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月19日
 * @version 1.0
 */
public class ExcelUtils {

	/**
	 * 表头字体较大时使用(字体大小20号)
	 */
	public static final Integer HEADR_FONT_SIZE = 20;

	public static String left = "left";
	public static String right = "right";
	public static String center = "center";
	public static String top = "top";
	public static String bottom = "bottom";

	/**
	 * 默认格式 需要传入定义好的参数
	 * 
	 * @param wb
	 * @param format
	 */
	public static CellStyle setStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		setFrame(style, true);
		setFont(style, wb.createFont(), BigExcelWriter.GOLABLE_FONT_NAME,
				BigExcelWriter.GOLABLE_FONT_SIZE, false);// 字体
		return style;
	}

	/**
	 * 表头格式
	 * 
	 * @param wb
	 * @param format
	 */
	public static CellStyle setStyle_header(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		setFont(style, font, BigExcelWriter.GOLABLE_FONT_NAME,
				BigExcelWriter.GOLABLE_FONT_SIZE, true);
		setFrame(style, true);
		setAlign(style, ExcelUtils.center, ExcelUtils.center, true);
		return style;
	}

	/**
	 * 数字格式
	 * 
	 * @param wb
	 * @param formatType
	 *            自定义格式，例如定义为“0.00,,”，则数字1200000.156表现为1.20
	 */
	public static CellStyle setStyle_number(Workbook wb, String formatType) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		DataFormat format = wb.createDataFormat();
		setFont(style, font, BigExcelWriter.GOLABLE_FONT_NAME,
				BigExcelWriter.GOLABLE_FONT_SIZE, true);
		setFrame(style, true);
		setAlign(style, ExcelUtils.right, ExcelUtils.center, false);
		setFormat(style, format, formatType);
		return style;
	}

	/**
	 * 日期格式
	 * 
	 * @param wb
	 * @param formatType
	 *            自定义格式，例如定义为“dd-mmm”，则日期2013-01-01表现为“01-JAN”
	 */
	public static CellStyle setStyle_date(Workbook wb, String formatType) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		DataFormat format = wb.createDataFormat();
		setFont(style, font, BigExcelWriter.GOLABLE_FONT_NAME,
				BigExcelWriter.GOLABLE_FONT_SIZE, true);
		setFrame(style, true);
		setAlign(style, ExcelUtils.center, ExcelUtils.center, false);
		setFormat(style, format, formatType);
		return style;
	}

	/**
	 * 设置字体
	 * 
	 * @param style
	 * @param font
	 * @param fontType
	 *            字体的名称，例如“宋体”、“楷体”等
	 * @param fontSize
	 *            字体的大小，例如正常为 11
	 * @param isOrNotOverstrike
	 *            是否加粗 “yes” or “no”
	 */
	public static void setFont(CellStyle style, Font font, String fontName,
			int fontSize, boolean isOrNotOverstrike) {
		// 设置字体类型
		font.setFontName(fontName);
		font.setFontHeightInPoints((short) fontSize);
		if (isOrNotOverstrike) {
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);// 粗体显示
		}
		style.setFont(font);
	}

	/**
	 * 设置红色字体
	 * 
	 * @param style
	 * @param font
	 * @param fontType
	 *            字体的名称，例如“宋体”、“楷体”等
	 * @param fontSize
	 *            字体的大小，例如正常为 11
	 * @param isOrNotOverstrike
	 *            是否加粗 “yes” or “no”
	 */
	public static void setRedFont(CellStyle style, Font font, String fontName,
			int fontSize, boolean isOrNotOverstrike) {
		// 设置字体类型
		font.setFontName(fontName);
		font.setColor(Font.COLOR_RED);
		font.setFontHeightInPoints((short) fontSize);
		if (isOrNotOverstrike) {
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);// 粗体显示
		}
		style.setFont(font);
	}

	/**
	 * 设置边框（包含上下左右）
	 * 
	 * @param style
	 * @param isOrNotframe
	 *            是否设置边框，“yes”代表设置
	 */
	public static void setFrame(CellStyle style, boolean isOrNotframe) {
		if (isOrNotframe) {
			style.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
			style.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
			style.setBorderTop(CellStyle.BORDER_THIN);// 上边框
			style.setBorderRight(CellStyle.BORDER_THIN);// 右边框
		}
	}

	/**
	 * 设置对其方式
	 * 
	 * @param style
	 * @param crosswiseAlign
	 *            横向 参数为
	 *            left、right、center，为了保证填的正确，请用系统定义参数，例如ExcelStyleUtils.left
	 * @param lengthwiseAlign
	 *            纵向 参数为
	 *            top、bottom、center，为了保证填的正确，请用系统定义参数，例如ExcelStyleUtils.top
	 * @param wrapText
	 *            是否自动换行
	 */
	public static void setAlign(CellStyle style, String crosswiseAlign,
			String lengthwiseAlign, boolean wrapText) {
		if ("right".equalsIgnoreCase(crosswiseAlign)) {
			// 右对齐
			style.setAlignment(CellStyle.ALIGN_RIGHT);
		} else if ("left".equalsIgnoreCase(crosswiseAlign)) {
			// 左对齐
			style.setAlignment(CellStyle.ALIGN_LEFT);
		} else if ("center".equalsIgnoreCase(crosswiseAlign)) {
			// 居中
			style.setAlignment(CellStyle.ALIGN_CENTER);
		}
		if ("top".equalsIgnoreCase(lengthwiseAlign)) {
			style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
		} else if ("center".equalsIgnoreCase(lengthwiseAlign)) {
			style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		} else if ("bottom".equalsIgnoreCase(lengthwiseAlign)) {
			style.setVerticalAlignment(CellStyle.VERTICAL_BOTTOM);
		}
		if (wrapText) {
			style.setWrapText(true);// 设置自动换行
		}
	}

	/**
	 * 设置单元格内的表现形式，例如日期的表现形式：“yyyy-MM-dd”,数字的表现形式：“0.00,,”等
	 * 
	 * @param defaultStyl
	 * @param format
	 * @param formatType
	 *            自定义格式，例如定义为“0.00,,”，则数字1200000.156表现为1.20
	 */
	public static void setFormat(CellStyle style, DataFormat format,
			String formatType) {
		style.setDataFormat(format.getFormat(formatType));
	}

	/**
	 * 设置背景色 此方法用数字设置
	 * 
	 * @param wb
	 * @param a
	 *            红色所占的的数字
	 * @param b
	 *            绿色所占的的数字
	 * @param c
	 *            蓝色所占的的数字
	 */
	public static void setColor(XSSFWorkbook wb, int a, int b, int c) {
		Color color = new Color(a, b, c);
		XSSFCellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(new XSSFColor(color));
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	}

	/**
	 * 设置背景色 此方法用16进制数表示设置
	 * 
	 * @param wb
	 * @param colorDecode
	 *            16进制数参数，如：“FFFFC8”
	 */
	public static void setColor(XSSFWorkbook wb, String colorDecode) {
		Color color = new Color(0, 0, 0);
		Color.decode(colorDecode);
		XSSFCellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(new XSSFColor(color));
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	}

	/**
	 * 向sheet中添加合并单元格(通过给定引用地址合并)
	 * 
	 * @param sheet
	 * @param refs
	 *            合并单元格引用序列
	 */
	public static void addMergedRegion(Sheet sheet, String... refs) {
		if (refs != null && refs.length > 0) {
			for (String ref : refs) {
				sheet.addMergedRegion(CellRangeAddress.valueOf(ref));
			}
		}
	}

	/**
	 * 添加合并单元格(根据具体行列号进行合并)
	 * 
	 * @param sheet
	 * @param firstRow
	 * @param lastRow
	 * @param firstCol
	 * @param lastCol
	 */
	public static void addMergedRegion(Sheet sheet, int firstRow, int lastRow,
			int firstCol, int lastCol) {
		sheet.addMergedRegion(CellRangeAddress.valueOf(CellReference
				.convertNumToColString(firstCol)
				+ (firstRow + 1) + ":"
				+ CellReference.convertNumToColString(lastCol) + (lastRow + 1)));
	}

	/**
	 * 获取系统表头(标题)统一使用的颜色
	 * 
	 * @return
	 */
	public static Color getDefaultHeaderColor() {
		return new Color(184, 204, 228);
	}

	/**
	 * 设置sheet第一行相关样式(宋体、加粗、居中、边框、系统统一的背景颜色)<br/>
	 * 若没有传入字体的字号，默认使用11号
	 * 
	 * @param wb
	 *            工作簿
	 * @param fontSize
	 *            字体大小
	 * @return 表头样式
	 */
	public static CellStyle getHeaderCellStyle(Workbook wb, Integer fontSize) {
		if (fontSize == null) {
			fontSize = BigExcelWriter.GOLABLE_FONT_SIZE;// 默认使用11号字体
		}
		XSSFCellStyle firstRowStyle = (XSSFCellStyle) wb.createCellStyle();
		// 设置字体
		ExcelUtils.setFont(firstRowStyle, wb.createFont(),
				BigExcelWriter.GOLABLE_FONT_NAME, fontSize, true);// 字体

		// 背景颜色
		firstRowStyle.setFillForegroundColor(new XSSFColor(
				getDefaultHeaderColor()));
		firstRowStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

		// 内容居中
		ExcelUtils.setAlign(firstRowStyle, "center", "center", false);
		// 设置边框
		ExcelUtils.setFrame(firstRowStyle, true);

		return firstRowStyle;
	}

	/**
	 * 单元格字符串样式
	 * 
	 * @param wb
	 *            工作簿
	 * @return 字符串样式
	 */
	public static CellStyle getDefaultStringStyle(Workbook wb) {
		XSSFCellStyle stringCellStyle = (XSSFCellStyle) wb.createCellStyle();
		// 设置字体
		ExcelUtils.setFont(stringCellStyle, wb.createFont(),
				BigExcelWriter.GOLABLE_FONT_NAME,
				BigExcelWriter.GOLABLE_FONT_SIZE, false);// 字体

		// 内容居左
		ExcelUtils.setAlign(stringCellStyle, "left", "left", false);
		// 设置边框
		ExcelUtils.setFrame(stringCellStyle, true);

		return stringCellStyle;
	}

	/**
	 * 单元格字符串样式红色
	 * 
	 * @param wb
	 *            工作簿
	 * @return 字符串样式
	 */
	public static CellStyle getRedStringStyle(Workbook wb) {
		XSSFCellStyle stringCellStyle = (XSSFCellStyle) wb.createCellStyle();
		// 设置字体
		ExcelUtils.setRedFont(stringCellStyle, wb.createFont(),
				BigExcelWriter.GOLABLE_FONT_NAME,
				BigExcelWriter.GOLABLE_FONT_SIZE, false);// 字体

		// 内容居左
		ExcelUtils.setAlign(stringCellStyle, "left", "left", false);
		// 设置边框
		ExcelUtils.setFrame(stringCellStyle, true);

		return stringCellStyle;
	}

	/**
	 * 时间样式
	 * 
	 * @param wb
	 *            工作簿
	 * @return 时间样式
	 */
	public static CellStyle getDefaultDateStyle(Workbook wb) {
		XSSFCellStyle dateCellStyle = (XSSFCellStyle) wb.createCellStyle();
		// 设置字体
		ExcelUtils.setFont(dateCellStyle, wb.createFont(),
				BigExcelWriter.GOLABLE_FONT_NAME,
				BigExcelWriter.GOLABLE_FONT_SIZE, false);// 字体

		DataFormat dayDateFormat = wb.createDataFormat();
		dateCellStyle.setDataFormat(dayDateFormat
				.getFormat(Constant.DATA_INTEFACE_DATEFORMATE));

		// 内容居中
		ExcelUtils.setAlign(dateCellStyle, "center", "center", false);
		// 设置边框
		ExcelUtils.setFrame(dateCellStyle, true);

		return dateCellStyle;
	}

	/***
	 * 默认数字格式样式
	 * 
	 * @param wb
	 *            工作簿
	 * @return 金额样式
	 */
	public static CellStyle getDefaultAmoutStyle(Workbook wb) {
		XSSFCellStyle AmtCellStyl = (XSSFCellStyle) wb.createCellStyle();
		// 设置字体
		ExcelUtils.setFont(AmtCellStyl, wb.createFont(),
				BigExcelWriter.GOLABLE_FONT_NAME,
				BigExcelWriter.GOLABLE_FONT_SIZE, false);// 字体
		// 设置精度
		DataFormat format = wb.createDataFormat();
		AmtCellStyl
				.setDataFormat(format.getFormat(Constant.DEFAULT_AMT_FORMAT));
		// 内容居右
		ExcelUtils.setAlign(AmtCellStyl, "right", "right", false);
		// 设置边框
		ExcelUtils.setFrame(AmtCellStyl, true);
		return AmtCellStyl;
	}

	/***
	 * 默认百分比格式样式
	 * 
	 * @param wb
	 *            工作簿
	 * @return 金额样式
	 */
	public static CellStyle getDefaultPercentStyle(Workbook wb) {
		XSSFCellStyle percentCellStyl = (XSSFCellStyle) wb.createCellStyle();
		// 设置字体
		ExcelUtils.setFont(percentCellStyl, wb.createFont(),
				BigExcelWriter.GOLABLE_FONT_NAME,
				BigExcelWriter.GOLABLE_FONT_SIZE, false);// 字体
		// 内容居右
		ExcelUtils.setAlign(percentCellStyl, "right", "right", false);
		// 设置边框
		ExcelUtils.setFrame(percentCellStyl, true);
		return percentCellStyl;
	}

	/**
	 * 对导出excel的文件名进行编码
	 * 
	 * @param exportFileName
	 *            需编码的文件名称
	 * @return 编码后的名称
	 * @throws Exception
	 */
	public static String getEncodeFileName(String exportFileName)
			throws Exception {
		return exportFileName;
	}
	
	/**
	 * 对导出excel的文件名进行编码
	 * 
	 * @param exportFileName
	 *            需编码的文件名称
	 * @param request
	 *            请求对象
	 * @return 编码后的名称
	 * @throws Exception
	 */
	public static String getEncodeFileName(String exportFileName,HttpServletRequest request)
			throws Exception {
		String agent = request.getHeader("USER-AGENT");
		if (StringUtils.isNotBlank(agent)) {
			/*if (-1 != agent.indexOf("Firefox")) {		// Firefox
				exportFileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(exportFileName.getBytes("UTF-8"))))
						+ "?=";
			} else if (-1 != agent.indexOf("Chrome")) {	// Chrome
				exportFileName = new String(exportFileName.getBytes(), "ISO8859-1");
			} */
			if (agent.indexOf("Firefox") == -1 && agent.indexOf("Chrome") == -1) {		// IE7+
				exportFileName = URLEncoder.encode(exportFileName, "UTF-8");
				exportFileName = StringUtils.replace(exportFileName, "+", "%20");// 替换空格
			}
		}
		return exportFileName;
	}

	/**
	 * 创建某行的cell并将其合并
	 * 
	 * @param sheet
	 *            当前sheet
	 * @param row
	 *            当前行
	 * @param colSize
	 *            创建列大小
	 * @param cellStyle
	 *            样式
	 * @param region
	 *            合并区域
	 * @param value
	 *            合并区域值
	 */
	public static void createCellAndmerged(Sheet sheet, Row row, int startNum,
			int colSize, CellStyle cellStyle, String region, String value) {
		Cell cell = row.createCell(startNum++);
		cell.setCellValue(value);
		cell.setCellStyle(cellStyle);
		// 创建后面的cell
		for (int i = startNum; i < colSize; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(cellStyle);
		}
		ExcelUtils.addMergedRegion(sheet, region);
	}

	/**
	 * 创建一个大型Excel文件输出辅助类对象
	 * 
	 * @param cacheRow
	 *            缓存中数据的行数，超过这个值后，会自动将超过之前的数据写入文件
	 * @param ifCompress
	 *            是否压缩，压缩会减少占用空间，对性能有一定影响，注意合理使用
	 * @return SXSSFWorkbook工作簿
	 */
	public static SXSSFWorkbook createSXSSFWorkbook(int cacheRow,
			boolean ifCompress) {
		SXSSFWorkbook wb = new SXSSFWorkbook(cacheRow);
		wb.setCompressTempFiles(ifCompress);
		return wb;
	}

	/**
	 * 创建一个大型Excel文件输出辅助类对象
	 *			<li>默认压缩数据</li>
	 *			<li>默认缓存100条数据</li>
	 * @return SXSSFWorkbook工作簿
	 */
	public static SXSSFWorkbook createSXSSFWorkbook() {
		return createSXSSFWorkbook(100, true);
	}
	
}

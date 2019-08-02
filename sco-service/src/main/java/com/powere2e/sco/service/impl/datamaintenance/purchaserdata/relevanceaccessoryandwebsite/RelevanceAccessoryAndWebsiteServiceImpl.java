package com.powere2e.sco.service.impl.datamaintenance.purchaserdata.relevanceaccessoryandwebsite;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.DecimalFormatUtils;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.relevanceaccessoryandwebsite.RelevanceAccessoryAndWebsiteDao;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.relevanceaccessoryandwebsite.RelevanceAccessoryAndWebsiteService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.relevanceaccessoryandwebsite.RelevanceAccessoryAndWebsite;
import com.powere2e.sco.service.impl.common.MasterDataTypeServiceImpl;

/**
 * 辅料与公示网站关联 业务类的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月17日
 */
public class RelevanceAccessoryAndWebsiteServiceImpl extends ServiceImpl
		implements RelevanceAccessoryAndWebsiteService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2351035321083047529L;
	private RelevanceAccessoryAndWebsiteDao relevanceAccessoryAndWebsiteDao;

	public static RelevanceAccessoryAndWebsiteService getInstance() {
		return (RelevanceAccessoryAndWebsiteService) ConfigFactory.getInstance()
				.getBean("relevanceAccessoryAndWebsiteService");
	}

	// 获得DAO实例
	public RelevanceAccessoryAndWebsiteDao getRelevanceAccessoryAndWebsiteDao() {
		return relevanceAccessoryAndWebsiteDao;
	}

	// 设置DAO实例
	public void setRelevanceAccessoryAndWebsiteDao(
			RelevanceAccessoryAndWebsiteDao relevanceAccessoryAndWebsiteDao) {
		this.relevanceAccessoryAndWebsiteDao = relevanceAccessoryAndWebsiteDao;
	}

	// 查询
	@Override
	public List<RelevanceAccessoryAndWebsite> listRelevanceAccessoryAndWebsite(
			Map<String, Object> map, PageInfo pageInfo) {
		return this.getRelevanceAccessoryAndWebsiteDao().listRelevanceAccessoryAndWebsite(map, pageInfo);
	}

	// 关联
	@Override
	public void insertRelevanceAccessoryAndWebsite(
			RelevanceAccessoryAndWebsite relevanceAccessoryAndWebsite) {
		//校验
		boolean flag = this.ifExistsReleAccessoryAndWeb(relevanceAccessoryAndWebsite);
		if(flag) {
			throw new EscmException("该辅料原料已关联到相同的公示网站原料下，无需再次关联");
		}
		relevanceAccessoryAndWebsite.setId(MasterDataTypeServiceImpl.getInstance().nextID("s_accessory_relevance_materia"));
		this.getRelevanceAccessoryAndWebsiteDao().insertRelevanceAccessoryAndWebsite(relevanceAccessoryAndWebsite.toMap());
	}

	/**
	 * 验证是否存在重复数据
	 * 
	 * @param releAccAndWebsite
	 *            关联实例
	 */
	private boolean ifExistsReleAccessoryAndWeb(
			RelevanceAccessoryAndWebsite releAccAndWebsite) {
		String id = this.relevanceAccessoryAndWebsiteDao.ifExistsReleAccessoryAndWeb(releAccAndWebsite.toMap());
		return !StringUtils.isBlank(id);
	}

	// 取消关联
	@Override
	public void deleteRelevanceAccessoryAndWebsite(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		this.getRelevanceAccessoryAndWebsiteDao().deleteRelevanceAccessoryAndWebsite(map);
	}

	@Override
	public void exportDataToExcel(Map<String, Object> map, ServletOutputStream out) {
		List<RelevanceAccessoryAndWebsite> list = this.listRelevanceAccessoryAndWebsite(map, null);
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("辅料原料信息列表");

		// 1. 创建excel表头(第一行和第二行)
		this.fillHeaderCell(wb, sheet);

		// 2.用数据填充单元格
		if (!list.isEmpty())
			this.fillDataCell(list, wb, sheet);
		try {
			wb.write(out);
		} catch (Exception e) {
			throw new EscmException("导出Excel时异常",
					new String[] { e.getMessage() });
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				throw new EscmException("导出Excel时异常",
						new String[] { e.getMessage() });
			}
		}
	}

	/**
	 * 创建excel表头(第一行和第二行)
	 * 
	 * @param wb
	 *            工作簿
	 * @param sheet
	 *            sheet
	 */
	private void fillHeaderCell(Workbook wb, Sheet sheet) {
		// 第一行
		Row row = sheet.createRow(0);
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb,
				ExcelUtils.HEADR_FONT_SIZE);
		Cell cell = row.createCell(0);
		cell.setCellValue("辅料原料信息列表");
		cell.setCellStyle(firstRowStyle);
		for (int i = 1; i < 15; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "A1:O1");

		// 第二行
		row = sheet.createRow(1);
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式
		
		int i = 0;// 从第零列(第一列)开始

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品编号");
		
		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品名称");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商编号");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商名称");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("细分类");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("辅料原料名称");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("克重");
		
		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("公示网站公示网站原料名称");
		
		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("公示网站名称");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("公示网站地址");
		
		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("价格地区");
		
		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("原料大类");
		
		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("原料小类");
		
		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("关联人");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("关联时间");
		
	}

	/**
	 * 为sheet填充数据
	 * 
	 * @param sheet
	 *            sheet
	 * @param wb
	 *            工作簿
	 * @param list
	 *            单据明细list
	 */
	private void fillDataCell(List<RelevanceAccessoryAndWebsite> list, Workbook wb,
			Sheet sheet) {
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		CellStyle dateStyle = ExcelUtils.getDefaultDateStyle(wb);// 字符串样式
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式

		for (int i = 0; i < list.size(); i++) {
			writeOneRowDate(sheet, i, list.get(i), strStyle, dateStyle,
					amtStyle);
		}
	}
	
	/**
	 * 
	 * @param sheet
	 *            sheet
	 * @param rowIndex
	 *            当前行
	 * @param list
	 *            数据list
	 * @param strStyle
	 *            字符串数据格式
	 * @param dateStyle
	 *            时间日期格式
	 * @param amtStyle
	 *            金额、数量格式
	 */
	private void writeOneRowDate(Sheet sheet, Integer rowIndex,
			RelevanceAccessoryAndWebsite releAccAndWeb, CellStyle strStyle, CellStyle dateStyle,
			CellStyle amtStyle) {
		Row row = sheet.createRow(rowIndex + 2);

		int j = 0;
		Cell cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(releAccAndWeb.getIntentionCode() );

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(releAccAndWeb.getIntentionName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		String supplierCode = releAccAndWeb.getSupplierCode();
		cell.setCellValue(supplierCode == null ? 
				releAccAndWeb.getIntentionSupplierCode() : supplierCode);
		
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		String supplierName = releAccAndWeb.getSupplierName();
		cell.setCellValue(supplierName == null ? 
				releAccAndWeb.getIntentionSupplierName() : supplierName);
		
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(releAccAndWeb.getFineTypeName());
		
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(releAccAndWeb.getAccessoryName());
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal weight = releAccAndWeb.getWeight();
		if (weight != null) cell.setCellValue(DecimalFormatUtils.removeRemain(weight, "#.##"));
		
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(releAccAndWeb.getWebsiteMaterialName());
		
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(releAccAndWeb.getWebsiteName());
		
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(releAccAndWeb.getWebsiteUrl());
		
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(releAccAndWeb.getPriceRegion());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(releAccAndWeb.getMaterialBigTypeName());
	
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(releAccAndWeb.getMaterialSmallTypeName());
		
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(releAccAndWeb.getCreateby());
		
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		Date created = releAccAndWeb.getCreated();
		if (created != null) cell.setCellValue(DateUtils.formatDateToStr(created, Constant.DATA_INTEFACE_DATEFORMATE));
		
	}
	
}
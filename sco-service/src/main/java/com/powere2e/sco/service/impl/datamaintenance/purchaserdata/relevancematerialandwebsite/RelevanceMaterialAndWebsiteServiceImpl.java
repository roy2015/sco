package com.powere2e.sco.service.impl.datamaintenance.purchaserdata.relevancematerialandwebsite;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.relevancematerialandwebsite.RelevanceMaterialAndWebsiteDao;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.relevancematerialandwebsite.RelevanceMaterialAndWebsiteService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.relevancematerialandwebsite.RelevanceMaterialAndWebsite;
import com.powere2e.sco.service.impl.common.MasterDataTypeServiceImpl;

/**
 * 商品原料与公示网站关联 业务类的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月13日
 */
public class RelevanceMaterialAndWebsiteServiceImpl extends ServiceImpl
		implements RelevanceMaterialAndWebsiteService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2351035321083047529L;
	private RelevanceMaterialAndWebsiteDao relevanceMaterialAndWebsiteDao;

	public static RelevanceMaterialAndWebsiteService getInstance() {
		return (RelevanceMaterialAndWebsiteService) ConfigFactory.getInstance()
				.getBean("relevanceMaterialAndWebsiteService");
	}

	// 获得DAO实例
	public RelevanceMaterialAndWebsiteDao getRelevanceMaterialAndWebsiteDao() {
		return relevanceMaterialAndWebsiteDao;
	}

	// 设置DAO实例
	public void setRelevanceMaterialAndWebsiteDao(
			RelevanceMaterialAndWebsiteDao relevanceMaterialAndWebsiteDao) {
		this.relevanceMaterialAndWebsiteDao = relevanceMaterialAndWebsiteDao;
	}

	// 查询
	@Override
	public List<RelevanceMaterialAndWebsite> listRelevanceMaterialAndWebsite(
			Map<String, Object> map, PageInfo pageInfo) {
		return this.getRelevanceMaterialAndWebsiteDao().listRelevanceMaterialAndWebsite(map, pageInfo);
	}

	// 关联
	@Override
	public void insertRelevanceMaterialAndWebsite(
			RelevanceMaterialAndWebsite relevanceMaterialAndWebsite) {
		//校验
		boolean flag = this.ifExistsReleMateAndWeb(relevanceMaterialAndWebsite);
		if(flag) {
			throw new EscmException("该商品原料已关联到相同的公示网站原料下，无需再次关联");
		}
		relevanceMaterialAndWebsite.setId(MasterDataTypeServiceImpl.getInstance().nextID("s_accessory_relevance_materia"));
		this.getRelevanceMaterialAndWebsiteDao().insertRelevanceMaterialAndWebsite(relevanceMaterialAndWebsite.toMap());
	}

	/**
	 * 验证是否存在重复数据
	 * 
	 * @param releMaterialAndWeb
	 *            关联实例
	 */
	private boolean ifExistsReleMateAndWeb(
			RelevanceMaterialAndWebsite releMaterialAndWeb) {
		String id = this.relevanceMaterialAndWebsiteDao.ifExistsReleMateAndWeb(releMaterialAndWeb.toMap());
		return !StringUtils.isBlank(id);
	}

	// 取消关联
	@Override
	public void deleteRelevanceMaterialAndWebsite(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		this.getRelevanceMaterialAndWebsiteDao().deleteRelevanceMaterialAndWebsite(map);
	}

	@Override
	public void exportDataToExcel(List<RelevanceMaterialAndWebsite> list, ServletOutputStream out) {
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("商品原料信息列表");

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

	@Override
	public void completeNotLinkMaterialAndWebsite(String inCode, String matCode) {
		List<RelevanceMaterialAndWebsite> list = new ArrayList<RelevanceMaterialAndWebsite>();
		if (inCode != null && matCode.length() > 0) {
			String[] inc = inCode.split(",");
			String[] mat = matCode.split(",");
			for (int i = 0; i < inc.length; i++) {
				list.add(new RelevanceMaterialAndWebsite(inc[i], mat[i]));
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		this.getRelevanceMaterialAndWebsiteDao().completeNotLinkMaterialAndWebsite(map);
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
		cell.setCellValue("商品原料信息列表");
		cell.setCellStyle(firstRowStyle);
		for (int i = 1; i < 22; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "A1:V1");

		// 第二行
		row = sheet.createRow(1);
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式
		
		int i = 0;// 从第零列(第一列)开始

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品编号");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (120 * 90));// 设置当前行单元格宽度
		cell.setCellValue("商品名称");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商编号");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (120 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商名称");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("是否已关联公示网站原料");
		
		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("投入成本占比");
		
		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品定性角色");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品定量角色");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("中分类");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("小分类");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("明细类");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("细分类");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品原料类型");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (120 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品原料名称");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (120 * 80));// 设置当前行单元格宽度
		cell.setCellValue("公示网站公示网站原料名称");
		
		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (120 * 80));// 设置当前行单元格宽度
		cell.setCellValue("公示网站名称");
		
		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (120 * 80));// 设置当前行单元格宽度
		cell.setCellValue("公示网站地址");
		
		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("价格地区");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("原料大类");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("原料小类");
		
		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("关联人");
		
		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
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
	private void fillDataCell(List<RelevanceMaterialAndWebsite> list, Workbook wb,
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
			RelevanceMaterialAndWebsite relMateWeb, CellStyle strStyle, CellStyle dateStyle,
			CellStyle amtStyle) {
		Row row = sheet.createRow(rowIndex + 2);

		int j = 0;
		Cell cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(relMateWeb.getMerchandiseCode());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(relMateWeb.getMerchandiseName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(relMateWeb.getSupplierCode());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(relMateWeb.getSupplierName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		String ifRelevanced = relMateWeb.getIfRelevanced();
		if ("Y".equalsIgnoreCase(ifRelevanced)) {
			cell.setCellValue("已关联");
		} else if ("N".equalsIgnoreCase(ifRelevanced)) {
			cell.setCellValue("未关联");
		} else {
			cell.setCellValue("暂不关联");
		}
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		Double inputCost = relMateWeb.getInputCost();
		if (inputCost != null) {//不等于null
			if (inputCost != 0) {  //不等于0
				cell.setCellValue(DecimalFormatUtils.formatBigDecimal(
						new BigDecimal(inputCost), "#.##") + "%");
	} else {
				cell.setCellValue("0");
			}
		}

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(relMateWeb.getDxRoleName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(relMateWeb.getDlRoleName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(relMateWeb.getCentreName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(relMateWeb.getSmallName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(relMateWeb.getDetailName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(relMateWeb.getFineName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(relMateWeb.getMaterialTypeName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(relMateWeb.getIngredientCodeName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(relMateWeb.getMaterialName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(relMateWeb.getWebsiteName());
		
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(relMateWeb.getWebsiteUrl());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(relMateWeb.getPriceRegion());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(relMateWeb.getMaterialBigTypeName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(relMateWeb.getMaterialSmallTypeName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(relMateWeb.getCreateby());
		
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		Date created = relMateWeb.getCreated();
		if(created != null) {
			cell.setCellValue(DateUtils.formatDateToStr(created, Constant.DATA_INTEFACE_DATEFORMATE));
		}
	}
	
}
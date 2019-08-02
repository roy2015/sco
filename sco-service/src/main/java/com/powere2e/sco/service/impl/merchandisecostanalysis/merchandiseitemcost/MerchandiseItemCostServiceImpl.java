package com.powere2e.sco.service.impl.merchandisecostanalysis.merchandiseitemcost;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.DecimalFormatUtils;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.FreeMarkerUtil;
import com.powere2e.sco.common.utils.LoggerUtil;
import com.powere2e.sco.interfaces.dao.merchandisecostanalysis.merchandiseitemcost.MerchandiseItemCostDao;
import com.powere2e.sco.interfaces.service.merchandisecostanalysis.merchandiseitemcost.MerchandiseItemCostService;
import com.powere2e.sco.model.merchandisecostanalysis.merchandiseitemcost.Merchandiseitemcost;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

/**
 * 商品分项成本类比
 * 
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月23日
 */
public class MerchandiseItemCostServiceImpl extends ServiceImpl implements MerchandiseItemCostService {

	private static final long serialVersionUID = 6919319981233901494L;
	private MerchandiseItemCostDao merchandiseItemCostDao;
	private Font font = null;

	public MerchandiseItemCostDao getMerchandiseItemCostDao() {
		return merchandiseItemCostDao;
	}

	public void setMerchandiseItemCostDao(MerchandiseItemCostDao merchandiseItemCostDao) {
		this.merchandiseItemCostDao = merchandiseItemCostDao;
	}

	public static MerchandiseItemCostService getInstance() {
		return (MerchandiseItemCostService) ConfigFactory.getInstance().getBean("merchandiseItemCostService");
	}

	@Override
	public List<Merchandiseitemcost> searchMechandiseItemCost(Map<String, Object> map, PageInfo pageInfo) {
		return this.merchandiseItemCostDao.searchMechandiseItemCost(map, pageInfo);
	}

	@Override
	public String saveSearchDataForm(String fileName, Map<String, Object> paraMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Merchandiseitemcost> list = this.searchMechandiseItemCost(paraMap, null);
		map.put("dataList", list);
		map.put("itemValue", paraMap.get("itemValue"));
		map.put("selectComboxValue", paraMap.get("selectComboxValue"));
		String tarPath;
		try {
			tarPath = FreeMarkerUtil.generateHtml("merchandiseItemCost/merchandiseItemCostReport.ftl", "merchandiseItemCost".concat("/").concat(fileName), map);
		} catch (Exception e) {
			return "转换报表模板时异常";
		}
		File file = new File(tarPath);// 报表文件
		if (file.exists()) {
			try {
				Reports myReport = new Reports(BusinessConstants.myReportType.MAA.toString(), fileName, tarPath.replace(ConfigPath.getUploadFilePath(), ""));
				ReportsServiceImpl.getInstance().insertReports(myReport);
			} catch (Exception e) {
				file.delete();
				LoggerUtil.logger.error("MerchandiseItemCostServiceImpl.saveSearchDataForm删除文件["+ file.getPath() +"]");
				return "记录报表文件信息时出错";
			}
		} else {
			return "生成Html文件失败";
		}
		return null;
	}	
	
	@Override
	public void exportSignedQtyExcel(Map<String, Object> map, ServletOutputStream out) {
		List<Merchandiseitemcost> list = this.searchMechandiseItemCost(map, null);
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("商品分项成本类比分析");
		font = wb.createFont();
		font.setFontName("微软雅黑");
		// 1. 创建excel表头(第一行和第二行)
		this.fillHeaderCell(wb, sheet, map);

		// 2.用数据填充单元格
		if (!list.isEmpty())
			this.fillDataCell(list, wb, sheet, map);
		try {
			wb.write(out);
		} catch (Exception e) {
			throw new EscmException("导出Excel时异常", new String[] { e.getMessage() });
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				throw new EscmException("导出Excel时异常", new String[] { e.getMessage() });
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
	private void fillHeaderCell(Workbook wb, Sheet sheet, Map<String, Object> radioValuesMap) {
		// 第一行
		Row row = sheet.createRow(0);
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb, ExcelUtils.HEADR_FONT_SIZE);
		Cell cell = row.createCell(0);
		cell.setCellValue("商品分项成本类比");
		cell.setCellStyle(firstRowStyle);

		// 第二行
		row = sheet.createRow(1);
		CellStyle leftSencondRowStyle = ExcelUtils.getHeaderCellStyle(wb, null);
		CellStyle rightSencondRowStyle = ExcelUtils.getHeaderCellStyle(wb, null);
		leftSencondRowStyle.setAlignment(CellStyle.ALIGN_LEFT);
		rightSencondRowStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		leftSencondRowStyle.setFont(font);
		rightSencondRowStyle.setFont(font);
		int i = 0;// 从第零列(第一列)开始

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品/意向品编号");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品/意向品名称");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商/意向供应商编号");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商/意向供应商名称");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品定性角色");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品定量角色");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("采购部门");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("是否定量装");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("采购类型");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售方式");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("中分类");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("小分类");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("明细类");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("细分类");

		// 根据radio的值选择使用哪些列
		// 原材料
		if ("material".equals(radioValuesMap.get("itemValue"))) {
			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("原料类型");

			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("原料名称");

			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("原料产地");

			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("原料品牌");

			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("原料等级与规格");

			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("原料采购价格(元/kg)");

			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("原料投入量(kg)");

			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("原料投入成本(元)");

			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("平均成品原料成本(元/kg)");

			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("投入量占比(%)");

			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("成品含水率(%)");

			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("得率(%)");
		}
		// 内包装
		else if ("npackag".equals(radioValuesMap.get("itemValue"))) {
			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("包装类型");

			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("包装名称");

			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("报价");

			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("报价计量单位");

			switch (radioValuesMap.get("selectComboxValue").toString()) {
			case "FHD_JM": // 复合袋-卷膜
			case "FHD_LSM": // 复合袋-拉伸膜
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("具体材质&厚度");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("公斤价格");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("重量占比(%)");
				break;
			case "FHD_ZD": // 复合袋-制袋
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("具体材质&厚度");

				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("尺寸(cm)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("单个克重(g)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("单价(元)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("公斤价格(元)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("数量");
				break;
			case "ST": // 塑拖
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("具体材质&厚度");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("单个克重(g)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("公斤价格(元)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("重量占比(%)");
				break;
			case "TYJ": // 脱氧剂
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("单个克重(g)");

				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("尺寸(cm)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("单价(元)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("数量");
				break;
			case "NDD": // 内胆袋
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("具体材质&厚度");
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("尺寸(cm)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("单价(元)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("数量");
				break;
			case "BQ": // 标签
			case "ZZL": // 纸张类
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("具体材质&克重");

				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("尺寸(cm)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("数量");

				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("工艺要求");
				break;
			default:
				break;
			}
			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("备注");
		}
		// 外包装
		else if ("wpackag".equals(radioValuesMap.get("itemValue"))) {
			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("包装类型");

			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("包装名称");

			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("报价");

			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("报价计量单位");

			switch (radioValuesMap.get("selectComboxValue").toString()) {
			case "FXD": // 封箱带
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("单价(元/米)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("使用量(元/米)");
				break;
			case "ZX": // 纸箱
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("具体材质说明");

				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("长*宽*高(cm)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("纸箱用料面积(㎡)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("单价(元/只)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("纸箱用料单价(元/㎡)");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("单价(元/只)");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("纸箱用料单价(元/㎡)");

				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("箱规");
				break;
			default:
				break;
			}
			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("备注");
		}
		// 其他成本项
		else if ("elsecost".equals(radioValuesMap.get("itemValue"))) {
			// 损耗表的损耗类型
			if (radioValuesMap.get("selectComboxValue").equals("SH")) {
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("损耗类型");
			}
			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("报价");
			
			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("报价计量单位");

			switch (radioValuesMap.get("selectComboxValue").toString()) {
			case "SH": // 损耗
				break;
			case "SDM": // 水电煤
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("耗水(元/t成品)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("耗电(元/t成品)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("耗气(元/t成品)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("耗煤(元/t成品)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("耗油(元/t成品)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("合计(kg成品)");
				break;
			case "SBZJWH": // 设备折旧及维护
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("设备总价(万元)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("折旧年限(年)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("折旧值(万元/年)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("产能值(t成品/年)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("合计折旧值(元/kg成品)");
				break;
			case "RG": // 人工
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("车间工人数(人次)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("平均工资(元/人/月)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("月产量(t)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("每kg成品(元/kg)");
				break;
			case "LR": // 利润
			case "GL": // 管理
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("进货地区");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("占比(%)");
				break;
			case "YS": // 运输
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("工厂所在地");

				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("进货地区");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("总公里数");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("单位成本");
				break;
			case "SS": // 税收
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("进货地区");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("占比(%)");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("税率(%)");
				break;
			default:
				break;
			}
			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("备注");
		}
		
    // 进口相关成本
	else if ("importcost".equals(radioValuesMap.get("itemValue"))) {
		switch (radioValuesMap.get("selectComboxValue").toString()) {
		case "CC": //商品报价
			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("报价计量单位");
			
			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("商品报价");
			
			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("币种");
			
			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("付款方式");
			
			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("商品报价备注");
			
			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("汇率(%)");
			
			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("汇率参考日期");
			
			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("汇率参考银行");
			
			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("汇率备注");
			
			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("商品人民币结算价格");
			
			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("商品人民币结算价格备注");
			break;
		case "HY": 
		case "KY": 
			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("报价");

			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("报价计量单位");

			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("出发港");

			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("到达港");

			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("货柜类型");
			
			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("货柜尺寸");
			
			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("单价(元/货柜)");
			
			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("货柜内容物数量&重量");
			
			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("计算方式");
			break;
		case "HD":
		case "BX":
			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("报价");

			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("报价计量单位");
			break;
		case "BG":
			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("报价");
			
			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("报价计量单位");
			
			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("报关费");
			
			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("港杂费");
			
			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("滞港费");
			
			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("污箱费");

			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("其他费用");
			break;
		case "GS":
			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("报价");

			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("报价计量单位");

			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("HS编码");
			
			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("税率(%)");
			
			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("关税计算方式");
			break;
		case "ZZ": 
			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("报价");

			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("报价计量单位");

			cell = row.createCell(i);
			cell.setCellStyle(rightSencondRowStyle);
			cell.getCellStyle().setAlignment(CellStyle.ALIGN_RIGHT);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("税率(%)");
			
			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("增值税计算方式");
			break;
		default:
			break;
		}
		if (!"CC".equals(radioValuesMap.get("selectComboxValue").toString())) {
			cell = row.createCell(i);
			cell.setCellStyle(leftSencondRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("备注");
		}
	}
		// 固定列的部分
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("核算/投料表编号");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("报价日期");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
		cell.setCellValue("SCO申请单号");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("OA审批状态");

		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("X001地区合作价格");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("审批通过日期");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("SCO申请单创建人");
		
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, sheet.getRow(1).getLastCellNum()-1));
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
	private void fillDataCell(List<Merchandiseitemcost> list, Workbook wb, Sheet sheet, Map<String, Object> radioValuesMap) {
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		strStyle.setAlignment(CellStyle.ALIGN_LEFT);
		strStyle.setFont(font);
		
		CellStyle perStyle = ExcelUtils.getDefaultPercentStyle(wb);// 百分比样式
		perStyle.setFont(font);
		perStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		perStyle.setDataFormat(wb.createDataFormat().getFormat("0.00"));
		
		CellStyle intStyle = ExcelUtils.getDefaultPercentStyle(wb);// 百分比样式
		intStyle.setFont(font);
		intStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		intStyle.setDataFormat(wb.createDataFormat().getFormat("0.00"));
		
		CellStyle dateStyle = ExcelUtils.getDefaultDateStyle(wb);// 日期样式
		dateStyle.setAlignment(CellStyle.ALIGN_LEFT);
		dateStyle.setFont(font);
		
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式
		amtStyle.setFont(font);
		amtStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		amtStyle.setDataFormat(wb.createDataFormat().getFormat("#,##0.00"));

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == null) {
				continue;
			}
			writeOneRowDate(sheet, i, list.get(i), radioValuesMap, strStyle, dateStyle, amtStyle, perStyle, intStyle);
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
	 *@param perStyle
	 *            百分比格式
	 *@param intStyle
	 *            整数格式        
	 */
	private void writeOneRowDate(Sheet sheet, Integer rowIndex, Merchandiseitemcost mic, Map<String, Object> radioValuesMap, CellStyle strStyle, CellStyle dateStyle, CellStyle amtStyle, CellStyle perStyle,CellStyle intStyle) {
		Row row = sheet.createRow(rowIndex + 2);

		int j = 0;
		// 商品/意向品编号
		Cell cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mic.getMerchandiseCode() == null ? mic.getIntentionCode() : mic.getMerchandiseCode());

		// 商品/意向品名称
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mic.getMerchandiseCode() == null ? mic.getIntentionName() : mic.getMerchandiseName());

		// 供应商/意向供应商编号
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mic.getMerchandiseCode() == null ? mic.getIntentionSupplierCode() : mic.getSupplierCode());

		// 供应商/意向供应商名称
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mic.getMerchandiseCode() == null ? mic.getIntentionSupplierName() : mic.getSupplierName());

		// 商品定性角色
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mic.getMerchandiseDxRoleName()==null? "" :mic.getMerchandiseDxRoleName());

		// 商品定量角色
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mic.getMerchandiseDlRoleName()==null? "" :mic.getMerchandiseDlRoleName());

		// 采购部门
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mic.getMerchandiseCode() == null ? mic.getIntentionPurchaseDepartments() : mic.getPurchaseDepartments());

		// 是否定量装
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mic.getMerchandiseCode() == null ? mic.getRationed() : mic.getNetWeight());

		// 采购类型
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mic.getPurchaseType()==null? "" :mic.getPurchaseType());

		// 销售方式
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mic.getMerchandiseCode() == null ? mic.getSaleType() : mic.getStorageForm());

		// 中分类
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mic.getCentreTypeName()==null? "" :mic.getCentreTypeName());

		// 小分类
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mic.getIntentionSmallTypeCode() != null && "ELSE".equals(mic.getIntentionSmallTypeCode()) ? mic.getElseTypeName() : mic.getSmallTypeName());

		// 明细类
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mic.getDetailTypeName()==null? "" :mic.getDetailTypeName());

		// 细分类
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mic.getFineTypeName()==null? "" :mic.getFineTypeName());
		// 根据radio的值选择使用哪些列
		// 原材料
		if (radioValuesMap.get("itemValue").equals("material")) {
			// 原料类型
			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(mic.getMaterialType()==null? "" :mic.getMaterialType());
			// 原料名称
			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(mic.getMaterialName()==null? "" :mic.getMaterialName());
			// 原料产地
			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(mic.getMaterialOrigin()==null? "" :mic.getMaterialOrigin());
			// 原料品牌
			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(mic.getMaterialBrand()==null? "" :mic.getMaterialBrand());
			// 原料等级与规格
			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(mic.getMaterialLevelSpecification()==null? "" :mic.getMaterialLevelSpecification());
			// 原料采购价格(元/kg)
			cell = row.createCell(j++);
			cell.setCellStyle(amtStyle);
			cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getPurchasePrice(), 4));
			// 原料投入量(kg)
			cell = row.createCell(j++);
			cell.setCellStyle(amtStyle);
			cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getInputCount(), 4));
			// 原料投入成本(元)
			cell = row.createCell(j++);
			cell.setCellStyle(amtStyle);
			cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getInputCost(), 4));
			// 平均成品原料成本(元/kg)
			cell = row.createCell(j++);
			cell.setCellStyle(amtStyle);
			cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getAvgCost(), 4));
			// 投入量占比(%)
			cell = row.createCell(j++);
			cell.setCellStyle(perStyle);
			cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getInputCountProportion()));
			// 成品含水率(%)
			cell = row.createCell(j++);
			cell.setCellStyle(perStyle);
			cell.setCellValue(mic.getMoisture());
			// 得率(%)
			cell = row.createCell(j++);
			cell.setCellStyle(perStyle);
			cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getYield(), 2));
		}
		// 内包装
		else if (radioValuesMap.get("itemValue").equals("npackag")) {
			// 包装类型
			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(mic.getPackagType()==null? "" :mic.getPackagType());

			// 包装名称
			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(mic.getPackagName()==null? "" :mic.getPackagName());

			// 报价
			cell = row.createCell(j++);
			cell.setCellStyle(amtStyle);
			cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getPrice()));

			// 报价计量单位
			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(mic.getUnits()==null? "" :mic.getUnits());

			switch (radioValuesMap.get("selectComboxValue").toString()) {
			case "FHD_JM": // 复合袋-卷膜
			case "FHD_LSM": // 复合袋-拉伸膜
				// 具体材质&厚度
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getTexture()==null? "" :mic.getTexture());
				// 公斤价格
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getKgPrice()));
				// 重量占比(%)
				cell = row.createCell(j++);
				cell.setCellStyle(perStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getWeightProportion()));
				break;
			case "FHD_ZD": // 复合袋-制袋
				// 具体材质&厚度
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getTexture()==null? "" :mic.getTexture());

				// 尺寸(cm)
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getMaterialSize()==null? "" :mic.getMaterialSize());

				// 单个克重(g)
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getWeight()));

				// 单价(元)
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getUnitsPrice()));

				// 公斤价格(元)
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getKgPrice()));

				// 数量
				cell = row.createCell(j++);
				cell.setCellStyle(intStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getQuantity()));
				break;
			case "ST": // 塑拖
				// 具体材质&厚度
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getTexture()==null? "" :mic.getTexture());

				// 单个克重(g)
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getWeight()));

				// 公斤价格(元)
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getKgPrice()));

				// 重量占比(%)
				cell = row.createCell(j++);
				cell.setCellStyle(perStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getWeightProportion()));
				break;
			case "TYJ": // 脱氧剂
				// 单个克重(g)
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getWeight()));

				// 尺寸(cm)
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getMaterialSize()==null? "" :mic.getMaterialSize());

				// 单价(元)
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getUnitsPrice()));

				// 数量
				cell = row.createCell(j++);
				cell.setCellStyle(intStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getQuantity()));
				break;
			case "NDD": // 内胆袋
				// 具体材质&厚度
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getTexture()==null? "" :mic.getTexture());
				// 尺寸(cm)
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getMaterialSize()==null? "" :mic.getMaterialSize());

				// 单价(元)
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getUnitsPrice()));

				// 数量
				cell = row.createCell(j++);
				cell.setCellStyle(intStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getQuantity()));
				break;
			case "BQ": // 标签
			case "ZZL": // 纸张类
				// 具体材质&克重
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getTexture()==null? "" :mic.getTexture());

				// 尺寸(cm)
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getMaterialSize()==null? "" :mic.getMaterialSize());

				// 数量
				cell = row.createCell(j++);
				cell.setCellStyle(intStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getQuantity()));

				// 工艺要求
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getTechnologyRequirements()==null? "" :mic.getTechnologyRequirements());
				break;
			default:
				break;
			}
			// 备注
			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(mic.getRemarks()==null? "" :mic.getRemarks());
		}
		// 外包装
		else if (radioValuesMap.get("itemValue").equals("wpackag")) {
			// 包装类型
			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(mic.getPackagType()==null? "" :mic.getPackagType());

			// 包装名称
			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(mic.getPackagName()==null? "" :mic.getPackagName());

			// 报价
			cell = row.createCell(j++);
			cell.setCellStyle(amtStyle);
			cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getPrice()));

			// 报价计量单位
			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(mic.getUnits()==null? "" :mic.getUnits());

			switch (radioValuesMap.get("selectComboxValue").toString()) {
			case "FXD": // 封箱带
				// 单价(元/米)
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getUnitsPrice()));

				// 使用量(元/米)
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getUseQuantity()));
				break;
			case "ZX": // 纸箱
				// 具体材质说明
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getTexture()==null? "" :mic.getTexture());

				// 长*宽*高(cm)
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getLwh()==null? "" :mic.getLwh());

				// 纸箱用料面积(㎡)
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getArea()));

				// 单价(元/只)
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getUnitsPrice()));

				// 纸箱用料单价(元/㎡)
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getYlUnitsPrice()));

				// 箱规
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getSpecification()==null? "" :mic.getSpecification());
				break;
			default:
				break;
			}
			// 备注
			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(mic.getRemarks()==null? "" :mic.getRemarks());
		}
		// 其他成本项
		else if (radioValuesMap.get("itemValue").equals("elsecost")) {
			// 损耗表的损耗类型
			if (radioValuesMap.get("selectComboxValue").equals("SH")) {
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getWastageType()==null? "" :mic.getWastageType());
			}
			// 报价
			cell = row.createCell(j++);
			cell.setCellStyle(amtStyle);
			cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getPrice()));

			// 报价计量单位
			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(mic.getUnits()==null? "" :mic.getUnits());

			switch (radioValuesMap.get("selectComboxValue").toString()) {
			case "SH": // 损耗
				break;
			case "SDM": // 水电煤
				// 耗水(元/t成品)
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getWater()));

				// 耗电(元/t成品)
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getElectricity()));

				// 耗气(元/t成品)
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getGas()));

				// 耗煤(元/t成品)
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getCoal()));

				// 耗油(元/t成品)
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getOil()));

				// 合计/kg成品
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getTotal()));
				break;
			case "SBZJWH": // 设备折旧及维护
				// 设备总价万元
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getTotalPrice()));

				// 折旧年限年
				cell = row.createCell(j++);
				cell.setCellStyle(intStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getAgeLimit()));

				// 折旧值万元/年
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getDepreciation()));

				// 产能值t成品/年
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getCapacity()));

				// 合计折旧值元/kg成品
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getTotal()));
				break;
			case "RG": // 人工
				// 车间工人数人次
				cell = row.createCell(j++);
				cell.setCellStyle(intStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getManpowerCount()));

				// 平均工资元/人/月
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getAvgWage()));

				// 月产量t
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getMonthCapacity()));

				// 每kg成品元/kg
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getUnitsWage()));
				break;
			case "LR": // 利润
			case "GL": // 管理
				// 进货地区
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getRegion()==null? "" :mic.getRegion());

				// 占比(%)
				cell = row.createCell(j++);
				cell.setCellStyle(perStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getProportion()));
				break;
			case "YS": // 运输
				// 工厂所在地
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getCompanySite()==null? "" :mic.getCompanySite());

				// 进货地区
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getRegion()==null? "" :mic.getRegion());

				// 总公里数
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getSumKm()));

				// 单位成本
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getUnitsCost()));
				break;
			case "SS": // 税收
				// 进货地区
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getRegion()==null? "" :mic.getRegion());

				// 占比(%)
				cell = row.createCell(j++);
				cell.setCellStyle(perStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getProportion()));

				// 税率(%)
				cell = row.createCell(j++);
				cell.setCellStyle(perStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getTaxRate()));
				break;
			default:
				break;
			}
			// 备注
			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(mic.getRemarks()==null?"":mic.getRemarks());
		}else if(radioValuesMap.get("itemValue").equals("importcost")){
			switch (radioValuesMap.get("selectComboxValue").toString()) {
			case "CC":
				// 报价计量单位
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getUnits()==null? "" :mic.getUnits());
				
				//商品报价
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getPrice()));
				
				//币种
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getCurrency() == null ? "" : mic.getCurrency());
				
				//付款方式
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getPaymentType() == null ? "" : mic.getPaymentType());
				
				//出厂备注
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getRemarks() == null ? "" : mic.getRemarks());
				
				//汇率
				cell = row.createCell(j++);
				cell.setCellStyle(perStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getExchangerate()));
				
				//参考日期
				cell = row.createCell(j++);
				cell.setCellStyle(dateStyle);
				cell.setCellValue(mic.getReferenceDate());
				
				//参考银行
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getReferenceBank() == null ? "" : mic.getReferenceBank());
				
				//汇率备注
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getAeRemarks() == null ? "" : mic.getAeRemarks());
				
				//商品人民币结算价格
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getRmbSettlementPrice()));
				
				//商品人民币结算价格备注
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getRmbSettlementPriceRemarks() == null ? "" : mic.getRmbSettlementPriceRemarks());
				break;
			case "HY":
			case "KY":
				//报价
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getPrice()));
				
				// 报价计量单位
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getUnits()==null?"":mic.getUnits());
				
				//出发港
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getStarting() == null ? "" : mic.getStarting());
				
				//到达港
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getDestination() == null ? "" : mic.getDestination());
				
				//货柜类型
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getContainerType() == null ? "" : mic.getContainerType());
				
				//货柜尺寸
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getContainerSize() == null ? "" : mic.getContainerSize());
				
				//单价
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getUnitPrice()));
				
				//货柜内容物数量&重量
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getContainerCapacity() == null ? "" : mic.getContainerCapacity());
				
				//计算方式
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getComputeType() == null ? "" : mic.getComputeType());
				
				//备注
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getRemarks() == null ? "" : mic.getRemarks());
				break;
			case "HD":
				//换单费
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getUpdateOrderFee()));
				
				// 报价计量单位
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getUnits()==null?"":mic.getUnits());
				
				//换单费备注
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getUpdateOrderFeeRemarks() == null ? "" : mic.getUpdateOrderFeeRemarks());
				break;
			case "BX":
				//保险费
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getPremium()));
				
				// 报价计量单位
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getUnits()==null? "" :mic.getUnits());
				
				//保险费备注
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getPremiumRemarks() == null ? "" : mic.getPremiumRemarks());
				break;
			case "BG":
				//报价
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getPrice()));
				
				// 报价计量单位
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getUnits()==null? "" :mic.getUnits());
				
				//报关费
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getCustomscharges());
				
				//港杂费
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getPortSurcharge());
				
				//滞港费
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getDemurrageCharge());
				
				//污箱费
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getContainerDirtynessChange());
				
				//其他费用
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getElseFee());
				
				//备注
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getRemarks() == null ? "" : mic.getRemarks());
				break;
			case "GS":
				//报价
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getPrice()));
				
				// 报价计量单位
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getUnits()==null?"":mic.getUnits());
				
				//HS编码
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getHsCode() == null ? "" : mic.getHsCode());
				
				//税率(%)
				cell = row.createCell(j++);
				cell.setCellStyle(perStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getTaxRate()));
				
				//关税计算方式
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getCustomsdutiesComputeType() == null ? "" : mic.getCustomsdutiesComputeType());
				
				//备注
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getRemarks() == null ? "" : mic.getRemarks());
				break;
			case "ZZ":
				//报价
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getPrice()));
				
				// 报价计量单位
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getUnits()==null?"":mic.getUnits());
				
				//税率(%)
				cell = row.createCell(j++);
				cell.setCellStyle(perStyle);
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getTaxRate()));
				
				//增值税计算方式
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getAddedvaluetaxComputeType() == null ? "" : mic.getAddedvaluetaxComputeType());
				
				//备注
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(mic.getRemarks() == null ? "" : mic.getRemarks());
				break;
			default:
				break;
			}
		}

		// 核算/投料表编号
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mic.getAccountingCode()==null?"":mic.getAccountingCode());

		// 报价日期
		cell = row.createCell(j++);
		cell.setCellStyle(dateStyle);
		cell.setCellValue(mic.getQuotedDate()==null?"":DateUtils.formatDateToStr(mic.getQuotedDate(),"yyyy-MM-dd HH:mm:ss"));

		// SCO申请单号
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mic.getApplicationCode()==null?"":mic.getApplicationCode());

		// OA审批状态
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mic.getApplicationStatusValue()==null?"":mic.getApplicationStatusValue());

		// X001地区合作价格
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(DecimalFormatUtils.bigDecimalToString(mic.getX001Region()));

		// 审批通过日期
		cell = row.createCell(j++);
		cell.setCellStyle(dateStyle);
		cell.setCellValue(mic.getOaApproveDate()==null?"":DateUtils.formatDateToStr(mic.getOaApproveDate(),"yyyy-MM-dd HH:mm:ss"));

		// SCO申请单创建人
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mic.getCreateby()==null?"":mic.getCreateby());

	}
}

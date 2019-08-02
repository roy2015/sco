package com.powere2e.sco.service.impl.sharefunctionanalysis.purchasinganalysis.merchandisepromotionbuypriceadjust;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.FreeMarkerUtil;
import com.powere2e.sco.common.utils.LoggerUtil;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.purchasinganalysis.merchandisepromotionbuypriceadjust.MerchandisePromotionBuyPriceAdjustDao;
import com.powere2e.sco.interfaces.service.sharefunctionanalysis.purchasinganalysis.merchandisepromotionbuypriceadjust.MerchandisePromotionBuyPriceAdjustService;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.model.sharefunctionanalysis.purchasinganalysis.merchandisepromotionbuypriceadjust.MerchandisePromotionBuyPriceAdjust;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;
import com.powere2e.security.model.Option;

/**
 * 商品进货明细业务类的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public class MerchandisePromotionBuyPriceAdjustServiceImpl extends ServiceImpl implements MerchandisePromotionBuyPriceAdjustService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2328541788205369097L;
	private MerchandisePromotionBuyPriceAdjustDao MerchandisePromotionBuyPriceAdjustDao;

	public static MerchandisePromotionBuyPriceAdjustService getInstance() {
		return (MerchandisePromotionBuyPriceAdjustService) ConfigFactory.getInstance().getBean("MerchandisePromotionBuyPriceAdjustService");
	}

	// 获得商品进货明细DAO实例
	public MerchandisePromotionBuyPriceAdjustDao getMerchandisePromotionBuyPriceAdjustDao() {
		return MerchandisePromotionBuyPriceAdjustDao;
	}

	// 设置商品进货明细DAO实例
	public void setMerchandisePromotionBuyPriceAdjustDao(MerchandisePromotionBuyPriceAdjustDao MerchandisePromotionBuyPriceAdjustDao) {
		this.MerchandisePromotionBuyPriceAdjustDao = MerchandisePromotionBuyPriceAdjustDao;
	}

	@Override
	public void exportMerchandisePromotionBuyPriceAdjust(Map<String, Object> map, ServletOutputStream out) throws Exception {

		List<MerchandisePromotionBuyPriceAdjust> list = this.getMerchandisePromotionBuyPriceAdjust(map, null);
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("长期进价调整明细报表");
		// 1. 创建excel表头(第一行和第二行)
		this.fillHeaderCellDetail(wb, sheet);
		// 2.用数据填充单元格
		if (!list.isEmpty()) {
			this.fillDataCellDetail(list, wb, sheet, map);
		}
		try {
			wb.write(out);
		} catch (Exception e) {
			throw new EscmException("导出Excel时异常", new String[] { e.getMessage() });
		} finally {
			try {
				if (out != null) {
					out.close();
				}
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
	private void fillHeaderCellDetail(Workbook wb, Sheet sheet) {
		// 第一行
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb, 15);
		CellStyle thirdRowStyle = ExcelUtils.getHeaderCellStyle(wb, null);
		thirdRowStyle.getWrapText();
		ExcelUtils.setAlign(thirdRowStyle, "center", "center", true);
		Row row = sheet.createRow(3);
		Cell cell = row.createCell(0);

		cell.setCellValue("商品进价调整明细报表");
		cell.setCellStyle(firstRowStyle);
		for (int i = 1; i < 17; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "A4:Q4");

		// 第二行
		row = sheet.createRow(4);
		row.setHeight((short) (80 * 20));
		int i = 0;// 从第零列(第一列)开始

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品编号");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品名称");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品定性角色");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品定量角色");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("中分类");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("小分类");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("明细类");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("细分类");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商编号");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商名称");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("工厂编号");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("促销进价开始结束日期");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("促销档期");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("促销活动名称");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("促销进价（元/kg或元/件）");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("促销期间采购量（kg/件）");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("促销期间采购额（元）");

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
	 * @throws Exception
	 */
	private void fillDataCellDetail(List<MerchandisePromotionBuyPriceAdjust> list, Workbook wb, Sheet sheet, Map<String, Object> map) throws Exception {
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		CellStyle dateStyle = ExcelUtils.getDefaultDateStyle(wb);// 字符串样式
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式

		for (int i = 0; i < list.size(); i++) {
			writeOneRowDateDetail(sheet, i, list.get(i), strStyle, dateStyle, amtStyle, map);
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
	private void writeOneRowDateDetail(Sheet sheet, Integer rowIndex, MerchandisePromotionBuyPriceAdjust msd, CellStyle strStyle, CellStyle dateStyle, CellStyle amtStyle, Map<String, Object> map)
			throws Exception {
		if (rowIndex == 0) {
			Row row = sheet.createRow(rowIndex);
			Cell cell = row.createCell(0);
			cell.setCellStyle(strStyle);
			cell.setCellValue("时间范围:");

			cell = row.createCell(1);
			cell.setCellStyle(strStyle);
			cell.setCellValue(map.get("minDate") + "~" + map.get("maxDate"));

			row = sheet.createRow(rowIndex + 1);
			cell = row.createCell(0);
			cell.setCellStyle(strStyle);
			cell.setCellValue("工厂:");

			cell = row.createCell(1);
			cell.setCellStyle(strStyle);
			cell.setCellValue(map.get("warehouseCode").toString().replace("'", ""));
		}
		Row row = sheet.createRow(rowIndex + 5);
		int j = 0;
		Cell cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getMerchandiseCode());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getMerchandiseName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getDxRoleName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getDlRoleName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getCentreName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getSmallName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getDetailName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getFineName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getSupplierCode());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getSupplierName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getWarehouseCode());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getPriceDate());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getPromotionSchedule());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getPromotionName());

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPurchasePrice()));
		try {
			cell = row.createCell(j++);
			cell.setCellStyle(amtStyle);
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getReceiptRationed()));
		} catch (Exception e) {
		}

		try {
			cell = row.createCell(j++);
			cell.setCellStyle(amtStyle);
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getReceiptTotalPrice()));
		} catch (Exception e) {
		}
	}

	@Override
	public String saveMerchandisePromotionBuyPriceAdjust(String fileName, Map<String, Object> paraMap) throws Exception {
		String tarPath;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("warehouseCodeSplit", paraMap.get("warehouseCode").toString().replace("'", ""));
		map.put("date", paraMap.get("minDate") + "~" + paraMap.get("maxDate"));
		map.put("dataList", this.getMerchandisePromotionBuyPriceAdjust(paraMap, null));
		try {
			tarPath = FreeMarkerUtil.generateHtml("purchasingAnalysis/merchandisePromotionBuyPriceAdjust/merchandisePromotionBuyPriceAdjustInfoGrid.ftl", "merchandisePromotionBuyPriceAdjustInfo"
					.concat("/").concat(fileName), map);
		} catch (Exception e) {
			return "转换报表模板时异常";
		}
		File file = new File(tarPath);// 报表文件
		if (file.exists()) {
			try {
				Reports myReport = new Reports(BusinessConstants.myReportType.PTP.toString(), fileName, tarPath.replace(ConfigPath.getUploadFilePath(), ""));
				ReportsServiceImpl.getInstance().insertReports(myReport);
			} catch (Exception e) {
				file.delete();
				LoggerUtil.logger.error("MerchandisePromotionBuyPriceAdjustServiceImpl.saveMerchandisePromotionBuyPriceAdjust删除文件["+ file.getPath() +"]");
				throw new EscmException("记录报表文件信息时出错");
			}
		} else {
			return "生成Html文件失败";
		}
		return null;
	}

	@Override
	public List<MerchandisePromotionBuyPriceAdjust> listMerchandise(Map<String, Object> map, PageInfo pageInfo) {

		return this.getMerchandisePromotionBuyPriceAdjustDao().listMerchandise(map, pageInfo);
	}

	@Override
	public List<Option> listRegion(String regionCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("regionCode", regionCode);
		return this.getMerchandisePromotionBuyPriceAdjustDao().listRegion(map);
	}

	@Override
	public List<MerchandisePromotionBuyPriceAdjust> getMerchandisePromotionBuyPriceAdjust(Map<String, Object> map, PageInfo pageInfo) {
		// 查询促销记录的促销档期和价格，商品信息
		List<MerchandisePromotionBuyPriceAdjust> listPromotionPriceOrDate = this.getMerchandisePromotionBuyPriceAdjustDao().listPromotionPriceOrDate(map, pageInfo);
		for (int i = 0; i < listPromotionPriceOrDate.size(); i++) {
			listPromotionPriceOrDate.get(i).setPriceDate(listPromotionPriceOrDate.get(i).getMinDate() + "~" + listPromotionPriceOrDate.get(i).getMaxDate());
		}
		return listPromotionPriceOrDate;
	}
}
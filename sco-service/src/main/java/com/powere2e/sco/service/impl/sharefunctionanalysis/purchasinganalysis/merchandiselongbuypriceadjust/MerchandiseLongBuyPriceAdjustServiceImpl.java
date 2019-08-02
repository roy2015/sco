package com.powere2e.sco.service.impl.sharefunctionanalysis.purchasinganalysis.merchandiselongbuypriceadjust;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.FreeMarkerUtil;
import com.powere2e.sco.common.utils.LoggerUtil;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.purchasinganalysis.merchandiselongbuypriceadjust.MerchandiseLongBuyPriceAdjustDao;
import com.powere2e.sco.interfaces.service.sharefunctionanalysis.purchasinganalysis.merchandiselongbuypriceadjust.MerchandiseLongBuyPriceAdjustService;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.model.sharefunctionanalysis.purchasinganalysis.merchandiselongbuypriceadjust.MerchandiseLongBuyPriceAdjust;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;
import com.powere2e.security.model.Option;

/**
 * 商品进货明细业务类的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public class MerchandiseLongBuyPriceAdjustServiceImpl extends ServiceImpl implements MerchandiseLongBuyPriceAdjustService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2328541788205369097L;
	private MerchandiseLongBuyPriceAdjustDao MerchandiseLongBuyPriceAdjustDao;

	public static MerchandiseLongBuyPriceAdjustService getInstance() {
		return (MerchandiseLongBuyPriceAdjustService) ConfigFactory.getInstance().getBean("MerchandiseLongBuyPriceAdjustService");
	}

	// 获得商品进货明细DAO实例
	public MerchandiseLongBuyPriceAdjustDao getMerchandiseLongBuyPriceAdjustDao() {
		return MerchandiseLongBuyPriceAdjustDao;
	}

	// 设置商品进货明细DAO实例
	public void setMerchandiseLongBuyPriceAdjustDao(MerchandiseLongBuyPriceAdjustDao MerchandiseLongBuyPriceAdjustDao) {
		this.MerchandiseLongBuyPriceAdjustDao = MerchandiseLongBuyPriceAdjustDao;
	}

	@Override
	public void exportMerchandiseLongBuyPriceAdjust(Map<String, Object> map, ServletOutputStream out) throws Exception {

		List<MerchandiseLongBuyPriceAdjust> list = this.getMerchandiseLongBuyPriceAdjustSum(map, null);
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("长期进价调整明细报表");
		// 1. 创建excel表头(第一行和第二行)
		this.fillHeaderCellDetail(wb, sheet, 0, map);
		// 2.用数据填充单元格
		if (!list.isEmpty()) {
			this.fillDataCellDetail(list, wb, sheet, map, 0);
			List<MerchandiseLongBuyPriceAdjust> lists = this.getMerchandiseLongBuyPriceAdjustDetailInfo(map, null);
			// 二次创建excel表头
			this.fillHeaderCellDetail(wb, sheet, list.size() + 2, map);
			// 二次填充单元格
			this.fillDataCellDetail(lists, wb, sheet, map, list.size() + 2);
		}
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
	private void fillHeaderCellDetail(Workbook wb, Sheet sheet, Integer num, Map<String, Object> map) {
		// 第一行
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb, 15);
		CellStyle thirdRowStyle = ExcelUtils.getHeaderCellStyle(wb, null);
		Row row = sheet.createRow(num + 4);
		Cell cell = row.createCell(0);
		if (num == 0) {
			cell.setCellValue("汇总表");
			cell.setCellStyle(firstRowStyle);
			if (!map.get("type").toString().equals("2")) {
				for (int i = 1; i < 13; i++) {
					cell = row.createCell(i);
					cell.setCellStyle(firstRowStyle);
				}
				ExcelUtils.addMergedRegion(sheet, "A5:N5");
			} else {
				for (int i = 1; i < 9; i++) {
					cell = row.createCell(i);
					cell.setCellStyle(firstRowStyle);
				}
				ExcelUtils.addMergedRegion(sheet, "A5:I5");
			}
		} else {
			cell.setCellValue("明细表");
			cell.setCellStyle(firstRowStyle);
			if (!map.get("type").toString().equals("2")) {
				for (int i = 1; i < 19; i++) {
					cell = row.createCell(i);
					cell.setCellStyle(firstRowStyle);
				}
				ExcelUtils.addMergedRegion(sheet, "A" + (num + 5) + ":T" + (num + 5));
			} else {
				for (int i = 1; i < 14; i++) {
					cell = row.createCell(i);
					cell.setCellStyle(firstRowStyle);
				}
				ExcelUtils.addMergedRegion(sheet, "A" + (num + 5) + ":O" + (num + 5));
			}
		}
		// 第二行
		row = sheet.createRow(num + 5);

		int i = 0;// 从第零列(第一列)开始

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品编号");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品名称");
		if (!map.get("type").toString().equals("2")) {
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
		}
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
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("工厂编号");
		if (num == 0) {
			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("调整后价格采购量");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("调整后价格采购额");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("成本增减情况");
		} else {
			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("库存地点");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("调整后价格维护日期");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("调整前价格");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("调整后价格");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("调整后价差");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("调整后价差百分比");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("调整后价格采购量");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("调整后价格采购额");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("成本增减情况");
		}
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
	private void fillDataCellDetail(List<MerchandiseLongBuyPriceAdjust> list, Workbook wb, Sheet sheet, Map<String, Object> map, Integer num) throws Exception {
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		CellStyle dateStyle = ExcelUtils.getDefaultDateStyle(wb);// 字符串样式
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式

		for (int i = 0; i < list.size(); i++) {
			writeOneRowDateDetail(sheet, i + num, list.get(i), strStyle, dateStyle, amtStyle, map, num);
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
	private void writeOneRowDateDetail(Sheet sheet, Integer rowIndex, MerchandiseLongBuyPriceAdjust msd, CellStyle strStyle, CellStyle dateStyle, CellStyle amtStyle, Map<String, Object> map,
			Integer num) throws Exception {
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

			row = sheet.createRow(rowIndex + 2);
			cell = row.createCell(0);
			cell.setCellStyle(strStyle);
			cell.setCellValue("库存地点:");

			cell = row.createCell(1);
			cell.setCellStyle(strStyle);
			cell.setCellValue(map.get("sellRegion").toString().replace("'", ""));

		}
		Row row = sheet.createRow(rowIndex + 6);
		int j = 0;
		Cell cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getMerchandiseCode());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getMerchandiseName());
		if (!map.get("type").toString().equals("2")) {
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
		}
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

		if (num != 0) {
			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(msd.getRegionCode());

			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(msd.getPriceDate());

			cell = row.createCell(j++);
			cell.setCellStyle(amtStyle);
			if (msd.getPurchasePriceO() != null) {
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPurchasePriceO()));
			} else {
				cell.setCellValue("");
			}

			cell = row.createCell(j++);
			cell.setCellStyle(amtStyle);
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPurchasePriceN()));

			cell = row.createCell(j++);
			cell.setCellStyle(amtStyle);
			if (msd.getPurchasePriceC() != null) {
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPurchasePriceC()));
			} else {
				cell.setCellValue("");
			}

			cell = row.createCell(j++);
			cell.setCellStyle(amtStyle);
			if (msd.getPurchasePriceB() != null) {
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPurchasePriceB()) + "%");
			} else {
				cell.setCellValue("");
			}
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getReceiptRationed()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getReceiptTotalPrice()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getIncreaseDecrease()));

	}

	@Override
	public String saveMerchandiseLongBuyPriceAdjust(String fileName, Map<String, Object> paraMap) throws Exception {
		String tarPath;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", paraMap.get("minDate") + "~" + paraMap.get("maxDate"));
		map.put("regionCodeSplit", paraMap.get("regionCode").toString().replace("'", ""));
		map.put("warehouseCodeSplit", paraMap.get("warehouseCode").toString().replace("'", ""));
		map.put("dataList", this.getMerchandiseLongBuyPriceAdjustSum(paraMap, null));
		map.put("dataLists", this.getMerchandiseLongBuyPriceAdjustDetailInfo(paraMap, null));
		try {
			if (!paraMap.get("type").toString().equals("2")) {
				tarPath = FreeMarkerUtil.generateHtml("purchasingAnalysis/merchandiseLongBuyPriceAdjust/merchandiseLongBuyPriceAdjustInfoGrid.ftl", "merchandiseLongBuyPriceAdjustInfo".concat("/")
						.concat(fileName), map);
			} else {
				tarPath = FreeMarkerUtil.generateHtml("purchasingAnalysis/accessoryLongBuyPriceAdjust/accessoryLongBuyPriceAdjustInfoGrid.ftl",
						"accessoryLongBuyPriceAdjustInfo".concat("/").concat(fileName), map);
			}
		} catch (Exception e) {
			return "转换报表模板时异常";
		}
		File file = new File(tarPath);// 报表文件
		if (file.exists()) {
			try {
				Reports myReport = new Reports(BusinessConstants.myReportType.LTP.toString(), fileName, tarPath.replace(ConfigPath.getUploadFilePath(), ""));
				ReportsServiceImpl.getInstance().insertReports(myReport);
			} catch (Exception e) {
				file.delete();
				LoggerUtil.logger.error("MerchandiseLongBuyPriceAdjustServiceImpl.saveMerchandiseLongBuyPriceAdjust删除文件["+ file.getPath() +"]");
				throw new EscmException("记录报表文件信息时出错");
			}
		} else {
			return "生成Html文件失败";
		}
		return null;
	}

	@Override
	public List<MerchandiseLongBuyPriceAdjust> listMerchandise(Map<String, Object> map, PageInfo pageInfo) {

		return this.getMerchandiseLongBuyPriceAdjustDao().listMerchandise(map, pageInfo);
	}

	@Override
	public List<Option> listWarehouseSite(String regionCode) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("regionCode", regionCode);
		return this.getMerchandiseLongBuyPriceAdjustDao().listWarehouseSite(map);
	}

	@Override
	public List<MerchandiseLongBuyPriceAdjust> getMerchandiseLongBuyPriceAdjustDetailInfo(Map<String, Object> map, PageInfo pageInfo) throws Exception {

		List<MerchandiseLongBuyPriceAdjust> list = new ArrayList<MerchandiseLongBuyPriceAdjust>();
		// 查询在指定时间段内的调价记录
		List<MerchandiseLongBuyPriceAdjust> listPriceOrDate = this.getMerchandiseLongBuyPriceAdjustDao().listPriceOrDate(map, pageInfo);
	//	SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<MerchandiseLongBuyPriceAdjust> lists = new ArrayList<MerchandiseLongBuyPriceAdjust>();
		boolean bool = false;
		Map<String, Object> mapA = new HashMap<String, Object>();
		mapA.put("regionCode", map.get("regionCode"));
		for (int i = 0; i < listPriceOrDate.size(); i++) {
			bool = false;	
			mapA.put("minDate", listPriceOrDate.get(i).getPriceDate());
			mapA.put("warehouseCode", listPriceOrDate.get(i).getWarehouseCode());
			mapA.put("merchandiseCode", listPriceOrDate.get(i).getMerchandiseCode());
			mapA.put("supplierCode", listPriceOrDate.get(i).getSupplierCode());
			mapA.put("minDateHtml", map.get("minDate").toString());
			mapA.put("maxDateHtml", map.get("maxDate").toString());
			if (i == listPriceOrDate.size() - 1) {
				mapA.put("search", 2);
				lists = this.getMerchandiseLongBuyPriceAdjustDao().listPriceOrDates(mapA, null);
				if (lists.size() > 1) {
					mapA.put("maxDate", DateUtils.getTime(lists.get(1).getPriceDate(), -1));
				} else {
					mapA.put("maxDate", map.get("maxDate").toString());
				}
			} else {
				if ((listPriceOrDate.get(i).getMerchandiseCode().equals(listPriceOrDate.get(i + 1).getMerchandiseCode())
						&& listPriceOrDate.get(i).getSupplierCode().equals(listPriceOrDate.get(i + 1).getSupplierCode()) && listPriceOrDate.get(i).getWarehouseCode()
						.equals(listPriceOrDate.get(i + 1).getWarehouseCode()))) {
					mapA.put("maxDate", DateUtils.getTime(listPriceOrDate.get(i + 1).getPriceDate(), -1));
				} else {
					bool = true;
					mapA.put("search", 2);
					lists = this.getMerchandiseLongBuyPriceAdjustDao().listPriceOrDates(mapA, null);
					if (lists.size() > 1) {
						mapA.put("maxDate", DateUtils.getTime(lists.get(1).getPriceDate(), -1));
					} else {
						mapA.put("maxDate", map.get("maxDate").toString());
					}
				}
			}
			
			// 根据调价时间段 查询在改时间段的进货数据
			List<MerchandiseLongBuyPriceAdjust> listReceiptInfo = this.getMerchandiseLongBuyPriceAdjustDao().listReceiptInfo(mapA, null);
			if (listReceiptInfo.size() != 0) {
				for (int x = 0; x < listReceiptInfo.size(); x++) {
					MerchandiseLongBuyPriceAdjust merchandiseLongBuyPriceAdjust=new MerchandiseLongBuyPriceAdjust();
					merchandiseLongBuyPriceAdjust.setCentreName(listPriceOrDate.get(i).getCentreName());
					merchandiseLongBuyPriceAdjust.setDetailName(listPriceOrDate.get(i).getDetailName());
					merchandiseLongBuyPriceAdjust.setSmallName(listPriceOrDate.get(i).getSmallName());
					merchandiseLongBuyPriceAdjust.setMerchandiseCode(listPriceOrDate.get(i).getMerchandiseCode());
					merchandiseLongBuyPriceAdjust.setMerchandiseName(listPriceOrDate.get(i).getMerchandiseName());
					merchandiseLongBuyPriceAdjust.setSupplierCode(listPriceOrDate.get(i).getSupplierCode());
					merchandiseLongBuyPriceAdjust.setSupplierName(listPriceOrDate.get(i).getSupplierName());
					merchandiseLongBuyPriceAdjust.setPriceDate(listPriceOrDate.get(i).getPriceDate());
					merchandiseLongBuyPriceAdjust.setPurchasePrice(listPriceOrDate.get(i).getPurchasePrice());
					merchandiseLongBuyPriceAdjust.setWarehouseCode(listPriceOrDate.get(i).getWarehouseCode());
					merchandiseLongBuyPriceAdjust.setRegionCode(listReceiptInfo.get(x).getRegionCode());
					if (i == 0) {
						mapA.put("search", 1);
						lists = this.getMerchandiseLongBuyPriceAdjustDao().listPriceOrDates(mapA, null);
						// 第一个价格的时候还需要查看该价格是否还有上一次记录
						if (lists.size() > 1) {
							merchandiseLongBuyPriceAdjust.setPurchasePriceO(lists.get(1).getPurchasePrice());
							merchandiseLongBuyPriceAdjust.setPurchasePriceC(merchandiseLongBuyPriceAdjust.getPurchasePrice().subtract(lists.get(1).getPurchasePrice()));
							if (!merchandiseLongBuyPriceAdjust.getPurchasePriceO().equals(new BigDecimal(0))) {
								merchandiseLongBuyPriceAdjust.setPurchasePriceB(
										merchandiseLongBuyPriceAdjust.getPurchasePriceC().multiply(new BigDecimal(100)).divide(merchandiseLongBuyPriceAdjust.getPurchasePriceO(), 2, RoundingMode.HALF_UP));
							}
						} else {
							merchandiseLongBuyPriceAdjust.setPurchasePriceO(null);
							merchandiseLongBuyPriceAdjust.setPurchasePriceC(null);
							merchandiseLongBuyPriceAdjust.setPurchasePriceB(null);
						}
					} else {
						if ((!merchandiseLongBuyPriceAdjust.getMerchandiseCode().equals(listPriceOrDate.get(i - 1).getMerchandiseCode())
								|| !merchandiseLongBuyPriceAdjust.getSupplierCode().equals(listPriceOrDate.get(i - 1).getSupplierCode()) || !merchandiseLongBuyPriceAdjust.getWarehouseCode()
								.equals(listPriceOrDate.get(i - 1).getWarehouseCode()))) {
							mapA.put("search", 1);
							lists = this.getMerchandiseLongBuyPriceAdjustDao().listPriceOrDates(mapA, null);
							// 第一个价格的时候还需要查看该价格是否还有上一次记录
							if (lists.size() > 1) {
								merchandiseLongBuyPriceAdjust.setPurchasePriceO(lists.get(1).getPurchasePrice());
								merchandiseLongBuyPriceAdjust.setPurchasePriceC(merchandiseLongBuyPriceAdjust.getPurchasePrice().subtract(lists.get(1).getPurchasePrice()));
								if (!merchandiseLongBuyPriceAdjust.getPurchasePriceO().equals(new BigDecimal(0))) {
									merchandiseLongBuyPriceAdjust.setPurchasePriceB(
											merchandiseLongBuyPriceAdjust.getPurchasePriceC().multiply(new BigDecimal(100)).divide(merchandiseLongBuyPriceAdjust.getPurchasePriceO(), 2, RoundingMode.HALF_UP));
								}
							} else {
								merchandiseLongBuyPriceAdjust.setPurchasePriceO(null);
								merchandiseLongBuyPriceAdjust.setPurchasePriceC(null);
								merchandiseLongBuyPriceAdjust.setPurchasePriceB(null);
							}
						} else if (bool == false
								|| (merchandiseLongBuyPriceAdjust.getMerchandiseCode().equals(listPriceOrDate.get(i - 1).getMerchandiseCode())
										&& merchandiseLongBuyPriceAdjust.getSupplierCode().equals(listPriceOrDate.get(i - 1).getSupplierCode()) && merchandiseLongBuyPriceAdjust.getWarehouseCode()
										.equals(listPriceOrDate.get(i - 1).getWarehouseCode()))) {
							merchandiseLongBuyPriceAdjust.setPurchasePriceO(listPriceOrDate.get(i - 1).getPurchasePrice());
							merchandiseLongBuyPriceAdjust.setPurchasePriceC(merchandiseLongBuyPriceAdjust.getPurchasePrice().subtract(listPriceOrDate.get(i - 1).getPurchasePrice()));
							if (!merchandiseLongBuyPriceAdjust.getPurchasePriceO().equals(new BigDecimal(0))) {
								merchandiseLongBuyPriceAdjust.setPurchasePriceB(
										merchandiseLongBuyPriceAdjust.getPurchasePriceC().multiply(new BigDecimal(100)).divide(merchandiseLongBuyPriceAdjust.getPurchasePriceO(), 2, RoundingMode.HALF_UP));
							}
						} else {
							lists = this.getMerchandiseLongBuyPriceAdjustDao().listPriceOrDates(mapA, null);
							if (lists.size() > 1) {
								merchandiseLongBuyPriceAdjust.setPurchasePriceO(lists.get(1).getPurchasePrice());
								merchandiseLongBuyPriceAdjust.setPurchasePriceC(merchandiseLongBuyPriceAdjust.getPurchasePrice().subtract(lists.get(1).getPurchasePrice()));
								if (!merchandiseLongBuyPriceAdjust.getPurchasePriceO().equals(new BigDecimal(0))) {
									merchandiseLongBuyPriceAdjust.setPurchasePriceB(
											merchandiseLongBuyPriceAdjust.getPurchasePriceC().multiply(new BigDecimal(100)).divide(merchandiseLongBuyPriceAdjust.getPurchasePriceO(), 2, RoundingMode.HALF_UP));
								}
							} else {
								merchandiseLongBuyPriceAdjust.setPurchasePriceO(null);
								merchandiseLongBuyPriceAdjust.setPurchasePriceC(null);
								merchandiseLongBuyPriceAdjust.setPurchasePriceB(null);
							}
						}
					}
					// 进行参数计算和参数赋值
					merchandiseLongBuyPriceAdjust.setPriceDate(merchandiseLongBuyPriceAdjust.getPriceDate());
					merchandiseLongBuyPriceAdjust.setPurchasePriceN(merchandiseLongBuyPriceAdjust.getPurchasePrice());
					merchandiseLongBuyPriceAdjust.setReceiptRationed(listReceiptInfo.get(x).getReceiptRationed());
					merchandiseLongBuyPriceAdjust.setReceiptTotalPrice(listReceiptInfo.get(x).getReceiptTotalPrice());
					if (merchandiseLongBuyPriceAdjust.getPurchasePriceC() != null) {
						if (!merchandiseLongBuyPriceAdjust.getPurchasePriceC().equals(new BigDecimal(0))) {
							merchandiseLongBuyPriceAdjust.setIncreaseDecrease(merchandiseLongBuyPriceAdjust.getPurchasePriceC().multiply(listReceiptInfo.get(x).getReceiptRationed()));
						} else {
							merchandiseLongBuyPriceAdjust.setIncreaseDecrease(new BigDecimal(0));
						}
					} else {
						merchandiseLongBuyPriceAdjust.setIncreaseDecrease(new BigDecimal(0));
					}
					list.add(merchandiseLongBuyPriceAdjust);
				}
			}
		}
		if (list.size() == 0) {
			return new ArrayList<MerchandiseLongBuyPriceAdjust>();
		}
		return list;
	}

	@Override
	public List<MerchandiseLongBuyPriceAdjust> getMerchandiseLongBuyPriceAdjustSum(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		// 获取数据个数
		List<MerchandiseLongBuyPriceAdjust> list = this.getMerchandiseLongBuyPriceAdjustDao().listMerchandiseSum(map, pageInfo);
		list.clear();
		MerchandiseLongBuyPriceAdjust merchandiseLongBuyPriceAdjust = new MerchandiseLongBuyPriceAdjust();
		// 得到明细表数据
		List<MerchandiseLongBuyPriceAdjust> listDetailInfo = this.getMerchandiseLongBuyPriceAdjustDetailInfo(map, null);
		String merchandiseName = "";
		String supplierName = "";
		String warehouseCode = "";
		if (listDetailInfo != null) {
			for (int i = 0; i < listDetailInfo.size(); i++) {
				if (merchandiseName.equals("")) {
					merchandiseName = listDetailInfo.get(i).getMerchandiseName();
					supplierName = listDetailInfo.get(i).getSupplierName();
					warehouseCode = listDetailInfo.get(i).getWarehouseCode();
				}
				if (listDetailInfo.get(i).getMerchandiseName().equals(merchandiseName) && listDetailInfo.get(i).getSupplierName().equals(supplierName)
						&& listDetailInfo.get(i).getWarehouseCode().equals(warehouseCode)) {
					if (i == 0) {
						merchandiseLongBuyPriceAdjust = listDetailInfo.get(i);
					} else {
						merchandiseLongBuyPriceAdjust.setReceiptRationed(merchandiseLongBuyPriceAdjust.getReceiptRationed().add(listDetailInfo.get(i).getReceiptRationed()));
						merchandiseLongBuyPriceAdjust.setReceiptTotalPrice(merchandiseLongBuyPriceAdjust.getReceiptTotalPrice().add(listDetailInfo.get(i).getReceiptTotalPrice()));
						merchandiseLongBuyPriceAdjust.setIncreaseDecrease(merchandiseLongBuyPriceAdjust.getIncreaseDecrease().add(listDetailInfo.get(i).getIncreaseDecrease()));
					}
				} else {
					merchandiseName = listDetailInfo.get(i).getMerchandiseName();
					supplierName = listDetailInfo.get(i).getSupplierName();
					list.add(merchandiseLongBuyPriceAdjust);
					merchandiseLongBuyPriceAdjust = listDetailInfo.get(i);
				}
				if (i == listDetailInfo.size() - 1) {
					list.add(merchandiseLongBuyPriceAdjust);
				}
			}
		}
		return list;
	}
}
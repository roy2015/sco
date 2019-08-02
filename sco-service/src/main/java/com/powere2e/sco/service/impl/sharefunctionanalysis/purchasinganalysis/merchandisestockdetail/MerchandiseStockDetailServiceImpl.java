package com.powere2e.sco.service.impl.sharefunctionanalysis.purchasinganalysis.merchandisestockdetail;

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
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.purchasinganalysis.merchandisestockdetail.MerchandiseStockDetailDao;
import com.powere2e.sco.interfaces.service.sharefunctionanalysis.purchasinganalysis.merchandisestockdetail.MerchandiseStockDetailService;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.model.sharefunctionanalysis.purchasinganalysis.merchandisestockdetail.MerchandiseStockDetail;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;
import com.powere2e.security.model.Option;

/**
 * 商品进货明细业务类的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public class MerchandiseStockDetailServiceImpl extends ServiceImpl implements MerchandiseStockDetailService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2328541788205369097L;
	private MerchandiseStockDetailDao MerchandiseStockDetailDao;

	public static MerchandiseStockDetailService getInstance() {
		return (MerchandiseStockDetailService) ConfigFactory.getInstance().getBean("MerchandiseStockDetailService");
	}

	// 获得商品进货明细DAO实例
	public MerchandiseStockDetailDao getMerchandiseStockDetailDao() {
		return MerchandiseStockDetailDao;
	}

	// 设置商品进货明细DAO实例
	public void setMerchandiseStockDetailDao(MerchandiseStockDetailDao MerchandiseStockDetailDao) {
		this.MerchandiseStockDetailDao = MerchandiseStockDetailDao;
	}

	@Override
	public List<MerchandiseStockDetail> listMerchandiseStockDetailInfo(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		List<MerchandiseStockDetail> list = this.getMerchandiseStockDetailDao().listMerchandiseStockDetailDetailInfo(map, pageInfo);
		if (list.size() != 0) {
			if (list.get(0).getRealityReceiptDate().equals("合计")) {
				return null;
			}
		}
		return list;
	}

	@Override
	public void exportMerchandiseStockDetail(Map<String, Object> map, ServletOutputStream out) throws Exception {
		List<MerchandiseStockDetail> list = this.listMerchandiseStockDetailInfo(map, null);
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("进货明细报表");
		// 1. 创建excel表头(第一行和第二行)
		this.fillHeaderCellDetail(wb, sheet, map);
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
	private void fillHeaderCellDetail(Workbook wb, Sheet sheet, Map<String, Object> map) {
		// 第一行
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb, 15);

		Row row = sheet.createRow(5);
		int i = 0;// 从第零列(第一列)开始
		Cell cell = row.createCell(i);
		cell.setCellStyle(firstRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("到货日期");

		cell = row.createCell(i);
		cell.setCellStyle(firstRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品编号");

		cell = row.createCell(i);
		cell.setCellStyle(firstRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品名称");

		if (!map.get("show").toString().equals("4")) {
			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("商品定性角色");

			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("商品定量角色");

			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("中分类");

			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("小分类");

			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("明细类");
		}
		cell = row.createCell(i);
		cell.setCellStyle(firstRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("细分类");

		cell = row.createCell(i);
		cell.setCellStyle(firstRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商编号");

		cell = row.createCell(i);
		cell.setCellStyle(firstRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商名称");

		cell = row.createCell(i);
		cell.setCellStyle(firstRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("工厂编号");

		cell = row.createCell(i);
		cell.setCellStyle(firstRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("库存地点");

		cell = row.createCell(i);
		cell.setCellStyle(firstRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品到货量（基本价格单位）");

		cell = row.createCell(i);
		cell.setCellStyle(firstRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品到货额（元）");
		if (!map.get("show").toString().equals("4")) {
			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("小分类到货量（基本价格单位）");

			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("小分类到货额（元）");

			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("到货量占小分类比重");

			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("到货额占小分类比重");
		}
		cell = row.createCell(i);
		cell.setCellStyle(firstRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("公司总到货量（基本价格单位）");

		cell = row.createCell(i);
		cell.setCellStyle(firstRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("公司总到货额（元）");

		cell = row.createCell(i);
		cell.setCellStyle(firstRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("到货量占公司整体比重");

		cell = row.createCell(i);
		cell.setCellStyle(firstRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("到货额占公司整体比重");
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
	private void fillDataCellDetail(List<MerchandiseStockDetail> list, Workbook wb, Sheet sheet, Map<String, Object> map) throws Exception {
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
	private void writeOneRowDateDetail(Sheet sheet, Integer rowIndex, MerchandiseStockDetail msd, CellStyle strStyle, CellStyle dateStyle, CellStyle amtStyle, Map<String, Object> map)
			throws Exception {
		if (rowIndex == 0) {
			Row row = sheet.createRow(rowIndex);
			Cell cell = row.createCell(0);
			cell.setCellStyle(strStyle);
			cell.setCellValue("时间范围:");

			cell = row.createCell(1);
			cell.setCellStyle(strStyle);
			cell.setCellValue(map.get("minDateShow") + "~" + map.get("maxDateShow"));

			row = sheet.createRow(rowIndex + 1);
			rowIndex = rowIndex + 1;
			if (!map.get("show").toString().equals("4")) {
				cell = row.createCell(0);
				cell.setCellStyle(strStyle);
				cell.setCellValue(map.get("show").toString().equals("1") ? "只查定量装商品" : map.get("show").toString().equals("2") ? "只查非定量装商品" : "定量非定量商品均看");
				row = sheet.createRow(rowIndex + 1);
				rowIndex = rowIndex + 1;
			}

			cell = row.createCell(0);
			cell.setCellStyle(strStyle);
			cell.setCellValue(map.get("type").toString().equals("day") ? "查看日明细" : "查看月明细");

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
			rowIndex = 0;
		}
		Row row = sheet.createRow(rowIndex + 6);
		int j = 0;
		Cell cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getRealityReceiptDate());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getMerchandiseCode());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getMerchandiseName());
		if (!map.get("show").toString().equals("4")) {
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

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getRegionCode());

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getReceiptRationedM()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getReceiptTotalPriceM()));
		// 判断为辅料或者商品
		if (!map.get("show").toString().equals("4")) {
			cell = row.createCell(j++);
			cell.setCellStyle(amtStyle);
			try {
				if (!msd.getRealityReceiptDate().equals("合计")) {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getReceiptRationedS()));
				}
			} catch (Exception e) {

				try {
					if (msd.getReceiptRationedS() != null) {
						cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getReceiptRationedS()));
					}
				} catch (Exception e2) {

				}
			}

			cell = row.createCell(j++);
			cell.setCellStyle(amtStyle);
			try {
				if (!msd.getRealityReceiptDate().equals("合计")) {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getReceiptTotalPriceS()));
				}
			} catch (Exception e) {
				try {
					if (msd.getReceiptTotalPriceS() != null) {
						cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getReceiptTotalPriceS()));
					}
				} catch (Exception e2) {

				}
			}
			cell = row.createCell(j++);
			cell.setCellStyle(amtStyle);
			try {
				if (!msd.getRealityReceiptDate().equals("合计")) {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getReceiptRationedMS()) + "%");
				}
			} catch (Exception e) {

				try {
					if (msd.getReceiptRationedMS() != null) {
						cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getReceiptRationedMS()) + "%");
					}
				} catch (Exception e2) {

				}
			}

			cell = row.createCell(j++);
			cell.setCellStyle(amtStyle);
			try {
				if (!msd.getRealityReceiptDate().equals("合计")) {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getReceiptTotalPriceMS()) + "%");
				}
			} catch (Exception e) {

				try {
					if (msd.getReceiptTotalPriceMS() != null) {
						cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getReceiptTotalPriceMS()) + "%");
					}
				} catch (Exception e2) {

				}
			}
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		if (msd.getReceiptRationed() != null) {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getReceiptRationed()));
		} else {
			cell.setCellValue("");
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		if (msd.getReceiptTotalPrice() != null) {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getReceiptTotalPrice()));
		} else {
			cell.setCellValue("");
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		if (msd.getReceiptRationedMA() != null) {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getReceiptRationedMA()) + "%");
		} else {
			cell.setCellValue("");
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		if (msd.getReceiptTotalPriceMA() != null) {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getReceiptTotalPriceMA()) + "%");
		} else {
			cell.setCellValue("");
		}
	}

	// 保存页面
	@Override
	public String saveMerchandiseStockDetail(String fileName, Map<String, Object> paraMap) throws Exception {
		String tarPath;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("showName", paraMap.get("show").equals("1") ? "只查定量装商品" : "只查非定量装商品");
		map.put("typeName", paraMap.get("type").equals("day") ? "查看日明细" : "查看月明细");
		map.put("regionCodeSplit", paraMap.get("regionCode").toString().replace("'", ""));
		map.put("warehouseCodeSplit", paraMap.get("warehouseCode").toString().replace("'", ""));
		map.put("date", paraMap.get("minDateShow") + "~" + paraMap.get("maxDateShow"));
		map.put("dataList", this.listMerchandiseStockDetailInfo(paraMap, null));
		try {
			if (!paraMap.get("show").toString().equals("4")) {
				tarPath = FreeMarkerUtil.generateHtml("purchasingAnalysis/merchandiseStockDetail/merchandiseStockDetailInfoGrid.ftl", "merchandiseStockDetailInfo".concat("/").concat(fileName), map);
			} else {
				tarPath = FreeMarkerUtil.generateHtml("purchasingAnalysis/accessoryStockDetail/accessoryStockDetailInfoGrid.ftl", "accessoryStockDetailInfo".concat("/").concat(fileName), map);
			}
		} catch (Exception e) {
			return "转换报表模板时异常";
		}
		File file = new File(tarPath);// 报表文件
		if (file.exists()) {
			try {
				Reports myReport = new Reports(BusinessConstants.myReportType.STD.toString(), fileName, tarPath.replace(ConfigPath.getUploadFilePath(), ""));
				ReportsServiceImpl.getInstance().insertReports(myReport);
			} catch (Exception e) {
				file.delete();
				LoggerUtil.logger.error("MerchandiseStockDetailServiceImpl.saveMerchandiseStockDetail删除文件["+ file.getPath() +"]");
				throw new EscmException("记录报表文件信息时出错");
			}
		} else {
			return "生成Html文件失败";
		}
		return null;
	}

	@Override
	public List<MerchandiseStockDetail> listMerchandise(Map<String, Object> map, PageInfo pageInfo) {
		return this.getMerchandiseStockDetailDao().listMerchandise(map, pageInfo);
	}

	@Override
	public List<Option> listRegion(String regionCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("regionCode", regionCode);
		return this.getMerchandiseStockDetailDao().listRegion(map);
	}

	@Override
	public List<Option> listWarehouse() {
		return this.getMerchandiseStockDetailDao().listWarehouse();
	}

	@Override
	public List<Option> listWarehouseSite() {
		return this.getMerchandiseStockDetailDao().listWarehouseSite();
	}
}
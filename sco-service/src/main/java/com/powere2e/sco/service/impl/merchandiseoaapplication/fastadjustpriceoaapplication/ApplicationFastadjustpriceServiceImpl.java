package com.powere2e.sco.service.impl.merchandiseoaapplication.fastadjustpriceoaapplication;

import java.io.File;
import java.io.IOException;
import java.util.Date;
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
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.service.merchandiseoaapplication.MerchandiseOaApplicationUtil;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.MerchandiseOaApplicationDao;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.fastadjustpriceoaapplication.ApplicationFastadjustpriceDao;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.fastadjustpriceoaapplication.ApplicationFastadjustpriceService;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.merchandiseoaapplication.fastadjustpriceoaapplication.ApplicationFastadjustprice;
import com.powere2e.sco.service.impl.merchandiseoaapplication.MerchandiseOaApplicationServiceImpl;

/**
 * 申请报告(快速调价)业务类的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月20日
 */
public class ApplicationFastadjustpriceServiceImpl extends ServiceImpl implements ApplicationFastadjustpriceService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4170788089640625409L;
	private ApplicationFastadjustpriceDao fastAdjustpriceDao;
	private MerchandiseOaApplicationDao merchandiseOaApplicationDao;// oa申请的dao
	private Font font;

	public static ApplicationFastadjustpriceService getInstance() {
		return (ApplicationFastadjustpriceService) ConfigFactory.getInstance().getBean("fastAdjustpriceService");
	}

	// 查询没有申请报告的商品
	@Override
	public List<ApplicationLackFileM> queryNotHaveReportMerchandiseFastadjustprice(Map<String, Object> map) throws Exception {
		return fastAdjustpriceDao.queryNotHaveReportMerchandiseFastadjustprice(map);
	}

	// 查询
	@Override
	public List<ApplicationFastadjustprice> listApplicationFastadjustprice(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return fastAdjustpriceDao.listApplicationFastadjustprice(map, pageInfo);
	}

	// 添加
	@Override
	public void insertApplicationFastadjustprice(ApplicationFastadjustprice applicationFastadjustprice) throws Exception {
		fastAdjustpriceDao.insertApplicationFastadjustprice(applicationFastadjustprice.toMap());
	}

	// 删除
	@Override
	public void deleteApplicationFastadjustprice(String reportCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportCode", reportCode);
		fastAdjustpriceDao.deleteApplicationFastadjustprice(map);
	}

	// 加载一个申请报告(快速调价)
	@Override
	public ApplicationFastadjustprice loadApplicationFastadjustprice(String reportCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportCode", reportCode);
		return fastAdjustpriceDao.loadApplicationFastadjustprice(map);
	}

	// 申请文件的商品
	@Override
	public List<ApplicationFastadjustprice> listApplicationFileMerchandise(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return fastAdjustpriceDao.listApplicationFileMerchandise(map, pageInfo);
	}

	@Override
	public String completeInsertOrUpdateFastadjustprice(File uploadFile, Map<String, Object> map) throws Exception {
		String applicationCode = (String) map.get("applicationCode");
		String intentionAndSupplierCodes = (String) map.get("intentionAndSupplierCodes");
		String receiptInfo = MerchandiseOaApplicationServiceImpl.getInstance().getIntentionOaApplicationReceiptInfo(applicationCode, intentionAndSupplierCodes,
				BusinessConstants.ApplicationType.MERCHANDISE_FASTADJUSTPRICE.toString());
		if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.NONE.toString().equals(receiptInfo)
				|| MerchandiseOaApplicationUtil.ReportNewOaApplicationState.SAME_CG.toString().equals(receiptInfo)) {
			// map.put("applicationCode", applicationCode);
			/*List<ApplicationMerchandise> list = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(intentionAndSupplierCodes);
			if (list != null && list.size() > 0) {
				ApplicationMerchandise merchandise = list.get(0);
				map.put("merchandiseCode", merchandise.getMerchandiseCode());
				map.put("supplierCode", merchandise.getSupplierCode());
				// map.put("path", filePath);
			}*/
			map.put("uploadDate", new Date());// 文件上传日期
			fastAdjustpriceDao.deleteApplicationFastadjustprice(map);// 根据申请单号和、申请文件名称、商品编号和供应商编号删除
			fastAdjustpriceDao.insertApplicationFastadjustprice(map);
		}
		// 判断勾选数据是否进行OA申请的回执
		return receiptInfo;
	}

	// 查询申请文件
	@Override
	public List<ApplicationFastadjustprice> listApplicationFiles(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return fastAdjustpriceDao.listApplicationFiles(map, pageInfo);
	}

	// 根据申请单号删除申请报告(快速调价)
	@Override
	public void completeDeleteFastadjustpriceByApplicationCode(Map<String, Object> map) throws Exception {
		fastAdjustpriceDao.deleteFastadjustpriceByApplicationCode(map);// 删除OA申请报告
		merchandiseOaApplicationDao.deleteApplicationMerhandiseByCode(map);// 删除OA申请关联表
		merchandiseOaApplicationDao.deleteOaApplicationByCode(map);// 删除OA申请表
	}

	// 根据报告编号删除申请文件
	@Override
	public String deleteApplicationFiles(Map<String, Object> map) throws Exception {
		String intentionAndSupplierCodes = (String) map.get("intentionAndSupplierCodes");
		String applicationCode = (String) map.get("applicationCode");
		String receiptInfo = MerchandiseOaApplicationServiceImpl.getInstance().getIntentionOaApplicationReceiptInfo(applicationCode, intentionAndSupplierCodes,
				BusinessConstants.ApplicationType.MERCHANDISE_FASTADJUSTPRICE.toString());
		if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.SAME_CG.toString().equals(receiptInfo)) {
			// 如果为草稿状态，就可以删除申请文件，否则不能删除
			fastAdjustpriceDao.deleteApplicationFiles(map);
		}
		return receiptInfo;
	}
	
	@Override
	public void exportSignedQtyExcel(Map<String, Object> map,
			ServletOutputStream out) {
		try {
		List<MerchandiseIntention> list = this.merchandiseOaApplicationDao.queryMerchandiseApplicationList(map, null);
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("商品快速调价OA申请列表");
		font = wb.createFont();
		font.setFontName("微软雅黑");
		// 1. 创建excel表头(第一行和第二行)
		this.fillHeaderCell(wb, sheet);

		// 2.用数据填充单元格
		if (!list.isEmpty())
			this.fillDataCell(list, wb, sheet);
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
	private void fillHeaderCell(Workbook wb, Sheet sheet) {
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb, ExcelUtils.HEADR_FONT_SIZE);
		CellStyle leftSencondRowStyle = ExcelUtils.getHeaderCellStyle(wb, null);
		leftSencondRowStyle.setFont(font);
		leftSencondRowStyle.setAlignment(CellStyle.ALIGN_LEFT);
		CellStyle rightSencondRowStyle = ExcelUtils.getHeaderCellStyle(wb, null);
		rightSencondRowStyle.setFont(font);
		rightSencondRowStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		int i = 0;// 从第零列(第一列)开始

		// 第一行
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(i);
		firstRowStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("SCO申请单号");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (120 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品编号");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品名称");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商编号");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商名称");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("快速调价申请单状态");
		
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
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("采购部门");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("是否定量装");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("采购类型");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售方式");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
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
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("申请单创建人");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("申请单创建日期");
	}
	
	/**
	 * 为sheet填充数据
	 * 
	 * @param sheet
	 *            sheet
	 * @param wb
	 *            工作簿
	 * @param list
	 *            数据
	 */
	private void fillDataCell(List<MerchandiseIntention> list, Workbook wb, Sheet sheet) {
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		strStyle.setFont(font);
		
		CellStyle intStyle = ExcelUtils.getDefaultPercentStyle(wb);// 百分比样式
		intStyle.setFont(font);
		intStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		intStyle.setDataFormat(wb.createDataFormat().getFormat("#,##0"));
		
		CellStyle perStyle = ExcelUtils.getDefaultPercentStyle(wb);// 百分比样式
		perStyle.setFont(font);
		perStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		perStyle.setDataFormat(wb.createDataFormat().getFormat("0.00%"));
		
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式
		amtStyle.setFont(font);
		amtStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		amtStyle.setDataFormat(wb.createDataFormat().getFormat("#,##0.00"));

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == null) {
				continue;
			}
			writeOneRowDate(sheet, i, list.get(i), strStyle, amtStyle, perStyle, intStyle);
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
	 */
	private void writeOneRowDate(Sheet sheet, Integer rowIndex, MerchandiseIntention merchandiseIntention, CellStyle strStyle,  CellStyle amtStyle, CellStyle perStyle,CellStyle intStyle) {
		Row row = sheet.createRow(rowIndex + 1);

		int j = 0;
		// SCO申请单号
		Cell cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getApplicationCode());

		// 商品编号
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getMerchandiseCode());
		
		// 商品名称
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getMerchandiseName());
		
		// 供应商编号
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getSupplierCode());
		
		// 供应商名称
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getSupplierName());
		
		// 快速调价申请单状态
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getApplicationStatusName());
		
		// 商品定性角色
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getDxRoleName());
		
		// 商品定量角色
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getDlRoleName());
		
		// 采购部门
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getPurchaseDepartmentsName());
		
		// 是否定量装
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getRationedName());
		
		// 采购类型
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getPurchaseTypeName());
		
		// 销售方式
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getSaleType());
		
		// 中分类
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getCentreTypeName());
		
		// 小分类
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getSmallTypeName());
		
		// 明细类
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getDetailTypeName());
		
		// 细分类
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getFineTypeName());
		
		// 申请单创建人
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getOaApplicationName());
		
		// 申请单创建日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(DateUtils.formatDateToStr(merchandiseIntention.getOaApplicationDate(),"yyyy-MM-dd"));
	}

	// 获得申请报告(快速调价)DAO实例
	public ApplicationFastadjustpriceDao getFastAdjustpriceDao() {
		return fastAdjustpriceDao;
	}

	// 设置申请报告(快速调价)DAO实例
	public void setFastAdjustpriceDao(ApplicationFastadjustpriceDao fastAdjustpriceDao) {
		this.fastAdjustpriceDao = fastAdjustpriceDao;
	}

	public MerchandiseOaApplicationDao getMerchandiseOaApplicationDao() {
		return merchandiseOaApplicationDao;
	}

	public void setMerchandiseOaApplicationDao(MerchandiseOaApplicationDao merchandiseOaApplicationDao) {
		this.merchandiseOaApplicationDao = merchandiseOaApplicationDao;
	}

}
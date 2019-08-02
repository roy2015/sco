package com.powere2e.sco.service.impl.merchandiseoaapplication.reportadjustpriceoaapplication;

import java.io.IOException;
import java.math.BigDecimal;
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
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.service.merchandiseoaapplication.MerchandiseOaApplicationUtil;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.DecimalFormatUtils;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.MerchandiseOaApplicationDao;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportadjustpriceoaapplication.ApplicationReportAdjustpriceDao;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.HistoryPriceAdjustpriceDao;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportnewoaapplication.supplierattachment.SupplierAttachmentMDao;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportadjustpriceoaapplication.ApplicationReportAdjustpriceService;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationMerchandise;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.ApplicationReportAdjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.AtpTotalAdjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.CheckStandardAdjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.HistoryPriceAdjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.MerchandiseMaterial;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.SameMerchandiseAdjustprice;
import com.powere2e.sco.service.impl.merchandiseoaapplication.MerchandiseOaApplicationServiceImpl;

/**
 * 申请报告(快速调价)业务类的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月20日
 */
public class ApplicationReportAdjustpriceServiceImpl extends ServiceImpl implements ApplicationReportAdjustpriceService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -638916444463318799L;
	private ApplicationReportAdjustpriceDao reportAdjustpriceDao;// 正常调价报告的dao
	private HistoryPriceAdjustpriceDao priceAdjustpriceDao;// 正常调价价格的dao
	private MerchandiseOaApplicationDao merchandiseOaApplicationDao;// oa申请的dao
	private SupplierAttachmentMDao supplierAttachmentMDao;// 供应商附件的dao
	private Font font;

	public static ApplicationReportAdjustpriceService getInstance() {
		return (ApplicationReportAdjustpriceService) ConfigFactory.getInstance().getBean("reportAdjustpriceService");
	}

	// 查询没有报告的商品
	@Override
	public List<ApplicationLackFileM> queryNotHaveReportMerchandiseAdjustprice(Map<String, Object> map)  throws Exception{
		return reportAdjustpriceDao.queryNotHaveReportMerchandiseAdjustprice(map);
	}

	// 查询
	@Override
	public List<ApplicationReportAdjustprice> listApplicationReportAdjustprice(String applicationCode, String intentionAndSupplierCodes)  throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		List<ApplicationMerchandise> list = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(intentionAndSupplierCodes);
		map.put("list", list);
		map.put("applicationCode", applicationCode);
		List<ApplicationReportAdjustprice> adjustPriceList = reportAdjustpriceDao.listApplicationReportAdjustprice(map);
		if (adjustPriceList.isEmpty()) {
			// 如果申请报告不存在，就查询商品信息
			return reportAdjustpriceDao.queryReportOfAdjustprice(map);
		} else {
			// 申请报告存在
			return adjustPriceList;
		}
	}

	// 加载一个申请报告(快速调价)
	@Override
	public ApplicationReportAdjustprice loadApplicationReportAdjustprice(String applicationCode, String intentionAndSupplierCodes)  throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		List<ApplicationMerchandise> list = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(intentionAndSupplierCodes);
		if (list != null && list.size() > 0) {
			ApplicationMerchandise merchandise = list.get(0);
			map.put("intentionCode", merchandise.getMerchandiseCode());
			map.put("supplierCode", merchandise.getSupplierCode());
		}
		map.put("applicationCode", applicationCode);
		return reportAdjustpriceDao.loadApplicationReportAdjustprice(map);
	}

	// 添加
	@Override
	public String insertApplicationReportAdjustprice(List<ApplicationReportAdjustprice> applicationList, String applicationCode, String intentionAndSupplierCodes)  throws Exception{
		String receiptInfo = MerchandiseOaApplicationServiceImpl.getInstance().getIntentionOaApplicationReceiptInfo(applicationCode, intentionAndSupplierCodes,
				BusinessConstants.ApplicationType.MERCHANDISE_ADJUSTPRICE.toString());
		// 只有为OA申请或者OA申请状态为草稿的才可以进行操作
		if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.NONE.toString().equals(receiptInfo)) {
			this.completeInsertOrUpdateReportAdjustprice(applicationList, applicationCode);
		} else if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.SAME_CG.toString().equals(receiptInfo)) {
			// TODO当已经做了OA申请，并且状态为"草稿"时
			// 先查询报告编号，然后根据报告编号删除所以的信息，再新增
			List<ApplicationReportAdjustprice> reportList = listApplicationReportAdjustprice(applicationCode, intentionAndSupplierCodes);
			if (reportList != null && reportList.size() > 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (ApplicationReportAdjustprice applicationReportAdjustprice : reportList) {
					map.put("reportCode", applicationReportAdjustprice.getReportCode());
					reportAdjustpriceDao.deleteApplicationReportAdjustprice(map);// 删除正常调价

					priceAdjustpriceDao.deleteCheckStandardAdjustprice(map);// 删除检验标准
					priceAdjustpriceDao.deleteHistoryPriceAdjustprice(map);// 删除历史与本次价格
					priceAdjustpriceDao.deleteMerchandiseMaterial(map);// 删除商品原料
					priceAdjustpriceDao.deleteAtpTotalAdjustprice(map);// 删除签量总计
					priceAdjustpriceDao.deleteSameMerchandiseAdjustprice(map);// 删除同类商品
				}
			}
			this.completeInsertOrUpdateReportAdjustprice(applicationList, applicationCode);
		}
		// 判断勾选数据是否进行OA申请的回执
		return receiptInfo;
	}

	// 插入老品新上报告和关联信息
	@Override
	public void completeInsertOrUpdateReportAdjustprice(List<ApplicationReportAdjustprice> applicationList, String applicationCode)  throws Exception{
		if (applicationList != null && applicationList.size() > 0) {
			Map<String,Object> map=new HashMap<String,Object>();
			// 说明还没有新增正常调价的报告，新增
			for (ApplicationReportAdjustprice applicationReportAdjustprice : applicationList) {
				//String reportCode = SequenceFactory.getInstance().nextID("APPLICATION_REPORT_ADJUSTPRICE");
				map.clear();
				map.put("sequenceName", "S_APPLICATION_REPORT_ADJUSTPRI");//序列名太长了
				String reportCode = merchandiseOaApplicationDao.selectReportNextID(map);
				applicationReportAdjustprice.setApplicationCode(applicationCode);
				applicationReportAdjustprice.setReportCode(reportCode);
				// 插入申请报告
				reportAdjustpriceDao.insertApplicationReportAdjustprice(applicationReportAdjustprice.toMap());
				// 插入报告检验标准
				CheckStandardAdjustprice checkStandardAdjustprice = new CheckStandardAdjustprice();
				checkStandardAdjustprice.setReportCode(reportCode);
				checkStandardAdjustprice.setOldStandardColour(applicationReportAdjustprice.getOldStandardColour());
				checkStandardAdjustprice.setOldStandardFrom(applicationReportAdjustprice.getOldStandardFrom());
				checkStandardAdjustprice.setOldStandardSmell(applicationReportAdjustprice.getOldStandardSmell());
				checkStandardAdjustprice.setNewStandardColour(applicationReportAdjustprice.getNewStandardColour());
				checkStandardAdjustprice.setNewStandardForm(applicationReportAdjustprice.getNewStandardForm());
				checkStandardAdjustprice.setNewStandardSmell(applicationReportAdjustprice.getNewStandardSmell());
				checkStandardAdjustprice.setOldMoistureContent(applicationReportAdjustprice.getOldMoistureContent());
				checkStandardAdjustprice.setNewMoistureContent(applicationReportAdjustprice.getNewMoistureContent());
				priceAdjustpriceDao.insertCheckStandardAdjustprice(checkStandardAdjustprice.toMap());

				// 插入本次商品价格(正常调价)
				HistoryPriceAdjustprice oldPriceArr[] = applicationReportAdjustprice.getOldPriceArr();
				if (oldPriceArr.length > 0) {
					for (HistoryPriceAdjustprice oldPriceAdjustprice : oldPriceArr) {
						oldPriceAdjustprice.setReportCode(reportCode);
						oldPriceAdjustprice.setPiceType("old");
						if ("".equals(oldPriceAdjustprice.getSellPrice())||oldPriceAdjustprice.getSellPrice() == null || oldPriceAdjustprice.getSellPrice().compareTo(BigDecimal.ZERO) == 0) {
							oldPriceAdjustprice.setProfitRate(null);	
						}else{
						oldPriceAdjustprice.setProfitRate(DecimalFormatUtils.divideBigDecimal(oldPriceAdjustprice.getPurchasePrice(), oldPriceAdjustprice.getSellPrice(), 4));
						}
						priceAdjustpriceDao.insertHistoryPriceAdjustprice(oldPriceAdjustprice.toMap());
					}
				}
				// 插入历史商品价格(正常调价)
				HistoryPriceAdjustprice nowPriceArr[] = applicationReportAdjustprice.getNowPriceArr();
				if (nowPriceArr.length > 0) {
					for (HistoryPriceAdjustprice nowPriceAdjustprice : nowPriceArr) {
						nowPriceAdjustprice.setReportCode(reportCode);
						nowPriceAdjustprice.setPiceType("now");
						if ("".equals(nowPriceAdjustprice.getSellPrice())||nowPriceAdjustprice.getSellPrice() == null || nowPriceAdjustprice.getSellPrice().compareTo(BigDecimal.ZERO) == 0) {
							nowPriceAdjustprice.setProfitRate(null);	
						}else{
						nowPriceAdjustprice.setProfitRate(DecimalFormatUtils.divideBigDecimal(nowPriceAdjustprice.getPurchasePrice(), nowPriceAdjustprice.getSellPrice(), 4));
						}
						priceAdjustpriceDao.insertHistoryPriceAdjustprice(nowPriceAdjustprice.toMap());
					}
				}
				// 同类商品市场零售价(正常调价)
				SameMerchandiseAdjustprice sameArr[] = applicationReportAdjustprice.getSameArr();
				if (sameArr.length > 0) {
					for (SameMerchandiseAdjustprice sameAdjustprice : sameArr) {
						sameAdjustprice.setReportCode(reportCode);
						priceAdjustpriceDao.insertSameMerchandiseAdjustprice(sameAdjustprice.toMap());
					}
				}
				// 商品材料(正常调价)
				MerchandiseMaterial materialArr[] = applicationReportAdjustprice.getMaterialArr();
				if (materialArr.length > 0) {
					for (MerchandiseMaterial materialAdjustprice : materialArr) {
						materialAdjustprice.setReportCode(reportCode);
						priceAdjustpriceDao.insertMerchandiseMaterial(materialAdjustprice.toMap());
					}
				}
				
				//商品签量(正常调价)
				AtpTotalAdjustprice totalAdjustprice=new AtpTotalAdjustprice();
				totalAdjustprice.setReportCode(reportCode);
				totalAdjustprice.setQlDate(applicationReportAdjustprice.getQlDate());
				totalAdjustprice.setAtpQuantitySum(applicationReportAdjustprice.getAtpQuantitySum());
				totalAdjustprice.setAtpQuantitySumAccomplish(applicationReportAdjustprice.getAtpQuantitySumAccomplish());
				totalAdjustprice.setAtpQuantitySumUnfinished(applicationReportAdjustprice.getAtpQuantitySumUnfinished());
				totalAdjustprice.setRemarks(applicationReportAdjustprice.getRemarks());
				
				priceAdjustpriceDao.insertAtpTotalAdjustprice(totalAdjustprice.toMap());
			}
		}
	}

	// 删除
	@Override
	public void deleteApplicationReportAdjustprice(String reportCode)  throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportCode", reportCode);
		reportAdjustpriceDao.deleteApplicationReportAdjustprice(map);
	}

	// 修改
	@Override
	public void updateApplicationReportAdjustprice(ApplicationReportAdjustprice applicationReportAdjustprice)  throws Exception{
		reportAdjustpriceDao.updateApplicationReportAdjustprice(applicationReportAdjustprice.toMap());
	}

	@Override
	public void deleteAllReportAdjustpriceByApplicationCode(Map<String, Object> map)  throws Exception{
		priceAdjustpriceDao.deleteCheckStandardAdjustpriceByCode(map);// 删除检验标准
		priceAdjustpriceDao.deleteHistoryPriceAdjustpriceByCode(map);// 删除历史与本次价格
		priceAdjustpriceDao.deleteMerchandiseMaterialByCode(map);// 删除商品物料
		priceAdjustpriceDao.deleteAtpTotalAdjustpriceByCode(map);// 删除签量总计
		priceAdjustpriceDao.deleteSameMerchandiseAdjustpriceByCode(map);// 删除同类商品

		reportAdjustpriceDao.deleteReportAdjustpriceByApplicationCode(map);

		merchandiseOaApplicationDao.deleteApplicationMerhandiseByCode(map);// 删除OA申请关联表
		merchandiseOaApplicationDao.deleteOaApplicationByCode(map);// 删除OA申请表
		supplierAttachmentMDao.deleteSupplierAttachmentByCode(map);// 删除供应商附件
		
		priceAdjustpriceDao.deleteAnalysisReportsMByApplicationCode(map);//删除关联分析报表(商品OA)

	}

	@Override
	public void exportSignedQtyExcel(Map<String, Object> map,
			ServletOutputStream out) {
		try {
		List<MerchandiseIntention> list = this.merchandiseOaApplicationDao.queryMerchandiseApplicationList(map, null);
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("商品正常调价OA申请列表");
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
		cell.setCellValue("正常调价申请单状态");
		
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
		
		// 正常调价申请单状态
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
		cell.setCellValue(DateUtils.formatDateToStr(merchandiseIntention.getOaApplicationDate(), "yyyy-MM-dd"));
	}
	
	// 获得申请报告(快速调价)DAO实例

	public ApplicationReportAdjustpriceDao getReportAdjustpriceDao() {
		return reportAdjustpriceDao;
	}

	// 设置申请报告(快速调价)DAO实例
	public void setReportAdjustpriceDao(ApplicationReportAdjustpriceDao reportAdjustpriceDao) {
		this.reportAdjustpriceDao = reportAdjustpriceDao;
	}

	public HistoryPriceAdjustpriceDao getPriceAdjustpriceDao() {
		return priceAdjustpriceDao;
	}

	public void setPriceAdjustpriceDao(HistoryPriceAdjustpriceDao priceAdjustpriceDao) {
		this.priceAdjustpriceDao = priceAdjustpriceDao;
	}

	public MerchandiseOaApplicationDao getMerchandiseOaApplicationDao() {
		return merchandiseOaApplicationDao;
	}

	public void setMerchandiseOaApplicationDao(MerchandiseOaApplicationDao merchandiseOaApplicationDao) {
		this.merchandiseOaApplicationDao = merchandiseOaApplicationDao;
	}

	public SupplierAttachmentMDao getSupplierAttachmentMDao() {
		return supplierAttachmentMDao;
	}

	public void setSupplierAttachmentMDao(SupplierAttachmentMDao supplierAttachmentMDao) {
		this.supplierAttachmentMDao = supplierAttachmentMDao;
	}

}
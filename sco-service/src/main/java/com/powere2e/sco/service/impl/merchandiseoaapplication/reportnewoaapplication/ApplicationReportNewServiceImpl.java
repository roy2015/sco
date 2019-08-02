package com.powere2e.sco.service.impl.merchandiseoaapplication.reportnewoaapplication;

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
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportnewoaapplication.ApplicationReportNewDao;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportnewoaapplication.reportnew.MerchandisePriceNewDao;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportnewoaapplication.supplierattachment.SupplierAttachmentMDao;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportnewoaapplication.ApplicationReportNewService;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationMerchandise;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.MerchandiseMaterial;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.ApplicationReportNew;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.reportnew.CheckStandardNew;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.reportnew.MerchandisePriceNew;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.reportnew.SameMerchandiseNew;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.reportnew.SellAnticipatedNew;
import com.powere2e.sco.service.impl.merchandiseoaapplication.MerchandiseOaApplicationServiceImpl;

/**
 * 申请报告(新品引进)业务类的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月20日
 */
public class ApplicationReportNewServiceImpl extends ServiceImpl implements ApplicationReportNewService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3357139909125967491L;
	private ApplicationReportNewDao reportNewDao;
	private MerchandisePriceNewDao priceNewDao;
	private MerchandiseOaApplicationDao merchandiseOaApplicationDao;// oa申请的dao
	private SupplierAttachmentMDao supplierAttachmentMDao;// 供应商附件的dao
	private Font font = null;

	public static ApplicationReportNewService getInstance() {
		return (ApplicationReportNewService) ConfigFactory.getInstance().getBean("reportNewService");
	}

	// 没有报告的商品
	@Override
	public List<ApplicationLackFileM> queryNotHaveReportMerchandiseNew(Map<String, Object> map)  throws Exception{
		return reportNewDao.queryNotHaveReportMerchandiseNew(map);
	}

	// 查询
	@Override
	public List<ApplicationReportNew> listApplicationReportNew(String applicationCode,String intentionAndSupplierCodes)  throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		List<ApplicationMerchandise> list = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(intentionAndSupplierCodes);
		map.put("list", list);
		map.put("applicationCode", applicationCode);

		List<ApplicationReportNew> newList = reportNewDao.listApplicationReportNew(map);
		if (newList.isEmpty()) {
			// 如果申请报告不存在，就查询商品信息
			return reportNewDao.queryReportOfNew(map);
		} else {
			// 申请报告存在
			return newList;
		}
	}

	// 加载一个申请报告(新品引进)
	@Override
	public ApplicationReportNew loadApplicationReportNew(String applicationCode, String intentionAndSupplierCodes)  throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		List<ApplicationMerchandise> list = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(intentionAndSupplierCodes);
		if (list != null && list.size() > 0) {
			ApplicationMerchandise merchandise = list.get(0);
			map.put("intentionCode", merchandise.getMerchandiseCode());
			map.put("supplierCode", merchandise.getSupplierCode());
		}
		map.put("applicationCode", applicationCode);
		return reportNewDao.loadApplicationReportNew(map);
	}

	// 添加
	@Override
	public String insertApplicationReportNew(List<ApplicationReportNew> applicationList, String applicationCode, String intentionAndSupplierCodes)  throws Exception{
		String receiptInfo = MerchandiseOaApplicationServiceImpl.getInstance().getIntentionOaApplicationReceiptInfo(applicationCode, intentionAndSupplierCodes,
				BusinessConstants.ApplicationType.MERCHANDISE_NEW.toString());
		// 只有为OA申请或者OA申请状态为草稿的才可以进行操作
		if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.NONE.toString().equals(receiptInfo)) {
			this.completeInsertOrUpdateReport(applicationList, applicationCode);
		} else if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.SAME_CG.toString().equals(receiptInfo)) {
			// TODO当已经做了OA申请，并且状态为"草稿"时
			// 先查询报告编号，然后根据报告编号删除所以的信息，再新增
			List<ApplicationReportNew> reportList = listApplicationReportNew(applicationCode,intentionAndSupplierCodes);
			if (reportList != null && reportList.size() > 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (ApplicationReportNew appplicationReportNew : reportList) {
					map.put("reportCode", appplicationReportNew.getReportCode());
					reportNewDao.deleteApplicationReportNew(map);// 删除新品引进报告
					priceNewDao.deleteMerchandisePriceNew(map);// 删除新品引进价格
					priceNewDao.deleteSameMerchandiseNew(map);// 删除新品引进同类商品
					priceNewDao.deleteSellAnticipatedNew(map);// 删除新品引进商品销售
					priceNewDao.deleteMerchandiseMaterial(map);// 删除商品原料
					priceNewDao.deleteCheckStandardNew(map);// 删除老品新上检验标准
				}
			}
			this.completeInsertOrUpdateReport(applicationList, applicationCode);
		}
		// 判断勾选数据是否进行OA申请的回执
		return receiptInfo;
	}

	/**
	 * 新增或修改报告
	 */
	public void completeInsertOrUpdateReport(List<ApplicationReportNew> applicationList, String applicationCode)  throws Exception{
		if (applicationList != null && applicationList.size() > 0) {
			Map<String,Object> map=new HashMap<String,Object>();
			// 说明还没有新增新品引进的报告，新增
			for (ApplicationReportNew appplicationReportNew : applicationList) {
				//String reportCode = SequenceFactory.getInstance().nextID("APPLICATION_REPORT_NEW");
				map.clear();
				map.put("sequenceName", "S_APPLICATION_REPORT_NEW");
				String reportCode = merchandiseOaApplicationDao.selectReportNextID(map);
				appplicationReportNew.setApplicationCode(applicationCode);
				appplicationReportNew.setReportCode(reportCode);
				// 插入申请报告
				reportNewDao.insertApplicationReportNew(appplicationReportNew.toMap());
				// 插入商品价格(新品引进)
				MerchandisePriceNew priceArr[] = appplicationReportNew.getPriceArr();
				if (priceArr.length > 0) {
					for (MerchandisePriceNew priceNew : priceArr) {
						priceNew.setReportCode(reportCode);
						if ("".equals(priceNew.getSellPrice())||priceNew.getSellPrice() == null || priceNew.getSellPrice().compareTo(BigDecimal.ZERO) == 0) {
							priceNew.setProfitRate(null);	
						}else{
						priceNew.setProfitRate(DecimalFormatUtils.divideBigDecimal(priceNew.getPurchasePrice(), priceNew.getSellPrice(), 4));
						}
						priceNewDao.insertMerchandisePriceNew(priceNew.toMap());
					}
				}
				
				//插入报告检验标准(新品引进)
				CheckStandardNew checkstandardnew = new CheckStandardNew();
				checkstandardnew.setReportCode(reportCode);
				checkstandardnew.setColour(appplicationReportNew.getColour());
				checkstandardnew.setSmell(appplicationReportNew.getSmell());
				checkstandardnew.setForm(appplicationReportNew.getForm());
				checkstandardnew.setMoistureContent(appplicationReportNew.getMoistureContent());
				priceNewDao.insertCheckStandardNew(checkstandardnew.toMap());
				
				// 商品材料(正常调价)
				MerchandiseMaterial materialArr[] = appplicationReportNew.getMaterialArr();
				if (materialArr.length > 0) {
					for (MerchandiseMaterial materialOld : materialArr) {
						materialOld.setReportCode(reportCode);
						priceNewDao.insertMerchandiseMaterial(materialOld.toMap());
					}
				}
				
				// 同类商品市场零售价(新品引进)
				SameMerchandiseNew sameArr[] = appplicationReportNew.getSameArr();
				if (sameArr.length > 0) {
					for (SameMerchandiseNew sameNew : sameArr) {
						sameNew.setReportCode(reportCode);
						priceNewDao.insertSameMerchandiseNew(sameNew.toMap());
					}
				}
				// 同类商品市场零售价(新品引进)
				SellAnticipatedNew sellArr[] = appplicationReportNew.getSellArr();
				if (sellArr.length > 0) {
					for (SellAnticipatedNew sellNew : sellArr) {
						sellNew.setReportCode(reportCode);
						priceNewDao.insertSellAnticipatedNew(sellNew.toMap());
					}
				}

			}
		}
	}

	// 删除
	@Override
	public void deleteApplicationReportNew(String reportCode)  throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportCode", reportCode);
		reportNewDao.deleteApplicationReportNew(map);
	}

	// 修改
	@Override
	public void updateApplicationReportNew(ApplicationReportNew applicationReportNew)  throws Exception{
		reportNewDao.updateApplicationReportNew(applicationReportNew.toMap());
	}

	// 根据申请单号删除申请报告(需要同时删除和OA申请相关联的所有表)
	@Override
	public void completeDeleteReportNewByApplicationCode(Map<String, Object> map)  throws Exception{
		this.deleteCheckStandardNewByApplicationCode(map);//删除报告检验标准
		priceNewDao.deletePriceNewByApplicationCode(map);// 删除商品价格
		priceNewDao.deleteSameNewByApplicationCode(map);// 删除同类商品
		priceNewDao.deleteMerchandiseMaterialByCode(map);// 删除商品物料
		priceNewDao.deleteSellNewByApplicationCode(map);// 删除销售预计
		reportNewDao.deleteReportNewByApplicationCode(map);// 删除OA申请报告
		merchandiseOaApplicationDao.deleteApplicationMerhandiseByCode(map);// 删除OA申请关联表
		merchandiseOaApplicationDao.deleteOaApplicationByCode(map);// 删除OA申请表
		supplierAttachmentMDao.deleteSupplierAttachmentByCode(map);// 删除供应商附件
		
		priceNewDao.deleteAnalysisReportsMByApplicationCode(map);//删除关联分析报表(商品OA)
		
		//CR,删除巡厂、包装设计和进度信息
		merchandiseOaApplicationDao.deleteApplicationVisitFactory(map);
		merchandiseOaApplicationDao.deleteApplicationVisitFactoryM(map);
		merchandiseOaApplicationDao.deleteApplicationPackageDesign(map);
		merchandiseOaApplicationDao.deleteApplicationPackageDesignM(map);
		merchandiseOaApplicationDao.deleteApplicationScheduleM2(map);
	}

	//根据申请编号删除检验标准
	@Override
	public void deleteCheckStandardNewByApplicationCode(Map<String, Object> map) {
		this.reportNewDao.deleteCheckStandardNewByApplicationCode(map);
	}
	
	@Override
	public void exportSignedQtyExcel(Map<String, Object> map,
			ServletOutputStream out) {
		try {
		List<MerchandiseIntention> list = this.merchandiseOaApplicationDao.queryIntentionApplicationList(map, null);
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("商品新品引进OA申请列表");
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
		leftSencondRowStyle.setAlignment(CellStyle.ALIGN_CENTER);
		leftSencondRowStyle.setWrapText(true);
		CellStyle rightSencondRowStyle = ExcelUtils.getHeaderCellStyle(wb, null);
		rightSencondRowStyle.setFont(font);
		rightSencondRowStyle.setAlignment(CellStyle.ALIGN_CENTER);
		rightSencondRowStyle.setWrapText(true);
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
				cell.setCellValue("意向品编号");

				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("意向品名称");

				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("SAP物料号 ");
				
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
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("标准总天数");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("实际总天数");
				
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("新品引进申请单状态");
				
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
				cell.setCellValue("新品引进申请审批环节");

				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("巡厂申请审批状态");

				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("巡厂申请审批环节");
				
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
				cell.setCellValue("包装设计申请审批状态");

				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
				cell.setCellValue("包装设计申请审批环节");
				
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
				cell.setCellValue("OA新品引进申请单创建人");
				
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("样品收集日期");

				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("试吃通过日期");

				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
				cell.setCellValue("标准天数\r试吃通过日-样品收集日");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
				cell.setCellValue("实际天数 \r试吃通过日-样品收集日");

				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("巡厂申请提交日期");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
				cell.setCellValue("标准天数\r 巡厂申请日-试吃通过");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
				cell.setCellValue("实际天数\r 巡厂申请日-试吃通过");
				
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
				cell.setCellValue("巡厂申请审批通过日期");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
				cell.setCellValue("标准天数\r巡厂申请报批完成日-巡厂申请提交日");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
				cell.setCellValue("实际天数\r巡厂申请报批完成日-巡厂申请提交日");
				
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("品控实际巡厂日期");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
				cell.setCellValue("标准天数\r实际巡厂日-巡厂申请");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
				cell.setCellValue("实际天数\r实际巡厂日-巡厂申请");
				
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
				cell.setCellValue("新品引进申请提交日期");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
				cell.setCellValue("标准天数 \r新品申请提交-试吃通过");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
				cell.setCellValue("实际天数\r新品申请提交-试吃通过");
				
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
				cell.setCellValue("新品引进审批通过日期");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
				cell.setCellValue("标准天数\r引进报告通过-引进报告申请");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
				cell.setCellValue("实际天数\r引进报告通过-引进报告申请");
				
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (85 * 80));// 设置当前行单元格宽度
				cell.setCellValue("物料主数据申请审批通过日期");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (85 * 80));// 设置当前行单元格宽度
				cell.setCellValue("标准天数 \r主数据申请通过-引进报告通过");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (85 * 80));// 设置当前行单元格宽度
				cell.setCellValue("实际天数 \r主数据申请通过-引进报告通过");
				
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
				cell.setCellValue("包装设计申请提交日期");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
				cell.setCellValue("标准天数\r 包装设计申请-试吃通过");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
				cell.setCellValue("实际天数\r 包装设计申请-试吃通过");
				
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
				cell.setCellValue("包装设计审批通过日期");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
				cell.setCellValue("标准天数 \r包装设计申请-包装设计通过");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
				cell.setCellValue("实际天数 \r包装设计申请-包装设计通过");
				
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
				cell.setCellValue("包装设计初稿确认日期");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
				cell.setCellValue("标准天数\r设计初稿确认-包装设计通过");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
				cell.setCellValue("实际天数\r设计初稿确认-包装设计通过");
				
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
				cell.setCellValue("包装设计完稿通过日期");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (85 * 80));// 设置当前行单元格宽度
				cell.setCellValue("标准天数\r设计完稿确认-主数据申请通过");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (85 * 80));// 设置当前行单元格宽度
				cell.setCellValue("实际天数\r设计完稿确认-主数据申请通过");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
				cell.setCellValue("标准天数\r设计完稿确认-设计初稿确认");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
				cell.setCellValue("实际天数\r设计完稿确认-设计初稿确认");
				
				cell = row.createCell(i);
				cell.setCellStyle(leftSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("新品发放日期");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
				cell.setCellValue("标准天数\r新品发放日-设计完稿确认");
				
				cell = row.createCell(i);
				cell.setCellStyle(rightSencondRowStyle);
				sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
				cell.setCellValue("实际天数\r新品发放日-设计完稿确认  ");
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

		// 意向品编号
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getIntentionCode());
		
		// 意向品名称
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getIntentionName());
		
		// SAP物料号
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getMerchandiseCode());
		
		// 供应商编号
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getIntentionSupplierCode());
		
		// 供应商名称
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getIntentionSupplierName());
		
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
		
		// 标准总天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getBzDay()==null?"":merchandiseIntention.getBzDay().toString());
		
		// 实际总天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getSjDay()==null?"":merchandiseIntention.getSjDay().toString());
		
		// 新品引进申请单状态
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getApplicationStatusName());
		
		// 新品引进申请审批环节
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getApplicationLink());
		
		// 巡厂申请审批状态
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getVisitApplicationStatusName());
		
		// 巡厂申请审批环节
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getVisitApplicationLink());
		
		// 包装设计申请审批状态
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getPackageApplicationStatusName());
		
		// 包装设计申请审批环节
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getPackageApplicationLink());
		
		// OA新品引进申请单创建人
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getCreateUserName());
		
		// 样品收集日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(DateUtils.formatDateToStr(merchandiseIntention.getCreated(),"yyyy-MM-dd"));
		
		// 试吃通过日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(DateUtils.formatDateToStr(merchandiseIntention.getForetastePassDate(),"yyyy-MM-dd"));
		
		// 试吃通过标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getSctgBzDay()==null?"":merchandiseIntention.getSctgBzDay().toString());
		
		// 试吃通过实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getSctgSjDay()==null?"":merchandiseIntention.getSctgSjDay().toString());
		
		// 巡厂申请提交日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(DateUtils.formatDateToStr(merchandiseIntention.getXcsqtjDate(),"yyyy-MM-dd"));
		
		// 巡厂申请提交标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getXcsqtjBzDay()==null?"":merchandiseIntention.getXcsqtjBzDay().toString());
		
		// 巡厂申请提交实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getXcsqtjSjDay()==null?"":merchandiseIntention.getXcsqtjSjDay().toString());
		
		// 巡厂申请审批通过日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(DateUtils.formatDateToStr(merchandiseIntention.getXcsqshtgDate(),"yyyy-MM-dd"));
		
		// 巡厂申请审批通过标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue("");
		
		// 巡厂申请审批通过实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getXcsqshtgSjDay()==null?"":merchandiseIntention.getXcsqshtgSjDay().toString());
		
		// 品控实际巡厂日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(DateUtils.formatDateToStr(merchandiseIntention.getPksjxcDate(),"yyyy-MM-dd"));
		
		// 品控实际巡厂标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getPksjxcBzDay()==null?"":merchandiseIntention.getPksjxcBzDay().toString());
		
		// 品控实际巡厂实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getPksjxcSjDay()==null?"":merchandiseIntention.getPksjxcSjDay().toString());
		
		// 新品引进申请提交日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(DateUtils.formatDateToStr(merchandiseIntention.getXpyjbgtjDate(),"yyyy-MM-dd"));
		
		// 新品引进申请提交标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getXpyjbgtjBzDay()==null?"":merchandiseIntention.getXpyjbgtjBzDay().toString());
		
		// 新品引进申请提交实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getXpyjbgtjSjDay()==null?"":merchandiseIntention.getXpyjbgtjSjDay().toString());
		
		// 新品引进审批通过日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(DateUtils.formatDateToStr(merchandiseIntention.getXpyjbgsptgDate(),"yyyy-MM-dd"));
		
		// 新品引进审批通过标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getXpyjbgsptgBzDay()==null?"":merchandiseIntention.getXpyjbgsptgBzDay().toString());
		
		// 新品引进审批通过实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getXpyjbgsptgSjDay()==null?"":merchandiseIntention.getXpyjbgsptgSjDay().toString());
		
		// 物料主数据申请审批通过日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(DateUtils.formatDateToStr(merchandiseIntention.getZsjsqsptgDate(),"yyyy-MM-dd"));
		
		// 物料主数据申请审批通过标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getZsjsqsptgBzDay()==null?"":merchandiseIntention.getZsjsqsptgBzDay().toString());
		
		// 物料主数据申请审批通过实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getZsjsqsptgSjDay()==null?"":merchandiseIntention.getZsjsqsptgSjDay().toString());
		
		// 包装设计申请提交日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(DateUtils.formatDateToStr(merchandiseIntention.getBzsjsqDate(),"yyyy-MM-dd"));
		
		// 包装设计申请提交标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getBzsjsqBzDay()==null?"":merchandiseIntention.getBzsjsqBzDay().toString());
		
		// 包装设计申请提交实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getBzsjsqSjDay()==null?"":merchandiseIntention.getBzsjsqSjDay().toString());
		
		// 包装设计审批通过日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(DateUtils.formatDateToStr(merchandiseIntention.getBzsjsqwcDate(),"yyyy-MM-dd"));
		
		// 包装设计审批通过标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getBzsjsqwcBzDay()==null?"":merchandiseIntention.getBzsjsqwcBzDay().toString());
		
		// 包装设计审批通过实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getBzsjsqwcSjDay()==null?"":merchandiseIntention.getBzsjsqwcSjDay().toString());
		
		// 包装设计初稿确认日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(DateUtils.formatDateToStr(merchandiseIntention.getBzsjcgqrDate(),"yyyy-MM-dd"));
		
		// 包装设计初稿确认标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getBzsjcgqrBzDay()==null?"":merchandiseIntention.getBzsjcgqrBzDay().toString());
		
		// 包装设计初稿确认实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getBzsjcgqrSjDay()==null?"":merchandiseIntention.getBzsjcgqrSjDay().toString());
		
		// 包装设计完稿通过日期
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(DateUtils.formatDateToStr(merchandiseIntention.getBzsjwgtgDate(),"yyyy-MM-dd"));
		
		// 距离物料主数据申请审批通过日期标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getJlbzsjcgqrBzDay()==null?"":merchandiseIntention.getJlbzsjcgqrBzDay().toString());
		
		// 距离物料主数据申请审批通过日期实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getJlbzsjcgqrSjDay()==null?"":merchandiseIntention.getJlbzsjcgqrSjDay().toString());
		
		// 距离包装设计初稿确认日期标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getJlwlzsjsqsptgBzDay()==null?"":merchandiseIntention.getJlwlzsjsqsptgBzDay().toString());
		
		// 距离包装设计初稿确认日期实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getJlwlzsjsqsptgSjDay()==null?"":merchandiseIntention.getJlwlzsjsqsptgSjDay().toString());
		
		// 新品发放日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(DateUtils.formatDateToStr(merchandiseIntention.getXpffDate(),"yyyy-MM-dd"));
		
		// 新品发放标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getXpffBzDay()==null?"":merchandiseIntention.getXpffBzDay().toString());
		
		// 新品发放实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(merchandiseIntention.getXpffSjDay()==null?"":merchandiseIntention.getXpffSjDay().toString());
		
	}
	
	// 获得申请报告(新品引进)DAO实例
	public ApplicationReportNewDao getReportNewDao() {
		return reportNewDao;
	}

	// 设置申请报告(新品引进)DAO实例
	public void setReportNewDao(ApplicationReportNewDao reportNewDao) {
		this.reportNewDao = reportNewDao;
	}

	public MerchandisePriceNewDao getPriceNewDao() {
		return priceNewDao;
	}

	public void setPriceNewDao(MerchandisePriceNewDao priceNewDao) {
		this.priceNewDao = priceNewDao;
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
package com.powere2e.sco.service.impl.merchandiseoaapplication.reportoldupoaapplication;

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
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportnewoaapplication.supplierattachment.SupplierAttachmentMDao;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportoldupoaapplication.ApplicationReportOldupDao;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportoldupoaapplication.reportOldup.HistoryPriceOldupDao;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportoldupoaapplication.ApplicationReportOldupService;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationMerchandise;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.MerchandiseMaterial;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.ApplicationReportOldup;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.AnticipatedSellOld;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.CheckStandardOldup;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.HistoryPriceOldup;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.SameMerchandiseOldup;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.UpDownMarketOldup;
import com.powere2e.sco.service.impl.merchandiseoaapplication.MerchandiseOaApplicationServiceImpl;

/**
 * 申请报告(老品新上)业务类的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月20日
 */
public class ApplicationReportOldupServiceImpl extends ServiceImpl implements ApplicationReportOldupService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1157797894604241963L;
	private ApplicationReportOldupDao reportOldupDao;// 老品新上报告的dao
	private HistoryPriceOldupDao priceOldupDao;// 老品新上价格的dao
	private MerchandiseOaApplicationDao merchandiseOaApplicationDao;// oa申请的dao
	private SupplierAttachmentMDao supplierAttachmentMDao;// 供应商附件的dao
	private Font font;

	public static ApplicationReportOldupService getInstance() {
		return (ApplicationReportOldupService) ConfigFactory.getInstance().getBean("reportOldupService");
	}

	// 查询没有报告的商品
	@Override
	public List<ApplicationLackFileM> queryNotHaveReportMerchandiseOldup(Map<String, Object> map)  throws Exception{
		return reportOldupDao.queryNotHaveReportMerchandiseOldup(map);
	}

	// 查询老品新上的报告信息
	@Override
	public List<ApplicationReportOldup> listApplicationReportOldup(String applicationCode, String intentionAndSupplierCodes)  throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		List<ApplicationMerchandise> list = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(intentionAndSupplierCodes);
		map.put("list", list);
		map.put("applicationCode", applicationCode);

		List<ApplicationReportOldup> oldUpList = reportOldupDao.listApplicationReportOldup(map);
		if (oldUpList.isEmpty()) {
			// 如果申请报告不存在，就查询商品信息
			return reportOldupDao.queryReportOfOldup(map);
		} else {
			// 申请报告存在
			return oldUpList;
		}
		// return reportOldupDao.listApplicationReportOldup(map, null);
	}

	// 查询报告从商品中带入的信息(老品新上)
	@Override
	public List<ApplicationReportOldup> queryReportOfOldup(String intentionAndSupplierCodes)  throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		List<ApplicationMerchandise> list = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(intentionAndSupplierCodes);
		map.put("list", list);

		return reportOldupDao.queryReportOfOldup(map);
	}

	// 添加老品新上报告
	@Override
	public String insertApplicationReportOldup(List<ApplicationReportOldup> applicationList, String applicationCode, String intentionAndSupplierCodes)  throws Exception{
		String receiptInfo = MerchandiseOaApplicationServiceImpl.getInstance().getIntentionOaApplicationReceiptInfo(applicationCode, intentionAndSupplierCodes,
				BusinessConstants.ApplicationType.MERCHANDISE_OLDUP.toString());
		// 只有为OA申请或者OA申请状态为草稿的才可以进行操作
		if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.NONE.toString().equals(receiptInfo)) {
			this.completeInsertOrUpdateReportOldup(applicationList, applicationCode);
		} else if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.SAME_CG.toString().equals(receiptInfo)) {
			// TODO当已经做了OA申请，并且状态为"草稿"时
			// 先查询报告编号，然后根据报告编号删除所以的信息，再新增
			List<ApplicationReportOldup> reportList = listApplicationReportOldup(applicationCode, intentionAndSupplierCodes);
			if (reportList != null && reportList.size() > 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (ApplicationReportOldup applicationReportOldup : reportList) {
					map.put("reportCode", applicationReportOldup.getReportCode());
					reportOldupDao.deleteApplicationReportOldup(map);// 删除新品引进报告

					priceOldupDao.deleteUpDownMarketOldup(map);// 删除老品新上上下市
					priceOldupDao.deleteCheckStandardOldup(map);// 删除老品新上检验标准
					priceOldupDao.deleteHistoryPriceOldup(map);// 删除老品新上历史与本次价格
					priceOldupDao.deleteSameMerchandiseOldup(map);// 删除老品新上同类商品
					priceOldupDao.deleteMerchandiseMaterial(map);// 删除商品原料
					priceOldupDao.deleteAnticipatedSellOld(map);// 删除老品新上商品销售预计
				}
			}
			this.completeInsertOrUpdateReportOldup(applicationList, applicationCode);
		}
		// 判断勾选数据是否进行OA申请的回执
		return receiptInfo;
	}

	// 插入老品新上报告和关联信息
	public void completeInsertOrUpdateReportOldup(List<ApplicationReportOldup> applicationList, String applicationCode)  throws Exception{
		if (applicationList != null && applicationList.size() > 0) {
			Map<String,Object> map=new HashMap<String,Object>();
			// 说明还没有新增新品引进的报告，新增
			for (ApplicationReportOldup appplicationReportOldup : applicationList) {
				//String reportCode = SequenceFactory.getInstance().nextID("APPLICATION_REPORT_OLDUP");
				map.clear();
				map.put("sequenceName", "S_APPLICATION_REPORT_OLDUP");
				String reportCode = merchandiseOaApplicationDao.selectReportNextID(map);
				appplicationReportOldup.setApplicationCode(applicationCode);
				appplicationReportOldup.setReportCode(reportCode);
				// 插入申请报告
				reportOldupDao.insertApplicationReportOldup(appplicationReportOldup.toMap());
				// 插入上下市时间
				UpDownMarketOldup upDownArr[] = appplicationReportOldup.getUpDownArr();
				if (upDownArr.length > 0) {
					for (UpDownMarketOldup upDownOldup : upDownArr) {
						upDownOldup.setReportCode(reportCode);
						priceOldupDao.insertUpDownMarketOldup(upDownOldup.toMap());
					}
				}
				// 插入报告检验标准
				CheckStandardOldup checkStandardOldup = new CheckStandardOldup();
				checkStandardOldup.setReportCode(reportCode);
				checkStandardOldup.setOldStandardColour(appplicationReportOldup.getOldStandardColour());
				checkStandardOldup.setOldStandardFrom(appplicationReportOldup.getOldStandardFrom());
				checkStandardOldup.setOldStandardSmell(appplicationReportOldup.getOldStandardSmell());
				checkStandardOldup.setNewStandardColour(appplicationReportOldup.getNewStandardColour());
				checkStandardOldup.setNewStandardForm(appplicationReportOldup.getNewStandardForm());
				checkStandardOldup.setNewStandardSmell(appplicationReportOldup.getNewStandardSmell());
				checkStandardOldup.setOldMoistureContent(appplicationReportOldup.getOldMoistureContent());
				checkStandardOldup.setNewMoistureContent(appplicationReportOldup.getNewMoistureContent());
				priceOldupDao.insertCheckStandardOldup(checkStandardOldup.toMap());

				// 插入商品历史价格(老品新上)
				HistoryPriceOldup oldPriceArr[] = appplicationReportOldup.getOldPriceArr();
				if (oldPriceArr.length > 0) {
					for (HistoryPriceOldup oldPriceOldup : oldPriceArr) {
						oldPriceOldup.setReportCode(reportCode);
						// TODO 提出为公共的
						oldPriceOldup.setPiceType("old");// 设置类型为历史价格
						if ("".equals(oldPriceOldup.getSellPrice())||oldPriceOldup.getSellPrice() == null || oldPriceOldup.getSellPrice().compareTo(BigDecimal.ZERO) == 0) {
							oldPriceOldup.setProfitRate(null);	
						}else{
						oldPriceOldup.setProfitRate(DecimalFormatUtils.divideBigDecimal(oldPriceOldup.getPurchasePrice(), oldPriceOldup.getSellPrice(), 4));
						}
						priceOldupDao.insertHistoryPriceOldup(oldPriceOldup.toMap());
					}
				}
				// 插入商品本次价格(老品新上)
				HistoryPriceOldup nowPriceArr[] = appplicationReportOldup.getNowPriceArr();
				if (nowPriceArr.length > 0) {
					for (HistoryPriceOldup nowPriceOldup : nowPriceArr) {
						nowPriceOldup.setReportCode(reportCode);
						// TODO 提出为公共的
						nowPriceOldup.setPiceType("now");// 设置类型为本次价格
						if ("".equals(nowPriceOldup.getSellPrice())||nowPriceOldup.getSellPrice() == null || nowPriceOldup.getSellPrice().compareTo(BigDecimal.ZERO) == 0) {
							nowPriceOldup.setProfitRate(null);	
						}else{
						nowPriceOldup.setProfitRate(DecimalFormatUtils.divideBigDecimal(nowPriceOldup.getPurchasePrice(), nowPriceOldup.getSellPrice(), 4));
						}
						priceOldupDao.insertHistoryPriceOldup(nowPriceOldup.toMap());
					}
				}
				// 同类商品市场零售价(老品新上)
				SameMerchandiseOldup sameArr[] = appplicationReportOldup.getSameArr();
				if (sameArr.length > 0) {
					for (SameMerchandiseOldup sameOldup : sameArr) {
						sameOldup.setReportCode(reportCode);
						priceOldupDao.insertSameMerchandiseOldup(sameOldup.toMap());
					}
				}
				
				// 商品材料(正常调价)
				MerchandiseMaterial materialArr[] = appplicationReportOldup.getMaterialArr();
				if (materialArr.length > 0) {
					for (MerchandiseMaterial materialOld : materialArr) {
						materialOld.setReportCode(reportCode);
						priceOldupDao.insertMerchandiseMaterial(materialOld.toMap());
					}
				}
				
				// 同类商品市场零售价(老品新上)
				AnticipatedSellOld sellArr[] = appplicationReportOldup.getSellArr();
				if (sellArr.length > 0) {
					for (AnticipatedSellOld sellOldup : sellArr) {
						sellOldup.setReportCode(reportCode);
						priceOldupDao.insertAnticipatedSellOld(sellOldup.toMap());
					}
				}
			}
		}
	}

	// 删除老品新上报告
	@Override
	public void deleteApplicationReportOldup(String applicationCode)  throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		reportOldupDao.deleteApplicationReportOldup(map);
	}

	// 修改老品新上报告
	@Override
	public void updateApplicationReportOldup(ApplicationReportOldup applicationReportOldup)  throws Exception{
		reportOldupDao.updateApplicationReportOldup(applicationReportOldup.toMap());
	}

	// 根据申请单号删除申请报告(老品新上)
	public void deleteAllReportOldUpByApplicationCode(Map<String, Object> map)  throws Exception{
		priceOldupDao.deleteUpDownMarketOldupByCode(map);// 删除上下市信息
		priceOldupDao.deleteCheckStandardOldupByCode(map);// 删除检验标注
		priceOldupDao.deleteHistoryPriceOldupByCode(map);// 删除历史与本次价格
		priceOldupDao.deleteSameMerchandiseOldupByCode(map);// 删除同类商品
		priceOldupDao.deleteAnticipatedSellOldByCode(map);// 删除销售预计
		priceOldupDao.deleteMerchandiseMaterialByCode(map);// 删除商品物料
		reportOldupDao.deleteReportOldupByApplicationCode(map);// 删除报告

		merchandiseOaApplicationDao.deleteApplicationMerhandiseByCode(map);// 删除OA申请关联表
		merchandiseOaApplicationDao.deleteOaApplicationByCode(map);// 删除OA申请表
		supplierAttachmentMDao.deleteSupplierAttachmentByCode(map);// 删除供应商附件
		
		priceOldupDao.deleteAnalysisReportsMByApplicationCode(map);//删除关联分析报表(商品OA)
	}

	// 加载一个申请报告(老品新上)
	@Override
	public ApplicationReportOldup loadApplicationReportOldup(String applicationCode, String intentionAndSupplierCodes)  throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		List<ApplicationMerchandise> list = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(intentionAndSupplierCodes);
		if (list != null && list.size() > 0) {
			ApplicationMerchandise merchandise = list.get(0);
			map.put("intentionCode", merchandise.getMerchandiseCode());
			map.put("supplierCode", merchandise.getSupplierCode());
		}
		map.put("applicationCode", applicationCode);
		return reportOldupDao.loadApplicationReportOldup(map);
	}
	
	@Override
	public void exportSignedQtyExcel(Map<String, Object> map,
			ServletOutputStream out) {
		try {
		List<MerchandiseIntention> list = this.merchandiseOaApplicationDao.queryMerchandiseApplicationList(map, null);
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("商品老品新上OA申请列表");
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
		cell.setCellValue("老品新上申请单状态");
		
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
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("巡厂申请日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("品控巡厂日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("包装设计申请日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("引进报告提交日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("引进报告完成日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("主数据申请日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("主数据申请完成日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("合同签订日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("八标提供日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("取得光盘日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商收到光盘日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("包装印刷确认日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("大样确认日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品到达大仓日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("上市日期");
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
		
		// 老品新上申请单状态
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
		cell.setCellValue(merchandiseIntention.getOaApplicationDate()==null?"":DateUtils.formatDateToStr(merchandiseIntention.getOaApplicationDate(), "yyyy-MM-dd"));
		
		// 巡厂申请日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getXcsqDate()==null?"":DateUtils.formatDateToStr(merchandiseIntention.getXcsqDate(), "yyyy-MM-dd"));
		
		// 品控巡厂日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getPkxcDate()==null?"":DateUtils.formatDateToStr(merchandiseIntention.getPkxcDate(), "yyyy-MM-dd"));
		
		// 包装设计申请日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getBzsjsqDate()==null?"":DateUtils.formatDateToStr(merchandiseIntention.getBzsjsqDate(), "yyyy-MM-dd"));
		
		// 引进报告提交日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getYjbgtjDate()==null?"":DateUtils.formatDateToStr(merchandiseIntention.getYjbgtjDate(), "yyyy-MM-dd"));
		
		// 引进报告完成日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getYjbgwcDate()==null?"":DateUtils.formatDateToStr(merchandiseIntention.getYjbgwcDate(), "yyyy-MM-dd"));
		
		// 主数据申请日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getZsjsqDate()==null?"":DateUtils.formatDateToStr(merchandiseIntention.getZsjsqDate(), "yyyy-MM-dd"));
		
		// 主数据申请完成日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getZsjsqwcDate()==null?"":DateUtils.formatDateToStr(merchandiseIntention.getZsjsqwcDate(), "yyyy-MM-dd"));
		
		// 合同签订日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getHtqdDate()==null?"":DateUtils.formatDateToStr(merchandiseIntention.getHtqdDate(), "yyyy-MM-dd"));
		
		// 八标提供日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getBbtgDate()==null?"":DateUtils.formatDateToStr(merchandiseIntention.getBbtgDate(), "yyyy-MM-dd"));
		
		// 取得光盘日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getQdgpDate()==null?"":DateUtils.formatDateToStr(merchandiseIntention.getQdgpDate(), "yyyy-MM-dd"));
		
		// 供应商收到光盘日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getGyssdgpDate()==null?"":DateUtils.formatDateToStr(merchandiseIntention.getGyssdgpDate(), "yyyy-MM-dd"));
		
		// 包装印刷确认日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getGzysqrDate()==null?"":DateUtils.formatDateToStr(merchandiseIntention.getGzysqrDate(), "yyyy-MM-dd"));
		
		// 大样确认日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getDyqrDate()==null?"":DateUtils.formatDateToStr(merchandiseIntention.getDyqrDate(), "yyyy-MM-dd"));
		
		// 商品到达大仓日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getSpdddcDate()==null?"":DateUtils.formatDateToStr(merchandiseIntention.getSpdddcDate(), "yyyy-MM-dd"));
		
		// 上市日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getSsDate()==null?"":DateUtils.formatDateToStr(merchandiseIntention.getSsDate(), "yyyy-MM-dd"));
	}

	// 获得申请报告(老品新上)DAO实例
	public ApplicationReportOldupDao getReportOldupDao() {
		return reportOldupDao;
	}

	// 设置申请报告(老品新上)DAO实例
	public void setReportOldupDao(ApplicationReportOldupDao reportOldupDao) {
		this.reportOldupDao = reportOldupDao;
	}

	public HistoryPriceOldupDao getPriceOldupDao() {
		return priceOldupDao;
	}

	public void setPriceOldupDao(HistoryPriceOldupDao priceOldupDao) {
		this.priceOldupDao = priceOldupDao;
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
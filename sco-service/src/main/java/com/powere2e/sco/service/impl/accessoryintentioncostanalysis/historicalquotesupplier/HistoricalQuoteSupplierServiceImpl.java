package com.powere2e.sco.service.impl.accessoryintentioncostanalysis.historicalquotesupplier;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.FreeMarkerUtil;
import com.powere2e.sco.common.utils.LoggerUtil;
import com.powere2e.sco.interfaces.dao.accessoryintentioncostanalysis.historicalquotesupplier.HistoricalQuoteSupplierDao;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryQuotedElectronicService;
import com.powere2e.sco.interfaces.service.accessoryintentioncostanalysis.historicalquotesupplier.HistoricalQuoteSupplierService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.ApplicationQuotedService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.ApplicationReportAccessoryService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.DhInfoService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.OaApplicationService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.SubscribeAccessoryService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.WlInfoService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.committeeApply.supplierattachment.QuotedElectronicOfScanService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.committeeApply.supplierattachment.SupplierAttachmentAService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.nonFoodApply.reportanalysis.NonFoodReportAnalysisService;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryAccessory;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryElse;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryMaterial;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryPacking;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryTechnology;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedElectronic;
import com.powere2e.sco.model.accessoryintentioncostanalysis.historicalquotesupplier.HistoricalQuoteSupplier;
import com.powere2e.sco.model.accessoryintentioncostanalysis.historicalquotesupplier.HistoricalQuoteSupplierForm;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.QuotedDetailForm;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.QuotedForm;
import com.powere2e.sco.model.accessoryoaapplication.ApplicationQuoted;
import com.powere2e.sco.model.accessoryoaapplication.ApplicationReportAccessory;
import com.powere2e.sco.model.accessoryoaapplication.OaApplication;
import com.powere2e.sco.model.accessoryoaapplication.SubscribeAccessory;
import com.powere2e.sco.model.accessoryoaapplication.WlInfo;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.service.impl.accessoryoaapplication.committeeApply.reportanalysis.CommitteeReportAnalysisServiceImpl;
import com.powere2e.sco.service.impl.accessoryoaapplication.committeeApply.supplierattachment.QuotedElectronicOfScanServiceImpl;
import com.powere2e.sco.service.impl.accessoryoaapplication.committeeApply.suppliercertificate.SupplierCertificateAServiceImpl;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

/**
 * 采购委员会OA申请类的实现
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月20日
 */
public class HistoricalQuoteSupplierServiceImpl extends ServiceImpl implements HistoricalQuoteSupplierService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5497243415785782818L;
	private ApplicationReportAccessoryService applicationReportAccessoryService;
	private SubscribeAccessoryService subscribeAccessoryService;
	private OaApplicationService oaApplicationService;
	private ApplicationQuotedService applicationQuotedService;
	private AccessoryQuotedElectronicService accessoryQuotedElectronicService;
	private DhInfoService dhInfoService;
	private WlInfoService wlInfoService;
	private NonFoodReportAnalysisService nonFoodReportAnalysisService;
	private SupplierAttachmentAService supplierAttachmentAService;
	private QuotedElectronicOfScanService quotedElectronicOfScanService;
	private HistoricalQuoteSupplierDao historicalQuoteSupplierDao;

	public static HistoricalQuoteSupplierService getInstance() {
		return (HistoricalQuoteSupplierService) ConfigFactory.getInstance().getBean("historicalQuoteSupplierService");
	}

	public ApplicationReportAccessoryService getApplicationReportAccessoryService() {
		return applicationReportAccessoryService;
	}

	public void setApplicationReportAccessoryService(ApplicationReportAccessoryService applicationReportAccessoryService) {
		this.applicationReportAccessoryService = applicationReportAccessoryService;
	}

	public SubscribeAccessoryService getSubscribeAccessoryService() {
		return subscribeAccessoryService;
	}

	public void setSubscribeAccessoryService(SubscribeAccessoryService subscribeAccessoryService) {
		this.subscribeAccessoryService = subscribeAccessoryService;
	}

	public OaApplicationService getOaApplicationService() {
		return oaApplicationService;
	}

	public void setOaApplicationService(OaApplicationService oaApplicationService) {
		this.oaApplicationService = oaApplicationService;
	}

	public ApplicationQuotedService getApplicationQuotedService() {
		return applicationQuotedService;
	}

	public void setApplicationQuotedService(ApplicationQuotedService applicationQuotedService) {
		this.applicationQuotedService = applicationQuotedService;
	}

	public QuotedElectronicOfScanService getQuotedElectronicOfScanService() {
		return quotedElectronicOfScanService;
	}

	public void setQuotedElectronicOfScanService(QuotedElectronicOfScanService quotedElectronicOfScanService) {
		this.quotedElectronicOfScanService = quotedElectronicOfScanService;
	}

	public DhInfoService getDhInfoService() {
		return dhInfoService;
	}

	public void setDhInfoService(DhInfoService dhInfoService) {
		this.dhInfoService = dhInfoService;
	}

	public WlInfoService getWlInfoService() {
		return wlInfoService;
	}

	public void setWlInfoService(WlInfoService wlInfoService) {
		this.wlInfoService = wlInfoService;
	}

	public NonFoodReportAnalysisService getNonFoodReportAnalysisService() {
		return nonFoodReportAnalysisService;
	}

	public void setNonFoodReportAnalysisService(NonFoodReportAnalysisService nonFoodReportAnalysisService) {
		this.nonFoodReportAnalysisService = nonFoodReportAnalysisService;
	}

	// 获得报价单DAO实例
	public HistoricalQuoteSupplierDao getHistoricalQuoteSupplierDao() {
		return historicalQuoteSupplierDao;
	}

	// 设置报价单DAO实例
	public void setHistoricalQuoteSupplierDao(HistoricalQuoteSupplierDao historicalQuoteSupplierDao) {
		this.historicalQuoteSupplierDao = historicalQuoteSupplierDao;
	}

	public AccessoryQuotedElectronicService getAccessoryQuotedElectronicService() {
		return accessoryQuotedElectronicService;
	}

	public void setAccessoryQuotedElectronicService(AccessoryQuotedElectronicService accessoryQuotedElectronicService) {
		this.accessoryQuotedElectronicService = accessoryQuotedElectronicService;
	}

	public SupplierAttachmentAService getSupplierAttachmentAService() {
		return supplierAttachmentAService;
	}

	public void setSupplierAttachmentAService(SupplierAttachmentAService supplierAttachmentAService) {
		this.supplierAttachmentAService = supplierAttachmentAService;
	}

	@Override
	public String saveSearchDataForm(String fileName, Map<String, Object> paraMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableInfo", paraMap.get("tableInfo"));
		String tarPath;
		try {
			tarPath = FreeMarkerUtil.generateHtml("totalCostAnalogyAnalysis/accessoryIntentiontotalCostAnalogyAnalysis.ftl", "accessoryIntentiontotalCostAnalogyAnalysis/historicalQuoteSupplier".concat("/").concat(fileName), map);
		} catch (Exception e) {
			e.printStackTrace();
			return "转换报表模板时异常";
		}
		File file = new File(tarPath);// 报表文件
		if (file.exists()) {
			try {
				Reports myReport = new Reports(BusinessConstants.myReportType.FSP.toString(), fileName, tarPath.replace(ConfigPath.getUploadFilePath(), ""));
				ReportsServiceImpl.getInstance().insertReports(myReport);
			} catch (Exception e) {
				file.delete();
				LoggerUtil.logger.error("HistoricalQuoteSupplierServiceImpl.HistoricalQuoteSupplierServiceImpl删除文件["+ file.getPath() +"]");
				return "记录报表文件信息时出错";
			}
		} else {
			return "生成Html文件失败";
		}
		return null;
	}

	// 采购委员会竞价单OA申请意向品列表
	@Override
	public List<HistoricalQuoteSupplier> listHistoricalQuoteSupplierIntentionApplication(Map<String, Object> map, PageInfo pageInfo) {
		return historicalQuoteSupplierDao.listHistoricalQuoteSupplierIntentionApplication(map, pageInfo);
	}

	@Override
	public List<HistoricalQuoteSupplierForm> listHistoricalQuoteSupplierForm(Map<String, Object> map, PageInfo pageInfo) {
		return historicalQuoteSupplierDao.listHistoricalQuoteSupplierForm(map, pageInfo);
	}

	// 根据报价单编号获得采购委员会竞价单OA申请意向品
	@Override
	public HistoricalQuoteSupplier loadHistoricalQuoteSupplierIntentionApplication(String quotedCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quotedCode", quotedCode);
		return historicalQuoteSupplierDao.loadHistoricalQuoteSupplierIntentionApplication(map);
	}

	@Override
	public void insertApplicationReportAccessoryAndSubscribeAccessory(ApplicationReportAccessory applicationReportAccessory, List<SubscribeAccessory> subscribeAccessoryList) {
		applicationReportAccessoryService.deleteApplicationReportAccessory(applicationReportAccessory.getApplicationCode());
		subscribeAccessoryService.deleteSubscribeAccessory(applicationReportAccessory.getApplicationCode());
		applicationReportAccessoryService.insertApplicationReportAccessory(applicationReportAccessory);
		for (SubscribeAccessory subscribeAccessory : subscribeAccessoryList) {
			subscribeAccessoryService.insertSubscribeAccessory(subscribeAccessory);
		}

	}

	@Override
	public Boolean committeeInsertUpdateDeleteIsOk(String quotedCodes, String applicationCode, String applicationType) {
		Boolean isOk = true;
		try {
			String qtc[] = quotedCodes.split(",");
			List<String> applicationCodeList = new ArrayList<String>();
			for (String quotedCode : qtc) {
				String appcode = null;
				ApplicationQuoted applicationQuoted = new ApplicationQuoted();
				applicationQuoted = applicationQuotedService.loadApplicationQuoted(quotedCode);
				if (applicationQuoted != null)
					appcode = applicationQuoted.getApplicationCode();
				if (appcode != null)
					applicationCodeList.add(appcode);
			}
			if (applicationCodeList.size() > 0) {
				for (String code : applicationCodeList) {
					if (!code.equalsIgnoreCase(applicationCode))
						isOk = false;
				}
			}
			OaApplication oaApplication = oaApplicationService.loadOaApplication(applicationCode);
			if (oaApplication != null && !BusinessConstants.ApplicationStatus.W.toString().equals(oaApplication.getApplicationStatus()) && !BusinessConstants.ApplicationStatus.CG.toString().equals(oaApplication.getApplicationStatus())) {
				isOk = false;
			}
			if (isOk) {
				try {
					this.insertApplicationQuotedAndOaApplication(quotedCodes, applicationCode, applicationType);
				} catch (Exception e) {
					isOk = false;
				}
			}
		} catch (Exception e) {
			throw new EscmException("操作错误，请重试");
		}
		return isOk;
	}

	@Override
	public void insertApplicationQuotedAndOaApplication(String quotedCodes, String applicationCode, String applicationType) {
		String qtc[] = quotedCodes.split(",");
		List<ApplicationQuoted> applicationQuotedList = new ArrayList<ApplicationQuoted>();
		for (String quotedCode : qtc) {
			ApplicationQuoted applicationQuoted = new ApplicationQuoted();
			AccessoryQuotedElectronic accessoryQuotedElectronic = new AccessoryQuotedElectronic();
			accessoryQuotedElectronic = accessoryQuotedElectronicService.loadAccessoryQuotedElectronic(quotedCode);
			applicationQuoted.setApplicationCode(applicationCode);
			applicationQuoted.setQuotedCode(quotedCode);
			applicationQuoted.setIntentionCode(accessoryQuotedElectronic.getIntentionCode());
			applicationQuoted.setAccessoryCode(accessoryQuotedElectronic.getIntentionCode());
			applicationQuoted.setEnquiryCode(accessoryQuotedElectronic.getEnquiryCode());
			applicationQuoted.setSupplierCode(accessoryQuotedElectronic.getSupplierCode() == null ? accessoryQuotedElectronic.getIntentionSupplierCode() : accessoryQuotedElectronic.getSupplierCode());
			applicationQuotedList.add(applicationQuoted);
		}
		OaApplication oaApplication = new OaApplication();
		oaApplication.setApplicationCode(applicationCode);
		oaApplication.setApplicationType(applicationType);
		oaApplication.setApplicationStatus(BusinessConstants.ApplicationStatus.CG.toString());
		OaApplication oaApplicationNow = oaApplicationService.loadOaApplication(applicationCode);
		if (oaApplicationNow == null) {
			oaApplicationService.insertOaApplication(oaApplication);
			for (ApplicationQuoted applicationQuotedNow : applicationQuotedList) {
				applicationQuotedService.insertApplicationQuoted(applicationQuotedNow);
			}
		}
	}

	@Override
	public void deleteDhInfo(String[] dhinfoId) {
		for (String id : dhinfoId) {
			dhInfoService.deleteDhInfo(id);
		}
	}

	@Override
	public void deleteApplication(String[] applicationCodes) {
		for (String applicationCode : applicationCodes) {
			oaApplicationService.deleteOaApplication(applicationCode);
			applicationQuotedService.deleteApplicationQuotedFromCode(applicationCode);
			dhInfoService.deleteDhInfoFromCode(applicationCode);
			subscribeAccessoryService.deleteSubscribeAccessory(applicationCode);
		}

	}

	@Override
	public void closeApplication(String[] applicationCodes) {
		for (String applicationCode : applicationCodes) {
			OaApplication oaApplication = new OaApplication();
			oaApplication = oaApplicationService.loadOaApplication(applicationCode);
			oaApplication.setApplicationStatus(BusinessConstants.ApplicationStatus.GB.toString());
			oaApplicationService.updateOaApplication(oaApplication);
		}
	}

	@Override
	public void undoOaSynchronous(String[] applicationCodes) {
		for (String applicationCode : applicationCodes) {
			OaApplication oaApplication = new OaApplication();
			oaApplication.setApplicationStatus(BusinessConstants.ApplicationStatus.CG.toString());
			oaApplication.setApplicationCode(applicationCode);
			oaApplicationService.updateOaApplicationForUndo(oaApplication);
		}

	}

	@Override
	public String checkApplication(String applicationCode) {
		String check = "";
		if (applicationReportAccessoryService.loadApplicationReportAccessory(applicationCode) == null)
			check += "缺少申请报告.";
		if (!CommitteeReportAnalysisServiceImpl.getInstance().ifApplicationExistsMCA(applicationCode))
			check += "缺少成本分析表.";
		if (!CommitteeReportAnalysisServiceImpl.getInstance().ifApplicationExistsPurOrder(applicationCode))
			check += "缺少申购单.";
		if (!QuotedElectronicOfScanServiceImpl.getInstance().searchCount(applicationCode))
			check += "缺少供应商报价单扫描文件.";
		if (!SupplierCertificateAServiceImpl.getInstance().searchCount(applicationCode))
			check += "缺少《企业法人营业执照》或《税务登记证》或《企业组织机构代码》.";
		/*
		 * Map<String, Object> map = new HashMap<String, Object>(); map.put("applicationCode", applicationCode); List<Reports> list=nonFoodReportAnalysisService.listAnalysisReportUpload(map, null); if(list!=null &&list.size()>0 ) check+="缺少成本分析表."; String quotedCodes="" List<ApplicationQuoted> applicationQuotedList= applicationQuotedService.listApplicationQuoted(map, null); for(ApplicationQuoted applicationQuoted:applicationQuotedList){ quotedCodes=quotedCodes+applicationQuoted.getQuotedCode()+","; } Map<String, Object> map1 = new HashMap<String, Object>(); map.put("quotedCode", quotedCodes.split(",")); List<SupplierAttachmentA> listSupplierAttachmentA = supplierAttachmentAService.listQuotedRecord(map, null);
		 */
		return check;
	}

	@Override
	public void allowOaSynchronous(String applicationCode) {
		OaApplication oaApplication = new OaApplication();
		oaApplication.setApplicationStatus(BusinessConstants.ApplicationStatus.YX.toString());
		oaApplication.setApplicationCode(applicationCode);
		oaApplicationService.updateOaApplicationForAllow(oaApplication);

	}

	@Override
	public void insertWlInfo(WlInfo[] dataArray) {
		for (WlInfo wlInfo : dataArray) {
			wlInfoService.insertWlInfo(wlInfo);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("accessorySapCode", wlInfo.getAccessorySapCode());
			map.put("supplierSapCode", wlInfo.getSupplierSapCode());
			map.put("intentionCode", wlInfo.getIntentionCode());
			historicalQuoteSupplierDao.updateIntentionSupplierAccessory(map);
		}
	}

	@Override
	public List<QuotedForm> listQuotedForm(Map<String, Object> map, PageInfo pageInfo) {
		return historicalQuoteSupplierDao.listQuotedForm(map, pageInfo);
	}

	@Override
	public List<QuotedDetailForm> listQuotedDetailForm(Map<String, Object> map, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return historicalQuoteSupplierDao.listQuotedDetailForm(map, pageInfo);
	}

	@Override
	public void exportExcel(List<HistoricalQuoteSupplierForm> historicalQuoteSupplierFormListTotal, ServletOutputStream out) {
		Workbook wb = new XSSFWorkbook();
		XSSFFont blueFont = (XSSFFont) wb.createFont();
		XSSFColor blueColor = new XSSFColor(Color.BLUE);
		blueFont.setColor(blueColor);// 蓝色
		blueFont.setBold(true);
		XSSFFont fontYH = (XSSFFont) wb.createFont();//创建字体对象  
		fontYH.setFontName("微软雅黑"); 
		Font font  = wb.createFont();      
		font.setFontHeightInPoints((short) 11);//字号      
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);//加粗    
		font.setFontName("微软雅黑"); 
		//SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("供应商历史报价分析结果");
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		// CellStyle dateStyle = ExcelUtils.getDefaultDateStyle(wb);// 字符串样式
		// CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式
		CellStyle strStyleCenter = ExcelUtils.getDefaultStringStyle(wb);// 字符串居中靠左样式
		ExcelUtils.setAlign(strStyleCenter, "left", "top", false);
		CellStyle strStyleCenterCenter = ExcelUtils.getDefaultStringStyle(wb);// 合并居中样式
		ExcelUtils.setAlign(strStyleCenterCenter, "center", "center", false);
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb, 11);
		CellStyle firstRowStyleLeft = ExcelUtils.getHeaderCellStyle(wb, 11);
		ExcelUtils.setAlign(firstRowStyleLeft, "left", "center", false);
		firstRowStyle.setWrapText(true);
		strStyle.setWrapText(true);
		strStyleCenter.setWrapText(true);
		strStyle.setFont(fontYH);
		strStyleCenter.setFont(fontYH);
		firstRowStyleLeft.setFont(font);
		firstRowStyle.setFont(font);
		for (int i = 0; i < 29; i++) {
			sheet.setColumnWidth( i, 60 *100);
		}
		Row row = sheet.createRow(0);
		row.setHeight((short) (50 * 20));
		Cell cell = row.createCell(0);
		cell.setCellValue("供应商/意向供应商编号");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(1);
		cell.setCellValue("供应商/意向供应商名称");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(2);
		cell.setCellValue("意向品编号");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(3);
		cell.setCellValue("意向品名称");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(4);
		cell.setCellValue("供应商最后一次报价规格");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(5);
		cell.setCellValue("实际引进规格");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(6);
		cell.setCellValue("供应商最后一次报价数量");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(7);
		cell.setCellValue("供应商最后一次报价单价");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(8);
		cell.setCellValue("实际采购数量");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(9);
		cell.setCellValue("报价供应商数");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(10);
		cell.setCellValue("供应商报价排行");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(11);
		cell.setCellValue("合作价格");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(12);
		cell.setCellValue("合作价格日期");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(13);
		cell.setCellValue("实际采购金额");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(14);
		cell.setCellValue("收到申购单日期");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(15);
		cell.setCellValue("设计完稿日期");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(16);
		cell.setCellValue("要求到货日期");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(17);
		cell.setCellValue("竞价单提交日期");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(18);
		cell.setCellValue("竞价单完成日期");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(19);
		cell.setCellValue("竞价单审批完成天数");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(20);
		cell.setCellValue("下达采购订单日期");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(21);
		cell.setCellValue("实际到货日期");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(22);
		cell.setCellValue("样品打样周期");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(23);
		cell.setCellValue("正常作业流程时间(天)");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(24);
		cell.setCellValue("实际作业时间(天)");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(25);
		cell.setCellValue("差异天数");
		cell.setCellStyle(firstRowStyle);
		cell = row.createCell(26);
		cell.setCellValue("产前样评价");
		cell.setCellStyle(firstRowStyle);
		
		for (int i = 0; i < historicalQuoteSupplierFormListTotal.size(); i++) {
			row = sheet.createRow(i+1);
			cell = row.createCell(0);
			cell.setCellValue(formatNull(historicalQuoteSupplierFormListTotal.get(i).getIntentionSupplierCode()));
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(1);
			cell.setCellValue(formatNull(historicalQuoteSupplierFormListTotal.get(i).getIntentionSupplierName()));
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(2);
			cell.setCellValue(formatNull(historicalQuoteSupplierFormListTotal.get(i).getIntentionCode()));
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(3);
			cell.setCellValue(formatNull(historicalQuoteSupplierFormListTotal.get(i).getIntentionName()));
			cell.setCellStyle(strStyleCenter);
			
			cell = row.createCell(4);
			int[] length=new int[4];
			String gg = "";
				gg += "原材料名称|" + "材料|" + "尺寸|" + "\r\n";
				List<AccessoryEnquiryMaterial> accessoryEnquiryMaterialList = historicalQuoteSupplierFormListTotal.get(i).getAccessoryEnquiryMaterialList();
				if (accessoryEnquiryMaterialList != null) {
					for (AccessoryEnquiryMaterial accessoryEnquiryMaterial : accessoryEnquiryMaterialList) {
						gg += accessoryEnquiryMaterial.getMaterialName() + "|" + accessoryEnquiryMaterial.getMaterial() + "|" + accessoryEnquiryMaterial.getMaterialSize() + "\r\n";
					}
				}
				length[0]=gg.length();
				gg += "辅料名称|" + "材料|" + "尺寸|" + "\r\n";
				List<AccessoryEnquiryAccessory> accessoryEnquiryAccessoryList =  historicalQuoteSupplierFormListTotal.get(i).getAccessoryEnquiryAccessoryList();
				if (accessoryEnquiryAccessoryList != null) {
					for (AccessoryEnquiryAccessory accessoryEnquiryAccessory : accessoryEnquiryAccessoryList) {
						gg += accessoryEnquiryAccessory.getAccessoryName() + "|" + accessoryEnquiryAccessory.getMaterial() + "|" + accessoryEnquiryAccessory.getMaterialSize() + "\r\n";
					}
				}
				length[1]=gg.length();
				gg += "内外包装材料名称|" + "材料|" + "尺寸|" + "\r\n";
				List<AccessoryEnquiryPacking> accessoryEnquiryPackingList =  historicalQuoteSupplierFormListTotal.get(i).getAccessoryEnquiryPackingList();
				if (accessoryEnquiryPackingList != null) {
					for (AccessoryEnquiryPacking accessoryEnquiryPacking : accessoryEnquiryPackingList) {
						gg += accessoryEnquiryPacking.getPackingName() + "|" + accessoryEnquiryPacking.getPackingMaterial() + "|" + accessoryEnquiryPacking.getMaterialSize() + "\r\n";
					}
				}
				length[2]=gg.length();
				gg += "工艺名称|" + "工艺要求|" + "\r\n";
				List<AccessoryEnquiryTechnology> accessoryEnquiryTechnologyList =  historicalQuoteSupplierFormListTotal.get(i)
						.getAccessoryEnquiryTechnologyList();
				if (accessoryEnquiryTechnologyList != null) {
					for (AccessoryEnquiryTechnology accessoryEnquiryTechnology : accessoryEnquiryTechnologyList) {
						gg += accessoryEnquiryTechnology.getTechnologyName() + "|" + accessoryEnquiryTechnology.getTechnologyInfo() + "\r\n";
					}
				}
				length[3]=gg.length();
				gg += "要求名称|" + "要求内容|" + "\r\n";
				List<AccessoryEnquiryElse> accessoryEnquiryElseList =  historicalQuoteSupplierFormListTotal.get(i).getAccessoryEnquiryElseList();
				if (accessoryEnquiryElseList != null) {
					for (AccessoryEnquiryElse accessoryEnquiryElse : accessoryEnquiryElseList) {
						gg += accessoryEnquiryElse.getName() + "|" + accessoryEnquiryElse.getInfo() + "\r\n";
					}
				}
			XSSFRichTextString richString = new XSSFRichTextString(gg);
			richString.applyFont(0, 12, blueFont);
			richString.applyFont(length[0], length[0]+11, blueFont);
			richString.applyFont(length[1], length[1]+15, blueFont);
			richString.applyFont(length[2], length[2]+10, blueFont);
			richString.applyFont(length[3], length[3]+10, blueFont);
			cell.setCellValue(richString);
			//cell.setCellValue(gg);
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(5);
			cell.setCellValue(formatNull(historicalQuoteSupplierFormListTotal.get(i).getActualSpecifications()));
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(6);
			cell.setCellValue(formatNull(historicalQuoteSupplierFormListTotal.get(i).getLastQuoteCount()));
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(7);
			cell.setCellValue(formatNumber(historicalQuoteSupplierFormListTotal.get(i).getLastQuoteTotal()));
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(8);
			cell.setCellValue(formatNull(historicalQuoteSupplierFormListTotal.get(i).getPurchaseCount()));
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(9);
			cell.setCellValue(formatNumber(historicalQuoteSupplierFormListTotal.get(i).getSupplierCount()).replace(".000", ""));
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(10);
			cell.setCellValue(formatNumber(historicalQuoteSupplierFormListTotal.get(i).getSupplierRanking()).replace(".000", ""));
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(11);
			cell.setCellValue(formatNumber(historicalQuoteSupplierFormListTotal.get(i).getCooperationPrice()));
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(12);
			cell.setCellValue(formatDate(historicalQuoteSupplierFormListTotal.get(i).getCooperationPriceDate()));
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(13);
			cell.setCellValue(formatNumber(historicalQuoteSupplierFormListTotal.get(i).getPurchaseMoney()));
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(14);
			cell.setCellValue(formatDate(historicalQuoteSupplierFormListTotal.get(i).getReceivedOADate()));
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(15);
			cell.setCellValue(formatDate(historicalQuoteSupplierFormListTotal.get(i).getSjwgDate()));
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(16);
			cell.setCellValue(formatDate(historicalQuoteSupplierFormListTotal.get(i).getRequiredDeliveryDate()));
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(17);
			cell.setCellValue(formatDate(historicalQuoteSupplierFormListTotal.get(i).getOaSubmitDate()));
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(18);
			cell.setCellValue(formatDate(historicalQuoteSupplierFormListTotal.get(i).getOaCompleteDate()));
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(19);
			cell.setCellValue(formatNumber(historicalQuoteSupplierFormListTotal.get(i).getOaCompleteDays()).replace(".000", ""));
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(20);
			cell.setCellValue(formatDate(historicalQuoteSupplierFormListTotal.get(i).getPoDate()));
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(21);
			cell.setCellValue(formatDate(historicalQuoteSupplierFormListTotal.get(i).getActualDeliveryDate()));
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(22);
			cell.setCellValue(formatNumber(historicalQuoteSupplierFormListTotal.get(i).getProofingCycle()).replace(".000", ""));
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(23);
			cell.setCellValue(formatNumber(historicalQuoteSupplierFormListTotal.get(i).getNormalProcessDays()).replace(".000", ""));
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(24);
			cell.setCellValue(formatNumber(historicalQuoteSupplierFormListTotal.get(i).getActualProcessDays()).replace(".000", ""));
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(25);
			cell.setCellValue(formatNumber(historicalQuoteSupplierFormListTotal.get(i).getDifferencesDays()).replace(".000", ""));
			cell.setCellStyle(strStyleCenter);
			cell = row.createCell(26);
			cell.setCellValue(formatNull(historicalQuoteSupplierFormListTotal.get(i).getProofingEvaluate()));
			cell.setCellStyle(strStyleCenter);
		}
			try {
				wb.write(out);
			} catch (IOException e) {
				throw new EscmException("public.upload.io.error", new String[] { e.getMessage() });
			} finally {
				try {
					if (out != null){
						out.flush();
						out.close();
					}
				} catch (IOException e) {
					throw new EscmException("public.upload.io.error", new String[] { e.getMessage() });
				}
			}
		
	}
	/**
	 *格式化数字
	 * 
	 * 
	 */
	private String  formatNumber(BigDecimal num)  {
		DecimalFormat df =new DecimalFormat("#,##0.000;(#)");
		df.setRoundingMode(RoundingMode.HALF_UP); 
		if(num!=null){
			return df.format(num);
		}
		return "";
	}
	
	/**
	 * 格式化日期
	 * 
	 * 
	 */
	private String  formatDate(Date date)  {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(date!=null){
			return format.format(date);
		}
		return "";
	}
	/**
	 * 格式化NULL
	 * 
	 * 
	 */
	private String  formatNull(String str)  {
		if(str!=null){
			return str;
		}
		return "";
	}
}
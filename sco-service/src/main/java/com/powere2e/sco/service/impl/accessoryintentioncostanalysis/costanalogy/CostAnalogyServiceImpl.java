package com.powere2e.sco.service.impl.accessoryintentioncostanalysis.costanalogy;

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

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
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
import com.powere2e.sco.interfaces.dao.accessoryintentioncostanalysis.costanalogy.CostAnalogyDao;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryQuotedElectronicService;
import com.powere2e.sco.interfaces.service.accessoryintentioncostanalysis.costanalogy.CostAnalogyService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.ApplicationQuotedService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.ApplicationReportAccessoryService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.DhInfoService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.OaApplicationService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.SubscribeAccessoryService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.WlInfoService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.committeeApply.supplierattachment.QuotedElectronicOfScanService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.committeeApply.supplierattachment.SupplierAttachmentAService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.nonFoodApply.reportanalysis.NonFoodReportAnalysisService;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedElectronic;
import com.powere2e.sco.model.accessoryintentioncostanalysis.costanalogy.CostAnalogy;
import com.powere2e.sco.model.accessoryintentioncostanalysis.costanalogy.CostAnalogyForm;
import com.powere2e.sco.model.accessoryintentioncostanalysis.costanalogy.SupplierForm;
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
public class CostAnalogyServiceImpl extends ServiceImpl implements CostAnalogyService {
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
	private CostAnalogyDao costAnalogyDao;

	public static CostAnalogyService getInstance() {
		return (CostAnalogyService) ConfigFactory.getInstance().getBean("costAnalogyService");
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
	public CostAnalogyDao getCostAnalogyDao() {
		return costAnalogyDao;
	}

	// 设置报价单DAO实例
	public void setCostAnalogyDao(CostAnalogyDao costAnalogyDao) {
		this.costAnalogyDao = costAnalogyDao;
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
			tarPath = FreeMarkerUtil.generateHtml("totalCostAnalogyAnalysis/accessoryIntentiontotalCostAnalogyAnalysis.ftl", "accessoryIntentiontotalCostAnalogyAnalysis/costAnalogy".concat("/").concat(fileName), map);
		} catch (Exception e) {
			return "转换报表模板时异常";
		}
		File file = new File(tarPath);// 报表文件
		if (file.exists()) {
			try {
				Reports myReport = new Reports(BusinessConstants.myReportType.FCA.toString(), fileName, tarPath.replace(ConfigPath.getUploadFilePath(), ""));
				ReportsServiceImpl.getInstance().insertReports(myReport);
			} catch (Exception e) {
				file.delete();
				LoggerUtil.logger.error("CostAnalogyServiceImpl.saveSearchDataForm生成报表文件出错,删除文件["+ file.getPath() +"]");
				return "记录报表文件信息时出错";
			}
		} else {
			return "生成Html文件失败";
		}
		return null;
	}

	// 采购委员会竞价单OA申请意向品列表
	@Override
	public List<CostAnalogy> listCostAnalogyIntentionApplication(Map<String, Object> map, PageInfo pageInfo) {
		return costAnalogyDao.listCostAnalogyIntentionApplication(map, pageInfo);
	}

	// 根据报价单编号获得采购委员会竞价单OA申请意向品
	@Override
	public CostAnalogy loadCostAnalogyIntentionApplication(String quotedCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quotedCode", quotedCode);
		return costAnalogyDao.loadCostAnalogyIntentionApplication(map);
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
			costAnalogyDao.updateIntentionSupplierAccessory(map);
		}
	}

	@Override
	public List<QuotedForm> listQuotedForm(Map<String, Object> map, PageInfo pageInfo) {
		return costAnalogyDao.listQuotedForm(map, pageInfo);
	}

	@Override
	public List<QuotedDetailForm> listQuotedDetailForm(Map<String, Object> map, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return costAnalogyDao.listQuotedDetailForm(map, pageInfo);
	}

	@Override
	public void exportExcel(CostAnalogyForm costAnalogyForm, List<String> remarkTexts,ServletOutputStream out) {
		Workbook wb = new XSSFWorkbook();
		XSSFFont blueFont = (XSSFFont) wb.createFont();
		XSSFColor blueColor = new XSSFColor(Color.BLUE);
		blueFont.setColor(blueColor);// 蓝色
		blueFont.setBold(true);
		//SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("成本类比分析结果");
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		// CellStyle dateStyle = ExcelUtils.getDefaultDateStyle(wb);// 字符串样式
		// CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式
		CellStyle strStyleCenter = ExcelUtils.getDefaultStringStyle(wb);// 字符串居中靠左样式
		CellStyle strStyleCenterFont = ExcelUtils.getDefaultStringStyle(wb);// 字符串居中靠左样式
		XSSFFont fontYH = (XSSFFont) wb.createFont();//创建字体对象  
		Font font  = wb.createFont();      
		font.setFontHeightInPoints((short) 11);//字号      
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);//加粗    
		font.setFontName("微软雅黑"); 
		XSSFFont fontwryh = (XSSFFont) wb.createFont();//创建字体对象  
		fontwryh.setFontName("微软雅黑"); 
		strStyleCenterFont.setFont(font);
		ExcelUtils.setAlign(strStyleCenter, "left", "top", true);
		CellStyle strStyleCenterRight = ExcelUtils.getDefaultStringStyle(wb);// 字符串居中靠左样式
		CellStyle strStyleCenterRightFont = ExcelUtils.getDefaultStringStyle(wb);// 字符串居中靠左样式
		strStyleCenterRightFont.setFont(font);
		ExcelUtils.setAlign(strStyleCenterRight, "right", "top", true);
		ExcelUtils.setAlign(strStyleCenterFont, "left", "center", true);
		ExcelUtils.setAlign(strStyleCenterRightFont, "right", "top", true);
		CellStyle strStyleCenterCenter = ExcelUtils.getDefaultStringStyle(wb);// 合并居中样式
		ExcelUtils.setAlign(strStyleCenterCenter, "center", "center", true);
		strStyleCenterRight.setFont(fontYH);
		CellStyle strStyleLeftFont = ExcelUtils.getDefaultStringStyle(wb);// 字符串居中靠左样式
		strStyleLeftFont.setFont(font);
		strStyleCenter.setFont(fontwryh);
		strStyleCenterRight.setFont(fontwryh);
		ExcelUtils.setAlign(strStyleLeftFont, "left", "top", true);
		Row row = sheet.createRow(0);
		
		// row.setHeight((short) (50 * 20));
		// 4是前面的固定列
		sheet.setColumnWidth(0, 70 * 90);
		sheet.setColumnWidth(1, 70 * 80);
		sheet.setColumnWidth(2, 70 * 80);
		sheet.setColumnWidth(3, 70 * 80);
		for (int i = 0; i < costAnalogyForm.getSupplierFormList().size() + 4; i++) {
			sheet.setColumnWidth(1 + i, 70 * 80);
		}
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb, 11);
		CellStyle firstRowStyleLeft = ExcelUtils.getHeaderCellStyle(wb, 11);
		ExcelUtils.setAlign(firstRowStyleLeft, "left", "center", true);
		ExcelUtils.setAlign(firstRowStyle, "left", "center", true);
		firstRowStyle.setWrapText(true);
		strStyle.setWrapText(true);
		strStyleCenter.setWrapText(true);
		int rows=0;//抬头行数
		for (int i = 0; i < costAnalogyForm.getSupplierFormList().size(); i++) {
			String value = "";
			SupplierForm supplierForm = costAnalogyForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(costAnalogyForm.getIntentionSupplierCodeShow())){
				value += supplierForm.getIntentionName() + "-"+"\r" + supplierForm.getIntentionCode() ;
				Cell cell;
				if(i==0){
				cell = row.createCell(0);
				cell.setCellValue("品名-"+"\r"+"意向品编号");
				cell.setCellStyle(strStyleLeftFont);
				
				}
			cell = row.createCell(1 + i);
			cell.setCellValue(value);
			cell.setCellStyle(strStyleLeftFont);
				if(i==0){
			rows++;
				}
				
			}
			
		}
		for (int i = 0; i < costAnalogyForm.getSupplierFormList().size(); i++) {
			String value = "";
			SupplierForm supplierForm = costAnalogyForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(costAnalogyForm.getIntentionSupplierCodeShow())){
				value += supplierForm.getIntentionSupplierCode() + "-"+"\r" + supplierForm.getIntentionSupplierName() ;
				Cell cell;
				if(i==0){
				row = sheet.createRow(rows);
				cell = row.createCell(0);
				cell.setCellValue("供应商编号-"+"\r"+"供应商名称");
				cell.setCellStyle(strStyleLeftFont);
				
				}
			cell = row.createCell(1 + i);
			cell.setCellValue(value);
			cell.setCellStyle(strStyleLeftFont);
				if(i==0){
			rows++;
				}
				
			}
			
		}
		for (int i = 0; i < costAnalogyForm.getSupplierFormList().size(); i++) {
			String value = "";
			SupplierForm supplierForm = costAnalogyForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(costAnalogyForm.getMerchandiseCodeShow())){
				value +=  supplierForm.getMerchandiseCode() ;
				Cell cell;
				if(i==0){
					row = sheet.createRow(rows);
					cell = row.createCell(0);
					cell.setCellValue("SAP物料号");
					cell.setCellStyle(strStyleCenter);
					
					}
				cell = row.createCell(1 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
				
			}
			}
		for (int i = 0; i < costAnalogyForm.getSupplierFormList().size(); i++) {
			String value = "";
			SupplierForm supplierForm = costAnalogyForm.getSupplierFormList().get(i);
				value +=  formatDate(supplierForm.getQuotedDate()) ;
				Cell cell;
				if(i==0){
					row = sheet.createRow(rows);
					cell = row.createCell(0);
					cell.setCellValue("报价日期");
					cell.setCellStyle(strStyleCenter);
					
					}
				cell = row.createCell(1 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
				
			}
		for (int i = 0; i < costAnalogyForm.getSupplierFormList().size(); i++) {
			String value = "";
			SupplierForm supplierForm = costAnalogyForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(costAnalogyForm.getQuotedCountShow())){
				value +=  formatNumber(supplierForm.getQuotedCount()).replace(".000", "") ;
				Cell cell;
				if(i==0){
					row = sheet.createRow(rows);
					cell = row.createCell(0);
					cell.setCellValue("报价数量");
					cell.setCellStyle(strStyleCenter);
					
					}
				cell = row.createCell(1 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
				
			}
			}
		for (int i = 0; i < costAnalogyForm.getSupplierFormList().size(); i++) {
			String value = "";
			SupplierForm supplierForm = costAnalogyForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(costAnalogyForm.getContactsShow())){
				value +=  supplierForm.getContacts() ;
				Cell cell;
				if(i==0){
					row = sheet.createRow(rows);
					cell = row.createCell(0);
					cell.setCellValue("联系人");
					cell.setCellStyle(strStyleCenter);
					
					}
				cell = row.createCell(1 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
				
			}
			}
		for (int i = 0; i < costAnalogyForm.getSupplierFormList().size(); i++) {
			String value = "";
			SupplierForm supplierForm = costAnalogyForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(costAnalogyForm.getPhoneShow())){
				value +=  supplierForm.getPhone() ;
				Cell cell;
				if(i==0){
					row = sheet.createRow(rows);
					cell = row.createCell(0);
					cell.setCellValue("联系电话");
					cell.setCellStyle(strStyleCenter);
					
					}
				cell = row.createCell(1 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
		for (int i = 0; i < costAnalogyForm.getSupplierFormList().size(); i++) {
			String value = "";
			SupplierForm supplierForm = costAnalogyForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(costAnalogyForm.getCompanySiteShow())){
				value +=  supplierForm.getCompanySite() ;
				Cell cell;
				if(i==0){
					row = sheet.createRow(rows);
					cell = row.createCell(0);
					cell.setCellValue("公司地址");
					cell.setCellStyle(strStyleCenter);
					}
				cell = row.createCell(1 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
			for (int i = 0; i < costAnalogyForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = costAnalogyForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(costAnalogyForm.getFactorySiteShow())){
				value +=  supplierForm.getFactorySite();
				Cell cell;
				if(i==0){
					row = sheet.createRow(rows);
					cell = row.createCell(0);
					cell.setCellValue("工厂地址");
					cell.setCellStyle(strStyleCenter);
					}
				cell = row.createCell(1 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
			for (int i = 0; i < costAnalogyForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = costAnalogyForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(costAnalogyForm.getRegisterCapitalShow())){
				value += supplierForm.getRegisterCapital() ;
				Cell cell;
				if(i==0){
					row = sheet.createRow(rows);
					cell = row.createCell(0);
					cell.setCellValue("注册资本(万元)");
					cell.setCellStyle(strStyleCenter);
					}
				cell = row.createCell(1 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
			for (int i = 0; i < costAnalogyForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = costAnalogyForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(costAnalogyForm.getYearTurnoverShow())){
				value +=  supplierForm.getYearTurnover() ;
				Cell cell;
				if(i==0){
					row = sheet.createRow(rows);
					cell = row.createCell(0);
					cell.setCellValue("年营业额(万元)");
					cell.setCellStyle(strStyleCenter);
					}
				cell = row.createCell(1 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
			for (int i = 0; i < costAnalogyForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = costAnalogyForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(costAnalogyForm.getFactoryAreaShow())){
				value +=  formatNumber(supplierForm.getFactoryArea()).replace(".000", "") ;
				Cell cell;
				if(i==0){
					row = sheet.createRow(rows);
					cell = row.createCell(0);
					cell.setCellValue("工厂面积(㎡)");
					cell.setCellStyle(strStyleCenter);
					}
				cell = row.createCell(1 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
			for (int i = 0; i < costAnalogyForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = costAnalogyForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(costAnalogyForm.getStaffCountShow())){
				value +=  formatNumber(supplierForm.getStaffCount()).replace(".000", "") ;
				Cell cell;
				if(i==0){
					row = sheet.createRow(rows);
					cell = row.createCell(0);
					cell.setCellValue("工人数");
					cell.setCellStyle(strStyleCenter);
					}
				cell = row.createCell(1 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
			for (int i = 0; i < costAnalogyForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = costAnalogyForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(costAnalogyForm.getDailyCapacityShow())){
				value +=  supplierForm.getDailyCapacity() ;
				Cell cell;
				if(i==0){
					row = sheet.createRow(rows);
					cell = row.createCell(0);
					cell.setCellValue("日产能");
					cell.setCellStyle(strStyleCenter);
					}
				cell = row.createCell(1 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
			for (int i = 0; i < costAnalogyForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = costAnalogyForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(costAnalogyForm.getHzgppShow())){
				value +=  supplierForm.getHzgpp() ;
				Cell cell;
				if(i==0){
					row = sheet.createRow(rows);
					cell = row.createCell(0);
					cell.setCellValue("合作过品牌");
					cell.setCellStyle(strStyleCenter);
					}
				cell = row.createCell(1 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
			for (int i = 0; i < costAnalogyForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = costAnalogyForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(costAnalogyForm.getInvoiceTypeShow())){
				value +=  supplierForm.getInvoiceType() ;
				Cell cell;
				if(i==0){
					row = sheet.createRow(rows);
					cell = row.createCell(0);
					cell.setCellValue("发票类型(增/普票)");
					cell.setCellStyle(strStyleCenter);
					}
				cell = row.createCell(1 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
			for (int i = 0; i < costAnalogyForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = costAnalogyForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(costAnalogyForm.getTaxRateShow())){
				value +=  formatNumber(supplierForm.getTaxRate())+"%" ;
				Cell cell;
				if(i==0){
					row = sheet.createRow(rows);
					cell = row.createCell(0);
					cell.setCellValue("税率(%)");
					cell.setCellStyle(strStyleCenter);
					}
				cell = row.createCell(1 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
			for (int i = 0; i < costAnalogyForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = costAnalogyForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(costAnalogyForm.getPaymentTypeShow())){
				value +=supplierForm.getPaymentType() ;
				Cell cell;
				if(i==0){
					row = sheet.createRow(rows);
					cell = row.createCell(0);
					cell.setCellValue("付款方式");
					cell.setCellStyle(strStyleCenter);
					}
				cell = row.createCell(1 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
			for (int i = 0; i < costAnalogyForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = costAnalogyForm.getSupplierFormList().get(i);
				if (StringUtils.isNotBlank(costAnalogyForm.getProofingContentBeforeShow())){
				value += supplierForm.getProofingContentBefore() ;
				Cell cell;
				if(i==0){
					row = sheet.createRow(rows);
					cell = row.createCell(0);
					cell.setCellValue("产前样打样内容");
					cell.setCellStyle(strStyleCenter);
					}
				cell = row.createCell(1 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
				}
			}
			for (int i = 0; i < costAnalogyForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = costAnalogyForm.getSupplierFormList().get(i);
				if (StringUtils.isNotBlank(costAnalogyForm.getProofingContentBeforeShow())){
				value += supplierForm.getProofingEvaluateBefore() ;
				Cell cell;
				if(i==0){
					row = sheet.createRow(rows);
					cell = row.createCell(0);
					cell.setCellValue("产前样打样评价");
					cell.setCellStyle(strStyleCenter);
					}
				cell = row.createCell(1 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
				}
			}
				for (int i = 0; i < costAnalogyForm.getSupplierFormList().size(); i++) {
					String value = "";
					SupplierForm supplierForm = costAnalogyForm.getSupplierFormList().get(i);
				if (StringUtils.isNotBlank(costAnalogyForm.getProofingContentAfterShow())){
				value +=  supplierForm.getProofingContentAfter() ;
				Cell cell;
				if(i==0){
					row = sheet.createRow(rows);
					cell = row.createCell(0);
					cell.setCellValue("非产前样打样内容");
					cell.setCellStyle(strStyleCenter);
					}
				cell = row.createCell(1 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
				}
				}
				for (int i = 0; i < costAnalogyForm.getSupplierFormList().size(); i++) {
					String value = "";
					SupplierForm supplierForm = costAnalogyForm.getSupplierFormList().get(i);
					if (StringUtils.isNotBlank(costAnalogyForm.getProofingContentAfterShow())){
				value += supplierForm.getProofingEvaluateAfter() ;
				Cell cell;
				if(i==0){
					row = sheet.createRow(rows);
					cell = row.createCell(0);
					cell.setCellValue("非产前样打样评价");
					cell.setCellStyle(strStyleCenter);
					}
				cell = row.createCell(1 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
					}
				}
				//ExcelUtils.addMergedRegion(sheet, "A1" + ":" + "A" + (rows));
				
				int line = rows;
				//原材料
				for (int i = 0; i < costAnalogyForm.getAccessoryQuotedMaterialAndSubtotalList().size(); i++) {
					row = sheet.createRow(line);
					line++;
					for(int n=0;n<costAnalogyForm.getAccessoryQuotedMaterialAndSubtotalList().get(i).getAccessoryQuotedMaterialAndSubtotalarray().size();n++){
						if(n==0){
						Cell cell = row.createCell(0);
						cell.setCellValue("原材料："+costAnalogyForm.getAccessoryQuotedMaterialAndSubtotalList().get(i).getAccessoryQuotedMaterialAndSubtotalarray().get(n).getAccessoryQuotedMaterial().getMaterialName() );
						cell.setCellStyle(strStyleCenter);	
						}else{
							Cell cell = row.createCell(n);
							cell.setCellValue("产地："+costAnalogyForm.getAccessoryQuotedMaterialAndSubtotalList().get(i).getAccessoryQuotedMaterialAndSubtotalarray().get(n).getAccessoryQuotedMaterial().getOrigin()+ "\r\n"+"品牌："+costAnalogyForm.getAccessoryQuotedMaterialAndSubtotalList().get(i).getAccessoryQuotedMaterialAndSubtotalarray().get(n).getAccessoryQuotedMaterial().getBrand()+ "\r\n"+"价格:"+(formatNumber(costAnalogyForm.getAccessoryQuotedMaterialAndSubtotalList().get(i).getAccessoryQuotedMaterialAndSubtotalarray().get(n).getAccessoryQuotedMaterial().getPrice())==null?"":formatNumber(costAnalogyForm.getAccessoryQuotedMaterialAndSubtotalList().get(i).getAccessoryQuotedMaterialAndSubtotalarray().get(n).getAccessoryQuotedMaterial().getPrice()))+ "\r\n"+"价格单位:"+costAnalogyForm.getAccessoryQuotedMaterialAndSubtotalList().get(i).getAccessoryQuotedMaterialAndSubtotalarray().get(n).getAccessoryQuotedMaterial().getPriceUnit());
							cell.setCellStyle(strStyleCenter);	
						}
					}
					//原材料价格
					row = sheet.createRow(line);
					line++;
					for(int n=0;n<costAnalogyForm.getAccessoryQuotedMaterialAndSubtotalList().get(i).getAccessoryQuotedMaterialAndSubtotalarray().size();n++){
						if(n==0){
						Cell cell = row.createCell(0);
						cell.setCellValue("原材料价格");
						cell.setCellStyle(strStyleCenter);	
						}else{
							Cell cell = row.createCell(n);
							cell.setCellValue(formatNumber(costAnalogyForm.getAccessoryQuotedMaterialAndSubtotalList().get(i).getAccessoryQuotedMaterialAndSubtotalarray().get(n).getAccessoryQuotedMaterial().getCost()));
							cell.setCellStyle(strStyleCenterRight);	
						}
					}
					
					/*row = sheet.createRow(line);
					line++;
					for(int n=0;n<costAnalogyForm.getAccessoryQuotedMaterialAndSubtotalList().get(i).getAccessoryQuotedMaterialAndSubtotalarray().size();n++){
						if(n==0){
						Cell cell = row.createCell(0);
						cell.setCellValue("原材料损耗:");
						cell.setCellStyle(strStyleCenter);	
						}else{
							Cell cell = row.createCell(n);
							cell.setCellValue(costAnalogyForm.getAccessoryQuotedMaterialAndSubtotalList().get(i).getAccessoryQuotedMaterialAndSubtotalarray().get(n).getAccessoryQuotedMaterial().getWastage());
							cell.setCellStyle(strStyleCenterRight);	
						}
					}
					*/
					row = sheet.createRow(line);
					line++;
					for(int n=0;n<costAnalogyForm.getAccessoryQuotedMaterialAndSubtotalList().get(i).getAccessoryQuotedMaterialAndSubtotalarray().size();n++){
						if(n==0){
						Cell cell = row.createCell(0);
						cell.setCellValue("原材料："+costAnalogyForm.getAccessoryQuotedMaterialAndSubtotalList().get(i).getAccessoryQuotedMaterialAndSubtotalarray().get(n).getAccessoryQuotedMaterial().getMaterialName()+"-占原材料小计占比");
						cell.setCellStyle(strStyleCenter);	
						}else{
							Cell cell = row.createCell(n);
							cell.setCellValue(formatNumber2(new BigDecimal( costAnalogyForm.getAccessoryQuotedMaterialAndSubtotalList().get(i).getAccessoryQuotedMaterialAndSubtotalarray().get(n).getSubtotal()))+"%");
							cell.setCellStyle(strStyleCenterRight);	
						}
					}
					
					
				}
				row = sheet.createRow(line);
				line++;
				Cell cell = row.createCell(0);
				cell.setCellValue("原材料小计");
				cell.setCellStyle(strStyleCenterFont);	
				for(int k=0;k<costAnalogyForm.getTotalMaterialList().size();k++){
					
					cell = row.createCell(1+k);
					cell.setCellValue(costAnalogyForm.getTotalMaterialList().get(k)==null?"":formatNumber(costAnalogyForm.getTotalMaterialList().get(k)));
					cell.setCellStyle(strStyleCenterRightFont);	
					
				}
				//辅料
				for (int i = 0; i < costAnalogyForm.getAccessoryQuotedAccessoryAndSubtotalList().size(); i++) {
					row = sheet.createRow(line);
					line++;
					for(int n=0;n<costAnalogyForm.getAccessoryQuotedAccessoryAndSubtotalList().get(i).getAccessoryQuotedAccessoryAndSubtotalarray().size();n++){
						if(n==0){
						cell = row.createCell(0);
						cell.setCellValue("辅料：辅料名称："+costAnalogyForm.getAccessoryQuotedAccessoryAndSubtotalList().get(i).getAccessoryQuotedAccessoryAndSubtotalarray().get(n).getAccessoryQuotedAccessory().getAccessoryName() );
						cell.setCellStyle(strStyleCenter);	
						}else{
							cell = row.createCell(n);
							cell.setCellValue(formatNumber(costAnalogyForm.getAccessoryQuotedAccessoryAndSubtotalList().get(i).getAccessoryQuotedAccessoryAndSubtotalarray().get(n).getAccessoryQuotedAccessory().getCost()));
							cell.setCellStyle(strStyleCenterRight);	
						}
					}
					
					/*row = sheet.createRow(line);
					line++;
					for(int n=0;n<costAnalogyForm.getAccessoryQuotedAccessoryAndSubtotalList().get(i).getAccessoryQuotedAccessoryAndSubtotalarray().size();n++){
						if(n==0){
						Cell cell = row.createCell(0);
						cell.setCellValue("损耗:");
						cell.setCellStyle(strStyleCenter);	
						}else{
							Cell cell = row.createCell(n);
							cell.setCellValue(formatNumber(costAnalogyForm.getAccessoryQuotedAccessoryAndSubtotalList().get(i).getAccessoryQuotedAccessoryAndSubtotalarray().get(n).getAccessoryQuotedAccessory().getWastage()));
							cell.setCellStyle(strStyleCenter);	
						}
					}*/
					
			/*		row = sheet.createRow(line);
					line++;
					for(int n=0;n<costAnalogyForm.getAccessoryQuotedAccessoryAndSubtotalList().get(i).getAccessoryQuotedAccessoryAndSubtotalarray().size();n++){
						if(n==0){
						Cell cell = row.createCell(0);
						cell.setCellValue("辅料小计占比:");
						cell.setCellStyle(strStyleCenter);	
						}else{
							Cell cell = row.createCell(n);
							cell.setCellValue(formatNumber(costAnalogyForm.getAccessoryQuotedAccessoryAndSubtotalList().get(i).getAccessoryQuotedAccessoryAndSubtotalarray().get(n).getSubtotal())+"%");
							cell.setCellStyle(strStyleCenter);	
						}
					}
					*/
				
				}
				row = sheet.createRow(line);
				line++;
				cell = row.createCell(0);
				cell.setCellValue("辅料小计");
				cell.setCellStyle(strStyleCenterFont);	
				for(int k=0;k<costAnalogyForm.getTotalAccessoryList().size();k++){
					
					cell = row.createCell(1+k);
					cell.setCellValue(costAnalogyForm.getTotalAccessoryList().get(k)==null?"":formatNumber(costAnalogyForm.getTotalAccessoryList().get(k)));
					cell.setCellStyle(strStyleCenterRightFont);	
					
				}
				
				row = sheet.createRow(line);
				line++;
				cell = row.createCell(0);
				cell.setCellValue("原辅料小计");
				cell.setCellStyle(strStyleCenterFont);	
				for(int k=0;k<costAnalogyForm.getTotalMaterialAccessoryList().size();k++){
					
					cell = row.createCell(1+k);
					cell.setCellValue(costAnalogyForm.getTotalMaterialAccessoryList().get(k)==null?"":formatNumber(costAnalogyForm.getTotalMaterialAccessoryList().get(k)));
					cell.setCellStyle(strStyleCenterRightFont);	
					
				}
				//工艺
				for (int i = 0; i < costAnalogyForm.getAccessoryQuotedTechnologyAndSubtotalList().size(); i++) {
					row = sheet.createRow(line);
					line++;
					for(int n=0;n<costAnalogyForm.getAccessoryQuotedTechnologyAndSubtotalList().get(i).getAccessoryQuotedTechnologyAndSubtotalarray().size();n++){
						if(n==0){
							cell = row.createCell(0);
						cell.setCellValue("工艺：工艺名称："+costAnalogyForm.getAccessoryQuotedTechnologyAndSubtotalList().get(i).getAccessoryQuotedTechnologyAndSubtotalarray().get(n).getAccessoryQuotedTechnology().getTechnologyName() );
						cell.setCellStyle(strStyleCenter);	
						}else{
							cell = row.createCell(n);
							cell.setCellValue(formatNumber(costAnalogyForm.getAccessoryQuotedTechnologyAndSubtotalList().get(i).getAccessoryQuotedTechnologyAndSubtotalarray().get(n).getAccessoryQuotedTechnology().getCost()));
							cell.setCellStyle(strStyleCenterRight);	
						}
					}
				
				}
				//内外包装
				for (int i = 0; i < costAnalogyForm.getAccessoryQuotedPackingAndSubtotalList().size(); i++) {
					row = sheet.createRow(line);
					line++;
					for(int n=0;n<costAnalogyForm.getAccessoryQuotedPackingAndSubtotalList().get(i).getAccessoryQuotedPackingAndSubtotalarray().size();n++){
						if(n==0){
							cell = row.createCell(0);
						cell.setCellValue("内外包装材料：内外包装材料名称："+costAnalogyForm.getAccessoryQuotedPackingAndSubtotalList().get(i).getAccessoryQuotedPackingAndSubtotalarray().get(n).getAccessoryQuotedPacking().getPackingName() );
						cell.setCellStyle(strStyleCenter);	
						}else{
							cell = row.createCell(n);
							cell.setCellValue(formatNumber(costAnalogyForm.getAccessoryQuotedPackingAndSubtotalList().get(i).getAccessoryQuotedPackingAndSubtotalarray().get(n).getAccessoryQuotedPacking().getCost()));
							cell.setCellStyle(strStyleCenterRight);	
						}
					}
				/*	row = sheet.createRow(line);
					line++;
					for(int n=0;n<costAnalogyForm.getAccessoryQuotedPackingAndSubtotalList().get(i).getAccessoryQuotedPackingAndSubtotalarray().size();n++){
						if(n==0){
						cell = row.createCell(0);
						cell.setCellValue("损耗:");
						cell.setCellStyle(strStyleCenter);	
						}else{
							cell = row.createCell(n);
							cell.setCellValue(formatNumber(costAnalogyForm.getAccessoryQuotedPackingAndSubtotalList().get(i).getAccessoryQuotedPackingAndSubtotalarray().get(n).getAccessoryQuotedPacking().getWastage()));
							cell.setCellStyle(strStyleCenter);	
						}
					}*/
				
				}
				row = sheet.createRow(line);
				line++;
				cell = row.createCell(0);
				cell.setCellValue( "其他成本：打样费");
				cell.setCellStyle(strStyleCenter);	
				for(int i=0;i<costAnalogyForm.getAccessoryQuotedElseList().size();i++){
					String value="";
					cell = row.createCell(i+1);
					for(int n=0;n<costAnalogyForm.getAccessoryQuotedElseList().get(i).getAccessoryQuotedElsearray().size();n++){
						if("打样费".equals(costAnalogyForm.getAccessoryQuotedElseList().get(i).getAccessoryQuotedElsearray().get(n).getCostType())){
							value+=(formatNumber(costAnalogyForm.getAccessoryQuotedElseList().get(i).getAccessoryQuotedElsearray().get(n).getCost()));
							}
					}
					
					cell.setCellValue(value);
					cell.setCellStyle(strStyleCenterRight);
				}
				row = sheet.createRow(line);
				line++;
				cell = row.createCell(0);
				cell.setCellValue( "其他成本：人工");
				cell.setCellStyle(strStyleCenter);	
				for(int i=0;i<costAnalogyForm.getAccessoryQuotedElseList().size();i++){
					String value="";
					cell = row.createCell(i+1);
					
					for(int n=0;n<costAnalogyForm.getAccessoryQuotedElseList().get(i).getAccessoryQuotedElsearray().size();n++){
						if("人工".equals(costAnalogyForm.getAccessoryQuotedElseList().get(i).getAccessoryQuotedElsearray().get(n).getCostType())){
							value+=(formatNumber(costAnalogyForm.getAccessoryQuotedElseList().get(i).getAccessoryQuotedElsearray().get(n).getCost()));
							}
					}
					
					cell.setCellValue(value);
					cell.setCellStyle(strStyleCenterRight);
				}
				row = sheet.createRow(line);
				line++;
				cell = row.createCell(0);
				cell.setCellValue( "其他成本：运输");
				cell.setCellStyle(strStyleCenter);	
				for(int i=0;i<costAnalogyForm.getAccessoryQuotedElseList().size();i++){
					String value="";
					cell = row.createCell(i+1);
					for(int n=0;n<costAnalogyForm.getAccessoryQuotedElseList().get(i).getAccessoryQuotedElsearray().size();n++){
						if("运输".equals(costAnalogyForm.getAccessoryQuotedElseList().get(i).getAccessoryQuotedElsearray().get(n).getCostType())){
							value+=(formatNumber(costAnalogyForm.getAccessoryQuotedElseList().get(i).getAccessoryQuotedElsearray().get(n).getCost()));
							}
					}
					cell.setCellValue(value);
					cell.setCellStyle(strStyleCenterRight);
				}
				
				row = sheet.createRow(line);
				line++;
				cell = row.createCell(0);
				cell.setCellValue( "其他成本：管理");
				cell.setCellStyle(strStyleCenter);	
				for(int i=0;i<costAnalogyForm.getAccessoryQuotedElseList().size();i++){
					String value="";
					cell = row.createCell(i+1);
					for(int n=0;n<costAnalogyForm.getAccessoryQuotedElseList().get(i).getAccessoryQuotedElsearray().size();n++){
						if("管理".equals(costAnalogyForm.getAccessoryQuotedElseList().get(i).getAccessoryQuotedElsearray().get(n).getCostType())){
							value+=(formatNumber(costAnalogyForm.getAccessoryQuotedElseList().get(i).getAccessoryQuotedElsearray().get(n).getCost()));
							}
					}
					cell.setCellValue(value);
					cell.setCellStyle(strStyleCenterRight);
				}
				
				row = sheet.createRow(line);
				line++;
				cell = row.createCell(0);
				cell.setCellValue( "其他成本：税额");
				cell.setCellStyle(strStyleCenter);	
				for(int i=0;i<costAnalogyForm.getAccessoryQuotedElseList().size();i++){
					String value="";
					cell = row.createCell(i+1);
					for(int n=0;n<costAnalogyForm.getAccessoryQuotedElseList().get(i).getAccessoryQuotedElsearray().size();n++){
						if("税额".equals(costAnalogyForm.getAccessoryQuotedElseList().get(i).getAccessoryQuotedElsearray().get(n).getCostType())){
							value+=(formatNumber(costAnalogyForm.getAccessoryQuotedElseList().get(i).getAccessoryQuotedElsearray().get(n).getCost()));
							}
					}
					
					cell.setCellValue(value);
					cell.setCellStyle(strStyleCenterRight);
				}
				
				row = sheet.createRow(line);
				line++;
				cell = row.createCell(0);
				cell.setCellValue( "其他成本：税额占比");
				cell.setCellStyle(strStyleCenter);	
				for(int i=0;i<costAnalogyForm.getAccessoryQuotedElseList().size();i++){
					String value="";
					cell = row.createCell(i+1);
					for(int n=0;n<costAnalogyForm.getAccessoryQuotedElseList().get(i).getAccessoryQuotedElsearray().size();n++){
						if("税额占比".equals(costAnalogyForm.getAccessoryQuotedElseList().get(i).getAccessoryQuotedElsearray().get(n).getCostType())){
							value+=(formatNumber2(costAnalogyForm.getAccessoryQuotedElseList().get(i).getAccessoryQuotedElsearray().get(n).getCost())+"%");
							}
					}
					cell.setCellValue(value);
					cell.setCellStyle(strStyleCenterRight);
				}
				
				row = sheet.createRow(line);
				line++;
				cell = row.createCell(0);
				cell.setCellValue( "其他成本：利润");
				cell.setCellStyle(strStyleCenter);	
				for(int i=0;i<costAnalogyForm.getAccessoryQuotedElseList().size();i++){
					String value="";
					cell = row.createCell(i+1);
					for(int n=0;n<costAnalogyForm.getAccessoryQuotedElseList().get(i).getAccessoryQuotedElsearray().size();n++){
						if("利润".equals(costAnalogyForm.getAccessoryQuotedElseList().get(i).getAccessoryQuotedElsearray().get(n).getCostType())){
							value+=(formatNumber(costAnalogyForm.getAccessoryQuotedElseList().get(i).getAccessoryQuotedElsearray().get(n).getCost()));
							}
					}
					
					cell.setCellValue(value);
					cell.setCellStyle(strStyleCenterRight);
				}
				
				row = sheet.createRow(line);
				line++;
				cell = row.createCell(0);
				cell.setCellValue( "其他成本：利润占比");
				cell.setCellStyle(strStyleCenter);	
				for(int i=0;i<costAnalogyForm.getAccessoryQuotedElseList().size();i++){
					String value="";
					cell = row.createCell(i+1);
					for(int n=0;n<costAnalogyForm.getAccessoryQuotedElseList().get(i).getAccessoryQuotedElsearray().size();n++){
						if("利润占比".equals(costAnalogyForm.getAccessoryQuotedElseList().get(i).getAccessoryQuotedElsearray().get(n).getCostType())){
							value+=(formatNumber2(costAnalogyForm.getAccessoryQuotedElseList().get(i).getAccessoryQuotedElsearray().get(n).getCost())+"%");
							}
					}
					cell.setCellValue(value);
					cell.setCellStyle(strStyleCenterRight);
				}
				
				
				row = sheet.createRow(line);
				line++;
				cell = row.createCell(0);
				cell.setCellValue("以上总价：");
				cell.setCellStyle(strStyleCenterFont);
				for(int n=0;n<costAnalogyForm.getSupplierFormList().size();n++){
					
					cell = row.createCell(n+1);
					cell.setCellValue(formatNumber(costAnalogyForm.getSupplierFormList().get(n).getTotal()));
					cell.setCellStyle(strStyleCenterRightFont);	
				}
				
				row = sheet.createRow(line);
				line++;
				cell = row.createCell(0);
				cell.setCellValue("供应商报价：");
				cell.setCellStyle(strStyleCenterFont);	
				for(int n=0;n<costAnalogyForm.getAllTotalList().size();n++){
					
					cell = row.createCell(n+1);
					cell.setCellValue(formatNumber(costAnalogyForm.getAllTotalList().get(n)));
					cell.setCellStyle(strStyleCenterRightFont);	
				}
				
				row = sheet.createRow(line);
				line++;
				cell = row.createCell(0);
				cell.setCellValue("合作价格：");
				cell.setCellStyle(strStyleCenterFont);	
				for(int n=0;n<costAnalogyForm.getCooperativePriceList().size();n++){
					cell = row.createCell(n+1);
					cell.setCellValue(formatNumber(StringUtils.isBlank(costAnalogyForm.getCooperativePriceList().get(n))?null: new BigDecimal(costAnalogyForm.getCooperativePriceList().get(n))));
					cell.setCellStyle(strStyleCenterRightFont);	
				}
				row = sheet.createRow(line);
				cell = row.createCell(0);
				cell.setCellValue("采购备注：");
				cell.setCellStyle(strStyleCenterFont);	
				for(int n=0;n<remarkTexts.size();n++){
					cell = row.createCell(n+1);
					cell.setCellValue(remarkTexts.get(n));
					cell.setCellStyle(strStyleCenterRightFont);	
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
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * 
	 */
	private String formatNumber(BigDecimal num) {
		DecimalFormat df = new DecimalFormat("#,##0.000;(#)");
		df.setRoundingMode(RoundingMode.HALF_UP);
		if (num != null) {
			return df.format(num);
		}
		return "";
	}
	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * 
	 */
	private String formatNumber2(BigDecimal num) {
		DecimalFormat df = new DecimalFormat("#,##0.00;(#)");
		df.setRoundingMode(RoundingMode.HALF_UP);
		if (num != null) {
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

}
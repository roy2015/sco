package com.powere2e.sco.service.impl.accessoryintentioncostanalysis.totalcostanalysis;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.powere2e.sco.interfaces.dao.accessoryintentioncostanalysis.totalcostanalysis.TotalcostanalysisDao;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryQuotedElectronicService;
import com.powere2e.sco.interfaces.service.accessoryintentioncostanalysis.totalcostanalysis.TotalcostanalysisService;
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
import com.powere2e.sco.model.accessoryintention.AccessoryIntention;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedElectronic;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.QuotedDetailForm;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.QuotedForm;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.SupplierForm;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.Totalcostanalysis;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.TotalcostanalysisForm;
import com.powere2e.sco.model.accessoryoaapplication.ApplicationQuoted;
import com.powere2e.sco.model.accessoryoaapplication.ApplicationReportAccessory;
import com.powere2e.sco.model.accessoryoaapplication.OaApplication;
import com.powere2e.sco.model.accessoryoaapplication.SubscribeAccessory;
import com.powere2e.sco.model.accessoryoaapplication.WlInfo;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.service.impl.accessoryintention.AccessoryIntentionServiceImpl;
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
public class TotalcostanalysisServiceImpl extends ServiceImpl implements TotalcostanalysisService {
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
	private TotalcostanalysisDao totalcostanalysisDao;

	public static TotalcostanalysisService getInstance() {
		return (TotalcostanalysisService) ConfigFactory.getInstance().getBean("totalcostanalysisService");
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
	public TotalcostanalysisDao getTotalcostanalysisDao() {
		return totalcostanalysisDao;
	}

	// 设置报价单DAO实例
	public void setTotalcostanalysisDao(TotalcostanalysisDao totalcostanalysisDao) {
		this.totalcostanalysisDao = totalcostanalysisDao;
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
			tarPath = FreeMarkerUtil.generateHtml("totalCostAnalogyAnalysis/accessoryIntentionCostAnalogyAnalysis.ftl", "accessoryIntentiontotalCostAnalogyAnalysis/totalcostanalysis".concat("/")
					.concat(fileName), map);
		} catch (Exception e) {
			return "转换报表模板时异常";
		}
		File file = new File(tarPath);// 报表文件
		if (file.exists()) {
			try {
				Reports myReport = new Reports(BusinessConstants.myReportType.FQA.toString(), fileName, tarPath.replace(ConfigPath.getUploadFilePath(), ""));
				ReportsServiceImpl.getInstance().insertReports(myReport);
			} catch (Exception e) {
				file.delete();
				LoggerUtil.logger.error("TotalcostanalysisServiceImpl.saveSearchDataForm删除文件["+ file.getPath() +"]");
				return "记录报表文件信息时出错";
			}
		} else {
			return "生成Html文件失败";
		}
		return null;
	}

	// 采购委员会竞价单OA申请意向品列表
	@Override
	public List<Totalcostanalysis> listTotalcostanalysisIntentionApplication(Map<String, Object> map, PageInfo pageInfo) {
		return totalcostanalysisDao.listTotalcostanalysisIntentionApplication(map, pageInfo);
	}

	// 根据报价单编号获得采购委员会竞价单OA申请意向品
	@Override
	public Totalcostanalysis loadTotalcostanalysisIntentionApplication(String quotedCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quotedCode", quotedCode);
		return totalcostanalysisDao.loadTotalcostanalysisIntentionApplication(map);
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
			if (oaApplication != null && !BusinessConstants.ApplicationStatus.W.toString().equals(oaApplication.getApplicationStatus())
					&& !BusinessConstants.ApplicationStatus.CG.toString().equals(oaApplication.getApplicationStatus())) {
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
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("applicationCode", applicationCode); List<Reports>
		 * list=nonFoodReportAnalysisService.listAnalysisReportUpload(map,
		 * null); if(list!=null &&list.size()>0 ) check+="缺少成本分析表."; String
		 * quotedCodes="" List<ApplicationQuoted> applicationQuotedList=
		 * applicationQuotedService.listApplicationQuoted(map, null);
		 * for(ApplicationQuoted applicationQuoted:applicationQuotedList){
		 * quotedCodes=quotedCodes+applicationQuoted.getQuotedCode()+","; }
		 * Map<String, Object> map1 = new HashMap<String, Object>();
		 * map.put("quotedCode", quotedCodes.split(","));
		 * List<SupplierAttachmentA> listSupplierAttachmentA =
		 * supplierAttachmentAService.listQuotedRecord(map, null);
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
			totalcostanalysisDao.updateIntentionSupplierAccessory(map);
		}
	}

	@Override
	public List<QuotedForm> listQuotedForm(Map<String, Object> map, PageInfo pageInfo) {
		return totalcostanalysisDao.listQuotedForm(map, pageInfo);
	}

	@Override
	public List<QuotedDetailForm> listQuotedDetailForm(Map<String, Object> map, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return totalcostanalysisDao.listQuotedDetailForm(map, pageInfo);
	}

	@Override
	public void exportExcel(TotalcostanalysisForm totalcostanalysisForm, ServletOutputStream out) {
		Workbook wb = new XSSFWorkbook();
		XSSFFont blueFont = (XSSFFont) wb.createFont();
		XSSFColor blueColor = new XSSFColor(Color.BLUE);
		blueFont.setColor(blueColor);// 蓝色
		blueFont.setFontName("微软雅黑"); 
		blueFont.setBold(true);
		XSSFFont blackFont = (XSSFFont) wb.createFont();
		blackFont.setFontName("微软雅黑"); 
		blackFont.setBold(true);
		 XSSFFont fontYH = (XSSFFont) wb.createFont();//创建字体对象  
		 fontYH.setFontName("微软雅黑"); 
		//SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("辅料总价分析结果");
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		// CellStyle dateStyle = ExcelUtils.getDefaultDateStyle(wb);// 字符串样式
		// CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式
		CellStyle strStyleCenter = ExcelUtils.getDefaultStringStyle(wb);// 字符串居中靠左样式
		ExcelUtils.setAlign(strStyleCenter, "left", "top", true);
		CellStyle strStyleCenterCenter = ExcelUtils.getDefaultStringStyle(wb);// 合并居中样式
		ExcelUtils.setAlign(strStyleCenterCenter, "center", "center", true);
		CellStyle strStyleCenterFont = ExcelUtils.getDefaultStringStyle(wb);// 字符串居中靠左样式
		Font font  = wb.createFont();      
		font.setFontHeightInPoints((short) 11);//字号      
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);//加粗    
		font.setFontName("微软雅黑"); 
		strStyleCenterFont.setFont(font);
		strStyleCenter.setFont(fontYH);
		strStyle.setFont(fontYH);
		strStyleCenterCenter.setFont(fontYH);
		ExcelUtils.setAlign(strStyleCenterFont, "center", "center", true);
		CellStyle strStyleLeftFont = ExcelUtils.getDefaultStringStyle(wb);// 字符串居中靠左样式
		strStyleLeftFont.setFont(font);
		ExcelUtils.setAlign(strStyleLeftFont, "left", "top", true);
		Row row = sheet.createRow(0);
		
		// row.setHeight((short) (50 * 20));
		// 4是前面的固定列
		sheet.setColumnWidth(0, 60 * 70);
		sheet.setColumnWidth(1, 80 * 90);
		sheet.setColumnWidth(2, 60 * 70);
		sheet.setColumnWidth(3, 60 * 70);
		for (int i = 0; i < totalcostanalysisForm.getSupplierFormList().size() + 4; i++) {
			sheet.setColumnWidth(4 + i, 90 * 90);
		}
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb, 11);
		CellStyle firstRowStyleLeft = ExcelUtils.getHeaderCellStyle(wb, 11);
		ExcelUtils.setAlign(firstRowStyleLeft, "left", "center", true);
		firstRowStyle.setWrapText(true);
		strStyle.setWrapText(true);
		strStyleCenter.setWrapText(true);
		int rows=0;//抬头行数
		for (int i = 0; i < totalcostanalysisForm.getSupplierFormList().size(); i++) {
			String value = "";
			SupplierForm supplierForm = totalcostanalysisForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(totalcostanalysisForm.getIntentionSupplierCodeShow())){
				value += "供应商编号-公司名称："+"\r" + supplierForm.getIntentionSupplierCode() + "-" + supplierForm.getIntentionSupplierName() ;
				Cell cell;
				if(i==0){
				cell = row.createCell(0);
				cell.setCellValue("意向品名称");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(1);
				cell.setCellValue("询价单意向品规格");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(2);
				cell.setCellValue("报价数量");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(3);
				cell.setCellValue("采购数量");
				cell.setCellStyle(strStyleCenterFont);
				}
			cell = row.createCell(4 + i);
			cell.setCellValue(value);
			cell.setCellStyle(strStyleLeftFont);
				if(i==0){
			rows++;
				}
				
			}
			
		}
		for (int i = 0; i < totalcostanalysisForm.getSupplierFormList().size(); i++) {
			String value = "";
			SupplierForm supplierForm = totalcostanalysisForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(totalcostanalysisForm.getContactsShow())){
				value += "联系人：" + supplierForm.getContacts() ;
				Cell cell;
				if(i==0){
				row = sheet.createRow(rows);
				
				cell = row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(2);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(3);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				}
				cell = row.createCell(4 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
				
			}
			}
		for (int i = 0; i < totalcostanalysisForm.getSupplierFormList().size(); i++) {
			String value = "";
			SupplierForm supplierForm = totalcostanalysisForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(totalcostanalysisForm.getPhoneShow())){
				value += "联系电话：" + supplierForm.getPhone() ;
				Cell cell;
				if(i==0){
				row = sheet.createRow(rows);
				
				cell = row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(2);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(3);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				}
				cell = row.createCell(4 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
		for (int i = 0; i < totalcostanalysisForm.getSupplierFormList().size(); i++) {
			String value = "";
			SupplierForm supplierForm = totalcostanalysisForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(totalcostanalysisForm.getCompanySiteShow())){
				value += "公司地址："+"\r" + supplierForm.getCompanySite() ;
				Cell cell;
				if(i==0){
				row = sheet.createRow(rows);
				
				cell = row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(2);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(3);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				}
				cell = row.createCell(4 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
			for (int i = 0; i < totalcostanalysisForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = totalcostanalysisForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(totalcostanalysisForm.getFactorySiteShow())){
				value += "工厂地址："+"\r" + supplierForm.getFactorySite();
				Cell cell;
				if(i==0){
				row = sheet.createRow(rows);
				
				cell = row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(2);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(3);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				}
				cell = row.createCell(4 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
			for (int i = 0; i < totalcostanalysisForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = totalcostanalysisForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(totalcostanalysisForm.getRegisterCapitalShow())){
				value += "注册资本(万元)：" + supplierForm.getRegisterCapital() ;
				Cell cell;
				if(i==0){
				row = sheet.createRow(rows);
				
				cell = row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(2);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(3);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				}
				cell = row.createCell(4 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
			for (int i = 0; i < totalcostanalysisForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = totalcostanalysisForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(totalcostanalysisForm.getYearTurnoverShow())){
				value += "年营业额(万元)：" + supplierForm.getYearTurnover() ;
				Cell cell;
				if(i==0){
				row = sheet.createRow(rows);
				
				cell = row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(2);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(3);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				}
				cell = row.createCell(4 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
			for (int i = 0; i < totalcostanalysisForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = totalcostanalysisForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(totalcostanalysisForm.getFactoryAreaShow())){
				value += "工厂面积(㎡)：" + formatNumber(supplierForm.getFactoryArea()) ;
				Cell cell;
				if(i==0){
				row = sheet.createRow(rows);
				
				cell = row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(2);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(3);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				}
				cell = row.createCell(4 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
			for (int i = 0; i < totalcostanalysisForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = totalcostanalysisForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(totalcostanalysisForm.getStaffCountShow())){
				value += "工人数：" + formatNumber(supplierForm.getStaffCount()).replace(".000", "") ;
				Cell cell;
				if(i==0){
				row = sheet.createRow(rows);
				
				cell = row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(2);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(3);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				}
				cell = row.createCell(4 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
			for (int i = 0; i < totalcostanalysisForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = totalcostanalysisForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(totalcostanalysisForm.getDailyCapacityShow())){
				value += "日产能：" + supplierForm.getDailyCapacity() ;
				Cell cell;
				if(i==0){
				row = sheet.createRow(rows);
				
				cell = row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(2);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(3);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				}
				cell = row.createCell(4 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
			for (int i = 0; i < totalcostanalysisForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = totalcostanalysisForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(totalcostanalysisForm.getHzgppShow())){
				value += "合作过品牌：" + supplierForm.getHzgpp() ;
				Cell cell;
				if(i==0){
				row = sheet.createRow(rows);
				
				cell = row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(2);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(3);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				}
				cell = row.createCell(4 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
			for (int i = 0; i < totalcostanalysisForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = totalcostanalysisForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(totalcostanalysisForm.getInvoiceTypeShow())){
				value += "发票类型(增/普票)：" + supplierForm.getInvoiceType() ;
				Cell cell;
				if(i==0){
				row = sheet.createRow(rows);
				
				cell = row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(2);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(3);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				}
				cell = row.createCell(4 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
			for (int i = 0; i < totalcostanalysisForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = totalcostanalysisForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(totalcostanalysisForm.getQuotedCurrencyShow())){
				value += "报价币种：" + supplierForm.getQuotedCurrency() ;
				Cell cell;
				if(i==0){
				row = sheet.createRow(rows);
				
				cell = row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(2);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(3);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				}
				cell = row.createCell(4 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
			for (int i = 0; i < totalcostanalysisForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = totalcostanalysisForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(totalcostanalysisForm.getTaxRateShow())){
				value += "税率(%)：" + formatNumber(supplierForm.getTaxRate())+"%" ;
				Cell cell;
				if(i==0){
				row = sheet.createRow(rows);
				
				cell = row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(2);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(3);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				}
				cell = row.createCell(4 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
			for (int i = 0; i < totalcostanalysisForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = totalcostanalysisForm.getSupplierFormList().get(i);
			if (StringUtils.isNotBlank(totalcostanalysisForm.getPaymentTypeShow())){
				value += "付款方式：" + supplierForm.getPaymentType() ;
				Cell cell;
				if(i==0){
				row = sheet.createRow(rows);
				
				cell = row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(2);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(3);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				}
				cell = row.createCell(4 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
			}
			}
			for (int i = 0; i < totalcostanalysisForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = totalcostanalysisForm.getSupplierFormList().get(i);
				if (StringUtils.isNotBlank(totalcostanalysisForm.getProofingContentBeforeShow())){
				value += "产前样打样内容："+"\r" + supplierForm.getProofingContentBefore() ;
				Cell cell;
				if(i==0){
				row = sheet.createRow(rows);
				
				cell = row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(2);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(3);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				}
				cell = row.createCell(4 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
				}
			}
			for (int i = 0; i < totalcostanalysisForm.getSupplierFormList().size(); i++) {
				String value = "";
				SupplierForm supplierForm = totalcostanalysisForm.getSupplierFormList().get(i);
				if (StringUtils.isNotBlank(totalcostanalysisForm.getProofingContentBeforeShow())){
				value += "产前样打样评价："+"\r" + supplierForm.getProofingEvaluateBefore() ;
				Cell cell;
				if(i==0){
				row = sheet.createRow(rows);
				
				cell = row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(2);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(3);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				}
				cell = row.createCell(4 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
				}
			}
				for (int i = 0; i < totalcostanalysisForm.getSupplierFormList().size(); i++) {
					String value = "";
					SupplierForm supplierForm = totalcostanalysisForm.getSupplierFormList().get(i);
					if (StringUtils.isNotBlank(totalcostanalysisForm.getProofingContentAfterShow())){
				value += "非产前样打样内容："+"\r" + supplierForm.getProofingContentAfter() ;
				Cell cell;
				if(i==0){
				row = sheet.createRow(rows);
				
				cell = row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(2);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(3);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				}
				cell = row.createCell(4 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
					}
				}
				for (int i = 0; i < totalcostanalysisForm.getSupplierFormList().size(); i++) {
					String value = "";
					SupplierForm supplierForm = totalcostanalysisForm.getSupplierFormList().get(i);
					if (StringUtils.isNotBlank(totalcostanalysisForm.getProofingContentAfterShow())){
				value += "非产前样打样评价："+"\r" + supplierForm.getProofingEvaluateAfter() ;
				Cell cell;
				if(i==0){
				row = sheet.createRow(rows);
				
				cell = row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(2);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				cell = row.createCell(3);
				cell.setCellValue("");
				cell.setCellStyle(strStyleCenterFont);
				}
				cell = row.createCell(4 + i);
				cell.setCellValue(value);
				cell.setCellStyle(strStyleCenter);
				if(i==0){
					rows++;
						}
					}
				}
				ExcelUtils.addMergedRegion(sheet, "A1" + ":" + "A" + (rows));
				ExcelUtils.addMergedRegion(sheet, "B1" + ":" + "B" + (rows));
				ExcelUtils.addMergedRegion(sheet, "C1" + ":" + "C" + (rows));
				ExcelUtils.addMergedRegion(sheet, "D1" + ":" + "D" + (rows));
		int line = rows;
		for (int i = 0; i < totalcostanalysisForm.getIntentionFormList().size(); i++) {
			for (int m = 0; m < totalcostanalysisForm.getIntentionFormList().get(i).getEnquiryList().size(); m++) {
				for (int n = 0; n < totalcostanalysisForm.getIntentionFormList().get(i).getEnquiryList().get(m).getAccessoryEnquiryQuotedCountList().size(); n++) {
					row = sheet.createRow(line);
					Cell cell = row.createCell(0);
					AccessoryIntention accessoryIntention=AccessoryIntentionServiceImpl.getInstance().loadAccessoryIntention(totalcostanalysisForm.getIntentionFormList().get(i).getIntentionCode());
					cell.setCellValue(accessoryIntention.getIntentionName());
					cell.setCellStyle(strStyleCenterFont);
					// 规格
					int[] length=new int[4];
					cell = row.createCell(1);
					String gg = "";
					if (n == 0) {
						gg += "原材料名称|" + "材料|" + "尺寸|" + "\r\n";
						List<AccessoryEnquiryMaterial> accessoryEnquiryMaterialList = totalcostanalysisForm.getIntentionFormList().get(i).getEnquiryList().get(m).getAccessoryEnquiryMaterialList();
						if (accessoryEnquiryMaterialList != null) {
							for (AccessoryEnquiryMaterial accessoryEnquiryMaterial : accessoryEnquiryMaterialList) {
								gg += accessoryEnquiryMaterial.getMaterialName() + "|" + accessoryEnquiryMaterial.getMaterial() + "|" + accessoryEnquiryMaterial.getMaterialSize() + "\r\n";
							}
						}
						length[0]=gg.length();
						gg += "辅料名称|" + "材料|" + "尺寸|" + "\r\n";
						List<AccessoryEnquiryAccessory> accessoryEnquiryAccessoryList = totalcostanalysisForm.getIntentionFormList().get(i).getEnquiryList().get(m).getAccessoryEnquiryAccessoryList();
						if (accessoryEnquiryAccessoryList != null) {
							for (AccessoryEnquiryAccessory accessoryEnquiryAccessory : accessoryEnquiryAccessoryList) {
								gg += accessoryEnquiryAccessory.getAccessoryName() + "|" + accessoryEnquiryAccessory.getMaterial() + "|" + accessoryEnquiryAccessory.getMaterialSize() + "\r\n";
							}
						}
						length[1]=gg.length();
						gg += "内外包装材料名称|" + "材料|" + "尺寸|" + "\r\n";
						List<AccessoryEnquiryPacking> accessoryEnquiryPackingList = totalcostanalysisForm.getIntentionFormList().get(i).getEnquiryList().get(m).getAccessoryEnquiryPackingList();
						if (accessoryEnquiryPackingList != null) {
							for (AccessoryEnquiryPacking accessoryEnquiryPacking : accessoryEnquiryPackingList) {
								gg += accessoryEnquiryPacking.getPackingName() + "|" + accessoryEnquiryPacking.getPackingMaterial() + "|" + accessoryEnquiryPacking.getMaterialSize() + "\r\n";
							}
						}
						length[2]=gg.length();
						gg += "工艺名称|" + "工艺要求|" + "\r\n";
						List<AccessoryEnquiryTechnology> accessoryEnquiryTechnologyList = totalcostanalysisForm.getIntentionFormList().get(i).getEnquiryList().get(m)
								.getAccessoryEnquiryTechnologyList();
						if (accessoryEnquiryTechnologyList != null) {
							for (AccessoryEnquiryTechnology accessoryEnquiryTechnology : accessoryEnquiryTechnologyList) {
								gg += accessoryEnquiryTechnology.getTechnologyName() + "|" + accessoryEnquiryTechnology.getTechnologyInfo() + "\r\n";
							}
						}
						length[3]=gg.length();
						gg += "要求名称|" + "要求内容|" + "\r\n";
						List<AccessoryEnquiryElse> accessoryEnquiryElseList = totalcostanalysisForm.getIntentionFormList().get(i).getEnquiryList().get(m).getAccessoryEnquiryElseList();
						if (accessoryEnquiryElseList != null) {
							for (AccessoryEnquiryElse accessoryEnquiryElse : accessoryEnquiryElseList) {
								gg += accessoryEnquiryElse.getName() + "|" + accessoryEnquiryElse.getInfo() + "\r\n";
							}
						}
					}
					if (n == 0) {
					XSSFRichTextString richString = new XSSFRichTextString(gg);
					richString.applyFont(0, 12, blueFont);
					richString.applyFont(length[0], length[0]+11, blueFont);
					richString.applyFont(length[1], length[1]+15, blueFont);
					richString.applyFont(length[2], length[2]+10, blueFont);
					richString.applyFont(length[3], length[3]+10, blueFont);
					cell.setCellValue(richString);
					}else{
						cell.setCellValue(gg);
					}
					//cell.setCellValue(gg);
					cell.setCellStyle(strStyleCenter);
					// 报价数量
					cell = row.createCell(2);
					cell.setCellValue(formatNumber(totalcostanalysisForm.getIntentionFormList().get(i).getEnquiryList().get(m).getAccessoryEnquiryQuotedCountList().get(n).getQuotedCount()).replace(".000", ""));
					cell.setCellStyle(strStyleCenter);
					// 实际采购数量
					cell = row.createCell(3);
					cell.setCellValue(formatNumber(totalcostanalysisForm.getIntentionFormList().get(i).getEnquiryList().get(m).getAccessoryEnquiryQuotedCountList().get(n).getPurchaseCount()).replace(".000", ""));
					cell.setCellStyle(strStyleCenter);
				
					for (int k = 0; k < totalcostanalysisForm.getIntentionFormList().get(i).getEnquiryList().get(m).getAccessoryEnquiryQuotedCountList().get(n).getQuotedFormList().size(); k++) {
						cell = row.createCell(4 + k);
						String supValue = "";
						List<Integer> strLengthList=new ArrayList<Integer>();
						if (totalcostanalysisForm.getIntentionFormList().get(i).getEnquiryList().get(m).getAccessoryEnquiryQuotedCountList().get(n).getQuotedFormList().get(k) != null
								&& totalcostanalysisForm.getIntentionFormList().get(i).getEnquiryList().get(m).getAccessoryEnquiryQuotedCountList().get(n).getQuotedFormList().get(k)
										.getLastQuotedRank() != null) {
							supValue +=StringUtils.isBlank(totalcostanalysisForm.getIntentionFormList().get(i).getEnquiryList().get(m).getAccessoryEnquiryQuotedCountList().get(n).getQuotedFormList().get(k)
									.getLastQuotedRank())?"": "最后一次报价排行："
									+ totalcostanalysisForm.getIntentionFormList().get(i).getEnquiryList().get(m).getAccessoryEnquiryQuotedCountList().get(n).getQuotedFormList().get(k)
											.getLastQuotedRank() + "\r\n";
							supValue += StringUtils.isBlank(formatNumber(totalcostanalysisForm.getIntentionFormList().get(i).getEnquiryList().get(m).getAccessoryEnquiryQuotedCountList().get(n).getQuotedFormList().get(k)
									.getLastProductionCycle()))?"":"最后一次报价生产周期(天)："
									+ formatNumber(totalcostanalysisForm.getIntentionFormList().get(i).getEnquiryList().get(m).getAccessoryEnquiryQuotedCountList().get(n).getQuotedFormList().get(k)
											.getLastProductionCycle()).replace(".000", "") + "\r\n";
							strLengthList.add(supValue.length());
						}
						if (totalcostanalysisForm.getIntentionFormList().get(i).getEnquiryList().get(m).getAccessoryEnquiryQuotedCountList().get(n).getQuotedFormList().get(k)
								.getQuotedDetailFormList() != null) {
							for (int x = 0; x < totalcostanalysisForm.getIntentionFormList().get(i).getEnquiryList().get(m).getAccessoryEnquiryQuotedCountList().get(n).getQuotedFormList().get(k)
									.getQuotedDetailFormList().size(); x++) {
								SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd"); 
								QuotedDetailForm quotedDetailForm = totalcostanalysisForm.getIntentionFormList().get(i).getEnquiryList().get(m).getAccessoryEnquiryQuotedCountList().get(n)
										.getQuotedFormList().get(k).getQuotedDetailFormList().get(x);
								if (totalcostanalysisForm.getAllbjd()!=null&&totalcostanalysisForm.getAllbjd()) {
									supValue += "第" + (x + 1) + "次报价日期：" + time.format(quotedDetailForm.getQuotedDate()) + "\r\n";
											
									strLengthList.add(supValue.length());
									supValue +="报价单价(元)：" + formatNumber(quotedDetailForm.getUnitPrice()) + "\r\n"+"报价总价(元)：" + formatNumber(quotedDetailForm.getQuotedTotal())+ "\r\n";
								} else {
									supValue += "最后一次报价日期：" + time.format(quotedDetailForm.getQuotedDate()) + "\r";
									strLengthList.add(supValue.length());
									supValue +="报价单价(元)：" + formatNumber(quotedDetailForm.getUnitPrice()) + "\r\n"+"报价总价(元)：" + formatNumber(quotedDetailForm.getQuotedTotal()) + "\r\n";
								}
							}
						}
						if(StringUtils.isNotBlank(supValue)&&strLengthList.size()>1){
							XSSFRichTextString richString = new XSSFRichTextString(supValue);
							if(totalcostanalysisForm.getAllbjd()!=null&&totalcostanalysisForm.getAllbjd()){
							richString.applyFont(0, strLengthList.get(0), blackFont);
							for(int x=1;x<strLengthList.size();x++){
								richString.applyFont(strLengthList.get(x)-20, strLengthList.get(x), blackFont);	
							}
							}else{
								richString.applyFont(0, strLengthList.get(0), blackFont);
								for(int x=1;x<strLengthList.size();x++){
									richString.applyFont(strLengthList.get(x)-22, strLengthList.get(x), blackFont);	
								}	
							}
							cell.setCellValue(richString);
						}else{
						cell.setCellValue(supValue);
						}
						cell.setCellStyle(strStyleCenter);
					}
					if(totalcostanalysisForm.getIntentionFormList().get(i).getEnquiryList().get(m).getAccessoryEnquiryQuotedCountList().size()<=1){
					row.setHeight((short) (28 * gg.length()));
					}
					if(totalcostanalysisForm.getIntentionFormList().get(i).getEnquiryList().get(m).getAccessoryEnquiryQuotedCountList().size()==2&&gg.length()>450){
						row.setHeight((short) (28 * gg.length()));
						}
					if(totalcostanalysisForm.getIntentionFormList().get(i).getEnquiryList().get(m).getAccessoryEnquiryQuotedCountList().size()==3&&gg.length()>700){
						row.setHeight((short) (28 * gg.length()));
						}
					if(totalcostanalysisForm.getIntentionFormList().get(i).getEnquiryList().get(m).getAccessoryEnquiryQuotedCountList().size()==4&&gg.length()>1000){
						row.setHeight((short) (28 * gg.length()));
						}
					line++;
				}
				ExcelUtils.addMergedRegion(sheet, "B" + (line - totalcostanalysisForm.getIntentionFormList().get(i).getEnquiryList().get(m).getAccessoryEnquiryQuotedCountList().size() + 1) + ":"
						+ "B" + (line));
			}
			ExcelUtils.addMergedRegion(sheet, "A" + (line - totalcostanalysisForm.getIntentionFormList().get(i).getCount() + 1) + ":" + "A" + (line));
		}
		// ExcelUtils.addMergedRegion(sheet, "A" + (rowIndex - listAem.size() +
		// 1) + ":" + "A" + (rowIndex));
		/*
		 * if (totalcostanalysisForm.getIntentionTotalFormList().size() > 1) {
		 * // 合计 for (int i = 0; i <
		 * totalcostanalysisForm.getIntentionTotalFormList().size(); i++) { row
		 * = sheet.createRow(line); Cell cell = row.createCell(0);
		 * cell.setCellValue("最后一次询价单报价合计:"); cell.setCellStyle(strStyle); cell
		 * = row.createCell(1); cell.setCellValue("");
		 * cell.setCellStyle(strStyleCenter); cell = row.createCell(2);
		 * cell.setCellValue
		 * (totalcostanalysisForm.getIntentionTotalFormList().get
		 * (i).getQuotedCount()); cell.setCellStyle(strStyleCenter); cell =
		 * row.createCell(3); cell.setCellValue("");
		 * cell.setCellStyle(strStyleCenter); ExcelUtils.addMergedRegion(sheet,
		 * "A" + (line + 1) + ":" + "B" + (line + 1)); for (int m = 0; m <
		 * totalcostanalysisForm
		 * .getIntentionTotalFormList().get(i).getQuotedTotalFormList().size();
		 * m++) { cell = row.createCell(4 + m); String hj = ""; hj +=
		 * totalcostanalysisForm
		 * .getIntentionTotalFormList().get(i).getQuotedTotalFormList
		 * ().get(m).getLastQuotedRank() + "\r\n"; for (int n = 0; n <
		 * totalcostanalysisForm
		 * .getIntentionTotalFormList().get(i).getQuotedTotalFormList
		 * ().get(m).getQuotedDetailTotalFormList().size(); n++) { hj += "第" +
		 * (n + 1) + "次报价合计" +
		 * df.format(totalcostanalysisForm.getIntentionTotalFormList
		 * ().get(i).getQuotedTotalFormList
		 * ().get(m).getQuotedDetailTotalFormList
		 * ().get(n).getQuotedTotal()).replace(".000", "") + "\r\n"; }
		 * cell.setCellValue(hj); cell.setCellStyle(strStyleCenter); } line++; }
		 * // 抵税后合计 for (int i = 0; i <
		 * totalcostanalysisForm.getIntentionTotalFormList().size(); i++) { row
		 * = sheet.createRow(line); Cell cell = row.createCell(0);
		 * cell.setCellValue("最后一次询价单报价合计(换成普票):"); cell.setCellStyle(strStyle);
		 * cell = row.createCell(1); cell.setCellValue("");
		 * cell.setCellStyle(strStyleCenter); cell = row.createCell(2);
		 * cell.setCellValue
		 * (totalcostanalysisForm.getIntentionTotalFormList().get
		 * (i).getQuotedCount()); cell.setCellStyle(strStyleCenter); cell =
		 * row.createCell(3); cell.setCellValue("");
		 * cell.setCellStyle(strStyleCenter); ExcelUtils.addMergedRegion(sheet,
		 * "A" + (line + 1) + ":" + "B" + (line + 1)); for (int m = 0; m <
		 * totalcostanalysisForm
		 * .getIntentionTotalFormList().get(i).getQuotedTotalFormList().size();
		 * m++) { cell = row.createCell(4 + m); String hj = ""; hj +=
		 * totalcostanalysisForm
		 * .getIntentionTotalFormList().get(i).getQuotedTotalFormList
		 * ().get(m).getLastQuotedRank() + "\r\n"; for (int n = 0; n <
		 * totalcostanalysisForm
		 * .getIntentionTotalFormList().get(i).getQuotedTotalFormList
		 * ().get(m).getQuotedDetailTotalFormListHs().size(); n++) { hj += "第" +
		 * (n + 1) + "次报价合计" +
		 * df.format(totalcostanalysisForm.getIntentionTotalFormList
		 * ().get(i).getQuotedTotalFormList
		 * ().get(m).getQuotedDetailTotalFormListHs
		 * ().get(n).getQuotedTotal()).replace(".000", "") + "\r\n"; }
		 * cell.setCellValue(hj); cell.setCellStyle(strStyleCenter); } line++; }
		 * 
		 * }
		 */
		row = sheet.createRow(line);
		Cell cell = row.createCell(0);
		cell.setCellValue("采购备注:"+totalcostanalysisForm.getRemark());
		cell.setCellStyle(strStyle);
		for (int i = 1; i <totalcostanalysisForm.getSupplierFormList().size()+4; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(strStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "A" + (line+1) + ":" + "E" + (line+1));
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
	private String  formatNumber(BigDecimal num)  {
		DecimalFormat df =new DecimalFormat("#,##0.000;(#)");
		df.setRoundingMode(RoundingMode.HALF_UP); 
		if(num!=null){
			return df.format(num);
		}
		return "";
	}
}
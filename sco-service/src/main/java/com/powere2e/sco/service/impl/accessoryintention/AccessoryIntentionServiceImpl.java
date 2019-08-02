package com.powere2e.sco.service.impl.accessoryintention;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.LoggerUtil;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryIntentionDao;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryEnquiryAccessoryService;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryEnquiryElseService;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryEnquiryMaterialService;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryEnquiryPackingService;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryEnquiryQuotedCountService;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryEnquiryService;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryEnquiryTechnologyService;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryIntentionService;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryProofingService;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryQuotedAccessoryService;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryQuotedElectronicService;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryQuotedElseService;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryQuotedMaterialService;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryQuotedPackingService;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryQuotedScanService;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryQuotedTechnologyService;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryQuotedTotalService;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiry;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryAccessory;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryElse;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryMaterial;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryPacking;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryQuotedCount;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryTechnology;
import com.powere2e.sco.model.accessoryintention.AccessoryIntention;
import com.powere2e.sco.model.accessoryintention.AccessoryIntentionSupplier;
import com.powere2e.sco.model.accessoryintention.AccessoryProofing;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedAccessory;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedElectronic;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedElse;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedMaterial;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedPacking;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedScan;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedTechnology;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedTotal;
import com.powere2e.sco.validate.CheckUploadAccessoryQuotedValidate;
import com.powere2e.security.model.Option;
import com.powere2e.security.model.User;
import com.powere2e.security.utils.PowerUtils;

/**
 * 辅料意向品业务类的实现
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月16日
 */
public class AccessoryIntentionServiceImpl extends ServiceImpl implements AccessoryIntentionService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2495094510699289509L;
	private AccessoryIntentionDao accessoryIntentionDao;

	private AccessoryEnquiryMaterialService accessoryEnquiryMaterialService;
	private AccessoryEnquiryService accessoryEnquiryService;

	private AccessoryEnquiryAccessoryService accessoryEnquiryAccessoryService;
	private AccessoryEnquiryPackingService accessoryEnquiryPackingService;
	private AccessoryEnquiryTechnologyService accessoryEnquiryTechnologyService;
	private AccessoryEnquiryQuotedCountService accessoryEnquiryQuotedCountService;
	private AccessoryEnquiryElseService accessoryEnquiryElseService;

	private AccessoryQuotedMaterialService accessoryQuotedMaterialService;
	private AccessoryQuotedElectronicService accessoryQuotedElectronicService;
	private AccessoryQuotedAccessoryService accessoryQuotedAccessoryService;
	private AccessoryQuotedPackingService accessoryQuotedPackingService;
	private AccessoryQuotedTechnologyService accessoryQuotedTechnologyService;
	private AccessoryQuotedElseService accessoryQuotedElseService;
	private AccessoryQuotedTotalService accessoryQuotedTotalService;
	private AccessoryProofingService accessoryProofingService;
	private AccessoryQuotedScanService accessoryQuotedScanService;

	public static AccessoryIntentionService getInstance() {
		return (AccessoryIntentionService) ConfigFactory.getInstance().getBean("accessoryIntentionService");
	}

	// 获得辅料意向品DAO实例
	public AccessoryIntentionDao getAccessoryIntentionDao() {
		return accessoryIntentionDao;
	}

	// 设置辅料意向品DAO实例
	public void setAccessoryIntentionDao(AccessoryIntentionDao accessoryIntentionDao) {
		this.accessoryIntentionDao = accessoryIntentionDao;
	}

	public AccessoryEnquiryMaterialService getAccessoryEnquiryMaterialService() {
		return accessoryEnquiryMaterialService;
	}

	public void setAccessoryEnquiryMaterialService(AccessoryEnquiryMaterialService accessoryEnquiryMaterialService) {
		this.accessoryEnquiryMaterialService = accessoryEnquiryMaterialService;
	}

	public AccessoryEnquiryService getAccessoryEnquiryService() {
		return accessoryEnquiryService;
	}

	public void setAccessoryEnquiryService(AccessoryEnquiryService accessoryEnquiryService) {
		this.accessoryEnquiryService = accessoryEnquiryService;
	}

	public AccessoryEnquiryAccessoryService getAccessoryEnquiryAccessoryService() {
		return accessoryEnquiryAccessoryService;
	}

	public void setAccessoryEnquiryAccessoryService(AccessoryEnquiryAccessoryService accessoryEnquiryAccessoryService) {
		this.accessoryEnquiryAccessoryService = accessoryEnquiryAccessoryService;
	}

	public AccessoryEnquiryPackingService getAccessoryEnquiryPackingService() {
		return accessoryEnquiryPackingService;
	}

	public void setAccessoryEnquiryPackingService(AccessoryEnquiryPackingService accessoryEnquiryPackingService) {
		this.accessoryEnquiryPackingService = accessoryEnquiryPackingService;
	}

	public AccessoryEnquiryTechnologyService getAccessoryEnquiryTechnologyService() {
		return accessoryEnquiryTechnologyService;
	}

	public void setAccessoryEnquiryTechnologyService(AccessoryEnquiryTechnologyService accessoryEnquiryTechnologyService) {
		this.accessoryEnquiryTechnologyService = accessoryEnquiryTechnologyService;
	}

	public AccessoryEnquiryQuotedCountService getAccessoryEnquiryQuotedCountService() {
		return accessoryEnquiryQuotedCountService;
	}

	public void setAccessoryEnquiryQuotedCountService(AccessoryEnquiryQuotedCountService accessoryEnquiryQuotedCountService) {
		this.accessoryEnquiryQuotedCountService = accessoryEnquiryQuotedCountService;
	}

	public AccessoryEnquiryElseService getAccessoryEnquiryElseService() {
		return accessoryEnquiryElseService;
	}

	public void setAccessoryEnquiryElseService(AccessoryEnquiryElseService accessoryEnquiryElseService) {
		this.accessoryEnquiryElseService = accessoryEnquiryElseService;
	}

	public AccessoryQuotedMaterialService getAccessoryQuotedMaterialService() {
		return accessoryQuotedMaterialService;
	}

	public void setAccessoryQuotedMaterialService(AccessoryQuotedMaterialService accessoryQuotedMaterialService) {
		this.accessoryQuotedMaterialService = accessoryQuotedMaterialService;
	}

	public AccessoryQuotedElectronicService getAccessoryQuotedElectronicService() {
		return accessoryQuotedElectronicService;
	}

	public void setAccessoryQuotedElectronicService(AccessoryQuotedElectronicService accessoryQuotedElectronicService) {
		this.accessoryQuotedElectronicService = accessoryQuotedElectronicService;
	}

	public AccessoryQuotedAccessoryService getAccessoryQuotedAccessoryService() {
		return accessoryQuotedAccessoryService;
	}

	public void setAccessoryQuotedAccessoryService(AccessoryQuotedAccessoryService accessoryQuotedAccessoryService) {
		this.accessoryQuotedAccessoryService = accessoryQuotedAccessoryService;
	}

	public AccessoryQuotedPackingService getAccessoryQuotedPackingService() {
		return accessoryQuotedPackingService;
	}

	public void setAccessoryQuotedPackingService(AccessoryQuotedPackingService accessoryQuotedPackingService) {
		this.accessoryQuotedPackingService = accessoryQuotedPackingService;
	}

	public AccessoryQuotedTechnologyService getAccessoryQuotedTechnologyService() {
		return accessoryQuotedTechnologyService;
	}

	public void setAccessoryQuotedTechnologyService(AccessoryQuotedTechnologyService accessoryQuotedTechnologyService) {
		this.accessoryQuotedTechnologyService = accessoryQuotedTechnologyService;
	}

	public AccessoryQuotedElseService getAccessoryQuotedElseService() {
		return accessoryQuotedElseService;
	}

	public void setAccessoryQuotedElseService(AccessoryQuotedElseService accessoryQuotedElseService) {
		this.accessoryQuotedElseService = accessoryQuotedElseService;
	}

	public AccessoryQuotedTotalService getAccessoryQuotedTotalService() {
		return accessoryQuotedTotalService;
	}

	public void setAccessoryQuotedTotalService(AccessoryQuotedTotalService accessoryQuotedTotalService) {
		this.accessoryQuotedTotalService = accessoryQuotedTotalService;
	}

	public AccessoryProofingService getAccessoryProofingService() {
		return accessoryProofingService;
	}

	public void setAccessoryProofingService(AccessoryProofingService accessoryProofingService) {
		this.accessoryProofingService = accessoryProofingService;
	}

	public AccessoryQuotedScanService getAccessoryQuotedScanService() {
		return accessoryQuotedScanService;
	}

	public void setAccessoryQuotedScanService(AccessoryQuotedScanService accessoryQuotedScanService) {
		this.accessoryQuotedScanService = accessoryQuotedScanService;
	}

	// 查询
	@Override
	public List<AccessoryIntention> listAccessoryIntention(Map<String, Object> map, PageInfo pageInfo) {
		return this.getAccessoryIntentionDao().listAccessoryIntention(map, pageInfo);
	}

	// 查询关联供应商
	@Override
	public List<AccessoryIntentionSupplier> listAccessoryIntentionSupplier(Map<String, Object> map, PageInfo pageInfo) {
		return this.getAccessoryIntentionDao().listAccessoryIntentionSupplier(map, pageInfo);
	}

	// 添加
	@Override
	public void insertAccessoryIntention(AccessoryIntention accessoryIntention) {
		this.getAccessoryIntentionDao().insertAccessoryIntention(accessoryIntention.toMap());
	}

	// 添加询价单
	@Override
	public void insertAccessoryIntentionXJD(String enquiryCode, String intentionCode, AccessoryEnquiry ae, AccessoryEnquiryMaterial[] arr1, AccessoryEnquiryAccessory[] arr2,
			AccessoryEnquiryPacking[] arr3, AccessoryEnquiryTechnology[] arr4, AccessoryEnquiryQuotedCount[] arr5, AccessoryEnquiryElse[] arr6) throws IOException {
		/*
		 * AccessoryEnquiryMaterialService accessoryEnquiryMaterialService =
		 * AccessoryEnquiryMaterialServiceImpl.getInstance();
		 * AccessoryEnquiryService accessoryEnquiryService =
		 * AccessoryEnquiryServiceImpl.getInstance();
		 * 
		 * AccessoryEnquiryAccessoryService accessoryEnquiryAccessoryService =
		 * AccessoryEnquiryAccessoryServiceImpl.getInstance();
		 * AccessoryEnquiryPackingService accessoryEnquiryPackingService =
		 * AccessoryEnquiryPackingServiceImpl.getInstance();
		 * AccessoryEnquiryTechnologyService accessoryEnquiryTechnologyService =
		 * AccessoryEnquiryTechnologyServiceImpl.getInstance();
		 * AccessoryEnquiryQuotedCountService accessoryEnquiryQuotedCountService
		 * = AccessoryEnquiryQuotedCountServiceImpl.getInstance();
		 * AccessoryEnquiryElseService accessoryEnquiryElseService =
		 * AccessoryEnquiryElseServiceImpl.getInstance();
		 */

		AccessoryEnquiry aeq = null;
		AccessoryEnquiryMaterial aem = new AccessoryEnquiryMaterial();
		AccessoryEnquiryAccessory aea = new AccessoryEnquiryAccessory();
		AccessoryEnquiryPacking aep = new AccessoryEnquiryPacking();
		AccessoryEnquiryTechnology aet = new AccessoryEnquiryTechnology();
		AccessoryEnquiryQuotedCount aeqc = new AccessoryEnquiryQuotedCount();
		AccessoryEnquiryElse aee = new AccessoryEnquiryElse();
		aeq = accessoryEnquiryService.loadAccessoryEnquiry(enquiryCode);
		if (aeq == null) {
			ae.setIntentionCode(intentionCode);
			accessoryEnquiryService.insertAccessoryEnquiry(ae);
		} else {
			if (aeq.getAttachment() != null && ae.getAttachment() != null) {
				File file = new File(ConfigPath.getUploadFilePath() + aeq.getAttachment());
				if (file.isFile() && file.exists()) {
					file.delete();
					LoggerUtil.logger.error("AccessoryIntentionServiceImpl.insertAccessoryIntentionXJD生成报表文件出错,删除文件["+ file.getPath() +"]");
				}
			}
			accessoryEnquiryMaterialService.deleteAccessoryEnquiryMaterial(enquiryCode);
			accessoryEnquiryAccessoryService.deleteAccessoryEnquiryAccessory(enquiryCode);
			accessoryEnquiryPackingService.deleteAccessoryEnquiryPacking(enquiryCode);
			accessoryEnquiryTechnologyService.deleteAccessoryEnquiryTechnology(enquiryCode);
			accessoryEnquiryQuotedCountService.deleteAccessoryEnquiryQuotedCount(enquiryCode);
			accessoryEnquiryElseService.deleteAccessoryEnquiryElse(enquiryCode);
			ae.setIntentionCode(intentionCode);
			accessoryEnquiryService.updateAccessoryEnquiry(ae);
		}

		for (int i = 0; i < arr1.length; i++) {
			aem = arr1[i];
			aem.setEnquiryCode(enquiryCode);
			accessoryEnquiryMaterialService.insertAccessoryEnquiryMaterial(aem);
		}
		for (int i = 0; i < arr2.length; i++) {
			aea = arr2[i];
			if (StringUtils.isBlank(aea.getAccessoryName()) && StringUtils.isBlank(aea.getMaterial()) && StringUtils.isBlank(aea.getMaterialSize())) {

			} else {
				aea.setEnquiryCode(enquiryCode);
				accessoryEnquiryAccessoryService.insertAccessoryEnquiryAccessory(aea);
			}
		}
		for (int i = 0; i < arr3.length; i++) {
			aep = arr3[i];
			aep.setEnquiryCode(enquiryCode);
			accessoryEnquiryPackingService.insertAccessoryEnquiryPacking(aep);
		}
		for (int i = 0; i < arr4.length; i++) {
			aet = arr4[i];
			if (StringUtils.isBlank(aet.getTechnologyName()) && StringUtils.isBlank(aet.getTechnologyInfo())) {

			} else {
				aet.setEnquiryCode(enquiryCode);
				accessoryEnquiryTechnologyService.insertAccessoryEnquiryTechnology(aet);
			}

		}
		for (int i = 0; i < arr5.length; i++) {
			aeqc = arr5[i];
			aeqc.setEnquiryCode(enquiryCode);
			accessoryEnquiryQuotedCountService.insertAccessoryEnquiryQuotedCount(aeqc);
		}
		for (int i = 0; i < arr6.length; i++) {
			aee = arr6[i];
			if (StringUtils.isBlank(aee.getName()) && StringUtils.isBlank(aee.getInfo())) {

			} else {
				aee.setEnquiryCode(enquiryCode);
				accessoryEnquiryElseService.insertAccessoryEnquiryElse(aee);
			}

		}
	}

	// 从excel中解析报价单
	@Override
	public String insertUploadQuotedFromExcel(File quotedFile, Map<String, Object> map) throws Exception {
		FileInputStream is = new FileInputStream(quotedFile);
		XSSFWorkbook wb = new XSSFWorkbook(is);
		XSSFSheet sheet = wb.getSheetAt(0);
		Map<String, Object> newMap = new HashMap<String, Object>();
		newMap.put("enquiryCode", map.get("enquiryCode"));
		List<AccessoryEnquiryMaterial> listAem = accessoryEnquiryMaterialService.listAccessoryEnquiryMaterial(newMap, null);
		List<AccessoryEnquiryAccessory> listAea = accessoryEnquiryAccessoryService.listAccessoryEnquiryAccessory(newMap, null);
		List<AccessoryEnquiryPacking> listAep = accessoryEnquiryPackingService.listAccessoryEnquiryPacking(newMap, null);
		List<AccessoryEnquiryTechnology> listAet = accessoryEnquiryTechnologyService.listAccessoryEnquiryTechnology(newMap, null);
		List<AccessoryEnquiryQuotedCount> listAeqc = accessoryEnquiryQuotedCountService.listAccessoryEnquiryQuotedCount(newMap, null);
		CheckUploadAccessoryQuotedValidate checkUploadQuoted = new CheckUploadAccessoryQuotedValidate();
		Map<String, Object> quotedMap = checkUploadQuoted.upload(sheet, map, listAem, listAea, listAep, listAet, listAeqc);
		String check = (String) quotedMap.get("CHECK");

		if (check.length() == 0) {
			check += uploadQuotedFromExcel(quotedMap);
		}
		return check;
	}

	// 新增exel数据
	@SuppressWarnings("unchecked")
	private String uploadQuotedFromExcel(Map<String, Object> map) throws Exception {
		String check = "";
		try {
//			AccessoryIntentionSupplier accessoryIntentionSupplier = (AccessoryIntentionSupplier) map.get("accessoryIntentionSupplier");
			List<AccessoryQuotedMaterial> listAccessoryQuotedMaterial = (List<AccessoryQuotedMaterial>) map.get("listAccessoryQuotedMaterial");
			List<AccessoryQuotedAccessory> listAccessoryQuotedAccessory = (List<AccessoryQuotedAccessory>) map.get("listAccessoryQuotedAccessory");
			List<AccessoryQuotedPacking> listAccessoryQuotedPacking = (List<AccessoryQuotedPacking>) map.get("listAccessoryQuotedPacking");
			List<AccessoryQuotedTechnology> listAccessoryQuotedTechnology = (List<AccessoryQuotedTechnology>) map.get("listAccessoryQuotedTechnology");
			List<AccessoryQuotedElse> listAccessoryQuotedElse = (List<AccessoryQuotedElse>) map.get("listAccessoryQuotedElse");
			List<AccessoryQuotedTotal> listAccessoryQuotedTotal = (List<AccessoryQuotedTotal>) map.get("listAccessoryQuotedTotal");
			AccessoryQuotedElectronic accessoryQuotedElectronic = (AccessoryQuotedElectronic) map.get("accessoryQuotedElectronic");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Map<String, Object> newMap = new HashMap<String, Object>();
			newMap.put("enquiryCode", accessoryQuotedElectronic.getEnquiryCode());
			newMap.put("intentionSupplierCode", accessoryQuotedElectronic.getIntentionSupplierCode());
			newMap.put("quotedDate", format.format(accessoryQuotedElectronic.getQuotedDate()));
			List<AccessoryQuotedElectronic> accessoryQuotedElectronicList=accessoryQuotedElectronicService.listAccessoryQuotedElectronic(newMap, null);
			if(accessoryQuotedElectronicList!=null&&accessoryQuotedElectronicList.size()>0){
				accessoryQuotedMaterialService.deleteAccessoryQuotedMaterial(accessoryQuotedElectronicList.get(0).getQuotedCode());
				accessoryQuotedAccessoryService.deleteAccessoryQuotedAccessory(accessoryQuotedElectronicList.get(0).getQuotedCode());
				accessoryQuotedPackingService.deleteAccessoryQuotedPacking(accessoryQuotedElectronicList.get(0).getQuotedCode());
				accessoryQuotedTechnologyService.deleteAccessoryQuotedTechnology(accessoryQuotedElectronicList.get(0).getQuotedCode());
				accessoryQuotedElseService.deleteAccessoryQuotedElse(accessoryQuotedElectronicList.get(0).getQuotedCode());
				accessoryQuotedTotalService.deleteAccessoryQuotedTotalFromQuotedCode(accessoryQuotedElectronicList.get(0).getQuotedCode());
				accessoryQuotedElectronicService.deleteAccessoryQuotedElectronic(accessoryQuotedElectronicList.get(0).getQuotedCode());
			}
			accessoryQuotedElectronicService.insertAccessoryQuotedElectronic(accessoryQuotedElectronic);
			for (int i = 0; i < listAccessoryQuotedMaterial.size(); i++) {
				accessoryQuotedMaterialService.insertAccessoryQuotedMaterial(listAccessoryQuotedMaterial.get(i));
			}
			for (int i = 0; i < listAccessoryQuotedAccessory.size(); i++) {
				accessoryQuotedAccessoryService.insertAccessoryQuotedAccessory(listAccessoryQuotedAccessory.get(i));
			}
			for (int i = 0; i < listAccessoryQuotedPacking.size(); i++) {
				accessoryQuotedPackingService.insertAccessoryQuotedPacking(listAccessoryQuotedPacking.get(i));
			}
			for (int i = 0; i < listAccessoryQuotedTechnology.size(); i++) {
				accessoryQuotedTechnologyService.insertAccessoryQuotedTechnology(listAccessoryQuotedTechnology.get(i));
			}
			for (int i = 0; i < listAccessoryQuotedElse.size(); i++) {
				accessoryQuotedElseService.insertAccessoryQuotedElse(listAccessoryQuotedElse.get(i));
			}
			for (int i = 0; i < listAccessoryQuotedTotal.size(); i++) {
				accessoryQuotedTotalService.insertAccessoryQuotedTotal(listAccessoryQuotedTotal.get(i));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			check += "保存数据库错误";
		}
		// Map<String, Object> map = new HashMap<String, Object>();
		// 根据报价日期和供应商编号先删除报价信息
		// deleteMerchandiseQuoted(map);
		// insertMerchandiseQuoted(merchandiseQuoted.toMap());
		// insertQuotedTransactionControl(map, merchandiseQuoted);

		return check;
	}

	// 导出询价单
	@Override
	public void exportAccessoryEnquiryToExcel(Map<String, Object> map, ServletOutputStream out) {
		AccessoryIntention ai = new AccessoryIntention();
		ai = this.loadAccessoryIntention(map.get("intentionCode").toString());
		AccessoryEnquiry accessoryEnquiry = new AccessoryEnquiry();
		User user = PowerUtils.getCurrentUser();
		accessoryEnquiry = accessoryEnquiryService.loadAccessoryEnquiry(map.get("enquiryCode").toString());
		Map<String, Object> newMap = new HashMap<String, Object>();
		newMap.put("enquiryCode", map.get("enquiryCode"));
		List<AccessoryEnquiryMaterial> listAem = accessoryEnquiryMaterialService.listAccessoryEnquiryMaterial(newMap, null);
		List<AccessoryEnquiryAccessory> listAea = accessoryEnquiryAccessoryService.listAccessoryEnquiryAccessory(newMap, null);
		List<AccessoryEnquiryPacking> listAep = accessoryEnquiryPackingService.listAccessoryEnquiryPacking(newMap, null);
		List<AccessoryEnquiryTechnology> listAet = accessoryEnquiryTechnologyService.listAccessoryEnquiryTechnology(newMap, null);
		List<AccessoryEnquiryQuotedCount> listAeqc = accessoryEnquiryQuotedCountService.listAccessoryEnquiryQuotedCount(newMap, null);
		List<AccessoryEnquiryElse> listAee = accessoryEnquiryElseService.listAccessoryEnquiryElse(newMap, null);
		// List<AccessoryEnquiryElse> listAee =
		// accessoryEnquiryElseService.listAccessoryEnquiryElse(newMap, null);
		//Workbook wb = new XSSFWorkbook();
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("询价单");
		sheet.protectSheet("password");
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		ExcelUtils.setAlign(strStyle, "left", "center", true);
		CellStyle strStyleRed = ExcelUtils.getRedStringStyle(wb);// 红色字符串样式
		CellStyle strStyle1 = ExcelUtils.getDefaultStringStyle(wb);
		strStyle1.setLocked(false);
		// CellStyle dateStyle = ExcelUtils.getDefaultDateStyle(wb);// 字符串样式
		// CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式
		CellStyle strStyleCenter = ExcelUtils.getDefaultStringStyle(wb);// 字符串居中靠左样式
		ExcelUtils.setAlign(strStyleCenter, "left", "center", true);
		ExcelUtils.setAlign(strStyle1, "left", "center", true);
		CellStyle strStyleCenterCenter = ExcelUtils.getDefaultStringStyle(wb);// 合并居中样式
		ExcelUtils.setAlign(strStyleCenterCenter, "center", "center", true);
		CellStyle strStyleCenterCenterRed = ExcelUtils.getRedStringStyle(wb);// 合并居中样式
		ExcelUtils.setAlign(strStyleCenterCenterRed, "center", "center", true);
		XSSFFont redFont = (XSSFFont) wb.createFont();
		XSSFColor redColor = new XSSFColor(Color.RED);
		redFont.setFontName("微软雅黑"); 
		redFont.setColor(redColor);// 红色
		redFont.setFontName("微软雅黑");
		XSSFFont fontYH = (XSSFFont) wb.createFont();//创建字体对象  
		fontYH.setFontName("微软雅黑");
		Font font  = wb.createFont();      
		font.setFontHeightInPoints((short)20);//字号      
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);//加粗    
		font.setFontName("微软雅黑"); 
		strStyle.setFont(fontYH);
		strStyle1.setFont(fontYH);
		strStyleRed.setFont(redFont);
		strStyleCenterCenter.setFont(fontYH);
		strStyleCenter.setFont(fontYH);
		Row row = sheet.createRow(0);
		row.setHeight((short) (80 * 20));
		sheet.setColumnWidth(0, 80 * 80);
		sheet.setColumnWidth(1, 80 * 80);
		sheet.setColumnWidth(2, 80 * 80);
		sheet.setColumnWidth(3, 80 * 80);
		sheet.setColumnWidth(4, 80 * 80);
		sheet.setColumnWidth(5, 80 * 80);
		sheet.setColumnWidth(6, 80 * 80);
		sheet.setColumnWidth(7, 80 * 80);
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb, ExcelUtils.HEADR_FONT_SIZE);
		firstRowStyle.setWrapText(true);
		firstRowStyle.setFont(font);
		Cell cell = row.createCell(0);
		cell.setCellValue("上海来伊份股份有限公司 询价单\rShanghai LaiYiFen Co., Ltd. RFQ");
		cell.setCellStyle(firstRowStyle);
		for (int i = 1; i < 4; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "A1:D1");
		// 询价单编号
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue("询价单编号");
		cell.setCellStyle(strStyle);
		cell = row.createCell(1);
		cell.setCellValue(map.get("enquiryCode").toString());
		cell.setCellStyle(strStyle);
		for (int i = 2; i < 4; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(strStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "B2:D2");
		// 来一份信息
		Row row1 = sheet.createRow(2);
		cell = row1.createCell(0);
		cell.setCellValue("来伊份信息");
		CellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		ExcelUtils.setAlign(style, "center", "center", false);
		style.setFont(fontYH);
		CellStyle styleBlue = wb.createCellStyle();
		styleBlue.setFillForegroundColor(IndexedColors.TEAL.getIndex());
		styleBlue.setFillPattern(CellStyle.SOLID_FOREGROUND);
		ExcelUtils.setAlign(styleBlue, "center", "center", false);
		styleBlue.setFont(fontYH);
		cell.setCellStyle(style);
		for (int i = 1; i < 4; i++) {
			cell = row1.createCell(i);
			cell.setCellStyle(style);
		}
		ExcelUtils.addMergedRegion(sheet, "A3:D3");

		Row row2 = sheet.createRow(3);
		cell = row2.createCell(0);
		cell.setCellValue("公司名称");
		cell.setCellStyle(strStyle);
		cell = row2.createCell(1);
		cell.setCellValue("上海来伊份股份有限公司");
		cell.setCellStyle(strStyle);
		for (int i = 2; i < 4; i++) {
			cell = row2.createCell(i);
			cell.setCellStyle(strStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "B4:D4");

		Row row3 = sheet.createRow(4);
		cell = row3.createCell(0);
		cell.setCellValue("公司地址");
		cell.setCellStyle(strStyle);
		cell = row3.createCell(1);
		cell.setCellValue("上海市松江区沪松公路1399弄68号来伊份青年大厦");
		cell.setCellStyle(strStyle);
		for (int i = 2; i < 4; i++) {
			cell = row3.createCell(i);
			cell.setCellStyle(strStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "B5:D5");

		Row row4 = sheet.createRow(5);
		cell = row4.createCell(0);
		cell.setCellValue("联系人");
		cell.setCellStyle(strStyle);
		cell = row4.createCell(1);
		cell.setCellValue(user.getRealName());
		cell.setCellStyle(strStyle);
		cell = row4.createCell(2);
		cell.setCellValue("联系电话");
		cell.setCellStyle(strStyle);
		cell = row4.createCell(3);
		cell.setCellValue(user.getMobile() == null ? user.getPhone() : user.getMobile());
		cell.setCellStyle(strStyle);

		Row row5 = sheet.createRow(6);
		cell = row5.createCell(0);
		cell.setCellValue("联系人邮箱");
		cell.setCellStyle(strStyle);
		cell = row5.createCell(1);
		cell.setCellValue(user.getEmail());
		cell.setCellStyle(strStyle);
		cell = row5.createCell(2);
		cell.setCellValue("联系人传真号");
		cell.setCellStyle(strStyle);
		cell = row5.createCell(3);
		cell.setCellValue("");
		cell.setCellStyle(strStyle);

		// 意向品基础信息
		Row row6 = sheet.createRow(7);
		cell = row6.createCell(0);
		cell.setCellValue("意向品基础信息");
		cell.setCellStyle(style);
		for (int i = 1; i < 4; i++) {
			cell = row6.createCell(i);
			cell.setCellStyle(style);
		}
		ExcelUtils.addMergedRegion(sheet, "A8:D8");

		Row row7 = sheet.createRow(8);
		cell = row7.createCell(0);
		cell.setCellValue("意向品名称");
		cell.setCellStyle(strStyle);
		cell = row7.createCell(1);
		cell.setCellValue(ai.getIntentionName());
		cell.setCellStyle(strStyle);
		cell = row7.createCell(2);
		cell.setCellValue("意向品编号");
		cell.setCellStyle(strStyle);
		cell = row7.createCell(3);
		cell.setCellValue(ai.getIntentionCode());
		cell.setCellStyle(strStyle);

		Row row8 = sheet.createRow(9);
		cell = row8.createCell(0);
		cell.setCellValue("询价日期");
		cell.setCellStyle(strStyle);
		cell = row8.createCell(1);
		cell.setCellValue(DateUtils.formateDate());
		cell.setCellStyle(strStyle);
		cell = row8.createCell(2);
		cell.setCellValue("最晚报价日期");
		cell.setCellStyle(strStyle);
		cell = row8.createCell(3);
		cell.setCellValue(map.get("enquiryDate").toString());
		cell.setCellStyle(strStyle);

		Row row9 = sheet.createRow(10);
		cell = row9.createCell(0);
		cell.setCellValue("报价币种");
		cell.setCellStyle(strStyle);
		cell = row9.createCell(1);
		cell.setCellValue(accessoryEnquiry.getQuotedCurrency());
		cell.setCellStyle(strStyle);
		cell = row9.createCell(2);
		cell.setCellValue("要求到货日期");
		cell.setCellStyle(strStyle);
		cell = row9.createCell(3);
		cell.setCellValue(map.get("receiveDate")==null?"":map.get("receiveDate").toString());
		cell.setCellStyle(strStyle);

		Row row10 = sheet.createRow(11);
		cell = row10.createCell(0);
		cell.setCellValue("报价单位");
		cell.setCellStyle(strStyle);
		cell = row10.createCell(1);
		cell.setCellValue(accessoryEnquiry.getQuotedUnits());
		cell.setCellStyle(strStyle);
		cell = row10.createCell(2);
		cell.setCellValue("付款方式");
		cell.setCellStyle(strStyle);
		cell = row10.createCell(3);
		cell.setCellValue(accessoryEnquiry.getPaymentType());
		cell.setCellStyle(strStyle);

		Row row11 = sheet.createRow(12);
		cell = row11.createCell(0);
		cell.setCellValue("交货方式");
		cell.setCellStyle(strStyle);
		cell = row11.createCell(1);
		cell.setCellValue(accessoryEnquiry.getDeliveryType());
		cell.setCellStyle(strStyle);
		for (int i = 2; i < 4; i++) {
			cell = row11.createCell(i);
			cell.setCellStyle(strStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "B13:D13");

		Row row12 = sheet.createRow(13);
		cell = row12.createCell(0);
		cell.setCellValue("来伊份备注");
		cell.setCellStyle(strStyle);
		cell = row12.createCell(1);
		cell.setCellValue(accessoryEnquiry.getRemarks());
		cell.setCellStyle(strStyle);
		for (int i = 2; i < 4; i++) {
			cell = row12.createCell(i);
			cell.setCellStyle(strStyle);
		}
		if(accessoryEnquiry.getRemarks()!=null&&accessoryEnquiry.getRemarks().length()>38 )
		row12.setHeight((short) (20 * accessoryEnquiry.getRemarks().length()));
		ExcelUtils.addMergedRegion(sheet, "B14:D14");

		Row row13 = sheet.createRow(14);
		cell = row13.createCell(0);
		cell.setCellValue("其他要求");
		cell.setCellStyle(strStyle);
		String other = "";
		if (listAee != null && listAee.size() > 0) {
			for (int m = 0; m < listAee.size(); m++) {
				if (m == listAee.size() - 1) {
					other += listAee.get(m).getName() + ":" + listAee.get(m).getInfo();
				} else {
					other += listAee.get(m).getName() + ":" + listAee.get(m).getInfo() + ","+"\r";
				}
			}
			row13.setHeight((short) (20 * 20*(listAee.size()+1)));
		}
		cell = row13.createCell(1);
		cell.setCellValue(other);
		cell.setCellStyle(strStyle);
		for (int i = 2; i < 4; i++) {
			cell = row13.createCell(i);
			cell.setCellStyle(strStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "B15:D15");
		// 供应商信息
		Row row14 = sheet.createRow(15);
		cell = row14.createCell(0);
		cell.setCellValue("供应商信息");
		cell.setCellStyle(styleBlue);
		for (int i = 1; i < 4; i++) {
			cell = row14.createCell(i);
			cell.setCellStyle(styleBlue);
		}
		ExcelUtils.addMergedRegion(sheet, "A16:D16");

		Row row15 = sheet.createRow(16);
		cell = row15.createCell(0);
	//	XSSFRichTextString richString = new XSSFRichTextString("*公司名称");
	//	richString.applyFont(0, 1, redFont);
	//	cell.setCellValue(richString);
		cell.setCellValue("公司名称");
		cell.setCellStyle(strStyleRed);
		cell = row15.createCell(1);
		cell.setCellValue("");
		cell.setCellStyle(strStyle1);
		for (int i = 2; i < 4; i++) {
			cell = row15.createCell(i);
			cell.setCellStyle(strStyle1);
		}
		ExcelUtils.addMergedRegion(sheet, "B17:D17");

		Row row16 = sheet.createRow(17);
		cell = row16.createCell(0);
		/*XSSFRichTextString richString1 = new XSSFRichTextString("*公司地址");
		richString1.applyFont(0, 1, redFont);
		cell.setCellValue(richString1);*/
		cell.setCellValue("公司地址");
		cell.setCellStyle(strStyleRed);
		cell = row16.createCell(1);
		cell.setCellValue("");
		cell.setCellStyle(strStyle1);
		for (int i = 2; i < 4; i++) {
			cell = row16.createCell(i);
			cell.setCellStyle(strStyle1);
		}
		ExcelUtils.addMergedRegion(sheet, "B18:D18");

		Row row17 = sheet.createRow(18);
		cell = row17.createCell(0);
		/*XSSFRichTextString richString2 = new XSSFRichTextString("*工厂地址");
		richString2.applyFont(0, 1, redFont);
		cell.setCellValue(richString2);*/
		cell.setCellValue("工厂地址");
		cell.setCellStyle(strStyleRed);
		cell = row17.createCell(1);
		cell.setCellValue("");
		cell.setCellStyle(strStyle1);
		for (int i = 2; i < 4; i++) {
			cell = row17.createCell(i);
			cell.setCellStyle(strStyle1);
		}
		ExcelUtils.addMergedRegion(sheet, "B19:D19");

		Row row18 = sheet.createRow(19);
		cell = row18.createCell(0);
		/*XSSFRichTextString richString3 = new XSSFRichTextString("*供应商编号");
		richString3.applyFont(0, 1, redFont);
		cell.setCellValue(richString3);*/
		cell.setCellValue("供应商编号");
		cell.setCellStyle(strStyleRed);
		cell = row18.createCell(1);
		cell.setCellValue(map.get("supplierCode") == null ? null : map.get("supplierCode").toString());
		cell.setCellStyle(strStyle);
		for (int i = 2; i < 4; i++) {
			cell = row18.createCell(i);
			cell.setCellStyle(strStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "B20:D20");

		Row row19 = sheet.createRow(20);
		cell = row19.createCell(0);
		/*XSSFRichTextString richString4 = new XSSFRichTextString("*发票类型 (增/普票)");
		richString4.applyFont(0, 1, redFont);
		cell.setCellValue(richString4);*/
		cell.setCellValue("发票类型 (增/普票)");
		cell.setCellStyle(strStyleRed);
		cell = row19.createCell(1);
		cell.setCellValue("");
		cell.setCellStyle(strStyle1);
		cell = row19.createCell(2);
		/*XSSFRichTextString richString5 = new XSSFRichTextString("*税率(%)");
		richString5.applyFont(0, 1, redFont);
		cell.setCellValue(richString5);*/
		cell.setCellValue("税率(%)");
		cell.setCellStyle(strStyleRed);
		cell = row19.createCell(3);
		cell.setCellValue("");
		cell.setCellStyle(strStyle1);

		Row row20 = sheet.createRow(21);
		cell = row20.createCell(0);
		/*XSSFRichTextString richString6 = new XSSFRichTextString("*联系人");
		richString6.applyFont(0, 1, redFont);
		cell.setCellValue(richString6);*/
		cell.setCellValue("联系人");
		cell.setCellStyle(strStyleRed);
		cell = row20.createCell(1);
		cell.setCellValue("");
		cell.setCellStyle(strStyle1);
		cell = row20.createCell(2);
		/*XSSFRichTextString richString7 = new XSSFRichTextString("*联系电话");
		richString7.applyFont(0, 1, redFont);
		cell.setCellValue(richString7);*/
		cell.setCellValue("联系电话");
		cell.setCellStyle(strStyleRed);
		cell = row20.createCell(3);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("");
		cell.setCellStyle(strStyle1);

		Row row21 = sheet.createRow(22);
		cell = row21.createCell(0);
		cell.setCellValue("联系人邮箱");
		cell.setCellStyle(strStyle);
		cell = row21.createCell(1);
		cell.setCellValue("");
		cell.setCellStyle(strStyle1);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell = row21.createCell(2);
		cell.setCellValue("联系人传真号");
		cell.setCellStyle(strStyle);
		cell = row21.createCell(3);
		cell.setCellValue("");
		cell.setCellStyle(strStyle1);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);

		Row row22 = sheet.createRow(23);
		cell = row22.createCell(0);
		/*XSSFRichTextString richString8 = new XSSFRichTextString("*注册资本");
		richString8.applyFont(0, 1, redFont);
		cell.setCellValue(richString8);*/
		cell.setCellValue("注册资本(万元)");
		cell.setCellStyle(strStyleRed);
		cell = row22.createCell(1);
		cell.setCellValue("");
		cell.setCellStyle(strStyle1);
		cell = row22.createCell(2);
		/*XSSFRichTextString richString9 = new XSSFRichTextString("*年营业额");
		richString9.applyFont(0, 1, redFont);
		cell.setCellValue(richString9);*/
		cell.setCellValue("年营业额(万元)");
		cell.setCellStyle(strStyleRed);
		cell = row22.createCell(3);
		cell.setCellValue("");
		cell.setCellStyle(strStyle1);

		Row row23 = sheet.createRow(24);
		cell = row23.createCell(0);
		/*XSSFRichTextString richString10 = new XSSFRichTextString("*工厂面积(㎡)");
		richString10.applyFont(0, 1, redFont);
		cell.setCellValue(richString10);*/
		cell.setCellValue("工厂面积(㎡)");
		cell.setCellStyle(strStyleRed);
		cell = row23.createCell(1);
		cell.setCellValue("");
		cell.setCellStyle(strStyle1);
		cell = row23.createCell(2);
		/*XSSFRichTextString richString11 = new XSSFRichTextString("*工人数");
		richString11.applyFont(0, 1, redFont);
		cell.setCellValue(richString11);*/
		cell.setCellValue("工人数");
		cell.setCellStyle(strStyleRed);
		cell = row23.createCell(3);
		cell.setCellValue("");
		cell.setCellStyle(strStyle1);

		Row row24 = sheet.createRow(25);
		cell = row24.createCell(0);
		/*XSSFRichTextString richString12 = new XSSFRichTextString("*合作过品牌");
		richString12.applyFont(0, 1, redFont);
		cell.setCellValue(richString12);*/
		cell.setCellValue("合作过品牌");
		cell.setCellStyle(strStyleRed);
		cell = row24.createCell(1);
		cell.setCellValue("");
		cell.setCellStyle(strStyle1);
		for (int i = 2; i < 4; i++) {
			cell = row24.createCell(i);
			cell.setCellStyle(strStyle1);
		}
		ExcelUtils.addMergedRegion(sheet, "B26:D26");

		Row row25 = sheet.createRow(26);
		cell = row25.createCell(0);
		/*XSSFRichTextString richString13 = new XSSFRichTextString("*付款方式");
		richString13.applyFont(0, 1, redFont);
		cell.setCellValue(richString13);*/
		cell.setCellValue("付款方式");
		cell.setCellStyle(strStyleRed);
		cell = row25.createCell(1);
		cell.setCellValue("");
		cell.setCellStyle(strStyle1);
		for (int i = 2; i < 4; i++) {
			cell = row25.createCell(i);
			cell.setCellStyle(strStyle1);
		}
		ExcelUtils.addMergedRegion(sheet, "B27:D27");

		Row row26 = sheet.createRow(27);
		cell = row26.createCell(0);
		cell.setCellValue("交货方式");
		cell.setCellStyle(strStyle);
		cell = row26.createCell(1);
		cell.setCellValue("");
		cell.setCellStyle(strStyle1);
		for (int i = 2; i < 4; i++) {
			cell = row26.createCell(i);
			cell.setCellStyle(strStyle1);
		}
		ExcelUtils.addMergedRegion(sheet, "B28:D28");

		// 原材料信息
		Row row27 = sheet.createRow(28);
		cell = row27.createCell(0);
		cell.setCellValue("原材料信息");
		cell.setCellStyle(style);
		for (int i = 1; i < 6; i++) {
			cell = row27.createCell(i);
			cell.setCellStyle(style);
		}
		ExcelUtils.addMergedRegion(sheet, "A29:H29");

		Row row28 = sheet.createRow(29);
		cell = row28.createCell(0);
		cell.setCellValue("原材料编号");
		cell.setCellStyle(strStyle);
		cell = row28.createCell(1);
		cell.setCellValue("原材料名称");
		cell.setCellStyle(strStyle);
		cell = row28.createCell(2);
		cell.setCellValue("材料描述");
		cell.setCellStyle(strStyleCenterCenter);
		cell = row28.createCell(3);
		cell.setCellStyle(strStyleCenterCenter);
		ExcelUtils.addMergedRegion(sheet, "C30:D30");
		cell = row28.createCell(4);
		cell.setCellValue("尺寸");
		cell.setCellStyle(strStyle);
		cell = row28.createCell(5);
		cell.setCellValue("品牌");
		cell.setCellStyle(strStyleCenterCenter);
		cell = row28.createCell(6);
		cell.setCellStyle(strStyleCenterCenter);
		ExcelUtils.addMergedRegion(sheet, "F30:G30");
		cell = row28.createCell(7);
		cell.setCellValue("原产地");
		cell.setCellStyle(strStyle);
		int rowIndex = 30;
		Row rowX = sheet.createRow(rowIndex);
		for (int i = 0; i < listAem.size(); i++) {
			AccessoryEnquiryMaterial aem = null;
			rowX = sheet.createRow(rowIndex++);
			aem = listAem.get(i);
			cell = rowX.createCell(0);
			cell.setCellValue(aem.getMaterialCode());
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(1);
			cell.setCellValue(aem.getMaterialName());
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(2);
			//cell.setCellValue(aem.getMaterial());
			if(aem.getMaterial()!=null&&aem.getMaterial().length()>21){
				rowX.setHeight((short) (aem.getMaterial().length() * 30));
				}
			cell.setCellValue(aem.getMaterial());
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(3);
			cell.setCellStyle(strStyle);
			ExcelUtils.addMergedRegion(sheet, "C" + (rowIndex) + ":" + "D" + (rowIndex));
			cell = rowX.createCell(4);
			if(aem.getMaterialSize()!=null&&aem.getMaterial().length()<aem.getMaterialSize().length()&&aem.getMaterial().length()>21){
				rowX.setHeight((short) (aem.getMaterialSize().length() * 30));
				}
			cell.setCellValue(aem.getMaterialSize());
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(5);
			cell.setCellValue("");
			cell.setCellStyle(strStyle1);
			cell = rowX.createCell(6);
			cell.setCellStyle(strStyle1);
			ExcelUtils.addMergedRegion(sheet, "F" + (rowIndex) + ":" + "G" + (rowIndex));
			cell = rowX.createCell(7);
			cell.setCellValue("");
			cell.setCellStyle(strStyle1);
		}
		// 原材料报价
		rowX = sheet.createRow(rowIndex++);
		cell = rowX.createCell(0);
		cell.setCellValue("原材料报价");
		cell.setCellStyle(styleBlue);
		for (int i = 1; i < 8; i++) {
			cell = rowX.createCell(i);
			cell.setCellStyle(styleBlue);
		}
		ExcelUtils.addMergedRegion(sheet, "A" + (rowIndex) + ":" + "H" + (rowIndex));

		for (int i = 0; i < listAeqc.size(); i++) {
			AccessoryEnquiryQuotedCount aeqc = null;
			aeqc = listAeqc.get(i);
			rowX = sheet.createRow(rowIndex++);
			cell = rowX.createCell(0);
			cell.setCellValue("订购数量");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(1);
			cell.setCellValue("原材料编号");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(2);
			cell.setCellValue("原材料名称");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(3);
			cell.setCellValue("价格单位");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(4);
			cell.setCellValue("价格");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(5);
			cell.setCellValue("用料量");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(6);
			cell.setCellValue("用料损耗");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(7);
			cell.setCellValue("单位商品成本");
			cell.setCellStyle(strStyleRed);
			for (int k = 0; k < listAem.size(); k++) {
				AccessoryEnquiryMaterial aem = null;
				rowX = sheet.createRow(rowIndex++);
				aem = listAem.get(k);
				cell = rowX.createCell(0);
				cell.setCellValue(formatNumber(aeqc.getQuotedCount()));
				cell.setCellStyle(strStyleCenter);
				cell = rowX.createCell(1);
				cell.setCellValue(aem.getMaterialCode());
				cell.setCellStyle(strStyle);
				cell = rowX.createCell(2);
				cell.setCellValue(aem.getMaterialName());
				cell.setCellStyle(strStyle);
				cell = rowX.createCell(3);
				cell.setCellValue("");
				cell.setCellStyle(strStyle1);
				cell = rowX.createCell(4);
				cell.setCellValue("");
				cell.setCellStyle(strStyle1);
				cell = rowX.createCell(5);
				cell.setCellValue("");
				cell.setCellStyle(strStyle1);
				cell = rowX.createCell(6);
				cell.setCellValue("");
				cell.setCellStyle(strStyle1);
				cell = rowX.createCell(7);
				cell.setCellValue("");
				cell.setCellStyle(strStyle1);
			}
			ExcelUtils.addMergedRegion(sheet, "A" + (rowIndex - listAem.size() + 1) + ":" + "A" + (rowIndex));
		}

		// 辅料信息
		if(listAea.size()>0){
		rowX = sheet.createRow(rowIndex++);
		cell = rowX.createCell(0);
		cell.setCellValue("辅料信息");
		cell.setCellStyle(style);
		for (int i = 1; i < 8; i++) {
			cell = rowX.createCell(i);
			cell.setCellStyle(style);
		}
		ExcelUtils.addMergedRegion(sheet, "A" + (rowIndex) + ":" + "H" + (rowIndex));

		rowX = sheet.createRow(rowIndex++);
		cell = rowX.createCell(0);
		cell.setCellValue("辅料序号");
		cell.setCellStyle(strStyle);
		cell = rowX.createCell(1);
		cell.setCellValue("辅料名称");
		cell.setCellStyle(strStyle);
		cell = rowX.createCell(2);
		cell.setCellValue("材料描述");
		cell.setCellStyle(strStyleCenterCenter);
		cell = rowX.createCell(3);
		cell.setCellStyle(strStyleCenterCenter);
		ExcelUtils.addMergedRegion(sheet, "C" + (rowIndex) + ":" + "D" + (rowIndex));
		cell = rowX.createCell(4);
		cell.setCellValue("尺寸");
		cell.setCellStyle(strStyle);
		cell = rowX.createCell(5);
		cell.setCellValue("品牌");
		cell.setCellStyle(strStyleCenterCenter);
		cell = rowX.createCell(6);
		cell.setCellStyle(strStyleCenterCenter);
		ExcelUtils.addMergedRegion(sheet, "F" + (rowIndex) + ":" + "G" + (rowIndex));
		cell = rowX.createCell(7);
		cell.setCellValue("原产地");
		cell.setCellStyle(strStyle);
		for (int i = 0; i < listAea.size(); i++) {
			AccessoryEnquiryAccessory aea = null;
			rowX = sheet.createRow(rowIndex++);
			aea = listAea.get(i);
			cell = rowX.createCell(0);
			cell.setCellValue(aea.getAccessoryCode());
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(1);
			cell.setCellValue(aea.getAccessoryName());
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(2);
			/*if(aea.getMaterial().length()>21){
				rowX.setHeight((short) (20 * 40));
				}*/
			if(aea.getMaterial()!=null&&aea.getMaterial().length()>21){
				rowX.setHeight((short) (aea.getMaterial().length() * 30));
				}
			cell.setCellValue(aea.getMaterial());	
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(3);
			cell.setCellStyle(strStyle);
			ExcelUtils.addMergedRegion(sheet, "C" + (rowIndex) + ":" + "D" + (rowIndex));
			cell = rowX.createCell(4);
			if(aea.getMaterialSize()!=null&&aea.getMaterial().length()<aea.getMaterialSize().length()&&aea.getMaterial().length()>21){
				rowX.setHeight((short) (aea.getMaterialSize().length() * 30));
				}
			cell.setCellValue(aea.getMaterialSize());
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(5);
			cell.setCellValue("");
			cell.setCellStyle(strStyle1);
			cell = rowX.createCell(6);
			cell.setCellStyle(strStyle1);
			ExcelUtils.addMergedRegion(sheet, "F" + (rowIndex) + ":" + "G" + (rowIndex));
			cell = rowX.createCell(7);
			cell.setCellValue("");
			cell.setCellStyle(strStyle1);
		}
		// 辅料报价
		rowX = sheet.createRow(rowIndex++);
		cell = rowX.createCell(0);
		cell.setCellValue("辅料报价");
		cell.setCellStyle(styleBlue);
		for (int i = 1; i < 8; i++) {
			cell = rowX.createCell(i);
			cell.setCellStyle(styleBlue);
		}
		ExcelUtils.addMergedRegion(sheet, "A" + (rowIndex) + ":" + "H" + (rowIndex));

		for (int i = 0; i < listAeqc.size(); i++) {
			AccessoryEnquiryQuotedCount aeqc = null;
			aeqc = listAeqc.get(i);
			rowX = sheet.createRow(rowIndex++);
			cell = rowX.createCell(0);
			cell.setCellValue("订购数量");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(1);
			cell.setCellValue("辅料编号");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(2);
			cell.setCellValue("辅料名称");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(3);
			cell.setCellValue("价格单位");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(4);
			cell.setCellValue("价格");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(5);
			cell.setCellValue("用料量");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(6);
			cell.setCellValue("用料损耗");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(7);
			cell.setCellValue("单位商品成本");
			cell.setCellStyle(strStyleRed);
			for (int k = 0; k < listAea.size(); k++) {
				AccessoryEnquiryAccessory aea = null;
				rowX = sheet.createRow(rowIndex++);
				aea = listAea.get(k);
				cell = rowX.createCell(0);
				cell.setCellValue(formatNumber(aeqc.getQuotedCount()));
				cell.setCellStyle(strStyleCenter);
				cell = rowX.createCell(1);
				cell.setCellValue(aea.getAccessoryCode());
				cell.setCellStyle(strStyle);
				cell = rowX.createCell(2);
				cell.setCellValue(aea.getAccessoryName());
				cell.setCellStyle(strStyle);
				cell = rowX.createCell(3);
				cell.setCellValue("");
				cell.setCellStyle(strStyle1);
				cell = rowX.createCell(4);
				cell.setCellValue("");
				cell.setCellStyle(strStyle1);
				cell = rowX.createCell(5);
				cell.setCellValue("");
				cell.setCellStyle(strStyle1);
				cell = rowX.createCell(6);
				cell.setCellValue("");
				cell.setCellStyle(strStyle1);
				cell = rowX.createCell(7);
				cell.setCellValue("");
				cell.setCellStyle(strStyle1);
			}
			if (listAea.size() > 1) {
				ExcelUtils.addMergedRegion(sheet, "A" + (rowIndex - listAea.size() + 1) + ":" + "A" + (rowIndex));
			}
		}
		}
		// 内外包装材料信息
		
		rowX = sheet.createRow(rowIndex++);
		cell = rowX.createCell(0);
		cell.setCellValue("内外包装材料信息");
		cell.setCellStyle(style);
		for (int i = 1; i < 8; i++) {
			cell = rowX.createCell(i);
			cell.setCellStyle(style);
		}
		ExcelUtils.addMergedRegion(sheet, "A" + (rowIndex) + ":" + "H" + (rowIndex));

		rowX = sheet.createRow(rowIndex++);
		cell = rowX.createCell(0);
		cell.setCellValue("内外包装材料编号");
		cell.setCellStyle(strStyle);
		cell = rowX.createCell(1);
		cell.setCellValue("内外包装材料名称");
		cell.setCellStyle(strStyle);
		cell = rowX.createCell(2);
		cell.setCellValue("材料描述");
		cell.setCellStyle(strStyleCenterCenter);
		cell = rowX.createCell(3);
		cell.setCellStyle(strStyleCenterCenter);
		ExcelUtils.addMergedRegion(sheet, "C" + (rowIndex) + ":" + "D" + (rowIndex));
		cell = rowX.createCell(4);
		cell.setCellValue("尺寸");
		cell.setCellStyle(strStyle);
		cell = rowX.createCell(5);
		cell.setCellValue("品牌");
		cell.setCellStyle(strStyleCenterCenter);
		cell = rowX.createCell(6);
		cell.setCellStyle(strStyleCenterCenter);
		ExcelUtils.addMergedRegion(sheet, "F" + (rowIndex) + ":" + "G" + (rowIndex));
		cell = rowX.createCell(7);
		cell.setCellValue("原产地");
		cell.setCellStyle(strStyle);
		for (int i = 0; i < listAep.size(); i++) {
			AccessoryEnquiryPacking aep = null;
			rowX = sheet.createRow(rowIndex++);
			aep = listAep.get(i);
			cell = rowX.createCell(0);
			cell.setCellValue(aep.getPackingCode());
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(1);
			cell.setCellValue(aep.getPackingName());
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(2);
			//cell.setCellValue(aep.getPackingMaterial());
			/*if(aep.getPackingMaterial().length()>21){
				rowX.setHeight((short) (20 * 40));
				}*/
			if(aep.getPackingMaterial()!=null&&aep.getPackingMaterial().length()>21){
				rowX.setHeight((short) (aep.getPackingMaterial().length() * 30));
				}
			cell.setCellValue(aep.getPackingMaterial());
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(3);
			cell.setCellStyle(strStyle);
			ExcelUtils.addMergedRegion(sheet, "C" + (rowIndex) + ":" + "D" + (rowIndex));
			cell = rowX.createCell(4);
			if(aep.getMaterialSize()!=null&&aep.getPackingMaterial().length()<aep.getMaterialSize().length()&&aep.getPackingMaterial().length()>21){
				rowX.setHeight((short) (aep.getMaterialSize().length() * 30));
				}
			cell.setCellValue(aep.getMaterialSize());
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(5);
			cell.setCellValue("");
			cell.setCellStyle(strStyle1);
			cell = rowX.createCell(6);
			cell.setCellStyle(strStyle1);
			ExcelUtils.addMergedRegion(sheet, "F" + (rowIndex) + ":" + "G" + (rowIndex));
			cell = rowX.createCell(7);
			cell.setCellValue("");
			cell.setCellStyle(strStyle1);
		}
		// 内外包装材料报价
		rowX = sheet.createRow(rowIndex++);
		cell = rowX.createCell(0);
		cell.setCellValue("内外包装材料报价");
		cell.setCellStyle(styleBlue);
		for (int i = 1; i < 8; i++) {
			cell = rowX.createCell(i);
			cell.setCellStyle(styleBlue);
		}
		ExcelUtils.addMergedRegion(sheet, "A" + (rowIndex) + ":" + "H" + (rowIndex));

		for (int i = 0; i < listAeqc.size(); i++) {
			AccessoryEnquiryQuotedCount aeqc = null;
			aeqc = listAeqc.get(i);
			rowX = sheet.createRow(rowIndex++);
			cell = rowX.createCell(0);
			cell.setCellValue("订购数量");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(1);
			cell.setCellValue("内外包装材料编号");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(2);
			cell.setCellValue("内外包装材料名称");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(3);
			cell.setCellValue("价格单位");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(4);
			cell.setCellValue("价格");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(5);
			cell.setCellValue("用料量");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(6);
			cell.setCellValue("用料损耗");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(7);
			cell.setCellValue("单位商品成本");
			cell.setCellStyle(strStyleRed);
			for (int k = 0; k < listAep.size(); k++) {
				AccessoryEnquiryPacking aep = null;
				rowX = sheet.createRow(rowIndex++);
				aep = listAep.get(k);
				cell = rowX.createCell(0);
				cell.setCellValue(formatNumber(aeqc.getQuotedCount()));
				cell.setCellStyle(strStyleCenter);
				cell = rowX.createCell(1);
				cell.setCellValue(aep.getPackingCode());
				cell.setCellStyle(strStyle);
				cell = rowX.createCell(2);
				cell.setCellValue(aep.getPackingName());
				cell.setCellStyle(strStyle);
				cell = rowX.createCell(3);
				cell.setCellValue("");
				cell.setCellStyle(strStyle1);
				cell = rowX.createCell(4);
				cell.setCellValue("");
				cell.setCellStyle(strStyle1);
				cell = rowX.createCell(5);
				cell.setCellValue("");
				cell.setCellStyle(strStyle1);
				cell = rowX.createCell(6);
				cell.setCellValue("");
				cell.setCellStyle(strStyle1);
				cell = rowX.createCell(7);
				cell.setCellValue("");
				cell.setCellStyle(strStyle1);
			}
			ExcelUtils.addMergedRegion(sheet, "A" + (rowIndex - listAep.size() + 1) + ":" + "A" + (rowIndex));
		}

		// 工艺信息
		if(listAet.size()>0){
		rowX = sheet.createRow(rowIndex++);
		cell = rowX.createCell(0);
		cell.setCellValue("工艺信息");
		cell.setCellStyle(style);
		for (int i = 1; i < 8; i++) {
			cell = rowX.createCell(i);
			cell.setCellStyle(style);
		}
		ExcelUtils.addMergedRegion(sheet, "A" + (rowIndex) + ":" + "H" + (rowIndex));

		rowX = sheet.createRow(rowIndex++);
		cell = rowX.createCell(0);
		cell.setCellValue("工艺编号");
		cell.setCellStyle(strStyle);
		cell = rowX.createCell(1);
		cell.setCellValue("工艺名称");
		cell.setCellStyle(strStyle);
		cell = rowX.createCell(2);
		cell.setCellValue("工艺说明");
		cell.setCellStyle(strStyleCenterCenter);
		for (int i = 3; i < 8; i++) {
			cell = rowX.createCell(i);
			cell.setCellStyle(strStyleCenterCenter);
		}
		ExcelUtils.addMergedRegion(sheet, "C" + (rowIndex) + ":" + "H" + (rowIndex));
		for (int i = 0; i < listAet.size(); i++) {
			AccessoryEnquiryTechnology aet = null;
			rowX = sheet.createRow(rowIndex++);
			aet = listAet.get(i);
			cell = rowX.createCell(0);
			cell.setCellValue(i + 1);
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(1);
			cell.setCellValue(aet.getTechnologyName());
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(2);
			cell.setCellValue(aet.getTechnologyInfo());
			cell.setCellStyle(strStyle);
			for (int m = 3; m < 8; m++) {
				cell = rowX.createCell(m);
				cell.setCellStyle(strStyleCenterCenter);
			}
			if(aet.getTechnologyInfo().length()>50){
				rowX.setHeight((short) (20 * 40));
				}
			if(aet.getTechnologyInfo().length()>100){
				rowX.setHeight((short) (40 * 40));
				}
			ExcelUtils.addMergedRegion(sheet, "C" + (rowIndex) + ":" + "H" + (rowIndex));
		}
		// 工艺报价
		rowX = sheet.createRow(rowIndex++);
		cell = rowX.createCell(0);
		cell.setCellValue("工艺报价");
		cell.setCellStyle(styleBlue);
		for (int i = 1; i < 8; i++) {
			cell = rowX.createCell(i);
			cell.setCellStyle(styleBlue);
		}
		ExcelUtils.addMergedRegion(sheet, "A" + (rowIndex) + ":" + "H" + (rowIndex));

		for (int i = 0; i < listAeqc.size(); i++) {
			AccessoryEnquiryQuotedCount aeqc = null;
			aeqc = listAeqc.get(i);
			rowX = sheet.createRow(rowIndex++);
			cell = rowX.createCell(0);
			cell.setCellValue("订购数量");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(1);
			cell.setCellValue("工艺编号");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(2);
			cell.setCellValue("工艺名称");
			cell.setCellStyle(strStyleCenterCenter);
			for (int m = 3; m < 6; m++) {
				cell = rowX.createCell(m);
				cell.setCellStyle(strStyleCenterCenter);
			}
			cell = rowX.createCell(6);
			cell.setCellValue("价格单位");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(7);
			cell.setCellValue("单位商品成本");
			cell.setCellStyle(strStyleRed);
			ExcelUtils.addMergedRegion(sheet, "C" + (rowIndex) + ":" + "F" + (rowIndex));
			for (int k = 0; k < listAet.size(); k++) {
				AccessoryEnquiryTechnology aet = null;
				rowX = sheet.createRow(rowIndex++);
				aet = listAet.get(k);
				cell = rowX.createCell(0);
				cell.setCellValue(formatNumber(aeqc.getQuotedCount()));
				cell.setCellStyle(strStyleCenter);
				cell = rowX.createCell(1);
				cell.setCellValue(aet.getTechnologyCode());
				cell.setCellStyle(strStyleCenter);
				cell = rowX.createCell(2);
				cell.setCellValue(aet.getTechnologyName());
				cell.setCellStyle(strStyleCenterCenter);
				for (int m = 3; m < 6; m++) {
					cell = rowX.createCell(m);
					cell.setCellStyle(strStyleCenterCenter);
				}
				cell = rowX.createCell(6);
				cell.setCellValue("");
				cell.setCellStyle(strStyle1);
				cell = rowX.createCell(7);
				cell.setCellValue("");
				cell.setCellStyle(strStyle1);
				ExcelUtils.addMergedRegion(sheet, "C" + (rowIndex) + ":" + "F" + (rowIndex));
			}
			if (listAet.size() > 1) {
				ExcelUtils.addMergedRegion(sheet, "A" + (rowIndex - listAet.size() + 1) + ":" + "A" + (rowIndex));
			}
		}
		}
		// 其他成本报价
		rowX = sheet.createRow(rowIndex++);
		cell = rowX.createCell(0);
		cell.setCellValue("其他成本报价");
		cell.setCellStyle(style);
		for (int i = 1; i < 8; i++) {
			cell = rowX.createCell(i);
			cell.setCellStyle(style);
		}
		ExcelUtils.addMergedRegion(sheet, "A" + (rowIndex) + ":" + "H" + (rowIndex));

		for (int i = 0; i < listAeqc.size(); i++) {
			AccessoryEnquiryQuotedCount aeqc = null;
			aeqc = listAeqc.get(i);
			rowX = sheet.createRow(rowIndex++);
			cell = rowX.createCell(0);
			cell.setCellValue("订购数量");
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(1);
			cell.setCellValue("费用类型");
			cell.setCellStyle(strStyleCenterCenter);
			for (int m = 2; m < 7; m++) {
				cell = rowX.createCell(m);
				cell.setCellStyle(strStyleCenterCenter);
			}
			cell = rowX.createCell(7);
			cell.setCellValue("单位商品成本");
			cell.setCellStyle(strStyleRed);
			ExcelUtils.addMergedRegion(sheet, "B" + (rowIndex) + ":" + "G" + (rowIndex));

			rowX = sheet.createRow(rowIndex++);
			cell = rowX.createCell(0);
			cell.setCellValue(formatNumber(aeqc.getQuotedCount()));
			cell.setCellStyle(strStyleCenter);
			cell = rowX.createCell(1);
			cell.setCellValue("打样费");
			cell.setCellStyle(strStyleCenterCenter);
			for (int m = 2; m < 7; m++) {
				cell = rowX.createCell(m);
				cell.setCellStyle(strStyleCenterCenter);
			}
			cell = rowX.createCell(7);
			cell.setCellValue("");
			cell.setCellStyle(strStyle1);
			ExcelUtils.addMergedRegion(sheet, "B" + (rowIndex) + ":" + "G" + (rowIndex));

			rowX = sheet.createRow(rowIndex++);
			cell = rowX.createCell(0);
			cell.setCellValue(formatNumber(aeqc.getQuotedCount()));
			cell.setCellStyle(strStyleCenter);
			cell = rowX.createCell(1);
			cell.setCellValue("人工");
			cell.setCellStyle(strStyleCenterCenter);
			for (int m = 2; m < 7; m++) {
				cell = rowX.createCell(m);
				cell.setCellStyle(strStyleCenterCenter);
			}
			cell = rowX.createCell(7);
			cell.setCellValue("");
			cell.setCellStyle(strStyle1);
			ExcelUtils.addMergedRegion(sheet, "B" + (rowIndex) + ":" + "G" + (rowIndex));

			rowX = sheet.createRow(rowIndex++);
			cell = rowX.createCell(0);
			cell.setCellValue(formatNumber(aeqc.getQuotedCount()));
			cell.setCellStyle(strStyleCenter);
			cell = rowX.createCell(1);
			cell.setCellValue("运输");
			cell.setCellStyle(strStyleCenterCenter);
			for (int m = 2; m < 7; m++) {
				cell = rowX.createCell(m);
				cell.setCellStyle(strStyleCenterCenter);
			}
			cell = rowX.createCell(7);
			cell.setCellValue("");
			cell.setCellStyle(strStyle1);
			ExcelUtils.addMergedRegion(sheet, "B" + (rowIndex) + ":" + "G" + (rowIndex));

			rowX = sheet.createRow(rowIndex++);
			cell = rowX.createCell(0);
			cell.setCellValue(formatNumber(aeqc.getQuotedCount()));
			cell.setCellStyle(strStyleCenter);
			cell = rowX.createCell(1);
			cell.setCellValue("管理");
			cell.setCellStyle(strStyleCenterCenter);
			for (int m = 2; m < 7; m++) {
				cell = rowX.createCell(m);
				cell.setCellStyle(strStyleCenterCenter);
			}
			cell = rowX.createCell(7);
			cell.setCellValue("");
			cell.setCellStyle(strStyle1);
			ExcelUtils.addMergedRegion(sheet, "B" + (rowIndex) + ":" + "G" + (rowIndex));

			rowX = sheet.createRow(rowIndex++);
			cell = rowX.createCell(0);
			cell.setCellValue(formatNumber(aeqc.getQuotedCount()));
			cell.setCellStyle(strStyleCenter);
			cell = rowX.createCell(1);
			cell.setCellValue("税额");
			cell.setCellStyle(strStyleCenterCenter);
			for (int m = 2; m < 7; m++) {
				cell = rowX.createCell(m);
				cell.setCellStyle(strStyleCenterCenter);
			}
			cell = rowX.createCell(7);
			cell.setCellValue("");
			cell.setCellStyle(strStyle1);
			ExcelUtils.addMergedRegion(sheet, "B" + (rowIndex) + ":" + "G" + (rowIndex));

			rowX = sheet.createRow(rowIndex++);
			cell = rowX.createCell(0);
			cell.setCellValue(formatNumber(aeqc.getQuotedCount()));
			cell.setCellStyle(strStyleCenter);
			cell = rowX.createCell(1);
			cell.setCellValue("税额占比(%)");
			cell.setCellStyle(strStyleCenterCenter);
			for (int m = 2; m < 7; m++) {
				cell = rowX.createCell(m);
				cell.setCellStyle(strStyleCenterCenter);
			}
			cell = rowX.createCell(7);
			cell.setCellValue("");
			cell.setCellStyle(strStyle1);
			ExcelUtils.addMergedRegion(sheet, "B" + (rowIndex) + ":" + "G" + (rowIndex));

			rowX = sheet.createRow(rowIndex++);
			cell = rowX.createCell(0);
			cell.setCellValue(formatNumber(aeqc.getQuotedCount()));
			cell.setCellStyle(strStyleCenter);
			cell = rowX.createCell(1);
			cell.setCellValue("利润");
			cell.setCellStyle(strStyleCenterCenter);
			for (int m = 2; m < 7; m++) {
				cell = rowX.createCell(m);
				cell.setCellStyle(strStyleCenterCenter);
			}
			cell = rowX.createCell(7);
			cell.setCellValue("");
			cell.setCellStyle(strStyle1);
			ExcelUtils.addMergedRegion(sheet, "B" + (rowIndex) + ":" + "G" + (rowIndex));

			rowX = sheet.createRow(rowIndex++);
			cell = rowX.createCell(0);
			cell.setCellValue(formatNumber(aeqc.getQuotedCount()));
			cell.setCellStyle(strStyleCenter);
			cell = rowX.createCell(1);
			cell.setCellValue("利润占比(%)");
			cell.setCellStyle(strStyleCenterCenter);
			for (int m = 2; m < 7; m++) {
				cell = rowX.createCell(m);
				cell.setCellStyle(strStyleCenterCenter);
			}
			cell = rowX.createCell(7);
			cell.setCellValue("");
			cell.setCellStyle(strStyle1);
			ExcelUtils.addMergedRegion(sheet, "B" + (rowIndex) + ":" + "G" + (rowIndex));

			ExcelUtils.addMergedRegion(sheet, "A" + (rowIndex - 7) + ":" + "A" + (rowIndex));
		}

		// 总报价 （含税、运费）
		rowX = sheet.createRow(rowIndex++);
		cell = rowX.createCell(0);
		cell.setCellValue("总报价 （含税、运费）");
		cell.setCellStyle(style);
		for (int i = 1; i < 8; i++) {
			cell = rowX.createCell(i);
			cell.setCellStyle(style);
		}
		ExcelUtils.addMergedRegion(sheet, "A" + (rowIndex) + ":" + "H" + (rowIndex));

		rowX = sheet.createRow(rowIndex++);
		cell = rowX.createCell(0);
		cell.setCellValue("订购数量序号");
		cell.setCellStyle(strStyle);
		cell = rowX.createCell(1);
		cell.setCellValue("订购数量");
		cell.setCellStyle(strStyleCenterCenter);
		cell = rowX.createCell(2);
		cell.setCellStyle(strStyleCenterCenter);
		ExcelUtils.addMergedRegion(sheet, "B" + (rowIndex) + ":" + "C" + (rowIndex));
		cell = rowX.createCell(3);
		cell.setCellValue("打样周期(天)");
		cell.setCellStyle(strStyleRed);
		cell = rowX.createCell(4);
		cell.setCellValue("日产能");
		cell.setCellStyle(strStyleRed);
		cell = rowX.createCell(5);
		cell.setCellValue("生产周期(天)");
		cell.setCellStyle(strStyleCenterCenterRed);
		cell = rowX.createCell(6);
		cell.setCellStyle(strStyleCenterCenter);
		ExcelUtils.addMergedRegion(sheet, "F" + (rowIndex) + ":" + "G" + (rowIndex));
		cell = rowX.createCell(7);
		cell.setCellValue("单价");
		cell.setCellStyle(strStyleRed);

		for (int i = 0; i < listAeqc.size(); i++) {
			rowX = sheet.createRow(rowIndex++);
			cell = rowX.createCell(0);
			cell.setCellValue(i + 1);
			cell.setCellStyle(strStyleCenter);
			cell = rowX.createCell(1);
			cell.setCellValue(formatNumber(listAeqc.get(i).getQuotedCount()));
			cell.setCellStyle(strStyle);
			cell = rowX.createCell(2);
			cell.setCellStyle(strStyle);
			ExcelUtils.addMergedRegion(sheet, "B" + (rowIndex) + ":" + "C" + (rowIndex));
			cell = rowX.createCell(3);
			cell.setCellValue("");
			cell.setCellStyle(strStyle1);
			cell = rowX.createCell(4);
			cell.setCellValue("");
			cell.setCellStyle(strStyle1);
			cell = rowX.createCell(5);
			cell.setCellValue("");
			cell.setCellStyle(strStyle1);
			cell = rowX.createCell(6);
			cell.setCellStyle(strStyle1);
			ExcelUtils.addMergedRegion(sheet, "F" + (rowIndex) + ":" + "G" + (rowIndex));
			cell = rowX.createCell(7);
			cell.setCellValue("");
			cell.setCellStyle(strStyle1);
		}

		// 供应商备注
		rowX = sheet.createRow(rowIndex++);
		cell = rowX.createCell(0);
		cell.setCellValue("供应商备注");
		cell.setCellStyle(styleBlue);
		for (int i = 1; i < 8; i++) {
			cell = rowX.createCell(i);
			cell.setCellStyle(styleBlue);
		}
		ExcelUtils.addMergedRegion(sheet, "A" + (rowIndex) + ":" + "H" + (rowIndex));
		rowX = sheet.createRow(rowIndex++);
		rowX.setHeight((short) (50 * 20));
		cell = rowX.createCell(0);
		cell.setCellValue("");
		cell.setCellStyle(strStyle1);
		for (int i = 1; i < 8; i++) {
			cell = rowX.createCell(i);
			cell.setCellStyle(strStyle1);
		}
		ExcelUtils.addMergedRegion(sheet, "A" + (rowIndex) + ":" + "H" + (rowIndex));

		rowX = sheet.createRow(rowIndex++);
		cell = rowX.createCell(0);
		cell.setCellValue("说明");
		cell.setCellStyle(strStyle);
		for (int i = 1; i < 8; i++) {
			cell = rowX.createCell(i);
			cell.setCellStyle(strStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "A" + (rowIndex) + ":" + "H" + (rowIndex));

		rowX = sheet.createRow(rowIndex++);
		cell = rowX.createCell(0);
		cell.setCellValue("1、收到空白表单后请仔细核算并按照格式完整填写，填写完成后提供电子档和盖章件。");
		cell.setCellStyle(strStyle);
		for (int i = 1; i < 8; i++) {
			cell = rowX.createCell(i);
			cell.setCellStyle(strStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "A" + (rowIndex) + ":" + "H" + (rowIndex));
		rowX = sheet.createRow(rowIndex++);
		cell = rowX.createCell(0);
		cell.setCellValue("2、询价回复时间：收到表单后请在1个工作日内填写完毕并回复。");
		cell.setCellStyle(strStyle);
		for (int i = 1; i < 8; i++) {
			cell = rowX.createCell(i);
			cell.setCellStyle(strStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "A" + (rowIndex) + ":" + "H" + (rowIndex));
		rowX = sheet.createRow(rowIndex++);
		cell = rowX.createCell(0);
		cell.setCellValue("3、若有疑问请及时沟通。");
		cell.setCellStyle(strStyle);
		for (int i = 1; i < 8; i++) {
			cell = rowX.createCell(i);
			cell.setCellStyle(strStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "A" + (rowIndex) + ":" + "H" + (rowIndex));
		/*
		 * // 2.用数据填充单元格 if (!listAem.isEmpty()) this.fillDataCell(list, wb,
		 * sheet);
		 */
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

	// 添加关联供应商
	@Override
	public void insertAccessoryIntentionSupplier(Map<String, Object> map) {
		this.getAccessoryIntentionDao().insertAccessoryIntentionSupplier(map);
	}

	// 页面添加供应商到表
	@Override
	public void insertIntentionSupplier(Map<String, Object> map, Map<String, Object> map1) {
		this.getAccessoryIntentionDao().insertIntentionSupplier(map1);
		this.getAccessoryIntentionDao().insertAccessoryIntentionSupplier(map);
	}

	// 删除
	@Override
	public void deleteAccessoryIntention(String[] intentionCode) {
		for (String code : intentionCode) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("intentionCode", code);
			this.getAccessoryIntentionDao().deleteAccessoryIntention(map);
			map.clear();
			map.put("intentionCode", code);
			List<AccessoryEnquiry> accessoryEnquiryList = new ArrayList<AccessoryEnquiry>();
			List<AccessoryQuotedElectronic> accessoryQuotedElectronicList = new ArrayList<AccessoryQuotedElectronic>();
			List<AccessoryQuotedScan> accessoryQuotedScanList = new ArrayList<AccessoryQuotedScan>();
			List<AccessoryProofing> accessoryProofingList = new ArrayList<AccessoryProofing>();
			if (StringUtils.isNotBlank(code)) {
				accessoryEnquiryList = AccessoryEnquiryServiceImpl.getInstance().listAccessoryEnquiry(map, null);
				accessoryQuotedElectronicList = AccessoryQuotedElectronicServiceImpl.getInstance().listAccessoryQuotedElectronic(map, null);
				accessoryQuotedScanList = AccessoryQuotedScanServiceImpl.getInstance().listAccessoryQuotedScan(map, null);
				accessoryProofingList = AccessoryProofingServiceImpl.getInstance().listAccessoryProofing(map, null);
			}
			if (accessoryEnquiryList != null && accessoryEnquiryList.size() > 0) {
				for (AccessoryEnquiry accessoryEnquiry : accessoryEnquiryList) {

					AccessoryEnquiryServiceImpl.getInstance().deleteAccessoryEnquiry(accessoryEnquiry.getEnquiryCode());
				}

			}
			if (accessoryQuotedElectronicList != null && accessoryQuotedElectronicList.size() > 0) {
				for (AccessoryQuotedElectronic accessoryQuotedElectronic : accessoryQuotedElectronicList) {

					AccessoryQuotedElectronicServiceImpl.getInstance().deleteAccessoryQuotedElectronic(accessoryQuotedElectronic.getQuotedCode());
				}

			}
			if (accessoryQuotedScanList != null && accessoryQuotedScanList.size() > 0) {
				for (AccessoryQuotedScan accessoryQuotedScan : accessoryQuotedScanList) {
					AccessoryQuotedScanServiceImpl.getInstance().deleteAccessoryQuotedScan(accessoryQuotedScan.getQuotedCode());
				}

			}
			if (accessoryProofingList != null && accessoryProofingList.size() > 0) {
				for (AccessoryProofing accessoryProofing : accessoryProofingList) {
					AccessoryProofingServiceImpl.getInstance().deleteAccessoryProofing(accessoryProofing.getQuotedCode());
				}

			}

		}
	}

	// 删除关联供应商
	@Override
	public void deleteAccessoryIntentionSupplier(Map<String, Object> map) {
		this.getAccessoryIntentionDao().deleteAccessoryIntentionSupplier(map);
	}

	// 修改
	@Override
	public void updateAccessoryIntention(AccessoryIntention accessoryIntention) {
		this.getAccessoryIntentionDao().updateAccessoryIntention(accessoryIntention.toMap());
	}

	// 加载一个辅料意向品
	@Override
	public AccessoryIntention loadAccessoryIntention(String intentionCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("intentionCode", intentionCode);
		return this.getAccessoryIntentionDao().loadAccessoryIntention(map);
	}

	@Override
	public List<Option> listSupplier(Map<String, Object> map) {
		return this.getAccessoryIntentionDao().listSupplier(map);
	}

	@Override
	public List<Option> listMinceType(Map<String, Object> map) {
		return this.getAccessoryIntentionDao().listMinceType(map);
	}

	@Override
	public Boolean editIsOk(String intentionCode) {
		Boolean isOk = true;
		if (intentionCode == null) {
			isOk = false;
		} else {

			AccessoryIntention accessoryIntention = this.loadAccessoryIntention(intentionCode);
			if (accessoryIntention == null)
				accessoryIntention = new AccessoryIntention();
			if (StringUtils.isNotBlank(accessoryIntention.getSoaNo()))
				isOk = false;
		}
		return isOk;
	}

	@Override
	public void deleteAccessoryQuotedElectronic(String[] quotedCode) {
		for (String code : quotedCode) {
			accessoryQuotedElectronicService.deleteAccessoryQuotedElectronic(code);
			accessoryQuotedTotalService.deleteAccessoryQuotedTotalFromQuotedCode(code);
			accessoryQuotedMaterialService.deleteAccessoryQuotedMaterial(code);
			accessoryQuotedAccessoryService.deleteAccessoryQuotedAccessory(code);
			accessoryQuotedPackingService.deleteAccessoryQuotedPacking(code);
			accessoryQuotedTechnologyService.deleteAccessoryQuotedTechnology(code);
			accessoryQuotedElseService.deleteAccessoryQuotedElse(code);
		}

	}

	@Override
	public void deleteAccessoryQuotedScan(String[] quotedCode) {
		for (String code : quotedCode) {
			accessoryQuotedScanService.deleteAccessoryQuotedScan(code);
		}

	}

	@Override
	public void deleteAccessoryProofing(String[] proofingCode) {
		for (String code : proofingCode) {
			accessoryProofingService.deleteAccessoryProofing(code);
		}

	}

	@Override
	public void deleteAccessoryEnquiry(String[] enquiryCode) {
		for (String code : enquiryCode) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("enquiryCode", code);
			List<AccessoryQuotedElectronic> accessoryQuotedElectronicList = AccessoryQuotedElectronicServiceImpl.getInstance().listAccessoryQuotedElectronic(map, null);
			if (accessoryQuotedElectronicList != null && accessoryQuotedElectronicList.size() > 0) {
				throw new EscmException("删除失败", new String[] {code+"已有报价单，不能删除" });
			} else{
			accessoryEnquiryService.deleteAccessoryEnquiry(code);
			accessoryEnquiryMaterialService.deleteAccessoryEnquiryMaterial(code);
			accessoryEnquiryAccessoryService.deleteAccessoryEnquiryAccessory(code);
			accessoryEnquiryPackingService.deleteAccessoryEnquiryPacking(code);
			accessoryEnquiryTechnologyService.deleteAccessoryEnquiryTechnology(code);
			accessoryEnquiryQuotedCountService.deleteAccessoryEnquiryQuotedCount(code);
			}
		}
	}

	@Override
	public AccessoryIntentionSupplier loadIntentionSupplier(Map<String, Object> map2) {
		// TODO Auto-generated method stub
		return this.getAccessoryIntentionDao().loadIntentionSupplier(map2);
	}
	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * 
	 */
	private String formatNumber(BigDecimal num) {
		DecimalFormat df = new DecimalFormat("#,##0;(#)");
		df.setRoundingMode(RoundingMode.HALF_UP);
		if (num != null) {
			return df.format(num);
		}
		return "";
	}

}
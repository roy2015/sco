package com.powere2e.sco.service.impl.merchandiseintention;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.merchandiseintention.MerchandiseQuotedDao;
import com.powere2e.sco.interfaces.service.merchandiseintention.MerchandiseIntentionService;
import com.powere2e.sco.interfaces.service.merchandiseintention.MerchandiseQuotedService;
import com.powere2e.sco.model.masterdata.Supplier;
import com.powere2e.sco.model.merchandiseintention.MerchandiseQuoted;
import com.powere2e.sco.service.impl.masterdata.SupplierServiceImpl;
import com.powere2e.sco.validate.CheckUploadQuotedValidate;

/**
 * 商品意向品业务类的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年3月10日
 */
public class MerchandiseQuotedServiceImpl extends ServiceImpl implements MerchandiseQuotedService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1898963804653668435L;
	private MerchandiseQuotedDao merchandiseQuotedDao;

	public static MerchandiseIntentionService getInstance() {
		return (MerchandiseIntentionService) ConfigFactory.getInstance().getBean("merchandiseIntentionManager");
	}
	
	public static MerchandiseQuotedService getMerchandiseQuotedServiceInstance() {
		return (MerchandiseQuotedService) ConfigFactory.getInstance().getBean("merchandiseQuotedService");
	}
	

	/* ======================报价单操作========================================== */
	// 添加供应商报价单
	@Override
	public void insertMerchandiseQuoted(Map<String, Object> map) throws Exception {
		merchandiseQuotedDao.insertMerchandiseQuoted(map);
	}

	// 修改供应商报价单
	@Override
	public void updateMerchandiseQuoted(Map<String, Object> map) throws Exception {
		merchandiseQuotedDao.updateMerchandiseQuoted(map);
	}

	// 删除报价单(根据报价日期和供应商编号删除)
	@Override
	public void deleteMerchandiseQuoted(Map<String, Object> map) throws Exception {
		merchandiseQuotedDao.deleteMerchandiseQuoted(map);
	}

	// 根据id删除报价单
	@Override
	public void deleteMerchandiseQuotedById(Map<String, Object> map) throws Exception {
		merchandiseQuotedDao.deleteMerchandiseQuotedById(map);
	}

	// 从excel中解析报价单
	@Override
	public String insertUploadQuotedFromExcel(File quotedFile, MerchandiseQuoted selectedQuoted) throws Exception {
		FileInputStream is = new FileInputStream(quotedFile);
		XSSFWorkbook wb = new XSSFWorkbook(is);
		XSSFSheet sheet = wb.getSheetAt(0);

		CheckUploadQuotedValidate checkUploadQuoted = new CheckUploadQuotedValidate();
		Map<String, Object> quotedMap = checkUploadQuoted.upload(sheet, selectedQuoted);
		String check = (String) quotedMap.get("CHECK");
		MerchandiseQuoted merchandiseQuoted = (MerchandiseQuoted) quotedMap.get("MERCHANDISEQUOTED");

		if (check.length() == 0) {
			check += completeUploadQuotedFromExcel(merchandiseQuoted);
		}
		return check;
	}

	// 新增exel数据
	private String completeUploadQuotedFromExcel(MerchandiseQuoted merchandiseQuoted) throws Exception {
		String check = "";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("intentionCode", merchandiseQuoted.getIntentionCode());
		map.put("quotedDate", merchandiseQuoted.getQuotedDate());
		map.put("intentionSupplierCode", merchandiseQuoted.getIntentionSupplierCode());
		// 根据报价日期和供应商编号先删除报价信息
		// deleteMerchandiseQuoted(map);
		// insertMerchandiseQuoted(merchandiseQuoted.toMap());
		insertQuotedTransactionControl(map, merchandiseQuoted);

		return check;
	}

	// 供应商报价单查询
	@Override
	public List<MerchandiseQuoted> listMerchandiseQuoted(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return merchandiseQuotedDao.listMerchandiseQuoted(map, pageInfo);
	}
	
	// 供应商报价单查询(不分页,只查最晚的报价记录)
	@Override
	public List<MerchandiseQuoted> listSupplierQuoted(Map<String, Object> map) throws Exception {
		return merchandiseQuotedDao.listSupplierQuoted(map);
	}

	// 根据ID号加载一个供应商报价单
	@Override
	public MerchandiseQuoted loadMerchandiseQuoted(Map<String, Object> map) throws Exception {
		return merchandiseQuotedDao.loadMerchandiseQuoted(map);
	}

	// 新增报价单事务控制,先删除报价单，然后新增报价单(其中一个操作报错，就整个事务不提交)
	@Override
	public void insertQuotedTransactionControl(Map<String, Object> map, MerchandiseQuoted merchandiseQuoted) throws Exception {
		deleteMerchandiseQuoted(map);
		insertMerchandiseQuoted(merchandiseQuoted.toMap());
	}

	public MerchandiseQuotedDao getMerchandiseQuotedDao() {
		return merchandiseQuotedDao;
	}

	public void setMerchandiseQuotedDao(MerchandiseQuotedDao merchandiseQuotedDao) {
		this.merchandiseQuotedDao = merchandiseQuotedDao;
	}

	//查询勾选的参照品的报价计量单位
	@Override
	public List<MerchandiseQuoted> listRefMerchandiseQuoted(
			Map<String, Object> map) throws Exception {
		return merchandiseQuotedDao.listRefMerchandiseQuoted(map);
	}

	/**
	 * 从SAP或者最晚的报价单中查询公司相关信息
	 * 
	 * @param intentionSupplierCode
	 *            供应商编号
	 * @param merchandiseQuoted
	 *            最晚报价单实例
	 * @param map
	 *            查询参数
	 * @throws Exception
	 */
	@Override
	public void searchCompanyInfoInSapAndLastQuoted(
			String intentionSupplierCode, MerchandiseQuoted merchandiseQuoted,
			Map<String, Object> map) throws Exception {
		// 查询SAP中的供应商(正是供应商)
		Supplier supplier = SupplierServiceImpl.getInstance().loadSupplier(
				intentionSupplierCode);
		if (supplier != null) {// SAP里已有的供应商      设置相关 信息
			merchandiseQuoted.setCompanyName(supplier.getSupplierName());
			merchandiseQuoted.setCompanySite(supplier.getCompanySite());
			merchandiseQuoted.setContactsName(supplier.getContacts());
			merchandiseQuoted.setContactsPhone(supplier.getContactsPhone());
			merchandiseQuoted.setContactsFax(supplier.getCompanyFax());
		} else {// SAP里没有的供应商，从供应商在系统中的所有报价单中最晚的报价单中的信息带入
			map.put("excludeIntention", "excludeIntention");
			MerchandiseQuoted lastQuoted = QuotedCompareServiceImpl.getQuotedCompareServiceInstance()
					.queryLastQuoted(map);
			if (lastQuoted != null) {
				// 这是因为页面只需要带出公司名称和公司地址
				merchandiseQuoted.setCompanyName(lastQuoted.getCompanyName());
				merchandiseQuoted.setCompanySite(lastQuoted.getCompanySite());
				merchandiseQuoted.setContactsName(lastQuoted.getContactsName());
				merchandiseQuoted.setContactsPhone(lastQuoted.getContactsPhone());
				merchandiseQuoted.setContactsEmail(lastQuoted.getContactsEmail());
				merchandiseQuoted.setContactsFax(lastQuoted.getContactsFax());
			}
		}
	}
}
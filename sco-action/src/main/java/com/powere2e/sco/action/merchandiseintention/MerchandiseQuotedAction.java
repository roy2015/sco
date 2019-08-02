package com.powere2e.sco.action.merchandiseintention;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.interfaces.service.merchandiseintention.MerchandiseIntentionService;
import com.powere2e.sco.interfaces.service.merchandiseintention.MerchandiseQuotedService;
import com.powere2e.sco.model.merchandiseintention.IntentionSupplierMerchandise;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseintention.MerchandiseQuoted;
import com.powere2e.security.model.User;
import com.powere2e.security.utils.PowerUtils;

/**
 * 报价单的Action
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年3月10日
 */
public class MerchandiseQuotedAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5163620339872494109L;
	private MerchandiseIntentionService merchandiseIntentionService;
	private MerchandiseQuotedService merchandiseQuotedService;
	private File uploadQuoted;// 导入报价单文件域

	public File getUploadQuoted() {
		return uploadQuoted;
	}

	public void setUploadQuoted(File uploadQuoted) {
		this.uploadQuoted = uploadQuoted;
	}

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		merchandiseIntentionService = (MerchandiseIntentionService) ConfigFactory.getInstance().getBean("merchandiseIntentionService");
		merchandiseQuotedService = (MerchandiseQuotedService) ConfigFactory.getInstance().getBean("merchandiseQuotedService");
	}

	/**
	 * 导出询价单
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doExportEnquiryToExcel() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String intentionCode = this.asString("intentionCode");// 意向品编号
		Date enquiryDate = this.asDate("enquiryDate");// 询价单日期
		String intentionSupplierCode = this.asString("intentionSupplierCode");// 勾选的供应商的编号

		map.put("intentionCode", intentionCode);
		map.put("intentionSupplierCode", intentionSupplierCode);

		MerchandiseIntention intention = merchandiseIntentionService.loadMerchandiseIntention(intentionCode);
		// 设置最晚报价日期
		intention.setEnquiryDate(enquiryDate);
		// 设置询价单生成日期
		intention.setCreated(DateUtils.getCurrentDate());
		List<IntentionSupplierMerchandise> supplierList = merchandiseIntentionService.listIntentionSupplierMerchandise(map, null);
		IntentionSupplierMerchandise intentionSupplier = new IntentionSupplierMerchandise();
		if (!supplierList.isEmpty()) {
			intentionSupplier = supplierList.get(0);
		}
		// 用户信息
		User loginUser = PowerUtils.getCurrentUser();

		this.putObject("intention", intention);
		this.putObject("loginUser", loginUser);
		this.putObject("supplier", intentionSupplier);
		
		//变更  先取SAP中数据否则取最晚报价单中信息
		MerchandiseQuoted merchandiseQuoted = new MerchandiseQuoted();
		merchandiseQuotedService.searchCompanyInfoInSapAndLastQuoted(intentionSupplierCode, merchandiseQuoted, map);
		this.putObject("merchandiseQuoted", merchandiseQuoted);
		
		this.forwardDownload("excel/sco/merchandiseIntention/intentionEnquiryTemplate.xlsx", ExcelUtils.getEncodeFileName(intention.getIntentionCode() + "-" + intention.getIntentionName() + "_" + intentionSupplierCode + "_"
				+ intentionSupplier.getIntentionSupplierName() + "_" + DateUtils.formateDateTime() + ".xlsx", request));
	}

	/**
	 * 从excel中导入报价单
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doUploadQuoted() throws Exception {
		String intentionCode = this.asString("intentionCode");// 意向品编号
		String intentionSupplierCode = this.asString("intentionSupplierCode");// 供应商的编号
		Date quotedDate = this.asDate("quotedDate");// 报价日期

		// 设置页面所选择的信息
		MerchandiseQuoted selectedQuoted = new MerchandiseQuoted();
		selectedQuoted.setIntentionCode(intentionCode);
		selectedQuoted.setIntentionSupplierCode(intentionSupplierCode);
		selectedQuoted.setQuotedDate(quotedDate);

		String check = merchandiseQuotedService.insertUploadQuotedFromExcel(uploadQuoted, selectedQuoted);
		if (StringUtils.isBlank(check)) {
			this.forwardData(true, "", "");
		} else {
			this.forwardData(false, "", check);
		}
	}

	/**
	 * 显示录入报价单界面
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doShowInsertQuotedForm() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String intentionCode = this.asString("intentionCode");// 意向品编号
		String intentionSupplierCode = this.asString("intentionSupplierCode");// 供应商的编号

		map.put("intentionCode", intentionCode);
		map.put("intentionSupplierCode", intentionSupplierCode);

		MerchandiseQuoted merchandiseQuoted = new MerchandiseQuoted();
		merchandiseQuoted.setIntentionCode(intentionCode);
		merchandiseQuoted.setIntentionSupplierCode(intentionSupplierCode);

		//变更  先取SAP中数据否则取最晚报价单中信息
		merchandiseQuotedService.searchCompanyInfoInSapAndLastQuoted(intentionSupplierCode, merchandiseQuoted, map);

		this.putObject("merchandiseQuoted", merchandiseQuoted);
		this.forwardPage("sco/merchandiseIntention/merchandiseQuotedForm.ftl");
	}

	/**
	 * 录入报价单
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doInsertQuoted() throws Exception {
		MerchandiseQuoted merchandiseQuoted = getMerchandiseQuoted();
		Map<String, Object> map = new HashMap<String, Object>();
		// 根据报价日期和供应商编号先删除针对"该意向品"的报价
		map.put("intentionCode", merchandiseQuoted.getIntentionCode());
		map.put("quotedDate", merchandiseQuoted.getQuotedDate());
		map.put("intentionSupplierCode", merchandiseQuoted.getIntentionSupplierCode());

		// 根据报价日期和供应商编号先删除报价信息。为了防止删除报价单和插入报价单的事务不同步，在此添加事务
		merchandiseQuotedService.insertQuotedTransactionControl(map, merchandiseQuoted);
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 显示修改报价单界面
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doShowUpdateQuotedForm() throws Exception {
		String quotationCode = this.asString("quotationCode");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quotationCode", quotationCode);

		MerchandiseQuoted merchandiseQuoted = merchandiseQuotedService.loadMerchandiseQuoted(map);
		this.putObject("merchandiseQuoted", merchandiseQuoted);
		this.forwardPage("sco/merchandiseIntention/merchandiseQuotedForm.ftl");
	}

	/**
	 * 修改报价单
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doUpdateQuoted() throws Exception {
		MerchandiseQuoted merchandiseQuoted = getMerchandiseQuoted();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quotedDate", merchandiseQuoted.getQuotedDate());
		map.put("intentionSupplierCode", merchandiseQuoted.getIntentionSupplierCode());
		/* 直接根据报价单号更新
		// 根据供应商编号和报价日期判断供应商在这一天是否有报价
		MerchandiseQuoted existsQuoted = merchandiseQuotedService.loadMerchandiseQuoted(map);
		if (existsQuoted != null) {
			// 说明找到的是本身这个报价单
			if (existsQuoted.getQuotationCode().equals(merchandiseQuoted.getQuotationCode())) {
				merchandiseQuotedService.updateMerchandiseQuoted(merchandiseQuoted.toMap());
				this.forwardData(true, null, this.getText("public.success"));
			} else {
				this.forwardData(false, null, "该供应商已存在相同报价日期的报价单，请勿重复添加!");
				return;
			}
		} else {
			merchandiseQuotedService.updateMerchandiseQuoted(merchandiseQuoted.toMap());
			this.forwardData(true, null, this.getText("public.success"));
		}*/
		merchandiseQuotedService.updateMerchandiseQuoted(merchandiseQuoted.toMap());
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 根据id删除报价单
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doDeleteQuotedById() throws Exception {
		String quotationCode = this.asString("quotationCode");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quotationCode", quotationCode);

		merchandiseQuotedService.deleteMerchandiseQuotedById(map);
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 查询报价单列表
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doListMerchandiseQuoted() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String intentionCode = this.asString("intentionCode");
		String intentionSupplierCode = this.asString("intentionSupplierCode");
		String intentionSupplierName = this.asString("intentionSupplierName");

		map.put("intentionCode", intentionCode);
		map.put("intentionSupplierCode", intentionSupplierCode);
		map.put("intentionSupplierName", intentionSupplierName);
		List<MerchandiseQuoted> list = merchandiseQuotedService.listMerchandiseQuoted(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}
	
	/**
	 * 查询报价单列表(不分页,只查最晚的报价)
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doListSupplierQuoted() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String intentionCode = this.asString("intentionCode");
		String intentionSupplierCode = this.asString("intentionSupplierCode");
		String intentionSupplierName = this.asString("intentionSupplierName");

		map.put("intentionCode", intentionCode);
		map.put("intentionSupplierCode", intentionSupplierCode);
		map.put("intentionSupplierName", intentionSupplierName);
		List<MerchandiseQuoted> list = merchandiseQuotedService.listSupplierQuoted(map);
		this.forwardData(true, list, null);
	}
	
	/**
	 * 查询勾选的参照品的报价计量单位
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doListRefMerchandiseQuoted() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String intentionCode = this.asString("intentionCode");
		String compareIntentionCodes = this.asString("compareIntentionCodes");

		map.put("intentionCode", intentionCode);
		map.put("compareIntentionCodes", compareIntentionCodes);
		List<MerchandiseQuoted> list = merchandiseQuotedService.listRefMerchandiseQuoted(map);
		this.forwardData(true, list, null);
	}
	
	/**
	 * 获得页面传递的与实体属性相关的值
	 */
	private MerchandiseQuoted getMerchandiseQuoted() throws Exception {
		MerchandiseQuoted merchandiseQuoted = new MerchandiseQuoted();
		this.asBean(merchandiseQuoted);
		return merchandiseQuoted;
	}
}

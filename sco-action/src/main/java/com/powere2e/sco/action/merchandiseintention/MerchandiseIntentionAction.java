package com.powere2e.sco.action.merchandiseintention;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.common.utils.BusinessUtils;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.interfaces.service.common.MasterDataTypeService;
import com.powere2e.sco.interfaces.service.masterdata.MerchandiseService;
import com.powere2e.sco.interfaces.service.merchandiseintention.MerchandiseIntentionService;
import com.powere2e.sco.model.merchandiseintention.IntentionSupplierMerchandise;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntentionSupplier;

/**
 * 意向品的Action
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年3月10日
 */
public class MerchandiseIntentionAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5163620339872494109L;
	private MerchandiseIntentionService merchandiseIntentionService;
	private MerchandiseService merchandiseService;
	private MasterDataTypeService masterDataTypeService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		merchandiseIntentionService = (MerchandiseIntentionService) ConfigFactory.getInstance().getBean("merchandiseIntentionService");
		merchandiseService = (MerchandiseService) ConfigFactory.getInstance().getBean("merchandiseService");
		masterDataTypeService = (MasterDataTypeService) ConfigFactory.getInstance().getBean("masterDataTypeService");
	}

	/**
	 * 商品意向品列表
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doShowMerchandiseIntentionGrid() throws Exception {
		this.putObject("procurementDepartmentsList", BusinessUtils.PROCUREMENT_DEPARTMENTS);
		this.putObject("rationed", BusinessUtils.RATIONED);
		this.putObject("purchaseType", BusinessUtils.PURCHASE_TYPE);
		this.putObject("isForetastePass", BusinessUtils.FORETASTE_TYPE);
		this.putObject("saleType", merchandiseService.listMerchandiseStorageForm());
		this.forwardPage("sco/merchandiseIntention/merchandiseIntentionGrid.ftl");
	}

	/**
	 * 商品意向品列表
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doListMerchandiseIntention() throws Exception {
		Map<String, Object> map = getMerchandiseIntention().toMap();
		String createDateStart = this.asString("createDateStart");
		String createDateEnd = this.asString("createDateEnd");
		String eatDateStart = this.asString("eatDateStart");
		String eatDateEnd = this.asString("eatDateEnd");
		String isForetastePass = this.asString("isForetastePass");

		map.put("createDateStart", createDateStart);
		map.put("createDateEnd", createDateEnd);
		map.put("eatDateStart", eatDateStart);
		map.put("eatDateEnd", eatDateEnd);
		map.put("isForetastePass", isForetastePass);
		List<MerchandiseIntention> list = merchandiseIntentionService.listMerchandiseIntention(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 根据ID号获一个商品意向品
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doLoadMerchandiseIntention() throws Exception {
		MerchandiseIntention merchandiseIntention = this.merchandiseIntentionService.loadMerchandiseIntention(asString("intentionCode"));
		this.forwardData(true, merchandiseIntention, null);
	}

	/**
	 * 添加商品意向品界面
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention.insert")
	public void doShowInsertMerchandiseIntentionForm() throws Exception {
		MerchandiseIntention merchandiseIntention = new MerchandiseIntention();
		// 通过表名获取id主键
		merchandiseIntention.setIntentionCode("SY" + masterDataTypeService.nextID("S_MERCHANDISE_INTENTION"));
		this.putObject("merchandiseIntention", merchandiseIntention);

		this.putObject("procurementDepartmentsList", BusinessUtils.PROCUREMENT_DEPARTMENTS);
		this.putObject("rationed", BusinessUtils.RATIONED);
		this.putObject("purchaseType", BusinessUtils.PURCHASE_TYPE);
		this.putObject("saleType", merchandiseService.listMerchandiseStorageForm());
		this.forwardPage("sco/merchandiseIntention/merchandiseIntentionPanel.ftl");
	}

	/**
	 * 修改商品意向品界面
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doShowUpdateMerchandiseIntentionForm() throws Exception {
		MerchandiseIntention merchandiseIntention = merchandiseIntentionService.loadMerchandiseIntention(asString("intentionCode"));
		this.putObject("merchandiseIntention", merchandiseIntention);
		// 如果点击意向品编号或者新增时，显示锚点1的页面。如果选择意向品，点击报价比价，就进入锚点5的页面
		this.putObject("panelNodeId", this.asString("panelNodeId"));

		this.putObject("procurementDepartmentsList", BusinessUtils.PROCUREMENT_DEPARTMENTS);
		this.putObject("rationed", BusinessUtils.RATIONED);
		this.putObject("purchaseType", BusinessUtils.PURCHASE_TYPE);
		this.putObject("saleType", merchandiseService.listMerchandiseStorageForm());
		this.forwardPage("sco/merchandiseIntention/merchandiseIntentionPanel.ftl");
	}

	/**
	 * 添加商品意向品
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention.insert")
	public void doInsertMerchandiseIntention() throws Exception {
		MerchandiseIntention merchandiseIntention = null;
		String intentionCode = this.asString("intentionCode");
		// 因为页面已经初始化了intentionCode，因此无法判断是修改还是新增意向品，需要与后台交互
		merchandiseIntention = merchandiseIntentionService.loadMerchandiseIntention(intentionCode);
		if (merchandiseIntention != null) {
			merchandiseIntention = getMerchandiseIntention();

			merchandiseIntentionService.updateMerchandiseIntention(merchandiseIntention);
		} else {
			merchandiseIntention = getMerchandiseIntention();
			// 系统设置为当前登录的id
			merchandiseIntentionService.insertMerchandiseIntention(merchandiseIntention);
		}

		this.forwardData(true, null, this.getText("public.success"));
	}


	/**
	 * 删除商品意向品
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention.delete")
	public void doDeleteMerchandiseIntention() throws Exception {
		String intentionCodes = asString("intentionCode");
		String applicationCodes = asString("applicationCodes");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("intentionCodes", applicationCodes);
		List<MerchandiseIntention> applicationList = merchandiseIntentionService.findApplicationByIntentionCodes(map);

		if (applicationList == null || applicationList.size() == 0) {
			// 说明没有做OA申请
			if (intentionCodes != null && intentionCodes.contains(",")) {
				// 选择了多条进行删除
				String ids[] = intentionCodes.split(",");
				for (String intentionCode : ids) {
					merchandiseIntentionService.deleteMerchandiseIntention(intentionCode);
				}
			} else {
				// 只选择了一条进行删除
				merchandiseIntentionService.deleteMerchandiseIntention(intentionCodes);
			}
			this.forwardData(true, null, this.getText("public.success"));
		} else {
			this.forwardData(false, null, "勾选的记录中存在有OA申请的意向品，不能删除!");
		}
	}

	/**
	 * 导出商品意向品列表
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doExportIntentionToExcel() throws Exception {
		ServletOutputStream out = response.getOutputStream();
		String fileName = ("意向品列表_").concat(DateUtils.formateDateTime()).concat(".xlsx");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		
		Map<String, Object> map = getMerchandiseIntention().toMap();
		merchandiseIntentionService.exportSignedQtyExcel(map, out);
		/*List<MerchandiseIntention> intentionList = merchandiseIntentionService.listMerchandiseIntention(map, null);
		this.putObject("intentionList", intentionList);
		this.forwardDownload("excel/sco/merchandiseIntention/merchandiseIntentionTemplate.xlsx", ExcelUtils.getEncodeFileName("意向品列表_" + DateUtils.formateDateTime() + ".xlsx"));*/

	}

	/**
	 * 已关联的供应商列表
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doListIntentionSupplierMerchandise() throws Exception {
		String intentionCode = this.asString("intentionCode");
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("intentionCode", intentionCode);
		List<IntentionSupplierMerchandise> list = merchandiseIntentionService.listIntentionSupplierMerchandise(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 新增关联的供应商
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doInsertIntentionSupplierMerchandise() throws Exception {
		String intentionCode = this.asString("intentionCode");
		String supplierCode = this.asString("supplierCode");

		// 关联供应商时，需要先判断该意向品是否存在，如果不存在，就不能关联供应商
		MerchandiseIntention merchandiseIntention = merchandiseIntentionService.loadMerchandiseIntention(intentionCode);
		if (merchandiseIntention == null) {
			// 说明意向品不存在
			this.forwardData(false, null, "请先保存意向品信息,再关联供应商!");
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("intentionSupplierCode", supplierCode);
			map.put("intentionCode", intentionCode);
			//判断供应商是否已经被关联
			IntentionSupplierMerchandise supplierMerchandise =merchandiseIntentionService.findSupplierMerchandiseIsExists(map);
			if (supplierMerchandise==null) {
				merchandiseIntentionService.insertIntentionSupplierMerchandise(map);
				this.forwardData(true, null, "添加供应商成功!");
			} else {
				this.forwardData(false, null, "该供应商已被选择，请不要重复选择!");
			}
		}
	}

	/**
	 * 删除关联的供应商
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doDeleteIntentionSupplierMerchandise() throws Exception {
		String intentionCode = this.asString("intentionCode");
		String intentionSupplierCodes = this.asString("intentionSupplierCodes");// 勾选的供应商的编号,用于取消关联供应商
		String supplierCodes = this.asString("supplierCodes");// 用于判断意向品关联的供应商是否OA申请

		String supplierCodeArr[] = intentionSupplierCodes.split(",");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("intentionCodes", "'"+intentionCode+"'");// 意向品编号
		map.put("supplierCodes", supplierCodes);// 所选的多个供应商编号
		List<MerchandiseIntention> applicationList = merchandiseIntentionService.findApplicationByIntentionCodes(map);
		if (applicationList != null && applicationList.size() > 0) {
			this.forwardData(false, null, "已选供应商已经有OA申请了,不能取消关联!");
			return;
		}

		for (int i = 0; i < supplierCodeArr.length; i++) {
			map.clear();
			map.put("intentionCode", intentionCode);
			map.put("intentionSupplierCode", supplierCodeArr[i]);
			merchandiseIntentionService.deleteIntentionSupplierMerchandise(map);
		}
		this.forwardData(true, null, "取消供应商成功!");
	}

	/**
	 * 显示新增意向供应商的页面
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doShowCreateSupplierForm() throws Exception {
		String intentionCode = this.asString("intentionCode");
		String intentionSupplierCode = "SYG".concat(masterDataTypeService.nextID("S_MERCHANDISE_INTENTION_SUPPLI"));

		this.putObject("intentionCode", intentionCode);
		this.putObject("intentionSupplierCode", intentionSupplierCode);
		this.forwardPage("sco/merchandiseIntention/insertIntentionSupplierForm.ftl");
	}

	/**
	 * 新增意向供应商
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doInsertIntentionSupplier() throws Exception {
		String intentionSupplierCode = this.asString("intentionSupplierCode");
		String intentionCode = this.asString("intentionCode");
		String intentionSupplierName = this.asString("intentionSupplierName");

		// 判断供应商名称是否已存在
		MerchandiseIntentionSupplier intentionSupplier = merchandiseIntentionService.findSupplierNameIsExists(intentionSupplierName);
		if (intentionSupplier == null) {
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("intentionSupplierCode", intentionSupplierCode);
			map.put("intentionSupplierName", intentionSupplierName);
			merchandiseIntentionService.insertIntentionSupplier(map);
			map.put("intentionCode", intentionCode);
			map.put("intentionSupplierCode", intentionSupplierCode);
			// 新增关联的供应商
			merchandiseIntentionService.insertIntentionSupplierMerchandise(map);
			this.forwardData(true, null, "意向供应商添加成功!");
		} else {
			this.forwardData(false, null, "该意向供应商已存在,不能重复新增!");
		}
	}

	/**
	 * 判断是否保存意向品
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doIsSaveMerchandiseIntention() throws Exception {
		MerchandiseIntention merchandiseIntention = this.merchandiseIntentionService.loadMerchandiseIntention(asString("intentionCode"));
		if (merchandiseIntention != null) {
			this.forwardData(true, null, null);
			return;
		} else {
			this.forwardData(false, null, "请先保存商品意向品信息!");
		}
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 */
	private MerchandiseIntention getMerchandiseIntention() throws Exception {
		MerchandiseIntention merchandiseIntention = new MerchandiseIntention();
		this.asBean(merchandiseIntention);
		return merchandiseIntention;
	}
}

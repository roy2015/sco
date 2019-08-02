package com.powere2e.sco.action.common.combox;

import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.common.utils.BusinessUtils;

/**
 * 公共业务下拉列表
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年3月16日
 */
public class BusinessComBoxAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8915705722020454704L;

	@Override
	protected void beforeBuild() {

	}
	/**
	 * 考评模板状态
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doDemartments() throws Exception{
		this.forwardData(true, BusinessUtils.DEPARTMENTS, null);
	}
	/**
	 * 考评模板状态
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doEvaluateTemplateStatus() throws Exception{
		this.forwardData(true, BusinessUtils.EVALUATE_TEMPLATE_STATUS, null);
	}

	/**
	 * 签量的状态
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doSingedQtyList() throws Exception {
		this.forwardData(true, BusinessUtils.SINGED_QTY_LIST, null);
	}

	/**
	 * 采购部门
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doProcurementDepartments() throws Exception {
		this.forwardData(true, BusinessUtils.PROCUREMENT_DEPARTMENTS, null);
	}

	/**
	 * 投料表-投料类型
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doMaterialType() throws Exception {
		this.forwardData(true, BusinessUtils.MATERIAL_TYPE, null);
	}

	/**
	 * 是否定量装
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doRationed() throws Exception {
		this.forwardData(true, BusinessUtils.RATIONED, null);
	}

	/**
	 * 采购类型
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doPurchaseType() throws Exception {
		this.forwardData(true, BusinessUtils.PURCHASE_TYPE, null);
	}

	/**
	 * 辅料OA审批状态
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doApplicationStatus() throws Exception {
		this.forwardData(true, BusinessUtils.APPLICATION_STATUS, null);
	}

	/**
	 * 报表类型
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doMyreportType() throws Exception {
		this.forwardData(true, BusinessUtils.MYREPORT_TYPE, null);
	}

	/**
	 * 商品内包装类型
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doMerchandiseNPackag() throws Exception {
		if ("yes".equals(this.asString("noW"))) {
			this.forwardData(true, BusinessUtils.MERCHANDISE_NOHASW_NPACKAG, null);
		} else {
			this.forwardData(true, BusinessUtils.MERCHANDISE_NPACKAG, null);
		}
	}

	/**
	 * 商品外包装类型
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doMerchandiseWPackag() throws Exception {
		if ("yes".equals(this.asString("noW"))) {
			this.forwardData(true, BusinessUtils.MERCHANDISE_NOHASW_WPACKAG, null);
		} else {
			this.forwardData(true, BusinessUtils.MERCHANDISE_WPACKAG, null);
		}
	}
	
	/**
	 * 其他成本
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doElseCost() throws Exception {
		this.forwardData(true, BusinessUtils.ELSECOST, null);
	}
	
	/**
	 * 进口相关成本
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doImportRelevantCost() throws Exception {
		this.forwardData(true, BusinessUtils.IMPORTRELEVANTCOST, null);
	}

	/**
	 * 供应商附件装类型
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doSupplierAttachmentType() throws Exception {
		this.forwardData(true, BusinessUtils.SUPPLIER_ATTACHMENT_TYPE, null);
	}
	
	/**
	 * 投料表-原料类型
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doMaterialTypeList() throws Exception {
		this.forwardData(true, BusinessUtils.MATERIAL_TYPE, null);
	}
	
	/**
	 * OA申请-研发
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doDevelopmentList() throws Exception {
		this.forwardData(true, BusinessUtils.DEVELOPMENT, null);
	}
	
	/**
	 * 报价币种
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doQuotedCurrencyList() throws Exception {
		this.forwardData(true, BusinessUtils.QUOTEDCURRENCY, null);
	}
	
	/**
	 * 提醒类型
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doRemindTypeList() throws Exception {
		this.forwardData(true, BusinessUtils.REMIND, null);
	}
	
	/**
	 * 预警类型
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doWarnTypeList() throws Exception {
		this.forwardData(true, BusinessUtils.WARNTYPE, null);
	}
	
	/**
	 * 预警类型(带"无")
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doWarnTypeHashNullList() throws Exception {
		this.forwardData(true, BusinessUtils.WARNTYPE_W, null);
	}
	
	/**
	 * 核算表-运费类型
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doFreightType() throws Exception {
		this.forwardData(true, BusinessUtils.FREIGHTTYPE, null);
	}
	
	/**
	 * 核算表-货柜类型
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doContainerType() throws Exception {
		this.forwardData(true, BusinessUtils.CONTAINERTYPE, null);
	}
	
	/**
	 * 核算表-出厂价类型
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doFactoryPriceType() throws Exception {
		this.forwardData(true, BusinessUtils.FACTORYPRICETYPE, null);
	}
	
	/**
	 * 是/否
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doYesNo() throws Exception {
		this.forwardData(true, BusinessUtils.YESNO, null);
	}
	
	/**
	 * 是否试吃通过
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doForetasteType() throws Exception {
		this.forwardData(true, BusinessUtils.FORETASTE_TYPE, null);
	}
	
	
	/**
	 * 巡厂申请单状态
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doVisitApplicationStatus() throws Exception {
		this.forwardData(true, BusinessUtils.VISIT_APPLICATION_STATUS, null);
	}
	
	/**
	 * 商品OA审批状态
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doMerchandiseApplicationStatus() throws Exception {
		this.forwardData(true, BusinessUtils.MERCHANDISE_APPLICATION_STATUS, null);
	}
}

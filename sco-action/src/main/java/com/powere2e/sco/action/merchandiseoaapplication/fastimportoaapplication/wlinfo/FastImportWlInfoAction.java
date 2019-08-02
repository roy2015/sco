package com.powere2e.sco.action.merchandiseoaapplication.fastimportoaapplication.wlinfo;

import java.util.Arrays;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.fastimportoaapplication.wlinfo.FastImportWlInfoService;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.wlinfo.MerchandiseWlInfo;
import com.powere2e.sco.service.impl.merchandiseoaapplication.fastimportoaapplication.ApplicationFastImportServiceImpl;

/**
 * 快速引入物料信息 WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @since 2015年10月19日
 * @version 1.0
 */
public class FastImportWlInfoAction extends WorkAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5197263482967576431L;
	private FastImportWlInfoService fastImportWlInfoService;//快速引进物料Service

	@Override
	protected void beforeBuild() {
		fastImportWlInfoService = (FastImportWlInfoService) ConfigFactory
				.getInstance().getBean("fastImportWlInfoService");
	}

	/**
	 * 显示新增物料信息界面
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doShowInsertWlInfoForm() throws Exception {
		String applicationCode = this.asString("applicationCode");
		String intentionAndSupplierCodes = this.asString("intentionAndSupplierCodes");

		ApplicationFastImportServiceImpl.getInstance().validateApplicationStatus(applicationCode,
				BusinessConstants.ApplicationType.MERCHANDISE_FASTIMPORT
						.toString(), intentionAndSupplierCodes);
		this.putObject("wlInfo", this.getWlInfoFastAdjust());
		this.forwardPage("sco/merchandiseOaApplication/fastImportOaApplication/wlinfo/wlInfoFastImportForm.ftl");
	}

	/**
	 * 保存物料信息
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doInsertWlInfoFastAdjust() throws Exception {
		String applicationCode = this.asString("applicationCode");
		String intentionAndSupplierCodes = this.asString("intentionAndSupplierCodes");
		
		ApplicationFastImportServiceImpl.getInstance().validateApplicationStatus(applicationCode,
				BusinessConstants.ApplicationType.MERCHANDISE_FASTIMPORT
						.toString(), intentionAndSupplierCodes);
		
		this.fastImportWlInfoService.insertWlInfoFastImport(
				this.getWlInfoFastAdjust(), applicationCode,
				intentionAndSupplierCodes);
		this.forwardData(true, null, "保存成功");
	}

	/**
	 * 显示物料信息修改界面
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doShowUpdateWlInfoForm() throws Exception {
		String applicationCode = this.asString("applicationCode");
		String intentionAndSupplierCodes = this.asString("intentionAndSupplierCodes");
		ApplicationFastImportServiceImpl.getInstance().validateApplicationStatus(applicationCode,
				BusinessConstants.ApplicationType.MERCHANDISE_FASTIMPORT.toString(), intentionAndSupplierCodes);
		
		MerchandiseWlInfo mw = this.fastImportWlInfoService.loadWlInfo(this.getWlInfoFastAdjust().toMap());
		if (mw == null) throw new EscmException("本条数据可能已被删除!");
		this.putObject("wlInfo", mw);
		this.putObject("ifEdit", true);
		this.putObject("regionVal", StrUtils.concatStr(mw.getRegion().split(",")));
		this.forwardPage("sco/merchandiseOaApplication/fastImportOaApplication/wlinfo/wlInfoFastImportForm.ftl");
	}
	
	/**
	 * 更新物料地区价格信息
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doUpdateWlInfo() throws Exception {
		String applicationCode = this.asString("applicationCode");
		String intentionAndSupplierCodes = this.asString("intentionAndSupplierCodes");
		ApplicationFastImportServiceImpl.getInstance().validateApplicationStatus(applicationCode,
				BusinessConstants.ApplicationType.MERCHANDISE_FASTIMPORT.toString(), intentionAndSupplierCodes);
		
		this.fastImportWlInfoService.updateWlInfoFastAdjust(
				this.getWlInfoFastAdjust(), this.asString("oldRegion"),
				applicationCode, intentionAndSupplierCodes);
		this.forwardData(true, null, "修改成功");
	}
	
	/**
	 * 删除物料信息
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doCompleteDeleteWlInfo() throws Exception {
		String applicationCode = this.asString("applicationCode");
		String intentionAndSupplierCodes = this.asString("intentionAndSupplierCodes");
		ApplicationFastImportServiceImpl.getInstance().validateApplicationStatus(applicationCode,
				BusinessConstants.ApplicationType.MERCHANDISE_FASTIMPORT.toString(), intentionAndSupplierCodes);
		
		this.fastImportWlInfoService.completeDeleteWlInfo(applicationCode,
				this.asString("intSup"), this.asString("intSupReg"),
				intentionAndSupplierCodes);
		this.forwardData(true, null, "删除成功!");
	}
	
	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private MerchandiseWlInfo getWlInfoFastAdjust() throws Exception {
		MerchandiseWlInfo wl = new MerchandiseWlInfo();
		this.asBean(wl);
		String[] strArr = this.asStrings("region");
		if (strArr != null) {
			Arrays.sort(strArr);
			wl.setRegion(StrUtils.arrayToStr(strArr, ","));
		}
		return wl;
	}
}

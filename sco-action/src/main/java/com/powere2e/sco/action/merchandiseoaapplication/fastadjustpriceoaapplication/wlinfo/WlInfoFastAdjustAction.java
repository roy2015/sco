package com.powere2e.sco.action.merchandiseoaapplication.fastadjustpriceoaapplication.wlinfo;

import java.util.Arrays;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.fastadjustpriceoaapplication.wlinfo.WlInfoFastAdjustService;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.wlinfo.MerchandiseWlInfo;

/**
 * 快速调价物料信息 WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月21日
 */
public class WlInfoFastAdjustAction extends WorkAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6548666488747255615L;
	private WlInfoFastAdjustService wlInfoFastAdjustService;
	
	@Override
	protected void beforeBuild() {
		wlInfoFastAdjustService = (WlInfoFastAdjustService) ConfigFactory.getInstance()
				.getBean("wlInfoFastAdjustService");
	}

	/**
	 * 显示商品关联物料信息列表
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doListWlInfoFastAdjust() throws Exception {
		List<MerchandiseWlInfo> list = this.wlInfoFastAdjustService.listWlInfoFastAdjust(
				this.getWlInfoFastAdjust().toMap(), this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 显示物料的价格信息
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doListWlInfoDetail() throws Exception {
		List<MerchandiseWlInfo> list = this.wlInfoFastAdjustService.listWlInfoDetail(this
				.getWlInfoFastAdjust().toMap(), this.getPageInfo());
		this.forwardData(true, list, null);
	}
	
	/**
	 * 显示新增物料信息界面
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doShowInsertWlInfoForm() throws Exception {
		this.validateApplicationStatus(this.asString("applicationCode"),
				this.asString("intentionAndSupplierCodes"));
		this.putObject("wlInfo", this.getWlInfoFastAdjust());
		this.forwardPage("sco/merchandiseOaApplication/fastAdjustpriceOaApplication/wlinfo/wlInfoFastAdjustForm.ftl");
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
		this.validateApplicationStatus(applicationCode, intentionAndSupplierCodes);
		this.wlInfoFastAdjustService.insertWlInfoFastAdjust(this.getWlInfoFastAdjust(), applicationCode, intentionAndSupplierCodes);
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
		this.validateApplicationStatus(applicationCode, intentionAndSupplierCodes);
		
		MerchandiseWlInfo mw = this.wlInfoFastAdjustService.loadWlInfo(this.getWlInfoFastAdjust().toMap());
		if (mw == null) throw new EscmException("本条数据可能已被删除!");
		this.putObject("wlInfo", mw);
		this.putObject("regionVal", StrUtils.concatStr(mw.getRegion().split(",")));
		this.forwardPage("sco/merchandiseOaApplication/fastAdjustpriceOaApplication/wlinfo/wlInfoFastAdjustForm.ftl");
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
		this.validateApplicationStatus(applicationCode, intentionAndSupplierCodes);
		
		this.wlInfoFastAdjustService.updateWlInfoFastAdjust(
				this.getWlInfoFastAdjust(), this.asString("oldRegion"),
				applicationCode, intentionAndSupplierCodes);
		this.forwardData(true, null, "修改成功");
	}
	
	/**
	 * 删除物料地区价格
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doDeleteWlInfo() throws Exception {
		String rows = this.asString("rows");
		String applicationCode = this.asString("applicationCode");
		String intentionAndSupplierCodes = this.asString("intentionAndSupplierCodes");
		this.validateApplicationStatus(applicationCode, intentionAndSupplierCodes);
		
		MerchandiseWlInfo[] dataArray = new MerchandiseWlInfo[]{};
		if (!StringUtils.isBlank(rows)) {
			JSONArray jsonArr = JSONArray.fromObject(rows);
			dataArray = new MerchandiseWlInfo[jsonArr.size()];
			for (int j = 0; j < jsonArr.size(); j++) {
				dataArray[j] = (MerchandiseWlInfo) JSONObject.toBean(jsonArr.getJSONObject(j), MerchandiseWlInfo.class);
			}
		}
		this.wlInfoFastAdjustService.deleteWlInfoFastAdjust(dataArray, applicationCode, intentionAndSupplierCodes);
		this.forwardData(true, null, "删除成功");
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

	/**
	 * 校验申请单状态
	 * 
	 * @param applicationCode
	 *            申请单号
	 * @param intentionAndSupplierCodes
	 *            意向品编号和供应商编号组
	 */
	private void validateApplicationStatus(String applicationCode, String intentionAndSupplierCodes) {
		/* 需求变更   只有审批通过的才可以操作   2015-09-17 Gavillen
		String receiptInfo = MerchandiseOaApplicationServiceImpl
				.getInstance()
				.getIntentionOaApplicationReceiptInfo(
						applicationCode, intentionAndSupplierCodes,
						BusinessConstants.ApplicationType.MERCHANDISE_FASTADJUSTPRICE
								.toString());
		if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.DIFFER
				.toString().equals(receiptInfo)) {
			throw new EscmException("选择的数据中包含已经OA申请的数据,不能重复申请!");
		} else if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.SAME_OTHER
				.toString().equals(receiptInfo)){
			throw new EscmException("选择的数据中包含非“草稿”的数据,无法修改!");
		}*/
	}
	
}

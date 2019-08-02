package com.powere2e.sco.action.merchandiseoaapplication.reportadjustpriceoaapplication.wlinfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportadjustpriceoaapplication.wlinfo.WlInfoAdjustService;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.wlinfo.MerchandiseWlInfo;

/**
 * 正常调价物料信息 WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月21日
 */
public class WlInfoAdjustAction extends WorkAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6548666488747255615L;
	private WlInfoAdjustService wlInfoAdjustService;
	
	@Override
	protected void beforeBuild() {
		wlInfoAdjustService = (WlInfoAdjustService) ConfigFactory.getInstance()
				.getBean("wlInfoAdjustService");
	}

	/**
	 * 显示物料信息列表
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.reportAdjustprice.insert")
	public void doShowWlInfoAdjustFtl() throws Exception {
		List<MerchandiseWlInfo> list = this.wlInfoAdjustService.listWlInfoAdjust(
				this.getWlInfoAdjustMap(), null);
		this.putObject("dataList", list);
		this.forwardPage("sco/merchandiseOaApplication/reportAdjustpriceOaApplication/wlinfo/wlInfoAdjustForm.ftl");
	}

	/**
	 * 保存物料信息
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.reportAdjustprice.insert")
	public void doInsertWlInfoAdjust() {
		String applicationCode = this.asString("applicationCode");
		String intentionAndSupplierCodes = this.asString("intentionAndSupplierCodes");
		this.wlInfoAdjustService.insertWlInfoAdjust(this.generateWlInfo(applicationCode), applicationCode, intentionAndSupplierCodes);
		this.forwardData(true, null, "保存成功");
	}
	
	/**
	 * 获取前台界面参数
	 * 
	 * @param applicationCode
	 *            申请单号
	 * @return 前台整理后的数据
	 */
	private List<MerchandiseWlInfo> generateWlInfo(String applicationCode) {
		String[] saveDataIndx = this.asStrings("selectData");//选中的需保存的数据下班
		List<MerchandiseWlInfo> list = new ArrayList<MerchandiseWlInfo>();
		if(saveDataIndx == null || saveDataIndx.length < 1) return list;
		
		for (String index : saveDataIndx) {//循环选择的数据
			MerchandiseWlInfo wl = new MerchandiseWlInfo();
			wl.setApplicationCode(applicationCode);
			wl.setIntentionCode(this.asString("intentionCode" + index));
			wl.setSupplierCode(this.asString("supplierCode" + index));
			wl.setAccessorySAPCode(this.asString("accessorySAPCode" + index));
			wl.setSupplierSAPCode(this.asString("supplierSAPCode" + index));
			String[] priceName = this.asStrings("priceName"+index);//会有重复的(需取一半) 
			
			if(priceName != null && priceName.length > 0) {
				for (int j = 0; j < priceName.length; j++) {//循环起地区价格
					String sumPrice = this.asString(("sumPrice" + j ) + index);
					boolean flag = StringUtils.isNotBlank(sumPrice);//地区价格
					MerchandiseWlInfo w = new MerchandiseWlInfo();
					w.setApplicationCode(applicationCode);
					w.setIntentionCode(wl.getIntentionCode());
					w.setSupplierCode(wl.getSupplierCode());
					
					w.setRegion(priceName[j]);
					if(flag) w.setSumPrice(new BigDecimal(sumPrice));
					wl.addAreaList(w);
				}
			}
			list.add(wl);
		}
		return list;
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private Map<String, Object> getWlInfoAdjustMap() throws Exception {
		MerchandiseWlInfo wl = new MerchandiseWlInfo();
		this.asBean(wl);
		Map<String, Object> map = wl.toMap();
		map.put("applicationCode", this.asString("applicationCode"));
		map.put("intentionAndSupplierCodes", this.asString("intentionAndSupplierCodes"));

		return map;
	}

}

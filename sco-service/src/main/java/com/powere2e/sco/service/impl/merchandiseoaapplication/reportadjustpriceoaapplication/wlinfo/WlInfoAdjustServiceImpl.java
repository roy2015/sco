package com.powere2e.sco.service.impl.merchandiseoaapplication.reportadjustpriceoaapplication.wlinfo;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportadjustpriceoaapplication.wlinfo.WlInfoAdjustDao;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportadjustpriceoaapplication.wlinfo.WlInfoAdjustService;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.wlinfo.MerchandiseWlInfo;
import com.powere2e.sco.service.impl.merchandiseoaapplication.MerchandiseOaApplicationServiceImpl;
import com.powere2e.sco.service.impl.merchandiseoaapplication.reportnewoaapplication.wlinfo.WlInfoNewServiceImpl;

/**
 * 正常调价物料信息 业务类的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月21日
 */
public class WlInfoAdjustServiceImpl extends ServiceImpl implements
		WlInfoAdjustService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7466731150478236790L;
	private WlInfoAdjustDao wlInfoAdjustDao;
	
	public WlInfoAdjustDao getWlInfoAdjustDao() {
		return wlInfoAdjustDao;
	}

	public void setWlInfoAdjustDao(WlInfoAdjustDao wlInfoAdjustDao) {
		this.wlInfoAdjustDao = wlInfoAdjustDao;
	}

	@Override
	public List<MerchandiseWlInfo> listWlInfoAdjust(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.wlInfoAdjustDao.listWlInfoAdjust(map, pageInfo);
	}

	@Override
	public void insertWlInfoAdjust(List<MerchandiseWlInfo> wlInfoList,
			String applicationCode, String intentionAndSupplierCodes) {
		if (wlInfoList.isEmpty()) return;
		
		this.validateOaStatus(applicationCode, intentionAndSupplierCodes);
		for (MerchandiseWlInfo merchandiseWlInfo : wlInfoList) {
			//1.保存所填SAP供应商、物料号
			this.wlInfoAdjustDao.insertIntentionMerchandiseSap(merchandiseWlInfo.toMap());
			for (MerchandiseWlInfo wl : merchandiseWlInfo.getAreaList()) {
				//2.保存价格地区
				this.wlInfoAdjustDao.insertMerchandiseContractPrice(wl.toMap());
			}
			//3.维护关联表
			WlInfoNewServiceImpl.getInstance().updateIntentionSupplierMerchandise(merchandiseWlInfo.toMap());
		}
	}

	/**
	 * 根据申请单的状态来显示不同的消息
	 * 
	 * @param map :
	 * 			<li>applicationCode 新品引进OA申请单号</li>
	 * 			<li>intentionAndSupplierCodes 所选择的意向品编号和供应商编号组</li>
	 */
	private void validateOaStatus(String applicationCode, String intentionAndSupplierCodes) {
//		String oaStatus = 
			try {
				MerchandiseOaApplicationServiceImpl.getInstance()
					.getIntentionOaApplicationReceiptInfo(applicationCode,
							intentionAndSupplierCodes, BusinessConstants.ApplicationType.MERCHANDISE_ADJUSTPRICE.toString());
			} catch (Exception e) {
				e.printStackTrace();
				throw new EscmException("校验申请单[" + applicationCode + "]状态时出错！");
			}
//		MerchandiseOaApplicationUtil.responseMessage(oaStatus);
	}
	
}
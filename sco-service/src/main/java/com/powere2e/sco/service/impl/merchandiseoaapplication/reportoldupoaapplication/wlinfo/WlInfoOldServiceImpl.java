package com.powere2e.sco.service.impl.merchandiseoaapplication.reportoldupoaapplication.wlinfo;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportoldupoaapplication.wlinfo.WlInfoOldDao;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportoldupoaapplication.wlinfo.WlInfoOldService;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.wlinfo.MerchandiseWlInfo;
import com.powere2e.sco.service.impl.merchandiseoaapplication.reportnewoaapplication.wlinfo.WlInfoNewServiceImpl;

/**
 * 老品新上物料信息 业务类的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月19日
 */
public class WlInfoOldServiceImpl extends ServiceImpl implements
		WlInfoOldService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7466731150478236790L;
	private WlInfoOldDao wlInfoOldDao;
	
	public WlInfoOldDao getWlInfoOldDao() {
		return wlInfoOldDao;
	}

	public void setWlInfoOldDao(WlInfoOldDao wlInfoOldDao) {
		this.wlInfoOldDao = wlInfoOldDao;
	}

	@Override
	public List<MerchandiseWlInfo> listWlInfoOld(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.wlInfoOldDao.listWlInfoOld(map, pageInfo);
	}

	@Override
	public void insertWlInfoOld(List<MerchandiseWlInfo> wlInfoList,
			String applicationCode, String intentionAndSupplierCodes) {
		if (wlInfoList.isEmpty()) return;
		for (MerchandiseWlInfo merchandiseWlInfo : wlInfoList) {
			//1.保存所填SAP供应商、物料号
			this.wlInfoOldDao.insertIntentionMerchandiseSap(merchandiseWlInfo.toMap());
			for (MerchandiseWlInfo wl : merchandiseWlInfo.getAreaList()) {
				//2.保存价格地区
				this.wlInfoOldDao.insertMerchandiseContractPrice(wl.toMap());
			}
			//3.维护关联表
			WlInfoNewServiceImpl.getInstance().updateIntentionSupplierMerchandise(merchandiseWlInfo.toMap());
		}
	}

	@Override
	public String listWlInfoByApplicationCode(
			Map<String, Object> map) {
		List<MerchandiseWlInfo> list = wlInfoOldDao.listWlInfoByApplicationCode(map);
		StringBuilder sb = new StringBuilder();
		for (MerchandiseWlInfo wlInfo : list) {
			sb.append(wlInfo.getAccessorySAPCode()).append(":")
				.append(wlInfo.getSupplierSAPCode()).append(",");
		}
		return sb.length() > 0 ? sb.substring(0, sb.length() - 1).toString() : sb.toString();
	}

}
package com.powere2e.sco.service.impl.merchandiseoaapplication.reportnewoaapplication.wlinfo;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportnewoaapplication.wlinfo.WlInfoNewDao;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportnewoaapplication.wlinfo.WlInfoNewService;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.wlinfo.MerchandiseWlInfo;

/**
 * 新品引进物料信息 业务类的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月19日
 */
public class WlInfoNewServiceImpl extends ServiceImpl implements
		WlInfoNewService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7466731150478236790L;
	private WlInfoNewDao wlInfoNewDao;

	/**
	 * 新品引进物料实例
	 * 
	 * @return 实例
	 */
	public static WlInfoNewService getInstance() {
		return (WlInfoNewService) ConfigFactory.getInstance().getBean("wlInfoNewService");
	}
	
	public WlInfoNewDao getWlInfoNewDao() {
		return wlInfoNewDao;
	}

	public void setWlInfoNewDao(WlInfoNewDao wlInfoNewDao) {
		this.wlInfoNewDao = wlInfoNewDao;
	}

	@Override
	public List<MerchandiseWlInfo> listWlInfoNew(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.wlInfoNewDao.listWlInfoNew(map, pageInfo);
	}

	@Override
	public void insertWlInfoNew(List<MerchandiseWlInfo> wlInfoList,
			String applicationCode, String intentionAndSupplierCodes) {
		if (wlInfoList.isEmpty()) return;
		for (MerchandiseWlInfo merchandiseWlInfo : wlInfoList) {
			//1.保存所填SAP供应商、物料号
			this.wlInfoNewDao.insertIntentionMerchandiseSap(merchandiseWlInfo.toMap());
			for (MerchandiseWlInfo wl : merchandiseWlInfo.getAreaList()) {
				//2.保存价格地区
				this.wlInfoNewDao.insertMerchandiseContractPrice(wl.toMap());
			}
			//3.维护关联表
			this.updateIntentionSupplierMerchandise(merchandiseWlInfo.toMap());
		}
	}

	/**
	 * 维护意向品供应商关联表
	 * 
	 * @param map
	 *            相关关联信息
	 */
	@Override
	public void updateIntentionSupplierMerchandise(Map<String, Object> map) {
		this.wlInfoNewDao.updateIntentionSupplierMerchandise(map);
	}
	
}
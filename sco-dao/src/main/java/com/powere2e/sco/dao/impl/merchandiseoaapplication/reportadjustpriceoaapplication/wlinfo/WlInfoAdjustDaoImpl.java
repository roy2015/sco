package com.powere2e.sco.dao.impl.merchandiseoaapplication.reportadjustpriceoaapplication.wlinfo;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportadjustpriceoaapplication.wlinfo.WlInfoAdjustDao;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.wlinfo.MerchandiseWlInfo;

/**
 * 正常调价物料信息 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月21日
 */
public class WlInfoAdjustDaoImpl extends DaoImpl implements WlInfoAdjustDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3979463679672729649L;

	@Override
	public List<MerchandiseWlInfo> listWlInfoAdjust(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(WlInfoAdjustDao.class, "listWlInfoAdjust", map, pageInfo);
	}

	@Override
	public void insertIntentionMerchandiseSap(Map<String, Object> map) {
		this.insert(WlInfoAdjustDao.class, "insertIntentionMerchandiseSap", map);
	}

	@Override
	public void insertMerchandiseContractPrice(Map<String, Object> map) {
		this.insert(WlInfoAdjustDao.class, "insertMerchandiseContractPrice", map);
	}

}
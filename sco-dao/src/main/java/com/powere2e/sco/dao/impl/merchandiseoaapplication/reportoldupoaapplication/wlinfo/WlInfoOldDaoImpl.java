package com.powere2e.sco.dao.impl.merchandiseoaapplication.reportoldupoaapplication.wlinfo;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportoldupoaapplication.wlinfo.WlInfoOldDao;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.wlinfo.MerchandiseWlInfo;

/**
 * 老品新上物料信息 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月19日
 */
public class WlInfoOldDaoImpl extends DaoImpl implements WlInfoOldDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3979463679672729649L;

	@Override
	public List<MerchandiseWlInfo> listWlInfoOld(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(WlInfoOldDao.class, "listWlInfoOld", map, pageInfo);
	}

	@Override
	public void insertIntentionMerchandiseSap(Map<String, Object> map) {
		this.insert(WlInfoOldDao.class, "insertIntentionMerchandiseSap", map);
	}

	@Override
	public void insertMerchandiseContractPrice(Map<String, Object> map) {
		this.insert(WlInfoOldDao.class, "insertMerchandiseContractPrice", map);
	}

	@Override
	public List<MerchandiseWlInfo> listWlInfoByApplicationCode(
			Map<String, Object> map) {
		return this.query(WlInfoOldDao.class, "listWlInfoByApplicationCode", map, null);
	}

}
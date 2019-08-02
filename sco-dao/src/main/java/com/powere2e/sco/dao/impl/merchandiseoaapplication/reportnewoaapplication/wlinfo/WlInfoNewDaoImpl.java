package com.powere2e.sco.dao.impl.merchandiseoaapplication.reportnewoaapplication.wlinfo;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportnewoaapplication.wlinfo.WlInfoNewDao;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.wlinfo.MerchandiseWlInfo;

/**
 * 新品引进物料信息 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月15日
 */
public class WlInfoNewDaoImpl extends DaoImpl implements WlInfoNewDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3979463679672729649L;

	@Override
	public List<MerchandiseWlInfo> listWlInfoNew(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(WlInfoNewDao.class, "listWlInfoNew", map, pageInfo);
	}

	@Override
	public void insertIntentionMerchandiseSap(Map<String, Object> map) {
		this.insert(WlInfoNewDao.class, "insertIntentionMerchandiseSap", map);
	}

	@Override
	public void insertMerchandiseContractPrice(Map<String, Object> map) {
		this.insert(WlInfoNewDao.class, "insertMerchandiseContractPrice", map);
	}

	@Override
	public void updateIntentionSupplierMerchandise(Map<String, Object> map) {
		this.update(WlInfoNewDao.class, "updateIntentionSupplierMerchandise", map);
	}

}
package com.powere2e.sco.dao.impl.merchandiseoaapplication.fastadjustpriceoaapplication.wlinfo;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.fastadjustpriceoaapplication.wlinfo.WlInfoFastAdjustDao;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.wlinfo.MerchandiseWlInfo;

/**
 * 快速调价物料信息 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月21日
 */
public class WlInfoFastAdjustDaoImpl extends DaoImpl implements
		WlInfoFastAdjustDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6010469059916213371L;

	@Override
	public List<MerchandiseWlInfo> listWlInfoFastAdjust(
			Map<String, Object> map, PageInfo pageInfo) {
		return this.query(WlInfoFastAdjustDao.class, "listWlInfoFastAdjust",
				map, pageInfo);
	}

	@Override
	public List<String> ifWlInfoExists(Map<String, Object> map) {
		return this.query(WlInfoFastAdjustDao.class, "ifWlInfoExists",
				map, null);
	}

	@Override
	public void insertMerchandiseContractPrice(Map<String, Object> map) {
		this.insert(WlInfoFastAdjustDao.class,
				"insertMerchandiseContractPrice", map);
	}

	@Override
	public MerchandiseWlInfo loadWlInfo(Map<String, Object> map) {
		return (MerchandiseWlInfo) this.get(WlInfoFastAdjustDao.class, "loadWlInfo", map);
	}

	@Override
	public void updateMerchandiseContractPrice(Map<String, Object> map) {
		this.update(WlInfoFastAdjustDao.class,
				"updateMerchandiseContractPrice", map);
	}

	@Override
	public void deleteMerchandiseContractPrice(Map<String, Object> map) {
		this.delete(WlInfoFastAdjustDao.class,
				"deleteMerchandiseContractPrice", map);
	}

	@Override
	public List<MerchandiseWlInfo> listWlInfoDetail(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(WlInfoFastAdjustDao.class, "listWlInfoDetail", map,
				pageInfo);
	}

}
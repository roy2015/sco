package com.powere2e.sco.dao.impl.merchandiseoaapplication.fastimportoaapplication.wlinfo;

import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.fastimportoaapplication.wlinfo.FastImportWlInfoDao;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.wlinfo.MerchandiseWlInfo;

/**
 * 商品快速引进物料信息 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年10月12日
 * @version 1.0
 */
public class FastImportWlInfoDaoImpl extends DaoImpl implements
		FastImportWlInfoDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 844172660320551251L;

	@Override
	public MerchandiseWlInfo loadWlInfo(Map<String, Object> map) {
		return (MerchandiseWlInfo) this.get(FastImportWlInfoDao.class, "loadWlInfo", map);
	}

	@Override
	public void deleteWlInfo(Map<String, Object> map) {
		this.delete(FastImportWlInfoDao.class, "deleteWlInfo", map);
	}

	@Override
	public void deleteSAPData(Map<String, Object> map) {
		this.delete(FastImportWlInfoDao.class, "deleteSAPData", map);
	}

	@Override
	public void updateISMData(Map<String, Object> map) {
		this.update(FastImportWlInfoDao.class, "updateISMData", map);
	}

}
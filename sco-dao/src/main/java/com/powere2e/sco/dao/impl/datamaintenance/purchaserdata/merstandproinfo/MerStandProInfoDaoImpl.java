package com.powere2e.sco.dao.impl.datamaintenance.purchaserdata.merstandproinfo;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.merstandproinfo.MerStandProInfoDao;
import com.powere2e.sco.model.datamaintenance.purchaserdata.merStandProInfo.ApplicationScheduleM2;
import com.powere2e.sco.model.datamaintenance.purchaserdata.merStandProInfo.MerStandProInfo;

/**
 * 商品引进标准进度信息维护 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年09月28日
 */
public class MerStandProInfoDaoImpl extends DaoImpl implements
		MerStandProInfoDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2001215668952558506L;

	@Override
	public List<MerStandProInfo> listMerMerStandProInfo(
			Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MerStandProInfoDao.class, "listMerMerStandProInfo", map,
				pageInfo);
	}

	@Override
	public void insertMerStandProInfo(Map<String, Object> map) {
		this.insert(MerStandProInfoDao.class, "insertMerStandProInfo", map);
	}
	
	@Override
	public void updateMerStandProInfo(Map<String, Object> map) {
		this.update(MerStandProInfoDao.class, "updateMerStandProInfo", map);
	}

	@Override
	public void deleteMerStandProInfo(Map<String, Object> map) {
		this.delete(MerStandProInfoDao.class, "deleteMerStandProInfo", map);
	}

	@Override
	public String ifMerStandProInfoExists(Map<String, Object> map) {
		return (String) this.get(MerStandProInfoDao.class,
				"ifMerStandProInfoExists", map);
	}

	@Override
	public ApplicationScheduleM2 searchAppByCodeAndMerchandise(
			Map<String, Object> map) {
		return (ApplicationScheduleM2) get(MerStandProInfoDao.class,
				"searchAppByCodeAndMerchandise", map);
	}

	@Override
	public MerStandProInfo searchMerStandProInfoByCreated(
			Map<String, Object> map) {
		return (MerStandProInfo) this.get(MerStandProInfoDao.class,
				"searchMerStandProInfoByCreated", map);
	}

	@Override
	public void completeAppSchedule(Map<String, Object> map) {
		this.update(MerStandProInfoDao.class, "completeAppSchedule", map);
	}

	@Override
	public List<ApplicationScheduleM2> listAppByCodeAndMerchandise(Map<String, Object> map) {
		return this.query(MerStandProInfoDao.class, "searchAppByCodeAndMerchandise", map, null);
	}

}
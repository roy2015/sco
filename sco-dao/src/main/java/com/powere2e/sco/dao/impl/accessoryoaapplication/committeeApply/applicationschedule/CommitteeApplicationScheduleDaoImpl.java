package com.powere2e.sco.dao.impl.accessoryoaapplication.committeeApply.applicationschedule;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.committeeApply.applicationschedule.CommitteeApplicationScheduleDao;
import com.powere2e.sco.model.accessoryoaapplication.applicatonSchedule.AccessoryApplicationSchedulea;

/**
 * 辅料采购委员会竞价进度信息 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月06日
 */
public class CommitteeApplicationScheduleDaoImpl extends DaoImpl implements
		CommitteeApplicationScheduleDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5698380651379880346L;

	@Override
	public List<AccessoryApplicationSchedulea> listApplicationScheduleCommittee(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(CommitteeApplicationScheduleDao.class,
				"listApplicationScheduleCommittee", map, pageInfo);
	}

	@Override
	public void insertApplicationScheduleCommittee(Map<String, Object> map) {
		this.insert(CommitteeApplicationScheduleDao.class, "insertApplicationScheduleCommittee",
				map);
	}

	@Override
	public List<AccessoryApplicationSchedulea> listApplicationScheduleCommitteeByAppCode(
			Map<String, Object> map) {
		return this.query(CommitteeApplicationScheduleDao.class,
				"listApplicationScheduleCommitteeByAppCode", map, null);
	}

	@Override
	public void deleteApplicationScheduleCommitteeByAppCode(
			Map<String, Object> map) {
		this.delete(CommitteeApplicationScheduleDao.class,
				"deleteApplicationScheduleCommitteeByAppCode", map);
	}

}
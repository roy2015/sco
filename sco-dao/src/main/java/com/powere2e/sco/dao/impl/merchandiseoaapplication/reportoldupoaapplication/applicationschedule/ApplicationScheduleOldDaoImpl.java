package com.powere2e.sco.dao.impl.merchandiseoaapplication.reportoldupoaapplication.applicationschedule;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportoldupoaapplication.applicationschedule.ApplicationScheduleOldDao;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.applicatonSchedule.ApplicationScheduleaOld;

/**
 * 老品新上进度信息 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月25日
 */
public class ApplicationScheduleOldDaoImpl extends DaoImpl implements
		ApplicationScheduleOldDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5698380651379880346L;

	@Override
	public List<ApplicationScheduleaOld> listApplicationScheduleOld(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(ApplicationScheduleOldDao.class, "listApplicationScheduleOld", map, pageInfo);
	}

	@Override
	public void insertApplicationScheduleOld(Map<String, Object> map) {
		this.insert(ApplicationScheduleOldDao.class, "insertApplicationScheduleOld", map);
	}

}
package com.powere2e.sco.dao.impl.merchandiseoaapplication.reportnewoaapplication.applicationschedule;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportnewoaapplication.applicationschedule.ApplicationScheduleNewDao;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.applicatonSchedule.ApplicationScheduleaNew;

/**
 * 新品引进进度信息 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月22日
 */
public class ApplicationScheduleNewDaoImpl extends DaoImpl implements
		ApplicationScheduleNewDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5698380651379880346L;

	@Override
	public List<ApplicationScheduleaNew> listApplicationScheduleNew(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(ApplicationScheduleNewDao.class, "listApplicationScheduleNew", map, pageInfo);
	}

	@Override
	public void insertApplicationScheduleNew(Map<String, Object> map) {
		this.insert(ApplicationScheduleNewDao.class, "insertApplicationScheduleNew", map);
	}

}
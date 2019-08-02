package com.powere2e.sco.service.impl.merchandiseoaapplication.reportoldupoaapplication.applicationschedule;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportoldupoaapplication.applicationschedule.ApplicationScheduleOldDao;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportoldupoaapplication.applicationschedule.ApplicationScheduleOldService;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.applicatonSchedule.ApplicationScheduleaOld;

/**
 * 老品新上进度信息 业务类的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月25日
 */
public class ApplicationScheduleOldServiceImpl extends ServiceImpl
		implements ApplicationScheduleOldService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -670127382998790509L;
	private ApplicationScheduleOldDao applicationScheduleOldDao;

	// 获取ApplicationScheduleOld Dao
	public ApplicationScheduleOldDao getApplicationScheduleOldDao() {
		return applicationScheduleOldDao;
	}

	// 设置ApplicationScheduleOld Dao
	public void setApplicationScheduleOldDao(
			ApplicationScheduleOldDao applicationScheduleOldDao) {
		this.applicationScheduleOldDao = applicationScheduleOldDao;
	}

	@Override
	public List<ApplicationScheduleaOld> listApplicationScheduleOld(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.applicationScheduleOldDao.listApplicationScheduleOld(map, pageInfo);
	}
	
	@Override
	public void insertApplicationScheduleOld(Map<String, Object> map, ApplicationScheduleaOld[] appSche) {
		for (ApplicationScheduleaOld app : appSche) {
			this.applicationScheduleOldDao.insertApplicationScheduleOld(app.toMap());
		}
	}

}
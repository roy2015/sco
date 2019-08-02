package com.powere2e.sco.service.impl.merchandiseoaapplication.reportnewoaapplication.applicationschedule;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportnewoaapplication.applicationschedule.ApplicationScheduleNewDao;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportnewoaapplication.applicationschedule.ApplicationScheduleNewService;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.applicatonSchedule.ApplicationScheduleaNew;

/**
 * 新品引进进度信息 业务类的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月22日
 */
public class ApplicationScheduleNewServiceImpl extends ServiceImpl
		implements ApplicationScheduleNewService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -670127382998790509L;
	private ApplicationScheduleNewDao applicationScheduleNewDao;

	// 获取ApplicationScheduleNew Dao
	public ApplicationScheduleNewDao getApplicationScheduleNewDao() {
		return applicationScheduleNewDao;
	}

	// 设置ApplicationScheduleNew Dao
	public void setApplicationScheduleNewDao(
			ApplicationScheduleNewDao applicationScheduleNewDao) {
		this.applicationScheduleNewDao = applicationScheduleNewDao;
	}

	@Override
	public List<ApplicationScheduleaNew> listApplicationScheduleNew(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.applicationScheduleNewDao.listApplicationScheduleNew(map, pageInfo);
	}
	
	@Override
	public void insertApplicationScheduleNew(Map<String, Object> map, ApplicationScheduleaNew[] appSche) {
		for (ApplicationScheduleaNew app : appSche) {
			this.applicationScheduleNewDao.insertApplicationScheduleNew(app.toMap());
		}
	}

}
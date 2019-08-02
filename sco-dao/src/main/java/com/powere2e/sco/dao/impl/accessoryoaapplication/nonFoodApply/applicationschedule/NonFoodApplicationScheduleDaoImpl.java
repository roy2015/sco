package com.powere2e.sco.dao.impl.accessoryoaapplication.nonFoodApply.applicationschedule;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.nonFoodApply.applicationschedule.NonFoodApplicationScheduleDao;
import com.powere2e.sco.model.accessoryoaapplication.applicatonSchedule.AccessoryApplicationSchedulea;

/**
 * 非食品竞价单OA申请进度信息 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月06日
 */
public class NonFoodApplicationScheduleDaoImpl extends DaoImpl implements
		NonFoodApplicationScheduleDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5698380651379880346L;

	@Override
	public List<AccessoryApplicationSchedulea> listApplicationScheduleNonFood(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(NonFoodApplicationScheduleDao.class, "listApplicationScheduleNonFood", map, pageInfo);
	}

	@Override
	public void insertApplicationScheduleNonFood(Map<String, Object> map) {
		this.insert(NonFoodApplicationScheduleDao.class, "insertApplicationScheduleNonFood", map);
	}

}
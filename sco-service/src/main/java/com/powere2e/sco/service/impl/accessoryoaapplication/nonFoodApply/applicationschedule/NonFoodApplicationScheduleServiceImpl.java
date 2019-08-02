package com.powere2e.sco.service.impl.accessoryoaapplication.nonFoodApply.applicationschedule;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.nonFoodApply.applicationschedule.NonFoodApplicationScheduleDao;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.nonFoodApply.applicationschedule.NonFoodApplicationScheduleService;
import com.powere2e.sco.model.accessoryoaapplication.applicatonSchedule.AccessoryApplicationSchedulea;
import com.powere2e.sco.service.impl.accessoryoaapplication.NonFoodApplyServiceImpl;

/**
 * 非食品竞价单OA申请进度信息 业务类的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月06日
 */
public class NonFoodApplicationScheduleServiceImpl extends ServiceImpl
		implements NonFoodApplicationScheduleService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -670127382998790509L;
	private NonFoodApplicationScheduleDao nonFoodApplicationScheduleDao;

	// 获取ApplicationSchedule Dao
	public NonFoodApplicationScheduleDao getNonFoodApplicationScheduleDao() {
		return nonFoodApplicationScheduleDao;
	}
	
	// 设置ApplicationSchedule Dao
	public void setNonFoodApplicationScheduleDao(
			NonFoodApplicationScheduleDao nonFoodApplicationScheduleDao) {
		this.nonFoodApplicationScheduleDao = nonFoodApplicationScheduleDao;
	}

	@Override
	public List<AccessoryApplicationSchedulea> listApplicationScheduleNonFood(Map<String, Object> map,
			PageInfo pageInfo) {
		Object quoCode = map.get("quotedCodes");
		map.put("quotedCodes", StrUtils.concatStr(
				(quoCode == null ? "" : quoCode.toString()).split(",")));
		return this.nonFoodApplicationScheduleDao.listApplicationScheduleNonFood(map, pageInfo);
	}
	
	@Override
	public void insertApplicationScheduleNonFood(Map<String, Object> map, AccessoryApplicationSchedulea[] appSche) {
//		this.validateOaStatus(map);
		for (AccessoryApplicationSchedulea app : appSche) {
			this.nonFoodApplicationScheduleDao.insertApplicationScheduleNonFood(app.toMap());
		}
	}

	/**
	 * 根据申请单的状态来显示不同的消息
	 * 
	 * @param map
	 *            : <li>quotedCodes 报价单编号</li>
	 *              <li>applicationCode 新品引进OA申请单号</li> 
	 */
	@SuppressWarnings("unused")
	private void validateOaStatus(Map<String, Object> map) {
		//报价单
		Object quoCode = map.get("quotedCodes");
		String quotedCodes  = (quoCode == null ? null : quoCode.toString());
		//申请单号
		Object appCode = map.get("applicationCode");
		String applicationCode = (appCode == null ? null : appCode.toString());
		
		Boolean flag = NonFoodApplyServiceImpl.getInstance().
			nonFoodInsertUpdateDeleteIsOk(quotedCodes, applicationCode, 
					BusinessConstants.ApplicationType.ACCESSORY_FSPJJD.toString());
		if(!flag) {
			throw new EscmException("报价单在其他申请单中或者当前申请单状态下不可修改!");
		}
	}
	
}
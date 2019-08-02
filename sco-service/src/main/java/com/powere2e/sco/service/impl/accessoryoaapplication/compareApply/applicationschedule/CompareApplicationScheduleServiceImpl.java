package com.powere2e.sco.service.impl.accessoryoaapplication.compareApply.applicationschedule;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.compareApply.applicationschedule.CompareApplicationScheduleDao;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.compareApply.applicationschedule.CompareApplicationScheduleService;
import com.powere2e.sco.model.accessoryoaapplication.applicatonSchedule.AccessoryApplicationSchedulea;
import com.powere2e.sco.service.impl.accessoryoaapplication.CompareApplyServiceImpl;

/**
 * 辅料询价单比较 进度信息业务类的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月19日
 */
public class CompareApplicationScheduleServiceImpl extends ServiceImpl
		implements CompareApplicationScheduleService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -670127382998790509L;
	private CompareApplicationScheduleDao nonFoodApplicationScheduleDao;

	// 获取ApplicationSchedule Dao
	public CompareApplicationScheduleDao getCompareApplicationScheduleDao() {
		return nonFoodApplicationScheduleDao;
	}
	
	// 设置ApplicationSchedule Dao
	public void setCompareApplicationScheduleDao(
			CompareApplicationScheduleDao nonFoodApplicationScheduleDao) {
		this.nonFoodApplicationScheduleDao = nonFoodApplicationScheduleDao;
	}

	@Override
	public List<AccessoryApplicationSchedulea> listApplicationScheduleCompare(Map<String, Object> map,
			PageInfo pageInfo) {
		Object quoCode = map.get("quotedCodes");
		map.put("quotedCodes", StrUtils.concatStr(
				(quoCode == null ? "" : quoCode.toString()).split(",")));
		return this.nonFoodApplicationScheduleDao.listApplicationScheduleCompare(map, pageInfo);
	}
	
	@Override
	public void insertApplicationScheduleCompare(Map<String, Object> map, AccessoryApplicationSchedulea[] appSche) {
//		this.validateOaStatus(map);
		for (AccessoryApplicationSchedulea app : appSche) {
			this.nonFoodApplicationScheduleDao.insertApplicationScheduleCompare(app.toMap());
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
		
		Boolean flag = CompareApplyServiceImpl.getInstance().
			compareInsertUpdateDeleteIsOk(quotedCodes, applicationCode, 
					BusinessConstants.ApplicationType.ACCESSORY_XJBJ.toString());
		if(!flag) {
			throw new EscmException("报价单在其他申请单中或者当前申请单状态下不可修改!");
		}
	}
	
}
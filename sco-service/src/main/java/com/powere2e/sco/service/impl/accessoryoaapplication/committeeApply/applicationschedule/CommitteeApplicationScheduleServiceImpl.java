package com.powere2e.sco.service.impl.accessoryoaapplication.committeeApply.applicationschedule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.committeeApply.applicationschedule.CommitteeApplicationScheduleDao;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.committeeApply.applicationschedule.CommitteeApplicationScheduleService;
import com.powere2e.sco.model.accessoryoaapplication.applicatonSchedule.AccessoryApplicationSchedulea;
import com.powere2e.sco.service.impl.accessoryoaapplication.CommitteeApplyServiceImpl;

/**
 * 辅料采购委员会竞价进度信息 业务类的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月06日
 */
public class CommitteeApplicationScheduleServiceImpl extends ServiceImpl implements
		CommitteeApplicationScheduleService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -670127382998790509L;
	private CommitteeApplicationScheduleDao committeeApplicationScheduleDao;

	/**
	 * 获取CommitteeApplicationScheduleService 实例
	 * 
	 * @return 实例对象
	 */
	public static CommitteeApplicationScheduleService getInstance() {
		return (CommitteeApplicationScheduleService) ConfigFactory
				.getInstance().getBean("committeeApplicationScheduleService");
	}
	
	// 获取ApplicationSchedule Dao
	public CommitteeApplicationScheduleDao getCommitteeApplicationScheduleDao() {
		return committeeApplicationScheduleDao;
	}

	// 设置ApplicationSchedule Dao
	public void setCommitteeApplicationScheduleDao(
			CommitteeApplicationScheduleDao committeeApplicationScheduleDao) {
		this.committeeApplicationScheduleDao = committeeApplicationScheduleDao;
	}

	@Override
	public List<AccessoryApplicationSchedulea> listApplicationScheduleCommittee(Map<String, Object> map,
			PageInfo pageInfo) {
		Object quoCode = map.get("quotedCodes");
		map.put("quotedCodes", StrUtils.concatStr(
				(quoCode == null ? "" : quoCode.toString()).split(",")));
		return this.committeeApplicationScheduleDao.listApplicationScheduleCommittee(map, pageInfo);
	}
	
	@Override
	public List<AccessoryApplicationSchedulea> listApplicationScheduleCommitteeByAppCode(String applicationCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		return this.committeeApplicationScheduleDao.listApplicationScheduleCommitteeByAppCode(map);
	}
	
	@Override
	public void deleteApplicationScheduleCommitteeByAppCode(String applicationCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		this.committeeApplicationScheduleDao.deleteApplicationScheduleCommitteeByAppCode(map);
	}

	@Override
	public void insertApplicationScheduleCommittee(Map<String, Object> map, AccessoryApplicationSchedulea[] appSche) {
//		this.validateOaStatus(map);
		for (AccessoryApplicationSchedulea app : appSche) {
			this.committeeApplicationScheduleDao.insertApplicationScheduleCommittee(app.toMap());
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
		
		Boolean flag = CommitteeApplyServiceImpl.getInstance().
			committeeInsertUpdateDeleteIsOk(quotedCodes, applicationCode, 
					BusinessConstants.ApplicationType.ACCESSORY_CGWYHJJD.toString());
		if(!flag) {
			throw new EscmException("报价单在其他申请单中或者当前申请单状态下不可修改!");
		}
	}
	
}
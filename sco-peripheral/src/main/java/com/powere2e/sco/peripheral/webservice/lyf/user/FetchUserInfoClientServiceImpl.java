package com.powere2e.sco.peripheral.webservice.lyf.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.interfaces.dao.singlelogin.SingleLoginDao;
import com.powere2e.sco.interfaces.service.peripheral.webservice.lyf.user.FetchUserInfoClientService;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;
import com.powere2e.sco.peripheral.webservice.lyf.user.bean.CommonEmployeeField;
import com.powere2e.sco.peripheral.webservice.lyf.user.bean.EmployeeInfo;
import com.powere2e.sco.peripheral.webservice.lyf.user.webservice.CommonInterface;
import com.powere2e.sco.peripheral.webservice.lyf.user.webservice.CommonInterfaceProxy;

/**
 * 来伊份用户数据抓取
 * 
 * @author Gavillen.Zhou
 * @since 2015年10月30日
 * @version 1.0
 */
public class FetchUserInfoClientServiceImpl implements FetchUserInfoClientService {

	private static final long serialVersionUID = 3429742890477746984L;

	private Logger logger = PeripheralFileUtils.logger;
	private SingleLoginDao singleLoginDao;
	private static Integer BATCH_SIZE = 100;// 分批次提交

	public SingleLoginDao getSingleLoginDao() {
		return singleLoginDao;
	}

	public void setSingleLoginDao(SingleLoginDao singleLoginDao) {
		this.singleLoginDao = singleLoginDao;
	}

	/**
	 * 抓取来伊份用户数据并同步到SCO系统中(增量)
	 */
	@Override
	public void completeFetchUser() {
		// 1.抓取数据
		Map<String, String> delMap = new HashMap<String, String>();
		List<EmployeeInfo> list = this.fetchUserInfo(delMap);
		try {
			// 2.保存或更新数据
			this.completeSynUser(list);
			// 3.删除用户数据和对应权限
			this.deleSynUserAndDataPower(delMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户数据入库时发生异常!\r\n" + StrUtils.getStackMsgToStr(e));
		}
	}

	/**
	 * 通过WebService抓取用户信息数据
	 * 
	 * @param delMap-需删除的用户数据
	 * @return 抓取的用户信息
	 */
	private List<EmployeeInfo> fetchUserInfo(Map<String, String> delMap) {
		List<EmployeeInfo> listUser = new ArrayList<EmployeeInfo>();
		try {
			CommonInterface cf = new CommonInterfaceProxy().getCommonInterface();
			// 只同步今天的数据
			String todayStr = "";
			// 因为定时任务是在每天凌晨3点且只执行一次,所以要推迟到前一天,否则只有当0~3点修改的数据才会被同步过去[同步的数据 >= 传入时间]
			todayStr = DateUtils.getTime(DateUtils.formateDate(), -1).replace("-", "");
			CommonEmployeeField result = cf.sendEmployee("LYF_SCO", todayStr, "TFlGX1NDTw==");
			if (result == null)
				return listUser;
			EmployeeInfo[] emp = result.getEmp();
			if (emp == null || emp.length < 1) {
				logger.error("抓取用户数据消息:\r\n" + result.getResult());
				return listUser;
			}
			Map<String, EmployeeInfo> addMap = new HashMap<>();
			// 去除重复的数据
			for (EmployeeInfo e : emp) {
				String uid = e.getUid();
				String uids = "'".concat(uid).concat("'");
				String changeType = e.getChangeType();
				if (!"D".equalsIgnoreCase(changeType) && delMap.containsKey(uids)) {// 如果需删除的数据里包含现在的
					delMap.remove(uids);
				} else if ("D".equalsIgnoreCase(changeType)) {
					delMap.put(uids, uids);// 需要删除的
				}

				if ("I".equalsIgnoreCase(changeType)) {
					addMap.put(uid, e);// 如果是新增状态
				}
				logger.info("抓取用户数据[用户ID-用户名称-操作类型]:" + e.getUid() + "-" + e.getCn() + "-" + changeType);
			}
			// 将map中的数据转换为List
			Iterator<String> it = addMap.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next().toString();
				EmployeeInfo e = addMap.get(key);
				listUser.add(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("抓取用户数据时出错:\r\n" + StrUtils.getStackMsgToStr(e));
		}
		return listUser;
	}

	/**
	 * 将来伊份用户数据存储到sco系统
	 * 
	 * @param list-来伊份用户数据
	 */
	private void completeSynUser(List<EmployeeInfo> list) {
		if (list == null || list.isEmpty())
			return;
		Integer length = list.size();
		List<EmployeeInfo> tmpList = null;
		int curIndex = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; ((i + 1) * BATCH_SIZE) < length;) {
			curIndex = (i + 1) * BATCH_SIZE;
			tmpList = list.subList(i * BATCH_SIZE, curIndex);
			i++;

			map.put("list", tmpList);
			this.singleLoginDao.completeSynUser(map);
		}
		// 补刀
		if (curIndex < length) {
			map.put("list", list.subList(curIndex, length));
			this.singleLoginDao.completeSynUser(map);
		}
	}

	/**
	 * 删除需要删除的用户和对应权限
	 * 
	 * @param delMap-工号
	 */
	private void deleSynUserAndDataPower(Map<String, String> delMap) {
		List<String> dataList = new ArrayList<String>();
		if (delMap.size() < 1)
			return;
		dataList.addAll(delMap.keySet());

		List<String> tmpList = new ArrayList<String>();
		int length = dataList.size();
		int curIndex = 0;
		for (int i = 0; ((i + 1) * BATCH_SIZE) < length;) {
			curIndex = (i + 1) * BATCH_SIZE;
			tmpList = dataList.subList(i * BATCH_SIZE, curIndex);
			i++;
			String uids = StringUtils.join(tmpList, ",");
			this.singleLoginDao.deleteSynUser(uids);
			this.singleLoginDao.deleteSynRoleRec(uids);
		}
		// 补刀
		if (curIndex < length) {
			String uids = StringUtils.join(dataList.subList(curIndex, length), ",");
			this.singleLoginDao.deleteSynUser(uids);
			this.singleLoginDao.deleteSynRoleRec(uids);
		}
	}

}

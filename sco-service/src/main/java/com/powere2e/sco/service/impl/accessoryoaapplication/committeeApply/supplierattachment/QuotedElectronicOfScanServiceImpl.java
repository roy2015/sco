package com.powere2e.sco.service.impl.accessoryoaapplication.committeeApply.supplierattachment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.committeeApply.supplierattachment.QuotedElectronicOfScanDao;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.committeeApply.supplierattachment.QuotedElectronicOfScanService;
import com.powere2e.sco.model.accessoryoaapplication.supplierattachment.QuotedElectronicOfScan;
import com.powere2e.sco.service.impl.accessoryoaapplication.CommitteeApplyServiceImpl;

/**
 * 关联辅料报价单-电子和扫描--辅料OA业务类的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月5日
 */
public class QuotedElectronicOfScanServiceImpl extends ServiceImpl implements QuotedElectronicOfScanService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3771560103877053685L;
	private QuotedElectronicOfScanDao quotedElectronicOfScanDao;

	public static QuotedElectronicOfScanService getInstance() {
		return (QuotedElectronicOfScanService) ConfigFactory.getInstance().getBean("quotedElectronicOfScanService");
	}

	// 获得关联辅料报价单-电子和扫描--辅料OADAO实例
	public QuotedElectronicOfScanDao getQuotedElectronicOfScanDao() {
		return quotedElectronicOfScanDao;
	}

	// 设置关联辅料报价单-电子和扫描--辅料OADAO实例
	public void setQuotedElectronicOfScanDao(QuotedElectronicOfScanDao quotedElectronicOfScanDao) {
		this.quotedElectronicOfScanDao = quotedElectronicOfScanDao;
	}

	// 查询
	@Override
	public List<QuotedElectronicOfScan> listQuotedElectronicOfScan(Map<String, Object> map, PageInfo pageInfo) {
		return this.getQuotedElectronicOfScanDao().listQuotedElectronicOfScan(map, pageInfo);
	}

	// 添加
	@Override
	public void insertQuotedElectronicOfScan(Map<String, Object> map) {
		this.getQuotedElectronicOfScanDao().insertQuotedElectronicOfScan(map);
	}

	// 删除
	@Override
	public void deleteQuotedElectronicOfScan(Map<String, Object> map) {
		this.getQuotedElectronicOfScanDao().deleteQuotedElectronicOfScan(map);
	}

	// 删除
	@Override
	public void deleteQuotedElectronicOfScanByCode(String applicationCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		this.getQuotedElectronicOfScanDao().deleteQuotedElectronicOfScanByCode(map);
	}

	// 修改
	@Override
	public void updateQuotedElectronicOfScan(Map<String, Object> map) {
		this.validateOaStatus(map);
		this.getQuotedElectronicOfScanDao().updateQuotedElectronicOfScan(map);
	}

	// 加载一个关联辅料报价单-电子和扫描--辅料OA
	@Override
	public QuotedElectronicOfScan loadQuotedElectronicOfScan(String applicationCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		return this.getQuotedElectronicOfScanDao().loadQuotedElectronicOfScan(map);
	}

	// 检查缺少文件
	@Override
	public boolean searchCount(String applicationCode) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		List<String> list = this.getQuotedElectronicOfScanDao().searchCount(map, null);
		if (list.size() != 0) {
			for (String i : list) {
				if (i == null) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * 根据申请单的状态来显示不同的消息
	 * 
	 * @param map
	 *            : <li>quotedCodes 所选择的意向品编号和供应商编号组</li> <li>applicationCode
	 *            新品引进OA申请单号</li>
	 */
	@Override
	public void validateOaStatus(Map<String, Object> map) {
		// 报价单
		Object quoCode = map.get("quotedCodes");
		String quotedCodes = (quoCode == null ? null : quoCode.toString());
		// 申请单号
		Object appCode = map.get("applicationCode");
		String applicationCode = (appCode == null ? null : appCode.toString());

		String applicationType = "";
		String type = map.get("type").toString();
		if (type.equals("ac")) {
			applicationType = BusinessConstants.ApplicationType.ACCESSORY_CGWYHJJD.toString();
		} else if (type.equals("af")) {
			applicationType = BusinessConstants.ApplicationType.ACCESSORY_FSPJJD.toString();
		} else if (type.equals("ax")) {
			applicationType = BusinessConstants.ApplicationType.ACCESSORY_XJBJ.toString();
		}
		Boolean flag = CommitteeApplyServiceImpl.getInstance().committeeInsertUpdateDeleteIsOk(quotedCodes, applicationCode, applicationType);
		if (!flag) {
			throw new EscmException("报价单在其他申请单中或者当前申请单状态下不可修改!");
		}
	}
}
package com.powere2e.sco.service.impl.accessoryoaapplication.committeeApply.supplierattachment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.FileUtils;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.committeeApply.supplierattachment.SupplierAttachmentADao;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.committeeApply.supplierattachment.SupplierAttachmentAService;
import com.powere2e.sco.model.accessoryoaapplication.supplierattachment.SupplierAttachmentA;
import com.powere2e.sco.service.impl.accessoryoaapplication.CommitteeApplyServiceImpl;

/**
 * 供应商证件--辅料OA业务类的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月4日
 */
public class SupplierAttachmentAServiceImpl extends ServiceImpl implements SupplierAttachmentAService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3929642865742862086L;
	private SupplierAttachmentADao supplierAttachmentADao;

	public static SupplierAttachmentAService getInstance() {
		return (SupplierAttachmentAService) ConfigFactory.getInstance().getBean("supplierAttachmentAService");
	}

	// 获得供应商证件--辅料OADAO实例
	public SupplierAttachmentADao getSupplierAttachmentADao() {
		return supplierAttachmentADao;
	}

	// 设置供应商证件--辅料OADAO实例
	public void setSupplierAttachmentADao(SupplierAttachmentADao supplierAttachmentADao) {
		this.supplierAttachmentADao = supplierAttachmentADao;
	}

	// 查询
	@Override
	public List<SupplierAttachmentA> listSupplierAttachmentA(Map<String, Object> map, PageInfo pageInfo) {
		return this.getSupplierAttachmentADao().listSupplierAttachmentA(map, pageInfo);
	}

	// 查询报价列表
	@Override
	public List<SupplierAttachmentA> listQuotedRecord(Map<String, Object> map, PageInfo pageInfo) {
		return this.getSupplierAttachmentADao().listQuotedRecord(map, pageInfo);
	}

	// 添加
	@Override
	public void insertSupplierAttachmentA(SupplierAttachmentA supplierAttachmentA) {

		this.getSupplierAttachmentADao().insertSupplierAttachmentA(supplierAttachmentA.toMap());
	}

	// 删除
	@Override
	public void deleteSupplierAttachmentA(Map<String, Object> map) {
		this.getSupplierAttachmentADao().deleteSupplierAttachmentA(map);
		String[] paths = map.get("paths").toString().split(",");
		for (int i = 0; i < paths.length; i++) {
			FileUtils.deleteFile(ConfigPath.getUploadFilePath().concat(paths[i]));
		}
	}

	// 删除
	@Override
	public void deleteSupplierAttachmentAByCode(String applicationCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		this.getSupplierAttachmentADao().deleteSupplierAttachmentAByCode(map);
		List<SupplierAttachmentA> info = this.listSupplierAttachmentA(map, null);
		for (int i = 0; i < info.size(); i++) {
			FileUtils.deleteFile(ConfigPath.getUploadFilePath().concat(info.get(i).getPath()));
		}
	}

	// 加载一个供应商证件--辅料OA
	@Override
	public SupplierAttachmentA loadSupplierAttachmentA(Map<String, Object> map) {
		return this.getSupplierAttachmentADao().loadSupplierAttachmentA(map);
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
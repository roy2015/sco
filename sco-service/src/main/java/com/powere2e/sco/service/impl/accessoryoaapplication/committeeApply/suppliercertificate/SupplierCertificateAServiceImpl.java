package com.powere2e.sco.service.impl.accessoryoaapplication.committeeApply.suppliercertificate;

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
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.committeeApply.suppliercertificate.SupplierCertificateADao;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.committeeApply.suppliercertificate.SupplierCertificateAService;
import com.powere2e.sco.model.accessoryoaapplication.suppliercertificate.SupplierCertificateA;
import com.powere2e.sco.service.impl.accessoryoaapplication.CommitteeApplyServiceImpl;

/**
 * 供应商证件(辅料OA)业务类的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月6日
 */
public class SupplierCertificateAServiceImpl extends ServiceImpl implements SupplierCertificateAService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1327761343807599566L;
	private SupplierCertificateADao supplierCertificateADao;

	public static SupplierCertificateAService getInstance() {
		return (SupplierCertificateAService) ConfigFactory.getInstance().getBean("supplierCertificateAService");
	}

	// 获得供应商证件(辅料OA)DAO实例
	public SupplierCertificateADao getSupplierCertificateADao() {
		return supplierCertificateADao;
	}

	// 设置供应商证件(辅料OA)DAO实例
	public void setSupplierCertificateADao(SupplierCertificateADao supplierCertificateADao) {
		this.supplierCertificateADao = supplierCertificateADao;
	}

	// 查询
	@Override
	public List<SupplierCertificateA> listSupplierCertificateA(Map<String, Object> map, PageInfo pageInfo) {
		return this.getSupplierCertificateADao().listSupplierCertificateA(map, pageInfo);
	}

	// 添加
	@Override
	public void insertSupplierCertificateA(Map<String, Object> map) {
		this.getSupplierCertificateADao().insertSupplierCertificateA(map);
	}

	// 删除
	@Override
	public void deleteSupplierCertificateA(Map<String, Object> map) {
		this.getSupplierCertificateADao().deleteSupplierCertificateA(map);
		String[] paths = map.get("paths").toString().split(",");
		for (int i = 0; i < paths.length; i++) {
			FileUtils.deleteFile(ConfigPath.getUploadFilePath().concat(paths[i]));
		}
	}

	// 删除
	@Override
	public void deleteSupplierCertificateAByCode(String applicationCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		this.getSupplierCertificateADao().deleteSupplierCertificateAByCode(map);
	}

	// 加载一个供应商证件(辅料OA)
	@Override
	public SupplierCertificateA loadSupplierCertificateA(String applicationCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		return this.getSupplierCertificateADao().loadSupplierCertificateA(map);
	}

	// 查询辅料建议文件
	@Override
	public boolean searchCount(String applicationCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		List<String> list = this.getSupplierCertificateADao().searchCount(map, null);
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
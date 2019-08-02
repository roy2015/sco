package com.powere2e.sco.service.impl.merchandiseoaapplication.reportnewoaapplication.suppliercertificate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.service.merchandiseoaapplication.MerchandiseOaApplicationUtil;
import com.powere2e.sco.common.utils.FileUtils;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportnewoaapplication.suppliercertificate.SupplierCertificateMDao;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportnewoaapplication.suppliercertificate.SupplierCertificateMService;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.suppliercertificate.SupplierCertificateM;
import com.powere2e.sco.service.impl.merchandiseoaapplication.MerchandiseOaApplicationServiceImpl;

/**
 * 供应商证件业务类的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月23日
 */
public class SupplierCertificateMServiceImpl extends ServiceImpl implements SupplierCertificateMService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -471480083853777862L;
	private SupplierCertificateMDao supplierCertificateMDao;

	public static SupplierCertificateMService getInstance() {
		return (SupplierCertificateMService) ConfigFactory.getInstance().getBean("supplierCertificateMService");
	}

	// 获得供应商证件DAO实例
	public SupplierCertificateMDao getSupplierCertificateMDao() {
		return supplierCertificateMDao;
	}

	// 设置供应商证件DAO实例
	public void setSupplierCertificateMDao(SupplierCertificateMDao supplierCertificateMDao) {
		this.supplierCertificateMDao = supplierCertificateMDao;
	}

	// 查询
	@Override
	public List<SupplierCertificateM> listSupplierCertificateM(Map<String, Object> map, PageInfo pageInfo) {
		return this.getSupplierCertificateMDao().listSupplierCertificateM(map, pageInfo);
	}

	// 加载一个供应商证件
	@Override
	public SupplierCertificateM loadSupplierCertificateM(String applicationCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		return this.getSupplierCertificateMDao().loadSupplierCertificateM(map);
	}

	// 添加供应商证件
	@Override
	public void insertSupplierCertificateM(Map<String, Object> map) throws Exception {

		this.validateOaStatus(map);
		this.getSupplierCertificateMDao().insertSupplierCertificateM(map);
	}

	// 删除供应商证件
	@Override
	public void deleteSupplierCertificateM(Map<String, Object> map) throws Exception {

		this.validateOaStatus(map);
		this.getSupplierCertificateMDao().deleteSupplierCertificateMs(map);
		String[] paths = map.get("paths").toString().split(",");
		for (int i = 0; i < paths.length; i++) {
			FileUtils.deleteFile(ConfigPath.getUploadFilePath().concat(paths[i]));
		}
	}

	/**
	 * 根据申请单的状态来显示不同的消息
	 * 
	 * @param map
	 *            : <li>applicationCode 新品引进OA申请单号</li> <li>
	 *            intentionAndSupplierCodes 所选择的意向品编号和供应商编号组</li>
	 */
	@Override
	public void validateOaStatus(Map<String, Object> map) throws Exception {
		Object appCode = map.get("applicationCode");
		String applicationCode = (appCode == null ? null : appCode.toString());
		Object intenSupCode = map.get("intentionAndSupplierCodes");
		String intentionAndSupplierCodes = (intenSupCode == null ? null : intenSupCode.toString());
		Object appType = map.get("applicationType");
		String applicationType = (appType == null ? null : appType.toString());
		if (applicationType != null) {
			if (applicationType.equals("mn")) {
				applicationType = BusinessConstants.ApplicationType.MERCHANDISE_NEW.toString();
			} else if (applicationType.equals("mo")) {
				applicationType = BusinessConstants.ApplicationType.MERCHANDISE_OLDUP.toString();
			} else if (applicationType.equals("ma")) {
				applicationType = BusinessConstants.ApplicationType.MERCHANDISE_ADJUSTPRICE.toString();
			}
		} else {
			applicationType = "";
		}
		String oaStatus = MerchandiseOaApplicationServiceImpl.getInstance().getIntentionOaApplicationReceiptInfo(applicationCode, intentionAndSupplierCodes, applicationType);
		MerchandiseOaApplicationUtil.responseMessage(oaStatus);
	}
}
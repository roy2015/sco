package com.powere2e.sco.service.impl.merchandiseoaapplication.reportnewoaapplication.supplierattachment;

import java.util.ArrayList;
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
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportnewoaapplication.supplierattachment.SupplierAttachmentMDao;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportnewoaapplication.supplierattachment.SupplierAttachmentMService;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.supplierattachment.SupplierAttachmentM;
import com.powere2e.sco.service.impl.merchandiseoaapplication.MerchandiseOaApplicationServiceImpl;

/**
 * 供应商附件业务类的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月21日
 */
public class SupplierAttachmentMServiceImpl extends ServiceImpl implements SupplierAttachmentMService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -316773616048483411L;
	private SupplierAttachmentMDao supplierAttachmentMDao;

	public static SupplierAttachmentMService getInstance() {
		return (SupplierAttachmentMService) ConfigFactory.getInstance().getBean("supplierAttachmentMService");
	}

	// 获得供应商附件DAO实例
	public SupplierAttachmentMDao getSupplierAttachmentMDao() {
		return supplierAttachmentMDao;
	}

	// 设置供应商附件DAO实例
	public void setSupplierAttachmentMDao(SupplierAttachmentMDao supplierAttachmentMDao) {
		this.supplierAttachmentMDao = supplierAttachmentMDao;
	}

	// 查询
	@Override
	public List<SupplierAttachmentM> listSupplierAttachmentM(Map<String, Object> map, PageInfo pageInfo) {
		return this.getSupplierAttachmentMDao().listSupplierAttachmentM(map, pageInfo);
	}

	// 查询
	@Override
	public List<ApplicationLackFileM> listAttachmentLackInfo(String applicationCode, String applicationType) {
		List<ApplicationLackFileM> list = new ArrayList<ApplicationLackFileM>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		String[] row = { "新品信息登记表", "新供应商信息登记表", "感官标准" };
		for (int i = 0; i < row.length; i++) {
			if (applicationType.equals("MERCHANDISE_NEW")) {
				map.put("lackFileName", row[i]);
				List<ApplicationLackFileM> applicationLackFileM = this.getSupplierAttachmentMDao().listApplicationLackFileM(map);
				list.addAll(applicationLackFileM);
			}
			if (i == 2 && !applicationType.equals("MERCHANDISE_NEW")) {
				map.put("lackFileName", row[i]);
				List<ApplicationLackFileM> applicationLackFileM = this.getSupplierAttachmentMDao().listApplicationLackFileM(map);
				list.addAll(applicationLackFileM);
			}
		}
		return list;
	}

	// 查询商品投料核算
	@Override
	public List<SupplierAttachmentM> listSupplierAttachmentMInFrom(Map<String, Object> map, PageInfo pageInfo) {
		return this.getSupplierAttachmentMDao().listSupplierAttachmentMInFrom(map, pageInfo);
	}

	// 加载一个供应商附件
	@Override
	public List<SupplierAttachmentM> loadSupplierAttachmentM(SupplierAttachmentM supplierAttachmentM) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = supplierAttachmentM.toMap();
		return this.getSupplierAttachmentMDao().loadSupplierAttachmentM(map);
	}

	@Override
	public List<SupplierAttachmentM> listIntentionSupplier(Map<String, Object> map, PageInfo pageInfo) {

		return this.getSupplierAttachmentMDao().listIntentionSupplier(map, pageInfo);
	}

	@Override
	public List<SupplierAttachmentM> listMerchandiseSupplier(Map<String, Object> map, PageInfo pageInfo) {

		return this.getSupplierAttachmentMDao().listMerchandiseSupplier(map, pageInfo);
	}

	// 添加供应商附件
	@Override
	public void insertSupplierAttachmentM(Map<String, Object> map) throws Exception {
		this.validateOaStatus(map);
		this.getSupplierAttachmentMDao().insertSupplierAttachmentM(map);
	}

	// 多删除供应商附件
	@Override
	public void deleteSupplierAttachmentMs(Map<String, Object> map) throws Exception {
		this.validateOaStatus(map);
		this.getSupplierAttachmentMDao().deleteSupplierAttachmentMs(map);
		String[] paths = map.get("paths").toString().split(",");
		for (int i = 0; i < paths.length; i++) {
			FileUtils.deleteFile(ConfigPath.getUploadFilePath().concat(paths[i]));
		}
	}

	// 删除供应商附件
	@Override
	public void deleteSupplierAttachmentM(Map<String, Object> map) throws Exception {
		this.validateOaStatus(map);
		this.getSupplierAttachmentMDao().deleteSupplierAttachmentM(map);
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
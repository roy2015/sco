package com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportnewoaapplication.supplierattachment;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.supplierattachment.SupplierAttachmentM;

/**
 * 供应商附件Service接口
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月21日
 */
public interface SupplierAttachmentMService extends Service {
	/**
	 * 供应商附件查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回供应商附件列表
	 */
	public List<SupplierAttachmentM> listSupplierAttachmentM(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 意向品，供应商查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回供应商附件列表
	 */
	public List<SupplierAttachmentM> listIntentionSupplier(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 根据ID号加载一个供应商附件
	 *
	 * @param map
	 *
	 * @return
	 */
	public List<SupplierAttachmentM> loadSupplierAttachmentM(SupplierAttachmentM supplierAttachmentM);

	/**
	 * 添加供应商附件(新品引进)
	 *
	 * @param map
	 *
	 */
	public void insertSupplierAttachmentM(Map<String, Object> map) throws Exception;

	/**
	 * 删除供应商附件(新品引进)
	 */
	public void deleteSupplierAttachmentMs(Map<String, Object> map) throws Exception;

	/**
	 * 删除供应商附件(新品引进)
	 */
	public void deleteSupplierAttachmentM(Map<String, Object> map) throws Exception;

	/**
	 * 查询商品投料核算
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SupplierAttachmentM> listSupplierAttachmentMInFrom(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查询丢失信息
	 * 
	 * @param application
	 * @return
	 */
	public List<ApplicationLackFileM> listAttachmentLackInfo(String applicationCode, String applicationType);

	/**
	 * 商品查询
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SupplierAttachmentM> listMerchandiseSupplier(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 根据申请单的状态来显示不同的消息
	 * 
	 * @param map
	 *            : <li>applicationCode 新品引进OA申请单号</li> <li>
	 *            intentionAndSupplierCodes 所选择的意向品编号和供应商编号组</li>
	 */
	public void validateOaStatus(Map<String, Object> map) throws Exception;
}
package com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportnewoaapplication.supplierattachment;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.supplierattachment.SupplierAttachmentM;

/**
 * 供应商附件DAO接口
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月21日
 */
public interface SupplierAttachmentMDao extends Dao {
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
	 * 商品投料核算查询
	 *
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回供应商附件列表
	 */
	public List<SupplierAttachmentM> listSupplierAttachmentMInFrom(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 供应商附件查询
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
	public List<SupplierAttachmentM> loadSupplierAttachmentM(Map<String, Object> map);

	/**
	 * 添加供应商附件
	 * 
	 * @param map
	 */
	public void insertSupplierAttachmentM(Map<String, Object> map);

	/**
	 * 删除供应商附件(新品引进)
	 */
	public void deleteSupplierAttachmentMs(Map<String, Object> map);

	/**
	 * 删除供应商附件(新品引进)
	 */
	public void deleteSupplierAttachmentM(Map<String, Object> map);

	/**
	 * 根据申请单号删除供应商附件(新品引进)
	 * 
	 * author:joyce.li
	 */
	public void deleteSupplierAttachmentByCode(Map<String, Object> map);

	/**
	 * 
	 * @param map
	 * @return
	 */
	public List<ApplicationLackFileM> listApplicationLackFileM(Map<String, Object> map);

	/**
	 * 查询商品
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SupplierAttachmentM> listMerchandiseSupplier(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查询本次oa申请的商品信息
	 * 
	 * @param map
	 * @return
	 */
	public List<ApplicationLackFileM> listApplicationInfo(Map<String, Object> map);

}
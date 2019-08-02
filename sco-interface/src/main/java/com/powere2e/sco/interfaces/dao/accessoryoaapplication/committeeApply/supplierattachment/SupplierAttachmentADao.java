package com.powere2e.sco.interfaces.dao.accessoryoaapplication.committeeApply.supplierattachment;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.accessoryoaapplication.supplierattachment.SupplierAttachmentA;

/**
 * 供应商证件--辅料OADAO接口
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月4日
 */
public interface SupplierAttachmentADao extends Dao {
	/**
	 * 供应商证件--辅料OA查询
	 *
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回供应商证件--辅料OA列表
	 */
	public List<SupplierAttachmentA> listSupplierAttachmentA(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 根据ID号加载一个供应商证件--辅料OA
	 *
	 * @param map
	 *
	 * @return
	 */
	public SupplierAttachmentA loadSupplierAttachmentA(Map<String, Object> map);

	/**
	 * 添加供应商证件--辅料OA
	 *
	 * @param map
	 *
	 */
	public void insertSupplierAttachmentA(Map<String, Object> map);

	/**
	 * 删除供应商证件--辅料OA
	 *
	 * @param map
	 *            必须参数id为要删除的供应商证件--辅料OAid号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteSupplierAttachmentA(Map<String, Object> map);

	/**
	 * 查询报价列表
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SupplierAttachmentA> listQuotedRecord(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 删除--根据oa申请单号
	 * 
	 * @param map
	 */
	public void deleteSupplierAttachmentAByCode(Map<String, Object> map);
}
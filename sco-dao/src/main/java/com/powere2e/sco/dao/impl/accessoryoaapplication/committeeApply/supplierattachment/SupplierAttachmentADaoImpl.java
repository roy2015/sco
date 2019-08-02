package com.powere2e.sco.dao.impl.accessoryoaapplication.committeeApply.supplierattachment;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.committeeApply.supplierattachment.SupplierAttachmentADao;
import com.powere2e.sco.model.accessoryoaapplication.supplierattachment.SupplierAttachmentA;

/**
 * 供应商证件--辅料OADAO接口的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月4日
 */
public class SupplierAttachmentADaoImpl extends DaoImpl implements SupplierAttachmentADao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8113336173981764421L;

	// 查询
	@Override
	public List<SupplierAttachmentA> listSupplierAttachmentA(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SupplierAttachmentADao.class, "searchSupplierAttachmentA", map, pageInfo);
	}

	// 查询报价列表
	@Override
	public List<SupplierAttachmentA> listQuotedRecord(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SupplierAttachmentADao.class, "searchQuotedRecord", map, pageInfo);
	}

	// 添加
	@Override
	public void insertSupplierAttachmentA(Map<String, Object> map) {
		this.insert(SupplierAttachmentADao.class, "saveSupplierAttachmentA", map);
	}

	// 删除
	@Override
	public void deleteSupplierAttachmentA(Map<String, Object> map) {
		this.delete(SupplierAttachmentADao.class, "deleteSupplierAttachmentA", map);
	}

	// 删除--根据oa申请单号
	@Override
	public void deleteSupplierAttachmentAByCode(Map<String, Object> map) {
		this.delete(SupplierAttachmentADao.class, "deleteSupplierAttachmentAByCode", map);
	}

	// 装载一个供应商证件--辅料OA
	@Override
	public SupplierAttachmentA loadSupplierAttachmentA(Map<String, Object> map) {
		return (SupplierAttachmentA) this.get(SupplierAttachmentADao.class, "searchSupplierAttachmentA", map);
	}
}
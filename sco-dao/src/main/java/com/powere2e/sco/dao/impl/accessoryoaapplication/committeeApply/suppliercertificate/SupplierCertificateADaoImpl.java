package com.powere2e.sco.dao.impl.accessoryoaapplication.committeeApply.suppliercertificate;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.committeeApply.suppliercertificate.SupplierCertificateADao;
import com.powere2e.sco.model.accessoryoaapplication.suppliercertificate.SupplierCertificateA;

/**
 * 供应商证件(辅料OA)DAO接口的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月6日
 */
public class SupplierCertificateADaoImpl extends DaoImpl implements SupplierCertificateADao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 709303782961777402L;

	// 查询
	@Override
	public List<SupplierCertificateA> listSupplierCertificateA(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SupplierCertificateADao.class, "searchSupplierCertificateA", map, pageInfo);
	}

	// 查询证件个数
	@Override
	public List<String> searchCount(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SupplierCertificateADao.class, "searchCount", map, pageInfo);
	}

	// 添加
	@Override
	public void insertSupplierCertificateA(Map<String, Object> map) {
		this.insert(SupplierCertificateADao.class, "saveSupplierCertificateA", map);
	}

	// 删除
	@Override
	public void deleteSupplierCertificateA(Map<String, Object> map) {
		this.delete(SupplierCertificateADao.class, "deleteSupplierCertificateA", map);
	}

	// 删除--根据oa申请单号
	@Override
	public void deleteSupplierCertificateAByCode(Map<String, Object> map) {
		this.delete(SupplierCertificateADao.class, "deleteSupplierCertificateAByCode", map);
	}

	// 修改
	@Override
	public void updateSupplierCertificateA(Map<String, Object> map) {
		this.update(SupplierCertificateADao.class, "updateSupplierCertificateA", map);
	}

	// 装载一个供应商证件(辅料OA)
	@Override
	public SupplierCertificateA loadSupplierCertificateA(Map<String, Object> map) {
		return (SupplierCertificateA) this.get(SupplierCertificateADao.class, "searchSupplierCertificateA", map);
	}
}
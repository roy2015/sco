package com.powere2e.sco.dao.impl.datamaintenance.systemmanagerdata.suppliercertificatetype;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.datamaintenance.systemmanagerdata.suppliercertificatetype.SupplierCertificateTypeDao;
import com.powere2e.sco.model.datamaintenance.systemmanagerdata.suppliercertificatetype.SupplierCertificateType;

/**
 * 证件名称DAO接口的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月17日
 */
public class SupplierCertificateTypeDaoImpl extends DaoImpl implements SupplierCertificateTypeDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2541072885059344601L;

	// 查询
	@Override
	public List<SupplierCertificateType> listSupplierCertificateType(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SupplierCertificateTypeDao.class, "searchSupplierCertificateType", map, pageInfo);
	}

	// 添加
	@Override
	public void insertSupplierCertificateType(Map<String, Object> map) {
		this.insert(SupplierCertificateTypeDao.class, "saveSupplierCertificateType", map);
	}

	// 删除
	@Override
	public void deleteSupplierCertificateType(Map<String, Object> map) {
		this.delete(SupplierCertificateTypeDao.class, "deleteSupplierCertificateType", map);
	}

	// 装载一个证件名称
	@Override
	public SupplierCertificateType loadSupplierCertificateType(Map<String, Object> map) {
		return (SupplierCertificateType) this.get(SupplierCertificateTypeDao.class, "loadSupplierCertificateType", map);
	}

	// 查询供应商中存在该证件编号的数量
	@Override
	public Integer serachSupplierCertificate(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (Integer) this.get(SupplierCertificateTypeDao.class, "searchSupplierCertificate", map);
	}
}
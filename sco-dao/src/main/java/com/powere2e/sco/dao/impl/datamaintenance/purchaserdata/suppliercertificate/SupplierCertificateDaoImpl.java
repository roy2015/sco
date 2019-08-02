package com.powere2e.sco.dao.impl.datamaintenance.purchaserdata.suppliercertificate;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.suppliercertificate.SupplierCertificateDao;
import com.powere2e.sco.model.datamaintenance.purchaserdata.suppliercertificate.SupplierCertificate;
import com.powere2e.security.model.Option;

/**
 * 供应商证件 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月10日
 */
public class SupplierCertificateDaoImpl extends DaoImpl implements SupplierCertificateDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2819846029653721190L;

	// 查询
	@Override
	public List<SupplierCertificate> listSupplierCertificate(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SupplierCertificateDao.class, "listSupplierCertificate", map, pageInfo);
	}

	// 查询
	@Override
	public List<SupplierCertificate> listSupplierCertificateAll(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SupplierCertificateDao.class, "listSupplierCertificateAll", map, pageInfo);
	}

	// 根据参数查询
	@Override
	public SupplierCertificate loadSupplierCertificate(Map<String, Object> map) {
		return (SupplierCertificate) this.get(SupplierCertificateDao.class, "loadSupplierCertificate", map);
	}

	// 添加
	@Override
	public void insertSupplierCertificate(Map<String, Object> map) {
		this.insert(SupplierCertificateDao.class, "insertSupplierCertificate", map);
	}

	@Override
	public void deleteSupplierCertificate(Map<String, Object> map) {
		this.delete(SupplierCertificateDao.class, "deleteSupplierCertificate", map);
	}

	@Override
	public List<Option> listSupplierCertificateType() {
		return this.query(SupplierCertificateDao.class, "listSupplierCertificateType", null, null);
	}

	@Override
	public void deleteIntetionSupplierCertificate(Map<String, Object> map) {
		this.delete(SupplierCertificateDao.class, "deleteIntetionSupplierCertificate", map);
	}

	@Override
	public void deleteAcessorySupplierCertificate(Map<String, Object> map) {
		this.delete(SupplierCertificateDao.class, "deleteAcessorySupplierCertificate", map);
	}

	@Override
	public SupplierCertificate loadCertificateByCode(Map<String, Object> map) {
		return (SupplierCertificate) get(SupplierCertificateDao.class,
				"loadCertificateByCode", map);
	}

}
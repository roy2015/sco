package com.powere2e.sco.service.impl.datamaintenance.systemmanagerdata.suppliercertificatetype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.datamaintenance.systemmanagerdata.suppliercertificatetype.SupplierCertificateTypeDao;
import com.powere2e.sco.interfaces.service.datamaintenance.systemmanagerdata.suppliercertificatetype.SupplierCertificateTypeService;
import com.powere2e.sco.model.datamaintenance.systemmanagerdata.suppliercertificatetype.SupplierCertificateType;

/**
 * 证件名称业务类的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月17日
 */
public class SupplierCertificateTypeServiceImpl extends ServiceImpl implements SupplierCertificateTypeService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7184196007058411341L;
	private SupplierCertificateTypeDao supplierCertificateTypeDao;

	public static SupplierCertificateTypeService getInstance() {
		return (SupplierCertificateTypeService) ConfigFactory.getInstance().getBean("supplierCertificateTypeService");
	}

	// 获得证件名称DAO实例
	public SupplierCertificateTypeDao getSupplierCertificateTypeDao() {
		return supplierCertificateTypeDao;
	}

	// 设置证件名称DAO实例
	public void setSupplierCertificateTypeDao(SupplierCertificateTypeDao supplierCertificateTypeDao) {
		this.supplierCertificateTypeDao = supplierCertificateTypeDao;
	}

	// 查询
	@Override
	public List<SupplierCertificateType> listSupplierCertificateType(Map<String, Object> map, PageInfo pageInfo) {
		return this.getSupplierCertificateTypeDao().listSupplierCertificateType(map, pageInfo);
	}

	// 添加
	@Override
	public void insertSupplierCertificateType(SupplierCertificateType supplierCertificateType) {
		this.getSupplierCertificateTypeDao().insertSupplierCertificateType(supplierCertificateType.toMap());
	}

	// 删除
	@Override
	public void deleteSupplierCertificateType(String certificateTypeCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("certificateTypeCode", certificateTypeCode.split(","));
		this.getSupplierCertificateTypeDao().deleteSupplierCertificateType(map);
	}

	// 加载一个证件名称
	@Override
	public SupplierCertificateType loadSupplierCertificateType(String certificateTypeName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("certificateTypeName", certificateTypeName);
		return this.getSupplierCertificateTypeDao().loadSupplierCertificateType(map);
	}

	// 根据编号查询证件
	@Override
	public Integer serachSupplierCertificate(String certificateTypeCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("certificateTypeCode", certificateTypeCode);
		return this.getSupplierCertificateTypeDao().serachSupplierCertificate(map);
	}
}
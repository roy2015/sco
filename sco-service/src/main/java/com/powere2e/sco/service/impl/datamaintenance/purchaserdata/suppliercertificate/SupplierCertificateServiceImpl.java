package com.powere2e.sco.service.impl.datamaintenance.purchaserdata.suppliercertificate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.suppliercertificate.SupplierCertificateDao;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.suppliercertificate.SupplierCertificateService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.suppliercertificate.SupplierCertificate;
import com.powere2e.sco.service.impl.common.MasterDataTypeServiceImpl;
import com.powere2e.security.model.Option;

/**
 * 供应商证件 业务类的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月10日
 */
public class SupplierCertificateServiceImpl extends ServiceImpl implements SupplierCertificateService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7444457131945486652L;
	private SupplierCertificateDao supplierCertificateDao;

	/**
	 * 添加获取供应商证件service实例方法供其他模块调用
	 * 
	 * @return 供应商证件service实例
	 */
	public static SupplierCertificateService getInstance() {
		return (SupplierCertificateService) ConfigFactory.getInstance()
				.getBean("supplierCertificateService");
	}

	// 获得供应商证件 DAO实例
	public SupplierCertificateDao getSupplierCertificateDao() {
		return supplierCertificateDao;
	}

	// 设置供应商证件 DAO实例
	public void setSupplierCertificateDao(SupplierCertificateDao supplierCertificateDao) {
		this.supplierCertificateDao = supplierCertificateDao;
	}

	// 查询供应商证件(包括商品意向和辅料供应商)
	@Override
	public List<SupplierCertificate> listSupplierCertificate(Map<String, Object> map, PageInfo pageInfo) {
		return this.getSupplierCertificateDao().listSupplierCertificate(map, pageInfo);
	}
	
	// 查询
	@Override
	public List<SupplierCertificate> listSupplierCertificateAll(Map<String, Object> map, PageInfo pageInfo) {
		return this.getSupplierCertificateDao().listSupplierCertificateAll(map, pageInfo);
	}
	
	// 添加
	@Override
	public void insertSupplierCertificate(SupplierCertificate supplierCertificate) {
		supplierCertificate.setCertificateCode(MasterDataTypeServiceImpl.getInstance().nextID("s_supplier_certificate"));// 文件主键
		if ("Y".equalsIgnoreCase(supplierCertificate.getIsperpetual())) {
			supplierCertificate.setEndDate(null);// 如果是有效期是永久，则有些日期为Null
		}
		this.getSupplierCertificateDao().insertSupplierCertificate(supplierCertificate.toMap());
	}

	@Override
	public void deleteSupplierCertificate(Map<String, Object> map) {
		this.getSupplierCertificateDao().deleteSupplierCertificate(map); // 删除供应商证件
		this.supplierCertificateDao.deleteIntetionSupplierCertificate(map); // 删除意向供应商证件
		this.supplierCertificateDao.deleteAcessorySupplierCertificate(map); // 删除辅料供应商证件
	}

	@Override
	public List<Option> listSupplierCertificateType() {
		return this.supplierCertificateDao.listSupplierCertificateType();
	}

	@Override
	public SupplierCertificate loadSupplierCertificate(Map<String, Object> map) {
		return this.supplierCertificateDao.loadSupplierCertificate(map);
	}

	@Override
	public SupplierCertificate loadCertificateByCode(String certificateCode) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("certificateCode", certificateCode);
		return this.supplierCertificateDao.loadCertificateByCode(map);
	}

}
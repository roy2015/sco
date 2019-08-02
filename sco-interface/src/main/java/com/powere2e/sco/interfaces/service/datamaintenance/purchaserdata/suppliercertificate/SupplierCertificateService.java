package com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.suppliercertificate;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.datamaintenance.purchaserdata.suppliercertificate.SupplierCertificate;
import com.powere2e.security.model.Option;

/**
 * 供应商证件 Service接口
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月10日
 */
public interface SupplierCertificateService extends Service {
	/**
	 * 供应商证件 查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回供应商证件 列表
	 */
	public List<SupplierCertificate> listSupplierCertificate(
			Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 供应商证件 查询
	 * 
	 * @param map
	 *            查询参数
	 * @return 返回供应商证件
	 */
	public SupplierCertificate loadSupplierCertificate(Map<String, Object> map);

	/**
	 * 添加供应商证件
	 * 
	 * @param supplierCertificate
	 *            证件实例
	 */
	public void insertSupplierCertificate(
			SupplierCertificate supplierCertificate);

	/**
	 * 删除供应商证件
	 * 
	 * @param map
	 *            证件实例
	 */
	public void deleteSupplierCertificate(Map<String, Object> map);

	/**
	 * 查询供应商证件类型
	 * 
	 * @return 供应商证件类型
	 */
	public List<Option> listSupplierCertificateType();

	/**
	 * 根据所有供应商查询证件
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 供应商证件信息
	 */
	public List<SupplierCertificate> listSupplierCertificateAll(
			Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 根据报表编号查询报表路径
	 * 
	 * @param certificateCode
	 *            报表编号
	 * @return 证件类型编号、证件所在相对路径
	 */
	public SupplierCertificate loadCertificateByCode(String certificateCode);
	
}
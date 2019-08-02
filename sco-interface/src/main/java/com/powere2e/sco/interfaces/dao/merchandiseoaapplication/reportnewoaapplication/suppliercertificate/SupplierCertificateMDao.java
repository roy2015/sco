package com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportnewoaapplication.suppliercertificate;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.suppliercertificate.SupplierCertificateM;

/**
 * 供应商证件DAO接口
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月23日
 */
public interface SupplierCertificateMDao extends Dao {
	/**
	 * 供应商证件查询
	 *
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回供应商证件列表
	 */
	public List<SupplierCertificateM> listSupplierCertificateM(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 根据ID号加载一个供应商证件
	 *
	 * @param map
	 *
	 * @return
	 */
	public SupplierCertificateM loadSupplierCertificateM(Map<String, Object> map);

	/**
	 * 添加供应商证件
	 * 
	 * @param map
	 */
	public void insertSupplierCertificateM(Map<String, Object> map);

	/**
	 * 删除供应商证件(新品引进)
	 */
	public void deleteSupplierCertificateMs(Map<String, Object> map);
}
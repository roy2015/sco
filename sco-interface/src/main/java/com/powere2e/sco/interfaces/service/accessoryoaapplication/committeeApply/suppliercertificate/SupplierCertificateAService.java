package com.powere2e.sco.interfaces.service.accessoryoaapplication.committeeApply.suppliercertificate;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.accessoryoaapplication.suppliercertificate.SupplierCertificateA;

/**
 * 供应商证件(辅料OA)Service接口
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月6日
 */
public interface SupplierCertificateAService extends Service {
	/**
	 * 供应商证件(辅料OA)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回供应商证件(辅料OA)列表
	 */
	public List<SupplierCertificateA> listSupplierCertificateA(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 根据ID号加载一个供应商证件(辅料OA)
	 *
	 * @param map
	 *
	 * @return
	 */
	public SupplierCertificateA loadSupplierCertificateA(String applicationCode);

	/**
	 *
	 *
	 * @param map
	 *
	 * @return
	 */
	public boolean searchCount(String applicationCode);

	/**
	 * 添加供应商证件(辅料OA)
	 *
	 * @param map
	 *
	 */
	public void insertSupplierCertificateA(Map<String, Object> map);

	/**
	 * 删除供应商证件(辅料OA)
	 *
	 * @param map
	 *
	 */
	public void deleteSupplierCertificateA(Map<String, Object> map);

	/**
	 * 查询丢失文件
	 * 
	 * @param map
	 */
	public void validateOaStatus(Map<String, Object> map);

	/**
	 * 删除--根据oa申请单号
	 * 
	 * @param map
	 */
	public void deleteSupplierCertificateAByCode(String applicationCode);
}
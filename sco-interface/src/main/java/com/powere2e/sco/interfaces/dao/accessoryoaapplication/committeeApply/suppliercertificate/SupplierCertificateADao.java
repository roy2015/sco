package com.powere2e.sco.interfaces.dao.accessoryoaapplication.committeeApply.suppliercertificate;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.accessoryoaapplication.suppliercertificate.SupplierCertificateA;

/**
 * 供应商证件(辅料OA)DAO接口
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月6日
 */
public interface SupplierCertificateADao extends Dao {
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
	public SupplierCertificateA loadSupplierCertificateA(Map<String, Object> map);

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
	 *            必须参数id为要删除的供应商证件(辅料OA)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteSupplierCertificateA(Map<String, Object> map);

	/**
	 * 修改供应商证件(辅料OA)
	 *
	 * @param map
	 *            必须参数id为要修改供应商证件(辅料OA)的id号，不能为数组
	 */
	public void updateSupplierCertificateA(Map<String, Object> map);

	/**
	 * 查询丢失文件
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<String> searchCount(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 删除=-根据oa申请单号
	 * 
	 * @param map
	 */
	public void deleteSupplierCertificateAByCode(Map<String, Object> map);
}
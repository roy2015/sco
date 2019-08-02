package com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportnewoaapplication.suppliercertificate;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.suppliercertificate.SupplierCertificateM;

/**
 * 供应商证件Service接口
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月23日
 */
public interface SupplierCertificateMService extends Service {
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
	public SupplierCertificateM loadSupplierCertificateM(String applicationCode);

	/**
	 * 添加供应商证件
	 * 
	 * @param supplierCertificateM
	 */
	public void insertSupplierCertificateM(Map<String, Object> map) throws Exception;

	/**
	 * 删除
	 * 
	 * @param map
	 */
	public void deleteSupplierCertificateM(Map<String, Object> map) throws Exception;

	/**
	 * 根据申请单的状态来显示不同的消息
	 * 
	 * @param map
	 *            : <li>applicationCode 新品引进OA申请单号</li> <li>
	 *            intentionAndSupplierCodes 所选择的意向品编号和供应商编号组</li>
	 */
	public void validateOaStatus(Map<String, Object> map) throws Exception;
}
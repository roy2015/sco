package com.powere2e.sco.interfaces.service.accessoryoaapplication.committeeApply.supplierattachment;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.accessoryoaapplication.supplierattachment.QuotedElectronicOfScan;

/**
 * 关联辅料报价单-电子和扫描--辅料OAService接口
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月5日
 */
public interface QuotedElectronicOfScanService extends Service {
	/**
	 * 关联辅料报价单-电子和扫描--辅料OA查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回关联辅料报价单-电子和扫描--辅料OA列表
	 */
	public List<QuotedElectronicOfScan> listQuotedElectronicOfScan(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 根据ID号加载一个关联辅料报价单-电子和扫描--辅料OA
	 *
	 * @param map
	 *
	 * @return
	 */
	public QuotedElectronicOfScan loadQuotedElectronicOfScan(String applicationCode);

	/**
	 * 根据ID号加载一个关联辅料报价单-电子和扫描--辅料OA
	 *
	 * @param map
	 *
	 * @return
	 */
	public boolean searchCount(String applicationCode);

	/**
	 * 添加关联辅料报价单-电子和扫描--辅料OA
	 *
	 * @param map
	 *
	 */
	public void insertQuotedElectronicOfScan(Map<String, Object> map);

	/**
	 * 删除关联辅料报价单-电子和扫描--辅料OA
	 *
	 * @param map
	 *            必须参数id为要删除的关联辅料报价单-电子和扫描--辅料OAid号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteQuotedElectronicOfScan(Map<String, Object> map);

	/**
	 * 修改关联辅料报价单-电子和扫描--辅料OA
	 *
	 * @param map
	 *            必须参数id为要修改关联辅料报价单-电子和扫描--辅料OA的id号，不能为数组
	 */
	public void updateQuotedElectronicOfScan(Map<String, Object> map);

	/**
	 * 根据申请单号显示不同的信息
	 * 
	 * @param map
	 */
	public void validateOaStatus(Map<String, Object> map);

	/**
	 * 删除-根据oa申请单号
	 * 
	 * @param map
	 */
	public void deleteQuotedElectronicOfScanByCode(String applicationCode);
}
package com.powere2e.sco.interfaces.dao.accessoryoaapplication.committeeApply.supplierattachment;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.accessoryoaapplication.supplierattachment.QuotedElectronicOfScan;
/**
 * 关联辅料报价单-电子和扫描--辅料OADAO接口
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月5日
 */
public interface QuotedElectronicOfScanDao extends Dao {
	/**
	 * 关联辅料报价单-电子和扫描--辅料OA查询
	 *
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回关联辅料报价单-电子和扫描--辅料OA列表
	 */
	public List<QuotedElectronicOfScan> listQuotedElectronicOfScan(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个关联辅料报价单-电子和扫描--辅料OA
	 *
	 * @param map
	 *				
	 * @return
	 */
	public QuotedElectronicOfScan loadQuotedElectronicOfScan(Map<String,Object> map);
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
	 *				必须参数id为要删除的关联辅料报价单-电子和扫描--辅料OAid号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteQuotedElectronicOfScan(Map<String, Object> map);
	/**
	 * 修改关联辅料报价单-电子和扫描--辅料OA
	 *
	 * @param map 
	 *				必须参数id为要修改关联辅料报价单-电子和扫描--辅料OA的id号，不能为数组
	 */
	public void updateQuotedElectronicOfScan(Map<String, Object> map);
	/**
	 * 查询丢失文件
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<String> searchCount(Map<String, Object> map, PageInfo pageInfo);
	/**
	 * 根据oa申请单号删除
	 * @param map
	 */
	public void deleteQuotedElectronicOfScanByCode(Map<String, Object> map);
}
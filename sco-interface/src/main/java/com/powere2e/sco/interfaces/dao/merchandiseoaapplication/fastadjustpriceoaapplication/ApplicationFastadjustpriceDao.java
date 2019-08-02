package com.powere2e.sco.interfaces.dao.merchandiseoaapplication.fastadjustpriceoaapplication;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.merchandiseoaapplication.fastadjustpriceoaapplication.ApplicationFastadjustprice;

/**
 * 申请报告(快速调价)DAO接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月20日
 */
public interface ApplicationFastadjustpriceDao extends Dao {

	/**
	 * 查询没有报告的商品
	 */
	public List<ApplicationLackFileM> queryNotHaveReportMerchandiseFastadjustprice(Map<String, Object> map) throws Exception;

	/**
	 * 申请报告(快速调价)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回申请报告(快速调价)列表
	 */
	public List<ApplicationFastadjustprice> listApplicationFastadjustprice(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 根据ID号加载一个申请报告(快速调价)
	 * 
	 * @param map
	 * 
	 * @return
	 */
	public ApplicationFastadjustprice loadApplicationFastadjustprice(Map<String, Object> map) throws Exception;

	/**
	 * 添加申请报告(快速调价)
	 * 
	 * @param map
	 * 
	 */
	public void insertApplicationFastadjustprice(Map<String, Object> map) throws Exception;

	/**
	 * 根据申请单号和、申请文件名称、商品编号和供应商编号删除
	 * 
	 */
	public void deleteApplicationFastadjustprice(Map<String, Object> map) throws Exception;

	/**
	 * 查询申请文件的商品
	 */
	public List<ApplicationFastadjustprice> listApplicationFileMerchandise(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 查询申请文件列表
	 */
	public List<ApplicationFastadjustprice> listApplicationFiles(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 根据申请单号删除申请报告(快速调价)
	 */
	public void deleteFastadjustpriceByApplicationCode(Map<String, Object> map) throws Exception;

	/**
	 * 根据报告编号删除申请文件
	 */
	public void deleteApplicationFiles(Map<String, Object> map) throws Exception;
}
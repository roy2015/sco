package com.powere2e.sco.interfaces.service.merchandiseintention;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.merchandiseintention.MerchandiseQuoted;

/**
 * 商品意向品Service接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年3月10日
 */
public interface MerchandiseQuotedService extends Service {

	/* ======================录入报价单============================================== */
	/**
	 * 添加供应商报价单
	 */
	public void insertMerchandiseQuoted(Map<String, Object> map) throws Exception;

	/**
	 * 修改供应商报价单
	 */
	public void updateMerchandiseQuoted(Map<String, Object> map) throws Exception;

	/**
	 * 删除报价单(根据报价日期和供应商编号删除)
	 */
	public void deleteMerchandiseQuoted(Map<String, Object> map) throws Exception;

	/**
	 * 根据报价单id删除报价单
	 */
	public void deleteMerchandiseQuotedById(Map<String, Object> map) throws Exception;

	/**
	 * 从excel中导入报价单(该方法只在service中有，在dao中不存在)
	 */
	public String insertUploadQuotedFromExcel(File quotedFile, MerchandiseQuoted selectedQuoted) throws Exception;

	/**
	 * 供应商报价单查询
	 */
	public List<MerchandiseQuoted> listMerchandiseQuoted(Map<String, Object> map, PageInfo pageInfo) throws Exception;
	
	/**
	 * 供应商报价单查询(不分页,只查最晚的)
	 */
	public List<MerchandiseQuoted> listSupplierQuoted(Map<String, Object> map) throws Exception;

	/**
	 * 查询勾选的参照品的报价计量单位
	 */
	public List<MerchandiseQuoted> listRefMerchandiseQuoted(Map<String, Object> map) throws Exception;
	
	/**
	 * 根据ID号加载一个供应商报价单
	 */
	public MerchandiseQuoted loadMerchandiseQuoted(Map<String, Object> map) throws Exception;

	/**
	 * 新增报价单事务控制,先删除报价单，然后新增报价单(其中一个操作报错，就整个事务不提交) (该方法只在service中有，在dao中不存在)
	 * 
	 * @param map
	 *            删除报价单参数
	 * @param merchandiseQuoted
	 *            新增报价单参数
	 */
	public void insertQuotedTransactionControl(Map<String, Object> map, MerchandiseQuoted merchandiseQuoted) throws Exception;

	/**
	 * 从SAP或者最晚的报价单中查询公司相关信息
	 * 
	 * @param intentionSupplierCode
	 *            供应商编号
	 * @param merchandiseQuoted
	 *            最晚报价单实例
	 * @param map
	 *            查询参数
	 * @throws Exception
	 */
	public void searchCompanyInfoInSapAndLastQuoted(String intentionSupplierCode,
			MerchandiseQuoted merchandiseQuoted, Map<String, Object> map) throws Exception;

}
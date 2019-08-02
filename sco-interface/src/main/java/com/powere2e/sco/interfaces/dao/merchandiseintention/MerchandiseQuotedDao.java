package com.powere2e.sco.interfaces.dao.merchandiseintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.merchandiseintention.MerchandiseQuoted;

/**
 * 商品意向品DAO接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年3月10日
 */
public interface MerchandiseQuotedDao extends Dao {
	/* ======================录入报价单======================================== */
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

}
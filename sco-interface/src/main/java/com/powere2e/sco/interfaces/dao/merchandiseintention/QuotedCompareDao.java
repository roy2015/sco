package com.powere2e.sco.interfaces.dao.merchandiseintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseintention.MerchandiseQuoted;

/**
 * 商品意向品DAO接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年3月10日
 */
public interface QuotedCompareDao extends Dao {

	/* ======================报价比价====================================== */
	/**
	 * 分析已有报价记录
	 */
	public List<MerchandiseQuoted> analysisQuoted(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 查看供应商历史报价
	 */
	public List<MerchandiseQuoted> listHistoryQuoted(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 查看供应商针对某个意向品的最晚报价
	 */
	public MerchandiseQuoted queryLastQuoted(Map<String, Object> map) throws Exception;

	/**
	 * 查看对比结果
	 */
	public List<MerchandiseQuoted> listCompareResult(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 查看对比商品
	 */
	public List<MerchandiseIntention> listCompareMerchandise(Map<String, Object> map, PageInfo pageInfo) throws Exception;

}
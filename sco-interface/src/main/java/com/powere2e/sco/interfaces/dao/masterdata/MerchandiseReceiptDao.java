package com.powere2e.sco.interfaces.dao.masterdata;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.masterdata.MerchandiseReceipt;

/**
 * 商品收货单信息DAO接口
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年5月13日
 */
public interface MerchandiseReceiptDao extends Dao {
	
	/**
	 * 查询商品收货单数据
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回列表
	 */
	public List<MerchandiseReceipt> listMerchandiseReceipt(
			Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查询今天各商品收货总数
	 * 
	 * @param map
	 *            查询参数
	 * @return 商品编号及收货总数
	 */
	public List<MerchandiseReceipt> listSumTodayMerchandiseReceipt(
			Map<String, Object> map);
	
	/**
	 * 添加
	 * 
	 * @param map
	 *            商品收货单实例
	 */
	public void insertMerchandiseReceipt(Map<String, Object> map);

	/**
	 * 删除
	 * 
	 * @param map
	 *            数据主键ID
	 */
	public void deleteMerchandiseReceipt(Map<String, Object> map);

}
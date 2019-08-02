package com.powere2e.sco.interfaces.service.masterdata;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.masterdata.MerchandiseReceipt;

/**
 * 商品收货单信息 Service接口
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年5月13日
 */
public interface MerchandiseReceiptService extends Service {

	/**
	 * 查询商品收货单信息
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
	 * @return 商品编号及收货信息
	 */
	public List<MerchandiseReceipt> listSumTodayMerchandiseReceipt(Map<String, Object> map);
	
	/**
	 * 添加商品收货单
	 * 
	 * @param map
	 *            商品数据实例
	 */
	public void insertMerchandiseReceipt(MerchandiseReceipt merchandiseReceipt);

	/**
	 * 删除商品收货单
	 * 
	 * @param merchandiseCode
	 *            数据ID
	 */
	public void deleteMerchandiseReceipt(String merchandiseCode);

}
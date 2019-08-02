package com.powere2e.sco.interfaces.dao.materialmarketanalysis.merchmatesuppquote;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.datamaintenance.purchaserdata.relevancematerialandwebsite.RelevanceMaterialAndWebsite;
import com.powere2e.sco.model.materialmarketanalysis.merchmatesuppquote.MerchMateSuppQuote;

/**
 * 商品原料供应商报价 Dao接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年7月30日
 * @version 1.0
 */
public interface MerchMateSuppQuoteDao extends Dao {

	/**
	 * 查询商品原料数据
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 商品原料数据
	 */
	public List<RelevanceMaterialAndWebsite> listMerchandise(
			Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查看商品原料历史价格
	 * 
	 * @param map
	 *            查询参数
	 * @return
	 */
	public List<MerchMateSuppQuote> listMerMateHisPrice(Map<String, Object> map);

	/**
	 * 查询原料采购价格、投料占比
	 * 
	 * @param map
	 *            查询参数
	 * @return 
	 */
	public MerchMateSuppQuote listAccAndIng(Map<String, Object> map);

}

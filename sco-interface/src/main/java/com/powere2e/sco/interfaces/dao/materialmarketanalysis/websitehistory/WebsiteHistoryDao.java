package com.powere2e.sco.interfaces.dao.materialmarketanalysis.websitehistory;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.datamaintenance.purchaserdata.relevancematerialandwebsite.RelevanceMaterialAndWebsite;
import com.powere2e.sco.model.materialmarketanalysis.websitehistory.WebsiteHistory;

/**
 * 公示网站原料历史行情 Dao接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年7月20日
 * @version 1.0
 */
public interface WebsiteHistoryDao extends Dao {

	/**
	 * 查看历史价格
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 历史价格数据
	 */
	public List<WebsiteHistory> listHisPrice(Map<String, Object> map,
			PageInfo pageInfo);

	/**
	 * 查询商品数据
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return
	 */
	public List<RelevanceMaterialAndWebsite> listMerchandise(
			Map<String, Object> map, PageInfo pageInfo);
	
}

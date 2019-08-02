package com.powere2e.sco.dao.impl.materialmarketanalysis.websitehistory;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.materialmarketanalysis.websitehistory.WebsiteHistoryDao;
import com.powere2e.sco.model.datamaintenance.purchaserdata.relevancematerialandwebsite.RelevanceMaterialAndWebsite;
import com.powere2e.sco.model.materialmarketanalysis.websitehistory.WebsiteHistory;

/**
 * 公示网站原料历史行情 Dao接口的实现 
 * 
 * @author Gavillen.Zhou
 * @since 2015年7月20日
 * @version 1.0
 */
public class WebsiteHistoryDaoImpl extends DaoImpl implements WebsiteHistoryDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1308092618957726690L;

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
			PageInfo pageInfo) {
		return this.query(WebsiteHistoryDao.class, "listHisPrice", map, pageInfo);
	}
	
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
			Map<String, Object> map, PageInfo pageInfo) {
		return this.query(WebsiteHistoryDao.class, "listMerchandise", map, pageInfo);
	}

}

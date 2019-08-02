package com.powere2e.sco.interfaces.dao.materialmarketanalysis.materialwarning;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.datamaintenance.purchaserdata.relevancematerialandwebsite.RelevanceMaterialAndWebsite;
import com.powere2e.sco.model.materialmarketanalysis.materialwarningrecord.MaterialWarningRecord;

/**
 * 原料预警记录 Dao接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年8月7日
 * @version 1.0
 */
public interface MaterialWarningRecordDao extends Dao {

	/***
	 * 查看预警记录
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 预警记录
	 */
	public List<MaterialWarningRecord> listMatWarnRecord(
			Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 计算同环比月均价格
	 * 
	 * @param map
	 *            查询参数
	 * @return 同环比月均价格
	 */
	public BigDecimal calculateMomAvgMonthPrice(Map<String, Object> map);

	/**
	 * 查询商品数据
	 * 
	 * @param map
	 *            查询参数
	 * @return 与之关联的商品数据
	 */
	public List<RelevanceMaterialAndWebsite> listMerchandise(
			Map<String, Object> map);

}

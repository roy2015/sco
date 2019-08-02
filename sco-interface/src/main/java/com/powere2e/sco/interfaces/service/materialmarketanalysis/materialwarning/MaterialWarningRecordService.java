package com.powere2e.sco.interfaces.service.materialmarketanalysis.materialwarning;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.datamaintenance.purchaserdata.relevancematerialandwebsite.RelevanceMaterialAndWebsite;
import com.powere2e.sco.model.materialmarketanalysis.materialwarningrecord.MaterialWarningRecord;

/**
 * 原料预警记录 Service接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年8月7日
 * @version 1.0
 */
public interface MaterialWarningRecordService extends Service {

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
	 * 查询原料所关联的商品
	 * 
	 * @param map	<br>
	 * 		<li>materialCode 原料编号</li>
	 * 		<li>region 地区编号</li>
	 * @return 关联的商品
	 */
	public List<RelevanceMaterialAndWebsite> listMerchandise(
			Map<String, Object> map);

}

package com.powere2e.sco.interfaces.dao.materialmarketanalysis.accessoryintentionrecord;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.materialmarketanalysis.accessoryintentionrecord.AccessoryIntentionRecord;

/**
 * 辅料商品预警记录 Dao接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年8月11日
 * @version 1.0
 */
public interface AccessoryIntentionRecordDao extends Dao {

	/***
	 * 查看辅料商品预警记录
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 预警记录
	 */
	public List<AccessoryIntentionRecord> listAccessoryIntentionRecord(
			Map<String, Object> map, PageInfo pageInfo);

}

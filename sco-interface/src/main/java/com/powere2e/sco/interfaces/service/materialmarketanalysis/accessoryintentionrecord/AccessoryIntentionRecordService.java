package com.powere2e.sco.interfaces.service.materialmarketanalysis.accessoryintentionrecord;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.materialmarketanalysis.accessoryintentionrecord.AccessoryIntentionRecord;

/**
 * 辅料商品预警记录 Service接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年8月13日
 * @version 1.0
 */
public interface AccessoryIntentionRecordService extends Service {

	/**
	 * 查询辅料商品预警记录
	 * 
	 * @param map
	 *            查询添加
	 * @param pageInfo
	 *            分页参数
	 * @return 商品预警记录
	 */
	public List<AccessoryIntentionRecord> listAccessoryIntentionRecord(
			Map<String, Object> map, PageInfo pageInfo);

}

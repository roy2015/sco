package com.powere2e.sco.interfaces.service.materialmarketanalysis.merchandisewarningrecord;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.materialmarketanalysis.merchandisewarningrecord.MerchandiseWarningRecord;
import com.powere2e.security.model.Option;

/**
 * 商品预警记录 Service接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年8月11日
 * @version 1.0
 */
public interface MerchandiseWarningRecordService extends Service {

	/**
	 * 查询原料地区
	 * 
	 * @return 原料地区list
	 */
	public List<Option> listMaterialRegionOption();

	/**
	 * 查询商品预警记录
	 * 
	 * @param map
	 *            查询添加
	 * @param pageInfo
	 *            分页参数
	 * @return 商品预警记录
	 */
	public List<MerchandiseWarningRecord> listMerchandiseWarningRecrod(
			Map<String, Object> map, PageInfo pageInfo);

}

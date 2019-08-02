package com.powere2e.sco.interfaces.service.peripheral.bw;

import java.io.File;
import java.util.Map;

import com.powere2e.frame.commons.service.Service;

/**
 * 商品区域销售情况(日加盟)Service接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月17日
 */
public interface MerchandiseSellJoinDayService extends Service {

	/**
	 * 添加商品区域销售情况(日加盟)
	 *
	 * @param map
	 *
	 */
	public void insertMerchandiseSellJoinDay();

	/**
	 * 添加商品区域销售情况(日加盟)
	 *
	 * @param map
	 *
	 */
	public void completeInsertMerchandiseSellJoinDay(File file, String charsetCode, int processCount, String fileRootPath);

	/**
	 * 删除商品区域销售情况(日加盟)
	 * 
	 * @param map
	 */
	public void deleteMerchandiseSellJoinDay(Map<String, Object> map);
}
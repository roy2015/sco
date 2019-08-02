package com.powere2e.sco.interfaces.service.peripheral.bw;

import java.io.File;
import java.util.Map;

import com.powere2e.frame.commons.service.Service;

/**
 * 商品区域销售情况(日直营)单Service
 * 
 * @author Joyce.li
 * @since 2015年8月13日 下午2:39:08
 * @version 1.0
 */
public interface MerchandiseSellDirectDayService extends Service {
	/**
	 * 插入商品区域销售情况(日直营)单
	 * 
	 * @param merchandiseSellDirectDay
	 */
	public void insertMerchandiseSellDirectDay();

	/**
	 * 插入商品区域销售情况(日直营)单
	 * 
	 * @param merchandiseSellDirectDay
	 */
	public void completeInsertMerchandiseSellDirectDay(File file, String charsetCode, int processCount, String fileRootPath);

	/**
	 * 删除商品区域销售情况(日直营)
	 * 
	 * @param map
	 */
	public void deleteMerchandiseSellDirectDay(Map<String, Object> map);

}

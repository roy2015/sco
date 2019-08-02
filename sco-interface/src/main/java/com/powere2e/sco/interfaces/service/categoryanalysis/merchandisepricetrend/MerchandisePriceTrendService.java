package com.powere2e.sco.interfaces.service.categoryanalysis.merchandisepricetrend;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.categoryanalysis.merchandisepricetrend.MerchandisePriceTrend;
import com.powere2e.security.model.Option;

/**
 * 商品价格趋势Service接口
 * 
 * @author len.zhao
 * @version 1.0
 * @since 2015年5月8日
 */
public interface MerchandisePriceTrendService extends Service {

	/**
	 * 价格数据查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 签量数据列表
	 */
	public List<MerchandisePriceTrend> listMerchandisePriceTrend(Map<String, Object> map, PageInfo pageInfo) throws Exception ;

	/**
	 * 商品数据查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 签量数据列表
	 */
	public List<MerchandisePriceTrend> listMerchandiseData(Map<String, Object> map, PageInfo pageInfo) throws Exception ;

	/**
	 * 导出商品价格趋势
	 * 
	 * @param map
	 *            查询参数
	 * @param out
	 *            输出流
	 */
	public void exportPriceTrendExcel(Map<String, Object> map, ServletOutputStream out) throws Exception ;

	/**
	 * 保存商品价格趋势
	 * 
	 * @param fileName
	 *            保存的文件名
	 * @param paraMap
	 *            查询条件
	 * @return 消息
	 */
	public String saveSearchDataForm(String fileName, Map<String, Object> paraMap)  throws Exception ;

	/**
	 * 查询地区
	 * 
	 * @param regionCode
	 * @return
	 */
	public List<Option> listRegion(String regionCode);
}

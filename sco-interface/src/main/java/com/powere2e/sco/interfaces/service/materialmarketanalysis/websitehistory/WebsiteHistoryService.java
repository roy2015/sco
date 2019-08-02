package com.powere2e.sco.interfaces.service.materialmarketanalysis.websitehistory;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.datamaintenance.purchaserdata.relevancematerialandwebsite.RelevanceMaterialAndWebsite;
import com.powere2e.sco.model.materialmarketanalysis.websitehistory.WebsiteHistory;

/**
 * 公示网站原料历史行情 Dao接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年7月20日
 * @version 1.0
 */
public interface WebsiteHistoryService extends Service {

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
	 * 导出历史价格数据
	 * 
	 * @param out
	 *            输出流
	 * @param listData
	 *            查询出的数据
	 * @param pubMap
	 *            公共参数
	 */
	public void exportHisPrice(ServletOutputStream out,
			List<WebsiteHistory> listData, Map<String, Object> pubMap);

	/**
	 * 保存历史价格数据
	 * 
	 * @param fileName
	 *            文件名称
	 * @param list
	 *            数据
	 * @param pubMap
	 *            公共参数
	 */
	public String saveHistoryPrice(String fileName, List<WebsiteHistory> list,
			Map<String, Object> pubMap);

	/**
	 * 查询商品数据
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return
	 */
	public List<RelevanceMaterialAndWebsite> listMerchandise(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 导出商品数据
	 * 
	 * @param out
	 *            输出流
	 * @param listData
	 *            商品数据
	 * @param pubMap
	 *            公共参数
	 */
	public void exportHisMerchandise(ServletOutputStream out,
			List<RelevanceMaterialAndWebsite> listData, Map<String, Object> pubMap);

	/**
	 * 保存商品数据
	 * 
	 * @param fileName
	 *            文件名称
	 * @param list
	 *            商品数据
	 * @param pubMap
	 *            公共参数
	 * @return 保存消息
	 */
	public String saveHistoryMerchandise(String fileName,
			List<RelevanceMaterialAndWebsite> list, Map<String, Object> pubMap);

}

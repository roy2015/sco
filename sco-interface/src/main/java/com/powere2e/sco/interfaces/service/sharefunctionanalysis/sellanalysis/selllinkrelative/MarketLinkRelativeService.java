package com.powere2e.sco.interfaces.service.sharefunctionanalysis.sellanalysis.selllinkrelative;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selllinkrelative.SellLinkRelativeAnalysis;

/**
 * 整体销售环比Service接口
 * 
 * @author Joyce.li
 * @since 2015年7月15日 上午10:18:07
 * @version 1.0
 */
public interface MarketLinkRelativeService extends Service {

	/**
	 * 查询整体销售环比
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SellLinkRelativeAnalysis> queryMarketSellLinkRelative(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 保存整体销售环比数据为html页面
	 * 
	 * @param fileName
	 *            保存为该文件名
	 * @param paraMap
	 *            参数
	 * @return
	 * @throws Exception
	 */
	public String saveMarketSellToHtml(String fileName, Map<String, Object> paraMap) throws Exception;

	/**
	 * 导出整体销售环比到excel
	 * 
	 * @param map
	 *            查询参数
	 * @param out
	 *            输出流
	 */
	public void exportMarketSellToExcel(Map<String, Object> map, ServletOutputStream out) throws Exception;

}
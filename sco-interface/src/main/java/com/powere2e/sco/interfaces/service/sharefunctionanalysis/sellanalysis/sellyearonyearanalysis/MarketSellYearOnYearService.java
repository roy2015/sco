package com.powere2e.sco.interfaces.service.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.SellYearOnYearAnalysis;

/**
 * 整体销售同比service接口
 * 
 * @author Joyce.li
 * @since 2015年6月8日 下午6:33:05
 * @version 1.0
 */
public interface MarketSellYearOnYearService extends Service {
	/**
	 * 查询整体销售同比
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SellYearOnYearAnalysis> queryMarketSellYearOnYear(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查询整体销售同比总计
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SellYearOnYearAnalysis> queryMarketSellSum(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 保存整体销售同比数据为html页面
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
	 * 导出整体销售同比到excel
	 * 
	 * @param map
	 *            查询参数
	 * @param out
	 *            输出流
	 */
	public void exportMarketSellToExcel(Map<String, Object> map, ServletOutputStream out) throws Exception;

}
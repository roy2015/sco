package com.powere2e.sco.interfaces.service.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.SellYearOnYearAnalysis;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.SellYearOnYear;

/**
 * 销售同比service接口
 * 
 * @author Joyce.li
 * @since 2015年5月28日 下午3:43:05
 * @version 1.0
 */
public interface SellYearOnYearService extends Service {

	/**
	 * 查询销售同比列表
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SellYearOnYear> listSellYearOnYear(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查询单品信息
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SellYearOnYear> queryProductInfo(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查询单品销售同比
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SellYearOnYearAnalysis> queryProductSellYearOnYear(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 保存商品销售同比数据为html页面
	 * 
	 * @param fileName
	 *            保存为该文件名
	 * @param paraMap
	 *            参数
	 * @return
	 * @throws Exception
	 */
	public String saveProductSellToHtml(String fileName, Map<String, Object> paraMap) throws Exception;

	/**
	 * 导出单品销售同比到excel
	 * 
	 * @param map
	 *            查询参数
	 * @param out
	 *            输出流
	 */
	public void exportMerchandiseSellToExcel(Map<String, Object> map, ServletOutputStream out) throws Exception;

	/**
	 * 商品销售同比月同步(每个月第一天凌晨同步上一个月的数据,从merchandise_sell_direct_day日直营表汇总上个月信息)
	 * 
	 * @throws Exception
	 */
	public void syncMerchandiseYoyAnalysis(Map<String, Object> map) throws Exception;

	/**
	 * 查询单品销售同比总计
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SellYearOnYearAnalysis> queryProductSellSum(Map<String, Object> map, PageInfo pageInfo);

}
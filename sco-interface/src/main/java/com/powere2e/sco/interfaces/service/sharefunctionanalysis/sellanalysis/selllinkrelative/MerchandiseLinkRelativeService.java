package com.powere2e.sco.interfaces.service.sharefunctionanalysis.sellanalysis.selllinkrelative;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selllinkrelative.LinkRelative;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selllinkrelative.SellLinkRelativeAnalysis;

/**
 * 销售环比service接口
 * 
 * @author Joyce.li
 * @since 2015年7月7日 上午11:24:14
 * @version 1.0
 */
public interface MerchandiseLinkRelativeService extends Service {

	/**
	 * 查询销售环比列表
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<LinkRelative> listLinkRelative(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查询单品销售环比的单品信息
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<LinkRelative> queryMerchandiseLinkRelative(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查询单品销售环比
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SellLinkRelativeAnalysis> queryProductSellLinkRelative(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 保存商品销售环比数据为html页面
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
	 * 导出单品销售环比到excel
	 * 
	 * @param map
	 *            查询参数
	 * @param out
	 *            输出流
	 */
	public void exportMerchandiseSellToExcel(Map<String, Object> map, ServletOutputStream out) throws Exception;

}
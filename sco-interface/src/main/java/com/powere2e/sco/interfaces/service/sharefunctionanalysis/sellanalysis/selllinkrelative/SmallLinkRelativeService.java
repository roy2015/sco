package com.powere2e.sco.interfaces.service.sharefunctionanalysis.sellanalysis.selllinkrelative;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selllinkrelative.LinkRelative;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selllinkrelative.SellLinkRelativeAnalysis;

/**
 * 小分类销售环比Service接口
 * 
 * @author Joyce.li
 * @since 2015年7月15日 上午10:18:30
 * @version 1.0
 */
public interface SmallLinkRelativeService extends Service {

	/**
	 * 查询小分类信息
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<LinkRelative> listSmallTypeInfo(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查询小分类销售环比
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SellLinkRelativeAnalysis> querySmallSellLinkRelative(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 保存小分类销售环比数据为html页面
	 * 
	 * @param fileName
	 *            保存为该文件名
	 * @param paraMap
	 *            参数
	 * @return
	 * @throws Exception
	 */
	public String saveSmallSellToHtml(String fileName, Map<String, Object> paraMap) throws Exception;

	/**
	 * 导出小分类销售环比到excel
	 * 
	 * @param map
	 *            查询参数
	 * @param out
	 *            输出流
	 */
	public void exportSmallSellToExcel(Map<String, Object> map, ServletOutputStream out) throws Exception;

}
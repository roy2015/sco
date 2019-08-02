package com.powere2e.sco.interfaces.service.sharefunctionanalysis.sellanalysis.selllinkrelative;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selllinkrelative.LinkRelative;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selllinkrelative.SellLinkRelativeAnalysis;

/**
 * 明细类销售环比Service接口
 * 
 * @author Joyce.li
 * @since 2015年7月15日 上午10:07:00
 * @version 1.0
 */
public interface DetailLinkRelativeService extends Service {

	/**
	 * 查询明细类信息
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<LinkRelative> listDetailTypeInfo(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查询单品销售环比
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SellLinkRelativeAnalysis> queryDetailSellLinkRelative(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 保存明细类销售环比数据为html页面
	 * 
	 * @param fileName
	 *            保存为该文件名
	 * @param paraMap
	 *            参数
	 * @return
	 * @throws Exception
	 */
	public String saveDetailSellToHtml(String fileName, Map<String, Object> paraMap) throws Exception;

	/**
	 * 导出明细类销售环比到excel
	 * 
	 * @param map
	 *            查询参数
	 * @param out
	 *            输出流
	 */
	public void exportDetailSellToExcel(Map<String, Object> map, ServletOutputStream out) throws Exception;

}
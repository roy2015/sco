package com.powere2e.sco.interfaces.service.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.SellYearOnYear;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.SellYearOnYearAnalysis;

/**
 * 明细类销售同比service接口
 * 
 * @author Joyce.li
 * @since 2015年6月8日 下午5:51:14
 * @version 1.0
 */
public interface DetailSellYearOnYearService extends Service {

	/**
	 * 查询明细类信息
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SellYearOnYear> listDetailTypeInfo(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查询明细类销售同比
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SellYearOnYearAnalysis> queryDetailSellYearOnYear(Map<String, Object> map, PageInfo pageInfo);

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
	public String saveDetailSellToHtml(String fileName, Map<String, Object> paraMap) throws Exception;

	/**
	 * 导出单品销售同比到excel
	 * 
	 * @param map
	 *            查询参数
	 * @param out
	 *            输出流
	 */
	public void exportDetailSellToExcel(Map<String, Object> map, ServletOutputStream out) throws Exception;

	/**
	 * 查询明细类销售同比总计
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SellYearOnYearAnalysis> queryDetailSellSum(Map<String, Object> map, PageInfo pageInfo);

}
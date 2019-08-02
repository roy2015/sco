package com.powere2e.sco.interfaces.service.categoryanalysis;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.categoryanalysis.CategoryPrice;

/**
 * 商品价格带 公共Service接口
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月28日
 */
public interface CategoryPriceService extends Service {

	/**
	 * 显示汇总数据
	 * 
	 * @param map
	 *            查询条件
	 * @param pageInfo
	 *            分页参数
	 * @return 汇总数据list
	 */
	public List<CategoryPrice> listCollectData(Map<String, Object> map,
			PageInfo pageInfo);

	/**
	 * 查询明细数据
	 * 
	 * @param map
	 *            查询条件
	 * @param pageInfo
	 *            分页参数
	 * @return 明细数据list
	 */
	public List<CategoryPrice> listDetailData(Map<String, Object> map,
			PageInfo pageInfo);

	/**
	 * dataGrid上面的数据统计
	 * 
	 * @param map
	 *            查询条件
	 * @return SKU最多的价格带 | 销售量最大价格带 | 销售价最大的价格带 | 毛利额最大的价格带
	 */
	public String listCalculateTotal(Map<String, Object> map);

	/**
	 * 查询自动计算价格带所需数据
	 * 
	 * @param map
	 *            查询条件
	 * 
	 * @return 数据('|'隔开)
	 */
	public String listPriceRegionDate(Map<String, Object> map);

	/**
	 * 所有商品价格带导出到Excel
	 * 
	 * @param tol1
	 *            表格1统计数据
	 * @param data1
	 *            表格1数据
	 * @param tol2
	 *            表格2统计数据
	 * @param data2
	 *            表格2数据
	 * @param map
	 *            公共查询条件
	 * @param out
	 *            输入流
	 */
	public void exportAllCategoryToExcel(String tol1,
			List<CategoryPrice> data1, String tol2,
			List<CategoryPrice> data2, Map<String, Object> map,
			ServletOutputStream out);

	/**
	 * 保存所有价格带数据
	 * 
	 * @param fileName
	 *            文件名称
	 * @param dirName
	 *            存放的目录名称
	 * @param reportType
	 *            报表类型
	 * @param paraMap
	 *            所需参数
	 * @return 保存消息
	 */
	public String completeSaveAllCategory(String fileName, String dirName,
			String reportType, Map<String, Object> paraMap);

	/**
	 * 明细数据导出到Excel
	 * 
	 * @param data1
	 *            表格1数据
	 * @param data2
	 *            表格2数据
	 * @param map
	 *            公共查询条件
	 * @param out
	 *            输入流
	 */
	public void exportDetCategoryToExcel(List<CategoryPrice> data1,
			List<CategoryPrice> data2, Map<String, Object> map,
			ServletOutputStream out);

	/**
	 * 保存明细数据
	 * 
	 * @param fileName
	 *            文件名称
	 * @param dirName
	 *            存放的目录名称
	 * @param reportType
	 *            报表类型
	 * @param paraMap
	 *            所需参数
	 * @return 保存消息
	 */
	public String completeSaveDetCategory(String fileName, String dirName,
			String reportType, Map<String, Object> paraMap);

}
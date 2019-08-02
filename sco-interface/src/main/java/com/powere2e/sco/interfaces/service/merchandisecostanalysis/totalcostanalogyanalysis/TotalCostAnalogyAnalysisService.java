package com.powere2e.sco.interfaces.service.merchandisecostanalysis.totalcostanalogyanalysis;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingBo;
import com.powere2e.sco.model.merchandisecostanalysis.totalcostanalogyanalysis.TotalCostAnalysis;

/**
 * 商品总成本类比分析
 * 
 * @author lipengjie
 * @version 1.0
 * @since 2015年5月7日
 */
public interface TotalCostAnalogyAnalysisService {

	/**
	 * 查询总成本类比数据(同核算表与投料表数据一样)
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页信息
	 * @return 所需要的数据
	 */
	public List<AccountingBo> listTotalCostAnalogyAnalysis(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 获取本次申请商品
	 * 
	 * @param map
	 *            查询参数
	 * @return 所需要的数据
	 */
	public TotalCostAnalysis listThisApplicationMerchandise(Map<String, Object> map);

	/**
	 * 查询所有已通过审批的商品
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页信息
	 * @return 所需要的数据
	 */
	public List<AccountingBo> listReferMerchandise(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查询所有未通过审批的商品意向品
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页信息
	 * @return 所需要的数据
	 */
	public List<AccountingBo> listReferIntention(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 列出所有纵向的表头
	 * @param map 条件,本次申请商品的核算编号
	 * @return 所需要的数据
	 */
	public Map<String, Object> listTitle(Map<String, Object> m);

	/**
	 * 导出总成本类比结果
	 * 
	 * @param map
	 *            查询参数
	 * @param radioValuesMap
	 *            存储页面radio的值,用来控制显示的列
	 * @param out
	 *            输出流
	 */
	public void exportSignedQtyExcel(Map<String, Object> map, ServletOutputStream out);

	/**
	 * 保存总成本类比结果
	 * 
	 * @param fileName
	 *            保存的文件名
	 * @param paraMap
	 *            查询条件
	 * @return 消息
	 */
	public String isnertReports(String fileName, Map<String, Object> paraMap);
/*	
	*//**
	 * 
	 * 查询对应商品的投料
	 * 
	 * @param map
	 *            查询参数
	 * @return 所需要的数据
	 *//*
	public Map<String, Object> searchIngredient(Map<String, Object> map);*/
	
}

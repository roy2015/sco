package com.powere2e.sco.interfaces.service.sharefunctionanalysis.sellanalysis.marketpromotionanalysis;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.marketpromotionanalysis.MarketPromotionAnalysis;
/**
 * 销售明细Service接口
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public interface MarketPromotionAnalysisService extends Service {
	/**
	 * 大盘促销分析明细档期汇总查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回销售明细列表
	 */
	public List<MarketPromotionAnalysis> listMarketPromotionAnalysisDetail(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 大盘促销分析明细日销售查询
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回销售明细列表
	 */
	public List<MarketPromotionAnalysis> listMarketPromotionAnalysisDetails(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 大盘促销分析查询
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回销售明细列表
	 */
	public List<MarketPromotionAnalysis> listMarketPromotionAnalysis(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 导出促销汇总分析execl
	 * @param map
	 * @param out
	 */
	public void exportMarketPromotionAnalysis(Map<String, Object> map, ServletOutputStream out);
	/**
	 * 导出促销明细分析execl
	 * @param map
	 * @param out
	 */
	public void exportMarketPromotionAnalysisDetail(Map<String, Object> map, ServletOutputStream out);
	/**
	 * 保存促销汇总分析
	 * @param fileName
	 * @param paraMap
	 * @return
	 */
	public String saveMarketPromotionAnalysis(String fileName, Map<String, Object> paraMap);
	/**
	 * 保存促销明细分析
	 * @param fileName
	 * @param paraMap
	 * @return
	 */
	public String saveMarketPromotionAnalysisDetail(String fileName, Map<String, Object> paraMap);
}
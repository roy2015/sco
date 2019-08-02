package com.powere2e.sco.interfaces.service.sharefunctionanalysis.sellanalysis.merchandisepromotionanalysis;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.merchandisepromotionanalysis.MerchandisePromotionAnalysis;
/**
 * 商品促销分析Service接口
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public interface MerchandisePromotionAnalysisService extends Service {
	/**
	 * 销售明细查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回销售明细列表
	 */
	public List<MerchandisePromotionAnalysis> listMerchandise(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 商品促销分析明细档期汇总查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回商品促销分析列表
	 * @throws Exception 
	 */
	public List<MerchandisePromotionAnalysis> listMerchandisePromotionAnalysisDetail(Map<String, Object> map,PageInfo pageInfo) throws Exception;
	/**
	 * 商品促销分析查询
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回商品促销分析列表
	 */
	public List<MerchandisePromotionAnalysis> listMerchandisePromotionAnalysis(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 导出促销明细分析execl
	 * @param map
	 * @param out
	 * @throws Exception 
	 */
	public void exportMerchandisePromotionAnalysisDetail(Map<String, Object> map, ServletOutputStream out) throws Exception;
	/**
	 * 保存促销明细分析
	 * @param fileName
	 * @param paraMap
	 * @return
	 * @throws Exception 
	 */
	public String saveMerchandisePromotionAnalysisDetail(String fileName, Map<String, Object> paraMap) throws Exception;
	/**
	 * 查询商品
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<MerchandisePromotionAnalysis> listMerchandiseInfo(Map<String, Object> map, PageInfo pageInfo);
	/**
	 * 促销分析明细查询
	 * @param map
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<MerchandisePromotionAnalysis> listMerchandisePromotionAnalysisDetailInfo(Map<String, Object> map, PageInfo pageInfo) throws Exception;
	/**
	 * 查询图形报表
	 * @param map
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<MerchandisePromotionAnalysis> lislistMerchandisePromotionAnalysisDetailTB(Map<String, Object> map, PageInfo pageInfo) throws Exception;
}
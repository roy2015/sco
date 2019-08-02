package com.powere2e.sco.interfaces.service.sharefunctionanalysis.sellanalysis.selldetail;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selldetail.SellDetail;
/**
 * 销售明细Service接口
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public interface SellDetailService extends Service {
	/**
	 * 销售明细查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回销售明细列表
	 */
	public List<SellDetail> getSellDetail(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 销售明细汇总查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回销售明细列表
	 */
	public List<SellDetail> getSellDetailSum(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 商品信息查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回销售明细列表
	 */
	public List<SellDetail> getMerchandise(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个销售明细
	 *
	 * @param map
	 *				
	 * @return
	 */
	public SellDetail getSellDetail(String merchandiseCode);
	
	/**
	 * 导出销售明细
	 * @param map
	 * @param out
	 */
	public void exportSellDetail(Map<String, Object> map, ServletOutputStream out);
	/**
	 * 保存销售明细
	 * @param fileName
	 * @param paraMap
	 * @return
	 */
	public String saveSellDetail(String fileName, Map<String, Object> paraMap);
}
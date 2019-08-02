package com.powere2e.sco.interfaces.service.merchandisecostanalysis.merchandiseitemcost;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.merchandisecostanalysis.merchandiseitemcost.Merchandiseitemcost;

/**
 * 商品分项成本类比
 * 
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月23日
 */
public interface MerchandiseItemCostService extends Service {
	/**
	 * 查询商品分项成本类比
	 * 
	 * @param map
	 *            条件Map
	 * @return 查询的结果
	 */
	public List<Merchandiseitemcost> searchMechandiseItemCost(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 导出商品分项类成本类比
	 * 
	 * @param map
	 *            查询参数
	 * @param out
	 *            输出流
	 */
	public void exportSignedQtyExcel(Map<String, Object> map, ServletOutputStream out);

	/**
	 * 保存商品分项成本类比
	 * 
	 * @param fileName
	 *            保存的文件名
	 * @param paraMap
	 *            查询条件
	 * @return 消息
	 */
	public String saveSearchDataForm(String fileName, Map<String, Object> paraMap);
}

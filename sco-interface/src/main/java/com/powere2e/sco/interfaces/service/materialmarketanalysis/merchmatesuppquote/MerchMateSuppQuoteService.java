package com.powere2e.sco.interfaces.service.materialmarketanalysis.merchmatesuppquote;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.datamaintenance.purchaserdata.relevancematerialandwebsite.RelevanceMaterialAndWebsite;
import com.powere2e.sco.model.materialmarketanalysis.merchmatesuppquote.MerchMateSuppQuote;

/**
 * 商品原料供应商报价 Service接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年7月20日
 * @version 1.0
 */
public interface MerchMateSuppQuoteService extends Service {

	/**
	 * 查询商品原料数据
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 商品原料数据
	 */
	public List<RelevanceMaterialAndWebsite> listMerchandise(
			Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查看商品原料历史价格
	 * 
	 * @param map
	 *            查询参数
	 */
	public List<MerchMateSuppQuote> listMerMateHisPrice(Map<String, Object> map);

	/**
	 * 导出Excel
	 * 
	 * @param out
	 *            输出流
	 * @param list
	 *            数据
	 * @param relMatWeb
	 *            参数
	 */
	public void exportMerMat(ServletOutputStream out,
			List<MerchMateSuppQuote> list, RelevanceMaterialAndWebsite relMatWeb);

	/**
	 * 保存报表文件
	 * 
	 * @param fileName
	 *            文件名称
	 * 
	 * @param map
	 *            所需参数
	 * @return 保存信息
	 */
	public String saveMerMat(String fileName, Map<String, Object> map);

}

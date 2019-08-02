package com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.ApplicationReportAdjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.AtpItemAdjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.AtpTotalAdjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.HistoryPriceAdjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.MerchandiseMaterial;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.SameMerchandiseAdjustprice;

/**
 * 历史与本次价格(调价)Service接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年5月7日
 */
public interface HistoryPriceAdjustpriceService extends Service {
	/**
	 * 历史与本次价格(调价)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回历史与本次价格(调价)列表
	 */
	public List<HistoryPriceAdjustprice> listHistoryPriceAdjustprice(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 本次价格比历史价格高(正常调价)
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回历史与本次价格(调价)列表
	 */
	public List<HistoryPriceAdjustprice> listComparePriceAdjustprice(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 商品签量情况-总查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回商品签量情况-总列表
	 */
	public List<AtpTotalAdjustprice> listAtpTotalAdjustprice(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 商品签量情况-明细(调价)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回商品签量情况-明细(调价)列表
	 */
	public List<AtpItemAdjustprice> listAtpItemAdjustprice(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 同类商品市场零售价(调价)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回同类商品市场零售价(调价)列表
	 */
	public List<SameMerchandiseAdjustprice> listSameMerchandiseAdjustprice(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 商品原料情况(调价)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回同类商品市场零售价(调价)列表
	 */
	public List<MerchandiseMaterial> listMerchandiseMaterial(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 根据正常调价申请报告生成html
	 * 
	 * @param fileName
	 *            文件名
	 * @param applicationList
	 *            审批数据
	 * @param intentionAndSupplierCodes
	 *            商品供应商不好
	 * @return
	 * @throws Exception
	 */
	public String saveApplicationReportAdjustpriceToHtml(String fileName, String applicationCode,
			List<ApplicationReportAdjustprice> applicationList, String intentionAndSupplierCodes) throws Exception;

}
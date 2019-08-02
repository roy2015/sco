package com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportoldupoaapplication.reportoldup;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.MerchandiseMaterial;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.ApplicationReportOldup;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.AnticipatedSellOld;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.CheckStandardOldup;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.HistoryPriceOldup;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.SameMerchandiseOldup;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.UpDownMarketOldup;

/**
 * 历史与本次价格(老品新上)Service接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年5月5日
 */
public interface HistoryPriceOldupService extends Service {
	/**
	 * 历史与本次价格(老品新上)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回历史与本次价格(老品新上)列表
	 */
	public List<HistoryPriceOldup> listHistoryPriceOldup(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 本次价格比历史价格高(老品新上)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回历史与本次价格(老品新上)列表
	 */
	public List<HistoryPriceOldup> listComparePriceOldup(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 添加历史与本次价格(老品新上)
	 * 
	 * @param map
	 * 
	 */
	public void insertHistoryPriceOldup(HistoryPriceOldup historyPriceOldup) throws Exception;

	/**
	 * 删除历史与本次价格(老品新上)
	 * 
	 * @param map
	 *            必须参数id为要删除的历史与本次价格(老品新上)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteHistoryPriceOldup(String reportCode) throws Exception;

	/**
	 * 上下市时间(老品新上)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回上下市时间(老品新上)列表
	 */
	public List<UpDownMarketOldup> listUpDownMarketOldup(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 添加上下市时间(老品新上)
	 * 
	 * @param map
	 * 
	 */
	public void insertUpDownMarketOldup(UpDownMarketOldup upDownMarketOldup) throws Exception;

	/**
	 * 删除上下市时间(老品新上)
	 * 
	 * @param map
	 *            必须参数id为要删除的上下市时间(老品新上)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteUpDownMarketOldup(String reportCode) throws Exception;

	/**
	 * 报告检验标准(老品新上)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回报告检验标准(老品新上)列表
	 */
	public List<CheckStandardOldup> listCheckStandardOldup(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 添加报告检验标准(老品新上)
	 * 
	 * @param map
	 * 
	 */
	public void insertCheckStandardOldup(CheckStandardOldup checkStandardOldup) throws Exception;

	/**
	 * 删除报告检验标准(老品新上)
	 * 
	 * @param map
	 *            必须参数id为要删除的报告检验标准(老品新上)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteCheckStandardOldup(String reportCode) throws Exception;

	/**
	 * 同类商品市场零售价(老品新上)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回同类商品市场零售价(老品新上)列表
	 */
	public List<SameMerchandiseOldup> listSameMerchandiseOldup(Map<String, Object> map, PageInfo pageInfo)
			throws Exception;

	/**
	 * 添加同类商品市场零售价(老品新上)
	 * 
	 * @param map
	 * 
	 */
	public void insertSameMerchandiseOldup(SameMerchandiseOldup sameMerchandiseOldup) throws Exception;

	/**
	 * 删除同类商品市场零售价(老品新上)
	 * 
	 * @param map
	 *            必须参数id为要删除的同类商品市场零售价(老品新上)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteSameMerchandiseOldup(String reportCode) throws Exception;

	/**
	 * 同类商品市场零售价(老品新上)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回同类商品市场零售价(老品新上)列表
	 */
	public List<AnticipatedSellOld> listAnticipatedSellOld(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 添加同类商品市场零售价(老品新上)
	 * 
	 * @param map
	 * 
	 */
	public void insertAnticipatedSellOld(AnticipatedSellOld anticipatedSellOld) throws Exception;

	/**
	 * 删除同类商品市场零售价(老品新上)
	 * 
	 * @param map
	 *            必须参数id为要删除的同类商品市场零售价(老品新上)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteAnticipatedSellOld(String reportCode) throws Exception;

	/**
	 * 根据老品新上引进申请报告生成html
	 * 
	 * @param fileName
	 *            保存文件名称
	 * @param applicationCode
	 *            审批单号
	 * @param applicationList
	 *            审批数据
	 * @param intentionAndSupplierCodes
	 *            商品供应商编号
	 * @return
	 * @throws Exception
	 */
	public String saveApplicationReportOldupToHtml(String fileName, String applicationCode,
			List<ApplicationReportOldup> applicationList, String intentionAndSupplierCodes) throws Exception;

	/**
	 * 商品原料情况(老品新上)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回同类商品市场零售价(调价)列表
	 */
	public List<MerchandiseMaterial> listMerchandiseMaterial(Map<String, Object> map, PageInfo pageInfo);
}
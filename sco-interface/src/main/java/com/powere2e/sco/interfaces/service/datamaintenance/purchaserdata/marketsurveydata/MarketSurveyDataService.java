package com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.marketsurveydata;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.datamaintenance.purchaserdata.marketsurveydata.MarketSurveyData;

/**
 * 竞品价格市调 Service接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月30日
 * @version 1.0
 */
public interface MarketSurveyDataService extends Service {

	/**
	 * 竞品价格市调查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 竞品价格市调数据列表
	 */
	public List<MarketSurveyData> listMarketSurveyData(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 导入竞品价格市调数据
	 * 
	 * @param excel
	 *            上传文件
	 * @return 导入消息
	 */
	public String completeImportMarketSurveyData(File excel);
	
	/**
	 * 删除竞品价格市调数据
	 * 
	 * @param map
	 *            商品ID
	 */
	public void deleteMarketSurveyData(Map<String, Object> map);

	/**
	 * 导出到Excel
	 * 
	 * @param map
	 *            查询参数
	 * @param out
	 *            输出流
	 */
	public void exportSignedQtyExcel(Map<String, Object> map,
			ServletOutputStream out);

	/**
	 * 保存当前查询结果
	 * 
	 * @param fileName
	 *            保存的文件名称
	 * @param paraMap
	 *            查询条件
	 * @return 消息
	 */
	public String saveSearchDataForm(String fileName, Map<String, Object> paraMap);
	
}
package com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportadjustpriceoaapplication;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.ApplicationReportAdjustprice;

/**
 * 申请报告(调价)Service接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月20日
 */
public interface ApplicationReportAdjustpriceService extends Service {

	/**
	 * 查询没有报告的商品
	 */
	public List<ApplicationLackFileM> queryNotHaveReportMerchandiseAdjustprice(Map<String, Object> map) throws Exception;

	/**
	 * 申请报告(调价)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回申请报告(调价)列表
	 */
	public List<ApplicationReportAdjustprice> listApplicationReportAdjustprice(String applicationCode, String intentionAndSupplierCodes) throws Exception;

	/**
	 * 根据ID号加载一个申请报告(调价)
	 * 
	 * @param map
	 * 
	 * @return
	 */
	public ApplicationReportAdjustprice loadApplicationReportAdjustprice(String applicationCode, String intentionAndSupplierCodes) throws Exception;

	/**
	 * 添加申请报告(调价)
	 * 
	 * @param map
	 * 
	 */
	public String insertApplicationReportAdjustprice(List<ApplicationReportAdjustprice> applicationList, String applicationCode, String intentionAndSupplierCodes) throws Exception;

	/**
	 * 插入正常调价报告和关联信息
	 * 
	 * @param applicationList
	 * @param applicationCode
	 */
	public void completeInsertOrUpdateReportAdjustprice(List<ApplicationReportAdjustprice> applicationList, String applicationCode) throws Exception;

	/**
	 * 删除申请报告(调价)
	 * 
	 * @param map
	 *            必须参数id为要删除的申请报告(调价)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteApplicationReportAdjustprice(String applicationCode) throws Exception;

	/**
	 * 修改申请报告(调价)
	 * 
	 * @param map
	 *            必须参数id为要修改申请报告(调价)的id号，不能为数组
	 */
	public void updateApplicationReportAdjustprice(ApplicationReportAdjustprice applicationReportAdjustprice) throws Exception;

	/**
	 * 根据申请单号删除申请报告(正常调价)
	 */
	public void deleteAllReportAdjustpriceByApplicationCode(Map<String, Object> map) throws Exception;
	
	/**
	 * 导出申请报告(正常调价)
	 * 
	 * @param map
	 *            查询参数
	 * @param out
	 *            输出流
	 */
	public void exportSignedQtyExcel(Map<String, Object> map, ServletOutputStream out);
}
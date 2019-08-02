package com.powere2e.sco.service.impl.merchandiseoaapplication.reportadjustpriceoaapplication.reportanalysis;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.service.merchandiseoaapplication.MerchandiseOaApplicationUtil;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportadjustpriceoaapplication.reportanalysis.AnalysisReportAdjustPriceDao;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportadjustpriceoaapplication.reportanalysis.AnalysisReportAdjustPriceService;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.service.impl.merchandiseoaapplication.MerchandiseOaApplicationServiceImpl;

/**
 * 商品正常调价报表分析 业务类的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月20日
 */
public class AnalysisReportAdjustPriceServiceImpl extends ServiceImpl implements
		AnalysisReportAdjustPriceService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7325560236999299767L;
	private AnalysisReportAdjustPriceDao analysisReportAdjustPriceDao;

	public static AnalysisReportAdjustPriceService getInstance() {
		return (AnalysisReportAdjustPriceService) ConfigFactory.getInstance()
				.getBean("analysisReportAdjustPriceService");
	}
	//获取AnalysisReportAdjustPrice Dao
	public AnalysisReportAdjustPriceDao getAnalysisReportAdjustPriceDao() {
		return analysisReportAdjustPriceDao;
	}
	//设置AnalysisReportAdjustPrice Dao
	public void setAnalysisReportAdjustPriceDao(AnalysisReportAdjustPriceDao analysisReportAdjustPriceDao) {
		this.analysisReportAdjustPriceDao = analysisReportAdjustPriceDao;
	}

	@Override
	public List<Reports> listAnalysisReportAdjustPrice(
			Map<String, Object> map, PageInfo pageInfo) {
		return this.analysisReportAdjustPriceDao.listAnalysisReportAdjustPrice(map, pageInfo);
	}

	@Override
	public void insertLinkAnalysisReportAdjustPrice(Map<String, Object> map) {
		Object obj = map.get("reportsTypeCode");
		if(obj == null) throw new EscmException("管理的报表类型不能空");
		String type = obj.toString();
		//如果是成本分析表
		if(BusinessConstants.myReportType.MCA.toString().equalsIgnoreCase(type)) {
			this.validateAnalysisReportAdjustPrice(map);
			//只能关联一个成本分析表
			String MCACode = this.analysisReportAdjustPriceDao.searchMCAData(map);
			map.put("MCACode", StringUtils.isBlank(MCACode) ? "" : MCACode);
		} 
		//查询该申请记录状况
		this.validateOaStatus(map);
		//添加关联报表
		this.analysisReportAdjustPriceDao.insertLinkAnalysisReportAdjustPrice(map);
	}
	
	/**
	 * 校验是否已被其他申请关联
	 * 
	 * @param map
	 *            相关参数
	 */
	private void validateAnalysisReportAdjustPrice(Map<String, Object> map) {
		String data = this.analysisReportAdjustPriceDao.validateAnalysisReportAdjustPrice(map);
		if (!StringUtils.isBlank(data)) {
			throw new EscmException("本次申请商品已关联其它SCO申请单，不可重复关联");
		}
	}
	
	@Override
	public void deleteAnalysisReportAdjustPrice(Map<String, Object> map) {
		// 验证该申请记录状态
		this.validateOaStatus(map);
		
		this.analysisReportAdjustPriceDao.deleteAnalysisReportAdjustPrice(map);
	}
	
	/**
	 * 根据申请单的状态来显示不同的消息
	 * 
	 * @param map :
	 * 			<li>applicationCode 新品引进OA申请单号</li>
	 * 			<li>intentionAndSupplierCodes  所选择的意向品编号和供应商编号组</li>
	 */
	private void validateOaStatus(Map<String, Object> map) {
		Object appCode = map.get("applicationCode");
		String applicationCode = (appCode == null ? null : appCode.toString());
		Object intenSupCode = map.get("intentionAndSupplierCodes");
		String intentionAndSupplierCodes = (intenSupCode == null ? null : intenSupCode.toString());
		String oaStatus;
		try {
			oaStatus = MerchandiseOaApplicationServiceImpl.getInstance()
					.getIntentionOaApplicationReceiptInfo(applicationCode,
							intentionAndSupplierCodes, BusinessConstants.ApplicationType.MERCHANDISE_ADJUSTPRICE.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new EscmException("校验申请单[" + applicationCode + "]状态时出错！");
		}
		MerchandiseOaApplicationUtil.responseMessage(oaStatus);
	}
	
}
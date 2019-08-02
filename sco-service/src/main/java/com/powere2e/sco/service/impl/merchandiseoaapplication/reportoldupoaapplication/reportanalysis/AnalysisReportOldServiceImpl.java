package com.powere2e.sco.service.impl.merchandiseoaapplication.reportoldupoaapplication.reportanalysis;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.service.merchandiseoaapplication.MerchandiseOaApplicationUtil;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportoldupoaapplication.reportanalysis.AnalysisReportOldDao;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportoldupoaapplication.reportanalysis.AnalysisReportOldService;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.service.impl.merchandiseoaapplication.MerchandiseOaApplicationServiceImpl;

/**
 * 旧品新上报表分析 业务类的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月20日
 */
public class AnalysisReportOldServiceImpl extends ServiceImpl implements
		AnalysisReportOldService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7325560236999299767L;
	private AnalysisReportOldDao analysisReportOldDao;

	public static AnalysisReportOldService getInstance() {
		return (AnalysisReportOldService) ConfigFactory.getInstance()
				.getBean("analysisReportOldService");
	}
	//获取AnalysisReportOld Dao
	public AnalysisReportOldDao getAnalysisReportOldDao() {
		return analysisReportOldDao;
	}
	//设置AnalysisReportOld Dao
	public void setAnalysisReportOldDao(AnalysisReportOldDao analysisReportOldDao) {
		this.analysisReportOldDao = analysisReportOldDao;
	}

	@Override
	public List<Reports> listAnalysisReportOld(
			Map<String, Object> map, PageInfo pageInfo) {
		return this.analysisReportOldDao.listAnalysisReportOld(map, pageInfo);
	}

	@Override
	public void insertLinkAnalysisReportOld(Map<String, Object> map) {
		Object obj = map.get("reportsTypeCode");
		if(obj == null) throw new EscmException("管理的报表类型不能空");
		String type = obj.toString();
		//如果是成本分析表
		if(BusinessConstants.myReportType.MCA.toString().equalsIgnoreCase(type)) {
			this.validateAnalysisReportOld(map);
			//只能关联一个成本分析表
			String MCACode = this.analysisReportOldDao.searchMCAData(map);
			map.put("MCACode", StringUtils.isBlank(MCACode) ? "" : MCACode);
		} 
		//查询该申请记录状况
		this.validateOaStatus(map);
		//添加关联报表
		this.analysisReportOldDao.insertLinkAnalysisReportOld(map);
	}
	
	/**
	 * 校验是否已经关联
	 * 
	 * @param map
	 */
	private void validateAnalysisReportOld(Map<String, Object> map) {
		String data = this.analysisReportOldDao.validateAnalysisReportOld(map);
		if (!StringUtils.isBlank(data)) {
			throw new EscmException("本次申请商品已关联其它SCO申请单，不可重复关联");
		}
	}
	
	@Override
	public void deleteAnalysisReportOld(Map<String, Object> map) {
		//查询该申请记录状况
		this.validateOaStatus(map);
		
		this.analysisReportOldDao.deleteAnalysisReportOld(map);
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
					.getIntentionOaApplicationReceiptInfo(applicationCode, intentionAndSupplierCodes,
							BusinessConstants.ApplicationType.MERCHANDISE_OLDUP.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new EscmException("校验申请单[" + applicationCode + "]状态时出错！");
		}
		MerchandiseOaApplicationUtil.responseMessage(oaStatus);
	}
	
}
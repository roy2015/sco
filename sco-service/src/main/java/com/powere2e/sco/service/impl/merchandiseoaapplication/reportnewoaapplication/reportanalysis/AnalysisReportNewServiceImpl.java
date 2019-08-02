package com.powere2e.sco.service.impl.merchandiseoaapplication.reportnewoaapplication.reportanalysis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.service.merchandiseoaapplication.MerchandiseOaApplicationUtil;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportnewoaapplication.reportanalysis.AnalysisReportNewDao;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportnewoaapplication.reportanalysis.AnalysisReportNewService;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.service.impl.merchandiseoaapplication.MerchandiseOaApplicationServiceImpl;

/**
 * 新品引进报表分析 业务类的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月20日
 */
public class AnalysisReportNewServiceImpl extends ServiceImpl implements
		AnalysisReportNewService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7325560236999299767L;
	private AnalysisReportNewDao analysisReportNewDao;

	public static AnalysisReportNewService getInstance() {
		return (AnalysisReportNewService) ConfigFactory.getInstance()
				.getBean("analysisReportNewService");
	}
	//获取AnalysisReportNew Dao
	public AnalysisReportNewDao getAnalysisReportNewDao() {
		return analysisReportNewDao;
	}
	//设置AnalysisReportNew Dao
	public void setAnalysisReportNewDao(AnalysisReportNewDao analysisReportNewDao) {
		this.analysisReportNewDao = analysisReportNewDao;
	}

	@Override
	public List<Reports> listAnalysisReportNew(
			Map<String, Object> map, PageInfo pageInfo) {
		return this.analysisReportNewDao.listAnalysisReportNew(map, pageInfo);
	}

	@Override
	public void insertLinkAnalysisReportNew(Map<String, Object> map) {
		Object obj = map.get("reportsTypeCode");
		if(obj == null) throw new EscmException("管理的报表类型不能空");
		String type = obj.toString();
		//如果是成本分析表
		if(BusinessConstants.myReportType.MCA.toString().equalsIgnoreCase(type)) {
			this.validateAnalysisReportNew(map);
			//只能关联一个成本分析表
			String MCACode = this.analysisReportNewDao.searchMCAData(map);
			map.put("MCACode", StringUtils.isBlank(MCACode) ? "" : MCACode);
		} 
		//查询该申请记录状况
		this.validateOaStatus(map);
		//添加关联报表
		this.analysisReportNewDao.insertLinkAnalysisReportNew(map);
	}
	
	/**
	 * 校验是否已被其他申请关联
	 * 
	 * @param map
	 *            相关参数
	 */
	private void validateAnalysisReportNew(Map<String, Object> map) {
		String data = this.analysisReportNewDao.validateAnalysisReportNew(map);
		if (!StringUtils.isBlank(data)) {
			throw new EscmException("本次申请商品已关联其它SCO申请单，不可重复关联");
		}
	}
	
	@Override
	public void deleteAnalysisReportNew(Map<String, Object> map) {
		// 验证该申请记录状态
		this.validateOaStatus(map);
		
		this.analysisReportNewDao.deleteAnalysisReportNew(map);
	}
	
	/**
	 * 通过审批单号删除与之关联的报表信息
	 * 
	 * @param applicationCode
	 *            审批单号
	 */
	@Override
	public void deleteAnalysisReportByAppCode(String applicationCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		this.analysisReportNewDao.deleteAnalysisReportNew(map);
	}
	
	@Override
	public List<ApplicationLackFileM> listNoLinkMCAReportApplication(String applicationCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		return this.analysisReportNewDao.listNoLinkReportApplication(map);
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
							intentionAndSupplierCodes, BusinessConstants.ApplicationType.MERCHANDISE_NEW.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new EscmException("校验申请单[" + applicationCode + "]状态时出错！");
		}
		MerchandiseOaApplicationUtil.responseMessage(oaStatus);
	}
	
}
package com.powere2e.sco.service.impl.accessoryoaapplication.nonFoodApply.reportanalysis;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.FileUtils;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.nonFoodApply.reportanalysis.NonFoodReportAnalysisDao;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.nonFoodApply.reportanalysis.NonFoodReportAnalysisService;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.service.impl.accessoryoaapplication.NonFoodApplyServiceImpl;
import com.powere2e.sco.service.impl.common.MasterDataTypeServiceImpl;

/**
 * 非食品竞价单OA申请报表分析 业务类的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月30日
 */
public class NonFoodReportAnalysisServiceImpl extends ServiceImpl implements
		NonFoodReportAnalysisService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7325560236999299767L;
	private NonFoodReportAnalysisDao nonFoodReportAnalysisDao;

	// 获取CommitteeReportAnalysis Dao
	public NonFoodReportAnalysisDao getNonFoodReportAnalysisDao() {
		return nonFoodReportAnalysisDao;
	}

	// 设置CommitteeReportAnalysis Dao
	public void setNonFoodReportAnalysisDao(
			NonFoodReportAnalysisDao nonFoodReportAnalysisDao) {
		this.nonFoodReportAnalysisDao = nonFoodReportAnalysisDao;
	}

	public static NonFoodReportAnalysisService getInstance() {
		return (NonFoodReportAnalysisService) ConfigFactory.getInstance()
				.getBean("nonFoodReportAnalysisService");
	}

	@Override
	public List<Reports> listAnalysisReportSystem(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.nonFoodReportAnalysisDao.listAnalysisReportSystem(map,
				pageInfo);
	}

	@Override
	public List<Reports> listAnalysisReportUpload(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.nonFoodReportAnalysisDao.listAnalysisReportUpload(map,
				pageInfo);
	}

	@Override
	public List<Reports> listAnalysisReportPurOrder(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.nonFoodReportAnalysisDao.listAnalysisReportPurOrder(map,
				pageInfo);
	}

	@Override
	public void insertLinkAnalysisReportNonFood(Map<String, Object> map) {
		Object obj = map.get("reportsTypeCode");
		if (obj == null)
			throw new EscmException("管理的报表类型不能空");

		// 查询该申请记录状况
		this.validateOaStatus(map);
		// 添加关联报表
		this.nonFoodReportAnalysisDao.insertLinkAnalysisReportNonFood(map);
	}

	@Override
	public void completeUploadReport(Map<String, Object> map, File file) {
		this.validateOaStatus(map);
		this.nonFoodReportAnalysisDao.insertAnalysisUploadReport(map);
		this.insertLinkAnalysisReportNonFood(map);
	}

	@Override
	public void completeUploadReportPurOrder(Map<String, Object> map, File file) {
		this.validateOaStatus(map);

		Reports rpt = this.nonFoodReportAnalysisDao
				.loadRepeatPurOrderByName(map);
		if (rpt != null) {
			// 存在相同的采购单名称时
			try {
				FileUtils.deleteFile(ConfigPath.getUploadFilePath().concat(
						rpt.getReportsUrl()));
			} catch (Exception e) {
				// 删除文件时出错
				e.printStackTrace();
			}
		} else {
			String id = MasterDataTypeServiceImpl.getInstance().nextID(
					"S_REPORTS");//由于都关联了analysis_reports_a,防止报表ID相同时造成关联问题
			map.put("reportsCode", id);
			map.put("reportsTypeCode", "SGD");
			this.insertLinkAnalysisReportNonFood(map);
		}
		this.nonFoodReportAnalysisDao.insertAnalysisUploadPurOrder(map);
	}

	@Override
	public void deleteAnalysisReportSystem(Map<String, Object> map) {
		// 验证该申请记录状态
		this.validateOaStatus(map);

		this.nonFoodReportAnalysisDao.deleteAnalysisReportLink(map);
	}

	@Override
	public void deleteAnalysisReportUpload(Map<String, Object> map) {
		// 验证该申请记录状态
		this.validateOaStatus(map);
		List<Reports> list = this.listAnalysisReportUpload(map, null);
		for (Reports reports : list) {
			try {
				FileUtils.deleteFile(ConfigPath.getUploadFilePath().concat(
						reports.getReportsUrl()));
			} catch (Exception e) {
				// 删除文件时出错
				e.printStackTrace();
			}
		}
		this.nonFoodReportAnalysisDao.deleteAnalysisReportLink(map);// 1.关联表
		this.nonFoodReportAnalysisDao.deleteAnalysisReportUpload(map);// 2.上传记录表
	}

	@Override
	public void deleteAnalysisReportPurOrder(Map<String, Object> map) {
		// 验证该申请记录状态
		this.validateOaStatus(map);
		List<Reports> list = this.listAnalysisReportPurOrder(map, null);
		for (Reports reports : list) {
			try {
				FileUtils.deleteFile(ConfigPath.getUploadFilePath().concat(
						reports.getReportsUrl()));
			} catch (Exception e) {
				// 删除文件时出错
				e.printStackTrace();
			}
		}
		this.nonFoodReportAnalysisDao.deleteAnalysisReportLink(map);// 1.关联表
		this.nonFoodReportAnalysisDao.deleteAnalysisReportPurOrder(map);// 2.申购单记录
	}

	/**
	 * 根据申请单的状态来显示不同的消息
	 * 
	 * @param map
	 *            : <li>quotedCodes 所选择的意向品编号和供应商编号组</li> <li>applicationCode
	 *            新品引进OA申请单号</li>
	 */
	private void validateOaStatus(Map<String, Object> map) {
		// 报价单
		Object quoCode = map.get("quotedCodes");
		String quotedCodes = (quoCode == null ? null : quoCode.toString());
		// 申请单号
		Object appCode = map.get("applicationCode");
		String applicationCode = (appCode == null ? null : appCode.toString());

		Boolean flag = NonFoodApplyServiceImpl.getInstance()
				.nonFoodInsertUpdateDeleteIsOk(
						quotedCodes, applicationCode,
						BusinessConstants.ApplicationType.ACCESSORY_FSPJJD
								.toString());
		if(!flag) {
			throw new EscmException("报价单在其他申请单中或者当前申请单状态下不可修改!");
		}
	}

}
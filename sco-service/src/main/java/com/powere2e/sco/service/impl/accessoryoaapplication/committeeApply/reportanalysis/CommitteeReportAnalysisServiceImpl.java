package com.powere2e.sco.service.impl.accessoryoaapplication.committeeApply.reportanalysis;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.FileUtils;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.committeeApply.reportanalysis.CommitteeReportAnalysisDao;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.committeeApply.reportanalysis.CommitteeReportAnalysisService;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.service.impl.accessoryoaapplication.CommitteeApplyServiceImpl;
import com.powere2e.sco.service.impl.common.MasterDataTypeServiceImpl;

/**
 * 辅料采购委员会竞价报表分析 业务类的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月30日
 */
public class CommitteeReportAnalysisServiceImpl extends ServiceImpl implements
		CommitteeReportAnalysisService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7325560236999299767L;
	private CommitteeReportAnalysisDao committeeReportAnalysisDao;

	// 获取CommitteeReportAnalysis Dao
	public CommitteeReportAnalysisDao getCommitteeReportAnalysisDao() {
		return committeeReportAnalysisDao;
	}

	// 设置CommitteeReportAnalysis Dao
	public void setCommitteeReportAnalysisDao(
			CommitteeReportAnalysisDao committeeReportAnalysisDao) {
		this.committeeReportAnalysisDao = committeeReportAnalysisDao;
	}

	/**
	 * 获取CommitteeReportAnalysisService 实例
	 * 
	 * @return 实例对象
	 */
	public static CommitteeReportAnalysisService getInstance() {
		return (CommitteeReportAnalysisService) ConfigFactory.getInstance()
				.getBean("committeeReportAnalysisService");
	}

	@Override
	public List<Reports> listAnalysisReportSystem(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.committeeReportAnalysisDao.listAnalysisReportSystem(map,
				pageInfo);
	}

	@Override
	public List<Reports> listAnalysisReportUpload(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.committeeReportAnalysisDao.listAnalysisReportUpload(map,
				pageInfo);
	}

	@Override
	public List<Reports> listAnalysisReportPurOrder(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.committeeReportAnalysisDao.listAnalysisReportPurOrder(map,
				pageInfo);
	}

	@Override
	public void insertLinkAnalysisReportCommittee(Map<String, Object> map) {
		Object obj = map.get("reportsTypeCode");
		if (obj == null)
			throw new EscmException("管理的报表类型不能空");
		/*
		 * String type = obj.toString(); // 如果是成本分析表(包括三个类型) if
		 * (BusinessConstants.myReportType.MCA.toString()
		 * .equalsIgnoreCase(type)) { this.validateAnalysisReportNew(map); //
		 * 只能关联一个成本分析表(包括三个类型) String MCACode =
		 * this.committeeReportAnalysisDao.searchMCAData(map);
		 * map.put("MCACode", StringUtils.isBlank(MCACode) ? "" : MCACode); }
		 */
		// 查询该申请记录状况
		this.validateOaStatus(map);
		// 添加关联报表
		this.committeeReportAnalysisDao.insertLinkAnalysisReportCommittee(map);
	}

	@Override
	public void completeUploadReport(Map<String, Object> map, File file) {
//		this.validateOaStatus(map);
		this.committeeReportAnalysisDao.insertAnalysisUploadReport(map);
		this.insertLinkAnalysisReportCommittee(map);
	}

	@Override
	public void completeUploadReportPurOrder(Map<String, Object> map, File file) {
		this.validateOaStatus(map);

		Reports rpt = this.committeeReportAnalysisDao.loadRepeatPurOrderByName(map);
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
			this.insertLinkAnalysisReportCommittee(map);
		}
		this.committeeReportAnalysisDao.insertAnalysisUploadPurOrder(map);
	}

	@Override
	public void deleteAnalysisReportSystem(Map<String, Object> map) {
		// 验证该申请记录状态
		this.validateOaStatus(map);

		this.committeeReportAnalysisDao.deleteAnalysisReportLink(map);
	}

	@Override
	public void deleteAnalysisReportUpload(Map<String, Object> map) {
		// 验证该申请记录状态
		this.validateOaStatus(map);
		List<Reports> list = this.listAnalysisReportUpload(map, null);
		this.committeeReportAnalysisDao.deleteAnalysisReportLink(map);// 1.关联表
		this.committeeReportAnalysisDao.deleteAnalysisReportUpload(map);// 2.上传记录表
		//删除上传文件
		for (Reports reports : list) {
			try {
				FileUtils.deleteFile(ConfigPath.getUploadFilePath().concat(
						reports.getReportsUrl()));
			} catch (Exception e) {
				// 删除文件时出错
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteAnalysisReportPurOrder(Map<String, Object> map) {
		// 验证该申请记录状态
		this.validateOaStatus(map);
		List<Reports> list = this.listAnalysisReportPurOrder(map, null);
		this.committeeReportAnalysisDao.deleteAnalysisReportLink(map);// 1.关联表
		this.committeeReportAnalysisDao.deleteAnalysisReportPurOrder(map);// 2.申购单记录
		//删除上传文件
		for (Reports reports : list) {
			try {
				FileUtils.deleteFile(ConfigPath.getUploadFilePath().concat(
						reports.getReportsUrl()));
			} catch (Exception e) {
				// 删除文件时出错
				e.printStackTrace();
			}
		}
	}

	/**
	 * 通过审批单号删除与之关联的报表信息及文件
	 * 
	 * @param applicationCode
	 *            申请单号
	 */
	@Override
	public void deleteAnalysisReportByAppCode(String applicationCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		
		//1.删除已关联的上传报表
		List<Reports> list = this.listAnalysisReportUpload(map, null);
		this.committeeReportAnalysisDao.deleteAnalysisReportUpload(map);// 2.上传记录表
		
		//2.删除关联的申购单
		list.addAll(this.listAnalysisReportPurOrder(map, null));
		this.committeeReportAnalysisDao.deleteAnalysisReportPurOrder(map);// 2.申购单记录
		
		//3.已关联的系统报表
		this.committeeReportAnalysisDao.deleteAnalysisReportLink(map);// 1.关联表(不上删除文件)
		
		//4.删除上传文件
		for (Reports reports : list) {
			try {
				FileUtils.deleteFile(ConfigPath.getUploadFilePath().concat(
						reports.getReportsUrl()));
			} catch (Exception e) {
				// 删除文件时出错
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 校验是否已被其他申请关联(成本分析表包括三个类型)
	 * 
	 * @param map
	 *            相关参数
	 *
	 *            private void validateAnalysisReportNew(Map<String, Object>
	 *            map) { String data =
	 *            this.committeeReportAnalysisDao.validateAnalysisReportCommittee
	 *            (map); if (!StringUtils.isBlank(data)) { throw new
	 *            EscmException("本次申请商品已关联其它SCO申请单，不可重复关联"); } }
	 */

	@Override
	public Boolean ifApplicationExistsPurOrder(String applicationCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		return this.listAnalysisReportPurOrder(map, null).size() > 0;
	}

	/**
	 * 查询魔申购单是否包含商品成本分析报表
	 * 
	 * @param applicationCode
	 *            申请单号
	 * @return 是否关联成本分析报表(包括三个类型)
	 */
	@Override
	public Boolean ifApplicationExistsMCA(String applicationCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		String reportTypes = StrUtils.concatStr(
				BusinessConstants.myReportType.FQA.toString(),// 辅料总价分析表
				BusinessConstants.myReportType.FCA.toString(),// 辅料成本类比分析表
				BusinessConstants.myReportType.FSP.toString());// 辅料供应商历史报价分析表
		map.put("reportsTypeCode", reportTypes);
		return this.committeeReportAnalysisDao.listLinkReportByAppCode(map)
				.size() > 0;
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

		Boolean flag = CommitteeApplyServiceImpl.getInstance()
				.committeeInsertUpdateDeleteIsOk(
						quotedCodes,
						applicationCode,
						BusinessConstants.ApplicationType.ACCESSORY_CGWYHJJD
								.toString());
		if (!flag) {
			throw new EscmException("报价单在其他申请单中或者当前申请单状态下不可修改!");
		}
	}

	@Override
	public List<Reports> listUploadByApp(String applicationCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		return this.committeeReportAnalysisDao.listUploadByApp(map);
	}

	@Override
	public List<Reports> listPurOrderByApp(String applicationCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		return this.committeeReportAnalysisDao.listPurOrderByApp(map);
	}

}
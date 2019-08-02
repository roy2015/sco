package com.powere2e.sco.interfaces.dao.merchandiseoaapplication.fastimportoaapplication;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.sco.model.merchandiseoaapplication.fastimportoaapplication.ApplicationFastImport;

/**
 * 商品快速引进 DAO接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年10月12日
 * @version 1.0
 */
public interface ApplicationFastImportDao extends Dao {

	/**
	 * 删除审批单时验证商品(包装设计申请和巡厂申请)状态
	 * 
	 * @param map
	 *            : applicationCode 审批单号
	 * @return '审批单号'
	 */
	public List<String> listApplicationMerStatus(
			Map<String, Object> map);

	/**
	 * 查询申请单审批状态为草稿的意向品和其供应商
	 * 
	 * @param map
	 *            查询参数
	 * @return 状态为草稿的意向品和其供应商
	 */
	public List<ApplicationFastImport> listApplicationAndMer(
			Map<String, Object> map);

}
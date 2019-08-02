package com.powere2e.sco.interfaces.service.merchandiseintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.merchandiseintention.ForetasteFeedback;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationMerchandise;

/**
 * 商品意向品Service接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年3月10日
 */
public interface ForetasteFeedbackService extends Service {

	/* ======================试吃反馈============================================== */
	/**
	 * 试吃反馈查询
	 */
	public List<ForetasteFeedback> listForetasteFeedback(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 根据ID号加载一个试吃反馈
	 */
	public ForetasteFeedback loadForetasteFeedback(Map<String, Object> map) throws Exception;

	/**
	 * 添加试吃反馈
	 */
	public void insertForetasteFeedback(Map<String, Object> map) throws Exception;

    /**
     * 推送试吃反馈给伙伴协同系统
     * @param map
     * @throws Exception
     */
	void pushInsertForetasteFeedbackToHBXT(Map<String, Object> map) throws Exception;

	/**
	 * 删除试吃反馈
	 */
	public void deleteForetasteFeedback(Map<String, Object> map) throws Exception;

    /**
     * 推送删除试吃反馈给伙伴协同系统
     * @param map
     * @throws Exception
     */
    void pushDeleteForetasteFeedback(Map<String, Object> map) throws Exception;

	/**
	 * 修改试吃反馈
	 */
	public void updateForetasteFeedback(Map<String, Object> map) throws Exception;

    /**
     * 推送修改试吃反馈给伙伴协同系统
     * @param map
     * @throws Exception
     */
    void pushUpdateForetasteFeedback(Map<String, Object> map) throws Exception;

	/**
	 * 修改试吃反馈是否通过
	 */
	public void updateForetasteFeedbackIsPass(String intentionAndSupplierCodes, String isForetastePass)
			throws Exception;

	/**
	 * 根据意向品编号和供应商编号查询是否做了OA新品引进或者OA新品快速引进
	 */
	public List<ApplicationMerchandise> queryIsOaApplicationNew(Map<String, Object> map) throws Exception;

}
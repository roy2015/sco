package com.powere2e.sco.interfaces.service.merchandiseoaapplication;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationMerchandise;
import com.powere2e.sco.model.merchandiseoaapplication.MerchandiseOaApplication;

/**
 * 商品OA申请单Service接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月22日
 */
public interface MerchandiseOaApplicationService extends Service {
	/**
	 * 新品引进OA申请意向品列表
	 */
	public List<MerchandiseIntention> queryIntentionApplicationList(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 查询老品新上、正常调价和快速调价OA申请列表
	 */
	public List<MerchandiseIntention> queryMerchandiseApplicationList(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 添加商品OA申请单
	 * 
	 * @param map
	 * 
	 */
	public void insertOaApplication(MerchandiseOaApplication oaApplication) throws Exception;

	/**
	 * 根据所选的意向品编号和供应商编号对该供应商对应的意向品新增OA申请
	 * 
	 * @param map
	 */
	public void insertIntentionOaApplication(Map<String, Object> map) throws Exception;

	/**
	 * 根据意向品编号和供应商编号组查询OA申请记录，根据记录和页面获取的申请单进行比对
	 * 
	 * @param applicationCode
	 *            新品引进oa申请单号
	 * @param intentionAndSupplierCodes
	 *            所选择的意向品编号和供应商编号组
	 * @return 
	 *         NONE(没有进行OA申请)，SAME(进行了OA申请，并且与获取的applicationCode一致)，DIFFER(进行了OA申请
	 *         ，并且与获取的applicationCode不一致)
	 */
	public String getIntentionOaApplicationReceiptInfo(String applicationCode, String intentionAndSupplierCodes,
			String applicationType) throws Exception;

	/**
	 * 根据申请单号删除OA申请
	 */
	public void deleteOaApplicationByCode(Map<String, Object> map) throws Exception;

	/**
	 * 根据申请单号删除OA申请关联表
	 */
	public void deleteApplicationMerhandiseByCode(Map<String, Object> map) throws Exception;

	/**
	 * 修改OA申请单状态
	 */
	public void updateOaApplicationStatus(Map<String, Object> map) throws Exception;

	/**
	 * 添加缺失文件说明
	 * 
	 * @param map
	 */
	public void insertApplicationLackFileM(Map<String, Object> map) throws Exception;

	/**
	 * 撤销允许OA同步
	 * 
	 * @param applicationCodes
	 *            多个申请单号用,分隔
	 */
	public void completeUndoOaApplicationStatus(String applicationCodes) throws Exception;

	/**
	 * 查看OA系统的申请单号和联系人
	 */
	public MerchandiseOaApplication findApproveOpinion(String applicationCode) throws Exception;

	/**
	 * 根据ID号加载一个商品OA申请单
	 */
	public MerchandiseOaApplication loadOaApplication(Map<String, Object> map) throws Exception;

	/**
	 * 根据OA申请单号查询意向品/商品编号和对应的供应商编号
	 * 
	 * @param map
	 * @return
	 */
	public List<ApplicationMerchandise> findMerchandiseCodeAndSupplierCodeByApplicationCode(Map<String, Object> map) throws Exception;

	/**
	 * 根据序列获取主键
	 * 
	 * @param map
	 * @return
	 */
	public String selectReportNextID(String sequenceName) throws Exception;

	// CR，巡厂和包装信息
	/**
	 * 添加商品巡厂申请单
	 * 
	 * @param map
	 * 
	 */
	public void insertApplicationVisitFactory(String applicationVfCode,String codes) throws Exception;

	/**
	 * 删除商品巡厂申请单
	 * 
	 * @param map
	 *            必须参数id为要删除的商品巡厂申请单id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteApplicationVisitFactory(Map<String, Object> map) throws Exception;

	/**
	 * 添加商品巡厂申请单(商品)
	 * 
	 * @param map
	 * 
	 */
	public void insertApplicationVisitFactoryM(Map<String, Object> map) throws Exception;

	/**
	 * 删除商品巡厂申请单(商品)
	 * 
	 * @param map
	 *            必须参数id为要删除的商品巡厂申请单id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteApplicationVisitFactoryM(Map<String, Object> map) throws Exception;

	/**
	 * 添加商品包装设计申请单
	 * 
	 * @param map
	 * 
	 */
	public void insertApplicationPackageDesign(Map<String, Object> map) throws Exception;

	/**
	 * 删除商品包装设计申请单
	 * 
	 * @param map
	 *            必须参数id为要删除的商品包装设计申请单id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteApplicationPackageDesign(Map<String, Object> map) throws Exception;

	/**
	 * 添加商品包装设计申请单(商品)
	 * 
	 * @param map
	 * 
	 */
	public void insertApplicationPackageDesignM(Map<String, Object> map) throws Exception;

	/**
	 * 删除商品包装设计申请单(商品)
	 * 
	 * @param map
	 *            必须参数id为要删除的商品包装设计申请单id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteApplicationPackageDesignM(Map<String, Object> map) throws Exception;

	/**
	 * 删除申请进度信息(商品OA)2
	 * 
	 * @param map
	 *            必须参数id为要删除的商品包装设计申请单id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteApplicationScheduleM2(Map<String, Object> map) throws Exception;
	
	/**
	 * 根据意向品编号和供应商编号查询该供应商对应的意向品是否已经OA申请
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<ApplicationMerchandise> listIntentionOaApplication(Map<String, Object> map)  throws Exception;

	/**
	 * 校验并添加申请报告
	 * 
	 * @param applicationType
	 *            申请单类型
	 * @param applicationCode
	 *            申请单号
	 * @param reportCode
	 *            报表编号
	 * @param intentionAndSupplierCodes
	 *            意向品供应商编号
	 * @param intentionCode
	 *            商品编号
	 * @param supplierCode
	 *            供应商编号
	 * @return map 申购报告
	 * @throws Exception
	 */
	public Map<String, Object> getApplicationReportMap(String applicationType, String applicationCode, String reportCode,
			String intentionAndSupplierCodes, String intentionCode, String supplierCode) throws Exception;
}
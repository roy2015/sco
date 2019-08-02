package com.powere2e.sco.interfaces.service.merchandiseintention;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.merchandiseintention.IntentionSupplierMerchandise;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntentionSupplier;

/**
 * 商品意向品Service接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年3月10日
 */
public interface MerchandiseIntentionService extends Service {
	/**
	 * 商品意向品查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回商品意向品列表
	 */
	public List<MerchandiseIntention> listMerchandiseIntention(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 根据ID号加载一个商品意向品
	 * 
	 * @param map
	 * 
	 * @return
	 */
	public MerchandiseIntention loadMerchandiseIntention(String intentionCode) throws Exception;

	/**
	 * 添加商品意向品
	 * 
	 * @param map
	 * 
	 */
	public void insertMerchandiseIntention(MerchandiseIntention merchandiseIntention) throws Exception;

	/**
	 * 删除商品意向品
	 * 
	 * @param map
	 *            必须参数id为要删除的商品意向品id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteMerchandiseIntention(String intentionCode) throws Exception;

	/**
	 * 修改商品意向品
	 * 
	 * @param map
	 *            必须参数id为要修改商品意向品的id号，不能为数组
	 */
	public void updateMerchandiseIntention(MerchandiseIntention merchandiseIntention) throws Exception;

	/* ======================供应商相关操作============================= */
	/**
	 * 意向供应商查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回意向供应商列表
	 */
	public List<MerchandiseIntentionSupplier> listIntentionSupplier(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 添加意向供应商
	 */
	public void insertIntentionSupplier(Map<String, Object> map) throws Exception;

	/**
	 * 删除意向品供应商关联数据
	 * 
	 */
	public void deleteIntentionSupplierMerchandise(Map<String, Object> map) throws Exception;

	/**
	 * 查询关联的意向供应商
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回意向品供应商关联表列表
	 */
	public List<IntentionSupplierMerchandise> listIntentionSupplierMerchandise(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 添加意向品供应商关联表
	 * 
	 * @param map
	 * 
	 */
	public void insertIntentionSupplierMerchandise(Map<String, Object> map) throws Exception;

	/**
	 * 判断供应商为正式供应商还是意向供应商
	 */

	public MerchandiseIntentionSupplier findSupplierIsTrue(String supplierCode) throws Exception;

	/**
	 * 判断供应商名称是否存在
	 */
	public MerchandiseIntentionSupplier findSupplierNameIsExists(String intentionSupplierName) throws Exception;

	/**
	 * 判断某个供应商在关联表中是否存在
	 */
	public IntentionSupplierMerchandise findSupplierMerchandiseIsExists(Map<String, Object> map) throws Exception;

	/**
	 * 根据意向品编号查询是否有OA申请
	 */
	public List<MerchandiseIntention> findApplicationByIntentionCodes(Map<String, Object> map) throws Exception;
	
	/**
	 * 导出意向品列表
	 * 
	 * @param map
	 *            查询参数
	 * @param out
	 *            输出流
	 */
	public void exportSignedQtyExcel(Map<String, Object> map, ServletOutputStream out);
}
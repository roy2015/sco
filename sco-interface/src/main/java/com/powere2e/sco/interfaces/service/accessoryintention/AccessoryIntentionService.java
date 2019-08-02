package com.powere2e.sco.interfaces.service.accessoryintention;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiry;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryAccessory;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryElse;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryMaterial;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryPacking;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryQuotedCount;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryTechnology;
import com.powere2e.sco.model.accessoryintention.AccessoryIntention;
import com.powere2e.sco.model.accessoryintention.AccessoryIntentionSupplier;
import com.powere2e.security.model.Option;

/**
 * 辅料意向品Service接口
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月16日
 */
public interface AccessoryIntentionService extends Service {
	/**
	 * 辅料意向品查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回辅料意向品列表
	 */
	public List<AccessoryIntention> listAccessoryIntention(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 关联供应商
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回辅料意向品列表
	 */
	public List<AccessoryIntentionSupplier> listAccessoryIntentionSupplier(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 根据ID号加载一个辅料意向品
	 * 
	 * @param map
	 * 
	 * @return
	 */
	public AccessoryIntention loadAccessoryIntention(String intentionCode);

	/**
	 * 添加辅料意向品
	 * 
	 * @param map
	 * 
	 */
	public void insertAccessoryIntention(AccessoryIntention accessoryIntention);

	/**
	 * 添加询价单
	 * 
	 * @param map
	 * 
	 */
	public void insertAccessoryIntentionXJD(String enquiryCode, String intentionCode, AccessoryEnquiry ae, AccessoryEnquiryMaterial[] arr1, AccessoryEnquiryAccessory[] arr2,
			AccessoryEnquiryPacking[] arr3, AccessoryEnquiryTechnology[] arr4, AccessoryEnquiryQuotedCount[] arr5, AccessoryEnquiryElse[] arr6) throws IOException;

	/**
	 * 关联供应商
	 * 
	 * @param map
	 * 
	 */
	public void insertAccessoryIntentionSupplier(Map<String, Object> map);

	/**
	 * 页面添加供应商
	 * 
	 * @param map
	 * 
	 */
	public void insertIntentionSupplier(Map<String, Object> map,Map<String, Object> map1);

	/**
	 * 删除辅料意向品
	 * 
	 * @param map
	 *            必须参数id为要删除的辅料意向品id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteAccessoryIntention(String[] intentionCode);

	/**
	 * 导出询价单excel
	 * 
	 * @param map
	 * 
	 */
	public void exportAccessoryEnquiryToExcel(Map<String, Object> map, ServletOutputStream out);

	/**
	 * 从excel中导入报价单
	 */
	public String insertUploadQuotedFromExcel(File quotedFile, Map<String, Object> map) throws Exception;

	/**
	 * 取消关联供应商
	 * 
	 * @param map
	 * 
	 */
	public void deleteAccessoryIntentionSupplier(Map<String, Object> map);

	/**
	 * 修改辅料意向品
	 * 
	 * @param map
	 *            必须参数id为要修改辅料意向品的id号，不能为数组
	 */
	public void updateAccessoryIntention(AccessoryIntention accessoryIntention);

	/**
	 * 供应商列表查询
	 * 
	 * @param map
	 *            必须参数id为要修改辅料意向品的id号，不能为数组
	 */
	public List<Option> listSupplier(Map<String, Object> map);

	/**
	 * 辅料细分类列表查询
	 * 
	 * @param map
	 *            必须参数id为要修改辅料意向品的id号，不能为数组
	 */
	public List<Option> listMinceType(Map<String, Object> map);

	public Boolean editIsOk(String intentionCode);

	// 批量删除电子版报价单
	public void deleteAccessoryQuotedElectronic(String[] quotedCode);

	// 批量删除扫描版报价单
	public void deleteAccessoryQuotedScan(String[] proofingCode);

	// 批量删除打样信息
	public void deleteAccessoryProofing(String[] proofingCode);
	// 批量删除询价单
	public void deleteAccessoryEnquiry(String[] enquiryCode);
    //根据供应商名称查询
	public AccessoryIntentionSupplier loadIntentionSupplier(Map<String, Object> map2);
}
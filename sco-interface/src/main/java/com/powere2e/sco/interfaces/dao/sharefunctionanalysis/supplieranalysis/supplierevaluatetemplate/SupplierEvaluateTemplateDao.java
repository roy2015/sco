package com.powere2e.sco.interfaces.dao.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate.SupplierEvaluateTemplate;
/**
 * 供应商考评表模板DAO接口
 * @author caoliqiang
 * @version 1.0
 * @since 2015年4月24日
 */
public interface SupplierEvaluateTemplateDao extends Dao {
	/**
	 * 供应商考评表模板查询
	 *
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回供应商考评表模板列表
	 */
	public List<SupplierEvaluateTemplate> listSupplierEvaluateTemplate(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个供应商考评表模板
	 *
	 * @param map
	 *				
	 * @return
	 */
	public List<SupplierEvaluateTemplate> loadSupplierEvaluateTemplateItem(Map<String,Object> map);
	/**
	 * 根据ID号加载一个供应商考评表模板－－修改
	 *
	 * @param map
	 *				
	 * @return
	 */
	public SupplierEvaluateTemplate loadSupplierEvaluateTemplate(Map<String,Object> map);
	/**
	 * 添加供应商考评表模板--商品
	 *
	 * @param map
	 *				
	 */
	public void insertSupplierEvaluateTemplate(Map<String, Object> map);
	/**
	 * 添加供应商考评表模板--辅料
	 *
	 * @param map
	 *				
	 */
	public void insertAccessorySupplierEvaluateTemplate(Map<String, Object> map);
	/**
	 * 查询TemplateCode
	 * @return
	 */
	public String selectTemplateCode(Map<String, Object> map);
	/**
	 * 添加供应商考评表模板--商品的考评项
	 * @param map			
	 */
	public void insertSupplierEvaluateTemplateItem(Map<String, Object> map);
	/**
	 * 删除供应商考评表模板
	 *
	 * @param map 
	 *				必须参数id为要删除的供应商考评表模板id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteSupplierEvaluateTemplate(Map<String, Object> map);
	/**
	 * 修改供应商考评表模板
	 *
	 * @param map 
	 *				必须参数id为要修改供应商考评表模板的id号，不能为数组
	 */
	public void updateSupplierEvaluateTemplate(Map<String, Object> map);
	/**
	 * 删除考评项，用于更新考评模板
	 * @param map
	 */
	public void deleteSupplierEvaluateTemplateItem(Map<String, Object> map);
	/**
	 * 发布供应商考评表模板
	 */
	public void publishSupplierEvaluateTemplate(Map<String,Object> map);
	/**
	 * 关闭考评表模板
	 */
	public void closeSupplierEvaluateTemplateByTemplateCode(Map<String,Object> map);
}
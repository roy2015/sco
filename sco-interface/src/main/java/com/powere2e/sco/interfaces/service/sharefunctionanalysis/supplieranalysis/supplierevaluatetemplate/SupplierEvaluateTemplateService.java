package com.powere2e.sco.interfaces.service.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate.SupplierEvaluateTemplate;

/**
 * 供应商考评表模板Service接口
 * @author caoliqiang
 * @version 1.0
 * @since 2015年4月24日
 */
public interface SupplierEvaluateTemplateService extends Service {
	/**
	 * 供应商考评表模板查询
	 
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
	public List<SupplierEvaluateTemplate> loadSupplierEvaluateTemplateItem(String id);
	/**
	 * 根据ID号加载一个供应商考评表模板
	 *
	 * @param map
	 *				
	 * @return
	 */
	public SupplierEvaluateTemplate loadSupplierEvaluateTemplate(String id);
	/**
	 * 添加供应商考评表模板--商品
	 *
	 * @param map
	 *				
	 */
	public void addSupplierEvaluateTemplate(List<Object> list);
	/**
	 * 添加供应商考评表模板--辅料
	 *
	 * @param map
	 *				
	 */
	public void addAccessorySupplierEvaluateTemplate(List<Object> list);
	/**
	 * 删除供应商考评表模板
	 *
	 * @param map 
	 *				必须参数id为要删除的供应商考评表模板id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteSupplierEvaluateTemplate(List<Object> list);
	/**
	 * 修改供应商考评表模板
	 *
	 * @param map 
	 *				必须参数id为要修改供应商考评表模板的id号，不能为数组
	 */
	public void alterSupplierEvaluateTemplate(List<Object> list);
	/**
	 * 发布考评表模板
	 */
	public void updateSupplierEvaluateTemplate(List<Object> list);
	/**
	 * 关闭考评表模板
	 */
	public void updateSupplierEvaluateTemplateByTemplateCode(List<Object> list);
}
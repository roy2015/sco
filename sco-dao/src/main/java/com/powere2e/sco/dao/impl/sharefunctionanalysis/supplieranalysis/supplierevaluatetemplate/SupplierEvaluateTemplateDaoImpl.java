package com.powere2e.sco.dao.impl.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate.SupplierEvaluateTemplateDao;
import com.powere2e.sco.model.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate.SupplierEvaluateTemplate;

/**
 * 供应商考评表模板DAO接口的实现
 * @author caoliqiang
 * @version 1.0
 * @since 2015年4月24日
 */
public class SupplierEvaluateTemplateDaoImpl extends DaoImpl implements SupplierEvaluateTemplateDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5374353185921295933L;
	//查询考评模板
	@Override
	public List<SupplierEvaluateTemplate> listSupplierEvaluateTemplate(Map<String, Object> map,PageInfo pageInfo){
		return this.query(SupplierEvaluateTemplateDao.class, "searchSupplierEvaluateTemplate", map,pageInfo);
	}
	//添加供应商考评表模板--商品
	@Override
	public void insertSupplierEvaluateTemplate(Map<String, Object> map){
		this.insert(SupplierEvaluateTemplateDao.class, "saveSupplierEvaluateTemplate", map);
		this.insert(SupplierEvaluateTemplateDao.class, "saveSupplierEvaluateTemplateItems", map);
	}
	//添加供应商考评表模板--商品的考评项
	public void insertSupplierEvaluateTemplateItem(Map<String, Object> map){
		this.insert(SupplierEvaluateTemplateDao.class, "saveSupplierEvaluateTemplateItem", map);
	}
	//查看数据库中有没有TemplateCode存在
	public String selectTemplateCode(Map<String, Object> map){
		return (String)this.getSqlSession().selectOne("com.powere2e.sco.interfaces.dao.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate.SupplierEvaluateTemplateDao.selectTemplateCode", map);
	}
	//添加供应商考评表模板--辅料
	public void insertAccessorySupplierEvaluateTemplate(Map<String, Object> map){
		this.insert(SupplierEvaluateTemplateDao.class, "saveAccessorySupplierEvaluateTemplate", map);
		this.insert(SupplierEvaluateTemplateDao.class, "saveSupplierEvaluateTemplateItems", map);
	}
	//修改供应商考评表模板，用于更新考评模板
	@Override
	public void updateSupplierEvaluateTemplate(Map<String, Object> map){
		this.update(SupplierEvaluateTemplateDao.class, "updateSupplierEvaluateTemplate", map);
		this.delete(SupplierEvaluateTemplateDao.class, "deleteSupplierEvaluateTemplateItemByTemplateCode", map);
		this.insert(SupplierEvaluateTemplateDao.class, "saveSupplierEvaluateTemplateItems", map);
	}
	//删除考评项，用于更新考评模板
	public void deleteSupplierEvaluateTemplateItem(Map<String, Object> map){
		this.delete(SupplierEvaluateTemplateDao.class, "deleteSupplierEvaluateTemplateItem", map);
	}
	//装载一个供应商考评表模板
	@Override
	public List<SupplierEvaluateTemplate> loadSupplierEvaluateTemplateItem(Map<String, Object> map) {
		return this.query(SupplierEvaluateTemplateDao.class, "seeSupplierEvaluateItem", map,null);
	}
	//装载一个供应商考评表模板
	@Override
	public SupplierEvaluateTemplate loadSupplierEvaluateTemplate(Map<String, Object> map) {
		return (SupplierEvaluateTemplate) this.get(SupplierEvaluateTemplateDao.class, "seeSupplierEvaluateItem", map);
	}
	//发布考评表模板
	@Override
	public void publishSupplierEvaluateTemplate(Map<String,Object> map) {
		this.update(SupplierEvaluateTemplateDao.class, "publishSupplierEvaluateTemplate", map);
	}
	//关闭考评表模板
	@Override
	public void closeSupplierEvaluateTemplateByTemplateCode(Map<String, Object> map) {
		this.update(SupplierEvaluateTemplateDao.class, "closeSupplierEvaluateTemplate", map);
	}
	//删除考评表模板
	@Override
	public void deleteSupplierEvaluateTemplate(Map<String, Object> map){
		this.delete(SupplierEvaluateTemplateDao.class, "deleteSupplierEvaluateTemplate", map);
		this.delete(SupplierEvaluateTemplateDao.class, "deleteSupplierEvaluateTemplateItems", map);
	}
}
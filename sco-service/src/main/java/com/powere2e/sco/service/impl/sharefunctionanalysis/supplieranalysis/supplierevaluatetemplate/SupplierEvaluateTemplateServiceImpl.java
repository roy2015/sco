package com.powere2e.sco.service.impl.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate.SupplierEvaluateTemplateDao;
import com.powere2e.sco.interfaces.service.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate.SupplierEvaluateTemplateService;
import com.powere2e.sco.model.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate.SupplierEvaluateTemplate;
import com.powere2e.sco.service.impl.common.MasterDataTypeServiceImpl;

/**
 * 供应商考评表模板业务类的实现
 * 
 * @author caoliqiang
 * @version 1.0
 * @since 2015年4月24日
 */
public class SupplierEvaluateTemplateServiceImpl extends ServiceImpl implements SupplierEvaluateTemplateService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4925750258698948275L;
	private SupplierEvaluateTemplateDao supplierEvaluateTemplateDao;

	public static SupplierEvaluateTemplateService getInstance() {
		return (SupplierEvaluateTemplateService) ConfigFactory.getInstance().getBean("supplierEvaluateTemplateManager");
	}

	// 获得供应商考评表模板DAO实例
	public SupplierEvaluateTemplateDao getSupplierEvaluateTemplateDao() {
		return supplierEvaluateTemplateDao;
	}

	// 设置供应商考评表模板DAO实例
	public void setSupplierEvaluateTemplateDao(SupplierEvaluateTemplateDao supplierEvaluateTemplateDao) {
		this.supplierEvaluateTemplateDao = supplierEvaluateTemplateDao;
	}

	// 查询考评模板
	@Override
	public List<SupplierEvaluateTemplate> listSupplierEvaluateTemplate(Map<String, Object> map, PageInfo pageInfo) {
		return this.getSupplierEvaluateTemplateDao().listSupplierEvaluateTemplate(map, pageInfo);
	}

	// 添加考评模板-----商品
	@Override
	public void addSupplierEvaluateTemplate(List<Object> list) {
		List<SupplierEvaluateTemplate> supplierEvaluateTemplateList = new ArrayList<SupplierEvaluateTemplate>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 获得序列号
		String templateCode = MasterDataTypeServiceImpl.getInstance().nextID("S_SUPPLIER_EVALUATE_TEMPLATE");
		int num = 0;
		String str = (String) list.get(0);
		String[] strArr = str.split(",");
		for (int i = 0; i < strArr.length / 9; i++) {
			// 获得考评项序列号
			String evaluateItemCode = MasterDataTypeServiceImpl.getInstance().nextID("S_SUPPLIER_EVALUATE_ITEM");
			// 创建一个SupplierEvaluateTemplate对象
			SupplierEvaluateTemplate set = new SupplierEvaluateTemplate();
			set.setEvaluateItemCode(evaluateItemCode);
			set.setTemplateCode(templateCode);
			set.setTemplateName(strArr[num++]);
			try {
				set.setEvaluateStartDate(sdf.parse(strArr[num++]));
				set.setEvaluateEndDate(sdf.parse(strArr[num++]));
			} catch (Exception e) {
				e.printStackTrace();
			}
			set.setEvaluateFullScore(Double.parseDouble(strArr[num++]));
			set.setScore(Double.parseDouble(strArr[num - 1]) * Double.parseDouble(strArr[num + 1]) / 100);
			set.setEvaluateItemName(strArr[num++]);
			set.setWeight(Double.parseDouble(strArr[num++]));
			set.setDepartments(strArr[num++]);
			if ("null".equals(strArr[num++])) {
				set.setAccording(null);
			} else {
				set.setAccording(strArr[num - 1]);
			}
			if ("null".equals(strArr[num++])) {
				set.setStandard(null);
			} else {
				set.setStandard(strArr[num - 1]);
			}
			supplierEvaluateTemplateList.add(set);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("template", supplierEvaluateTemplateList);
		this.getSupplierEvaluateTemplateDao().insertSupplierEvaluateTemplate(map);
	}

	// 添加考评模板-----辅料
	@Override
	public void addAccessorySupplierEvaluateTemplate(List<Object> list) {
		List<SupplierEvaluateTemplate> supplierEvaluateTemplateList = new ArrayList<SupplierEvaluateTemplate>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 获得序列号
		String templateCode = MasterDataTypeServiceImpl.getInstance().nextID("S_SUPPLIER_EVALUATE_TEMPLATE");
		// 给字符串是两个分号的地方加上"null"值
		int num = 0;
		String str = (String) list.get(0);
		String[] strArr = str.split(",");
		for (int i = 0; i < strArr.length / 8; i++) {
			String evaluateItemCode = MasterDataTypeServiceImpl.getInstance().nextID("S_SUPPLIER_EVALUATE_ITEM");
			SupplierEvaluateTemplate set = new SupplierEvaluateTemplate();
			// 获得一个字符串

			set.setTemplateCode(templateCode);
			set.setEvaluateItemCode(evaluateItemCode);
			set.setTemplateName(strArr[num++]);
			try {
				set.setEvaluateStartDate(sdf.parse(strArr[num++]));
				set.setEvaluateEndDate(sdf.parse(strArr[num++]));
			} catch (Exception e) {
				e.printStackTrace();
			}
			set.setEvaluateFullScore(Double.parseDouble(strArr[num++]));
			set.setEvaluateItemName(strArr[num++]);
			set.setWeight(Double.parseDouble(strArr[num++]));
			set.setScore(Double.parseDouble(strArr[num - 3]) * Double.parseDouble(strArr[num - 1]) / 100);
			set.setAccording("null".equals(strArr[num++]) ? null : strArr[num - 1]);
			set.setStandard("null".equals(strArr[num++]) ? null : strArr[num - 1]);
			supplierEvaluateTemplateList.add(set);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("template", supplierEvaluateTemplateList);
		this.getSupplierEvaluateTemplateDao().insertAccessorySupplierEvaluateTemplate(map);
	}

	// 修改供应商考评表模板--
	@Override
	public void alterSupplierEvaluateTemplate(List<Object> list) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<SupplierEvaluateTemplate> supplierEvaluateTemplateList = new ArrayList<SupplierEvaluateTemplate>();
		int num = 0;
		String strArr = (String) list.get(0);
		String[] str = strArr.split(",");
		for (; num != str.length;) {
			SupplierEvaluateTemplate set = new SupplierEvaluateTemplate();
			String evaluateItemCode = MasterDataTypeServiceImpl.getInstance().nextID("S_SUPPLIER_EVALUATE_ITEM");
			set.setEvaluateItemCode(evaluateItemCode);
			set.setTemplateCode(str[num++]);
			set.setTemplateName(str[num++]);
			try {
				set.setEvaluateStartDate(sdf.parse(str[num++]));
				set.setEvaluateEndDate(sdf.parse(str[num++]));
			} catch (Exception e) {
				e.printStackTrace();
			}
			set.setEvaluateFullScore(Double.parseDouble(str[num++]));
			set.setEvaluateItemName(str[num++]);
			set.setWeight(Double.parseDouble(str[num++]));
			set.setScore(set.getEvaluateFullScore() * set.getWeight() / 100);
			if (str.length % 10 == 0) {
				set.setDepartments(str[num++]);
				set.setAccording("null".equals(str[num++]) ? null : str[num - 1]);
				set.setStandard("null".equals(str[num++]) ? null : str[num - 1]);
			}
			if (str.length % 9 == 0) {
				set.setAccording("null".equals(str[num++]) ? null : str[num - 1]);
				set.setStandard("null".equals(str[num++]) ? null : str[num - 1]);
			}
			supplierEvaluateTemplateList.add(set);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("template", supplierEvaluateTemplateList);
		this.getSupplierEvaluateTemplateDao().updateSupplierEvaluateTemplate(map);
	}

	// 查看、修改一个供应商考评表模板－－clare
	@Override
	public List<SupplierEvaluateTemplate> loadSupplierEvaluateTemplateItem(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("templateCode", id);
		return this.getSupplierEvaluateTemplateDao().loadSupplierEvaluateTemplateItem(map);
	}

	// 加载一个供应商考评表模板
	@Override
	public SupplierEvaluateTemplate loadSupplierEvaluateTemplate(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return this.getSupplierEvaluateTemplateDao().loadSupplierEvaluateTemplate(map);
	}

	// 发布考评表模板
	@Override
	public void updateSupplierEvaluateTemplate(List<Object> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("templateCode", list);
		this.getSupplierEvaluateTemplateDao().publishSupplierEvaluateTemplate(map);
	}

	// 关闭考评表模板
	@Override
	public void updateSupplierEvaluateTemplateByTemplateCode(List<Object> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("templateCode", list);
		this.getSupplierEvaluateTemplateDao().closeSupplierEvaluateTemplateByTemplateCode(map);
	}

	// 删除考评表模板
	@Override
	public void deleteSupplierEvaluateTemplate(List<Object> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("template", list);
		this.getSupplierEvaluateTemplateDao().deleteSupplierEvaluateTemplate(map);
	}
}
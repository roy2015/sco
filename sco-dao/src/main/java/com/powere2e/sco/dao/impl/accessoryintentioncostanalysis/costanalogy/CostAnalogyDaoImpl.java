package com.powere2e.sco.dao.impl.accessoryintentioncostanalysis.costanalogy;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryintentioncostanalysis.costanalogy.CostAnalogyDao;
import com.powere2e.sco.model.accessoryintentioncostanalysis.costanalogy.CostAnalogy;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.QuotedDetailForm;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.QuotedForm;

/**
 * 采购委员会OA申请DAO接口的实现
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月20日
 */
public class CostAnalogyDaoImpl extends DaoImpl implements CostAnalogyDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2191963660799254704L;

	// 新品引进OA申请意向品列表
	@Override
	public List<CostAnalogy> listCostAnalogyIntentionApplication(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(CostAnalogyDao.class, "listCostAnalogyIntentionApplication", map, pageInfo);
	}

	// 根据报价单编号获得采购委员会竞价单OA申请意向品
	@Override
	public CostAnalogy loadCostAnalogyIntentionApplication(Map<String, Object> map) {
		return (CostAnalogy) this.get(CostAnalogyDao.class, "loadCostAnalogyIntentionApplication", map);
	}

	@Override
	public void updateIntentionSupplierAccessory(Map<String, Object> map) {
		this.update(CostAnalogyDao.class, "updateIntentionSupplierAccessory", map);

	}

	@Override
	public List<QuotedForm> listQuotedForm(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(CostAnalogyDao.class, "listQuotedForm", map, pageInfo);
	}

	@Override
	public List<QuotedDetailForm> listQuotedDetailForm(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(CostAnalogyDao.class, "listQuotedDetailForm", map, pageInfo);
	}
}
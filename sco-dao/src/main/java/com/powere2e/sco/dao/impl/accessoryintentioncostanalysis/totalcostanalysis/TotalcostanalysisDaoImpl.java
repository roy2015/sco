package com.powere2e.sco.dao.impl.accessoryintentioncostanalysis.totalcostanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryintentioncostanalysis.totalcostanalysis.TotalcostanalysisDao;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.QuotedDetailForm;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.QuotedForm;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.Totalcostanalysis;

/**
 * 采购委员会OA申请DAO接口的实现
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月20日
 */
public class TotalcostanalysisDaoImpl extends DaoImpl implements TotalcostanalysisDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2191963660799254704L;

	// 新品引进OA申请意向品列表
	@Override
	public List<Totalcostanalysis> listTotalcostanalysisIntentionApplication(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(TotalcostanalysisDao.class, "listTotalcostanalysisIntentionApplication", map, pageInfo);
	}

	// 根据报价单编号获得采购委员会竞价单OA申请意向品
	@Override
	public Totalcostanalysis loadTotalcostanalysisIntentionApplication(Map<String, Object> map) {
		return (Totalcostanalysis)this.get(TotalcostanalysisDao.class, "loadTotalcostanalysisIntentionApplication", map);
	}

	@Override
	public void updateIntentionSupplierAccessory(Map<String, Object> map) {
		this.update(TotalcostanalysisDao.class, "updateIntentionSupplierAccessory", map);
		
	}

	@Override
	public List<QuotedForm> listQuotedForm(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(TotalcostanalysisDao.class, "listQuotedForm", map, pageInfo);
	}

	@Override
	public List<QuotedDetailForm> listQuotedDetailForm(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(TotalcostanalysisDao.class, "listQuotedDetailForm", map, pageInfo);
	}
}